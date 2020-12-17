package com.company;

import org.junit.jupiter.api.*;
import static org.mockito.Mockito.*;

import java.net.MalformedURLException;
import java.net.URLConnection;
import java.util.Date;

class MainTest {
    URLConnection connection;
    @Test
    void test() throws MalformedURLException {
        connection = Mockito.mock(URLConnection.class);
        ConcreteSubject sub = new ConcreteSubject();
        ConcreteObserver observer = new ConcreteObserver();
        sub.register(observer);
        sub.setModifiedDate(10L);
        Date date = new Date();
        when(connection.getLastModified()).thenReturn(date.getTime());
        sub.setModifiedDate(connection.getLastModified());
        Assertions.assertEquals(observer.getModDate(),date);
    }
}