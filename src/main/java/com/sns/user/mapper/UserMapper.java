package com.sns.user.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sns.user.Entity.UserEntity;

@Repository
public interface UserMapper extends JpaRepository<UserEntity, Integer>{
//	public List<Map<String, Object>> selectUser();
	public UserEntity findByLoginIdAndPassword(String loginId, String password);
	
}
