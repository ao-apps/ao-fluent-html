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
 *   Interface hierarchy, abstract base classes, and overall relationship between {@linkplain AnyDocument document},
 *   {@linkplain Element element}, {@linkplain Attributes attributes}, and {@link Content content}.
 * </p>
 * <p>
 *   This package makes extensive use of parameterized types, may of them self-referential.  This is primarily so that
 *   code-assistance can provide the full context of the current element/attribute/content.  The verbosity caused by
 *   the expanding type parameters is not expected to be intrusive for two reasons: <code>var</code> is available for
 *   local variables in newer versions of Java, and the type definitions provide meaningful limits to wildcard bounds
 *   (<code>&lt;?&gt;</code>).
 * </p>
 * <p>
 *   This follows the naming conventions specified in {@link com.aoindustries.html}, but with "Any" prefixed to names.
 * </p>
 */
package com.aoindustries.html.any;

// TODO: Move this package to a different sub-project?
//       Doing so would clean the code-assist namespace for projects that only need one type of implementation, such as
//       ao-fluent-html-servlet, which "HTML" currently matches two implementations, increasing developer workload to
//       select the correct one.
