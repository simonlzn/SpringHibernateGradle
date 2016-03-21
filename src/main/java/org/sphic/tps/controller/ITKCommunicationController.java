package org.sphic.tps.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.sphic.tps.service.ITKService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
                return itkService.Slicing(channel, func.get("id").toString(), func.get("views").toString());
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
