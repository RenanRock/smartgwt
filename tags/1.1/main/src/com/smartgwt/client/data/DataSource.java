/*
 * SmartGWT (GWT for SmartClient)
 * Copyright 2008 and beyond, Isomorphic Software, Inc.
 *
 * SmartGWT is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License version 3
 * as published by the Free Software Foundation.  SmartGWT is also
 * available under typical commercial license terms - see
 * http://smartclient.com/license
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 */
 
package com.smartgwt.client.data;



import com.smartgwt.client.event.*;
import com.smartgwt.client.core.*;
import com.smartgwt.client.types.*;
import com.smartgwt.client.data.*;
import com.smartgwt.client.data.events.*;
import com.smartgwt.client.rpc.*;
import com.smartgwt.client.widgets.*;
import com.smartgwt.client.widgets.events.*;
import com.smartgwt.client.widgets.form.*;
import com.smartgwt.client.widgets.form.validator.*;
import com.smartgwt.client.widgets.form.fields.*;
import com.smartgwt.client.widgets.tile.*;
import com.smartgwt.client.widgets.tile.events.*;
import com.smartgwt.client.widgets.grid.*;
import com.smartgwt.client.widgets.grid.events.*;
import com.smartgwt.client.widgets.layout.*;
import com.smartgwt.client.widgets.menu.*;
import com.smartgwt.client.widgets.tab.*;
import com.smartgwt.client.widgets.toolbar.*;
import com.smartgwt.client.widgets.tree.*;
import com.smartgwt.client.widgets.tree.events.*;
import com.smartgwt.client.widgets.viewer.*;
import com.smartgwt.client.widgets.calendar.*;
import com.smartgwt.client.widgets.calendar.events.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.Element;
import com.smartgwt.client.util.JSOHelper;
import com.smartgwt.client.util.EnumUtil;
import com.google.gwt.event.shared.*;
import com.google.gwt.event.shared.HasHandlers;
   /**
    * A DataSource is data-provider-independant description of a set of objects&#010 that will be loaded, edited and saved within the user interface of your application.&#010 <P>&#010 Each DataSource consists of a list of {@link com.smartgwt.client.data.DataSource#getFields fields} that make up a&#010 DataSource <code>record</code>, along with {@link com.smartgwt.client.data.DataSourceField#getType type}, &#010 {@link com.smartgwt.client.data.DataSourceField#getValidators validators}, &#010 {@link com.smartgwt.client.data.DataSourceField#getForeignKey foreignKey} to other DataSources, and other&#010 metadata.&#010 <P>&#010 The abstract object description provided by a DataSource is easily mapped to a variety of&#010 backend object models and storage schemes.  The following table shows analogous terminology&#010 across systems.&#010 <table border=1 class="normal">&#010 <tr>&#010   <td>Isomorphic SmartGWT</td>&#010   <td>Relational Database</td>&#010   <td>Enterprise Java Beans (EJB)</td>&#010   <td>Entity/Relationship Modelling</td>&#010   <td>OO/UML</td>&#010   <td>XML Schema/WSDL</td>&#010   <td>LDAP</td>&#010 </tr><tr>&#010   <td>DataSource</td>&#010   <td>Table</td>&#010   <td>EJB class</td>&#010   <td>Entity</td>&#010   <td>Class</td>&#010   <td>Element Schema (ComplexType)</td>&#010   <td>Objectclass</td>&#010 </tr><tr>&#010   <td>Record</td>&#010   <td>Row</td>&#010   <td>EJB instance</td>&#010   <td>Entity instance</td>&#010   <td>Class instance/Object</td>&#010   <td>Element instance (ComplexType)</td>&#010   <td>Entry</td>&#010 </tr><tr>&#010   <td>Field</td>&#010   <td>Column</td>&#010   <td>Property</td>&#010   <td>Attribute</td>&#010   <td>Property/Attribute</td>&#010   <td>Attribute or Element (SimpleType)</td>&#010   <td>Attribute</td>&#010 </tr></table>&#010 <P>&#010 DataSources can be {@link com.smartgwt.client.docs.DataSourceDeclaration 'declared'} in either JavaScript or XML&#010 format, and can also be {@link com.smartgwt.client.docs.MetadataImport 'imported'} from existing metadata formats,&#010 including XML Schema.&#010 <P>&#010 <i>Data Binding</i> is the process by which&#010 {@link com.smartgwt.client.widgets.DataBoundComponent} can automatically configure&#010 themselves for viewing, editing and saving data described by DataSources.  DataBinding is&#010 covered in the ${isc.DocUtils.linkForDocNode('QuickStartGuide', 'QuickStart Guide')}, Chapter 6, <i>Data&#010 Binding</i>.&#010 <P>&#010 {@link com.smartgwt.client.docs.ClientServerIntegration 'Data Integration'} is the process by which a DataSource&#010 can be connected to server systems such as SQL DataBases, Java Object models, WSDL web&#010 services and other data providers.  Data Integration comes in two variants: client-side and&#010 server-side.  {@link com.smartgwt.client.docs.ServerDataIntegration 'Server-side integration'} uses the&#010 SmartGWT Java-based server to connect to data represented by Java Objects or&#010 JDBC-accessible databases.  {@link com.smartgwt.client.docs.ClientDataIntegration 'Client-side integration'}&#010 connects SmartGWT DataSources to XML, JSON or other formats accessible via HTTP.&#010 <P>&#010 DataSources have a concept of {@link com.smartgwt.client.docs.DataSourceOperations '4 core operations'} ("fetch",&#010 "add", "update" and "remove") that can be performed on the set of objects represented by a&#010 DataSource.  Once a DataSource has been integrated with your data store, databinding-capable &#010 UI components can leverage the 4 core DataSource operations to provide many complete user&#010 interactions without the need to configure how each individual component loads and saves&#010 data.&#010 <P>&#010 These interactions include {@link com.smartgwt.client.widgets.grid.ListGrid}, {@link com.smartgwt.client.widgets.tree.TreeGrid}, &#010 {@link com.smartgwt.client.widgets.viewer.DetailViewer}, {@link com.smartgwt.client.widgets.form.DynamicForm}-based &#010 {@link com.smartgwt.client.widgets.form.DynamicForm#editRecord} and {@link com.smartgwt.client.widgets.form.DynamicForm#saveData},&#010 grid-based {@link com.smartgwt.client.widgets.grid.ListGrid#getCanEdit canEdit} and {@link com.smartgwt.client.widgets.grid.ListGrid#getSaveByCell saveByCell},&#010 and custom interactions provided by ${isc.DocUtils.linkForExampleId('patternReuse')} custom&#010 databinding-capable components.

    */

public class DataSource extends BaseClass  implements com.smartgwt.client.data.events.HasHandleErrorHandlers {

    public static DataSource getOrCreateRef(JavaScriptObject jsObj) {
        if(jsObj == null) return null;
        BaseClass obj = BaseClass.getRef(jsObj);
        if(obj != null) {
            return (DataSource) obj;
        } else {
            return new DataSource(jsObj);
        }
    }


    public DataSource(){
        
    }

    public DataSource(JavaScriptObject jsObj){
        super(jsObj);
    }

    public DataSource(String dataURL) {
        setDataURL(dataURL);
        
    }

    public native JavaScriptObject create()/*-{
        var config = this.@com.smartgwt.client.core.BaseClass::getConfig()();
        return $wnd.isc.DataSource.create(config);
    }-*/;
    // ********************* Properties / Attributes ***********************

    /**
    * Whether to make this DataSource available as a global variable for convenience.
    * <p><b>Note : </b> This is an advanced setting</p>
    *
    * @param addGlobalId addGlobalId Default value is true
    * @throws IllegalStateException this property cannot be changed after the underlying component has been created
    */
    public void setAddGlobalId(Boolean addGlobalId)  throws IllegalStateException {
        setAttribute("addGlobalId", addGlobalId, false);
    }
    /**
     * Whether to make this DataSource available as a global variable for convenience.
     *
     *
     * @return Boolean
     *
     */
    public Boolean getAddGlobalId()  {
        return getAttributeAsBoolean("addGlobalId");
    }
             
    /**
    * Indicates the format to be used for HTTP requests and responses when&#010 fulfilling DSRequests (eg {@link com.smartgwt.client.data.DataSource#fetchData} is called).
    *
    * @param dataFormat dataFormat Default value is "iscServer"
    * @throws IllegalStateException this property cannot be changed after the underlying component has been created
    */
    public void setDataFormat(DSDataFormat dataFormat)  throws IllegalStateException {
        setAttribute("dataFormat", dataFormat.getValue(), false);
    }
    /**
     * Indicates the format to be used for HTTP requests and responses when&#010 fulfilling DSRequests (eg {@link com.smartgwt.client.data.DataSource#fetchData} is called).
     *
     *
     * @return DSDataFormat
     *
     */
    public DSDataFormat getDataFormat()  {
        return (DSDataFormat) EnumUtil.getEnum(DSDataFormat.values(), getAttribute("dataFormat"));
    }

    /**
    * Applies only to dataFormat: "json".  Specifies the name of the query parameter that&#010 tells your JSON service what function to call as part of the response.
    *
    * @param callbackParam callbackParam Default value is "callback"
    * @throws IllegalStateException this property cannot be changed after the underlying component has been created
    */
    public void setCallbackParam(String callbackParam)  throws IllegalStateException {
        setAttribute("callbackParam", callbackParam, false);
    }
    /**
     * Applies only to dataFormat: "json".  Specifies the name of the query parameter that&#010 tells your JSON service what function to call as part of the response.
     *
     *
     * @return String
     *
     */
    public String getCallbackParam()  {
        return getAttributeAsString("callbackParam");
    }
             
    /**
    * Transport to use for all operations on this DataSource. Defaults to&#010 {@link com.smartgwt.client.rpc.RPCManager#defaultTransport}.  This would typically only be set to&#010 enable "scriptInclude" transport for contacting {@link com.smartgwt.client.data.DataSource#getDataFormat dataFormat} web&#010 services hosted on servers other than the origin server.&#010 <p>&#010 When using the "scriptInclude" transport, be sure to set {@link com.smartgwt.client.data.DataSource#getCallbackParam callbackParam} or&#010 {@link com.smartgwt.client.data.OperationBinding#getCallbackParam callbackParam} to match the name of the query parameter name expected&#010 by your JSON service provider.
    *
    * @param dataTransport dataTransport Default value is RPCManager.defaultTransport
    * @throws IllegalStateException this property cannot be changed after the underlying component has been created
    */
    public void setDataTransport(RPCTransport dataTransport)  throws IllegalStateException {
        setAttribute("dataTransport", dataTransport.getValue(), false);
    }
    /**
     * Transport to use for all operations on this DataSource. Defaults to&#010 {@link com.smartgwt.client.rpc.RPCManager#defaultTransport}.  This would typically only be set to&#010 enable "scriptInclude" transport for contacting {@link com.smartgwt.client.data.DataSource#getDataFormat dataFormat} web&#010 services hosted on servers other than the origin server.&#010 <p>&#010 When using the "scriptInclude" transport, be sure to set {@link com.smartgwt.client.data.DataSource#getCallbackParam callbackParam} or&#010 {@link com.smartgwt.client.data.OperationBinding#getCallbackParam callbackParam} to match the name of the query parameter name expected&#010 by your JSON service provider.
     *
     *
     * @return RPCTransport
     *
     */
    public RPCTransport getDataTransport()  {
        return (RPCTransport) EnumUtil.getEnum(RPCTransport.values(), getAttribute("dataTransport"));
    }

    /**
    * If set, the DataSource will ensure that it never uses a cached HTTP response, even if the&#010 server marks the response as cacheable.&#010 <P>&#010 Note that this does not disable caching at higher levels in the framework, for example, the&#010 caching performed by {@link com.smartgwt.client.data.ResultSet}.
    *
    * @param preventHTTPCaching preventHTTPCaching Default value is true
    * @throws IllegalStateException this property cannot be changed after the underlying component has been created
    */
    public void setPreventHTTPCaching(Boolean preventHTTPCaching)  throws IllegalStateException {
        setAttribute("preventHTTPCaching", preventHTTPCaching, false);
    }
    /**
     * If set, the DataSource will ensure that it never uses a cached HTTP response, even if the&#010 server marks the response as cacheable.&#010 <P>&#010 Note that this does not disable caching at higher levels in the framework, for example, the&#010 caching performed by {@link com.smartgwt.client.data.ResultSet}.
     *
     *
     * @return Boolean
     *
     */
    public Boolean getPreventHTTPCaching()  {
        return getAttributeAsBoolean("preventHTTPCaching");
    }
             
    /**
    * For a DataSource stored in .xml format on the ISC server, indicates what server-side&#010 connector to use to execute requests, that is, what happens if you call&#010 dsRequest.execute() in server code.
    *
    * @param serverType serverType Default value is "generic"
    * @throws IllegalStateException this property cannot be changed after the underlying component has been created
    */
    public void setServerType(DSServerType serverType)  throws IllegalStateException {
        setAttribute("serverType", serverType.getValue(), false);
    }
    /**
     * For a DataSource stored in .xml format on the ISC server, indicates what server-side&#010 connector to use to execute requests, that is, what happens if you call&#010 dsRequest.execute() in server code.
     *
     *
     * @return DSServerType
     *
     */
    public DSServerType getServerType()  {
        return (DSServerType) EnumUtil.getEnum(DSServerType.values(), getAttribute("serverType"));
    }

    /**
    * For DataSources using the {@link com.smartgwt.client.docs.SqlDataSource 'SmartGWT SQL engine'} for&#010 persistence, what database table name to use.  The default is to use the DataSource ID&#010 as the table name.
    *
    * @param tableName tableName Default value is null
    * @throws IllegalStateException this property cannot be changed after the underlying component has been created
    */
    public void setTableName(String tableName)  throws IllegalStateException {
        setAttribute("tableName", tableName, false);
    }
    /**
     * For DataSources using the {@link com.smartgwt.client.docs.SqlDataSource 'SmartGWT SQL engine'} for&#010 persistence, what database table name to use.  The default is to use the DataSource ID&#010 as the table name.
     *
     *
     * @return String
     *
     */
    public String getTableName()  {
        return getAttributeAsString("tableName");
    }

    /**
    * For DataSources using the {@link com.smartgwt.client.docs.SqlDataSource 'SmartGWT SQL engine'} for&#010 persistence, which database configuration to use.  Database configurations can be&#010 created using the "Databases" tab in the Developer Console.  If unset, the default&#010 database configuration is used (which also settable using the "Databases" tab).
    *
    * @param dbName dbName Default value is null
    * @throws IllegalStateException this property cannot be changed after the underlying component has been created
    */
    public void setDbName(String dbName)  throws IllegalStateException {
        setAttribute("dbName", dbName, false);
    }
    /**
     * For DataSources using the {@link com.smartgwt.client.docs.SqlDataSource 'SmartGWT SQL engine'} for&#010 persistence, which database configuration to use.  Database configurations can be&#010 created using the "Databases" tab in the Developer Console.  If unset, the default&#010 database configuration is used (which also settable using the "Databases" tab).
     *
     *
     * @return String
     *
     */
    public String getDbName()  {
        return getAttributeAsString("dbName");
    }

    /**
    * Allows you to specify an arbitrary prefix string to apply to all json format responses &#010 sent from the server to this application.<br>&#010 The inclusion of such a prefix ensures your code is not directly executable outside of&#010 your application, as a preventitive measure against&#010 <a href='http://www.google.com/search?q=javascript+hijacking' onclick="window.open('http://www.google.com/search?q=javascript+hijacking');return false;">javascript hijacking</a>.&#010 <br>&#010 Only applies to responses formatted as json objects. Does not apply to responses returned&#010 via scriptInclude type transport.<br>&#010 Note: If the prefix / suffix served by your backend is not a constant, you can use &#010 {@link com.smartgwt.client.data.OperationBinding#getDataFormat dataFormat} instead and explicitly parse&#010 the prefix out as part of {@link com.smartgwt.client.data.DataSource#transformResponse}.
    * <p><b>Note : </b> This is an advanced setting</p>
    *
    * @param jsonPrefix jsonPrefix Default value is null
    * @throws IllegalStateException this property cannot be changed after the underlying component has been created
    */
    public void setJsonPrefix(String jsonPrefix)  throws IllegalStateException {
        setAttribute("jsonPrefix", jsonPrefix, false);
    }
    /**
     * Allows you to specify an arbitrary prefix string to apply to all json format responses &#010 sent from the server to this application.<br>&#010 The inclusion of such a prefix ensures your code is not directly executable outside of&#010 your application, as a preventitive measure against&#010 <a href='http://www.google.com/search?q=javascript+hijacking' onclick="window.open('http://www.google.com/search?q=javascript+hijacking');return false;">javascript hijacking</a>.&#010 <br>&#010 Only applies to responses formatted as json objects. Does not apply to responses returned&#010 via scriptInclude type transport.<br>&#010 Note: If the prefix / suffix served by your backend is not a constant, you can use &#010 {@link com.smartgwt.client.data.OperationBinding#getDataFormat dataFormat} instead and explicitly parse&#010 the prefix out as part of {@link com.smartgwt.client.data.DataSource#transformResponse}.
     *
     *
     * @return String
     *
     */
    public String getJsonPrefix()  {
        return getAttributeAsString("jsonPrefix");
    }

    /**
    * Allows you to specify an arbitrary suffix string to apply to all json format responses &#010 sent from the server to this application.<br>&#010 The inclusion of such a suffix ensures your code is not directly executable outside of&#010 your application, as a preventitive measure against&#010 <a href='http://www.google.com/search?q=javascript+hijacking' onclick="window.open('http://www.google.com/search?q=javascript+hijacking');return false;">javascript hijacking</a>.&#010 <br>&#010 Only applies to responses formatted as json objects. Does not apply to responses returned&#010 via scriptInclude type transport.
    * <p><b>Note : </b> This is an advanced setting</p>
    *
    * @param jsonSuffix jsonSuffix Default value is null
    * @throws IllegalStateException this property cannot be changed after the underlying component has been created
    */
    public void setJsonSuffix(String jsonSuffix)  throws IllegalStateException {
        setAttribute("jsonSuffix", jsonSuffix, false);
    }
    /**
     * Allows you to specify an arbitrary suffix string to apply to all json format responses &#010 sent from the server to this application.<br>&#010 The inclusion of such a suffix ensures your code is not directly executable outside of&#010 your application, as a preventitive measure against&#010 <a href='http://www.google.com/search?q=javascript+hijacking' onclick="window.open('http://www.google.com/search?q=javascript+hijacking');return false;">javascript hijacking</a>.&#010 <br>&#010 Only applies to responses formatted as json objects. Does not apply to responses returned&#010 via scriptInclude type transport.
     *
     *
     * @return String
     *
     */
    public String getJsonSuffix()  {
        return getAttributeAsString("jsonSuffix");
    }

    /**
    * Like {@link com.smartgwt.client.widgets.DataBoundComponent#getUseFlatFields useFlatFields}, but applies to all DataBound components&#010 that bind to this DataSource.
    *
    * @param useFlatFields useFlatFields Default value is null
    * @throws IllegalStateException this property cannot be changed after the underlying component has been created
    */
    public void setUseFlatFields(Boolean useFlatFields)  throws IllegalStateException {
        setAttribute("useFlatFields", useFlatFields, false);
    }
    /**
     * Like {@link com.smartgwt.client.widgets.DataBoundComponent#getUseFlatFields useFlatFields}, but applies to all DataBound components&#010 that bind to this DataSource.
     *
     *
     * @return Boolean
     *
     */
    public Boolean getUseFlatFields()  {
        return getAttributeAsBoolean("useFlatFields");
    }

    /**
    * For a DataSource that inherits {@link com.smartgwt.client.data.DataSource#getFields fields} from another DataSource &#010 (via {@link com.smartgwt.client.data.DataSource#getInheritsFrom inheritsFrom}), indicates that only the fields listed in this DataSource&#010 should be shown.  All other inherited parent fields will be marked "hidden:true".
    *
    * @param showLocalFieldsOnly showLocalFieldsOnly Default value is null
    * @throws IllegalStateException this property cannot be changed after the underlying component has been created
    */
    public void setShowLocalFieldsOnly(Boolean showLocalFieldsOnly)  throws IllegalStateException {
        setAttribute("showLocalFieldsOnly", showLocalFieldsOnly, false);
    }
    /**
     * For a DataSource that inherits {@link com.smartgwt.client.data.DataSource#getFields fields} from another DataSource &#010 (via {@link com.smartgwt.client.data.DataSource#getInheritsFrom inheritsFrom}), indicates that only the fields listed in this DataSource&#010 should be shown.  All other inherited parent fields will be marked "hidden:true".
     *
     *
     * @return Boolean
     *
     */
    public Boolean getShowLocalFieldsOnly()  {
        return getAttributeAsBoolean("showLocalFieldsOnly");
    }

    /**
    * For a DataSource that inherits {@link com.smartgwt.client.data.DataSource#getFields fields} from another DataSource &#010 (via {@link com.smartgwt.client.data.DataSource#getInheritsFrom inheritsFrom}), indicates that the parent's field order should be used&#010 instead of the order of the fields as declared in this DataSource.  New fields, if any,&#010 are placed at the end.
    *
    * @param useParentFieldOrder useParentFieldOrder Default value is null
    * @throws IllegalStateException this property cannot be changed after the underlying component has been created
    */
    public void setUseParentFieldOrder(Boolean useParentFieldOrder)  throws IllegalStateException {
        setAttribute("useParentFieldOrder", useParentFieldOrder, false);
    }
    /**
     * For a DataSource that inherits {@link com.smartgwt.client.data.DataSource#getFields fields} from another DataSource &#010 (via {@link com.smartgwt.client.data.DataSource#getInheritsFrom inheritsFrom}), indicates that the parent's field order should be used&#010 instead of the order of the fields as declared in this DataSource.  New fields, if any,&#010 are placed at the end.
     *
     *
     * @return Boolean
     *
     */
    public Boolean getUseParentFieldOrder()  {
        return getAttributeAsBoolean("useParentFieldOrder");
    }

    /**
    * Indicates that for server responses, for any data being interpreted as DataSource records, &#010 only data that&#010 corresponds to declared fields should be retained; any extra fields should be discarded.&#010 <P>&#010 For {@link com.smartgwt.client.data.DataSource#getDataFormat dataFormat} data, this means extra properties in selected&#010 objects are dropped.&#010 <P>&#010 By default, for DMI DSResponses, DSResponse.data is filtered on the server to just&#010 the set of fields defined on the DataSource.  This type of filtering can also be enabled&#010 for non-DMI DSResponses (see the overview in {@link com.smartgwt.client..DMI}).  Setting this property to&#010 <code>false</code> disables this filtering for this DataSource only.  This setting&#010 overrides the configuration in <code>server.properties</code>.  This setting can&#010 be overridden by {@link com.smartgwt.client..ServerObject#getDropExtraFields dropExtraFields}.
    *
    * @param dropExtraFields dropExtraFields Default value is null
    * @throws IllegalStateException this property cannot be changed after the underlying component has been created
    */
    public void setDropExtraFields(Boolean dropExtraFields)  throws IllegalStateException {
        setAttribute("dropExtraFields", dropExtraFields, false);
    }
    /**
     * Indicates that for server responses, for any data being interpreted as DataSource records, &#010 only data that&#010 corresponds to declared fields should be retained; any extra fields should be discarded.&#010 <P>&#010 For {@link com.smartgwt.client.data.DataSource#getDataFormat dataFormat} data, this means extra properties in selected&#010 objects are dropped.&#010 <P>&#010 By default, for DMI DSResponses, DSResponse.data is filtered on the server to just&#010 the set of fields defined on the DataSource.  This type of filtering can also be enabled&#010 for non-DMI DSResponses (see the overview in {@link com.smartgwt.client..DMI}).  Setting this property to&#010 <code>false</code> disables this filtering for this DataSource only.  This setting&#010 overrides the configuration in <code>server.properties</code>.  This setting can&#010 be overridden by {@link com.smartgwt.client..ServerObject#getDropExtraFields dropExtraFields}.
     *
     *
     * @return Boolean
     *
     */
    public Boolean getDropExtraFields()  {
        return getAttributeAsBoolean("dropExtraFields");
    }

    /**
    * Analagous to {@link com.smartgwt.client.data.DataSource#getDropExtraFields dropExtraFields}, for data sent to the server.  Setting this&#010 attribute to false ensures that for any records in the data object, only fields that&#010 correspond to declared dataSource fields will be present on the dsRequest data object passed&#010 to {@link com.smartgwt.client.data.DataSource#transformRequest} and ultimately sent to the server.
    *
    * @param sendExtraFields sendExtraFields Default value is true
    * @throws IllegalStateException this property cannot be changed after the underlying component has been created
    */
    public void setSendExtraFields(Boolean sendExtraFields)  throws IllegalStateException {
        setAttribute("sendExtraFields", sendExtraFields, false);
    }
    /**
     * Analagous to {@link com.smartgwt.client.data.DataSource#getDropExtraFields dropExtraFields}, for data sent to the server.  Setting this&#010 attribute to false ensures that for any records in the data object, only fields that&#010 correspond to declared dataSource fields will be present on the dsRequest data object passed&#010 to {@link com.smartgwt.client.data.DataSource#transformRequest} and ultimately sent to the server.
     *
     *
     * @return Boolean
     *
     */
    public Boolean getSendExtraFields()  {
        return getAttributeAsBoolean("sendExtraFields");
    }

    /**
    * For a DataSource with serverType: "sql" or serverType: "hibernate", automatically derive&#010 the dataSource's schema (field definitions) from from the SQL table specified in &#010 {@link com.smartgwt.client.data.DataSource#getTableName tableName}.  This causes SmartGWT to create a "super" DataSource, which&#010 this dataSource then automatically {@link com.smartgwt.client.data.DataSource#getInheritsFrom inheritsFrom}.  This allows you to &#010 override auto-derived schema as required.&#010 <p>&#010 This property is only applicable if you are using the SmartGWT server.
    *
    * @param autoDeriveSchema autoDeriveSchema Default value is null
    * @throws IllegalStateException this property cannot be changed after the underlying component has been created
    */
    public void setAutoDeriveSchema(Boolean autoDeriveSchema)  throws IllegalStateException {
        setAttribute("autoDeriveSchema", autoDeriveSchema, false);
    }
    /**
     * For a DataSource with serverType: "sql" or serverType: "hibernate", automatically derive&#010 the dataSource's schema (field definitions) from from the SQL table specified in &#010 {@link com.smartgwt.client.data.DataSource#getTableName tableName}.  This causes SmartGWT to create a "super" DataSource, which&#010 this dataSource then automatically {@link com.smartgwt.client.data.DataSource#getInheritsFrom inheritsFrom}.  This allows you to &#010 override auto-derived schema as required.&#010 <p>&#010 This property is only applicable if you are using the SmartGWT server.
     *
     *
     * @return Boolean
     *
     */
    public Boolean getAutoDeriveSchema()  {
        return getAttributeAsBoolean("autoDeriveSchema");
    }

    /**
    * For an XML DataSource, URN of the WebService to use to invoke operations.  This URN&#010 comes from the "targetNamespace" attribute of the &lt;wsdl:definitions&gt; element in a&#010 WSDL (Web Service Description Language) document, and serves as the unique identifier of&#010 the service.&#010 <P>&#010 Having loaded a WebService using {@link com.smartgwt.client.data.XMLTools#loadWSDL}, setting&#010 <code>serviceNamespace</code> combined with specifying &#010 {@link com.smartgwt.client.data.OperationBinding}&#010 that set {@link com.smartgwt.client.data.OperationBinding#getWsOperation wsOperation} will cause a DataSource to invoke web&#010 service operations to fulfill DataSource requests ({@link com.smartgwt.client.data.DSRequest}).&#010 <P>&#010 Setting <code>serviceNamespace</code> also defaults&#010 {@link com.smartgwt.client.data.DataSource#getDataURL dataURL} to the service's location,&#010 {@link com.smartgwt.client.data.DataSource#getDataFormat dataFormat} to "xml" and&#010 {@link com.smartgwt.client.data.OperationBinding#getDataProtocol dataProtocol} to "soap".
    *
    * @param serviceNamespace serviceNamespace Default value is null
    * @throws IllegalStateException this property cannot be changed after the underlying component has been created
    */
    public void setServiceNamespace(String serviceNamespace)  throws IllegalStateException {
        setAttribute("serviceNamespace", serviceNamespace, false);
    }
    /**
     * For an XML DataSource, URN of the WebService to use to invoke operations.  This URN&#010 comes from the "targetNamespace" attribute of the &lt;wsdl:definitions&gt; element in a&#010 WSDL (Web Service Description Language) document, and serves as the unique identifier of&#010 the service.&#010 <P>&#010 Having loaded a WebService using {@link com.smartgwt.client.data.XMLTools#loadWSDL}, setting&#010 <code>serviceNamespace</code> combined with specifying &#010 {@link com.smartgwt.client.data.OperationBinding}&#010 that set {@link com.smartgwt.client.data.OperationBinding#getWsOperation wsOperation} will cause a DataSource to invoke web&#010 service operations to fulfill DataSource requests ({@link com.smartgwt.client.data.DSRequest}).&#010 <P>&#010 Setting <code>serviceNamespace</code> also defaults&#010 {@link com.smartgwt.client.data.DataSource#getDataURL dataURL} to the service's location,&#010 {@link com.smartgwt.client.data.DataSource#getDataFormat dataFormat} to "xml" and&#010 {@link com.smartgwt.client.data.OperationBinding#getDataProtocol dataProtocol} to "soap".
     *
     *
     * @return String
     *
     */
    public String getServiceNamespace()  {
        return getAttributeAsString("serviceNamespace");
    }

    /**
     * For a DataSource derived from WSDL or XML schema, the XML namespace this schema belongs&#010 to.  This is a read-only attribute automatically present on DataSources returned from&#010 {@link com.smartgwt.client..SchemaSet#getSchema} and {@link com.smartgwt.client.data.WebService#getSchema}.
     *
     * <b>Note :</b> This method should be called only after the underlying component has been created.
     *
     * @return String
     *
     * @throws IllegalStateException if underlying component has not yet been created.
     */
    public String getSchemaNamespace() throws IllegalStateException {
        errorIfNotCreated("schemaNamespace");
        return getAttributeAsString("schemaNamespace");
    }

    /**
    * See {@link com.smartgwt.client.data.OperationBinding#getRecordXPath recordXPath}.  <code>recordXPath</code> can be specified&#010 directly on the DataSource for a simple read-only DataSource only capable of "fetch"&#010 operations.
    *
    * @param recordXPath recordXPath Default value is null
    * @throws IllegalStateException this property cannot be changed after the underlying component has been created
    */
    public void setRecordXPath(String recordXPath)  throws IllegalStateException {
        setAttribute("recordXPath", recordXPath, false);
    }
    /**
     * See {@link com.smartgwt.client.data.OperationBinding#getRecordXPath recordXPath}.  <code>recordXPath</code> can be specified&#010 directly on the DataSource for a simple read-only DataSource only capable of "fetch"&#010 operations.
     *
     *
     * @return String
     *
     */
    public String getRecordXPath()  {
        return getAttributeAsString("recordXPath");
    }

    /**
    * Default URL to contact to fulfill all DSRequests.  Can also be set on a&#010 per-operationType basis via {@link com.smartgwt.client.data.OperationBinding#getDataURL dataURL}.&#010 <P>&#010 NOTE: Best practice is to use the same <code>dataURL</code> for all DataSources which&#010 fulfill DSRequests via the server-side RPCManager API.  Otherwise, cross-DataSource&#010 {@link com.smartgwt.client.rpc.RPCManager#startQueue} will not be possible.
    *
    * @param dataURL dataURL Default value is null
    * @throws IllegalStateException this property cannot be changed after the underlying component has been created
    */
    public void setDataURL(String dataURL)  throws IllegalStateException {
        setAttribute("dataURL", dataURL, false);
    }
    /**
     * Default URL to contact to fulfill all DSRequests.  Can also be set on a&#010 per-operationType basis via {@link com.smartgwt.client.data.OperationBinding#getDataURL dataURL}.&#010 <P>&#010 NOTE: Best practice is to use the same <code>dataURL</code> for all DataSources which&#010 fulfill DSRequests via the server-side RPCManager API.  Otherwise, cross-DataSource&#010 {@link com.smartgwt.client.rpc.RPCManager#startQueue} will not be possible.
     *
     *
     * @return String
     *
     */
    public String getDataURL()  {
        return getAttributeAsString("dataURL");
    }

    /**
    * Tag name to use when serializing to XML.  If unspecified, the <code>dataSource.ID</code>&#010 will be used.
    * <p><b>Note : </b> This is an advanced setting</p>
    *
    * @param tagName tagName Default value is null
    * @throws IllegalStateException this property cannot be changed after the underlying component has been created
    */
    public void setTagName(String tagName)  throws IllegalStateException {
        setAttribute("tagName", tagName, false);
    }
    /**
     * Tag name to use when serializing to XML.  If unspecified, the <code>dataSource.ID</code>&#010 will be used.
     *
     *
     * @return String
     *
     */
    public String getTagName()  {
        return getAttributeAsString("tagName");
    }

    /**
    * Whether to act as a "client-only" DataSource, which has no permanent storage and never&#010 contacts the server, instead using a set of test data to respond to requests in the same&#010 manner as a server-based DataSource might.&#010 <P>&#010 See {@link com.smartgwt.client.docs.ClientOnlyDataSources 'this discussion'} for ways to populate a&#010 client-only DataSource with test data.
    *
    * @param clientOnly clientOnly Default value is false
    * @throws IllegalStateException this property cannot be changed after the underlying component has been created
    */
    public void setClientOnly(Boolean clientOnly)  throws IllegalStateException {
        setAttribute("clientOnly", clientOnly, false);
    }
    /**
     * Whether to act as a "client-only" DataSource, which has no permanent storage and never&#010 contacts the server, instead using a set of test data to respond to requests in the same&#010 manner as a server-based DataSource might.&#010 <P>&#010 See {@link com.smartgwt.client.docs.ClientOnlyDataSources 'this discussion'} for ways to populate a&#010 client-only DataSource with test data.
     *
     *
     * @return Boolean
     *
     */
    public Boolean getClientOnly()  {
        return getAttributeAsBoolean("clientOnly");
    }
             
    /**
    * Decides under what conditions the {@link com.smartgwt.client.data.ResultSet} cache should be dropped when the&#010 {@link com.smartgwt.client.data.ResultSet#getCriteria criteria} changes
    * <p><b>Note : </b> This is an advanced setting</p>
    *
    * @param criteriaPolicy criteriaPolicy Default value is "dropOnShortening"
    */
    public void setCriteriaPolicy(CriteriaPolicy criteriaPolicy) {
        setAttribute("criteriaPolicy", criteriaPolicy.getValue(), true);
    }
    /**
     * Decides under what conditions the {@link com.smartgwt.client.data.ResultSet} cache should be dropped when the&#010 {@link com.smartgwt.client.data.ResultSet#getCriteria criteria} changes
     *
     *
     * @return CriteriaPolicy
     *
     */
    public CriteriaPolicy getCriteriaPolicy()  {
        return (CriteriaPolicy) EnumUtil.getEnum(CriteriaPolicy.values(), getAttribute("criteriaPolicy"));
    }

    /**
    * Whether to attempt validation on the client at all for this DataSource.  If unset (the&#010 default), client-side validation is enabled.&#010 <p>&#010 Disabling client-side validation entirely is a good way to test server-side validation.
    * <p><b>Note : </b> This is an advanced setting</p>
    *
    * @param useLocalValidators useLocalValidators Default value is null
    */
    public void setUseLocalValidators(Boolean useLocalValidators) {
        setAttribute("useLocalValidators", useLocalValidators, true);
    }
    /**
     * Whether to attempt validation on the client at all for this DataSource.  If unset (the&#010 default), client-side validation is enabled.&#010 <p>&#010 Disabling client-side validation entirely is a good way to test server-side validation.
     *
     *
     * @return Boolean
     *
     */
    public Boolean getUseLocalValidators()  {
        return getAttributeAsBoolean("useLocalValidators");
    }

    /**
    * Whether RPCRequests sent by this DataSource should enable &#010 {@link com.smartgwt.client.rpc.RPCRequest#getShowPrompt showPrompt} if it's unset.
    *
    * @param showPrompt showPrompt Default value is true
    */
    public void setShowPrompt(Boolean showPrompt) {
        setAttribute("showPrompt", showPrompt, true);
    }
    /**
     * Whether RPCRequests sent by this DataSource should enable &#010 {@link com.smartgwt.client.rpc.RPCRequest#getShowPrompt showPrompt} if it's unset.
     *
     *
     * @return Boolean
     *
     */
    public Boolean getShowPrompt()  {
        return getAttributeAsBoolean("showPrompt");
    }
             
    /**
    * Sets the strategy this DataSource uses to translate Java enumerated types (objects of type&#010 enum) to and from Javascript.&#010 &#010 This property is only applicable if you are using the SmartGWT server
    * <p><b>Note : </b> This is an advanced setting</p>
    *
    * @param enumTranslateStrategy enumTranslateStrategy Default value is null
    * @throws IllegalStateException this property cannot be changed after the underlying component has been created
    */
    public void setEnumTranslateStrategy(EnumTranslateStrategy enumTranslateStrategy)  throws IllegalStateException {
        setAttribute("enumTranslateStrategy", enumTranslateStrategy.getValue(), false);
    }

    /**
    * The name of the property this DataSource uses for constant name when translating&#010 Java enumerated types to and from Javascript, if the {@link com.smartgwt.client.types.EnumTranslateStrategy} is set&#010 to "bean".  Defaults to "_constant" if not set.&#010 <p>&#010  This property is only applicable if you are using the SmartGWT server
    * <p><b>Note : </b> This is an advanced setting</p>
    *
    * @param enumConstantProperty enumConstantProperty Default value is null
    * @throws IllegalStateException this property cannot be changed after the underlying component has been created
    */
    public void setEnumConstantProperty(String enumConstantProperty)  throws IllegalStateException {
        setAttribute("enumConstantProperty", enumConstantProperty, false);
    }

    /**
    * The name of the property this DataSource uses for ordinal number when translating&#010 Java enumerated types to and from Javascript, if the {@link com.smartgwt.client.types.EnumTranslateStrategy} is set&#010 to "bean".  Defaults to "_ordinal" if not set.&#010 <p>&#010  This property is only applicable if you are using the SmartGWT server
    * <p><b>Note : </b> This is an advanced setting</p>
    *
    * @param enumOrdinalProperty enumOrdinalProperty Default value is null
    * @throws IllegalStateException this property cannot be changed after the underlying component has been created
    */
    public void setEnumOrdinalProperty(String enumOrdinalProperty)  throws IllegalStateException {
        setAttribute("enumOrdinalProperty", enumOrdinalProperty, false);
    }

    /**
    * If set, titles are automatically derived from {@link com.smartgwt.client.data.DataSourceField#getName name} for any &#010 field that does not have a {@link com.smartgwt.client.data.DataSourceField#getTitle title} and is not marked&#010 {@link com.smartgwt.client.data.DataSourceField#getHidden hidden}:true, by calling the method {@link com.smartgwt.client.data.DataSource#getAutoTitle}.
    *
    * @param autoDeriveTitles autoDeriveTitles Default value is true
    * @throws IllegalStateException this property cannot be changed after the underlying component has been created
    */
    public void setAutoDeriveTitles(Boolean autoDeriveTitles)  throws IllegalStateException {
        setAttribute("autoDeriveTitles", autoDeriveTitles, false);
    }
    /**
     * If set, titles are automatically derived from {@link com.smartgwt.client.data.DataSourceField#getName name} for any &#010 field that does not have a {@link com.smartgwt.client.data.DataSourceField#getTitle title} and is not marked&#010 {@link com.smartgwt.client.data.DataSourceField#getHidden hidden}:true, by calling the method {@link com.smartgwt.client.data.DataSource#getAutoTitle}.
     *
     *
     * @return Boolean
     *
     */
    public Boolean getAutoDeriveTitles()  {
        return getAttributeAsBoolean("autoDeriveTitles");
    }

    /**
    * User-visible name for this DataSource.&#010 <P>&#010 For example, for the supplyItem DataSource, "Supply Item".&#010 <P>&#010 Defaults to <code>dataSource.ID</code>.
    *
    * @param title title Default value is dataSource.ID
    */
    public void setTitle(String title) {
        setAttribute("title", title, true);
    }
    /**
     * User-visible name for this DataSource.&#010 <P>&#010 For example, for the supplyItem DataSource, "Supply Item".&#010 <P>&#010 Defaults to <code>dataSource.ID</code>.
     *
     *
     * @return String
     *
     */
    public String getTitle()  {
        return getAttributeAsString("title");
    }

    /**
    * User-visible plural name for this DataSource.&#010 <P>&#010 For example, for the supplyItem DataSource, "Supply Items".&#010 <P>&#010 Defaults to (<code>dataSource.title</code> or <code>dataSource.ID</code>) + "s".
    *
    * @param pluralTitle pluralTitle Default value is dataSource.ID
    * @throws IllegalStateException this property cannot be changed after the underlying component has been created
    */
    public void setPluralTitle(String pluralTitle)  throws IllegalStateException {
        setAttribute("pluralTitle", pluralTitle, false);
    }
    /**
     * User-visible plural name for this DataSource.&#010 <P>&#010 For example, for the supplyItem DataSource, "Supply Items".&#010 <P>&#010 Defaults to (<code>dataSource.title</code> or <code>dataSource.ID</code>) + "s".
     *
     *
     * @return String
     *
     */
    public String getPluralTitle()  {
        return getAttributeAsString("pluralTitle");
    }

    /**
    * Best field to use for a user-visible title for an individual record from this dataSource.&#010 <p>&#010 For example, for a DataSource of employees, a "full name" field would probably most clearly&#010 labels an employee record.&#010 <p>&#010 If not explicitly set, titleField looks for fields named "title", "label", "name", and "id"&#010 in that order.  If a field exists with one of those names, it becomes the titleField.  If not,&#010 then the first field is designated as the titleField.
    *
    * @param titleField titleField Default value is see below
    * @throws IllegalStateException this property cannot be changed after the underlying component has been created
    */
    public void setTitleField(String titleField)  throws IllegalStateException {
        setAttribute("titleField", titleField, false);
    }
    /**
     * Best field to use for a user-visible title for an individual record from this dataSource.&#010 <p>&#010 For example, for a DataSource of employees, a "full name" field would probably most clearly&#010 labels an employee record.&#010 <p>&#010 If not explicitly set, titleField looks for fields named "title", "label", "name", and "id"&#010 in that order.  If a field exists with one of those names, it becomes the titleField.  If not,&#010 then the first field is designated as the titleField.
     *
     *
     * @return String
     *
     */
    public String getTitleField()  {
        return getAttributeAsString("titleField");
    }

    /**
    * Designates a field of {@link com.smartgwt.client.types.FieldType}:"image" as the field to use when rendering a&#010 record as an image, for example, in a {@link com.smartgwt.client.widgets.tile.TileGrid}.&#010 <p>&#010 For example, for a DataSource of employees, a "photo" field of type "image" should be&#010 designated as the iconField.&#010 <p>&#010 If not explicitly set, iconField looks for fields named "picture", "thumbnail", "icon",&#010 "image" and "img", in that order, and will use any of these fields as the iconField if it&#010 exists and has type "image". &#010 <P>&#010 To avoid any field being used as the iconField, set iconField to <code>null</code>.
    *
    * @param iconField iconField Default value is see below
    * @throws IllegalStateException this property cannot be changed after the underlying component has been created
    */
    public void setIconField(String iconField)  throws IllegalStateException {
        setAttribute("iconField", iconField, false);
    }
    /**
     * Designates a field of {@link com.smartgwt.client.types.FieldType}:"image" as the field to use when rendering a&#010 record as an image, for example, in a {@link com.smartgwt.client.widgets.tile.TileGrid}.&#010 <p>&#010 For example, for a DataSource of employees, a "photo" field of type "image" should be&#010 designated as the iconField.&#010 <p>&#010 If not explicitly set, iconField looks for fields named "picture", "thumbnail", "icon",&#010 "image" and "img", in that order, and will use any of these fields as the iconField if it&#010 exists and has type "image". &#010 <P>&#010 To avoid any field being used as the iconField, set iconField to <code>null</code>.
     *
     *
     * @return String
     *
     */
    public String getIconField()  {
        return getAttributeAsString("iconField");
    }

    /**
    * fieldName for a field in the dataSource expected to contain an explicit array of child nodes.&#010 Enables loading a databound tree as a hierachical data structure, rather than a flat list of&#010 nodes linked by foreignKey.<br>&#010 Note this is an alternative to setting {@link com.smartgwt.client.data.DataSourceField#getChildrenProperty childrenProperty} directly on&#010 the childrenField object.<P>&#010 By default the children field will be assumed to be {@link com.smartgwt.client.data.DataSourceField#getMultiple multiple},&#010 for XML databinding. This implies that child data should be delivered in the format:&#010 <pre>&#010      &lt;childrenFieldName&gt;&#010          &lt;item name="firstChild" ...&gt;&#010          &lt;item name="secondChild" ...&gt;&#010      &lt;/childrenFieldName&gt;&#010 </pre>&#010 However data may also be delivered as a direct list of <code>childrenFieldName</code> &#010 elements:&#010 <pre>&#010      &lt;childrenFieldName name="firstChild" ...&gt;&#010      &lt;childrenFieldName name="secondChild" ...&gt;&#010 </pre>&#010 If you want to return your data in this format, you will need to explicitly set &#010 <code>multiple</code> to false in the appropriate dataSource field definition.
    *
    * @param childrenField childrenField Default value is null
    * @throws IllegalStateException this property cannot be changed after the underlying component has been created
    */
    public void setChildrenField(String childrenField)  throws IllegalStateException {
        setAttribute("childrenField", childrenField, false);
    }
    /**
     * fieldName for a field in the dataSource expected to contain an explicit array of child nodes.&#010 Enables loading a databound tree as a hierachical data structure, rather than a flat list of&#010 nodes linked by foreignKey.<br>&#010 Note this is an alternative to setting {@link com.smartgwt.client.data.DataSourceField#getChildrenProperty childrenProperty} directly on&#010 the childrenField object.<P>&#010 By default the children field will be assumed to be {@link com.smartgwt.client.data.DataSourceField#getMultiple multiple},&#010 for XML databinding. This implies that child data should be delivered in the format:&#010 <pre>&#010      &lt;childrenFieldName&gt;&#010          &lt;item name="firstChild" ...&gt;&#010          &lt;item name="secondChild" ...&gt;&#010      &lt;/childrenFieldName&gt;&#010 </pre>&#010 However data may also be delivered as a direct list of <code>childrenFieldName</code> &#010 elements:&#010 <pre>&#010      &lt;childrenFieldName name="firstChild" ...&gt;&#010      &lt;childrenFieldName name="secondChild" ...&gt;&#010 </pre>&#010 If you want to return your data in this format, you will need to explicitly set &#010 <code>multiple</code> to false in the appropriate dataSource field definition.
     *
     *
     * @return String
     *
     */
    public String getChildrenField()  {
        return getAttributeAsString("childrenField");
    }

    /**
    * Specifies the fully-qualified name of the DataSource subclass that should be instantiated&#010 server-side for this dataSource. This property allows you to write and use custom DataSource &#010 subclasses on the server.&#010 <p>&#010 One reason you might wish to do this would be to override the validate() method to provide &#010 some arbitrary custom validation (such as complex database lookups, validation embedded in &#010 legacy applications, etc).  It is also possible - though obviously a more substantial task -&#010 to override the execute() method in your custom DataSource.  This is one way of creating &#010 a completely customized DataSource implementation.&#010 <p>&#010 <b>Note:</b> If you use this property, you are responsible for making sure that it refers to&#010 a valid server-side class that extends <code>com.isomorphic.datasource.DataSource</code>.&#010 If your implementation relies on methods or state only present in certain specialized &#010 subclasses of DataSource (for example, you want the normal behavior and features of a &#010 HibernateDataSource, but with a specialized validate() method), then you shoud extend the &#010 subclass rather than the base class.
    *
    * @param serverConstructor serverConstructor Default value is null
    * @throws IllegalStateException this property cannot be changed after the underlying component has been created
    */
    public void setServerConstructor(String serverConstructor)  throws IllegalStateException {
        setAttribute("serverConstructor", serverConstructor, false);
    }
    /**
     * Specifies the fully-qualified name of the DataSource subclass that should be instantiated&#010 server-side for this dataSource. This property allows you to write and use custom DataSource &#010 subclasses on the server.&#010 <p>&#010 One reason you might wish to do this would be to override the validate() method to provide &#010 some arbitrary custom validation (such as complex database lookups, validation embedded in &#010 legacy applications, etc).  It is also possible - though obviously a more substantial task -&#010 to override the execute() method in your custom DataSource.  This is one way of creating &#010 a completely customized DataSource implementation.&#010 <p>&#010 <b>Note:</b> If you use this property, you are responsible for making sure that it refers to&#010 a valid server-side class that extends <code>com.isomorphic.datasource.DataSource</code>.&#010 If your implementation relies on methods or state only present in certain specialized &#010 subclasses of DataSource (for example, you want the normal behavior and features of a &#010 HibernateDataSource, but with a specialized validate() method), then you shoud extend the &#010 subclass rather than the base class.
     *
     *
     * @return String
     *
     */
    public String getServerConstructor()  {
        return getAttributeAsString("serverConstructor");
    }

    /**
    * For dataSources of {@link com.smartgwt.client.data.DataSource#getServerType serverType} "sql", determines whether we qualify column&#010 names with table names in any SQL we generate.  This property can be overridden on specific&#010 operationBindings.
    *
    * @param qualifyColumnNames qualifyColumnNames Default value is true
    * @throws IllegalStateException this property cannot be changed after the underlying component has been created
    */
    public void setQualifyColumnNames(Boolean qualifyColumnNames)  throws IllegalStateException {
        setAttribute("qualifyColumnNames", qualifyColumnNames, false);
    }
    /**
     * For dataSources of {@link com.smartgwt.client.data.DataSource#getServerType serverType} "sql", determines whether we qualify column&#010 names with table names in any SQL we generate.  This property can be overridden on specific&#010 operationBindings.
     *
     *
     * @return Boolean
     *
     */
    public Boolean getQualifyColumnNames()  {
        return getAttributeAsBoolean("qualifyColumnNames");
    }

    /**
    * Very advanced: for servers that do not support paging, and must return large numbers of XML&#010 records in one HTTP response, SmartGWT breaks up the processing of the response&#010 in order to avoid the "script running slowly" dialog appearing for an end user.&#010 <P>&#010 If you have a relatively small number of records with a great deal of properties or&#010 subojects on each record, and you have not set {@link com.smartgwt.client.data.DataSource#getDropExtraFields dropExtraFields} to eliminate unused&#010 data, and you see the "script running slowly" dialog, you may need to set this number&#010 lower.
    * <p><b>Note : </b> This is an advanced setting</p>
    *
    * @param resultBatchSize resultBatchSize Default value is 150
    */
    public void setResultBatchSize(int resultBatchSize) {
        setAttribute("resultBatchSize", resultBatchSize, true);
    }
    /**
     * Very advanced: for servers that do not support paging, and must return large numbers of XML&#010 records in one HTTP response, SmartGWT breaks up the processing of the response&#010 in order to avoid the "script running slowly" dialog appearing for an end user.&#010 <P>&#010 If you have a relatively small number of records with a great deal of properties or&#010 subojects on each record, and you have not set {@link com.smartgwt.client.data.DataSource#getDropExtraFields dropExtraFields} to eliminate unused&#010 data, and you see the "script running slowly" dialog, you may need to set this number&#010 lower.
     *
     *
     * @return int
     *
     */
    public int getResultBatchSize()  {
        return getAttributeAsInt("resultBatchSize");
    }

    /**
    * [A] If set to true, both client and server-side advanced filtering used by SmartGWT will follow&#010 SQL99 behavior for dealing with NULL values, which is often counter-intuitive to users.&#010 Specifically, when a field has NULL value, all of the following expression are false:&#010 <pre>&#010    field == "someValue"  (normally false)&#010    field != "someValue"  (normally true)&#010    not (field == "someValue")   (normally true)&#010    not (field != "someValue")   (normally false)&#010 </pre>
    *
    * @param strictSQLFiltering strictSQLFiltering Default value is false
    * @throws IllegalStateException this property cannot be changed after the underlying component has been created
    */
    public void setStrictSQLFiltering(Boolean strictSQLFiltering)  throws IllegalStateException {
        setAttribute("strictSQLFiltering", strictSQLFiltering, false);
    }
    /**
     * [A] If set to true, both client and server-side advanced filtering used by SmartGWT will follow&#010 SQL99 behavior for dealing with NULL values, which is often counter-intuitive to users.&#010 Specifically, when a field has NULL value, all of the following expression are false:&#010 <pre>&#010    field == "someValue"  (normally false)&#010    field != "someValue"  (normally true)&#010    not (field == "someValue")   (normally true)&#010    not (field != "someValue")   (normally false)&#010 </pre>
     *
     *
     * @return Boolean
     *
     */
    public Boolean getStrictSQLFiltering()  {
        return getAttributeAsBoolean("strictSQLFiltering");
    }

    // ********************* Methods ***********************

        /**
         * For a DataSource that describes a DOM structure, the list legal child elements that can&#010 be contained by the element described by this DataSource.&#010 <p>&#010 For a DataSource described by XML schema, this is the list of legal subelements <b>of&#010 complexType</b> (elements of simpleType become DataSourceFields with atomic type).&#010 <p>&#010 Note that currently, if an XML schema file contains ordering constraints, DataSources&#010 derived from XML Schema do not capture these constraints.&#010&#010
         */
        public native void getLegalChildTags() /*-{
            var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
            self.getLegalChildTags();
        }-*/;


















        /**
         * Add a handleError handler.
         * <p>
         * If you define this method on a DataSource, it will be called whenever the server returns&#010 a DSResponse with a status other than {@link com.smartgwt.client.rpc.RPCResponse#STATUS_SUCCESS}.  You can use&#010 this hook to do DataSource-specific error handling.  Unless you return&#010 <code>false</code> from this method, {@link com.smartgwt.client.rpc.RPCManager#handleError} will be called by&#010 SmartGWT right after this method completes.&#010 &#010
         *
         * @param handler the handleError handler
         * @return {@link HandlerRegistration} used to remove this handler
         */
        public HandlerRegistration addHandleErrorHandler(com.smartgwt.client.data.events.HandleErrorHandler handler) {
            if(getHandlerCount(com.smartgwt.client.data.events.ErrorEvent.getType()) == 0) setupHandleErrorEvent();
            return doAddHandler(handler, com.smartgwt.client.data.events.ErrorEvent.getType());
        }
        private native void setupHandleErrorEvent() /*-{
            var obj = null;
            var selfJ = this;
            if(this.@com.smartgwt.client.core.BaseClass::isCreated()()) {
                obj = this.@com.smartgwt.client.core.BaseClass::getJsObj()();
                obj.addProperties({handleError:function(){
                        var param = {"response" : arguments[0], "request" : arguments[1]};
                        var event = @com.smartgwt.client.data.events.ErrorEvent::new(Lcom/google/gwt/core/client/JavaScriptObject;)(param);
                        selfJ.@com.smartgwt.client.core.BaseClass::fireEvent(Lcom/google/gwt/event/shared/GwtEvent;)(event);
                        var ret = event.@com.smartgwt.client.event.Cancellable::isCancelled()();
                        return !ret;
                    }
                });
            } else {
                obj = this.@com.smartgwt.client.core.BaseClass::getConfig()();
                obj.handleError = function(){
                    var param = {"response" : arguments[0], "request" : arguments[1]};
                    var event = @com.smartgwt.client.data.events.ErrorEvent::new(Lcom/google/gwt/core/client/JavaScriptObject;)(param);
                    selfJ.@com.smartgwt.client.core.BaseClass::fireEvent(Lcom/google/gwt/event/shared/GwtEvent;)(event);
                    var ret = event.@com.smartgwt.client.event.Cancellable::isCancelled()();
                    return !ret;
                };
            }
        }-*/;

        /**
         * Return the field definition object.&#010
         * @param fieldName Name of the field to retrieve
         *
         * @return field object
         */
        public native DataSourceField getField(String fieldName) /*-{
            var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
            var ret = self.getField(fieldName);
            if(ret == null || ret === undefined) return null;
            return @com.smartgwt.client.data.DataSourceField::new(Lcom/google/gwt/core/client/JavaScriptObject;)(ret);
        }-*/;


        /**
         * Returns a pointer to the primaryKey field for this DataSource&#010
         *
         * @return primary key field object
         */
        public native DataSourceField getPrimaryKeyField() /*-{
            var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
            var ret = self.getPrimaryKeyField();
            if(ret == null || ret === undefined) return null;
            return @com.smartgwt.client.data.DataSourceField::new(Lcom/google/gwt/core/client/JavaScriptObject;)(ret);
        }-*/;

        /**
         * Returns the primary key fieldName for this DataSource&#010
         *
         * @return primary key field name
         */
        public native String getPrimaryKeyFieldName() /*-{
            var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
            return self.getPrimaryKeyFieldName();
        }-*/;





        /**
         * Does this dataSource support the specified "textMatchStyle" when performing a filter&#010 operation against a text field.&#010
         * @param textMatchStyle textMatchStyle to check. If passed a null value, assume                                an exact match is being requested.
         */
        public native void supportsTextMatchStyle(String textMatchStyle) /*-{
            var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
            self.supportsTextMatchStyle(textMatchStyle);
        }-*/;












    // ********************* Static Methods ***********************

        /**
         * Lookup a DataSource by ID.&#010&#010
         * @param ID DataSource ID
         *
         * @return the DataSource with this ID, if loaded, otherwise null.
         */
        public static native DataSource getDataSource(String ID) /*-{
            var ret = $wnd.isc.DataSource.getDataSource(ID);
            if(ret == null || ret === undefined) return null;
            var retVal = @com.smartgwt.client.core.BaseClass::getRef(Lcom/google/gwt/core/client/JavaScriptObject;)(ret);
            if(retVal == null) {
                retVal = @com.smartgwt.client.data.DataSource::new(Lcom/google/gwt/core/client/JavaScriptObject;)(ret);
            }
            return retVal;
        }-*/;

        /**
         * Synonym of {@link com.smartgwt.client.data.DataSource#getDataSource}: Lookup a DataSource by ID.&#010&#010
         * @param ID DataSource ID
         *
         * @return the DataSource with this ID, if loaded, otherwise null.
         */
        public static native DataSource get(String ID) /*-{
            var ret = $wnd.isc.DataSource.get(ID);
            if(ret == null || ret === undefined) return null;
            var retVal = @com.smartgwt.client.core.BaseClass::getRef(Lcom/google/gwt/core/client/JavaScriptObject;)(ret);
            if(retVal == null) {
                retVal = @com.smartgwt.client.data.DataSource::new(Lcom/google/gwt/core/client/JavaScriptObject;)(ret);
            }
            return retVal;
        }-*/;










    protected native void onInit() /*-{
        var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();

        self.__transformRequest = self.transformRequest;
        self.transformRequest = function(dsRequest) {
            var jObj = this.__ref;
            if(jObj === undefined) {
                //handle case where oneTimeDS is cared from original DS (when clientOnly=true with dataURL)
                jObj = $wnd.isc.DS.get(this.inheritsFrom).__ref;
            }
            var dsRequestJ = @com.smartgwt.client.data.DSRequest::new(Lcom/google/gwt/core/client/JavaScriptObject;)(dsRequest);
            var data = jObj.@com.smartgwt.client.data.DataSource::transformRequest(Lcom/smartgwt/client/data/DSRequest;)(dsRequestJ);
            return data;
        };


        self.__transformResponse = self.transformResponse;
        self.transformResponse = function(dsResponse, dsRequest, data) {
            var jObj = this.__ref;
            if(jObj === undefined) {
                //handle case where oneTimeDS is cared from original DS (when clientOnly=true with dataURL)
                jObj = $wnd.isc.DS.get(this.inheritsFrom).__ref;
            }
            var responseJ = @com.smartgwt.client.data.DSResponse::new(Lcom/google/gwt/core/client/JavaScriptObject;)(dsResponse);
            var requestJ = @com.smartgwt.client.data.DSRequest::new(Lcom/google/gwt/core/client/JavaScriptObject;)(dsRequest);
            jObj.@com.smartgwt.client.data.DataSource::transformResponse(Lcom/smartgwt/client/data/DSResponse;Lcom/smartgwt/client/data/DSRequest;Ljava/lang/Object;)(responseJ, requestJ, data);
        };

        self.__getClientOnlyResponse = self.getClientOnlyResponse;
        self.getClientOnlyResponse = function(dsRequest) {
            var jObj = this.__ref;
            if(jObj === undefined) {
                //handle case where oneTimeDS is cared from original DS (when clientOnly=true with dataURL)
                jObj = $wnd.isc.DS.get(this.inheritsFrom).__ref;
            }
            var requestJ = @com.smartgwt.client.data.DSRequest::new(Lcom/google/gwt/core/client/JavaScriptObject;)(dsRequest);
            var responseJ = jObj.@com.smartgwt.client.data.DataSource::getClientOnlyResponse(Lcom/smartgwt/client/data/DSRequest;)(requestJ);
            return responseJ == null ? null : responseJ.@com.smartgwt.client.data.DSResponse::getJsObj()();
        };
    }-*/;


    /**
     * Controls the format in which inputs are sent to the dataURL when fulfilling DSRequests. May be overridden for
     * individual request types using {@link com.smartgwt.client.data.OperationBinding#getDataProtocol dataProtocol}}
     *
     * @param dataProtocol dataProtocol Default value is null
     * @throws IllegalStateException this property cannot be changed after the underlying component has been created
     */
    public void setDataProtocol(DSProtocol dataProtocol) throws IllegalStateException {
        setAttribute("dataProtocol", dataProtocol.getValue(), false);
    }       

    /**
     * HTTP parameters that should be submitted with every DSRequest. <br> Useful for authenticated services that
     * require a sessionId with every request. <br> Can be set for all operations of a given DataSource as
     * DataSource.defaultParams.
     *
     * @param defaultParams the default params
     */
    public void setDefaultParams(Map defaultParams) {
        setAttribute("defaultParams", defaultParams, true);
    }

    /**
     * Controls the format in which inputs are sent to the dataURL when fulfilling DSRequests. May be overridden for
     * individual request types using {@link com.smartgwt.client.data.OperationBinding#getDataProtocol dataProtocol}}
     *
     * @return DSDataProtocol
     */
    public DSProtocol getDataProtocol() {
        return (DSProtocol) EnumUtil.getEnum(DSProtocol.values(), getAttribute("dataProtocol"));
    }

    /**
     * For a dataSource using client-side data integration, return the data that should be sent to the
     * DataSource.dataURL. <br> By default, HTTP requests sent to non-SmartGWT servers do not include DSRequest
     * metadata such as DSRequest.startRow, endRow, and oldValues. Only the core datasource protocol data is sent, such
     * as the criteria passed to fetchData() or the updated values submitted by form.saveData(). <br> transformRequest()
     * allows you to transform dsRequest metadata into a format understood by your server and include it in the HTTP
     * request, so that you can integrate DataSource features such as data paging with servers that support such
     * features. <br> How the data is actually sent to the URL is controlled by OperationBinding.dataProtocol. If using
     * the "getParams" or "postParams" protocol, data is expected to be a JavaScript Object where each property will
     * become a GET or POST'd parameter. If using dataProtocol:"soap" or "postXML", data will be serialized as an XML
     * message by DataSource.xmlSerialize(). <br> Other reasons to implement transformRequest(): <ul> <li>transform a
     * Criteria object into the custom query language of a web service</li> <li>add a session id to requests that
     * require authentication</li> <li>detect colliding updates by sending both updated values and the values the user
     * originally retrieved before editing began (available as DSRequest.oldValues)</li>
     * <p/>
     * <br><b>Note: This is an override point</b>
     *
     * @param dsRequest the DSRequest being processed
     * @return data to be sent to the dataURL (JavaScriptObject or String)
     */
    protected native Object transformRequest(DSRequest dsRequest) /*-{
        var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
        return self.__transformRequest(dsRequest.@com.smartgwt.client.data.DSRequest::getJsObj()());
    }-*/;

    /**
     * Modify the DSResponse object derived from the response returned from the  {@link
     * com.smartgwt.client.data.DataSource#getDataURL dataURL}. <P> This is an override point that makes it possible to
     * use DataSource features such as paging with web services that support such features, by allowing you to fill in
     * metadata fields in the DSResponse object (such as {@link com.smartgwt.client.data.DSResponse#getStartRow
     * startRow}) based on service-specific metadata fields contained in the service's response. <P> The DSResponse
     * passed to this method already has {@link com.smartgwt.client.data.DSResponse#getData data}, which is derived
     * differently depending on the {@link com.smartgwt.client.data.DataSource#getDataFormat dataFormat} setting: <ul>
     * <li> <code>dataFormat:"xml"</code> : either the {@link com.smartgwt.client.data.OperationBinding#getRecordXPath
     * recordXPath} or  {@link com.smartgwt.client.data.OperationBinding#getRecordName recordName} is used to select the
     * XML elements that represent DataSource records.  The selected XML elements are passed to {@link
     * com.smartgwt.client.data.DataSource#recordsFromXML}, which transforms the XML elements to typed JavaScript data
     * using the DataSource as a schema. <li> <code>dataFormat:"json"</code> : the {@link
     * com.smartgwt.client.data.OperationBinding#getRecordXPath recordXPath}, if specified, is used to select records
     * from the returned JSON data via {@link com.smartgwt.client.util.XMLTools#selectObjects}.   {@link
     * com.smartgwt.client.data.DataSourceField#getValueXPath valueXPath} is used to derive correctly typed field
     * values. <li> <code>dataFormat:"custom"</code> : <code>dsResponse.data</code> is the raw response in String form.
     * It must be parsed into an Array of Objects for subsequent processing to work. </ul>  <P> In addition to
     * <code>dsResponse.data</code>, {@link com.smartgwt.client.data.DSResponse#getStatus status} is defaulted to 0
     * (indicating no error), and {@link com.smartgwt.client.data.DSResponse#getStartRow startRow} is assumed to be
     * zero, with {@link com.smartgwt.client.data.DSResponse#getEndRow endRow} and {@link
     * com.smartgwt.client.data.DSResponse#getTotalRows totalRows} both set to <code>dsResponse.data.length - 1</code>,
     * that is, the returned data is assumed to be all records that matched the filter criteria. <P> Examples of using
     * this API include: <ul> <li> setting {@link com.smartgwt.client.data.DSResponse#getStartRow startRow}, {@link
     * com.smartgwt.client.data.DSResponse#getEndRow endRow} and {@link com.smartgwt.client.data.DSResponse#getTotalRows
     * totalRows}      to allow paging for a service that supports it.  For example, if an XML service      returns a
     * "resultRow" tag that contained the row number of the first row of the      returned results:<pre>
     * dsResponse.startRow =         isc.XMLTools.selectNumber(xmlData, "//resultRow"); </pre> <li> setting {@link
     * com.smartgwt.client.data.DSResponse#getStatus status} to recognized ISC error values based on service-specific
     * errors, in order to trigger standard ISC error handling.  For      example, setting
     * <code>dsResponse.status</code> to      {@link com.smartgwt.client.rpc.RPCResponse#STATUS_VALIDATION_ERROR} and
     * filling in      {@link com.smartgwt.client.data.DSResponse#getErrors errors} in order to cause validation errors
     * to be shown in      forms and grids. <li> for services that either do not return cache update data, or return
     * partial data,      using {@link com.smartgwt.client.data.DSRequest#getOldValues oldValues} to create cache update
     * data (whether this is      appropriate is application-specific), or setting      {@link
     * com.smartgwt.client.data.DSResponse#getInvalidateCache invalidateCache}. </ul> <P> <span
     * STYLE="color:red">NOTE:</span> this method is NOT an appropriate time to call methods on visual components such
     * as grids, initiate new DSRequests or RPCRequests, or in general do anything other than fill in fields on the
     * DSResponse based on data that is already available.  Any actions that need to be taken as a result of the web
     * service response should be implemented exactly as for a DataSource where <code>transformResponse()</code> has not
     * been overriden, that is, use the callback passed to high-level methods such as  <code>grid.fetchData()</code>,
     * and do error handling via either {@link com.smartgwt.client.data.DataSource#handleError} or by setting {@link
     * com.smartgwt.client.rpc.RPCRequest#getWillHandleError willHandleError}.
     * <p>
     * <b>Note</b>: This is an override point
     * @param response the response
     * @param request the request
     * @param data
     */
    protected native void transformResponse(DSResponse response, DSRequest request, Object data) /*-{
        var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
        self.__transformResponse(response.@com.smartgwt.client.data.DSResponse::getJsObj()(), request.@com.smartgwt.client.data.DSRequest::getJsObj()(), data);
    }-*/;

    /**
     * Return a "spoofed" response for a {@link com.smartgwt.client.data.DataSource#getClientOnly clientOnly} DataSource.&#010 <P>&#010 The default implementation will {@link com.smartgwt.client.data.DataSource#getTestData testData} to provide an appropriate&#010 response, by using {@link com.smartgwt.client.data.DataSource#applyFilter} for a "fetch" request, and&#010 by modifying the <code>testData</code> for other requests.&#010 <P>&#010 Override this method to provide simulations of other server-side behavior, such as&#010 modifying other records, or to implement <b>synchronous</b> client-side data providers&#010 (such as Google Gears).  For <b>asynchronous</b> third-party data provides, such as&#010 GWT-RPC, HTML5 sockets, or bridges to plug-in based protocols (Java, Flash,&#010 Silverlight..), use ${isc.DocUtils.linkForRef('DSDataProtocol','dataProtocol:"clientCustom"')} instead. &#010 <P>&#010 Overriding this method is also a means of detecting that a normal DataSource (not&#010 clientOnly) would be contacting the server.&#010&#010
     * <p>
     * <b>Note</b>: This is an override point
     * @param request DataSource request to respond to
     *
     * @return DSResponse
    */
    protected native DSResponse getClientOnlyResponse(DSRequest request) /*-{
        var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
        var ret = self.__getClientOnlyResponse(request.@com.smartgwt.client.data.DSRequest::getJsObj()());
        if(ret == null || ret === undefined) return null;
        return @com.smartgwt.client.data.DSResponse::new(Lcom/google/gwt/core/client/JavaScriptObject;)(ret);
    }-*/;

    public native boolean isCreated()/*-{
        var id = this.@com.smartgwt.client.core.BaseClass::getID()();
        var obj = $wnd.isc.DataSource.getDataSource(id);
        return id != null && obj != null && obj !== undefined;
    }-*/;

    public native JavaScriptObject getJsObj()/*-{
        var id = this.@com.smartgwt.client.core.BaseClass::getID()();
        var obj = $wnd.isc.DataSource.getDataSource(id);
        return obj;
    }-*/;

    /**
     * Additional properties to pass through to the {@link com.smartgwt.client.data.DSRequest}s made by this DataSource.
     * <p> These properties are applied before {@link com.smartgwt.client.data.DataSource#transformRequest} is called.
     *
     * @param requestProperties requestProperties Default value is null
     * @throws IllegalStateException this property cannot be changed after the underlying component has been created
     */
    public void setRequestProperties(DSRequest requestProperties) throws IllegalStateException {
        setAttribute("requestProperties", requestProperties, false);
    }


    /**
     * ID of another DataSource this DataSource inherits its DataSource fields from.<br><br>Local fields (fields defined
     * in this DataSource) are added to inherited fields to form the full set of fields. Fields with the same name are
     * merged in the same way that databound component fields are merged with DataSource fields.<br><br>The default
     * order of the combined fields is new local fields first (including any fields present in the parent DataSource
     * which the local DataSource re-declares), then parent fields.  You can set {@link
     * com.smartgwt.client.data.DataSource#setUseParentFieldOrder(Boolean) useParentFieldOrder} to instead use the
     * parent's field order, with new local fields appearing last.<br><br>
     * <p/>
     * You can set {@link com.smartgwt.client.data.DataSource#setShowLocalFieldsOnly(Boolean) showLocalFieldsOnly}  to
     * have all non-local fields hidden.<br><br>Note that &lt;b&gt;only fields are inherited&lt;/b&gt; - other
     * properties such as dataURL and dataFormat are not.  You can use ordinary inheritance, that is, creating a
     * subclass of DataSource, in order to share properties such as dataURL across a series of DataSources that also
     * inherit fields from each other via <code>inheritsFrom</code> <br><br> This feature can be used for
     * creating a customized view (eg, only certain fields shown) which will be used bymultiple databound
     * components.adding presentation-specific attributes to metadata that has been automatically derived from {@link
     * com.smartgwt.client.data.XMLTools#loadXMLSchema(String, XSDLoadCallback)} or other metadata formats modelling
     * object subclassing and extension in server-side code and storage systems modelling relational database joins, and
     * the equivalents in other systems creating hooks for others to customize your application in a maintainable way.
     * For example, if you have a dataSource &quot;employee&quot;, you can create a
     * dataSource&quot;customizedEmployee&quot; which inherits from &quot;employee&quot; but does not initially define
     * anyfields, and bind all databound components to&quot;customizedEmployee&quot;. Customizations of fields
     * (including appearance changes, fieldorder, new fields, hiding of fields, and custom validation rules) can be
     * added to&quot;customizedEmployee&quot;, so that they are kept separtely from the original field data andhave the
     * best possible chance of working with future versions of the &quot;employee&quot;dataSource.&lt;/ul&gt;
     *
     * @param inheritsFrom the datasource to inherit from
     * @throws IllegalStateException this property cannot be changed after the underlying component has been created
     */
    public void setInheritsFrom(DataSource inheritsFrom) throws IllegalStateException {
        setAttribute("inheritsFrom", inheritsFrom.getOrCreateJsObj(), false);
    }

    /**
     * The list of fields that compose records from this DataSource. <P> Each DataSource field can have type,
     * user-visible title, validators, and other metadata attached.
     *
     * @param fields fields Default value is null
     * @throws IllegalStateException this property cannot be changed after the underlying component has been created
     */
    public void setFields(DataSourceField... fields) {
        for (DataSourceField field : fields) {
            addField(field);
        }
    }

    public void addField(DataSourceField field) {
        JavaScriptObject fieldJS = field.getJsObj();
        addFieldPreCreate(fieldJS);
    }

    private native void addFieldPreCreate(JavaScriptObject fieldJS) /*-{
		var config = this.@com.smartgwt.client.core.BaseClass::config;

        if(!config.fields) {
            config.fields = @com.smartgwt.client.util.JSOHelper::createJavaScriptArray()();
        }
        config.fields.push(fieldJS);
    }-*/;

    /**
     * For a client-only DataSource, a set of records to use as a dataset, specified as an Array of JavaScript Objects.
     * <P> See this discussion for ways to populate a client-only DataSource with test data.
     *
     * @param testData testData Default value is null
     */
    public void setTestData(DataClass[] testData) {
        setAttribute("testData", testData, true);
    }

    /**
     * For a client-only DataSource, a set of records to use as a dataset, specified as an Array of JavaScript Objects.
     * <P> See this discussion for ways to populate a client-only DataSource with test data.
     *
     * @return the test data
     */
    public DataClass[] getTestData() {
        return convertToTabArray(getAttributeAsJavaScriptObject("testData"));
    }

    private static DataClass[] convertToTabArray(JavaScriptObject nativeArray) {
        if (nativeArray == null) {
            return new DataClass[]{};
        }
        JavaScriptObject[] componentsj = JSOHelper.toArray(nativeArray);
        DataClass[] objects = new DataClass[componentsj.length];
        for (int i = 0; i < componentsj.length; i++) {
            JavaScriptObject componentJS = componentsj[i];
            DataClass obj = RefDataClass.getRef(componentJS);
            if (obj == null) {
                obj = new DataClass(componentJS);
            }
            objects[i] = obj;
        }
        return objects;
    }

    /**
     * <br>Optional array of OperationBindings, which provide instructions to the DataSource about how each DSOperation
     * is to be performed. <p> For DataSources bound to WSDL-described web services using {@link
     * com.smartgwt.client.data.DataSource#setServiceNamespace(String) serviceNamespace} </a>, OperationBindings are
     * used to bind each DataSource <a href="" onmouseout="isc.Hover.clear()" onmouseover='isc.DocUtils.$46s("attr:OperationBinding.operationType")'
     * onclick='isc.Hover.clear();isc.DocViewer.instance.$46t("attr:OperationBinding.operationType");return
     * false;'>operationType</a> to an <a href="" onmouseout="isc.Hover.clear()" onmouseover='isc.DocUtils.$46s("attr:OperationBinding.wsOperation")'
     * onclick='isc.Hover.clear();isc.DocViewer.instance.$46t("attr:OperationBinding.wsOperation");return
     * false;'>operation</a> of a WSDL-described <a href="" onmouseout="isc.Hover.clear()"
     * onmouseover='isc.DocUtils.$46s("class:WebService")' onclick='isc.Hover.clear();isc.DocViewer.instance.$46t("class:WebService");return
     * false;'>web service</a>, so that a DataSource can both fetch and save data to a web service. </p><p> For example,
     * this code accomplishes part of the binding to the  <a href="http://www.google.com/search?q=sforce+partner+wsdl"
     * onclick="window.open('http://www.google.com/search?q=sforce+partner+wsdl');return false;">SalesForce partner web
     * services</a>
     * </p><pre>
     *  isc.DataSource.create({
     *  serviceNamespace : "urn:partner.soap.sforce.com",
     *     operationBindings : [
     *         { operationType:"fetch", wsOperation:"query", recordName: "sObject" },
     *         { operationType:"update", wsOperation:"update", recordName: "SaveResult" },
     *         { operationType:"add", wsOperation:"create", recordName: "SaveResult" },
     *         { operationType:"remove", wsOperation:"delete", recordName: "DeleteResult" }
     *     ],
     *     ...
     *  });
     *  </pre>
     * <p/> NOTE: additional code is required to handle authentication and other details, see the complete code in
     * isomorphicSDK/examples/databinding/SalesForce. <p> For DataSources that contact non-WSDL-described XML or JSON
     * services, OperationBindings can be used to separately configure the URL, HTTP method, input and output processing
     * for each operationType.  This makes it possible to fetch JSON data from one URL for the "fetch" operationType and
     * save to a web service for the "update" operationType, while appearing as a single integrated DataSource to a <a
     * href="" onmouseout="isc.Hover.clear()" onmouseover='isc.DocUtils.$46s("interface:DataBoundComponent")'
     * onclick='isc.Hover.clear();isc.DocViewer.instance.$46t("interface:DataBoundComponent");return
     * false;'>DataBoundComponent</a> such as an <a href="" onmouseout="isc.Hover.clear()"
     * onmouseover='isc.DocUtils.$46s("attr:ListGrid.canEdit")' onclick='isc.Hover.clear();isc.DocViewer.instance.$46t("attr:ListGrid.canEdit");return
     * false;'>editable ListGrid</a>. <p/> </p><p> If no operationBinding is defined for a given DataSource operation,
     * all of the properties which are valid on the operationBinding are checked for on the DataSource itself. </p><p>
     * This also means that a read-only DataSource, that is, a DataSource only capable of fetch operations,
     * operationBindings need not be specified, and instead all operationBinding properties can be set on the DataSource
     * itself.  An example of using OperationBinding properties directly on the DataSource in order to read an RSS feed
     * can be found here: </p><p> <a href="/examples/databinding/rss_databinding.html"
     * onclick="window.open('/examples/databinding/rss_databinding.html');return false;">/examples/databinding/rss_databinding.html</a>&nbsp;<img
     * src="http://www.smartclient.com/docs/6.5.1/a/system/reference/skin/images/text_code.gif"
     * onclick='isc.DocUtils.showExampleSource("/examples/databinding/rss_databinding.html")'
     * onmouseover="isc.Hover.setAction(isc.DocUtils, isc.DocUtils.doSourceHover,null,300)"
     * onmouseout="isc.Hover.clear()" style="cursor: pointer;" suppress="TRUE" align="texttop" border="0" height="16"
     * width="16"><br><br><b>See Also:</b><br>&nbsp;&nbsp;<a href="" onmouseout="isc.Hover.clear()"
     * onmouseover='isc.DocUtils.$46s("class:OperationBinding")' onclick='isc.Hover.clear();isc.DocViewer.instance.$46t("class:OperationBinding");return
     * false;'>OperationBinding</a> <br><br></p>
     *
     * @param operationBindings the operation bindings
     * @throws IllegalStateException this property cannot be changed after the underlying component has been created
     */
    public void setOperationBindings(OperationBinding... operationBindings) throws IllegalStateException {
        setAttribute("operationBindings", operationBindings, false);
    }

    /**
     * See {@link com.smartgwt.client.data.OperationBinding#getRecordName recordName}.  <code>recordName</code> can be
     * specified directly on the DataSource for a simple read-only DataSource only capable of "fetch" operations.
     *
     * @param recordName Default value is null
     * @throws IllegalStateException this property cannot be changed after the underlying component has been created
     */
    public void setRecordName(String recordName) throws IllegalStateException {
        setAttribute("recordName", recordName, false);
    }

    /**
     * Optional object declaring namespace prefixes for use in OperationBinding.recordXPath and
     * DataSourceField.valueXPath XPath expressions. xmlNamespaces should be specified as a mapping from namespace
     * prefix to namespace URI, for example: <br>
     * <p/>
     * xmlNamespaces : { az : "http://webservices.amazon.com/AWSECommerceService/2005-03-23" } <br> By default, all
     * namespaces declared on the document element (outermost element of the response) are made available with the
     * prefix used in the document itself. Then, for non-WSDL-described XML results, if there is a default namespace on
     * the document element, it is made available with the special prefix "default". <br>
     * <p/>
     * For results of WSDL-described operations, the prefix "service" means the service namespace, that is, the
     * "targetNamespace" on the element from the WSDL file. The prefix "schema" means the namespace of the outermost
     * element in the output message for the current operation. "default" will be the schema namespace if there is one,
     * otherwise the service namespace. <br> For basic information on XML Namespaces and their use in XPath, try the
     * following search: http://www.google.com/search?q=XPath+xml+namespaces
     *
     * @param xmlNamespaces xml namespaces
     * @throws IllegalStateException this property cannot be changed after the underlying component has been created
     */
    public void setXmlNamespaces(XmlNamespaces xmlNamespaces) throws IllegalStateException {
        setAttribute("xmlNamespaces", xmlNamespaces, false);
    }

    /**
     * Perform a "fetch" DataSource operation against this DataSource, sending search criteria and retrieving matching
     * records. <P> In contrast to {@link com.smartgwt.client.widgets.grid.ListGrid#fetchData}, which creates a {@link
     * com.smartgwt.client.data.ResultSet} to manage the returned data, calling <code>dataSource.fetchData()</code>
     * provides the returned data in the callback as a simple JavaScript Array of JavaScript Objects.  Calling
     * <code>dataSource.fetchData()</code> does not automatically update any visual components or caches: code in the
     * callback passed to <code>fetchData()</code> decides what to do with the returned data.  <P> For example, given a
     * ListGrid "myGrid" and a DataSource "employees", the following code would populate "myGrid" with data fetched
     * from
     * the DataSource: <pre>    isc.DataSource.get("employees").fetchData(null, "myGrid.setData(data)"); </pre> Unlike
     * calling <code>myGrid.fetchData()</code>, which creates a {@link com.smartgwt.client.data.ResultSet}, the data
     * provided to the grid is "disconnected" data, unmanaged by SmartGWT's databinding facilities and safe to
     * directly modify.  This is useful when, for example, a ListGrid is being used as a more sophisticated version of
     * HTML's multi-select component. <P> Disconnected datasets may be used to populate various visual components.  For
     * example, while an individual FormItem can be configured to fetch {@link com.smartgwt.client.widgets.form.fields.FormItem#getValueMap
     * valueMap} options from a DataSource via the {@link com.smartgwt.client.widgets.form.fields.FormItem#getOptionDataSource
     * optionDataSource} property, the following code shows storing a dataset to derive valueMaps from later: <pre>
     * isc.DataSource.get("countries").fetchData(null, "window.countries = data");    ... later, a form is created
     * dynamically ...    function showForm() {       isc.DynamicForm.create({           items : [              {
     * name:"country", title:"Pick Country",                valueMap: window.countries.getValueMap("countryId",
     * "countryName")              },       ... </pre> <P> You can also create a ResultSet from the data retrieved from
     * <code>fetchData()</code>, like so: <pre>    isc.DataSource.get("countries").fetchData(null,        function
     * (dsResponse, data) {           isc.ResultSet.create({              dataSource:"countries",
     * allRows:data           })        }    ) </pre> <P> This gives you a dataset that supports client-side filtering
     * (via {@link com.smartgwt.client.data.ResultSet#setCriteria}), can provide  {@link
     * com.smartgwt.client.data.ResultSet#getValueMap}, will  {@link com.smartgwt.client.data.ResultSet#getDisableCacheSync
     * disableCacheSync} to the DataSource made via other components, and can be re-used with multiple visual
     * components. <P> See also the server-side com.isomorphic.js.JSTranslater class in the
     * ${isc.DocUtils.linkForDocNode('javaServerReference', 'Java Server Reference')} for other, similar approaches
     * involving dumping data into the page during initial page load.  <b>Note:</b> care should be taken when using this
     * approach.  Large datasets degrade the basic performance of some browsers, so use {@link
     * com.smartgwt.client..PickList#getOptionDataSource optionDataSource} and similar facilities to manage datasets
     * that may become very large. <P> <b>Data-Driven Visual Component Creation</b> <P>
     * <code>DataSource.fetchData()</code> can also be used to create SmartGWT components in a data-driven way.  Many
     * properties on SmartGWT visual components are configured via an Array of Objects - the same data format that
     * <code>dataSource.fetchData()</code> returns.  These include {@link com.smartgwt.client.widgets.grid.ListGrid#getFields
     * fields}, {@link com.smartgwt.client.widgets.tab.TabSet#getTabs tabs}, {@link
     * com.smartgwt.client.widgets.form.DynamicForm#getItems items}, {@link com.smartgwt.client..Facet#getValues values}
     * and even {@link com.smartgwt.client.data.DataSource#getFields fields}. <P> For example, if you had a DataSource
     * "myFormFields" whose fields included the basic properties of {@link com.smartgwt.client.widgets.form.fields.FormItem}
     * (name, title, type, etc), this example code would create a form based on stored field definitions, loaded from
     * the "myFormFields" DataSource on the fly: <pre>    isc.DataSource.get("myFormFields").fetchData(null,
     * "isc.DynamicForm.create({ items:data })"    ) </pre> This capability to dynamically create visual components
     * from
     * dynamically fetched data provides a foundation for creating interfaces that can be customized by end users. See
     * also {@link com.smartgwt.client.data.DataSource#getInheritsFrom inheritsFrom}.
     */
    public native void fetchData() /*-{
        var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
        self.fetchData();
    }-*/;

    /**
     * Perform a "fetch" DataSource operation against this DataSource, sending search criteria and retrieving matching
     * records. <P> In contrast to {@link com.smartgwt.client.widgets.grid.ListGrid#fetchData}, which creates a {@link
     * com.smartgwt.client.data.ResultSet} to manage the returned data, calling <code>dataSource.fetchData()</code>
     * provides the returned data in the callback as a simple JavaScript Array of JavaScript Objects.  Calling
     * <code>dataSource.fetchData()</code> does not automatically update any visual components or caches: code in the
     * callback passed to <code>fetchData()</code> decides what to do with the returned data.  <P> For example, given a
     * ListGrid "myGrid" and a DataSource "employees", the following code would populate "myGrid" with data fetched
     * from
     * the DataSource: <pre>    isc.DataSource.get("employees").fetchData(null, "myGrid.setData(data)"); </pre> Unlike
     * calling <code>myGrid.fetchData()</code>, which creates a {@link com.smartgwt.client.data.ResultSet}, the data
     * provided to the grid is "disconnected" data, unmanaged by SmartGWT's databinding facilities and safe to
     * directly modify.  This is useful when, for example, a ListGrid is being used as a more sophisticated version of
     * HTML's multi-select component. <P> Disconnected datasets may be used to populate various visual components.  For
     * example, while an individual FormItem can be configured to fetch {@link com.smartgwt.client.widgets.form.fields.FormItem#getValueMap
     * valueMap} options from a DataSource via the {@link com.smartgwt.client.widgets.form.fields.FormItem#getOptionDataSource
     * optionDataSource} property, the following code shows storing a dataset to derive valueMaps from later: <pre>
     * isc.DataSource.get("countries").fetchData(null, "window.countries = data");    ... later, a form is created
     * dynamically ...    function showForm() {       isc.DynamicForm.create({           items : [              {
     * name:"country", title:"Pick Country",                valueMap: window.countries.getValueMap("countryId",
     * "countryName")              },       ... </pre> <P> You can also create a ResultSet from the data retrieved from
     * <code>fetchData()</code>, like so: <pre>    isc.DataSource.get("countries").fetchData(null,        function
     * (dsResponse, data) {           isc.ResultSet.create({              dataSource:"countries",
     * allRows:data           })        }    ) </pre> <P> This gives you a dataset that supports client-side filtering
     * (via {@link com.smartgwt.client.data.ResultSet#setCriteria}), can provide  {@link
     * com.smartgwt.client.data.ResultSet#getValueMap}, will  {@link com.smartgwt.client.data.ResultSet#getDisableCacheSync
     * disableCacheSync} to the DataSource made via other components, and can be re-used with multiple visual
     * components. <P> See also the server-side com.isomorphic.js.JSTranslater class in the
     * ${isc.DocUtils.linkForDocNode('javaServerReference', 'Java Server Reference')} for other, similar approaches
     * involving dumping data into the page during initial page load.  <b>Note:</b> care should be taken when using this
     * approach.  Large datasets degrade the basic performance of some browsers, so use {@link
     * com.smartgwt.client..PickList#getOptionDataSource optionDataSource} and similar facilities to manage datasets
     * that may become very large. <P> <b>Data-Driven Visual Component Creation</b> <P>
     * <code>DataSource.fetchData()</code> can also be used to create SmartGWT components in a data-driven way.  Many
     * properties on SmartGWT visual components are configured via an Array of Objects - the same data format that
     * <code>dataSource.fetchData()</code> returns.  These include {@link com.smartgwt.client.widgets.grid.ListGrid#getFields
     * fields}, {@link com.smartgwt.client.widgets.tab.TabSet#getTabs tabs}, {@link
     * com.smartgwt.client.widgets.form.DynamicForm#getItems items}, {@link com.smartgwt.client..Facet#getValues values}
     * and even {@link com.smartgwt.client.data.DataSource#getFields fields}. <P> For example, if you had a DataSource
     * "myFormFields" whose fields included the basic properties of {@link com.smartgwt.client.widgets.form.fields.FormItem}
     * (name, title, type, etc), this example code would create a form based on stored field definitions, loaded from
     * the "myFormFields" DataSource on the fly: <pre>    isc.DataSource.get("myFormFields").fetchData(null,
     * "isc.DynamicForm.create({ items:data })"    ) </pre> This capability to dynamically create visual components
     * from
     * dynamically fetched data provides a foundation for creating interfaces that can be customized by end users. See
     * also {@link com.smartgwt.client.data.DataSource#getInheritsFrom inheritsFrom}.
     *
     * @param criteria search criteria
     */
    public native void fetchData(Criteria criteria) /*-{
        var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
        self.fetchData(criteria == null ? null : criteria.@com.smartgwt.client.data.Criteria::getJsObj()());
    }-*/;

    /**
     * Perform a "fetch" DataSource operation against this DataSource, sending search criteria and retrieving matching
     * records. <P> In contrast to {@link com.smartgwt.client.widgets.grid.ListGrid#fetchData}, which creates a {@link
     * com.smartgwt.client.data.ResultSet} to manage the returned data, calling <code>dataSource.fetchData()</code>
     * provides the returned data in the callback as a simple JavaScript Array of JavaScript Objects.  Calling
     * <code>dataSource.fetchData()</code> does not automatically update any visual components or caches: code in the
     * callback passed to <code>fetchData()</code> decides what to do with the returned data.  <P> For example, given a
     * ListGrid "myGrid" and a DataSource "employees", the following code would populate "myGrid" with data fetched
     * from
     * the DataSource: <pre>    isc.DataSource.get("employees").fetchData(null, "myGrid.setData(data)"); </pre> Unlike
     * calling <code>myGrid.fetchData()</code>, which creates a {@link com.smartgwt.client.data.ResultSet}, the data
     * provided to the grid is "disconnected" data, unmanaged by SmartGWT's databinding facilities and safe to
     * directly modify.  This is useful when, for example, a ListGrid is being used as a more sophisticated version of
     * HTML's multi-select component. <P> Disconnected datasets may be used to populate various visual components.  For
     * example, while an individual FormItem can be configured to fetch {@link com.smartgwt.client.widgets.form.fields.FormItem#getValueMap
     * valueMap} options from a DataSource via the {@link com.smartgwt.client.widgets.form.fields.FormItem#getOptionDataSource
     * optionDataSource} property, the following code shows storing a dataset to derive valueMaps from later: <pre>
     * isc.DataSource.get("countries").fetchData(null, "window.countries = data");    ... later, a form is created
     * dynamically ...    function showForm() {       isc.DynamicForm.create({           items : [              {
     * name:"country", title:"Pick Country",                valueMap: window.countries.getValueMap("countryId",
     * "countryName")              },       ... </pre> <P> You can also create a ResultSet from the data retrieved from
     * <code>fetchData()</code>, like so: <pre>    isc.DataSource.get("countries").fetchData(null,        function
     * (dsResponse, data) {           isc.ResultSet.create({              dataSource:"countries",
     * allRows:data           })        }    ) </pre> <P> This gives you a dataset that supports client-side filtering
     * (via {@link com.smartgwt.client.data.ResultSet#setCriteria}), can provide  {@link
     * com.smartgwt.client.data.ResultSet#getValueMap}, will  {@link com.smartgwt.client.data.ResultSet#getDisableCacheSync
     * disableCacheSync} to the DataSource made via other components, and can be re-used with multiple visual
     * components. <P> See also the server-side com.isomorphic.js.JSTranslater class in the
     * ${isc.DocUtils.linkForDocNode('javaServerReference', 'Java Server Reference')} for other, similar approaches
     * involving dumping data into the page during initial page load.  <b>Note:</b> care should be taken when using this
     * approach.  Large datasets degrade the basic performance of some browsers, so use {@link
     * com.smartgwt.client..PickList#getOptionDataSource optionDataSource} and similar facilities to manage datasets
     * that may become very large. <P> <b>Data-Driven Visual Component Creation</b> <P>
     * <code>DataSource.fetchData()</code> can also be used to create SmartGWT components in a data-driven way.  Many
     * properties on SmartGWT visual components are configured via an Array of Objects - the same data format that
     * <code>dataSource.fetchData()</code> returns.  These include {@link com.smartgwt.client.widgets.grid.ListGrid#getFields
     * fields}, {@link com.smartgwt.client.widgets.tab.TabSet#getTabs tabs}, {@link
     * com.smartgwt.client.widgets.form.DynamicForm#getItems items}, {@link com.smartgwt.client..Facet#getValues values}
     * and even {@link com.smartgwt.client.data.DataSource#getFields fields}. <P> For example, if you had a DataSource
     * "myFormFields" whose fields included the basic properties of {@link com.smartgwt.client.widgets.form.fields.FormItem}
     * (name, title, type, etc), this example code would create a form based on stored field definitions, loaded from
     * the "myFormFields" DataSource on the fly: <pre>    isc.DataSource.get("myFormFields").fetchData(null,
     * "isc.DynamicForm.create({ items:data })"    ) </pre> This capability to dynamically create visual components
     * from
     * dynamically fetched data provides a foundation for creating interfaces that can be customized by end users. See
     * also {@link com.smartgwt.client.data.DataSource#getInheritsFrom inheritsFrom}.
     *
     * @param criteria search criteria
     * @param callback callback to invoke on completion
     */
    public native void fetchData(Criteria criteria, DSCallback callback) /*-{
        var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
        var critJS = criteria == null ? null : criteria.@com.smartgwt.client.data.Criteria::getJsObj()();
        self.fetchData(critJS, function (dsResponse, data, dsRequest) {
            var responseJ = @com.smartgwt.client.data.DSResponse::new(Lcom/google/gwt/core/client/JavaScriptObject;)(dsResponse);
            var requestJ = @com.smartgwt.client.data.DSRequest::new(Lcom/google/gwt/core/client/JavaScriptObject;)(dsRequest);
            if(callback != null) callback.@com.smartgwt.client.data.DSCallback::execute(Lcom/smartgwt/client/data/DSResponse;Ljava/lang/Object;Lcom/smartgwt/client/data/DSRequest;)(responseJ, data, requestJ);
        });
    }-*/;

    /**
     * Perform a "fetch" DataSource operation against this DataSource, sending search criteria and retrieving matching
     * records. <P> In contrast to {@link com.smartgwt.client.widgets.grid.ListGrid#fetchData}, which creates a {@link
     * com.smartgwt.client.data.ResultSet} to manage the returned data, calling <code>dataSource.fetchData()</code>
     * provides the returned data in the callback as a simple JavaScript Array of JavaScript Objects.  Calling
     * <code>dataSource.fetchData()</code> does not automatically update any visual components or caches: code in the
     * callback passed to <code>fetchData()</code> decides what to do with the returned data.  <P> For example, given a
     * ListGrid "myGrid" and a DataSource "employees", the following code would populate "myGrid" with data fetched
     * from
     * the DataSource: <pre>    isc.DataSource.get("employees").fetchData(null, "myGrid.setData(data)"); </pre> Unlike
     * calling <code>myGrid.fetchData()</code>, which creates a {@link com.smartgwt.client.data.ResultSet}, the data
     * provided to the grid is "disconnected" data, unmanaged by SmartGWT's databinding facilities and safe to
     * directly modify.  This is useful when, for example, a ListGrid is being used as a more sophisticated version of
     * HTML's multi-select component. <P> Disconnected datasets may be used to populate various visual components.  For
     * example, while an individual FormItem can be configured to fetch {@link com.smartgwt.client.widgets.form.fields.FormItem#getValueMap
     * valueMap} options from a DataSource via the {@link com.smartgwt.client.widgets.form.fields.FormItem#getOptionDataSource
     * optionDataSource} property, the following code shows storing a dataset to derive valueMaps from later: <pre>
     * isc.DataSource.get("countries").fetchData(null, "window.countries = data");    ... later, a form is created
     * dynamically ...    function showForm() {       isc.DynamicForm.create({           items : [              {
     * name:"country", title:"Pick Country",                valueMap: window.countries.getValueMap("countryId",
     * "countryName")              },       ... </pre> <P> You can also create a ResultSet from the data retrieved from
     * <code>fetchData()</code>, like so: <pre>    isc.DataSource.get("countries").fetchData(null,        function
     * (dsResponse, data) {           isc.ResultSet.create({              dataSource:"countries",
     * allRows:data           })        }    ) </pre> <P> This gives you a dataset that supports client-side filtering
     * (via {@link com.smartgwt.client.data.ResultSet#setCriteria}), can provide  {@link
     * com.smartgwt.client.data.ResultSet#getValueMap}, will  {@link com.smartgwt.client.data.ResultSet#getDisableCacheSync
     * disableCacheSync} to the DataSource made via other components, and can be re-used with multiple visual
     * components. <P> See also the server-side com.isomorphic.js.JSTranslater class in the
     * ${isc.DocUtils.linkForDocNode('javaServerReference', 'Java Server Reference')} for other, similar approaches
     * involving dumping data into the page during initial page load.  <b>Note:</b> care should be taken when using this
     * approach.  Large datasets degrade the basic performance of some browsers, so use {@link
     * com.smartgwt.client..PickList#getOptionDataSource optionDataSource} and similar facilities to manage datasets
     * that may become very large. <P> <b>Data-Driven Visual Component Creation</b> <P>
     * <code>DataSource.fetchData()</code> can also be used to create SmartGWT components in a data-driven way.  Many
     * properties on SmartGWT visual components are configured via an Array of Objects - the same data format that
     * <code>dataSource.fetchData()</code> returns.  These include {@link com.smartgwt.client.widgets.grid.ListGrid#getFields
     * fields}, {@link com.smartgwt.client.widgets.tab.TabSet#getTabs tabs}, {@link
     * com.smartgwt.client.widgets.form.DynamicForm#getItems items}, {@link com.smartgwt.client..Facet#getValues values}
     * and even {@link com.smartgwt.client.data.DataSource#getFields fields}. <P> For example, if you had a DataSource
     * "myFormFields" whose fields included the basic properties of {@link com.smartgwt.client.widgets.form.fields.FormItem}
     * (name, title, type, etc), this example code would create a form based on stored field definitions, loaded from
     * the "myFormFields" DataSource on the fly: <pre>    isc.DataSource.get("myFormFields").fetchData(null,
     * "isc.DynamicForm.create({ items:data })"    ) </pre> This capability to dynamically create visual components
     * from
     * dynamically fetched data provides a foundation for creating interfaces that can be customized by end users. See
     * also {@link com.smartgwt.client.data.DataSource#getInheritsFrom inheritsFrom}.
     *
     * @param criteria          search criteria
     * @param callback          callback to invoke on completion
     * @param requestProperties additional properties to set on the DSRequest that will be issued
     */
    public native void fetchData(Criteria criteria, DSCallback callback, DSRequest requestProperties) /*-{
        var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
        var critJS = criteria == null ? null : criteria.@com.smartgwt.client.data.Criteria::getJsObj()();
        var requestPropertiesJS = requestProperties == null ? null : requestProperties.@com.smartgwt.client.core.DataClass::getJsObj()();
        self.fetchData(critJS, function (dsResponse, data, dsRequest) {
            var responseJ = @com.smartgwt.client.data.DSResponse::new(Lcom/google/gwt/core/client/JavaScriptObject;)(dsResponse);
            var requestJ = @com.smartgwt.client.data.DSRequest::new(Lcom/google/gwt/core/client/JavaScriptObject;)(dsRequest);
            if(callback != null) callback.@com.smartgwt.client.data.DSCallback::execute(Lcom/smartgwt/client/data/DSResponse;Ljava/lang/Object;Lcom/smartgwt/client/data/DSRequest;)(responseJ, data, requestJ);
        }, requestPropertiesJS);
    }-*/;

    /**
     * Perform a "fetch" DataSource operation against this DataSource, sending search criteria and retrieving matching
     * records. <P> This is identical to {@link com.smartgwt.client.data.DataSource#fetchData} except that {@link
     * com.smartgwt.client.data.DSRequest#getTextMatchStyle textMatchStyle} is set to "substring" to cause case
     * insensitive substring matching (if the server respects this setting).
     *
     * @param criteria search criteria
     */
    public native void filterData(Criteria criteria) /*-{
        var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
        self.filterData(criteria == null ? null : criteria.@com.smartgwt.client.data.Criteria::getJsObj()());
    }-*/;

    /**
     * Perform a "fetch" DataSource operation against this DataSource, sending search criteria and retrieving matching
     * records. <P> This is identical to {@link com.smartgwt.client.data.DataSource#fetchData} except that {@link
     * com.smartgwt.client.data.DSRequest#getTextMatchStyle textMatchStyle} is set to "substring" to cause case
     * insensitive substring matching (if the server respects this setting).
     *
     * @param criteria search criteria
     * @param callback callback to invoke on completion
     */
    public native void filterData(Criteria criteria, DSCallback callback) /*-{
        var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
        var critJS = criteria == null ? null : criteria.@com.smartgwt.client.data.Criteria::getJsObj()();
        self.filterData(critJS, function (dsResponse, data, dsRequest) {
            var responseJ = @com.smartgwt.client.data.DSResponse::new(Lcom/google/gwt/core/client/JavaScriptObject;)(dsResponse);
            var requestJ = @com.smartgwt.client.data.DSRequest::new(Lcom/google/gwt/core/client/JavaScriptObject;)(dsRequest);
            if(callback != null) callback.@com.smartgwt.client.data.DSCallback::execute(Lcom/smartgwt/client/data/DSResponse;Ljava/lang/Object;Lcom/smartgwt/client/data/DSRequest;)(responseJ, data, requestJ);
        });
    }-*/;

    public native void filterData(Criteria criteria, DSCallback callback, DSRequest requestProperties) /*-{
        var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
        var critJS = criteria == null ? null : criteria.@com.smartgwt.client.data.Criteria::getJsObj()();
        var requestPropertiesJS = requestProperties == null ? null : requestProperties.@com.smartgwt.client.core.DataClass::getJsObj()();
        self.filterData(critJS, function (dsResponse, data, dsRequest) {
            var responseJ = @com.smartgwt.client.data.DSResponse::new(Lcom/google/gwt/core/client/JavaScriptObject;)(dsResponse);
            var requestJ = @com.smartgwt.client.data.DSRequest::new(Lcom/google/gwt/core/client/JavaScriptObject;)(dsRequest);
            if(callback != null) callback.@com.smartgwt.client.data.DSCallback::execute(Lcom/smartgwt/client/data/DSResponse;Ljava/lang/Object;Lcom/smartgwt/client/data/DSRequest;)(responseJ, data, requestJ);
        }, requestPropertiesJS);
    }-*/;

    /**
     * Perform an "add" DataSource operation against this DataSource, to create a new DataSource record.
     *
     * @param record new record
     */
    public native void addData(Record record) /*-{
        var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
        var recordJS = record.@com.smartgwt.client.data.Record::getJsObj()();
        self.addData(recordJS);
    }-*/;

    /**
     * Perform an "add" DataSource operation against this DataSource, to create a new DataSource record.
     *
     * @param record new record
     * @param callback  callback to invoke on completion
     */
    public native void addData(Record record, DSCallback callback) /*-{
        var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
        var recordJS = record.@com.smartgwt.client.data.Record::getJsObj()();
        self.addData(recordJS, function (dsResponse, data, dsRequest) {
            var responseJ = @com.smartgwt.client.data.DSResponse::new(Lcom/google/gwt/core/client/JavaScriptObject;)(dsResponse);
            var requestJ = @com.smartgwt.client.data.DSRequest::new(Lcom/google/gwt/core/client/JavaScriptObject;)(dsRequest);
            if(callback != null) callback.@com.smartgwt.client.data.DSCallback::execute(Lcom/smartgwt/client/data/DSResponse;Ljava/lang/Object;Lcom/smartgwt/client/data/DSRequest;)(responseJ, data, requestJ);
        });
    }-*/;

    /**
     * Perform an "add" DataSource operation against this DataSource, to create a new DataSource record.
     *
     * @param record         new record
     * @param callback          callback to invoke on completion
     * @param requestProperties additional properties to set on the DSRequest that will be issued
     */
    public native void addData(Record record, DSCallback callback, DSRequest requestProperties) /*-{
        var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
        var recordJS = record.@com.smartgwt.client.data.Record::getJsObj()();
        var requestPropertiesJS = requestProperties == null ? null : requestProperties.@com.smartgwt.client.core.DataClass::getJsObj()();
        self.addData(recordJS, function (dsResponse, data, dsRequest) {
            var responseJ = @com.smartgwt.client.data.DSResponse::new(Lcom/google/gwt/core/client/JavaScriptObject;)(dsResponse);
            var requestJ = @com.smartgwt.client.data.DSRequest::new(Lcom/google/gwt/core/client/JavaScriptObject;)(dsRequest);
            if(callback != null) callback.@com.smartgwt.client.data.DSCallback::execute(Lcom/smartgwt/client/data/DSResponse;Ljava/lang/Object;Lcom/smartgwt/client/data/DSRequest;)(responseJ, data, requestJ);
        }, requestPropertiesJS);
    }-*/;

    /**
     * Perform an "update" DataSource operation against this DataSource, to update values in an existing DataSource
     * record.
     *
     * @param record updated record
     */
    public native void updateData(Record record) /*-{
        var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
        var recordJS = record.@com.smartgwt.client.data.Record::getJsObj()();
        self.updateData(recordJS);
    }-*/;

    /**
     * Perform an "update" DataSource operation against this DataSource, to update values in an existing DataSource
     * record.
     *
     * @param record updated record
     * @param callback      callback to invoke on completion
     */
    public native void updateData(Record record, DSCallback callback) /*-{
        var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
        var recordJS = record.@com.smartgwt.client.data.Record::getJsObj()();
        self.updateData(recordJS, function (dsResponse, data, dsRequest) {
            var responseJ = @com.smartgwt.client.data.DSResponse::new(Lcom/google/gwt/core/client/JavaScriptObject;)(dsResponse);
            var requestJ = @com.smartgwt.client.data.DSRequest::new(Lcom/google/gwt/core/client/JavaScriptObject;)(dsRequest);
            if(callback != null) callback.@com.smartgwt.client.data.DSCallback::execute(Lcom/smartgwt/client/data/DSResponse;Ljava/lang/Object;Lcom/smartgwt/client/data/DSRequest;)(responseJ, data, requestJ);
        });
    }-*/;

    /**
     * Perform an "update" DataSource operation against this DataSource, to update values in an existing DataSource
     * record.
     *
     * @param record     updated record
     * @param callback          callback to invoke on completion
     * @param requestProperties additional properties to set on the DSRequest that will be issued
     */
    public native void updateData(Record record, DSCallback callback, DSRequest requestProperties) /*-{
        var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
        var recordJS = record.@com.smartgwt.client.data.Record::getJsObj()();
        var requestPropertiesJS = requestProperties == null ? null : requestProperties.@com.smartgwt.client.core.DataClass::getJsObj()();
        self.updateData(recordJS, function (dsResponse, data, dsRequest) {
            var responseJ = @com.smartgwt.client.data.DSResponse::new(Lcom/google/gwt/core/client/JavaScriptObject;)(dsResponse);
            var requestJ = @com.smartgwt.client.data.DSRequest::new(Lcom/google/gwt/core/client/JavaScriptObject;)(dsRequest);
            if(callback != null) callback.@com.smartgwt.client.data.DSCallback::execute(Lcom/smartgwt/client/data/DSResponse;Ljava/lang/Object;Lcom/smartgwt/client/data/DSRequest;)(responseJ, data, requestJ);
        }, requestPropertiesJS);
    }-*/;

    /**
     * Perform a "remove" DataSource operation against this DataSource, to delete an existing DataSource record.
     *
     * @param record primary key values of record to delete,                                             (or complete
     *             record)
     */
    public native void removeData(Record record) /*-{
        var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
        var recordJS = record.@com.smartgwt.client.data.Record::getJsObj()();
        self.removeData(recordJS);
    }-*/;

    /**
     * Perform a "remove" DataSource operation against this DataSource, to delete an existing DataSource record.
     *
     * @param record     primary key values of record to delete,                                             (or complete
     *                 record)
     * @param callback callback to invoke on completion
     */
    public native void removeData(Record record, DSCallback callback) /*-{
        var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
        var recordJS = record.@com.smartgwt.client.data.Record::getJsObj()();
        self.removeData(recordJS, function (dsResponse, data, dsRequest) {
            var responseJ = @com.smartgwt.client.data.DSResponse::new(Lcom/google/gwt/core/client/JavaScriptObject;)(dsResponse);
            var requestJ = @com.smartgwt.client.data.DSRequest::new(Lcom/google/gwt/core/client/JavaScriptObject;)(dsRequest);
            if(callback != null) callback.@com.smartgwt.client.data.DSCallback::execute(Lcom/smartgwt/client/data/DSResponse;Ljava/lang/Object;Lcom/smartgwt/client/data/DSRequest;)(responseJ, data, requestJ);
        });
    }-*/;

    /**
     * Perform a "remove" DataSource operation against this DataSource, to delete an existing DataSource record.
     *
     * @param record              primary key values of record to delete,(or complete record)
     * @param callback          callback to invoke on completion
     * @param requestProperties additional properties to set on the DSRequest that will be issued
     */
    public native void removeData(Record record, DSCallback callback, DSRequest requestProperties) /*-{
        var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
        var recordJS = record.@com.smartgwt.client.data.Record::getJsObj()();
        var requestPropertiesJS = requestProperties == null ? null : requestProperties.@com.smartgwt.client.core.DataClass::getJsObj()();
        self.removeData(recordJS, function (dsResponse, data, dsRequest) {
            var responseJ = @com.smartgwt.client.data.DSResponse::new(Lcom/google/gwt/core/client/JavaScriptObject;)(dsResponse);
            var requestJ = @com.smartgwt.client.data.DSRequest::new(Lcom/google/gwt/core/client/JavaScriptObject;)(dsRequest);
            if(callback != null) callback.@com.smartgwt.client.data.DSCallback::execute(Lcom/smartgwt/client/data/DSResponse;Ljava/lang/Object;Lcom/smartgwt/client/data/DSRequest;)(responseJ, data, requestJ);
        }, requestPropertiesJS);
    }-*/;

   /**
    * Serialize a JavaScript object as XML.&#010 <P>&#010 The JavaScript Object passed to {@link com.smartgwt.client.data.DataSource#xmlSerialize} becomes an XML&#010 element named after the {@link com.smartgwt.client.data.DataSource#getTagName tagName} (or {@link com.smartgwt.client.data.DataSource#getID ID} if&#010 tagName is unset).  Each property of the object becomes a subElement.  For example,&#010 using a DataSource to serialize like this:&#010 <pre>&#010     var inputObject = {&#010        startRow : 5,&#010        endRow : 50,&#010        data : [&#010           { field1 : "value1", field2: new Date() },&#010           { field1 : "value3", field2: null }&#010        ]&#010     };&#010     var myDS = isc.DataSource.create({ tagName:"DSRequest" });&#010     myDS.xmlSerialize(inputObject);&#010 </pre>&#010 .. produces the following XML:&#010 <pre>&#010     &lt;DSRequest&gt;&#010         &lt;startRow&gt;5&lt;/startRow&gt;&#010         &lt;endRow&gt;50&lt;/endRow&gt;&#010         &lt;data&gt;&#010             &lt;field1&gt;value1&lt;/field1&gt;&#010             &lt;field2&gt;2005-10-14T18:01:16&lt;/field2&gt;&#010         &lt;/data&gt;&#010         &lt;data&gt;&#010             &lt;field1&gt;value3&lt;/field1&gt;&#010             &lt;field2&gt;&lt;/field2&gt;&#010         &lt;/data&gt;&#010     &lt;/DSRequest&gt;&#010 </pre>&#010 <P>&#010 If you are working with a WSDL-described web service, XML serialization is performed&#010 automatically by APIs like {@link com.smartgwt.client.data.WebService#callOperation} - you only need to&#010 know about serialization in order to understand how to put together JavaScript data that&#010 will fill in an XML message properly.   &#010 <br>&#010 <b>Note:</b> when trying to send data to a web service, it is best to avoid putting&#010 together any XML yourself, instead modify the JavaScript data being fed to ISC's SOAP&#010 engine.  This is because the WSDL and SOAP rules for correctly namespacing and encoding&#010 Web Service messages are very complex and are subject to change with new versions of the&#010 web service you are contacting, whereas the data itself is easy to manipulate and less&#010 likely to change.&#010 <br>&#010 To troubleshoot message formation, you can set the log category "xmlComm" to &#010 <code>DEBUG</code> level in order to see the XML message formed by SmartGWT reported&#010 in log statements in the Developer Console.&#010&#010
    * @param data data to be serialized
    *
    * @return data as serialized to XML
    */
    public native String xmlSerialize(JavaScriptObject data) /*-{
        var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
        return self.xmlSerialize(data);
    }-*/;


    /**
     * Serialize a JavaScript object as XML.&#010 <P>&#010 The JavaScript Object passed to {@link com.smartgwt.client.data.DataSource#xmlSerialize} becomes an XML&#010 element named after the {@link com.smartgwt.client.data.DataSource#getTagName tagName} (or {@link com.smartgwt.client.data.DataSource#getID ID} if&#010 tagName is unset).  Each property of the object becomes a subElement.  For example,&#010 using a DataSource to serialize like this:&#010 <pre>&#010     var inputObject = {&#010        startRow : 5,&#010        endRow : 50,&#010        data : [&#010           { field1 : "value1", field2: new Date() },&#010           { field1 : "value3", field2: null }&#010        ]&#010     };&#010     var myDS = isc.DataSource.create({ tagName:"DSRequest" });&#010     myDS.xmlSerialize(inputObject);&#010 </pre>&#010 .. produces the following XML:&#010 <pre>&#010     &lt;DSRequest&gt;&#010         &lt;startRow&gt;5&lt;/startRow&gt;&#010         &lt;endRow&gt;50&lt;/endRow&gt;&#010         &lt;data&gt;&#010             &lt;field1&gt;value1&lt;/field1&gt;&#010             &lt;field2&gt;2005-10-14T18:01:16&lt;/field2&gt;&#010         &lt;/data&gt;&#010         &lt;data&gt;&#010             &lt;field1&gt;value3&lt;/field1&gt;&#010             &lt;field2&gt;&lt;/field2&gt;&#010         &lt;/data&gt;&#010     &lt;/DSRequest&gt;&#010 </pre>&#010 <P>&#010 If you are working with a WSDL-described web service, XML serialization is performed&#010 automatically by APIs like {@link com.smartgwt.client.data.WebService#callOperation} - you only need to&#010 know about serialization in order to understand how to put together JavaScript data that&#010 will fill in an XML message properly.   &#010 <br>&#010 <b>Note:</b> when trying to send data to a web service, it is best to avoid putting&#010 together any XML yourself, instead modify the JavaScript data being fed to ISC's SOAP&#010 engine.  This is because the WSDL and SOAP rules for correctly namespacing and encoding&#010 Web Service messages are very complex and are subject to change with new versions of the&#010 web service you are contacting, whereas the data itself is easy to manipulate and less&#010 likely to change.&#010 <br>&#010 To troubleshoot message formation, you can set the log category "xmlComm" to &#010 <code>DEBUG</code> level in order to see the XML message formed by SmartGWT reported&#010 in log statements in the Developer Console.&#010&#010
     * @param data data to be serialized
     * @param flags options for the serialization engine
     *
     * @return data as serialized to XML
     */
    public native String xmlSerialize(JavaScriptObject data, SerializationContext flags) /*-{
        var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
        var flagsJS = flags.@com.smartgwt.client.core.DataClass::getJsObj()();
        return self.xmlSerialize(data, flagsJS);
    }-*/;

    /**
     * Process a dsResponse for a request initiated by a DataSource with {@link com.smartgwt.client.data.OperationBinding#getDataProtocol dataProtocol}. <code>requestId</code> parameter should be dsRequest.requestId as found on the dsRequest passed to {@link com.smartgwt.client.data.DataSource#transformRequest}. <P> You must provide a response for both error and non-error cases.  For an error case, a sufficient response is: <pre> { status : -1 } </pre>
     * @param requestId requestId attribute from the associated dataSource request object
     * @param requestId the request ID
     * @param requestId Configuration for the dsResponse
     */
    public native void processResponse(String requestId, DSResponse responseProperties) /*-{
        var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
        var responsePropertiesJS = responseProperties == null ? null : responseProperties.@com.smartgwt.client.core.DataClass::getJsObj()();
        self.processResponse(requestId, responsePropertiesJS);
    }-*/;

   /**
    * Transform a list of XML elements to DataSource records.
    * <p>
    * recordsFromXML() will return a List of DataSource Records. The value for each field is extracted from the XML according
    * to the rules described under {@link DataSourceField#setValueXPath(String) valueXPath}.
    *
    * @param elements XML elements to transform, eg, the result of a call to {@link com.smartgwt.client.data.XMLTools#selectNodes(Object, String)}
    *
    * @return list of DataSource records derived from the XML elements
    */
    public native Record[] recordsFromXML(Object elements) /*-{
		var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
		var records = self.recordsFromXML(elements);
        if(records == null || records === undefined) return null;
        return @com.smartgwt.client.data.Record::convertToRecordArray(Lcom/google/gwt/core/client/JavaScriptObject;)(records);
	}-*/;    

}



