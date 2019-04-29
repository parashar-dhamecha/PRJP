package com.dxdevil.pd.prjp.Model.Response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetContactIdResponse {

    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("data")
    @Expose
    public List<GetContactIdDatum> data = null;
    @SerializedName("license")
    @Expose
    public Object license;

    /**
     * No args constructor for use in serialization
     *
     */
    public GetContactIdResponse() {
    }

    /**
     *
     * @param message
     * @param data
     * @param license
     */
    public GetContactIdResponse(String message, List<GetContactIdDatum> data, Object license) {
        super();
        this.message = message;
        this.data = data;
        this.license = license;
    }

    public static class GetContactIdDatum {

        @SerializedName("Id")
        @Expose
        public String id;
        @SerializedName("userId")
        @Expose
        public Object userId;
        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("email")
        @Expose
        public String email;
        @SerializedName("countryId")
        @Expose
        public Integer countryId;
        @SerializedName("mobileNumber")
        @Expose
        public String mobileNumber;
        @SerializedName("jobTitle")
        @Expose
        public String jobTitle;
        @SerializedName("jobDescription")
        @Expose
        public String jobDescription;
        @SerializedName("shortName")
        @Expose
        public String shortName;

        /**
         * No args constructor for use in serialization
         *
         */
        public GetContactIdDatum() {
        }

        /**
         *
         * @param countryId
         * @param id
         * @param email
         * @param name
         * @param userId
         * @param shortName
         * @param mobileNumber
         * @param jobDescription
         * @param jobTitle
         */
        public GetContactIdDatum(String id, Object userId, String name, String email, Integer countryId, String mobileNumber, String jobTitle, String jobDescription, String shortName) {
            super();
            this.id = id;
            this.userId = userId;
            this.name = name;
            this.email = email;
            this.countryId = countryId;
            this.mobileNumber = mobileNumber;
            this.jobTitle = jobTitle;
            this.jobDescription = jobDescription;
            this.shortName = shortName;
        }

    }
}