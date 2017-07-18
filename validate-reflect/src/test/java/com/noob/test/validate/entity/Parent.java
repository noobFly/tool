package com.noob.test.validate.entity;

import com.noob.validate.annotation.Length;
import com.noob.validate.annotation.NotBlank;
import com.noob.validate.annotation.NotNull;

import lombok.Data;

@Data
public class Parent {

	@NotNull
	private Integer p1;
	@NotBlank
	private String p2;
	@Length
	private Integer p3;

}
