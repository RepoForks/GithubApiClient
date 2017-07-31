package com.example.vitali.githubapiclient.data.database;


import com.example.vitali.githubapiclient.data.database.table.RepositoriesTable;

interface SQL {

    String DB_NAME = "repositories";
    int DB_VERSION = 1;
    String DROP_QUERY = "DROP TABLE IF EXIST " + RepositoriesTable.TABLE_NAME;
    String GET_REPOSITORIES_QUERY = "SELECT * FROM " + RepositoriesTable.TABLE_NAME;


    String CREATE_TABLE_QUERY = "CREATE TABLE " + RepositoriesTable.TABLE_NAME + "" +
            "(" + RepositoriesTable.ID + " INTEGER PRIMARY KEY, " +
            RepositoriesTable.NAME + " TEXT not null, " +
            RepositoriesTable.FULL_NAME + " TEXT not null, " +
            RepositoriesTable. URL + " TEXT not null, " +
            RepositoriesTable.DESCRIPTION + " TEXT, " +
            RepositoriesTable.IS_PRIVATE + " BOOLEAN, " +
            RepositoriesTable.AVATAR_URL + " TEXT)";
}