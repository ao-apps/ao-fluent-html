/*
 * ao-fluent-html - Fluent Java DSL for high-performance HTML generation.
 * Copyright (C) 2019  AO Industries, Inc.
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

import com.aoindustries.encoding.Coercion;
import static com.aoindustries.encoding.JavaScriptInXhtmlAttributeEncoder.javaScriptInXhtmlAttributeEncoder;
import com.aoindustries.encoding.MediaWriter;
import static com.aoindustries.encoding.TextInXhtmlAttributeEncoder.encodeTextInXhtmlAttribute;
import static com.aoindustries.encoding.TextInXhtmlAttributeEncoder.textInXhtmlAttributeEncoder;
import static com.aoindustries.html.ApplicationResources.accessor;
import com.aoindustries.lang.LocalizedIllegalArgumentException;
import com.aoindustries.util.StringUtility;
import com.aoindustries.util.WrappedException;
import com.aoindustries.util.i18n.MarkupType;
import java.io.IOException;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * See <a href="https://www.w3schools.com/tags/ref_attributes.asp">HTML Attributes</a>.
 *
 * @author  AO Industries, Inc.
 */
// TODO: Review which attributes should be trimmed and/or nullIfEmpty
public class Attributes {

	/** Make no instances. */
	private Attributes() {}

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
	 * See <a href="https://html.spec.whatwg.org/multipage/common-microsyntaxes.html#boolean-attributes">2.4.2 Boolean attributes</a>.
	 */
	public static class Boolean {

		/** Make no instances. */
		private Boolean() {}

		static <E extends Element<E>> E attribute(E element, java.lang.String name, boolean value) throws IOException {
			if(value) {
				element.html.out.write(' ');
				element.html.out.write(name);
				if(element.html.serialization == Serialization.XML) {
					element.html.out.write("=\"");
					element.html.out.write(name);
					element.html.out.write('"');
				} else {
					assert element.html.serialization == Serialization.SGML;
				}
			}
			return element;
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_async.asp">HTML async Attribute</a>.
		 */
		public static interface Async<E extends Element<E> & Async<E>> {

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
			 */
			default E async(java.lang.Boolean async) throws IOException {
				return async(async != null && async);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_async.asp">HTML async Attribute</a>.
			 */
			default <Ex extends Throwable> E async(Supplier<? extends java.lang.Boolean,Ex> async) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				return async((async == null) ? null : async.get(element.html.serialization, element.html.doctype));
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_checked.asp">HTML checked Attribute</a>.
		 */
		public static interface Checked<E extends Element<E> & Checked<E>> {

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
			 */
			default E checked(java.lang.Boolean checked) throws IOException {
				return checked(checked != null && checked);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_checked.asp">HTML checked Attribute</a>.
			 */
			default <Ex extends Throwable> E checked(Supplier<? extends java.lang.Boolean,Ex> checked) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				return checked((checked == null) ? null : checked.get(element.html.serialization, element.html.doctype));
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_defer.asp">HTML defer Attribute</a>.
		 */
		public static interface Defer<E extends Element<E> & Defer<E>> {

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
			 */
			default E defer(java.lang.Boolean defer) throws IOException {
				return defer(defer != null && defer);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_defer.asp">HTML defer Attribute</a>.
			 */
			default <Ex extends Throwable> E defer(Supplier<? extends java.lang.Boolean,Ex> defer) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				return defer((defer == null) ? null : defer.get(element.html.serialization, element.html.doctype));
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_disabled.asp">HTML disabled Attribute</a>.
		 */
		public static interface Disabled<E extends Element<E> & Disabled<E>> {

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
			 */
			default E disabled(java.lang.Boolean disabled) throws IOException {
				return disabled(disabled != null && disabled);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_disabled.asp">HTML disabled Attribute</a>.
			 */
			default <Ex extends Throwable> E disabled(Supplier<? extends java.lang.Boolean,Ex> disabled) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				return disabled((disabled == null) ? null : disabled.get(element.html.serialization, element.html.doctype));
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_hr_noshade.asp">HTML hr noshade Attribute</a>.
		 *
		 * @deprecated  The noshade attribute of <code>&lt;hr&gt;</code> is not supported in HTML5. Use CSS instead.
		 */
		@Deprecated
		public static interface Noshade<E extends Element<E> & Noshade<E>> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_hr_noshade.asp">HTML hr noshade Attribute</a>.
			 *
			 * @deprecated  The noshade attribute of <code>&lt;hr&gt;</code> is not supported in HTML5. Use CSS instead.
			 */
			@Deprecated
			@Funnel
			default E noshade(boolean noshade) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				if(element.html.doctype == Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						accessor,
						"Attributes.notSupportedInHtml5",
						"noshade"
					);
				}
				return attribute(element, "noshade", noshade);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_hr_noshade.asp">HTML hr noshade Attribute</a>.
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
			 * @deprecated  The noshade attribute of <code>&lt;hr&gt;</code> is not supported in HTML5. Use CSS instead.
			 */
			@Deprecated
			default <Ex extends Throwable> E noshade(Supplier<? extends java.lang.Boolean,Ex> noshade) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				return noshade((noshade == null) ? null : noshade.get(element.html.serialization, element.html.doctype));
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_readonly.asp">HTML readonly Attribute</a>.
		 */
		public static interface Readonly<E extends Element<E> & Readonly<E>> {

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
			 */
			default E readonly(java.lang.Boolean readonly) throws IOException {
				return readonly(readonly != null && readonly);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_readonly.asp">HTML readonly Attribute</a>.
			 */
			default <Ex extends Throwable> E readonly(Supplier<? extends java.lang.Boolean,Ex> readonly) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				return readonly((readonly == null) ? null : readonly.get(element.html.serialization, element.html.doctype));
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_selected.asp">HTML selected Attribute</a>.
		 */
		public static interface Selected<E extends Element<E> & Selected<E>> {

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
			 */
			default E selected(java.lang.Boolean selected) throws IOException {
				return selected(selected != null && selected);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_selected.asp">HTML selected Attribute</a>.
			 */
			default <Ex extends Throwable> E selected(Supplier<? extends java.lang.Boolean,Ex> selected) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				return selected((selected == null) ? null : selected.get(element.html.serialization, element.html.doctype));
			}
		}
	}

	/**
	 * See <a href="https://html.spec.whatwg.org/multipage/common-microsyntaxes.html#percentages-and-dimensions">2.4.4.4 Percentages and lengths</a>.
	 * <p>
	 * Supports Integer length or percentage of parent (HTML 4-only).
	 * </p>
	 */
	// TODO: Extend both Integer and String types?
	public static class Dimension {

		/** Make no instances. */
		private Dimension() {}

		static <E extends Element<E>> E attribute(E element, java.lang.String name, int pixels) throws IOException {
			return Integer.attribute(element, name, pixels);
		}

		static <E extends Element<E>> E attribute(E element, java.lang.String name, java.lang.Integer pixels) throws IOException {
			return Integer.attribute(element, name, pixels);
		}

		/**
		 * @deprecated  In HTML 4.01, the value could be defined in pixels or in % of the containing element. In HTML5, the value must be in pixels.
		 */
		@Deprecated
		static <E extends Element<E>> E attribute(E element, java.lang.String name, java.lang.String pixelsOrPercent) throws IOException {
			pixelsOrPercent = StringUtility.trimNullIfEmpty(pixelsOrPercent);
			if(pixelsOrPercent != null) {
				element.html.out.write(' ');
				element.html.out.write(name);
				element.html.out.write("=\"");
				encodeTextInXhtmlAttribute(pixelsOrPercent, element.html.out);
				element.html.out.write('"');
			}
			return element;
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_width.asp">HTML width Attribute</a>.
		 */
		public static interface Width<E extends Element<E> & Width<E>> {

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
			 */
			default <Ex extends Throwable> E width(Supplier<? extends java.lang.Integer,Ex> pixels) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				return width((pixels == null) ? null : pixels.get(element.html.serialization, element.html.doctype));
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
			 * @deprecated  In HTML 4.01, the width could be defined in pixels or in % of the containing element. In HTML5, the value must be in pixels.
			 */
			@Deprecated
			default <Ex extends Throwable> E width(StringSupplier<Ex> pixelsOrPercent) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				return width((pixelsOrPercent == null) ? null : pixelsOrPercent.get(element.html.serialization, element.html.doctype));
			}
		}
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/ref_eventattributes.asp">HTML Event Attributes</a>.
	 */
	public static class Event {

		/** Make no instances. */
		private Event() {}

		static <E extends Element<E>,Ex extends Throwable> E attribute(E element, java.lang.String name, Object script) throws IOException, Ex {
			while(script instanceof Supplier<?,?>) {
				@SuppressWarnings("unchecked")
				Supplier<?,Ex> supplier = (Supplier<?,Ex>)script;
				script = supplier.get(element.html.serialization, element.html.doctype);
			}
			if(script != null) {
				element.html.out.write(' ');
				element.html.out.write(name);
				element.html.out.write("=\"");
				if(script instanceof AttributeWriter<?>) {
					@SuppressWarnings("unchecked")
					AttributeWriter<Ex> writer = (AttributeWriter<Ex>)script;
					writer.writeAttribute(
						new MediaWriter(javaScriptInXhtmlAttributeEncoder, element.html.out) {
							@Override
							public void close() throws IOException {
								// Do not close underlying writer
							}
						}
					);
				} else {
					// TODO: Find more places where we can do javascript markups (ao-taglib...)
					Coercion.write(script, MarkupType.JAVASCRIPT, javaScriptInXhtmlAttributeEncoder, false, element.html.out);
				}
				element.html.out.write('"');
			}
			return element;
		}

		public static class Mouse {

			/** Make no instances. */
			private Mouse() {}

			/**
			 * See <a href="https://www.w3schools.com/tags/ref_eventattributes.asp">Mouse Events</a>.
			 * Supported HTML tags:
			 * <blockquote>
			 * All HTML elements, EXCEPT: &lt;base&gt;, &lt;bdo&gt;, &lt;br&gt;, &lt;head&gt;, &lt;html&gt;, &lt;iframe&gt;, &lt;meta&gt;, &lt;param&gt;, &lt;script&gt;, &lt;style&gt;, and &lt;title&gt;
			 * </blockquote>
			 */
			public static interface Events<E extends Element<E> & Onclick<E>> extends Onclick<E> {
				// No methods, just adding defaults for all mouse event types
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/ev_onclick.asp">HTML onclick Event Attribute</a>.
			 */
			public static interface Onclick<E extends Element<E> & Onclick<E>> {

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
				 */
				default <Ex extends Throwable> E onclick(Supplier<?,Ex> onclick) throws IOException, Ex {
					@SuppressWarnings("unchecked") E element = (E)this;
					return onclick((onclick == null) ? null : onclick.get(element.html.serialization, element.html.doctype));
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onclick.asp">HTML onclick Event Attribute</a>.
				 */
				default <Ex extends Throwable> E onclick(AttributeWriter<Ex> onclick) throws IOException, Ex {
					return onclick((Object)onclick);
				}
			}
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
	 * When converting from {@link java.lang.Enum}, uses {@link StringSupplier#get(com.aoindustries.html.Serialization, com.aoindustries.html.Doctype)}.
	 * </p>
	 */
	public static class Enum {

		/** Make no instances. */
		private Enum() {}

		// TODO: Test if attributeE, onclickE required in naming, or if java 8+ compiler can not-too-verbosely figure it out without the special names

		/**
		 * See <a href="https://www.w3schools.com/tags/att_rel.asp">HTML rel Attribute</a>.
		 */
		public static interface Rel<
			E extends Element<E> & Rel<E,V>,
			V extends java.lang.Enum<V> & StringSupplier<RuntimeException>
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
			 */
			default <Ex extends Throwable> E rel(StringSupplier<Ex> rel) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				return rel((rel == null) ? null : rel.get(element.html.serialization, element.html.doctype));
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_rel.asp">HTML rel Attribute</a>.
			 */
			default E rel(V rel) throws IOException {
				return rel((StringSupplier<RuntimeException>)rel);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_rel.asp">HTML rel Attribute</a>.
			 */
			default <Ex extends Throwable> E rel(Supplier<? extends V,Ex> rel) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				return rel((rel== null) ? (V)null : rel.get(element.html.serialization, element.html.doctype));
			}
		}

		/**
		 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/CORS_settings_attributes">The crossorigin attribute: Requesting CORS access to content</a>.
		 */
		public static interface Crossorigin<
			E extends Element<E> & Crossorigin<E,V>,
			V extends java.lang.Enum<V> & StringSupplier<RuntimeException>
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
			 */
			default <Ex extends Throwable> E crossorigin(StringSupplier<Ex> crossorigin) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				return crossorigin((crossorigin == null) ? null : crossorigin.get(element.html.serialization, element.html.doctype));
			}

			/**
			 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/CORS_settings_attributes">The crossorigin attribute: Requesting CORS access to content</a>.
			 */
			default E crossorigin(V crossorigin) throws IOException {
				return crossorigin((StringSupplier<RuntimeException>)crossorigin);
			}

			/**
			 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/CORS_settings_attributes">The crossorigin attribute: Requesting CORS access to content</a>.
			 */
			default <Ex extends Throwable> E crossorigin(Supplier<? extends V,Ex> crossorigin) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				return crossorigin((crossorigin== null) ? (V)null : crossorigin.get(element.html.serialization, element.html.doctype));
			}
		}
	}

	/**
	 * See <a href="https://html.spec.whatwg.org/multipage/common-microsyntaxes.html#signed-integers">2.4.4.1 Signed integers</a>.
	 */
	public static class Integer {

		/** Make no instances. */
		private Integer() {}

		static <E extends Element<E>> E attribute(E element, java.lang.String name, int value) throws IOException {
			element.html.out.write(' ');
			element.html.out.write(name);
			element.html.out.write("=\"");
			// TODO: Encode attribute value?
			element.html.out.write(java.lang.Integer.toString(value));
			element.html.out.write('"');
			return element;
		}

		static <E extends Element<E>> E attribute(E element, java.lang.String name, java.lang.Integer value) throws IOException {
			if(value != null) {
				return attribute(element, name, value.intValue());
			} else {
				return element;
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_height.asp">HTML height Attribute</a>.
		 */
		public static interface Height<E extends Element<E> & Height<E>> {

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
			 */
			default <Ex extends Throwable> E height(Supplier<? extends java.lang.Integer,Ex> pixels) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				return height((pixels == null) ? null : pixels.get(element.html.serialization, element.html.doctype));
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_height.asp">HTML height Attribute</a>.
		 */
		public static interface HeightHtml5Only<E extends Element<E> & HeightHtml5Only<E>> extends Height<E> {

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
				if(element.html.doctype != Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						accessor,
						"Attributes.onlySupportedInHtml5",
						element.html.doctype,
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
				if(element.html.doctype != Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						accessor,
						"Attributes.onlySupportedInHtml5",
						element.html.doctype,
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
			default <Ex extends Throwable> E height(Supplier<? extends java.lang.Integer,Ex> pixels) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				if(element.html.doctype != Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						accessor,
						"Attributes.onlySupportedInHtml5",
						element.html.doctype,
						"height"
					);
				}
				return Height.super.height(pixels);
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_maxlength.asp">HTML maxlength Attribute</a>.
		 */
		public static interface Maxlength<E extends Element<E> & Maxlength<E>> {

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
			 */
			default <Ex extends Throwable> E maxlength(Supplier<? extends java.lang.Integer,Ex> maxlength) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				return maxlength((maxlength == null) ? null : maxlength.get(element.html.serialization, element.html.doctype));
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_size.asp">HTML size Attribute</a>.
		 */
		public static interface Size<E extends Element<E> & Size<E>> {

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
			 */
			default <Ex extends Throwable> E size(Supplier<? extends java.lang.Integer,Ex> size) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				return size((size == null) ? null : size.get(element.html.serialization, element.html.doctype));
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_size.asp">HTML size Attribute</a>.
		 *
		 * @deprecated  Not supported in HTML5.
		 */
		@Deprecated
		public static interface SizeHtml4Only<E extends Element<E> & SizeHtml4Only<E>> extends Size<E> {

			/**
			 * {@inheritDoc}
			 *
			 * @deprecated  Not supported in HTML5.
			 */
			@Deprecated
			@Override
			@Funnel
			default E size(int size) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				if(element.html.doctype == Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						accessor,
						"Attributes.notSupportedInHtml5",
						"size"
					);
				}
				return Size.super.size(size);
			}

			/**
			 * {@inheritDoc}
			 *
			 * @deprecated  Not supported in HTML5.
			 */
			@Deprecated
			@Override
			@Funnel
			default E size(java.lang.Integer size) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				if(element.html.doctype == Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						accessor,
						"Attributes.notSupportedInHtml5",
						"size"
					);
				}
				return Size.super.size(size);
			}

			/**
			 * {@inheritDoc}
			 *
			 * @deprecated  Not supported in HTML5.
			 */
			@Deprecated
			@Override
			default <Ex extends Throwable> E size(Supplier<? extends java.lang.Integer,Ex> size) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				if(element.html.doctype == Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						accessor,
						"Attributes.notSupportedInHtml5",
						"size"
					);
				}
				return Size.super.size(size);
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_global_tabindex.asp">HTML Global tabindex Attribute</a>.
		 * <blockquote>
		 * In HTML5, the tabindex attribute can be used on <b>any</b> HTML element (it will validate on any HTML element. However, it is not necessarily useful).
		 * </blockquote>
		 */
		public static interface Tabindex<E extends Element<E> & Tabindex<E>> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_tabindex.asp">HTML Global tabindex Attribute</a>.
			 * <blockquote>
			 * In HTML5, the tabindex attribute can be used on <b>any</b> HTML element (it will validate on any HTML element. However, it is not necessarily useful).
			 * </blockquote>
			 */
			@Funnel
			default E tabindex(int tabindex) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				if(element.html.doctype != Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						accessor,
						"Attributes.invalidGlobalAttributeForDoctype",
						element.html.doctype,
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
				if(element.html.doctype != Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						accessor,
						"Attributes.invalidGlobalAttributeForDoctype",
						element.html.doctype,
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
			default <Ex extends Throwable> E tabindex(Supplier<? extends java.lang.Integer,Ex> tabindex) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				return tabindex((tabindex == null) ? null : tabindex.get(element.html.serialization, element.html.doctype));
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_global_tabindex.asp">HTML Global tabindex Attribute</a>.
		 * <blockquote>
		 * In HTML 4.01, the tabindex attribute can be used with: &lt;a&gt;, &lt;area&gt;, &lt;button&gt;, &lt;input&gt;, &lt;object&gt;, &lt;select&gt;, and &lt;textarea&gt;.
		 * </blockquote>
		 */
		public static interface TabindexHtml4<E extends Element<E> & TabindexHtml4<E>> extends Tabindex<E> {

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
			 */
			@Override
			default <Ex extends Throwable> E tabindex(Supplier<? extends java.lang.Integer,Ex> tabindex) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				return tabindex((tabindex == null) ? null : tabindex.get(element.html.serialization, element.html.doctype));
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_width.asp">HTML width Attribute</a>.
		 */
		public static interface Width<E extends Element<E> & Width<E>> {

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
			 */
			default <Ex extends Throwable> E width(Supplier<? extends java.lang.Integer,Ex> pixels) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				return width((pixels == null) ? null : pixels.get(element.html.serialization, element.html.doctype));
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_width.asp">HTML width Attribute</a>.
		 */
		// TODO: inheritDoc works here?  Use here and other if so
		public static interface WidthHtml5Only<E extends Element<E> & WidthHtml5Only<E>> extends Width<E> {

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
				if(element.html.doctype != Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						accessor,
						"Attributes.onlySupportedInHtml5",
						element.html.doctype,
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
				if(element.html.doctype != Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						accessor,
						"Attributes.onlySupportedInHtml5",
						element.html.doctype,
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
			default <Ex extends Throwable> E width(Supplier<? extends java.lang.Integer,Ex> pixels) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				if(element.html.doctype != Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						accessor,
						"Attributes.onlySupportedInHtml5",
						element.html.doctype,
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
		 * @param value  If is {@link StringSupplier#NO_VALUE} (by identity), will write empty attribute.
		 */
		static <E extends Element<E>> E attribute(E element, java.lang.String name, MarkupType markupType, java.lang.String value, boolean trim, boolean nullIfEmpty) throws IOException {
			if(value != null) {
				if(value == StringSupplier.NO_VALUE) { // Identity comparison for marker value
					// Empty attribute
					element.html.out.write(' ');
					element.html.out.write(name);
				} else {
					if(trim) value = value.trim();
					if(!nullIfEmpty || !value.isEmpty()) {
						element.html.out.write(' ');
						element.html.out.write(name);
						element.html.out.write("=\"");
						if(markupType == null || markupType == MarkupType.NONE) {
							// Short-cut additional type checks done by Coercion, since we already have a String
							encodeTextInXhtmlAttribute(value, element.html.out);
						} else {
							Coercion.write(value, markupType, textInXhtmlAttributeEncoder, false, element.html.out);
						}
						element.html.out.write('"');
					}
				}
			}
			return element;
		}

		// TODO: Some non-streamable from Text to here?
	}

	/**
	 * Streamable text attributes.
	 */
	public static class Text {

		/** Make no instances. */
		private Text() {}

		static <E extends Element<E>,Ex extends Throwable> E attribute(E element, java.lang.String name, MarkupType markupType, Object value, boolean trim, boolean nullIfEmpty) throws IOException, Ex {
			while(value instanceof Supplier<?,?>) {
				@SuppressWarnings("unchecked")
				Supplier<?,Ex> supplier = (Supplier<?,Ex>)value;
				value = supplier.get(element.html.serialization, element.html.doctype);
			}
			if(value != null) {
				if(value instanceof AttributeWriter<?>) {
					@SuppressWarnings("unchecked")
					AttributeWriter<Ex> writer = (AttributeWriter<Ex>)value;
					element.html.out.write(' '); // TODO: Combine these three writes by passing-in a single combined String?
					element.html.out.write(name);
					element.html.out.write("=\"");
					writer.writeAttribute(
						new MediaWriter(textInXhtmlAttributeEncoder, element.html.out) {
							@Override
							public void close() throws IOException {
								// Do not close underlying writer
							}
						}
					);
					element.html.out.write('"');
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
						element.html.out.write(' '); // TODO: Combine these three writes by passing-in a single combined String?
						element.html.out.write(name);
						element.html.out.write("=\"");
						Coercion.write(value, markupType, textInXhtmlAttributeEncoder, false, element.html.out);
						element.html.out.write('"');
					}
				}
			}
			return element;
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_global_class.asp">HTML Global class Attribute</a>.
		 * <blockquote>
		 * In HTML5, the class attribute can be used on <b>any</b> HTML element (it will validate on any HTML element. However, it is not necessarily useful).
		 * </blockquote>
		 */
		public static interface Class<E extends Element<E> & Class<E>> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_class.asp">HTML Global class Attribute</a>.
			 * <blockquote>
			 * In HTML5, the class attribute can be used on <b>any</b> HTML element (it will validate on any HTML element. However, it is not necessarily useful).
			 * </blockquote>
			 */
			@Funnel
			default E clazz(Object clazz) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "class", MarkupType.NONE, clazz, true, true);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_class.asp">HTML Global class Attribute</a>.
			 * <blockquote>
			 * In HTML5, the class attribute can be used on <b>any</b> HTML element (it will validate on any HTML element. However, it is not necessarily useful).
			 * </blockquote>
			 */
			default <Ex extends Throwable> E clazz(Supplier<?,Ex> clazz) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				return clazz((clazz == null) ? null : clazz.get(element.html.serialization, element.html.doctype));
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_class.asp">HTML Global class Attribute</a>.
			 * <blockquote>
			 * In HTML5, the class attribute can be used on <b>any</b> HTML element (it will validate on any HTML element. However, it is not necessarily useful).
			 * </blockquote>
			 */
			default <Ex extends Throwable> E clazz(AttributeWriter<Ex> clazz) throws IOException, Ex {
				return clazz((Object)clazz);
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_global_class.asp">HTML Global class Attribute</a>.
		 * <blockquote>
		 * In HTML 4.01, the class attribute cannot be used with: &lt;base&gt;, &lt;head&gt;, &lt;html&gt;, &lt;meta&gt;, &lt;param&gt;, &lt;script&gt;, &lt;style&gt;, and &lt;title&gt;.
		 * </blockquote>
		 */
		public static interface ClassNoHtml4<E extends Element<E> & ClassNoHtml4<E>> extends Class<E> {

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
				if(element.html.doctype != Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						accessor,
						"Attributes.invalidGlobalAttributeForDoctype",
						element.html.doctype,
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
			 */
			@Override
			default <Ex extends Throwable> E clazz(Supplier<?,Ex> clazz) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				if(element.html.doctype != Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						accessor,
						"Attributes.invalidGlobalAttributeForDoctype",
						element.html.doctype,
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
			 */
			@Override
			default <Ex extends Throwable> E clazz(AttributeWriter<Ex> clazz) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				if(element.html.doctype != Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						accessor,
						"Attributes.invalidGlobalAttributeForDoctype",
						element.html.doctype,
						"class"
					);
				}
				return Class.super.clazz(clazz);
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_global_id.asp">HTML Global id Attribute</a>.
		 * <blockquote>
		 * In HTML5, the id attribute can be used on <b>any</b> HTML element (it will validate on any HTML element. However, it is not necessarily useful).
		 * </blockquote>
		 */
		public static interface Id<E extends Element<E> & Id<E>> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_id.asp">HTML Global id Attribute</a>.
			 * <blockquote>
			 * In HTML5, the id attribute can be used on <b>any</b> HTML element (it will validate on any HTML element. However, it is not necessarily useful).
			 * </blockquote>
			 */
			@Funnel
			default E id(Object id) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "id", MarkupType.NONE, id, true, true);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_id.asp">HTML Global id Attribute</a>.
			 * <blockquote>
			 * In HTML5, the id attribute can be used on <b>any</b> HTML element (it will validate on any HTML element. However, it is not necessarily useful).
			 * </blockquote>
			 */
			default <Ex extends Throwable> E id(Supplier<?,Ex> id) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				return id((id == null) ? null : id.get(element.html.serialization, element.html.doctype));
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_id.asp">HTML Global id Attribute</a>.
			 * <blockquote>
			 * In HTML5, the id attribute can be used on <b>any</b> HTML element (it will validate on any HTML element. However, it is not necessarily useful).
			 * </blockquote>
			 */
			default <Ex extends Throwable> E id(AttributeWriter<Ex> id) throws IOException, Ex {
				return id((Object)id);
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_global_id.asp">HTML Global id Attribute</a>.
		 * <blockquote>
		 * In HTML 4.01, the id attribute cannot be used with: &lt;base&gt;, &lt;head&gt;, &lt;html&gt;, &lt;meta&gt;, &lt;param&gt;, &lt;script&gt;, &lt;style&gt;, and &lt;title&gt;.
		 * </blockquote>
		 */
		public static interface IdNoHtml4<E extends Element<E> & IdNoHtml4<E>> extends Id<E> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_id.asp">HTML Global id Attribute</a>.
			 * <blockquote>
			 * In HTML 4.01, the id attribute cannot be used with: &lt;base&gt;, &lt;head&gt;, &lt;html&gt;, &lt;meta&gt;, &lt;param&gt;, &lt;script&gt;, &lt;style&gt;, and &lt;title&gt;.
			 * </blockquote>
			 */
			@Override
			@Funnel
			default E id(Object id) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				if(element.html.doctype != Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						accessor,
						"Attributes.invalidGlobalAttributeForDoctype",
						element.html.doctype,
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
			 */
			@Override
			default <Ex extends Throwable> E id(Supplier<?,Ex> id) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				if(element.html.doctype != Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						accessor,
						"Attributes.invalidGlobalAttributeForDoctype",
						element.html.doctype,
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
			 */
			@Override
			default <Ex extends Throwable> E id(AttributeWriter<Ex> id) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				if(element.html.doctype != Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						accessor,
						"Attributes.invalidGlobalAttributeForDoctype",
						element.html.doctype,
						"id"
					);
				}
				return Id.super.id(id);
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_label.asp">HTML label Attribute</a>.
		 */
		public static interface Label<E extends Element<E> & Label<E>> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_label.asp">HTML label Attribute</a>.
			 */
			@Funnel
			default E label(Object label) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "label", MarkupType.TEXT, label, false, false);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_label.asp">HTML label Attribute</a>.
			 */
			default <Ex extends Throwable> E label(Supplier<?,Ex> label) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				return label((label == null) ? null : label.get(element.html.serialization, element.html.doctype));
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_label.asp">HTML label Attribute</a>.
			 */
			default <Ex extends Throwable> E label(AttributeWriter<Ex> label) throws IOException, Ex {
				return label((Object)label);
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_media.asp">HTML media Attribute</a>.
		 */
		// TODO: Any sort of comments allowed in media queries?  MarkupType?
		public static interface Media<E extends Element<E> & Media<E>> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_media.asp">HTML media Attribute</a>.
			 */
			@Funnel
			default E media(Object media) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "media", MarkupType.NONE, media, true, true);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_media.asp">HTML media Attribute</a>.
			 */
			default <Ex extends Throwable> E media(Supplier<?,Ex> media) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				return media((media == null) ? null : media.get(element.html.serialization, element.html.doctype));
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_media.asp">HTML media Attribute</a>.
			 */
			default <Ex extends Throwable> E media(AttributeWriter<Ex> media) throws IOException, Ex {
				return media((Object)media);
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_name.asp">HTML name Attribute</a>.
		 */
		public static interface Name<E extends Element<E> & Name<E>> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_name.asp">HTML name Attribute</a>.
			 */
			@Funnel
			default E name(Object name) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "name", MarkupType.NONE, name, false, true);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_name.asp">HTML name Attribute</a>.
			 */
			default <Ex extends Throwable> E name(Supplier<?,Ex> name) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				return name((name == null) ? null : name.get(element.html.serialization, element.html.doctype));
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_name.asp">HTML name Attribute</a>.
			 */
			default <Ex extends Throwable> E name(AttributeWriter<Ex> name) throws IOException, Ex {
				return name((Object)name);
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_global_style.asp">HTML Global style Attribute</a>.
		 * <blockquote>
		 * In HTML5, the style attribute can be used on <b>any</b> HTML element (it will validate on any HTML element. However, it is not necessarily useful).
		 * </blockquote>
		 */
		// TODO: cssInXmlAttributeEncoder
		public static interface Style<E extends Element<E> & Style<E>> {

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
				return attribute(element, "style", MarkupType.JAVASCRIPT, style, true, true);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_style.asp">HTML Global style Attribute</a>.
			 * <blockquote>
			 * In HTML5, the style attribute can be used on <b>any</b> HTML element (it will validate on any HTML element. However, it is not necessarily useful).
			 * </blockquote>
			 */
			default <Ex extends Throwable> E style(Supplier<?,Ex> style) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				return style((style == null) ? null : style.get(element.html.serialization, element.html.doctype));
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_style.asp">HTML Global style Attribute</a>.
			 * <blockquote>
			 * In HTML5, the style attribute can be used on <b>any</b> HTML element (it will validate on any HTML element. However, it is not necessarily useful).
			 * </blockquote>
			 */
			default <Ex extends Throwable> E style(AttributeWriter<Ex> style) throws IOException, Ex {
				return style((Object)style);
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_global_style.asp">HTML Global style Attribute</a>.
		 * <blockquote>
		 * In HTML 4.01, the style attribute cannot be used with: &lt;base&gt;, &lt;head&gt;, &lt;html&gt;, &lt;meta&gt;, &lt;param&gt;, &lt;script&gt;, &lt;style&gt;, and &lt;title&gt;.
		 * </blockquote>
		 */
		public static interface StyleNoHtml4<E extends Element<E> & StyleNoHtml4<E>> extends Style<E> {

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
				if(element.html.doctype != Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						accessor,
						"Attributes.invalidGlobalAttributeForDoctype",
						element.html.doctype,
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
			 */
			@Override
			default <Ex extends Throwable> E style(Supplier<?,Ex> style) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				if(element.html.doctype != Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						accessor,
						"Attributes.invalidGlobalAttributeForDoctype",
						element.html.doctype,
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
			 */
			@Override
			default <Ex extends Throwable> E style(AttributeWriter<Ex> style) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				if(element.html.doctype != Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						accessor,
						"Attributes.invalidGlobalAttributeForDoctype",
						element.html.doctype,
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
		public static interface Title<E extends Element<E> & Title<E>> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_title.asp">HTML Global title Attribute</a>.
			 * <blockquote>
			 * In HTML5, the title attribute can be used on <b>any</b> HTML element (it will validate on any HTML element. However, it is not necessarily useful).
			 * </blockquote>
			 */
			@Funnel
			default E title(Object title) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "title", MarkupType.TEXT, title, true, true);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_title.asp">HTML Global title Attribute</a>.
			 * <blockquote>
			 * In HTML5, the title attribute can be used on <b>any</b> HTML element (it will validate on any HTML element. However, it is not necessarily useful).
			 * </blockquote>
			 */
			default <Ex extends Throwable> E title(Supplier<?,Ex> title) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				return title((title == null) ? null : title.get(element.html.serialization, element.html.doctype));
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_title.asp">HTML Global title Attribute</a>.
			 * <blockquote>
			 * In HTML5, the title attribute can be used on <b>any</b> HTML element (it will validate on any HTML element. However, it is not necessarily useful).
			 * </blockquote>
			 */
			default <Ex extends Throwable> E title(AttributeWriter<Ex> title) throws IOException, Ex {
				return title((Object)title);
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_global_title.asp">HTML Global title Attribute</a>.
		 * <blockquote>
		 * In HTML 4.01, the title attribute cannot be used with: &lt;base&gt;, &lt;head&gt;, &lt;html&gt;, &lt;meta&gt;, &lt;param&gt;, &lt;script&gt;, &lt;style&gt;, and &lt;title&gt;.
		 * </blockquote>
		 */
		public static interface TitleNoHtml4<E extends Element<E> & TitleNoHtml4<E>> extends Title<E> {

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
				if(element.html.doctype != Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						accessor,
						"Attributes.invalidGlobalAttributeForDoctype",
						element.html.doctype,
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
			 */
			@Override
			default <Ex extends Throwable> E title(Supplier<?,Ex> title) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				if(element.html.doctype != Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						accessor,
						"Attributes.invalidGlobalAttributeForDoctype",
						element.html.doctype,
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
			 */
			@Override
			default <Ex extends Throwable> E title(AttributeWriter<Ex> title) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				if(element.html.doctype != Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						accessor,
						"Attributes.invalidGlobalAttributeForDoctype",
						element.html.doctype,
						"title"
					);
				}
				return Title.super.title(title);
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_value.asp">HTML value Attribute</a>.
		 */
		public static interface Value<E extends Element<E> & Value<E>> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_value.asp">HTML value Attribute</a>.
			 */
			@Funnel
			default E value(Object value) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "value", MarkupType.NONE, value, false, true);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_value.asp">HTML value Attribute</a>.
			 */
			default <Ex extends Throwable> E value(Supplier<?,Ex> value) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				return value((value == null) ? null : value.get(element.html.serialization, element.html.doctype));
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_value.asp">HTML value Attribute</a>.
			 */
			default <Ex extends Throwable> E value(AttributeWriter<Ex> value) throws IOException, Ex {
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

		static <E extends Element<E>> E attribute(E element, java.lang.String name, java.lang.String url) throws IOException {
			if(url != null) {
				element.html.out.write(' ');
				element.html.out.write(name);
				element.html.out.write("=\"");
				// TODO: UrlInXhtmlAttributeEncoder once RFC 3987 supported
				textInXhtmlAttributeEncoder.write(url, element.html.out);
				element.html.out.write('"');
			}
			return element;
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_href.asp">HTML href Attribute</a>.
		 */
		public static interface Href<E extends Element<E> & Href<E>> {

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
			 */
			default <Ex extends Throwable> E href(Supplier<? extends java.lang.String,Ex> href) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				return href((href == null) ? null : href.get(element.html.serialization, element.html.doctype));
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_src.asp">HTML src Attribute</a>.
		 */
		public static interface Src<E extends Element<E> & Src<E>> {

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
			 */
			// TODO: More bounds like this when disambiguation between multiple types unnecessary?
			default <Ex extends Throwable> E src(Supplier<? extends java.lang.String,Ex> src) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				return src((src == null) ? null : src.get(element.html.serialization, element.html.doctype));
			}
		}
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/ref_standardattributes.asp">HTML Global attributes</a>.
	 */
	public static interface Global<E extends Element<E> & Global<E>> extends
		Text.Class<E>,
		Text.Id<E>,
		Text.Style<E>,
		Integer.Tabindex<E>,
		Text.Title<E>
	{
		// No methods, just adding defaults for all mouse event types
	}
}
