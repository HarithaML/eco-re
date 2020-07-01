/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.observers;

/**
 * @author hari
 */
public class NotificationObject {
    final String machineId;
    final String notificationType;
    final Object o;

    public NotificationObject(String machineId, String notificationType, Object o) {
        this.machineId = machineId;
        this.notificationType = notificationType;
        this.o = o;
    }


}
