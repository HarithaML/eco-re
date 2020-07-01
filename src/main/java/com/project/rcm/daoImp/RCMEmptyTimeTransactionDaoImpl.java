/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.rcm.daoImp;

import com.project.common.Response;
import com.project.rcm.dao.RCMEmptyTimeTransactionDao;

import java.sql.ResultSet;
import java.util.Date;
import java.util.function.Function;

/**
 * @author hari
 */
public class RCMEmptyTimeTransactionDaoImpl extends DaoHelper implements RCMEmptyTimeTransactionDao {

    public RCMEmptyTimeTransactionDaoImpl() {
    }

    @Override
    public int getNumberOfEmptyTimes(String machineId, Date startTime, Date endTime) {
        Function<ResultSet, Integer> callback = (rs) -> {
            int count = 0;
            try {
                if (rs.next()) {
                    count = rs.getInt("cnt");
                }
            } catch (Exception ex) {
                logger.severe(String.format("Failed To Get RecyclableItemTransactions, %s", ex.getMessage()));
            }
            return count;
        };
        return runSql(getInsertRCMQuery(machineId, startTime, endTime), callback);
    }

    @Override
    public Response addEmptyTime(String machineId, String groupId) {
        String query = insertEmptyTime(machineId, groupId);
        return updateSql(query);
    }

    //private Query Helpers
    private String getInsertRCMQuery(String machineId, Date startDate, Date endDate) {
        //Query helpers
        String numberofEmptyTimes = "SELECT COUNT(*) AS cnt from rcm_empty_transactions WHERE machine_id ='%s' AND timestamp >= '%s' AND timestamp <= '%s' ;";
        String query = String.format(numberofEmptyTimes, machineId, getDateAsString(startDate), getDateAsString(endDate));
        logger.info(query);
        return query;
    }

    private String insertEmptyTime(String machineId, String groupId) {
        String insertEmptyTime = "INSERT INTO rcm_empty_transactions (machine_id,group_id) values ('%s','%s');";
        String query = String.format(insertEmptyTime, machineId, groupId);
        logger.info(query);
        return query;
    }

}
