package com.fruityveggies.www.repository.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByUsername(String username);
    Member findByEmail(String email);
    
    
    @Modifying
    @Query("UPDATE Member u SET u.password = :password WHERE u.email = :email")
    void updateMemberPassword(@Param("email") String email, @Param("password") String password);

    
}
