package com.example.vitali.githubapiclient.data.network.model;

import com.google.gson.annotations.SerializedName;

import java.io.IOException;
import java.io.Serializable;


public class Repository implements Serializable {

    private Integer id;
    private String name;
    @SerializedName("full_name")
    private String fullName;
    @SerializedName("owner")
    private Owner owner;
    @SerializedName("private")
    private Boolean isPrivate;
    @SerializedName("html_url")
    private String htmlUrl;
    private String description;
    @SerializedName("fork")
    private Boolean isFork;
    private String url;

    private boolean isFromDatabase;

    public Repository() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Boolean getPrivate() {
        return isPrivate;
    }

    public void setPrivate(Boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getFork() {
        return isFork;
    }

    public void setFork(Boolean isFork) {
        this.isFork = isFork;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isFromDatabase() {
        return isFromDatabase;
    }

    public void setFromDatabase(boolean fromDatabase) {
        isFromDatabase = fromDatabase;
    }

    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        out.writeObject(id);
        out.writeObject(name);
        out.writeObject(fullName);
        out.writeObject(url);
        out.writeObject(description);
        out.writeObject(isPrivate);
        out.writeObject(owner.getAvatarUrl());
    }

    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
        id = (int) in.readObject();
        name = (String) in.readObject();
        fullName = (String) in.readObject();
        url = (String) in.readObject();
        description = (String) in.readObject();
        isPrivate = (boolean) in.readObject();
        String s = owner.getAvatarUrl();
        s = (String) in.readObject();
    }
}