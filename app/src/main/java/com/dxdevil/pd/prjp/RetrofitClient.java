package com.dxdevil.pd.prjp;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static String BASE_URL ="https://cygnatureapipoc.stagingapplications.com/api/";
    private static RetrofitClient minstance;
    private static Retrofit retrofit;
    HttpLoggingInterceptor logging = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    OkHttpClient.Builder httpClient = new OkHttpClient.Builder().addInterceptor(logging);

    private RetrofitClient(){

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
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
