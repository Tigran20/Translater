package com.android.alextory.mytranslator.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.alextory.mytranslator.R;
import com.android.alextory.mytranslator.model.Word;

import java.util.List;

public class TranslateAdapter extends RecyclerView.Adapter<TranslateAdapter.ViewHolder> {

    private List<Word> dictionaryList;

    public TranslateAdapter(List<Word> dictionaryList) {
        this.dictionaryList = dictionaryList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.word_list_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textOriginal.setText(dictionaryList.get(position).getTextOriginal());
        holder.textTranslated.setText(dictionaryList.get(position).getTextTranslated());
    }

    @Override
    public int getItemCount() {
        if (dictionaryList == null) {
            return 0;
        }
        return dictionaryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textOriginal;
        private TextView textTranslated;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            textOriginal = itemView.findViewById(R.id.word);
            textTranslated = itemView.findViewById(R.id.textTranslated);
        }
    }

    public void setData(List<Word> newData) {
        dictionaryList.clear();
        dictionaryList.addAll(newData);
        notifyDataSetChanged();
    }
}
