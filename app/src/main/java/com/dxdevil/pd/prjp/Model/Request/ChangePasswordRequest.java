package com.dxdevil.pd.prjp.Model.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChangePasswordRequest {

 public   ChangePasswordRequest(String currentPassword,String newPassword,String confirmPassword){

     this.currentPassword=currentPassword;
     this.newPassword=newPassword;
     this.confirmPassword=confirmPassword;
    }

    @SerializedName("currentPassword")
    @Expose
    private String currentPassword;
    @SerializedName("newPassword")
    @Expose
    private String newPassword;
    @SerializedName("confirmPassword")
    @Expose
    private String confirmPassword;

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

}