package com.vervyle.lw5_oop.model;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

import java.util.LinkedList;
import java.util.List;

public class MyIntegerProperty implements Observable {

    volatile private Integer value;
    private transient List<InvalidationListener> invalidationListeners;

    public MyIntegerProperty(Integer value) {
        this.value = value;
        invalidationListeners = new LinkedList<>();
    }

    public Integer getValue() {
        return value;
    }

    public void update(Integer value) {
        this.value = value;
        invalidationListeners.forEach(invalidationListener -> {
            invalidationListener.invalidated(this);
        });
    }

    @Override
    public void addListener(InvalidationListener invalidationListener) {
        invalidationListeners.add(invalidationListener);
    }

    @Override
    public void removeListener(InvalidationListener invalidationListener) {
        invalidationListeners.remove(invalidationListener);
    }
}
