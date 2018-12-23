package com.android.alextory.mytranslator.api;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.android.alextory.mytranslator.bd.AppDatabase;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

    private static final String BASE_URL = "https://translate.yandex.net/";
    private static final String DATABASE_NAME = "database";

    private static AppDatabase database;
    private static Retrofit retrofit;
    private static Api api;

    @Override
    public void onCreate() {
        super.onCreate();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        database = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries()
                .build();

        api = retrofit.create(Api.class);
    }

    public static AppDatabase getDatabase() {
        return database;
    }

    public static Api getApi() {
        return api;
    }
}
