package com.chenkp.test.util;

import java.util.Date;

public class Cookid {

	public static void main(String[] args) {
		//2223
		String url = "http://it-java.izuche.com/eprentalcarapi/api/v1/cities";
//		String url = "http://it-java.izuche.com/eprentalcarapi/api/v1/cities/areastores?cityId=2223";
		
//		String url = "http://it-java.izuche.com/eprentalcarapi/api/v1/orders/create";
		Date date = new Date();
		String timesta = String.valueOf(date.getTime() / 1000);
		Integer timestamp = Integer.valueOf(timesta);
		String appid = "BF44EC9A-87B5-11E7-AB9D-00163E2EA544";
		String salt = appid + timestamp;
		salt = Md5Util.EncoderByMd5(salt);
		String cookid = "timestamp=" + timestamp + ";salt=" + salt + ";channel-name=MrCar";
		System.out.println(cookid);
		String param = "";
		HttpClient.doGet(url, cookid);
		HttpClient.doPost(url, cookid, param);
	}
	
}
