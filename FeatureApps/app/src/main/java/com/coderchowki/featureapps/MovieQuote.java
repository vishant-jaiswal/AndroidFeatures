package com.coderchowki.featureapps;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by Vishant on 21-04-2016.
 */
public class MovieQuote {
    private String quote;
    private String movie;

    @JsonIgnore
    private String key;

    public MovieQuote() {
    }


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }



    public MovieQuote(String quote, String movie) {
        this.movie = movie;
        this.quote = quote;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }
}

