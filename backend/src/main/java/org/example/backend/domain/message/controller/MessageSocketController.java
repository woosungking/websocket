package org.example.backend.domain.message.controller;

import org.example.backend.domain.message.dto.RequestMessage;
import org.example.backend.domain.message.dto.ResponseMessage;
import org.example.backend.domain.message.service.MessageService;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageSocketController {
	private final MessageService messageService;

	public MessageSocketController(MessageService messageService) {
		this.messageService = messageService;
	}

	@MessageMapping("/{roomId}") // 즉 얘는 수신기의 역할
	//StompConfig에서 설정한 /send 뒤에 붙음, 그럼 최종 결과물은 /send/{roomId} 가 되겠지 ?
	//일반적인 컨트롤러 클레스에 RequestMapping("/api/vi") 해놓고 매서드에 PostMapping("/create")를 한다고 가정라면
	//최종적 으로 프론트에서 요청하는 url은 /api/v1/create 가 되는거라고 생각하면 된다.
	@SendTo("/topic/{roomId}") // 얘는 발신기의 역할
	// @SendTo("/queue/room/{roomId}") // enableStompBroker에서 /topic과 /queue를 설정을 해둬서 둘중 하나 골라써야함, 이건 컨벤션에 불과하니 참고~
	public ResponseMessage enter(@DestinationVariable("roomId") Long roomId, RequestMessage message){
	messageService.saveMessage(message.getContent(), message.getSender(),roomId);
		ResponseMessage responseMessage = new ResponseMessage();
		responseMessage.setContent(message.getContent());
		responseMessage.setSender(message.getSender());
	return responseMessage;
	};

	@MessageMapping("/realtime")
	@SendTo("/room/realtime")
	public ResponseMessage RealTimeMessage(ResponseMessage message){ResponseMessage responseMessage = new ResponseMessage();
		responseMessage.setContent(message.getContent());
		responseMessage.setSender(message.getSender());
		return responseMessage;
	}
}
