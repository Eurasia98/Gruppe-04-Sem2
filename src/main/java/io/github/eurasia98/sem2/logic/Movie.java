package io.github.eurasia98.sem2.logic;

import java.time.LocalDate;
import java.util.ArrayList;

public class Movie extends Production{
    public Movie(String title, int productionID, int releaseYear, LocalDate creationDate, ArrayList<Credit> myCreditsList){
        super(title, productionID, releaseYear, creationDate, myCreditsList);
    }
}
