package com.txmpay.ewallet.model;

import android.os.Parcel;
import android.os.Parcelable;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

/**
 * created by czh on 2018-03-12
 * 用户设置数据库实体类，详细内容请访问Objectbox官方网站
 * http://objectbox.io
 */

@Entity
public class UserSetting implements Parcelable{

    @Id(assignable = true)
    long uid;

    String gesture;
    String finger;
    int isGesture;
    int isFingerPrint;
    int gestureCheckCount;


    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getGesture() {
        return gesture;
    }

    public void setGesture(String gesture) {
        this.gesture = gesture;
    }

    public String getFinger() {
        return finger;
    }

    public void setFinger(String finger) {
        this.finger = finger;
    }

    public int getIsGesture() {
        return isGesture;
    }

    public void setIsGesture(int isGesture) {
        this.isGesture = isGesture;
    }

    public int getIsFingerPrint() {
        return isFingerPrint;
    }

    public void setIsFingerPrint(int isFingerPrint) {
        this.isFingerPrint = isFingerPrint;
    }

    public int getGestureCheckCount() {
        return gestureCheckCount;
    }

    public void setGestureCheckCount(int gestureCheckCount) {
        this.gestureCheckCount = gestureCheckCount;
    }

    public static Creator<UserSetting> getCREATOR() {
        return CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.uid);
        dest.writeString(this.gesture);
        dest.writeString(this.finger);
        dest.writeInt(this.isGesture);
        dest.writeInt(this.isFingerPrint);
        dest.writeInt(this.gestureCheckCount);
    }

    public UserSetting() {
    }

    protected UserSetting(Parcel in) {
        this.uid = in.readLong();
        this.gesture = in.readString();
        this.finger = in.readString();
        this.isGesture = in.readInt();
        this.isFingerPrint = in.readInt();
        this.gestureCheckCount = in.readInt();
    }

    public static final Creator<UserSetting> CREATOR = new Creator<UserSetting>() {
        @Override
        public UserSetting createFromParcel(Parcel source) {
            return new UserSetting(source);
        }

        @Override
        public UserSetting[] newArray(int size) {
            return new UserSetting[size];
        }
    };
}
