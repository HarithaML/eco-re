/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.rcm.dao;

import com.project.common.Response;
import com.project.models.RCMMetrics;

import java.util.ArrayList;

/**
 * @author hari
 */
public interface RCMMetricsDao {

    ArrayList<RCMMetrics> getRCMMetricsList();

    Response addRCMMetrics(String machineId, double capacity, double initialMoney, double currentMoney, double currentWeight);

    Response emptyRCM(String machineId);

    Response updateRCMMetrics(RCMMetrics rcmMetrics);
}
