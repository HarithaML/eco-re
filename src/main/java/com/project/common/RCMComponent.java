/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.common;

import com.project.models.RCM;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hari
 */
public abstract class RCMComponent {

    public void add(RCMComponent newRCMComponent) {
        throw new UnsupportedOperationException();
    }

    public void remove(RCMComponent newRCMComponent) {
        throw new UnsupportedOperationException();
    }

    public RCMComponent getComponent(int componentIndex) {
        throw new UnsupportedOperationException();
    }

    public ArrayList<RCMComponent> getComponents() {
        throw new UnsupportedOperationException();
    }

    public List<RCM> getRCMComponentList() {
        throw new UnsupportedOperationException();
    }
}
