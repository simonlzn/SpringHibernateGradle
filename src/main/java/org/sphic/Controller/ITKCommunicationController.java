package org.sphic.Controller;

import org.sphic.Message.MessageQueue;
import org.sphic.util.PubSub;
import org.sphic.util.Subscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@RestController
@RequestMapping("/itk")
@Component
public class ITKCommunicationController {
	private final Queue<DeferredResult<String>> responseBodyQueue = new ConcurrentLinkedQueue<DeferredResult<String>>();
	private Subscriber subscriber;
	private MessageQueue messageQueue;

	@Autowired
	public ITKCommunicationController(MessageQueue messageQueue) {
		this.messageQueue = messageQueue;
	}

	@RequestMapping("/call")
	public DeferredResult<String> Info(@RequestParam(value = "func", defaultValue = "")String func) {
		messageQueue.Send(func);
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