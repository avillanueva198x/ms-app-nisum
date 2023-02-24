package org.nisum.repository;

import org.nisum.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Adolfo Villanueva
 * @version 22/03/2023
 */
@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, Long> {

	UsersEntity findByEmail(String email);
}