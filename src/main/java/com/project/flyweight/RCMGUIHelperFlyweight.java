/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.flyweight;

import com.project.gui.mediator.GUIRCMHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hari
 */
public class RCMGUIHelperFlyweight {

    private static final Map<String, GUIRCMHelper> rcmGuiHelperCache = new HashMap<>();
    private static RCMGUIHelperFlyweight instance;

    private RCMGUIHelperFlyweight() {
    }

    public static RCMGUIHelperFlyweight getInstance() {
        if (instance == null) {
            instance = new RCMGUIHelperFlyweight();
        }
        return instance;
    }

    public GUIRCMHelper getRCMGUIHelper(String machineId) {
        return rcmGuiHelperCache.computeIfAbsent(machineId, k -> new GUIRCMHelper(machineId));
    }

}
