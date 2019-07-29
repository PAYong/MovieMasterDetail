package com.example.android.itunesappandroidv2.model;

import java.util.List;

public class MovieList {

    private int resultCount;

    private List<Movie> results;

    public int getResultCount() {
        return resultCount;
    }

    public void setResultCount(int resultCount) {
        this.resultCount = resultCount;
    }

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }


}
