/*
 * ao-fluent-html - Fluent Java DSL for high-performance HTML generation.
 * Copyright (C) 2021  AO Industries, Inc.
 *     support@aoindustries.com
 *     7262 Bull Pen Cir
 *     Mobile, AL 36695
 *
 * This file is part of ao-fluent-html.
 *
 * ao-fluent-html is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * ao-fluent-html is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with ao-fluent-html.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * <p>
 *   Fluent Java DSL for high-performance HTML generation.  This is the thinnest possible abstraction around generating
 *   HTML output.  An emphasis is placed on writing fully encoded, safe output by default.
 * </p>
 * <p>
 *   Meaningful, context-aware code assistance and compiler support is achieved through extensive use of the interface
 *   hierarchy along with bounded generics.  With these techniques, the relationships of HTML elements, attributes, and
 *   content is modeled without specifically programming all combinations through code generation.
 * </p>
 * <p>
 *   Things this is not:
 * </p>
 * <ul>
 *   <li>
 *     <p>
 *       DOM - elements, attributes, and content are rather ethereal.  They exist only as the code writing HTML output.
 *       There is no way to navigate or query the document object model, because it simply does not exist.
 *     </p>
 *   </li>
 *   <li>
 *     <p>
 *       HTML validator - although many details have been put into the relationships between elements, attributes, and
 *       content, the API does not actively prevent writing incorrect HTML, such as writing the same attribute more than
 *       once on an element.  Most of the writers are quite stateless, and just do as they are told and do so immediately.
 *     </p>
 *   </li>
 * </ul>
 * <p>
 *   Naming conventions within the package:
 * </p>
 * <ul>
 *   <li>
 *     <p>
 *      <code>ELEMENT</code> - Classes that represent HTML elements.
 *     </p>
 *   </li>
 *   <li>
 *     <p>
 *       <code>ELEMENT__</code> - Non-{@link com.aoindustries.html.any.Closeable closeable}, lambda-friendly content of HTML elements (tag is
 *       automatically ended when lambda execution completes).
 *     </p>
 *   </li>
 *   <li>
 *     <p>
 *       <code>ELEMENT_c</code> - {@link com.aoindustries.html.any.Closeable} HTML element content, which must be explicitly closed to end the tag
 *       (with either {@link com.aoindustries.html.any.Closeable#close()} or {@link com.aoindustries.html.any.Closeable#__()}, the latter allowing further fluent method
 *       chaining).  This is useful when the opening tag and closing tag are written at different times or in different
 *       parts of the code.  For example, when creating a template where one method writes the template header and a
 *       different method writes the template footer.
 *     </p>
 *   </li>
 *   <li>
 *     <p>
 *       <code>ELEMENT_content</code> - Content model interface specific to a single type of element.  This interface, or
 *       one of the more general-purpose <code>*Content</code> models, is implemented by both <code>ELEMENT__</code> and
 *       <code>ELEMENT_c</code>.
 *     </p>
 *   </li>
 *   <li>
 *     <p>
 *       <code>*Content</code> - Various content model interfaces, either directly defined by the HTML specification or a
 *       result of adapting the HTML specification into an interface hierarchy.
 *     </p>
 *   </li>
 *   <li>
 *     <p>
 *       <code>Union_*</code> - Union content models represent the specific elements that are common between two or more
 *       content models, but where the content models cannot inherit from one another.
 *       These interfaces are not specifically part of the HTML specification, but are an artifact of this implementation.
 *       These interfaces are primarily needed because there is no "or" for generic upper bounds.
 *     </p>
 *     <p>
 *       For example, both {@link B} and {@link I} are part of both {@link PalpableContent} and {@link PhrasingContent}.
 *       Thus, when you have one, you know you can have the other (see {@link Union_Palpable_Phrasing} in this case).
 *     </p>
 *   </li>
 * </ul>
 */
package com.aoindustries.html;
