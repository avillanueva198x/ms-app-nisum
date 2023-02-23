package org.nisum.rest.request;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel
public class PhoneRequest  implements Serializable{
	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Número de teléfono necesario")
	@Pattern(regexp = "^\\+?51\\d{9}$", message = "Número de teléfono inválido")
	@JsonProperty(value = "number", required = true)
	private String number;
	private String citycode;
	private String contrycode;

	@Override
	public String toString() {
		return "PhoneDto [number=" + number + ", citycode=" + citycode + ", contrycode=" + contrycode + "]";
	}

}
