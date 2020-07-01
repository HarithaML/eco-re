/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.observers;

import com.project.flyweight.RCMGUIHelperFlyweight;
import com.project.gui.mediator.GUIRCMHelper;
import com.project.models.RCMPerItemType;

import java.util.Observable;
import java.util.Observer;

/**
 * @author hari
 */
public class RCMObserver implements Observer {

    private final String machineId;

    public RCMObserver(String machineId) {
        this.machineId = machineId;
    }

    @Override
    public void update(Observable o, Object notificationObject) {
        NotificationObject no = (NotificationObject) notificationObject;
        if (no.machineId.equals(this.machineId)) {
            GUIRCMHelper helper = RCMGUIHelperFlyweight.getInstance().getRCMGUIHelper(machineId);

            System.out.println(no.notificationType + ", " + no.machineId);
            if (no.notificationType.equals("AddItemType")) {
                helper.handleAddRCMTypeNotification(no.machineId, (RCMPerItemType) no.o);
            }
            if (no.notificationType.equals("UpdateItemType")) {
                System.out.println(no.notificationType + ", " + no.machineId);
                helper.handleUpdateRCMTypeNotification(no.machineId, (RCMPerItemType) no.o);
            }
        }
    }

}
