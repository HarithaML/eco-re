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
public interface RCMStatusState {
    void statusChanged(StatusChanged statusChanged, JFrame frame);
}
