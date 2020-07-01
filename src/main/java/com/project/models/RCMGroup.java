/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.models;

import java.sql.ResultSet;

/**
 * @author hari
 */
public class RCMGroup {

    private final String groupId;
    private final String groupName;
    private final String groupDescription;

    private RCMGroup(String groupId, String groupName, String groupDescription) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.groupDescription = groupDescription;
    }

    public static RCMGroup fromResultSet(ResultSet rs) throws Exception {
        return new RCMGroup.RCMGroupBuilder().withGroupId(rs.getString("group_id"))
                .withGroupName(rs.getString("group_name"))
                .withGroupDescription(rs.getString("group_description"))
                .build();
    }

    public String getGroupId() {
        return groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getGroupDescription() {
        return groupDescription;
    }

    public static class RCMGroupBuilder {

        String groupId;
        String groupName;
        String groupDescription;

        public RCMGroupBuilder withGroupId(String groupId) {
            this.groupId = groupId;
            return this;
        }

        public RCMGroupBuilder withGroupName(String groupName) {
            this.groupName = groupName;
            return this;
        }

        public RCMGroupBuilder withGroupDescription(String groupDescription) {
            this.groupDescription = groupDescription;
            return this;
        }

        public RCMGroup build() {
            return new RCMGroup(groupId, groupName, groupDescription);
        }

    }
}
