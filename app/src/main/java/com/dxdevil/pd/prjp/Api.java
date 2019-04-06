package com.dxdevil.pd.prjp;

import com.dxdevil.pd.prjp.Model.Request.*;
import com.dxdevil.pd.prjp.Model.Request.SignUp;
import com.dxdevil.pd.prjp.Model.Response.*;
import com.dxdevil.pd.prjp.Model.Response.SignUpModel;
import com.dxdevil.pd.prjp.Model.Response.UserExistResponse;
import retrofit2.Call;
import retrofit2.http.*;

public interface Api {

    @POST("account/register")
    Call<SignUpModel>register(@Body  SignUp signUp);


    @GET("account/check-user-exists/{id}")
    Call<UserExistResponse>isUserExist(@Path("id") String  id);


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

    @POST("user/change-password")
    Call<ChangePasswordModel>changepassword(
            @Header("Authorization") String Authorization,
            @Body ChangePasswordRequest request
    );

}
