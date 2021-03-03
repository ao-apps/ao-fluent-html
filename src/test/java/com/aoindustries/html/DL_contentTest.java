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
package com.aoindustries.html;

import com.aoindustries.collections.AoArrays;
import org.junit.Assert;
import org.junit.Test;

/**
 * @see  DL_content
 *
 * @author  AO Industries, Inc.
 */
public class DL_contentTest {

	@Test
	@SuppressWarnings("unchecked")
	public void testUnions() {
		UnionContentTest.testUnions(
			DL_content.class,
			//
			// Unions:
			//
			Union_COLGROUP_ScriptSupporting.class,
			Union_DIV_DL.class,
			Union_DL_Palpable.class
		);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testContentModels() {
		ContentModelTest.testContentModels(
			DL_content.class,
			//
			// Content models:
			//
			Content.class,
			ScriptSupportingContent.class
		);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testElementContentModels() {
		ElementContentModelTest.testElementContentModels(
			DL_content.class
			//
			// Per-element content models:
			//
			// None
		);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testFactories() {
		FactoryTest.testFactories(
			DL_content.class,
			//
			// Factories:
			//
			DD_factory.class,
			DIV_factory.class,
			DT_factory.class,
			SCRIPT_factory.class,
			TEMPLATE_factory.class
		);
	}

	@Test
	public void testNoImplementInherited() {
		Assert.assertNotEquals(
			"Must be included in " + ElementContentModelTest.class.getSimpleName() + ".getAllElementContentModels()",
			-1,
			AoArrays.indexOf(ElementContentModelTest.getAllElementContentModels(), DL_content.class)
		);
		InheritanceTests.testNoImplementInherited(DL_content.class);
	}
}
