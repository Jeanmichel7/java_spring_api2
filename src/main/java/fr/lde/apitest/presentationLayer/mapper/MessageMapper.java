package fr.lde.apitest.presentationLayer.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import fr.lde.apitest.domainLayer.model.Message;
import fr.lde.apitest.presentationLayer.dto.MessageDTO;
import fr.lde.apitest.presentationLayer.validator.CreateMessageDTO;

@Mapper(componentModel = "spring")
public interface MessageMapper {

  MessageDTO toDto(Message message);

  Message toEntity(CreateMessageDTO messageDTO);

  Message toEntity(MessageDTO messageDTO);
}
