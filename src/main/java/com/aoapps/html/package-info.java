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
 * along with ao-fluent-html.  If not, see <https://www.gnu.org/licenses/>.
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
 *   This follows the naming conventions specified in <a href="https://oss.aoapps.com/fluent-html/any/apidocs/com.aoapps.html.any/com/aoapps/html/any/package-summary.html"><code>com.aoapps.html.any</code></a>, but without "Any" prefixed to names.
 * </p>
 */
package com.aoapps.html;
