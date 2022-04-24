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
import com.aoapps.html.DIV_content;
import com.aoapps.html.EmbeddedContent;
import com.aoapps.html.FlowContent;
import com.aoapps.html.HeadingContent;
import com.aoapps.html.InteractiveContent;
import com.aoapps.html.PalpableContent;
import com.aoapps.html.PhrasingContent;
import com.aoapps.html.ScriptSupportingContent;
import com.aoapps.html.SectioningContent;
import com.aoapps.html.TextContent;
import com.aoapps.html.Union_COLGROUP_ScriptSupporting;
import com.aoapps.html.Union_DIV_DL;
import com.aoapps.html.Union_DL_Palpable;
import com.aoapps.html.Union_Embedded_Interactive;
import com.aoapps.html.Union_Embedded_Palpable_Phrasing;
import com.aoapps.html.Union_Interactive_Phrasing;
import com.aoapps.html.Union_Metadata_Phrasing;
import com.aoapps.html.Union_Palpable_Phrasing;
import com.aoapps.html.any.tests.AnyDIV_contentTest;
import com.aoapps.html.any.tests.InheritanceTestHelper;
import org.junit.Assert;
import org.junit.Test;

/**
 * @see  DIV_content
 *
 * @author  AO Industries, Inc.
 */
public class DIV_contentTest extends AnyDIV_contentTest {

  public DIV_contentTest() {
    super(DIV_content.class);
  }

  @Test
  @SuppressWarnings("unchecked")
  @Override
  public void testUnions() {
    UnionContentTest.testUnions(
        DIV_content.class,
        //
        // Unions:
        //
        Union_COLGROUP_ScriptSupporting.class,
        Union_DIV_DL.class,
        Union_DL_Palpable.class,
        Union_Embedded_Interactive.class,
        Union_Embedded_Palpable_Phrasing.class,
        Union_Interactive_Phrasing.class,
        Union_Metadata_Phrasing.class,
        Union_Palpable_Phrasing.class
    );
  }

  @Test
  @SuppressWarnings("unchecked")
  @Override
  public void testContentModels() {
    ContentModelTest.testContentModels(
        DIV_content.class,
        //
        // Content models:
        //
        Content.class,
        EmbeddedContent.class,
        FlowContent.class,
        HeadingContent.class,
        InteractiveContent.class,
        PalpableContent.class,
        PhrasingContent.class,
        ScriptSupportingContent.class,
        SectioningContent.class,
        TextContent.class
    );
  }

  @Test
  @SuppressWarnings("unchecked")
  @Override
  public void testElementContentModels() {
    ElementContentModelTest.testElementContentModels(
        DIV_content.class
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
        AoArrays.indexOf(ElementContentModelTest.getAllElementContentModels(), DIV_content.class)
    );
    InheritanceTestHelper.testNoImplementInherited(Content.class, DIV_content.class);
  }
}
