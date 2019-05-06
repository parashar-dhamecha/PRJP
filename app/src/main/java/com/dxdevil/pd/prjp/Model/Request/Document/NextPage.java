package com.dxdevil.pd.prjp.Model.Request.Document;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NextPage {

  public NextPage(
            String id,
            String pageFrom,
            String pageTo)
  {
      this.id=id;
      this.pageFrom=pageFrom;
      this.pageTo=pageTo;
  }

    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("pageFrom")
    @Expose
    private String pageFrom;
    @SerializedName("pageTo")
    @Expose
    private String pageTo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPageFrom() {
        return pageFrom;
    }

    public void setPageFrom(String pageFrom) {
        this.pageFrom = pageFrom;
    }

    public String getPageTo() {
        return pageTo;
    }

    public void setPageTo(String pageTo) {
        this.pageTo = pageTo;
    }

}
