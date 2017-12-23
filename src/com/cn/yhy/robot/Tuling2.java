package com.cn.yhy.robot;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.cn.util.Aes;
import com.cn.util.Md5;
import com.cn.util.PostServer;

public class Tuling2 {
	@Test
	public void testAes(){
		//图灵网站上的secret
		String secret = "e0af9d85be1b7333";//网站的secret
		//图灵网站上的apiKey
		String apiKey = "ad62576a34bf447e9cbfee68b44a2f8e";//网站的apikey
		String cmd = "武汉的天气情况";//测试用例
		//待加密的json数据
		String data = "{\"key\":\""+apiKey+"\",\"info\":\""+cmd+"\"}";
		//获取时间戳
		String timestamp = String.valueOf(System.currentTimeMillis());
		
		//生成密钥
		String keyParam = secret+timestamp+apiKey;
		String key = Md5.MD5(keyParam);
		
		//加密
		Aes mc = new Aes(key);
		data = mc.encrypt(data);		
		
		//封装请求参数
		JSONObject json = new JSONObject();
		json.put("key", apiKey);
		json.put("timestamp", timestamp);
		json.put("data", data);
		//请求图灵api
		String result = PostServer.SendPost(json.toString(), "http://www.tuling123.com/openapi/api");
		System.out.println(result);
	}
}
