package com.dxdevil.pd.prjp;

import com.dxdevil.pd.prjp.Model.Request.*;
import com.dxdevil.pd.prjp.Model.Request.Document.ListOfDocument;
import com.dxdevil.pd.prjp.Model.Response.*;
import com.dxdevil.pd.prjp.Model.Response.ChangePassword.ChangePasswordModel;
import com.dxdevil.pd.prjp.Model.Response.Document.DocDetailsResponse;
import com.dxdevil.pd.prjp.Model.Response.SignUpModel;
import com.dxdevil.pd.prjp.Model.Response.UserExistResponse;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.ArrayList;
import java.util.List;

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

    @POST("contact/add")
    Call<AddContactResponse>addcontact(
            @Header("Authorization") String Authorization,
            @Body AddContactRequest addco
    );

    @GET("contact/get")
    Call<GetContactResponse>getcontactresponse(
            @Header("Authorization") String Authorization
    );


    @POST("user/change-password")
    Call<ChangePasswordModel>changepassword(
            @Header("Authorization") String Authorization,
            @Body ChangePasswordRequest request
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
    @GET("document/document-detail/{id}")
    Call<DocDetailsResponse>doc_details(@Path("id") String  id);

    @POST("document/documents")
    Call<ListOfDocument>doclist(
            @Header("Authorization") String Authorization,
            @Body ListOfDocument list
    );
}
