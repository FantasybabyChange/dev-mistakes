package com.fantasybaby.dev.error.security.sensitivedata.storeidcard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CipherRepository extends JpaRepository<CipherData, Long> {
}
