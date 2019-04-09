
package com.dxdevil.pd.prjp.Model.Response.Document;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Notarization {

    @SerializedName("txHash")
    @Expose
    private Object txHash;
    @SerializedName("txHashLink")
    @Expose
    private Object txHashLink;
    @SerializedName("isNotarized")
    @Expose
    private Boolean isNotarized;
    @SerializedName("blockId")
    @Expose
    private Object blockId;
    @SerializedName("notarizedOn")
    @Expose
    private String notarizedOn;
    @SerializedName("notarizeMessage")
    @Expose
    private String notarizeMessage;

    public Object getTxHash() {
        return txHash;
    }

    public void setTxHash(Object txHash) {
        this.txHash = txHash;
    }

    public Object getTxHashLink() {
        return txHashLink;
    }

    public void setTxHashLink(Object txHashLink) {
        this.txHashLink = txHashLink;
    }

    public Boolean getIsNotarized() {
        return isNotarized;
    }

    public void setIsNotarized(Boolean isNotarized) {
        this.isNotarized = isNotarized;
    }

    public Object getBlockId() {
        return blockId;
    }

    public void setBlockId(Object blockId) {
        this.blockId = blockId;
    }

    public String getNotarizedOn() {
        return notarizedOn;
    }

    public void setNotarizedOn(String notarizedOn) {
        this.notarizedOn = notarizedOn;
    }

    public String getNotarizeMessage() {
        return notarizeMessage;
    }

    public void setNotarizeMessage(String notarizeMessage) {
        this.notarizeMessage = notarizeMessage;
    }

}
