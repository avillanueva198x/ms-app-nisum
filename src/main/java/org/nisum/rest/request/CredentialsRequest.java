package org.nisum.rest.request;

import lombok.Data;

@Data
public class CredentialsRequest {
	
	private String username;
	private String password;

}
