package com.typeof.flickpicker.activities;

import android.app.Application;
import android.content.Context;

import com.typeof.flickpicker.core.User;
import com.typeof.flickpicker.database.Database;
import com.typeof.flickpicker.database.FriendDAO;
import com.typeof.flickpicker.database.MovieDAO;
import com.typeof.flickpicker.database.PlaylistDAO;
import com.typeof.flickpicker.database.RatingDAO;
import com.typeof.flickpicker.database.UserDAO;
import com.typeof.flickpicker.database.sql.SQLDatabase;
import com.typeof.flickpicker.database.sql.SQLFriendDAO;
import com.typeof.flickpicker.database.sql.SQLMovieDAO;
import com.typeof.flickpicker.database.sql.SQLPlaylistDAO;
import com.typeof.flickpicker.database.sql.SQLRatingDAO;
import com.typeof.flickpicker.database.sql.SQLUserDAO;

/**
 * FlickPicker
 * Group 22
 * Created on 16-05-03.
 */
public class App extends Application {

    private static Context mContext;
    private static User mCurrentUser;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        mCurrentUser = new User("pelle", "password");
        mCurrentUser.setScore(0);
        getUserDAO().saveUser(mCurrentUser);
    }

    public static User getCurrentUser() {return mCurrentUser;}

    public static MovieDAO getMovieDAO() {
        return new SQLMovieDAO(mContext);
    }

    public static UserDAO getUserDAO() {
        return new SQLUserDAO(mContext);
    }

    public static RatingDAO getRatingDAO() {
        return new SQLRatingDAO(mContext);
    }

    public static PlaylistDAO getPlaylistDAO() {
        return new SQLPlaylistDAO(mContext);
    }

    public static FriendDAO getFriendDAO() {
        return new SQLFriendDAO(mContext);
    }

    public static Database getDatabaseSeed() {
        return new SQLDatabase(mContext);
    }
}
