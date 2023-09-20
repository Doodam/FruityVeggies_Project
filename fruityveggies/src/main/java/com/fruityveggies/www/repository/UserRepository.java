package com.fruityveggies.www.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fruityveggies.www.domain.UserDomain;


@Repository
public interface UserRepository extends CrudRepository<UserDomain, String> {
    
    Optional<UserDomain> findByEmailAndOauthType(String email, String oauthType);
    
}
