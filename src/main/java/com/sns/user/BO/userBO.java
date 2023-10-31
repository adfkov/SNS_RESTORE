package com.sns.user.BO;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.user.Entity.UserEntity;
import com.sns.user.mapper.UserMapper;


@Service
public class userBO {
	@Autowired
	private UserMapper userMapper;
	
	public UserEntity findByLoginIdAndPassword(String loginId, String password) {
		return userMapper.findByLoginIdAndPassword(loginId,password);
	}
}

