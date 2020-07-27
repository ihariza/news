package com.ihariza.news.presentation.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class ReportDto implements Parcelable {

    private String id;
    private String title;
    private String description;
    private String url;
    private String author;
    private String image;
    private String language;
    private List<String> category = null;
    private String published;

    public ReportDto() {
    }

    protected ReportDto(Parcel in) {
        id = in.readString();
        title = in.readString();
        description = in.readString();
        url = in.readString();
        author = in.readString();
        image = in.readString();
        language = in.readString();
        category = in.createStringArrayList();
        published = in.readString();
    }

    public static final Creator<ReportDto> CREATOR = new Creator<ReportDto>() {
        @Override
        public ReportDto createFromParcel(Parcel in) {
            return new ReportDto(in);
        }

        @Override
        public ReportDto[] newArray(int size) {
            return new ReportDto[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeString(url);
        parcel.writeString(author);
        parcel.writeString(image);
        parcel.writeString(language);
        parcel.writeStringList(category);
        parcel.writeString(published);
    }
}
