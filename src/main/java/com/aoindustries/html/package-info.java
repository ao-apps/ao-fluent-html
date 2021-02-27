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
 * Fluent Java DSL for high-performance HTML generation.  This is the thinnest possible abstraction around generating
 * HTML output.  An emphasis is placed on writing fully encoded, safe output by default.
 * <p>
 * Meaningful, context-aware code assistance and compiler support is achieved through extensive use of the interface
 * hierarchy along with bounded generics.  With these techniques, the relationships of HTML elements, attributes, and
 * content is modeled without specifically programming all combinations through code generation.
 * </p>
 * <p>
 * Things this is not:
 * </p>
 * <ul>
 * <li>DOM - elements, attributes, and content are rather ethereal.  They exist only as the code writing HTML output.
 *     There is no way to navigate or query the document object model, because it simply does not exist.
 * </li>
 * <li>HTML validator - although many details have been put into the relationships between elements, attributes, and
 *     content, the API does not actively prevent writing incorrect HTML, such as writing the same attribute more than
 *     once on an element.  Most of the writers are quite stateless, and just do as they are told and do so immediately.
 * </li>
 * </ul>
 * <p>
 * Naming conventions within the package:
 * </p>
 * <ul>
 * <li><code>*Content</code> - Various content model interfaces, either directly defined by the HTML specification or a
 *     result of adapting the HTML specification into an interface hierarchy.
 * </li>
 * <li><code>ELEMENT</code> - Classes that represent HTML elements.</li>
 * <li><code>ELEMENT__</code> - Non-{@link Closeable closeable}, lambda-friendly content of HTML elements (tag is
 *     automatically ended when lambda execution completes).
 * </li>
 * <li><code>ELEMENT_c</code> - {@link Closeable} HTML element content, which must be explicitly closed to end the tag
 *     (with either {@link Closeable#close()} or {@link Closeable#__()}, the latter allowing further fluent method
 *     chaining).  This is useful when the opening tag and closing tag are written at different times or in different
 *     parts of the code.  For example, when creating a template where one method writes the template header and a
 *     different method writes the template footer.
 * </li>
 * <li><code>ELEMENT_content</code> - Content model interface specific to a single type of element.  This interface, or
 *     one of the more general-purpose <code>*Content</code> models, is implemented by both <code>ELEMENT__</code> and
 *     <code>ELEMENT_c</code>.
 * </li>
 * <li><code>ELEMENT_factory</code> - Creates child elements of the given type.  These factories are implemented by
 *     <code>*Content</code> models, element-specific <code>ELEMENT_content</code> models, and {@link Document} itself.
 *     Notably, {@link Document} can create all element types.
 * </li>
 * </ul>
 */
package com.aoindustries.html;
