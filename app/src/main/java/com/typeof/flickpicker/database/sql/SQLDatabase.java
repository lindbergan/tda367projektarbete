package com.typeof.flickpicker.database.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.typeof.flickpicker.core.Movie;
import com.typeof.flickpicker.database.Database;
import com.typeof.flickpicker.database.MovieDAO;

/**
 * FlickPicker
 * Group 22
 * Created on 16-04-19.
 */
public class SQLDatabase implements Database {

    private SQLiteDatabase db;
    private Context ctx;

    public SQLDatabase(Context ctx) {
        SQLiteDatabaseHelper mDbHelper = SQLiteDatabaseHelper.getInstance(ctx);
        this.db = mDbHelper.getWritableDatabase();
        this.ctx = ctx;
    }

    @Override
    public void setUpTables() {

        dropTables();

        //-----Movie-----
        db.execSQL(MovieTable.MovieEntry.getSQLCreateTableQuery());


        //-----User-----
        db.execSQL(UserTable.UserEntry.getSQLCreateTableQuery());

        //-----Rating-----
        db.execSQL(RatingTable.RatingEntry.getSQLCreateTableQuery());


        //-----Playlist-----
        db.execSQL(PlaylistTable.PlaylistEntry.getSQLCreateTableQuery());

        //-----Friend-----
        db.execSQL(FriendTable.FriendEntry.getSQLCreateTableQuery());
    }

    @Override
    public void dropTables() {
        //-----Movie-----
        db.execSQL(MovieTable.MovieEntry.getSQLDropTableQuery());

        //-----User------
        db.execSQL(UserTable.UserEntry.getSQLDropTableQuery());

        //-----Rating-----
        db.execSQL(RatingTable.RatingEntry.getSQLDropTableQuery());

        //-----Playlist----
        db.execSQL(PlaylistTable.PlaylistEntry.getSQLDropTableQuery());

        //-----Friend-----
        db.execSQL(FriendTable.FriendEntry.getSQLDropTableQuery());
    }

    public void seedDatabase() {

        dropTables();
        setUpTables();

        //
        // Create a new map of values, where column names are the keys
        ContentValues firstMovieValues = new ContentValues();
        firstMovieValues.put(MovieTable.MovieEntry.COLUMN_NAME_ID, 5);
        firstMovieValues.put(MovieTable.MovieEntry.COLUMN_NAME_TITLE, "Terminator");
        firstMovieValues.put(MovieTable.MovieEntry.COLUMN_NAME_YEAR, 1995);
        firstMovieValues.put(MovieTable.MovieEntry.COLUMN_NAME_DESCRIPTION, "whatever");
        firstMovieValues.put(MovieTable.MovieEntry.COLUMN_NAME_GENRE, "action");
        firstMovieValues.put(MovieTable.MovieEntry.COLUMN_NAME_VOTES, 3);
        firstMovieValues.put(MovieTable.MovieEntry.COLUMN_NAME_COMMUNITY_RATING, 4.2);


        long firstNewRowIdMovie = db.insert(
                RatingTable.RatingEntry.TABLE_NAME,
                RatingTable.RatingEntry.COLUMN_NAME_NULLABLE,
                firstMovieValues);

        ContentValues secondMovieValues = new ContentValues();
        secondMovieValues.put(MovieTable.MovieEntry.COLUMN_NAME_ID, 5);
        secondMovieValues.put(MovieTable.MovieEntry.COLUMN_NAME_TITLE, "Speed");
        secondMovieValues.put(MovieTable.MovieEntry.COLUMN_NAME_YEAR, 1994);
        secondMovieValues.put(MovieTable.MovieEntry.COLUMN_NAME_DESCRIPTION, "whatever");
        secondMovieValues.put(MovieTable.MovieEntry.COLUMN_NAME_GENRE, "action");
        secondMovieValues.put(MovieTable.MovieEntry.COLUMN_NAME_VOTES, 5);
        secondMovieValues.put(MovieTable.MovieEntry.COLUMN_NAME_COMMUNITY_RATING, 3.8);

        long secondNewRowIdMovie = db.insert(
                RatingTable.RatingEntry.TABLE_NAME,
                RatingTable.RatingEntry.COLUMN_NAME_NULLABLE,
                secondMovieValues);

        ContentValues thirdMovieValues = new ContentValues();
        thirdMovieValues.put(MovieTable.MovieEntry.COLUMN_NAME_ID, 5);
        thirdMovieValues.put(MovieTable.MovieEntry.COLUMN_NAME_TITLE, "Robin Hood");
        thirdMovieValues.put(MovieTable.MovieEntry.COLUMN_NAME_YEAR, 1993);
        thirdMovieValues.put(MovieTable.MovieEntry.COLUMN_NAME_DESCRIPTION, "whatever");
        thirdMovieValues.put(MovieTable.MovieEntry.COLUMN_NAME_GENRE, "action");
        thirdMovieValues.put(MovieTable.MovieEntry.COLUMN_NAME_VOTES, 1);
        thirdMovieValues.put(MovieTable.MovieEntry.COLUMN_NAME_COMMUNITY_RATING, 4.1);

        long thirdNewRowIdMovie = db.insert(
                RatingTable.RatingEntry.TABLE_NAME,
                RatingTable.RatingEntry.COLUMN_NAME_NULLABLE,
                thirdMovieValues);

        ContentValues fourthMovieValues = new ContentValues();
        fourthMovieValues.put(MovieTable.MovieEntry.COLUMN_NAME_ID, 5);
        fourthMovieValues.put(MovieTable.MovieEntry.COLUMN_NAME_TITLE, "Jaws");
        fourthMovieValues.put(MovieTable.MovieEntry.COLUMN_NAME_YEAR, 1993);
        fourthMovieValues.put(MovieTable.MovieEntry.COLUMN_NAME_DESCRIPTION, "whatever");
        fourthMovieValues.put(MovieTable.MovieEntry.COLUMN_NAME_GENRE, "action");
        fourthMovieValues.put(MovieTable.MovieEntry.COLUMN_NAME_VOTES, 2);
        fourthMovieValues.put(MovieTable.MovieEntry.COLUMN_NAME_COMMUNITY_RATING, 4.4);

        long fourthNewRowIdMovie = db.insert(
                RatingTable.RatingEntry.TABLE_NAME,
                RatingTable.RatingEntry.COLUMN_NAME_NULLABLE,
                fourthMovieValues);

                        //---RATING---

        db.execSQL(RatingTable.RatingEntry.getSQLDropTableQuery());
        db.execSQL(RatingTable.RatingEntry.getSQLCreateTableQuery());


        //
        // Create a new map of values, where column names are the keys
        ContentValues firstRatingValues = new ContentValues();
        firstRatingValues.put(RatingTable.RatingEntry.COLUMN_NAME_ID, 5);
        firstRatingValues.put(RatingTable.RatingEntry.COLUMN_NAME_RATING, 3.0);
        firstRatingValues.put(RatingTable.RatingEntry.COLUMN_NAME_MOVIEID, 3);
        firstRatingValues.put(RatingTable.RatingEntry.COLUMN_NAME_USERID, 2);

        long firstNewRowIdRating = db.insert(
                RatingTable.RatingEntry.TABLE_NAME,
                RatingTable.RatingEntry.COLUMN_NAME_NULLABLE,
                firstRatingValues);

        ContentValues SecondRatingValues = new ContentValues();
        SecondRatingValues.put(RatingTable.RatingEntry.COLUMN_NAME_ID, 4);
        SecondRatingValues.put(RatingTable.RatingEntry.COLUMN_NAME_RATING, 3.0);
        SecondRatingValues.put(RatingTable.RatingEntry.COLUMN_NAME_MOVIEID, 2);
        SecondRatingValues.put(RatingTable.RatingEntry.COLUMN_NAME_USERID, 1);

        long secondNewRowIdRating = db.insert(
                RatingTable.RatingEntry.TABLE_NAME,
                RatingTable.RatingEntry.COLUMN_NAME_NULLABLE,
                SecondRatingValues);


        //---USER---

        db.execSQL(UserTable.UserEntry.getSQLDropTableQuery());
        db.execSQL(UserTable.UserEntry.getSQLCreateTableQuery());

        // Create a new map of values, where column names are the keys
        ContentValues userValues = new ContentValues();
        userValues.put(UserTable.UserEntry.COLUMN_NAME_ID, 12);
        userValues.put(UserTable.UserEntry.COLUMN_NAME_USERNAME, "Frodo");
        userValues.put(UserTable.UserEntry.COLUMN_NAME_PASSWORD, "TheRing");
        userValues.put(UserTable.UserEntry.COLUMN_NAME_SCORE, 0);

        long newRowIdUser = db.insert(
                UserTable.UserEntry.TABLE_NAME,
                UserTable.UserEntry.COLUMN_NAME_NULLABLE,
                userValues);


        SQLMovieDAO movieDAO = new SQLMovieDAO(this.ctx);
        movieDAO.saveMovie(new Movie("Pirates of the Caribbean", 2003));

    }


    public void clearDatabase() {
        db.execSQL(MovieTable.MovieEntry.getSQLDropTableQuery());
        db.execSQL(RatingTable.RatingEntry.getSQLDropTableQuery());
    }

}
