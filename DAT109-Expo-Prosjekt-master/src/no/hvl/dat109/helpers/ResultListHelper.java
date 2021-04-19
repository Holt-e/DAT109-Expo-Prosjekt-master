package no.hvl.dat109.helpers;

import no.hvl.dat109.model.Stand;


import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ResultListHelper {


    public static List<Stand> sortStandsByVotes(List<Stand> stands) {

        Collections.sort(stands, new Comparator<Stand>() {
            @Override
            public int compare(Stand o1, Stand o2) {
                return o2.getScore().compareTo(o1.getScore());
            }
        });
        return stands;
    }
}
