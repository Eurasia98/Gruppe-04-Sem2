package io.github.eurasia98.sem2.logic;

import io.github.eurasia98.sem2.persistence.DatabaseMovieHandler;

public class MovieManager {
    public MovieManager(){}

    public Movie createMovie(String title, String productionId){
        Movie movie = new Movie(title, productionId);
        return movie;
    }

    public Boolean insertMovie(Movie movie){
        DatabaseMovieHandler databaseMovieHandler = new DatabaseMovieHandler();
        return databaseMovieHandler.insertMovie(movie);
    }
}
