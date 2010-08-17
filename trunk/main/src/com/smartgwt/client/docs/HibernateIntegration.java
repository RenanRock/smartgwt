
package com.smartgwt.client.docs;

/**
 * <h3>Integration with Hibernate</h3>
 * Smart GWT can integrate with Hibernate in two main ways:&#010 <ul>&#010 <li> With pre-existing Hibernate configuration
 * and Java beans, Smart GWT&#010 server-side DSRequests can be transformed into Hibernate <code>Criteria</code>
 * objects,&#010 and results returned via a Smart GWT DSResponse.  &#010 <li> Smart GWT can drive Hibernate as a storage
 * layer only, automatically generating&#010 Hibernate configuration from a Smart GWT DataSource file
 * (<i>dataSourceID</i>.ds.xml).  In&#010 this case, you do not write a Java bean; Hibernate's beanless &#010 <a
 * href='http://www.hibernate.org/hib_docs/v3/reference/en/html_single/#persistent-classes-dynamicmodels'
 * onclick="window.open('http://www.hibernate.org/hib_docs/v3/reference/en/html_single/#persistent-classes-dynamicmodels');return
 * false;">"dynamic model"</a>&#010 mode is used.  This is enabled via {@link
 * com.smartgwt.client.docs.serverds.DataSource#serverType serverType}:"hibernate".&#010 </ul>&#010 <P>&#010 <b>Hibernate
 * Configuration</b>&#010 <P>&#010 You can provide Hibernate configuration to the Smart GWT server in three ways:&#010
 * <ul>&#010 <li>You can place a traditional <code>hibernate.cfg.xml</code> file somewhere on the &#010    
 * classpath</li>&#010 <li>You can have Smart GWT look up a Hibernate <code>Configuration</code> to use.  This &#010    
 * works in the same way as a {@link com.smartgwt.client.docs.serverds.ServerObject}, and in fact makes use of the &#010   
 * ServerObject code.  To do this, add ServerObject-compliant properties to your &#010     <code>server.properties</code>
 * file, prefixed with <code>hibernate.config</code>.  For&#010     example: <pre>&#010       
 * hibernate.config.lookupStyle: spring&#010        hibernate.config.bean: mySessionFactory&#010 </pre></li>&#010 <li>You
 * can provide a Hibernate configuration at the level of individual DataSources, by &#010     specifying a {@link
 * com.smartgwt.client.docs.serverds.DataSource#configBean configBean} on the dataSource (this is only &#010     applicable
 * if you are using Spring; see below)</li>&#010 </ul>&#010 If you choose to have Smart GWT lookup the Hibernate
 * configuration, and you specify a &#010 {@link com.smartgwt.client.docs.serverds.ServerObject#lookupStyle lookupStyle} of
 * "spring", Smart GWT will make use of a&#010 Hibernate <code>SessionFactory</code> configured by Spring.  It is possible
 * to set up multiple&#010 Hibernate configurations in Spring, and to map individual DataSources to different &#010
 * configurations by making use of the <code>dataSource.configBean</code> property mentioned&#010 above.  Please note the
 * following caveats:&#010 <ul>&#010 <li>DataSource-level Hibernate configuration is intended for unusual cases, such as
 * when the&#010     physical data store for one DataSource is actually a different database.  Hibernate &#010    
 * relations between entities with different configurations do not work</li>&#010 <li>Whilst it is possible to have
 * traditional mapped beans alongside Smart GWT "beanless" &#010     on-the-fly mappings, we cannot share the Hibernate
 * configuration because on-the-fly &#010     mapping requires the <code>SessionFactory</code> to be rebuilt for each new
 * mapping.&#010     Spring will not allow this, so we fall back to a second Hibernate configuration, &#010     specified
 * via a .cfg.xml file as described above, for on-the-fly mappings.&#010 </ul>&#010 <P>&#010 <b>Mapping
 * DSRequest/DSResponse to Hibernate</b>&#010 <P>&#010 This integration strategy uses the server-side Java APIs described
 * in&#010 {@link com.smartgwt.client.docs.ServerDataIntegration}.  Two complete examples of Hibernate integration are&#010
 * provided in the SDK, one using {@link com.smartgwt.client.docs.DmiOverview DMI} and one using {@link
 * com.smartgwt.client.rpc.RPCManager} dispatch.  Both&#010 are accessible from the SDK Explorer as Examples->Server
 * Integration, and both include&#010 sample Hibernate configuration as well as a sample UI capable of loading and saving
 * data.&#010 <ul>&#010 <li>&#010 <a href='/examples/server_integration/index.html#customDataSourceIntegrationHibernate'
 * onclick="window.open('/examples/server_integration/index.html#customDataSourceIntegrationHibernate');return
 * false;">"Custom DataSource Integration with Hibernate"</a> &#010 shows how to create a .jsp that uses RPCManager
 * dispatch to implement all four DataSource&#010 request {@link com.smartgwt.client.data.DSRequest#getOperationType
 * operationType}s via Hibernate for a sample bean.&#010 <li>&#010 <a
 * href='/examples/server_integration/index.html#springDSHiberateDMI'
 * onclick="window.open('/examples/server_integration/index.html#springDSHiberateDMI');return false;">"Spring DataSource
 * Integration using DMI to Hibernate"</a>&#010 shows how to use Smart GWT Direct Method Invocation ({@link
 * com.smartgwt.client.docs.DmiOverview DMI}) to implement all four&#010 DataSource operations with Hibernate.  This
 * particular example uses&#010 {@link com.smartgwt.client.docs.SpringIntegration Spring}-based lookup of the target for
 * DMI, however, the same&#010 approach will work without Spring - see other DMI examples on the same page for
 * non-Spring&#010 methods of DMI target lookup.&#010 </ul>&#010 As discussed under DMI, whether to use DMI or RPCManager
 * dispatch is largely a matter&#010 of preference, however if you are already using Spring, a DMI-based approach is
 * probably&#010 a better fit.&#010 <P>&#010 <b>serverType:"hibernate"</b>&#010 <P>&#010 As with DataSources using Smart
 * GWT's built-in {@link com.smartgwt.client.docs.SqlDataSource SQL engine}, you&#010 need only {@link
 * com.smartgwt.client.docs.DataSourceDeclaration create a DataSource} in XML format - no Java&#010 classes or other
 * configuration are required.  The {@link com.smartgwt.client.docs.AdminConsole Admin Console}'s&#010 "Import DataSources"
 * section can be used to import test data into serverType:"hibernate"&#010 DataSources in the same manner as
 * SQLDataSources.
 */
public interface HibernateIntegration {
}
