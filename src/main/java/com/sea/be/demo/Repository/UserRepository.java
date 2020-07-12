package com.sea.be.demo.Repository;

import com.sea.be.demo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User getByNameEquals(String userName);
}
