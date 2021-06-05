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
package com.aoapps.html;

import com.aoapps.html.any.InheritanceTests;
import org.junit.Test;

/**
 * Tests <code>Union_*</code> interfaces,
 * which confirm a class implements the expected set of interfaces.
 *
 * @author  AO Industries, Inc.
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class UnionContentTest {

	/**
	 * Gets the set of all <code>Union_*</code> interfaces.
	 */
	static Class<? extends Content>[] getAllUnions() {
		return new Class[] {
			Union_COLGROUP_ScriptSupporting.class,
			Union_DATALIST_OPTGROUP.class,
			Union_DIV_DL.class,
			Union_DL_Palpable.class,
			Union_Embedded_Interactive.class,
			Union_Embedded_Palpable_Phrasing.class,
			Union_Interactive_Phrasing.class,
			Union_Metadata_Phrasing.class,
			Union_Palpable_Phrasing.class,
			Union_TBODY_THEAD_TFOOT.class
		};
	}

	static void testUnions(Class<? extends Content> clazz, Class<? extends Content> ... expected) {
		InheritanceTests.testInterfaces(
			Content.class,
			iface -> iface.getSimpleName().startsWith("Union_"),
			getAllUnions(),
			clazz,
			expected
		);
	}

	@Test
	public void testNoImplementInherited() {
		for(Class<? extends Content> iface : getAllUnions()) {
			InheritanceTests.testNoImplementInherited(Content.class, iface);
		}
	}
}
