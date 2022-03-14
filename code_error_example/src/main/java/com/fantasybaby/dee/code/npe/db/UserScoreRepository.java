package com.fantasybaby.dee.code.npe.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Reid.liu
 */
@Repository
public interface UserScoreRepository extends JpaRepository<UserScore, Long> {
    @Query(nativeQuery = true, value = "SELECT SUM(score) FROM `user_score`")
    Long wrong1();

    @Query(nativeQuery = true, value = "SELECT COUNT(score) FROM `user_score`")
    Long wrong2();

    @Query(nativeQuery = true, value = "SELECT * FROM `user_score` WHERE score=null")
    List<User> wrong3();

    @Query(nativeQuery = true, value = "SELECT IFNULL(SUM(score),0) FROM `user_score`")
    Long right1();

    @Query(nativeQuery = true, value = "SELECT COUNT(*) FROM `user_score`")
    Long right2();

    @Query(nativeQuery = true, value = "SELECT * FROM `user_score` WHERE score IS NULL")
    List<UserScore> right3();
}
