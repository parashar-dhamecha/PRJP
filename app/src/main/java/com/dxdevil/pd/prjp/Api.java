package com.dxdevil.pd.prjp;

import com.dxdevil.pd.prjp.Model.Request.Login;
import com.dxdevil.pd.prjp.Model.Response.LoginModel;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Api {
    @POST("Acount/login")
    Call<LoginModel>login(@Body Login alogin);
}
