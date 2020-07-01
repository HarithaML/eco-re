/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.models;

import com.project.enums.ItemTypeEnum;
import com.project.enums.RewardsEnum;

import java.sql.ResultSet;
import java.util.Date;

/**
 * @author hari
 */
public class RecyclableItemTransaction {
    private ItemTypeEnum itemType;
    private double itemWieght;
    private String machineId;
    private String groupId;
    private Date timestamp;
    private RewardsEnum rewardType;
    private double itemPrice;

    private RecyclableItemTransaction(String machineId, String groupId, ItemTypeEnum itemType, double itemWieght, Date timestamp, double itemPrice, RewardsEnum rewardType) {
        this.itemType = itemType;
        this.itemWieght = itemWieght;
        this.timestamp = timestamp;
        this.itemPrice = itemPrice;
        this.rewardType = rewardType;
        this.machineId = machineId;
        this.groupId = groupId;
    }

    public static RecyclableItemTransaction fromResultSet(ResultSet rs) throws Exception {
        return new RecyclableItemTransactionBuilder().withItemType(ItemTypeEnum.valueOf(rs.getString("item_type")))
                .withItemWieght(rs.getDouble("item_Wieght"))
                .withTimestamp(rs.getTimestamp("time_stamp"))
                .withReward(RewardsEnum.valueOf(rs.getString("reward_type")))
                .withItemPrice(rs.getDouble("item_price"))
                .withMachineId(rs.getString("machine_id"))
                .withGroupId(rs.getString("group_id"))
                .build();
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public RewardsEnum getReward() {
        return rewardType;
    }

    public void setReward(RewardsEnum rewardType) {
        this.rewardType = rewardType;
    }

    public String getMachineId() {
        return machineId;
    }

    public void setMachineId(String machineId) {
        this.machineId = machineId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public RewardsEnum getRewardType() {
        return rewardType;
    }

    public void setRewardType(RewardsEnum rewardType) {
        this.rewardType = rewardType;
    }

    public ItemTypeEnum getItemType() {
        return itemType;
    }

    public void setItemType(ItemTypeEnum itemType) {
        this.itemType = itemType;
    }

    public double getItemWieght() {
        return itemWieght;
    }

    public void setItemWieght(double itemWieght) {
        this.itemWieght = itemWieght;
    }

    public Date getAddItemTime() {
        return timestamp;
    }

    public void setAddItemTime(Date addItemTime) {
        this.timestamp = addItemTime;
    }

    public static class RecyclableItemTransactionBuilder {
        private ItemTypeEnum itemType;
        private double itemWieght;
        private Date timestamp;
        private RewardsEnum rewardType;
        private double itemPrice;
        private String machineId;
        private String groupId;

        public RecyclableItemTransactionBuilder withItemType(ItemTypeEnum itemType) {
            this.itemType = itemType;
            return this;
        }

        public RecyclableItemTransactionBuilder withItemWieght(double itemWieght) {
            this.itemWieght = itemWieght;
            return this;
        }

        public RecyclableItemTransactionBuilder withTimestamp(Date timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public RecyclableItemTransactionBuilder withReward(RewardsEnum rewardType) {
            this.rewardType = rewardType;
            return this;
        }

        public RecyclableItemTransactionBuilder withItemPrice(double itemPrice) {
            this.itemPrice = itemPrice;
            return this;
        }

        public RecyclableItemTransactionBuilder withMachineId(String machineId) {
            this.machineId = machineId;
            return this;
        }

        public RecyclableItemTransactionBuilder withGroupId(String groupId) {
            this.groupId = groupId;
            return this;
        }

        public RecyclableItemTransaction build() {
            return new RecyclableItemTransaction(machineId, groupId, itemType, itemWieght, timestamp, itemPrice, rewardType);
        }
    }
}
