package org.sphic.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.sphic.Message.MessageQueue;
import org.sphic.util.PubSub;
import org.sphic.util.Subscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

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

	@RequestMapping("/call")
	public DeferredResult<String> Info(@RequestParam(defaultValue = "" ) Map<String, String> params) throws JsonProcessingException {
		messageQueue.Send(new ObjectMapper().writeValueAsString(params));
		DeferredResult<String> result = new DeferredResult<String>();
		subscriber =
		new Subscriber(){
			@Override
			public void Callback(String message){
				result.setResult(message);
				PubSub.Unsubscribe("rabbitmq", subscriber);
			}
		};
		PubSub.Subscribe("rabbitmq", subscriber);
		return result;
	}

}