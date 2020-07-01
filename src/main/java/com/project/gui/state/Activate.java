/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.gui.state;

import javax.swing.*;
import java.util.logging.Logger;

/**
 * @author hari
 */
public class Activate implements RCMStatusState {
    private static final java.util.logging.Logger logger = Logger.getLogger(Activate.class.getName());

    @Override
    public void statusChanged(StatusChanged statusChanged, JFrame frame) {
        try {
            frame.setVisible(true);
        } catch (Exception ex) {
            logger.severe("frame not added");
        }
    }

}
