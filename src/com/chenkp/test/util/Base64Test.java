package com.chenkp.test.util;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;

public class Base64Test {

	public static void main(String[] args) {
		String content = "【MrCar】 abc";
		String context = null;
		try {
			context = new String(Base64.encodeBase64(content.getBytes("utf-8")));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String context2 = new String(Base64.decodeBase64(context.getBytes()));
		System.out.println(context);
		System.out.println(context2);
	}
	
	
}
