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

		static <E extends Element<E>> E attribute(E element, java.lang.String name, java.lang.Boolean value) throws IOException {
			if(value) {
				return attribute(element, name, value.booleanValue());
			} else {
				return element;
			}
		}

		static <E extends Element<E>,Ex extends Throwable> E attributeE(E element, java.lang.String name, BooleanSupplierE<Ex> value) throws IOException, Ex {
			return attribute(element, name, (value == null) ? null : value.get(element.html.serialization, element.html.doctype));
		}

		static <E extends Element<E>> E attribute(E element, java.lang.String name, BooleanSupplier value) throws IOException {
			return attributeE(element, name, value);
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_async.asp">HTML async Attribute</a>.
		 */
		public static interface Async<E extends Element<E> & Async<E>> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_async.asp">HTML async Attribute</a>.
			 */
			default E async(boolean async) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "async", async);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_async.asp">HTML async Attribute</a>.
			 */
			default E async(java.lang.Boolean async) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "async", async);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_async.asp">HTML async Attribute</a>.
			 */
			default <Ex extends Throwable> E asyncE(BooleanSupplierE<Ex> async) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attributeE(element, "async", async);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_async.asp">HTML async Attribute</a>.
			 */
			default E async(BooleanSupplier async) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "async", async);
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_checked.asp">HTML checked Attribute</a>.
		 */
		public static interface Checked<E extends Element<E> & Checked<E>> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_checked.asp">HTML checked Attribute</a>.
			 */
			default E checked(boolean checked) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "checked", checked);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_checked.asp">HTML checked Attribute</a>.
			 */
			default E checked(java.lang.Boolean checked) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "checked", checked);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_checked.asp">HTML checked Attribute</a>.
			 */
			default <Ex extends Throwable> E checkedE(BooleanSupplierE<Ex> checked) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attributeE(element, "checked", checked);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_checked.asp">HTML checked Attribute</a>.
			 */
			default E checked(BooleanSupplier checked) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "checked", checked);
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_defer.asp">HTML defer Attribute</a>.
		 */
		public static interface Defer<E extends Element<E> & Defer<E>> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_defer.asp">HTML defer Attribute</a>.
			 */
			default E defer(boolean defer) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "defer", defer);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_defer.asp">HTML defer Attribute</a>.
			 */
			default E defer(java.lang.Boolean defer) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "defer", defer);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_defer.asp">HTML defer Attribute</a>.
			 */
			default <Ex extends Throwable> E deferE(BooleanSupplierE<Ex> defer) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attributeE(element, "defer", defer);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_defer.asp">HTML defer Attribute</a>.
			 */
			default E defer(BooleanSupplier defer) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "defer", defer);
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_disabled.asp">HTML disabled Attribute</a>.
		 */
		public static interface Disabled<E extends Element<E> & Disabled<E>> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_disabled.asp">HTML disabled Attribute</a>.
			 */
			default E disabled(boolean disabled) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "disabled", disabled);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_disabled.asp">HTML disabled Attribute</a>.
			 */
			default E disabled(java.lang.Boolean disabled) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "disabled", disabled);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_disabled.asp">HTML disabled Attribute</a>.
			 */
			default <Ex extends Throwable> E disabledE(BooleanSupplierE<Ex> disabled) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attributeE(element, "disabled", disabled);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_disabled.asp">HTML disabled Attribute</a>.
			 */
			default E disabled(BooleanSupplier disabled) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "disabled", disabled);
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
			default <Ex extends Throwable> E noshadeE(BooleanSupplierE<Ex> noshade) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
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
			default E noshade(BooleanSupplier noshade) throws IOException {
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
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_readonly.asp">HTML readonly Attribute</a>.
		 */
		public static interface Readonly<E extends Element<E> & Readonly<E>> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_readonly.asp">HTML readonly Attribute</a>.
			 */
			default E readonly(boolean readonly) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "readonly", readonly);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_readonly.asp">HTML readonly Attribute</a>.
			 */
			default E readonly(java.lang.Boolean readonly) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "readonly", readonly);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_readonly.asp">HTML readonly Attribute</a>.
			 */
			default <Ex extends Throwable> E readonlyE(BooleanSupplierE<Ex> readonly) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attributeE(element, "readonly", readonly);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_readonly.asp">HTML readonly Attribute</a>.
			 */
			default E readonly(BooleanSupplier readonly) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "readonly", readonly);
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_selected.asp">HTML selected Attribute</a>.
		 */
		public static interface Selected<E extends Element<E> & Selected<E>> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_selected.asp">HTML selected Attribute</a>.
			 */
			default E selected(boolean selected) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "selected", selected);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_selected.asp">HTML selected Attribute</a>.
			 */
			default E selected(java.lang.Boolean selected) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "selected", selected);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_selected.asp">HTML selected Attribute</a>.
			 */
			default <Ex extends Throwable> E selectedE(BooleanSupplierE<Ex> selected) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attributeE(element, "selected", selected);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_selected.asp">HTML selected Attribute</a>.
			 */
			default E selected(BooleanSupplier selected) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "selected", selected);
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

		static <E extends Element<E>,Ex extends Throwable> E attributeE(E element, java.lang.String name, IntegerSupplierE<Ex> pixels) throws IOException, Ex {
			return Integer.attributeE(element, name, pixels);
		}

		static <E extends Element<E>> E attribute(E element, java.lang.String name, IntegerSupplier pixels) throws IOException {
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
		 * @deprecated  In HTML 4.01, the value could be defined in pixels or in % of the containing element. In HTML5, the value must be in pixels.
		 */
		@Deprecated
		static <E extends Element<E>,Ex extends Throwable> E attributeE(E element, java.lang.String name, StringSupplierE<Ex> pixelsOrPercent) throws IOException, Ex {
			return attribute(element, name, (pixelsOrPercent == null) ? null : pixelsOrPercent.get(element.html.serialization, element.html.doctype));
		}

		/**
		 * @deprecated  In HTML 4.01, the value could be defined in pixels or in % of the containing element. In HTML5, the value must be in pixels.
		 */
		@Deprecated
		static <E extends Element<E>> E attribute(E element, java.lang.String name, StringSupplier pixelsOrPercent) throws IOException {
			return attributeE(element, name, pixelsOrPercent);
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_width.asp">HTML width Attribute</a>.
		 */
		public static interface Width<E extends Element<E> & Width<E>> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_width.asp">HTML width Attribute</a>.
			 */
			default E width(int pixels) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "width", pixels);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_width.asp">HTML width Attribute</a>.
			 */
			default E width(java.lang.Integer pixels) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "width", pixels);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_width.asp">HTML width Attribute</a>.
			 */
			default <Ex extends Throwable> E widthE(IntegerSupplierE<Ex> pixels) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attributeE(element, "width", pixels);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_width.asp">HTML width Attribute</a>.
			 */
			default E width(IntegerSupplier pixels) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "width", pixels);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_width.asp">HTML width Attribute</a>.
			 *
			 * @deprecated  In HTML 4.01, the width could be defined in pixels or in % of the containing element. In HTML5, the value must be in pixels.
			 */
			@Deprecated
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
			default <Ex extends Throwable> E widthE(StringSupplierE<Ex> pixelsOrPercent) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attributeE(element, "width", pixelsOrPercent);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_width.asp">HTML width Attribute</a>.
			 *
			 * @deprecated  In HTML 4.01, the width could be defined in pixels or in % of the containing element. In HTML5, the value must be in pixels.
			 */
			@Deprecated
			default E width(StringSupplier pixelsOrPercent) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "width", pixelsOrPercent);
			}
		}
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/ref_eventattributes.asp">HTML Event Attributes</a>.
	 */
	public static class Event {

		/** Make no instances. */
		private Event() {}

		static <E extends Element<E>> E attribute(E element, java.lang.String name, Object script) throws IOException {
			if(script != null) {
				if(script instanceof Supplier<?>) return attribute(element, name, (Supplier<?>)script);
				if(script instanceof SupplierE<?,?>) {
					try {
						return attributeE(element, name, (SupplierE<?,?>)script);
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
				element.html.out.write(' ');
				element.html.out.write(name);
				element.html.out.write("=\"");
				// TODO: Find more places where we can do javascript markups (ao-taglib...)
				Coercion.write(script, MarkupType.JAVASCRIPT, javaScriptInXhtmlAttributeEncoder, false, element.html.out);
				element.html.out.write('"');
			}
			return element;
		}

		static <E extends Element<E>,Ex extends Throwable> E attributeE(E element, java.lang.String name, SupplierE<?,Ex> script) throws IOException, Ex {
			return attribute(element, name, (script == null) ? null : script.get(element.html.serialization, element.html.doctype));
		}

		static <E extends Element<E>> E attribute(E element, java.lang.String name, Supplier<?> script) throws IOException {
			return attributeE(element, name, script);
		}

		static <E extends Element<E>> MediaWriter attribute(E element, java.lang.String name) throws IOException {
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

		static <E extends Element<E>,Ex extends Throwable> E attributeE(E element, java.lang.String name, AttributeWriterE<Ex> script) throws IOException, Ex {
			if(script != null) {
				try (MediaWriter out = attribute(element, name)) {
					script.writeAttribute(out);
				}
			}
			return element;
		}

		static <E extends Element<E>> E attribute(E element, java.lang.String name, AttributeWriter script) throws IOException {
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
				default E onclick(Object onclick) throws IOException {
					@SuppressWarnings("unchecked") E element = (E)this;
					return attribute(element, "onclick", onclick);
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onclick.asp">HTML onclick Event Attribute</a>.
				 */
				default <Ex extends Throwable> E onclickE(SupplierE<?,Ex> onclick) throws IOException, Ex {
					@SuppressWarnings("unchecked") E element = (E)this;
					return attributeE(element, "onclick", onclick);
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onclick.asp">HTML onclick Event Attribute</a>.
				 */
				default E onclick(Supplier<?> onclick) throws IOException {
					@SuppressWarnings("unchecked") E element = (E)this;
					return attribute(element, "onclick", onclick);
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onclick.asp">HTML onclick Event Attribute</a>.
				 */
				default MediaWriter onclick() throws IOException {
					@SuppressWarnings("unchecked") E element = (E)this;
					return attribute(element, "onclick");
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onclick.asp">HTML onclick Event Attribute</a>.
				 */
				default <Ex extends Throwable> E onclickE(AttributeWriterE<Ex> onclick) throws IOException, Ex {
					@SuppressWarnings("unchecked") E element = (E)this;
					return attributeE(element, "onclick", onclick);
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onclick.asp">HTML onclick Event Attribute</a>.
				 */
				default E onclick(AttributeWriter onclick) throws IOException {
					@SuppressWarnings("unchecked") E element = (E)this;
					return attribute(element, "onclick", onclick);
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
			V extends java.lang.Enum<V> & StringSupplier
		> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_rel.asp">HTML rel Attribute</a>.
			 */
			// All other rel(...) methods call this one
			default E rel(java.lang.String rel) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return String.attribute(element, "rel", MarkupType.NONE, rel, true, true);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_rel.asp">HTML rel Attribute</a>.
			 */
			default <Ex extends Throwable> E relE(StringSupplierE<Ex> rel) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				return rel((rel == null) ? null : rel.get(element.html.serialization, element.html.doctype));
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_rel.asp">HTML rel Attribute</a>.
			 */
			default E rel(StringSupplier rel) throws IOException {
				return relE(rel);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_rel.asp">HTML rel Attribute</a>.
			 */
			default E rel(V rel) throws IOException {
				return rel((StringSupplier)rel);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_rel.asp">HTML rel Attribute</a>.
			 */
			default <Ex extends Throwable> E relE(EnumSupplierE<V,Ex> rel) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				return rel((rel== null) ? (V)null : rel.get(element.html.serialization, element.html.doctype));
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_rel.asp">HTML rel Attribute</a>.
			 */
			default E rel(EnumSupplier<V> rel) throws IOException {
				return relE(rel);
			}
		}

		/**
		 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/CORS_settings_attributes">The crossorigin attribute: Requesting CORS access to content</a>.
		 */
		public static interface Crossorigin<
			E extends Element<E> & Crossorigin<E,V>,
			V extends java.lang.Enum<V> & StringSupplier
		> {

			/**
			 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/CORS_settings_attributes">The crossorigin attribute: Requesting CORS access to content</a>.
			 */
			// All other crossorigin(...) methods call this one
			default E crossorigin(java.lang.String crossorigin) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return String.attribute(element, "crossorigin", MarkupType.NONE, crossorigin, true, true);
			}

			/**
			 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/CORS_settings_attributes">The crossorigin attribute: Requesting CORS access to content</a>.
			 */
			default <Ex extends Throwable> E crossoriginE(StringSupplierE<Ex> crossorigin) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				return crossorigin((crossorigin == null) ? null : crossorigin.get(element.html.serialization, element.html.doctype));
			}

			/**
			 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/CORS_settings_attributes">The crossorigin attribute: Requesting CORS access to content</a>.
			 */
			default E crossorigin(StringSupplier crossorigin) throws IOException {
				return crossoriginE(crossorigin);
			}

			/**
			 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/CORS_settings_attributes">The crossorigin attribute: Requesting CORS access to content</a>.
			 */
			default E crossorigin(V crossorigin) throws IOException {
				return crossorigin((StringSupplier)crossorigin);
			}

			/**
			 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/CORS_settings_attributes">The crossorigin attribute: Requesting CORS access to content</a>.
			 */
			default <Ex extends Throwable> E crossoriginE(EnumSupplierE<V,Ex> crossorigin) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				return crossorigin((crossorigin== null) ? (V)null : crossorigin.get(element.html.serialization, element.html.doctype));
			}

			/**
			 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/CORS_settings_attributes">The crossorigin attribute: Requesting CORS access to content</a>.
			 */
			default E crossorigin(EnumSupplier<V> crossorigin) throws IOException {
				return crossoriginE(crossorigin);
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

		static <E extends Element<E>,Ex extends Throwable> E attributeE(E element, java.lang.String name, IntegerSupplierE<Ex> value) throws IOException, Ex {
			return attribute(element, name, (value == null) ? (java.lang.Integer)null : value.get(element.html.serialization, element.html.doctype));
		}

		static <E extends Element<E>> E attribute(E element, java.lang.String name, IntegerSupplier value) throws IOException {
			return attributeE(element, name, value);
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_height.asp">HTML height Attribute</a>.
		 */
		public static interface Height<E extends Element<E> & Height<E>> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_height.asp">HTML height Attribute</a>.
			 */
			default E height(int pixels) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "height", pixels);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_height.asp">HTML height Attribute</a>.
			 */
			default E height(java.lang.Integer pixels) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "height", pixels);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_height.asp">HTML height Attribute</a>.
			 */
			default <Ex extends Throwable> E heightE(IntegerSupplierE<Ex> pixels) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attributeE(element, "height", pixels);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_height.asp">HTML height Attribute</a>.
			 */
			default E height(IntegerSupplier pixels) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "height", pixels);
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
			default <Ex extends Throwable> E heightE(IntegerSupplierE<Ex> pixels) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				if(element.html.doctype != Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						accessor,
						"Attributes.onlySupportedInHtml5",
						element.html.doctype,
						"height"
					);
				}
				return Height.super.heightE(pixels);
			}

			/**
			 * {@inheritDoc}
			 * <p>
			 * The height attribute is new in HTML5.
			 * </p>
			 */
			@Override
			default E height(IntegerSupplier pixels) throws IOException {
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
			default E maxlength(int maxlength) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "maxlength", maxlength);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_maxlength.asp">HTML maxlength Attribute</a>.
			 */
			default E maxlength(java.lang.Integer maxlength) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "maxlength", maxlength);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_maxlength.asp">HTML maxlength Attribute</a>.
			 */
			default <Ex extends Throwable> E maxlengthE(IntegerSupplierE<Ex> maxlength) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attributeE(element, "maxlength", maxlength);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_maxlength.asp">HTML maxlength Attribute</a>.
			 */
			default E maxlength(IntegerSupplier maxlength) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "maxlength", maxlength);
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_size.asp">HTML size Attribute</a>.
		 */
		public static interface Size<E extends Element<E> & Size<E>> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_size.asp">HTML size Attribute</a>.
			 */
			default E size(int size) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "size", size);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_size.asp">HTML size Attribute</a>.
			 */
			default E size(java.lang.Integer size) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "size", size);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_size.asp">HTML size Attribute</a>.
			 */
			default <Ex extends Throwable> E sizeE(IntegerSupplierE<Ex> size) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attributeE(element, "size", size);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_size.asp">HTML size Attribute</a>.
			 */
			default E size(IntegerSupplier size) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "size", size);
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
			default <Ex extends Throwable> E sizeE(IntegerSupplierE<Ex> size) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
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
			default E size(IntegerSupplier size) throws IOException {
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
			default <Ex extends Throwable> E tabindexE(IntegerSupplierE<Ex> tabindex) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
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
			default E tabindex(IntegerSupplier tabindex) throws IOException {
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
			default <Ex extends Throwable> E tabindexE(IntegerSupplierE<Ex> tabindex) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attributeE(element, "tabindex", tabindex);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_tabindex.asp">HTML Global tabindex Attribute</a>.
			 * <blockquote>
			 * In HTML 4.01, the tabindex attribute can be used with: &lt;a&gt;, &lt;area&gt;, &lt;button&gt;, &lt;input&gt;, &lt;object&gt;, &lt;select&gt;, and &lt;textarea&gt;.
			 * </blockquote>
			 */
			@Override
			default E tabindex(IntegerSupplier tabindex) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "tabindex", tabindex);
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_width.asp">HTML width Attribute</a>.
		 */
		public static interface Width<E extends Element<E> & Width<E>> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_width.asp">HTML width Attribute</a>.
			 */
			default E width(int pixels) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "width", pixels);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_width.asp">HTML width Attribute</a>.
			 */
			default E width(java.lang.Integer pixels) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "width", pixels);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_width.asp">HTML width Attribute</a>.
			 */
			default <Ex extends Throwable> E widthE(IntegerSupplierE<Ex> pixels) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attributeE(element, "width", pixels);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_width.asp">HTML width Attribute</a>.
			 */
			default E width(IntegerSupplier pixels) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "width", pixels);
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
			default <Ex extends Throwable> E widthE(IntegerSupplierE<Ex> pixels) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				if(element.html.doctype != Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						accessor,
						"Attributes.onlySupportedInHtml5",
						element.html.doctype,
						"width"
					);
				}
				return Width.super.widthE(pixels);
			}

			/**
			 * {@inheritDoc}
			 * <p>
			 * The width attribute is new in HTML5.
			 * </p>
			 */
			@Override
			default E width(IntegerSupplier pixels) throws IOException {
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
		 * @param value  If is {@link StringSupplierE#NO_VALUE} (by identity), will write empty attribute.
		 */
		static <E extends Element<E>> E attribute(E element, java.lang.String name, MarkupType markupType, java.lang.String value, boolean trim, boolean nullIfEmpty) throws IOException {
			if(value != null) {
				if(value == StringSupplierE.NO_VALUE) { // Identity comparison for marker value
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

		static <E extends Element<E>,Ex extends Throwable> E attributeE(E element, java.lang.String name, MarkupType markupType, StringSupplierE<Ex> value, boolean trim, boolean nullIfEmpty) throws IOException, Ex {
			return attribute(element, name, markupType, (value == null) ? null : value.get(element.html.serialization, element.html.doctype), trim, nullIfEmpty);
		}

		static <E extends Element<E>> E attribute(E element, java.lang.String name, MarkupType markupType, StringSupplier value, boolean trim, boolean nullIfEmpty) throws IOException {
			return attributeE(element, name, markupType, value, trim, nullIfEmpty);
		}
	}

	/**
	 * Streamable text attributes.
	 */
	public static class Text {

		/** Make no instances. */
		private Text() {}

		static <E extends Element<E>> E attribute(E element, java.lang.String name, MarkupType markupType, Object value, boolean trim, boolean nullIfEmpty) throws IOException {
			if(value != null) {
				if(value instanceof Supplier<?>) return attribute(element, name, markupType, (Supplier<?>)value, trim, nullIfEmpty);
				if(value instanceof SupplierE<?,?>) {
					try {
						return attributeE(element, name, markupType, (SupplierE<?,?>)value, trim, nullIfEmpty);
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
					element.html.out.write(' '); // TODO: Combine these three writes by passing-in a single combined String?
					element.html.out.write(name);
					element.html.out.write("=\"");
					Coercion.write(value, markupType, textInXhtmlAttributeEncoder, false, element.html.out);
					element.html.out.write('"');
				}
			}
			return element;
		}

		static <E extends Element<E>,Ex extends Throwable> E attributeE(E element, java.lang.String name, MarkupType markupType, SupplierE<?,Ex> value, boolean trim, boolean nullIfEmpty) throws IOException, Ex {
			return attribute(element, name, markupType, (value == null) ? null : value.get(element.html.serialization, element.html.doctype), trim, nullIfEmpty);
		}

		static <E extends Element<E>> E attribute(E element, java.lang.String name, MarkupType markupType, Supplier<?> value, boolean trim, boolean nullIfEmpty) throws IOException {
			return attributeE(element, name, markupType, value, trim, nullIfEmpty);
		}

		static <E extends Element<E>> MediaWriter attribute(E element, java.lang.String name) throws IOException {
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

		static <E extends Element<E>,Ex extends Throwable> E attributeE(E element, java.lang.String name, AttributeWriterE<Ex> value) throws IOException, Ex {
			if(value != null) {
				try (MediaWriter out = attribute(element, name)) {
					value.writeAttribute(out);
				}
			}
			return element;
		}

		static <E extends Element<E>> E attribute(E element, java.lang.String name, AttributeWriter value) throws IOException {
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
			default <Ex extends Throwable> E clazzE(SupplierE<?,Ex> clazz) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attributeE(element, "class", MarkupType.NONE, clazz, true, true);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_class.asp">HTML Global class Attribute</a>.
			 * <blockquote>
			 * In HTML5, the class attribute can be used on <b>any</b> HTML element (it will validate on any HTML element. However, it is not necessarily useful).
			 * </blockquote>
			 */
			default E clazz(Supplier<?> clazz) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "class", MarkupType.NONE, clazz, true, true);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_class.asp">HTML Global class Attribute</a>.
			 * <blockquote>
			 * In HTML5, the class attribute can be used on <b>any</b> HTML element (it will validate on any HTML element. However, it is not necessarily useful).
			 * </blockquote>
			 */
			default MediaWriter clazz() throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "class");
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_class.asp">HTML Global class Attribute</a>.
			 * <blockquote>
			 * In HTML5, the class attribute can be used on <b>any</b> HTML element (it will validate on any HTML element. However, it is not necessarily useful).
			 * </blockquote>
			 */
			default <Ex extends Throwable> E clazzE(AttributeWriterE<Ex> clazz) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attributeE(element, "class", clazz);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_class.asp">HTML Global class Attribute</a>.
			 * <blockquote>
			 * In HTML5, the class attribute can be used on <b>any</b> HTML element (it will validate on any HTML element. However, it is not necessarily useful).
			 * </blockquote>
			 */
			default E clazz(AttributeWriter clazz) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "class", clazz);
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
			default <Ex extends Throwable> E clazzE(SupplierE<?,Ex> clazz) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
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
			default E clazz(Supplier<?> clazz) throws IOException {
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
			default MediaWriter clazz() throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
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
			default <Ex extends Throwable> E clazzE(AttributeWriterE<Ex> clazz) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
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
			default E clazz(AttributeWriter clazz) throws IOException {
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
			default <Ex extends Throwable> E idE(SupplierE<?,Ex> id) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attributeE(element, "id", MarkupType.NONE, id, true, true);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_id.asp">HTML Global id Attribute</a>.
			 * <blockquote>
			 * In HTML5, the id attribute can be used on <b>any</b> HTML element (it will validate on any HTML element. However, it is not necessarily useful).
			 * </blockquote>
			 */
			default E id(Supplier<?> id) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "id", MarkupType.NONE, id, true, true);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_id.asp">HTML Global id Attribute</a>.
			 * <blockquote>
			 * In HTML5, the id attribute can be used on <b>any</b> HTML element (it will validate on any HTML element. However, it is not necessarily useful).
			 * </blockquote>
			 */
			default MediaWriter id() throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "id");
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_id.asp">HTML Global id Attribute</a>.
			 * <blockquote>
			 * In HTML5, the id attribute can be used on <b>any</b> HTML element (it will validate on any HTML element. However, it is not necessarily useful).
			 * </blockquote>
			 */
			default <Ex extends Throwable> E idE(AttributeWriterE<Ex> id) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attributeE(element, "id", id);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_id.asp">HTML Global id Attribute</a>.
			 * <blockquote>
			 * In HTML5, the id attribute can be used on <b>any</b> HTML element (it will validate on any HTML element. However, it is not necessarily useful).
			 * </blockquote>
			 */
			default E id(AttributeWriter id) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "id", id);
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
			default <Ex extends Throwable> E idE(SupplierE<?,Ex> id) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
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
			default E id(Supplier<?> id) throws IOException {
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
			default MediaWriter id() throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
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
			default <Ex extends Throwable> E idE(AttributeWriterE<Ex> id) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
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
			default E id(AttributeWriter id) throws IOException {
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
			default E label(Object label) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "label", MarkupType.TEXT, label, false, false);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_label.asp">HTML label Attribute</a>.
			 */
			default <Ex extends Throwable> E labelE(SupplierE<?,Ex> label) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attributeE(element, "label", MarkupType.TEXT, label, false, false);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_label.asp">HTML label Attribute</a>.
			 */
			default E label(Supplier<?> label) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "label", MarkupType.TEXT, label, false, false);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_label.asp">HTML label Attribute</a>.
			 */
			default MediaWriter label() throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "label");
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_label.asp">HTML label Attribute</a>.
			 */
			default <Ex extends Throwable> E labelE(AttributeWriterE<Ex> label) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attributeE(element, "label", label);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_label.asp">HTML label Attribute</a>.
			 */
			default E label(AttributeWriter label) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "label", label);
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
			default E media(Object media) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "media", MarkupType.NONE, media, true, true);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_media.asp">HTML media Attribute</a>.
			 */
			default <Ex extends Throwable> E mediaE(SupplierE<?,Ex> media) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attributeE(element, "media", MarkupType.NONE, media, true, true);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_media.asp">HTML media Attribute</a>.
			 */
			default E media(Supplier<?> media) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "media", MarkupType.NONE, media, true, true);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_media.asp">HTML media Attribute</a>.
			 */
			default MediaWriter media() throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "media");
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_media.asp">HTML media Attribute</a>.
			 */
			default <Ex extends Throwable> E mediaE(AttributeWriterE<Ex> media) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attributeE(element, "media", media);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_media.asp">HTML media Attribute</a>.
			 */
			default E media(AttributeWriter media) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "media", media);
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_name.asp">HTML name Attribute</a>.
		 */
		public static interface Name<E extends Element<E> & Name<E>> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_name.asp">HTML name Attribute</a>.
			 */
			default E name(Object name) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "name", MarkupType.NONE, name, false, true);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_name.asp">HTML name Attribute</a>.
			 */
			default <Ex extends Throwable> E nameE(SupplierE<?,Ex> name) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attributeE(element, "name", MarkupType.NONE, name, false, true);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_name.asp">HTML name Attribute</a>.
			 */
			default E name(Supplier<?> name) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "name", MarkupType.NONE, name, false, true);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_name.asp">HTML name Attribute</a>.
			 */
			default MediaWriter name() throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "name");
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_name.asp">HTML name Attribute</a>.
			 */
			default <Ex extends Throwable> E nameE(AttributeWriterE<Ex> name) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attributeE(element, "name", name);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_name.asp">HTML name Attribute</a>.
			 */
			default E name(AttributeWriter name) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "name", name);
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
			default <Ex extends Throwable> E styleE(SupplierE<?,Ex> style) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				// TODO: MarkupType.CSS
				return attributeE(element, "style", MarkupType.JAVASCRIPT, style, true, true);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_style.asp">HTML Global style Attribute</a>.
			 * <blockquote>
			 * In HTML5, the style attribute can be used on <b>any</b> HTML element (it will validate on any HTML element. However, it is not necessarily useful).
			 * </blockquote>
			 */
			default E style(Supplier<?> style) throws IOException {
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
			default MediaWriter style() throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "style");
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_style.asp">HTML Global style Attribute</a>.
			 * <blockquote>
			 * In HTML5, the style attribute can be used on <b>any</b> HTML element (it will validate on any HTML element. However, it is not necessarily useful).
			 * </blockquote>
			 */
			default <Ex extends Throwable> E styleE(AttributeWriterE<Ex> style) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attributeE(element, "style", style);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_style.asp">HTML Global style Attribute</a>.
			 * <blockquote>
			 * In HTML5, the style attribute can be used on <b>any</b> HTML element (it will validate on any HTML element. However, it is not necessarily useful).
			 * </blockquote>
			 */
			default E style(AttributeWriter style) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "style", style);
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
			default <Ex extends Throwable> E styleE(SupplierE<?,Ex> style) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
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
			default E style(Supplier<?> style) throws IOException {
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
			default MediaWriter style() throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
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
			default <Ex extends Throwable> E styleE(AttributeWriterE<Ex> style) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
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
			default E style(AttributeWriter style) throws IOException {
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
			default <Ex extends Throwable> E titleE(SupplierE<?,Ex> title) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attributeE(element, "title", MarkupType.TEXT, title, true, true);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_title.asp">HTML Global title Attribute</a>.
			 * <blockquote>
			 * In HTML5, the title attribute can be used on <b>any</b> HTML element (it will validate on any HTML element. However, it is not necessarily useful).
			 * </blockquote>
			 */
			default E title(Supplier<?> title) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "title", MarkupType.TEXT, title, true, true);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_title.asp">HTML Global title Attribute</a>.
			 * <blockquote>
			 * In HTML5, the title attribute can be used on <b>any</b> HTML element (it will validate on any HTML element. However, it is not necessarily useful).
			 * </blockquote>
			 */
			default MediaWriter title() throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "title");
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_title.asp">HTML Global title Attribute</a>.
			 * <blockquote>
			 * In HTML5, the title attribute can be used on <b>any</b> HTML element (it will validate on any HTML element. However, it is not necessarily useful).
			 * </blockquote>
			 */
			default <Ex extends Throwable> E titleE(AttributeWriterE<Ex> title) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attributeE(element, "title", title);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_global_title.asp">HTML Global title Attribute</a>.
			 * <blockquote>
			 * In HTML5, the title attribute can be used on <b>any</b> HTML element (it will validate on any HTML element. However, it is not necessarily useful).
			 * </blockquote>
			 */
			default E title(AttributeWriter title) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "title", title);
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
			default <Ex extends Throwable> E titleE(SupplierE<?,Ex> title) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
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
			default E title(Supplier<?> title) throws IOException {
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
			default MediaWriter title() throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
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
			default <Ex extends Throwable> E titleE(AttributeWriterE<Ex> title) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
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
			default E title(AttributeWriter title) throws IOException {
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
			default E value(Object value) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "value", MarkupType.NONE, value, false, true);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_value.asp">HTML value Attribute</a>.
			 */
			default <Ex extends Throwable> E valueE(SupplierE<?,Ex> value) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attributeE(element, "value", MarkupType.NONE, value, false, true);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_value.asp">HTML value Attribute</a>.
			 */
			default E value(Supplier<?> value) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "value", MarkupType.NONE, value, false, true);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_value.asp">HTML value Attribute</a>.
			 */
			default MediaWriter value() throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "value");
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_value.asp">HTML value Attribute</a>.
			 */
			default <Ex extends Throwable> E valueE(AttributeWriterE<Ex> value) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attributeE(element, "value", value);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_value.asp">HTML value Attribute</a>.
			 */
			default E value(AttributeWriter value) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "value", value);
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

		static <E extends Element<E>> E attribute(E element, java.lang.String name, Object url) throws IOException {
			if(url != null) {
				if(url instanceof Supplier<?>) return attribute(element, name, (Supplier<?>)url);
				if(url instanceof SupplierE<?,?>) {
					try {
						return attributeE(element, name, (SupplierE<?,?>)url);
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

		static <E extends Element<E>,Ex extends Throwable> E attributeE(E element, java.lang.String name, SupplierE<?,Ex> url) throws IOException, Ex {
			return attribute(element, name, (url == null) ? null : url.get(element.html.serialization, element.html.doctype));
		}

		static <E extends Element<E>> E attribute(E element, java.lang.String name, MarkupType markupType, Supplier<?> url) throws IOException {
			return attributeE(element, name, url);
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_href.asp">HTML href Attribute</a>.
		 */
		public static interface Href<E extends Element<E> & Href<E>> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_href.asp">HTML href Attribute</a>.
			 */
			default E href(java.lang.String href) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "href", href);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_href.asp">HTML href Attribute</a>.
			 */
			default E href(Object href) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "href", href);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_href.asp">HTML href Attribute</a>.
			 */
			default <Ex extends Throwable> E hrefE(SupplierE<?,Ex> href) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attributeE(element, "href", href);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_href.asp">HTML href Attribute</a>.
			 */
			default E href(Supplier<?> href) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "href", href);
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_src.asp">HTML src Attribute</a>.
		 */
		public static interface Src<E extends Element<E> & Src<E>> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_src.asp">HTML src Attribute</a>.
			 */
			default E src(java.lang.String src) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "src", src);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_src.asp">HTML src Attribute</a>.
			 */
			default E src(Object src) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "src", src);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_src.asp">HTML src Attribute</a>.
			 */
			default <Ex extends Throwable> E srcE(SupplierE<?,Ex> src) throws IOException, Ex {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attributeE(element, "src", src);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_src.asp">HTML src Attribute</a>.
			 */
			default E src(Supplier<?> src) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return attribute(element, "src", src);
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
