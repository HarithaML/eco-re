/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.models;


import com.project.common.RCMComponent;
import com.project.enums.StatusEnum;

import java.sql.ResultSet;
import java.util.Date;

/**
 * @author hari
 */
public class RCM extends RCMComponent {
    private String machineId;
    private String locationId;
    private String groupId;
    private StatusEnum status;
    private Date createdOn;

    private RCM(String machineId, String locationId, String groupId, StatusEnum status, Date createdOn) {
        this.machineId = machineId;
        this.locationId = locationId;
        this.groupId = groupId;
        this.status = status;
        this.createdOn = createdOn;
    }

    public static RCM fromResultSet(ResultSet rs) throws Exception {
        return new RCMBuilder().withMachineId(rs.getString("machine_id"))
                .withLocationId(rs.getString("location_id"))
                .withGroupId(rs.getString("group_id"))
                .withStatus(StatusEnum.valueOf(rs.getString("status")))
                .withCreatedOn(rs.getTimestamp("created_on"))
                .build();
    }

    public String getMachineId() {
        return machineId;
    }

    public void setMachineId(String machineId) {
        this.machineId = machineId;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public Date getcreatedOn() {
        return createdOn;
    }

    public void setcreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public static class RCMBuilder {
        private String machineId;
        private String locationId;
        private String groupId;
        private StatusEnum status;
        private Date createdOn;

        public RCMBuilder withMachineId(String machineId) {
            this.machineId = machineId;
            return this;
        }

        public RCMBuilder withLocationId(String locationId) {
            this.locationId = locationId;
            return this;
        }

        public RCMBuilder withGroupId(String groupId) {
            this.groupId = groupId;
            return this;
        }

        public RCMBuilder withStatus(StatusEnum status) {
            this.status = status;
            return this;
        }

        public RCMBuilder withCreatedOn(Date createdOn) {
            this.createdOn = createdOn;
            return this;
        }

        public RCM build() {
            return new RCM(machineId, locationId, groupId, status, createdOn);
        }
    }
}
