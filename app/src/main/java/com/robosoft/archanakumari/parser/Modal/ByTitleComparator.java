package com.robosoft.archanakumari.parser.Modal;

import java.util.Comparator;

/**
 * Created by archanakumari on 4/2/16.
 */
public class ByTitleComparator implements Comparator<MovieDetails> {

    @Override
    public int compare(MovieDetails objectone, MovieDetails objecttwo) {
        return objectone.getmTilte().compareTo(objecttwo.getmTilte());

    }
}
