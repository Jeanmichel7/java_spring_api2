package fr.lde.apitest.presentationLayer.controler;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.lde.apitest.businessLayer.service.MessageService;
import fr.lde.apitest.domainLayer.model.Message;
import fr.lde.apitest.presentationLayer.dto.MessageDTO;
import fr.lde.apitest.presentationLayer.mapper.MessageMapper;
import fr.lde.apitest.presentationLayer.validator.CreateMessageDTO;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@Validated
@RequestMapping("/api")
public class MessageController {

  private MessageService messageService;
  private MessageMapper mapper;

  public MessageController(
      MessageService messageService,
      MessageMapper mapper) {
    this.messageService = messageService;
    this.mapper = mapper;
  }

  @GetMapping("/messages")
  @ResponseBody
  public String getMessages() {
    return messageService.getMessages();
  }

  @ResponseBody
  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping("/message/from/{userId}")
  public Message createMessage(
      @PathVariable final Long userId,
      @Valid @RequestBody CreateMessageDTO body) {
    return messageService.saveMessage(
        userId,
        mapper.toEntity(body));
    // body);
  }

}
