/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.facade;

import com.project.models.RCMMetrics;
import com.project.models.RCMPerItemType;
import com.project.models.RecyclableItemTransaction;
import com.project.storage.DaoFactory;
import com.project.storage.RCMCacheHelper;

import java.util.List;

/**
 * @author hari
 */
public class RCMFacade {

    private final RCMCacheHelper cacheHelper;
    private final DaoFactory dao;

    public RCMFacade() {
        this.cacheHelper = new RCMCacheHelper();
        this.dao = DaoFactory.getInstance();
    }

    public List<RCMPerItemType> getRCMPerItemTypes(String machineId) {
        return this.cacheHelper.getRCMItemTypes().get(machineId);
    }

    public void handleAddRCMTypeNotification(String machineId, RCMPerItemType rcmPerItemType) {
        this.cacheHelper.addNewItemType(machineId, rcmPerItemType);
    }

    public void handleUpdateRCMTypeNotification(String machineId, RCMPerItemType rcmPerItemType) {
        this.cacheHelper.updateItemType(machineId, rcmPerItemType);
    }

    public RCMMetrics getRCMMetrics(String machineId) {
        return this.cacheHelper.getRCMMetrics().get(machineId);
    }

    public void updateTransactions(RCMMetrics rcmMetrics, List<RecyclableItemTransaction> rcmTransactions) {

        this.cacheHelper.updateRCMMetrics(rcmMetrics);

        this.dao.getRCMMetricsDao().updateRCMMetrics(rcmMetrics);

        rcmTransactions.forEach(s -> {
            this.dao.getRecyclableItemTransactionDao().insertRecyclableItemTransaction(s);
            this.dao.getRCMDao().getRCMList().forEach(m -> {
                if (m.getMachineId().equals(s.getMachineId())) {
                    s.setGroupId(m.getGroupId());
                }
            });
        });
    }


}
