package com.itheima.smart.beijing.pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2017/9/21.
 */

public class TestBean implements Parcelable {

    /**
     * versionDesc : 这是描述内容,更新了牛逼的功能,是否更新?
     * downloadUrl : http://192.168.2.107/Mobilesafe.apk
     * versionName : 服务器版本
     * versionCode : 2
     */
    private String versionDesc;
    private String downloadUrl;
    private String versionName;
    private String versionCode;

    @Override
    public String toString() {
        return "TestBean{" +
                "versionDesc='" + versionDesc + '\'' +
                ", downloadUrl='" + downloadUrl + '\'' +
                ", versionName='" + versionName + '\'' +
                ", versionCode='" + versionCode + '\'' +
                '}';
    }

    public void setVersionDesc(String versionDesc) {
        this.versionDesc = versionDesc;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionDesc() {
        return versionDesc;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public String getVersionName() {
        return versionName;
    }

    public String getVersionCode() {
        return versionCode;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.versionDesc);
        dest.writeString(this.downloadUrl);
        dest.writeString(this.versionName);
        dest.writeString(this.versionCode);
    }

    public TestBean() {
    }

    protected TestBean(Parcel in) {
        this.versionDesc = in.readString();
        this.downloadUrl = in.readString();
        this.versionName = in.readString();
        this.versionCode = in.readString();
    }

    public static final Parcelable.Creator<TestBean> CREATOR = new Parcelable.Creator<TestBean>() {
        @Override
        public TestBean createFromParcel(Parcel source) {
            return new TestBean(source);
        }

        @Override
        public TestBean[] newArray(int size) {
            return new TestBean[size];
        }
    };
}
