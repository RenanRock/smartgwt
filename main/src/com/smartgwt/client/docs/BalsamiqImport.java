
package com.smartgwt.client.docs;

/**
 * <h3>Reify (Balsamiq Importer)</h3>
 * <a href='http://balsamiq.com' onclick="window.open('http://balsamiq.com');return
 * false;">Balsamiq</a> is a tool for rapidly &amp; collaboratively
 *  creating mockups of user interfaces.  Smart GWT Pro and better includes a tool
 *  called <i>Reify</i> that allows you take exported Balsamiq mockups (.bmml files)
 *  and instantly turn them into interactive Smart GWT applications backed by clean, clear
 *  code.  This allows you to:
 *  <ul>
 *  <li> speed up development by reusing all the careful layout work you did in Balsamiq 
 *  <li> use Balsamiq as a development tool for Smart GWT, since you can re-import at any time
 *  or even continuously
 *  <li> instantly create a fully interactive version of a Balsamiq mockup in any Smart GWT skin
 *  <li> offer such functionality to your end users as a means of extending your application
 * (<i>requires <a href='http://smartclient.com/product'
 * onclick="window.open('http://smartclient.com/product');return false;">Visual Builder OEM
 * license</a></i>)
 *  </ul>
 *  <h3>Using the tool</h3>
 *  <P>
 *  There are two ways to use Reify:
 *  <ul>
 *  <li> as a standalone tool: go to &lt;project base&gt;/tools/bmmlImporter.jsp and an import
 *  dialog similar to Visual Builder's File -&gt; Import dialog will immediately appear.  
 *  
 *  For example, if your bootstrap .html file is at
 *  <i>/myProject/start.html</i>,
 *  the importer should be at <i>/myProject/tools/bmmlImporter.jsp</i> (be sure your project
 *  imports the same tools module required to run Visual Builder).
 *  
 *  <li> in Visual Builder: in the "Screens" tab of the "Project" pane click on the "Add..."
 *  button and select "Import from Balsamiq".  You can provide the Balsamiq mockup file in
 *  various ways; providing a file will automatically load the screen created from the imported
 *  Balsamiq mockup into Visual Builder for further editing.
 *  </ul>
 *  Then, in both cases, what you want to save for further work is the XML source generated by
 * the tool, which is called {@link com.smartgwt.client.docs.ComponentXML Component XML}.  In
 * Visual Builder, you
 *  can copy and paste the XML from the "Code" tab; with the standalone tool you can press the
 *  "Show Source" button and copy and paste from there.
 *  <P>
 *  When using the standalone tool and running the server on your own machine, when you use the
 *  "Select file.." option to import a mockup from a file on disk, this tool will periodically
 *  re-import the mockup, so you can work in Balsamiq and the browser will periodically refresh,
 *  showing the Smart GWT version updated to reflect your latest changes.
 *  <P>
 * Both tools can also save to a file under webroot.  In Visual Builder, use File-&gt;Save As.  In
 *  the standalone tool, use the "Output File" text field in the initial dialog.  This is most
 *  useful if you are using an "exploded" deployment .  If you are using the Eclipse IDE 
 * with the built-in Jetty server, saving under webroot may not be useful since it means saving to
 *  a generated "deployment directory", not your Eclipse project.  In this case copy and paste
 *  is the best way to grab the generated source code.
 *  <P>
 *  For both tools, you can add the URL parameter "mockup" to cause your mockup to be immediately
 *  imported as the tool starts up:
 *  <pre>
 *    tools/bmmlImporter.jsp?mockup=myMockup.bmml
 *  </pre>
 *  This makes your imported mockup into something you can bookmark.
 *  <P>
 *  For the standalone tool, you can also add the URL parameter "outputFile" to specify where the
 *  generated XML source should be saved.  This makes the entire import &amp; save process
 *  bookmarkable, but again is most valuable in an "exploded" deployment.
 *  <P>
 *  The next sections explain how Reify works, and how to tweak your Balsamiq mockup to
 *  produce the best results, then the section "From imported mockup to application" explains how
 *  to turn your generated Smart GWT code into a working application.
 *  <P>
 *  <h3>Containers &amp; Layout</h3>
 *  <P>
 *  Most Balsamiq mockup elements have a direct Smart GWT equivalent - what Balsamiq calls a
 *  "Tabs Bar" is translated to a Smart GWT TabSet component, a Balsamiq "Accordion" is
 *  translated to a Smart GWT SectionStack, and so on.  However, rather than only translate
 *  elements 1-to-1, Reify uses heuristics to try to organize your components into layout
 *  managers and logical forms, as follows.
 *  <P>
 *  <h4>Containers</h4>
 *  <P>
 *  Balsamiq mockups have no true notion of containers - to put something "in" a container in
 *  Balsamiq, you just make sure it <i>visually</i> appears inside of the container - you never
 *  <i>assign</i> it as a child of the container.  If Reify did the same thing, the
 *  resulting code wouldn't be very useful because the screen would become jumbled if anything was
 *  resized.  Instead, Reify detects that you have placed something inside the visual area of
 *  a container and automatically creates a true container relationship in Smart GWT.  A grid
 *  inside of a Window really will be <i>inside</i> that Window in Smart GWT, and will move with
 *  the window if it is dragged.
 *  <P>
 *  It's also OK to be inexact.  If you look at a typical Balsamiq mockup, if an element doesn't
 *  have a visible right/bottom border its very common that its logical extents go outside of it's
 *  container.  Reify automatically recognizes such elements as contained anyway.  If
 *  something is sticking very far out of its container and Reify didn't realize it was
 *  meant to be contained, just fix the size in your mockup, and re-run import.
 *  <P>
 *  Reify will also recognize extra controls in the header area of a container.  For
 *  example, just placing buttons or icons in a Window's header area will lead to
 * {@link com.smartgwt.client.widgets.Window#getHeaderControls headerControls} being created,
 * similarly, placing controls in an Accordion's
 * section will lead to {@link com.smartgwt.client.widgets.layout.SectionHeader#getControls
 * controls} being created.
 *  <P>
 *  <h4>Stacks &amp; Layouts</h4>
 *  <P>
 *  Reify recognizes when you have stacked together several things of the same approximate
 *  size and assumes they go together.  It's OK to be inexact - Reify will ignore small
 * differences in size and alignment and still realize that several items are meant to end up in a
 *  Layout.  If there are two or more possibilities for how elements can be arranged into Layouts,
 *  Reify will generally pick the option that results in the simplest overall layout.
 *  <P>
 *  <h4>Space Filling &amp; Fluid Layout</h4>
 *  <P>
 *  By default, Reify assumes your mockup isn't really meant to be fixed size - whatever
 *  container it's placed into, whether that's the whole browser or something smaller, your design
 *  is actually intended to fill it.
 *  <P>
 *  To achieve this, Reify defaults to ignoring any blank space that appears around the
 *  outside of the your mockup (ignoring markup elements - see below).  Then, Reify tries to
 *  find a way to allow your design to "flex" by finding elements that can use more space: grids,
 *  text areas, accordions, etc.  These elements are given flexible size and layout settings are
 *  applied to all of their parents such that resizing the mockup as a whole will give available
 *  space to the components that can use it.
 *  <P>
 * To get the best results from this system, pay close attention to whether or not you are filling
 *  containers - Reify will only assume that a given component would like more space if it
 *  is <i>already</i> taking up as much space as possible in the mockup.  For example, if you have
 *  a grid as the sole member of a tab, but the grid only fills half the tab, Reify will
 *  assume the grid should be fixed size rather than fill the tab.
 * <!-- TODO: add support for custom setting to avoid flexing one component, or even actual
 * dimension
 *  setting -->
 *  <P>
 *  If you really want the entire mockup to be exactly the sizes you specified, this fluid layout
 *  behavior is one of the things you can turn off right in the import dialog: uncheck "Fill
 *  Space" if the design should not be made into a fluid layout, and uncheck "Trim Space" if you
 *  meant the empty space around the mockup to be taken literally.
 *  <P>
 *  <h4>Markup Elements</h4>
 *  <P>
 *  Elements such as Sticky Notes are considered "markup" and are dropped by default since it's
 *  assumed you don't want them now that you are moving on to creating your real application.
 *  You can retain them if you'd like by unchecking "Drop Markup" in the importer dialog.
 *  <P>
 *  Also, if you right-click on a component in Balsamiq (or option-click on MacOS), the menu item
 *  "Treat as Markup" allows you to convert a normal component into markup, which gives you a
 *  way to have Reify ignore selected components. 
 *  <P>
 *  <h3>Linked Mockups</h3>
 *  <P>
 *  Balsamiq supports
 * <a href='http://support.balsamiq.com/customer/portal/articles/111742'
 * onclick="window.open('http://support.balsamiq.com/customer/portal/articles/111742');return
 * false;">linking multiple mockups together</a> 
 *  to create basic interactivity for guided walkthroughs of
 *  multi-screen mockups.  Reify understands that certain types of Balsamiq links and can
 *  create a combined appliction from several linked mockups, with the interactivity you would
 *  expect.
 *  <P>
 *  However, linking <i>only</i> works when mockups are loaded via the "Select file.." option
 *  and exist on disk at correct relative paths (the same relative locations where Balsamiq
 *  stores them); linking cannot be used with mockups that are uploaded using the web interface.
 *  <P>
 *  <h4>Links on Tabs</h4>
 *  <P>
 *  A common idiom used in Balsamiq to create the illusion of functioning tabs is to create a
 *  link on a tab that points to another mockup which is <i>exactly the same</i> except for the
 *  selected tab and contents of the tab pane.  Reify will recognize this idiom and take
 * the contents of the selected tab from the linked mockup and place it in the same-named tab from
 *  the primary mockup.  As intended, the contents will be initially hidden since the tab will not
 *  be initially selected, and will be revealed when an end user clicks on the tab.  All other
 *  content from the linked mockup is ignored.
 *  <P>
 *  Unlike other aspects of Reify that compensate for small differences, here it is required
 *  that the two mockups be exactly the same except for the selected tab and tab contents.  The
 *  only elements that can differ are "markup" elements such as Sticky Notes.
 *  <P>
 *  <h4>Links on Accordions</h4>
 *  <P>
 *  Similar to the idiom for multiple tabs, links on Accordion components are sometimes used to
 *  point to mockups that are identical except for the expanded Accordion section.  Reify 
 *  will recognize this idiom in the same way as for tabs, and create a SectionStack where the
 *  contents of the visible Accordion section from the linked mockup appear in an initially
 *  collapsed section in the imported application.
 *  <P>
 *  <h4>Other links</h4>
 *  <P>
 *  A link on an element such as a button that leads to a second mockup will, by default, cause
 *  both the primary and linked mockup to be imported with all of their components, and an event
 *  handler will be generated on the button that simply hides all the components of the primary
 *  mockup and shows all the components of the linked mockup.  
 *  <P>
 *  If this is not correct (perhaps the linked mockup is really intended to be the same screen,
 *  with various changes caused by the button), simply remove the link before importing.  You can
 *  then add event handling logic to implement the intended effect of the button.  You can also
 *  separately import the linked mockup and load it as an additional screen - see the follow
 *  sections on going from imported mockup to full applications.
 *  <P>
 *  <h4>Symbols &amp; Assets</h4>
 *  <P>
 *  Balsamiq has a concept of
 * <a href='http://support.balsamiq.com/customer/portal/articles/110439'
 * onclick="window.open('http://support.balsamiq.com/customer/portal/articles/110439');return
 * false;">symbols</a>, which are
 *  reusable elements that can be incorporated into multiple mockups.  Reify supports
 *  symbols, however, like linked mockups, symbols are only supported when the mockup file and
 *  symbol file are both present on disk and do not need to be uploaded.  The symbols file
 *  should be in the same location relative to the mockup as Balsamiq expects it -
 *  <i>assets/symbols.bmml</i> by default.
 *  <P>
 *  <h3>From imported mockup to application</h3>
 *  <P>
 *  Once you've imported your mockup, making it into a real application involves 3 things:
 *  <ol>
 *  <li> loading the mockup into a larger application (or as the entire application)
 *  <li> connecting to real DataSources
 *  <li> adding event handlers and other programmatic logic
 *  </ol> 
 *  <P>
 *  <h4>Loading the mockup as an application</h4>
 *  <P>
 *  To load your imported mockup into an application, see the "Loading screens stored in
 * Component XML" section of the {@link com.smartgwt.client.docs.ComponentXML Component XML
 * overview}.
 *  <P>
 *  <h4>Connecting to real DataSources</h4>
 *  <P>
 *  First, be sure you have read the QuickStart Guide chapters on Data Binding and the Server
 *  Framework so that you are familiar with DataSources, .ds.xml files and how DataSources are
 *  loaded into your application.  Then, consider the different data-aware widgets in the
 *  application created from your mockup:
 *  <P>
 *  <b>Grids and Trees</b>
 *  <P>
 *  Sample data provided for grids and trees in the mockup is converted to a special kind of
 *  DataSource called a "MockDataSource", which is essentially a
 * {@link com.smartgwt.client.data.DataSource#getClientOnly clientOnly DataSource}.  There are two
 * ways to replace these with
 *  real DataSources that save to a persistent store:
 *  <ul>
 *  <li> if you have already separately developed real DataSources: the simplest approach is to
 *  load your DataSources normally, then just delete the &lt;MockDataSource&gt; definitions from
 *  the XML source, and replace the <code>dataSource</code> attribute on the &lt;ListGrid&gt; or
 *  &lt;TreeGrid&gt; element with the ID of your real DataSource
 *  <li> if you want to start from the mockup DataSources: move the XML definition of the
 *  MockDataSource into a new .ds.xml file, rename the outer tag to just &lt;DataSource&gt;,
 * add <code>clientOnly="true"</code> and remove the &lt;mockData&gt; element.  Load the
 * DataSource
 * normally and the application imported from the mockup will continue to function just as it has.
 *  You can now add settings to the new .ds.xml file to set up persistence (see QuickStart
 *  Guide) - remove the <code>clientOnly="true"</code> setting and &lt;cacheData&gt; element
 *  whenever you want to switch over to real persistence.
 *  </ul>
 *  <P>
 *  <b>Forms &amp; ValuesManagers</b>
 *  <P>
 *  As far as form controls (anything used to enter data), the basic rules are:
 *  <ul>
 * <li> any series of adjacent form controls that have a roughly tabular layout will be created as
 *  a single DynamicForm
 * <li> all DynamicForm controls within one container will share a common {@link
 * com.smartgwt.client.widgets.form.ValuesManager}
 *  </ul>
 *  To connect these forms and ValuesManagers to DataSources, just set the "dataSource" attribute
 *  on the &lt;DynamicForm&gt; or &lt;ValuesManager&gt; elements respectively.  If Reify has
 * created a ValuesManager linking two forms that should be attached to separate DataSources, just
 *  delete the &lt;ValuesManager&gt; declaration and corresponding &lt;valuesManager&gt; attribute
 *  on the forms that share the ValuesManager - they will now be independent forms that can use
 *  independent DataSources.
 *  <P>
 *  Bear in mind when adding DataSources: the FormItems within each DynamicForm will only be
 * connected to the DataSource field if they <i>have the same name attribute</i>.  You'll notice
 * the
 *  FormItems are given automatically generated names based on the label for the field in the
 * mockup, for example, a title of "First Name" yields a field name of "First_Name".  You may need
 *  to edit these in order to match the names of your DataSource fields.
 *  <P>
 *  <h3>Setting IDs &amp; Custom Properties</h3>
 *  <P>
 *  If you right-click on a component in Balsamiq (or option-click on MacOS), a menu item
 *  "Custom Properties" allows you to set a "Custom control ID" and provide "Custom control
 * data".  The "Custom control ID" will become {@link com.smartgwt.client.widgets.Canvas#getID
 * component.ID} for the
 *  Smart GWT component generated from the Balsamiq component.
 *  <P>
 *  You can use "Custom control data" to provide additional properties for the generated
 *  component, as a semicolon-separated set of properties and values.  For example this setting:
 *  <pre>
 *      showFilterEditor:true; border:"1px solid black"
 *  </pre>
 *  .. applied to a Balsamiq "Data Grid" would enable
 * {@link com.smartgwt.client.widgets.grid.ListGrid#getShowFilterEditor showFilterEditor} on the
 * generated ListGrid.
 *  <P>
 *  
 *  <P>
 *  <h3>Event Handlers &amp; Scripting loaded components</h3>
 *  <P>
 *  See the section "Event Handlers &amp; Scripting loaded components" in the
 *  {@link com.smartgwt.client.docs.ComponentXML Component XML overview}.
 *  <P>
 *  <h3>Troubleshooting</h3>
 *  <P>
 *  If you manage to get the importer to crash or do something clearly incorrect, we want to know
 *  about it.  Take the BMML file(s) that failed and post them to the
 * <a href='http://forums.smartclient.com/'
 * onclick="window.open('http://forums.smartclient.com/');return false;">forums</a> along with the
 * exact version of your product
 * (lower-left-hand corner of the Developer Console).  If you can isolate the exact portion of the
 *  mockup that causes the problem and save that as a separate file, that's very helpful in terms
 *  of fixing the problem as soon as possible.
 *  <P>
 *  In terms of getting past the problem while you wait for a fix, the easiest thing is to just
 *  remove the offending portion of the mockup.  You can then create that portion with normal
 *  coding techniques or with {@link com.smartgwt.client.docs.ComponentXML}.
 *  <P>
 *  <h3>Supported balsamiq component types</h3>
 *  <P>
 *  Almost all balsamiq components have obvious analogs in smartclient, so during translation
 *  they converted in according classes. Here is the list of balsamiq components and smartclient
 *  classes which used for convertation. All balsamiq types that are not in this list converted
 *  to MockupElement which shows an image of balsamiq component (if there is an image in 
 *  smartclient library for the component) and component title as a Label. 
 *  <ul>
 * <li>"com.balsamiq.mockups::Accordion": {@link
 * com.smartgwt.client.widgets.layout.SectionStack}</li>
 * <li>"com.balsamiq.mockups::BarChart": {@link com.smartgwt.client.widgets.chart.FacetChart}</li>
 * <li>"com.balsamiq.mockups::BreadCrumbs": {@link
 * com.smartgwt.client.widgets.layout.HLayout}</li>
 * <li>"com.balsamiq.mockups::BrowserWindow": {@link
 * com.smartgwt.client.widgets.layout.VStack}</li>
 * <li>"com.balsamiq.mockups::Button": {@link
 * com.smartgwt.client.widgets.form.fields.ButtonItem}</li>
 *   <li>"com.balsamiq.mockups::ButtonBar": {@link com.smartgwt.client.widgets.menu.MenuBar}</li>
 *   <li>"com.balsamiq.mockups::Calendar": {@link com.smartgwt.client.widgets.DateChooser}</li>
 *   <li>"com.balsamiq.mockups::Canvas": {@link com.smartgwt.client.widgets.layout.VStack}</li>
 * <li>"com.balsamiq.mockups::CheckBox": {@link
 * com.smartgwt.client.widgets.form.fields.CheckboxItem}</li>
 * <li>"com.balsamiq.mockups::CheckBoxGroup": {@link
 * com.smartgwt.client.widgets.form.DynamicForm}</li>
 * <li>"com.balsamiq.mockups::ColorPicker": {@link
 * com.smartgwt.client.widgets.form.fields.ColorItem}</li>
 * <li>"com.balsamiq.mockups::ColumnChart": {@link
 * com.smartgwt.client.widgets.chart.FacetChart}</li>
 * <li>"com.balsamiq.mockups::ComboBox": {@link
 * com.smartgwt.client.widgets.form.fields.SelectItem}</li>
 *   <li>"com.balsamiq.mockups::DataGrid": {@link com.smartgwt.client.widgets.grid.ListGrid}</li>
 * <li>"com.balsamiq.mockups::DateChooser": {@link
 * com.smartgwt.client.widgets.form.fields.DateItem}</li>
 *   <li>"com.balsamiq.mockups::FieldSet": {@link com.smartgwt.client.widgets.Canvas}</li>
 * <li>"com.balsamiq.mockups::FormattingToolbar": {@link
 * com.smartgwt.client.widgets.toolbar.ToolStrip}</li>
 *   <li>"com.balsamiq.mockups::HelpButton": {@link com.smartgwt.client.widgets.Label}</li>
 * <li>"com.balsamiq.mockups::HorizontalScrollBar": {@link
 * com.smartgwt.client.widgets.Scrollbar}</li>
 * <li>"com.balsamiq.mockups::HSlider": {@link
 * com.smartgwt.client.widgets.form.fields.SliderItem}</li>
 *   <li>"com.balsamiq.mockups::Icon": {@link com.smartgwt.client.widgets.Label}</li>
 *   <li>"com.balsamiq.mockups::IconLabel": {@link com.smartgwt.client.widgets.Label}</li>
 *   <li>"com.balsamiq.mockups::Image": {@link com.smartgwt.client.widgets.Img}</li>
 *   <li>"com.balsamiq.mockups::Label": {@link com.smartgwt.client.widgets.Label}</li>
 * <li>"com.balsamiq.mockups::LineChart": {@link
 * com.smartgwt.client.widgets.chart.FacetChart}</li>
 * <li>"com.balsamiq.mockups::Link": {@link com.smartgwt.client.widgets.form.fields.LinkItem}</li>
 *   <li>"com.balsamiq.mockups::LinkBar": {@link com.smartgwt.client.widgets.layout.HLayout}</li>
 * <li>"com.balsamiq.mockups::List": {@link
 * com.smartgwt.client.widgets.form.fields.SelectItem}</li>
 *   <li>"com.balsamiq.mockups::Menu": {@link com.smartgwt.client.widgets.menu.MenuButton}</li>
 *   <li>"com.balsamiq.mockups::MenuBar": {@link com.smartgwt.client.widgets.menu.MenuBar}</li>
 *   <li>"com.balsamiq.mockups::MultilineButton": {@link com.smartgwt.client.widgets.Button}</li>
 * <li>"com.balsamiq.mockups::NumericStepper": {@link
 * com.smartgwt.client.widgets.form.fields.SpinnerItem}</li>
 *   <li>"com.balsamiq.mockups::Paragraph": {@link com.smartgwt.client.widgets.Label}</li>
 * <li>"com.balsamiq.mockups::PieChart": {@link com.smartgwt.client.widgets.chart.FacetChart}</li>
 *   <li>"com.balsamiq.mockups::PointyButton": {@link com.smartgwt.client.widgets.Button}</li>
 *   <li>"com.balsamiq.mockups::ProgressBar": {@link com.smartgwt.client.widgets.Progressbar}</li>
 *   <li>"com.balsamiq.mockups::RadioButton": RadioItem</li>
 * <li>"com.balsamiq.mockups::RadioButtonGroup": {@link
 * com.smartgwt.client.widgets.form.DynamicForm}</li>
 * <li>"com.balsamiq.mockups::SearchBox": {@link
 * com.smartgwt.client.widgets.form.fields.TextItem}</li>
 *   <li>"com.balsamiq.mockups::SubTitle": {@link com.smartgwt.client.widgets.Label}</li>
 * <li>"com.balsamiq.mockups::Switch": {@link
 * com.smartgwt.client.widgets.form.fields.CheckboxItem}</li>
 *   <li>"com.balsamiq.mockups::TabBar": {@link com.smartgwt.client.widgets.tab.TabSet}</li>
 *   <li>"com.balsamiq.mockups::TagCloud": {@link com.smartgwt.client.widgets.Label}</li>
 * <li>"com.balsamiq.mockups::TextArea": {@link
 * com.smartgwt.client.widgets.form.fields.TextAreaItem}</li>
 * <li>"com.balsamiq.mockups::TextInput": {@link
 * com.smartgwt.client.widgets.form.fields.TextItem}</li>
 *   <li>"com.balsamiq.mockups::Title": {@link com.smartgwt.client.widgets.Label}</li>
 *   <li>"com.balsamiq.mockups::TitleWindow": {@link com.smartgwt.client.widgets.Window}</li>
 *   <li>"com.balsamiq.mockups::Tree": {@link com.smartgwt.client.widgets.tree.TreeGrid}</li>
 * <li>"com.balsamiq.mockups::VerticalScrollBar": {@link
 * com.smartgwt.client.widgets.Scrollbar}</li>
 * <li>"com.balsamiq.mockups::VerticalTabBar": {@link com.smartgwt.client.widgets.tab.TabSet}</li>
 * <li>"com.balsamiq.mockups::VSlider": {@link
 * com.smartgwt.client.widgets.form.fields.SliderItem}</li>
 *  </ul>
 */
public interface BalsamiqImport {
}
