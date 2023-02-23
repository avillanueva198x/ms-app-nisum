package org.nisum.rest.response;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(description = "Bean de creación de usuario")
public class CreateResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;
	@Temporal(TemporalType.TIMESTAMP)
	private Date modified;
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastLogin;
	private String token;
	private Boolean active;

	@Override
	public String toString() {
		return "CreateResponse [id=" + id + ", created=" + created + ", modified=" + modified + ", lastLogin=" + lastLogin
				+ ", token=" + token + ", active=" + active + "]";
	}

}
