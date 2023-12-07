package fr.lde.apitest.businessLayer.service;

import org.springframework.stereotype.Service;

import fr.lde.apitest.domainLayer.model.Employee;
import fr.lde.apitest.domainLayer.model.Message;
import fr.lde.apitest.domainLayer.repository.MessageRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Service
@Slf4j
public class MessageService {

  private MessageRepository messageRepository;
  private EmployeeService employeeService;

  public MessageService(
      MessageRepository messageRepository,
      EmployeeService employeeService) {
    this.messageRepository = messageRepository;
    this.employeeService = employeeService;
  }

  public String getMessages() {
    return "message ici";
  }

  // coucou
  public Message saveMessage(long userId, Message message) {
    Employee author = employeeService.getEmployeeById(userId);
    log.info("message : " + message);
    log.info("author : " + author);

    message.setAuthor(author);

    Message savedMessage = messageRepository.save(message);
    return savedMessage;
  }

}
