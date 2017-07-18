package com.noob.validate.abstracts;

import java.lang.reflect.Field;

public interface IResolver {
	String EMPTY ="";
	 <T> void validate(T j, Field f) throws Exception ;
}
