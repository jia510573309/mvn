package com.min.dao;


import com.min.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {

	List<UserEntity> selectUser();
	
}
