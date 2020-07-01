/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.gui.mediator;

import com.project.common.RCMOperationalStatus;
import com.project.common.Response;
import com.project.common.Response.ResponseBuilder;
import com.project.enums.ItemTypeEnum;
import com.project.enums.ResponseStatus;
import com.project.enums.StatusEnum;
import com.project.facade.RMOS;
import com.project.gui.RecyclableMachineGUI;
import com.project.gui.renderers.ImageTextListRenderer;
import com.project.gui.renderers.ImagesNText;
import com.project.gui.state.Activate;
import com.project.gui.state.DeActivate;
import com.project.gui.state.StatusChanged;
import com.project.models.RCM;
import com.project.models.RCM.RCMBuilder;
import com.project.models.RCMMetrics;
import com.project.models.RCMPerItemType;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author hari
 */
public class GUIRmosHelper extends GUIImageHelper {

    private static GUIRmosHelper instance;
    final RMOS rmos;
    final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
    private final HashMap<String, JFrame> rcmFramesByMachineId = new HashMap<>();


    private GUIRmosHelper() {
        this.rmos = new RMOS();


    }

    public static GUIRmosHelper getInstance() {
        if (instance == null) {
            instance = new GUIRmosHelper();
        }
        return instance;
    }


    public void bootstrapRCMFrames() {
        try {
            rmos.getRCMGroupList().forEach(g -> rmos.getRCMList(g.getGroupId()).forEach(m -> {
                rcmFramesByMachineId.put(m.getMachineId(), new RecyclableMachineGUI(m));
            }));
        } catch (Exception ex) {
            Logger.getLogger(GUIRmosHelper.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public Response addRCMGroup(JLabel label, JTextField GroupId, JTextField GroupName, JTextField GroupDescription) {
        String groupId = GroupId.getText();
        String groupName = GroupName.getText();
        String groupDescription = GroupDescription.getText();
        if (groupId.length() == 0 && groupName.length() == 0 && groupDescription.length() == 0) {
            return buildErrorResponse("Please fill out the form!");
        } else {
            return rmos.addRCMGroup(groupId, groupName, groupDescription);

        }

    }

    public Response addRCM(JLabel label, JTextField MachineId, JTextField LocationId, JComboBox<String> GroupId, JTextField Capacity, JTextField InitialMoney) {
        java.util.Date date = new java.util.Date();
        String machineId = MachineId.getText();
        String locationId = LocationId.getText();
        String groupId = Objects.requireNonNull(GroupId.getSelectedItem()).toString();
        StatusEnum status = StatusEnum.DOWN;
        RCM rcm = new RCMBuilder().withMachineId(machineId).withLocationId(locationId).withGroupId(groupId).withStatus(status).withCreatedOn(date).build();
        if (machineId.length() == 0 && locationId.length() == 0 && groupId.length() == 0 && Capacity.getText().length() == 0 && InitialMoney.getText().length() == 0) {
            return buildErrorResponse("Please fill out the form!");
        } else {
            Response res = rmos.addRCM(machineId, locationId, groupId, StatusEnum.DOWN, date);
            if (res.getStatus().equals(ResponseStatus.SUCCESS)) {
                try {
                    double capacity = Double.parseDouble(Capacity.getText());
                    double initialMoney = Double.parseDouble(InitialMoney.getText());
                    res = rmos.addRCMMetrics(machineId, capacity, initialMoney, null, initialMoney, null, 0);
                    rcmFramesByMachineId.put(machineId, new RecyclableMachineGUI(rcm));
                    return res;
                } catch (Exception ex) {

                    return buildErrorResponse("Please enter the valid capacity/initial-money fields");
                }
            } else {
                return res;
            }
        }

    }

    public void addItemType(JLabel label, JComboBox itemType, JTextField priceField, JTextField weightField, JComboBox machineId) {

        ItemTypeEnum type = ItemTypeEnum.valueOf(Objects.requireNonNull(itemType.getSelectedItem()).toString());
        try {
            boolean t = false;
            double price = Double.parseDouble(priceField.getText());
            double weight = Double.parseDouble(weightField.getText());
            Response rs = rmos.addItemType(Objects.requireNonNull(machineId.getSelectedItem()).toString(), type, price, weight);
            if (rs.getStatus().equals(ResponseStatus.SUCCESS)) {
                setMessageForLabel(label, "Added Items Successfully");
            } else {
                setErrorForLabel(label, "Item Already Exists");
            }

        } catch (NumberFormatException ex) {
            setMessageForLabel(label, "Please fill all the fields");
        }
    }

    public void changeItemType(JLabel label, DefaultTableModel operationalStatusTableModel, JComboBox itemType, JComboBox machineIdList, JTextField priceField, JTextField weightField) {

        try {
            ItemTypeEnum type = ItemTypeEnum.valueOf(Objects.requireNonNull(itemType.getSelectedItem()).toString());
            String machineId = Objects.requireNonNull(machineIdList.getSelectedItem()).toString();
            double price = Double.parseDouble(priceField.getText());
            double weight = Double.parseDouble(weightField.getText());
            rmos.updateItemType(type, machineId, price, weight);
        } catch (Exception ex) {
            setMessageForLabel(label, "unable to change item type");
        }
    }

    public List<String> getGroupIdList() {
        List<String> l = new ArrayList<>();
        rmos.getRCMGroupList().forEach(m -> l.add(m.getGroupId()));
        return l;
    }

    public List<String> getRCMMachineIdList(String groupId) {
        return rmos.getRCMMachineIdList(groupId);
    }

    public List<RCM> getRCMList(String groupId) {
        return rmos.getRCMList(groupId);
    }

    public void getListImageNText(JComboBox jcombo, String groupId) {
        try {
            jcombo.setRenderer(new ImageTextListRenderer());
            for (RCM m : getRCMList(groupId)) {
                switch (m.getStatus()) {
                    case OPERATIONAL:
                        if (m.getMachineId().length() != 0) {
                            ImagesNText it = new ImagesNText(getScaledActiveIcon(10, 10), m.getMachineId());
                            jcombo.addItem(it);
                        }
                        break;
                    case DOWN:
                        if (m.getMachineId().length() != 0) {
                            ImagesNText it = new ImagesNText(getScaledInactiveIcon(10, 10), m.getMachineId());
                            jcombo.addItem(it);
                        }
                        break;
                    case FULL:
                        if (m.getMachineId().length() != 0) {
                            ImagesNText it = new ImagesNText(getScaledFullIcon(10, 10), m.getMachineId());
                            jcombo.addItem(it);
                        }
                        break;

                    case DELETED:
                        getRCMList(groupId).remove(m);
                    default:
                        break;
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(GUIRmosHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void populateRCMTable(DefaultTableModel model, String groupId) {
        try {
            List<RCMOperationalStatus> rs = rmos.getRCMOperationalStatus(groupId);

            rs.forEach(m -> model.addRow(
                    new Object[]{
                            m.getRcm().getMachineId(),
                            m.getRcm().getLocationId(),
                            String.format("%f", m.getRcmMetrics().getCapacity()),
                            String.format("%f", m.getRcmMetrics().getCurrentWeight()),
                            String.format("%f", m.getRcmMetrics().getCurrentMoney()),
                            m.getRcm().getStatus().toString()
                    }));
        } catch (Exception ex) {
            Logger.getLogger(GUIRmosHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void populateItemTypeTable(JLabel label, DefaultTableModel itemTypeTableModel, String machineId) {
        try {
            Map<String, List<RCMPerItemType>> map = rmos.getRCMPerItemType();
            map.get(machineId).forEach(m -> itemTypeTableModel.addRow(
                    new Object[]{
                            m.getItemType().toString(),
                            m.getPrice(),
                            m.getWeight()
                    }));
        } catch (Exception ex) {

            setErrorForLabel(label, ex.getMessage());
        }

    }

    public void changeRCMStatus(JLabel label, StatusEnum status, String groupId, String machineId) {
        if (machineId.length() == 0) {
            setErrorForLabel(label, "Select machine Id");
        } else {
            JFrame frame = rcmFramesByMachineId.get(machineId);
            Optional<RCM> rcm1 = rmos.getRCMList(groupId).stream().filter(m -> m.getMachineId().equals(machineId)).findFirst();
            rcm1.ifPresent(rcm -> frame.setTitle(String.format("MachineId: %s|GroupId: %s|LocationId: %s", machineId, groupId, rcm.getLocationId())));

            if (frame != null) {
                StatusChanged statusChange = new StatusChanged(frame);
                RCM rcm = new RCMBuilder().withStatus(status).withGroupId(groupId).withMachineId(machineId).build();
                Response rs = rmos.updateStatusRCM(rcm);
                if (rs.getStatus() == ResponseStatus.SUCCESS) {
                    switch (status) {
                        case OPERATIONAL:
                            setMessageForLabel(label, "RCM Activated");
                            statusChange.setState(new Activate());
                            statusChange.statusChanged();
                            break;
                        case DOWN:
                            setMessageForLabel(label, "RCM DeActivated");
                            statusChange.setState(new DeActivate());
                            statusChange.statusChanged();
                            break;
                        case DELETED:
                            setMessageForLabel(label, "RCM Deleted");
                            break;
                        default:
                            break;
                    }

                }
            }
        }

    }

    public void getRCMCheckWeight(JLabel label, String machineId) {
        Optional<RCMMetrics> rcmMetrics = rmos.getRCMMetrics().stream().filter(m -> m.getMachineId().equals(machineId)).findFirst();
        if (rcmMetrics.isPresent()) {
            if (rcmMetrics.get().getCapacity() == rcmMetrics.get().getCurrentWeight()) {
                setErrorForLabel(label, "RCM is full");
            } else {
                setMessageForLabel(label, String.format("The current weight of the machine id %s is %f", machineId, rcmMetrics.get().getCurrentWeight()));
            }
        } else {
            setErrorForLabel(label, String.format("Selected Machine Id : %s not found", machineId));
        }

    }

    public void getRCMCheckMoney(JLabel label, String machineId) {
        Optional<RCMMetrics> rcmMetrics = rmos.getRCMMetrics().stream().filter(m -> m.getMachineId().equals(machineId)).findFirst();
        if (rcmMetrics.isPresent()) {
            setMessageForLabel(label, String.format("The current money of the machine id %s is %f", machineId, rcmMetrics.get().getCurrentMoney()));
        } else {
            setErrorForLabel(label, String.format("Selected Machine Id : %s not found", machineId));
        }
    }

    public void getLastEmptiedTime(JLabel label, String machineId) {
        Optional<RCMMetrics> rcmMetrics = rmos.getRCMMetrics().stream().filter(m -> m.getMachineId().equals(machineId)).findFirst();
        if (rcmMetrics.isPresent()) {
            try {
                String date = rcmMetrics.get().getLastEmptiedOn().toString();
                setMessageForLabel(label, String.format("The machine was emptied on %s", date));
            } catch (Exception ex) {
                setMessageForLabel(label, "The machine was not emptied ");
            }
        } else {
            setErrorForLabel(label, String.format("Selected Machine Id : %s not found", machineId));
        }
    }

    public void getNumberOfRecycledItems(JLabel label, JComboBox month, JComboBox year, String machineId) {

        String yearString = (String) year.getSelectedItem();
        String monthString = (String) month.getSelectedItem();
        try {
            Integer count = rmos.getNumberOfItems(monthString, yearString, machineId);
            setMessageForLabel(label, String.format("The number of recycled items in month %s year %s is %s", monthString, yearString, count.toString()));
        } catch (Exception ex) {
            setErrorForLabel(label, "No recycled items present");
        }

    }

    public void emptyRCM(JLabel label, String machineId, String groupId) {
        if (machineId.length() == 0) {
            setErrorForLabel(label, "Select machine Id");
        } else {
            Response rs = rmos.emptyRCM(machineId);
            rmos.addEmptyTime(machineId, groupId);
            if (rs.getStatus() == ResponseStatus.SUCCESS) {
                setMessageForLabel(label, "RCM Emptied");
            }
        }
    }

    public void getMostFrequentlyUsedRCMInNDays(JLabel label, JTextField jTextField, String groupId) {
        try {
            String numberOfDays = jTextField.getText();
            String s = rmos.getMostFrequentlyUsedRCMInNdays(groupId, Integer.parseInt(numberOfDays));
            setMessageForLabel(label, String.format("The Most Frequently Used RCM is %s", s));
        } catch (Exception ex) {
            setErrorForLabel(label, "Select number of days");
        }
    }

    public DefaultCategoryDataset getDataSetWeightByDay(String machineId) {
        Map<Date, Integer> map = rmos.getWeightStatisticsPerDay(machineId);
        final DefaultCategoryDataset dataset
                = new DefaultCategoryDataset();
        for (Date key : map.keySet()) {
            dataset.addValue(map.get(key), "TotalWeight", key);
        }
        return dataset;
    }

    public DefaultCategoryDataset getDataSetWeightByWeek(String machineId) {
        Map<String, Integer> map = rmos.getWeightStatisticsPerWeek(machineId);
        final DefaultCategoryDataset dataset
                = new DefaultCategoryDataset();
        for (String key : map.keySet()) {
            dataset.addValue(map.get(key), "TotalWeight", key);
        }
        return dataset;
    }

    public DefaultCategoryDataset getDataSetValue(String machineId) {
        Map<String, Integer> map = rmos.getTotalValueIssued(machineId);
        final DefaultCategoryDataset dataset
                = new DefaultCategoryDataset();
        for (String key : map.keySet()) {
            dataset.addValue(map.get(key), "Total_Value", key);
        }
        return dataset;
    }

    public void getDataTextWeightByDay(JTable table, String machineId) {
        Map<Date, Integer> map = rmos.getWeightStatisticsPerDay(machineId);
        DefaultTableModel templatesTableModel = new DefaultTableModel(new String[0][0], new String[]{"day", "total weight"});
        map.keySet().forEach((key) -> templatesTableModel.addRow(
                new Object[]{
                        key, map.get(key)}));
        table.setModel(templatesTableModel);
    }

    public void getDataTextWeightByWeek(JTable table, String machineId) {
        Map<String, Integer> map = rmos.getWeightStatisticsPerWeek(machineId);
        DefaultTableModel model = new DefaultTableModel(new String[0][0], new String[]{"week", "total weight"});
        map.keySet().forEach((key) -> model.addRow(
                new Object[]{
                        key, map.get(key)}));
        table.setModel(model);
    }

    public void getTableValueRewardType(JTable table, String machineId) {
        Map<String, Integer> map = rmos.getTotalValueIssued(machineId);
        DefaultTableModel model = new DefaultTableModel(new String[0][0], new String[]{"reward type", "value"});
        map.keySet().forEach((key) -> model.addRow(
                new Object[]{
                        key, map.get(key)}));
        table.setModel(model);
    }

    public int getNumberOfEmptyTime(JLabel label, JTextField startTime, JTextField endTime, JComboBox machineIdList) {
        Date startDate;
        Date endDate;
        try {
            if (startTime.getText().length() != 0 && endTime.getText().length() != 0) {
                startDate = formatter.parse(startTime.getText());
                endDate = formatter.parse(endTime.getText());
                return rmos.getNumberOfEmptyTime(Objects.requireNonNull(machineIdList.getSelectedItem()).toString(), startDate, endDate);
            } else {
                setErrorForLabel(label, "Please enter all fields");
                return 0;
            }
        } catch (ParseException ex) {
            Logger.getLogger(GUIRmosHelper.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    public void getEmptyTimeTable(JLabel label, JTable table, JTextField startTime, JTextField endTime, JComboBox machineIdList) {
        if (startTime.getText().length() != 0 && endTime.getText().length() != 0) {
            int val = getNumberOfEmptyTime(label, startTime, endTime, machineIdList);
            String machineId = Objects.requireNonNull(machineIdList.getSelectedItem()).toString();
            DefaultTableModel model = new DefaultTableModel(new String[0][0], new String[]{"machineId", "Number Of Empty Times"});
            model.addRow(
                    new Object[]{
                            machineId, val
                    });

            table.setModel(model);
        } else {
            setErrorForLabel(label, "Please enter all fields");

        }

    }

    public DefaultCategoryDataset getDataSetEmptyTime(JLabel label, JTextField startTime, JTextField endTime, JComboBox machineIdList) {
        String machineId = Objects.requireNonNull(machineIdList.getSelectedItem()).toString();

        if (startTime.getText().length() != 0 && endTime.getText().length() != 0) {
            int value = getNumberOfEmptyTime(label, startTime, endTime, machineIdList);
            final DefaultCategoryDataset dataset
                    = new DefaultCategoryDataset();

            dataset.addValue(value, "Total_Value", machineId);

            return dataset;

        } else {
            setErrorForLabel(label, "Please enter all fields");
            return null;
        }

    }

    public void populateItemTypeCB(JLabel label, String machineId, JComboBox itemTypeList) {
        List<RCMPerItemType> rcmPerItemType = rmos.getItemTypes(machineId);
        itemTypeList.removeAllItems();
        try {
            rcmPerItemType.forEach((item) -> itemTypeList.addItem(item.getItemType()));
        } catch (Exception ex) {
            setErrorForLabel(label, "No item types available for the machine id");
        }
    }

    // private functions
    public void setErrorForLabel(JLabel label, String messsage) {
        label.setVisible(true);
        label.setText(messsage);
        label.setForeground(new java.awt.Color(234, 134, 133));
    }

    public void setMessageForLabel(JLabel label, String messsage) {
        label.setVisible(true);
        label.setText(messsage);
        label.setForeground(new java.awt.Color(99, 205, 218));
    }

    private Response buildErrorResponse(String message) {
        return new ResponseBuilder().withStatus(ResponseStatus.FAILURE)
                .withMessage(message)
                .build();
    }

    public void handleRCMIsFullTypeNotification(String machineId, Object rcm) {
        rmos.updateStatusRCM((RCM) rcm);


    }

    public void handleUpdateRCMMetricsTypeNotification(String machineId, Object o) {
        rmos.updateRCMMetrics((RCMMetrics) o);
    }

}
