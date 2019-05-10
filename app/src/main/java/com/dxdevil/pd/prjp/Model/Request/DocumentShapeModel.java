package com.dxdevil.pd.prjp.Model.Request;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DocumentShapeModel {

    @SerializedName("x")
    @Expose
    public Integer x;
    @SerializedName("xPercentage")
    @Expose
    public Double xPercentage;
    @SerializedName("y")
    @Expose
    public Integer y;
    @SerializedName("yPercentage")
    @Expose
    public Double yPercentage;
    @SerializedName("w")
    @Expose
    public Integer w;
    @SerializedName("wPercentage")
    @Expose
    public Double wPercentage;
    @SerializedName("h")
    @Expose
    public Integer h;
    @SerializedName("hPercentage")
    @Expose
    public Double hPercentage;
    @SerializedName("p")
    @Expose
    public Integer p;
    @SerializedName("ratio")
    @Expose
    public String ratio;
    @SerializedName("userId")
    @Expose
    public String userId;
    @SerializedName("isAnnotation")
    @Expose
    public Boolean isAnnotation;
    @SerializedName("SignatureType")
    @Expose
    public String signatureType;

    /**
     * No args constructor for use in serialization
     *
     */
    public DocumentShapeModel() {
    }

    /**
     *
     * @param signatureType
     * @param xPercentage
     * @param isAnnotation
     * @param wPercentage
     * @param yPercentage
     * @param h
     * @param w
     * @param ratio
     * @param p
     * @param userId
     * @param hPercentage
     * @param y
     * @param x
     */
    public DocumentShapeModel(Integer x, Double xPercentage, Integer y, Double yPercentage, Integer w, Double wPercentage, Integer h, Double hPercentage, Integer p, String ratio, String userId, Boolean isAnnotation, String signatureType) {
        super();
        this.x = x;
        this.xPercentage = xPercentage;
        this.y = y;
        this.yPercentage = yPercentage;
        this.w = w;
        this.wPercentage = wPercentage;
        this.h = h;
        this.hPercentage = hPercentage;
        this.p = p;
        this.ratio = ratio;
        this.userId = userId;
        this.isAnnotation = isAnnotation;
        this.signatureType = signatureType;
    }

}