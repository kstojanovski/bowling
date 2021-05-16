package org.acme.model;

import java.util.ArrayList;
import java.util.List;

public class Score {

    List<Integer> scoredValues;

    public Score() {
        this.scoredValues = new ArrayList<>();
    }

    public List<Integer> getScoredValues() {
        return scoredValues;
    }

}
