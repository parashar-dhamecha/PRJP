package com.dxdevil.pd.prjp;

import com.dxdevil.pd.prjp.Model.Request.*;
import com.dxdevil.pd.prjp.Model.Request.Document.ListOfDocument;
import com.dxdevil.pd.prjp.Model.Request.Document.NextPage;
import com.dxdevil.pd.prjp.Model.Response.*;
import com.dxdevil.pd.prjp.Model.Response.ChangePassword.ChangePasswordModel;
import com.dxdevil.pd.prjp.Model.Response.Document.DocDetails.DocDetailsResponse;
import com.dxdevil.pd.prjp.Model.Response.Document.ListOfDocument.ListOfDocumentResponse;
import com.dxdevil.pd.prjp.Model.Response.Document.NextPage.NextPageResponse;
import com.dxdevil.pd.prjp.Model.Response.Document.Preview.PreviewDocResponse;
import com.dxdevil.pd.prjp.Model.Response.SignUpModel;
import com.dxdevil.pd.prjp.Model.Response.UserExistResponse;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;


public interface Api {

    @POST("account/register")
    Call<SignUpModel>register(@Body  SignUp signUp);


    @GET("account/check-user-exists/{id}")
    Call<UserExistResponse>isUserExist(@Path("id") String  id);

    @GET("document/preview/{documentId}")
    Call<PreviewDocResponse>getdocpreview(@Header("Authorization") String Authorization,
                                          @Path("documentId") String documentId);

    @POST("document/next-pages")
    Call<NextPageResponse>nextPages(
            @Header("Authorization") String Authorization,
            @Body NextPage nextPage);

    @POST("account/verify")
    Call<ResponseBody>verify_user(@Body  VerifyUser verify);

    @GET("contact/get-contact-by-id/{UserId}")
    Call<GetContactIdResponse>getcontactid(@Header("Authorization") String Authorization,
                                             @Path("UserId") String UserId);

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
    Call<ContactList>getcontactresponse(
            @Header("Authorization") String Authorization
    );


    @POST("user/change-password")
    Call<ChangePasswordModel>changepassword(
            @Header("Authorization") String Authorization,
            @Body ChangePasswordRequest request
    );


    @POST("user/enroll-signature")
    Call<EnrollSignModel>enrollsignature(
            @Header("Authorization") String Authorization,
            @Body EnrollSignRequest enrollsignrequest
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
    Call<DocDetailsResponse>docdetails(
            @Header("Authorization") String Authorization,
            @Path("id") String  id
    );


    @POST("document/documents")
    Call<ListOfDocumentResponse>doclist(
            @Header("Authorization") String Authorization,
            @Body ListOfDocument list
    );
    @PUT("user/profile/{UserId}")
    Call<ResponseBody>updateprofile(
            @Header("Authorization") String Authorization,
            @Path("UserId") String userid,
        @Body UpdateProfile updateProfile
    );

    @PUT("contact/update/{UserId}")
    Call<UpdateIdResponse>updateid(@Header("Authorization") String Authorization,
                                     @Path("UserId") String userid,
                                     @Body UpdateIdRequest updateId
    );

    @DELETE("contact/delete/{UserId}")
    Call<DeleteIdResponse>deleteid(@Header("Authorization") String Authorization,
                                   @Path("UserId") String userid
                                   );

    @Multipart
    @POST("document/upload")
    Call<UploadfileModel>upload(
            @Header("Authorization") String Authorization,
            @Part MultipartBody.Part file
//            @Part("file") RequestBody file
    );
}

