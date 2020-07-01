/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.rcm.dao;

import com.project.common.Response;

import java.util.Date;

/**
 * @author hari
 */
public interface RCMEmptyTimeTransactionDao {
    int getNumberOfEmptyTimes(String machineId, Date startTime, Date endTime);

    Response addEmptyTime(String machineId, String groupId);
}
