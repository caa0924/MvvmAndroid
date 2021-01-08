package com.ning.mvvmplayandroid.api;

import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * 作者： ch
 * 时间： 2016/12/27.13:56
 * 描述：
 * 来源：
 */
public class ApiRetrofit {

    private static ApiRetrofit apiRetrofit;
    private Retrofit retrofit;
    private OkHttpClient client;
    private ApiServer apiServer;

    private String TAG = "ApiRetrofit";


    /**
     * 请求访问quest
     * response拦截器
     */
    private Interceptor interceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            long startTime = System.currentTimeMillis();
            Response response = chain.proceed(chain.request());

            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            okhttp3.MediaType mediaType = response.body().contentType();
            String content = response.body().string();
            Log.e(TAG, "----------Request Start----------------");
            Log.e(TAG, "| " + request.toString() + request.headers().toString());
            Log.e(TAG, "| Response:" + content);
            Log.e(TAG, "----------Request End:" + duration + "毫秒----------");
            return response.newBuilder()
                    .body(ResponseBody.create(mediaType, content))
                    .build();
        }
    };

    private Interceptor headInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {

            Request request = chain.request();

            //获取到方法
            String method = request.method();
            if (method.equals("GET")) {
                HttpUrl httpUrlurl = request.url();
                String url = httpUrlurl.toString();
                int index = url.indexOf("?");
                if (index > 0) {
                    //url = url + "&APP_KEY=" + AppConstant.APP_KEY;
                } else {
                  //  url = url + "?APP_KEY=" + AppConstant.APP_KEY;  //拼接新的url
                }
                request = request.newBuilder().url(url).build();  //重新构建请求

            } else if (method.equals("POST")) {
                Request.Builder requestBuilder = request.newBuilder();

                //请求体定制：统一添加token参数
                if (request.body().contentLength() == 0) {
                    //没有参数
                    FormBody.Builder newFormBody = new FormBody.Builder();
                    //newFormBody.add("APP_KEY", AppConstant.APP_KEY);
                    //newFormBody.add("APP_TOKEN", UserImpl.getAppToken());
                    //newFormBody.add("version", AppUtils.getVersionName(App.getInstance()));
                    requestBuilder.method(request.method(), newFormBody.build());
                } else if (request.body() instanceof FormBody) {
                    //正常post
                    FormBody.Builder newFormBody = new FormBody.Builder();
                    FormBody oidFormBody = (FormBody) request.body();
                    for (int i = 0; i < oidFormBody.size(); i++) {
                        newFormBody.addEncoded(oidFormBody.encodedName(i), oidFormBody.encodedValue(i));
                    }
                    //newFormBody.add("APP_KEY", AppConstant.APP_KEY);
                   // newFormBody.add("APP_TOKEN", UserImpl.getAppToken());
                   // newFormBody.add("version", AppUtils.getVersionName(App.getInstance()));
                    requestBuilder.method(request.method(), newFormBody.build());

                }
//                else if (request.body() instanceof MultipartBody) {
//                    //包含图片
//                    MultipartBody oldBodyMultipart = (MultipartBody) request.body();
//
//                    List<MultipartBody.Part> oldPartList = oldBodyMultipart.parts();
//
//                    MultipartBody.Builder builder = new MultipartBody.Builder();
//                    builder.setType(MultipartBody.FORM);
//
//
//                    for (MultipartBody.Part part : oldPartList) {
//                        builder.addPart(part);
//                    }
//
//
////                    RequestBody appkey = RequestBody.create(MediaType.parse("multipart/form-data"), AppConstant.APP_KEY);
////                    RequestBody apptoken = RequestBody.create(MediaType.parse("multipart/form-data"), UserImpl.getAppToken());
//////                    MultipartBody.Part appkey = MultipartBody.Part.createFormData("APP_KEY", AppConstant.APP_KEY);
////                    MultipartBody.Part apptoken1 = MultipartBody.Part.createFormData("APP_TOKEN", "", apptoken);
////
////                    MultipartBody.Part appkey1 = MultipartBody.Part.createFormData("APP_KEY", "", appkey);
////
//////
////                    builder.addPart(appkey1);
////                    builder.addPart(apptoken1);
//                    builder.addPart(MultipartBody.Part.createFormData("APP_KEY", AppConstant.APP_KEY));
//                    builder.addPart(MultipartBody.Part.createFormData("APP_TOKEN", UserImpl.getAppToken()));
//
////                    builder.addFormDataPart("APP_KEY", AppConstant.APP_KEY);
////                    builder.addFormDataPart("APP_TOKEN", UserImpl.getAppToken());
//                    requestBuilder.put(builder.build());
//                }

                request = requestBuilder.build();

            }

            return chain.proceed(request);

//            Request.Builder builder = request.newBuilder().addHeader("LEDAYOUXUAN", "159357456")
//                    .addHeader("APP_TOKEN", UserImpl.getAppToken());
//
//            return chain.proceed(builder.build());
        }
    };


    public ApiRetrofit() {


        client = new OkHttpClient.Builder()
                //添加log拦截器
                .addInterceptor(headInterceptor)
                .addInterceptor(interceptor)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
//                .sslSocketFactory()
                .build();


        retrofit = new Retrofit.Builder()
                .baseUrl(fule.com.playandroidkotlin.ui.util.AppConstant.BASE_SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                //支持RxJava2
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();

        apiServer = retrofit.create(ApiServer.class);
    }

    public static ApiRetrofit getInstance() {
        if (apiRetrofit == null) {
            synchronized (Object.class) {
                if (apiRetrofit == null) {
                    apiRetrofit = new ApiRetrofit();
                }
            }
        }
        return apiRetrofit;
    }

    public ApiServer getApiService() {
        return apiServer;
    }

}
