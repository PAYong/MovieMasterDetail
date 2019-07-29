package com.example.android.itunesappandroidv2.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "movie_table")
public class Movie {

    @PrimaryKey
    @NonNull
    private String trackName;
    private String artworkUrl100;
    private String trackPrice;
    private String primaryGenreName;

    //Other info
    private String kind;
    private String longDescription;
    private String artistName;
    private String collectionName;
    private String collectionPrice;
    private String releaseDate;
    private String country;
    private String currency;
    private String trackViewUrl;

    public Movie(String trackName, String artworkUrl100, String trackPrice, String primaryGenreName, String kind, String longDescription, String artistName, String collectionName, String collectionPrice, String releaseDate, String country, String currency, String trackViewUrl) {
        this.trackName = trackName;
        this.artworkUrl100 = artworkUrl100;
        this.trackPrice = trackPrice;
        this.primaryGenreName = primaryGenreName;
        this.kind = kind;
        this.longDescription = longDescription;
        this.artistName = artistName;
        this.collectionName = collectionName;
        this.collectionPrice = collectionPrice;
        this.releaseDate = releaseDate;
        this.country = country;
        this.currency = currency;
        this.trackViewUrl = trackViewUrl;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getArtworkUrl100() {
        return artworkUrl100;
    }

    public void setArtworkUrl100(String artworkUrl100) {
        this.artworkUrl100 = artworkUrl100;
    }

    public String getTrackPrice() {
        return trackPrice;
    }

    public void setTrackPrice(String trackPrice) {
        this.trackPrice = trackPrice;
    }

    public String getPrimaryGenreName() {
        return primaryGenreName;
    }

    public void setPrimaryGenreName(String primaryGenreName) {
        this.primaryGenreName = primaryGenreName;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public String getCollectionPrice() {
        return collectionPrice;
    }

    public void setCollectionPrice(String collectionPrice) {
        this.collectionPrice = collectionPrice;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getTrackViewUrl() {
        return trackViewUrl;
    }

    public void setTrackViewUrl(String trackViewUrl) {
        this.trackViewUrl = trackViewUrl;
    }




}
