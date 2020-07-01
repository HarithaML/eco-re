/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.gui.renderers;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

/**
 * @author hari
 */
public class ImageTextTableRenderer extends DefaultTableCellRenderer {
    final JLabel r = new JLabel();

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {

        r.setIcon(((ImagesNText) value).getImage());
        r.setText(((ImagesNText) value).getMachineId());
        return r;
    }

}
