package com.hygieia.Project.Hygieia.repository;

import com.hygieia.Project.Hygieia.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByEmail(String email);

    User findUserById(Long id);

}