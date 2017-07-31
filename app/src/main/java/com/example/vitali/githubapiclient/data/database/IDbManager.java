package com.example.vitali.githubapiclient.data.database;


interface IDbManager {

    void openConnection();

    void closeConnection();
}