package org.sphic.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.sphic.Message.MessageQueue;
import org.sphic.util.PubSub;
import org.sphic.util.Subscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/itk")
@Component
public class ITKCommunicationController {
	private Subscriber subscriber;
	private MessageQueue messageQueue;

	@Autowired
	public ITKCommunicationController(MessageQueue messageQueue) {
		this.messageQueue = messageQueue;
	}

//	@RequestMapping(value = "/call", method = RequestMethod.GET)
//	public DeferredResult<String> Info(@RequestParam(value = "func" , defaultValue = "" ) String func) throws JsonProcessingException {
//		messageQueue.Send(new ObjectMapper().writeValueAsString(func));
//		DeferredResult<String> result = new DeferredResult<String>();
//		subscriber =
//		new Subscriber(){
//			@Override
//			public void Callback(String message){
//				result.setResult(message);
//				PubSub.Unsubscribe("rabbitmq", subscriber);
//			}
//		};
//		PubSub.Subscribe("rabbitmq", subscriber);
//		return result;
//	}

	@RequestMapping(value = "/call", method = RequestMethod.GET)
	public DeferredResult<String> Info(@RequestParam Map func, final HttpServletRequest request, final HttpServletResponse response) throws JsonProcessingException {
		System.out.println(func);
		if (func.get("func").toString().compareTo("slicing") == 0)
			response.setHeader("Cache-Control", "private, max-age=86400");
		messageQueue.Send(new ObjectMapper().writeValueAsString(func));

		final DeferredResult<String> result = new DeferredResult<String>();
		String channel = request.getRequestURI();
		subscriber =
			new Subscriber(channel){
				@Override
				public void Callback(String message){
					try {
						result.setResult(message);
					}catch (Exception e){
						result.setErrorResult(e);
					}finally {
						PubSub.Unsubscribe(this.channel, subscriber);
					}
				}
			};
		PubSub.Subscribe(channel, subscriber);
		return result;
	}

	// $.ajax({url: 'http://localhost:8080/itk/call?func=render', type:'POST', data:  {seeds : ["(1,1,1)","(2,2,2)","(3,3,3)"]}, success:function(ret){console.log(ret)}})
	@RequestMapping(value = "/call", method = RequestMethod.POST)
	public DeferredResult<String> Info(@RequestParam(value = "func" , defaultValue = "" ) String func, final HttpServletRequest request, @RequestParam(value = "seeds[]" , defaultValue = "" ) String[] seeds) throws JsonProcessingException {
		HashMap params = new HashMap<String, List<String>>();
		params.put("func", func);
		params.put("seeds", seeds);
		String channel = request.getRequestURI();
		messageQueue.Send(new ObjectMapper().writeValueAsString(params));
		final DeferredResult<String> result = new DeferredResult<String>();
		subscriber =
				new Subscriber(channel){
					@Override
					public void Callback(String message){
						result.setResult(message);
						PubSub.Unsubscribe(this.channel, subscriber);
					}
				};
		PubSub.Subscribe(channel, subscriber);
		return result;
	}

}