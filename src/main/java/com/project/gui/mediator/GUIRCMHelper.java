/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.gui.mediator;

import com.project.common.GiftVoucher;
import com.project.common.Money;
import com.project.common.Reward;
import com.project.enums.RewardsEnum;
import com.project.enums.StatusEnum;
import com.project.facade.RCMFacade;
import com.project.gui.renderers.ImagesNText;
import com.project.models.RCM;
import com.project.models.RCM.RCMBuilder;
import com.project.models.RCMMetrics;
import com.project.models.RCMMetrics.RCMMetricsBuilder;
import com.project.models.RCMPerItemType;
import com.project.models.RecyclableItemTransaction;
import com.project.observers.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.*;

/**
 * @author hari
 */
public class GUIRCMHelper extends GUIImageHelper {

    private final RCMFacade rcmFacade;
    private final String machineId;
    private final RCMObserverable rcmObserverable;
    private DefaultTableModel itemTypeTableModel;
    private DefaultTableModel metricsTableModel;
    private List<RecyclableItemTransaction> recyclableItemTransaction;


    public GUIRCMHelper(String machineId) {
        this.machineId = machineId;
        this.rcmFacade = new RCMFacade();

        RMOSObserverable obsv = RMOSObserverable.getInstance();
        obsv.addObserver(new RCMObserver(machineId));

        initModel();

        recyclableItemTransaction = new ArrayList<>();

        rcmObserverable = new RCMObserverable();
        rcmObserverable.addObserver(new RMOSObserver());
    }

    private void initModel() {
        itemTypeTableModel = new DefaultTableModel(new String[0][0], new String[]{"Type", "Price", "Weight"}) {
            @Override
            public Class<?> getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return ImagesNText.class;
                    default:
                        return String.class;
                }

            }
        };
        metricsTableModel = new DefaultTableModel(new String[0][0], new String[]{"Capacity", "Current Weight", "Current Money"}) {
            @Override
            public Class<?> getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return ImagesNText.class;
                    default:
                        return String.class;
                }

            }
        };
    }

    public DefaultTableModel getItemTypesModel() {
        return itemTypeTableModel;
    }

    public DefaultTableModel getMetricsTableModel() {
        return metricsTableModel;
    }

    public void populateItemTypeTable() {
        List<RCMPerItemType> rcmPerItemTypes = rcmFacade.getRCMPerItemTypes(machineId);
        if (rcmPerItemTypes.size() > 0) {
            itemTypeTableModel.setRowCount(0);
            rcmPerItemTypes.forEach(m
                    -> itemTypeTableModel.addRow(
                    new Object[]{
                            new ImagesNText(getScaledCashIcon(10, 10), m.getItemType().toString()), m.getPrice(), m.getWeight()
                    }));

        }
    }

    public void populateMetricsTable() {
        RCMMetrics metrics = rcmFacade.getRCMMetrics(machineId);
        metricsTableModel.setRowCount(0);
        metricsTableModel.addRow(
                new Object[]{
                        metrics.getCapacity(), metrics.getCurrentWeight(), metrics.getCurrentMoney()
                });


    }

    public void populateItemTypeList(JComboBox comboBox) {
        List<RCMPerItemType> itemtypeList = rcmFacade.getRCMPerItemTypes(machineId);
        itemtypeList.forEach(m -> comboBox.addItem(m.getItemType()));
    }

    public void showSelectedItemTypeListLbs(JTable table) {
        DefaultTableModel model = new DefaultTableModel(new String[0][0], new String[]{"Type", "Price", "SelectedWeight(in Lbs)"});
        try {
            recyclableItemTransaction.forEach(m -> model.addRow(
                    new Object[]{
                            m.getItemType(),
                            m.getItemPrice(),
                            m.getItemWieght(),}));
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }

        table.setModel(model);
    }

    public void showSelectedItemTypeListKgs(JTable table) {
        if (!recyclableItemTransaction.isEmpty()) {
            DefaultTableModel model = new DefaultTableModel(new String[0][0], new String[]{"Type", "Price", "SelectedWeight(in Kgs)"});
            recyclableItemTransaction.forEach(m -> model.addRow(
                    new Object[]{
                            m.getItemType(),
                            m.getItemPrice(),
                            m.getItemWieght() * 0.453592
                    }));
            table.setModel(model);
        }
    }

    public void setTotalPriceLabel(JLabel label) {

        label.setText(String.valueOf(caluclateItemPriceForTransactions()));

    }

    public void handleAddRCMTypeNotification(String machineId, RCMPerItemType rcmPerItemType) {
        this.rcmFacade.handleAddRCMTypeNotification(machineId, rcmPerItemType);
        populateItemTypeTable();
        //TODO update cacheHelper and rerender
    }

    public void handleUpdateRCMTypeNotification(String machineId, RCMPerItemType rcmPerItemType) {
        this.rcmFacade.handleUpdateRCMTypeNotification(machineId, rcmPerItemType);
        populateItemTypeTable();
    }

    public void addItemsInTransaction(JLabel label, RecyclableItemTransaction itemsInTransaction, String groupId, boolean notifyIsFull) {
        double totalWeight = caluclateItemWeightForTransactions() + itemsInTransaction.getItemWieght();
        if (isRCMFull(totalWeight)) {
            label.setText("Exceeding Please enter valid weight ");
            RCM rcm = new RCMBuilder()
                    .withMachineId(machineId)
                    .withGroupId(groupId)
                    .withStatus(StatusEnum.FULL)
                    .build();
            if (notifyIsFull) {
                NotificationObject notificationObject = new NotificationObject(machineId, "RCMIsFull", rcm);
                rcmObserverable.notifyAllObservers(notificationObject);
            }
        } else {
            List<RCMPerItemType> itemtypeList = rcmFacade.getRCMPerItemTypes(machineId);
            Optional<RCMPerItemType> siOpt = itemtypeList.stream()
                    .filter(m -> m.getItemType().equals(itemsInTransaction.getItemType()))
                    .findFirst();
            if (siOpt.isPresent()) {
                RCMPerItemType ss = siOpt.get();
                itemsInTransaction.setItemPrice((ss.getPrice() * itemsInTransaction.getItemWieght()) / ss.getWeight());
            } else {
                setErrorForLabel(label, "Item Type Not Found");
            }
            try {
                recyclableItemTransaction.add(itemsInTransaction);
            } catch (Exception ex) {
                recyclableItemTransaction = new ArrayList<>();
                recyclableItemTransaction.add(itemsInTransaction);
            }

        }
    }

    public boolean isRCMFull(double totalWeight) {
        RCMMetrics rcmMetrics = rcmFacade.getRCMMetrics(machineId);
        double capacity = rcmMetrics.getCapacity();
        double currentWeight = rcmMetrics.getCurrentWeight();
        double weight = currentWeight + totalWeight;
        return weight > capacity; // RCM is full
    }

    public Reward getReward(JComboBox rewardSelectionCombo) {
        Reward r = null;
        if (!recyclableItemTransaction.isEmpty()) {

            RCMMetrics rcmMetrics = rcmFacade.getRCMMetrics(machineId);
            double currentMoney = rcmMetrics.getCurrentMoney();
            double totalPrice = caluclateItemPriceForTransactions();
            double delta = currentMoney - totalPrice;

            if (delta >= 0.0) {
                if (Objects.requireNonNull(Objects.requireNonNull(rewardSelectionCombo.getSelectedItem())).equals("Money")) {
                    recyclableItemTransaction.forEach(m -> m.setRewardType(RewardsEnum.Money));
                    r = new Money(totalPrice);

                } else if (rewardSelectionCombo.getSelectedItem().equals("GiftVoucher")) {
                    recyclableItemTransaction.forEach(m -> m.setRewardType(RewardsEnum.Gift_Voucher));
                    r = new GiftVoucher(totalPrice);
                } else {
                    r = null;
                }
            } else {
                recyclableItemTransaction.forEach(m -> m.setRewardType(RewardsEnum.Gift_Voucher));
                r = new GiftVoucher(totalPrice);
            }

        }
        return r;
    }

    public void getRewardSelectionState(JComboBox rewardSelectionCombo, JLabel label) {
        RCMMetrics rcmMetrics = rcmFacade.getRCMMetrics(machineId);
        double currentMoney = rcmMetrics.getCurrentMoney();
        double totalPrice = caluclateItemPriceForTransactions();
        double delta = currentMoney - totalPrice;
        if (delta >= 0.0) {
            if (rewardSelectionCombo.getSelectedIndex() == 1) {
                label.setText(String.format("The amount %f will be given as money.", totalPrice));
            } else if (rewardSelectionCombo.getSelectedIndex() == 2) {
                label.setText(String.format("The amount %f will be given as giftVoucher.", totalPrice));
            }

        } else {
            label.setText(String.format("Sorry! there isn't enough money in the machine so the amount %f will be given as giftVoucher.", totalPrice));
        }
    }

    //submission of data to database
    public RCMMetrics submitRCMMetricsTransactions() {
        double totalWeight = caluclateItemWeightForTransactions();
        double totalMoney = caluclateItemPriceForTransactions();
        Date lastUsedOn = new Date();

        return new RCMMetricsBuilder().withCurrentWeight(totalWeight)
                .withCurrentMoney(totalMoney)
                .withLastUsedOn(lastUsedOn)
                .withMachineId(machineId)
                .build();
    }

    public List<RecyclableItemTransaction> submitRecyclableTransactions(String groupId) {
        recyclableItemTransaction.forEach(m -> m.setGroupId(groupId));
        return recyclableItemTransaction;
    }

    public void submitTransactions(String groupId) {
        NotificationObject notificationObject = new NotificationObject(machineId, "UpdateRCMMetrics", submitRCMMetricsTransactions());
        rcmObserverable.notifyAllObservers(notificationObject);
        rcmFacade.updateTransactions(submitRCMMetricsTransactions(), submitRecyclableTransactions(groupId));
    }

    public void clearAllData() {
        recyclableItemTransaction = null;
    }

    // cacncel adding items
    public void cancelTrasaction() {
        clearAllData();

    }

    // private function
    private double caluclateItemPriceForTransactions() {
        double c = 0.0;
        if (!recyclableItemTransaction.isEmpty()) {
            c = recyclableItemTransaction.stream().map(RecyclableItemTransaction::getItemPrice).reduce(0.0, Double::sum);
        }
        return c;
    }

    private double caluclateItemWeightForTransactions() {
        if (recyclableItemTransaction == null || recyclableItemTransaction.isEmpty()) {
            return 0.0;
        } else {
            return recyclableItemTransaction.stream()
                    .map(RecyclableItemTransaction::getItemWieght)
                    .reduce(0.0, Double::sum);
        }

    }


    // private functions 
    public void setErrorForLabel(JLabel label, String message) {
        label.setVisible(true);
        label.setText(message);
        label.setForeground(new java.awt.Color(234, 134, 133));
    }

    public void setMessageForLabel(JLabel label, String message) {
        label.setVisible(true);
        label.setText(message);
        label.setForeground(new java.awt.Color(99, 205, 218));
    }
}
