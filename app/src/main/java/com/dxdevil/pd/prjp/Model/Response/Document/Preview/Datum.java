
package com.dxdevil.pd.prjp.Model.Response.Document.Preview;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("documentData")
    @Expose
    private DocumentData documentData;
    @SerializedName("annotationShapes")
    @Expose
    private List<Object> annotationShapes = null;
    @SerializedName("authenticationType")
    @Expose
    private Object authenticationType;

    public DocumentData getDocumentData() {
        return documentData;
    }

    public void setDocumentData(DocumentData documentData) {
        this.documentData = documentData;
    }

    public List<Object> getAnnotationShapes() {
        return annotationShapes;
    }

    public void setAnnotationShapes(List<Object> annotationShapes) {
        this.annotationShapes = annotationShapes;
    }

    public Object getAuthenticationType() {
        return authenticationType;
    }

    public void setAuthenticationType(Object authenticationType) {
        this.authenticationType = authenticationType;
    }

}
