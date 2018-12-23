package com.android.alextory.mytranslator.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Word {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String textOriginal;
    private String textTranslated;

    public Word(String textOriginal, String textTranslated) {
        this.textOriginal = textOriginal;
        this.textTranslated = textTranslated;
    }

    public String getTextOriginal() {
        return textOriginal;
    }

    public String getTextTranslated() {
        return textTranslated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
