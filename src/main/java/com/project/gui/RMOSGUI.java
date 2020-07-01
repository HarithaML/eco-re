/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.gui;

import com.project.common.Response;
import com.project.enums.ItemTypeEnum;
import com.project.enums.ResponseStatus;
import com.project.enums.StatusEnum;
import com.project.gui.mediator.GUIImageHelper;
import com.project.gui.mediator.GUIRmosHelper;
import com.project.gui.renderers.ImagesNText;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Calendar;
import java.util.Objects;

/**
 * @author
 */
public class RMOSGUI extends javax.swing.JFrame {

    private final GUIRmosHelper guiRmosHelper;
    JFreeChart barChartData;
    ChartPanel barPanel;
    private String selectedGroupId;
    private javax.swing.DefaultComboBoxModel RCMFunctionsComboBoxModel;
    private String UPDATE_RCM_CAPABILITIES;
    private String RCM_METRICS;
    private String RCM_OPS;
    private String L_MACHINE_ID;
    private String L_LOCATION_ID;
    private String L_MAX_WEIGHT;
    private String L_CURRENT_WEIGHT;
    private String L_CURRENT_MONEY;
    private String L_STATUS;
    private String WELCOME_MESSAGE;
    private String I_TYPE;
    private String I_PRICE;
    private String I_WEIGHT;
    private javax.swing.JButton activateRCMButton;
    private javax.swing.JComboBox<String> addGroupComboBox;
    private javax.swing.JButton addRCMButton;
    private javax.swing.JButton addRCMFormButton;
    private javax.swing.JButton addRCMGroup;
    private javax.swing.JButton addRCMGroupButton;
    private javax.swing.JLayeredPane addRCMGroupPanel;
    private javax.swing.JPanel addRCMPanel;
    private javax.swing.JTextField capacityTextField;
    private javax.swing.JComboBox<String> changeItemTypeRcmComboBox;
    private javax.swing.JButton checkEmptiedTimeButton;
    private javax.swing.JButton checkMoneyButton;
    private javax.swing.JPanel checkRCMMetrics;
    private javax.swing.JButton checkWeightButton;
    private javax.swing.JComboBox<String> currentWeightRCMComboBox;
    private javax.swing.JButton deactivateRCMButton;
    private javax.swing.JLabel ecoReLogo;
    private javax.swing.JButton emptyRCMButton;
    private javax.swing.JComboBox<String> emptyTimeComboBox;
    private javax.swing.JTextField endTimeField;
    private javax.swing.JTextField groupDescriptionTextField;
    private javax.swing.JTextField groupIdTextField;
    private javax.swing.JTextField groupNameTextField;
    private javax.swing.JTextField initialDepositTextField;
    private javax.swing.JComboBox<String> itemTypeSelectComboBoxChange;
    private javax.swing.JComboBox<String> itemTypeSelectionComboBoxaddItem;
    private javax.swing.JTextField locationIdTextField;
    private javax.swing.JTextField machineIdTextField;
    private javax.swing.JButton mostFrequentlyUsedButton;
    private javax.swing.JTextField numberOfDaysRCMMostUsedField;
    private javax.swing.JComboBox<String> operationsRCMComboBox;
    private javax.swing.JPanel operationsRCMPanel;
    private javax.swing.JLabel outputLabel;
    private javax.swing.JTextField priceChangeField;
    private javax.swing.JTextField priceTextField;
    private javax.swing.JComboBox<String> rcmComboBoxAddItemType;
    private javax.swing.JComboBox<String> rcmGroupIdComboBox;
    private javax.swing.JTabbedPane rcmStatusTrackPanel;
    private javax.swing.JButton removeRCMButton;
    private javax.swing.JComboBox<String> selectCategoryComboBox;
    private javax.swing.JComboBox<String> selectFunctionalityComboBox;
    private javax.swing.JComboBox<String> selectMonthComboBox;
    private javax.swing.JComboBox<String> selectRCMValueBox;
    private javax.swing.JComboBox<String> selectRCMWeightOfRCMStatisticsComboBox;
    private javax.swing.JComboBox<String> selectYearComboBox;
    private javax.swing.JTextField startTimeField;
    private javax.swing.JButton submitCheckRCMMetricsButton;
    private javax.swing.JButton submitFunctionalitySelection;
    private javax.swing.JPanel textPanel;
    private javax.swing.JPanel updateCapabilitiesPanel;
    private javax.swing.JLabel userIcon;
    private javax.swing.JPanel visualDisplayPanel;
    private javax.swing.JTextField weightChangeField;
    private javax.swing.JTextField weightTextField;
    private javax.swing.JTable weightValueEmptyTable;
    private javax.swing.JPanel welcomePanel;
    private DefaultTableModel operationalStatusTableModel;
    private DefaultTableModel showItemTypeTableModel;

    /**
     * Creates new form RMODashboardGUI
     */
    public RMOSGUI() {
        guiRmosHelper = GUIRmosHelper.getInstance();
        initConstants();
        initTableModel();
        initComponents();
        initLogos();
        hideAllPanels();
        resetComboBoxList();
        welcomePanel.setVisible(true);
        initGroupDropDown();
        guiRmosHelper.bootstrapRCMFrames();
    }

    private void initGroupDropDown() {
        addGroupComboBox.removeAllItems();
        guiRmosHelper.getGroupIdList().forEach(m -> addGroupComboBox.addItem(m));
    }

    private void initImageNText() {
        operationsRCMComboBox.removeAllItems();
        guiRmosHelper.getListImageNText(operationsRCMComboBox, selectedGroupId);
    }

    private void initLogos() {
        ecoReLogo.setIcon(GUIImageHelper.getScaledLogoImage(ecoReLogo.getWidth(), ecoReLogo.getHeight()));
        userIcon.setIcon(GUIImageHelper.getScaledUserIcon(userIcon.getWidth(), userIcon.getHeight()));
        addRCMGroupButton.setIcon(GUIImageHelper.getScaledPlusIcon(14, 14));
        addRCMButton.setIcon(GUIImageHelper.getScaledPlusIcon(14, 14));
        submitFunctionalitySelection.setIcon(GUIImageHelper.getScaledSendIcon(14, 14));
        addRCMFormButton.setIcon(GUIImageHelper.getScaledSendIcon(14, 14));
        addRCMGroup.setIcon(GUIImageHelper.getScaledSendIcon(14, 14));
        activateRCMButton.setIcon(GUIImageHelper.getScaledOnlineIcon(14, 14));
        deactivateRCMButton.setIcon(GUIImageHelper.getScaledDeactivateIcon(14, 14));
        emptyRCMButton.setIcon(GUIImageHelper.getScaledEmptyIcon(14, 14));
        removeRCMButton.setIcon(GUIImageHelper.getScaledRemoveIcon(14, 14));

        checkWeightButton.setIcon(GUIImageHelper.getScaledWeightIcon(14, 14));
        checkMoneyButton.setIcon(GUIImageHelper.getScaledCashIcon(14, 14));
        checkEmptiedTimeButton.setIcon(GUIImageHelper.getScaledTimeIcon(14, 14));

        submitCheckRCMMetricsButton.setIcon(GUIImageHelper.getScaledSendIcon(14, 14));
        mostFrequentlyUsedButton.setIcon(GUIImageHelper.getScaledSendIcon(14, 14));
    }

    private void initTableModel() {
        operationalStatusTableModel = new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                        L_MACHINE_ID, L_LOCATION_ID, L_MAX_WEIGHT, L_CURRENT_WEIGHT, L_CURRENT_MONEY, L_STATUS
                }
        );

        showItemTypeTableModel = new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                        I_TYPE,
                        I_PRICE,
                        I_WEIGHT
                }
        );

        RCMFunctionsComboBoxModel = new javax.swing.DefaultComboBoxModel<>(new String[]{"--Select Functionality--", UPDATE_RCM_CAPABILITIES, RCM_OPS, RCM_METRICS, " "});
    }

    private void initConstants() {
        UPDATE_RCM_CAPABILITIES = "Update RCM Capabilities";
        RCM_METRICS = "Show RCM Metrics";
        RCM_OPS = "RCM Operations";

        L_MACHINE_ID = "Machine Id";
        L_LOCATION_ID = "Location Id";
        L_MAX_WEIGHT = "Max. Weight";
        L_CURRENT_WEIGHT = "Curr. Weight";
        L_CURRENT_MONEY = "Curr. Money";
        L_STATUS = "Status";

        I_TYPE = "Item Type";
        I_PRICE = "Price";
        I_WEIGHT = "Weight";

        WELCOME_MESSAGE = "Welcome Admin";

    }

    private void initComponents() {

        JPanel mainPanel = new JPanel();
        JPanel topPanel = new JPanel();
        ecoReLogo = new javax.swing.JLabel();
        JLabel logoutLabel = new JLabel();
        userIcon = new javax.swing.JLabel();
        JPanel bodyPanel = new JPanel();
        JPanel mrcTopPanel = new JPanel();
        JPanel addButtonsPanel = new JPanel();
        addRCMGroupButton = new javax.swing.JButton();
        addRCMButton = new javax.swing.JButton();
        JPanel rcmFunctionalityPanel = new JPanel();
        addGroupComboBox = new javax.swing.JComboBox<>();
        selectFunctionalityComboBox = new javax.swing.JComboBox<>();
        JLabel selectGroupLabel = new JLabel();
        JLabel functionsLabel = new JLabel();
        submitFunctionalitySelection = new javax.swing.JButton();
        JPanel mrcBodyPanel = new JPanel();
        addRCMPanel = new javax.swing.JPanel();
        machineIdTextField = new javax.swing.JTextField();
        locationIdTextField = new javax.swing.JTextField();
        capacityTextField = new javax.swing.JTextField();
        initialDepositTextField = new javax.swing.JTextField();
        addRCMFormButton = new javax.swing.JButton();
        rcmGroupIdComboBox = new javax.swing.JComboBox<>();
        JLabel addRCMTitle = new JLabel();
        addRCMGroupPanel = new javax.swing.JLayeredPane();
        groupIdTextField = new javax.swing.JTextField();
        groupDescriptionTextField = new javax.swing.JTextField();
        addRCMGroup = new javax.swing.JButton();
        groupNameTextField = new javax.swing.JTextField();
        JLabel addRCMGroupTitle = new JLabel();
        operationsRCMPanel = new javax.swing.JPanel();
        operationsRCMComboBox = new javax.swing.JComboBox<>();
        JLabel selectRCMOperationsLabel = new JLabel();
        JLabel rcmOperationsLabel = new JLabel();
        activateRCMButton = new javax.swing.JButton();
        deactivateRCMButton = new javax.swing.JButton();
        removeRCMButton = new javax.swing.JButton();
        JLabel activateDeactivateStatusLabel = new JLabel();
        emptyRCMButton = new javax.swing.JButton();
        rcmStatusTrackPanel = new javax.swing.JTabbedPane();
        JPanel operationalStatusRCM = new JPanel();
        JLabel operationaStatusRCMLabel = new JLabel();
        JScrollPane operationalStatusTablePanelMain = new JScrollPane();
        JScrollPane operationalStatusTablePane = new JScrollPane();
        JTable operationalStatusTable = new JTable();
        checkRCMMetrics = new javax.swing.JPanel();
        currentWeightRCMComboBox = new javax.swing.JComboBox<>();
        checkEmptiedTimeButton = new javax.swing.JButton();
        JLabel checkWeightSelectRCMLabel = new JLabel();
        JLabel displayCurrentWeightRCMLabel = new JLabel();
        checkWeightButton = new javax.swing.JButton();
        JLabel currentWeightMoneyRecycledItemsTitle = new JLabel();
        checkMoneyButton = new javax.swing.JButton();
        JPanel numberOfItemsReturnedByRCMInAMonth = new JPanel();
        JLabel selectMonthLabel = new JLabel();
        submitCheckRCMMetricsButton = new javax.swing.JButton();
        selectYearComboBox = new javax.swing.JComboBox<>();
        selectMonthComboBox = new javax.swing.JComboBox<>();
        JLabel selectYearLabel = new JLabel();
        JPanel rcmUsedMostFrequently = new JPanel();
        JLabel titleMostFrequent = new JLabel();
        JLabel labelMostFrequent = new JLabel();
        numberOfDaysRCMMostUsedField = new javax.swing.JTextField();
        mostFrequentlyUsedButton = new javax.swing.JButton();
        JPanel usageStatistics = new JPanel();
        JTabbedPane displayStatisticsPanel = new JTabbedPane();
        textPanel = new javax.swing.JPanel();
        JScrollPane weightValueAndEmptyPanel = new JScrollPane();
        weightValueEmptyTable = new javax.swing.JTable();
        visualDisplayPanel = new javax.swing.JPanel();
        JTabbedPane statisticsQueries = new JTabbedPane();
        JPanel numberOfEmptyTimePanel = new JPanel();
        JLabel startTimeLabel = new JLabel();
        JLabel endTimeLabel = new JLabel();
        startTimeField = new javax.swing.JTextField();
        endTimeField = new javax.swing.JTextField();
        JLabel selectRCMLabelEmptyTime = new JLabel();
        emptyTimeComboBox = new javax.swing.JComboBox<>();
        JLabel timeEnterTitle = new JLabel();
        JButton submitEmptyTime = new JButton();
        JPanel valueSelectionPanel = new JPanel();
        JLabel selectRCMValue = new JLabel();
        selectRCMValueBox = new javax.swing.JComboBox<>();
        JButton submitValueButton = new JButton();
        JPanel weightOfRCMPerDayPerWeekPanel = new JPanel();
        JLabel selectRCMSPerDay = new JLabel();
        selectRCMWeightOfRCMStatisticsComboBox = new javax.swing.JComboBox<>();
        selectCategoryComboBox = new javax.swing.JComboBox<>();
        JLabel selectCategoryLabel = new JLabel();
        JButton jButton1 = new JButton();
        updateCapabilitiesPanel = new javax.swing.JPanel();
        JLabel titleUpdateCapabilities = new JLabel();
        JTabbedPane updateCapabilitiesTabbedPane = new JTabbedPane();
        JPanel addItemType = new JPanel();
        JLabel selectRCMLabel = new JLabel();
        rcmComboBoxAddItemType = new javax.swing.JComboBox<>();
        JLabel itemTypeLabel = new JLabel();
        JLabel priceLabel = new JLabel();
        JLabel weightLabel = new JLabel();
        priceTextField = new javax.swing.JTextField();
        weightTextField = new javax.swing.JTextField();
        JButton addItemTypeButton = new JButton();
        itemTypeSelectionComboBoxaddItem = new javax.swing.JComboBox<>();
        JScrollPane itemTypesDisplayPanelA = new JScrollPane();
        JTable itemTypesDisplayTableA = new JTable();
        JPanel changeItemType = new JPanel();
        JLabel selectRCMChangeItemTypeLabel = new JLabel();
        changeItemTypeRcmComboBox = new javax.swing.JComboBox<>();
        JLabel itemTypeSelectLabelChange = new JLabel();
        itemTypeSelectComboBoxChange = new javax.swing.JComboBox<>();
        JLabel priceLabelChange = new JLabel();
        JLabel weightChangeLabel = new JLabel();
        priceChangeField = new javax.swing.JTextField();
        weightChangeField = new javax.swing.JTextField();
        JButton changeButton = new JButton();
        JScrollPane itemTypesDisplayPanelC = new JScrollPane();
        JTable itemTypesDisplayTableC = new JTable();
        welcomePanel = new javax.swing.JPanel();
        JLabel welcomeLabel = new JLabel();
        JPanel outputPanel = new JPanel();
        outputLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("RMO Center");
        setBackground(new java.awt.Color(255, 255, 255));
        setLocationByPlatform(true);
        setMinimumSize(new java.awt.Dimension(580, 600));
        setResizable(false);
        //getContentPane().setLayout(new AbsoluteLayout());

        mainPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        mainPanel.setBounds(new java.awt.Rectangle(0, 23, 580, 600));
        mainPanel.setMaximumSize(new java.awt.Dimension(580, 600));
        mainPanel.setMinimumSize(new java.awt.Dimension(580, 600));
        mainPanel.setPreferredSize(new java.awt.Dimension(580, 600));
        mainPanel.setLayout(new java.awt.BorderLayout());

        topPanel.setBackground(new java.awt.Color(1, 87, 155));
        topPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        topPanel.setMaximumSize(new java.awt.Dimension(560, 100));
        topPanel.setMinimumSize(new java.awt.Dimension(560, 100));
        topPanel.setPreferredSize(new java.awt.Dimension(560, 100));

        ecoReLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ecoReLogo.setMaximumSize(new java.awt.Dimension(400, 90));
        ecoReLogo.setMinimumSize(new java.awt.Dimension(400, 90));
        ecoReLogo.setPreferredSize(new java.awt.Dimension(400, 90));
        ecoReLogo.setSize(new java.awt.Dimension(400, 90));

        logoutLabel.setForeground(new java.awt.Color(204, 255, 255));
        logoutLabel.setText("Logout");
        logoutLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoutLabelMouseClicked(evt);
            }
        });

        userIcon.setMaximumSize(new java.awt.Dimension(20, 20));
        userIcon.setMinimumSize(new java.awt.Dimension(20, 20));
        userIcon.setPreferredSize(new java.awt.Dimension(20, 20));
        userIcon.setSize(new java.awt.Dimension(20, 20));

        javax.swing.GroupLayout topPanelLayout = new javax.swing.GroupLayout(topPanel);
        topPanel.setLayout(topPanelLayout);
        topPanelLayout.setHorizontalGroup(
                topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(topPanelLayout.createSequentialGroup()
                                .addContainerGap(17, Short.MAX_VALUE)
                                .addComponent(ecoReLogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(77, 77, 77)
                                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(logoutLabel)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, topPanelLayout.createSequentialGroup()
                                                .addComponent(userIcon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(12, 12, 12)))
                                .addGap(18, 18, 18))
        );
        topPanelLayout.setVerticalGroup(
                topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(topPanelLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, topPanelLayout.createSequentialGroup()
                                                .addComponent(userIcon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(logoutLabel)
                                                .addGap(26, 26, 26))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, topPanelLayout.createSequentialGroup()
                                                .addComponent(ecoReLogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap())))
        );

        mainPanel.add(topPanel, java.awt.BorderLayout.PAGE_START);

        bodyPanel.setBackground(new java.awt.Color(255, 255, 255));
        bodyPanel.setMaximumSize(new java.awt.Dimension(560, 440));
        bodyPanel.setMinimumSize(new java.awt.Dimension(560, 440));
        bodyPanel.setPreferredSize(new java.awt.Dimension(560, 440));
        bodyPanel.setSize(new java.awt.Dimension(560, 440));
        bodyPanel.setLayout(new java.awt.BorderLayout());

        mrcTopPanel.setBackground(new java.awt.Color(51, 153, 255));
        mrcTopPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        mrcTopPanel.setMaximumSize(new java.awt.Dimension(560, 140));
        mrcTopPanel.setMinimumSize(new java.awt.Dimension(560, 140));
        mrcTopPanel.setPreferredSize(new java.awt.Dimension(560, 140));

        addButtonsPanel.setBackground(new java.awt.Color(51, 153, 255));
        addButtonsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Add"));
        addButtonsPanel.setMaximumSize(new java.awt.Dimension(140, 135));
        addButtonsPanel.setMinimumSize(new java.awt.Dimension(140, 135));
        addButtonsPanel.setPreferredSize(new java.awt.Dimension(140, 135));
        addButtonsPanel.setSize(new java.awt.Dimension(140, 135));

        addRCMGroupButton.setBackground(new java.awt.Color(255, 255, 255));
        addRCMGroupButton.setText("Group");
        addRCMGroupButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 204, 255), 1, true));
        addRCMGroupButton.setMaximumSize(new java.awt.Dimension(40, 20));
        addRCMGroupButton.setMinimumSize(new java.awt.Dimension(40, 20));
        addRCMGroupButton.setPreferredSize(new java.awt.Dimension(40, 20));
        addRCMGroupButton.setRequestFocusEnabled(false);
        addRCMGroupButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addRCMGroupButtonChangePanel(evt);
            }
        });

        addRCMButton.setBackground(new java.awt.Color(255, 255, 255));
        addRCMButton.setText("RCM");
        addRCMButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 204, 255), 1, true));
        addRCMButton.setMaximumSize(new java.awt.Dimension(40, 20));
        addRCMButton.setMinimumSize(new java.awt.Dimension(40, 20));
        addRCMButton.setPreferredSize(new java.awt.Dimension(40, 20));
        addRCMButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addRcmButtonClickChangePanel(evt);
            }
        });

        javax.swing.GroupLayout addButtonsPanelLayout = new javax.swing.GroupLayout(addButtonsPanel);
        addButtonsPanel.setLayout(addButtonsPanelLayout);
        addButtonsPanelLayout.setHorizontalGroup(
                addButtonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(addButtonsPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(addButtonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(addRCMGroupButton, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                                        .addComponent(addRCMButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE))
                                .addContainerGap())
        );
        addButtonsPanelLayout.setVerticalGroup(
                addButtonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(addButtonsPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(addRCMGroupButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(addRCMButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(21, Short.MAX_VALUE))
        );

        rcmFunctionalityPanel.setBackground(new java.awt.Color(51, 153, 255));
        rcmFunctionalityPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Selection Parameters"));
        rcmFunctionalityPanel.setMaximumSize(new java.awt.Dimension(420, 135));
        rcmFunctionalityPanel.setMinimumSize(new java.awt.Dimension(420, 135));
        rcmFunctionalityPanel.setPreferredSize(new java.awt.Dimension(420, 135));
        rcmFunctionalityPanel.setSize(new java.awt.Dimension(420, 135));

        addGroupComboBox.setMaximumSize(new java.awt.Dimension(160, 27));
        addGroupComboBox.setMinimumSize(new java.awt.Dimension(160, 27));
        addGroupComboBox.setPreferredSize(new java.awt.Dimension(160, 27));

        selectFunctionalityComboBox.setModel(RCMFunctionsComboBoxModel);
        selectFunctionalityComboBox.setMaximumSize(new java.awt.Dimension(160, 27));
        selectFunctionalityComboBox.setMinimumSize(new java.awt.Dimension(160, 27));
        selectFunctionalityComboBox.setName("");
        selectFunctionalityComboBox.setPreferredSize(new java.awt.Dimension(160, 27));
        selectGroupLabel.setText("Select Group");

        functionsLabel.setText("Function(s)");

        submitFunctionalitySelection.setBackground(new java.awt.Color(255, 255, 255));
        submitFunctionalitySelection.setText("Submit ");
        submitFunctionalitySelection.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 204, 255), 1, true));
        submitFunctionalitySelection.setMaximumSize(new java.awt.Dimension(40, 20));
        submitFunctionalitySelection.setMinimumSize(new java.awt.Dimension(40, 20));
        submitFunctionalitySelection.setPreferredSize(new java.awt.Dimension(40, 20));
        submitFunctionalitySelection.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                submitFunctionalitySelectionMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout rcmFunctionalitiesPanelLayout = new javax.swing.GroupLayout(rcmFunctionalityPanel);
        rcmFunctionalityPanel.setLayout(rcmFunctionalitiesPanelLayout);
        rcmFunctionalitiesPanelLayout.setHorizontalGroup(
                rcmFunctionalitiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(rcmFunctionalitiesPanelLayout.createSequentialGroup()
                                .addGroup(rcmFunctionalitiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(rcmFunctionalitiesPanelLayout.createSequentialGroup()
                                                .addGap(36, 36, 36)
                                                .addGroup(rcmFunctionalitiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(addGroupComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(selectGroupLabel))
                                                .addGap(57, 57, 57)
                                                .addGroup(rcmFunctionalitiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(functionsLabel)
                                                        .addComponent(selectFunctionalityComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(rcmFunctionalitiesPanelLayout.createSequentialGroup()
                                                .addGap(116, 116, 116)
                                                .addComponent(submitFunctionalitySelection, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(41, Short.MAX_VALUE))
        );
        rcmFunctionalitiesPanelLayout.setVerticalGroup(
                rcmFunctionalitiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(rcmFunctionalitiesPanelLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(rcmFunctionalitiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(functionsLabel)
                                        .addComponent(selectGroupLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(rcmFunctionalitiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(addGroupComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(selectFunctionalityComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(submitFunctionalitySelection, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8))
        );

        javax.swing.GroupLayout mrcTopPanelLayout = new javax.swing.GroupLayout(mrcTopPanel);
        mrcTopPanel.setLayout(mrcTopPanelLayout);
        mrcTopPanelLayout.setHorizontalGroup(
                mrcTopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(mrcTopPanelLayout.createSequentialGroup()
                                .addComponent(addButtonsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rcmFunctionalityPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        mrcTopPanelLayout.setVerticalGroup(
                mrcTopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mrcTopPanelLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(mrcTopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(rcmFunctionalityPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(addButtonsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(4, 4, 4))
        );

        bodyPanel.add(mrcTopPanel, java.awt.BorderLayout.NORTH);

        mrcBodyPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        mrcBodyPanel.setMaximumSize(new java.awt.Dimension(560, 300));
        mrcBodyPanel.setMinimumSize(new java.awt.Dimension(560, 300));
        mrcBodyPanel.setPreferredSize(new java.awt.Dimension(560, 300));
        mrcBodyPanel.setSize(new java.awt.Dimension(560, 300));
        mrcBodyPanel.setLayout(new java.awt.CardLayout());

        addRCMPanel.setBackground(new java.awt.Color(102, 204, 255));
        addRCMPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        addRCMPanel.setMaximumSize(new java.awt.Dimension(550, 290));
        addRCMPanel.setMinimumSize(new java.awt.Dimension(550, 290));
        addRCMPanel.setPreferredSize(new java.awt.Dimension(550, 290));

        machineIdTextField.setBackground(new java.awt.Color(102, 204, 255));
        machineIdTextField.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true), "Machine Id"));
        machineIdTextField.setMaximumSize(new java.awt.Dimension(150, 30));
        machineIdTextField.setMinimumSize(new java.awt.Dimension(150, 30));
        machineIdTextField.setSize(new java.awt.Dimension(150, 30));

        locationIdTextField.setBackground(new java.awt.Color(102, 204, 255));
        locationIdTextField.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true), "Location Id"));
        locationIdTextField.setMaximumSize(new java.awt.Dimension(150, 30));
        locationIdTextField.setMinimumSize(new java.awt.Dimension(150, 30));
        locationIdTextField.setPreferredSize(new java.awt.Dimension(150, 30));
        locationIdTextField.setSize(new java.awt.Dimension(150, 30));

        capacityTextField.setBackground(new java.awt.Color(102, 204, 255));
        capacityTextField.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true), "Capacity (lbs)"));
        capacityTextField.setMaximumSize(new java.awt.Dimension(150, 30));
        capacityTextField.setMinimumSize(new java.awt.Dimension(150, 30));
        capacityTextField.setPreferredSize(new java.awt.Dimension(150, 30));
        capacityTextField.addActionListener(evt -> capacityTextFieldActionPerformed(evt));

        initialDepositTextField.setBackground(new java.awt.Color(102, 204, 255));
        initialDepositTextField.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true), "Initial Price (USD)"));
        initialDepositTextField.setMaximumSize(new java.awt.Dimension(150, 30));
        initialDepositTextField.setMinimumSize(new java.awt.Dimension(150, 30));
        initialDepositTextField.setPreferredSize(new java.awt.Dimension(150, 30));

        addRCMFormButton.setBackground(new java.awt.Color(255, 255, 255));
        addRCMFormButton.setText("Submit");
        addRCMFormButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 204, 255), 1, true));
        addRCMFormButton.setMaximumSize(new java.awt.Dimension(100, 40));
        addRCMFormButton.setMinimumSize(new java.awt.Dimension(100, 40));
        addRCMFormButton.setPreferredSize(new java.awt.Dimension(100, 40));
        addRCMFormButton.setSize(new java.awt.Dimension(100, 40));
        addRCMFormButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addRCMFormButtonFormButtonMouseClickEvent(evt);
            }
        });

        rcmGroupIdComboBox.setBackground(new java.awt.Color(102, 204, 255));
        rcmGroupIdComboBox.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true), "Group Id"));
        rcmGroupIdComboBox.setMaximumSize(new java.awt.Dimension(150, 30));
        rcmGroupIdComboBox.setMinimumSize(new java.awt.Dimension(150, 30));
        rcmGroupIdComboBox.setPreferredSize(new java.awt.Dimension(150, 30));

        addRCMTitle.setFont(new java.awt.Font("Comic Sans MS", Font.BOLD, 13)); // NOI18N
        addRCMTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        addRCMTitle.setText("Add RCM ");
        addRCMTitle.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout addRCMPanelLayout = new javax.swing.GroupLayout(addRCMPanel);
        addRCMPanel.setLayout(addRCMPanelLayout);
        addRCMPanelLayout.setHorizontalGroup(
                addRCMPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(addRCMPanelLayout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(addRCMPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(rcmGroupIdComboBox, 0, 216, Short.MAX_VALUE)
                                        .addComponent(machineIdTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(addRCMPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(capacityTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                                        .addComponent(locationIdTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(addRCMPanelLayout.createSequentialGroup()
                                .addGroup(addRCMPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(addRCMPanelLayout.createSequentialGroup()
                                                .addGap(158, 158, 158)
                                                .addComponent(addRCMTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(addRCMPanelLayout.createSequentialGroup()
                                                .addGap(175, 175, 175)
                                                .addComponent(initialDepositTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(addRCMPanelLayout.createSequentialGroup()
                                                .addGap(211, 211, 211)
                                                .addComponent(addRCMFormButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(181, Short.MAX_VALUE))
        );
        addRCMPanelLayout.setVerticalGroup(
                addRCMPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(addRCMPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(addRCMTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(addRCMPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(machineIdTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(locationIdTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(addRCMPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(rcmGroupIdComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(capacityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(initialDepositTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(addRCMFormButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(24, Short.MAX_VALUE))
        );

        mrcBodyPanel.add(addRCMPanel, "card4");

        addRCMGroupPanel.setBackground(new java.awt.Color(102, 204, 255));
        addRCMGroupPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        addRCMGroupPanel.setForeground(new java.awt.Color(51, 204, 255));
        addRCMGroupPanel.setMaximumSize(new java.awt.Dimension(560, 300));
        addRCMGroupPanel.setMinimumSize(new java.awt.Dimension(560, 300));
        addRCMGroupPanel.setOpaque(true);

        groupIdTextField.setBackground(new java.awt.Color(102, 204, 255));
        groupIdTextField.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true), "Group Id"));
        groupIdTextField.setMaximumSize(new java.awt.Dimension(150, 40));
        groupIdTextField.setMinimumSize(new java.awt.Dimension(150, 40));
        groupIdTextField.setPreferredSize(new java.awt.Dimension(150, 40));
        groupIdTextField.setSize(new java.awt.Dimension(150, 40));

        groupDescriptionTextField.setBackground(new java.awt.Color(102, 204, 255));
        groupDescriptionTextField.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true), "Description"));
        groupDescriptionTextField.setMaximumSize(new java.awt.Dimension(150, 40));
        groupDescriptionTextField.setMinimumSize(new java.awt.Dimension(150, 40));
        groupDescriptionTextField.setPreferredSize(new java.awt.Dimension(150, 40));
        groupDescriptionTextField.setSize(new java.awt.Dimension(150, 40));


        addRCMGroup.setBackground(new java.awt.Color(255, 255, 255));
        addRCMGroup.setText("Submit");
        addRCMGroup.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 204, 255), 1, true));
        addRCMGroup.setMaximumSize(new java.awt.Dimension(100, 40));
        addRCMGroup.setMinimumSize(new java.awt.Dimension(100, 40));
        addRCMGroup.setPreferredSize(new java.awt.Dimension(100, 40));
        addRCMGroup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addRCMGroupFormButtonMouseClickEvent(evt);
            }
        });

        groupNameTextField.setBackground(new java.awt.Color(102, 204, 255));
        groupNameTextField.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true), "Group Name"));
        groupNameTextField.setMaximumSize(new java.awt.Dimension(150, 40));
        groupNameTextField.setMinimumSize(new java.awt.Dimension(150, 40));
        groupNameTextField.setPreferredSize(new java.awt.Dimension(150, 40));
        groupNameTextField.setSize(new java.awt.Dimension(150, 40));


        addRCMGroupTitle.setFont(new java.awt.Font("Comic Sans MS", Font.BOLD, 13)); // NOI18N
        addRCMGroupTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        addRCMGroupTitle.setText("Add RCM Group");
        addRCMGroupTitle.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        addRCMGroupPanel.setLayer(groupIdTextField, javax.swing.JLayeredPane.DEFAULT_LAYER);
        addRCMGroupPanel.setLayer(groupDescriptionTextField, javax.swing.JLayeredPane.DEFAULT_LAYER);
        addRCMGroupPanel.setLayer(addRCMGroup, javax.swing.JLayeredPane.DEFAULT_LAYER);
        addRCMGroupPanel.setLayer(groupNameTextField, javax.swing.JLayeredPane.DEFAULT_LAYER);
        addRCMGroupPanel.setLayer(addRCMGroupTitle, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout addRCMGroupPanelLayout = new javax.swing.GroupLayout(addRCMGroupPanel);
        addRCMGroupPanel.setLayout(addRCMGroupPanelLayout);
        addRCMGroupPanelLayout.setHorizontalGroup(
                addRCMGroupPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(addRCMGroupPanelLayout.createSequentialGroup()
                                .addGroup(addRCMGroupPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(addRCMGroupPanelLayout.createSequentialGroup()
                                                .addGap(204, 204, 204)
                                                .addComponent(addRCMGroup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(addRCMGroupPanelLayout.createSequentialGroup()
                                                .addGap(146, 146, 146)
                                                .addGroup(addRCMGroupPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(addRCMGroupTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(addRCMGroupPanelLayout.createSequentialGroup()
                                                                .addGap(6, 6, 6)
                                                                .addGroup(addRCMGroupPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(groupIdTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                                                        .addComponent(groupNameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(groupDescriptionTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                                .addContainerGap(198, Short.MAX_VALUE))
        );
        addRCMGroupPanelLayout.setVerticalGroup(
                addRCMGroupPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(addRCMGroupPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(addRCMGroupTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(groupIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(groupNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(groupDescriptionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(addRCMGroup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(38, Short.MAX_VALUE))
        );

        mrcBodyPanel.add(addRCMGroupPanel, "card3");

        operationsRCMPanel.setBackground(new java.awt.Color(102, 204, 255));
        operationsRCMPanel.setMaximumSize(new java.awt.Dimension(550, 290));
        operationsRCMPanel.setMinimumSize(new java.awt.Dimension(550, 290));
        operationsRCMPanel.setPreferredSize(new java.awt.Dimension(550, 290));

        operationsRCMComboBox.setMaximumSize(new java.awt.Dimension(150, 30));
        operationsRCMComboBox.setMinimumSize(new java.awt.Dimension(150, 30));
        operationsRCMComboBox.setPreferredSize(new java.awt.Dimension(150, 30));

        selectRCMOperationsLabel.setText("Select an RCM ");

        rcmOperationsLabel.setFont(new java.awt.Font("Comic Sans MS", Font.BOLD, 14)); // NOI18N
        rcmOperationsLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rcmOperationsLabel.setText("RCM OPERATIONS");
        rcmOperationsLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        activateRCMButton.setBackground(new java.awt.Color(255, 255, 255));
        activateRCMButton.setText("Activate");
        activateRCMButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 204, 255), 1, true));
        activateRCMButton.setMaximumSize(new java.awt.Dimension(100, 40));
        activateRCMButton.setMinimumSize(new java.awt.Dimension(100, 40));
        activateRCMButton.setPreferredSize(new java.awt.Dimension(100, 40));
        activateRCMButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                activateMouseClicked(evt);
            }
        });

        deactivateRCMButton.setBackground(new java.awt.Color(255, 255, 255));
        deactivateRCMButton.setText("Deactivate");
        deactivateRCMButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 204, 255), 1, true));
        deactivateRCMButton.setMaximumSize(new java.awt.Dimension(100, 40));
        deactivateRCMButton.setMinimumSize(new java.awt.Dimension(100, 40));
        deactivateRCMButton.setPreferredSize(new java.awt.Dimension(100, 40));
        deactivateRCMButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deactivateButtonClick(evt);
            }
        });


        removeRCMButton.setBackground(new java.awt.Color(250, 177, 160));
        removeRCMButton.setText("Remove");
        removeRCMButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 204, 255), 1, true));
        removeRCMButton.setMaximumSize(new java.awt.Dimension(100, 40));
        removeRCMButton.setMinimumSize(new java.awt.Dimension(100, 40));
        removeRCMButton.setPreferredSize(new java.awt.Dimension(100, 40));
        removeRCMButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                removeButtonClick(evt);
            }
        });


        emptyRCMButton.setBackground(new java.awt.Color(255, 255, 255));
        emptyRCMButton.setText("Empty RCM");
        emptyRCMButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 204, 255), 1, true));
        emptyRCMButton.setMaximumSize(new java.awt.Dimension(120, 40));
        emptyRCMButton.setMinimumSize(new java.awt.Dimension(120, 40));
        emptyRCMButton.setPreferredSize(new java.awt.Dimension(120, 40));
        emptyRCMButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                emptyButtonClick(evt);
            }
        });


        javax.swing.GroupLayout operationsRCMPanelLayout = new javax.swing.GroupLayout(operationsRCMPanel);
        operationsRCMPanel.setLayout(operationsRCMPanelLayout);
        operationsRCMPanelLayout.setHorizontalGroup(
                operationsRCMPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(operationsRCMPanelLayout.createSequentialGroup()
                                .addGap(140, 140, 140)
                                .addComponent(rcmOperationsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(operationsRCMPanelLayout.createSequentialGroup()
                                .addGap(179, 179, 179)
                                .addComponent(operationsRCMComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(operationsRCMPanelLayout.createSequentialGroup()
                                .addGap(82, 82, 82)
                                .addComponent(activateRCMButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(operationsRCMPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(operationsRCMPanelLayout.createSequentialGroup()
                                                .addComponent(deactivateRCMButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(31, 31, 31)
                                                .addComponent(emptyRCMButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(operationsRCMPanelLayout.createSequentialGroup()
                                                .addComponent(removeRCMButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(224, 224, 224)
                                                .addComponent(activateDeactivateStatusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(operationsRCMPanelLayout.createSequentialGroup()
                                .addGap(202, 202, 202)
                                .addComponent(selectRCMOperationsLabel))
        );
        operationsRCMPanelLayout.setVerticalGroup(
                operationsRCMPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(operationsRCMPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(rcmOperationsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(selectRCMOperationsLabel)
                                .addGap(18, 18, 18)
                                .addComponent(operationsRCMComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(operationsRCMPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(operationsRCMPanelLayout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(activateRCMButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(operationsRCMPanelLayout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addGroup(operationsRCMPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(deactivateRCMButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(emptyRCMButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addGap(18, 18, Short.MAX_VALUE)
                                .addGroup(operationsRCMPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(activateDeactivateStatusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(removeRCMButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(37, 37, 37))
        );

        mrcBodyPanel.add(operationsRCMPanel, "card5");

        rcmStatusTrackPanel.setBackground(new java.awt.Color(102, 204, 255));
        rcmStatusTrackPanel.setMaximumSize(new java.awt.Dimension(550, 290));
        rcmStatusTrackPanel.setMinimumSize(new java.awt.Dimension(550, 290));
        rcmStatusTrackPanel.setPreferredSize(new java.awt.Dimension(550, 290));

        operationalStatusRCM.setBackground(new java.awt.Color(102, 204, 255));
        operationalStatusRCM.setMaximumSize(new java.awt.Dimension(560, 300));
        operationalStatusRCM.setMinimumSize(new java.awt.Dimension(560, 300));
        operationalStatusRCM.setPreferredSize(new java.awt.Dimension(560, 300));

        operationaStatusRCMLabel.setFont(new java.awt.Font("Comic Sans MS", Font.BOLD, 13)); // NOI18N
        operationaStatusRCMLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        operationaStatusRCMLabel.setText("Operational Status of all RCMS");
        operationaStatusRCMLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        operationalStatusTablePanelMain.setMaximumSize(new java.awt.Dimension(520, 160));
        operationalStatusTablePanelMain.setMinimumSize(new java.awt.Dimension(520, 160));
        operationalStatusTablePanelMain.setPreferredSize(new java.awt.Dimension(520, 160));
        operationalStatusTablePanelMain.setSize(new java.awt.Dimension(520, 160));

        operationalStatusTablePane.setMaximumSize(new java.awt.Dimension(520, 160));
        operationalStatusTablePane.setMinimumSize(new java.awt.Dimension(520, 160));
        operationalStatusTablePane.setPreferredSize(new java.awt.Dimension(520, 160));

        operationalStatusTable.setModel(operationalStatusTableModel);
        operationalStatusTable.setMaximumSize(new java.awt.Dimension(520, 160));
        operationalStatusTable.setMinimumSize(new java.awt.Dimension(520, 160));
        operationalStatusTable.setPreferredSize(new java.awt.Dimension(520, 160));
        operationalStatusTable.setSize(new java.awt.Dimension(520, 160));
        operationalStatusTable.setSurrendersFocusOnKeystroke(true);
        operationalStatusTablePane.setViewportView(operationalStatusTable);

        operationalStatusTablePanelMain.setViewportView(operationalStatusTablePane);

        javax.swing.GroupLayout operationalStatusRCMLayout = new javax.swing.GroupLayout(operationalStatusRCM);
        operationalStatusRCM.setLayout(operationalStatusRCMLayout);
        operationalStatusRCMLayout.setHorizontalGroup(
                operationalStatusRCMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(operationalStatusRCMLayout.createSequentialGroup()
                                .addGap(118, 118, 118)
                                .addComponent(operationaStatusRCMLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(operationalStatusRCMLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(operationalStatusTablePanelMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        operationalStatusRCMLayout.setVerticalGroup(
                operationalStatusRCMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(operationalStatusRCMLayout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(operationaStatusRCMLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(operationalStatusTablePanelMain, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(17, Short.MAX_VALUE))
        );

        rcmStatusTrackPanel.addTab("Operational Status", operationalStatusRCM);

        checkRCMMetrics.setBackground(new java.awt.Color(102, 204, 255));
        checkRCMMetrics.setMaximumSize(new java.awt.Dimension(300, 300));
        checkRCMMetrics.setMinimumSize(new java.awt.Dimension(300, 300));
        checkRCMMetrics.setPreferredSize(new java.awt.Dimension(300, 300));

        currentWeightRCMComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{" "}));


        checkEmptiedTimeButton.setBackground(new java.awt.Color(255, 255, 255));
        checkEmptiedTimeButton.setText("Check Emptied Time");
        checkEmptiedTimeButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 204, 255), 1, true));
        checkEmptiedTimeButton.setMaximumSize(new java.awt.Dimension(180, 40));
        checkEmptiedTimeButton.setMinimumSize(new java.awt.Dimension(180, 40));
        checkEmptiedTimeButton.setPreferredSize(new java.awt.Dimension(180, 40));
        checkEmptiedTimeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkLastEmptiedTimeButton(evt);
            }
        });

        checkWeightSelectRCMLabel.setText("Select RCM ");

        checkWeightButton.setBackground(new java.awt.Color(255, 255, 255));
        checkWeightButton.setText("Check Weight");
        checkWeightButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 204, 255), 1, true));
        checkWeightButton.setMaximumSize(new java.awt.Dimension(120, 40));
        checkWeightButton.setMinimumSize(new java.awt.Dimension(120, 40));
        checkWeightButton.setPreferredSize(new java.awt.Dimension(120, 40));
        checkWeightButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkWeightButtonClicked(evt);
            }
        });

        currentWeightMoneyRecycledItemsTitle.setFont(new java.awt.Font("Comic Sans MS", Font.BOLD, 13)); // NOI18N
        currentWeightMoneyRecycledItemsTitle.setText("Check current Weight Money and Number of recycled Items");
        currentWeightMoneyRecycledItemsTitle.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        checkMoneyButton.setBackground(new java.awt.Color(255, 255, 255));
        checkMoneyButton.setText("Check Money");
        checkMoneyButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 204, 255), 1, true));
        checkMoneyButton.setMaximumSize(new java.awt.Dimension(120, 40));
        checkMoneyButton.setMinimumSize(new java.awt.Dimension(120, 40));
        checkMoneyButton.setPreferredSize(new java.awt.Dimension(120, 40));
        checkMoneyButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkMoneyButtonClicked(evt);
            }
        });
        checkMoneyButton.addActionListener(evt -> checkMoneyButtonActionPerformed(evt));

        numberOfItemsReturnedByRCMInAMonth.setBackground(new java.awt.Color(102, 204, 255));
        numberOfItemsReturnedByRCMInAMonth.setBorder(javax.swing.BorderFactory.createTitledBorder("#Recycled Items"));
        numberOfItemsReturnedByRCMInAMonth.setMaximumSize(new java.awt.Dimension(500, 100));
        numberOfItemsReturnedByRCMInAMonth.setPreferredSize(new java.awt.Dimension(500, 100));
        numberOfItemsReturnedByRCMInAMonth.setSize(new java.awt.Dimension(500, 100));

        selectMonthLabel.setText("Select a Month::");

        submitCheckRCMMetricsButton.setBackground(new java.awt.Color(255, 255, 255));
        submitCheckRCMMetricsButton.setText("Submit");
        submitCheckRCMMetricsButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 204, 255), 1, true));
        submitCheckRCMMetricsButton.setMaximumSize(new java.awt.Dimension(100, 40));
        submitCheckRCMMetricsButton.setMinimumSize(new java.awt.Dimension(100, 40));
        submitCheckRCMMetricsButton.setPreferredSize(new java.awt.Dimension(100, 40));
        submitCheckRCMMetricsButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                recycledItemsButtonClicked(evt);
            }
        });

        selectYearComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"--Year--"}));
        selectYearComboBox.setMaximumSize(new java.awt.Dimension(150, 30));
        selectYearComboBox.setMinimumSize(new java.awt.Dimension(150, 30));
        selectYearComboBox.setPreferredSize(new java.awt.Dimension(150, 30));

        selectMonthComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"--Month--", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"}));
        selectMonthComboBox.setMaximumSize(new java.awt.Dimension(150, 30));
        selectMonthComboBox.setMinimumSize(new java.awt.Dimension(150, 30));
        selectMonthComboBox.setPreferredSize(new java.awt.Dimension(150, 30));

        selectYearLabel.setText("Select an Year::");

        javax.swing.GroupLayout numberOfItemsReturnedByRCMInAMonthLayout = new javax.swing.GroupLayout(numberOfItemsReturnedByRCMInAMonth);
        numberOfItemsReturnedByRCMInAMonth.setLayout(numberOfItemsReturnedByRCMInAMonthLayout);
        numberOfItemsReturnedByRCMInAMonthLayout.setHorizontalGroup(
                numberOfItemsReturnedByRCMInAMonthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(numberOfItemsReturnedByRCMInAMonthLayout.createSequentialGroup()
                                .addGroup(numberOfItemsReturnedByRCMInAMonthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(numberOfItemsReturnedByRCMInAMonthLayout.createSequentialGroup()
                                                .addComponent(selectYearLabel)
                                                .addGap(23, 23, 23))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, numberOfItemsReturnedByRCMInAMonthLayout.createSequentialGroup()
                                                .addComponent(selectMonthLabel)
                                                .addGap(18, 18, 18)))
                                .addGroup(numberOfItemsReturnedByRCMInAMonthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(selectMonthComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(selectYearComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
                                .addComponent(submitCheckRCMMetricsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(54, 54, 54))
        );
        numberOfItemsReturnedByRCMInAMonthLayout.setVerticalGroup(
                numberOfItemsReturnedByRCMInAMonthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(numberOfItemsReturnedByRCMInAMonthLayout.createSequentialGroup()
                                .addGroup(numberOfItemsReturnedByRCMInAMonthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(numberOfItemsReturnedByRCMInAMonthLayout.createSequentialGroup()
                                                .addGap(21, 21, 21)
                                                .addComponent(submitCheckRCMMetricsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(numberOfItemsReturnedByRCMInAMonthLayout.createSequentialGroup()
                                                .addGap(10, 10, 10)
                                                .addGroup(numberOfItemsReturnedByRCMInAMonthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(selectMonthLabel)
                                                        .addComponent(selectMonthComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(numberOfItemsReturnedByRCMInAMonthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(selectYearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(selectYearLabel))))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout checkRCMMetricsLayout = new javax.swing.GroupLayout(checkRCMMetrics);
        checkRCMMetrics.setLayout(checkRCMMetricsLayout);
        checkRCMMetricsLayout.setHorizontalGroup(
                checkRCMMetricsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(checkRCMMetricsLayout.createSequentialGroup()
                                .addGap(206, 206, 206)
                                .addComponent(checkWeightSelectRCMLabel))
                        .addGroup(checkRCMMetricsLayout.createSequentialGroup()
                                .addGap(187, 187, 187)
                                .addComponent(currentWeightRCMComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(checkRCMMetricsLayout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addComponent(currentWeightMoneyRecycledItemsTitle))
                        .addGroup(checkRCMMetricsLayout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(checkRCMMetricsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(checkRCMMetricsLayout.createSequentialGroup()
                                                .addComponent(checkWeightButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(56, 56, 56)
                                                .addComponent(checkMoneyButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(33, 33, 33)
                                                .addComponent(checkEmptiedTimeButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(37, 37, 37)
                                                .addComponent(displayCurrentWeightRCMLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(numberOfItemsReturnedByRCMInAMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 525, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        checkRCMMetricsLayout.setVerticalGroup(
                checkRCMMetricsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(checkRCMMetricsLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(currentWeightMoneyRecycledItemsTitle)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(checkWeightSelectRCMLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(currentWeightRCMComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(checkRCMMetricsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(displayCurrentWeightRCMLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(checkRCMMetricsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(checkWeightButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(checkMoneyButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(checkEmptiedTimeButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addComponent(numberOfItemsReturnedByRCMInAMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(52, 52, 52))
        );

        rcmStatusTrackPanel.addTab("Check RCM Metrics", checkRCMMetrics);

        rcmUsedMostFrequently.setBackground(new java.awt.Color(102, 204, 255));
        rcmUsedMostFrequently.setMaximumSize(new java.awt.Dimension(300, 300));
        rcmUsedMostFrequently.setMinimumSize(new java.awt.Dimension(300, 300));
        rcmUsedMostFrequently.setPreferredSize(new java.awt.Dimension(300, 300));

        titleMostFrequent.setFont(new java.awt.Font("Comic Sans MS", Font.BOLD, 13)); // NOI18N
        titleMostFrequent.setText("RCM used most frequently in last N days");

        labelMostFrequent.setText("Number of days (N)");

        mostFrequentlyUsedButton.setBackground(new java.awt.Color(255, 255, 255));
        mostFrequentlyUsedButton.setText("Submit");
        mostFrequentlyUsedButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 204, 255), 1, true));
        mostFrequentlyUsedButton.setMaximumSize(new java.awt.Dimension(100, 40));
        mostFrequentlyUsedButton.setMinimumSize(new java.awt.Dimension(100, 40));
        mostFrequentlyUsedButton.setPreferredSize(new java.awt.Dimension(100, 40));
        mostFrequentlyUsedButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mostFrequentRCMButtonClick(evt);
            }
        });

        javax.swing.GroupLayout rcmUsedMostFrequentlyLayout = new javax.swing.GroupLayout(rcmUsedMostFrequently);
        rcmUsedMostFrequently.setLayout(rcmUsedMostFrequentlyLayout);
        rcmUsedMostFrequentlyLayout.setHorizontalGroup(
                rcmUsedMostFrequentlyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(rcmUsedMostFrequentlyLayout.createSequentialGroup()
                                .addGroup(rcmUsedMostFrequentlyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(rcmUsedMostFrequentlyLayout.createSequentialGroup()
                                                .addGap(113, 113, 113)
                                                .addComponent(labelMostFrequent)
                                                .addGap(31, 31, 31)
                                                .addComponent(numberOfDaysRCMMostUsedField, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(rcmUsedMostFrequentlyLayout.createSequentialGroup()
                                                .addGap(141, 141, 141)
                                                .addComponent(titleMostFrequent))
                                        .addGroup(rcmUsedMostFrequentlyLayout.createSequentialGroup()
                                                .addGap(205, 205, 205)
                                                .addComponent(mostFrequentlyUsedButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        rcmUsedMostFrequentlyLayout.setVerticalGroup(
                rcmUsedMostFrequentlyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(rcmUsedMostFrequentlyLayout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(titleMostFrequent)
                                .addGap(32, 32, 32)
                                .addGroup(rcmUsedMostFrequentlyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(labelMostFrequent)
                                        .addComponent(numberOfDaysRCMMostUsedField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(28, 28, 28)
                                .addComponent(mostFrequentlyUsedButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(140, Short.MAX_VALUE))
        );

        rcmStatusTrackPanel.addTab("RCM Most Used", rcmUsedMostFrequently);

        usageStatistics.setBackground(new java.awt.Color(102, 204, 255));
        usageStatistics.setMaximumSize(new java.awt.Dimension(560, 300));
        usageStatistics.setMinimumSize(new java.awt.Dimension(560, 300));
        usageStatistics.setPreferredSize(new java.awt.Dimension(560, 300));

        displayStatisticsPanel.setMaximumSize(new java.awt.Dimension(270, 200));
        displayStatisticsPanel.setMinimumSize(new java.awt.Dimension(270, 200));
        displayStatisticsPanel.setPreferredSize(new java.awt.Dimension(270, 200));
        displayStatisticsPanel.setSize(new java.awt.Dimension(270, 200));

        textPanel.setMaximumSize(new java.awt.Dimension(270, 200));
        textPanel.setMinimumSize(new java.awt.Dimension(270, 200));
        textPanel.setPreferredSize(new java.awt.Dimension(270, 200));
        textPanel.setLayout(new java.awt.CardLayout(2, 2));

        weightValueAndEmptyPanel.setMaximumSize(new java.awt.Dimension(150, 64));
        weightValueAndEmptyPanel.setMinimumSize(new java.awt.Dimension(150, 64));
        weightValueAndEmptyPanel.setPreferredSize(new java.awt.Dimension(150, 64));

        weightValueEmptyTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                        {},
                        {},
                        {},
                        {}
                },
                new String[]{

                }
        ));
        weightValueEmptyTable.setMaximumSize(new java.awt.Dimension(150, 64));
        weightValueEmptyTable.setMinimumSize(new java.awt.Dimension(150, 64));
        weightValueEmptyTable.setSize(new java.awt.Dimension(150, 64));
        weightValueAndEmptyPanel.setViewportView(weightValueEmptyTable);

        textPanel.add(weightValueAndEmptyPanel, "card2");

        displayStatisticsPanel.addTab("Text", textPanel);

        visualDisplayPanel.setMaximumSize(new java.awt.Dimension(270, 200));
        visualDisplayPanel.setMinimumSize(new java.awt.Dimension(270, 200));
        visualDisplayPanel.setPreferredSize(new java.awt.Dimension(270, 200));
        visualDisplayPanel.setLayout(new java.awt.BorderLayout());
        displayStatisticsPanel.addTab("Visual Display", visualDisplayPanel);

        statisticsQueries.setMaximumSize(new java.awt.Dimension(270, 200));
        statisticsQueries.setMinimumSize(new java.awt.Dimension(270, 200));
        statisticsQueries.setPreferredSize(new java.awt.Dimension(270, 200));
        statisticsQueries.setSize(new java.awt.Dimension(270, 200));

        numberOfEmptyTimePanel.setMaximumSize(new java.awt.Dimension(249, 160));
        numberOfEmptyTimePanel.setMinimumSize(new java.awt.Dimension(249, 160));
        numberOfEmptyTimePanel.setPreferredSize(new java.awt.Dimension(249, 160));

        startTimeLabel.setText("Start Date");

        endTimeLabel.setText("End Date");

        startTimeField.setMaximumSize(new java.awt.Dimension(150, 30));
        startTimeField.setMinimumSize(new java.awt.Dimension(150, 30));
        startTimeField.setPreferredSize(new java.awt.Dimension(150, 30));
        startTimeField.setSize(new java.awt.Dimension(150, 30));

        endTimeField.setMaximumSize(new java.awt.Dimension(150, 30));
        endTimeField.setMinimumSize(new java.awt.Dimension(150, 30));
        endTimeField.setPreferredSize(new java.awt.Dimension(150, 30));
        endTimeField.setSize(new java.awt.Dimension(150, 30));

        selectRCMLabelEmptyTime.setText("Select RCM:");

        emptyTimeComboBox.setMaximumSize(new java.awt.Dimension(150, 30));
        emptyTimeComboBox.setMinimumSize(new java.awt.Dimension(150, 30));
        emptyTimeComboBox.setPreferredSize(new java.awt.Dimension(150, 30));

        timeEnterTitle.setText("Date Format: (dd-MMM-yyy)");

        submitEmptyTime.setBackground(new java.awt.Color(255, 255, 255));
        submitEmptyTime.setText("submit");
        submitEmptyTime.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 204, 255), 1, true));
        submitEmptyTime.setMaximumSize(new java.awt.Dimension(100, 40));
        submitEmptyTime.setMinimumSize(new java.awt.Dimension(100, 40));
        submitEmptyTime.setPreferredSize(new java.awt.Dimension(100, 40));
        submitEmptyTime.setSize(new java.awt.Dimension(100, 40));
        submitEmptyTime.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                emptyTimeButtonClick(evt);
            }
        });

        javax.swing.GroupLayout numberOfEmptyTimePanelLayout = new javax.swing.GroupLayout(numberOfEmptyTimePanel);
        numberOfEmptyTimePanel.setLayout(numberOfEmptyTimePanelLayout);
        numberOfEmptyTimePanelLayout.setHorizontalGroup(
                numberOfEmptyTimePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(numberOfEmptyTimePanelLayout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addGroup(numberOfEmptyTimePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(numberOfEmptyTimePanelLayout.createSequentialGroup()
                                                .addComponent(selectRCMLabelEmptyTime)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(emptyTimeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(numberOfEmptyTimePanelLayout.createSequentialGroup()
                                                .addGroup(numberOfEmptyTimePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(endTimeLabel)
                                                        .addComponent(startTimeLabel))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(numberOfEmptyTimePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(startTimeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(endTimeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(submitEmptyTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(numberOfEmptyTimePanelLayout.createSequentialGroup()
                                                .addGap(22, 22, 22)
                                                .addComponent(timeEnterTitle)))
                                .addContainerGap(7, Short.MAX_VALUE))
        );
        numberOfEmptyTimePanelLayout.setVerticalGroup(
                numberOfEmptyTimePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(numberOfEmptyTimePanelLayout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addGroup(numberOfEmptyTimePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(selectRCMLabelEmptyTime)
                                        .addComponent(emptyTimeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12)
                                .addComponent(timeEnterTitle)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(numberOfEmptyTimePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(startTimeLabel)
                                        .addComponent(startTimeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(5, 5, 5)
                                .addGroup(numberOfEmptyTimePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(endTimeLabel)
                                        .addComponent(endTimeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(submitEmptyTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(21, Short.MAX_VALUE))
        );

        statisticsQueries.addTab("No of Emptied Times", numberOfEmptyTimePanel);

        valueSelectionPanel.setBackground(new java.awt.Color(102, 204, 255));
        valueSelectionPanel.setMaximumSize(new java.awt.Dimension(249, 160));
        valueSelectionPanel.setMinimumSize(new java.awt.Dimension(249, 160));
        valueSelectionPanel.setSize(new java.awt.Dimension(249, 160));

        selectRCMValue.setText("Select RCM");

        submitValueButton.setBackground(new java.awt.Color(255, 255, 255));
        submitValueButton.setText("submit");
        submitValueButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 204, 255), 1, true));
        submitValueButton.setMaximumSize(new java.awt.Dimension(100, 40));
        submitValueButton.setMinimumSize(new java.awt.Dimension(100, 40));
        submitValueButton.setPreferredSize(new java.awt.Dimension(100, 40));
        submitValueButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                submitValueButtonClick(evt);
            }
        });

        javax.swing.GroupLayout valueSelectionPanelLayout = new javax.swing.GroupLayout(valueSelectionPanel);
        valueSelectionPanel.setLayout(valueSelectionPanelLayout);
        valueSelectionPanelLayout.setHorizontalGroup(
                valueSelectionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(valueSelectionPanelLayout.createSequentialGroup()
                                .addGap(81, 81, 81)
                                .addComponent(selectRCMValue)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, valueSelectionPanelLayout.createSequentialGroup()
                                .addGap(0, 60, Short.MAX_VALUE)
                                .addComponent(selectRCMValueBox, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(59, 59, 59))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, valueSelectionPanelLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(submitValueButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(80, 80, 80))
        );
        valueSelectionPanelLayout.setVerticalGroup(
                valueSelectionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(valueSelectionPanelLayout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(selectRCMValue)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(selectRCMValueBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(submitValueButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(69, Short.MAX_VALUE))
        );

        statisticsQueries.addTab("Total Value Issued", valueSelectionPanel);

        weightOfRCMPerDayPerWeekPanel.setBackground(new java.awt.Color(102, 204, 255));
        weightOfRCMPerDayPerWeekPanel.setMaximumSize(new java.awt.Dimension(276, 160));
        weightOfRCMPerDayPerWeekPanel.setMinimumSize(new java.awt.Dimension(276, 160));

        selectRCMSPerDay.setText("Select RCM");

        selectRCMWeightOfRCMStatisticsComboBox.setMaximumSize(new java.awt.Dimension(120, 40));
        selectRCMWeightOfRCMStatisticsComboBox.setMinimumSize(new java.awt.Dimension(120, 40));
        selectRCMWeightOfRCMStatisticsComboBox.setPreferredSize(new java.awt.Dimension(120, 40));
        selectRCMWeightOfRCMStatisticsComboBox.setSize(new java.awt.Dimension(120, 40));

        selectCategoryComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"perDay", "perWeek", " "}));
        selectCategoryComboBox.setMaximumSize(new java.awt.Dimension(120, 40));
        selectCategoryComboBox.setMinimumSize(new java.awt.Dimension(120, 40));
        selectCategoryComboBox.setPreferredSize(new java.awt.Dimension(120, 40));
        selectCategoryComboBox.setSize(new java.awt.Dimension(120, 40));

        selectCategoryLabel.setText("Select Category");

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Submit");
        jButton1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 204, 255), 1, true));
        jButton1.setMaximumSize(new java.awt.Dimension(100, 40));
        jButton1.setMinimumSize(new java.awt.Dimension(100, 40));
        jButton1.setPreferredSize(new java.awt.Dimension(100, 40));
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                weightSubmitButtonClick(evt);
            }
        });

        javax.swing.GroupLayout weightOfRCMPerDayPerWeekPanelLayout = new javax.swing.GroupLayout(weightOfRCMPerDayPerWeekPanel);
        weightOfRCMPerDayPerWeekPanel.setLayout(weightOfRCMPerDayPerWeekPanelLayout);
        weightOfRCMPerDayPerWeekPanelLayout.setHorizontalGroup(
                weightOfRCMPerDayPerWeekPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(weightOfRCMPerDayPerWeekPanelLayout.createSequentialGroup()
                                .addGroup(weightOfRCMPerDayPerWeekPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(weightOfRCMPerDayPerWeekPanelLayout.createSequentialGroup()
                                                .addGap(21, 21, 21)
                                                .addGroup(weightOfRCMPerDayPerWeekPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(selectCategoryLabel)
                                                        .addComponent(selectRCMSPerDay))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(weightOfRCMPerDayPerWeekPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(selectCategoryComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(selectRCMWeightOfRCMStatisticsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(weightOfRCMPerDayPerWeekPanelLayout.createSequentialGroup()
                                                .addGap(74, 74, 74)
                                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))
        );
        weightOfRCMPerDayPerWeekPanelLayout.setVerticalGroup(
                weightOfRCMPerDayPerWeekPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(weightOfRCMPerDayPerWeekPanelLayout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addGroup(weightOfRCMPerDayPerWeekPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(selectRCMWeightOfRCMStatisticsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(selectRCMSPerDay))
                                .addGap(28, 28, 28)
                                .addGroup(weightOfRCMPerDayPerWeekPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(selectCategoryLabel)
                                        .addComponent(selectCategoryComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(8, Short.MAX_VALUE))
        );

        statisticsQueries.addTab("Total Weight Of RCM", weightOfRCMPerDayPerWeekPanel);

        javax.swing.GroupLayout usageStatisticsLayout = new javax.swing.GroupLayout(usageStatistics);
        usageStatistics.setLayout(usageStatisticsLayout);
        usageStatisticsLayout.setHorizontalGroup(
                usageStatisticsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(usageStatisticsLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(displayStatisticsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(statisticsQueries, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(76, 76, 76))
        );
        usageStatisticsLayout.setVerticalGroup(
                usageStatisticsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(usageStatisticsLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(usageStatisticsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(displayStatisticsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
                                        .addComponent(statisticsQueries, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 41, Short.MAX_VALUE))
        );

        displayStatisticsPanel.getAccessibleContext().setAccessibleName("Text\n");

        rcmStatusTrackPanel.addTab("Statistics", usageStatistics);

        mrcBodyPanel.add(rcmStatusTrackPanel, "card6");

        updateCapabilitiesPanel.setBackground(new java.awt.Color(102, 204, 255));
        updateCapabilitiesPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("View"));
        updateCapabilitiesPanel.setMaximumSize(new java.awt.Dimension(550, 290));
        updateCapabilitiesPanel.setMinimumSize(new java.awt.Dimension(550, 290));
        updateCapabilitiesPanel.setPreferredSize(new java.awt.Dimension(550, 290));

        titleUpdateCapabilities.setFont(new java.awt.Font("Comic Sans MS", Font.BOLD, 13)); // NOI18N
        titleUpdateCapabilities.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleUpdateCapabilities.setText("Update RCM Capabilities");
        titleUpdateCapabilities.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        updateCapabilitiesTabbedPane.setBackground(new java.awt.Color(102, 204, 255));
        updateCapabilitiesTabbedPane.setMaximumSize(new java.awt.Dimension(300, 300));
        updateCapabilitiesTabbedPane.setMinimumSize(new java.awt.Dimension(300, 300));
        updateCapabilitiesTabbedPane.setPreferredSize(new java.awt.Dimension(500, 200));
        updateCapabilitiesTabbedPane.setSize(new java.awt.Dimension(300, 300));

        addItemType.setBackground(new java.awt.Color(102, 204, 255));
        addItemType.setMaximumSize(new java.awt.Dimension(300, 300));
        addItemType.setMinimumSize(new java.awt.Dimension(300, 300));
        addItemType.setPreferredSize(new java.awt.Dimension(300, 300));

        selectRCMLabel.setText("Select RCM");

        rcmComboBoxAddItemType.setMaximumSize(new java.awt.Dimension(150, 30));
        rcmComboBoxAddItemType.setMinimumSize(new java.awt.Dimension(150, 30));
        rcmComboBoxAddItemType.setPreferredSize(new java.awt.Dimension(150, 30));
        rcmComboBoxAddItemType.addItemListener(evt -> rcmComboBoxAddItemTypeItemStateChanged(evt));
        rcmComboBoxAddItemType.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                showTable(evt);
            }
        });
        rcmComboBoxAddItemType.addActionListener(evt -> rcmComboBoxAddItemTypeActionPerformed(evt));

        itemTypeLabel.setText("Item Type");

        priceLabel.setText("Price (USD)");

        weightLabel.setText("Weight(in lbs)");

        priceTextField.setMaximumSize(new java.awt.Dimension(150, 30));
        priceTextField.setMinimumSize(new java.awt.Dimension(150, 30));
        priceTextField.setPreferredSize(new java.awt.Dimension(150, 30));
        priceTextField.addActionListener(evt -> priceTextFieldActionPerformed(evt));

        weightTextField.setMaximumSize(new java.awt.Dimension(150, 30));
        weightTextField.setMinimumSize(new java.awt.Dimension(150, 30));
        weightTextField.setPreferredSize(new java.awt.Dimension(150, 30));

        addItemTypeButton.setText("Submit");
        addItemTypeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addItemTypeButton(evt);
            }
        });
        addItemTypeButton.addActionListener(evt -> addItemTypeButtonActionPerformed(evt));

        itemTypeSelectionComboBoxaddItem.setMaximumSize(new java.awt.Dimension(150, 30));
        itemTypeSelectionComboBoxaddItem.setMinimumSize(new java.awt.Dimension(150, 30));
        itemTypeSelectionComboBoxaddItem.setPreferredSize(new java.awt.Dimension(150, 30));
        itemTypeSelectionComboBoxaddItem.setSize(new java.awt.Dimension(150, 30));
        itemTypeSelectionComboBoxaddItem.addActionListener(evt -> itemTypeSelectionComboBoxaddItemActionPerformed(evt));

        itemTypesDisplayTableA.setModel(showItemTypeTableModel);
        itemTypesDisplayPanelA.setViewportView(itemTypesDisplayTableA);

        javax.swing.GroupLayout addItemTypeLayout = new javax.swing.GroupLayout(addItemType);
        addItemType.setLayout(addItemTypeLayout);
        addItemTypeLayout.setHorizontalGroup(
                addItemTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(addItemTypeLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(itemTypesDisplayPanelA, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                                .addGroup(addItemTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(addItemTypeLayout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(addItemTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(weightLabel)
                                                        .addComponent(priceLabel)
                                                        .addComponent(itemTypeLabel)
                                                        .addComponent(selectRCMLabel))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(addItemTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(weightTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(addItemTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                .addComponent(itemTypeSelectionComboBoxaddItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(priceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(rcmComboBoxAddItemType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addContainerGap(33, Short.MAX_VALUE))
                                        .addGroup(addItemTypeLayout.createSequentialGroup()
                                                .addGap(67, 67, 67)
                                                .addComponent(addItemTypeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))))
        );
        addItemTypeLayout.setVerticalGroup(
                addItemTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(addItemTypeLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(itemTypesDisplayPanelA, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(addItemTypeLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(addItemTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(rcmComboBoxAddItemType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(selectRCMLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(addItemTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(itemTypeSelectionComboBoxaddItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(itemTypeLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(addItemTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(priceLabel)
                                        .addComponent(priceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(addItemTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(weightLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(weightTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(addItemTypeButton)
                                .addGap(114, 114, 114))
        );

        updateCapabilitiesTabbedPane.addTab("Add Item Type", addItemType);

        changeItemType.setBackground(new java.awt.Color(102, 204, 255));

        selectRCMChangeItemTypeLabel.setText("Select RCM");

        changeItemTypeRcmComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"--Select--"}));
        changeItemTypeRcmComboBox.setMaximumSize(new java.awt.Dimension(150, 30));
        changeItemTypeRcmComboBox.setMinimumSize(new java.awt.Dimension(150, 30));
        changeItemTypeRcmComboBox.setPreferredSize(new java.awt.Dimension(150, 30));
        changeItemTypeRcmComboBox.addItemListener(evt -> changeItemTypeRcmComboBoxItemStateChanged(evt));
        changeItemTypeRcmComboBox.addActionListener(evt -> changeItemTypeRcmComboBoxActionPerformed(evt));

        itemTypeSelectLabelChange.setText("Select Item Type");

        itemTypeSelectComboBoxChange.setMaximumSize(new java.awt.Dimension(150, 30));
        itemTypeSelectComboBoxChange.setMinimumSize(new java.awt.Dimension(150, 30));
        itemTypeSelectComboBoxChange.setPreferredSize(new java.awt.Dimension(150, 30));
        itemTypeSelectComboBoxChange.setSize(new java.awt.Dimension(150, 30));
        itemTypeSelectComboBoxChange.addActionListener(evt -> itemTypeSelectComboBoxChangeActionPerformed(evt));

        priceLabelChange.setText("Price (USD)");

        weightChangeLabel.setText("Weight (lbs)");

        weightChangeField.addActionListener(evt -> weightChangeFieldActionPerformed(evt));

        changeButton.setText("Submit");
        changeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                changeItemTypeButton(evt);
            }
        });

        itemTypesDisplayTableC.setModel(showItemTypeTableModel);
        itemTypesDisplayPanelC.setViewportView(itemTypesDisplayTableC);

        javax.swing.GroupLayout changeItemTypeLayout = new javax.swing.GroupLayout(changeItemType);
        changeItemType.setLayout(changeItemTypeLayout);
        changeItemTypeLayout.setHorizontalGroup(
                changeItemTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(changeItemTypeLayout.createSequentialGroup()
                                .addGroup(changeItemTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(changeItemTypeLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(itemTypeSelectComboBoxChange, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(changeItemTypeLayout.createSequentialGroup()
                                                .addGap(20, 20, 20)
                                                .addComponent(itemTypeSelectLabelChange))
                                        .addGroup(changeItemTypeLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(changeItemTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(selectRCMChangeItemTypeLabel)
                                                        .addComponent(changeItemTypeRcmComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(92, 92, 92)
                                                .addGroup(changeItemTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(priceLabelChange)
                                                        .addComponent(priceChangeField, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(weightChangeLabel)
                                                        .addComponent(weightChangeField, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(changeItemTypeLayout.createSequentialGroup()
                                                .addGap(44, 44, 44)
                                                .addComponent(changeButton)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(itemTypesDisplayPanelC, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                                .addGap(22, 22, 22))
        );
        changeItemTypeLayout.setVerticalGroup(
                changeItemTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(changeItemTypeLayout.createSequentialGroup()
                                .addGroup(changeItemTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(changeItemTypeLayout.createSequentialGroup()
                                                .addGap(21, 21, 21)
                                                .addGroup(changeItemTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(priceLabelChange)
                                                        .addComponent(selectRCMChangeItemTypeLabel))
                                                .addGap(9, 9, 9)
                                                .addGroup(changeItemTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(priceChangeField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(changeItemTypeRcmComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(changeItemTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(itemTypeSelectLabelChange)
                                                        .addComponent(weightChangeLabel))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(changeItemTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(itemTypeSelectComboBoxChange, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(weightChangeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(changeButton))
                                        .addGroup(changeItemTypeLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(itemTypesDisplayPanelC, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(16, Short.MAX_VALUE))
        );

        updateCapabilitiesTabbedPane.addTab("Change Item Type", changeItemType);

        javax.swing.GroupLayout updateCapabilitiesPanelLayout = new javax.swing.GroupLayout(updateCapabilitiesPanel);
        updateCapabilitiesPanel.setLayout(updateCapabilitiesPanelLayout);
        updateCapabilitiesPanelLayout.setHorizontalGroup(
                updateCapabilitiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(updateCapabilitiesTabbedPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE)
                        .addGroup(updateCapabilitiesPanelLayout.createSequentialGroup()
                                .addGap(140, 140, 140)
                                .addComponent(titleUpdateCapabilities, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        updateCapabilitiesPanelLayout.setVerticalGroup(
                updateCapabilitiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(updateCapabilitiesPanelLayout.createSequentialGroup()
                                .addComponent(titleUpdateCapabilities)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(updateCapabilitiesTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 243, Short.MAX_VALUE))
        );

        mrcBodyPanel.add(updateCapabilitiesPanel, "card6");

        welcomePanel.setBackground(new java.awt.Color(102, 204, 255));
        welcomePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("View"));
        welcomePanel.setMaximumSize(new java.awt.Dimension(560, 100));
        welcomePanel.setMinimumSize(new java.awt.Dimension(560, 100));
        welcomePanel.setPreferredSize(new java.awt.Dimension(560, 100));

        welcomeLabel.setFont(new java.awt.Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 24)); // NOI18N
        welcomeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        welcomeLabel.setText("Welcome to RMOS");
        welcomeLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout welcomePanelLayout = new javax.swing.GroupLayout(welcomePanel);
        welcomePanel.setLayout(welcomePanelLayout);
        welcomePanelLayout.setHorizontalGroup(
                welcomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(welcomePanelLayout.createSequentialGroup()
                                .addGap(124, 124, 124)
                                .addComponent(welcomeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(167, Short.MAX_VALUE))
        );
        welcomePanelLayout.setVerticalGroup(
                welcomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(welcomePanelLayout.createSequentialGroup()
                                .addGap(77, 77, 77)
                                .addComponent(welcomeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mrcBodyPanel.add(welcomePanel, "card7");

        bodyPanel.add(mrcBodyPanel, java.awt.BorderLayout.CENTER);

        mainPanel.add(bodyPanel, java.awt.BorderLayout.CENTER);

        outputPanel.setBackground(new java.awt.Color(1, 87, 155));
        outputPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Message", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", Font.PLAIN, 13), new java.awt.Color(204, 255, 255))); // NOI18N
        outputPanel.setMaximumSize(new java.awt.Dimension(560, 60));
        outputPanel.setMinimumSize(new java.awt.Dimension(560, 60));
        outputPanel.setPreferredSize(new java.awt.Dimension(560, 60));

        outputLabel.setForeground(new java.awt.Color(204, 255, 255));

        javax.swing.GroupLayout outputPanelLayout = new javax.swing.GroupLayout(outputPanel);
        outputPanel.setLayout(outputPanelLayout);
        outputPanelLayout.setHorizontalGroup(
                outputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 564, Short.MAX_VALUE)
                        .addGroup(outputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(outputPanelLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(outputLabel)
                                        .addGap(0, 0, Short.MAX_VALUE)))
        );
        outputPanelLayout.setVerticalGroup(
                outputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 36, Short.MAX_VALUE)
                        .addGroup(outputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(outputPanelLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(outputLabel)
                                        .addGap(0, 0, Short.MAX_VALUE)))
        );

        mainPanel.add(outputPanel, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(mainPanel);

        pack();
    }

    private void logoutLabelMouseClicked(java.awt.event.MouseEvent evt) {
        new RMOLoginGUI().setVisible(true);
        this.setVisible(false);
    }

    private void addRCMGroupButtonChangePanel(java.awt.event.MouseEvent evt) {
        // HIDE WELCOME PANEL ADD RCM GROUP VISIBLE
        welcomePanel.setVisible(false);
        addRCMGroupButton.setBorder(new BevelBorder(BevelBorder.LOWERED));
        addRCMButton.setBorder(new BevelBorder(BevelBorder.RAISED));
        hideAllPanels();
        addRCMGroupPanel.setVisible(true);


    }

    private void addRcmButtonClickChangePanel(java.awt.event.MouseEvent evt) {
        // HIDE WELCOME PANEL ADD RCM PANEL VISIBLE
        welcomePanel.setVisible(false);
        addRCMButton.setBorder(new BevelBorder(BevelBorder.LOWERED));
        addRCMGroupButton.setBorder(new BevelBorder(BevelBorder.RAISED));
        hideAllPanels();
        addRCMPanel.setVisible(true);
        rcmGroupIdComboBox.removeAllItems();
        guiRmosHelper.getGroupIdList().forEach(m -> rcmGroupIdComboBox.addItem(m));
    }

    private void addRCMGroupFormButtonMouseClickEvent(java.awt.event.MouseEvent evt) {

        Response res = guiRmosHelper.addRCMGroup(outputLabel, groupIdTextField, groupNameTextField, groupDescriptionTextField);
        if (res.getStatus().equals(ResponseStatus.SUCCESS)) {
            initGroupDropDown();
            guiRmosHelper.setMessageForLabel(outputLabel, "RCM Group Added succesfully ");
            groupIdTextField.setText("");
            groupNameTextField.setText("");
            groupDescriptionTextField.setText("");
        } else {
            guiRmosHelper.setErrorForLabel(outputLabel, res.getMessage());
        }
    }

    private void submitFunctionalitySelectionMouseClicked(java.awt.event.MouseEvent evt) {

        selectedGroupId = getSelectedItem(addGroupComboBox);
        welcomePanel.setVisible(false);
        hideAllPanels();
        if (selectFunctionalityComboBox.getSelectedItem() == RCM_OPS) {
            operationsRCMComboBox.removeAllItems();
            guiRmosHelper.getListImageNText(operationsRCMComboBox, selectedGroupId);
            guiRmosHelper.getRCMMachineIdList(selectedGroupId).forEach(m -> currentWeightRCMComboBox.addItem(m));
            checkRCMMetrics.setVisible(true);
            operationsRCMPanel.setVisible(true);

        } else if (selectFunctionalityComboBox.getSelectedItem() == RCM_METRICS) {

            //updating operational status tables
            //GUIRmosHelper.populateItemTypeTable(outputLabel, operationalStatusTableModel, selectedGroupId);
            operationalStatusTableModel.setRowCount(0);
            guiRmosHelper.populateRCMTable(operationalStatusTableModel, selectedGroupId);

            for (int years = 2010; years <= Calendar.getInstance().get(Calendar.YEAR); years++) {
                selectYearComboBox.addItem(years + "");
            }

            //clearing combo boxes
            currentWeightRCMComboBox.removeAllItems();
            selectRCMWeightOfRCMStatisticsComboBox.removeAllItems();
            selectRCMValueBox.removeAllItems();
            emptyTimeComboBox.removeAllItems();

            // updating combo boxes again
            guiRmosHelper.getRCMMachineIdList(selectedGroupId).forEach(m -> currentWeightRCMComboBox.addItem(m));
            guiRmosHelper.getRCMMachineIdList(selectedGroupId).forEach(m -> selectRCMWeightOfRCMStatisticsComboBox.addItem(m));
            guiRmosHelper.getRCMMachineIdList(selectedGroupId).forEach(m -> selectRCMValueBox.addItem(m));
            guiRmosHelper.getRCMMachineIdList(selectedGroupId).forEach(m -> emptyTimeComboBox.addItem(m));
            rcmStatusTrackPanel.setVisible(true);
            textPanel.setVisible(true);
            visualDisplayPanel.removeAll();

        } else if (selectFunctionalityComboBox.getSelectedItem() == UPDATE_RCM_CAPABILITIES) {
            updateCapabilitiesPanel.setVisible(true);
            rcmComboBoxAddItemType.removeAllItems();
            itemTypeSelectionComboBoxaddItem.removeAllItems();
            changeItemTypeRcmComboBox.removeAllItems();
            itemTypeSelectComboBoxChange.removeAllItems();
            guiRmosHelper.getRCMMachineIdList(selectedGroupId).forEach(m -> changeItemTypeRcmComboBox.addItem(m));
            guiRmosHelper.getRCMMachineIdList(selectedGroupId).forEach(m -> rcmComboBoxAddItemType.addItem(m));
            guiRmosHelper.populateItemTypeCB(outputLabel, getSelectedItem(changeItemTypeRcmComboBox), itemTypeSelectComboBoxChange);
            ItemTypeEnum[] itemTypes = ItemTypeEnum.values();
            for (ItemTypeEnum itemType : itemTypes) {
                itemTypeSelectionComboBoxaddItem.addItem(itemType.name());
            }
        } else {
            guiRmosHelper.setErrorForLabel(outputLabel, "Please select valid function!");
            welcomePanel.setVisible(true);
        }
    }

    private void _rcmOperationsButtonClicked(StatusEnum status) {
        if (operationsRCMComboBox.getSelectedIndex() != -1) {
            ImagesNText imt = (ImagesNText) operationsRCMComboBox.getSelectedItem();
            String machineId = Objects.requireNonNull(imt).getMachineId();
            guiRmosHelper.changeRCMStatus(outputLabel, status, selectedGroupId, machineId);
            initImageNText();
        } else {
            guiRmosHelper.setErrorForLabel(outputLabel, "Please Select Machine Id");
        }
    }

    private void deactivateButtonClick(java.awt.event.MouseEvent evt) {
        _rcmOperationsButtonClicked(StatusEnum.DOWN);
    }

    private void removeButtonClick(java.awt.event.MouseEvent evt) {
        _rcmOperationsButtonClicked(StatusEnum.DELETED);
    }

    private void activateMouseClicked(java.awt.event.MouseEvent evt) {
        _rcmOperationsButtonClicked(StatusEnum.OPERATIONAL);
    }

    private void emptyButtonClick(java.awt.event.MouseEvent evt) {

        if (operationsRCMComboBox.getSelectedIndex() != -1) {
            ImagesNText imt = (ImagesNText) operationsRCMComboBox.getSelectedItem();
            String id = Objects.requireNonNull(imt).getMachineId();
            guiRmosHelper.emptyRCM(outputLabel, id, selectedGroupId);
            initImageNText();
        } else {
            guiRmosHelper.setErrorForLabel(outputLabel, "Please Select Machine Id");
        }
    }

    private void checkWeightButtonClicked(java.awt.event.MouseEvent evt) {
        String machineId = (String) currentWeightRCMComboBox.getSelectedItem();
        guiRmosHelper.getRCMCheckWeight(outputLabel, machineId);
    }

    private void checkMoneyButtonClicked(java.awt.event.MouseEvent evt) {
        String machineId = (String) currentWeightRCMComboBox.getSelectedItem();
        guiRmosHelper.getRCMCheckMoney(outputLabel, machineId);
    }

    private void checkLastEmptiedTimeButton(java.awt.event.MouseEvent evt) {
        String machineId = (String) currentWeightRCMComboBox.getSelectedItem();
        guiRmosHelper.getLastEmptiedTime(outputLabel, machineId);
    }

    private void recycledItemsButtonClicked(java.awt.event.MouseEvent evt) {
        String machineId = (String) currentWeightRCMComboBox.getSelectedItem();
        guiRmosHelper.getNumberOfRecycledItems(outputLabel, selectMonthComboBox, selectYearComboBox, machineId);
    }

    private void mostFrequentRCMButtonClick(java.awt.event.MouseEvent evt) {
        guiRmosHelper.getMostFrequentlyUsedRCMInNDays(outputLabel, numberOfDaysRCMMostUsedField, selectedGroupId);
    }


    private void weightSubmitButtonClick(java.awt.event.MouseEvent evt) {
        String machineId;
        try {
            machineId = Objects.requireNonNull(selectRCMWeightOfRCMStatisticsComboBox.getSelectedItem()).toString();

            if (Objects.equals(selectCategoryComboBox.getSelectedItem(), "perDay")) {
                visualDisplayPanel.removeAll();
                DefaultCategoryDataset dataset;
                dataset = guiRmosHelper.getDataSetWeightByDay(machineId);
                barChartData = ChartFactory.createBarChart("", "day", "total weight", dataset, PlotOrientation.VERTICAL, true,
                        false,
                        false);
                barPanel = new ChartPanel(barChartData);
                visualDisplayPanel.add(barPanel, BorderLayout.CENTER);
                visualDisplayPanel.validate();
                guiRmosHelper.getDataTextWeightByDay(weightValueEmptyTable, machineId);
            } else if (Objects.equals(selectCategoryComboBox.getSelectedItem(), "perWeek")) {
                visualDisplayPanel.removeAll();
                DefaultCategoryDataset dataset;
                dataset = guiRmosHelper.getDataSetWeightByWeek(machineId);
                barChartData = ChartFactory.createBarChart("", "week", "total weight", dataset, PlotOrientation.VERTICAL, true,
                        false,
                        false);
                barPanel = new ChartPanel(barChartData);
                visualDisplayPanel.add(barPanel, BorderLayout.CENTER);
                visualDisplayPanel.validate();
                guiRmosHelper.getDataTextWeightByWeek(weightValueEmptyTable, machineId);

            }
        } catch (Exception ex) {
            guiRmosHelper.setErrorForLabel(outputLabel, "Select machineId");
        }


    }

    private void addRCMFormButtonFormButtonMouseClickEvent(java.awt.event.MouseEvent evt) {

        Response res = guiRmosHelper.addRCM(outputLabel, machineIdTextField, locationIdTextField, rcmGroupIdComboBox, capacityTextField, initialDepositTextField);
        if (res.getStatus().equals(ResponseStatus.SUCCESS)) {
            machineIdTextField.setText("");
            locationIdTextField.setText("");
            capacityTextField.setText("");
            rcmGroupIdComboBox.setSelectedIndex(0);
            guiRmosHelper.setMessageForLabel(outputLabel, "RCM  Added succesfully ");
        } else {
            guiRmosHelper.setErrorForLabel(outputLabel, res.getMessage());
        }
    }

    private void capacityTextFieldActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void submitValueButtonClick(java.awt.event.MouseEvent evt) {
        visualDisplayPanel.removeAll();
        String machineId = Objects.requireNonNull(selectRCMValueBox.getSelectedItem()).toString();
        DefaultCategoryDataset dataset;
        dataset = guiRmosHelper.getDataSetValue(machineId);
        barChartData = ChartFactory.createBarChart("", "reward_type", "total_value", dataset, PlotOrientation.VERTICAL, true, false, false);
        barPanel = new ChartPanel(barChartData);
        visualDisplayPanel.add(barPanel, BorderLayout.CENTER);
        visualDisplayPanel.validate();
        guiRmosHelper.getTableValueRewardType(weightValueEmptyTable, machineId);
    }

    private void changeItemTypeButton(java.awt.event.MouseEvent evt) {

        guiRmosHelper.changeItemType(outputLabel, showItemTypeTableModel, itemTypeSelectComboBoxChange, changeItemTypeRcmComboBox, priceChangeField, weightChangeField);
        populateItemTypeTable(changeItemTypeRcmComboBox);
    }

    private void weightChangeFieldActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void itemTypeSelectionComboBoxaddItemActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void addItemTypeButton(java.awt.event.MouseEvent evt) {

        if (rcmComboBoxAddItemType.getSelectedIndex() > -1) {
            guiRmosHelper.addItemType(outputLabel, itemTypeSelectionComboBoxaddItem, priceTextField, weightTextField, rcmComboBoxAddItemType);
            populateItemTypeTable(rcmComboBoxAddItemType);
            itemTypeSelectComboBoxChange.removeAllItems();
            guiRmosHelper.populateItemTypeCB(outputLabel, getSelectedItem(changeItemTypeRcmComboBox), itemTypeSelectComboBoxChange);
        }
    }

    private void priceTextFieldActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void rcmComboBoxAddItemTypeActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void showTable(java.awt.event.MouseEvent evt) {

    }

    private void addItemTypeButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void checkMoneyButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void emptyTimeButtonClick(java.awt.event.MouseEvent evt) {
        visualDisplayPanel.removeAll();
        guiRmosHelper.getEmptyTimeTable(outputLabel, weightValueEmptyTable, startTimeField, endTimeField, emptyTimeComboBox);
        DefaultCategoryDataset dataset;
        dataset = guiRmosHelper.getDataSetEmptyTime(outputLabel, startTimeField, endTimeField, emptyTimeComboBox);
        barChartData = ChartFactory.createBarChart("", "machine id", "number of empty times", dataset, PlotOrientation.VERTICAL, true, false, false);
        barPanel = new ChartPanel(barChartData);
        visualDisplayPanel.add(barPanel, BorderLayout.CENTER);
        visualDisplayPanel.validate();
    }

    private void itemTypeSelectComboBoxChangeActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void changeItemTypeRcmComboBoxActionPerformed(java.awt.event.ActionEvent evt) {


    }

    private void changeItemTypeRcmComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {

        if (changeItemTypeRcmComboBox.getSelectedIndex() > -1) {
            populateItemTypeTable(changeItemTypeRcmComboBox);
            guiRmosHelper.populateItemTypeCB(outputLabel, getSelectedItem(changeItemTypeRcmComboBox), itemTypeSelectComboBoxChange);
        }
    }

    private void rcmComboBoxAddItemTypeItemStateChanged(java.awt.event.ItemEvent evt) {
        populateItemTypeTable(rcmComboBoxAddItemType);
    }

    //private functions
    private void resetComboBoxList() {
        addGroupComboBox.removeAllItems();
        operationsRCMComboBox.removeAllItems();
        rcmGroupIdComboBox.removeAllItems();
        currentWeightRCMComboBox.removeAllItems();
        selectYearComboBox.removeAllItems();
        rcmComboBoxAddItemType.removeAllItems();
        itemTypeSelectComboBoxChange.removeAllItems();
        changeItemTypeRcmComboBox.removeAllItems();
    }

    private void hideAllPanels() {
        addRCMGroupPanel.setVisible(false);
        addRCMPanel.setVisible(false);
        rcmStatusTrackPanel.setVisible(false);
        operationsRCMPanel.setVisible(false);
        updateCapabilitiesPanel.setVisible(false);
        outputLabel.setText(WELCOME_MESSAGE);
    }

    private String getSelectedItem(JComboBox cb) {
        if (cb.getSelectedIndex() == -1) {
            return "na";
        } else {
            return Objects.requireNonNull(cb.getSelectedItem()).toString();
        }
    }

    private void populateItemTypeTable(JComboBox cb) {
        showItemTypeTableModel.setRowCount(0);
        guiRmosHelper.populateItemTypeTable(outputLabel, showItemTypeTableModel, getSelectedItem(cb));
    }

}
