package com.example.vitali.githubapiclient.data.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.vitali.githubapiclient.data.database.table.RepositoriesTable;
import com.example.vitali.githubapiclient.data.network.model.Owner;
import com.example.vitali.githubapiclient.data.network.model.Repository;

import java.util.ArrayList;
import java.util.List;


public class DbManager implements IRepositoriesDbManager {

    private SQLiteDatabase database;
    private Context context;

    public DbManager(Context context) {
        this.context = context;
        openConnection();
    }

    public void addRepository(Repository repository) {
        ContentValues values = generateValuesFromWordModel(repository);
        database.insert(RepositoriesTable.TABLE_NAME, null, values);
        database.close();
    }

    @Override
    public int save(Repository repository) {
        ContentValues values = generateValuesFromWordModel(repository);
        int i = database.update(RepositoriesTable.TABLE_NAME,
                values,
                "id = ?",
                new String[]{String.valueOf(repository.getId())});

        return i;
    }

    @Override
    public void saveAll(List<Repository> repositories) {
        for (Repository repository : repositories) {
            save(repository);
        }
    }

    public Repository getRepository(int id) {
        Cursor cursor = database.query(RepositoriesTable.TABLE_NAME,
                RepositoriesTable.COLUMNS,
                RepositoriesTable.ID + " = ?",
                new String[]{String.valueOf(id)},
                null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        cursor.close();
        return getRepositoriesFromCursor(cursor);
    }

    @Override
    public List<Repository> getAll() {
        List<Repository> repositories = new ArrayList<>();
        Cursor cursor = database.rawQuery(SQL.GET_REPOSITORIES_QUERY, null);
        if (cursor.moveToFirst()) {
            do {
                repositories.add(getRepositoriesFromCursor(cursor));
            } while (cursor.moveToNext());
        }
        return repositories;
    }

    @Override
    public List<Repository> getAll(boolean isPrivate) {
        return null;
    }

    public void openConnection() {
        if (database == null) {
            database = new DBHelper(context).getWritableDatabase();
        } else if (!database.isOpen()) {
            database = new DBHelper(context).getWritableDatabase();
        }
    }

    public void closeConnection() {
        database.close();
    }

    private ContentValues generateValuesFromWordModel(Repository repository) {
        Log.d("MyTag", "Values Got: " + repository.getName());
        ContentValues values = new ContentValues();
        values.put(RepositoriesTable.ID, repository.getId());
        values.put(RepositoriesTable.NAME, repository.getName());
        values.put(RepositoriesTable.FULL_NAME, repository.getFullName());
        values.put(RepositoriesTable.URL, repository.getUrl());
        values.put(RepositoriesTable.DESCRIPTION, repository.getDescription());
        values.put(RepositoriesTable.IS_PRIVATE, repository.getPrivate());
        values.put(RepositoriesTable.AVATAR_URL, repository.getOwner().getAvatarUrl());
        return values;
    }

    private Repository getRepositoriesFromCursor(Cursor cursor) {
        Repository repository = new Repository();
        Owner owner = new Owner();
        repository.setFromDatabase(true);
        repository.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(RepositoriesTable.ID))));
        repository.setName(cursor.getString(cursor.getColumnIndex(RepositoriesTable.NAME)));
        repository.setFullName(cursor.getString(cursor.getColumnIndex(RepositoriesTable.FULL_NAME)));
        repository.setUrl(cursor.getString(cursor.getColumnIndex(RepositoriesTable.URL)));
        repository.setDescription(cursor.getString(cursor.getColumnIndex(RepositoriesTable.DESCRIPTION)));
        repository.setPrivate(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(RepositoriesTable.IS_PRIVATE))));
        owner.setAvatarUrl(cursor.getString(cursor.getColumnIndex(RepositoriesTable.AVATAR_URL)));
        return repository;
    }
}