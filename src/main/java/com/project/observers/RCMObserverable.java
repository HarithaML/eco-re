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
public class RCMObserverable extends Observable {


    public RCMObserverable() {
    }

    public void notifyAllObservers(NotificationObject obj) {
        this.setChanged();
        this.notifyObservers(obj);
    }


}
