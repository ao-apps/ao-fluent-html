/*
 * ao-fluent-html - Fluent Java DSL for high-performance HTML generation.
 * Copyright (C) 2021, 2022  AO Industries, Inc.
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

package com.aoapps.html.tests;

import com.aoapps.collections.AoArrays;
import com.aoapps.html.Content;
import com.aoapps.html.OPTGROUP_content;
import com.aoapps.html.ScriptSupportingContent;
import com.aoapps.html.Union_COLGROUP_ScriptSupporting;
import com.aoapps.html.Union_DATALIST_OPTGROUP;
import com.aoapps.html.any.tests.AnyOPTGROUP_contentTest;
import com.aoapps.html.any.tests.InheritanceTestHelper;
import org.junit.Assert;
import org.junit.Test;

/**
 * @see  OPTGROUP_content
 *
 * @author  AO Industries, Inc.
 */
public class OPTGROUP_contentTest extends AnyOPTGROUP_contentTest {

	public OPTGROUP_contentTest() {
		super(OPTGROUP_content.class);
	}

	@Test
	@SuppressWarnings("unchecked")
	@Override
	public void testUnions() {
		UnionContentTest.testUnions(
			OPTGROUP_content.class,
			//
			// Unions:
			//
			Union_COLGROUP_ScriptSupporting.class,
			Union_DATALIST_OPTGROUP.class
		);
	}

	@Test
	@SuppressWarnings("unchecked")
	@Override
	public void testContentModels() {
		ContentModelTest.testContentModels(
			OPTGROUP_content.class,
			//
			// Content models:
			//
			Content.class,
			ScriptSupportingContent.class
		);
	}

	@Test
	@SuppressWarnings("unchecked")
	@Override
	public void testElementContentModels() {
		ElementContentModelTest.testElementContentModels(
			OPTGROUP_content.class
			//
			// Per-element content models:
			//
			// None
		);
	}

	@Test
	@Override
	public void testNoImplementInherited() {
		Assert.assertNotEquals(
			"Must be included in " + ElementContentModelTest.class.getSimpleName() + ".getAllElementContentModels()",
			-1,
			AoArrays.indexOf(ElementContentModelTest.getAllElementContentModels(), OPTGROUP_content.class)
		);
		InheritanceTestHelper.testNoImplementInherited(Content.class, OPTGROUP_content.class);
	}
}
