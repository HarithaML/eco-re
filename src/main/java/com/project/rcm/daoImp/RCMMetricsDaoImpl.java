/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.rcm.daoImp;

import com.project.common.Response;
import com.project.models.RCMMetrics;
import com.project.rcm.dao.RCMMetricsDao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;

/**
 * @author hari
 */
public class RCMMetricsDaoImpl extends DaoHelper implements RCMMetricsDao {

    public RCMMetricsDaoImpl() {
    }

    /**
     * @return
     */
    @Override
    public ArrayList<RCMMetrics> getRCMMetricsList() {
        ArrayList<RCMMetrics> rcmMetricsList = new ArrayList<>(getAllRCMMetricsTypes());
        logSuccess("Fetched all the RCM Metrics from database");
        return rcmMetricsList;

    }

    @Override
    public Response addRCMMetrics(String machineId, double capacity, double initialMoney, double currentMoney, double currentWeight) {
        String query = getInsertRCMMetricsQuery(machineId, capacity, initialMoney, currentMoney, currentWeight);
        return updateSql(query);
    }

    @Override
    public Response emptyRCM(String machineId) {
        java.util.Date now;
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("last_emptied_on = CURRENT_TIMESTAMP");
        conditions.add(String.format("currentWeight = '%f'", 0.0));
        String finalCondition = String.join(", ", conditions);
        return updateSql(getUpdateRCMMetricsSql(finalCondition, machineId));
    }

    @Override
    public Response updateRCMMetrics(RCMMetrics rcmMetrics) {
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add(String.format("last_used_on = '%s'", getDateAsString(rcmMetrics.getLastUsedOn())));
        conditions.add(String.format("current_weight = '%f'", rcmMetrics.getCurrentWeight()));
        conditions.add(String.format("current_money = '%s'", rcmMetrics.getCurrentMoney()));
        String finalCondition = String.join(", ", conditions);
        return updateSql(getUpdateRCMMetricsSql(finalCondition, rcmMetrics.getMachineId()));
    }

    //private methods
    public Collection<RCMMetrics> getAllRCMMetricsTypes() {
        Function<ResultSet, Collection<RCMMetrics>> callback = (rs) -> {
            Collection<RCMMetrics> rcmMetricsList = new ArrayList<>();
            try {
                while (rs.next()) {
                    rcmMetricsList.add(RCMMetrics.fromResultSet(rs));
                }
            } catch (Exception ex) {
                logger.severe(String.format("Failed To Get RCM metrics, %s", ex.getMessage()));
            }
            return rcmMetricsList;
        };
        //Query Helpers
        String listRCMMetricsSql = "SELECT * from rcm_metrics;";
        return runSql(listRCMMetricsSql, callback);
    }

    //private Query methods
    private String getInsertRCMMetricsQuery(String machineId, double capacity, double initialMoney, double currentMoney, double currentWeight) {
        String insertRCMMetricsSql = "Insert into rcm_metrics (machine_id, capacity, initial_money, current_money, current_weight) values ('%s', %f, %f, %f, %f);";
        String query = String.format(insertRCMMetricsSql, machineId, capacity, initialMoney, currentMoney, currentWeight);
        logger.info(query);
        return query;
    }

    private String getUpdateRCMMetricsSql(String parameters, String machineId) {
        String updateRCMMetricsSql = "UPDATE rcm_metrics SET %s WHERE machine_id = '%s';";
        String query = String.format(updateRCMMetricsSql, parameters, machineId);
        logger.info(query);
        return query;
    }

}
