package com.barsege.expensetracker.repository;

import com.barsege.expensetracker.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <UserEntity , Long>{
	

}
