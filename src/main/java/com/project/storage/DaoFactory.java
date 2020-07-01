/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.storage;


import com.project.rcm.dao.*;
import com.project.rcm.daoImp.*;


/**
 * @author hari
 */
public class DaoFactory {
    private static DaoFactory instance;

    private DaoFactory() {

    }

    public static DaoFactory getInstance() {
        if (instance == null) {
            instance = new DaoFactory();
        }
        return instance;
    }

    public RCMDao getRCMDao() {
        return new RCMDaoImpl();
    }

    public RCMGroupDao getRCMGroupDao() {
        return new RCMGroupDaoImpl();
    }

    public RCMMetricsDao getRCMMetricsDao() {
        return new RCMMetricsDaoImpl();
    }

    public RCMPerItemTypeDao getRCMPerItemTypeDao() {
        return new RCMPerItemTypeDaoImpl();
    }

    public RecyclableItemTransactionDao getRecyclableItemTransactionDao() {
        return new RecyclableItemTransactionDaoImpl();
    }

    public RCMEmptyTimeTransactionDao getRCMEmptyTimeTransactionDao() {
        return new RCMEmptyTimeTransactionDaoImpl();
    }


}
