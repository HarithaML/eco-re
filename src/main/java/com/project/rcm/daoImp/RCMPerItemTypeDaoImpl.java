/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.rcm.daoImp;

import com.project.common.Response;
import com.project.enums.ItemTypeEnum;
import com.project.models.RCMPerItemType;
import com.project.rcm.dao.RCMPerItemTypeDao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.groupingBy;

/**
 * @author hari
 */
public class RCMPerItemTypeDaoImpl extends DaoHelper implements RCMPerItemTypeDao {

    public RCMPerItemTypeDaoImpl() {
    }

    /**
     * @return
     */
    @Override
    public ArrayList<RCMPerItemType> getRCMPerItemTypeList() {
        ArrayList<RCMPerItemType> rcmPerItemTypeList = new ArrayList<>(getAllRCMPerItemTypeTypes());
        logSuccess("Fetched all the RCM per item type from database");
        return rcmPerItemTypeList;

    }

    @Override
    public Map<String, List<RCMPerItemType>> getGroupedRCMPerItemTypeList() {
        Collection<RCMPerItemType> rcmPerItemTypes = getAllRCMPerItemTypeTypes();
        return rcmPerItemTypes.stream().collect(groupingBy(RCMPerItemType::getMachineId));
    }

    @Override
    public Response addRCMPerItemType(String machineId, ItemTypeEnum itemType, double price, double weight) {
        String query = getInsertRCMPerItemTypeQuery(machineId, itemType, price, weight);
        return updateSql(query);
    }

    @Override
    public Response updateRCMItemType(ItemTypeEnum type, String machineId, double price, double weight) {
        String params1 = String.format("price =  %f ", price);
        String params2 = String.format("weight = %f ", weight);
        String finalCondition = String.join(",", params1, params2);
        return updateSql(getUpdateRCMPerItemTypeSql(finalCondition, machineId, type.toString()));
    }

    //private methods
    public Collection<RCMPerItemType> getAllRCMPerItemTypeTypes() {
        Function<ResultSet, Collection<RCMPerItemType>> callback = (rs) -> {
            Collection<RCMPerItemType> rcmPerItemTypeList = new ArrayList<>();
            try {
                while (rs.next()) {
                    rcmPerItemTypeList.add(RCMPerItemType.fromResultSet(rs));
                }
            } catch (Exception ex) {
                logger.severe(String.format("Failed To Get RCM per item type, %s", ex.getMessage()));
            }
            return rcmPerItemTypeList;
        };
        //Query Helpers
        String listRCMPerItemTypeSql = "SELECT * from rcm_item_types;";
        return runSql(listRCMPerItemTypeSql, callback);
    }

    //private Query methods
    private String getInsertRCMPerItemTypeQuery(String machineId, ItemTypeEnum itemType, double price, double weight) {
        String insertRCMPerItemTypeSql = "Insert into rcm_item_types (machine_id, item_type,price,weight) values ('%s','%s',%f,%f);";
        String query = String.format(insertRCMPerItemTypeSql, machineId, itemType, price, weight);
        logger.info(query);
        return query;
    }

    private String getUpdateRCMPerItemTypeSql(String parameters, String machineId, String itemType) {
        String updateRCMPerItemTypeSql2 = "UPDATE rcm_item_types SET %s WHERE machine_id = '%s' AND item_type = '%s'";
        String query = String.format(updateRCMPerItemTypeSql2, parameters, machineId, itemType);
        logger.info(query);
        return query;
    }

}
