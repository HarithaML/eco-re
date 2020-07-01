/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.gui.renderers;


import javax.swing.*;
import java.awt.*;
import java.util.logging.Logger;

/**
 * @author hari
 */
public class ImageTextListRenderer extends JLabel implements ListCellRenderer {
    private static final Logger logger = Logger.getLogger(ImageTextListRenderer.class.getName());

    @Override
    public Component getListCellRendererComponent(JList list, Object val, int index, boolean isSelected, boolean cellHasFocus) {
        // TODO Auto-generated method stub

        ImagesNText imagesNText = (ImagesNText) val;
        try {
            setIcon(imagesNText.getImage());
            setText(imagesNText.getMachineId());
        } catch (Exception e) {
            logger.severe(String.format("Failed To set list, %s", e.getMessage()));
        }

        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
        setFont(list.getFont());
        return this;
    }
}
