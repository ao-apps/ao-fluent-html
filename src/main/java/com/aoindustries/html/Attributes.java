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
import com.aoindustries.util.i18n.MarkupCoercion;
import com.aoindustries.util.i18n.MarkupType;
import com.aoindustries.validation.InvalidResult;
import com.aoindustries.validation.ValidationResult;
import java.io.IOException;
import java.io.Writer;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
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

	public static final Resources RESOURCES = Resources.getResources(Attributes.class);

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
	// TODO: Move to com.aoindustries.html and use for *_factory classes, too?
	@Retention(RetentionPolicy.SOURCE)
	@Target(ElementType.METHOD)
	public static @interface Funnel {
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
	 *
	 * @see  com.aoindustries.html.attributes.Boolean
	 */
	public static class Boolean {

		/** Make no instances. */
		private Boolean() {}

		public static <E extends Element<E, ?>> E attribute(E element, java.lang.String name, boolean value) throws IOException {
			if(value) {
				Writer out = element.document.out;
				out.write(' ');
				out.write(name);
				if(element.document.serialization == Serialization.XML) {
					out.write("=\"");
					out.write(name);
					out.write('"');
				} else {
					assert element.document.serialization == Serialization.SGML;
				}
			}
			return element;
		}
	}

	/**
	 * See <a href="https://html.spec.whatwg.org/multipage/common-microsyntaxes.html#percentages-and-dimensions">2.4.4.4 Percentages and lengths</a>.
	 * <p>
	 * Supports Integer length or percentage of parent (HTML 4-only).
	 * </p>
	 *
	 * @see  com.aoindustries.html.attributes.Dimension
	 */
	public static class Dimension {

		/** Make no instances. */
		private Dimension() {}

		public static <E extends Element<E, ?>> E attribute(E element, java.lang.String name, int pixels) throws IOException {
			return Integer.attribute(element, name, pixels);
		}

		public static <E extends Element<E, ?>> E attribute(E element, java.lang.String name, java.lang.Integer pixels) throws IOException {
			return Integer.attribute(element, name, pixels);
		}

		/**
		 * @deprecated  In HTML 4.01, the value could be defined in pixels or in % of the containing element. In HTML5, the value must be in pixels.
		 */
		@Deprecated
		public static <E extends Element<E, ?>> E attribute(E element, java.lang.String name, java.lang.String pixelsOrPercent) throws IOException {
			return String.attribute(element, name, MarkupType.NONE, pixelsOrPercent, true, true);
		}
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/ref_eventattributes.asp">HTML Event Attributes</a>.
	 *
	 * @see  com.aoindustries.html.attributes.event.window
	 * @see  com.aoindustries.html.attributes.event.form
	 * @see  com.aoindustries.html.attributes.event.keyboard
	 * @see  com.aoindustries.html.attributes.event.mouse
	 * @see  com.aoindustries.html.attributes.event.drag
	 * @see  com.aoindustries.html.attributes.event.clipboard
	 * @see  com.aoindustries.html.attributes.event.media
	 * @see  com.aoindustries.html.attributes.event.misc
	 */
	public static class Event {

		/** Make no instances. */
		private Event() {}

		public static <E extends Element<E, ?>, Ex extends Throwable> E attribute(E element, java.lang.String name, Object script) throws IOException, Ex {
			return Attributes.Text.attribute(element, name, MarkupType.JAVASCRIPT, script, true, true, javaScriptInXhtmlAttributeEncoder);
		}
	}

	/**
	 * See <a href="https://html.spec.whatwg.org/multipage/common-microsyntaxes.html#signed-integers">2.4.4.1 Signed integers</a>.
	 *
	 * @see  com.aoindustries.html.attributes.Integer
	 */
	public static class Integer {

		/** Make no instances. */
		private Integer() {}

		public static <E extends Element<E, ?>> E attribute(E element, java.lang.String name, int value) throws IOException {
			Writer out = element.document.out;
			out.write(' ');
			out.write(name);
			out.write("=\"");
			out.write(java.lang.Integer.toString(value));
			out.write('"');
			return element;
		}

		public static <E extends Element<E, ?>> E attribute(E element, java.lang.String name, java.lang.Integer value) throws IOException {
			if(value != null) {
				return attribute(element, name, value.intValue());
			} else {
				return element;
			}
		}
	}

	/**
	 * Non-streamable text attributes (expected to be short, relatively fixed values)
	 *
	 * @see  com.aoindustries.html.attributes.String
	 */
	public static class String {

		/** Make no instances. */
		private String() {}

		/**
		 * @param value  If is {@link #NO_VALUE} (by identity), will write empty attribute.
		 */
		// TODO: Remove trim and nullIfEmpty once all attributes have normalize methods
		@SuppressWarnings("StringEquality")
		public static <E extends Element<E, ?>> E attribute(E element, java.lang.String name, MarkupType markupType, java.lang.String value, boolean trim, boolean nullIfEmpty) throws IOException {
			if(value != null) {
				if(value == NO_VALUE) { // Identity comparison for marker value
					// Empty attribute
					Writer out = element.document.out;
					out.write(' ');
					out.write(name);
				} else {
					if(trim) value = value.trim(); // TODO: These trims should all be from Strings?
					if(!nullIfEmpty || !value.isEmpty()) {
						Writer out = element.document.out;
						out.write(' ');
						out.write(name);
						out.write("=\"");
						if(markupType == null || markupType == MarkupType.NONE) {
							// Short-cut additional type checks done by Coercion, since we already have a String
							encodeTextInXhtmlAttribute(value, out);
						} else {
							MarkupCoercion.write(value, markupType, true, textInXhtmlAttributeEncoder, false, out);
						}
						out.write('"');
					}
				}
			}
			return element;
		}
	}

	/**
	 * Streamable text attributes.
	 *
	 * @see  com.aoindustries.html.attributes.Text
	 */
	public static class Text {

		/** Make no instances. */
		private Text() {}

		/**
		 * @param value  The attribute value, {@link Attributes#NO_VALUE} (by identity, not value) for an empty attribute, {@code null} for no attribute.
		 */
		public static <E extends Element<E, ?>, Ex extends Throwable> E attribute(E element, java.lang.String name, MarkupType markupType, Object value, boolean trim, boolean nullIfEmpty, MediaEncoder encoder) throws IOException, Ex {
			// TODO: Assert is valid attribute name by doctype
			while(value instanceof IOSupplierE<?, ?>) {
				@SuppressWarnings("unchecked") IOSupplierE<?, Ex> supplier = (IOSupplierE<?, Ex>)value;
				value = supplier.get();
			}
			if(value != null) {
				if(value instanceof MediaWritable<?>) {
					@SuppressWarnings("unchecked") MediaWritable<Ex> writer = (MediaWritable<Ex>)value;
					Writer out = element.document.out;
					out.write(' ');
					out.write(name);
					out.write("=\"");
					writer.writeTo(
						// Not using DocumentMediaWriter for three reasons:
						// 1) Newlines and tabs should be encoded within the attribute, not written directly out
						// 2) The attribute content should have its own indentation scope and settings
						// 3) Attribute value indentation should be off by default always
						new MediaWriter(element.document.encodingContext, encoder, out) {
							@Override
							public void close() throws IOException {
								// Do not close underlying writer
							}
						}
					);
					out.write('"');
				} else {
					if(value == NO_VALUE) { // Identity comparison for marker value
						// Empty attribute
						Writer out = element.document.out;
						out.write(' ');
						out.write(name);
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
							Writer out = element.document.out;
							out.write(' ');
							out.write(name);
							out.write("=\"");
							MarkupCoercion.write(value, markupType, true, encoder, false, out);
							out.write('"');
						}
					}
				}
			}
			return element;
		}
	}

	/**
	 * URL attributes.
	 *
	 * @see  com.aoindustries.html.attributes.Url
	 */
	// TODO: Encoding URL via encoding context
	public static class Url {

		/** Make no instances. */
		private Url() {}

		public static <E extends Element<E, ?>> E attribute(E element, java.lang.String name, java.lang.String url) throws IOException {
			if(url != null) {
				Writer out = element.document.out;
				out.write(' ');
				out.write(name);
				out.write("=\"");
				// TODO: UrlInXhtmlAttributeEncoder once RFC 3987 supported
				textInXhtmlAttributeEncoder.write(url, out);
				out.write('"');
			}
			return element;
		}
	}
}
