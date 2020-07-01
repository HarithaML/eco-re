package com.project.rcm.daoImp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.project.common.Response;
import com.project.enums.ItemTypeEnum;
import com.project.enums.RewardsEnum;
import com.project.models.RecyclableItemTransaction;
import com.project.rcm.dao.RecyclableItemTransactionDao;

import java.sql.ResultSet;
import java.util.*;
import java.util.function.Function;

/**
 * @author hari
 */
public class RecyclableItemTransactionDaoImpl extends DaoHelper implements RecyclableItemTransactionDao {


    public RecyclableItemTransactionDaoImpl() {
    }

    @Override
    public ArrayList<RecyclableItemTransaction> getRecyclableItemTransactionList() {
        ArrayList<RecyclableItemTransaction> RecyclableItems = new ArrayList<>(getAllRecyclableItemsTypes());
        logSuccess("Fetched all the RecyclableItemTransactions from database");
        return RecyclableItems;
    }

    @Override
    public Integer getAllRecyclableItemsTypesByMonthAndTime(String month, String year, String machineId) {
        Function<ResultSet, Integer> callback = (rs) -> {
            int count;
            try {
                if (rs.next()) {
                    count = rs.getInt("cnt");
                } else {
                    count = 0;
                }
            } catch (Exception ex) {
                logger.severe(String.format("Failed To Get RecyclableItemTransactions, %s", ex.getMessage()));
                count = 0;
            }
            return count;
        };
        return runSql(GetByMonthAndYearSql(month, year, machineId), callback);
    }

    @Override
    public String getMostFrequentlyUsedRCM(String groupId, int numberOfDays) {

        Function<ResultSet, String> callback = (rs) -> {
            String machineId = null;

            try {
                if (rs.next()) {
                    machineId = rs.getString("machine_id");

                } else {
                }
            } catch (Exception ex) {
                logger.severe(String.format("Failed To Get RecyclableItemTransactions, %s", ex.getMessage()));
            }
            return machineId;
        };
        return runSql(GetNoOfTimesUsed(groupId, numberOfDays), callback);
    }

    @Override
    public Map<Date, Integer> getTotalWeightPerDay(String machineId) {
        Function<ResultSet, Map<Date, Integer>> callback = (rs) -> {
            Map<Date, Integer> totalWeightMap = new LinkedHashMap<>();
            try {
                while (rs.next()) {
                    totalWeightMap.put(rs.getDate("day"), rs.getInt("total_weight"));
                }
            } catch (Exception ex) {
                logger.severe(String.format("Failed To Get RecyclableItemTransactions, %s", ex.getMessage()));

            }
            return totalWeightMap;
        };
        return runSql(getTotalWeightPerDaySql(machineId), callback);
    }

    @Override
    public Map<String, Integer> getTotalWeightPerWeek(String machineId) {
        Function<ResultSet, Map<String, Integer>> callback = (rs) -> {
            Map<String, Integer> totalWeightMap = new LinkedHashMap<>();
            try {
                while (rs.next()) {
                    totalWeightMap.put(rs.getString("week"), rs.getInt("total_weight"));
                }
            } catch (Exception ex) {
                logger.severe(String.format("Failed To Get RecyclableItemTransactions, %s", ex.getMessage()));

            }
            return totalWeightMap;
        };
        return runSql(getTotalWeightPerWeekSql(machineId), callback);
    }

    @Override
    public Map<String, Integer> getTotalValueIssued(String machineId) {
        Function<ResultSet, Map<String, Integer>> callback = (rs) -> {
            Map<String, Integer> totalValueMap = new LinkedHashMap<>();
            try {
                while (rs.next()) {
                    totalValueMap.put(rs.getString("reward_type"), rs.getInt("value"));
                }
            } catch (Exception ex) {
                logger.severe(String.format("Failed To Get RecyclableItemTransactions, %s", ex.getMessage()));

            }
            return totalValueMap;
        };
        return runSql(getTotalValueIssuedSql(machineId), callback);
    }

    @Override
    public Response insertRecyclableItemTransaction(RecyclableItemTransaction recyclableItemTransaction) {
        String query = getInsertRecyclableItemTransactionQuery(
                recyclableItemTransaction.getMachineId(),
                recyclableItemTransaction.getGroupId(),
                recyclableItemTransaction.getItemType(),
                recyclableItemTransaction.getItemWieght(),
                recyclableItemTransaction.getItemPrice(),
                recyclableItemTransaction.getTimestamp(),
                recyclableItemTransaction.getRewardType()

        );
        return updateSql(query);
    }

    //private methods
    public Collection<RecyclableItemTransaction> getAllRecyclableItemsTypes() {
        Function<ResultSet, Collection<RecyclableItemTransaction>> callback = (rs) -> {
            Collection<RecyclableItemTransaction> RecyclableItems = new ArrayList<>();
            try {
                while (rs.next()) {
                    RecyclableItems.add(RecyclableItemTransaction.fromResultSet(rs));
                }
            } catch (Exception ex) {
                logger.severe(String.format("Failed To Get RecyclableItemTransactions, %s", ex.getMessage()));
            }
            return RecyclableItems;
        };
        //Query Helpers
        String listRecyclableItemTransactionSql = "SELECT * from recyclable_item_transaction;";
        return runSql(listRecyclableItemTransactionSql, callback);
    }

    //private Query methods
    private String getInsertRecyclableItemTransactionQuery(String machineId, String groupId, ItemTypeEnum itemType, double itemWieght, double itemPrice, Date timestamp, RewardsEnum rewardType) {
        String insertRecyclableItemTransactionSql = "Insert into recyclable_item_transaction (machine_id,group_id,item_type, item_weight, item_price, time_stamp, reward_type) values ('%s','%s','%s',%f,%f,'%s', '%s');";
        String query = String.format(insertRecyclableItemTransactionSql, machineId, groupId, itemType, itemWieght, itemPrice, getDateAsString(timestamp), rewardType);
        logger.info(query);
        return query;
    }

    private String getUpdateRecyclableItemTransactionSql(String parameters, ItemTypeEnum itemType) {
        String updateRecyclableItemTransactionSql = "UPDATE recyclable_item_transaction SET %s WHERE item_type = '%s';";
        String query = String.format(updateRecyclableItemTransactionSql, parameters, itemType);
        logger.info(query);
        return query;
    }

    private String GetByMonthAndYearSql(String month, String year, String machineId) {
        String byMonthAndYearSql = "SELECT COUNT(*) AS cnt FROM recyclable_item_transaction WHERE MONTH(time_stamp) = %d AND YEAR(time_stamp) = %d AND machine_id = '%s';";
        String query = String.format(byMonthAndYearSql, ConvertStringToInt(month), Integer.parseInt(year), machineId);
        logger.info(query);
        return query;
    }

    private int ConvertStringToInt(String month) {
        if (month == null) {
            return 0;
        } else switch (month) {
            case "January":
                return 1;
            case "February":
                return 2;
            case "March":
                return 3;
            case "April":
                return 4;
            case "May":
                return 5;
            case "June":
                return 6;
            case "July":
                return 7;
            case "August":
                return 8;
            case "September":
                return 9;
            case "October":
                return 10;
            case "November":
                return 11;
            case "December":
                return 12;
            default:
                return 0;
        }

    }

    private String GetNoOfTimesUsed(String groupId, int numberOfDays) {
        String noOfTimesUsed = "SELECT machine_id,COUNT(*) as cnt FROM recyclable_item_transaction WHERE group_id = '%s' AND time_stamp >= (DATE(NOW()) - INTERVAL %d DAY) GROUP BY machine_id ORDER BY cnt DESC;";
        String query = String.format(noOfTimesUsed, groupId, numberOfDays);
        logger.info(query);
        return query;
    }

    private String getTotalWeightPerDaySql(String machineId) {
        String totalWeightPerDay = "SELECT DATE(time_stamp) AS day,SUM(item_weight) AS total_weight FROM recyclable_item_transaction WHERE machine_id = '%s' GROUP BY DATE(time_stamp) ORDER BY day ASC;";
        String query = String.format(totalWeightPerDay, machineId);
        logger.info(query);
        return query;
    }

    private String getTotalWeightPerWeekSql(String machineId) {
        String totalWeightPerWeek = "SELECT WEEK(time_stamp) AS week,SUM(item_weight) AS total_weight FROM recyclable_item_transaction WHERE machine_id = '%s' GROUP BY WEEK(time_stamp) ORDER BY week ASC;";
        String query = String.format(totalWeightPerWeek, machineId);
        logger.info(query);
        return query;
    }

    private String getTotalValueIssuedSql(String machineId) {
        String totalValueIssued = "SELECT reward_type,SUM(item_price) AS value FROM recyclable_item_transaction WHERE machine_id = '%s' GROUP BY reward_type;";
        String query = String.format(totalValueIssued, machineId);
        logger.info(query);
        return query;
    }


}
