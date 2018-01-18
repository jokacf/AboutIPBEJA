package com.example.user.aboutipbeja.model;

/**
 * Created by user on 03/01/2018.
 */

public class Escola {
    private String Nome;
    private String Artigo;
    private String Imagem;

    public Escola(String nome, String artigo, String imagem) {
        Nome = nome;
        Artigo = artigo;
        Imagem = imagem;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getArtigo() {
        return Artigo;
    }

    public void setArtigo(String artigo) {
        Artigo = artigo;
    }

    public String getImagem() {
        return Imagem;
    }

    public void setImagem(String imagem) {
        Imagem = imagem;
    }
}
