package com.typeof.flickpicker.activities;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TabHost;
import android.widget.Toast;

import com.typeof.flickpicker.R;
import com.typeof.flickpicker.core.Movie;
import com.typeof.flickpicker.core.User;
import com.typeof.flickpicker.database.sql.MovieTable;
import com.typeof.flickpicker.database.sql.UserTable;

import java.util.List;

public class SearchFragment extends Fragment {

    private TabHost mTabHostSearch;
    private ListView listViewSearchMovies;
    private ListView listViewSearchUser;
    private SearchView mSearchViewMovie;
    private SearchView mSearchViewFriend;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View searchView = inflater.inflate(R.layout.activity_search, container, false);
        hookUpViews(searchView);
        configureTabs(searchView);
        setUpListeners();
        return searchView;
    }

    public void hookUpViews(View view){
        listViewSearchMovies = (ListView) view.findViewById(R.id.listViewSearchMovie);
        listViewSearchUser = (ListView) view.findViewById(R.id.listViewSearchFriend);
        mSearchViewMovie = (SearchView) view.findViewById(R.id.searchViewMovie);
        mSearchViewFriend = (SearchView) view.findViewById(R.id.searchViewFriend);

        populateMovieListView(listViewSearchMovies, App.getMovieDAO().getCommunityTopPicks(25));
        populateUserListView(listViewSearchUser, App.getUserDAO().searchUser(UserTable.UserEntry.COLUMN_NAME_USERNAME, ""));

    }
    public void configureTabs(View view){

        mTabHostSearch = (TabHost) view.findViewById(R.id.tabHostSearch);
        mTabHostSearch.setup();

        final TabHost.TabSpec mTabSpecSearchMovie = mTabHostSearch.newTabSpec("searchMovie");
        mTabSpecSearchMovie.setContent(R.id.tabSearchMovie);
        mTabSpecSearchMovie.setIndicator("Search Movie");
        mTabHostSearch.addTab(mTabSpecSearchMovie);

        final TabHost.TabSpec mTabSpecSearchFriend = mTabHostSearch.newTabSpec("searchFriend");
        mTabSpecSearchFriend.setContent(R.id.tabSearchFriend);
        mTabSpecSearchFriend.setIndicator("Search User");
        mTabHostSearch.addTab(mTabSpecSearchFriend);

        setActiveTabColor();//default

        //NOTE: only needed if we want to return to specific tab and have previous search result displayed:
        mTabHostSearch.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {

                setActiveTabColor();

                if(tabId.equals("searchMovie")){
                    //do this
                }
                else{
                    //do that
                }
            }
        });

    }

    public void setActiveTabColor(){
        mTabHostSearch.getTabWidget().getChildAt(mTabHostSearch.getCurrentTab()).getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
    }

    public void setUpListeners(){

        //NOTE: these two line should be implemented in the XML-file, not the actual code:
        mSearchViewMovie.setIconifiedByDefault(false);
        mSearchViewFriend.setIconifiedByDefault(false);

        mSearchViewMovie.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                List<Movie> matches = App.getMovieDAO().searchMovieBy(MovieTable.MovieEntry.COLUMN_NAME_TITLE, s);

                if(matches.size() != 0){
                    populateMovieListView(listViewSearchMovies, matches);
                }
                else{
                    Toast message = Toast.makeText(getActivity(), "No such movie in database", Toast.LENGTH_SHORT);
                    message.show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                List<Movie> matches = App.getMovieDAO().searchMovieBy(MovieTable.MovieEntry.COLUMN_NAME_TITLE, s);
                populateMovieListView(listViewSearchMovies, matches);
                return true;
            }
        });


        mSearchViewFriend.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                List<User> matches = App.getUserDAO().searchUser(UserTable.UserEntry.COLUMN_NAME_USERNAME, s);

                if(matches.size() != 0){
                    populateUserListView(listViewSearchUser, matches);
                }
                else{
                    Toast message = Toast.makeText(getActivity(), "No such user in database", Toast.LENGTH_SHORT);
                    message.show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                List<User> matches = App.getUserDAO().searchUser(UserTable.UserEntry.COLUMN_NAME_USERNAME, s);
                populateUserListView(listViewSearchUser, matches);
                return true;
            }
        });

    }

    public void populateMovieListView(ListView listView, List<Movie> listOfViewCellsWeGotFromHelpClass){

        //Code for populating elements in the listView;

        ListAdapter adapter = new MovieAdapter(getActivity(),listOfViewCellsWeGotFromHelpClass.toArray());
        listView.setAdapter(adapter);
    }

    public void populateUserListView(ListView listView, List<User> listOfViewCellsWeGotFromHelpClass){

        ListAdapter adapter = new UserAdapter(getActivity(),listOfViewCellsWeGotFromHelpClass.toArray());
        listView.setAdapter(adapter);
    }

}
