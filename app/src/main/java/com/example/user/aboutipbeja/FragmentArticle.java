package com.example.user.aboutipbeja;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.user.aboutipbeja.model.DataContainer;
import com.example.user.aboutipbeja.model.Escola;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentArticle extends Fragment {
    public static final String BUNDLE_POSITION = "position";
    private int currentPosition;

    public FragmentArticle() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_fragment_article, container, false);
        Bundle args = getArguments();
            if (args != null) {
            currentPosition = args.getInt(BUNDLE_POSITION);
            Escola escola = DataContainer.getEscolas().get(currentPosition);

            TextView textView = (TextView) view.findViewById(R.id.textViewTitle);
            TextView textViewArticle = (TextView) view.findViewById(R.id.textViewArticle);
            ImageView imageView = (ImageView) view.findViewById(R.id.imageView2);

            textView.setText(escola.getNome());
            textViewArticle.setText(escola.getArtigo());

            Glide.with(getContext())
                    .load(escola.getImagem())
                    .into(imageView);
        }else{
          /* Escola escola = DataContainer.getEscolas().get(0);

            TextView textView = (TextView) view.findViewById(R.id.textViewTitle);
            TextView textViewArticle = (TextView) view.findViewById(R.id.textViewArticle);
            ImageView imageView = (ImageView) view.findViewById(R.id.imageView2);

            textView.setText(escola.getNome());
            textViewArticle.setText(escola.getArtigo());

            Glide.with(getContext())
                    .load(escola.getImagem())
                    .into(imageView);*/
        }
        return view;
    }
}
