/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.storage;

import com.project.models.RCMMetrics;
import com.project.models.RCMPerItemType;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hari
 */
public class RCMCacheHelper {
    private static RCMCacheHelper INSTANCE;
    private static RCMCache cache;


    public RCMCacheHelper() {
        cache = new RCMCache();
        DaoFactory daoFactory = DaoFactory.getInstance();
        cache.bootstrapRCMMetrics(daoFactory.getRCMMetricsDao().getRCMMetricsList());
        cache.bootstrapRCMItemTypes(daoFactory.getRCMPerItemTypeDao().getGroupedRCMPerItemTypeList());
    }

    public void clearDataStore() {
        cache.clearData();
    }

    public Map<String, List<RCMPerItemType>> getRCMItemTypes() {
        return cache.getRcmItemTypeMap();
    }

    public LinkedHashMap<String, RCMMetrics> getRCMMetrics() {
        return cache.getRcmMetricsMap();
    }


    public void addNewItemType(String machineId, RCMPerItemType rcmPerItemType) {
        cache.addRCMItemType(machineId, rcmPerItemType);
    }

    public void updateItemType(String machineId, RCMPerItemType rcmPerItemType) {
        cache.updateRCMItemType(machineId, rcmPerItemType);
    }

    public void updateRCMMetrics(RCMMetrics rcmMetrics) {
        cache.updateRCMMetrics(rcmMetrics);
    }

}
