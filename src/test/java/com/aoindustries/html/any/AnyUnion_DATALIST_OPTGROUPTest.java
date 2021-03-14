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
package com.aoindustries.html.any;

import com.aoindustries.collections.AoArrays;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;

/**
 * @see  AnyUnion_DATALIST_OPTGROUP
 *
 * @author  AO Industries, Inc.
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class AnyUnion_DATALIST_OPTGROUPTest {

	private final Class<? extends AnyUnion_DATALIST_OPTGROUP> testingClass;

	protected AnyUnion_DATALIST_OPTGROUPTest(Class<? extends AnyUnion_DATALIST_OPTGROUP> testingClass) {
		this.testingClass = testingClass;
	}

	public AnyUnion_DATALIST_OPTGROUPTest() {
		this(AnyUnion_DATALIST_OPTGROUP.class);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testUnions() {
		AnyUnionContentTest.testUnions(
			AnyUnion_DATALIST_OPTGROUP.class
			//
			// Unions:
			//
			// NOne
		);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testContentModels() {
		ContentModelTest.testContentModels(
			AnyUnion_DATALIST_OPTGROUP.class,
			//
			// Content models:
			//
			Content.class
		);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testElementContentModels() {
		ElementContentModelTest.testElementContentModels(
			AnyUnion_DATALIST_OPTGROUP.class
			//
			// Per-element content models:
			//
			// None
		);
	}

	@Test
	public void testFactories() throws IOException {
		FactoryTest.testFactories(
			testingClass,
			//
			// Factories:
			//
			"option"
		);
	}

	@Test
	public void testNoImplementInherited() {
		Assert.assertNotEquals("Must be included in " + AnyUnionContentTest.class.getSimpleName() + ".getAllUnions()",
			-1,
			AoArrays.indexOf(AnyUnionContentTest.getAllUnions(), AnyUnion_DATALIST_OPTGROUP.class)
		);
		InheritanceTests.testNoImplementInherited(Content.class, AnyUnion_DATALIST_OPTGROUP.class);
	}
}
