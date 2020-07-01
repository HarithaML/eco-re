/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.gui.state;

import javax.swing.*;

/**
 * @author hari
 */
public class StatusChanged {
    private final JFrame frame;
    private RCMStatusState currentstate;


    public StatusChanged(JFrame frame) {
        currentstate = new Activate();
        this.frame = frame;

    }

    public void setState(RCMStatusState state) {
        currentstate = state;
    }

    public void statusChanged() {
        currentstate.statusChanged(this, frame);
    }
}
