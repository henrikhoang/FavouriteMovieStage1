package com.example.henrikhoang.projectmoviestage1;

/**
 * Created by henrikhoang on 6/12/17.
 */


public class Film {
    private int id;
    private String title;
    private String posterPath;
    private int vote;
    private String date;
    private String overview;

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getPosterPath()
    {
        return posterPath;
    }

    public void setPosterPath(String posterPath){
         this.posterPath  = posterPath;
    }

    public String getDate () {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) { this.vote = vote; }

//    public Film(String fTitle, String fOverview,
//                int fVote, String fDate, String fPosterPath) {
//        this.title = fTitle;
//        this.overview = fOverview;
//        this.posterPath = fPosterPath;
//        this.vote = fVote;
//        this.date = fDate;
//    }

}
