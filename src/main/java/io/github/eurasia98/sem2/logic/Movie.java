package io.github.eurasia98.sem2.logic;

public class Movie extends Production {
    private String production_fk_id;
    private int movie_id;

    public Movie(String title, String productionID){
        super(title, productionID);
        super.setProductionType("Movie");
    }

    public String getProduction_fk_id() {
        return production_fk_id;
    }

    public void setProduction_fk_id(String production_fk_id) {
        this.production_fk_id = production_fk_id;
    }

    public int getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }
}
