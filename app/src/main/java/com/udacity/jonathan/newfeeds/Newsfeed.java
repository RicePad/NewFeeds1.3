package com.udacity.jonathan.newfeeds;

import java.util.ArrayList;

public class Newsfeed {

    /** NewssFeed Tittle */
    private String mTitle;

    /** Website URL of the NewsFeed */
    private String mUrl;

    /** Section to which the newsfeed belongs */
    private String mSection;

    /** Publication date of the newsfeed */
    private long mDateTime;

    private ArrayList<String> mAuthors;

    public Newsfeed(String title, String url, String section, long dateTime, ArrayList<String> authors) {
        mTitle = title;
        mUrl = url;
        mSection = section;
        mDateTime = dateTime;
        mAuthors = authors;
    }

    public String getNewsfeedTitle(){
        return mTitle;
    }

    public String getUrl(){
        return mUrl;
    }

    public String getSection(){
        return mSection;
    }

    public long getDateTime(){
        return mDateTime;
    }

    public ArrayList<String> getAuthors() { return mAuthors; }

}
