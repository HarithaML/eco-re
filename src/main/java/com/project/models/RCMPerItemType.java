/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.models;

import com.project.enums.ItemTypeEnum;

import java.sql.ResultSet;


/**
 * @author hari
 */
public class RCMPerItemType {
    private String machineId;
    private ItemTypeEnum itemType;
    private double price;
    private double weight;


    public RCMPerItemType(String machineId, ItemTypeEnum itemType, double price, double weight) {
        this.machineId = machineId;
        this.itemType = itemType;
        this.price = price;
        this.weight = weight;
    }

    public static RCMPerItemType fromResultSet(ResultSet rs) throws Exception {
        return new RCMPerItemTypeBuilder().withMachineId(rs.getString("machine_id"))
                .withItemType(ItemTypeEnum.valueOf(rs.getString("item_type")))
                .withPrice(rs.getFloat("price"))
                .withWeight(rs.getFloat("weight"))
                .build();
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getMachineId() {
        return machineId;
    }

    public void setMachineId(String machineId) {
        this.machineId = machineId;
    }

    public ItemTypeEnum getItemType() {
        return itemType;
    }

    public void setItemTypes(ItemTypeEnum itemType) {
        this.itemType = itemType;
    }

    public static class RCMPerItemTypeBuilder {
        private String machineId;
        private ItemTypeEnum itemType;
        private double price;
        private double weight;

        public RCMPerItemTypeBuilder withMachineId(String machineId) {
            this.machineId = machineId;
            return this;
        }

        public RCMPerItemTypeBuilder withItemType(ItemTypeEnum itemType) {
            this.itemType = itemType;
            return this;
        }

        public RCMPerItemTypeBuilder withPrice(double price) {
            this.price = price;
            return this;
        }

        public RCMPerItemTypeBuilder withWeight(double weight) {
            this.weight = weight;
            return this;
        }

        public RCMPerItemType build() {
            return new RCMPerItemType(machineId, itemType, price, weight);
        }
    }
}
