package com.noob.test.validate;

import org.junit.Test;

import com.noob.test.validate.entity.Child;
import com.noob.validate.AnnotationValidate;

public class ValidateTest {

	@Test
	public void Test() {
		Child entity = new Child(22.33);
		entity.setP1(new Integer(1));
		AnnotationValidate.validate(entity);
	}
}
