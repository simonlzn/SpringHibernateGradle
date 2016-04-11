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

    public DeferredResult Slicing(String key, String id, String views, String path){
        Map params = new HashMap();
        params.put("folderPath", path);
        params.put("slice_indices", views);
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

    public DeferredResult ReconstructStructure(String key, String id, int structureId, String coord, String path){
        Map params = new HashMap();
        params.put("key", key);
        params.put("id", id);
        params.put("folderPath", path);
        params.put("func", "reconstruct_structure");
        params.put("structure_id", structureId);
        params.put("coord", coord);

        try {
            messageQueue.Send(new ObjectMapper().writeValueAsString(params), id);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ConstructResult(key);
    }

    public DeferredResult SlicingStructure(String key, String id, int structureId, String sliceIndices, String path){
        Map params = new HashMap();
        params.put("key", key);
        params.put("id", id);
        params.put("folderPath", path);
        params.put("func", "slicing_structure");
        params.put("structure_id", structureId);
        params.put("slice_indices", sliceIndices);

        try {
            messageQueue.Send(new ObjectMapper().writeValueAsString(params), id);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ConstructResult(key);
    }

    public DeferredResult RegionGrowingSegmentation(String key, String id, int structureId, String seeds, String path, String template, boolean isHoleFilling, String boundingBox, int multiplier, int nrOfIterations, int initialNeighborhoodRadius){
        Map params = new HashMap();
        params.put("key", key);
        params.put("id", id);
        params.put("folderPath", path);
        params.put("func", "region_growing_segmentation");
        params.put("structure_id", structureId);
        params.put("seeds", seeds);

        if (!template.isEmpty()){
            params.put("template", template);
        }else {
            if (multiplier!=0)
                params.put("multiplier", multiplier);
            if (nrOfIterations!=0)
                params.put("number_of_iterations", nrOfIterations);
            if (initialNeighborhoodRadius!=0)
                params.put("initial_neighborhood_radius", initialNeighborhoodRadius);
        }

        if (isHoleFilling)
            params.put("is_hole_filling", true);

        if (!boundingBox.isEmpty())
            params.put("bounding_box", boundingBox);

        try {
            messageQueue.Send(new ObjectMapper().writeValueAsString(params), id);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ConstructResult(key);
    }

    public DeferredResult ContourInterpolation(String key, String id, String path, int startSliceId, int endSliceId, String interpolatedSliceId, String startSliceData, String endSliceData){
        Map params = new HashMap();
        params.put("key", key);
        params.put("id", id);
        params.put("folderPath", path);
        params.put("func", "contour_interpolation");
        params.put("start_slice_id", startSliceId);
        params.put("end_slice_id", endSliceId);
        params.put("interpolated_slice_id", interpolatedSliceId);
        params.put("start_slice_data", startSliceData);
        params.put("end_slice_data", endSliceData);

        try {
            messageQueue.Send(new ObjectMapper().writeValueAsString(params), id);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ConstructResult(key);
    }

    public DeferredResult LiveWirePreprocessing(String key, String id, String path, int sliceIndex){
        Map params = new HashMap();
        params.put("key", key);
        params.put("id", id);
        params.put("folderPath", path);
        params.put("func", "live_wire_preprocessing");
        params.put("slice_index", sliceIndex);

        try {
            messageQueue.Send(new ObjectMapper().writeValueAsString(params), id);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ConstructResult(key);
    }

    public DeferredResult LiveWire(String key, String id, String path, int sliceIndex, String anchorPoint, String freePoint){
        Map params = new HashMap();
        params.put("key", key);
        params.put("id", id);
        params.put("folderPath", path);
        params.put("func", "live_wire");
        params.put("slice_index", sliceIndex);
        params.put("anchor_point", anchorPoint);
        params.put("free_point", freePoint);

        try {
            messageQueue.Send(new ObjectMapper().writeValueAsString(params), id);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ConstructResult(key);
    }

    private DeferredResult ConstructResult(final String key) {
        final DeferredResult result = new DeferredResult((long) 180000);

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

