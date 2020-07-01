/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.storage;

import com.project.common.RCMComposite;
import com.project.models.RCM;
import com.project.models.RCMGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

/**
 * @author hari
 */
public class RCMCompositeFactory {
    private static final Map<String, RCMComposite> rcmGroupCache = new HashMap<>();
    private static RCMCompositeFactory instance;
    final DaoFactory daoFactory;


    private RCMCompositeFactory() {
        daoFactory = DaoFactory.getInstance();
        ArrayList<RCM> rcms = daoFactory.getRCMDao().getRCMList();
        Map<String, List<RCM>> rcmsByGroupId = rcms.stream().collect(groupingBy(RCM::getGroupId));
        rcmsByGroupId.forEach((groupId, rcmList) -> {
            RCMComposite rcmComposite = new RCMComposite();
            rcmList.forEach(rcmComposite::add);
            rcmGroupCache.put(groupId, rcmComposite);
        });
    }

    public static RCMCompositeFactory getInstance() {
        if (instance == null) {
            instance = new RCMCompositeFactory();
        }
        return instance;
    }

    public RCMComposite getByGroupId(String groupId) {
        return rcmGroupCache.get(groupId);
    }

    public void update(String groupId, RCM rcm) {
        RCMComposite rcmComposite = rcmGroupCache.get(groupId);
        if (rcmComposite == null) {
            RCMComposite r = new RCMComposite();
            r.add(rcm);
            rcmGroupCache.put(groupId, r);
        } else {
            rcmComposite.add(rcm);
        }
    }


    public Map<String, RCMComposite> getRCMComponents() {
        return rcmGroupCache;
    }

    public void addRCMGroup(RCMGroup rcmGroup) {
        rcmGroupCache.put(rcmGroup.getGroupId(), new RCMComposite());
    }

    public void updateRCMStatus(RCM rcm) {
        RCMComposite rcmComposite = rcmGroupCache.get(rcm.getGroupId());
        rcmComposite.getRCMComponentList().stream().filter(m -> m.getMachineId().equals(rcm.getMachineId()))
                .forEach(m -> m.setStatus(rcm.getStatus()));

    }

    public void deleteRCM(RCM rcm) {
        RCMComposite rcmComposite = rcmGroupCache.get(rcm.getGroupId());
        rcmComposite.getRCMComponentList().removeIf(x -> x.getMachineId().equals(rcm.getMachineId()));
    }

}
