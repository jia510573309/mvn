package com.min.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserEntity implements Serializable {
	
	private Long userId;
	
	private String username;
	
	private String password;
	
	private Integer sex;
	
	private String address;
	
}
