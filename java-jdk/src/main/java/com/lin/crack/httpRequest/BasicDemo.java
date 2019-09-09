package com.lin.crack.httpRequest;

import com.github.kevinsawicki.http.HttpRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: linran
 * @email: linran@live.com
 * @create: 2019-09-09 12:25
 * @Description:
 **/
public class BasicDemo {
    public static void main(String[] args) {
        String url = "http://www.baidu.com";
        /**
        HttpRequest request = HttpRequest.get(url);
        System.out.println(request.toString());
        System.out.println(request.body());
        **/
        /**
        HttpRequest request = HttpRequest.get(url, true, "q", "httpRequest", "size", "10");
        System.out.println(request.toString());
        System.out.println(request.body());
        **/
        /**
         Map parms = new HashMap<String, String>();
         parms.put("q", "httpRequest");
         parms.put("size", "10");
         HttpRequest request = HttpRequest.get(url, true).form(parms);
         System.out.println(request.toString());
        System.out.println(request.body());
         **/
    }
}
