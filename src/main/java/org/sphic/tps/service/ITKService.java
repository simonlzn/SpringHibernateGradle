package org.sphic.tps.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.sphic.tps.message.MessageQueue;
import org.sphic.tps.util.PubSub;
import org.sphic.tps.util.Subscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.HashMap;
import java.util.Map;

@Service
public class ITKService {
    private MessageQueue messageQueue;

    @Autowired
    public ITKService(MessageQueue messageQueue) {
        this.messageQueue = messageQueue;
    }

    public DeferredResult Reconstruct(String key, String id, String path){
        Map params = new HashMap();
        params.put("folderPath", path);
        params.put("key", key);
        params.put("id", id);
        params.put("func", "reconstruct");
        try {
            messageQueue.Send(new ObjectMapper().writeValueAsString(params), id);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ConstructResult(key);
    }

    public DeferredResult Slicing(String key, String id, String views){
        Map params = new HashMap();
        params.put("views", views);
        params.put("key", key);
        params.put("id", id);
        params.put("func", "slicing");
        try {
            messageQueue.Send(new ObjectMapper().writeValueAsString(params), id);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ConstructResult(key);
    }

    private DeferredResult ConstructResult(final String key) {
        final DeferredResult result = new DeferredResult();


        final Subscriber subscriber =
                new Subscriber(key){
                    @Override
                    public void Callback(Object message){
                        try {
                            result.setResult(message);
                        }catch (Exception e){
                            result.setErrorResult(e);
                        }finally {
                            PubSub.Unsubscribe(this.channel, this);
                        }
                    }
                };

        result.onTimeout(new Runnable() {
            @Override
            public void run() {
                result.setErrorResult("Time out");
                PubSub.Unsubscribe(key, subscriber);
            }
        });

        PubSub.Subscribe(key, subscriber);
        return result;
    }
}

