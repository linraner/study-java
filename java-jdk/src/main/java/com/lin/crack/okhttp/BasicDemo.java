package com.lin.crack.okhttp;


import com.squareup.okhttp.Call;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;

/**
 * @author: linran
 * @email: linran@live.com
 * @create: 2019-09-09 12:10
 * @Description:
 **/
public class BasicDemo {

  public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

  public static void main(String[] args) {
    String url = "https://www.baidu.com";
    /**
     OkHttpClient client = new OkHttpClient();
     Request request = new Request.Builder()
     .get()
     .url(url)
     .build();
     Call call = client.newCall(request);
     **/
    /**
     // sync
     try {
     Response response = call.execute();
     System.out.println(response.toString());
     System.out.println(response.body());
     } catch (IOException e) {
     e.printStackTrace();
     }
     **/
    /**
     // async
     call.enqueue(new Callback() {
    @Override public void onFailure(Request request, IOException e) {
    System.out.println("fail");
    }

    @Override public void onResponse(Response response) throws IOException {
    System.out.println(response.toString());
    System.out.println(response.body().string());
    }
    });
     **/
    String json = "";
    RequestBody body = RequestBody.create(JSON, json);
    OkHttpClient client = new OkHttpClient();
    Request request = new Request.Builder()
        .post(body)
        .url(url)
        .build();
    Call call = client.newCall(request);
  }
}
