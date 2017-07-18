package com.noob.stream.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultData<E> extends Result {

	private E data;

}
