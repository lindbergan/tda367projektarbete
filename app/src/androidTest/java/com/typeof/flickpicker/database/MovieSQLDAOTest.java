package com.typeof.flickpicker.database;
import android.test.AndroidTestCase;

import com.typeof.flickpicker.core.Movie;
import com.typeof.flickpicker.database.sql.MovieSQLDAO;

import java.util.List;

/**
 * FlickPicker
 * Group 22
 * Created on 16-04-19.
 */
public class MovieSQLDAOTest extends AndroidTestCase {

    private MovieSQLDAO mMovieDatabaseHelper;
    private DatabaseSeed mDatabaseSeed;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mMovieDatabaseHelper = new MovieSQLDAO(getContext());
        mDatabaseSeed = new DatabaseSeed(getContext());
        mDatabaseSeed.seedDatabase();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        mDatabaseSeed.clearDatabase();
    }

    /**
     * Tests if we can find a record in the database
     * @throws Exception
     */
    public void testFind() throws Exception {
        Movie movie = mMovieDatabaseHelper.find(5); // This record is created via the DatabaseSeed
        assertEquals("Checking if fetching movie is successful", "Shawshank Redemption", movie.getTitle());
    }

    /**
     * Tests if a record is saved in the database
     * @throws Exception
     */
    public void testSave() throws Exception {
        Movie movie = new Movie("Rocky");
        long rowId = mMovieDatabaseHelper.save(movie);
        assertFalse(rowId == -1);
    }

    /**
     * Tests if we can create a record and then update it
     * @throws Exception
     */
    public void testUpdate() throws Exception {
        Movie movie = new Movie("2001: A Space Odyssey");
        long movieId = mMovieDatabaseHelper.save(movie);

        // We assert that the movie was saved and was given a unique ID;
        assertFalse(movieId == -1);

        movie.setTitle("2001");
        mMovieDatabaseHelper.save(movie);

        // We now look in our database for the record saved
        Movie movieFetched = mMovieDatabaseHelper.find(movieId);

        // Check if the movie has the new updated title
        assertEquals(movieFetched.getTitle(), "2001");
    }

    /**
     * Tests if we can create a movie in the database and then find it by searching for it.
     * @throws Exception
     */
    public void testSearch() throws Exception {
        Movie movie = new Movie("Pulp Fiction");
        long id = mMovieDatabaseHelper.save(movie);

        List<Movie> results = mMovieDatabaseHelper.search("Pulp");

        assertEquals(results.size(), 1);

        Movie foundMovie = results.get(0);
        assertEquals(id, foundMovie.getId());
    }

    public void testDelete() throws Exception {
        Movie movie = new Movie("Reservoir Dogs");
        long id = mMovieDatabaseHelper.save(movie);

        mMovieDatabaseHelper.delete(movie);

        Movie foundMovie = mMovieDatabaseHelper.find(id);
    }

}