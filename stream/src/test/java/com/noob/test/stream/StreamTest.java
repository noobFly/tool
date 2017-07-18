package com.noob.test.stream;

import org.junit.Test;

import com.noob.stream.util.StreamUtil;

public class StreamTest {

	@Test
	public void Test() {
		StreamUtil.executeMethod("StreamTest", () -> {
			return "request for test";
		}, () -> {
			System.out.println("hello!");
		});
	}
}
