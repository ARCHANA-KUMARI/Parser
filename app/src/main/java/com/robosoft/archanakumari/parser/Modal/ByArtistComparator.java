package com.robosoft.archanakumari.parser.Modal;

import java.util.Comparator;

/**
 * Created by archanakumari on 4/2/16.
 */
public class ByArtistComparator implements Comparator<MovieDetails>{

    @Override
    public int compare(MovieDetails lhs, MovieDetails rhs) {
        return lhs.getmArtist().compareTo(rhs.getmArtist());
    }
}
