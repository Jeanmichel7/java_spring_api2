package fr.lde.apitest.businessLayer.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import fr.lde.apitest.domainLayer.model.Employee;
import fr.lde.apitest.domainLayer.model.Message;
import fr.lde.apitest.domainLayer.repository.EmployeeRepository;
import fr.lde.apitest.domainLayer.repository.MessageRepository;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Service
@Slf4j
public class MessageService {

  private MessageRepository messageRepository;
  private EmployeeRepository employeeRepository;
  // private EmployeeService employeeService;

  public MessageService(
      MessageRepository messageRepository,
      EmployeeService employeeService,
      EmployeeRepository employeeRepository) {
    this.messageRepository = messageRepository;
    this.employeeRepository = employeeRepository;
    // this.employeeService = employeeService;
  }

  public String getMessages() {
    return "message ici";
  }

  @Transactional
  public Message saveMessage(long userId, Message message) {
    log.info("message : " + message);
    Optional<Employee> author = employeeRepository.findById(userId);
    if (!author.isPresent())
      throw new RuntimeException("Employee not found with id " + userId);

    log.info("author : " + author);

    Employee getAuthor = author.get();
    // author.getMessages().add(message);
    message.setAuthor(getAuthor);

    Message savedMessage = messageRepository.save(message);
    // author.save
    return savedMessage;
  }

}
