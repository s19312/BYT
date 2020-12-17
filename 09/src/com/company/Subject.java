package com.company;

public interface Subject {
    void register(Observer obj);
    void unregister(Observer obj);
    void setModifiedDate(long l);
}
