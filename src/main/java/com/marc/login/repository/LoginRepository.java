package com.marc.login.repository;

import com.marc.login.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LoginRepository extends JpaRepository<Login, Long> {
    @Query("from Login l where l.username=:username and l.password=:password")
    Login findByUserandPass(@Param("username") String username, @Param("password") String password);
}
