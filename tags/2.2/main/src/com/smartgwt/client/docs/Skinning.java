
package com.smartgwt.client.docs;

/**
 * <h3>Skinning / Theming</h3>
 * Skinning (aka "theming" or "branding") is the process of modifying Smart GWT's default&#010 look and feel to match the
 * desired look and feel for your application.  Smart GWT supports&#010 an extremely powerful and simple skinning system
 * that allows designers with a basic grasp of&#010 CSS and JSON to skin any Smart GWT component.&#010 <P>&#010
 * <h4>Basics</h4>&#010 <P>&#010 <ul>&#010 <li> Smart GWT components create their visual appearance by dynamically
 * generating HTML,&#010 within the browser, using JavaScript.&#010&#010 <li> the HTML generated by Smart GWT components
 * contains CSS style names and URLs to&#010 images&#010&#010 <li> Smart GWT components can be skinned by replacing the CSS
 * styles and images that&#010 the components use by default, or by using JavaScript properties to configure&#010
 * components to use new CSS styles and new image URLs.&#010&#010 <li> You can change the appearance of an individual Smart
 * GWT component by passing &#010 properties to  create(), or you can skin all components of the&#010 same class at once,
 * by using  addProperties() and &#010  changeDefaults() to change the defaults for the class.&#010&#010 <li> A "skin"
 * consists of:&#010 <ul>&#010 <li> a single CSS stylesheet containing all CSS styles used by Smart GWT components&#010
 * (<code>skin_styles.css</code>)&#010 <li> a single JavaScript file that sets component defaults
 * (<code>load_skin.js</code>)&#010 <li> a directory tree of images organized by component&#010 </ul>&#010 &#010 <li>&#010
 * The example skins that come with Smart GWT are in&#010 <code>smartclientSDK/isomorphic/skins</code>.  The standard
 * filesystem layout for a skin is:&#010 <pre>&#010    isomorphic/skins&#010        skin_styles.css&#010       
 * load_skin.js&#010        images/&#010            ListGrid/&#010                sort_ascending.gif&#010               
 * ...&#010            Tab/&#010            ... other directories containing&#010                component or shared media
 * ...&#010 </pre>&#010 <li> A skin is loaded via a &lt;SCRIPT SRC=&gt; tag that loads load_skin.js, or, if using&#010 the
 * Smart GWT server, by specifying the "skin" property of the {@link loadISCTag}.&#010 load_skin.js loads the stylesheet
 * and sets the CSS styleNames and media URLs that&#010 Smart GWT components will use.&#010 </ul>&#010 <P>&#010
 * <h4>Modifying Skins</h4>&#010 <P>&#010 To modify a skin, first create a copy of one of the skins that comes with the
 * Smart GWT&#010 SDK, then modify the copy.  Full instructions are provided in Chapter 9 of the &#010
 * ${isc.DocUtils.linkForDocNode('QuickStartGuide', 'QuickStart Guide')}.&#010 <P>&#010 <h4>Locating Skinning
 * Properties</h4>&#010 <P>&#010 <b>Starting from the name of the component</b>&#010 <P>&#010 Given a Smart GWT component
 * that you want to skin, use the search feature of the Smart GWT&#010 Reference to locate it, and open the "Instance APIs"
 * tab.&#010 <ul>&#010 <li> for properties that set CSS styles, look for properties whose name includes "style", eg&#010
 * {@link com.smartgwt.client.widgets.Button#getBaseStyle baseStyle}&#010 <li> for properties that control URLs to media,
 * look for properties whose name includes&#010 "src", "image" or "icon", such as {@link
 * com.smartgwt.client.widgets.Img#getSrc src}&#010 <li> for subcomponents that also support skinning, look for properties
 * of type "AutoChild"&#010 and check the reference for the type of the AutoChild for settable properties.  For
 * example,&#010 {@link com.smartgwt.client.widgets.Window#getMinimizeButton minimizeButton} is an ImgButton and therefore
 * supports {@link com.smartgwt.client.widgets.ImgButton#getSrc src}.&#010 </ul>&#010 <b>TIP</b>: the Instance APIs tab
 * allows you to search within just the current class, limit &#010 the display to just properties or methods, and sort by
 * type.&#010 <P>&#010 <b>Starting from a running example</b>&#010 <P>&#010 Open the Developer Console and use the Watch
 * Tab to locate the component or subcomponent you &#010 want to skin, then locate it in the documentation, as above.&#010
 * <P>&#010 If you don't find the component in the documentation, it may be a custom component specific&#010 to your
 * organization.  To find the base Smart GWT component for a component named&#010 "MyComponent", use the following code to
 * find out the name of the superclass:&#010 <pre>&#010     isc.<i>MyComponent</i>.getSuperClass().getClassName()&#010
 * </pre>&#010 Repeat this until you arrive at a Smart GWT built-in class.  You can execute this code in&#010 the "Eval JS"
 * area of the Results pane of the Developer Console.&#010 <P>&#010 Specific browsers offer alternate approaches to quickly
 * discover the images or style names&#010 being used for a part of a Smart GWT component's appearance: &#010 <ul>&#010
 * <li> the Firefox browser offers a dialog via Tools->"Page Info" that gives a manifest of&#010 media used in the
 * page.&#010 <li> the <a href='http://www.getfirebug.com/' onclick="window.open('http://www.getfirebug.com/');return
 * false;">Firebug</a> extension for Firefox has an&#010 "Inspect" feature that allows you to see the HTML, CSS and media
 * in use for a given area of&#010 the screen&#010 <li> right clicking (option-click on a Mac) on an image and choosing
 * "Properties" shows a&#010 dialog that provides the image URL in most browsers.  Tips:&#010 <ul>&#010 <li> if a Smart GWT
 * component is showing text over an image, right-click at the very edge of&#010 the underlying image to get image
 * properties rather than information about the text label&#010 <li> on some browsers, in order to see the full image URL,
 * you may need to drag select the &#010 partial URL of the image shown in the properties dialog&#010 </ul>&#010 </ul>&#010
 * <P>&#010 <h4>Image URLs in Smart GWT</h4>&#010 <P>&#010 Properties that refer to images by URL, such as {@link
 * com.smartgwt.client.widgets.Img#getSrc src} and {@link com.smartgwt.client.widgets.Button#getIcon icon}, are&#010
 * specially interpreted in Smart GWT to allow for simpler and more uniform image URLs,&#010 and to allow applications to
 * be restructured more easily.&#010 <P>&#010 Unlike the URL used with an HTML &lt;IMG&gt; element, the image URL passed to
 * a Smart GWT&#010 component is not assumed to be relative to the current page.  See {@link java.lang.String} for a&#010
 * full explanation of the default application image directory, and the meaning of the "[SKIN]"&#010 prefix.&#010 <P>&#010
 * <h4>Specifying Image URLs</h4>&#010 <P>&#010 Default image URLs for Smart GWT components are specified in
 * <code>load_skin.js</code> via&#010 JavaScript, using calls to  Class.addProperties and&#010  Class.changeDefaults.  For
 * example, the <code>load_skin.js</code> file&#010 from the "Smart GWT" sample skin includes the following code to
 * establish the media used by&#010 {@link com.smartgwt.client.widgets.Window#getMinimizeButton minimizeButton}:&#010
 * <pre>&#010    isc.Window.changeDefaults("minimizeButtonDefaults", { &#010         src:"[SKIN]/Window/minimize.png"&#010 
 * });&#010 </pre>&#010 <P>&#010 <h4>Specifying Image Sizes</h4>&#010 <P>&#010 Many Smart GWT components must know some
 * image sizes in advance, in order to allow those&#010 components to autosize to data or content.&#010 <P>&#010 For
 * example, the {@link com.smartgwt.client.widgets.tab.ImgTab}s used in {@link com.smartgwt.client.widgets.tab.TabSet}s are
 * capable of automatically sizing&#010 to a variable length {@link com.smartgwt.client.widgets.tab.Tab#getTitle title}. 
 * To make this possible, Smart GWT must know the&#010 sizes of the images used as "endcaps" on each tab in advance.&#010
 * <P>&#010 Like image URLs, image sizes are specified in <code>load_skin.js</code>.  The following code&#010 sample
 * establishes the default size of the "endcaps" for tabs, by setting a default value&#010 for {@link
 * com.smartgwt.client.widgets.tab.ImgTab#getCapSize capSize}:&#010 <pre>&#010     isc.ImgTab.addProperties({&#010        
 * capSize:4&#010     })&#010 </pre>&#010 <P>&#010 <h4>CSS usage in Smart GWT</h4>&#010 <P>&#010 In Smart GWT, screen
 * layout and sizing are controlled via JavaScript, and appearance via&#010 CSS and images.  &#010 <P>&#010 CSS borders,
 * margins and padding applied to Smart GWT components can be treated as purely&#010 visual properties with no effect on
 * sizing or layout.  Unlike HTML elements, a Smart GWT&#010 component will always have the exact size you specify via
 * JavaScript, regardless of browser&#010 platform, browser compatibility mode, or borders, margins, or padding, all of
 * which normally&#010 affect the final size of an HTML element. &#010 <P>&#010 For this reason, Smart GWT skinning
 * requires only novice-level familiarity with CSS, as CSS&#010 is used principally for colors and fonts.  See {@link
 * java.lang.String this discussion} for&#010 further details on what properties should be set via CSS vs via
 * JavaScript.&#010 <P>&#010 <h4>Statefulness and Suffixes</h4>&#010 <P>&#010 Some components or areas within components,
 * including buttons and the cells within a grid, are&#010 "stateful", meaning that they can be in one of a set of states
 * each of which has a distinct&#010 visual appearance.&#010 <P>&#010 Stateful components switch the CSS styles or image
 * URLs they are using as they transition&#010 from state to state, appending state information as suffixes on the style
 * names or URL.&#010 See {@link com.smartgwt.client.widgets.Img#getSrc src} and {@link
 * com.smartgwt.client.widgets.Button#getBaseStyle baseStyle} for details and examples.&#010 <P>&#010 Smart GWT has
 * built-in logic to manage a series of state transitions, such as:&#010 <ul>&#010 <li> "rollover": showing a different
 * appearance when the mouse is over a component&#010 <li> "button down": showing a different appearance when the mouse is
 * pressed over a&#010 component&#010 <li> "disabled": showing a different appearance when a component cannot be interacted
 * with&#010 <li> "selected": showing one of a set of components in a different state to indicate&#010 selection&#010
 * </ul>&#010 Flags on some components, such as {@link com.smartgwt.client.widgets.ImgButton#getShowRollOver showRollOver},
 * allow you to control whether the&#010 component will switch CSS style or image URL when the component transitions into a
 * given state.&#010 <P>&#010 <h4>StretchImg: 3-segment stretchable images</h4>&#010 <P>&#010 A {@link
 * com.smartgwt.client.widgets.StretchImg} is Smart GWT component that renders out a compound image composed of 3&#010
 * image files: two fixed-size endcaps images and a stretchable center segment.  Like stateful&#010 components, the names
 * of each image segment is appended to the image URL as a suffix.  See&#010 {@link
 * com.smartgwt.client.widgets.StretchImg#getSrc src} for details.&#010 <P>&#010 <h4>EdgedCanvas</h4>&#010 <P>&#010 Similar
 * to a StretchImg, an {@link com.smartgwt.client.widgets.EdgedCanvas} provides an image-based decorative edge&#010 around
 * and/or behind another component, with up to 9 segments (a 3x3 grid).  Decorative&#010 edges can be added to any
 * component by setting {@link com.smartgwt.client.widgets.Canvas#getShowEdges showEdges:true}.&#010 EdgedCanvas is also
 * used to construct dropshadows, which can be enabled on any component via&#010 {@link
 * com.smartgwt.client.widgets.Canvas#getShowShadow showShadow:true}.&#010 <P>&#010 <h4>Multiple looks for the same
 * component type</h4>&#010 <P>&#010 In some cases you need to create two variations in appearance for a component with the
 * same&#010 behavior.  For example, you may want to create a specialized Window, called "PaletteWindow",&#010 that behaves
 * like a normal Window but has a very compact look & feel.  To create a&#010 separately skinnable component for
 * PaletteWindow, use {@link com.smartgwt.client.util.isc#defineClass isc.defineClass}.  For&#010 example:&#010 <pre>&#010 
 * isc.defineClass("PaletteWindow", "Window");&#010    isc.PaletteWindow.addProperties({&#010        showFooter:false,&#010
 *        ...&#010    })&#010 </pre>
 */
public interface Skinning {
}