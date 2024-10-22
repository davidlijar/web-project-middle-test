package com.jvision.admin202335003.middletest.Repository;

import com.jvision.admin202335003.middletest.Domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<Users, String>  {
}

