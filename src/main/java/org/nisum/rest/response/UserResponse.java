package org.nisum.rest.response;

import java.io.Serializable;
import java.util.List;

import org.nisum.dto.PhoneDto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(description = "Bean de usuario")
public class UserResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;
	private String email;
	private String password;
	private String token;
	private Boolean active;
	private String lastLogin;
	private String created;
	private String modified;
    private List<PhoneDto> phones;
    
	@Override
	public String toString() {
		return "UserResponse [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", token="
				+ token + ", active=" + active + ", lastLogin=" + lastLogin + ", created=" + created + ", modified="
				+ modified + ", phones=" + phones + "]";
	}


}
