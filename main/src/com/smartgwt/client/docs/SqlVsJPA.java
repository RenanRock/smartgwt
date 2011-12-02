
package com.smartgwt.client.docs;

/**
 * <h3>SQL DataSource vs JPA, EJB, Ibatis and other technologies</h3>
 * If you are free to choose which persistence mechanism your application will use, you should consider using the Smart GWT
 * SQL DataSource instead of a more heavyweight, bean-based  solution.  This article discusses the advantages of doing so.
 * <p> <b>Simplicity</b> <p> With the Smart GWT SQL DataSource, simple CRUD connectivity can be set up via a  <a
 * href="http://www.smartclient.com/smartgwtee/showcase/#tools_ds_wizard" target="examples">wizard</a> and requires zero
 * server side code.  Only a DataSource  descriptor (.ds.xml file) needs to exist; this descriptor can be generated by the
 * wizard  or created by hand.  The descriptor actually serves double duty by also providing the  configuration for UI
 * components - in other words, this is information that you would need  to express anyway. <p> Semi-technical product
 * managers, testers, business analysts and IT staff who have no familiarity with Java can easily comprehend DataSource
 * definitions and even customized SQL queries, allowing them to go further with prototyping efforts, provide more specific
 * feedback and capture more relevant diagnostics when reporting issues. <p> This level of simplicity is lost when using
 * more heavyweight systems.  JPA / EJB  best practices indicate creation of a bean class for every domain object, as well
 * as  related "services" or "session beans", DTOs  (<a href='http://en.wikipedia.org/wiki/Data_Transfer_Object'
 * onclick="window.open('http://en.wikipedia.org/wiki/Data_Transfer_Object');return false;">Data Transfer Objects</a>) and 
 * other unnecessary scaffolding.  Ibatis avoids some of this scaffolding, but requires every  SQL query to be written by
 * hand.  In contrast the SQL DataSource supports basic CRUD queries out of the box. <p> <b>Performance</b> <p> Systems
 * like JPA work nicely when dealing with a single object at a time, but enterprise applications routinely work with lists
 * or trees of objects that draw data from multiple tables.  In these situations, it's trivial to express an efficient SQL
 * query for retrieving the desired results (as shown in @see <a
 * href="http://www.smartclient.com/smartgwtee/showcase/#largeValueMap" target="examples">this example</a>).  Fetching the
 * same data using getter methods on Java Beans often leads to nightmare performance scenarios (such as 3 or more separate
 * SQL queries per object retrieved). <P> Trying to "trick" the persistence system into generating efficient queries
 * doesn't make sense - this just leads to a far more complex and fragile solution that now requires deep knowledge of how
 * the ORM system generates SQL as well as SQL itself.  <P> SQLDataSource allows you to directly write SQL when it makes
 * sense, and  {@link com.smartgwt.client.docs.serverds.DataSource#beanClassName to use beans} when object oriented
 * approaches are clearer and simpler.  When you do write SQL directly, you override just the parts of the query that you
 * need to change - you still leverage SQLDataSource's ability to generate cross-database SQL for complex search criteria,
 * efficient data paging and sorting, even in a complex reporting query (see <a
 * href="http://www.smartclient.com/smartgwtee/showcase/#sql_dynamic_reporting" target="examples">this example</a>). <p>
 * <b>Portability</b> <p> Smart GWT DataSources provide cross-database portability like JPA and other solutions. However,
 * DataSources can also be replaced with an entirely different integration strategy or entirely different server platform,
 * such as a SOA architecture where the browser contacts WSDL web services directly.  The clear data requirements
 * definition represented by a DataSource makes such drastic technology changes much easier with the SQL DataSource than
 * with any other technology. <p> <b>Power</b> <p> The SQL DataSource has out of the box support for server-side advanced
 * filtering without the need to write any code (see the  <a
 * href="http://www.smartclient.com/smartgwt/showcase/#grid_nested_filter_builder" target="examples">SQL Advanced Filtering
 * example</a>), and Smart GWT provides {@link com.smartgwt.client.widgets.form.FilterBuilder pre-built user interfaces for
 * filtering}.  The effort required to develop similar functionality with another persistence mechanism would vary from 
 * substantial to spectacular. <p> You can leverage advanced, automatic SQL generation, such as advanced filter criteria, 
 * GROUP BY and ORDER BY clauses, and selection of row ranges, even in very heavily customized queries.  The <a
 * href="http://www.smartclient.com/smartgwtee/showcase/#sql_dynamic_reporting" target="examples">Dynamic Reporting
 * example</a> shows this. <p> With the SQL DataSource and Transaction Chaining, you can chain together multiple SQL 
 * queries, or a mixture of SQL queries and other data access, with simple declarations right  in the DataSource, as <a
 * href="http://www.smartclient.com/smartgwtee/showcase/#transactions_queued_md" target="examples">this example</a>
 * demonstrates. <p> Because you write the SQL, you can use database-specific features when absolutely  necessary. 
 * Features such as query optimizer hints or stored procedures are thus accessible but, importantly, are within the same
 * processing model used for all other data access. <p> <b>Security</b> <p> Because the central DataSource definition
 * expresses all the available operations, how they are performed and who has access to them, things are clear and simple. 
 * It's much easier to  understand and audit a DataSource definition than a slew of Java classes. <p> There is no
 * information leakage from server to client with the SQL DataSource.  All  server-side declarations, such as SQL
 * templates, are automatically stripped out of the  DataSource definition before the browser sees it. <p> Custom SQL in a
 * Smart GWT SQL DataSource is protected from SQL injection attacks.  It is impossible for a developer to write a SQL
 * template that is vulnerable to SQL injection  without going through the {@link com.smartgwt.client.docs.VelocitySupport
 * $rawValue} feature, a rarely used feature that is very prominently flagged in the documentation as requiring special
 * care. Other ORM systems tend to require hand-coded SQL queries for advanced use cases such as reporting; these
 * hand-written queries are where most security holes appear.  By providing a safe environment for SQL customizations, SQL
 * DataSource removes these risks.
 */
public interface SqlVsJPA {
}
