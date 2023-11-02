package com.sns.post.Entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Table(name="post")
public class PostEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="userId")
	private int userId;
	private String content;
	private String imagePath;
	@Column(name="createdAt", updatable = false)
	@UpdateTimestamp
	private Date createdAt;
	@Column(name="updatedAt")
	@UpdateTimestamp
	private Date updatedAt;
}
