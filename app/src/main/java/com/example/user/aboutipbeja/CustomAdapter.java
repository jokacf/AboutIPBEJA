package com.example.user.aboutipbeja;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.user.aboutipbeja.model.Escola;

import java.util.List;

/**
 * Created by user on 03/01/2018.
 */

public class CustomAdapter extends BaseAdapter {
    private List<Escola> escolasList;
    private Context context;

    public CustomAdapter(Context context, List<Escola> escolas) {
        this.context = context;
        this.escolasList = escolas;
    }

    @Override
    public int getCount() {
        return this.escolasList.size();
    }

    @Override
    public Escola getItem(int position) {
        return this.escolasList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView= layoutInflater.inflate(R.layout.row_headlines, null);
        }

        Escola escola = getItem(position);

        TextView textView = (TextView) convertView.findViewById(R.id.textViewEscola);
        textView.setText(escola.getNome());

        return convertView;
    }

    public void updateList(List<Escola> novasEscolas) {
        this.escolasList = novasEscolas;
        notifyDataSetChanged();
    }
}
