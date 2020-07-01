/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.common;

import com.project.models.RCM;
import com.project.models.RCMMetrics;

/**
 * @author hari
 */
public class RCMOperationalStatus {

    private final RCM rcm;
    private final RCMMetrics rcmMetrics;

    public RCMOperationalStatus(RCM rcm, RCMMetrics rcmMetrics) {
        this.rcm = rcm;
        this.rcmMetrics = rcmMetrics;
    }

    public RCM getRcm() {
        return rcm;
    }

    public RCMMetrics getRcmMetrics() {
        return rcmMetrics;
    }
}
