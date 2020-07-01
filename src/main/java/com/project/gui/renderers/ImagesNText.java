/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.gui.renderers;

import javax.swing.*;

/**
 * @author hari
 */
public class ImagesNText {
    private Icon image;
    private String name;

    public ImagesNText(Icon image, String machineId) {
        this.image = image;
        this.name = machineId;
    }

    public Icon getImage() {
        return image;
    }

    public void setImage(Icon image) {
        this.image = image;
    }

    public String getMachineId() {
        return name;
    }

    public void setMachineId(String machineId) {
        this.name = machineId;
    }


}
