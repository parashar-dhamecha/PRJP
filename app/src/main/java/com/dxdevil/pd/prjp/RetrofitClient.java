package com.dxdevil.pd.prjp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static String BASE_URL ="https://cygnatureapipoc.stagingapplications.com/";
    private static RetrofitClient minstance;
    private static Retrofit retrofit;
    private RetrofitClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
    }
    public static synchronized RetrofitClient getInstance(){
        if(minstance == null)  minstance=new RetrofitClient();
        return minstance;
    }
    public Api getApi(){
        return retrofit.create(Api.class);
    }
}
