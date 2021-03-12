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

import com.aoindustries.lang.reflect.Classes;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.apache.bcel.classfile.AnnotationEntry;
import org.apache.bcel.classfile.ClassParser;
import org.apache.bcel.classfile.ElementValue;
import org.apache.bcel.classfile.ElementValuePair;
import org.apache.bcel.classfile.Method;
import org.apache.bcel.util.ClassPath;
import org.junit.Assert;

/**
 * Tests {@link Factory} element factory methods,
 * which confirm a class implements the expected set of elements.
 *
 * @author  AO Industries, Inc.
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class FactoryTest {

	/**
	 * Gets the set of all element factory names.
	 */
	static String[] getAllFactories() {
		return new String[] {
			"abbr",
			"address",
			"a",
			"area",
			"article",
			"aside",
			"audio",
			"base",
			"bdi",
			"bdo",
			"b",
			"blockquote",
			"body",
			"br",
			"button",
			"canvas",
			"caption",
			"cite",
			"code",
			"col",
			"colgroup",
			"data",
			"datalist",
			"dd",
			"del",
			"details",
			"dfn",
			"dialog",
			"div",
			"dl",
			"dt",
			"embed",
			"em",
			"fieldset",
			"figcaption",
			"figure",
			"form",
			"footer",
			"h1",
			"h2",
			"h3",
			"h4",
			"h5",
			"h6",
			"h#",
			"header",
			"head",
			"hgroup",
			"hr",
			"html",
			"i",
			"iframe",
			"img",
			"input",
			"ins",
			"kbd",
			"label",
			"legend",
			"li",
			"link",
			"main",
			"map",
			"mark",
			"menu",
			"meta",
			"meter",
			"nav",
			"noscript",
			"object",
			"ol",
			"optgroup",
			"option",
			"output",
			"param",
			"p",
			"picture",
			"pre",
			"progress",
			"q",
			"rp",
			"rt",
			"ruby",
			"samp",
			"script",
			"section",
			"select",
			"s",
			"slot",
			"small",
			"source",
			"span",
			"strong",
			"style",
			"sub",
			"summary",
			"sup",
			"table",
			"tbody",
			"td",
			"template",
			"textarea",
			"tfoot",
			"thead",
			"th",
			"time",
			"title",
			"track",
			"tr",
			"u",
			"ul",
			"var",
			"video",
			"wbr"
		};
	}

	private static final String FACTORY_ANNOTATION_TYPE = "L" + Factory.class.getCanonicalName().replace('.', '/') + ";";

	private static final Map<Class<? extends Content>, Set<String>> factoryCache = new HashMap<>();

	/**
	 * Finds the set of all element factory name for the given class.
	 */
	private static void getFactoryNames(Class<? extends Content> clazz, Set<String> factoryNames) throws IOException {
		Set<String> factories = factoryCache.get(clazz);
		if(factories == null) {
			factories = new HashSet<>();
			ClassPath.ClassFile classFile = ClassPath.SYSTEM_CLASS_PATH.getClassFile(clazz.getCanonicalName());
			for(
				Method method :
				new ClassParser(classFile.getInputStream(), classFile.getPath()).parse().getMethods()
			) {
				for(AnnotationEntry annotation : method.getAnnotationEntries()) {
					String annotationType = annotation.getAnnotationType();
					if(FACTORY_ANNOTATION_TYPE.equals(annotationType)) {
						for(ElementValuePair pair : annotation.getElementValuePairs()) {
							if("value".equals(pair.getNameString())) {
								ElementValue value = pair.getValue();
								int valueType = value.getElementValueType();
								if(valueType == ElementValue.STRING) {
									factories.add(value.toString());
								} else {
									Assert.fail("Unexected annotation element value type (" + valueType + "): " + pair.toShortString());
								}
							} else {
								Assert.fail("Unexected annotation element: " + pair.toShortString());
							}
						}
					} else if(
						// Allow Deprecated
						!annotationType.startsWith("Ljava/lang")
					) {
						Assert.fail("Unexected annotation type \"" + annotationType + "\": " + annotation);
					}
				}
			}
			factoryCache.put(clazz, factories);
		}
		factoryNames.addAll(factories);
	}

	static void testFactories(Class<? extends Content> clazz, String ... expected) throws IOException {
		String[] all = getAllFactories();
		Set<String> allSet = new HashSet<>(Arrays.asList(all));
		// Check parameters
		for(String factory : expected) {
			Assert.assertTrue("factory not in getAllFactories(): " + factory, allSet.contains(factory));
		}
		// Find all content interfaces
		Set<Class<? extends Content>> allClasses = Classes.getAllClasses(clazz, Content.class);
		// First make sure has all the expected
		Set<String> clazzFactories = new HashSet<>();
		for(Class<? extends Content> iface : allClasses) {
			getFactoryNames(iface, clazzFactories);
		}
		for(String factory : expected) {
			Assert.assertTrue(
				clazz.getSimpleName() + " must have factory methods for \"" + factory + '"',
				clazzFactories.contains(factory)
			);
		}
		// Next make sure no unexpected
		Set<String> expectedSet = new HashSet<>(Arrays.asList(expected));
		for(String factory : clazzFactories) {
			Assert.assertTrue("factory not in getAllFactories(): " + factory, allSet.contains(factory));
			Assert.assertTrue(
				clazz.getSimpleName() + " may not have factory methods for \"" + factory + '"',
				expectedSet.contains(factory)
			);
		}
		// TODO: Test factory not inherited from different interfaces?
		//       Would this instead verify an @OverrideFactory annotation, which would require all override?
	}
}
