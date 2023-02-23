package org.nisum.rest.request;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * Esta clase define los atributos con el que se creará un usuario y contiene validación de campos
 * @author: Adoolfo Villanueva
 * @version: 21/02/2023
 */
@Data
@ApiModel
public class UserRequest implements Serializable{
	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Nombre del usuario necesario")
    @JsonProperty(value = "name", required = true)
	private String name;
	
	@NotEmpty(message = "email necesario")
    @Email(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "email inválido")
    @JsonProperty(value = "email", required = true)
    private String email;
	
	/**
	 * El atributo del password tiene una validación de expresión regular para validar ciertos criterios de seguridad, para este ejemplo 
	   se tiene definido estas reglas: 
		(?=.*[0-9]): debe incluir al menos un número.
		(?=.*[a-z]): debe incluir al menos una letra minúscula.
		(?=.*[A-Z]): debe incluir al menos una letra mayúscula.
		(?=.*[@#$%^&+=!]): debe incluir al menos uno de los caracteres especiales (@#$%^&+=!).
		(?=\S+$): no debe contener espacios en blanco.
		.{8,}: debe tener una longitud mínima de 8 caracteres.
	 * @author: Adoolfo Villanueva
	 * @version: 21/02/2023
	 */	
    @JsonProperty(value = "password", required = true)
	@NotEmpty(message = "Password necesario")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$", message = "La contraseña no cumple con los requisitos de seguridad")
    private String password;

	@NotEmpty(message = "Lista de teléfonos necesario")
    @JsonProperty(value = "phones", required = true)
	
    private List<PhoneRequest> phones;

	@Override
	public String toString() {
		return "UserRequest [name=" + name + ", email=" + email + ", password=" + password + ", phones=" + phones
				+ "]";
	}

}
