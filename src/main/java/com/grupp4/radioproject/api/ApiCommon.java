package com.grupp4.radioproject.api;

public class ApiCommon {

    // Alla sökvägar måste börja med / och får INTE sluta med /.

    /**
     * URL:en för SR:s API
     */
    public static final String BASE_URL = "http://api.sr.se/api/v2";
    public static final String PROGRAMS = "/programs";
    public static final String EPISODES = "/episodes";

    // Parametrar
    public static final String PARAM_JSON = "?format=json";
    public static String PARAM_PAGE(int pagenr) {
        return "?page=" + pagenr;
    }

}
