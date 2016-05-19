package com.typeof.flickpicker.database.sql;

import android.database.Cursor;

import com.typeof.flickpicker.core.Friend;
import com.typeof.flickpicker.core.Movie;

/**
 * FlickPicker
 * Group 22
 * Created on 19/05/16.
 */
public class CoreEntityFactory {

    public static Friend createFriendFromCursor(Cursor c){

        long id = c.getLong(c.getColumnIndex(FriendTable.FriendEntry.COLUMN_NAME_ID));
        long userId1 = c.getLong(c.getColumnIndex(FriendTable.FriendEntry.COLUMN_NAME_USER1ID));
        long userId2 = c.getLong(c.getColumnIndex(FriendTable.FriendEntry.COLUMN_NAME_USER2ID));
        double disMatch = c.getDouble(c.getColumnIndex(FriendTable.FriendEntry.COLUMN_NAME_DISMATCH));
        int nmbrOfMoviesBothSeen = c.getInt(c.getColumnIndex(FriendTable.FriendEntry.COLUMN_NAME_NUMBER_OF_MOVIES_BOTH_SEEN));

        Friend friendRelation = new Friend(userId1, userId2);
        friendRelation.setId(id);
        friendRelation.setDisMatch(disMatch);
        friendRelation.setNmbrOfMoviesBothSeen(nmbrOfMoviesBothSeen);
        
        return friendRelation;

    }


    public static Movie createMovieFromCursor(Cursor c) {

        String title = c.getString(c.getColumnIndex(MovieTable.MovieEntry.COLUMN_NAME_TITLE));
        long id = c.getLong(c.getColumnIndex(MovieTable.MovieEntry.COLUMN_NAME_ID));
        int year = c.getInt(c.getColumnIndex(MovieTable.MovieEntry.COLUMN_NAME_YEAR));
        double rating = c.getDouble(c.getColumnIndex(MovieTable.MovieEntry.COLUMN_NAME_COMMUNITY_RATING));
        int votes = c.getInt(c.getColumnIndex(MovieTable.MovieEntry.COLUMN_NAME_VOTES));
        String genre = c.getString(c.getColumnIndex(MovieTable.MovieEntry.COLUMN_NAME_GENRE));
        String poster = c.getString(c.getColumnIndex(MovieTable.MovieEntry.COLUMN_NAME_POSTER));
        String description = c.getString(c.getColumnIndex(MovieTable.MovieEntry.COLUMN_NAME_DESCRIPTION));

        Movie m = new Movie(id, title, year);
        m.setCommunityRating(rating);
        m.setNumberOfVotes(votes);
        m.setGenre(genre);
        m.setPoster(poster);
        m.setDescription(description);

        return m;
    }

}
