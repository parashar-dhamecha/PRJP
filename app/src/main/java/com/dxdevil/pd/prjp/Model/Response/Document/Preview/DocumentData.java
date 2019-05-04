
package com.dxdevil.pd.prjp.Model.Response.Document.Preview;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DocumentData {

    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("data")
    @Expose
    private Object data;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("pageCount")
    @Expose
    private Integer pageCount;
    @SerializedName("pageFrom")
    @Expose
    private Integer pageFrom;
    @SerializedName("pageTo")
    @Expose
    private Integer pageTo;
    @SerializedName("pages")
    @Expose
    private List<String> pages = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getPageFrom() {
        return pageFrom;
    }

    public void setPageFrom(Integer pageFrom) {
        this.pageFrom = pageFrom;
    }

    public Integer getPageTo() {
        return pageTo;
    }

    public void setPageTo(Integer pageTo) {
        this.pageTo = pageTo;
    }

    public List<String> getPages() {
        return pages;
    }

    public void setPages(List<String> pages) {
        this.pages = pages;
    }

}
