package com.company;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Observation implements Serializable {
    private List<ConcreteSubject> observedList = new ArrayList<>();

    public List<ConcreteSubject> getObservedList() {
        return observedList;
    }
    public void addToList(ConcreteSubject w){
        if (!observedList.contains(w))
            observedList.add(w);
        else
            System.out.println("already Observed");
    }
}