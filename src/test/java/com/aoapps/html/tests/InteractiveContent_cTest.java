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
import com.aoapps.html.InteractiveContent_c;
import com.aoapps.html.Union_Embedded_Interactive_c;
import com.aoapps.html.Union_Interactive_Phrasing_c;
import com.aoapps.html.any.tests.AnyInteractiveContent_cTest;
import com.aoapps.html.any.tests.InheritanceTestHelper;
import org.junit.Assert;
import org.junit.Test;

/**
 * @see  InteractiveContent_c
 *
 * @author  AO Industries, Inc.
 */
public class InteractiveContent_cTest extends AnyInteractiveContent_cTest {

  public InteractiveContent_cTest() {
    super(InteractiveContent_c.class);
  }

  @Test
  @SuppressWarnings("unchecked")
  @Override
  public void testUnions() {
    UnionContent_cTest.testUnions(
        InteractiveContent_c.class,
        //
        // Unions:
        //
        Union_Embedded_Interactive_c.class,
        Union_Interactive_Phrasing_c.class
    );
  }

  @Test
  @SuppressWarnings("unchecked")
  @Override
  public void testContentModels() {
    ContentModel_cTest.testContentModels(
        InteractiveContent_c.class,
        //
        // Content models:
        //
        Content_c.class
    );
  }

  @Test
  @SuppressWarnings("unchecked")
  @Override
  public void testElementContentModels() {
    ElementContentModel_cTest.testElementContentModels(
        InteractiveContent_c.class
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
        AoArrays.indexOf(ContentModel_cTest.getAllContentModels(), InteractiveContent_c.class)
    );
    InheritanceTestHelper.testNoImplementInherited(Content_c.class, InteractiveContent_c.class);
  }
}
