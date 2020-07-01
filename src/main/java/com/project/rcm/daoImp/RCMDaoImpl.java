/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.rcm.daoImp;

import com.project.common.Response;
import com.project.enums.StatusEnum;
import com.project.models.RCM;
import com.project.rcm.dao.RCMDao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.function.Function;

public class RCMDaoImpl extends DaoHelper implements RCMDao {

    public RCMDaoImpl() {
    }

    @Override
    public ArrayList<RCM> getRCMList() {
        ArrayList<RCM> rcms = new ArrayList<>(getAllRcmTypes());
        logSuccess("Fetched all the RCMs from database");
        return rcms;
    }

    @Override
    public Response addRCM(String machineId, String locationId, String groupId, StatusEnum status, Date createdOn) {
        String query = getInsertRCMQuery(machineId, locationId, groupId, status, createdOn);
        return updateSql(query);
    }

    @Override
    public Response updateGroupId(String machineId, String groupId) {
        String params = String.format("group_id = '%s'", groupId);
        return updateSql(getUpdateRCMSql(params, machineId));
    }

    @Override
    public Response updateStatus(String machineId, StatusEnum status) {
        String params = String.format("status = '%s'", status);
        return updateSql(getUpdateRCMSql(params, machineId));
    }

    @Override
    public Response deleteRCM(RCM rcm) {

        String query = getDeleteRCMSql(rcm.getMachineId());
        System.out.println(updateSql(query).getMessage() + updateSql(query).getStatus());
        return updateSql(query);
    }

    //private methods
    public Collection<RCM> getAllRcmTypes() {
        Function<ResultSet, Collection<RCM>> callback = (rs) -> {
            Collection<RCM> rcmList = new ArrayList<>();
            try {
                while (rs.next()) {
                    rcmList.add(RCM.fromResultSet(rs));
                }
            } catch (Exception ex) {
                logger.severe(String.format("Failed To Get RCMs, %s", ex.getMessage()));
            }
            return rcmList;
        };
        //Query Helpers
        String listRCMSql = "SELECT * from recycle_machines;";
        return runSql(listRCMSql, callback);
    }


    //private Query methods
    private String getInsertRCMQuery(String machineId, String locationId, String groupId, StatusEnum status, Date createdOn) {
        String insertRCMSql = "Insert into recycle_machines (machine_id, location_id, group_id, status, created_on) values ('%s','%s','%s','%s', '%s');";
        String query = String.format(insertRCMSql, machineId, locationId, groupId, status, getDateAsString(createdOn));
        logger.info(query);
        return query;
    }

    private String getUpdateRCMSql(String parameters, String machineId) {
        String updateRCMSql = "UPDATE recycle_machines SET %s WHERE machine_id = '%s';";
        String query = String.format(updateRCMSql, parameters, machineId);
        logger.info(query);
        return query;
    }

    private String getDeleteRCMSql(String machineId) {
        String deleteRCMSql = "DELETE FROM recycle_machines WHERE machine_id = '%s';";
        String query = String.format(deleteRCMSql, machineId);
        logger.info(query);
        return query;
    }

}
