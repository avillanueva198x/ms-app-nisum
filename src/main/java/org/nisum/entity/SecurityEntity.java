package org.nisum.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * Clase entidad de la security para validar tokens
 * 
 * @author Adolfo Villanueva
 */

@Entity
@Table(name = "Security")
@Data
public class SecurityEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String username;
	@Column
	private String password;

	@Override
	public String toString() {
		return "SecurityEntity [id=" + id + ", username=" + username + ", password=" + password + "]";
	}

}
