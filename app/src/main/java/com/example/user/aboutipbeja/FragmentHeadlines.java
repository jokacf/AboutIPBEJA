package com.example.user.aboutipbeja;


import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentHeadlines extends ListFragment {


    public FragmentHeadlines() {


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(inflater.getContext(), android.R.layout.simple_list_item_1, IPBejaData.Escolas);

        setListAdapter(adapter);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        int orientation = this.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                Toast.makeText(getActivity().getBaseContext(), "PORTRAIT ",Toast.LENGTH_SHORT).show();
            FragmentArticle newFragment = new FragmentArticle();
            Bundle args = new Bundle();
            args.putInt("position", position);
            newFragment.setArguments(args);
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();

        } else {
            Toast.makeText(getActivity().getBaseContext(), "LANDSCAPE ",Toast.LENGTH_SHORT).show();
            TextView articleTextView = (TextView) getActivity().findViewById(R.id.articleTextView);
            articleTextView.setText(IPBejaData.Artigos[position]);
        }
    }
}
