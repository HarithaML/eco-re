/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.rcm.dao;

import com.project.common.Response;
import com.project.enums.ItemTypeEnum;
import com.project.models.RCMPerItemType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author hari
 */
public interface RCMPerItemTypeDao {
    ArrayList<RCMPerItemType> getRCMPerItemTypeList();

    Map<String, List<RCMPerItemType>> getGroupedRCMPerItemTypeList();

    Response addRCMPerItemType(String machineId, ItemTypeEnum itemType, double price, double weight);

    Response updateRCMItemType(ItemTypeEnum type, String machineId, double price, double weight);
}
