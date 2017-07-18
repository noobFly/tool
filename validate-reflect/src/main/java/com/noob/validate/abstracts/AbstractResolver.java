package com.noob.validate.abstracts;

import java.lang.reflect.Field;


public abstract class AbstractResolver implements IResolver {
	public <T> void validate(T j, Field f) throws Exception {
		validate(f.get(j));
	}

	public abstract <T> void validate(T object) throws Exception;
}
