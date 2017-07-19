package com.example.vitali.githubapiclient.data.model;


public class Permissions {

    private Boolean isAdmin;
    private Boolean isPush;
    private Boolean isPull;

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Boolean getPush() {
        return isPush;
    }

    public void setPush(Boolean isPush) {
        this.isPush = isPush;
    }

    public Boolean getPull() {
        return isPull;
    }

    public void setPull(Boolean isPull) {
        this.isPull = isPull;
    }
}