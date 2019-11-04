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
import static com.aoindustries.encoding.TextInXhtmlAttributeEncoder.textInXhtmlAttributeEncoder;
import static com.aoindustries.html.ApplicationResources.accessor;
import com.aoindustries.lang.LocalizedIllegalArgumentException;
import com.aoindustries.util.WrappedException;
import com.aoindustries.util.i18n.MarkupType;
import java.io.IOException;

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
	 * See <a href="https://html.spec.whatwg.org/multipage/common-microsyntaxes.html#boolean-attributes">2.4.2 Boolean attributes</a>.
	 */
	public static class Boolean {

		/** Make no instances. */
		private Boolean() {}

		static <E extends Element<E>> E attribute(E element, String name, boolean value) throws IOException {
			if(value) {
				// TODO: Validate attribute name?
				element.html.out.write(' ');
				element.html.out.write(name);
				if(element.html.serialization == Serialization.XML) {
					element.html.out.write("=\"");
					// TODO: Encode attribute value?
					element.html.out.write(name);
					element.html.out.write('"');
				} else {
					assert element.html.serialization == Serialization.SGML;
				}
			}
			return element;
		}

		static <E extends Element<E>> E attribute(E element, String name, java.lang.Boolean value) throws IOException {
			if(value) {
				return attribute(element, name, value.booleanValue());
			} else {
				return element;
			}
		}

		static <E extends Element<E>,Ex extends Throwable> E attributeE(E element, String name, AttributeSupplierE<? extends java.lang.Boolean,Ex> value) throws IOException, Ex {
			return attribute(element, name, (value == null) ? null : value.get());
		}

		static <E extends Element<E>> E attribute(E element, String name, AttributeSupplier<? extends java.lang.Boolean> value) throws IOException {
			return attributeE(element, name, value);
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_async.asp">HTML async Attribute</a>.
		 */
		public static interface Async<E extends Element<E> & Async<E>> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_async.asp">HTML async Attribute</a>.
			 */
			@SuppressWarnings("unchecked")
			default E async(boolean async) throws IOException {
				return attribute((E)this, "async", async);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_async.asp">HTML async Attribute</a>.
			 */
			@SuppressWarnings("unchecked")
			default E async(java.lang.Boolean async) throws IOException {
				return attribute((E)this, "async", async);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_async.asp">HTML async Attribute</a>.
			 */
			@SuppressWarnings("unchecked")
			default <Ex extends Throwable> E asyncE(AttributeSupplierE<? extends java.lang.Boolean,Ex> async) throws IOException, Ex {
				return attributeE((E)this, "async", async);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_async.asp">HTML async Attribute</a>.
			 */
			@SuppressWarnings("unchecked")
			default E async(AttributeSupplier<? extends java.lang.Boolean> async) throws IOException {
				return attribute((E)this, "async", async);
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_checked.asp">HTML checked Attribute</a>.
		 */
		public static interface Checked<E extends Element<E> & Checked<E>> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_checked.asp">HTML checked Attribute</a>.
			 */
			@SuppressWarnings("unchecked")
			default E checked(boolean checked) throws IOException {
				return attribute((E)this, "checked", checked);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_checked.asp">HTML checked Attribute</a>.
			 */
			@SuppressWarnings("unchecked")
			default E checked(java.lang.Boolean checked) throws IOException {
				return attribute((E)this, "checked", checked);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_checked.asp">HTML checked Attribute</a>.
			 */
			@SuppressWarnings("unchecked")
			default <Ex extends Throwable> E checkedE(AttributeSupplierE<? extends java.lang.Boolean,Ex> checked) throws IOException, Ex {
				return attributeE((E)this, "checked", checked);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_checked.asp">HTML checked Attribute</a>.
			 */
			@SuppressWarnings("unchecked")
			default E checked(AttributeSupplier<? extends java.lang.Boolean> checked) throws IOException {
				return attribute((E)this, "checked", checked);
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_defer.asp">HTML defer Attribute</a>.
		 */
		public static interface Defer<E extends Element<E> & Defer<E>> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_defer.asp">HTML defer Attribute</a>.
			 */
			@SuppressWarnings("unchecked")
			default E defer(boolean defer) throws IOException {
				return attribute((E)this, "defer", defer);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_defer.asp">HTML defer Attribute</a>.
			 */
			@SuppressWarnings("unchecked")
			default E defer(java.lang.Boolean defer) throws IOException {
				return attribute((E)this, "defer", defer);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_defer.asp">HTML defer Attribute</a>.
			 */
			@SuppressWarnings("unchecked")
			default <Ex extends Throwable> E deferE(AttributeSupplierE<? extends java.lang.Boolean,Ex> defer) throws IOException, Ex {
				return attributeE((E)this, "defer", defer);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_defer.asp">HTML defer Attribute</a>.
			 */
			@SuppressWarnings("unchecked")
			default E defer(AttributeSupplier<? extends java.lang.Boolean> defer) throws IOException {
				return attribute((E)this, "defer", defer);
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_disabled.asp">HTML disabled Attribute</a>.
		 */
		public static interface Disabled<E extends Element<E> & Disabled<E>> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_disabled.asp">HTML disabled Attribute</a>.
			 */
			@SuppressWarnings("unchecked")
			default E disabled(boolean disabled) throws IOException {
				return attribute((E)this, "disabled", disabled);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_disabled.asp">HTML disabled Attribute</a>.
			 */
			@SuppressWarnings("unchecked")
			default E disabled(java.lang.Boolean disabled) throws IOException {
				return attribute((E)this, "disabled", disabled);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_disabled.asp">HTML disabled Attribute</a>.
			 */
			@SuppressWarnings("unchecked")
			default <Ex extends Throwable> E disabledE(AttributeSupplierE<? extends java.lang.Boolean,Ex> disabled) throws IOException, Ex {
				return attributeE((E)this, "disabled", disabled);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_disabled.asp">HTML disabled Attribute</a>.
			 */
			@SuppressWarnings("unchecked")
			default E disabled(AttributeSupplier<? extends java.lang.Boolean> disabled) throws IOException {
				return attribute((E)this, "disabled", disabled);
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
			@SuppressWarnings("unchecked")
			default E noshade(boolean noshade) throws IOException {
				E element = (E)this;
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
			@SuppressWarnings("unchecked")
			default E noshade(java.lang.Boolean noshade) throws IOException {
				E element = (E)this;
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
			@SuppressWarnings("unchecked")
			default <Ex extends Throwable> E noshadeE(AttributeSupplierE<? extends java.lang.Boolean,Ex> noshade) throws IOException, Ex {
				E element = (E)this;
				if(element.html.doctype == Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						accessor,
						"Attributes.notSupportedInHtml5",
						"noshade"
					);
				}
				return attributeE(element, "noshade", noshade);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_hr_noshade.asp">HTML hr noshade Attribute</a>.
			 *
			 * @deprecated  The noshade attribute of <code>&lt;hr&gt;</code> is not supported in HTML5. Use CSS instead.
			 */
			@Deprecated
			@SuppressWarnings("unchecked")
			default E noshade(AttributeSupplier<? extends java.lang.Boolean> noshade) throws IOException {
				E element = (E)this;
				if(element.html.doctype == Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						accessor,
						"Attributes.notSupportedInHtml5",
						"noshade"
					);
				}
				return attribute(element, "noshade", noshade);
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_readonly.asp">HTML readonly Attribute</a>.
		 */
		public static interface Readonly<E extends Element<E> & Readonly<E>> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_readonly.asp">HTML readonly Attribute</a>.
			 */
			@SuppressWarnings("unchecked")
			default E readonly(boolean readonly) throws IOException {
				return attribute((E)this, "readonly", readonly);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_readonly.asp">HTML readonly Attribute</a>.
			 */
			@SuppressWarnings("unchecked")
			default E readonly(java.lang.Boolean readonly) throws IOException {
				return attribute((E)this, "readonly", readonly);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_readonly.asp">HTML readonly Attribute</a>.
			 */
			@SuppressWarnings("unchecked")
			default <Ex extends Throwable> E readonlyE(AttributeSupplierE<? extends java.lang.Boolean,Ex> readonly) throws IOException, Ex {
				return attributeE((E)this, "readonly", readonly);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_readonly.asp">HTML readonly Attribute</a>.
			 */
			@SuppressWarnings("unchecked")
			default E readonly(AttributeSupplier<? extends java.lang.Boolean> readonly) throws IOException {
				return attribute((E)this, "readonly", readonly);
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_selected.asp">HTML selected Attribute</a>.
		 */
		public static interface Selected<E extends Element<E> & Selected<E>> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_selected.asp">HTML selected Attribute</a>.
			 */
			@SuppressWarnings("unchecked")
			default E selected(boolean selected) throws IOException {
				return attribute((E)this, "selected", selected);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_selected.asp">HTML selected Attribute</a>.
			 */
			@SuppressWarnings("unchecked")
			default E selected(java.lang.Boolean selected) throws IOException {
				return attribute((E)this, "selected", selected);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_selected.asp">HTML selected Attribute</a>.
			 */
			@SuppressWarnings("unchecked")
			default <Ex extends Throwable> E selectedE(AttributeSupplierE<? extends java.lang.Boolean,Ex> selected) throws IOException, Ex {
				return attributeE((E)this, "selected", selected);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_selected.asp">HTML selected Attribute</a>.
			 */
			@SuppressWarnings("unchecked")
			default E selected(AttributeSupplier<? extends java.lang.Boolean> selected) throws IOException {
				return attribute((E)this, "selected", selected);
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

		static <E extends Element<E>> E attribute(E element, String name, int pixels) throws IOException {
			return Integer.attribute(element, name, pixels);
		}

		static <E extends Element<E>> E attribute(E element, String name, java.lang.Integer pixels) throws IOException {
			return Integer.attribute(element, name, pixels);
		}

		static <E extends Element<E>,Ex extends Throwable> E attributeE(E element, String name, AttributeSupplierE<? extends java.lang.Integer,Ex> pixels) throws IOException, Ex {
			return Integer.attributeE(element, name, pixels);
		}

		static <E extends Element<E>> E attribute(E element, String name, AttributeSupplier<? extends java.lang.Integer> pixels) throws IOException {
			return Integer.attribute(element, name, pixels);
		}

		/**
		 * @deprecated  In HTML 4.01, the value could be defined in pixels or in % of the containing element. In HTML5, the value must be in pixels.
		 */
		@Deprecated
		static <E extends Element<E>> E attributePP(E element, String name, Object pixelsOrPercent) throws IOException {
			if(pixelsOrPercent != null) {
				if(pixelsOrPercent instanceof java.lang.Integer) return attribute(element, name, (java.lang.Integer)pixelsOrPercent);
				if(pixelsOrPercent instanceof AttributeSupplier<?>) return attributePP(element, name, (AttributeSupplier<?>)pixelsOrPercent);
				if(pixelsOrPercent instanceof AttributeSupplierE<?,?>) {
					try {
						return attributePPE(element, name, (AttributeSupplierE<?,?>)pixelsOrPercent);
					} catch(Error|RuntimeException|IOException e) {
						throw e;
					} catch(Throwable t) {
						throw new WrappedException(t);
					}
				}
				pixelsOrPercent = Coercion.trimNullIfEmpty(pixelsOrPercent);
				if(pixelsOrPercent != null) {
					// TODO: Validate attribute name?
					element.html.out.write(' ');
					element.html.out.write(name);
					element.html.out.write("=\"");
					Coercion.write(pixelsOrPercent, textInXhtmlAttributeEncoder, element.html.out);
					element.html.out.write('"');
				}
			}
			return element;
		}

		/**
		 * @deprecated  In HTML 4.01, the value could be defined in pixels or in % of the containing element. In HTML5, the value must be in pixels.
		 */
		@Deprecated
		static <E extends Element<E>,Ex extends Throwable> E attributePPE(E element, String name, AttributeSupplierE<?,Ex> pixelsOrPercent) throws IOException, Ex {
			return attributePP(element, name, (pixelsOrPercent == null) ? null : pixelsOrPercent.get());
		}

		/**
		 * @deprecated  In HTML 4.01, the value could be defined in pixels or in % of the containing element. In HTML5, the value must be in pixels.
		 */
		@Deprecated
		static <E extends Element<E>> E attributePP(E element, String name, AttributeSupplier<?> pixelsOrPercent) throws IOException {
			return attributePPE(element, name, pixelsOrPercent);
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_width.asp">HTML width Attribute</a>.
		 */
		public static interface Width<E extends Element<E> & Width<E>> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_width.asp">HTML width Attribute</a>.
			 */
			@SuppressWarnings("unchecked")
			default E width(int pixels) throws IOException {
				return attribute((E)this, "width", pixels);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_width.asp">HTML width Attribute</a>.
			 */
			@SuppressWarnings("unchecked")
			default E width(java.lang.Integer pixels) throws IOException {
				return attribute((E)this, "width", pixels);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_width.asp">HTML width Attribute</a>.
			 */
			@SuppressWarnings("unchecked")
			default <Ex extends Throwable> E widthE(AttributeSupplierE<? extends java.lang.Integer,Ex> pixels) throws IOException, Ex {
				return attributeE((E)this, "width", pixels);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_width.asp">HTML width Attribute</a>.
			 */
			@SuppressWarnings("unchecked")
			default E width(AttributeSupplier<? extends java.lang.Integer> pixels) throws IOException {
				return attribute((E)this, "width", pixels);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_width.asp">HTML width Attribute</a>.
			 *
			 * @deprecated  In HTML 4.01, the width could be defined in pixels or in % of the containing element. In HTML5, the value must be in pixels.
			 */
			@Deprecated
			@SuppressWarnings("unchecked")
			default E widthPP(Object pixelsOrPercent) throws IOException {
				return attributePP((E)this, "width", pixelsOrPercent);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_width.asp">HTML width Attribute</a>.
			 *
			 * @deprecated  In HTML 4.01, the width could be defined in pixels or in % of the containing element. In HTML5, the value must be in pixels.
			 */
			@Deprecated
			@SuppressWarnings("unchecked")
			default <Ex extends Throwable> E widthPPE(AttributeSupplierE<?,Ex> pixelsOrPercent) throws IOException, Ex {
				return attributePPE((E)this, "width", pixelsOrPercent);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_width.asp">HTML width Attribute</a>.
			 *
			 * @deprecated  In HTML 4.01, the width could be defined in pixels or in % of the containing element. In HTML5, the value must be in pixels.
			 */
			@Deprecated
			@SuppressWarnings("unchecked")
			default E widthPP(AttributeSupplier<?> pixelsOrPercent) throws IOException {
				return attributePP((E)this, "width", pixelsOrPercent);
			}
		}
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/ref_eventattributes.asp">HTML Event Attributes</a>.
	 */
	public static class Event {

		/** Make no instances. */
		private Event() {}

		static <E extends Element<E>> E attribute(E element, String name, Object script) throws IOException {
			if(script != null) {
				if(script instanceof AttributeSupplier<?>) return attribute(element, name, (AttributeSupplier<?>)script);
				if(script instanceof AttributeSupplierE<?,?>) {
					try {
						return attributeE(element, name, (AttributeSupplierE<?,?>)script);
					} catch(Error|RuntimeException|IOException e) {
						throw e;
					} catch(Throwable t) {
						throw new WrappedException(t);
					}
				}
				if(script instanceof AttributeWriter) return attribute(element, name, (AttributeWriter)script);
				if(script instanceof AttributeWriterE<?>) {
					try {
						return attributeE(element, name, (AttributeWriterE<?>)script);
					} catch(Error|RuntimeException|IOException e) {
						throw e;
					} catch(Throwable t) {
						throw new WrappedException(t);
					}
				}
				// TODO: Validate attribute name?
				element.html.out.write(' ');
				element.html.out.write(name);
				element.html.out.write("=\"");
				// TODO: Find more places where we can do javascript markups (ao-taglib...)
				Coercion.write(script, MarkupType.JAVASCRIPT, javaScriptInXhtmlAttributeEncoder, false, element.html.out);
				element.html.out.write('"');
			}
			return element;
		}

		static <E extends Element<E>,Ex extends Throwable> E attributeE(E element, String name, AttributeSupplierE<?,Ex> script) throws IOException, Ex {
			return attribute(element, name, (script == null) ? null : script.get());
		}

		static <E extends Element<E>> E attribute(E element, String name, AttributeSupplier<?> script) throws IOException {
			return attributeE(element, name, script);
		}

		static <E extends Element<E>> MediaWriter attribute(E element, String name) throws IOException {
			// TODO: Validate attribute name?
			element.html.out.write(' ');
			element.html.out.write(name);
			element.html.out.write("=\"");
			return new MediaWriter(javaScriptInXhtmlAttributeEncoder, element.html.out) {
				@Override
				public void close() throws IOException {
					element.html.out.write('"');
				}
			};
		}

		static <E extends Element<E>,Ex extends Throwable> E attributeE(E element, String name, AttributeWriterE<Ex> script) throws IOException, Ex {
			if(script != null) {
				try (MediaWriter out = attribute(element, name)) {
					script.writeAttribute(out);
				}
			}
			return element;
		}

		static <E extends Element<E>> E attribute(E element, String name, AttributeWriter script) throws IOException {
			return attributeE(element, name, script);
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
				@SuppressWarnings("unchecked")
				default E onclick(Object onclick) throws IOException {
					return attribute((E)this, "onclick", onclick);
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onclick.asp">HTML onclick Event Attribute</a>.
				 */
				@SuppressWarnings("unchecked")
				default <Ex extends Throwable> E onclickE(AttributeSupplierE<?,Ex> onclick) throws IOException, Ex {
					return attributeE((E)this, "onclick", onclick);
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onclick.asp">HTML onclick Event Attribute</a>.
				 */
				@SuppressWarnings("unchecked")
				default E onclick(AttributeSupplier<?> onclick) throws IOException {
					return attribute((E)this, "onclick", onclick);
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onclick.asp">HTML onclick Event Attribute</a>.
				 */
				@SuppressWarnings("unchecked")
				default MediaWriter onclick() throws IOException {
					return attribute((E)this, "onclick");
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onclick.asp">HTML onclick Event Attribute</a>.
				 */
				@SuppressWarnings("unchecked")
				default <Ex extends Throwable> E onclickE(AttributeWriterE<Ex> onclick) throws IOException, Ex {
					return attributeE((E)this, "onclick", onclick);
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onclick.asp">HTML onclick Event Attribute</a>.
				 */
				@SuppressWarnings("unchecked")
				default E onclick(AttributeWriter onclick) throws IOException {
					return attribute((E)this, "onclick", onclick);
				}
			}
		}
	}

	/**
	 * See <a href="https://html.spec.whatwg.org/multipage/common-microsyntaxes.html#signed-integers">2.4.4.1 Signed integers</a>.
	 */
	public static class Integer {

		/** Make no instances. */
		private Integer() {}

		static <E extends Element<E>> E attribute(E element, String name, int value) throws IOException {
			// TODO: Validate attribute name?
			element.html.out.write(' ');
			element.html.out.write(name);
			element.html.out.write("=\"");
			// TODO: Encode attribute value?
			element.html.out.write(java.lang.Integer.toString(value));
			element.html.out.write('"');
			return element;
		}

		static <E extends Element<E>> E attribute(E element, String name, java.lang.Integer value) throws IOException {
			if(value != null) {
				return attribute(element, name, value.intValue());
			} else {
				return element;
			}
		}

		static <E extends Element<E>,Ex extends Throwable> E attributeE(E element, String name, AttributeSupplierE<? extends java.lang.Integer,Ex> value) throws IOException, Ex {
			return attribute(element, name, (value == null) ? null : value.get());
		}

		static <E extends Element<E>> E attribute(E element, String name, AttributeSupplier<? extends java.lang.Integer> value) throws IOException {
			return attributeE(element, name, value);
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_maxlength.asp">HTML maxlength Attribute</a>.
		 */
		public static interface Maxlength<E extends Element<E> & Maxlength<E>> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_maxlength.asp">HTML maxlength Attribute</a>.
			 */
			@SuppressWarnings("unchecked")
			default E maxlength(int maxlength) throws IOException {
				return attribute((E)this, "maxlength", maxlength);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_maxlength.asp">HTML maxlength Attribute</a>.
			 */
			@SuppressWarnings("unchecked")
			default E maxlength(java.lang.Integer maxlength) throws IOException {
				return attribute((E)this, "maxlength", maxlength);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_maxlength.asp">HTML maxlength Attribute</a>.
			 */
			@SuppressWarnings("unchecked")
			default <Ex extends Throwable> E maxlengthE(AttributeSupplierE<? extends java.lang.Integer,Ex> maxlength) throws IOException, Ex {
				return attributeE((E)this, "maxlength", maxlength);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_maxlength.asp">HTML maxlength Attribute</a>.
			 */
			@SuppressWarnings("unchecked")
			default E maxlength(AttributeSupplier<? extends java.lang.Integer> maxlength) throws IOException {
				return attribute((E)this, "maxlength", maxlength);
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_size.asp">HTML size Attribute</a>.
		 */
		public static interface Size<E extends Element<E> & Size<E>> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_size.asp">HTML size Attribute</a>.
			 */
			@SuppressWarnings("unchecked")
			default E size(int size) throws IOException {
				return attribute((E)this, "size", size);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_size.asp">HTML size Attribute</a>.
			 */
			@SuppressWarnings("unchecked")
			default E size(java.lang.Integer size) throws IOException {
				return attribute((E)this, "size", size);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_size.asp">HTML size Attribute</a>.
			 */
			@SuppressWarnings("unchecked")
			default <Ex extends Throwable> E sizeE(AttributeSupplierE<? extends java.lang.Integer,Ex> size) throws IOException, Ex {
				return attributeE((E)this, "size", size);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_size.asp">HTML size Attribute</a>.
			 */
			@SuppressWarnings("unchecked")
			default E size(AttributeSupplier<? extends java.lang.Integer> size) throws IOException {
				return attribute((E)this, "size", size);
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
			@SuppressWarnings("unchecked")
			default E size(int size) throws IOException {
				E element = (E)this;
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
			@SuppressWarnings("unchecked")
			default E size(java.lang.Integer size) throws IOException {
				E element = (E)this;
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
			@SuppressWarnings("unchecked")
			default <Ex extends Throwable> E sizeE(AttributeSupplierE<? extends java.lang.Integer,Ex> size) throws IOException, Ex {
				E element = (E)this;
				if(element.html.doctype == Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						accessor,
						"Attributes.notSupportedInHtml5",
						"size"
					);
				}
				return Size.super.sizeE(size);
			}

			/**
			 * {@inheritDoc}
			 *
			 * @deprecated  Not supported in HTML5.
			 */
			@Deprecated
			@Override
			@SuppressWarnings("unchecked")
			default E size(AttributeSupplier<? extends java.lang.Integer> size) throws IOException {
				E element = (E)this;
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
			@SuppressWarnings("unchecked")
			default E tabindex(int tabindex) throws IOException {
				E element = (E)this;
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
			@SuppressWarnings("unchecked")
			default E tabindex(java.lang.Integer tabindex) throws IOException {
				E element = (E)this;
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
			@SuppressWarnings("unchecked")
			default <Ex extends Throwable> E tabindexE(AttributeSupplierE<? extends java.lang.Integer,Ex> tabindex) throws IOException, Ex {
				E element = (E)this;
				if(element.html.doctype != Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						accessor,
						"Attributes.invalidGlobalAttributeForDoctype",
						element.html.doctype,
						"tabindex"
					);
				}
				return attributeE(element, "tabindex", tabindex);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_tabindex.asp">HTML Global tabindex Attribute</a>.
			 * <blockquote>
			 * In HTML5, the tabindex attribute can be used on <b>any</b> HTML element (it will validate on any HTML element. However, it is not necessarily useful).
			 * </blockquote>
			 */
			@SuppressWarnings("unchecked")
			default E tabindex(AttributeSupplier<? extends java.lang.Integer> tabindex) throws IOException {
				E element = (E)this;
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
			@SuppressWarnings("unchecked")
			default E tabindex(int tabindex) throws IOException {
				return attribute((E)this, "tabindex", tabindex);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_tabindex.asp">HTML Global tabindex Attribute</a>.
			 * <blockquote>
			 * In HTML 4.01, the tabindex attribute can be used with: &lt;a&gt;, &lt;area&gt;, &lt;button&gt;, &lt;input&gt;, &lt;object&gt;, &lt;select&gt;, and &lt;textarea&gt;.
			 * </blockquote>
			 */
			@Override
			@SuppressWarnings("unchecked")
			default E tabindex(java.lang.Integer tabindex) throws IOException {
				return attribute((E)this, "tabindex", tabindex);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_tabindex.asp">HTML Global tabindex Attribute</a>.
			 * <blockquote>
			 * In HTML 4.01, the tabindex attribute can be used with: &lt;a&gt;, &lt;area&gt;, &lt;button&gt;, &lt;input&gt;, &lt;object&gt;, &lt;select&gt;, and &lt;textarea&gt;.
			 * </blockquote>
			 */
			@Override
			@SuppressWarnings("unchecked")
			default <Ex extends Throwable> E tabindexE(AttributeSupplierE<? extends java.lang.Integer,Ex> tabindex) throws IOException, Ex {
				return attributeE((E)this, "tabindex", tabindex);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_tabindex.asp">HTML Global tabindex Attribute</a>.
			 * <blockquote>
			 * In HTML 4.01, the tabindex attribute can be used with: &lt;a&gt;, &lt;area&gt;, &lt;button&gt;, &lt;input&gt;, &lt;object&gt;, &lt;select&gt;, and &lt;textarea&gt;.
			 * </blockquote>
			 */
			@Override
			@SuppressWarnings("unchecked")
			default E tabindex(AttributeSupplier<? extends java.lang.Integer> tabindex) throws IOException {
				return attribute((E)this, "tabindex", tabindex);
			}
		}
	}

	/**
	 * Text attributes.
	 */
	public static class Text {

		/** Make no instances. */
		private Text() {}

		static <E extends Element<E>> E attribute(E element, String name, MarkupType markupType, Object value, boolean trim, boolean nullIfEmpty) throws IOException {
			if(value != null) {
				if(value instanceof AttributeSupplier<?>) return attribute(element, name, markupType, (AttributeSupplier<?>)value, trim, nullIfEmpty);
				if(value instanceof AttributeSupplierE<?,?>) {
					try {
						return attributeE(element, name, markupType, (AttributeSupplierE<?,?>)value, trim, nullIfEmpty);
					} catch(Error|RuntimeException|IOException e) {
						throw e;
					} catch(Throwable t) {
						throw new WrappedException(t);
					}
				}
				if(value instanceof AttributeWriter) return attribute(element, name, (AttributeWriter)value);
				if(value instanceof AttributeWriterE<?>) {
					try {
						return attributeE(element, name, (AttributeWriterE<?>)value);
					} catch(Error|RuntimeException|IOException e) {
						throw e;
					} catch(Throwable t) {
						throw new WrappedException(t);
					}
				}
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
					// TODO: Validate attribute name?
					element.html.out.write(' '); // TODO: Combine these three writes by passing-in a single combined String?
					element.html.out.write(name);
					element.html.out.write("=\"");
					Coercion.write(value, markupType, textInXhtmlAttributeEncoder, false, element.html.out);
					element.html.out.write('"');
				}
			}
			return element;
		}

		static <E extends Element<E>,Ex extends Throwable> E attributeE(E element, String name, MarkupType markupType, AttributeSupplierE<?,Ex> value, boolean trim, boolean nullIfEmpty) throws IOException, Ex {
			return attribute(element, name, markupType, (value == null) ? null : value.get(), trim, nullIfEmpty);
		}

		static <E extends Element<E>> E attribute(E element, String name, MarkupType markupType, AttributeSupplier<?> value, boolean trim, boolean nullIfEmpty) throws IOException {
			return attributeE(element, name, markupType, value, trim, nullIfEmpty);
		}

		static <E extends Element<E>> MediaWriter attribute(E element, String name) throws IOException {
			// TODO: Validate attribute name?
			element.html.out.write(' ');
			element.html.out.write(name);
			element.html.out.write("=\"");
			return new MediaWriter(textInXhtmlAttributeEncoder, element.html.out) {
				@Override
				public void close() throws IOException {
					element.html.out.write('"');
				}
			};
		}

		static <E extends Element<E>,Ex extends Throwable> E attributeE(E element, String name, AttributeWriterE<Ex> value) throws IOException, Ex {
			if(value != null) {
				try (MediaWriter out = attribute(element, name)) {
					value.writeAttribute(out);
				}
			}
			return element;
		}

		static <E extends Element<E>> E attribute(E element, String name, AttributeWriter value) throws IOException {
			return attributeE(element, name, value);
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
			@SuppressWarnings("unchecked")
			default E clazz(Object clazz) throws IOException {
				return attribute((E)this, "class", MarkupType.NONE, clazz, true, true);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_class.asp">HTML Global class Attribute</a>.
			 * <blockquote>
			 * In HTML5, the class attribute can be used on <b>any</b> HTML element (it will validate on any HTML element. However, it is not necessarily useful).
			 * </blockquote>
			 */
			@SuppressWarnings("unchecked")
			default <Ex extends Throwable> E clazzE(AttributeSupplierE<?,Ex> clazz) throws IOException, Ex {
				return attributeE((E)this, "class", MarkupType.NONE, clazz, true, true);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_class.asp">HTML Global class Attribute</a>.
			 * <blockquote>
			 * In HTML5, the class attribute can be used on <b>any</b> HTML element (it will validate on any HTML element. However, it is not necessarily useful).
			 * </blockquote>
			 */
			@SuppressWarnings("unchecked")
			default E clazz(AttributeSupplier<?> clazz) throws IOException {
				return attribute((E)this, "class", MarkupType.NONE, clazz, true, true);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_class.asp">HTML Global class Attribute</a>.
			 * <blockquote>
			 * In HTML5, the class attribute can be used on <b>any</b> HTML element (it will validate on any HTML element. However, it is not necessarily useful).
			 * </blockquote>
			 */
			@SuppressWarnings("unchecked")
			default MediaWriter clazz() throws IOException {
				return attribute((E)this, "class");
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_class.asp">HTML Global class Attribute</a>.
			 * <blockquote>
			 * In HTML5, the class attribute can be used on <b>any</b> HTML element (it will validate on any HTML element. However, it is not necessarily useful).
			 * </blockquote>
			 */
			@SuppressWarnings("unchecked")
			default <Ex extends Throwable> E clazzE(AttributeWriterE<Ex> clazz) throws IOException, Ex {
				return attributeE((E)this, "class", clazz);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_class.asp">HTML Global class Attribute</a>.
			 * <blockquote>
			 * In HTML5, the class attribute can be used on <b>any</b> HTML element (it will validate on any HTML element. However, it is not necessarily useful).
			 * </blockquote>
			 */
			@SuppressWarnings("unchecked")
			default E clazz(AttributeWriter clazz) throws IOException {
				return attribute((E)this, "class", clazz);
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
			@SuppressWarnings("unchecked")
			default E clazz(Object clazz) throws IOException {
				E element = (E)this;
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
			@SuppressWarnings("unchecked")
			default <Ex extends Throwable> E clazzE(AttributeSupplierE<?,Ex> clazz) throws IOException, Ex {
				E element = (E)this;
				if(element.html.doctype != Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						accessor,
						"Attributes.invalidGlobalAttributeForDoctype",
						element.html.doctype,
						"class"
					);
				}
				return Class.super.clazzE(clazz);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_class.asp">HTML Global class Attribute</a>.
			 * <blockquote>
			 * In HTML 4.01, the class attribute cannot be used with: &lt;base&gt;, &lt;head&gt;, &lt;html&gt;, &lt;meta&gt;, &lt;param&gt;, &lt;script&gt;, &lt;style&gt;, and &lt;title&gt;.
			 * </blockquote>
			 */
			@Override
			@SuppressWarnings("unchecked")
			default E clazz(AttributeSupplier<?> clazz) throws IOException {
				E element = (E)this;
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
			@SuppressWarnings("unchecked")
			default MediaWriter clazz() throws IOException {
				E element = (E)this;
				if(element.html.doctype != Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						accessor,
						"Attributes.invalidGlobalAttributeForDoctype",
						element.html.doctype,
						"class"
					);
				}
				return Class.super.clazz();
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_class.asp">HTML Global class Attribute</a>.
			 * <blockquote>
			 * In HTML 4.01, the class attribute cannot be used with: &lt;base&gt;, &lt;head&gt;, &lt;html&gt;, &lt;meta&gt;, &lt;param&gt;, &lt;script&gt;, &lt;style&gt;, and &lt;title&gt;.
			 * </blockquote>
			 */
			@Override
			@SuppressWarnings("unchecked")
			default <Ex extends Throwable> E clazzE(AttributeWriterE<Ex> clazz) throws IOException, Ex {
				E element = (E)this;
				if(element.html.doctype != Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						accessor,
						"Attributes.invalidGlobalAttributeForDoctype",
						element.html.doctype,
						"class"
					);
				}
				return Class.super.clazzE(clazz);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_class.asp">HTML Global class Attribute</a>.
			 * <blockquote>
			 * In HTML 4.01, the class attribute cannot be used with: &lt;base&gt;, &lt;head&gt;, &lt;html&gt;, &lt;meta&gt;, &lt;param&gt;, &lt;script&gt;, &lt;style&gt;, and &lt;title&gt;.
			 * </blockquote>
			 */
			@Override
			@SuppressWarnings("unchecked")
			default E clazz(AttributeWriter clazz) throws IOException {
				E element = (E)this;
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
			@SuppressWarnings("unchecked")
			default E id(Object id) throws IOException {
				return attribute((E)this, "id", MarkupType.NONE, id, true, true);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_id.asp">HTML Global id Attribute</a>.
			 * <blockquote>
			 * In HTML5, the id attribute can be used on <b>any</b> HTML element (it will validate on any HTML element. However, it is not necessarily useful).
			 * </blockquote>
			 */
			@SuppressWarnings("unchecked")
			default <Ex extends Throwable> E idE(AttributeSupplierE<?,Ex> id) throws IOException, Ex {
				return attributeE((E)this, "id", MarkupType.NONE, id, true, true);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_id.asp">HTML Global id Attribute</a>.
			 * <blockquote>
			 * In HTML5, the id attribute can be used on <b>any</b> HTML element (it will validate on any HTML element. However, it is not necessarily useful).
			 * </blockquote>
			 */
			@SuppressWarnings("unchecked")
			default E id(AttributeSupplier<?> id) throws IOException {
				return attribute((E)this, "id", MarkupType.NONE, id, true, true);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_id.asp">HTML Global id Attribute</a>.
			 * <blockquote>
			 * In HTML5, the id attribute can be used on <b>any</b> HTML element (it will validate on any HTML element. However, it is not necessarily useful).
			 * </blockquote>
			 */
			@SuppressWarnings("unchecked")
			default MediaWriter id() throws IOException {
				return attribute((E)this, "id");
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_id.asp">HTML Global id Attribute</a>.
			 * <blockquote>
			 * In HTML5, the id attribute can be used on <b>any</b> HTML element (it will validate on any HTML element. However, it is not necessarily useful).
			 * </blockquote>
			 */
			@SuppressWarnings("unchecked")
			default <Ex extends Throwable> E idE(AttributeWriterE<Ex> id) throws IOException, Ex {
				return attributeE((E)this, "id", id);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_id.asp">HTML Global id Attribute</a>.
			 * <blockquote>
			 * In HTML5, the id attribute can be used on <b>any</b> HTML element (it will validate on any HTML element. However, it is not necessarily useful).
			 * </blockquote>
			 */
			@SuppressWarnings("unchecked")
			default E id(AttributeWriter id) throws IOException {
				return attribute((E)this, "id", id);
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
			@SuppressWarnings("unchecked")
			default E id(Object id) throws IOException {
				E element = (E)this;
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
			@SuppressWarnings("unchecked")
			default <Ex extends Throwable> E idE(AttributeSupplierE<?,Ex> id) throws IOException, Ex {
				E element = (E)this;
				if(element.html.doctype != Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						accessor,
						"Attributes.invalidGlobalAttributeForDoctype",
						element.html.doctype,
						"id"
					);
				}
				return Id.super.idE(id);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_id.asp">HTML Global id Attribute</a>.
			 * <blockquote>
			 * In HTML 4.01, the id attribute cannot be used with: &lt;base&gt;, &lt;head&gt;, &lt;html&gt;, &lt;meta&gt;, &lt;param&gt;, &lt;script&gt;, &lt;style&gt;, and &lt;title&gt;.
			 * </blockquote>
			 */
			@Override
			@SuppressWarnings("unchecked")
			default E id(AttributeSupplier<?> id) throws IOException {
				E element = (E)this;
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
			@SuppressWarnings("unchecked")
			default MediaWriter id() throws IOException {
				E element = (E)this;
				if(element.html.doctype != Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						accessor,
						"Attributes.invalidGlobalAttributeForDoctype",
						element.html.doctype,
						"id"
					);
				}
				return Id.super.id();
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_id.asp">HTML Global id Attribute</a>.
			 * <blockquote>
			 * In HTML 4.01, the id attribute cannot be used with: &lt;base&gt;, &lt;head&gt;, &lt;html&gt;, &lt;meta&gt;, &lt;param&gt;, &lt;script&gt;, &lt;style&gt;, and &lt;title&gt;.
			 * </blockquote>
			 */
			@Override
			@SuppressWarnings("unchecked")
			default <Ex extends Throwable> E idE(AttributeWriterE<Ex> id) throws IOException, Ex {
				E element = (E)this;
				if(element.html.doctype != Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						accessor,
						"Attributes.invalidGlobalAttributeForDoctype",
						element.html.doctype,
						"id"
					);
				}
				return Id.super.idE(id);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_id.asp">HTML Global id Attribute</a>.
			 * <blockquote>
			 * In HTML 4.01, the id attribute cannot be used with: &lt;base&gt;, &lt;head&gt;, &lt;html&gt;, &lt;meta&gt;, &lt;param&gt;, &lt;script&gt;, &lt;style&gt;, and &lt;title&gt;.
			 * </blockquote>
			 */
			@Override
			@SuppressWarnings("unchecked")
			default E id(AttributeWriter id) throws IOException {
				E element = (E)this;
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
			@SuppressWarnings("unchecked")
			default E label(Object label) throws IOException {
				return attribute((E)this, "label", MarkupType.TEXT, label, false, false);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_label.asp">HTML label Attribute</a>.
			 */
			@SuppressWarnings("unchecked")
			default <Ex extends Throwable> E labelE(AttributeSupplierE<?,Ex> label) throws IOException, Ex {
				return attributeE((E)this, "label", MarkupType.TEXT, label, false, false);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_label.asp">HTML label Attribute</a>.
			 */
			@SuppressWarnings("unchecked")
			default E label(AttributeSupplier<?> label) throws IOException {
				return attribute((E)this, "label", MarkupType.TEXT, label, false, false);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_label.asp">HTML label Attribute</a>.
			 */
			@SuppressWarnings("unchecked")
			default MediaWriter label() throws IOException {
				return attribute((E)this, "label");
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_label.asp">HTML label Attribute</a>.
			 */
			@SuppressWarnings("unchecked")
			default <Ex extends Throwable> E labelE(AttributeWriterE<Ex> label) throws IOException, Ex {
				return attributeE((E)this, "label", label);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_label.asp">HTML label Attribute</a>.
			 */
			@SuppressWarnings("unchecked")
			default E label(AttributeWriter label) throws IOException {
				return attribute((E)this, "label", label);
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
			@SuppressWarnings("unchecked")
			default E media(Object media) throws IOException {
				return attribute((E)this, "media", MarkupType.NONE, media, true, true);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_media.asp">HTML media Attribute</a>.
			 */
			@SuppressWarnings("unchecked")
			default <Ex extends Throwable> E mediaE(AttributeSupplierE<?,Ex> media) throws IOException, Ex {
				return attributeE((E)this, "media", MarkupType.NONE, media, true, true);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_media.asp">HTML media Attribute</a>.
			 */
			@SuppressWarnings("unchecked")
			default E media(AttributeSupplier<?> media) throws IOException {
				return attribute((E)this, "media", MarkupType.NONE, media, true, true);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_media.asp">HTML media Attribute</a>.
			 */
			@SuppressWarnings("unchecked")
			default MediaWriter media() throws IOException {
				return attribute((E)this, "media");
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_media.asp">HTML media Attribute</a>.
			 */
			@SuppressWarnings("unchecked")
			default <Ex extends Throwable> E mediaE(AttributeWriterE<Ex> media) throws IOException, Ex {
				return attributeE((E)this, "media", media);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_media.asp">HTML media Attribute</a>.
			 */
			@SuppressWarnings("unchecked")
			default E media(AttributeWriter media) throws IOException {
				return attribute((E)this, "media", media);
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_name.asp">HTML name Attribute</a>.
		 */
		public static interface Name<E extends Element<E> & Name<E>> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_name.asp">HTML name Attribute</a>.
			 */
			@SuppressWarnings("unchecked")
			default E name(Object name) throws IOException {
				return attribute((E)this, "name", MarkupType.NONE, name, false, true);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_name.asp">HTML name Attribute</a>.
			 */
			@SuppressWarnings("unchecked")
			default <Ex extends Throwable> E nameE(AttributeSupplierE<?,Ex> name) throws IOException, Ex {
				return attributeE((E)this, "name", MarkupType.NONE, name, false, true);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_name.asp">HTML name Attribute</a>.
			 */
			@SuppressWarnings("unchecked")
			default E name(AttributeSupplier<?> name) throws IOException {
				return attribute((E)this, "name", MarkupType.NONE, name, false, true);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_name.asp">HTML name Attribute</a>.
			 */
			@SuppressWarnings("unchecked")
			default MediaWriter name() throws IOException {
				return attribute((E)this, "name");
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_name.asp">HTML name Attribute</a>.
			 */
			@SuppressWarnings("unchecked")
			default <Ex extends Throwable> E nameE(AttributeWriterE<Ex> name) throws IOException, Ex {
				return attributeE((E)this, "name", name);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_name.asp">HTML name Attribute</a>.
			 */
			@SuppressWarnings("unchecked")
			default E name(AttributeWriter name) throws IOException {
				return attribute((E)this, "name", name);
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
			@SuppressWarnings("unchecked")
			default E style(Object style) throws IOException {
				// TODO: MarkupType.CSS
				return attribute((E)this, "style", MarkupType.JAVASCRIPT, style, true, true);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_style.asp">HTML Global style Attribute</a>.
			 * <blockquote>
			 * In HTML5, the style attribute can be used on <b>any</b> HTML element (it will validate on any HTML element. However, it is not necessarily useful).
			 * </blockquote>
			 */
			@SuppressWarnings("unchecked")
			default <Ex extends Throwable> E styleE(AttributeSupplierE<?,Ex> style) throws IOException, Ex {
				// TODO: MarkupType.CSS
				return attributeE((E)this, "style", MarkupType.JAVASCRIPT, style, true, true);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_style.asp">HTML Global style Attribute</a>.
			 * <blockquote>
			 * In HTML5, the style attribute can be used on <b>any</b> HTML element (it will validate on any HTML element. However, it is not necessarily useful).
			 * </blockquote>
			 */
			@SuppressWarnings("unchecked")
			default E style(AttributeSupplier<?> style) throws IOException {
				// TODO: MarkupType.CSS
				return attribute((E)this, "style", MarkupType.JAVASCRIPT, style, true, true);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_style.asp">HTML Global style Attribute</a>.
			 * <blockquote>
			 * In HTML5, the style attribute can be used on <b>any</b> HTML element (it will validate on any HTML element. However, it is not necessarily useful).
			 * </blockquote>
			 */
			@SuppressWarnings("unchecked")
			default MediaWriter style() throws IOException {
				return attribute((E)this, "style");
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_style.asp">HTML Global style Attribute</a>.
			 * <blockquote>
			 * In HTML5, the style attribute can be used on <b>any</b> HTML element (it will validate on any HTML element. However, it is not necessarily useful).
			 * </blockquote>
			 */
			@SuppressWarnings("unchecked")
			default <Ex extends Throwable> E styleE(AttributeWriterE<Ex> style) throws IOException, Ex {
				return attributeE((E)this, "style", style);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_style.asp">HTML Global style Attribute</a>.
			 * <blockquote>
			 * In HTML5, the style attribute can be used on <b>any</b> HTML element (it will validate on any HTML element. However, it is not necessarily useful).
			 * </blockquote>
			 */
			@SuppressWarnings("unchecked")
			default E style(AttributeWriter style) throws IOException {
				return attribute((E)this, "style", style);
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
			@SuppressWarnings("unchecked")
			default E style(Object style) throws IOException {
				E element = (E)this;
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
			@SuppressWarnings("unchecked")
			default <Ex extends Throwable> E styleE(AttributeSupplierE<?,Ex> style) throws IOException, Ex {
				E element = (E)this;
				if(element.html.doctype != Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						accessor,
						"Attributes.invalidGlobalAttributeForDoctype",
						element.html.doctype,
						"style"
					);
				}
				return Style.super.styleE(style);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_style.asp">HTML Global style Attribute</a>.
			 * <blockquote>
			 * In HTML 4.01, the style attribute cannot be used with: &lt;base&gt;, &lt;head&gt;, &lt;html&gt;, &lt;meta&gt;, &lt;param&gt;, &lt;script&gt;, &lt;style&gt;, and &lt;title&gt;.
			 * </blockquote>
			 */
			@Override
			@SuppressWarnings("unchecked")
			default E style(AttributeSupplier<?> style) throws IOException {
				E element = (E)this;
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
			@SuppressWarnings("unchecked")
			default MediaWriter style() throws IOException {
				E element = (E)this;
				if(element.html.doctype != Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						accessor,
						"Attributes.invalidGlobalAttributeForDoctype",
						element.html.doctype,
						"style"
					);
				}
				return Style.super.style();
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_style.asp">HTML Global style Attribute</a>.
			 * <blockquote>
			 * In HTML 4.01, the style attribute cannot be used with: &lt;base&gt;, &lt;head&gt;, &lt;html&gt;, &lt;meta&gt;, &lt;param&gt;, &lt;script&gt;, &lt;style&gt;, and &lt;title&gt;.
			 * </blockquote>
			 */
			@Override
			@SuppressWarnings("unchecked")
			default <Ex extends Throwable> E styleE(AttributeWriterE<Ex> style) throws IOException, Ex {
				E element = (E)this;
				if(element.html.doctype != Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						accessor,
						"Attributes.invalidGlobalAttributeForDoctype",
						element.html.doctype,
						"style"
					);
				}
				return Style.super.styleE(style);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_style.asp">HTML Global style Attribute</a>.
			 * <blockquote>
			 * In HTML 4.01, the style attribute cannot be used with: &lt;base&gt;, &lt;head&gt;, &lt;html&gt;, &lt;meta&gt;, &lt;param&gt;, &lt;script&gt;, &lt;style&gt;, and &lt;title&gt;.
			 * </blockquote>
			 */
			@Override
			@SuppressWarnings("unchecked")
			default E style(AttributeWriter style) throws IOException {
				E element = (E)this;
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
			@SuppressWarnings("unchecked")
			default E title(Object title) throws IOException {
				return attribute((E)this, "title", MarkupType.TEXT, title, true, true);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_title.asp">HTML Global title Attribute</a>.
			 * <blockquote>
			 * In HTML5, the title attribute can be used on <b>any</b> HTML element (it will validate on any HTML element. However, it is not necessarily useful).
			 * </blockquote>
			 */
			@SuppressWarnings("unchecked")
			default <Ex extends Throwable> E titleE(AttributeSupplierE<?,Ex> title) throws IOException, Ex {
				return attributeE((E)this, "title", MarkupType.TEXT, title, true, true);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_title.asp">HTML Global title Attribute</a>.
			 * <blockquote>
			 * In HTML5, the title attribute can be used on <b>any</b> HTML element (it will validate on any HTML element. However, it is not necessarily useful).
			 * </blockquote>
			 */
			@SuppressWarnings("unchecked")
			default E title(AttributeSupplier<?> title) throws IOException {
				return attribute((E)this, "title", MarkupType.TEXT, title, true, true);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_title.asp">HTML Global title Attribute</a>.
			 * <blockquote>
			 * In HTML5, the title attribute can be used on <b>any</b> HTML element (it will validate on any HTML element. However, it is not necessarily useful).
			 * </blockquote>
			 */
			@SuppressWarnings("unchecked")
			default MediaWriter title() throws IOException {
				return attribute((E)this, "title");
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_title.asp">HTML Global title Attribute</a>.
			 * <blockquote>
			 * In HTML5, the title attribute can be used on <b>any</b> HTML element (it will validate on any HTML element. However, it is not necessarily useful).
			 * </blockquote>
			 */
			@SuppressWarnings("unchecked")
			default <Ex extends Throwable> E titleE(AttributeWriterE<Ex> title) throws IOException, Ex {
				return attributeE((E)this, "title", title);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_title.asp">HTML Global title Attribute</a>.
			 * <blockquote>
			 * In HTML5, the title attribute can be used on <b>any</b> HTML element (it will validate on any HTML element. However, it is not necessarily useful).
			 * </blockquote>
			 */
			@SuppressWarnings("unchecked")
			default E title(AttributeWriter title) throws IOException {
				return attribute((E)this, "title", title);
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
			@SuppressWarnings("unchecked")
			default E title(Object title) throws IOException {
				E element = (E)this;
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
			@SuppressWarnings("unchecked")
			default <Ex extends Throwable> E titleE(AttributeSupplierE<?,Ex> title) throws IOException, Ex {
				E element = (E)this;
				if(element.html.doctype != Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						accessor,
						"Attributes.invalidGlobalAttributeForDoctype",
						element.html.doctype,
						"title"
					);
				}
				return Title.super.titleE(title);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_title.asp">HTML Global title Attribute</a>.
			 * <blockquote>
			 * In HTML 4.01, the title attribute cannot be used with: &lt;base&gt;, &lt;head&gt;, &lt;html&gt;, &lt;meta&gt;, &lt;param&gt;, &lt;script&gt;, &lt;style&gt;, and &lt;title&gt;.
			 * </blockquote>
			 */
			@Override
			@SuppressWarnings("unchecked")
			default E title(AttributeSupplier<?> title) throws IOException {
				E element = (E)this;
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
			@SuppressWarnings("unchecked")
			default MediaWriter title() throws IOException {
				E element = (E)this;
				if(element.html.doctype != Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						accessor,
						"Attributes.invalidGlobalAttributeForDoctype",
						element.html.doctype,
						"title"
					);
				}
				return Title.super.title();
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_title.asp">HTML Global title Attribute</a>.
			 * <blockquote>
			 * In HTML 4.01, the title attribute cannot be used with: &lt;base&gt;, &lt;head&gt;, &lt;html&gt;, &lt;meta&gt;, &lt;param&gt;, &lt;script&gt;, &lt;style&gt;, and &lt;title&gt;.
			 * </blockquote>
			 */
			@Override
			@SuppressWarnings("unchecked")
			default <Ex extends Throwable> E titleE(AttributeWriterE<Ex> title) throws IOException, Ex {
				E element = (E)this;
				if(element.html.doctype != Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						accessor,
						"Attributes.invalidGlobalAttributeForDoctype",
						element.html.doctype,
						"title"
					);
				}
				return Title.super.titleE(title);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_title.asp">HTML Global title Attribute</a>.
			 * <blockquote>
			 * In HTML 4.01, the title attribute cannot be used with: &lt;base&gt;, &lt;head&gt;, &lt;html&gt;, &lt;meta&gt;, &lt;param&gt;, &lt;script&gt;, &lt;style&gt;, and &lt;title&gt;.
			 * </blockquote>
			 */
			@Override
			@SuppressWarnings("unchecked")
			default E title(AttributeWriter title) throws IOException {
				E element = (E)this;
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
			@SuppressWarnings("unchecked")
			default E value(Object value) throws IOException {
				return attribute((E)this, "value", MarkupType.NONE, value, false, true);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_value.asp">HTML value Attribute</a>.
			 */
			@SuppressWarnings("unchecked")
			default <Ex extends Throwable> E valueE(AttributeSupplierE<?,Ex> value) throws IOException, Ex {
				return attributeE((E)this, "value", MarkupType.NONE, value, false, true);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_value.asp">HTML value Attribute</a>.
			 */
			@SuppressWarnings("unchecked")
			default E value(AttributeSupplier<?> value) throws IOException {
				return attribute((E)this, "value", MarkupType.NONE, value, false, true);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_value.asp">HTML value Attribute</a>.
			 */
			@SuppressWarnings("unchecked")
			default MediaWriter value() throws IOException {
				return attribute((E)this, "value");
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_value.asp">HTML value Attribute</a>.
			 */
			@SuppressWarnings("unchecked")
			default <Ex extends Throwable> E valueE(AttributeWriterE<Ex> value) throws IOException, Ex {
				return attributeE((E)this, "value", value);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_value.asp">HTML value Attribute</a>.
			 */
			@SuppressWarnings("unchecked")
			default E value(AttributeWriter value) throws IOException {
				return attribute((E)this, "value", value);
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

		static <E extends Element<E>> E attribute(E element, String name, String url) throws IOException {
			if(url != null) {
				// TODO: Validate attribute name?
				element.html.out.write(' ');
				element.html.out.write(name);
				element.html.out.write("=\"");
				// TODO: UrlInXhtmlAttributeEncoder once RFC 3987 supported
				textInXhtmlAttributeEncoder.write(url, element.html.out);
				element.html.out.write('"');
			}
			return element;
		}

		static <E extends Element<E>> E attribute(E element, String name, Object url) throws IOException {
			if(url != null) {
				if(url instanceof AttributeSupplier<?>) return attribute(element, name, (AttributeSupplier<?>)url);
				if(url instanceof AttributeSupplierE<?,?>) {
					try {
						return attributeE(element, name, (AttributeSupplierE<?,?>)url);
					} catch(Error|RuntimeException|IOException e) {
						throw e;
					} catch(Throwable t) {
						throw new WrappedException(t);
					}
				}
				return attribute(element, name, Coercion.toString(url));
			}
			return element;
		}

		static <E extends Element<E>,Ex extends Throwable> E attributeE(E element, String name, AttributeSupplierE<?,Ex> url) throws IOException, Ex {
			return attribute(element, name, (url == null) ? null : url.get());
		}

		static <E extends Element<E>> E attribute(E element, String name, MarkupType markupType, AttributeSupplier<?> url) throws IOException {
			return attributeE(element, name, url);
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_href.asp">HTML href Attribute</a>.
		 */
		public static interface Href<E extends Element<E> & Href<E>> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_href.asp">HTML href Attribute</a>.
			 */
			@SuppressWarnings("unchecked")
			default E href(String href) throws IOException {
				return attribute((E)this, "href", href);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_href.asp">HTML href Attribute</a>.
			 */
			@SuppressWarnings("unchecked")
			default E href(Object href) throws IOException {
				return attribute((E)this, "href", href);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_href.asp">HTML href Attribute</a>.
			 */
			@SuppressWarnings("unchecked")
			default <Ex extends Throwable> E hrefE(AttributeSupplierE<?,Ex> href) throws IOException, Ex {
				return attributeE((E)this, "href", href);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_href.asp">HTML href Attribute</a>.
			 */
			@SuppressWarnings("unchecked")
			default E href(AttributeSupplier<?> href) throws IOException {
				return attribute((E)this, "href", href);
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_src.asp">HTML src Attribute</a>.
		 */
		public static interface Src<E extends Element<E> & Src<E>> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_src.asp">HTML src Attribute</a>.
			 */
			@SuppressWarnings("unchecked")
			default E src(String src) throws IOException {
				return attribute((E)this, "src", src);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_src.asp">HTML src Attribute</a>.
			 */
			@SuppressWarnings("unchecked")
			default E src(Object src) throws IOException {
				return attribute((E)this, "src", src);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_src.asp">HTML src Attribute</a>.
			 */
			@SuppressWarnings("unchecked")
			default <Ex extends Throwable> E srcE(AttributeSupplierE<?,Ex> src) throws IOException, Ex {
				return attributeE((E)this, "src", src);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_src.asp">HTML src Attribute</a>.
			 */
			@SuppressWarnings("unchecked")
			default E src(AttributeSupplier<?> src) throws IOException {
				return attribute((E)this, "src", src);
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
