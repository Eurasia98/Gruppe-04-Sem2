package io.github.eurasia98.sem2.logic.productionLogic;

import io.github.eurasia98.sem2.persistence.productionPersistence.DatabaseMovieHandler;

public class MovieManager {
    public MovieManager(){}

    public Movie createMovie(String title, String productionId){
        Movie movie = new Movie(title, productionId);
        return movie;
    }

    public Boolean saveMovie(Movie movie){
        DatabaseMovieHandler databaseMovieHandler = new DatabaseMovieHandler();
        return databaseMovieHandler.saveMovie(movie);
    }
}
