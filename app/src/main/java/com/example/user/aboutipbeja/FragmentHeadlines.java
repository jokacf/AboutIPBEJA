package com.example.user.aboutipbeja;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentHeadlines extends Fragment {


    public FragmentHeadlines() {


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_headlines, container, false);


    }

    @Override
    public void onStart() {
        super.onStart();
        Toast.makeText(getActivity(), "" +IPBejaData.Escolas[0] + "Historia" + IPBejaData.Artigos[0], Toast.LENGTH_SHORT).show();
        Toast.makeText(getActivity(), "" +IPBejaData.Escolas[1] + "Historia" + IPBejaData.Artigos[1], Toast.LENGTH_SHORT).show();
        Toast.makeText(getActivity(), "" +IPBejaData.Escolas[2] + "Historia" + IPBejaData.Artigos[2], Toast.LENGTH_SHORT).show();
        Toast.makeText(getActivity(), "" +IPBejaData.Escolas[3] + "Historia" + IPBejaData.Artigos[3], Toast.LENGTH_SHORT).show();
    }
}
