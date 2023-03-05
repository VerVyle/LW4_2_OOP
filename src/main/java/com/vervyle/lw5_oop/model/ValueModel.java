package com.vervyle.lw5_oop.model;

import javafx.beans.InvalidationListener;

import java.io.*;
import java.util.LinkedList;

public class ValueModel {

    public static final int MIN_VALUE;
    public static final int MAX_VALUE;

    private final ValueModelIO modelIO;
    private final MyIntegerProperty integerPropertyA;
    private final MyIntegerProperty integerPropertyB;
    private final MyIntegerProperty integerPropertyC;

    public ValueModel() {
        modelIO = new ValueModelIO();
        integerPropertyA = modelIO.getIntegerPropertyA();
        integerPropertyB = modelIO.getIntegerPropertyB();
        integerPropertyC = modelIO.getIntegerPropertyC();
    }

    private void adjustToMax() {
        Integer valA = integerPropertyA.getValue();
        if (integerPropertyB.getValue() < valA)
            integerPropertyB.update(valA);

        Integer valB = integerPropertyB.getValue();
        if (integerPropertyC.getValue() < valB)
            integerPropertyC.update(valB);
    }

    private void adjustToMin() {
        Integer valC = integerPropertyC.getValue();
        if (integerPropertyB.getValue() > valC)
            integerPropertyB.update(valC);

        Integer valB = integerPropertyB.getValue();
        if (integerPropertyA.getValue() > valB)
            integerPropertyA.update(valB);
    }

    private void adjustMid() {
        Integer valC = integerPropertyC.getValue();
        if (integerPropertyB.getValue() > valC)
            integerPropertyB.update(valC);

        Integer valA = integerPropertyA.getValue();
        if (integerPropertyB.getValue() < valA)
            integerPropertyB.update(valA);
    }

    public void updateValueA(Integer val) {
        if (val < MIN_VALUE) {
            integerPropertyA.update(MIN_VALUE);
        } else if (val > MAX_VALUE) {
            integerPropertyA.update(MAX_VALUE);
        } else {
            integerPropertyA.update(val);
        }
        adjustToMax();
    }

    public void updateValueB(Integer val) {
        if (val < MIN_VALUE) {
            integerPropertyB.update(MIN_VALUE);
        } else if (val > MAX_VALUE) {
            integerPropertyB.update(MAX_VALUE);
        } else {
            integerPropertyB.update(val);
        }
        adjustMid();
    }

    public void updateValueC(Integer val) {
        if (val < MIN_VALUE) {
            integerPropertyC.update(MIN_VALUE);
        } else if (val > MAX_VALUE) {
            integerPropertyC.update(MAX_VALUE);
        } else {
            integerPropertyC.update(val);
        }
        adjustToMin();
    }

    public void addListenerToA(InvalidationListener listener) {
        integerPropertyA.addListener(listener);
    }

    public void addListenerToB(InvalidationListener listener) {
        integerPropertyB.addListener(listener);
    }

    public void addListenerToC(InvalidationListener listener) {
        integerPropertyC.addListener(listener);
    }

    public Integer getValueA() {
        return integerPropertyA.getValue();
    }

    public Integer getValueB() {
        return integerPropertyB.getValue();
    }

    public Integer getValueC() {
        return integerPropertyC.getValue();
    }

    public void save() throws Exception {
        modelIO.saveValues();
    }

    static {
        MIN_VALUE = 0;
        MAX_VALUE = 100;
    }

    public static class ValueModelIO {

        private static final Integer INITIAL_VALUE_A;
        private static final Integer INITIAL_VALUE_B;
        private static final Integer INITIAL_VALUE_C;
        private static final String path;
        private static final File INITIAL_VALUES_FILE;

        private MyIntegerProperty integerPropertyA;
        private MyIntegerProperty integerPropertyB;
        private MyIntegerProperty integerPropertyC;

        private void initializeFromTemplate() {
            integerPropertyA = new MyIntegerProperty(INITIAL_VALUE_A);
            integerPropertyB = new MyIntegerProperty(INITIAL_VALUE_B);
            integerPropertyC = new MyIntegerProperty(INITIAL_VALUE_C);
        }

        private void readValues() throws Exception {
            FileInputStream in = new FileInputStream(INITIAL_VALUES_FILE);
            ObjectInputStream ois = new ObjectInputStream(in);

            integerPropertyA = (MyIntegerProperty) ois.readObject();
            integerPropertyB = (MyIntegerProperty) ois.readObject();
            integerPropertyC = (MyIntegerProperty) ois.readObject();

            integerPropertyA.setInvalidationListeners(new LinkedList<>());
            integerPropertyB.setInvalidationListeners(new LinkedList<>());
            integerPropertyC.setInvalidationListeners(new LinkedList<>());
        }

        public ValueModelIO() {
            try {
                readValues();
            } catch (Exception e) {
                e.printStackTrace();
                initializeFromTemplate();
            }
        }

        public final void saveValues() throws Exception {
            FileOutputStream out = new FileOutputStream(INITIAL_VALUES_FILE);
            ObjectOutputStream oos = new ObjectOutputStream(out);
            oos.writeObject(integerPropertyA);
            oos.writeObject(integerPropertyB);
            oos.writeObject(integerPropertyC);
        }

        public MyIntegerProperty getIntegerPropertyA() {
            return integerPropertyA;
        }

        public MyIntegerProperty getIntegerPropertyB() {
            return integerPropertyB;
        }

        public MyIntegerProperty getIntegerPropertyC() {
            return integerPropertyC;
        }

        static {
            INITIAL_VALUE_A = 0;
            INITIAL_VALUE_B = 50;
            INITIAL_VALUE_C = 100;
            path = "c:\\temp\\LW5\\initialValues.ser";
            INITIAL_VALUES_FILE = new File(path);
        }
    }

}
