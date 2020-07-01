/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.facade;

import com.project.common.RCMOperationalStatus;
import com.project.common.Response;
import com.project.enums.ItemTypeEnum;
import com.project.enums.StatusEnum;
import com.project.models.RCM;
import com.project.models.RCMGroup;
import com.project.models.RCMMetrics;
import com.project.models.RCMPerItemType;
import com.project.models.RCMPerItemType.RCMPerItemTypeBuilder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author hari
 */
public class RMOS {

    private final RMOSFacade rmosFacade;
    private String userName = "admin";
    private String passWord = "admin";


    public RMOS() {
        rmosFacade = new RMOSFacade();
    }


    //Add RCM
    public Response addRCM(String machineId, String locationId, String groupId, StatusEnum status, Date createdOn) {
        return rmosFacade.addRCM(machineId, locationId, groupId, status, createdOn);
    }


    public Response addRCMMetrics(String machineId, double capacity, double initialMoney, Date lastEmptiedOn, double currentMoney, Date lastUsedOn, double currentWeight) {
        return rmosFacade.addRCMMetrics(machineId, capacity, initialMoney, lastEmptiedOn, currentMoney, lastUsedOn, currentWeight);
    }

    //Add RCM Group
    public Response addRCMGroup(String groupId, String groupName, String groupDescription) {
        return rmosFacade.addRCMGroup(groupId, groupName, groupDescription);
    }


    //get Group List
    public List<RCMGroup> getRCMGroupList() {
        return rmosFacade.getRCMGroupList();
    }

    //get machine Id for a group id
    public List<String> getRCMMachineIdList(String groupId) {
        return rmosFacade.getRCMMachineIdList(groupId);
    }

    //get recycle machines
    public List<RCM> getRCMList(String groupId) {
        return rmosFacade.getRCMList(groupId);
    }

    //get RCMMetrics
    public List<RCMMetrics> getRCMMetrics() {
        return rmosFacade.getRCMMetrics();
    }

    //get operational status details
    public List<RCMOperationalStatus> getRCMOperationalStatus(String groupId) {
        return rmosFacade.getRCMOperationalStatus(groupId);
    }

    //get number of recycled items
    public Integer getNumberOfItems(String month, String year, String machineId) {
        return rmosFacade.getRecyclableItemTransaction(month, year, machineId);
    }

    //change status of RCM
    public Response updateStatusRCM(RCM rcm) {
        return rmosFacade.updateStatus(rcm);
    }

    //empty RCM 
    public Response emptyRCM(String machineId) {
        return rmosFacade.emptyRCM(machineId);
    }

    //get mostfrequently used RCM in N days
    public String getMostFrequentlyUsedRCMInNdays(String groupId, int numberOfDays) {
        return rmosFacade.getMostUsedRCMInNDays(groupId, numberOfDays);
    }

    //add RCM item type
    public Response addItemType(String machineId, ItemTypeEnum type, double price, double weight) {
        RCMPerItemType rcm = new RCMPerItemTypeBuilder().withMachineId(machineId)
                .withItemType(type)
                .withPrice(price)
                .withWeight(weight)
                .build();
        List<RCMPerItemType> rcmList = new ArrayList<>();

        return rmosFacade.addItemType(machineId, type, price, weight);
    }


    public Map<String, List<RCMPerItemType>> getRCMPerItemType() {
        return rmosFacade.getRCMItemTypes();
    }

    //change RCM item type

    public void updateItemType(ItemTypeEnum itemType, String machineId, double price, double weight) {
        rmosFacade.updateItemTypes(itemType, machineId, price, weight);
    }
    //get weight statistics

    public Map<Date, Integer> getWeightStatisticsPerDay(String machineId) {
        return rmosFacade.getWeightStatisticsPerDay(machineId);
    }

    public Map<String, Integer> getWeightStatisticsPerWeek(String machineId) {
        return rmosFacade.getWeightStatisticsPerWeek(machineId);
    }

    // get value reward type

    public Map<String, Integer> getTotalValueIssued(String machineId) {
        return rmosFacade.getTotalValueIssued(machineId);
    }


    // get number of empty times

    public int getNumberOfEmptyTime(String machineId, Date startTime, Date endTime) {
        return rmosFacade.getNumberOfEmptyTimes(machineId, startTime, endTime);
    }

    //add empty time

    public Response addEmptyTime(String machineId, String groupId) {
        return rmosFacade.addEmptyTime(machineId, groupId);
    }


    //get itemType List for a machineId

    public List<RCMPerItemType> getItemTypes(String machineId) {
        return rmosFacade.getRCMItemType(machineId);
    }

    public void updateRCMMetrics(RCMMetrics rcmMetrics) {
        this.rmosFacade.updateRCMMetrics(rcmMetrics);
    }

}
