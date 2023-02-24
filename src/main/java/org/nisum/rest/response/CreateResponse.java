package org.nisum.rest.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(description = "Bean de creación de usuario")
public class CreateResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonIgnoreProperties(ignoreUnknown = true)
	private Long id;
	@JsonIgnoreProperties(ignoreUnknown = true)
	private String created;
	@JsonIgnoreProperties(ignoreUnknown = true)
	private String modified;
	@JsonIgnoreProperties(ignoreUnknown = true)
	private String lastLogin;
	@JsonIgnoreProperties(ignoreUnknown = true)
	private String token;
	@JsonIgnoreProperties(ignoreUnknown = true)
	private Boolean active;

	@Override
	public String toString() {
		return "CreateResponse [id=" + id + ", created=" + created + ", modified=" + modified + ", lastLogin="
				+ lastLogin + ", token=" + token + ", active=" + active + "]";
	}

}
