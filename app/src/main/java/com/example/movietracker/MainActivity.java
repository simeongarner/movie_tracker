package com.example.movietracker;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

/****************************
 * To do List:
 *
 *      Add Movie List
 *      Edit Movie Function
 *      Delete Movie Button
 *
 * Extra:
 *
 *      Highlight Movie Searched for
 *      Move to Movie Searched for
 ************************************/

public class MainActivity extends AppCompatActivity {

    public static List<Movie> movieList = new ArrayList<>();
    public String allMovies = "";
    public LinearLayoutManager mLinearLayoutManager;
    public static final String NAME_MOVIE = "NAME_MOVIE";
    public MovieAdapter movieAdapter = new MovieAdapter(movieList);
    public RecyclerView recyclerView;
    public int pos = 0;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BigLetters();
        sortMovieList();
        showMoviesList();
    }


    /*

     */
    public void BigLetters(){
        for (int i = 0; i < allMovies.length(); i++){
            char c = allMovies.charAt(i + 1);
            if (allMovies.charAt(i) == '\n'  && i + 1 < allMovies.length() && !Character.isUpperCase(c)){
                allMovies = allMovies.substring(0, i) + Character.toUpperCase(c) + allMovies.substring(i + 2);
            }
        }
    }


    public void showMoviesList(){
        recyclerView = (RecyclerView) findViewById(R.id.movie_recycler);
        recyclerView.setHasFixedSize(true);
        mLinearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        recyclerView.setAdapter(movieAdapter);
        ((EditText) findViewById(R.id.editMovieName)).setText("");
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void addNewMovie(View v){
        EditText movieTxtName = (EditText) findViewById(R.id.editMovieName);
        String movieName = movieTxtName.getText().toString();
        Movie m = new Movie(movieName);
        movieList.add(m);
        showMoviesList();
        sortMovieList();
    }

    public void searchMovie(View v) {
        EditText movieTxtName = (EditText) findViewById(R.id.editMovieName);
        TextView textView = findViewById(R.id.showMovieFound);
        String movieName = movieTxtName.getText().toString();
        for (int i = 0; i < movieList.size(); i++){
            if (movieList.get(i).name.equalsIgnoreCase(movieName)){
                movieName = "You have " + movieName;
                textView.setText(movieName);
            }
            else {
                String nope = "You don't have that movie.  Check spelling.";
                textView.setText(nope);
            }
        }
    }

    public void editMovie(View v){
        Intent intent = new Intent(this, EditMovie.class);
        View view = null;
//        movieAdapter.
//        View w = mLinearLayoutManager.findViewByPosition(mLinearLayoutManager.getPosition(v));
//        TextView textView = w.findViewById(R.id.movieName);
//        String nameM = textView.getText().toString();
        recyclerView.findViewHolderForAdapterPosition(pos);
        intent.putExtra(NAME_MOVIE, "Hello Keira");
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void sortMovieList(){
        Movie temp;
        char first;
        char second;
        for (int i = 0; i < movieList.size(); i++){
            first = movieList.get(i).name.charAt(0);
            for (int j = i + 1; j < movieList.size(); j++){
                second = movieList.get(j).name.charAt(0);
                int letter = Character.compare(first, second);
                if (letter == 0){
                    Boolean A = FALSE;
                    int nextL = 1;
                    int limit = movieList.get(i).name.length();
                    if (limit > movieList.get(j).name.length()){
                        limit = movieList.get(j).name.length();
                        A = TRUE;
                    }
                    while (letter == 0 && nextL < limit){
                        letter = Character.compare(movieList.get(i).name.charAt(nextL), movieList.get(j).name.charAt(nextL));
                        nextL++;
                    }
                    if (letter == 0 && A == TRUE){
                        temp = movieList.get(i);
                        movieList.set(i, movieList.get(j));
                        movieList.set(j, temp);
                    }
                    // Also think about beta test with mom and nathan (finding a way to get it to them)
                }
                if(letter > 0){
                    temp = movieList.get(i);
                    movieList.set(i, movieList.get(j));
                    movieList.set(j, temp);
                }
            }
        }
    }
}