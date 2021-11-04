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
package com.aoapps.html.tests;

import com.aoapps.html.AnyContent;
import com.aoapps.html.Content;
import com.aoapps.html.EmbeddedContent;
import com.aoapps.html.FlowContent;
import com.aoapps.html.HeadingContent;
import com.aoapps.html.InteractiveContent;
import com.aoapps.html.ListContent;
import com.aoapps.html.MetadataContent;
import com.aoapps.html.PalpableContent;
import com.aoapps.html.PhrasingContent;
import com.aoapps.html.ScriptSupportingContent;
import com.aoapps.html.SectioningContent;
import com.aoapps.html.TextContent;
import com.aoapps.html.any.tests.InheritanceTestHelper;
import org.junit.Test;

/**
 * Tests <code>*Content</code> content model interfaces,
 * which confirm a class implements the expected set of interfaces.
 *
 * @author  AO Industries, Inc.
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class ContentModelTest {

	/**
	 * Gets the set of all <code>*Content</code> content model interfaces.
	 */
	static Class<? extends Content>[] getAllContentModels() {
		return new Class[] {
			AnyContent.class,
			Content.class,
			EmbeddedContent.class,
			FlowContent.class,
			HeadingContent.class,
			InteractiveContent.class,
			ListContent.class,
			MetadataContent.class,
			PalpableContent.class,
			PhrasingContent.class,
			ScriptSupportingContent.class,
			SectioningContent.class,
			TextContent.class
		};
	}

	static void testContentModels(Class<? extends Content> clazz, Class<? extends Content> ... expected) {
		InheritanceTestHelper.testInterfaces(
			Content.class,
			iface -> iface.getSimpleName().endsWith("Content"),
			getAllContentModels(),
			clazz,
			expected
		);
	}

	@Test
	public void testNoImplementInherited() {
		for(Class<? extends Content> iface : getAllContentModels()) {
			InheritanceTestHelper.testNoImplementInherited(Content.class, iface);
		}
	}
}
