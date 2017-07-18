package com.noob.validate;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.noob.validate.abstracts.IResolver;

public class AnnotationValidate {

	public static <T> boolean validate(T entity) {
		boolean is_valid = true;
		if (entity != null) {
			String fieldName = null;
			String annotationName = null;
			try {
				List<Field> fields = new ArrayList();
				Class tempClass = entity.getClass();
				while (tempClass != null) {//当父类为null的时候说明到达了最上层的父类(Object类).
					fields.addAll(Arrays.asList(tempClass.getDeclaredFields()));
					tempClass = tempClass.getSuperclass(); //得到父类,然后赋给自己
				}
				if (fields != null && fields.size() > 0) {
					for (Field field : fields) {
						fieldName = field.getName();
						Annotation[] annotations = field.getAnnotations();
						if (annotations != null && annotations.length > 0) {
							field.setAccessible(true);
							for (Annotation annotation : annotations) {
								annotationName = annotation.annotationType().getSimpleName();
								validate(entity, field, annotation);
							}
						}
					}
				}
			} catch (Exception e) {
				System.out.println(
						String.format("property: 【%s】, validate-type: 【%s】, exception: 【%s】",
								fieldName, annotationName, e.getMessage()));
				is_valid = false;
			}
		}
		return is_valid;
	}

	private static <T> void validate(T entity, Field field, Annotation annotation)
			throws Exception {
		IResolver resolver =
				AnnotationResolverContainer.getContainer().get(annotation.annotationType());
		if (resolver != null) {
			resolver.validate(entity, field);
		}
	}

}
