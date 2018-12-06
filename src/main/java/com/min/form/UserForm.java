package com.min.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

@Data
public class UserForm implements Serializable {
	
	@NotEmpty(message = "用户名不能为空")
	private String userName;
	
	@NotEmpty(message = "密码不能为空")
	private String userPwd;
}
