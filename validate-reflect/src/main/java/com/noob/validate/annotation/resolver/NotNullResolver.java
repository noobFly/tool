package com.noob.validate.annotation.resolver;

import com.noob.validate.abstracts.AbstractResolver;

public class NotNullResolver extends AbstractResolver {
	public <T> void validate(T j) throws Exception {

		if (j == null) {
			throw new Exception("notNull validate fail.");
		}
	}
}
