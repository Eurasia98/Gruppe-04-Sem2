package io.github.eurasia98.sem2.logic;

public class SearchResults {
    private String title;
    private String production_id;

    public SearchResults(String title, String production_id){
        this.title = title;
        this.production_id = production_id;
    }

    public String getTitle() {
        return title;
    }

    public String getProductionId(){return production_id; }

    @Override
    public String toString(){
        return title;
    }




}
