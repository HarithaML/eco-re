/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.storage;

import com.project.models.RCMGroup;
import com.project.models.RCMMetrics;
import com.project.models.RCMPerItemType;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hari
 */
public class RMOSCacheHelper {

    private static RMOSCacheHelper INSTANCE;
    private static RMOSCache cache;

    private RMOSCacheHelper() {
    }

    public static RMOSCacheHelper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RMOSCacheHelper();
            cache = new RMOSCache();
            DaoFactory daoFactory = DaoFactory.getInstance();
            cache.bootstrapRCMMetrics(daoFactory.getRCMMetricsDao().getRCMMetricsList());
            cache.bootstrapRCMItemTypes(daoFactory.getRCMPerItemTypeDao().getGroupedRCMPerItemTypeList());
            cache.bootstrapRCMGroup(daoFactory.getRCMGroupDao().getGroupList());

        }
        return INSTANCE;
    }


    public void addRCMMetrics(RCMMetrics rcmMetrics) {
        cache.addRCMMetrics(rcmMetrics);
    }

    public void addRCMGroup(RCMGroup rcmGroup) {
        cache.addRCMGroup(rcmGroup);
    }

    public LinkedHashMap<String, RCMGroup> getRCMGroups() {
        return cache.getRcmGroupMap();
    }

    public LinkedHashMap<String, RCMMetrics> getRCMMetrics() {
        return cache.getRcmMetricsMap();
    }

    public Map<String, List<RCMPerItemType>> getRCMItemTypes() {
        return cache.getRcmItemTypeMap();
    }

    public void emptyRCM(String machineId) {
        cache.emptyRCM(machineId);
    }

    public void addRCMItemType(RCMPerItemType rcmPerItemType) {

        cache.addRCMItemType(rcmPerItemType);
    }

    public void updateRCMItemType(RCMPerItemType r) {
        cache.updateRCMItemType(r);
    }

    public void updateRCMMetrics(RCMMetrics rcmMetrics) {
        cache.updateRCMMetrics(rcmMetrics);
    }

}
