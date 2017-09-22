package com.chenkp.test.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;

public class HttpClient {
 
 
    public static String doGet(String urlStr, String cookie) {
        String result = "";
        BufferedReader in = null;
        try {
            URL url = new URL(urlStr);
            // 打开和URL之间的连接
            URLConnection connection = url.openConnection();
            connection.setRequestProperty("Cookie", cookie);
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        System.out.println(result);
        return result;
    }
    public static String doPost(String urlStr, String cookie, String param) {
		String result = null;
		try {
			URL url = new URL(urlStr);
			HttpURLConnection urlconn = (HttpURLConnection) url.openConnection();
			urlconn.setDoOutput(true);
			urlconn.setDoInput(true);
			urlconn.setUseCaches(false);
			urlconn.setInstanceFollowRedirects(true);
			urlconn.setRequestMethod("POST");
			urlconn.setRequestProperty("Content-Type", "application/json");
			urlconn.setRequestProperty("Cookie", cookie);
			urlconn.connect();

			OutputStream out = urlconn.getOutputStream();
			out.write(param.getBytes("UTF-8"));//使用字节流进行传输
			out.flush();
			out.close();

			InputStream inputStream = null;

			if (urlconn.getResponseCode() == HttpURLConnection.HTTP_OK
					|| urlconn.getResponseCode() == HttpURLConnection.HTTP_CREATED
					|| urlconn.getResponseCode() == HttpURLConnection.HTTP_ACCEPTED) {
				inputStream = urlconn.getInputStream();//一切执行正常的时候，读取普通的返回流
			}else{
				//当CXF抛出异常时，会有一个异常的封装流！否则无法读出错误码
				inputStream = urlconn.getErrorStream();
			}

			result = IOUtils.toString(inputStream, "UTF-8");
			inputStream.close();
			urlconn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
