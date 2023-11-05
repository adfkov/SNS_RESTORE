package com.sns.post.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Repository
public interface PostMapper {
	public void insertPost
	(@Param("userId") int userId
	,@Param("subject") String subject
	, @Param("content")String content
	, @Param("imagePath")String imagePath);
	
}
