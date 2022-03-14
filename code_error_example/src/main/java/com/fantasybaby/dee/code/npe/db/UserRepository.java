package com.fantasybaby.dee.code.npe.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Reid.liu
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
