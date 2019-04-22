package com.dxdevil.pd.prjp.Model.Response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UploadDatum {

    @SerializedName("Id")
    @Expose
    public String id;
    @SerializedName("data")
    @Expose
    public Object data;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("pageCount")
    @Expose
    public Integer pageCount;
    @SerializedName("pageFrom")
    @Expose
    public Integer pageFrom;
    @SerializedName("pageTo")
    @Expose
    public Integer pageTo;
    @SerializedName("pages")
    @Expose
    public List<String> pages ;

    /**
     * No args constructor for use in serialization
     *
     */
    public UploadDatum() {
    }

    /**
     *
     * @param id
     * @param pageCount
     * @param pageTo
     * @param pages
     * @param name
     * @param data
     * @param pageFrom
     */
    public UploadDatum(String id, Object data, String name, Integer pageCount, Integer pageFrom, Integer pageTo, List<String> pages) {
        super();
        this.id = id;
        this.data = data;
        this.name = name;
        this.pageCount = pageCount;
        this.pageFrom = pageFrom;
        this.pageTo = pageTo;
        this.pages = pages;
    }

}