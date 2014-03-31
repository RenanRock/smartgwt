package com.smartgwt.sample.showcase.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import com.google.gwt.user.client.History;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.MultipleAppearance;
import com.smartgwt.client.types.OperatorId;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.types.TreeModelType;
import com.smartgwt.client.util.Browser;
import com.smartgwt.client.widgets.events.DrawEvent;
import com.smartgwt.client.widgets.events.DrawHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.events.ItemChangedEvent;
import com.smartgwt.client.widgets.form.events.ItemChangedHandler;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.FormItemIcon;
import com.smartgwt.client.widgets.form.fields.PickerIcon;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.SliderItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.IconClickEvent;
import com.smartgwt.client.widgets.form.fields.events.IconClickHandler;
import com.smartgwt.client.widgets.form.fields.events.KeyPressEvent;
import com.smartgwt.client.widgets.form.fields.events.KeyPressHandler;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tile.TileGrid;
import com.smartgwt.client.widgets.tile.events.RecordClickEvent;
import com.smartgwt.client.widgets.tile.events.RecordClickHandler;
import com.smartgwt.client.widgets.tree.Tree;
import com.smartgwt.client.widgets.tree.TreeNode;
import com.smartgwt.client.widgets.viewer.DetailFormatter;
import com.smartgwt.client.widgets.viewer.DetailViewerField;
import com.smartgwt.sample.showcase.client.data.CommandTreeNode;
import com.smartgwt.sample.showcase.client.data.ExplorerTreeNode;
import com.smartgwt.sample.showcase.client.data.ShowcaseData;

public class TileView extends VLayout {
    private static final ShowcaseMessages M = ShowcaseMessages.INSTANCE;

    private TileGrid tileGrid;
    private final String idSuffix = SideNavTree.ID_SUFFIX;
    private TreeNode[] showcaseData = ShowcaseData.getData(idSuffix);
    private DynamicForm filterForm;

    private TextItem searchItem;
    private SliderItem numSamplesItem;
    private CheckboxItem ascendingItem;
    private CheckboxItem disabledModeCB;

    private CheckboxItem featuredCB;
    private CheckboxItem newSamplesCB;
    private CheckboxItem comboBoxCB;
    private CheckboxItem gridsCB;
    private CheckboxItem treeCB;
    private CheckboxItem calendarCB;
    private CheckboxItem tilesCB;
    private CheckboxItem formsCB;
    private CheckboxItem layoutCB;
    private CheckboxItem windowsCB;
    private CheckboxItem tabsCB;
    private CheckboxItem sectionsCB;
    private CheckboxItem portalLayoutCB;
    private CheckboxItem buttonsCB;
    private CheckboxItem menusCB;
    private CheckboxItem toolStripCB;
    private CheckboxItem otherControlsCB;
    private CheckboxItem dataIntegrationCB;
    private CheckboxItem dragDropCB;
    private CheckboxItem basicsCB;
    private CheckboxItem drawingCB;
    private CheckboxItem effectsCB;
    // ---- OR ----
    private SelectItem categoriesItem;

    private Tree tree;

    public TileView() {
        setMargin(3);
        tree = new Tree();
        tree.setModelType(TreeModelType.PARENT);
        tree.setNameProperty("name");
        tree.setOpenProperty("isOpen");
        tree.setIdField("nodeID");
        tree.setParentIdField("parentNodeID");

        tree.setRootValue("root" + idSuffix);

        tree.setData(showcaseData);

        setMembersMargin(10);

        setWidth100();

        tileGrid = new TileGrid();
        tileGrid.setShowEdges(true);
        tileGrid.setTileWidth(140);
        tileGrid.setTileHeight(120);
        tileGrid.setWidth100();
        tileGrid.setHeight100();
        tileGrid.setShowAllRecords(true);

        tileGrid.setAutoFetchData(false);
        tileGrid.setAnimateTileChange(true);

        DetailViewerField nameField = new DetailViewerField("nodeTitle");
        nameField.setDetailFormatter(new DetailFormatter() {
            public String format(Object value, Record record, DetailViewerField field) {
                return value.toString();
            }
        });
        nameField.setCellStyle("thumbnailTitle");

        DetailViewerField iconField = new DetailViewerField("thumbnail");
        iconField.setType("image");
        iconField.setImageHeight(89);
        iconField.setImageWidth(119);
        iconField.setCellStyle("thumbnail");


        tileGrid.setFields(iconField, nameField);


        tileGrid.addRecordClickHandler(new RecordClickHandler() {
            public void onRecordClick(RecordClickEvent event) {
                Record record = event.getRecord();
                showSample(record);
            }
        });

        filterForm = new DynamicForm();
        filterForm.setBorder("1px solid #9C9C9C");
        filterForm.setNumCols(8);
        filterForm.setColWidths(16, "*", 16, "*", 16, "*", 16, "*");
        filterForm.setAutoFocus(false);
        filterForm.setPadding(5);

        addDrawHandler(new DrawHandler() {
            public void onDraw(DrawEvent event) {
                updateTiles();

            }
        });

        searchItem = new TextItem("description", M.searchTitle().asString());
        searchItem.setTitleOrientation(TitleOrientation.TOP);
        searchItem.setColSpan(2);
        searchItem.setTitleAlign(Alignment.LEFT);
        searchItem.addKeyPressHandler(new KeyPressHandler() {
            public void onKeyPress(KeyPressEvent event) {
                if("enter".equalsIgnoreCase(event.getKeyName())) {
                    updateTiles();
                }
            }
        });
        final PickerIcon findIcon = new PickerIcon(PickerIcon.SEARCH);
        final PickerIcon cancelIcon = new PickerIcon(PickerIcon.CLEAR);
        searchItem.setIcons(findIcon, cancelIcon);

        searchItem.addIconClickHandler(new IconClickHandler() {
            public void onIconClick(IconClickEvent event) {
                FormItemIcon icon = event.getIcon();
                if(icon.getSrc().equals(cancelIcon.getSrc())) {
                    filterForm.reset();
                    featuredCB.setValue(true);
                }
                updateTiles();
            }
        });

        numSamplesItem = new SliderItem("numSamples");
        numSamplesItem.setTitle(M.numSamplesTitle().asString());
        numSamplesItem.setTitleOrientation(TitleOrientation.TOP);
        numSamplesItem.setColSpan(2);
        numSamplesItem.setTitleAlign(Alignment.LEFT);
        numSamplesItem.setMinValue(1);
        // grep '^ *new ExplorerTreeNode' ShowcaseData.java | grep -o 'new [^.,]*\.Factory()' | sort | uniq | wc
        numSamplesItem.setMaxValue(320);
        numSamplesItem.setDefaultValue(100);
        numSamplesItem.setHeight(50);
        numSamplesItem.setOperator(OperatorId.LESS_THAN);

        ascendingItem = new CheckboxItem("chkSortDir");
        ascendingItem.setTitle(M.ascendingTitle().asString());

        disabledModeCB = new CheckboxItem("disabledModeCB", M.disabledModeTitle().asString());

        if (!Browser.getIsTouch()) {
            featuredCB = new CheckboxItem("featuredCB", M.featuredCategoryName().asString());
            featuredCB.setValue(true);
            newSamplesCB = new CheckboxItem("newSamplesCB", M.newSamplesCategoryName().asString());
            comboBoxCB = new CheckboxItem("comboBoxCB", M.comboBoxCategoryName().asString());
            gridsCB = new CheckboxItem("gridsCB", M.gridsCategoryName().asString());
            treeCB = new CheckboxItem("treeCB", M.treeCategoryName().asString());
            calendarCB = new CheckboxItem("calendarCB", M.calendarCategoryName().asString());
            tilesCB = new CheckboxItem("tilesCB", M.tilesCategoryName().asString());
            formsCB = new CheckboxItem("formsCB", M.formsCategoryName().asString());
            layoutCB = new CheckboxItem("layoutCB", M.layoutCategoryName().asString());
            windowsCB = new CheckboxItem("windowsCB", M.windowsCategoryName().asString());
            tabsCB = new CheckboxItem("tabsCB", M.tabsCategoryName().asString());
            sectionsCB = new CheckboxItem("accordionCB", M.sectionsCategoryName().asString());
            portalLayoutCB = new CheckboxItem("portalLayoutCB", M.portalLayoutCategoryName().asString());
            buttonsCB = new CheckboxItem("buttonsCB", M.buttonsCategoryName().asString());
            menusCB = new CheckboxItem("menusCB", M.menusCategoryName().asString());
            toolStripCB = new CheckboxItem("toolStripCB", M.toolStripCategoryName().asString());
            otherControlsCB = new CheckboxItem("otherControlsCB", M.otherControlsCategoryName().asString());
            dataIntegrationCB = new CheckboxItem("dataIntegrationCB", M.dataIntegrationCategoryName().asString());
            dragDropCB = new CheckboxItem("dragDropCB", M.dragDropCategoryName().asString());
            basicsCB = new CheckboxItem("basicsCB", M.basicsCategoryName().asString());
            drawingCB = new CheckboxItem("drawingCB", M.drawingCategoryName().asString());
            effectsCB = new CheckboxItem("effectsCB", M.effectsCategoryName().asString());
        } else {
            categoriesItem = new SelectItem("categories", M.categoriesTitle().asString());
            categoriesItem.setTitleOrientation(TitleOrientation.TOP);
            categoriesItem.setColSpan(4);
            categoriesItem.setMultiple(true);
            categoriesItem.setMultipleAppearance(MultipleAppearance.GRID);
            final LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
            valueMap.put("featured_category", M.featuredCategoryName().asString());
            valueMap.put("new_category", M.newSamplesCategoryName().asString());
            valueMap.put("combobox_category", M.comboBoxCategoryName().asString());
            valueMap.put("grid_category", M.gridsCategoryName().asString());
            valueMap.put("tree_category", M.treeCategoryName().asString());
            valueMap.put("calendar_category", M.calendarCategoryName().asString());
            valueMap.put("tiling_category", M.tilesCategoryName().asString());
            valueMap.put("form_category", M.formsCategoryName().asString());
            valueMap.put("layout_category", M.layoutCategoryName().asString());
            valueMap.put("layout_windows_category", M.windowsCategoryName().asString());
            valueMap.put("layout_tabs_category", M.tabsCategoryName().asString());
            valueMap.put("layout_sections_category", M.sectionsCategoryName().asString());
            valueMap.put("portal_layout_category", M.portalLayoutCategoryName().asString());
            valueMap.put("buttons_category", M.buttonsCategoryName().asString());
            valueMap.put("menus_category", M.menusCategoryName().asString());
            valueMap.put("toolstrip_category", M.toolStripCategoryName().asString());
            valueMap.put("controls_category", M.otherControlsCategoryName().asString());
            valueMap.put("data_integration_category", M.dataIntegrationCategoryName().asString());
            valueMap.put("effects_dd_category", M.dragDropCategoryName().asString());
            valueMap.put("basics_category", M.basicsCategoryName().asString());
            valueMap.put("drawing", M.drawingCategoryName().asString());
            valueMap.put("effects_category", M.effectsCategoryName().asString());
            categoriesItem.setValueMap(valueMap);
            categoriesItem.setDefaultValue(new String[] {"featured_category"});
        }

        final List<FormItem> filterFormItems = new ArrayList<FormItem>();
        filterFormItems.add(searchItem);
        filterFormItems.add(numSamplesItem);
        filterFormItems.add(ascendingItem);
        filterFormItems.add(disabledModeCB);
        if (!Browser.getIsTouch()) {
            filterFormItems.addAll(Arrays.asList(
                    featuredCB, newSamplesCB, comboBoxCB, gridsCB,
                    treeCB, calendarCB, tilesCB, formsCB,
                    layoutCB, windowsCB, tabsCB, sectionsCB,
                    portalLayoutCB, buttonsCB, menusCB, toolStripCB,
                    otherControlsCB, dataIntegrationCB, dragDropCB, basicsCB,
                    drawingCB, effectsCB));
        } else {
            filterFormItems.add(categoriesItem);
        }
        filterForm.setItems(filterFormItems.toArray(new FormItem[filterFormItems.size()]));

        filterForm.addItemChangedHandler(new ItemChangedHandler() {
            public void onItemChanged(ItemChangedEvent event) {
                FormItem item = event.getItem();
                if (item instanceof CheckboxItem || item instanceof SliderItem || item == categoriesItem) {
                    updateTiles();
                }
            }
        });

        addMember(filterForm);

        addMember(tileGrid);

    }

    private void updateTiles() {
        final String searchText = (String)searchItem.getValue();

        final List<String> categories = new ArrayList<String>();
        if (!Browser.getIsTouch()) {
            if (featuredCB.getValueAsBoolean()) categories.add("featured_category");
            if (newSamplesCB.getValueAsBoolean()) categories.add("new_category");
            if (comboBoxCB.getValueAsBoolean()) categories.add("combobox_category");
            if (gridsCB.getValueAsBoolean()) categories.add("grid_category");
            if (treeCB.getValueAsBoolean()) categories.add("tree_category");
            if (calendarCB.getValueAsBoolean()) categories.add("calendar_category");
            if (tilesCB.getValueAsBoolean()) categories.add("tiling_category");
            if (formsCB.getValueAsBoolean()) categories.add("form_category");
            if (layoutCB.getValueAsBoolean()) categories.add("layout_category");
            if (windowsCB.getValueAsBoolean()) categories.add("layout_windows_category");
            if (tabsCB.getValueAsBoolean()) categories.add("layout_tabs_category");
            if (sectionsCB.getValueAsBoolean()) categories.add("layout_sections_category");
            if (portalLayoutCB.getValueAsBoolean()) categories.add("portal_layout_category");
            if (buttonsCB.getValueAsBoolean()) categories.add("buttons_category");
            if (menusCB.getValueAsBoolean()) categories.add("menus_category");
            if (toolStripCB.getValueAsBoolean()) categories.add("toolstrip_category");
            if (otherControlsCB.getValueAsBoolean()) categories.add("controls_category");
            if (dataIntegrationCB.getValueAsBoolean()) categories.add("data_integration_category");
            if (dragDropCB.getValueAsBoolean()) categories.add("effects_dd_category");
            if (basicsCB.getValueAsBoolean()) categories.add("basics_category");
            if (drawingCB.getValueAsBoolean()) categories.add("drawing");
            if (effectsCB.getValueAsBoolean()) categories.add("effects_category");
        } else {
            categories.addAll(Arrays.asList(categoriesItem.getValues()));
        }

        showTiles(searchText, categories);

        Boolean sortDir = ascendingItem.getValueAsBoolean();
        tileGrid.sortByProperty("name", sortDir);
    }

    private void showSample(Record node) {
        boolean isExplorerTreeNode = node instanceof ExplorerTreeNode;
        if (node instanceof CommandTreeNode) {
            CommandTreeNode commandTreeNode = (CommandTreeNode) node;
            commandTreeNode.getCommand().execute();
        } else if (isExplorerTreeNode) {
            ExplorerTreeNode explorerTreeNode = (ExplorerTreeNode)node;
            History.newItem(explorerTreeNode.getNodeID(), true);
        }
    }

    private void showTiles(String searchText, List<String> categories) {
        final Set<TreeNode> data = new HashSet<TreeNode>();

        final int maxResults = ((Number)numSamplesItem.getValue()).intValue();

        if (searchText != null) {
            TreeNode[] children = tree.getAllNodes();
            applyFilter(tree, children, data, searchText, maxResults, true);
        } else {
            for (final String category : categories) {
                TreeNode categoryNode = tree.find("nodeID", category + idSuffix);
                TreeNode[] children = tree.getChildren(categoryNode);

                applyFilter(tree, children, data, searchText, maxResults, false);
            }
        }
        tileGrid.setData((Record[])data.toArray(new Record[data.size()]));
    }

    private void applyFilter(Tree tree, TreeNode[] children, Set data, String searchText,int maxResults, boolean skipCategories) {
        for (int i = 0; i < children.length; i++) {
            if(data.size() == maxResults) return;
            TreeNode child = children[i];
            if (!tree.hasChildren(child)) {
                if (searchText != null) {
                    searchText = searchText.toLowerCase();
                    boolean isExplorerTreeNode = child instanceof ExplorerTreeNode;
                    if (isExplorerTreeNode) {
                        ExplorerTreeNode explorerTreeNode = (ExplorerTreeNode) child;
                        //when searching through all nodes, skip the featured section to avoid duplicates
                        if(explorerTreeNode.getNodeID().contains("featured")) continue;
                        if (explorerTreeNode.getName().toLowerCase().contains(searchText)) {
                            data.add(child);
                        } else {
                            PanelFactory factory = explorerTreeNode.getFactory();
                            if (factory != null) {
                                String description = factory.getDescription();
                                if (description != null && description.toLowerCase().contains(searchText)) {
                                    data.add(child);
                                }
                            }
                        }
                    }
                } else {
                    data.add(child);
                }
            } else if(!skipCategories) {
                //skip categories when searching all nodes so that duplicates that exist in featured section and category are
                //both not included
                applyFilter(tree, tree.getChildren(child), data, searchText, maxResults, skipCategories);
            }
        }
    }
}
