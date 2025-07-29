// Copyright © 2023 Textkernel BV. All rights reserved.
// This file is provided for use by, or on behalf of, Textkernel licensees
// within the terms of their license of Textkernel products or Textkernel customers
// within the Terms of Service pertaining to the Textkernel SaaS products.

package com.textkernel.tx.models.api.matchV2.querying.results;

public class Facet {
    public String Field;
    public String FieldLabel;
    public Datatype DataType;
    public boolean DataTypeSpecified;
    public Combinationtype CombinationType;
    public boolean CombinationTypeSpecified;
    public Guitype GuiType;
    public boolean GuiTypeSpecified;
    public Cloudtype CloudType;
    public boolean CloudTypeSpecified;
    public boolean Collapsed;
    public boolean CollapsedSpecified;
    public boolean HideFacetIfAllZero;
    public boolean HideFacetIfAllZeroSpecified;
    public boolean HideZeroCountItems;
    public boolean HideZeroCountItemsSpecified;
    public Condition DefaultCondition;
    public boolean DefaultConditionSpecified;
    public boolean HideConditionWidget;
    public boolean HideConditionWidgetSpecified;
    public boolean ReverseItemOrder;
    public boolean ReverseItemOrderSpecified;
    public FacetItem[] Items;
    public String[] Distances;
    public String DefaultDistance;
    public boolean TextInputOnFacet;
    public boolean TextInputOnFacetSpecified;
    public String Format;
    public String[] NestedFields;
    public boolean ShowOnFacet;
    public boolean ShowOnFacetSpecified;
    public boolean ShowOnWidget;
    public boolean ShowOnWidgetSpecified;
    public String GroupFieldName;
    public String GroupName;
    public Facet[] ChildFacets;
    public Facet[] SubFacets;
    public String SearchEngine;
    public int DropdownSwitcherThreshold;
    public boolean DropdownSwitcherThresholdSpecified;
    public QueryPartItemCountsMapEntry[] QueryPartItemCounts;
}
