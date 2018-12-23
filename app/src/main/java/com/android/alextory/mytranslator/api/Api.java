package com.android.alextory.mytranslator.api;

import com.android.alextory.mytranslator.model.Translation;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("api/v1.5/tr.json/translate")
    Call<Translation> getTranslate(@Query("key") String key,
                                   @Query("text") String text,
                                   @Query("lang") String lang);
}
