package com.company;

import java.io.Serializable;
import java.util.Date;

public class ConcreteObserver implements Observer, Serializable {
    private Date modDate;

    public Date getModDate() {
        return modDate;
    }

    @Override
    public void update(long date) {
        modDate = new Date(date);
        System.out.println("Observer : updated " + this.modDate);
    }
}
