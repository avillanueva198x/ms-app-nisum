package org.nisum.rest.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Bean de Error genérico")
public class GenericErrorResponse{

	@ApiModelProperty(value = "mensaje", example = "mensaje de error")
	String mensaje;


	public GenericErrorResponse() {
		super();
	}

	public GenericErrorResponse(String mensaje) {
		super();
		this.mensaje = mensaje;
	}

	@Override
	public String toString() {
		return "GenericErrorResponse [mensaje=" + mensaje + "]";
	}

}
