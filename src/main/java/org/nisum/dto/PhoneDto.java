package org.nisum.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel
public class PhoneDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private String number;
	private String citycode;
	private String contrycode;

	public PhoneDto() {
		super();
	}

	public PhoneDto(String number, String citycode, String contrycode) {
		super();
		this.number = number;
		this.citycode = citycode;
		this.contrycode = contrycode;
	}
	
	@Override
	public String toString() {
		return "PhoneDto [number=" + number + ", citycode=" + citycode + ", contrycode=" + contrycode + "]";
	}

}
