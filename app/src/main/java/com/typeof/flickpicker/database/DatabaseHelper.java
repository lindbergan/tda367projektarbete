package com.typeof.flickpicker.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.typeof.flickpicker.core.CoreEntity;
import com.typeof.flickpicker.core.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * FlickPicker
 * Group 22
 * Created on 16-04-20.
 */
public abstract class DatabaseHelper<T> {

    private SQLiteDatabase db;

    public DatabaseHelper(Context ctx) {
        SQLiteDatabaseHelper mDbHelper = new SQLiteDatabaseHelper(ctx);
        db = mDbHelper.getWritableDatabase();
    }

    public Cursor find(long id, String tableName) throws DatabaseRecordNotFoundException {
        Cursor cursor = db.rawQuery("SELECT * FROM " + tableName + " WHERE id = ? LIMIT 1", new String[]{String.valueOf(id)});
        if (cursor.getCount() == 0) {
            throw new DatabaseRecordNotFoundException("Record not found in database");
        }
        return cursor;
    }

    public long save(CoreEntity object, String tableName, ContentValues values) {
        // If we have an id on this object
        // Check if it exists in the database

        if (object.getId() != 0) {
            try {
                this.find(object.getId(), tableName);
                update(object, values, tableName);
                return object.getId();
            } catch (DatabaseRecordNotFoundException e) {}
        }

        long newRowId = db.insert(tableName, MovieTable.MovieEntry.COLUMN_NAME_NULLABLE, values);

        // If there was an error saving the record, we return -1 as the id
        if (newRowId == -1) return -1;

        object.setId(newRowId);

        return newRowId;
    }

    public void update(CoreEntity object, ContentValues values, String tableName) {
        String selection = "id LIKE ?";
        String[] selectionArgs = { String.valueOf(object.getId()) };

        int count = db.update(
                tableName, 
                values,
                selection,
                selectionArgs);
    }

    public long delete(CoreEntity object, String tableName) throws IllegalStateException {
        if (object.getId() == 0) {
            throw new IllegalStateException("Core Entity cannot be deleted before it has been saved to the database");
        }

        db.rawQuery("DELETE FROM " + tableName + " WHERE id = ?", new String[]{String.valueOf(object.getId())});

        return object.getId();
    }

    public Cursor search(String tableName, String column, String searchString) {
        List<Movie> results = new ArrayList<>();
        return db.rawQuery("SELECT * FROM " + tableName + " WHERE " + column + " LIKE ?",
                new String[]{"%" + searchString + "%"});
    }
}
