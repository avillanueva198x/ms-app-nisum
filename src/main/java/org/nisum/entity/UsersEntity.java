package org.nisum.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

/**
 * Clase entidad de la tabla Users
 * @author Adolfo Villanueva
 */

@Entity
@Table(name = "Users")
@Data
public class UsersEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String name;
	@Column
	private String email;
	@Column
	private String password;
	@Column
	private String token;
	@Column
	private Boolean active;
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastLogin;
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date modified;
	
	@OneToMany(mappedBy = "idUser", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<PhoneEntity> phones;
    
	@Override
	public String toString() {
		return "UsersEntity [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", token="
				+ token + ", active=" + active + ", lastLogin=" + lastLogin + ", created=" + created + ", modified="
				+ modified + ", phones=" + phones + "]";
	}

}
