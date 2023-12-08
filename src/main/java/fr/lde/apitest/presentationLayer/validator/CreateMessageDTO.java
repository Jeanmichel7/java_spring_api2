package fr.lde.apitest.presentationLayer.validator;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateMessageDTO {
  @NotNull(message = "Le message ne peut pas être null")
  @Size(min = 2, message = "La longueur du message doit être de plus de 2 caractères")
  private String message;
}
