package com.spring.mathapp.repositories;

import com.spring.mathapp.models.User;
import com.spring.mathapp.models.Details;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    User findUserByEmail(@Param("email") String email);

    @Query("SELECT u FROM User u WHERE u.userName = ?1")
    User findUserByUserName(@Param("userName") String userName);

    User findUserByDetails(Details details);

    List<User> findUserByUserNameContainsOrFirstNameContainsOrLastNameContaining(String userName, String fName, String lName);


}
