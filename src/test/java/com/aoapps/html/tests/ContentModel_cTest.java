/*
 * ao-fluent-html - Fluent Java DSL for high-performance HTML generation.
 * Copyright (C) 2026  AO Industries, Inc.
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

import com.aoapps.html.AnyContent_c;
import com.aoapps.html.Content_c;
import com.aoapps.html.EmbeddedContent_c;
import com.aoapps.html.FlowContent_c;
import com.aoapps.html.HeadingContent_c;
import com.aoapps.html.InteractiveContent_c;
import com.aoapps.html.ListContent_c;
import com.aoapps.html.MetadataContent_c;
import com.aoapps.html.PalpableContent_c;
import com.aoapps.html.PhrasingContent_c;
import com.aoapps.html.ScriptSupportingContent_c;
import com.aoapps.html.SectioningContent_c;
import com.aoapps.html.TextContent_c;
import com.aoapps.html.any.Closeable;
import com.aoapps.html.any.tests.InheritanceTestHelper;
import org.junit.Test;

/**
 * Tests <code>*Content_c</code> {@link Closeable} content model interfaces,
 * which confirm a class implements the expected set of interfaces.
 *
 * @author  AO Industries, Inc.
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class ContentModel_cTest {

  /**
   * Gets the set of all <code>*Content_c</code> {@link Closeable} content model interfaces.
   */
  static Class<? extends Content_c>[] getAllContentModels() {
    return new Class[]{
        AnyContent_c.class,
        Content_c.class,
        EmbeddedContent_c.class,
        FlowContent_c.class,
        HeadingContent_c.class,
        InteractiveContent_c.class,
        ListContent_c.class,
        MetadataContent_c.class,
        PalpableContent_c.class,
        PhrasingContent_c.class,
        ScriptSupportingContent_c.class,
        SectioningContent_c.class,
        TextContent_c.class
    };
  }

  static void testContentModels(Class<? extends Content_c> clazz, Class<? extends Content_c>... expected) {
    InheritanceTestHelper.testInterfaces(
        Content_c.class,
        iface -> iface.getSimpleName().endsWith("Content_c"),
        getAllContentModels(),
        clazz,
        expected
    );
  }

  @Test
  public void testNoImplementInherited() {
    for (Class<? extends Content_c> iface : getAllContentModels()) {
      InheritanceTestHelper.testNoImplementInherited(Content_c.class, iface);
    }
  }
}
