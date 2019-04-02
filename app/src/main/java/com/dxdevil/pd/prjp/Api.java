package com.dxdevil.pd.prjp;

import com.dxdevil.pd.prjp.Model.Request.ForgotRequest;
import com.dxdevil.pd.prjp.Model.Request.Login;
import com.dxdevil.pd.prjp.Model.Request.SignUp;
import com.dxdevil.pd.prjp.Model.Response.DashboardResponse;
import com.dxdevil.pd.prjp.Model.Response.ForgotResponse;
import com.dxdevil.pd.prjp.Model.Response.LoginModel;
import com.dxdevil.pd.prjp.Model.Response.SignUpMode;
import retrofit2.Call;
import retrofit2.http.*;

public interface Api {


    @POST("api/account/login")
    Call<LoginModel>login(@Body Login login );

    @POST("api/account/register")
    Call<SignUpMode>register(@Body SignUp register);

    @POST("api/account/forgot-password")
    Call<ForgotResponse>forgotpass(
            @Body ForgotRequest forgotRequest
            );
    @GET("api/dashboard/document-counts")
    Call<DashboardResponse>getDashboard(
            @Header("Authorization")String Auth
    );
}
