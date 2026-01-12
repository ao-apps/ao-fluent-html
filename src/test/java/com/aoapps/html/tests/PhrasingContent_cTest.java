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

import com.aoapps.collections.AoArrays;
import com.aoapps.html.Content_c;
import com.aoapps.html.EmbeddedContent_c;
import com.aoapps.html.PhrasingContent_c;
import com.aoapps.html.ScriptSupportingContent_c;
import com.aoapps.html.TextContent_c;
import com.aoapps.html.Union_COLGROUP_ScriptSupporting_c;
import com.aoapps.html.Union_Embedded_Interactive_c;
import com.aoapps.html.Union_Embedded_Palpable_Phrasing_c;
import com.aoapps.html.Union_Interactive_Phrasing_c;
import com.aoapps.html.Union_Metadata_Phrasing_c;
import com.aoapps.html.Union_Palpable_Phrasing_c;
import com.aoapps.html.any.tests.AnyPhrasingContent_cTest;
import com.aoapps.html.any.tests.InheritanceTestHelper;
import org.junit.Assert;
import org.junit.Test;

/**
 * @see  PhrasingContent_c
 *
 * @author  AO Industries, Inc.
 */
public class PhrasingContent_cTest extends AnyPhrasingContent_cTest {

  public PhrasingContent_cTest() {
    super(PhrasingContent_c.class);
  }

  @Test
  @SuppressWarnings("unchecked")
  @Override
  public void testUnions() {
    UnionContent_cTest.testUnions(
        PhrasingContent_c.class,
        //
        // Unions:
        //
        Union_COLGROUP_ScriptSupporting_c.class,
        Union_Embedded_Interactive_c.class,
        Union_Embedded_Palpable_Phrasing_c.class,
        Union_Interactive_Phrasing_c.class,
        Union_Metadata_Phrasing_c.class,
        Union_Palpable_Phrasing_c.class
    );
  }

  @Test
  @SuppressWarnings("unchecked")
  @Override
  public void testContentModels() {
    ContentModel_cTest.testContentModels(
        PhrasingContent_c.class,
        //
        // Content models:
        //
        Content_c.class,
        EmbeddedContent_c.class,
        ScriptSupportingContent_c.class,
        TextContent_c.class
    );
  }

  @Test
  @SuppressWarnings("unchecked")
  @Override
  public void testElementContentModels() {
    ElementContentModel_cTest.testElementContentModels(
        PhrasingContent_c.class
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
        "Must be included in " + ContentModel_cTest.class.getSimpleName() + ".getAllContentModels()",
        -1,
        AoArrays.indexOf(ContentModel_cTest.getAllContentModels(), PhrasingContent_c.class)
    );
    InheritanceTestHelper.testNoImplementInherited(Content_c.class, PhrasingContent_c.class);
  }
}
