/*
 * ao-fluent-html - Fluent Java DSL for high-performance HTML generation.
 * Copyright (C) 2019, 2020, 2021  AO Industries, Inc.
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

import com.aoindustries.encoding.Doctype;
import static com.aoindustries.encoding.JavaScriptInXhtmlAttributeEncoder.javaScriptInXhtmlAttributeEncoder;
import com.aoindustries.encoding.MediaEncoder;
import com.aoindustries.encoding.MediaWritable;
import com.aoindustries.encoding.MediaWriter;
import com.aoindustries.encoding.Serialization;
import static com.aoindustries.encoding.TextInXhtmlAttributeEncoder.encodeTextInXhtmlAttribute;
import static com.aoindustries.encoding.TextInXhtmlAttributeEncoder.textInXhtmlAttributeEncoder;
import com.aoindustries.i18n.Resources;
import com.aoindustries.io.function.IOSupplierE;
import com.aoindustries.lang.Coercion;
import com.aoindustries.lang.LocalizedIllegalArgumentException;
import com.aoindustries.lang.Strings;
import com.aoindustries.util.i18n.MarkupCoercion;
import com.aoindustries.util.i18n.MarkupType;
import com.aoindustries.validation.InvalidResult;
import com.aoindustries.validation.ValidResult;
import com.aoindustries.validation.ValidationResult;
import com.aoindustries.xml.XmlUtils;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.io.IOException;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Locale;
import java.util.Objects;
import java.util.function.Function;

/**
 * See <a href="https://www.w3schools.com/tags/ref_attributes.asp">HTML Attributes</a>.
 *
 * @author  AO Industries, Inc.
 */
// TODO: Skip the space before attribute name after element had nl() called?
//       This may require more tracking than it's worth, especially considerting the potential for direct unsafe writes.
// TODO: We should probably be using long/Long for integer values.
// TODO: Review which attributes should be trimmed and/or nullIfEmpty
public class Attributes {

	private static final Resources RESOURCES = Resources.getResources(Attributes.class);

	/** Make no instances. */
	private Attributes() {}

	/**
	 * Special value used in-place of return values that should result in an empty
	 * attribute (expected on {@link Serialization#SGML} only).
	 * This distinguishes from a return value of {@code null}, which causes the
	 * attribute to not be added at all.
	 * <p>
	 * In order to never conflict with an actual attribute value, this string is
	 * compared by identity, not by value.
	 * </p>
	 */
	@SuppressWarnings("RedundantStringConstructorCall")
	public static final java.lang.String NO_VALUE = new java.lang.String("<<<NO_VALUE>>>"); // Use string constructor to ensure unique instance for identity comparisons

	/**
	 * Marks a method as being an attribute funnel to aid in implementation.
	 * A funnel is one of the methods that directly implements the attribute.
	 * Non-funnel methods must call directly, or indirectly, funnel methods.
	 * All funnel methods must be marked with this annotation.
	 * <p>
	 * When implementations need to override behavior, such as recording values
	 * or checking preconditions, only the funnel methods need to be overridden.
	 * </p>
	 */
	@Retention(RetentionPolicy.SOURCE)
	@Target(ElementType.METHOD)
	static @interface Funnel {
	}

	/**
	 * Checks a validation result.
	 *
	 * @return  The value when valid
	 * @throws  IllegalArgumentException  When invalid, supporting {@link LocalizedIllegalArgumentException} when
	 *                                    validationResult is a {@link InvalidResult}
	 */
	public static <T> T validate(T value, ValidationResult validationResult) throws IllegalArgumentException {
		if(validationResult.isValid()) {
			return value;
		} else {
			if(validationResult instanceof InvalidResult) {
				InvalidResult invalidResult = (InvalidResult)validationResult;
				throw new LocalizedIllegalArgumentException(
					invalidResult.getResources(),
					invalidResult.getKey(),
					invalidResult.getArgs()
				);
			} else {
				throw new IllegalArgumentException(validationResult.toString());
			}
		}
	}

	/**
	 * Validates a value using the provided validator.
	 *
	 * @return  The value when valid
	 * @throws  IllegalArgumentException  When invalid, supporting {@link LocalizedIllegalArgumentException} when
	 *                                    validationResult is a {@link InvalidResult}
	 */
	public static <T> T validate(T value, Function<? super T, ValidationResult> validator) throws IllegalArgumentException {
		return validate(value, validator.apply(value));
	}

	/**
	 * See <a href="https://html.spec.whatwg.org/multipage/common-microsyntaxes.html#boolean-attributes">2.4.2 Boolean attributes</a>.
	 */
	public static class Boolean {

		/** Make no instances. */
		private Boolean() {}

		static <E extends Element<E, ?>> E attribute(E element, java.lang.String name, boolean value) throws IOException {
			if(value) {
				element.document.out.write(' ');
				element.document.out.write(name);
				if(element.document.serialization == Serialization.XML) {
					element.document.out.write("=\"");
					element.document.out.write(name);
					element.document.out.write('"');
				} else {
					assert element.document.serialization == Serialization.SGML;
				}
			}
			return element;
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_async.asp">HTML async Attribute</a>.
		 */
		public static interface Async<E extends Element<E, ?> & Async<E>> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_async.asp">HTML async Attribute</a>.
			 */
			@Funnel
			default E async(boolean async) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "async", async);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_async.asp">HTML async Attribute</a>.
			 *
			 * @see #async(boolean)
			 */
			default E async(java.lang.Boolean async) throws IOException {
				return async(async != null && async);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_async.asp">HTML async Attribute</a>.
			 *
			 * @see #async(java.lang.Boolean)
			 */
			default <Ex extends Throwable> E async(IOSupplierE<? extends java.lang.Boolean, Ex> async) throws IOException, Ex {
				return async((async == null) ? null : async.get());
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_autofocus.asp">HTML autofocus Attribute</a>.
		 */
		public static interface Autofocus<E extends Element<E, ?> & Autofocus<E>> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_autofocus.asp">HTML autofocus Attribute</a>.
			 */
			@Funnel
			default E autofocus(boolean autofocus) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				if(element.document.doctype != Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						RESOURCES,
						"onlySupportedInHtml5",
						element.document.doctype,
						"autofocus"
					);
				}
				return attribute(element, "autofocus", autofocus);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_autofocus.asp">HTML autofocus Attribute</a>.
			 *
			 * @see #autofocus(boolean)
			 */
			default E autofocus(java.lang.Boolean autofocus) throws IOException {
				return autofocus(autofocus != null && autofocus);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_autofocus.asp">HTML autofocus Attribute</a>.
			 *
			 * @see #autofocus(java.lang.Boolean)
			 */
			default <Ex extends Throwable> E autofocus(IOSupplierE<? extends java.lang.Boolean, Ex> autofocus) throws IOException, Ex {
				return autofocus((autofocus == null) ? null : autofocus.get());
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_checked.asp">HTML checked Attribute</a>.
		 */
		public static interface Checked<E extends Element<E, ?> & Checked<E>> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_checked.asp">HTML checked Attribute</a>.
			 */
			@Funnel
			default E checked(boolean checked) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "checked", checked);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_checked.asp">HTML checked Attribute</a>.
			 *
			 * @see #checked(boolean)
			 */
			default E checked(java.lang.Boolean checked) throws IOException {
				return checked(checked != null && checked);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_checked.asp">HTML checked Attribute</a>.
			 *
			 * @see #checked(java.lang.Boolean)
			 */
			default <Ex extends Throwable> E checked(IOSupplierE<? extends java.lang.Boolean, Ex> checked) throws IOException, Ex {
				return checked((checked == null) ? null : checked.get());
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_defer.asp">HTML defer Attribute</a>.
		 */
		public static interface Defer<E extends Element<E, ?> & Defer<E>> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_defer.asp">HTML defer Attribute</a>.
			 */
			@Funnel
			default E defer(boolean defer) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "defer", defer);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_defer.asp">HTML defer Attribute</a>.
			 *
			 * @see #defer(boolean)
			 */
			default E defer(java.lang.Boolean defer) throws IOException {
				return defer(defer != null && defer);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_defer.asp">HTML defer Attribute</a>.
			 *
			 * @see #defer(java.lang.Boolean)
			 */
			default <Ex extends Throwable> E defer(IOSupplierE<? extends java.lang.Boolean, Ex> defer) throws IOException, Ex {
				return defer((defer == null) ? null : defer.get());
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_disabled.asp">HTML disabled Attribute</a>.
		 */
		public static interface Disabled<E extends Element<E, ?> & Disabled<E>> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_disabled.asp">HTML disabled Attribute</a>.
			 */
			@Funnel
			default E disabled(boolean disabled) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "disabled", disabled);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_disabled.asp">HTML disabled Attribute</a>.
			 *
			 * @see #disabled(boolean)
			 */
			default E disabled(java.lang.Boolean disabled) throws IOException {
				return disabled(disabled != null && disabled);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_disabled.asp">HTML disabled Attribute</a>.
			 *
			 * @see #disabled(java.lang.Boolean)
			 */
			default <Ex extends Throwable> E disabled(IOSupplierE<? extends java.lang.Boolean, Ex> disabled) throws IOException, Ex {
				return disabled((disabled == null) ? null : disabled.get());
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_ismap.asp">HTML ismap Attribute</a>.
		 */
		public static interface Ismap<E extends Element<E, ?> & Ismap<E>> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_ismap.asp">HTML ismap Attribute</a>.
			 */
			@Funnel
			default E ismap(boolean ismap) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "ismap", ismap);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_ismap.asp">HTML ismap Attribute</a>.
			 *
			 * @see #ismap(boolean)
			 */
			default E ismap(java.lang.Boolean ismap) throws IOException {
				return ismap(ismap != null && ismap);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_ismap.asp">HTML ismap Attribute</a>.
			 *
			 * @see #ismap(java.lang.Boolean)
			 */
			default <Ex extends Throwable> E ismap(IOSupplierE<? extends java.lang.Boolean, Ex> ismap) throws IOException, Ex {
				return ismap((ismap == null) ? null : ismap.get());
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_multiple.asp">HTML multiple Attribute</a>.
		 */
		public static interface Multiple<E extends Element<E, ?> & Multiple<E>> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_multiple.asp">HTML multiple Attribute</a>.
			 */
			@Funnel
			default E multiple(boolean multiple) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				if(element.document.doctype != Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						RESOURCES,
						"onlySupportedInHtml5",
						element.document.doctype,
						"multiple"
					);
				}
				return attribute(element, "multiple", multiple);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_multiple.asp">HTML multiple Attribute</a>.
			 *
			 * @see #multiple(boolean)
			 */
			default E multiple(java.lang.Boolean multiple) throws IOException {
				return multiple(multiple != null && multiple);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_multiple.asp">HTML multiple Attribute</a>.
			 *
			 * @see #multiple(java.lang.Boolean)
			 */
			default <Ex extends Throwable> E multiple(IOSupplierE<? extends java.lang.Boolean, Ex> multiple) throws IOException, Ex {
				return multiple((multiple == null) ? null : multiple.get());
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_hr_noshade.asp">HTML hr noshade Attribute</a>.
		 *
		 * @deprecated  The noshade attribute of <code>&lt;hr&gt;</code> is not supported in HTML5. Use CSS instead.
		 */
		@Deprecated
		public static interface Noshade<E extends Element<E, ?> & Noshade<E>> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_hr_noshade.asp">HTML hr noshade Attribute</a>.
			 *
			 * @deprecated  The noshade attribute of <code>&lt;hr&gt;</code> is not supported in HTML5. Use CSS instead.
			 */
			@Deprecated
			@Funnel
			default E noshade(boolean noshade) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				if(element.document.doctype == Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						RESOURCES,
						"notSupportedInHtml5",
						"noshade"
					);
				}
				return attribute(element, "noshade", noshade);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_hr_noshade.asp">HTML hr noshade Attribute</a>.
			 *
			 * @see #noshade(boolean)
			 *
			 * @deprecated  The noshade attribute of <code>&lt;hr&gt;</code> is not supported in HTML5. Use CSS instead.
			 */
			@Deprecated
			default E noshade(java.lang.Boolean noshade) throws IOException {
				return noshade(noshade != null && noshade);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_hr_noshade.asp">HTML hr noshade Attribute</a>.
			 *
			 * @see #noshade(java.lang.Boolean)
			 *
			 * @deprecated  The noshade attribute of <code>&lt;hr&gt;</code> is not supported in HTML5. Use CSS instead.
			 */
			@Deprecated
			default <Ex extends Throwable> E noshade(IOSupplierE<? extends java.lang.Boolean, Ex> noshade) throws IOException, Ex {
				return noshade((noshade == null) ? null : noshade.get());
			}
		}

		/**
		 * <ul>
		 * <li>See <a href="https://www.w3schools.com/tags/att_readonly.asp">HTML readonly Attribute</a>.</li>
		 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input#htmlattrdefreadonly">&lt;input&gt;: The Input (Form Input) element</a>.</li>
		 * </ul>
		 */
		public static interface Readonly<E extends Element<E, ?> & Readonly<E>> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_readonly.asp">HTML readonly Attribute</a>.
			 */
			@Funnel
			default E readonly(boolean readonly) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "readonly", readonly);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_readonly.asp">HTML readonly Attribute</a>.
			 *
			 * @see #readonly(boolean)
			 */
			default E readonly(java.lang.Boolean readonly) throws IOException {
				return readonly(readonly != null && readonly);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_readonly.asp">HTML readonly Attribute</a>.
			 *
			 * @see #readonly(java.lang.Boolean)
			 */
			default <Ex extends Throwable> E readonly(IOSupplierE<? extends java.lang.Boolean, Ex> readonly) throws IOException, Ex {
				return readonly((readonly == null) ? null : readonly.get());
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_selected.asp">HTML selected Attribute</a>.
		 */
		public static interface Selected<E extends Element<E, ?> & Selected<E>> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_selected.asp">HTML selected Attribute</a>.
			 */
			@Funnel
			default E selected(boolean selected) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "selected", selected);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_selected.asp">HTML selected Attribute</a>.
			 *
			 * @see #selected(boolean)
			 */
			default E selected(java.lang.Boolean selected) throws IOException {
				return selected(selected != null && selected);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_selected.asp">HTML selected Attribute</a>.
			 *
			 * @see #selected(java.lang.Boolean)
			 */
			default <Ex extends Throwable> E selected(IOSupplierE<? extends java.lang.Boolean, Ex> selected) throws IOException, Ex {
				return selected((selected == null) ? null : selected.get());
			}
		}
	}

	/**
	 * See <a href="https://html.spec.whatwg.org/multipage/common-microsyntaxes.html#percentages-and-dimensions">2.4.4.4 Percentages and lengths</a>.
	 * <p>
	 * Supports Integer length or percentage of parent (HTML 4-only).
	 * </p>
	 */
	public static class Dimension {

		/** Make no instances. */
		private Dimension() {}

		static <E extends Element<E, ?>> E attribute(E element, java.lang.String name, int pixels) throws IOException {
			return Integer.attribute(element, name, pixels);
		}

		static <E extends Element<E, ?>> E attribute(E element, java.lang.String name, java.lang.Integer pixels) throws IOException {
			return Integer.attribute(element, name, pixels);
		}

		/**
		 * @deprecated  In HTML 4.01, the value could be defined in pixels or in % of the containing element. In HTML5, the value must be in pixels.
		 */
		@Deprecated
		static <E extends Element<E, ?>> E attribute(E element, java.lang.String name, java.lang.String pixelsOrPercent) throws IOException {
			return String.attribute(element, name, MarkupType.NONE, pixelsOrPercent, true, true);
		}

		/**
		 * <ul>
		 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/area#attr-coords">&lt;area&gt; - HTML: Hypertext Markup Language</a>.</li>
		 * <li>See <a href="https://www.w3schools.com/tags/att_coords.asp">HTML coords Attribute</a>.</li>
		 * </ul>
		 */
		public static interface Coords<E extends Element<E, ?> & Coords<E>> {

			/**
			 * <ul>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/area#attr-coords">&lt;area&gt; - HTML: Hypertext Markup Language</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/att_coords.asp">HTML coords Attribute</a>.</li>
			 * </ul>
			 */
			@Funnel
			default E coords(java.lang.String coords) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "coords", coords);
			}

			/**
			 * <ul>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/area#attr-coords">&lt;area&gt; - HTML: Hypertext Markup Language</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/att_coords.asp">HTML coords Attribute</a>.</li>
			 * </ul>
			 *
			 * @see #coords(java.lang.String)
			 */
			@SuppressWarnings("overloads")
			default <Ex extends Throwable> E coords(IOSupplierE<? extends java.lang.String, Ex> coords) throws IOException, Ex {
				return coords((coords == null) ? null : coords.get());
			}

			// RECT

			/**
			 * <ul>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/area#attr-coords">&lt;area&gt; - HTML: Hypertext Markup Language</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/att_coords.asp">HTML coords Attribute</a>.</li>
			 * </ul>
			 */
			default E coords(int left, int top, int right, int bottom) throws IOException {
				return coords(
					java.lang.Integer.toString(left),
					java.lang.Integer.toString(top),
					java.lang.Integer.toString(right),
					java.lang.Integer.toString(bottom)
				);
			}

			/**
			 * <ul>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/area#attr-coords">&lt;area&gt; - HTML: Hypertext Markup Language</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/att_coords.asp">HTML coords Attribute</a>.</li>
			 * </ul>
			 */
			default E coords(java.lang.Integer left, java.lang.Integer top, java.lang.Integer right, java.lang.Integer bottom) throws IOException {
				return coords(
					Objects.toString(left, null),
					Objects.toString(top, null),
					Objects.toString(right, null),
					Objects.toString(bottom, null)
				);
			}

			/**
			 * <ul>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/area#attr-coords">&lt;area&gt; - HTML: Hypertext Markup Language</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/att_coords.asp">HTML coords Attribute</a>.</li>
			 * </ul>
			 */
			default E coords(Rectangle rect) throws IOException {
				if(rect != null) {
					return coords(rect.x, rect.y, rect.x + rect.width, rect.y + rect.height);
				} else {
					return coords((java.lang.Integer)null, null, null, null);
				}
			}

			/**
			 * <ul>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/area#attr-coords">&lt;area&gt; - HTML: Hypertext Markup Language</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/att_coords.asp">HTML coords Attribute</a>.</li>
			 * </ul>
			 *
			 * @see #coords(java.awt.Rectangle)
			 */
			@SuppressWarnings("overloads")
			default <Ex extends Throwable> E coords(Suppliers.Rectangle<Ex> rect) throws IOException, Ex {
				return coords((rect == null) ? null : rect.get());
			}

			/**
			 * <ul>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/area#attr-coords">&lt;area&gt; - HTML: Hypertext Markup Language</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/att_coords.asp">HTML coords Attribute</a>.</li>
			 * </ul>
			 *
			 * @deprecated  In HTML4, the values are numbers of pixels or percentages, if a percent sign (%) is appended;
			 *              in HTML5, the values are numbers of CSS pixels.
			 */
			@Deprecated
			default E coords(java.lang.String left, java.lang.String top, java.lang.String right, java.lang.String bottom) throws IOException {
				left = Strings.trimNullIfEmpty(left);
				top = Strings.trimNullIfEmpty(top);
				right = Strings.trimNullIfEmpty(right);
				bottom = Strings.trimNullIfEmpty(bottom);
				if(left != null || top != null || right != null || bottom != null) {
					return coords(left + "," + top + "," + right + "," + bottom);
				} else {
					return coords((java.lang.String)null);
				}
			}

			// CIRCLE

			/**
			 * <ul>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/area#attr-coords">&lt;area&gt; - HTML: Hypertext Markup Language</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/att_coords.asp">HTML coords Attribute</a>.</li>
			 * </ul>
			 */
			default E coords(int x, int y, int radius) throws IOException {
				return coords(
					java.lang.Integer.toString(x),
					java.lang.Integer.toString(y),
					java.lang.Integer.toString(radius)
				);
			}

			/**
			 * <ul>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/area#attr-coords">&lt;area&gt; - HTML: Hypertext Markup Language</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/att_coords.asp">HTML coords Attribute</a>.</li>
			 * </ul>
			 */
			default E coords(java.lang.Integer x, java.lang.Integer y, java.lang.Integer radius) throws IOException {
				return coords(
					Objects.toString(x, null),
					Objects.toString(y, null),
					Objects.toString(radius, null)
				);
			}

			/**
			 * <ul>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/area#attr-coords">&lt;area&gt; - HTML: Hypertext Markup Language</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/att_coords.asp">HTML coords Attribute</a>.</li>
			 * </ul>
			 */
			default E coords(Circle circle) throws IOException {
				if(circle != null) {
					return coords(circle.getX(), circle.getY(), circle.getRadius());
				} else {
					return coords((java.lang.Integer)null, null, null);
				}
			}

			/**
			 * <ul>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/area#attr-coords">&lt;area&gt; - HTML: Hypertext Markup Language</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/att_coords.asp">HTML coords Attribute</a>.</li>
			 * </ul>
			 *
			 * @see #coords(com.aoindustries.html.Circle)
			 */
			@SuppressWarnings("overloads")
			default <Ex extends Throwable> E coords(Suppliers.Circle<Ex> circle) throws IOException, Ex {
				return coords((circle == null) ? null : circle.get());
			}

			/**
			 * <ul>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/area#attr-coords">&lt;area&gt; - HTML: Hypertext Markup Language</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/att_coords.asp">HTML coords Attribute</a>.</li>
			 * </ul>
			 *
			 * @deprecated  In HTML4, the values are numbers of pixels or percentages, if a percent sign (%) is appended;
			 *              in HTML5, the values are numbers of CSS pixels.
			 */
			@Deprecated
			default E coords(java.lang.String x, java.lang.String y, java.lang.String radius) throws IOException {
				x = Strings.trimNullIfEmpty(x);
				y = Strings.trimNullIfEmpty(y);
				radius = Strings.trimNullIfEmpty(radius);
				if(x != null || y != null || radius != null) {
					return coords(x + "," + y + "," + radius);
				} else {
					return coords((java.lang.String)null);
				}
			}

			// POLY

			/**
			 * <ul>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/area#attr-coords">&lt;area&gt; - HTML: Hypertext Markup Language</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/att_coords.asp">HTML coords Attribute</a>.</li>
			 * </ul>
			 */
			default E coords(Point ... poly) throws IOException {
				// TODO: This could be done via a streaming attribute at the cost of not going through the current single funnel
				StringBuilder sb = new StringBuilder();
				if(poly != null) {
					for(Point p : poly) {
						if(p != null) {
							if(sb.length() > 0) sb.append(',');
							sb.append(p.x).append(',').append(p.y);
						}
					}
				}
				return coords(sb.length() == 0 ? (java.lang.String)null : sb.toString());
			}

			/**
			 * <ul>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/area#attr-coords">&lt;area&gt; - HTML: Hypertext Markup Language</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/att_coords.asp">HTML coords Attribute</a>.</li>
			 * </ul>
			 */
			default E coords(Polygon poly) throws IOException {
				if(poly == null || poly.npoints == 0) {
					return coords((java.lang.String)null);
				} else {
					// TODO: This could be done via a streaming attribute at the cost of not going through the current single funnel
					StringBuilder sb = new StringBuilder();
					for(int i = 0; i < poly.npoints; i++) {
						if(sb.length() > 0) sb.append(',');
						sb.append(poly.xpoints[i]).append(',').append(poly.ypoints[i]);
					}
					return coords(sb.toString());
				}
			}

			/**
			 * <ul>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/area#attr-coords">&lt;area&gt; - HTML: Hypertext Markup Language</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/att_coords.asp">HTML coords Attribute</a>.</li>
			 * </ul>
			 *
			 * @see #coords(java.awt.Polygon)
			 */
			@SuppressWarnings("overloads")
			default <Ex extends Throwable> E coords(Suppliers.Polygon<Ex> poly) throws IOException, Ex {
				return coords((poly == null) ? null : poly.get());
			}

			// Shape base class

			/**
			 * <ul>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/area#attr-coords">&lt;area&gt; - HTML: Hypertext Markup Language</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/att_coords.asp">HTML coords Attribute</a>.</li>
			 * </ul>
			 */
			default E coords(java.awt.Shape shape) throws IOException {
				if(shape == null) return coords((java.lang.String)null);
				if(shape instanceof Rectangle) return coords((Rectangle)shape);
				if(shape instanceof Circle) return coords((Circle)shape);
				if(shape instanceof Polygon) return coords((Polygon)shape);
				throw new LocalizedIllegalArgumentException(
					RESOURCES,
					"Dimension.Coords.unexpectedShape",
					java.awt.Shape.class.getName(),
					"coords",
					java.awt.Rectangle.class.getName(),
					Circle.class.getName(),
					java.awt.Polygon.class.getName(),
					shape.getClass().getName(),
					shape.toString()
				);
			}

			/**
			 * <ul>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/area#attr-coords">&lt;area&gt; - HTML: Hypertext Markup Language</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/att_coords.asp">HTML coords Attribute</a>.</li>
			 * </ul>
			 *
			 * @see #coords(java.awt.Shape)
			 */
			@SuppressWarnings("overloads")
			default <Ex extends Throwable> E coords(Suppliers.Shape<Ex> shape) throws IOException, Ex {
				return coords((shape == null) ? null : shape.get());
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_width.asp">HTML width Attribute</a>.
		 */
		public static interface Width<E extends Element<E, ?> & Width<E>> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_width.asp">HTML width Attribute</a>.
			 */
			@Funnel
			default E width(int pixels) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "width", pixels);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_width.asp">HTML width Attribute</a>.
			 */
			@Funnel
			default E width(java.lang.Integer pixels) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "width", pixels);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_width.asp">HTML width Attribute</a>.
			 *
			 * @see #width(java.lang.Integer)
			 */
			@SuppressWarnings("overloads")
			default <Ex extends Throwable> E width(IOSupplierE<? extends java.lang.Integer, Ex> pixels) throws IOException, Ex {
				return width((pixels == null) ? null : pixels.get());
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_width.asp">HTML width Attribute</a>.
			 *
			 * @deprecated  In HTML 4.01, the width could be defined in pixels or in % of the containing element. In HTML5, the value must be in pixels.
			 */
			@Deprecated
			@Funnel
			default E width(java.lang.String pixelsOrPercent) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "width", pixelsOrPercent);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_width.asp">HTML width Attribute</a>.
			 *
			 * @see #width(java.lang.String)
			 *
			 * @deprecated  In HTML 4.01, the width could be defined in pixels or in % of the containing element. In HTML5, the value must be in pixels.
			 */
			@Deprecated
			@SuppressWarnings("overloads")
			default <Ex extends Throwable> E width(Suppliers.String<Ex> pixelsOrPercent) throws IOException, Ex {
				return width((pixelsOrPercent == null) ? null : pixelsOrPercent.get());
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_width.asp">HTML width Attribute</a>.
		 *
		 * @deprecated  The width attribute is not supported in HTML5. Use CSS instead.
		 */
		@Deprecated
		public static interface WidthHtml4Only<E extends Element<E, ?> & WidthHtml4Only<E>> extends Width<E> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_width.asp">HTML width Attribute</a>.
			 *
			 * @deprecated  The width attribute is not supported in HTML5. Use CSS instead.
			 */
			@Deprecated
			@Override
			@Funnel
			default E width(int pixels) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				if(element.document.doctype == Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						RESOURCES,
						"notSupportedInHtml5",
						"width"
					);
				}
				return Width.super.width(pixels);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_width.asp">HTML width Attribute</a>.
			 *
			 * @deprecated  The width attribute is not supported in HTML5. Use CSS instead.
			 */
			@Deprecated
			@Override
			@Funnel
			default E width(java.lang.Integer pixels) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				if(element.document.doctype == Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						RESOURCES,
						"notSupportedInHtml5",
						"width"
					);
				}
				return Width.super.width(pixels);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_width.asp">HTML width Attribute</a>.
			 *
			 * @deprecated  The width attribute is not supported in HTML5. Use CSS instead.
			 */
			@Deprecated
			@Override
			@SuppressWarnings("overloads")
			default <Ex extends Throwable> E width(IOSupplierE<? extends java.lang.Integer, Ex> pixels) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				if(element.document.doctype == Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						RESOURCES,
						"notSupportedInHtml5",
						"width"
					);
				}
				return Width.super.width(pixels);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_width.asp">HTML width Attribute</a>.
			 *
			 * @deprecated  The width attribute is not supported in HTML5. Use CSS instead.
			 */
			@Deprecated
			@Override
			@Funnel
			default E width(java.lang.String pixelsOrPercent) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				if(element.document.doctype == Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						RESOURCES,
						"notSupportedInHtml5",
						"width"
					);
				}
				return Width.super.width(pixelsOrPercent);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_width.asp">HTML width Attribute</a>.
			 *
			 * @see #width(java.lang.String)
			 *
			 * @deprecated  The width attribute is not supported in HTML5. Use CSS instead.
			 */
			@Deprecated
			@Override
			@SuppressWarnings("overloads")
			default <Ex extends Throwable> E width(Suppliers.String<Ex> pixelsOrPercent) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				if(element.document.doctype == Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						RESOURCES,
						"notSupportedInHtml5",
						"width"
					);
				}
				return Width.super.width(pixelsOrPercent);
			}
		}
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/ref_eventattributes.asp">HTML Event Attributes</a>.
	 */
	public static class Event {

		/** Make no instances. */
		private Event() {}

		static <E extends Element<E, ?>, Ex extends Throwable> E attribute(E element, java.lang.String name, Object script) throws IOException, Ex {
			return Text.attribute(element, name, MarkupType.JAVASCRIPT, script, true, true, javaScriptInXhtmlAttributeEncoder);
		}

		public static class Window {

			/** Make no instances. */
			private Window() {}

			/**
			 * See <a href="https://www.w3schools.com/tags/ev_onafterprint.asp">HTML onafterprint Event Attribute</a>.
			 */
			public static interface Onafterprint<E extends Element<E, ?> & Onafterprint<E>> {

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onafterprint.asp">HTML onafterprint Event Attribute</a>.
				 */
				@Funnel
				default E onafterprint(Object onafterprint) throws IOException {
					@SuppressWarnings("unchecked") E element = (E)this;
					if(element.document.doctype != Doctype.HTML5) {
						throw new LocalizedIllegalArgumentException(
							RESOURCES,
							"onlySupportedInHtml5",
							element.document.doctype,
							"onafterprint"
						);
					}
					return Event.attribute(element, "onafterprint", onafterprint);
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onafterprint.asp">HTML onafterprint Event Attribute</a>.
				 *
				 * @see #onafterprint(java.lang.Object)
				 */
				default <Ex extends Throwable> E onafterprint(IOSupplierE<?, Ex> onafterprint) throws IOException, Ex {
					return onafterprint((onafterprint == null) ? null : onafterprint.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onafterprint.asp">HTML onafterprint Event Attribute</a>.
				 *
				 * @see #onafterprint(java.lang.Object)
				 */
				default <Ex extends Throwable> E onafterprint(MediaWritable<Ex> onafterprint) throws IOException, Ex {
					return onafterprint((Object)onafterprint);
				}
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/ev_onbeforeprint.asp">HTML onbeforeprint Event Attribute</a>.
			 */
			public static interface Onbeforeprint<E extends Element<E, ?> & Onbeforeprint<E>> {

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onbeforeprint.asp">HTML onbeforeprint Event Attribute</a>.
				 */
				@Funnel
				default E onbeforeprint(Object onbeforeprint) throws IOException {
					@SuppressWarnings("unchecked") E element = (E)this;
					if(element.document.doctype != Doctype.HTML5) {
						throw new LocalizedIllegalArgumentException(
							RESOURCES,
							"onlySupportedInHtml5",
							element.document.doctype,
							"onbeforeprint"
						);
					}
					return Event.attribute(element, "onbeforeprint", onbeforeprint);
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onbeforeprint.asp">HTML onbeforeprint Event Attribute</a>.
				 *
				 * @see #onbeforeprint(java.lang.Object)
				 */
				default <Ex extends Throwable> E onbeforeprint(IOSupplierE<?, Ex> onbeforeprint) throws IOException, Ex {
					return onbeforeprint((onbeforeprint == null) ? null : onbeforeprint.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onbeforeprint.asp">HTML onbeforeprint Event Attribute</a>.
				 *
				 * @see #onbeforeprint(java.lang.Object)
				 */
				default <Ex extends Throwable> E onbeforeprint(MediaWritable<Ex> onbeforeprint) throws IOException, Ex {
					return onbeforeprint((Object)onbeforeprint);
				}
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/ev_onbeforeunload.asp">HTML onbeforeunload Event Attribute</a>.
			 */
			public static interface Onbeforeunload<E extends Element<E, ?> & Onbeforeunload<E>> {

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onbeforeunload.asp">HTML onbeforeunload Event Attribute</a>.
				 */
				@Funnel
				default E onbeforeunload(Object onbeforeunload) throws IOException {
					@SuppressWarnings("unchecked") E element = (E)this;
					return Event.attribute(element, "onbeforeunload", onbeforeunload);
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onbeforeunload.asp">HTML onbeforeunload Event Attribute</a>.
				 *
				 * @see #onbeforeunload(java.lang.Object)
				 */
				default <Ex extends Throwable> E onbeforeunload(IOSupplierE<?, Ex> onbeforeunload) throws IOException, Ex {
					return onbeforeunload((onbeforeunload == null) ? null : onbeforeunload.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onbeforeunload.asp">HTML onbeforeunload Event Attribute</a>.
				 *
				 * @see #onbeforeunload(java.lang.Object)
				 */
				default <Ex extends Throwable> E onbeforeunload(MediaWritable<Ex> onbeforeunload) throws IOException, Ex {
					return onbeforeunload((Object)onbeforeunload);
				}
			}

			// TODO: oncancel
			//       https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes
			//       https://developer.mozilla.org/en-US/docs/Web/API/GlobalEventHandlers/oncancel
			//       https://developer.mozilla.org/en-US/docs/Web/HTML/Element/dialog

			// TODO: onclose
			//       https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes
			//       https://developer.mozilla.org/en-US/docs/Web/API/GlobalEventHandlers/onclose
			//       https://developer.mozilla.org/en-US/docs/Web/HTML/Element/dialog
			
			/**
			 * <ul>
			 * <li>See <a href="https://www.w3schools.com/tags/ev_onerror.asp">HTML onerror Event Attribute</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/jsref/event_onerror.asp">onerror Event</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/att_onerror.asp">HTML onerror Attribute</a>.</li>
			 * </ul>
			 */
			public static interface Onerror<E extends Element<E, ?> & Onerror<E>> {

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onerror.asp">HTML onerror Event Attribute</a>.
				 */
				@Funnel
				default E onerror(Object onerror) throws IOException {
					@SuppressWarnings("unchecked") E element = (E)this;
					return Event.attribute(element, "onerror", onerror);
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onerror.asp">HTML onerror Event Attribute</a>.
				 *
				 * @see #onerror(java.lang.Object)
				 */
				default <Ex extends Throwable> E onerror(IOSupplierE<?, Ex> onerror) throws IOException, Ex {
					return onerror((onerror == null) ? null : onerror.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onerror.asp">HTML onerror Event Attribute</a>.
				 *
				 * @see #onerror(java.lang.Object)
				 */
				default <Ex extends Throwable> E onerror(MediaWritable<Ex> onerror) throws IOException, Ex {
					return onerror((Object)onerror);
				}
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/ev_onhashchange.asp">HTML onhashchange Event Attribute</a>.
			 */
			public static interface Onhashchange<E extends Element<E, ?> & Onhashchange<E>> {

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onhashchange.asp">HTML onhashchange Event Attribute</a>.
				 */
				@Funnel
				default E onhashchange(Object onhashchange) throws IOException {
					@SuppressWarnings("unchecked") E element = (E)this;
					if(element.document.doctype != Doctype.HTML5) {
						throw new LocalizedIllegalArgumentException(
							RESOURCES,
							"onlySupportedInHtml5",
							element.document.doctype,
							"onhashchange"
						);
					}
					return Event.attribute(element, "onhashchange", onhashchange);
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onhashchange.asp">HTML onhashchange Event Attribute</a>.
				 *
				 * @see #onhashchange(java.lang.Object)
				 */
				default <Ex extends Throwable> E onhashchange(IOSupplierE<?, Ex> onhashchange) throws IOException, Ex {
					return onhashchange((onhashchange == null) ? null : onhashchange.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onhashchange.asp">HTML onhashchange Event Attribute</a>.
				 *
				 * @see #onhashchange(java.lang.Object)
				 */
				default <Ex extends Throwable> E onhashchange(MediaWritable<Ex> onhashchange) throws IOException, Ex {
					return onhashchange((Object)onhashchange);
				}
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/ev_onload.asp">HTML onload Event Attribute</a>.
			 */
			public static interface Onload<E extends Element<E, ?> & Onload<E>> {

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onload.asp">HTML onload Event Attribute</a>.
				 */
				@Funnel
				default E onload(Object onload) throws IOException {
					@SuppressWarnings("unchecked") E element = (E)this;
					return Event.attribute(element, "onload", onload);
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onload.asp">HTML onload Event Attribute</a>.
				 *
				 * @see #onload(java.lang.Object)
				 */
				default <Ex extends Throwable> E onload(IOSupplierE<?, Ex> onload) throws IOException, Ex {
					return onload((onload == null) ? null : onload.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onload.asp">HTML onload Event Attribute</a>.
				 *
				 * @see #onload(java.lang.Object)
				 */
				default <Ex extends Throwable> E onload(MediaWritable<Ex> onload) throws IOException, Ex {
					return onload((Object)onload);
				}
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/ev_onmessage.asp">HTML onmessage Event Attribute</a>.
			 */
			public static interface Onmessage<E extends Element<E, ?> & Onmessage<E>> {

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onmessage.asp">HTML onmessage Event Attribute</a>.
				 */
				@Funnel
				default E onmessage(Object onmessage) throws IOException {
					@SuppressWarnings("unchecked") E element = (E)this;
					if(element.document.doctype != Doctype.HTML5) {
						throw new LocalizedIllegalArgumentException(
							RESOURCES,
							"onlySupportedInHtml5",
							element.document.doctype,
							"onmessage"
						);
					}
					return Event.attribute(element, "onmessage", onmessage);
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onmessage.asp">HTML onmessage Event Attribute</a>.
				 *
				 * @see #onmessage(java.lang.Object)
				 */
				default <Ex extends Throwable> E onmessage(IOSupplierE<?, Ex> onmessage) throws IOException, Ex {
					return onmessage((onmessage == null) ? null : onmessage.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onmessage.asp">HTML onmessage Event Attribute</a>.
				 *
				 * @see #onmessage(java.lang.Object)
				 */
				default <Ex extends Throwable> E onmessage(MediaWritable<Ex> onmessage) throws IOException, Ex {
					return onmessage((Object)onmessage);
				}
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/ev_onoffline.asp">HTML onoffline Event Attribute</a>.
			 */
			public static interface Onoffline<E extends Element<E, ?> & Onoffline<E>> {

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onoffline.asp">HTML onoffline Event Attribute</a>.
				 */
				@Funnel
				default E onoffline(Object onoffline) throws IOException {
					@SuppressWarnings("unchecked") E element = (E)this;
					if(element.document.doctype != Doctype.HTML5) {
						throw new LocalizedIllegalArgumentException(
							RESOURCES,
							"onlySupportedInHtml5",
							element.document.doctype,
							"onoffline"
						);
					}
					return Event.attribute(element, "onoffline", onoffline);
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onoffline.asp">HTML onoffline Event Attribute</a>.
				 *
				 * @see #onoffline(java.lang.Object)
				 */
				default <Ex extends Throwable> E onoffline(IOSupplierE<?, Ex> onoffline) throws IOException, Ex {
					return onoffline((onoffline == null) ? null : onoffline.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onoffline.asp">HTML onoffline Event Attribute</a>.
				 *
				 * @see #onoffline(java.lang.Object)
				 */
				default <Ex extends Throwable> E onoffline(MediaWritable<Ex> onoffline) throws IOException, Ex {
					return onoffline((Object)onoffline);
				}
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/ev_ononline.asp">HTML ononline Event Attribute</a>.
			 */
			public static interface Ononline<E extends Element<E, ?> & Ononline<E>> {

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_ononline.asp">HTML ononline Event Attribute</a>.
				 */
				@Funnel
				default E ononline(Object ononline) throws IOException {
					@SuppressWarnings("unchecked") E element = (E)this;
					if(element.document.doctype != Doctype.HTML5) {
						throw new LocalizedIllegalArgumentException(
							RESOURCES,
							"onlySupportedInHtml5",
							element.document.doctype,
							"ononline"
						);
					}
					return Event.attribute(element, "ononline", ononline);
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_ononline.asp">HTML ononline Event Attribute</a>.
				 *
				 * @see #ononline(java.lang.Object)
				 */
				default <Ex extends Throwable> E ononline(IOSupplierE<?, Ex> ononline) throws IOException, Ex {
					return ononline((ononline == null) ? null : ononline.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_ononline.asp">HTML ononline Event Attribute</a>.
				 *
				 * @see #ononline(java.lang.Object)
				 */
				default <Ex extends Throwable> E ononline(MediaWritable<Ex> ononline) throws IOException, Ex {
					return ononline((Object)ononline);
				}
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/ev_onpagehide.asp">HTML onpagehide Event Attribute</a>.
			 */
			public static interface Onpagehide<E extends Element<E, ?> & Onpagehide<E>> {

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onpagehide.asp">HTML onpagehide Event Attribute</a>.
				 */
				@Funnel
				default E onpagehide(Object onpagehide) throws IOException {
					@SuppressWarnings("unchecked") E element = (E)this;
					return Event.attribute(element, "onpagehide", onpagehide);
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onpagehide.asp">HTML onpagehide Event Attribute</a>.
				 *
				 * @see #onpagehide(java.lang.Object)
				 */
				default <Ex extends Throwable> E onpagehide(IOSupplierE<?, Ex> onpagehide) throws IOException, Ex {
					return onpagehide((onpagehide == null) ? null : onpagehide.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onpagehide.asp">HTML onpagehide Event Attribute</a>.
				 *
				 * @see #onpagehide(java.lang.Object)
				 */
				default <Ex extends Throwable> E onpagehide(MediaWritable<Ex> onpagehide) throws IOException, Ex {
					return onpagehide((Object)onpagehide);
				}
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/ev_onpageshow.asp">HTML onpageshow Event Attribute</a>.
			 */
			public static interface Onpageshow<E extends Element<E, ?> & Onpageshow<E>> {

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onpageshow.asp">HTML onpageshow Event Attribute</a>.
				 */
				@Funnel
				default E onpageshow(Object onpageshow) throws IOException {
					@SuppressWarnings("unchecked") E element = (E)this;
					return Event.attribute(element, "onpageshow", onpageshow);
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onpageshow.asp">HTML onpageshow Event Attribute</a>.
				 *
				 * @see #onpageshow(java.lang.Object)
				 */
				default <Ex extends Throwable> E onpageshow(IOSupplierE<?, Ex> onpageshow) throws IOException, Ex {
					return onpageshow((onpageshow == null) ? null : onpageshow.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onpageshow.asp">HTML onpageshow Event Attribute</a>.
				 *
				 * @see #onpageshow(java.lang.Object)
				 */
				default <Ex extends Throwable> E onpageshow(MediaWritable<Ex> onpageshow) throws IOException, Ex {
					return onpageshow((Object)onpageshow);
				}
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/ev_onpopstate.asp">HTML onpopstate Event Attribute</a>.
			 */
			public static interface Onpopstate<E extends Element<E, ?> & Onpopstate<E>> {

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onpopstate.asp">HTML onpopstate Event Attribute</a>.
				 */
				@Funnel
				default E onpopstate(Object onpopstate) throws IOException {
					@SuppressWarnings("unchecked") E element = (E)this;
					return Event.attribute(element, "onpopstate", onpopstate);
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onpopstate.asp">HTML onpopstate Event Attribute</a>.
				 *
				 * @see #onpopstate(java.lang.Object)
				 */
				default <Ex extends Throwable> E onpopstate(IOSupplierE<?, Ex> onpopstate) throws IOException, Ex {
					return onpopstate((onpopstate == null) ? null : onpopstate.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onpopstate.asp">HTML onpopstate Event Attribute</a>.
				 *
				 * @see #onpopstate(java.lang.Object)
				 */
				default <Ex extends Throwable> E onpopstate(MediaWritable<Ex> onpopstate) throws IOException, Ex {
					return onpopstate((Object)onpopstate);
				}
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/ev_onresize.asp">HTML onresize Event Attribute</a>.
			 */
			public static interface Onresize<E extends Element<E, ?> & Onresize<E>> {

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onresize.asp">HTML onresize Event Attribute</a>.
				 */
				@Funnel
				default E onresize(Object onresize) throws IOException {
					@SuppressWarnings("unchecked") E element = (E)this;
					if(element.document.doctype != Doctype.HTML5) {
						throw new LocalizedIllegalArgumentException(
							RESOURCES,
							"onlySupportedInHtml5",
							element.document.doctype,
							"onresize"
						);
					}
					return Event.attribute(element, "onresize", onresize);
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onresize.asp">HTML onresize Event Attribute</a>.
				 *
				 * @see #onresize(java.lang.Object)
				 */
				default <Ex extends Throwable> E onresize(IOSupplierE<?, Ex> onresize) throws IOException, Ex {
					return onresize((onresize == null) ? null : onresize.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onresize.asp">HTML onresize Event Attribute</a>.
				 *
				 * @see #onresize(java.lang.Object)
				 */
				default <Ex extends Throwable> E onresize(MediaWritable<Ex> onresize) throws IOException, Ex {
					return onresize((Object)onresize);
				}
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/ev_onstorage.asp">HTML onstorage Event Attribute</a>.
			 */
			public static interface Onstorage<E extends Element<E, ?> & Onstorage<E>> {

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onstorage.asp">HTML onstorage Event Attribute</a>.
				 */
				@Funnel
				default E onstorage(Object onstorage) throws IOException {
					@SuppressWarnings("unchecked") E element = (E)this;
					return Event.attribute(element, "onstorage", onstorage);
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onstorage.asp">HTML onstorage Event Attribute</a>.
				 *
				 * @see #onstorage(java.lang.Object)
				 */
				default <Ex extends Throwable> E onstorage(IOSupplierE<?, Ex> onstorage) throws IOException, Ex {
					return onstorage((onstorage == null) ? null : onstorage.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onstorage.asp">HTML onstorage Event Attribute</a>.
				 *
				 * @see #onstorage(java.lang.Object)
				 */
				default <Ex extends Throwable> E onstorage(MediaWritable<Ex> onstorage) throws IOException, Ex {
					return onstorage((Object)onstorage);
				}
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/ev_onunload.asp">HTML onunload Event Attribute</a>.
			 */
			public static interface Onunload<E extends Element<E, ?> & Onunload<E>> {

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onunload.asp">HTML onunload Event Attribute</a>.
				 */
				@Funnel
				default E onunload(Object onunload) throws IOException {
					@SuppressWarnings("unchecked") E element = (E)this;
					return Event.attribute(element, "onunload", onunload);
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onunload.asp">HTML onunload Event Attribute</a>.
				 *
				 * @see #onunload(java.lang.Object)
				 */
				default <Ex extends Throwable> E onunload(IOSupplierE<?, Ex> onunload) throws IOException, Ex {
					return onunload((onunload == null) ? null : onunload.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onunload.asp">HTML onunload Event Attribute</a>.
				 *
				 * @see #onunload(java.lang.Object)
				 */
				default <Ex extends Throwable> E onunload(MediaWritable<Ex> onunload) throws IOException, Ex {
					return onunload((Object)onunload);
				}
			}
		}

		public static class Form {

			/** Make no instances. */
			private Form() {}

			/**
			 * See <a href="https://www.w3schools.com/tags/ev_onblur.asp">HTML onblur Event Attribute</a>.
			 */
			public static interface Onblur<E extends Element<E, ?> & Onblur<E>> {

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onblur.asp">HTML onblur Event Attribute</a>.
				 */
				@Funnel
				default E onblur(Object onblur) throws IOException {
					@SuppressWarnings("unchecked") E element = (E)this;
					return Event.attribute(element, "onblur", onblur);
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onblur.asp">HTML onblur Event Attribute</a>.
				 *
				 * @see #onblur(java.lang.Object)
				 */
				default <Ex extends Throwable> E onblur(IOSupplierE<?, Ex> onblur) throws IOException, Ex {
					return onblur((onblur == null) ? null : onblur.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onblur.asp">HTML onblur Event Attribute</a>.
				 *
				 * @see #onblur(java.lang.Object)
				 */
				default <Ex extends Throwable> E onblur(MediaWritable<Ex> onblur) throws IOException, Ex {
					return onblur((Object)onblur);
				}
			}

			/**
			 * <ul>
			 * <li>See <a href="https://www.w3schools.com/tags/ev_onchange.asp">HTML onchange Event Attribute</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/jsref/event_onchange.asp">onchange Event</a>.</li>
			 * </ul>
			 */
			public static interface Onchange<E extends Element<E, ?> & Onchange<E>> {

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onchange.asp">HTML onchange Event Attribute</a>.
				 */
				@Funnel
				default E onchange(Object onchange) throws IOException {
					@SuppressWarnings("unchecked") E element = (E)this;
					return Event.attribute(element, "onchange", onchange);
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onchange.asp">HTML onchange Event Attribute</a>.
				 *
				 * @see #onchange(java.lang.Object)
				 */
				default <Ex extends Throwable> E onchange(IOSupplierE<?, Ex> onchange) throws IOException, Ex {
					return onchange((onchange == null) ? null : onchange.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onchange.asp">HTML onchange Event Attribute</a>.
				 *
				 * @see #onchange(java.lang.Object)
				 */
				default <Ex extends Throwable> E onchange(MediaWritable<Ex> onchange) throws IOException, Ex {
					return onchange((Object)onchange);
				}
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/ev_oncontextmenu.asp">HTML oncontextmenu Event Attribute</a>.
			 */
			public static interface Oncontextmenu<E extends Element<E, ?> & Oncontextmenu<E>> {

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_oncontextmenu.asp">HTML oncontextmenu Event Attribute</a>.
				 */
				@Funnel
				default E oncontextmenu(Object oncontextmenu) throws IOException {
					@SuppressWarnings("unchecked") E element = (E)this;
					if(element.document.doctype != Doctype.HTML5) {
						throw new LocalizedIllegalArgumentException(
							RESOURCES,
							"onlySupportedInHtml5",
							element.document.doctype,
							"oncontextmenu"
						);
					}
					return Event.attribute(element, "oncontextmenu", oncontextmenu);
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_oncontextmenu.asp">HTML oncontextmenu Event Attribute</a>.
				 *
				 * @see #oncontextmenu(java.lang.Object)
				 */
				default <Ex extends Throwable> E oncontextmenu(IOSupplierE<?, Ex> oncontextmenu) throws IOException, Ex {
					return oncontextmenu((oncontextmenu == null) ? null : oncontextmenu.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_oncontextmenu.asp">HTML oncontextmenu Event Attribute</a>.
				 *
				 * @see #oncontextmenu(java.lang.Object)
				 */
				default <Ex extends Throwable> E oncontextmenu(MediaWritable<Ex> oncontextmenu) throws IOException, Ex {
					return oncontextmenu((Object)oncontextmenu);
				}
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/ev_onfocus.asp">HTML onfocus Event Attribute</a>.
			 */
			public static interface Onfocus<E extends Element<E, ?> & Onfocus<E>> {

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onfocus.asp">HTML onfocus Event Attribute</a>.
				 */
				@Funnel
				default E onfocus(Object onfocus) throws IOException {
					@SuppressWarnings("unchecked") E element = (E)this;
					return Event.attribute(element, "onfocus", onfocus);
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onfocus.asp">HTML onfocus Event Attribute</a>.
				 *
				 * @see #onfocus(java.lang.Object)
				 */
				default <Ex extends Throwable> E onfocus(IOSupplierE<?, Ex> onfocus) throws IOException, Ex {
					return onfocus((onfocus == null) ? null : onfocus.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onfocus.asp">HTML onfocus Event Attribute</a>.
				 *
				 * @see #onfocus(java.lang.Object)
				 */
				default <Ex extends Throwable> E onfocus(MediaWritable<Ex> onfocus) throws IOException, Ex {
					return onfocus((Object)onfocus);
				}
			}

			/**
			 * <ul>
			 * <li>See <a href="https://www.w3schools.com/tags/ev_oninput.asp">HTML oninput Event Attribute</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/jsref/event_oninput.asp">oninput Event</a>.</li>
			 * </ul>
			 */
			public static interface Oninput<E extends Element<E, ?> & Oninput<E>> {

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_oninput.asp">HTML oninput Event Attribute</a>.
				 */
				@Funnel
				default E oninput(Object oninput) throws IOException {
					@SuppressWarnings("unchecked") E element = (E)this;
					if(element.document.doctype != Doctype.HTML5) {
						throw new LocalizedIllegalArgumentException(
							RESOURCES,
							"onlySupportedInHtml5",
							element.document.doctype,
							"oninput"
						);
					}
					return Event.attribute(element, "oninput", oninput);
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_oninput.asp">HTML oninput Event Attribute</a>.
				 *
				 * @see #oninput(java.lang.Object)
				 */
				default <Ex extends Throwable> E oninput(IOSupplierE<?, Ex> oninput) throws IOException, Ex {
					return oninput((oninput == null) ? null : oninput.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_oninput.asp">HTML oninput Event Attribute</a>.
				 *
				 * @see #oninput(java.lang.Object)
				 */
				default <Ex extends Throwable> E oninput(MediaWritable<Ex> oninput) throws IOException, Ex {
					return oninput((Object)oninput);
				}
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/ev_oninvalid.asp">HTML oninvalid Event Attribute</a>.
			 */
			public static interface Oninvalid<E extends Element<E, ?> & Oninvalid<E>> {

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_oninvalid.asp">HTML oninvalid Event Attribute</a>.
				 */
				@Funnel
				default E oninvalid(Object oninvalid) throws IOException {
					@SuppressWarnings("unchecked") E element = (E)this;
					return Event.attribute(element, "oninvalid", oninvalid);
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_oninvalid.asp">HTML oninvalid Event Attribute</a>.
				 *
				 * @see #oninvalid(java.lang.Object)
				 */
				default <Ex extends Throwable> E oninvalid(IOSupplierE<?, Ex> oninvalid) throws IOException, Ex {
					return oninvalid((oninvalid == null) ? null : oninvalid.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_oninvalid.asp">HTML oninvalid Event Attribute</a>.
				 *
				 * @see #oninvalid(java.lang.Object)
				 */
				default <Ex extends Throwable> E oninvalid(MediaWritable<Ex> oninvalid) throws IOException, Ex {
					return oninvalid((Object)oninvalid);
				}
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/ev_onreset.asp">HTML onreset Event Attribute</a>.
			 */
			public static interface Onreset<E extends Element<E, ?> & Onreset<E>> {

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onreset.asp">HTML onreset Event Attribute</a>.
				 */
				@Funnel
				default E onreset(Object onreset) throws IOException {
					@SuppressWarnings("unchecked") E element = (E)this;
					if(element.document.doctype != Doctype.HTML5) {
						throw new LocalizedIllegalArgumentException(
							RESOURCES,
							"onlySupportedInHtml5",
							element.document.doctype,
							"onreset"
						);
					}
					return Event.attribute(element, "onreset", onreset);
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onreset.asp">HTML onreset Event Attribute</a>.
				 *
				 * @see #onreset(java.lang.Object)
				 */
				default <Ex extends Throwable> E onreset(IOSupplierE<?, Ex> onreset) throws IOException, Ex {
					return onreset((onreset == null) ? null : onreset.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onreset.asp">HTML onreset Event Attribute</a>.
				 *
				 * @see #onreset(java.lang.Object)
				 */
				default <Ex extends Throwable> E onreset(MediaWritable<Ex> onreset) throws IOException, Ex {
					return onreset((Object)onreset);
				}
			}

			/**
			 * <ul>
			 * <li>See <a href="https://www.w3schools.com/tags/ev_onsearch.asp">HTML onsearch Event Attribute</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/jsref/event_onsearch.asp">onsearch Event</a>.</li>
			 * </ul>
			 */
			public static interface Onsearch<E extends Element<E, ?> & Onsearch<E>> {

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onsearch.asp">HTML onsearch Event Attribute</a>.
				 */
				@Funnel
				default E onsearch(Object onsearch) throws IOException {
					@SuppressWarnings("unchecked") E element = (E)this;
					return Event.attribute(element, "onsearch", onsearch);
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onsearch.asp">HTML onsearch Event Attribute</a>.
				 *
				 * @see #onsearch(java.lang.Object)
				 */
				default <Ex extends Throwable> E onsearch(IOSupplierE<?, Ex> onsearch) throws IOException, Ex {
					return onsearch((onsearch == null) ? null : onsearch.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onsearch.asp">HTML onsearch Event Attribute</a>.
				 *
				 * @see #onsearch(java.lang.Object)
				 */
				default <Ex extends Throwable> E onsearch(MediaWritable<Ex> onsearch) throws IOException, Ex {
					return onsearch((Object)onsearch);
				}
			}

			/**
			 * <ul>
			 * <li>See <a href="https://www.w3schools.com/tags/ev_onselect.asp">HTML onselect Event Attribute</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/jsref/event_onselect.asp">onselect Event</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/API/GlobalEventHandlers/onselect">GlobalEventHandlers.onselect</a>.</li>
			 * </ul>
			 */
			public static interface Onselect<E extends Element<E, ?> & Onselect<E>> {

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onselect.asp">HTML onselect Event Attribute</a>.
				 */
				@Funnel
				default E onselect(Object onselect) throws IOException {
					@SuppressWarnings("unchecked") E element = (E)this;
					return Event.attribute(element, "onselect", onselect);
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onselect.asp">HTML onselect Event Attribute</a>.
				 *
				 * @see #onselect(java.lang.Object)
				 */
				default <Ex extends Throwable> E onselect(IOSupplierE<?, Ex> onselect) throws IOException, Ex {
					return onselect((onselect == null) ? null : onselect.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onselect.asp">HTML onselect Event Attribute</a>.
				 *
				 * @see #onselect(java.lang.Object)
				 */
				default <Ex extends Throwable> E onselect(MediaWritable<Ex> onselect) throws IOException, Ex {
					return onselect((Object)onselect);
				}
			}

			// TODO: onshow: https://www.w3schools.com/tags/ev_onshow.asp
			//               https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes
			//               (removed from HTML 5?)
			
			/**
			 * See <a href="https://www.w3schools.com/tags/ev_onsubmit.asp">HTML onsubmit Event Attribute</a>.
			 */
			public static interface Onsubmit<E extends Element<E, ?> & Onsubmit<E>> {

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onsubmit.asp">HTML onsubmit Event Attribute</a>.
				 */
				@Funnel
				default E onsubmit(Object onsubmit) throws IOException {
					@SuppressWarnings("unchecked") E element = (E)this;
					return Event.attribute(element, "onsubmit", onsubmit);
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onsubmit.asp">HTML onsubmit Event Attribute</a>.
				 *
				 * @see #onsubmit(java.lang.Object)
				 */
				default <Ex extends Throwable> E onsubmit(IOSupplierE<?, Ex> onsubmit) throws IOException, Ex {
					return onsubmit((onsubmit == null) ? null : onsubmit.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onsubmit.asp">HTML onsubmit Event Attribute</a>.
				 *
				 * @see #onsubmit(java.lang.Object)
				 */
				default <Ex extends Throwable> E onsubmit(MediaWritable<Ex> onsubmit) throws IOException, Ex {
					return onsubmit((Object)onsubmit);
				}
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/ref_eventattributes.asp">Form Events</a>.
			 * Supported HTML tags:
			 * <blockquote>
			 * All HTML elements, EXCEPT: &lt;base&gt;, &lt;bdo&gt;, &lt;br&gt;, &lt;head&gt;, &lt;html&gt;, &lt;iframe&gt;, &lt;meta&gt;, &lt;param&gt;, &lt;script&gt;, &lt;style&gt;, and &lt;title&gt;
			 * </blockquote>
			 */
			public static interface AlmostGlobal<E extends Element<E, ?> & AlmostGlobal<E>> extends
				Onblur<E>,
				Onfocus<E>
			{
				// No methods, just adding form event types
			}

			/**
			 * <ul>
			 * <li>See <a href="https://www.w3schools.com/tags/ref_eventattributes.asp">Form Events</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes">Global attributes</a>.</li>
			 * </ul>
			 */
			@SuppressWarnings("MarkerInterface")
			public static interface Global<E extends Element<E, ?> & Global<E>> extends
				Oncontextmenu<E>
				// TODO: onautocomplete
				// TODO: onautocompleteerror
			{
				// No methods, just adding form event types
			}
		}

		public static class Keyboard {

			/** Make no instances. */
			private Keyboard() {}

			/**
			 * See <a href="https://www.w3schools.com/tags/ev_onkeydown.asp">HTML onkeydown Event Attribute</a>.
			 */
			public static interface Onkeydown<E extends Element<E, ?> & Onkeydown<E>> {

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onkeydown.asp">HTML onkeydown Event Attribute</a>.
				 */
				@Funnel
				default E onkeydown(Object onkeydown) throws IOException {
					@SuppressWarnings("unchecked") E element = (E)this;
					return Event.attribute(element, "onkeydown", onkeydown);
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onkeydown.asp">HTML onkeydown Event Attribute</a>.
				 *
				 * @see #onkeydown(java.lang.Object)
				 */
				default <Ex extends Throwable> E onkeydown(IOSupplierE<?, Ex> onkeydown) throws IOException, Ex {
					return onkeydown((onkeydown == null) ? null : onkeydown.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onkeydown.asp">HTML onkeydown Event Attribute</a>.
				 *
				 * @see #onkeydown(java.lang.Object)
				 */
				default <Ex extends Throwable> E onkeydown(MediaWritable<Ex> onkeydown) throws IOException, Ex {
					return onkeydown((Object)onkeydown);
				}
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/ev_onkeypress.asp">HTML onkeypress Event Attribute</a>.
			 */
			public static interface Onkeypress<E extends Element<E, ?> & Onkeypress<E>> {

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onkeypress.asp">HTML onkeypress Event Attribute</a>.
				 */
				@Funnel
				default E onkeypress(Object onkeypress) throws IOException {
					@SuppressWarnings("unchecked") E element = (E)this;
					return Event.attribute(element, "onkeypress", onkeypress);
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onkeypress.asp">HTML onkeypress Event Attribute</a>.
				 *
				 * @see #onkeypress(java.lang.Object)
				 */
				default <Ex extends Throwable> E onkeypress(IOSupplierE<?, Ex> onkeypress) throws IOException, Ex {
					return onkeypress((onkeypress == null) ? null : onkeypress.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onkeypress.asp">HTML onkeypress Event Attribute</a>.
				 *
				 * @see #onkeypress(java.lang.Object)
				 */
				default <Ex extends Throwable> E onkeypress(MediaWritable<Ex> onkeypress) throws IOException, Ex {
					return onkeypress((Object)onkeypress);
				}
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/ev_onkeyup.asp">HTML onkeyup Event Attribute</a>.
			 */
			public static interface Onkeyup<E extends Element<E, ?> & Onkeyup<E>> {

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onkeyup.asp">HTML onkeyup Event Attribute</a>.
				 */
				@Funnel
				default E onkeyup(Object onkeyup) throws IOException {
					@SuppressWarnings("unchecked") E element = (E)this;
					return Event.attribute(element, "onkeyup", onkeyup);
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onkeyup.asp">HTML onkeyup Event Attribute</a>.
				 *
				 * @see #onkeyup(java.lang.Object)
				 */
				default <Ex extends Throwable> E onkeyup(IOSupplierE<?, Ex> onkeyup) throws IOException, Ex {
					return onkeyup((onkeyup == null) ? null : onkeyup.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onkeyup.asp">HTML onkeyup Event Attribute</a>.
				 *
				 * @see #onkeyup(java.lang.Object)
				 */
				default <Ex extends Throwable> E onkeyup(MediaWritable<Ex> onkeyup) throws IOException, Ex {
					return onkeyup((Object)onkeyup);
				}
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/ref_eventattributes.asp">Keyboard Events</a>.
			 * Supported HTML tags:
			 * <blockquote>
			 * All HTML elements, EXCEPT: &lt;base&gt;, &lt;bdo&gt;, &lt;br&gt;, &lt;head&gt;, &lt;html&gt;, &lt;iframe&gt;, &lt;meta&gt;, &lt;param&gt;, &lt;script&gt;, &lt;style&gt;, and &lt;title&gt;
			 * </blockquote>
			 */
			public static interface AlmostGlobal<E extends Element<E, ?> & AlmostGlobal<E>> extends
				Onkeydown<E>,
				Onkeypress<E>,
				Onkeyup<E>
			{
				// No methods, just adding mouse event types
			}
		}

		public static class Mouse {

			/** Make no instances. */
			private Mouse() {}

			/**
			 * See <a href="https://www.w3schools.com/tags/ev_onclick.asp">HTML onclick Event Attribute</a>.
			 */
			public static interface Onclick<E extends Element<E, ?> & Onclick<E>> {

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onclick.asp">HTML onclick Event Attribute</a>.
				 */
				@Funnel
				default E onclick(Object onclick) throws IOException {
					@SuppressWarnings("unchecked") E element = (E)this;
					return Event.attribute(element, "onclick", onclick);
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onclick.asp">HTML onclick Event Attribute</a>.
				 *
				 * @see #onclick(java.lang.Object)
				 */
				default <Ex extends Throwable> E onclick(IOSupplierE<?, Ex> onclick) throws IOException, Ex {
					return onclick((onclick == null) ? null : onclick.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onclick.asp">HTML onclick Event Attribute</a>.
				 *
				 * @see #onclick(java.lang.Object)
				 */
				default <Ex extends Throwable> E onclick(MediaWritable<Ex> onclick) throws IOException, Ex {
					return onclick((Object)onclick);
				}
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/ev_ondblclick.asp">HTML ondblclick Event Attribute</a>.
			 */
			public static interface Ondblclick<E extends Element<E, ?> & Ondblclick<E>> {

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_ondblclick.asp">HTML ondblclick Event Attribute</a>.
				 */
				@Funnel
				default E ondblclick(Object ondblclick) throws IOException {
					@SuppressWarnings("unchecked") E element = (E)this;
					return Event.attribute(element, "ondblclick", ondblclick);
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_ondblclick.asp">HTML ondblclick Event Attribute</a>.
				 *
				 * @see #ondblclick(java.lang.Object)
				 */
				default <Ex extends Throwable> E ondblclick(IOSupplierE<?, Ex> ondblclick) throws IOException, Ex {
					return ondblclick((ondblclick == null) ? null : ondblclick.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_ondblclick.asp">HTML ondblclick Event Attribute</a>.
				 *
				 * @see #ondblclick(java.lang.Object)
				 */
				default <Ex extends Throwable> E ondblclick(MediaWritable<Ex> ondblclick) throws IOException, Ex {
					return ondblclick((Object)ondblclick);
				}
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/ev_onmousedown.asp">HTML onmousedown Event Attribute</a>.
			 */
			public static interface Onmousedown<E extends Element<E, ?> & Onmousedown<E>> {

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onmousedown.asp">HTML onmousedown Event Attribute</a>.
				 */
				@Funnel
				default E onmousedown(Object onmousedown) throws IOException {
					@SuppressWarnings("unchecked") E element = (E)this;
					return Event.attribute(element, "onmousedown", onmousedown);
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onmousedown.asp">HTML onmousedown Event Attribute</a>.
				 *
				 * @see #onmousedown(java.lang.Object)
				 */
				default <Ex extends Throwable> E onmousedown(IOSupplierE<?, Ex> onmousedown) throws IOException, Ex {
					return onmousedown((onmousedown == null) ? null : onmousedown.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onmousedown.asp">HTML onmousedown Event Attribute</a>.
				 *
				 * @see #onmousedown(java.lang.Object)
				 */
				default <Ex extends Throwable> E onmousedown(MediaWritable<Ex> onmousedown) throws IOException, Ex {
					return onmousedown((Object)onmousedown);
				}
			}

			// TODO: onmouseenter: https://www.w3schools.com/jsref/event_onmouseenter.asp
			// TODO: onmouseleave: https://www.w3schools.com/jsref/event_onmouseleave.asp

			/**
			 * See <a href="https://www.w3schools.com/tags/ev_onmousemove.asp">HTML onmousemove Event Attribute</a>.
			 */
			public static interface Onmousemove<E extends Element<E, ?> & Onmousemove<E>> {

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onmousemove.asp">HTML onmousemove Event Attribute</a>.
				 */
				@Funnel
				default E onmousemove(Object onmousemove) throws IOException {
					@SuppressWarnings("unchecked") E element = (E)this;
					return Event.attribute(element, "onmousemove", onmousemove);
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onmousemove.asp">HTML onmousemove Event Attribute</a>.
				 *
				 * @see #onmousemove(java.lang.Object)
				 */
				default <Ex extends Throwable> E onmousemove(IOSupplierE<?, Ex> onmousemove) throws IOException, Ex {
					return onmousemove((onmousemove == null) ? null : onmousemove.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onmousemove.asp">HTML onmousemove Event Attribute</a>.
				 *
				 * @see #onmousemove(java.lang.Object)
				 */
				default <Ex extends Throwable> E onmousemove(MediaWritable<Ex> onmousemove) throws IOException, Ex {
					return onmousemove((Object)onmousemove);
				}
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/ev_onmouseout.asp">HTML onmouseout Event Attribute</a>.
			 */
			public static interface Onmouseout<E extends Element<E, ?> & Onmouseout<E>> {

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onmouseout.asp">HTML onmouseout Event Attribute</a>.
				 */
				@Funnel
				default E onmouseout(Object onmouseout) throws IOException {
					@SuppressWarnings("unchecked") E element = (E)this;
					return Event.attribute(element, "onmouseout", onmouseout);
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onmouseout.asp">HTML onmouseout Event Attribute</a>.
				 *
				 * @see #onmouseout(java.lang.Object)
				 */
				default <Ex extends Throwable> E onmouseout(IOSupplierE<?, Ex> onmouseout) throws IOException, Ex {
					return onmouseout((onmouseout == null) ? null : onmouseout.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onmouseout.asp">HTML onmouseout Event Attribute</a>.
				 *
				 * @see #onmouseout(java.lang.Object)
				 */
				default <Ex extends Throwable> E onmouseout(MediaWritable<Ex> onmouseout) throws IOException, Ex {
					return onmouseout((Object)onmouseout);
				}
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/ev_onmouseover.asp">HTML onmouseover Event Attribute</a>.
			 */
			public static interface Onmouseover<E extends Element<E, ?> & Onmouseover<E>> {

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onmouseover.asp">HTML onmouseover Event Attribute</a>.
				 */
				@Funnel
				default E onmouseover(Object onmouseover) throws IOException {
					@SuppressWarnings("unchecked") E element = (E)this;
					return Event.attribute(element, "onmouseover", onmouseover);
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onmouseover.asp">HTML onmouseover Event Attribute</a>.
				 *
				 * @see #onmouseover(java.lang.Object)
				 */
				default <Ex extends Throwable> E onmouseover(IOSupplierE<?, Ex> onmouseover) throws IOException, Ex {
					return onmouseover((onmouseover == null) ? null : onmouseover.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onmouseover.asp">HTML onmouseover Event Attribute</a>.
				 *
				 * @see #onmouseover(java.lang.Object)
				 */
				default <Ex extends Throwable> E onmouseover(MediaWritable<Ex> onmouseover) throws IOException, Ex {
					return onmouseover((Object)onmouseover);
				}
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/ev_onmouseup.asp">HTML onmouseup Event Attribute</a>.
			 */
			public static interface Onmouseup<E extends Element<E, ?> & Onmouseup<E>> {

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onmouseup.asp">HTML onmouseup Event Attribute</a>.
				 */
				@Funnel
				default E onmouseup(Object onmouseup) throws IOException {
					@SuppressWarnings("unchecked") E element = (E)this;
					return Event.attribute(element, "onmouseup", onmouseup);
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onmouseup.asp">HTML onmouseup Event Attribute</a>.
				 *
				 * @see #onmouseup(java.lang.Object)
				 */
				default <Ex extends Throwable> E onmouseup(IOSupplierE<?, Ex> onmouseup) throws IOException, Ex {
					return onmouseup((onmouseup == null) ? null : onmouseup.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onmouseup.asp">HTML onmouseup Event Attribute</a>.
				 *
				 * @see #onmouseup(java.lang.Object)
				 */
				default <Ex extends Throwable> E onmouseup(MediaWritable<Ex> onmouseup) throws IOException, Ex {
					return onmouseup((Object)onmouseup);
				}
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_onmousewheel.asp">HTML onmousewheel Attribute</a>.
			 *
			 * @deprecated  The onmousewheel attribute is deprecated, you should use the {@linkplain Onwheel onwheel} attribute instead.
			 */
			@Deprecated
			public static interface Onmousewheel<E extends Element<E, ?> & Onmousewheel<E>> {

				/**
				 * See <a href="https://www.w3schools.com/tags/att_onmousewheel.asp">HTML onmousewheel Attribute</a>.
				 *
				 * @deprecated  The onmousewheel attribute is deprecated, you should use the {@linkplain Onwheel#onwheel(java.lang.Object) onwheel} attribute instead.
				 */
				@Deprecated
				@Funnel
				default E onmousewheel(Object onmousewheel) throws IOException {
					@SuppressWarnings("unchecked") E element = (E)this;
					return Event.attribute(element, "onmousewheel", onmousewheel);
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/att_onmousewheel.asp">HTML onmousewheel Attribute</a>.
				 *
				 * @see #onmousewheel(java.lang.Object)
				 *
				 * @deprecated  The onmousewheel attribute is deprecated, you should use the {@linkplain Onwheel#onwheel(com.aoindustries.io.function.IOSupplierE) onwheel} attribute instead.
				 */
				@Deprecated
				default <Ex extends Throwable> E onmousewheel(IOSupplierE<?, Ex> onmousewheel) throws IOException, Ex {
					return onmousewheel((onmousewheel == null) ? null : onmousewheel.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/att_onmousewheel.asp">HTML onmousewheel Attribute</a>.
				 *
				 * @see #onmousewheel(java.lang.Object)
				 *
				 * @deprecated  The onmousewheel attribute is deprecated, you should use the {@linkplain Onwheel#onwheel(com.aoindustries.encoding.MediaWritable) onwheel} attribute instead.
				 */
				@Deprecated
				default <Ex extends Throwable> E onmousewheel(MediaWritable<Ex> onmousewheel) throws IOException, Ex {
					return onmousewheel((Object)onmousewheel);
				}
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/ev_onwheel.asp">HTML onwheel Event Attribute</a>.
			 */
			public static interface Onwheel<E extends Element<E, ?> & Onwheel<E>> {

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onwheel.asp">HTML onwheel Event Attribute</a>.
				 */
				@Funnel
				default E onwheel(Object onwheel) throws IOException {
					@SuppressWarnings("unchecked") E element = (E)this;
					if(element.document.doctype != Doctype.HTML5) {
						throw new LocalizedIllegalArgumentException(
							RESOURCES,
							"onlySupportedInHtml5",
							element.document.doctype,
							"onwheel"
						);
					}
					return Event.attribute(element, "onwheel", onwheel);
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onwheel.asp">HTML onwheel Event Attribute</a>.
				 *
				 * @see #onwheel(java.lang.Object)
				 */
				default <Ex extends Throwable> E onwheel(IOSupplierE<?, Ex> onwheel) throws IOException, Ex {
					return onwheel((onwheel == null) ? null : onwheel.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onwheel.asp">HTML onwheel Event Attribute</a>.
				 *
				 * @see #onwheel(java.lang.Object)
				 */
				default <Ex extends Throwable> E onwheel(MediaWritable<Ex> onwheel) throws IOException, Ex {
					return onwheel((Object)onwheel);
				}
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/ref_eventattributes.asp">Mouse Events</a>.
			 * Supported HTML tags:
			 * <blockquote>
			 * All HTML elements, EXCEPT: &lt;base&gt;, &lt;bdo&gt;, &lt;br&gt;, &lt;head&gt;, &lt;html&gt;, &lt;iframe&gt;, &lt;meta&gt;, &lt;param&gt;, &lt;script&gt;, &lt;style&gt;, and &lt;title&gt;
			 * </blockquote>
			 */
			public static interface AlmostGlobal<E extends Element<E, ?> & AlmostGlobal<E>> extends
				Onclick<E>,
				Ondblclick<E>,
				Onmousedown<E>,
				Onmousemove<E>,
				Onmouseout<E>,
				Onmouseover<E>,
				Onmouseup<E>
			{
				// No methods, just adding mouse event types
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/ref_eventattributes.asp">Mouse Events</a>.
			 */
			public static interface Global<E extends Element<E, ?> & Global<E>> extends
				Onmousewheel<E>,
				Onwheel<E>
			{
				// No methods, just adding mouse event types
			}
		}

		public static class Drag {

			/** Make no instances. */
			private Drag() {}

			/**
			 * See <a href="https://www.w3schools.com/tags/ev_ondrag.asp">HTML ondrag Event Attribute</a>.
			 */
			public static interface Ondrag<E extends Element<E, ?> & Ondrag<E>> {

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_ondrag.asp">HTML ondrag Event Attribute</a>.
				 */
				@Funnel
				default E ondrag(Object ondrag) throws IOException {
					@SuppressWarnings("unchecked") E element = (E)this;
					if(element.document.doctype != Doctype.HTML5) {
						throw new LocalizedIllegalArgumentException(
							RESOURCES,
							"onlySupportedInHtml5",
							element.document.doctype,
							"ondrag"
						);
					}
					return Event.attribute(element, "ondrag", ondrag);
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_ondrag.asp">HTML ondrag Event Attribute</a>.
				 *
				 * @see #ondrag(java.lang.Object)
				 */
				default <Ex extends Throwable> E ondrag(IOSupplierE<?, Ex> ondrag) throws IOException, Ex {
					return ondrag((ondrag == null) ? null : ondrag.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_ondrag.asp">HTML ondrag Event Attribute</a>.
				 *
				 * @see #ondrag(java.lang.Object)
				 */
				default <Ex extends Throwable> E ondrag(MediaWritable<Ex> ondrag) throws IOException, Ex {
					return ondrag((Object)ondrag);
				}
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/ev_ondragend.asp">HTML ondragend Event Attribute</a>.
			 */
			public static interface Ondragend<E extends Element<E, ?> & Ondragend<E>> {

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_ondragend.asp">HTML ondragend Event Attribute</a>.
				 */
				@Funnel
				default E ondragend(Object ondragend) throws IOException {
					@SuppressWarnings("unchecked") E element = (E)this;
					if(element.document.doctype != Doctype.HTML5) {
						throw new LocalizedIllegalArgumentException(
							RESOURCES,
							"onlySupportedInHtml5",
							element.document.doctype,
							"ondragend"
						);
					}
					return Event.attribute(element, "ondragend", ondragend);
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_ondragend.asp">HTML ondragend Event Attribute</a>.
				 *
				 * @see #ondragend(java.lang.Object)
				 */
				default <Ex extends Throwable> E ondragend(IOSupplierE<?, Ex> ondragend) throws IOException, Ex {
					return ondragend((ondragend == null) ? null : ondragend.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_ondragend.asp">HTML ondragend Event Attribute</a>.
				 *
				 * @see #ondragend(java.lang.Object)
				 */
				default <Ex extends Throwable> E ondragend(MediaWritable<Ex> ondragend) throws IOException, Ex {
					return ondragend((Object)ondragend);
				}
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/ev_ondragenter.asp">HTML ondragenter Event Attribute</a>.
			 */
			public static interface Ondragenter<E extends Element<E, ?> & Ondragenter<E>> {

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_ondragenter.asp">HTML ondragenter Event Attribute</a>.
				 */
				@Funnel
				default E ondragenter(Object ondragenter) throws IOException {
					@SuppressWarnings("unchecked") E element = (E)this;
					if(element.document.doctype != Doctype.HTML5) {
						throw new LocalizedIllegalArgumentException(
							RESOURCES,
							"onlySupportedInHtml5",
							element.document.doctype,
							"ondragenter"
						);
					}
					return Event.attribute(element, "ondragenter", ondragenter);
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_ondragenter.asp">HTML ondragenter Event Attribute</a>.
				 *
				 * @see #ondragenter(java.lang.Object)
				 */
				default <Ex extends Throwable> E ondragenter(IOSupplierE<?, Ex> ondragenter) throws IOException, Ex {
					return ondragenter((ondragenter == null) ? null : ondragenter.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_ondragenter.asp">HTML ondragenter Event Attribute</a>.
				 *
				 * @see #ondragenter(java.lang.Object)
				 */
				default <Ex extends Throwable> E ondragenter(MediaWritable<Ex> ondragenter) throws IOException, Ex {
					return ondragenter((Object)ondragenter);
				}
			}

			// ondragexit (Gecko specific - don't use or have a deprecated)

			/**
			 * See <a href="https://www.w3schools.com/tags/ev_ondragleave.asp">HTML ondragleave Event Attribute</a>.
			 */
			public static interface Ondragleave<E extends Element<E, ?> & Ondragleave<E>> {

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_ondragleave.asp">HTML ondragleave Event Attribute</a>.
				 */
				@Funnel
				default E ondragleave(Object ondragleave) throws IOException {
					@SuppressWarnings("unchecked") E element = (E)this;
					if(element.document.doctype != Doctype.HTML5) {
						throw new LocalizedIllegalArgumentException(
							RESOURCES,
							"onlySupportedInHtml5",
							element.document.doctype,
							"ondragleave"
						);
					}
					return Event.attribute(element, "ondragleave", ondragleave);
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_ondragleave.asp">HTML ondragleave Event Attribute</a>.
				 *
				 * @see #ondragleave(java.lang.Object)
				 */
				default <Ex extends Throwable> E ondragleave(IOSupplierE<?, Ex> ondragleave) throws IOException, Ex {
					return ondragleave((ondragleave == null) ? null : ondragleave.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_ondragleave.asp">HTML ondragleave Event Attribute</a>.
				 *
				 * @see #ondragleave(java.lang.Object)
				 */
				default <Ex extends Throwable> E ondragleave(MediaWritable<Ex> ondragleave) throws IOException, Ex {
					return ondragleave((Object)ondragleave);
				}
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/ev_ondragover.asp">HTML ondragover Event Attribute</a>.
			 */
			public static interface Ondragover<E extends Element<E, ?> & Ondragover<E>> {

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_ondragover.asp">HTML ondragover Event Attribute</a>.
				 */
				@Funnel
				default E ondragover(Object ondragover) throws IOException {
					@SuppressWarnings("unchecked") E element = (E)this;
					if(element.document.doctype != Doctype.HTML5) {
						throw new LocalizedIllegalArgumentException(
							RESOURCES,
							"onlySupportedInHtml5",
							element.document.doctype,
							"ondragover"
						);
					}
					return Event.attribute(element, "ondragover", ondragover);
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_ondragover.asp">HTML ondragover Event Attribute</a>.
				 *
				 * @see #ondragover(java.lang.Object)
				 */
				default <Ex extends Throwable> E ondragover(IOSupplierE<?, Ex> ondragover) throws IOException, Ex {
					return ondragover((ondragover == null) ? null : ondragover.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_ondragover.asp">HTML ondragover Event Attribute</a>.
				 *
				 * @see #ondragover(java.lang.Object)
				 */
				default <Ex extends Throwable> E ondragover(MediaWritable<Ex> ondragover) throws IOException, Ex {
					return ondragover((Object)ondragover);
				}
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/ev_ondragstart.asp">HTML ondragstart Event Attribute</a>.
			 */
			public static interface Ondragstart<E extends Element<E, ?> & Ondragstart<E>> {

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_ondragstart.asp">HTML ondragstart Event Attribute</a>.
				 */
				@Funnel
				default E ondragstart(Object ondragstart) throws IOException {
					@SuppressWarnings("unchecked") E element = (E)this;
					if(element.document.doctype != Doctype.HTML5) {
						throw new LocalizedIllegalArgumentException(
							RESOURCES,
							"onlySupportedInHtml5",
							element.document.doctype,
							"ondragstart"
						);
					}
					return Event.attribute(element, "ondragstart", ondragstart);
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_ondragstart.asp">HTML ondragstart Event Attribute</a>.
				 *
				 * @see #ondragstart(java.lang.Object)
				 */
				default <Ex extends Throwable> E ondragstart(IOSupplierE<?, Ex> ondragstart) throws IOException, Ex {
					return ondragstart((ondragstart == null) ? null : ondragstart.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_ondragstart.asp">HTML ondragstart Event Attribute</a>.
				 *
				 * @see #ondragstart(java.lang.Object)
				 */
				default <Ex extends Throwable> E ondragstart(MediaWritable<Ex> ondragstart) throws IOException, Ex {
					return ondragstart((Object)ondragstart);
				}
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/ev_ondrop.asp">HTML ondrop Event Attribute</a>.
			 */
			public static interface Ondrop<E extends Element<E, ?> & Ondrop<E>> {

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_ondrop.asp">HTML ondrop Event Attribute</a>.
				 */
				@Funnel
				default E ondrop(Object ondrop) throws IOException {
					@SuppressWarnings("unchecked") E element = (E)this;
					if(element.document.doctype != Doctype.HTML5) {
						throw new LocalizedIllegalArgumentException(
							RESOURCES,
							"onlySupportedInHtml5",
							element.document.doctype,
							"ondrop"
						);
					}
					return Event.attribute(element, "ondrop", ondrop);
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_ondrop.asp">HTML ondrop Event Attribute</a>.
				 *
				 * @see #ondrop(java.lang.Object)
				 */
				default <Ex extends Throwable> E ondrop(IOSupplierE<?, Ex> ondrop) throws IOException, Ex {
					return ondrop((ondrop == null) ? null : ondrop.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_ondrop.asp">HTML ondrop Event Attribute</a>.
				 *
				 * @see #ondrop(java.lang.Object)
				 */
				default <Ex extends Throwable> E ondrop(MediaWritable<Ex> ondrop) throws IOException, Ex {
					return ondrop((Object)ondrop);
				}
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/ev_onscroll.asp">HTML onscroll Event Attribute</a>.
			 */
			public static interface Onscroll<E extends Element<E, ?> & Onscroll<E>> {

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onscroll.asp">HTML onscroll Event Attribute</a>.
				 */
				@Funnel
				default E onscroll(Object onscroll) throws IOException {
					@SuppressWarnings("unchecked") E element = (E)this;
					if(element.document.doctype != Doctype.HTML5) {
						throw new LocalizedIllegalArgumentException(
							RESOURCES,
							"onlySupportedInHtml5",
							element.document.doctype,
							"onscroll"
						);
					}
					return Event.attribute(element, "onscroll", onscroll);
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onscroll.asp">HTML onscroll Event Attribute</a>.
				 *
				 * @see #onscroll(java.lang.Object)
				 */
				default <Ex extends Throwable> E onscroll(IOSupplierE<?, Ex> onscroll) throws IOException, Ex {
					return onscroll((onscroll == null) ? null : onscroll.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onscroll.asp">HTML onscroll Event Attribute</a>.
				 *
				 * @see #onscroll(java.lang.Object)
				 */
				default <Ex extends Throwable> E onscroll(MediaWritable<Ex> onscroll) throws IOException, Ex {
					return onscroll((Object)onscroll);
				}
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/ref_eventattributes.asp">Drag Events</a>.
			 */
			public static interface Global<E extends Element<E, ?> & Global<E>> extends
				Ondrag<E>,
				Ondragend<E>,
				Ondragenter<E>,
				Ondragleave<E>,
				Ondragover<E>,
				Ondragstart<E>,
				Ondrop<E>
			{
				// No methods, just adding mouse event types
			}
		}

		public static class Clipboard {

			/** Make no instances. */
			private Clipboard() {}

			/**
			 * See <a href="https://www.w3schools.com/tags/ev_oncopy.asp">HTML oncopy Event Attribute</a>.
			 */
			public static interface Oncopy<E extends Element<E, ?> & Oncopy<E>> {

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_oncopy.asp">HTML oncopy Event Attribute</a>.
				 */
				@Funnel
				default E oncopy(Object oncopy) throws IOException {
					@SuppressWarnings("unchecked") E element = (E)this;
					return Event.attribute(element, "oncopy", oncopy);
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_oncopy.asp">HTML oncopy Event Attribute</a>.
				 *
				 * @see #oncopy(java.lang.Object)
				 */
				default <Ex extends Throwable> E oncopy(IOSupplierE<?, Ex> oncopy) throws IOException, Ex {
					return oncopy((oncopy == null) ? null : oncopy.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_oncopy.asp">HTML oncopy Event Attribute</a>.
				 *
				 * @see #oncopy(java.lang.Object)
				 */
				default <Ex extends Throwable> E oncopy(MediaWritable<Ex> oncopy) throws IOException, Ex {
					return oncopy((Object)oncopy);
				}
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/ev_oncut.asp">HTML oncut Event Attribute</a>.
			 */
			public static interface Oncut<E extends Element<E, ?> & Oncut<E>> {

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_oncut.asp">HTML oncut Event Attribute</a>.
				 */
				@Funnel
				default E oncut(Object oncut) throws IOException {
					@SuppressWarnings("unchecked") E element = (E)this;
					return Event.attribute(element, "oncut", oncut);
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_oncut.asp">HTML oncut Event Attribute</a>.
				 *
				 * @see #oncut(java.lang.Object)
				 */
				default <Ex extends Throwable> E oncut(IOSupplierE<?, Ex> oncut) throws IOException, Ex {
					return oncut((oncut == null) ? null : oncut.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_oncut.asp">HTML oncut Event Attribute</a>.
				 *
				 * @see #oncut(java.lang.Object)
				 */
				default <Ex extends Throwable> E oncut(MediaWritable<Ex> oncut) throws IOException, Ex {
					return oncut((Object)oncut);
				}
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/ev_onpaste.asp">HTML onpaste Event Attribute</a>.
			 */
			public static interface Onpaste<E extends Element<E, ?> & Onpaste<E>> {

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onpaste.asp">HTML onpaste Event Attribute</a>.
				 */
				@Funnel
				default E onpaste(Object onpaste) throws IOException {
					@SuppressWarnings("unchecked") E element = (E)this;
					return Event.attribute(element, "onpaste", onpaste);
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onpaste.asp">HTML onpaste Event Attribute</a>.
				 *
				 * @see #onpaste(java.lang.Object)
				 */
				default <Ex extends Throwable> E onpaste(IOSupplierE<?, Ex> onpaste) throws IOException, Ex {
					return onpaste((onpaste == null) ? null : onpaste.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onpaste.asp">HTML onpaste Event Attribute</a>.
				 *
				 * @see #onpaste(java.lang.Object)
				 */
				default <Ex extends Throwable> E onpaste(MediaWritable<Ex> onpaste) throws IOException, Ex {
					return onpaste((Object)onpaste);
				}
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/ref_eventattributes.asp">Drag Events</a>.
			 */
			public static interface Global<E extends Element<E, ?> & Global<E>> extends
				Oncopy<E>,
				Oncut<E>,
				Onpaste<E>
			{
				// No methods, just adding mouse event types
			}
		}

		/**
		 * <ul>
		 * <li>See <a href="https://www.w3schools.com/tags/ref_eventattributes.asp">Media Events</a>.</li>
		 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes">Global attributes</a>.</li>
		 * </ul>
		 */
		public static class Media {

			/** Make no instances. */
			private Media() {}

			/**
			 * See <a href="https://www.w3schools.com/jsref/event_onabort.asp">onabort Event</a>.
			 */
			public static interface Onabort<E extends Element<E, ?> & Onabort<E>> {

				/**
				 * See <a href="https://www.w3schools.com/jsref/event_onabort.asp">onabort Event</a>.
				 */
				@Funnel
				default E onabort(Object onabort) throws IOException {
					@SuppressWarnings("unchecked") E element = (E)this;
					return Event.attribute(element, "onabort", onabort);
				}

				/**
				 * See <a href="https://www.w3schools.com/jsref/event_onabort.asp">onabort Event</a>.
				 *
				 * @see #onabort(java.lang.Object)
				 */
				default <Ex extends Throwable> E onabort(IOSupplierE<?, Ex> onabort) throws IOException, Ex {
					return onabort((onabort == null) ? null : onabort.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/jsref/event_onabort.asp">onabort Event</a>.
				 *
				 * @see #onabort(java.lang.Object)
				 */
				default <Ex extends Throwable> E onabort(MediaWritable<Ex> onabort) throws IOException, Ex {
					return onabort((Object)onabort);
				}
			}

			// TODO: oncanplay
			// TODO: oncanplaythrough
			// TODO: oncuechange
			// TODO: ondurationchange
			// TODO: onemptied
			// TODO: onended
			// TODO: onerror (move from Window here, duplicate?)
			// TODO: onloadeddata
			// TODO: onloadedmetadata
			// TODO: onloadstart
			// TODO: onpause
			// TODO: onplay
			// TODO: onplaying
			// TODO: onprogress
			// TODO: onratechange
			// TODO: onseeked
			// TODO: onseeking
			// TODO: onstalled
			// TODO: onsuspend
			// TODO: ontimeupdate
			// TODO: onvolumechange
			// TODO: onwaiting
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/ref_eventattributes.asp">Misc Events</a>.
		 */
		public static class Misc {

			/** Make no instances. */
			private Misc() {}

			// TODO: ontoggle
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/ref_eventattributes.asp">HTML Event Attributes</a>.
		 * Supported HTML tags:
		 * <blockquote>
		 * All HTML elements, EXCEPT: &lt;base&gt;, &lt;bdo&gt;, &lt;br&gt;, &lt;head&gt;, &lt;html&gt;, &lt;iframe&gt;, &lt;meta&gt;, &lt;param&gt;, &lt;script&gt;, &lt;style&gt;, and &lt;title&gt;
		 * </blockquote>
		 */
		public static interface AlmostGlobal<E extends Element<E, ?> & AlmostGlobal<E>> extends
			Form.AlmostGlobal<E>,
			Keyboard.AlmostGlobal<E>,
			Mouse.AlmostGlobal<E>
		{
			// No methods, just adding event types
		}
	}

	/**
	 * See <a href="https://html.spec.whatwg.org/multipage/common-microsyntaxes.html#keywords-and-enumerated-attributes">2.4.3 Keywords and enumerated attributes</a>.
	 * <p>
	 * Values may be provided either as a {@link java.lang.String} or an {@link java.lang.Enum},
	 * with the {@link java.lang.Enum} representation preferred.  The {@link java.lang.String}
	 * value is allowed for values that are not part of the enumeration.
	 * </p>
	 * <p>
	 * When converting from {@link java.lang.Enum}, uses {@link EnumSupplier#get(com.aoindustries.encoding.Serialization, com.aoindustries.encoding.Doctype)}.
	 * </p>
	 */
	public static class Enum {

		/** Make no instances. */
		private Enum() {}

		@FunctionalInterface
		public static interface EnumSupplier {
			/**
			 * @return  The attribute value, {@link #NO_VALUE} (by identity, not value) for an empty attribute, {@code null} for no attribute.
			 */
			java.lang.String get(Serialization serialization, Doctype doctype);
		}

		/**
		 * See <a href="https://www.w3resource.com/html/attributes/html-align-attribute.php">HTML align attribute</a>.
		 *
		 * @deprecated  The align attribute is not supported in HTML5. Use CSS instead.
		 */
		@Deprecated
		public static interface Align<
			E extends Element<E, ?> & Align<E, V>,
			V extends java.lang.Enum<V> & EnumSupplier
		> {

			/**
			 * See <a href="https://www.w3resource.com/html/attributes/html-align-attribute.php">HTML align attribute</a>.
			 *
			 * @deprecated  The align attribute is not supported in HTML5. Use CSS instead.
			 */
			@Deprecated
			@Funnel
			default E align(java.lang.String align) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				if(element.document.doctype == Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						RESOURCES,
						"notSupportedInHtml5",
						"align"
					);
				}
				return String.attribute(element, "align", MarkupType.NONE, align, true, true);
			}

			/**
			 * See <a href="https://www.w3resource.com/html/attributes/html-align-attribute.php">HTML align attribute</a>.
			 *
			 * @see #align(java.lang.String)
			 *
			 * @deprecated  The align attribute is not supported in HTML5. Use CSS instead.
			 */
			@Deprecated
			@SuppressWarnings("overloads")
			default <Ex extends Throwable> E align(Suppliers.String<Ex> align) throws IOException, Ex {
				return align((align == null) ? null : align.get());
			}

			/**
			 * See <a href="https://www.w3resource.com/html/attributes/html-align-attribute.php">HTML align attribute</a>.
			 *
			 * @see #align(java.lang.String)
			 *
			 * @deprecated  The align attribute is not supported in HTML5. Use CSS instead.
			 */
			@Deprecated
			default E align(V align) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return align((align == null) ? null : align.get(element.document.serialization, element.document.doctype));
			}

			/**
			 * See <a href="https://www.w3resource.com/html/attributes/html-align-attribute.php">HTML align attribute</a>.
			 *
			 * @see #align(java.lang.Enum)
			 *
			 * @deprecated  The align attribute is not supported in HTML5. Use CSS instead.
			 */
			@Deprecated
			@SuppressWarnings("overloads")
			default <Ex extends Throwable> E align(IOSupplierE<? extends V, Ex> align) throws IOException, Ex {
				return align((align== null) ? (V)null : align.get());
			}
		}

		/**
		 * <ul>
		 * <li>See <a href="https://www.w3schools.com/tags/att_autocomplete.asp">HTML autocomplete Attribute</a>.</li>
		 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input#htmlattrdefautocomplete">&lt;input&gt;: The Input (Form Input) element</a>.</li>
		 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Attributes/autocomplete">The HTML autocomplete attribute</a>.</li>
		 * </ul>
		 */
		public static interface Autocomplete<
			E extends Element<E, ?> & Autocomplete<E, V>,
			V extends java.lang.Enum<V> & EnumSupplier
		> {

			/**
			 * <ul>
			 * <li>See <a href="https://www.w3schools.com/tags/att_autocomplete.asp">HTML autocomplete Attribute</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input#htmlattrdefautocomplete">&lt;input&gt;: The Input (Form Input) element</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Attributes/autocomplete">The HTML autocomplete attribute</a>.</li>
			 * </ul>
			 */
			@Funnel
			default E autocomplete(java.lang.String autocomplete) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				if(element.document.doctype != Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						RESOURCES,
						"onlySupportedInHtml5",
						element.document.doctype,
						"autocomplete"
					);
				}
				return String.attribute(element, "autocomplete", MarkupType.NONE, autocomplete, true, true);
			}

			/**
			 * <ul>
			 * <li>See <a href="https://www.w3schools.com/tags/att_autocomplete.asp">HTML autocomplete Attribute</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input#htmlattrdefautocomplete">&lt;input&gt;: The Input (Form Input) element</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Attributes/autocomplete">The HTML autocomplete attribute</a>.</li>
			 * </ul>
			 */
			@SuppressWarnings("overloads")
			default <Ex extends Throwable> E autocomplete(Suppliers.String<Ex> autocomplete) throws IOException, Ex {
				return autocomplete((autocomplete == null) ? null : autocomplete.get());
			}

			/**
			 * <ul>
			 * <li>See <a href="https://www.w3schools.com/tags/att_autocomplete.asp">HTML autocomplete Attribute</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input#htmlattrdefautocomplete">&lt;input&gt;: The Input (Form Input) element</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Attributes/autocomplete">The HTML autocomplete attribute</a>.</li>
			 * </ul>
			 */
			default E autocomplete(V autocomplete) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return autocomplete((autocomplete == null) ? null : autocomplete.get(element.document.serialization, element.document.doctype));
			}

			/**
			 * <ul>
			 * <li>See <a href="https://www.w3schools.com/tags/att_autocomplete.asp">HTML autocomplete Attribute</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input#htmlattrdefautocomplete">&lt;input&gt;: The Input (Form Input) element</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Attributes/autocomplete">The HTML autocomplete attribute</a>.</li>
			 * </ul>
			 */
			@SuppressWarnings("overloads")
			default <Ex extends Throwable> E autocomplete(IOSupplierE<? extends V, Ex> autocomplete) throws IOException, Ex {
				return autocomplete((autocomplete== null) ? (V)null : autocomplete.get());
			}

			/**
			 * <ul>
			 * <li>See <a href="https://www.w3schools.com/tags/att_autocomplete.asp">HTML autocomplete Attribute</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input#htmlattrdefautocomplete">&lt;input&gt;: The Input (Form Input) element</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Attributes/autocomplete">The HTML autocomplete attribute</a>.</li>
			 * </ul>
			 */
			@Funnel
			default E autocomplete(java.lang.String ... autocomplete) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				if(element.document.doctype != Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						RESOURCES,
						"onlySupportedInHtml5",
						element.document.doctype,
						"autocomplete"
					);
				}
				if(autocomplete != null) {
					boolean didOne = false;
					for(java.lang.String value : autocomplete) {
						java.lang.String trimmed = Strings.trimNullIfEmpty(value);
						if(trimmed != null) {
							if(!didOne) {
								element.document.out.write(" autocomplete=\"");
								didOne = true;
							} else {
								element.document.out.write(' ');
							}
							encodeTextInXhtmlAttribute(trimmed, element.document.out);
						}
					}
					if(didOne) element.document.out.write('"');
				}
				return element;
			}

			// TODO: More variants for suppliers of arrays, arrays of suppliers, iterables, ...?

			/**
			 * <ul>
			 * <li>See <a href="https://www.w3schools.com/tags/att_autocomplete.asp">HTML autocomplete Attribute</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input#htmlattrdefautocomplete">&lt;input&gt;: The Input (Form Input) element</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Attributes/autocomplete">The HTML autocomplete attribute</a>.</li>
			 * </ul>
			 */
			@Funnel
			@SuppressWarnings("unchecked") // generic varargs
			default E autocomplete(V ... autocomplete) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				if(element.document.doctype != Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						RESOURCES,
						"onlySupportedInHtml5",
						element.document.doctype,
						"autocomplete"
					);
				}
				if(autocomplete != null) {
					boolean didOne = false;
					for(V value : autocomplete) {
						if(value != null) {
							if(!didOne) {
								element.document.out.write(" autocomplete=\"");
								didOne = true;
							} else {
								element.document.out.write(' ');
							}
							encodeTextInXhtmlAttribute(value.get(element.document.serialization, element.document.doctype), element.document.out);
						}
					}
					if(didOne) element.document.out.write('"');
				}
				return element;
			}

			// TODO: More variants for suppliers of arrays, arrays of suppliers, iterables, ...?
		}

		/**
		 * <ul>
		 * <li>See <a href="https://www.w3schools.com/tags/att_charset.asp">HTML charset Attribute</a>.</li>
		 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/meta#attr-charset">&lt;meta&gt;: The Document-level Metadata element</a>.</li>
		 * <li>See <a href="https://www.w3schools.com/tags/ref_charactersets.asp">HTML Character Sets</a>.</li>
		 * </ul>
		 */
		// TODO: Support java Charset, too
		public static interface Charset<
			E extends Element<E, ?> & Charset<E, V>,
			V extends java.lang.Enum<V> & EnumSupplier
		> {

			/**
			 * <ul>
			 * <li>See <a href="https://www.w3schools.com/tags/att_charset.asp">HTML charset Attribute</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/meta#attr-charset">&lt;meta&gt;: The Document-level Metadata element</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/ref_charactersets.asp">HTML Character Sets</a>.</li>
			 * </ul>
			 */
			@Funnel
			default E charset(java.lang.String charset) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				if(element.document.doctype != Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						RESOURCES,
						"onlySupportedInHtml5",
						element.document.doctype,
						"charset"
					);
				}
				return String.attribute(element, "charset", MarkupType.NONE, charset, true, true);
			}

			/**
			 * <ul>
			 * <li>See <a href="https://www.w3schools.com/tags/att_charset.asp">HTML charset Attribute</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/meta#attr-charset">&lt;meta&gt;: The Document-level Metadata element</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/ref_charactersets.asp">HTML Character Sets</a>.</li>
			 * </ul>
			 */
			@SuppressWarnings("overloads")
			default <Ex extends Throwable> E charset(Suppliers.String<Ex> charset) throws IOException, Ex {
				return charset((charset == null) ? null : charset.get());
			}

			/**
			 * <ul>
			 * <li>See <a href="https://www.w3schools.com/tags/att_charset.asp">HTML charset Attribute</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/meta#attr-charset">&lt;meta&gt;: The Document-level Metadata element</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/ref_charactersets.asp">HTML Character Sets</a>.</li>
			 * </ul>
			 */
			default E charset(V charset) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return charset((charset == null) ? null : charset.get(element.document.serialization, element.document.doctype));
			}

			/**
			 * <ul>
			 * <li>See <a href="https://www.w3schools.com/tags/att_charset.asp">HTML charset Attribute</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/meta#attr-charset">&lt;meta&gt;: The Document-level Metadata element</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/ref_charactersets.asp">HTML Character Sets</a>.</li>
			 * </ul>
			 */
			@SuppressWarnings("overloads")
			default <Ex extends Throwable> E charset(IOSupplierE<? extends V, Ex> charset) throws IOException, Ex {
				return charset((charset== null) ? (V)null : charset.get());
			}

			/**
			 * <ul>
			 * <li>See <a href="https://www.w3schools.com/tags/ref_charactersets.asp">HTML Character Sets</a>.</li>
			 * <li>See <a href="https://www.iana.org/assignments/character-sets/character-sets.xhtml">Character Sets</a>.</li>
			 * </ul>
			 */
			public enum Value implements EnumSupplier {
				// TODO: Add other charsets here?
				US_ASCII("US-ASCII"),
				ISO_8859_1("ISO-8859-1"),
				UTF_8("UTF-8"),
				WINDOWS_1252("windows-1252");

				private final java.lang.String value;

				private Value(java.lang.String value) {
					this.value = value;
				}

				@Override
				public java.lang.String toString() {
					return value;
				}

				@Override
				public java.lang.String get(Serialization serialization, Doctype doctype) {
					return value;
				}

				public java.lang.String getValue() {
					return value;
				}
			}
		}

		/**
		 * <ul>
		 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input#htmlattrdefcapture">&lt;input&gt;: The Input (Form Input) element</a>.</li>
		 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input/file#capture">&lt;input type="file"&gt;</a>.</li>
		 * <li>See <a href="https://www.w3.org/TR/html-media-capture/#the-capture-attribute">5. The capture attribute</a>.</li>
		 * <li>See <a href="https://www.w3.org/TR/mediacapture-streams/#dom-videofacingmodeenum">Media Capture and Streams: VideoFacingModeEnum</a>.</li>
		 * </ul>
		 */
		public static interface Capture<
			E extends Element<E, ?> & Capture<E, V>,
			V extends java.lang.Enum<V> & EnumSupplier
		> {

			/**
			 * <ul>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input#htmlattrdefcapture">&lt;input&gt;: The Input (Form Input) element</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input/file#capture">&lt;input type="file"&gt;</a>.</li>
			 * <li>See <a href="https://www.w3.org/TR/html-media-capture/#the-capture-attribute">5. The capture attribute</a>.</li>
			 * <li>See <a href="https://www.w3.org/TR/mediacapture-streams/#dom-videofacingmodeenum">Media Capture and Streams: VideoFacingModeEnum</a>.</li>
			 * </ul>
			 */
			@Funnel
			default E capture(java.lang.String capture) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return String.attribute(element, "capture", MarkupType.NONE, capture, true, true);
			}

			/**
			 * <ul>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input#htmlattrdefcapture">&lt;input&gt;: The Input (Form Input) element</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input/file#capture">&lt;input type="file"&gt;</a>.</li>
			 * <li>See <a href="https://www.w3.org/TR/html-media-capture/#the-capture-attribute">5. The capture attribute</a>.</li>
			 * <li>See <a href="https://www.w3.org/TR/mediacapture-streams/#dom-videofacingmodeenum">Media Capture and Streams: VideoFacingModeEnum</a>.</li>
			 * </ul>
			 *
			 * @see #capture(java.lang.String)
			 */
			@SuppressWarnings("overloads")
			default <Ex extends Throwable> E capture(Suppliers.String<Ex> capture) throws IOException, Ex {
				return capture((capture == null) ? null : capture.get());
			}

			/**
			 * <ul>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input#htmlattrdefcapture">&lt;input&gt;: The Input (Form Input) element</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input/file#capture">&lt;input type="file"&gt;</a>.</li>
			 * <li>See <a href="https://www.w3.org/TR/html-media-capture/#the-capture-attribute">5. The capture attribute</a>.</li>
			 * <li>See <a href="https://www.w3.org/TR/mediacapture-streams/#dom-videofacingmodeenum">Media Capture and Streams: VideoFacingModeEnum</a>.</li>
			 * </ul>
			 *
			 * @see #capture(java.lang.String)
			 */
			default E capture(V capture) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return capture((capture == null) ? null : capture.get(element.document.serialization, element.document.doctype));
			}

			/**
			 * <ul>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input#htmlattrdefcapture">&lt;input&gt;: The Input (Form Input) element</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input/file#capture">&lt;input type="file"&gt;</a>.</li>
			 * <li>See <a href="https://www.w3.org/TR/html-media-capture/#the-capture-attribute">5. The capture attribute</a>.</li>
			 * <li>See <a href="https://www.w3.org/TR/mediacapture-streams/#dom-videofacingmodeenum">Media Capture and Streams: VideoFacingModeEnum</a>.</li>
			 * </ul>
			 *
			 * @see #capture(java.lang.Enum)
			 */
			@SuppressWarnings("overloads")
			default <Ex extends Throwable> E capture(IOSupplierE<? extends V, Ex> capture) throws IOException, Ex {
				return capture((capture== null) ? (V)null : capture.get());
			}
		}

		/**
		 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/CORS_settings_attributes">The crossorigin attribute: Requesting CORS access to content</a>.
		 */
		public static interface Crossorigin<
			E extends Element<E, ?> & Crossorigin<E, V>,
			V extends java.lang.Enum<V> & EnumSupplier
		> {

			/**
			 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/CORS_settings_attributes">The crossorigin attribute: Requesting CORS access to content</a>.
			 */
			@Funnel
			default E crossorigin(java.lang.String crossorigin) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return String.attribute(element, "crossorigin", MarkupType.NONE, crossorigin, true, true);
			}

			/**
			 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/CORS_settings_attributes">The crossorigin attribute: Requesting CORS access to content</a>.
			 *
			 * @see #crossorigin(java.lang.String)
			 */
			@SuppressWarnings("overloads")
			default <Ex extends Throwable> E crossorigin(Suppliers.String<Ex> crossorigin) throws IOException, Ex {
				return crossorigin((crossorigin == null) ? null : crossorigin.get());
			}

			/**
			 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/CORS_settings_attributes">The crossorigin attribute: Requesting CORS access to content</a>.
			 *
			 * @see #crossorigin(java.lang.String)
			 */
			default E crossorigin(V crossorigin) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return crossorigin((crossorigin == null) ? null : crossorigin.get(element.document.serialization, element.document.doctype));
			}

			/**
			 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/CORS_settings_attributes">The crossorigin attribute: Requesting CORS access to content</a>.
			 *
			 * @see #crossorigin(java.lang.Enum)
			 */
			@SuppressWarnings("overloads")
			default <Ex extends Throwable> E crossorigin(IOSupplierE<? extends V, Ex> crossorigin) throws IOException, Ex {
				return crossorigin((crossorigin== null) ? (V)null : crossorigin.get());
			}
		}

		/**
		 * <ul>
		 * <li>See <a href="https://www.w3schools.com/tags/att_global_dir.asp">HTML Global dir Attribute</a>.</li>
		 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes/dir">dir - HTML: Hypertext Markup Language | MDN</a>.</li>
		 * </ul>
		 */
		public static interface Dir<
			E extends Element<E, ?>, // TODO: How to use from Global?  Remove others?  & Dir<E, V>,
			V extends java.lang.Enum<V> & EnumSupplier
		> {

			/**
			 * <p>
			 * Utility class for working with {@link Dir}.
			 * </p>
			 * <ul>
			 * <li>See <a href="https://www.w3schools.com/tags/att_global_dir.asp">HTML Global dir Attribute</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes/dir">dir - HTML: Hypertext Markup Language | MDN</a>.</li>
			 * </ul>
			 */
			public static final class dir {

				/**
				 * Normalizes a dir attribute.
				 *
				 * @see  Strings#trimNullIfEmpty(java.lang.String)
				 * @see  java.lang.String#toLowerCase(java.util.Locale)
				 * @see  Locale#ROOT
				 */
				// TODO: Normalize other attributes the same way
				public static java.lang.String normalize(java.lang.String dir) {
					dir = Strings.trimNullIfEmpty(dir);
					if(dir != null) dir = dir.toLowerCase(Locale.ROOT);
					return dir;
				}

				/**
				 * Validates a dir attribute.
				 * The value should already be {@linkplain #normalize(java.lang.String) normalized}.
				 *
				 * @see #normalize(java.lang.String)
				 */
				public static ValidationResult validate(java.lang.String dir) {
					if(
						dir != null
						&& Attributes.Enum.Dir.Value.getByValue(dir) == null
					) {
						return new InvalidResult(
							RESOURCES,
							"Enum.Dir.invalid",
							dir
						);
					} else {
						return ValidResult.getInstance();
					}
				}

				private dir() {}
			}

			/**
			 * <ul>
			 * <li>See <a href="https://www.w3schools.com/tags/att_global_dir.asp">HTML Global dir Attribute</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes/dir">dir - HTML: Hypertext Markup Language | MDN</a>.</li>
			 * </ul>
			 */
			@Funnel
			default E dir(java.lang.String dir) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return String.attribute(
					element,
					"dir",
					MarkupType.NONE,
					validate(
						Dir.dir.normalize(dir),
						Dir.dir::validate
					),
					false,
					false
				);
			}

			/**
			 * <ul>
			 * <li>See <a href="https://www.w3schools.com/tags/att_global_dir.asp">HTML Global dir Attribute</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes/dir">dir - HTML: Hypertext Markup Language | MDN</a>.</li>
			 * </ul>
			 */
			@SuppressWarnings("overloads")
			default <Ex extends Throwable> E dir(Suppliers.String<Ex> dir) throws IOException, Ex {
				return dir((dir == null) ? null : dir.get());
			}

			/**
			 * <ul>
			 * <li>See <a href="https://www.w3schools.com/tags/att_global_dir.asp">HTML Global dir Attribute</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes/dir">dir - HTML: Hypertext Markup Language | MDN</a>.</li>
			 * </ul>
			 */
			default E dir(V dir) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return dir((dir == null) ? null : dir.get(element.document.serialization, element.document.doctype));
			}

			/**
			 * <ul>
			 * <li>See <a href="https://www.w3schools.com/tags/att_global_dir.asp">HTML Global dir Attribute</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes/dir">dir - HTML: Hypertext Markup Language | MDN</a>.</li>
			 * </ul>
			 */
			@SuppressWarnings("overloads")
			default <Ex extends Throwable> E dir(IOSupplierE<? extends V, Ex> dir) throws IOException, Ex {
				return dir((dir== null) ? (V)null : dir.get());
			}

			/**
			 * <ul>
			 * <li>See <a href="https://www.w3schools.com/tags/att_global_dir.asp">HTML Global dir Attribute</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes/dir">dir - HTML: Hypertext Markup Language | MDN</a>.</li>
			 * </ul>
			 */
			public enum Value implements EnumSupplier {
				LTR("ltr"),
				RTL("rtl"),
				AUTO("auto");

				private final java.lang.String value;

				private Value(java.lang.String value) {
					this.value = value;
				}

				@Override
				public java.lang.String toString() {
					return value;
				}

				@Override
				public java.lang.String get(Serialization serialization, Doctype doctype) {
					return value;
				}

				public java.lang.String getValue() {
					return value;
				}

				private static final Value[] values = values();

				/**
				 * Gets the enum by value, case-sensitive.
				 *
				 * @return  The enum or {@code null} when not found.
				 */
				public static Value getByValue(java.lang.String dir) {
					if(dir != null) {
						for(Value value : values) {
							if(value.value.equals(dir)) return value;
						}
					}
					return null;
				}
			}
		}

		/**
		 * <ul>
		 * <li>See <a href="https://www.w3schools.com/tags/att_http-equiv.asp">HTML http-equiv Attribute</a>.</li>
		 * <li>See <a href="https://www.w3schools.com/tags/att_meta_http_equiv.asp">HTML meta http-equiv Attribute</a>.</li>
		 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/meta#attr-http-equiv">&lt;meta&gt;: The Document-level Metadata element</a>.</li>
		 * </ul>
		 */
		public static interface HttpEquiv<
			E extends Element<E, ?> & HttpEquiv<E, V>,
			V extends java.lang.Enum<V> & EnumSupplier
		> {

			/**
			 * <ul>
			 * <li>See <a href="https://www.w3schools.com/tags/att_http-equiv.asp">HTML http-equiv Attribute</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/att_meta_http_equiv.asp">HTML meta http-equiv Attribute</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/meta#attr-http-equiv">&lt;meta&gt;: The Document-level Metadata element</a>.</li>
			 * </ul>
			 */
			@Funnel
			default E httpEquiv(java.lang.String httpEquiv) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return String.attribute(element, "http-equiv", MarkupType.NONE, httpEquiv, true, false);
			}

			/**
			 * <ul>
			 * <li>See <a href="https://www.w3schools.com/tags/att_http-equiv.asp">HTML http-equiv Attribute</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/att_meta_http_equiv.asp">HTML meta http-equiv Attribute</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/meta#attr-http-equiv">&lt;meta&gt;: The Document-level Metadata element</a>.</li>
			 * </ul>
			 */
			@SuppressWarnings("overloads")
			default <Ex extends Throwable> E httpEquiv(Suppliers.String<Ex> httpEquiv) throws IOException, Ex {
				return httpEquiv((httpEquiv == null) ? null : httpEquiv.get());
			}

			/**
			 * <ul>
			 * <li>See <a href="https://www.w3schools.com/tags/att_http-equiv.asp">HTML http-equiv Attribute</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/att_meta_http_equiv.asp">HTML meta http-equiv Attribute</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/meta#attr-http-equiv">&lt;meta&gt;: The Document-level Metadata element</a>.</li>
			 * </ul>
			 */
			default E httpEquiv(V httpEquiv) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return httpEquiv((httpEquiv == null) ? null : httpEquiv.get(element.document.serialization, element.document.doctype));
			}

			/**
			 * <ul>
			 * <li>See <a href="https://www.w3schools.com/tags/att_http-equiv.asp">HTML http-equiv Attribute</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/att_meta_http_equiv.asp">HTML meta http-equiv Attribute</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/meta#attr-http-equiv">&lt;meta&gt;: The Document-level Metadata element</a>.</li>
			 * </ul>
			 */
			@SuppressWarnings("overloads")
			default <Ex extends Throwable> E httpEquiv(IOSupplierE<? extends V, Ex> httpEquiv) throws IOException, Ex {
				return httpEquiv((httpEquiv== null) ? (V)null : httpEquiv.get());
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_name.asp">HTML name Attribute</a>.
		 */
		public static interface Name<
			E extends Element<E, ?> & Name<E, V>,
			V extends java.lang.Enum<V> & EnumSupplier
		> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_name.asp">HTML name Attribute</a>.
			 */
			@Funnel
			default E name(java.lang.String name) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				// TODO: Is nullIfEmpty correct?  Is an empty name ever valid?
				return String.attribute(element, "name", MarkupType.NONE, name, true, true);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_name.asp">HTML name Attribute</a>.
			 *
			 * @see #name(java.lang.String)
			 */
			@SuppressWarnings("overloads")
			default <Ex extends Throwable> E name(Suppliers.String<Ex> name) throws IOException, Ex {
				return name((name == null) ? null : name.get());
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_name.asp">HTML name Attribute</a>.
			 *
			 * @see #name(java.lang.String)
			 */
			default E name(V name) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return name((name == null) ? null : name.get(element.document.serialization, element.document.doctype));
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_name.asp">HTML name Attribute</a>.
			 *
			 * @see #name(java.lang.Enum)
			 */
			@SuppressWarnings("overloads")
			default <Ex extends Throwable> E name(IOSupplierE<? extends V, Ex> name) throws IOException, Ex {
				return name((name== null) ? (V)null : name.get());
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_rel.asp">HTML rel Attribute</a>.
		 */
		public static interface Rel<
			E extends Element<E, ?> & Rel<E, V>,
			V extends java.lang.Enum<V> & EnumSupplier
		> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_rel.asp">HTML rel Attribute</a>.
			 */
			@Funnel
			default E rel(java.lang.String rel) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return String.attribute(element, "rel", MarkupType.NONE, rel, true, true);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_rel.asp">HTML rel Attribute</a>.
			 *
			 * @see #rel(java.lang.String)
			 */
			@SuppressWarnings("overloads")
			default <Ex extends Throwable> E rel(Suppliers.String<Ex> rel) throws IOException, Ex {
				return rel((rel == null) ? null : rel.get());
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_rel.asp">HTML rel Attribute</a>.
			 *
			 * @see #rel(java.lang.String)
			 */
			default E rel(V rel) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return rel((rel == null) ? null : rel.get(element.document.serialization, element.document.doctype));
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_rel.asp">HTML rel Attribute</a>.
			 *
			 * @see #rel(java.lang.Enum)
			 */
			@SuppressWarnings("overloads")
			default <Ex extends Throwable> E rel(IOSupplierE<? extends V, Ex> rel) throws IOException, Ex {
				return rel((rel== null) ? (V)null : rel.get());
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_shape.asp">HTML shape Attribute</a>.
		 */
		public static interface Shape<
			E extends Element<E, ?> & Shape<E, V>,
			V extends java.lang.Enum<V> & EnumSupplier
		> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_shape.asp">HTML shape Attribute</a>.
			 */
			@Funnel
			default E shape(java.lang.String shape) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return String.attribute(element, "shape", MarkupType.NONE, shape, true, true);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_shape.asp">HTML shape Attribute</a>.
			 *
			 * @see #shape(java.lang.String)
			 */
			@SuppressWarnings("overloads")
			default <Ex extends Throwable> E shape(Suppliers.String<Ex> shape) throws IOException, Ex {
				return shape((shape == null) ? null : shape.get());
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_shape.asp">HTML shape Attribute</a>.
			 *
			 * @see #shape(java.lang.String)
			 */
			default E shape(V shape) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return shape((shape == null) ? null : shape.get(element.document.serialization, element.document.doctype));
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_shape.asp">HTML shape Attribute</a>.
			 *
			 * @see #shape(java.lang.Enum)
			 */
			@SuppressWarnings("overloads")
			default <Ex extends Throwable> E shape(IOSupplierE<? extends V, Ex> shape) throws IOException, Ex {
				return shape((shape== null) ? (V)null : shape.get());
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_type.asp">HTML type Attribute</a>.
		 */
		public static interface Type<
			E extends Element<E, ?> & Type<E, V>,
			V extends java.lang.Enum<V> & EnumSupplier
		> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_type.asp">HTML type Attribute</a>.
			 */
			@Funnel
			default E type(java.lang.String type) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return String.attribute(element, "type", MarkupType.NONE, type, true, true);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_type.asp">HTML type Attribute</a>.
			 *
			 * @see #type(java.lang.String)
			 */
			@SuppressWarnings("overloads")
			default <Ex extends Throwable> E type(Suppliers.String<Ex> type) throws IOException, Ex {
				return type((type == null) ? null : type.get());
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_type.asp">HTML type Attribute</a>.
			 *
			 * @see #type(java.lang.String)
			 */
			default E type(V type) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return type((type == null) ? null : type.get(element.document.serialization, element.document.doctype));
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_type.asp">HTML type Attribute</a>.
			 *
			 * @see #type(java.lang.Enum)
			 */
			@SuppressWarnings("overloads")
			default <Ex extends Throwable> E type(IOSupplierE<? extends V, Ex> type) throws IOException, Ex {
				return type((type== null) ? (V)null : type.get());
			}
		}

		/**
		 * See <a href="https://www.w3resource.com/html/attributes/html-valign-attribute.php">HTML valign attribute</a>.
		 *
		 * @deprecated  The valign attribute is not supported in HTML5. Use CSS instead.
		 */
		@Deprecated
		public static interface Valign<
			E extends Element<E, ?> & Valign<E, V>,
			V extends java.lang.Enum<V> & EnumSupplier
		> {

			/**
			 * See <a href="https://www.w3resource.com/html/attributes/html-valign-attribute.php">HTML valign attribute</a>.
			 *
			 * @deprecated  The valign attribute is not supported in HTML5. Use CSS instead.
			 */
			@Deprecated
			@Funnel
			default E valign(java.lang.String valign) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				if(element.document.doctype == Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						RESOURCES,
						"notSupportedInHtml5",
						"valign"
					);
				}
				return String.attribute(element, "valign", MarkupType.NONE, valign, true, true);
			}

			/**
			 * See <a href="https://www.w3resource.com/html/attributes/html-valign-attribute.php">HTML valign attribute</a>.
			 *
			 * @see #valign(java.lang.String)
			 *
			 * @deprecated  The valign attribute is not supported in HTML5. Use CSS instead.
			 */
			@Deprecated
			@SuppressWarnings("overloads")
			default <Ex extends Throwable> E valign(Suppliers.String<Ex> valign) throws IOException, Ex {
				return valign((valign == null) ? null : valign.get());
			}

			/**
			 * See <a href="https://www.w3resource.com/html/attributes/html-valign-attribute.php">HTML valign attribute</a>.
			 *
			 * @see #valign(java.lang.String)
			 *
			 * @deprecated  The valign attribute is not supported in HTML5. Use CSS instead.
			 */
			@Deprecated
			default E valign(V valign) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return valign((valign == null) ? null : valign.get(element.document.serialization, element.document.doctype));
			}

			/**
			 * See <a href="https://www.w3resource.com/html/attributes/html-valign-attribute.php">HTML valign attribute</a>.
			 *
			 * @see #valign(java.lang.Enum)
			 *
			 * @deprecated  The valign attribute is not supported in HTML5. Use CSS instead.
			 */
			@Deprecated
			@SuppressWarnings("overloads")
			default <Ex extends Throwable> E valign(IOSupplierE<? extends V, Ex> valign) throws IOException, Ex {
				return valign((valign== null) ? (V)null : valign.get());
			}
		}
	}

	/**
	 * See <a href="https://html.spec.whatwg.org/multipage/common-microsyntaxes.html#signed-integers">2.4.4.1 Signed integers</a>.
	 */
	public static class Integer {

		/** Make no instances. */
		private Integer() {}

		static <E extends Element<E, ?>> E attribute(E element, java.lang.String name, int value) throws IOException {
			element.document.out.write(' ');
			element.document.out.write(name);
			element.document.out.write("=\"");
			element.document.out.write(java.lang.Integer.toString(value));
			element.document.out.write('"');
			return element;
		}

		static <E extends Element<E, ?>> E attribute(E element, java.lang.String name, java.lang.Integer value) throws IOException {
			if(value != null) {
				return attribute(element, name, value.intValue());
			} else {
				return element;
			}
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#attr-tdth-colspan">4.9.11 Attributes common to td and th elements / colspan</a>.
		 */
		public static interface Colspan<E extends Element<E, ?> & Colspan<E>> {

			/**
			 * See <a href="https://html.spec.whatwg.org/#attr-tdth-colspan">4.9.11 Attributes common to td and th elements / colspan</a>.
			 */
			@Funnel
			default E colspan(int colspan) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "colspan", colspan);
			}

			/**
			 * See <a href="https://html.spec.whatwg.org/#attr-tdth-colspan">4.9.11 Attributes common to td and th elements / colspan</a>.
			 */
			@Funnel
			default E colspan(java.lang.Integer colspan) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "colspan", colspan);
			}

			/**
			 * See <a href="https://html.spec.whatwg.org/#attr-tdth-colspan">4.9.11 Attributes common to td and th elements / colspan</a>.
			 *
			 * @see #colspan(java.lang.Integer)
			 */
			default <Ex extends Throwable> E colspan(IOSupplierE<? extends java.lang.Integer, Ex> colspan) throws IOException, Ex {
				return colspan((colspan == null) ? null : colspan.get());
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_height.asp">HTML height Attribute</a>.
		 */
		public static interface Height<E extends Element<E, ?> & Height<E>> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_height.asp">HTML height Attribute</a>.
			 */
			@Funnel
			default E height(int pixels) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "height", pixels);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_height.asp">HTML height Attribute</a>.
			 */
			@Funnel
			default E height(java.lang.Integer pixels) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "height", pixels);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_height.asp">HTML height Attribute</a>.
			 *
			 * @see #height(java.lang.Integer)
			 */
			default <Ex extends Throwable> E height(IOSupplierE<? extends java.lang.Integer, Ex> pixels) throws IOException, Ex {
				return height((pixels == null) ? null : pixels.get());
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_height.asp">HTML height Attribute</a>.
		 */
		public static interface HeightHtml5Only<E extends Element<E, ?> & HeightHtml5Only<E>> extends Height<E> {

			/**
			 * {@inheritDoc}
			 * <p>
			 * The height attribute is new in HTML5.
			 * </p>
			 */
			@Override
			@Funnel
			default E height(int pixels) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				if(element.document.doctype != Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						RESOURCES,
						"onlySupportedInHtml5",
						element.document.doctype,
						"height"
					);
				}
				return Height.super.height(pixels);
			}

			/**
			 * {@inheritDoc}
			 * <p>
			 * The height attribute is new in HTML5.
			 * </p>
			 */
			@Override
			@Funnel
			default E height(java.lang.Integer pixels) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				if(element.document.doctype != Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						RESOURCES,
						"onlySupportedInHtml5",
						element.document.doctype,
						"height"
					);
				}
				return Height.super.height(pixels);
			}

			/**
			 * {@inheritDoc}
			 * <p>
			 * The height attribute is new in HTML5.
			 * </p>
			 *
			 * @see #height(java.lang.Integer)
			 */
			@Override
			default <Ex extends Throwable> E height(IOSupplierE<? extends java.lang.Integer, Ex> pixels) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				if(element.document.doctype != Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						RESOURCES,
						"onlySupportedInHtml5",
						element.document.doctype,
						"height"
					);
				}
				return Height.super.height(pixels);
			}
		}

		/**
		 * <ul>
		 * <li>See <a href="https://www.w3schools.com/tags/att_maxlength.asp">HTML maxlength Attribute</a>.</li>
		 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input#htmlattrdefmaxlength">&lt;input&gt;: The Input (Form Input) element</a>.</li>
		 * </ul>
		 */
		public static interface Maxlength<E extends Element<E, ?> & Maxlength<E>> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_maxlength.asp">HTML maxlength Attribute</a>.
			 */
			@Funnel
			default E maxlength(int maxlength) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "maxlength", maxlength);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_maxlength.asp">HTML maxlength Attribute</a>.
			 */
			@Funnel
			default E maxlength(java.lang.Integer maxlength) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "maxlength", maxlength);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_maxlength.asp">HTML maxlength Attribute</a>.
			 *
			 * @see #maxlength(java.lang.Integer)
			 */
			default <Ex extends Throwable> E maxlength(IOSupplierE<? extends java.lang.Integer, Ex> maxlength) throws IOException, Ex {
				return maxlength((maxlength == null) ? null : maxlength.get());
			}
		}

		/**
		 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input#htmlattrdefminlength">&lt;input&gt;: The Input (Form Input) element</a>.
		 */
		public static interface Minlength<E extends Element<E, ?> & Minlength<E>> {

			/**
			 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input#htmlattrdefminlength">&lt;input&gt;: The Input (Form Input) element</a>.
			 */
			@Funnel
			default E minlength(int minlength) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "minlength", minlength);
			}

			/**
			 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input#htmlattrdefminlength">&lt;input&gt;: The Input (Form Input) element</a>.
			 */
			@Funnel
			default E minlength(java.lang.Integer minlength) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "minlength", minlength);
			}

			/**
			 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input#htmlattrdefminlength">&lt;input&gt;: The Input (Form Input) element</a>.
			 *
			 * @see #minlength(java.lang.Integer)
			 */
			default <Ex extends Throwable> E minlength(IOSupplierE<? extends java.lang.Integer, Ex> minlength) throws IOException, Ex {
				return minlength((minlength == null) ? null : minlength.get());
			}
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#attr-tdth-rowspan">4.9.11 Attributes common to td and th elements / rowspan</a>.
		 */
		public static interface Rowspan<E extends Element<E, ?> & Rowspan<E>> {

			/**
			 * See <a href="https://html.spec.whatwg.org/#attr-tdth-rowspan">4.9.11 Attributes common to td and th elements / rowspan</a>.
			 */
			@Funnel
			default E rowspan(int rowspan) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "rowspan", rowspan);
			}

			/**
			 * See <a href="https://html.spec.whatwg.org/#attr-tdth-rowspan">4.9.11 Attributes common to td and th elements / rowspan</a>.
			 */
			@Funnel
			default E rowspan(java.lang.Integer rowspan) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "rowspan", rowspan);
			}

			/**
			 * See <a href="https://html.spec.whatwg.org/#attr-tdth-rowspan">4.9.11 Attributes common to td and th elements / rowspan</a>.
			 *
			 * @see #rowspan(java.lang.Integer)
			 */
			default <Ex extends Throwable> E rowspan(IOSupplierE<? extends java.lang.Integer, Ex> rowspan) throws IOException, Ex {
				return rowspan((rowspan == null) ? null : rowspan.get());
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_size.asp">HTML size Attribute</a>.
		 */
		public static interface Size<E extends Element<E, ?> & Size<E>> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_size.asp">HTML size Attribute</a>.
			 */
			@Funnel
			default E size(int size) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "size", size);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_size.asp">HTML size Attribute</a>.
			 */
			@Funnel
			default E size(java.lang.Integer size) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "size", size);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_size.asp">HTML size Attribute</a>.
			 *
			 * @see #size(java.lang.Integer)
			 */
			default <Ex extends Throwable> E size(IOSupplierE<? extends java.lang.Integer, Ex> size) throws IOException, Ex {
				return size((size == null) ? null : size.get());
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_size.asp">HTML size Attribute</a>.
		 *
		 * @deprecated  Not supported in HTML5.
		 */
		@Deprecated
		public static interface SizeHtml4Only<E extends Element<E, ?> & SizeHtml4Only<E>> extends Size<E> {

			/**
			 * @deprecated  Not supported in HTML5.
			 */
			@Deprecated
			@Override
			@Funnel
			default E size(int size) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				if(element.document.doctype == Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						RESOURCES,
						"notSupportedInHtml5",
						"size"
					);
				}
				return Size.super.size(size);
			}

			/**
			 * @deprecated  Not supported in HTML5.
			 */
			@Deprecated
			@Override
			@Funnel
			default E size(java.lang.Integer size) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				if(element.document.doctype == Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						RESOURCES,
						"notSupportedInHtml5",
						"size"
					);
				}
				return Size.super.size(size);
			}

			/**
			 * @see #size(java.lang.Integer)
			 *
			 * @deprecated  Not supported in HTML5.
			 */
			@Deprecated
			@Override
			default <Ex extends Throwable> E size(IOSupplierE<? extends java.lang.Integer, Ex> size) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				if(element.document.doctype == Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						RESOURCES,
						"notSupportedInHtml5",
						"size"
					);
				}
				return Size.super.size(size);
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_span.asp">HTML span Attribute</a>.
		 */
		public static interface Span<E extends Element<E, ?> & Span<E>> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_span.asp">HTML span Attribute</a>.
			 */
			@Funnel
			default E span(int span) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "span", span);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_span.asp">HTML span Attribute</a>.
			 */
			@Funnel
			default E span(java.lang.Integer span) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "span", span);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_span.asp">HTML span Attribute</a>.
			 *
			 * @see #span(java.lang.Integer)
			 */
			default <Ex extends Throwable> E span(IOSupplierE<? extends java.lang.Integer, Ex> span) throws IOException, Ex {
				return span((span == null) ? null : span.get());
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_global_tabindex.asp">HTML Global tabindex Attribute</a>.
		 * <blockquote>
		 * In HTML5, the tabindex attribute can be used on <b>any</b> HTML element (it will validate on any HTML element. However, it is not necessarily useful).
		 * </blockquote>
		 */
		public static interface Tabindex<E extends Element<E, ?> & Tabindex<E>> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_tabindex.asp">HTML Global tabindex Attribute</a>.
			 * <blockquote>
			 * In HTML5, the tabindex attribute can be used on <b>any</b> HTML element (it will validate on any HTML element. However, it is not necessarily useful).
			 * </blockquote>
			 */
			@Funnel
			default E tabindex(int tabindex) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				if(element.document.doctype != Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						RESOURCES,
						"invalidGlobalAttributeForDoctype",
						element.document.doctype,
						"tabindex"
					);
				}
				return attribute(element, "tabindex", tabindex);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_tabindex.asp">HTML Global tabindex Attribute</a>.
			 * <blockquote>
			 * In HTML5, the tabindex attribute can be used on <b>any</b> HTML element (it will validate on any HTML element. However, it is not necessarily useful).
			 * </blockquote>
			 */
			@Funnel
			default E tabindex(java.lang.Integer tabindex) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				if(element.document.doctype != Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						RESOURCES,
						"invalidGlobalAttributeForDoctype",
						element.document.doctype,
						"tabindex"
					);
				}
				return attribute(element, "tabindex", tabindex);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_tabindex.asp">HTML Global tabindex Attribute</a>.
			 * <blockquote>
			 * In HTML5, the tabindex attribute can be used on <b>any</b> HTML element (it will validate on any HTML element. However, it is not necessarily useful).
			 * </blockquote>
			 *
			 * @see #tabindex(java.lang.Integer)
			 */
			default <Ex extends Throwable> E tabindex(IOSupplierE<? extends java.lang.Integer, Ex> tabindex) throws IOException, Ex {
				return tabindex((tabindex == null) ? null : tabindex.get());
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_global_tabindex.asp">HTML Global tabindex Attribute</a>.
		 * <blockquote>
		 * In HTML 4.01, the tabindex attribute can be used with: &lt;a&gt;, &lt;area&gt;, &lt;button&gt;, &lt;input&gt;, &lt;object&gt;, &lt;select&gt;, and &lt;textarea&gt;.
		 * </blockquote>
		 */
		public static interface TabindexHtml4<E extends Element<E, ?> & TabindexHtml4<E>> extends Tabindex<E> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_tabindex.asp">HTML Global tabindex Attribute</a>.
			 * <blockquote>
			 * In HTML 4.01, the tabindex attribute can be used with: &lt;a&gt;, &lt;area&gt;, &lt;button&gt;, &lt;input&gt;, &lt;object&gt;, &lt;select&gt;, and &lt;textarea&gt;.
			 * </blockquote>
			 */
			@Override
			@Funnel
			default E tabindex(int tabindex) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "tabindex", tabindex);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_tabindex.asp">HTML Global tabindex Attribute</a>.
			 * <blockquote>
			 * In HTML 4.01, the tabindex attribute can be used with: &lt;a&gt;, &lt;area&gt;, &lt;button&gt;, &lt;input&gt;, &lt;object&gt;, &lt;select&gt;, and &lt;textarea&gt;.
			 * </blockquote>
			 */
			@Override
			@Funnel
			default E tabindex(java.lang.Integer tabindex) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "tabindex", tabindex);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_tabindex.asp">HTML Global tabindex Attribute</a>.
			 * <blockquote>
			 * In HTML 4.01, the tabindex attribute can be used with: &lt;a&gt;, &lt;area&gt;, &lt;button&gt;, &lt;input&gt;, &lt;object&gt;, &lt;select&gt;, and &lt;textarea&gt;.
			 * </blockquote>
			 *
			 * @see #tabindex(java.lang.Integer)
			 */
			@Override
			default <Ex extends Throwable> E tabindex(IOSupplierE<? extends java.lang.Integer, Ex> tabindex) throws IOException, Ex {
				return tabindex((tabindex == null) ? null : tabindex.get());
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_width.asp">HTML width Attribute</a>.
		 */
		public static interface Width<E extends Element<E, ?> & Width<E>> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_width.asp">HTML width Attribute</a>.
			 */
			@Funnel
			default E width(int pixels) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "width", pixels);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_width.asp">HTML width Attribute</a>.
			 */
			@Funnel
			default E width(java.lang.Integer pixels) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "width", pixels);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_width.asp">HTML width Attribute</a>.
			 *
			 * @see #width(java.lang.Integer)
			 */
			default <Ex extends Throwable> E width(IOSupplierE<? extends java.lang.Integer, Ex> pixels) throws IOException, Ex {
				return width((pixels == null) ? null : pixels.get());
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_width.asp">HTML width Attribute</a>.
		 * <p>
		 * The width attribute is new in HTML5.
		 * </p>
		 */
		public static interface WidthHtml5Only<E extends Element<E, ?> & WidthHtml5Only<E>> extends Width<E> {

			/**
			 * {@inheritDoc}
			 * <p>
			 * The width attribute is new in HTML5.
			 * </p>
			 */
			@Override
			@Funnel
			default E width(int pixels) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				if(element.document.doctype != Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						RESOURCES,
						"onlySupportedInHtml5",
						element.document.doctype,
						"width"
					);
				}
				return Width.super.width(pixels);
			}

			/**
			 * {@inheritDoc}
			 * <p>
			 * The width attribute is new in HTML5.
			 * </p>
			 */
			@Override
			@Funnel
			default E width(java.lang.Integer pixels) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				if(element.document.doctype != Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						RESOURCES,
						"onlySupportedInHtml5",
						element.document.doctype,
						"width"
					);
				}
				return Width.super.width(pixels);
			}

			/**
			 * {@inheritDoc}
			 * <p>
			 * The width attribute is new in HTML5.
			 * </p>
			 *
			 * @see #width(java.lang.Integer)
			 */
			@Override
			default <Ex extends Throwable> E width(IOSupplierE<? extends java.lang.Integer, Ex> pixels) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				if(element.document.doctype != Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						RESOURCES,
						"onlySupportedInHtml5",
						element.document.doctype,
						"width"
					);
				}
				return Width.super.width(pixels);
			}
		}
	}

	/**
	 * Non-streamable text attributes (expected to be short, relatively fixed values)
	 */
	public static class String {

		/** Make no instances. */
		private String() {}

		/**
		 * @param value  If is {@link #NO_VALUE} (by identity), will write empty attribute.
		 */
		// TODO: Remove trim and nullIfEmpty once all attributes have normalize methods
		@SuppressWarnings("StringEquality")
		static <E extends Element<E, ?>> E attribute(E element, java.lang.String name, MarkupType markupType, java.lang.String value, boolean trim, boolean nullIfEmpty) throws IOException {
			if(value != null) {
				if(value == NO_VALUE) { // Identity comparison for marker value
					// Empty attribute
					element.document.out.write(' ');
					element.document.out.write(name);
				} else {
					if(trim) value = value.trim(); // TODO: These trims should all be from Strings?
					if(!nullIfEmpty || !value.isEmpty()) {
						element.document.out.write(' ');
						element.document.out.write(name);
						element.document.out.write("=\"");
						if(markupType == null || markupType == MarkupType.NONE) {
							// Short-cut additional type checks done by Coercion, since we already have a String
							encodeTextInXhtmlAttribute(value, element.document.out);
						} else {
							MarkupCoercion.write(value, markupType, true, textInXhtmlAttributeEncoder, false, element.document.out);
						}
						element.document.out.write('"');
					}
				}
			}
			return element;
		}

		// TODO: Move some non-streamable attributes from Text to here, such as id and name

		/**
		 * See <a href="https://www.w3schools.com/tags/att_hreflang.asp">HTML hreflang Attribute</a>.
		 */
		public static interface Hreflang<E extends Element<E, ?> & Hreflang<E>> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_hreflang.asp">HTML hreflang Attribute</a>.
			 */
			@Funnel
			default E hreflang(java.lang.String hreflang) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "hreflang", MarkupType.NONE, hreflang, true, true);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_hreflang.asp">HTML hreflang Attribute</a>.
			 *
			 * @see #hreflang(java.lang.String)
			 */
			@SuppressWarnings("overloads")
			default <Ex extends Throwable> E hreflang(IOSupplierE<? extends java.lang.String, Ex> hreflang) throws IOException, Ex {
				return hreflang((hreflang == null) ? null : hreflang.get());
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_hreflang.asp">HTML hreflang Attribute</a>.
			 *
			 * @see #hreflang(java.lang.String)
			 */
			default E hreflang(Locale hreflang) throws IOException {
				return hreflang((hreflang == null) ? null : hreflang.toLanguageTag());
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_hreflang.asp">HTML hreflang Attribute</a>.
			 *
			 * @see #hreflang(java.util.Locale)
			 */
			@SuppressWarnings("overloads")
			default <Ex extends Throwable> E hreflang(Suppliers.Locale<Ex> hreflang) throws IOException, Ex {
				return hreflang((hreflang == null) ? null : hreflang.get());
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_usemap.asp">HTML usemap Attribute</a>.
		 */
		public static interface Usemap<E extends Element<E, ?> & Usemap<E>> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_usemap.asp">HTML usemap Attribute</a>.
			 * <p>
			 * Automatically prefixes '#' to any non-null and non-empty (after trimming)
			 * value that does not already begin with '#'.
			 * </p>
			 */
			@Funnel
			default E usemap(java.lang.String usemap) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				// TODO: Why is this trimmed while name (above) is not?
				usemap = Strings.trimNullIfEmpty(usemap);
				if(usemap != null) {
					if(!usemap.startsWith("#")) usemap = '#' + usemap;
					return attribute(
						element,
						"usemap",
						MarkupType.NONE,
						usemap,
						false, // already trimmed
						false  // already nullIfEmpty
					);
				} else {
					return element;
				}
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_usemap.asp">HTML usemap Attribute</a>.
			 *
			 * @see #usemap(java.lang.String)
			 */
			default <Ex extends Throwable> E usemap(IOSupplierE<? extends java.lang.String, Ex> usemap) throws IOException, Ex {
				return usemap((usemap == null) ? null : usemap.get());
			}
		}
	}

	/**
	 * Streamable text attributes.
	 */
	public static class Text {

		/** Make no instances. */
		private Text() {}

		/**
		 * @param value  The attribute value, {@link #NO_VALUE} (by identity, not value) for an empty attribute, {@code null} for no attribute.
		 */
		static <E extends Element<E, ?>, Ex extends Throwable> E attribute(E element, java.lang.String name, MarkupType markupType, Object value, boolean trim, boolean nullIfEmpty, MediaEncoder encoder) throws IOException, Ex {
			// TODO: Assert is valid attribute name by doctype
			while(value instanceof IOSupplierE<?, ?>) {
				@SuppressWarnings("unchecked") IOSupplierE<?, Ex> supplier = (IOSupplierE<?, Ex>)value;
				value = supplier.get();
			}
			if(value != null) {
				if(value instanceof MediaWritable<?>) {
					@SuppressWarnings("unchecked") MediaWritable<Ex> writer = (MediaWritable<Ex>)value;
					element.document.out.write(' ');
					element.document.out.write(name);
					element.document.out.write("=\"");
					writer.writeTo(
						// Not using DocumentMediaWriter for three reasons:
						// 1) Newlines and tabs should be encoded within the attribute, not written directly out
						// 2) The attribute content should have its own indentation scope and settings
						// 3) Attribute value indentation should be off by default always
						new MediaWriter(element.document.encodingContext, encoder, element.document.out) {
							@Override
							public void close() throws IOException {
								// Do not close underlying writer
							}
						}
					);
					element.document.out.write('"');
				} else {
					if(value == NO_VALUE) { // Identity comparison for marker value
						// Empty attribute
						element.document.out.write(' ');
						element.document.out.write(name);
						// TODO: When serialization is XML, set equal to attribute name or empty?
					} else {
						if(trim) {
							if(nullIfEmpty) {
								value = Coercion.trimNullIfEmpty(value);
							} else {
								value = Coercion.trim(value);
							}
						} else if(nullIfEmpty) {
							value = Coercion.nullIfEmpty(value);
						}
						if(value != null) {
							element.document.out.write(' ');
							element.document.out.write(name);
							element.document.out.write("=\"");
							MarkupCoercion.write(value, markupType, true, encoder, false, element.document.out);
							element.document.out.write('"');
						}
					}
				}
			}
			return element;
		}

		/**
		 * An arbitrary attribute.
		 */
		public static interface Attribute<E extends Element<E, ?> & Attribute<E>> {

			/**
			 * An arbitrary attribute.
			 *
			 * @param value  The attribute value, {@link #NO_VALUE} (by identity, not value) for an empty attribute, {@code null} for no attribute.
			 *
			 * @deprecated  Please implement specific attributes as-needed
			 */
			@Deprecated
			@Funnel
			default E attribute(java.lang.String name, Object value) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				// TODO: Validate attribute name by doctype: https://dev.w3.org/html5/html-author/#attributes (XmlUtils could help)
				return Text.attribute(element, name, MarkupType.NONE, value, false, false, textInXhtmlAttributeEncoder);
			}

			/**
			 * An arbitrary attribute.
			 *
			 * @param value  The attribute value, {@link #NO_VALUE} (by identity, not value) for an empty attribute, {@code null} for no attribute.
			 *
			 * @see #attribute(java.lang.String, java.lang.Object)
			 *
			 * @deprecated  Please implement specific attributes as-needed
			 */
			@Deprecated
			default <Ex extends Throwable> E attribute(java.lang.String name, IOSupplierE<?, Ex> value) throws IOException, Ex {
				return attribute(name, (value == null) ? null : value.get());
			}

			/**
			 * An arbitrary attribute.
			 *
			 * @see #attribute(java.lang.String, java.lang.Object)
			 *
			 * @deprecated  Please implement specific attributes as-needed
			 */
			@Deprecated
			default <Ex extends Throwable> E attribute(java.lang.String name, MediaWritable<Ex> value) throws IOException, Ex {
				return attribute(name, (Object)value);
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_accept.asp">HTML accept Attribute</a>.
		 */
		public static interface Accept<E extends Element<E, ?> & Accept<E>> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_accept.asp">HTML accept Attribute</a>.
			 */
			@Funnel
			default E accept(Object accept) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "accept", MarkupType.NONE, accept, true, true, textInXhtmlAttributeEncoder);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_accept.asp">HTML accept Attribute</a>.
			 *
			 * @see #accept(java.lang.Object)
			 */
			default <Ex extends Throwable> E accept(IOSupplierE<?, Ex> accept) throws IOException, Ex {
				return accept((accept == null) ? null : accept.get());
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_accept.asp">HTML accept Attribute</a>.
			 *
			 * @see #accept(java.lang.Object)
			 */
			default <Ex extends Throwable> E accept(MediaWritable<Ex> accept) throws IOException, Ex {
				return accept((Object)accept);
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_alt.asp">HTML alt Attribute</a>.
		 */
		public static interface Alt<E extends Element<E, ?> & Alt<E>> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_alt.asp">HTML alt Attribute</a>.
			 */
			@Funnel
			default E alt(Object alt) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "alt", MarkupType.TEXT, alt, true, false, textInXhtmlAttributeEncoder);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_alt.asp">HTML alt Attribute</a>.
			 *
			 * @see #alt(java.lang.Object)
			 */
			default <Ex extends Throwable> E alt(IOSupplierE<?, Ex> alt) throws IOException, Ex {
				return alt((alt == null) ? null : alt.get());
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_alt.asp">HTML alt Attribute</a>.
			 *
			 * @see #alt(java.lang.Object)
			 */
			default <Ex extends Throwable> E alt(MediaWritable<Ex> alt) throws IOException, Ex {
				return alt((Object)alt);
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_global_class.asp">HTML Global class Attribute</a>.
		 * <blockquote>
		 * In HTML5, the class attribute can be used on <b>any</b> HTML element (it will validate on any HTML element. However, it is not necessarily useful).
		 * </blockquote>
		 */
		// TODO: Move to String?
		public static interface Class<E extends Element<E, ?> & Class<E>> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_class.asp">HTML Global class Attribute</a>.
			 * <blockquote>
			 * In HTML5, the class attribute can be used on <b>any</b> HTML element (it will validate on any HTML element. However, it is not necessarily useful).
			 * </blockquote>
			 */
			@Funnel
			default E clazz(Object clazz) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "class", MarkupType.NONE, clazz, true, true, textInXhtmlAttributeEncoder);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_class.asp">HTML Global class Attribute</a>.
			 * <blockquote>
			 * In HTML5, the class attribute can be used on <b>any</b> HTML element (it will validate on any HTML element. However, it is not necessarily useful).
			 * </blockquote>
			 *
			 * @see #clazz(java.lang.Object)
			 */
			default <Ex extends Throwable> E clazz(IOSupplierE<?, Ex> clazz) throws IOException, Ex {
				return clazz((clazz == null) ? null : clazz.get());
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_class.asp">HTML Global class Attribute</a>.
			 * <blockquote>
			 * In HTML5, the class attribute can be used on <b>any</b> HTML element (it will validate on any HTML element. However, it is not necessarily useful).
			 * </blockquote>
			 *
			 * @see #clazz(java.lang.Object)
			 */
			default <Ex extends Throwable> E clazz(MediaWritable<Ex> clazz) throws IOException, Ex {
				return clazz((Object)clazz);
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_global_class.asp">HTML Global class Attribute</a>.
		 * <blockquote>
		 * In HTML 4.01, the class attribute cannot be used with: &lt;base&gt;, &lt;head&gt;, &lt;html&gt;, &lt;meta&gt;, &lt;param&gt;, &lt;script&gt;, &lt;style&gt;, and &lt;title&gt;.
		 * </blockquote>
		 */
		public static interface ClassNoHtml4<E extends Element<E, ?> & ClassNoHtml4<E>> extends Class<E> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_class.asp">HTML Global class Attribute</a>.
			 * <blockquote>
			 * In HTML 4.01, the class attribute cannot be used with: &lt;base&gt;, &lt;head&gt;, &lt;html&gt;, &lt;meta&gt;, &lt;param&gt;, &lt;script&gt;, &lt;style&gt;, and &lt;title&gt;.
			 * </blockquote>
			 */
			@Override
			@Funnel
			default E clazz(Object clazz) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				if(element.document.doctype != Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						RESOURCES,
						"invalidGlobalAttributeForDoctype",
						element.document.doctype,
						"class"
					);
				}
				return Class.super.clazz(clazz);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_class.asp">HTML Global class Attribute</a>.
			 * <blockquote>
			 * In HTML 4.01, the class attribute cannot be used with: &lt;base&gt;, &lt;head&gt;, &lt;html&gt;, &lt;meta&gt;, &lt;param&gt;, &lt;script&gt;, &lt;style&gt;, and &lt;title&gt;.
			 * </blockquote>
			 *
			 * @see #clazz(java.lang.Object)
			 */
			@Override
			default <Ex extends Throwable> E clazz(IOSupplierE<?, Ex> clazz) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				if(element.document.doctype != Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						RESOURCES,
						"invalidGlobalAttributeForDoctype",
						element.document.doctype,
						"class"
					);
				}
				return Class.super.clazz(clazz);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_class.asp">HTML Global class Attribute</a>.
			 * <blockquote>
			 * In HTML 4.01, the class attribute cannot be used with: &lt;base&gt;, &lt;head&gt;, &lt;html&gt;, &lt;meta&gt;, &lt;param&gt;, &lt;script&gt;, &lt;style&gt;, and &lt;title&gt;.
			 * </blockquote>
			 *
			 * @see #clazz(java.lang.Object)
			 */
			@Override
			default <Ex extends Throwable> E clazz(MediaWritable<Ex> clazz) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				if(element.document.doctype != Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						RESOURCES,
						"invalidGlobalAttributeForDoctype",
						element.document.doctype,
						"class"
					);
				}
				return Class.super.clazz(clazz);
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_content.asp">HTML content Attribute</a>.
		 */
		public static interface Content<E extends Element<E, ?> & Content<E>> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_content.asp">HTML content Attribute</a>.
			 */
			@Funnel
			default E content(Object content) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				// TODO: Might be able to perform markup for some types of content (keywords, description, ...)?
				return attribute(element, "content", MarkupType.NONE, content, false, false, textInXhtmlAttributeEncoder);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_content.asp">HTML content Attribute</a>.
			 *
			 * @see #content(java.lang.Object)
			 */
			default <Ex extends Throwable> E content(IOSupplierE<?, Ex> content) throws IOException, Ex {
				return content((content == null) ? null : content.get());
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_content.asp">HTML content Attribute</a>.
			 *
			 * @see #content(java.lang.Object)
			 */
			default <Ex extends Throwable> E content(MediaWritable<Ex> content) throws IOException, Ex {
				return content((Object)content);
			}
		}

		/**
		 * <ul>
		 * <li>See <a href="https://www.w3schools.com/tags/att_global_data.asp">HTML Global data-* Attributes</a>.</li>
		 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes/data-*">data-* - HTML: Hypertext Markup Language | MDN</a>.</li>
		 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Learn/HTML/Howto/Use_data_attributes">Using data attributes - Learn web development | MDN</a>.</li>
		 * </ul>
		 */
		public static interface Data<E extends Element<E, ?> & Data<E>> {

			/**
			 * <p>
			 * Utility class for working with {@link Data} as data-* HTML attributes.
			 * </p>
			 * <ul>
			 * <li>See <a href="https://www.w3schools.com/tags/att_global_data.asp">HTML Global data-* Attributes</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes/data-*">data-* - HTML: Hypertext Markup Language | MDN</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Learn/HTML/Howto/Use_data_attributes">Using data attributes - Learn web development | MDN</a>.</li>
			 * </ul>
			 */
			public static final class data {

				/**
				 * The required prefix for data-* HTML attributes.
				 */
				public static final java.lang.String ATTRIBUTE_PREFIX = "data-";

				/**
				 * <p>
				 * Validates a data-* HTML attribute name.
				 * </p>
				 * <ul>
				 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes/data-*">data-* - HTML: Hypertext Markup Language | MDN</a>.</li>
				 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/API/HTMLOrForeignElement/dataset">HTMLOrForeignElement.dataset - Web APIs | MDN</a>.</li>
				 * <li>See <a href="https://www.w3.org/TR/REC-xml/#NT-Name">Name - Extensible Markup Language (XML) 1.0</a>.</li>
				 * </ul>
				 */
				public static ValidationResult validate(java.lang.String attrName) {
					if(attrName == null) {
						return new InvalidResult(RESOURCES, "Text.Data.data.validate.isNull");
					}
					if(!attrName.startsWith(ATTRIBUTE_PREFIX)) {
						return new InvalidResult(
							RESOURCES,
							"Text.Data.data.validate.invalidStart",
							ATTRIBUTE_PREFIX,
							attrName
						);
					}
					int len = attrName.length();
					int pos = ATTRIBUTE_PREFIX.length();
					// The * may be replaced by any name following the production rule of XML names:
					if(!XmlUtils.isValidName(attrName, pos, len)) {
						return new InvalidResult(
							RESOURCES,
							"Text.Data.data.validate.notFollowedByValidName",
							ATTRIBUTE_PREFIX,
							attrName
						);
					}
					// the name must not contain any semicolon (U+003A):
					// Nothing to do, semicolon is already not a valid XML Name

					// but NOT any ASCII capital letters (A to Z):
					while(pos < len) {
						char ch = attrName.charAt(pos++);
						if(ch >= 'A' && ch <= 'Z') {
							return new InvalidResult(
								RESOURCES,
								"Text.Data.data.validate.mayNotContainCapitalLetters",
								attrName
							);
						}
					}
					// the name must not start with xml, whatever case is used for these letters:
					if(attrName.regionMatches(ATTRIBUTE_PREFIX.length(), "xml", 0, 3)) {
						return new InvalidResult(
							RESOURCES,
							"Text.Data.data.validate.mayNotStartXml",
							ATTRIBUTE_PREFIX,
							attrName
						);
					}
					return ValidResult.getInstance();
				}

				/**
				 * <p>
				 * Converts a data-* HTML attribute name to a JavaScript dataset property name.
				 * </p>
				 * <p>
				 * This method is the simplest conversion implementation and does not
				 * perform full validation.
				 * </p>
				 * <ul>
				 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/API/HTMLOrForeignElement/dataset">HTMLOrForeignElement.dataset - Web APIs | MDN</a>.</li>
				 * </ul>
				 *
				 * @see  #validate(java.lang.String)
				 */
				public static java.lang.String toJSName(java.lang.String attrName) {
					java.lang.String jsName = toJSNameNoAssert(attrName);
					assert attrName.equals(dataset.toAttrNameNoAssert(jsName)) : "toJSName and toAttrName are inverse functions";
					return jsName;
				}

				/**
				 * Implementation of {@link #toJSName(java.lang.String)} without assertions.
				 * Used to avoid infinite recursion when assertions are enabled.
				 */
				private static java.lang.String toJSNameNoAssert(java.lang.String attrName) {
					if(!attrName.startsWith(ATTRIBUTE_PREFIX)) {
						throw new LocalizedIllegalArgumentException(
							RESOURCES,
							"Text.Data.data.validate.invalidStart",
							ATTRIBUTE_PREFIX,
							attrName
						);
					}
					int len = attrName.length();
					// 1. The prefix data- is removed (including the dash)
					int pos = ATTRIBUTE_PREFIX.length();
					StringBuilder jsName = new StringBuilder(len - pos);
					while(pos < len) {
						char ch = attrName.charAt(pos++);
						if(ch == '-' && pos < len) {
							// 2. For any dash (U+002D) followed by an ASCII lowercase letter a to z,
							//    the dash is removed, and the letter is transformed into its uppercase counterpart
							char ch2 = attrName.charAt(pos);
							if(ch2 >= 'a' && ch2 <= 'z') {
								ch = (char)(ch2 - ('a' - 'A'));
								pos++;
							}
						}
						// 3. Other characters (including other dashes) are left unchanged
						jsName.append(ch);
					}
					return jsName.toString();
				}

				private data() {}
			}

			/**
			 * <p>
			 * Data provided by HTML attribute name.  Name must begin with {@link data#ATTRIBUTE_PREFIX}, and must
			 * conform to the rules defined in HTML.
			 * </p>
			 * <ul>
			 * <li>See <a href="https://www.w3schools.com/tags/att_global_data.asp">HTML Global data-* Attributes</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes/data-*">data-* - HTML: Hypertext Markup Language | MDN</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Learn/HTML/Howto/Use_data_attributes">Using data attributes - Learn web development | MDN</a>.</li>
			 * </ul>
			 */
			@Funnel
			default E data(java.lang.String attrName, Object value) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				validate(attrName, data::validate);
				if(element.document.doctype != Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						RESOURCES,
						"onlySupportedInHtml5",
						element.document.doctype,
						attrName
					);
				}
				return attribute(
					element,
					attrName,
					MarkupType.NONE,
					value,
					false,
					false,
					textInXhtmlAttributeEncoder
				);
			}

			/**
			 * <p>
			 * Data provided by HTML attribute name.  Name must begin with {@link data#ATTRIBUTE_PREFIX}, and must
			 * conform to the rules defined in HTML.
			 * </p>
			 * <ul>
			 * <li>See <a href="https://www.w3schools.com/tags/att_global_data.asp">HTML Global data-* Attributes</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes/data-*">data-* - HTML: Hypertext Markup Language | MDN</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Learn/HTML/Howto/Use_data_attributes">Using data attributes - Learn web development | MDN</a>.</li>
			 * </ul>
			 */
			default <Ex extends Throwable> E data(java.lang.String attrName, IOSupplierE<?, Ex> value) throws IOException, Ex {
				return data(attrName, (value == null) ? null : value.get());
			}

			/**
			 * <p>
			 * Data provided by HTML attribute name.  Name must begin with {@link data#ATTRIBUTE_PREFIX}, and must
			 * conform to the rules defined in HTML.
			 * </p>
			 * <ul>
			 * <li>See <a href="https://www.w3schools.com/tags/att_global_data.asp">HTML Global data-* Attributes</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes/data-*">data-* - HTML: Hypertext Markup Language | MDN</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Learn/HTML/Howto/Use_data_attributes">Using data attributes - Learn web development | MDN</a>.</li>
			 * </ul>
			 */
			default <Ex extends Throwable> E data(java.lang.String attrName, MediaWritable<Ex> value) throws IOException, Ex {
				return data(attrName, (Object)value);
			}

			/**
			 * <p>
			 * Utility class for working with {@link Data} as JavaScript dataset property.
			 * </p>
			 * <ul>
			 * <li>See <a href="https://www.w3schools.com/tags/att_global_data.asp">HTML Global data-* Attributes</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes/data-*">data-* - HTML: Hypertext Markup Language | MDN</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Learn/HTML/Howto/Use_data_attributes">Using data attributes - Learn web development | MDN</a>.</li>
			 * </ul>
			 */
			public static final class dataset {

				/**
				 * <p>
				 * Validates a JavaScript dataset property name.
				 * </p>
				 * <ul>
				 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/API/HTMLOrForeignElement/dataset">HTMLOrForeignElement.dataset - Web APIs | MDN</a>.</li>
				 * </ul>
				 */
				public static ValidationResult validate(java.lang.String jsName) {
					if(jsName == null) {
						return new InvalidResult(RESOURCES, "Text.Data.dataset.validate.isNull");
					}
					// 1. Restriction: Before the transformation, a dash must not be immediately followed by
					//    an ASCII lowercase letter a to z
					int len = jsName.length();
					int pos = 0;
					while(pos < len) {
						pos = jsName.indexOf('-', pos) + 1;
						if(
							// Not found
							pos == 0
							// Found at end
							|| pos >= len
						) break;
						char nextChar = jsName.charAt(pos);
						if(nextChar >= 'a' && nextChar <= 'z') {
							return new InvalidResult(
								RESOURCES,
								"Text.Data.dataset.validate.dashThenLower",
								jsName
							);
						}
					}
					// Must also convert into a valid data-* HTML attribute name
					return data.validate(toAttrName(jsName));
				}

				/**
				 * <p>
				 * Converts a JavaScript dataset property name to a data-* HTML attribute name.
				 * </p>
				 * <p>
				 * This method is the simplest conversion implementation and does not
				 * perform full validation.
				 * </p>
				 * <ul>
				 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/API/HTMLOrForeignElement/dataset">HTMLOrForeignElement.dataset - Web APIs | MDN</a>.</li>
				 * </ul>
				 *
				 * @see  #validate(java.lang.String)
				 */
				public static java.lang.String toAttrName(java.lang.String jsName) {
					java.lang.String attrName = toAttrNameNoAssert(jsName);
					assert jsName.equals(data.toJSNameNoAssert(attrName)) : "toAttrName and toJSName are inverse functions";
					return attrName;
				}

				/**
				 * Implementation of {@link #toAttrName(java.lang.String)} without assertions.
				 * Used to avoid infinite recursion when assertions are enabled.
				 */
				private static java.lang.String toAttrNameNoAssert(java.lang.String jsName) {
					int len = jsName.length();
					StringBuilder attrName = new StringBuilder(
						// room for "data-"
						data.ATTRIBUTE_PREFIX.length()
						// and room for the JavaScript property name
						+ len
						// and some space for some added dashes from conversion
						// 10 is arbitrary, but bigger than the number of typical camelCase sections
						+ 10
					);
					// 2. The prefix data- is added
					attrName.append(data.ATTRIBUTE_PREFIX);
					int pos = 0;
					while(pos < len) {
						char ch = attrName.charAt(pos++);
						if(ch >= 'A' && ch <= 'Z') {
							// 3. Any ASCII uppercase letter A to Z is transformed into a dash,
							//    followed by its lowercase counterpart
							attrName.append('-');
							ch += 'a' - 'A';
						}
						// 4. Other characters are left unchanged
						attrName.append(ch);
					}
					return attrName.toString();
				}

				private dataset() {}
			}

			/**
			 * <p>
			 * Data provided by JavaScript dataset property name.
			 * </p>
			 * <ul>
			 * <li>See <a href="https://www.w3schools.com/tags/att_global_data.asp">HTML Global data-* Attributes</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes/data-*">data-* - HTML: Hypertext Markup Language | MDN</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Learn/HTML/Howto/Use_data_attributes">Using data attributes - Learn web development | MDN</a>.</li>
			 * </ul>
			 */
			default E dataset(java.lang.String jsName, Object value) throws IOException {
				return data(
					dataset.toAttrName(
						validate(jsName, dataset::validate)
					),
					value
				);
			}

			/**
			 * <p>
			 * Data provided by JavaScript dataset property name.
			 * </p>
			 * <ul>
			 * <li>See <a href="https://www.w3schools.com/tags/att_global_data.asp">HTML Global data-* Attributes</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes/data-*">data-* - HTML: Hypertext Markup Language | MDN</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Learn/HTML/Howto/Use_data_attributes">Using data attributes - Learn web development | MDN</a>.</li>
			 * </ul>
			 */
			default <Ex extends Throwable> E dataset(java.lang.String jsName, IOSupplierE<?, Ex> value) throws IOException, Ex {
				return dataset(jsName, (value == null) ? null : value.get());
			}

			/**
			 * <p>
			 * Data provided by JavaScript dataset property name.
			 * </p>
			 * <ul>
			 * <li>See <a href="https://www.w3schools.com/tags/att_global_data.asp">HTML Global data-* Attributes</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes/data-*">data-* - HTML: Hypertext Markup Language | MDN</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Learn/HTML/Howto/Use_data_attributes">Using data attributes - Learn web development | MDN</a>.</li>
			 * </ul>
			 */
			default <Ex extends Throwable> E dataset(java.lang.String jsName, MediaWritable<Ex> value) throws IOException, Ex {
				return dataset(jsName, (Object)value);
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_global_id.asp">HTML Global id Attribute</a>.
		 * <blockquote>
		 * In HTML5, the id attribute can be used on <b>any</b> HTML element (it will validate on any HTML element. However, it is not necessarily useful).
		 * </blockquote>
		 */
		// TODO: Move to String?
		public static interface Id<E extends Element<E, ?> & Id<E>> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_id.asp">HTML Global id Attribute</a>.
			 * <blockquote>
			 * In HTML5, the id attribute can be used on <b>any</b> HTML element (it will validate on any HTML element. However, it is not necessarily useful).
			 * </blockquote>
			 */
			@Funnel
			default E id(Object id) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				// TODO: Validate, with doctype-aware character constraints.  XmlUtils can help, or build into Doctype itself.
				return attribute(element, "id", MarkupType.NONE, id, true, true, textInXhtmlAttributeEncoder);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_id.asp">HTML Global id Attribute</a>.
			 * <blockquote>
			 * In HTML5, the id attribute can be used on <b>any</b> HTML element (it will validate on any HTML element. However, it is not necessarily useful).
			 * </blockquote>
			 *
			 * @see #id(java.lang.Object)
			 */
			default <Ex extends Throwable> E id(IOSupplierE<?, Ex> id) throws IOException, Ex {
				return id((id == null) ? null : id.get());
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_id.asp">HTML Global id Attribute</a>.
			 * <blockquote>
			 * In HTML5, the id attribute can be used on <b>any</b> HTML element (it will validate on any HTML element. However, it is not necessarily useful).
			 * </blockquote>
			 *
			 * @see #id(java.lang.Object)
			 */
			default <Ex extends Throwable> E id(MediaWritable<Ex> id) throws IOException, Ex {
				return id((Object)id);
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_global_id.asp">HTML Global id Attribute</a>.
		 * <blockquote>
		 * In HTML 4.01, the id attribute cannot be used with: &lt;base&gt;, &lt;head&gt;, &lt;html&gt;, &lt;meta&gt;, &lt;param&gt;, &lt;script&gt;, &lt;style&gt;, and &lt;title&gt;.
		 * </blockquote>
		 */
		public static interface IdNoHtml4<E extends Element<E, ?> & IdNoHtml4<E>> extends Id<E> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_id.asp">HTML Global id Attribute</a>.
			 * <blockquote>
			 * In HTML 4.01, the id attribute cannot be used with: &lt;base&gt;, &lt;head&gt;, &lt;html&gt;, &lt;meta&gt;, &lt;param&gt;, &lt;script&gt;, &lt;style&gt;, and &lt;title&gt;.
			 * </blockquote>
			 */
			@Override
			@Funnel
			default E id(Object id) throws IOException {
				// TODO: normalize, then only throw when non-empty/null.  Here and other attributes.
				@SuppressWarnings("unchecked") E element = (E)this;
				if(element.document.doctype != Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						RESOURCES,
						"invalidGlobalAttributeForDoctype",
						element.document.doctype,
						"id"
					);
				}
				return Id.super.id(id);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_id.asp">HTML Global id Attribute</a>.
			 * <blockquote>
			 * In HTML 4.01, the id attribute cannot be used with: &lt;base&gt;, &lt;head&gt;, &lt;html&gt;, &lt;meta&gt;, &lt;param&gt;, &lt;script&gt;, &lt;style&gt;, and &lt;title&gt;.
			 * </blockquote>
			 *
			 * @see #id(java.lang.Object)
			 */
			@Override
			default <Ex extends Throwable> E id(IOSupplierE<?, Ex> id) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				if(element.document.doctype != Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						RESOURCES,
						"invalidGlobalAttributeForDoctype",
						element.document.doctype,
						"id"
					);
				}
				return Id.super.id(id);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_id.asp">HTML Global id Attribute</a>.
			 * <blockquote>
			 * In HTML 4.01, the id attribute cannot be used with: &lt;base&gt;, &lt;head&gt;, &lt;html&gt;, &lt;meta&gt;, &lt;param&gt;, &lt;script&gt;, &lt;style&gt;, and &lt;title&gt;.
			 * </blockquote>
			 *
			 * @see #id(java.lang.Object)
			 */
			@Override
			default <Ex extends Throwable> E id(MediaWritable<Ex> id) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				if(element.document.doctype != Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						RESOURCES,
						"invalidGlobalAttributeForDoctype",
						element.document.doctype,
						"id"
					);
				}
				return Id.super.id(id);
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_label.asp">HTML label Attribute</a>.
		 */
		public static interface Label<E extends Element<E, ?> & Label<E>> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_label.asp">HTML label Attribute</a>.
			 */
			@Funnel
			default E label(Object label) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "label", MarkupType.TEXT, label, false, false, textInXhtmlAttributeEncoder);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_label.asp">HTML label Attribute</a>.
			 *
			 * @see #label(java.lang.Object)
			 */
			default <Ex extends Throwable> E label(IOSupplierE<?, Ex> label) throws IOException, Ex {
				return label((label == null) ? null : label.get());
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_label.asp">HTML label Attribute</a>.
			 *
			 * @see #label(java.lang.Object)
			 */
			default <Ex extends Throwable> E label(MediaWritable<Ex> label) throws IOException, Ex {
				return label((Object)label);
			}
		}

		/**
		 * <ul>
		 * <li>See <a href="https://www.w3schools.com/tags/att_list.asp">HTML list Attribute</a>.</li>
		 * <li>See <a href="https://www.w3schools.com/tags/att_input_list.asp">HTML input list Attribute</a>.</li>
		 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input#htmlattrdeflist">&lt;input&gt;: The Input (Form Input) element</a>.</li>
		 * </ul>
		 */
		public static interface List<E extends Element<E, ?> & List<E>> {

			/**
			 * <ul>
			 * <li>See <a href="https://www.w3schools.com/tags/att_list.asp">HTML list Attribute</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/att_input_list.asp">HTML input list Attribute</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input#htmlattrdeflist">&lt;input&gt;: The Input (Form Input) element</a>.</li>
			 * </ul>
			 */
			@Funnel
			default E list(Object list) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				if(element.document.doctype != Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						RESOURCES,
						"onlySupportedInHtml5",
						element.document.doctype,
						"list"
					);
				}
				return attribute(element, "list", MarkupType.NONE, list, true, true, textInXhtmlAttributeEncoder);
			}

			/**
			 * <ul>
			 * <li>See <a href="https://www.w3schools.com/tags/att_list.asp">HTML list Attribute</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/att_input_list.asp">HTML input list Attribute</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input#htmlattrdeflist">&lt;input&gt;: The Input (Form Input) element</a>.</li>
			 * </ul>
			 *
			 * @see #list(java.lang.Object)
			 */
			default <Ex extends Throwable> E list(IOSupplierE<?, Ex> list) throws IOException, Ex {
				return list((list == null) ? null : list.get());
			}

			/**
			 * <ul>
			 * <li>See <a href="https://www.w3schools.com/tags/att_list.asp">HTML list Attribute</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/att_input_list.asp">HTML input list Attribute</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input#htmlattrdeflist">&lt;input&gt;: The Input (Form Input) element</a>.</li>
			 * </ul>
			 *
			 * @see #list(java.lang.Object)
			 */
			default <Ex extends Throwable> E list(MediaWritable<Ex> list) throws IOException, Ex {
				return list((Object)list);
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_media.asp">HTML media Attribute</a>.
		 */
		// TODO: Any sort of comments allowed in media queries?  MarkupType?
		public static interface Media<E extends Element<E, ?> & Media<E>> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_media.asp">HTML media Attribute</a>.
			 */
			@Funnel
			default E media(Object media) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "media", MarkupType.NONE, media, true, true, textInXhtmlAttributeEncoder);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_media.asp">HTML media Attribute</a>.
			 *
			 * @see #media(java.lang.Object)
			 */
			default <Ex extends Throwable> E media(IOSupplierE<?, Ex> media) throws IOException, Ex {
				return media((media == null) ? null : media.get());
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_media.asp">HTML media Attribute</a>.
			 *
			 * @see #media(java.lang.Object)
			 */
			default <Ex extends Throwable> E media(MediaWritable<Ex> media) throws IOException, Ex {
				return media((Object)media);
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_name.asp">HTML name Attribute</a>.
		 */
		public static interface Name<E extends Element<E, ?> & Name<E>> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_name.asp">HTML name Attribute</a>.
			 */
			@Funnel
			default E name(Object name) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				// TODO: Review if trim-to-null is the best default.  Maybe default to "false" and override where should be true instead.
				return attribute(element, "name", MarkupType.NONE, name, false, true, textInXhtmlAttributeEncoder);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_name.asp">HTML name Attribute</a>.
			 *
			 * @see #name(java.lang.Object)
			 */
			default <Ex extends Throwable> E name(IOSupplierE<?, Ex> name) throws IOException, Ex {
				return name((name == null) ? null : name.get());
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_name.asp">HTML name Attribute</a>.
			 *
			 * @see #name(java.lang.Object)
			 */
			default <Ex extends Throwable> E name(MediaWritable<Ex> name) throws IOException, Ex {
				return name((Object)name);
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_placeholder.asp">HTML placeholder Attribute</a>.
		 */
		public static interface Placeholder<E extends Element<E, ?> & Placeholder<E>> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_placeholder.asp">HTML placeholder Attribute</a>.
			 */
			@Funnel
			default E placeholder(Object placeholder) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				if(element.document.doctype != Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						RESOURCES,
						"onlySupportedInHtml5",
						element.document.doctype,
						"placeholder"
					);
				}
				return attribute(element, "placeholder", MarkupType.TEXT, placeholder, false, true, textInXhtmlAttributeEncoder);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_placeholder.asp">HTML placeholder Attribute</a>.
			 *
			 * @see #placeholder(java.lang.Object)
			 */
			default <Ex extends Throwable> E placeholder(IOSupplierE<?, Ex> placeholder) throws IOException, Ex {
				return placeholder((placeholder == null) ? null : placeholder.get());
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_placeholder.asp">HTML placeholder Attribute</a>.
			 *
			 * @see #placeholder(java.lang.Object)
			 */
			default <Ex extends Throwable> E placeholder(MediaWritable<Ex> placeholder) throws IOException, Ex {
				return placeholder((Object)placeholder);
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_global_style.asp">HTML Global style Attribute</a>.
		 * <blockquote>
		 * In HTML5, the style attribute can be used on <b>any</b> HTML element (it will validate on any HTML element. However, it is not necessarily useful).
		 * </blockquote>
		 */
		// TODO: cssInXmlAttributeEncoder
		public static interface Style<E extends Element<E, ?> & Style<E>> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_style.asp">HTML Global style Attribute</a>.
			 * <blockquote>
			 * In HTML5, the style attribute can be used on <b>any</b> HTML element (it will validate on any HTML element. However, it is not necessarily useful).
			 * </blockquote>
			 */
			@Funnel
			default E style(Object style) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				// TODO: MarkupType.CSS
				return attribute(element, "style", MarkupType.JAVASCRIPT, style, true, true, textInXhtmlAttributeEncoder);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_style.asp">HTML Global style Attribute</a>.
			 * <blockquote>
			 * In HTML5, the style attribute can be used on <b>any</b> HTML element (it will validate on any HTML element. However, it is not necessarily useful).
			 * </blockquote>
			 *
			 * @see #style(java.lang.Object)
			 */
			default <Ex extends Throwable> E style(IOSupplierE<?, Ex> style) throws IOException, Ex {
				return style((style == null) ? null : style.get());
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_style.asp">HTML Global style Attribute</a>.
			 * <blockquote>
			 * In HTML5, the style attribute can be used on <b>any</b> HTML element (it will validate on any HTML element. However, it is not necessarily useful).
			 * </blockquote>
			 *
			 * @see #style(java.lang.Object)
			 */
			default <Ex extends Throwable> E style(MediaWritable<Ex> style) throws IOException, Ex {
				return style((Object)style);
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_global_style.asp">HTML Global style Attribute</a>.
		 * <blockquote>
		 * In HTML 4.01, the style attribute cannot be used with: &lt;base&gt;, &lt;head&gt;, &lt;html&gt;, &lt;meta&gt;, &lt;param&gt;, &lt;script&gt;, &lt;style&gt;, and &lt;title&gt;.
		 * </blockquote>
		 */
		public static interface StyleNoHtml4<E extends Element<E, ?> & StyleNoHtml4<E>> extends Style<E> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_style.asp">HTML Global style Attribute</a>.
			 * <blockquote>
			 * In HTML 4.01, the style attribute cannot be used with: &lt;base&gt;, &lt;head&gt;, &lt;html&gt;, &lt;meta&gt;, &lt;param&gt;, &lt;script&gt;, &lt;style&gt;, and &lt;title&gt;.
			 * </blockquote>
			 */
			@Override
			@Funnel
			default E style(Object style) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				if(element.document.doctype != Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						RESOURCES,
						"invalidGlobalAttributeForDoctype",
						element.document.doctype,
						"style"
					);
				}
				return Style.super.style(style);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_style.asp">HTML Global style Attribute</a>.
			 * <blockquote>
			 * In HTML 4.01, the style attribute cannot be used with: &lt;base&gt;, &lt;head&gt;, &lt;html&gt;, &lt;meta&gt;, &lt;param&gt;, &lt;script&gt;, &lt;style&gt;, and &lt;title&gt;.
			 * </blockquote>
			 *
			 * @see #style(java.lang.Object)
			 */
			@Override
			default <Ex extends Throwable> E style(IOSupplierE<?, Ex> style) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				if(element.document.doctype != Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						RESOURCES,
						"invalidGlobalAttributeForDoctype",
						element.document.doctype,
						"style"
					);
				}
				return Style.super.style(style);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_style.asp">HTML Global style Attribute</a>.
			 * <blockquote>
			 * In HTML 4.01, the style attribute cannot be used with: &lt;base&gt;, &lt;head&gt;, &lt;html&gt;, &lt;meta&gt;, &lt;param&gt;, &lt;script&gt;, &lt;style&gt;, and &lt;title&gt;.
			 * </blockquote>
			 *
			 * @see #style(java.lang.Object)
			 */
			@Override
			default <Ex extends Throwable> E style(MediaWritable<Ex> style) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				if(element.document.doctype != Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						RESOURCES,
						"invalidGlobalAttributeForDoctype",
						element.document.doctype,
						"style"
					);
				}
				return Style.super.style(style);
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_global_title.asp">HTML Global title Attribute</a>.
		 * <blockquote>
		 * In HTML5, the title attribute can be used on <b>any</b> HTML element (it will validate on any HTML element. However, it is not necessarily useful).
		 * </blockquote>
		 */
		public static interface Title<E extends Element<E, ?> & Title<E>> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_title.asp">HTML Global title Attribute</a>.
			 * <blockquote>
			 * In HTML5, the title attribute can be used on <b>any</b> HTML element (it will validate on any HTML element. However, it is not necessarily useful).
			 * </blockquote>
			 */
			@Funnel
			default E title(Object title) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "title", MarkupType.TEXT, title, true, true, textInXhtmlAttributeEncoder);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_title.asp">HTML Global title Attribute</a>.
			 * <blockquote>
			 * In HTML5, the title attribute can be used on <b>any</b> HTML element (it will validate on any HTML element. However, it is not necessarily useful).
			 * </blockquote>
			 *
			 * @see #title(java.lang.Object)
			 */
			default <Ex extends Throwable> E title(IOSupplierE<?, Ex> title) throws IOException, Ex {
				return title((title == null) ? null : title.get());
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_title.asp">HTML Global title Attribute</a>.
			 * <blockquote>
			 * In HTML5, the title attribute can be used on <b>any</b> HTML element (it will validate on any HTML element. However, it is not necessarily useful).
			 * </blockquote>
			 *
			 * @see #title(java.lang.Object)
			 */
			default <Ex extends Throwable> E title(MediaWritable<Ex> title) throws IOException, Ex {
				return title((Object)title);
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_global_title.asp">HTML Global title Attribute</a>.
		 * <blockquote>
		 * In HTML 4.01, the title attribute cannot be used with: &lt;base&gt;, &lt;head&gt;, &lt;html&gt;, &lt;meta&gt;, &lt;param&gt;, &lt;script&gt;, &lt;style&gt;, and &lt;title&gt;.
		 * </blockquote>
		 */
		public static interface TitleNoHtml4<E extends Element<E, ?> & TitleNoHtml4<E>> extends Title<E> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_title.asp">HTML Global title Attribute</a>.
			 * <blockquote>
			 * In HTML 4.01, the title attribute cannot be used with: &lt;base&gt;, &lt;head&gt;, &lt;html&gt;, &lt;meta&gt;, &lt;param&gt;, &lt;script&gt;, &lt;style&gt;, and &lt;title&gt;.
			 * </blockquote>
			 */
			@Override
			@Funnel
			default E title(Object title) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				if(element.document.doctype != Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						RESOURCES,
						"invalidGlobalAttributeForDoctype",
						element.document.doctype,
						"title"
					);
				}
				return Title.super.title(title);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_title.asp">HTML Global title Attribute</a>.
			 * <blockquote>
			 * In HTML 4.01, the title attribute cannot be used with: &lt;base&gt;, &lt;head&gt;, &lt;html&gt;, &lt;meta&gt;, &lt;param&gt;, &lt;script&gt;, &lt;style&gt;, and &lt;title&gt;.
			 * </blockquote>
			 *
			 * @see #title(java.lang.Object)
			 */
			@Override
			default <Ex extends Throwable> E title(IOSupplierE<?, Ex> title) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				if(element.document.doctype != Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						RESOURCES,
						"invalidGlobalAttributeForDoctype",
						element.document.doctype,
						"title"
					);
				}
				return Title.super.title(title);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_title.asp">HTML Global title Attribute</a>.
			 * <blockquote>
			 * In HTML 4.01, the title attribute cannot be used with: &lt;base&gt;, &lt;head&gt;, &lt;html&gt;, &lt;meta&gt;, &lt;param&gt;, &lt;script&gt;, &lt;style&gt;, and &lt;title&gt;.
			 * </blockquote>
			 *
			 * @see #title(java.lang.Object)
			 */
			@Override
			default <Ex extends Throwable> E title(MediaWritable<Ex> title) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				if(element.document.doctype != Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						RESOURCES,
						"invalidGlobalAttributeForDoctype",
						element.document.doctype,
						"title"
					);
				}
				return Title.super.title(title);
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_value.asp">HTML value Attribute</a>.
		 */
		public static interface Value<E extends Element<E, ?> & Value<E>> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_value.asp">HTML value Attribute</a>.
			 */
			@Funnel
			default E value(Object value) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				// TODO: Review if trim-to-null is the best default.  Maybe default to "false" and override where should be true instead.
				return attribute(element, "value", MarkupType.NONE, value, false, true, textInXhtmlAttributeEncoder);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_value.asp">HTML value Attribute</a>.
			 *
			 * @see #value(java.lang.Object)
			 */
			default <Ex extends Throwable> E value(IOSupplierE<?, Ex> value) throws IOException, Ex {
				return value((value == null) ? null : value.get());
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_value.asp">HTML value Attribute</a>.
			 *
			 * @see #value(java.lang.Object)
			 */
			default <Ex extends Throwable> E value(MediaWritable<Ex> value) throws IOException, Ex {
				return value((Object)value);
			}
		}
	}

	/**
	 * URL attributes.
	 */
	// TODO: Encoding URL via encoding context
	public static class Url {

		/** Make no instances. */
		private Url() {}

		static <E extends Element<E, ?>> E attribute(E element, java.lang.String name, java.lang.String url) throws IOException {
			if(url != null) {
				element.document.out.write(' ');
				element.document.out.write(name);
				element.document.out.write("=\"");
				// TODO: UrlInXhtmlAttributeEncoder once RFC 3987 supported
				textInXhtmlAttributeEncoder.write(url, element.document.out);
				element.document.out.write('"');
			}
			return element;
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_href.asp">HTML href Attribute</a>.
		 */
		public static interface Href<E extends Element<E, ?> & Href<E>> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_href.asp">HTML href Attribute</a>.
			 */
			@Funnel
			default E href(java.lang.String href) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "href", href);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_href.asp">HTML href Attribute</a>.
			 *
			 * @see #href(java.lang.String)
			 */
			default <Ex extends Throwable> E href(IOSupplierE<? extends java.lang.String, Ex> href) throws IOException, Ex {
				return href((href == null) ? null : href.get());
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_src.asp">HTML src Attribute</a>.
		 */
		public static interface Src<E extends Element<E, ?> & Src<E>> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_src.asp">HTML src Attribute</a>.
			 */
			@Funnel
			default E src(java.lang.String src) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "src", src);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_src.asp">HTML src Attribute</a>.
			 *
			 * @see #src(java.lang.String)
			 */
			default <Ex extends Throwable> E src(IOSupplierE<? extends java.lang.String, Ex> src) throws IOException, Ex {
				return src((src == null) ? null : src.get());
			}
		}
	}

	/**
	 * <ul>
	 * <li>See <a href="https://www.w3schools.com/tags/ref_standardattributes.asp">HTML Global attributes</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes">Global attributes</a>.</li>
	 * </ul>
	 */
	public static interface Global<E extends Element<E, ?> & Global<E>> extends
		// TODO: accesskey
		// TODO: autocapitalize
		Text.Class<E>,
		// TODO: contenteditable
		// TODO: contextmenu (deprecated)
		Text.Data<E>,
		Enum.Dir<E, Enum.Dir.Value>,
		// TODO: draggable
		// TODO: dropzone (experimental)
		// TODO: exportparts (experimental)
		// TODO: hidden
		Text.Id<E>,
		// TODO: inputmode
		// TODO: is
		// TODO: itemid
		// TODO: itemprop
		// TODO: itemref
		// TODO: itemscope
		// TODO: itemtype
		// TODO: lang
		// TODO: part (experimental)
		// TODO: slot
		// TODO: spellcheck (experimental)
		Text.Style<E>,
		Integer.Tabindex<E>,
		Text.Title<E>,
		// TODO: translate (experimental)
		// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
		Event.Form.Global<E>,
		Event.Mouse.Global<E>,
		Event.Drag.Global<E>,
		Event.Clipboard.Global<E>
		// TODO: onsort? https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes
	{
		// No methods, just adding attributes
	}
}
