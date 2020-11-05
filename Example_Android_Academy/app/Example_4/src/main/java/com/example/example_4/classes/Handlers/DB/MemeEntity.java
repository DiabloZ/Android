package com.example.example_4.classes.Handlers.DB;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "meme_table")
class MemeEntity {
    @PrimaryKey(autoGenerate = true)
    private int num;

    private int id;
    private int votes;
    private int commentsCount;
    private String width;
    private String height;
    private String description;
    private String date;
    private String author;
    private String gifURL;
    private String previewURL;

    public MemeEntity(int id, int votes, int commentsCount, String width, String height,
                      String description, String date, String author, String gifURL, String previewURL) {
        this.id = id;
        this.votes = votes;
        this.commentsCount = commentsCount;
        this.width = width;
        this.height = height;
        this.description = description;
        this.date = date;
        this.author = author;
        this.gifURL = gifURL;
        this.previewURL = previewURL;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public int getId() {
        return id;
    }

    public int getVotes() {
        return votes;
    }

    public int getCommentsCount() {
        return commentsCount;
    }

    public String getWidth() {
        return width;
    }

    public String getHeight() {
        return height;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getAuthor() {
        return author;
    }

    public String getGifURL() {
        return gifURL;
    }

    public String getPreviewURL() {
        return previewURL;
    }
}
