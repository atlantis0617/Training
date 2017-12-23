package com.cn.yhy.robot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Tuling {
	public static void main(String[] args) throws Exception {
		String APIKEY = "ad62576a34bf447e9cbfee68b44a2f8e"; 
//		String question = "";//这是上传给云机器人的问题
		String INFO = URLEncoder.encode("为啥这么帅?", "utf-8"); 
//		String INFO = URLEncoder.encode(question, "utf-8"); 
		String getURL = "http://www.tuling123.com/openapi/api?key=" + APIKEY + "&info=" + INFO; 
		URL getUrl = new URL(getURL); 
	    HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection(); 
	    connection.connect(); 
	    
	    // 取得输入流，并使用Reader读取 
	    BufferedReader reader = new BufferedReader(new InputStreamReader( connection.getInputStream(), "utf-8")); 
        StringBuffer sb = new StringBuffer(); 
        String line = ""; 
        while ((line = reader.readLine()) != null) { 
            sb.append(line); 
        } 
        reader.close(); 
        // 断开连接 
        connection.disconnect(); 
        System.out.println(sb); 
	}
}
