
package com.sample.sampleapp.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NativeDate {

    @SerializedName("Tag")
    @Expose
    private String tag;
    @SerializedName("TypeCode")
    @Expose
    private Integer typeCode;
    @SerializedName("Value")
    @Expose
    private String value;
    @SerializedName("IsBinaryUnique")
    @Expose
    private Boolean isBinaryUnique;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(Integer typeCode) {
        this.typeCode = typeCode;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Boolean getIsBinaryUnique() {
        return isBinaryUnique;
    }

    public void setIsBinaryUnique(Boolean isBinaryUnique) {
        this.isBinaryUnique = isBinaryUnique;
    }

}
