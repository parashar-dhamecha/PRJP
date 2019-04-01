package com.dxdevil.pd.prjp;

import com.dxdevil.pd.prjp.Model.Request.Login;
import com.dxdevil.pd.prjp.Model.Request.SignUp;
import com.dxdevil.pd.prjp.Model.Response.LoginModel;
import com.dxdevil.pd.prjp.Model.Response.SignUpModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Api {
    @POST("/api/account/login")
    Call<LoginModel>login(@Body Login alogin);

    @POST("api/account/register")
    Call<SignUpModel>registerethod(@Body SignUp asignUp);




}

