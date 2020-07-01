/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.rcm.daoImp;

import com.project.common.Response;
import com.project.models.RCMGroup;
import com.project.rcm.dao.RCMGroupDao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author hari
 */
public class RCMGroupDaoImpl extends DaoHelper implements RCMGroupDao {

    public RCMGroupDaoImpl() {
    }

    @Override
    public List<RCMGroup> getGroupList() {
        try {
            return new ArrayList<>(getAllRcmGroups());
        } catch (Exception e) {
            logError("Failed", "Not fetched RCM Groups from database");
            return new ArrayList<>();
        }
    }

    @Override
    public List<String> getGroupNames() {
        return getAllRcmGroups().stream().map(RCMGroup::getGroupName)
                .collect(Collectors.toList());
    }

    @Override
    public Response addRCMGroup(String groupId, String groupName, String groupDescription) {
        String query = getInsertRCMQuery(groupId, groupName, groupDescription);
        return updateSql(query);
    }

    //private methods
    public Collection<RCMGroup> getAllRcmGroups() {
        Function<ResultSet, Collection<RCMGroup>> callback = (rs) -> {
            Collection<RCMGroup> rcmGroupList = new ArrayList<>();
            try {
                while (rs.next()) {
                    rcmGroupList.add(RCMGroup.fromResultSet(rs));
                }
            } catch (Exception ex) {
                logger.severe(String.format("Failed To Get RCM Groups, %s", ex.getMessage()));
            }
            return rcmGroupList;
        };
        //Query Helpers
        String listRCMGroupSql = "SELECT * from rcm_groups;";
        return runSql(listRCMGroupSql, callback);
    }

    //private Query methods
    private String getInsertRCMQuery(String group_id, String group_name, String group_description) {
        String insertRCMGroupSql = "Insert into rcm_groups (group_id, group_name, group_description) values ('%s','%s','%s');";
        String query = String.format(insertRCMGroupSql, group_id, group_name, group_description);
        logger.info(query);
        return query;
    }

    private String getUpdateRCMSql(String parameters, String group_id) {
        String updateRCMGroupSql = "UPDATE rcm_groups SET %s WHERE group_id = '%s';";
        String query = String.format(updateRCMGroupSql, parameters, group_id);
        logger.info(query);
        return query;
    }

}
