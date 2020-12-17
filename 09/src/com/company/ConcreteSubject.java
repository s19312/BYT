package com.company;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ConcreteSubject implements Subject, Serializable {
    public URL url;
    private long modDate;
    private List<Observer> observers = new ArrayList<>();
    ConcreteSubject(){}
    public ConcreteSubject(String url) throws MalformedURLException {
        this.url = new URL(url);
    }

    @Override
    public void register(Observer o) {
        observers.add(o);
    }

    @Override
    public void unregister(Observer o) {
        observers.remove(o);
    }

    @Override
    public void setModifiedDate(long date) {
        this.modDate = date;
        for (Observer o : observers) {
            o.update(this.modDate);
        }
    }
}
