/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.gui.mediator;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.Objects;

/**
 * @author Haritha
 */
public class GUIImageHelper {
    public GUIImageHelper() {}
    /* TODO: Make GUI Helper a singleton & make all below methods non . */
    private static URL getFileLocation(String filename) {
        return GUIImageHelper.class.getClassLoader().getResource(filename);
    }

    private static ImageIcon scaleImage(String filename, int width, int height) {
        ImageIcon logo = new ImageIcon(getFileLocation(filename));
        Image logoImage = logo.getImage();
        Image logoImageScaled = logoImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(logoImageScaled);
    }

    public static ImageIcon getScaledLogoImage(int width, int height) {
        return scaleImage("logo.png", width, height);
    }

    public static ImageIcon getScaledLoginImage(int width, int height) {
        return scaleImage("loginImage.jpg", width, height);
    }

    public static ImageIcon getScaledUserIcon(int width, int height) {
        return scaleImage("user-icon.png", width, height);
    }

    public static ImageIcon getScaledDashboardIcon(int width, int height) {
        return scaleImage("dashboard.png", width, height);
    }

    public static ImageIcon getScaledMachinesIcon(int width, int height) {
        return scaleImage("machines.png", width, height);
    }

    public static ImageIcon getScaledActiveIcon(int width, int height) {
        return scaleImage("active.png", width, height);
    }

    public static ImageIcon getScaledInactiveIcon(int width, int height) {
        return scaleImage("inactive.png", width, height);
    }

    public static ImageIcon getScaledDeleteIcon(int width, int height) {
        return scaleImage("grey-icon.png", width, height);
    }

    public static ImageIcon getScaledPlusIcon(int width, int height) {
        return scaleImage("math-add-icon.png", width, height);
    }

    public static ImageIcon getScaledSendIcon(int width, int height) {
        return scaleImage("telegram-icon.png", width, height);
    }

    public static ImageIcon getScaledOnlineIcon(int width, int height) {
        return scaleImage("online-icon.png", width, height);
    }

    public static ImageIcon getScaledEmptyIcon(int width, int height) {
        return scaleImage("empty-icon.png", width, height);
    }

    public static ImageIcon getScaledDeactivateIcon(int width, int height) {
        return scaleImage("disconnect-icon.png", width, height);
    }

    public static ImageIcon getScaledRemoveIcon(int width, int height) {
        return scaleImage("delete-icon.png", width, height);
    }

    public static ImageIcon getScaledCashIcon(int width, int height) {
        return scaleImage("cash-icon.png", width, height);
    }

    public static ImageIcon getScaledTimeIcon(int width, int height) {
        return scaleImage("time-icon.png", width, height);
    }

    public static ImageIcon getScaledWeightIcon(int width, int height) {
        return scaleImage("weight-icon.png", width, height);
    }

    public static ImageIcon getScaledFullIcon(int width, int height) {
        return scaleImage("full-icon.png", width, height);
    }

}
