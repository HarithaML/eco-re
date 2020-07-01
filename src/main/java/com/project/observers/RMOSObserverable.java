/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.observers;

import java.util.Observable;

/**
 * @author hari
 */
public class RMOSObserverable extends Observable {
    private static RMOSObserverable instance;

    private RMOSObserverable() {
    }

    public static RMOSObserverable getInstance() {
        if (instance == null) {
            instance = new RMOSObserverable();
        }
        return instance;
    }

    public void notifyAllObservers(NotificationObject obj) {
        instance.setChanged();
        instance.notifyObservers(obj);
    }

}
