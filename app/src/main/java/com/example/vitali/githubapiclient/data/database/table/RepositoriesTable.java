package com.example.vitali.githubapiclient.data.database.table;


import android.provider.BaseColumns;

public class RepositoriesTable implements BaseColumns {

    public static final String TABLE_NAME = "repository";

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String FULL_NAME = "full_name";
    public static final String URL = "url";
    public static final String DESCRIPTION = "description";
    public static final String IS_PRIVATE = "isPrivate";
    public static final String AVATAR_URL = "avatar_url";

    public static final String[] COLUMNS = {
            ID,
            NAME,
            FULL_NAME,
            URL,
            DESCRIPTION,
            IS_PRIVATE,
            AVATAR_URL
    };
}