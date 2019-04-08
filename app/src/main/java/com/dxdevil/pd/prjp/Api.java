package com.dxdevil.pd.prjp;

import com.dxdevil.pd.prjp.Model.Request.*;
import com.dxdevil.pd.prjp.Model.Response.*;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.ArrayList;
import java.util.List;

public interface Api {


    @POST("account/login")
    Call<LoginModel>login(@Body Login login );

    @POST("account/forgot-password")
    Call<ForgotResponse>forgotpass(
            @Body ForgotRequest forgotRequest
            );

    @GET("dashboard/document-counts")
    Call<DashboardResponse>getDashboardCouts(
            @Header("Authorization") String Authorization
    );

    @POST("account/send-login-otp")
    Call<OtpModel>sendotp(
            @Header("Authorization") String Authorization,
            @Body Otp otp
    );


    @POST("account/validate-login-otp")
    Call<VerifyModel>verifyotp(
            @Header("Authorization") String Authorization,
            @Body Verify verifyotp
            );

    @POST("contact/add")
    Call<AddContactResponse>addcontact(
            @Header("Authorization") String Authorization,
            @Body AddContactRequest addco
    );

    @GET("contact/get")
    Call<AddContactResponse>addcontactresponse(
            @Header("Authorization") String Authorization
    );


}
