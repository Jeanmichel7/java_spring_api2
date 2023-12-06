package fr.lde.apitest.validator;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateEmployeeDTO {
  @Size(min = 2, max = 32, message = "La longueur du nom doit être entre 2 et 32 caractères")
  private String firstName;

  @Size(min = 2, max = 32, message = "La longueur du prénom doit être entre 2 et 32 caractères")
  private String lastName;

  @Email(message = "L'email doit être valide")
  private String email;

  @Size(min = 8, max = 32, message = "La longueur du mot de passe doit être entre 8 et 32 caractères")
  @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$", message = "Le mot de passe doit contenir au moins une lettre minuscule, une lettre majuscule et un chiffre")
  private String password;
}
