package com.company;

import java.io.*;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        ConcreteSubject sub = new ConcreteSubject("http://www.pja.edu.pl/");
        ConcreteSubject sub2 = new ConcreteSubject("https://pl.wikipedia.org/");
        Observer observerOne = new ConcreteObserver();
        sub.register(observerOne);

        Observation observation = new Observation();
        observation.addToList(sub);
        observation.addToList(sub2);
        sub.register(observerOne);

        Originator originator = new Originator();
        originator.setState(observation);
        Caretaker caretaker = new Caretaker();
        caretaker.add(originator.saveStateToMemento());

        FileOutputStream file = new FileOutputStream("out.txt");
        ObjectOutputStream out = new ObjectOutputStream(file);

        List<ConcreteSubject> list = observation.getObservedList();

        try {
            out.writeObject(caretaker.get(0));
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                for (ConcreteSubject w : list) {
                    w.setModifiedDate(w.url.openConnection().getLastModified());
                }
                Thread.sleep(3600);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


