package com.dxdevil.pd.prjp;

import com.dxdevil.pd.prjp.Model.Request.*;
import com.dxdevil.pd.prjp.Model.Response.*;
import retrofit2.Call;
import retrofit2.http.*;

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

    @POST("user/update-signature")
    Call<UpdateSignatureModel>updatesignature(
            @Header("Authorization") String Authorization,
            @Body UpdateSignature updateSignature
            );

    @GET("user/profile")
    Call<ProfileModel>getprofiledetails(
            @Header("Authorization") String Authorization
    );

    @POST("account/refresh-token")
    Call<RefreshTokenModel>refreshtoken(
         @Body RefreshToken refreshtoken
    );
}
