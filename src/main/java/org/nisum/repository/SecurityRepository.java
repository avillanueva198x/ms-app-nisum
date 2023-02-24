package org.nisum.repository;

import org.nisum.entity.SecurityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Adolfo Villanueva
 * @version 22/02/2023
 */
@Repository
public interface SecurityRepository extends JpaRepository<SecurityEntity, Long> {

	@Query(value = "SELECT count(id) FROM Security WHERE username = :username AND password = :password", nativeQuery = true)
	int existUserPassword(@Param("username") String username, @Param("password") String password);

}