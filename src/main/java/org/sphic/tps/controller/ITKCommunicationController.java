package org.sphic.tps.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.sphic.tps.service.ITKService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@RestController
@RequestMapping("/itk")
@Component
public class ITKCommunicationController {
    private ITKService itkService;

    @Autowired
    public ITKCommunicationController(ITKService itkService) {
        this.itkService = itkService;
    }

    @RequestMapping(value = "/call", method = RequestMethod.GET)
    public DeferredResult Info(@RequestParam Map func, final HttpServletRequest request) throws JsonProcessingException {
        String channel = request.getRequestURI() + "?" + request.getQueryString();
        if (func.containsKey("func")) {
            if (func.get("func").toString().equals("reconstruct")) {
                return itkService.Reconstruct(channel, func.get("id").toString(), func.get("folderPath").toString());
            } else if (func.get("func").toString().equals("slicing")) {
                return itkService.Slicing(channel, func.get("id").toString(), func.get("views").toString(), func.get("folderPath").toString());
            }
            else if (func.get("func").toString().equals("reconstruct_structure")) {
                return itkService.Slicing(channel, func.get("id").toString(), func.get("coord").toString(), func.get("folderPath").toString());
            }
        }

        return null;
    }

    @RequestMapping(value = "/call", method = RequestMethod.POST)
    public DeferredResult Post(@RequestBody Map func, final HttpServletRequest request) throws JsonProcessingException {
        String channel;
        if (request.getQueryString() == null)
            channel = request.getRequestURI() + func.hashCode();
        else
            channel = request.getRequestURI() + "?" + request.getQueryString();

        if (func.containsKey("func")) {
            if (func.get("func").toString().equals("reconstruct_structure")) {
                return itkService.ReconstructStructure(channel, func.get("id").toString(), Integer.parseInt(func.get("structureId").toString()), func.get("coord").toString(), func.get("folderPath").toString());
            } else if (func.get("func").toString().equals("slicing_structure")) {
                return itkService.SlicingStructure(channel, func.get("id").toString(), Integer.parseInt(func.get("structureId").toString()), func.get("slice_indices").toString(), func.get("folderPath").toString());
            }else if (func.get("func").toString().equals("region_growing_segmentation")) {
                String template;
                boolean isHoleFilling;
                String boundingBox;

                int multiplier = 0;
                int iterations = 0;
                int initialNeighborhoodRadius = 0;
                if (func.get("template") == null) {
                    template = "";
                    if (func.get("multiplier") != null)
                        multiplier = Integer.parseInt(func.get("multiplier").toString());

                    if (func.get("number_of_iterations") != null)
                        iterations = Integer.parseInt(func.get("number_of_iterations").toString());

                    if (func.get("initial_neighborhood_radius") != null)
                        initialNeighborhoodRadius = Integer.parseInt(func.get("initial_neighborhood_radius").toString());
                }
                else {
                    template = func.get("template").toString();
                    multiplier = 0;
                    iterations=0;
                    initialNeighborhoodRadius = 0;
                }
                isHoleFilling = func.get("is_hole_filling") == null ? false : Boolean.parseBoolean(func.get("is_hole_filling").toString());
                boundingBox = func.get("bounding_box")== null? "": func.get("bounding_box").toString();
                return itkService.RegionGrowingSegmentation(channel, func.get("id").toString(), Integer.parseInt(func.get("structureId").toString()), func.get("seeds").toString(), func.get("folderPath").toString(), template, isHoleFilling, boundingBox, multiplier,iterations,initialNeighborhoodRadius);
            }else if (func.get("func").toString().equals("contour_interpolation")) {
                return itkService.ContourInterpolation(channel, func.get("id").toString(),func.get("folderPath").toString(), Integer.parseInt(func.get("start_slice_id").toString()), Integer.parseInt(func.get("end_slice_id").toString()), func.get("interpolated_slice_id").toString(),func.get("start_slice_data").toString(),func.get("end_slice_data").toString());
            }

            else if (func.get("func").toString().equals("live_wire_preprocessing")) {
                return itkService.LiveWirePreprocessing(channel, func.get("id").toString(),func.get("folderPath").toString(), Integer.parseInt(func.get("slice_index").toString()));
            }

            else if (func.get("func").toString().equals("live_wire")) {
                return itkService.LiveWire(channel, func.get("id").toString(),func.get("folderPath").toString(), Integer.parseInt(func.get("slice_index").toString()), func.get("anchor_point").toString(), func.get("free_point").toString());
            }
        }
        return null;
    }

    // $.ajax({url: 'http://localhost:8080/itk/call?func=render', type:'POST', data:  {seeds : ["(1,1,1)","(2,2,2)","(3,3,3)"]}, success:function(ret){console.log(ret)}})
//	@RequestMapping(value = "/call", method = RequestMethod.POST)
//	public DeferredResult<String> Info(@RequestParam(value = "func" , defaultValue = "" ) String func, final HttpServletRequest request, @RequestParam(value = "seeds[]" , defaultValue = "" ) String[] seeds) throws JsonProcessingException {
//		HashMap params = new HashMap<String, List<String>>();
//		params.put("func", func);
//		params.put("seeds", seeds);
//		String channel = request.getRequestURI();
//		messageQueue.Send(new ObjectMapper().writeValueAsString(params), "2");
//		final DeferredResult<String> result = new DeferredResult<String>();
//		subscriber =
//				new Subscriber(channel){
//					@Override
//					public void Callback(String message){
//						result.setResult(message);
//						PubSub.Unsubscribe(this.channel, subscriber);
//					}
//				};
//		PubSub.Subscribe(channel, subscriber);
//		return result;
//	}

}
