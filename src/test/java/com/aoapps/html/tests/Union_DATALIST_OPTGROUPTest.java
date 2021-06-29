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
package com.aoapps.html.tests;

import com.aoapps.collections.AoArrays;
import com.aoapps.html.Content;
import com.aoapps.html.Union_DATALIST_OPTGROUP;
import com.aoapps.html.any.tests.AnyUnion_DATALIST_OPTGROUPTest;
import com.aoapps.html.any.tests.InheritanceTests;
import org.junit.Assert;
import org.junit.Test;

/**
 * @see  Union_DATALIST_OPTGROUP
 *
 * @author  AO Industries, Inc.
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class Union_DATALIST_OPTGROUPTest extends AnyUnion_DATALIST_OPTGROUPTest {

	public Union_DATALIST_OPTGROUPTest() {
		super(Union_DATALIST_OPTGROUP.class);
	}

	@Test
	@SuppressWarnings("unchecked")
	@Override
	public void testUnions() {
		UnionContentTest.testUnions(
			Union_DATALIST_OPTGROUP.class
			//
			// Unions:
			//
			// NOne
		);
	}

	@Test
	@SuppressWarnings("unchecked")
	@Override
	public void testContentModels() {
		ContentModelTest.testContentModels(
			Union_DATALIST_OPTGROUP.class,
			//
			// Content models:
			//
			Content.class
		);
	}

	@Test
	@SuppressWarnings("unchecked")
	@Override
	public void testElementContentModels() {
		ElementContentModelTest.testElementContentModels(
			Union_DATALIST_OPTGROUP.class
			//
			// Per-element content models:
			//
			// None
		);
	}

	@Test
	@Override
	public void testNoImplementInherited() {
		Assert.assertNotEquals("Must be included in " + UnionContentTest.class.getSimpleName() + ".getAllUnions()",
			-1,
			AoArrays.indexOf(UnionContentTest.getAllUnions(), Union_DATALIST_OPTGROUP.class)
		);
		InheritanceTests.testNoImplementInherited(Content.class, Union_DATALIST_OPTGROUP.class);
	}
}
