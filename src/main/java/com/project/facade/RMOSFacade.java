/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.facade;

import com.project.common.RCMComposite;
import com.project.common.RCMOperationalStatus;
import com.project.common.Response;
import com.project.common.Response.ResponseBuilder;
import com.project.enums.ItemTypeEnum;
import com.project.enums.ResponseStatus;
import com.project.enums.StatusEnum;
import com.project.models.RCM;
import com.project.models.RCM.RCMBuilder;
import com.project.models.RCMGroup;
import com.project.models.RCMGroup.RCMGroupBuilder;
import com.project.models.RCMMetrics;
import com.project.models.RCMMetrics.RCMMetricsBuilder;
import com.project.models.RCMPerItemType;
import com.project.models.RCMPerItemType.RCMPerItemTypeBuilder;
import com.project.observers.NotificationObject;
import com.project.observers.RMOSObserverable;
import com.project.storage.DaoFactory;
import com.project.storage.RCMCompositeFactory;
import com.project.storage.RMOSCacheHelper;

import java.util.*;
import java.util.logging.Logger;

/**
 * @author hari
 */
public class RMOSFacade {

    private static final Logger logger = Logger.getLogger(RMOSFacade.class.getName());
    private final RMOSCacheHelper cacheHelper;
    private final DaoFactory dao;
    private final RCMCompositeFactory rcmCompositeFactory;
    private final RMOSObserverable rmosObserverable;

    public RMOSFacade() {
        this.cacheHelper = RMOSCacheHelper.getInstance();
        this.dao = DaoFactory.getInstance();
        this.rcmCompositeFactory = RCMCompositeFactory.getInstance();
        this.rmosObserverable = RMOSObserverable.getInstance();
    }

    public Response addRCM(String machineId, String locationId, String groupId, StatusEnum status, Date createdOn) {
        RCM rcm = new RCMBuilder().withMachineId(machineId)
                .withLocationId(locationId)
                .withGroupId(groupId)
                .withStatus(status)
                .withCreatedOn(createdOn)
                .build();
        this.rcmCompositeFactory.update(groupId, rcm);
        return this.dao.getRCMDao().addRCM(machineId, locationId, groupId, status, createdOn);
    }

    public Response addRCMMetrics(String machineId, double capacity, double initialMoney, Date lastEmptiedOn, double currentMoney, Date lastUsedOn, double currentWeight) {
        RCMMetrics rcmMetrics = new RCMMetricsBuilder().withMachineId(machineId)
                .withCapacity(capacity)
                .withCurrentMoney(initialMoney)
                .withLastEmptiedOn(lastEmptiedOn)
                .withCurrentWeight(currentMoney)
                .withLastUsedOn(lastUsedOn)
                .withCurrentWeight(currentWeight)
                .build();

        this.cacheHelper.addRCMMetrics(rcmMetrics);
        return this.dao.getRCMMetricsDao().addRCMMetrics(machineId, capacity, initialMoney, currentMoney, currentWeight);

    }

    public Response addRCMGroup(String groupId, String groupName, String groupDescription) {
        RCMGroup rcmGroup = new RCMGroupBuilder().withGroupId(groupId)
                .withGroupName(groupName)
                .withGroupDescription(groupDescription)
                .build();
        this.rcmCompositeFactory.addRCMGroup(rcmGroup);
        this.cacheHelper.addRCMGroup(rcmGroup);
        Response rs = this.dao.getRCMGroupDao().addRCMGroup(groupId, groupName, groupDescription);
        logResponse(rs, "addRCMGroup");
        return rs;
    }

    public Response addItemType(String machineId, ItemTypeEnum type, double price, double weight) {
        List<RCMPerItemType> types = this.cacheHelper.getRCMItemTypes().get(machineId);
        if (types == null) {
            RCMPerItemType itemTypeObj = new RCMPerItemTypeBuilder()
                    .withItemType(type)
                    .withPrice(price)
                    .withWeight(weight)
                    .withMachineId(machineId)
                    .build();
            this.cacheHelper.addRCMItemType(itemTypeObj);
            Response rs = this.dao.getRCMPerItemTypeDao().addRCMPerItemType(machineId, type, price, weight);
            if (rs.getStatus() == ResponseStatus.SUCCESS) {
                NotificationObject notificationObject = new NotificationObject(machineId, "AddItemType", itemTypeObj);
                rmosObserverable.notifyAllObservers(notificationObject);
            }
            logResponse(rs, "addItemType");
            return rs;
        } else {
            Optional<RCMPerItemType> itOpt = types
                    .stream()
                    .filter(m -> m.getItemType().equals(type))
                    .findFirst();
            if (itOpt.isPresent()) {
                Response rs = new ResponseBuilder().withStatus(ResponseStatus.FAILURE).withMessage("Item Already Exists").build();
                logResponse(rs, "ItemType Already Exists");
                return rs;
            } else {
                RCMPerItemType itemTypeObj = new RCMPerItemTypeBuilder()
                        .withItemType(type)
                        .withPrice(price)
                        .withWeight(weight)
                        .withMachineId(machineId)
                        .build();
                this.cacheHelper.addRCMItemType(itemTypeObj);
                Response rs = this.dao.getRCMPerItemTypeDao().addRCMPerItemType(machineId, type, price, weight);
                if (rs.getStatus() == ResponseStatus.SUCCESS) {
                    NotificationObject notificationObject = new NotificationObject(machineId, "AddItemType", itemTypeObj);
                    rmosObserverable.notifyAllObservers(notificationObject);
                }
                logResponse(rs, "addItemType");
                return rs;
            }
        }

    }

    public List<String> getRCMMachineIdList(String groupId) {
        List<String> l = new ArrayList<>();
        try {

            RCMComposite rcmComposite = this.rcmCompositeFactory.getByGroupId(groupId);
            rcmComposite.getRCMComponentList().forEach(m -> l.add(m.getMachineId()));
            return l;
        } catch (Exception ex) {
            logger.severe(ex.getMessage());
            return l;
        }
    }

    public List<RCMGroup> getRCMGroupList() {
        return new ArrayList<>(this.cacheHelper.getRCMGroups().values());
    }

    public List<RCM> getRCMList(String groupId) {
        try {
            RCMComposite rcmComposite = this.rcmCompositeFactory.getByGroupId(groupId);
            return rcmComposite.getRCMComponentList();
        } catch (Exception ex) {
            logger.severe(ex.getMessage());
            return null;
        }

    }

    public List<RCMMetrics> getRCMMetrics() {
        return new ArrayList<>(this.cacheHelper.getRCMMetrics().values());
    }

    public List<RCMOperationalStatus> getRCMOperationalStatus(String groupId) {
        List<RCMOperationalStatus> l = new ArrayList<>();

        LinkedHashMap<String, RCMMetrics> rcmMetricsByMachineId = this.cacheHelper.getRCMMetrics();
        RCMComposite r = this.rcmCompositeFactory.getByGroupId(groupId);
        r.getRCMComponentList()
                .forEach(m -> {
                    RCMMetrics rm = rcmMetricsByMachineId.get(m.getMachineId());
                    l.add(new RCMOperationalStatus(m, rm));
                });
        return l;
    }

    public Integer getRecyclableItemTransaction(String month, String year, String machineId) {
        return this.dao.getRecyclableItemTransactionDao().getAllRecyclableItemsTypesByMonthAndTime(month, year, machineId);
    }

    public String getMostUsedRCMInNDays(String groupId, int numberOfDays) {
        return this.dao.getRecyclableItemTransactionDao().getMostFrequentlyUsedRCM(groupId, numberOfDays);
    }

    public Response updateStatus(RCM rcm) {
        Response rs;
        if (rcm.getStatus() == StatusEnum.DELETED) {
            System.out.println("reached");
            this.rcmCompositeFactory.deleteRCM(rcm);
            rs = this.dao.getRCMDao().deleteRCM(rcm);
        } else {
            this.rcmCompositeFactory.updateRCMStatus(rcm);
            rs = this.dao.getRCMDao().updateStatus(rcm.getMachineId(), rcm.getStatus());
        }

        logResponse(rs, "updateStatus");
        return rs;
    }

    public Response emptyRCM(String machineId) {
        this.cacheHelper.emptyRCM(machineId);
        Response rs = this.dao.getRCMMetricsDao().emptyRCM(machineId);
        logResponse(rs, "emptyRCM");
        return rs;
    }

    public Map<String, List<RCMPerItemType>> getRCMItemTypes() {
        return this.cacheHelper.getRCMItemTypes();
    }

    public Response updateItemTypes(ItemTypeEnum itemType, String machineId, double price, double weight) {
        RCMPerItemType itemTypeObj = new RCMPerItemTypeBuilder().withItemType(itemType)
                .withPrice(price)
                .withWeight(weight)
                .withMachineId(machineId)
                .build();

        this.cacheHelper.updateRCMItemType(itemTypeObj);

        Response rs = this.dao.getRCMPerItemTypeDao().updateRCMItemType(itemType, machineId, price, weight);
        if (rs.getStatus().equals(ResponseStatus.SUCCESS)) {
            NotificationObject notificationObject = new NotificationObject(machineId, "UpdateItemType", itemTypeObj);
            rmosObserverable.notifyAllObservers(notificationObject);
        }
        logResponse(rs, "updateItemTypes");
        return rs;
    }

    public Map<Date, Integer> getWeightStatisticsPerDay(String machineId) {
        return this.dao.getRecyclableItemTransactionDao().getTotalWeightPerDay(machineId);
    }

    public Map<String, Integer> getWeightStatisticsPerWeek(String machineId) {
        return this.dao.getRecyclableItemTransactionDao().getTotalWeightPerWeek(machineId);
    }

    public Map<String, Integer> getTotalValueIssued(String machineId) {
        return this.dao.getRecyclableItemTransactionDao().getTotalValueIssued(machineId);
    }

    public int getNumberOfEmptyTimes(String machineId, Date startTime, Date endTime) {
        return this.dao.getRCMEmptyTimeTransactionDao().getNumberOfEmptyTimes(machineId, startTime, endTime);
    }

    public Response addEmptyTime(String machineId, String groupId) {
        Response rs = this.dao.getRCMEmptyTimeTransactionDao().addEmptyTime(machineId, groupId);
        logResponse(rs, "addEmptyTime");
        return rs;
    }

    public List<RCMPerItemType> getRCMItemType(String machineId) {
        return this.cacheHelper.getRCMItemTypes().get(machineId);
    }

    private void logResponse(Response rs, String prefix) {
        if (rs.getStatus().equals((ResponseStatus.SUCCESS))) {
            logger.info(String.format("[%s] %s", prefix, "Successfully Exeuted!"));
        } else {
            logger.severe(String.format("[%s] %s", prefix, rs.getMessage()));
        }
    }

    public void updateRCMMetrics(RCMMetrics rcmMetrics) {
        this.cacheHelper.updateRCMMetrics(rcmMetrics);
    }
}
