package fr.lde.apitest.presentationLayer.controler;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fr.lde.apitest.businessLayer.service.MessageService;
import fr.lde.apitest.domainLayer.model.Message;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@Validated
@RequestMapping("/api")
public class MessageController {

  private MessageService messageService;

  public MessageController(MessageService messageService) {
    this.messageService = messageService;
  }

  @GetMapping("/messages")
  @ResponseBody
  public String getMessages() {
    return messageService.getMessages();
  }

  @ResponseBody
  @PostMapping("/message/from/{userId}")
  public Message postMethodName(
      @PathVariable final Long userId,
      @RequestBody Message body) {
    return messageService.saveMessage(userId, body);
  }

}
