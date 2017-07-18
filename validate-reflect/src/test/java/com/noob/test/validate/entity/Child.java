package com.noob.test.validate.entity;

import com.noob.validate.annotation.Multiple;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Child extends Parent {

	@Multiple
	private double c4;

}
