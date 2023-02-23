package org.nisum.repository;

import java.util.List;

import org.nisum.entity.PhoneEntity;
import org.nisum.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Adolfo Villanueva
 * @version 22/03/2023
 */
public interface PhoneRepository extends JpaRepository<PhoneEntity, Long> {
	
	List<PhoneEntity> findByIdUser(UsersEntity user);

}