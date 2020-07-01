/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.rcm.dao;

import com.project.common.Response;
import com.project.models.RCMGroup;

import java.util.List;

/**
 * @author hari
 */
public interface RCMGroupDao {
    List<RCMGroup> getGroupList();

    List<String> getGroupNames();

    Response addRCMGroup(String groupId, String groupName, String groupDescription);
}
