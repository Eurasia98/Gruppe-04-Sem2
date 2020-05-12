package io.github.eurasia98.sem2.logic;

import io.github.eurasia98.sem2.persistence.DatabaseMovieHandler;

import java.util.ArrayList;

public class MovieManager {
    public MovieManager(){}

    public void insertMovie(Movie movie){
        DatabaseMovieHandler databaseMovieHandler = new DatabaseMovieHandler();
        ArrayList<String> movieInfo = new ArrayList<>();
        movieInfo.add(movie.getProductionID());
        movieInfo.add(movie.getTitle());
        databaseMovieHandler.insertMovie(movieInfo);
    }
}
