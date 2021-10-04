package com.example.demo_security.repository;

import com.example.demo_security.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,String> {
}
