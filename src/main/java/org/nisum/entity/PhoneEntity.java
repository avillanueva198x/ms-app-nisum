package org.nisum.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import lombok.Data;

/**
 * Clase entidad de la tabla Phone
 * @author Adolfo Villanueva
 */

@Entity
@Table(name = "Phone")
@Data
public class PhoneEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPhone;

    @JoinColumn(name = "idUser")
    private Long idUser;
	
	@Column
	private String number;
	@Column
	private String citycode;
	@Column
	private String contrycode;

	@Override
	public String toString() {
		return "PhoneEntity [id=" + idPhone + ", idUser=" + idUser + ", number=" + number + ", citycode=" + citycode
				+ ", contrycode=" + contrycode + "]";
	}

}
