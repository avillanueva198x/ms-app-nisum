package org.nisum.rest.response;

import java.io.Serializable;
import java.util.List;

import org.nisum.dto.PhoneDto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(description = "Bean de usuario")
public class UserResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	@JsonIgnoreProperties(ignoreUnknown = true)
	private String name;
	@JsonIgnoreProperties(ignoreUnknown = true)
	private String email;
	@JsonIgnoreProperties(ignoreUnknown = true)
	private String password;
	@JsonIgnoreProperties(ignoreUnknown = true)
	private String token;
	@JsonIgnoreProperties(ignoreUnknown = true)
	private Boolean active;
	@JsonIgnoreProperties(ignoreUnknown = true)
	private String lastLogin;
	@JsonIgnoreProperties(ignoreUnknown = true)
	private String created;
	@JsonIgnoreProperties(ignoreUnknown = true)
	private String modified;
	@JsonIgnoreProperties(ignoreUnknown = true)
    private List<PhoneDto> phones;
    
	@Override
	public String toString() {
		return "UserResponse [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", token="
				+ token + ", active=" + active + ", lastLogin=" + lastLogin + ", created=" + created + ", modified="
				+ modified + ", phones=" + phones + "]";
	}


}
