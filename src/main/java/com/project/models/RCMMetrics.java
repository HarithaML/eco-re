/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.models;

import java.sql.ResultSet;
import java.util.Date;

/**
 * @author hari
 */
public class RCMMetrics {
    private String machineId;
    private double capacity;
    private double initialMoney;
    private double currentMoney;
    private Date lastEmptiedOn;
    private Date lastUsedOn;
    private double currentWeight;

    private RCMMetrics(String machineId, double capacity, double initialMoney, Date lastEmptiedOn, double currentMoney, Date lastUsedOn, double currentWeight) {
        this.machineId = machineId;
        this.capacity = capacity;
        this.initialMoney = initialMoney;
        this.lastEmptiedOn = lastEmptiedOn;
        this.currentMoney = currentMoney;
        this.lastUsedOn = lastUsedOn;
        this.currentWeight = currentWeight;
    }

    public static RCMMetrics fromResultSet(ResultSet rs) throws Exception {
        return new RCMMetricsBuilder().withMachineId(rs.getString("machine_id"))
                .withCapacity(rs.getDouble("capacity"))
                .withInitialMoney(rs.getDouble("initial_money"))
                .withLastEmptiedOn(rs.getTimestamp("last_emptied_on"))
                .withCurrentMoney(rs.getDouble("current_money"))
                .withLastUsedOn(rs.getTimestamp("last_used_on"))
                .withCurrentWeight(rs.getDouble("current_weight"))
                .build();
    }

    public double getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(double currentWeight) {
        this.currentWeight = currentWeight;
    }

    public Date getLastUsedOn() {
        return lastUsedOn;
    }

    public void setLastUsedOn(Date lastUsedOn) {
        this.lastUsedOn = lastUsedOn;
    }

    public double getCurrentMoney() {
        return currentMoney;
    }

    public void setCurrentMoney(double currentMoney) {
        this.currentMoney = currentMoney;
    }

    public String getMachineId() {
        return machineId;
    }

    public void setMachineId(String machineId) {
        this.machineId = machineId;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public double getInitialMoney() {
        return initialMoney;
    }

    public void setInitialMoney(double initialMoney) {
        this.initialMoney = initialMoney;
    }

    public Date getLastEmptiedOn() {
        return lastEmptiedOn;
    }

    public void setLastEmptiedOn(Date emptyCurrTime) {
        this.lastEmptiedOn = emptyCurrTime;
    }

    public static class RCMMetricsBuilder {
        private String machineId;
        private double capacity;
        private double initialMoney;
        private Date lastEmptiedOn;
        private double currentMoney;
        private Date lastUsedOn;
        private double currentWeight;

        public RCMMetricsBuilder withMachineId(String machineId) {
            this.machineId = machineId;
            return this;
        }

        public RCMMetricsBuilder withCapacity(double capacity) {
            this.capacity = capacity;
            return this;
        }

        public RCMMetricsBuilder withInitialMoney(double initialMoney) {
            this.initialMoney = initialMoney;
            return this;
        }

        public RCMMetricsBuilder withLastEmptiedOn(Date lastEmptiedOn) {
            this.lastEmptiedOn = lastEmptiedOn;
            return this;
        }

        public RCMMetricsBuilder withCurrentMoney(double currentMoney) {
            this.currentMoney = currentMoney;
            return this;
        }

        public RCMMetricsBuilder withLastUsedOn(Date lastUsedOn) {
            this.lastUsedOn = lastUsedOn;
            return this;
        }

        public RCMMetricsBuilder withCurrentWeight(double currentWeight) {
            this.currentWeight = currentWeight;
            return this;
        }

        public RCMMetrics build() {
            return new RCMMetrics(machineId, capacity, initialMoney, lastEmptiedOn, currentMoney, lastUsedOn, currentWeight);
        }


    }
}
