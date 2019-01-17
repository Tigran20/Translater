package com.android.alextory.mytranslator.bd;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.android.alextory.mytranslator.model.Word;

import java.util.List;

@Dao
public interface WordDao {
    @Query("SELECT * FROM word")
    List<Word> getAll();

    @Insert
    void insert(Word word);

    @Query("DELETE FROM word")
    void deleteAll();
}
