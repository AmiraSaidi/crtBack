package com.amira.crtBack.repository;

import com.amira.crtBack.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //@Query("DELETE FROM User WHERE email = :email")
    //void deleteByEmail(@Param("email") String email);

    @Query("SELECT u.password FROM User u WHERE u.email = :email")
    String findPasswordByEmail(@Param("email") String email);
}
