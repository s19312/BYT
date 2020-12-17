package com.company;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class Caretaker {
    private List<Memento> mementoList = new ArrayList<>();

    public void add(Memento state){
        mementoList.add(state);
    }

    public Memento get(int index){
        return mementoList.get(index);
    }
}

class Originator {
    private Observation state;

    public void setState(Observation state){
        this.state = state;
    }

    public Observation getState(){
        return state;
    }

    public Memento saveStateToMemento(){
        return new Memento(state);
    }

    public void getStateFromMemento(Memento memento){
        state = memento.getState();
    }
}

class Memento implements Serializable {
    private Observation state;

    public Memento(Observation state){
        this.state = state;
    }

    public Observation getState(){
        return state;
    }
}