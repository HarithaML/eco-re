/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.storage;

import com.project.models.RCMMetrics;
import com.project.models.RCMPerItemType;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author hari
 */
public class RCMCache {

    private final LinkedHashMap<String, RCMMetrics> rcmMetricsMap;
    private final Map<String, List<RCMPerItemType>> rcmItemTypeMap;
    private RMOSCache rmosCache = new RMOSCache();

    protected RCMCache() {
        rcmMetricsMap = new LinkedHashMap<>();
        rcmItemTypeMap = new LinkedHashMap<>();
    }

    //public void update(Map<String, List<RCMPerItemType>> rcmItemTypeMap){
    //this.rcmItemTypeMap = rcmItemTypeMap;
    //}
    //private Functions
    protected void clearData() {
        rcmMetricsMap.clear();
        this.rcmItemTypeMap.clear();
    }

    protected void bootstrapRCMMetrics(List<RCMMetrics> metrics) {
        metrics.forEach(m -> rcmMetricsMap.put(m.getMachineId(), m));
    }

    protected void bootstrapRCMItemTypes(Map<String, List<RCMPerItemType>> rcmPerItemTypes) {
        try {
            rcmPerItemTypes.keySet().forEach((key) -> this.rcmItemTypeMap.put(key, rcmPerItemTypes.get(key)));
        } catch (Exception ex) {
            Logger.getLogger(RMOSCache.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        }

    }

    protected LinkedHashMap<String, RCMMetrics> getRcmMetricsMap() {

        return rcmMetricsMap;
    }

    public Map<String, List<RCMPerItemType>> getRcmItemTypeMap() {
        return rcmItemTypeMap;
    }

    public void addRCMItemType(String machineId, RCMPerItemType rcmPerItemType) {
        if (rcmItemTypeMap.get(machineId) == null) {
            List<RCMPerItemType> types = new ArrayList<>();
            types.add(rcmPerItemType);
            rcmItemTypeMap.put(machineId, types);
        } else {
            rcmItemTypeMap.get(machineId).add(rcmPerItemType);
        }

    }

    public void updateRCMItemType(String machineId, RCMPerItemType rcmPerItemType) {
        rcmItemTypeMap.get(machineId).forEach(m -> {
            if (m.getItemType().equals(rcmPerItemType.getItemType())) {
                m.setPrice(rcmPerItemType.getPrice());
                m.setWeight(rcmPerItemType.getWeight());
            }
        });
    }

    public void updateRCMMetrics(RCMMetrics rcmMetrics) {
        rcmMetricsMap.get(rcmMetrics.getMachineId()).setCurrentMoney(rcmMetrics.getCurrentMoney());
        rcmMetricsMap.get(rcmMetrics.getMachineId()).setCurrentWeight(rcmMetrics.getCurrentWeight());
        rcmMetricsMap.get(rcmMetrics.getMachineId()).setLastUsedOn(rcmMetrics.getLastUsedOn());
    }


}
