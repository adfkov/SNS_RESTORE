package com.sns.user.BO;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.user.Entity.UserEntity;
import com.sns.user.mapper.UserMapper;


@Service
public class userBO {
	@Autowired
	private UserMapper userMapper;
	
	public UserEntity findByLoginId(String loginId) {
		return userMapper.findByLoginId(loginId);
	}
	
	public UserEntity findByLoginIdAndPassword(String loginId, String password) {
		return userMapper.findByLoginIdAndPassword(loginId,password);
	}
	
	// input: 4개 파라미터		output:id(pk)
	public Integer addUser(String loginId, String password , String name, String email) { // 해싱된 것
		// UserEntity = save(UserEntity);
		UserEntity userEntity = userMapper.save(
				UserEntity.builder()
				.loginId(loginId)
				.password(password)
				.name(name)
				.email(email)
				.build()); 
		return userEntity == null ? null : userEntity.getId();
	}
}

