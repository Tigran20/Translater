package com.android.alextory.mytranslator.bd;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.android.alextory.mytranslator.model.Word;

@Database(entities = {Word.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract WordDao wordDao();
}