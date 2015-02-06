package com.emc.tool.i18n;

import java.util.Arrays;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

public class CommonTest
{

	@Test
	public void test()
	{
		String ss = "aaaa * ffff";
		System.out.println(ss.replace("*", "must"));

		ss = "{0} ffff        dfdfd {1} sdsd fdfdf gfggd {2}";

		System.out.println(ss.replaceAll("\\{\\d\\}", "var"));

		String[] arr = ss.split("\\s+");

		System.out.println(arr.length);

		System.out.println("dfd.ddd.dd".split("\\.").length);

		arr = "d.d.d.d.d.d.d.d.d.d.d.d".split("\\.", 5);
		arr = Arrays.copyOf(arr, 4);
		System.out.println(StringUtils.join(arr, "."));
	}

}
