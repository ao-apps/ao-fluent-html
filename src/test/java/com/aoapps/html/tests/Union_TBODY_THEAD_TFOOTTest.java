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
import com.aoapps.html.ScriptSupportingContent;
import com.aoapps.html.Union_COLGROUP_ScriptSupporting;
import com.aoapps.html.Union_TBODY_THEAD_TFOOT;
import com.aoapps.html.any.tests.AnyUnion_TBODY_THEAD_TFOOTTest;
import com.aoapps.html.any.tests.InheritanceTestHelper;
import org.junit.Assert;
import org.junit.Test;

/**
 * @see  Union_TBODY_THEAD_TFOOT
 *
 * @author  AO Industries, Inc.
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class Union_TBODY_THEAD_TFOOTTest extends AnyUnion_TBODY_THEAD_TFOOTTest {

  public Union_TBODY_THEAD_TFOOTTest() {
    super(Union_TBODY_THEAD_TFOOT.class);
  }

  @Test
  @SuppressWarnings("unchecked")
  @Override
  public void testUnions() {
    UnionContentTest.testUnions(
        Union_TBODY_THEAD_TFOOT.class,
        //
        // Unions:
        //
        Union_COLGROUP_ScriptSupporting.class
    );
  }

  @Test
  @SuppressWarnings("unchecked")
  @Override
  public void testContentModels() {
    ContentModelTest.testContentModels(
        Union_TBODY_THEAD_TFOOT.class,
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
        Union_TBODY_THEAD_TFOOT.class
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
        AoArrays.indexOf(UnionContentTest.getAllUnions(), Union_TBODY_THEAD_TFOOT.class)
    );
    InheritanceTestHelper.testNoImplementInherited(Content.class, Union_TBODY_THEAD_TFOOT.class);
  }
}
