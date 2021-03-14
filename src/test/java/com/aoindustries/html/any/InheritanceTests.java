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
import com.aoindustries.lang.reflect.Classes;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Predicate;
import org.junit.Assert;

/**
 * Support class for inheritance hierarchy tests.
 *
 * @author  AO Industries, Inc.
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class InheritanceTests {

	private InheritanceTests() {}

	public static <C extends Content> void testInterfaces(
		Class<C> testFromClazz,
		Predicate<? super Class<? extends C>> filter,
		Class<? extends C>[] all,
		Class<? extends C> clazz,
		Class<? extends C> ... expected
	) {
		// Check parameters
		for(Class<? extends C> iface : expected) {
			Assert.assertTrue(iface.isInterface());
			Assert.assertTrue(filter.test(iface));
		}
		// First make sure has all the expected
		for(Class<? extends C> iface : expected) {
			Assert.assertTrue(
				clazz.getSimpleName() + " must be assignable to " + iface.getSimpleName(),
				iface.isAssignableFrom(clazz)
			);
		}
		// Next make sure no unexpected by pattern
		for(Class<? extends C> iface : Classes.getAllClasses(clazz, testFromClazz)) {
			Assert.assertTrue(iface.isAssignableFrom(clazz));
			if(iface != clazz) {
				Assert.assertTrue(iface.isInterface());
				if(filter.test(iface)) {
					Assert.assertNotEquals(
						clazz.getSimpleName() + " may not implement " + iface.getSimpleName(),
						-1,
						AoArrays.indexOf(expected, iface)
					);
				}
			}
		}
		// Next make sure no unexpected versus master list
		for(Class<? extends C> iface : all) {
			if(iface != clazz && AoArrays.indexOf(expected, iface) == -1) {
				Assert.assertFalse(
					clazz.getSimpleName() + " may not be assignable to " + iface.getSimpleName(),
					iface.isAssignableFrom(clazz)
				);
			}
		}
	}

	/**
	 * Makes sure no class or interface directly implements an interface that is also inherited.
	 */
	public static <C extends Content> void testNoImplementInherited(Class<C> testFromClazz, Class<? extends C> clazz) {
		// Find all inherited interfaces
		Set<Class<? extends C>> inherited = new LinkedHashSet<>();
		for(Class<?> iface : clazz.getInterfaces()) {
			if(testFromClazz.isAssignableFrom(iface)) {
				Class<? extends C> contentIface = iface.asSubclass(testFromClazz);
				Set<Class<? extends C>> higherInherited = Classes.getAllClasses(contentIface, testFromClazz);
				Assert.assertTrue(higherInherited.remove(contentIface));
				inherited.addAll(higherInherited);
			}
		}
		// Look for any direct interface that is also inherited
		for(Class<?> iface : clazz.getInterfaces()) {
			if(testFromClazz.isAssignableFrom(iface)) {
				Class<? extends C> contentIface = iface.asSubclass(testFromClazz);
				//System.out.println("Direct interface: " + contentIface);
				Assert.assertFalse(
					clazz.getSimpleName() + " may not both directly implement and inherit " + contentIface.getSimpleName() + " - comment-out direct implements to be inherited-only.",
					inherited.contains(contentIface)
				);
				testNoImplementInherited(testFromClazz, contentIface);
			}
		}
	}

	// TODO: Test generic upper bounds consistency between Element and Content?
}
