/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.rcm.dao;

import com.project.common.Response;
import com.project.models.RecyclableItemTransaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 * @author hari
 */
public interface RecyclableItemTransactionDao {
    ArrayList<RecyclableItemTransaction> getRecyclableItemTransactionList();

    Integer getAllRecyclableItemsTypesByMonthAndTime(String month, String year, String machineId);

    String getMostFrequentlyUsedRCM(String groupId, int numberOfDays);

    Map<Date, Integer> getTotalWeightPerDay(String machineId);

    Map<String, Integer> getTotalWeightPerWeek(String machineId);

    Map<String, Integer> getTotalValueIssued(String machineId);

    Response insertRecyclableItemTransaction(RecyclableItemTransaction recyclableItemTransaction);
}
