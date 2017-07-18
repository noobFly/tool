package com.noob.validate.annotation.resolver;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.noob.validate.abstracts.IResolver;
import com.noob.validate.annotation.StrMatchDate;

public class StrMatchDateResolver implements IResolver {

	@Override
	public <T> void validate(T j, Field f) throws Exception {
		Object value = f.get(j);
		if (value != null) {
			String str = String.valueOf(value).trim();
			if (!EMPTY.equals(str)) {
				StrMatchDate annotation = f.getAnnotation(StrMatchDate.class);
				validate(str, annotation.pattern());
			}
		}

	}

	private void validate(String value, String pattern) throws Exception {
		DateFormat sf = new SimpleDateFormat(pattern);
		sf.setLenient(false);

		try {
			sf.parse(value);
		} catch (Exception e) {
			throw new Exception("strMatchDate validate fail.");
		}
	}

}
