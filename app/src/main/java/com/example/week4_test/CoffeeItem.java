
package com.example.week4_test;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CoffeeItem implements Parcelable
{

    @SerializedName("desc")
    @Expose
    private String desc;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    public final static Creator<CoffeeItem> CREATOR = new Creator<CoffeeItem>() {


        @SuppressWarnings({
            "unchecked"
        })
        public CoffeeItem createFromParcel(Parcel in) {
            return new CoffeeItem(in);
        }

        public CoffeeItem[] newArray(int size) {
            return (new CoffeeItem[size]);
        }

    }
    ;

    protected CoffeeItem(Parcel in) {
        this.desc = ((String) in.readValue((String.class.getClassLoader())));
        this.imageUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public CoffeeItem() {
    }

    /**
     * 
     * @param id
     * @param desc
     * @param imageUrl
     * @param name
     */
    public CoffeeItem(String desc, String imageUrl, String id, String name) {
        super();
        this.desc = desc;
        this.imageUrl = imageUrl;
        this.id = id;
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(desc);
        dest.writeValue(imageUrl);
        dest.writeValue(id);
        dest.writeValue(name);
    }

    public int describeContents() {
        return  0;
    }

}
