/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.rcm.dao;

import com.project.common.Response;
import com.project.enums.StatusEnum;
import com.project.models.RCM;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author hari
 */
public interface RCMDao {
    ArrayList<RCM> getRCMList();

    Response addRCM(String machineId, String locationId, String groupId, StatusEnum status, Date createdOn);

    Response updateGroupId(String machineId, String groupId);

    Response updateStatus(String machineId, StatusEnum status);

    Response deleteRCM(RCM rcm);
}
