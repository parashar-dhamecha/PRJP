package com.dxdevil.pd.prjp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class RetrofitClient {
    private static String BASE_URL ="https://cygnatureapipoc.stagingapplications.com/api/";
    private static RetrofitClient minstance;
    private static Retrofit retrofit;
    HttpLoggingInterceptor logging = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    OkHttpClient.Builder httpClient = new OkHttpClient.Builder().addInterceptor(logging)
            .connectTimeout(2, TimeUnit.MINUTES).readTimeout(1, TimeUnit.MINUTES).writeTimeout(2, TimeUnit.MINUTES);
    Gson gson = new GsonBuilder()
            .setLenient()
            .create();
    private RetrofitClient(){

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build();
    }
    public static synchronized RetrofitClient getInstance(){
        if(minstance == null)  {
            minstance=new RetrofitClient();
        }
        return minstance;
    }
    public Api getApi(){
        return retrofit.create(Api.class);
    }
}
