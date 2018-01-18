package com.example.user.aboutipbeja;


import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

import com.example.user.aboutipbeja.model.DataContainer;
import com.example.user.aboutipbeja.model.Escola;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentHeadlines extends ListFragment {

    CustomAdapter mAdapter;

    private static final String fakeInfo = "[\n" +
            "\t{\n" +
            "\t\"Escola\": \"Escola Superior de Tecnologia e Gestão\",\n" +
            "\t\"Artigos\": \"A ESTIG foi criada em 1991 (Decreto-Lei nº 40791, de 21 de Janeiro). Neste diploma determinava-se que as suas actividades fossem financiadas por receitas próprias, subsídios, comparticipações de empresas e de câmaras localizadas na área geográfica de influência da escola.\",\n" +
            "\t\"Imagens\": \"https://www.ipbeja.pt/SiteCollectionImages/estig_logo.png\"\n" +
            "\t},\n" +
            "\t{\n" +
            "\t\"Escola\": \"Escola Superior de Saúde\",\n" +
            "\t\"Artigos\": \"Criação da Escola de Enfermagem de Beja através do Decreto nº 569/73, de 30 de Outubro. Em Outubro de 1975, iniciou as suas actividades lectivas, em edifício próprio, com um total de 70 alunos. A partir de 15 de Setembro de 1989 passou a designar-se Escola Superior de Enfermagem de Beja, tendo sido integrada na rede nacional do ensino superior politécnico desde 23 de Dezembro de 1988 (Dec.-Lei nº 480/88).\",\n" +
            "\t\"Imagens\": \"https://www.ipbeja.pt/PublishingImages/IPBejaESS.jpg\"\n" +
            "\t},\n" +
            "\t{\n" +
            "\t\"Escola\": \"Escola Superior Agrária\",\n" +
            "\t\"Artigos\": \"Conteudo a introduzir brevemente\",\n" +
            "\t\"Imagens\": \"https://www.ipbeja.pt/PublishingImages/IPBejaESA.jpg\"\n" +
            "\t},\n" +
            "\t{\n" +
            "\t\"Escola\": \"Escola Superior de Educação\",\n" +
            "\t\"Artigos\": \"A ESEB deu início aos primeiros cursos no ano lectivo de 1986/87, processo esse articulado com a extinção da Escola do Magistério Primário de Beja nesse mesmo ano. As instalações da Escola do Magistério, pertencentes à Diocese de Beja, continuaram a ser utilizadas pela ESEB, até à construção de instalações próprias que vieram a ser ocupadas em Janeiro de 1994.\",\n" +
            "\t\"Imagens\": \"https://www.ipbeja.pt/PublishingImages/IPBejaESE.jpg\"\n" +
            "\t}\n" +
            "]";

    public FragmentHeadlines() {

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        List<Escola> escolas = new ArrayList<>();
        new FetchEscolasSyncTask().execute();
        mAdapter = new CustomAdapter(getContext(), escolas);
        setListAdapter(mAdapter);

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        int orientation = this.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            FragmentArticle newFragment = new FragmentArticle();
            Bundle args = new Bundle();
            args.putInt(FragmentArticle.BUNDLE_POSITION, position);
            newFragment.setArguments(args);
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        } else {
            FragmentArticle newFragment = new FragmentArticle();
            Bundle args = new Bundle();
            args.putInt(FragmentArticle.BUNDLE_POSITION, position);
            newFragment.setArguments(args);
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, newFragment);
            transaction.commit();
        }
    }

    public class FetchEscolasSyncTask  extends AsyncTask<Void, Void, List<Escola>> {
        @Override
        protected void onPreExecute() {
            // Runs on UI Thread
            super.onPreExecute();
        }

        @Override
        protected List<Escola> doInBackground(Void... voids) {
            // Runs on bacground thread
            List<Escola> escolas = new ArrayList<>();

            try {
                JSONArray jsonArray = new JSONArray(fakeInfo);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String nome = jsonObject.optString("Escola", "");
                    String artigo = jsonObject.optString("Artigos", "");
                    String imagem = jsonObject.optString("Imagens", "");

                    Escola escola = new Escola(nome, artigo, imagem);
                    escolas.add(escola);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return escolas;
        }

        @Override
        protected void onPostExecute(List<Escola> escolas) {
            DataContainer.setEscolas(escolas);
            mAdapter.updateList(escolas);
        }
    }
}
