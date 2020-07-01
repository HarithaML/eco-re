/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.observers;

import com.project.gui.mediator.GUIRmosHelper;

import java.util.Observable;
import java.util.Observer;

/**
 * @author hari
 */
public class RMOSObserver implements Observer {


    public RMOSObserver() {
    }

    @Override
    public void update(Observable o, Object notificationObject) {
        NotificationObject no = (NotificationObject) notificationObject;
        GUIRmosHelper helper = GUIRmosHelper.getInstance();
        if (no.notificationType.equals("RCMIsFull")) {
            helper.handleRCMIsFullTypeNotification(no.machineId, no.o);
        }
        if (no.notificationType.equals("UpdateRCMMetrics")) {
            helper.handleUpdateRCMMetricsTypeNotification(no.machineId, no.o);
        }
    }

}


    

