package com.company.dao.inter;

import com.company.dao.impl.UserRepositoryCustom;
import com.company.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Integer>, UserRepositoryCustom {
    User findById(int id);
    User findByName(String name);
    User findByNameAndSurname(String name,String surname);

//    @Query(value="select * from user where email = ?",nativeQuery = true)
//    User findByEmail(String email);

    @Query(value="select u from User u where u.email = ?1")
//    @Query(value="select u from User u where u.email = :email")
//    User findByEmail(@Param("email") String alma);
    User findByEmail(String alma);
}
