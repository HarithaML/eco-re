/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.common;

import com.project.enums.ItemTypeEnum;

import java.util.ArrayList;

/**
 * @author hari
 */
public class RCMItemTypes {
    private String machineId;
    private ArrayList<ItemTypeEnum> itemTypes;

    private RCMItemTypes(String machineId, ArrayList<ItemTypeEnum> itemTypes) {
        this.machineId = machineId;
        this.itemTypes = itemTypes;
    }

    public String getMachineId() {
        return machineId;
    }

    public void setMachineId(String machineId) {
        this.machineId = machineId;
    }

    public ArrayList<ItemTypeEnum> getItemTypes() {
        return itemTypes;
    }

    public void setItemTypes(ArrayList<ItemTypeEnum> itemTypes) {
        this.itemTypes = itemTypes;
    }

    public static class RCMItemTypesBuilder {
        private String machineId;
        private ArrayList<ItemTypeEnum> itemTypes;

        public RCMItemTypesBuilder withMachineId(String machineId) {
            this.machineId = machineId;
            return this;
        }

        public RCMItemTypesBuilder withItemTypes(ArrayList<ItemTypeEnum> itemTypes) {
            this.itemTypes = itemTypes;
            return this;
        }

        public RCMItemTypes build() {
            return new RCMItemTypes(machineId, itemTypes);
        }
    }

}
