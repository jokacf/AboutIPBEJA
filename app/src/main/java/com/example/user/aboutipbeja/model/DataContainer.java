package com.example.user.aboutipbeja.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 04/01/2018.
 */

public class DataContainer {
    private static List<Escola> escolas;

    public static List<Escola> getEscolas() {
        if (null == escolas) escolas = new ArrayList<>();
        return escolas;
    }

    public static void setEscolas(List<Escola> escolas) {
        DataContainer.escolas = escolas;
    }
}
