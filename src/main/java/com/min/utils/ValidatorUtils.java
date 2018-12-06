package com.min.utils;




import com.min.exception.MinException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * hibernate-validator校验工具类
 * <p>
 * 参考文档：http://docs.jboss.org/hibernate/validator/5.4/reference/en-US/html_single/
 *
 * @author Li HuanLing
 * @email 503580622@qq.com
 * @date 2018-03-15 10:50
 */
public class ValidatorUtils {
	private static Validator validator;
	
	static {
		validator = Validation.buildDefaultValidatorFactory().getValidator();
	}
	
	/**
	 * 校验对象
	 *
	 * @param object 待校验对象
	 * @param groups 待校验的组
	 * @throws MinException 校验不通过，则报MinException异常
	 */
	public static void check(Object object, Class<?>... groups)
			throws MinException {
		Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
		if (!constraintViolations.isEmpty()) {
			StringBuilder msg = new StringBuilder();
			for (ConstraintViolation<Object> constraint : constraintViolations) {
				msg.append(constraint.getMessage()).append("<br>");
			}
			throw new MinException(msg.toString());
		}
	}
	
	/**
	 * 校验对象
	 *
	 * @param object 待校验对象
	 * @param groups 待校验的组
	 * @throws MinException 校验不通过，则报CTException异常
	 */
	public static void validateAppEntity(Object object, Class<?>... groups)
			throws MinException {
		Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
		if (!constraintViolations.isEmpty()) {
			StringBuilder msg = new StringBuilder();
			for (ConstraintViolation<Object> constraint : constraintViolations) {
				msg.append(constraint.getMessage());
			}
			throw new MinException(msg.toString());
		}
	}
}
