package io.github.eurasia98.sem2.logic;

public class TvProgram {

    private String ext_id;
    private String productcode;
    private String aspect_ratio;
    private String productionYear;
    private String productionCountry;
    private String series_id;
    private String episode_number;
    private String distributor_episode_count;

    public TvProgram(String ext_id, String productcode, String aspect_ratio, String productionYear,
                     String productionCountry, String series_id, String episode_number, String distributor_episode_count) {
        this.ext_id = ext_id;
        this.productcode = productcode;
        this.aspect_ratio = aspect_ratio;
        this.productionYear = productionYear;
        this.productionCountry = productionCountry;
        this.series_id = series_id;
        this.episode_number = episode_number;
        this.distributor_episode_count = distributor_episode_count;
    }

    public TvProgram(String ext_id, String productcode, String productionYear, String productionCountry,
                     String series_id, String episode_number, String distributor_episode_count) {
        this.ext_id = ext_id;
        this.productcode = productcode;
        this.productionYear = productionYear;
        this.productionCountry = productionCountry;
        this.series_id = series_id;
        this.episode_number = episode_number;
        this.distributor_episode_count = distributor_episode_count;
        this.aspect_ratio = "16:9";
    }

    public String getExt_id() {
        return ext_id;
    }

    public String getProductcode() {
        return productcode;
    }

    public String getAspect_ratio() {
        return aspect_ratio;
    }

    public String getProductionYear() {
        return productionYear;
    }

    public String getProductionCountry() {
        return productionCountry;
    }

    public String getSeries_id() {
        return series_id;
    }

    public String getEpisode_number() {
        return episode_number;
    }

    public String getDistributor_episode_count() {
        return distributor_episode_count;
    }

    public void setExt_id(String ext_id) {
        this.ext_id = ext_id;
    }

    public void setProductcode(String productcode) {
        this.productcode = productcode;
    }

    public void setAspect_ratio(String aspect_ratio) {
        this.aspect_ratio = aspect_ratio;
    }

    public void setProductionYear(String productionYear) {
        this.productionYear = productionYear;
    }

    public void setProductionCountry(String productionCountry) {
        this.productionCountry = productionCountry;
    }

    public void setSeries_id(String series_id) {
        this.series_id = series_id;
    }

    public void setEpisode_number(String episode_number) {
        this.episode_number = episode_number;
    }

    public void setDistributor_episode_count(String distributor_episode_count) {
        this.distributor_episode_count = distributor_episode_count;
    }
}
