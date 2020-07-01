/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.common;

import com.project.models.RCM;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author hari
 */
public class RCMComposite extends RCMComponent {
    final ArrayList<RCMComponent> rcmComponents = new ArrayList<RCMComponent>();


    @Override
    public void add(RCMComponent rcmComponent) {
        rcmComponents.add(rcmComponent);
    }

    @Override
    public void remove(RCMComponent rcmComponent) {
        rcmComponents.remove(rcmComponent);
    }

    @Override
    public RCMComponent getComponent(int componentIndex) {
        return (RCMComponent) rcmComponents.get(componentIndex);
    }


    public List<RCM> getRCMComponentList() {
        return rcmComponents.stream().map(m -> (RCM) m).collect(toList());
    }


}

