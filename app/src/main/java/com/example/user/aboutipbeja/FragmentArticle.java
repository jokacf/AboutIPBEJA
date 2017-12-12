package com.example.user.aboutipbeja;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentArticle extends Fragment {


    public FragmentArticle() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_article, container, false);
    }

    @Override public void onStart() {
        super.onStart();
        Bundle args = getArguments();
        if (args != null) {
            int currentPosition = args.getInt("position");
            TextView articleTextView = (TextView) getActivity().findViewById(R.id.articleTextView);
            ImageView imageView = (ImageView) getActivity().findViewById(R.id.imageViewEscola);
            articleTextView.setText(IPBejaData.Artigos[currentPosition]);

            Glide.with(this)
                    .load(IPBejaData.Imagens[currentPosition])
                    .override(300, 200)
                    .into(imageView);
        }
    }
}
