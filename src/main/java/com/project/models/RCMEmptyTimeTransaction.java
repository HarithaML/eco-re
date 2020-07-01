/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.models;

import java.util.Date;

/**
 * @author hari
 */
public class RCMEmptyTimeTransaction {
    String machineId;
    String groupId;
    Date timestamp;

    public RCMEmptyTimeTransaction(String machineId, String groupId, Date timestamp) {
        this.machineId = machineId;
        this.groupId = groupId;
        this.timestamp = timestamp;
    }

    public String getMachineId() {
        return machineId;
    }

    public void setMachineId(String machineId) {
        this.machineId = machineId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

}
