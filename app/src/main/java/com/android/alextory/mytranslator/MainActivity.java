package com.android.alextory.mytranslator;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.alextory.mytranslator.adapter.TranslateAdapter;
import com.android.alextory.mytranslator.api.App;
import com.android.alextory.mytranslator.model.Languages;
import com.android.alextory.mytranslator.model.Translation;
import com.android.alextory.mytranslator.model.Word;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static final String KEY = "trnsl.1.1.20181222T172221Z.5c3dc733c2d9107f.c9c76a2010c5905948a4201e021c5c94ed825739";
    private RecyclerView recyclerView;
    private TranslateAdapter adapter;
    private FloatingActionButton fab;
    private EditText wordEt;

    private String wordOriginal;
    private String wordTransl;

    private Spinner spinner1;
    private Spinner spinner2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        init();
        initRec();
        fab();
    }

    private void init() {
        recyclerView = findViewById(R.id.recycler_view);
        fab = findViewById(R.id.fab);
        wordEt = findViewById(R.id.word_et);

        spinner1 = findViewById(R.id.languages1);
        spinner2 = findViewById(R.id.languages2);

        setSpinners();
    }

    private void initRec() {
        adapter = new TranslateAdapter(App.getDatabase().wordDao().getAll());

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void fab() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wordOriginal = wordEt.getText().toString();
                translate(wordOriginal);

                if (wordOriginal.equals("")) {
                    snackBar(view, "Введите слово!");
                } else {
                    snackBar(view, "Слово: \"" + wordOriginal + "\" было добавлено");

                    Word word = new Word(wordEt.getText().toString(), wordTransl);
                    App.getDatabase().wordDao().insert(word);
                    adapter.setData(App.getDatabase().wordDao().getAll());
                }
            }
        });
    }

    private void snackBar(View view, String text) {
        Snackbar snackbar;
        snackbar = Snackbar.make(view, text, Snackbar.LENGTH_LONG).setAction("Action", null);
        View snackBarView = snackbar.getView();
        snackBarView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        snackbar.setAction("Action", null);
        snackbar.show();
    }

    public void setSpinners() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.langs_array, R.layout.spinner_item_main);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);
        spinner2.setSelection(1);
    }

    public String langCode(String selectedLang) {
        String code = null;
        if (Locale.getDefault().getLanguage().equals("en")) {
            for (int i = 0; i < Languages.getLangsEN().length; i++) {
                if (selectedLang.equals(Languages.getLangsEN()[i])) {
                    code = Languages.getLangCodeEN(i);
                }
            }
        } else {
            for (int i = 0; i < Languages.getLangsRU().length; i++) {
                if (selectedLang.equals(Languages.getLangsRU()[i])) {
                    code = Languages.getLangCodeRU(i);
                }
            }
        }
        return code;
    }

    private void translate(String text) {
        String language1 = String.valueOf(spinner1.getSelectedItem());
        String language2 = String.valueOf(spinner2.getSelectedItem());

        App.getApi().getTranslate(KEY, text, langCode(language1) + "-" + langCode(language2)).enqueue(new Callback<Translation>() {
            @Override
            public void onResponse(Call<Translation> call, Response<Translation> response) {
                wordTransl = response.body().getText().get(0);
            }

            @Override
            public void onFailure(Call<Translation> call, Throwable t) {

            }
        });
    }

}
