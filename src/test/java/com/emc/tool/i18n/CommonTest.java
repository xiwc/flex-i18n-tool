package com.emc.tool.i18n;

import org.junit.Test;

public class CommonTest {

	@Test
	public void test() {
		String ss = "aaaa * ffff";
		System.out.println(ss.replace("*", "must"));
	}

}
