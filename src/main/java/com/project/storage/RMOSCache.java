/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.storage;

import com.project.common.Response;
import com.project.common.Response.ResponseBuilder;
import com.project.enums.ResponseStatus;
import com.project.models.RCMGroup;
import com.project.models.RCMMetrics;
import com.project.models.RCMPerItemType;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author hari
 */
public class RMOSCache extends Observable {

    private final LinkedHashMap<String, RCMMetrics> rcmMetricsMap;
    private final Map<String, List<RCMPerItemType>> rcmItemTypeMap;
    private final LinkedHashMap<String, RCMGroup> rcmGroupMap;

    protected RMOSCache() {
        rcmMetricsMap = new LinkedHashMap<>();
        this.rcmItemTypeMap = new LinkedHashMap<>();
        rcmGroupMap = new LinkedHashMap<>();

    }

    //private Functions
    protected void clearData() {
        rcmMetricsMap.clear();
        this.rcmItemTypeMap.clear();
        rcmGroupMap.clear();
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

    protected void bootstrapRCMGroup(List<RCMGroup> rcmGroups) {
        try {
            rcmGroups.forEach(m -> rcmGroupMap.put(m.getGroupId(), m));
        } catch (Exception ex) {
            Logger.getLogger(RMOSCache.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        }
    }

    protected LinkedHashMap<String, RCMMetrics> getRcmMetricsMap() {
        return rcmMetricsMap;
    }

    protected Map<String, List<RCMPerItemType>> getRcmItemTypeMap() {
        return rcmItemTypeMap;
    }

    protected LinkedHashMap<String, RCMGroup> getRcmGroupMap() {
        return rcmGroupMap;
    }

    protected void addRCMItemType(RCMPerItemType rcmPerItemType) {

        if (rcmItemTypeMap.containsKey(rcmPerItemType.getMachineId())) {
            List<RCMPerItemType> itemTypes = rcmItemTypeMap.get(rcmPerItemType.getMachineId());

            itemTypes.add(rcmPerItemType);

        } else {
            List<RCMPerItemType> itemTypes = new ArrayList<>();
            itemTypes.add(rcmPerItemType);
            rcmItemTypeMap.put(rcmPerItemType.getMachineId(), itemTypes);

        }
    }

    protected void addRCMMetrics(RCMMetrics rcmMerics) {
        rcmMetricsMap.put(rcmMerics.getMachineId(), rcmMerics);
    }

    protected void addRCMGroup(RCMGroup rcmGroup) {
        rcmGroupMap.put(rcmGroup.getGroupId(), rcmGroup);
    }

    protected void emptyRCM(String machineId) {
        RCMMetrics rcmMertics = rcmMetricsMap.get(machineId);
        if (rcmMertics != null) {
            rcmMertics.setLastEmptiedOn(new Date());
            rcmMertics.setCurrentWeight(0);
        }
    }

    protected Response updateRCMItemType(RCMPerItemType rcmPerItemType) {
        if (rcmItemTypeMap.containsKey(rcmPerItemType.getMachineId())) {
            List<RCMPerItemType> itemTypes = rcmItemTypeMap.get(rcmPerItemType.getMachineId());
            itemTypes.stream().filter(m -> m.getItemType().equals(rcmPerItemType.getItemType()))
                    .forEach(m -> {
                        m.setPrice(rcmPerItemType.getPrice());
                        m.setWeight(rcmPerItemType.getWeight());
                    });

            return new ResponseBuilder().withStatus(ResponseStatus.SUCCESS).withMessage("updated").build();
        } else {

            return new ResponseBuilder().withStatus(ResponseStatus.FAILURE).withMessage("Machie iD not available").build();
        }
    }

    protected void updateRCMMetrics(RCMMetrics rcmMetrics) {
        rcmMetricsMap.get(rcmMetrics.getMachineId()).setCurrentMoney(rcmMetrics.getCurrentMoney());
        rcmMetricsMap.get(rcmMetrics.getMachineId()).setCurrentWeight(rcmMetrics.getCurrentWeight());
        rcmMetricsMap.get(rcmMetrics.getMachineId()).setLastUsedOn(rcmMetrics.getLastUsedOn());
    }

}
