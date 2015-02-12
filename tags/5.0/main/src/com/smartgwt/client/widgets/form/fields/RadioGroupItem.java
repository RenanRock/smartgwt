/*
 * Smart GWT (GWT for SmartClient)
 * Copyright 2008 and beyond, Isomorphic Software, Inc.
 *
 * Smart GWT is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License version 3
 * as published by the Free Software Foundation.  Smart GWT is also
 * available under typical commercial license terms - see
 * http://smartclient.com/license
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 */
/* sgwtgen */
 
package com.smartgwt.client.widgets.form.fields;


import com.smartgwt.client.event.*;
import com.smartgwt.client.core.*;
import com.smartgwt.client.types.*;
import com.smartgwt.client.data.*;
import com.smartgwt.client.data.events.*;
import com.smartgwt.client.rpc.*;
import com.smartgwt.client.callbacks.*;
import com.smartgwt.client.tools.*;
import com.smartgwt.client.bean.*;
import com.smartgwt.client.widgets.*;
import com.smartgwt.client.widgets.events.*;
import com.smartgwt.client.widgets.form.*;
import com.smartgwt.client.widgets.form.validator.*;
import com.smartgwt.client.widgets.form.fields.*;
import com.smartgwt.client.widgets.tile.*;
import com.smartgwt.client.widgets.tile.events.*;
import com.smartgwt.client.widgets.grid.*;
import com.smartgwt.client.widgets.grid.events.*;
import com.smartgwt.client.widgets.chart.*;
import com.smartgwt.client.widgets.layout.*;
import com.smartgwt.client.widgets.layout.events.*;
import com.smartgwt.client.widgets.menu.*;
import com.smartgwt.client.widgets.rte.*;
import com.smartgwt.client.widgets.rte.events.*;
import com.smartgwt.client.widgets.tab.*;
import com.smartgwt.client.widgets.toolbar.*;
import com.smartgwt.client.widgets.tree.*;
import com.smartgwt.client.widgets.tree.events.*;
import com.smartgwt.client.widgets.viewer.*;
import com.smartgwt.client.widgets.calendar.*;
import com.smartgwt.client.widgets.calendar.events.*;
import com.smartgwt.client.widgets.cube.*;
import com.smartgwt.client.widgets.drawing.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.Element;
import com.smartgwt.client.util.*;
import com.smartgwt.client.util.workflow.*;
import com.google.gwt.event.shared.*;
import com.google.gwt.event.shared.HasHandlers;

/**
 * FormItem that shows a set of mutually exclusive options as a group of radio buttons.
 */
@BeanFactory.FrameworkClass
public class RadioGroupItem extends FormItem {

    public static RadioGroupItem getOrCreateRef(JavaScriptObject jsObj) {

        if(jsObj == null) return null;

        RefDataClass obj = RefDataClass.getRef(jsObj);

		if(obj != null && JSOHelper.getAttribute(jsObj,"__ref")==null) {
            return com.smartgwt.client.util.ObjectFactory.createFormItem("RadioGroupItem",jsObj);

        } else
        if(obj != null) {
            obj.setJsObj(jsObj);
            return (RadioGroupItem) obj;
        } else {
            return new RadioGroupItem(jsObj);
        }
    }


    /**
     * Changes the defaults for Canvas AutoChildren named <code>autoChildName</code>.
     *
     * @param autoChildName name of an AutoChild to customize the defaults for.
     * @param defaults Canvas defaults to apply. These defaults override any existing properties
     * without destroying or wiping out non-overridden properties.
     * @see com.smartgwt.client.docs.AutoChildUsage
     */
    public static native void changeAutoChildDefaults(String autoChildName, Canvas defaults) /*-{
        $wnd.isc.RadioGroupItem.changeDefaults(autoChildName + "Defaults", defaults.@com.smartgwt.client.widgets.Canvas::getConfig()());
    }-*/;

    /**
     * Changes the defaults for FormItem AutoChildren named <code>autoChildName</code>.
     *
     * @param autoChildName name of an AutoChild to customize the defaults for.
     * @param defaults FormItem defaults to apply. These defaults override any existing properties
     * without destroying or wiping out non-overridden properties.
     * @see com.smartgwt.client.docs.AutoChildUsage
     */
    public static native void changeAutoChildDefaults(String autoChildName, FormItem defaults) /*-{
        $wnd.isc.RadioGroupItem.changeDefaults(autoChildName + "Defaults", defaults.@com.smartgwt.client.widgets.form.fields.FormItem::getJsObj()());
    }-*/;
    /**
     * Changes the defaults for DrawItem AutoChildren named <code>autoChildName</code>.
     *
     * @param autoChildName name of an AutoChild to customize the defaults for.
     * @param defaults DrawItem defaults to apply. These defaults override any existing properties
     * without destroying or wiping out non-overridden properties.
     * @see com.smartgwt.client.docs.AutoChildUsage
     */
    public static native void changeAutoChildDefaults(String autoChildName, DrawItem defaults) /*-{
        $wnd.isc.RadioGroupItem.changeDefaults(autoChildName + "Defaults", defaults.@com.smartgwt.client.widgets.drawing.DrawItem::getJsObj()());
    }-*/;

    public static native void changePickerIconDefaults(FormItemIcon defaults) /*-{
        $wnd.isc.RadioGroupItem.changeDefaults("pickerIconDefaults", defaults.@com.smartgwt.client.core.DataClass::getJsObj()());
    }-*/;

    public RadioGroupItem(){
        setAttribute("editorType", "RadioGroupItem");
    }

    public RadioGroupItem(JavaScriptObject jsObj){
        
        setJavaScriptObject(jsObj);
    }


    public RadioGroupItem(String name) {
        setName(name);
                setAttribute("editorType", "RadioGroupItem");
    }


    public RadioGroupItem(String name, String title) {
        setName(name);
		setTitle(title);
                setAttribute("editorType", "RadioGroupItem");
    }


    // ********************* Properties / Attributes ***********************
    

    /**
     * Default class used to construct {@link com.smartgwt.client.tools.EditProxy} for this component when editMode is enabled.
     *
     * @param editProxyConstructor  See {@link com.smartgwt.client.docs.SCClassName SCClassName} . Default value is "SelectItemEditProxy"
     */
    public void setEditProxyConstructor(String editProxyConstructor) {
        setAttribute("editProxyConstructor", editProxyConstructor);
    }

    /**
     * Default class used to construct {@link com.smartgwt.client.tools.EditProxy} for this component when editMode is enabled.
     *
     * @return  See {@link com.smartgwt.client.docs.SCClassName SCClassName} 
     */
    public String getEditProxyConstructor()  {
        return getAttributeAsString("editProxyConstructor");
    }
    
    

    /**
     * Base CSS class applied to the text for items within this radiogroup.
     *
     * @param textBoxStyle  See {@link com.smartgwt.client.docs.FormItemBaseStyle FormItemBaseStyle} . Default value is "labelAnchor"
     * @see com.smartgwt.client.docs.Appearance Appearance overview and related methods
     */
    public void setTextBoxStyle(String textBoxStyle) {
        setAttribute("textBoxStyle", textBoxStyle);
    }

    /**
     * Base CSS class applied to the text for items within this radiogroup.
     *
     * @return  See {@link com.smartgwt.client.docs.FormItemBaseStyle FormItemBaseStyle} 
     * @see com.smartgwt.client.docs.Appearance Appearance overview and related methods
     */
    public String getTextBoxStyle()  {
        return getAttributeAsString("textBoxStyle");
    }
    

    /**
     * True == display options vertically, false == display in a single row
     *
     * @param vertical  Default value is true
     * @see com.smartgwt.client.docs.Appearance Appearance overview and related methods
     */
    public void setVertical(Boolean vertical) {
        setAttribute("vertical", vertical);
    }

    /**
     * True == display options vertically, false == display in a single row
     *
     * @return Boolean
     * @see com.smartgwt.client.docs.Appearance Appearance overview and related methods
     */
    public Boolean getVertical()  {
        Boolean result = getAttributeAsBoolean("vertical", true);
        return result == null ? true : result;
    }
    

    /**
     * Should the text for items within this radio group wrap?
     *
     * @param wrap  Default value is null
     * @see com.smartgwt.client.docs.Appearance Appearance overview and related methods
     */
    public void setWrap(Boolean wrap) {
        setAttribute("wrap", wrap);
    }

    /**
     * Should the text for items within this radio group wrap?
     *
     * @return Boolean
     * @see com.smartgwt.client.docs.Appearance Appearance overview and related methods
     */
    public Boolean getWrap()  {
        return getAttributeAsBoolean("wrap", true);
    }
    

    // ********************* Methods ***********************

    // ********************* Static Methods ***********************

    /** 
     * Class level method to set the default properties of this class.  If set, then all
     * existing and subsequently created instances of this class will automatically have
     * default properties corresponding to
     * the properties of the class instance passed to this function.
     * This is a powerful feature that eliminates the need for users to create a separate
     * hierarchy of subclasses that only alter the default properties of this class. Can also
     * be used for skinning / styling purposes.  <P> <b>Note:</b> This method is intended for
     * setting default attributes only and will affect all instances of the underlying class
     * (including those automatically generated in JavaScript).  This method should not be used
     * to apply standard EventHandlers or override methods for a class - use a custom subclass
     * instead.  Calling this method after instances have been created can result in undefined
     * behavior, since it bypasses any setters and a class instance may have already examined 
     * a particular property and not be expecting any changes through this route.
     *
     * @param radioGroupItemProperties properties that should be used as new defaults when instances of this class are created
     */
    public static native void setDefaultProperties(RadioGroupItem radioGroupItemProperties) /*-{
    	var properties = $wnd.isc.addProperties({},radioGroupItemProperties.@com.smartgwt.client.core.RefDataClass::getJsObj()());
        @com.smartgwt.client.util.JSOHelper::cleanProperties(Lcom/google/gwt/core/client/JavaScriptObject;Z)(properties,false);
        $wnd.isc.RadioGroupItem.addProperties(properties);
    }-*/;

    // ***********************************************************


    /**
     * Return the value tracked by this form item.
     *
     * @return value of this element
     */
    public native String getValueAsString() /*-{
        var self = this.@com.smartgwt.client.core.DataClass::getJsObj()();
        var ret;
        if(self.setValue) {
             ret = self.getValue();
        } else {
            ret = self.value;
        }
        return ret == null || ret === undefined ? null : ret.toString();
    }-*/;    
    
    /**
     * Disable or Enable a specific option within this radioGroup
     * @param value value of option to disable
     * @param disabled true to disable the option, false to enable it
     */
    public native void setValueDisabled(Object value, boolean disabled) /*-{
        var self = this.@com.smartgwt.client.core.DataClass::getJsObj()();
        if (self.setValueDisabled) {
            self.setValueDisabled(value, disabled);
        } else {
            // pre-init, update the disabledValues object.
            var stringVal = value + "";
            if (self.disabledValues == null) {
                self.disabledValues = $wnd.Array.create();
            }
            if (self.disabledValues.contains(stringVal)) {
                if (!disabled) self.disabledValues.remove(stringVal);
            } else {
                if (disabled) self.disabledValues.add(stringVal);
            }
        }
        
    }-*/;
    
   /**
    * The FormItemHoverFormatter should return the HTML to display in a hover canvas when the 
    * user holds the mousepointer over a particular value in this item.
    * Return null to suppress the hover canvas altogether.
    *
    * @param hoverFormatter the hover formatter
    */
    public native void setValueHoverFormatter(FormItemHoverFormatter hoverFormatter) /*-{
        var self = this.@com.smartgwt.client.core.DataClass::getJsObj()();
        self.valueHoverHTML = $debox($entry(function(item, form) {
            var formJ = @com.smartgwt.client.widgets.form.DynamicForm::getOrCreateRef(Lcom/google/gwt/core/client/JavaScriptObject;)(form);
            var itemJ = @com.smartgwt.client.widgets.form.fields.FormItem::getOrCreateRef(Lcom/google/gwt/core/client/JavaScriptObject;)(item);
            return hoverFormatter.@com.smartgwt.client.widgets.form.FormItemHoverFormatter::getHoverHTML(Lcom/smartgwt/client/widgets/form/fields/FormItem;Lcom/smartgwt/client/widgets/form/DynamicForm;)(itemJ, formJ);
        }));
    }-*/;

    /**
     * Properties to apply to all generated items within this RadioGroup.
     * This allows you to customize the generated radio items for this item. Note that this
     * intended for simple customizations where there is no direct equivalent setting 
     * available on the RadioGroupItem itself - for example appearance settings such as 
     * {@link com.smartgwt.client.widgets.form.fields.FormItem#setShowFocused(Boolean) showFocused}. Some customizations
     * (for example attempting to set the {@link com.smartgwt.client.widgets.form.fields.FormItem#setName(String) name} 
     * for the item) are invalid and unsupported.
     * 
     * <p><b>Note : </b> This is an advanced setting.</p>
     *
     * @param itemProperties
     * @throws IllegalStateException this property cannot be changed after the component has been created
     */
    public void setItemProperties(FormItem itemProperties)  throws IllegalStateException {
    	if (isCreated()) {
    		throw new IllegalStateException("This attribute cannot be modified after the item has been created");
    	}
        setAttribute("itemProperties", itemProperties == null ? null : itemProperties.getConfig());
    }


}

