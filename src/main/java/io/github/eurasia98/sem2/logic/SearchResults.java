package io.github.eurasia98.sem2.logic;

public class SearchResults {
    private String SearchResultType;
    private String title;
    private String production_id;
    private String name;
    private String person_id;

    public SearchResults(int productionIdentifier,String title, String production_id){
        this.SearchResultType = "production";
        this.title = title;
        this.production_id = production_id;
    }

    public SearchResults(String name, String person_id, int personIdentifier){
        this.SearchResultType = "person";
        this.name = name;
        this.person_id = person_id;
    }

    public String getSearchResultType() {
        return SearchResultType;
    }

    public String getTitle() {
        return title;
    }

    public String getProductionId(){return production_id; }

    public String getName() {
        return name;
    }

    public String getPerson_id() {
        return person_id;
    }

    @Override
    public String toString(){
        return title;
    }




}
