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
 * @see Union_Interactive_Phrasing
 *
 * @author  AO Industries, Inc.
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class Union_Interactive_PhrasingTest {

	@Test
	@SuppressWarnings("unchecked")
	public void testUnions() {
		UnionContentTest.testUnions(
			Union_Interactive_Phrasing.class,
			//
			// Unions:
			//
			Union_Embedded_Interactive.class
		);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testContentModels() {
		ContentModelTest.testContentModels(
			Union_Interactive_Phrasing.class,
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
			Union_Interactive_Phrasing.class
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
			Union_Interactive_Phrasing.class,
			//
			// Factories:
			//
			A_factory.class,
			AUDIO_factory.class,
			BUTTON_factory.class,
			EMBED_factory.class,
			INPUT_factory.class,
			IFRAME_factory.class,
			IMG_factory.class,
			LABEL_factory.class,
			OBJECT_factory.class,
			SELECT_factory.class,
			TEXTAREA_factory.class,
			VIDEO_factory.class
		);
	}

	@Test
	public void testNoImplementInherited() {
		Assert.assertNotEquals("Must be included in " + UnionContentTest.class.getSimpleName() + ".getAllUnions()",
			-1,
			AoArrays.indexOf(UnionContentTest.getAllUnions(), Union_Interactive_Phrasing.class)
		);
		InheritanceTests.testNoImplementInherited(Union_Interactive_Phrasing.class);
	}
}
