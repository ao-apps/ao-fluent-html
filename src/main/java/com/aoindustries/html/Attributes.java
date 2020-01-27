/*
 * ao-fluent-html - Fluent Java DSL for high-performance HTML generation.
 * Copyright (C) 2019, 2020  AO Industries, Inc.
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
import com.aoindustries.encoding.MediaEncoder;
import com.aoindustries.encoding.MediaWriter;
import static com.aoindustries.encoding.TextInXhtmlAttributeEncoder.encodeTextInXhtmlAttribute;
import static com.aoindustries.encoding.TextInXhtmlAttributeEncoder.textInXhtmlAttributeEncoder;
import static com.aoindustries.html.ApplicationResources.accessor;
import com.aoindustries.lang.LocalizedIllegalArgumentException;
import com.aoindustries.util.StringUtility;
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
	 * Special value used in-place of return values that should result in an empty
	 * attribute (expected on {@link Serialization#SGML} only).
	 * This distinguishes from a return value of {@code null}, which causes the
	 * attribute to not be added at all.
	 * <p>
	 * In order to never conflict with an actual attribute value, this string is
	 * compared by identity, not by value.
	 * </p>
	 */
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
			default <Ex extends Throwable> E async(Supplier<? extends java.lang.Boolean,Ex> async) throws IOException, Ex {
				return async((async == null) ? null : async.get());
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_autofocus.asp">HTML autofocus Attribute</a>.
		 */
		public static interface Autofocus<E extends Element<E> & Autofocus<E>> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_autofocus.asp">HTML autofocus Attribute</a>.
			 */
			@Funnel
			default E autofocus(boolean autofocus) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				if(element.html.doctype != Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						accessor,
						"Attributes.onlySupportedInHtml5",
						element.html.doctype,
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
			default <Ex extends Throwable> E autofocus(Supplier<? extends java.lang.Boolean,Ex> autofocus) throws IOException, Ex {
				return autofocus((autofocus == null) ? null : autofocus.get());
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
			default <Ex extends Throwable> E checked(Supplier<? extends java.lang.Boolean,Ex> checked) throws IOException, Ex {
				return checked((checked == null) ? null : checked.get());
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
			default <Ex extends Throwable> E defer(Supplier<? extends java.lang.Boolean,Ex> defer) throws IOException, Ex {
				return defer((defer == null) ? null : defer.get());
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
			default <Ex extends Throwable> E disabled(Supplier<? extends java.lang.Boolean,Ex> disabled) throws IOException, Ex {
				return disabled((disabled == null) ? null : disabled.get());
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_multiple.asp">HTML multiple Attribute</a>.
		 */
		public static interface Multiple<E extends Element<E> & Multiple<E>> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_multiple.asp">HTML multiple Attribute</a>.
			 */
			@Funnel
			default E multiple(boolean multiple) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				if(element.html.doctype != Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						accessor,
						"Attributes.onlySupportedInHtml5",
						element.html.doctype,
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
			default <Ex extends Throwable> E multiple(Supplier<? extends java.lang.Boolean,Ex> multiple) throws IOException, Ex {
				return multiple((multiple == null) ? null : multiple.get());
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
			default <Ex extends Throwable> E noshade(Supplier<? extends java.lang.Boolean,Ex> noshade) throws IOException, Ex {
				return noshade((noshade == null) ? null : noshade.get());
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_readonly.asp">HTML readonly Attribute</a>.
		 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input#htmlattrdefreadonly">&lt;input&gt;: The Input (Form Input) element</a>.
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
			default <Ex extends Throwable> E readonly(Supplier<? extends java.lang.Boolean,Ex> readonly) throws IOException, Ex {
				return readonly((readonly == null) ? null : readonly.get());
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
			default <Ex extends Throwable> E selected(Supplier<? extends java.lang.Boolean,Ex> selected) throws IOException, Ex {
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
			return String.attribute(element, name, MarkupType.NONE, pixelsOrPercent, true, true);
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
			 *
			 * @see #width(java.lang.Integer)
			 */
			@SuppressWarnings("overloads")
			default <Ex extends Throwable> E width(Supplier<? extends java.lang.Integer,Ex> pixels) throws IOException, Ex {
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
			default <Ex extends Throwable> E width(StringSupplier<Ex> pixelsOrPercent) throws IOException, Ex {
				return width((pixelsOrPercent == null) ? null : pixelsOrPercent.get());
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
			return Text.attribute(element, name, MarkupType.JAVASCRIPT, script, true, true, javaScriptInXhtmlAttributeEncoder);
		}

		public static class Window {

			/** Make no instances. */
			private Window() {}

			/**
			 * See <a href="https://www.w3schools.com/tags/ev_onafterprint.asp">HTML onafterprint Event Attribute</a>.
			 */
			public static interface Onafterprint<E extends Element<E> & Onafterprint<E>> {

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onafterprint.asp">HTML onafterprint Event Attribute</a>.
				 */
				@Funnel
				default E onafterprint(Object onafterprint) throws IOException {
					@SuppressWarnings("unchecked") E element = (E)this;
					if(element.html.doctype != Doctype.HTML5) {
						throw new LocalizedIllegalArgumentException(
							accessor,
							"Attributes.onlySupportedInHtml5",
							element.html.doctype,
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
				default <Ex extends Throwable> E onafterprint(Supplier<?,Ex> onafterprint) throws IOException, Ex {
					return onafterprint((onafterprint == null) ? null : onafterprint.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onafterprint.asp">HTML onafterprint Event Attribute</a>.
				 *
				 * @see #onafterprint(java.lang.Object)
				 */
				default <Ex extends Throwable> E onafterprint(AttributeWriter<Ex> onafterprint) throws IOException, Ex {
					return onafterprint((Object)onafterprint);
				}
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/ev_onbeforeprint.asp">HTML onbeforeprint Event Attribute</a>.
			 */
			public static interface Onbeforeprint<E extends Element<E> & Onbeforeprint<E>> {

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onbeforeprint.asp">HTML onbeforeprint Event Attribute</a>.
				 */
				@Funnel
				default E onbeforeprint(Object onbeforeprint) throws IOException {
					@SuppressWarnings("unchecked") E element = (E)this;
					if(element.html.doctype != Doctype.HTML5) {
						throw new LocalizedIllegalArgumentException(
							accessor,
							"Attributes.onlySupportedInHtml5",
							element.html.doctype,
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
				default <Ex extends Throwable> E onbeforeprint(Supplier<?,Ex> onbeforeprint) throws IOException, Ex {
					return onbeforeprint((onbeforeprint == null) ? null : onbeforeprint.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onbeforeprint.asp">HTML onbeforeprint Event Attribute</a>.
				 *
				 * @see #onbeforeprint(java.lang.Object)
				 */
				default <Ex extends Throwable> E onbeforeprint(AttributeWriter<Ex> onbeforeprint) throws IOException, Ex {
					return onbeforeprint((Object)onbeforeprint);
				}
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/ev_onbeforeunload.asp">HTML onbeforeunload Event Attribute</a>.
			 */
			public static interface Onbeforeunload<E extends Element<E> & Onbeforeunload<E>> {

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
				default <Ex extends Throwable> E onbeforeunload(Supplier<?,Ex> onbeforeunload) throws IOException, Ex {
					return onbeforeunload((onbeforeunload == null) ? null : onbeforeunload.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onbeforeunload.asp">HTML onbeforeunload Event Attribute</a>.
				 *
				 * @see #onbeforeunload(java.lang.Object)
				 */
				default <Ex extends Throwable> E onbeforeunload(AttributeWriter<Ex> onbeforeunload) throws IOException, Ex {
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
			 * See <a href="https://www.w3schools.com/tags/ev_onerror.asp">HTML onerror Event Attribute</a>.
			 * See <a href="https://www.w3schools.com/jsref/event_onerror.asp">onerror Event</a>.
			 * See <a href="https://www.w3schools.com/tags/att_onerror.asp">HTML onerror Attribute</a>.
			 */
			public static interface Onerror<E extends Element<E> & Onerror<E>> {

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
				default <Ex extends Throwable> E onerror(Supplier<?,Ex> onerror) throws IOException, Ex {
					return onerror((onerror == null) ? null : onerror.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onerror.asp">HTML onerror Event Attribute</a>.
				 *
				 * @see #onerror(java.lang.Object)
				 */
				default <Ex extends Throwable> E onerror(AttributeWriter<Ex> onerror) throws IOException, Ex {
					return onerror((Object)onerror);
				}
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/ev_onhashchange.asp">HTML onhashchange Event Attribute</a>.
			 */
			public static interface Onhashchange<E extends Element<E> & Onhashchange<E>> {

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onhashchange.asp">HTML onhashchange Event Attribute</a>.
				 */
				@Funnel
				default E onhashchange(Object onhashchange) throws IOException {
					@SuppressWarnings("unchecked") E element = (E)this;
					if(element.html.doctype != Doctype.HTML5) {
						throw new LocalizedIllegalArgumentException(
							accessor,
							"Attributes.onlySupportedInHtml5",
							element.html.doctype,
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
				default <Ex extends Throwable> E onhashchange(Supplier<?,Ex> onhashchange) throws IOException, Ex {
					return onhashchange((onhashchange == null) ? null : onhashchange.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onhashchange.asp">HTML onhashchange Event Attribute</a>.
				 *
				 * @see #onhashchange(java.lang.Object)
				 */
				default <Ex extends Throwable> E onhashchange(AttributeWriter<Ex> onhashchange) throws IOException, Ex {
					return onhashchange((Object)onhashchange);
				}
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/ev_onload.asp">HTML onload Event Attribute</a>.
			 */
			public static interface Onload<E extends Element<E> & Onload<E>> {

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
				default <Ex extends Throwable> E onload(Supplier<?,Ex> onload) throws IOException, Ex {
					return onload((onload == null) ? null : onload.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onload.asp">HTML onload Event Attribute</a>.
				 *
				 * @see #onload(java.lang.Object)
				 */
				default <Ex extends Throwable> E onload(AttributeWriter<Ex> onload) throws IOException, Ex {
					return onload((Object)onload);
				}
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/ev_onmessage.asp">HTML onmessage Event Attribute</a>.
			 */
			public static interface Onmessage<E extends Element<E> & Onmessage<E>> {

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onmessage.asp">HTML onmessage Event Attribute</a>.
				 */
				@Funnel
				default E onmessage(Object onmessage) throws IOException {
					@SuppressWarnings("unchecked") E element = (E)this;
					if(element.html.doctype != Doctype.HTML5) {
						throw new LocalizedIllegalArgumentException(
							accessor,
							"Attributes.onlySupportedInHtml5",
							element.html.doctype,
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
				default <Ex extends Throwable> E onmessage(Supplier<?,Ex> onmessage) throws IOException, Ex {
					return onmessage((onmessage == null) ? null : onmessage.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onmessage.asp">HTML onmessage Event Attribute</a>.
				 *
				 * @see #onmessage(java.lang.Object)
				 */
				default <Ex extends Throwable> E onmessage(AttributeWriter<Ex> onmessage) throws IOException, Ex {
					return onmessage((Object)onmessage);
				}
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/ev_onoffline.asp">HTML onoffline Event Attribute</a>.
			 */
			public static interface Onoffline<E extends Element<E> & Onoffline<E>> {

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onoffline.asp">HTML onoffline Event Attribute</a>.
				 */
				@Funnel
				default E onoffline(Object onoffline) throws IOException {
					@SuppressWarnings("unchecked") E element = (E)this;
					if(element.html.doctype != Doctype.HTML5) {
						throw new LocalizedIllegalArgumentException(
							accessor,
							"Attributes.onlySupportedInHtml5",
							element.html.doctype,
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
				default <Ex extends Throwable> E onoffline(Supplier<?,Ex> onoffline) throws IOException, Ex {
					return onoffline((onoffline == null) ? null : onoffline.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onoffline.asp">HTML onoffline Event Attribute</a>.
				 *
				 * @see #onoffline(java.lang.Object)
				 */
				default <Ex extends Throwable> E onoffline(AttributeWriter<Ex> onoffline) throws IOException, Ex {
					return onoffline((Object)onoffline);
				}
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/ev_ononline.asp">HTML ononline Event Attribute</a>.
			 */
			public static interface Ononline<E extends Element<E> & Ononline<E>> {

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_ononline.asp">HTML ononline Event Attribute</a>.
				 */
				@Funnel
				default E ononline(Object ononline) throws IOException {
					@SuppressWarnings("unchecked") E element = (E)this;
					if(element.html.doctype != Doctype.HTML5) {
						throw new LocalizedIllegalArgumentException(
							accessor,
							"Attributes.onlySupportedInHtml5",
							element.html.doctype,
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
				default <Ex extends Throwable> E ononline(Supplier<?,Ex> ononline) throws IOException, Ex {
					return ononline((ononline == null) ? null : ononline.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_ononline.asp">HTML ononline Event Attribute</a>.
				 *
				 * @see #ononline(java.lang.Object)
				 */
				default <Ex extends Throwable> E ononline(AttributeWriter<Ex> ononline) throws IOException, Ex {
					return ononline((Object)ononline);
				}
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/ev_onpagehide.asp">HTML onpagehide Event Attribute</a>.
			 */
			public static interface Onpagehide<E extends Element<E> & Onpagehide<E>> {

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
				default <Ex extends Throwable> E onpagehide(Supplier<?,Ex> onpagehide) throws IOException, Ex {
					return onpagehide((onpagehide == null) ? null : onpagehide.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onpagehide.asp">HTML onpagehide Event Attribute</a>.
				 *
				 * @see #onpagehide(java.lang.Object)
				 */
				default <Ex extends Throwable> E onpagehide(AttributeWriter<Ex> onpagehide) throws IOException, Ex {
					return onpagehide((Object)onpagehide);
				}
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/ev_onpageshow.asp">HTML onpageshow Event Attribute</a>.
			 */
			public static interface Onpageshow<E extends Element<E> & Onpageshow<E>> {

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
				default <Ex extends Throwable> E onpageshow(Supplier<?,Ex> onpageshow) throws IOException, Ex {
					return onpageshow((onpageshow == null) ? null : onpageshow.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onpageshow.asp">HTML onpageshow Event Attribute</a>.
				 *
				 * @see #onpageshow(java.lang.Object)
				 */
				default <Ex extends Throwable> E onpageshow(AttributeWriter<Ex> onpageshow) throws IOException, Ex {
					return onpageshow((Object)onpageshow);
				}
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/ev_onpopstate.asp">HTML onpopstate Event Attribute</a>.
			 */
			public static interface Onpopstate<E extends Element<E> & Onpopstate<E>> {

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
				default <Ex extends Throwable> E onpopstate(Supplier<?,Ex> onpopstate) throws IOException, Ex {
					return onpopstate((onpopstate == null) ? null : onpopstate.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onpopstate.asp">HTML onpopstate Event Attribute</a>.
				 *
				 * @see #onpopstate(java.lang.Object)
				 */
				default <Ex extends Throwable> E onpopstate(AttributeWriter<Ex> onpopstate) throws IOException, Ex {
					return onpopstate((Object)onpopstate);
				}
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/ev_onresize.asp">HTML onresize Event Attribute</a>.
			 */
			public static interface Onresize<E extends Element<E> & Onresize<E>> {

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onresize.asp">HTML onresize Event Attribute</a>.
				 */
				@Funnel
				default E onresize(Object onresize) throws IOException {
					@SuppressWarnings("unchecked") E element = (E)this;
					if(element.html.doctype != Doctype.HTML5) {
						throw new LocalizedIllegalArgumentException(
							accessor,
							"Attributes.onlySupportedInHtml5",
							element.html.doctype,
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
				default <Ex extends Throwable> E onresize(Supplier<?,Ex> onresize) throws IOException, Ex {
					return onresize((onresize == null) ? null : onresize.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onresize.asp">HTML onresize Event Attribute</a>.
				 *
				 * @see #onresize(java.lang.Object)
				 */
				default <Ex extends Throwable> E onresize(AttributeWriter<Ex> onresize) throws IOException, Ex {
					return onresize((Object)onresize);
				}
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/ev_onstorage.asp">HTML onstorage Event Attribute</a>.
			 */
			public static interface Onstorage<E extends Element<E> & Onstorage<E>> {

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
				default <Ex extends Throwable> E onstorage(Supplier<?,Ex> onstorage) throws IOException, Ex {
					return onstorage((onstorage == null) ? null : onstorage.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onstorage.asp">HTML onstorage Event Attribute</a>.
				 *
				 * @see #onstorage(java.lang.Object)
				 */
				default <Ex extends Throwable> E onstorage(AttributeWriter<Ex> onstorage) throws IOException, Ex {
					return onstorage((Object)onstorage);
				}
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/ev_onunload.asp">HTML onunload Event Attribute</a>.
			 */
			public static interface Onunload<E extends Element<E> & Onunload<E>> {

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
				default <Ex extends Throwable> E onunload(Supplier<?,Ex> onunload) throws IOException, Ex {
					return onunload((onunload == null) ? null : onunload.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onunload.asp">HTML onunload Event Attribute</a>.
				 *
				 * @see #onunload(java.lang.Object)
				 */
				default <Ex extends Throwable> E onunload(AttributeWriter<Ex> onunload) throws IOException, Ex {
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
			public static interface Onblur<E extends Element<E> & Onblur<E>> {

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
				default <Ex extends Throwable> E onblur(Supplier<?,Ex> onblur) throws IOException, Ex {
					return onblur((onblur == null) ? null : onblur.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onblur.asp">HTML onblur Event Attribute</a>.
				 *
				 * @see #onblur(java.lang.Object)
				 */
				default <Ex extends Throwable> E onblur(AttributeWriter<Ex> onblur) throws IOException, Ex {
					return onblur((Object)onblur);
				}
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/ev_onchange.asp">HTML onchange Event Attribute</a>.
			 * See <a href="https://www.w3schools.com/jsref/event_onchange.asp">onchange Event</a>.
			 */
			public static interface Onchange<E extends Element<E> & Onchange<E>> {

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
				default <Ex extends Throwable> E onchange(Supplier<?,Ex> onchange) throws IOException, Ex {
					return onchange((onchange == null) ? null : onchange.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onchange.asp">HTML onchange Event Attribute</a>.
				 *
				 * @see #onchange(java.lang.Object)
				 */
				default <Ex extends Throwable> E onchange(AttributeWriter<Ex> onchange) throws IOException, Ex {
					return onchange((Object)onchange);
				}
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/ev_oncontextmenu.asp">HTML oncontextmenu Event Attribute</a>.
			 */
			public static interface Oncontextmenu<E extends Element<E> & Oncontextmenu<E>> {

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_oncontextmenu.asp">HTML oncontextmenu Event Attribute</a>.
				 */
				@Funnel
				default E oncontextmenu(Object oncontextmenu) throws IOException {
					@SuppressWarnings("unchecked") E element = (E)this;
					if(element.html.doctype != Doctype.HTML5) {
						throw new LocalizedIllegalArgumentException(
							accessor,
							"Attributes.onlySupportedInHtml5",
							element.html.doctype,
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
				default <Ex extends Throwable> E oncontextmenu(Supplier<?,Ex> oncontextmenu) throws IOException, Ex {
					return oncontextmenu((oncontextmenu == null) ? null : oncontextmenu.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_oncontextmenu.asp">HTML oncontextmenu Event Attribute</a>.
				 *
				 * @see #oncontextmenu(java.lang.Object)
				 */
				default <Ex extends Throwable> E oncontextmenu(AttributeWriter<Ex> oncontextmenu) throws IOException, Ex {
					return oncontextmenu((Object)oncontextmenu);
				}
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/ev_onfocus.asp">HTML onfocus Event Attribute</a>.
			 */
			public static interface Onfocus<E extends Element<E> & Onfocus<E>> {

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
				default <Ex extends Throwable> E onfocus(Supplier<?,Ex> onfocus) throws IOException, Ex {
					return onfocus((onfocus == null) ? null : onfocus.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onfocus.asp">HTML onfocus Event Attribute</a>.
				 *
				 * @see #onfocus(java.lang.Object)
				 */
				default <Ex extends Throwable> E onfocus(AttributeWriter<Ex> onfocus) throws IOException, Ex {
					return onfocus((Object)onfocus);
				}
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/ev_oninput.asp">HTML oninput Event Attribute</a>.
			 * See <a href="https://www.w3schools.com/jsref/event_oninput.asp">oninput Event</a>.
			 */
			public static interface Oninput<E extends Element<E> & Oninput<E>> {

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_oninput.asp">HTML oninput Event Attribute</a>.
				 */
				@Funnel
				default E oninput(Object oninput) throws IOException {
					@SuppressWarnings("unchecked") E element = (E)this;
					if(element.html.doctype != Doctype.HTML5) {
						throw new LocalizedIllegalArgumentException(
							accessor,
							"Attributes.onlySupportedInHtml5",
							element.html.doctype,
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
				default <Ex extends Throwable> E oninput(Supplier<?,Ex> oninput) throws IOException, Ex {
					return oninput((oninput == null) ? null : oninput.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_oninput.asp">HTML oninput Event Attribute</a>.
				 *
				 * @see #oninput(java.lang.Object)
				 */
				default <Ex extends Throwable> E oninput(AttributeWriter<Ex> oninput) throws IOException, Ex {
					return oninput((Object)oninput);
				}
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/ev_oninvalid.asp">HTML oninvalid Event Attribute</a>.
			 */
			public static interface Oninvalid<E extends Element<E> & Oninvalid<E>> {

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
				default <Ex extends Throwable> E oninvalid(Supplier<?,Ex> oninvalid) throws IOException, Ex {
					return oninvalid((oninvalid == null) ? null : oninvalid.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_oninvalid.asp">HTML oninvalid Event Attribute</a>.
				 *
				 * @see #oninvalid(java.lang.Object)
				 */
				default <Ex extends Throwable> E oninvalid(AttributeWriter<Ex> oninvalid) throws IOException, Ex {
					return oninvalid((Object)oninvalid);
				}
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/ev_onreset.asp">HTML onreset Event Attribute</a>.
			 */
			public static interface Onreset<E extends Element<E> & Onreset<E>> {

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onreset.asp">HTML onreset Event Attribute</a>.
				 */
				@Funnel
				default E onreset(Object onreset) throws IOException {
					@SuppressWarnings("unchecked") E element = (E)this;
					if(element.html.doctype != Doctype.HTML5) {
						throw new LocalizedIllegalArgumentException(
							accessor,
							"Attributes.onlySupportedInHtml5",
							element.html.doctype,
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
				default <Ex extends Throwable> E onreset(Supplier<?,Ex> onreset) throws IOException, Ex {
					return onreset((onreset == null) ? null : onreset.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onreset.asp">HTML onreset Event Attribute</a>.
				 *
				 * @see #onreset(java.lang.Object)
				 */
				default <Ex extends Throwable> E onreset(AttributeWriter<Ex> onreset) throws IOException, Ex {
					return onreset((Object)onreset);
				}
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/ev_onsearch.asp">HTML onsearch Event Attribute</a>.
			 * See <a href="https://www.w3schools.com/jsref/event_onsearch.asp">onsearch Event</a>.
			 */
			public static interface Onsearch<E extends Element<E> & Onsearch<E>> {

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
				default <Ex extends Throwable> E onsearch(Supplier<?,Ex> onsearch) throws IOException, Ex {
					return onsearch((onsearch == null) ? null : onsearch.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onsearch.asp">HTML onsearch Event Attribute</a>.
				 *
				 * @see #onsearch(java.lang.Object)
				 */
				default <Ex extends Throwable> E onsearch(AttributeWriter<Ex> onsearch) throws IOException, Ex {
					return onsearch((Object)onsearch);
				}
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/ev_onselect.asp">HTML onselect Event Attribute</a>.
			 * See <a href="https://www.w3schools.com/jsref/event_onselect.asp">onselect Event</a>.
			 * See <a href="https://developer.mozilla.org/en-US/docs/Web/API/GlobalEventHandlers/onselect">GlobalEventHandlers.onselect</a>.
			 */
			public static interface Onselect<E extends Element<E> & Onselect<E>> {

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
				default <Ex extends Throwable> E onselect(Supplier<?,Ex> onselect) throws IOException, Ex {
					return onselect((onselect == null) ? null : onselect.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onselect.asp">HTML onselect Event Attribute</a>.
				 *
				 * @see #onselect(java.lang.Object)
				 */
				default <Ex extends Throwable> E onselect(AttributeWriter<Ex> onselect) throws IOException, Ex {
					return onselect((Object)onselect);
				}
			}

			// TODO: onshow: https://www.w3schools.com/tags/ev_onshow.asp
			//               https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes
			//               (removed from HTML 5?)
			
			/**
			 * See <a href="https://www.w3schools.com/tags/ev_onsubmit.asp">HTML onsubmit Event Attribute</a>.
			 */
			public static interface Onsubmit<E extends Element<E> & Onsubmit<E>> {

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
				default <Ex extends Throwable> E onsubmit(Supplier<?,Ex> onsubmit) throws IOException, Ex {
					return onsubmit((onsubmit == null) ? null : onsubmit.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onsubmit.asp">HTML onsubmit Event Attribute</a>.
				 *
				 * @see #onsubmit(java.lang.Object)
				 */
				default <Ex extends Throwable> E onsubmit(AttributeWriter<Ex> onsubmit) throws IOException, Ex {
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
			public static interface AlmostGlobal<E extends Element<E> & AlmostGlobal<E>> extends
				Onblur<E>,
				Onfocus<E>
			{
				// No methods, just adding form event types
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/ref_eventattributes.asp">Form Events</a>.
			 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes">Global attributes</a>.
			 */
			public static interface Global<E extends Element<E> & Global<E>> extends
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
			public static interface Onkeydown<E extends Element<E> & Onkeydown<E>> {

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
				default <Ex extends Throwable> E onkeydown(Supplier<?,Ex> onkeydown) throws IOException, Ex {
					return onkeydown((onkeydown == null) ? null : onkeydown.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onkeydown.asp">HTML onkeydown Event Attribute</a>.
				 *
				 * @see #onkeydown(java.lang.Object)
				 */
				default <Ex extends Throwable> E onkeydown(AttributeWriter<Ex> onkeydown) throws IOException, Ex {
					return onkeydown((Object)onkeydown);
				}
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/ev_onkeypress.asp">HTML onkeypress Event Attribute</a>.
			 */
			public static interface Onkeypress<E extends Element<E> & Onkeypress<E>> {

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
				default <Ex extends Throwable> E onkeypress(Supplier<?,Ex> onkeypress) throws IOException, Ex {
					return onkeypress((onkeypress == null) ? null : onkeypress.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onkeypress.asp">HTML onkeypress Event Attribute</a>.
				 *
				 * @see #onkeypress(java.lang.Object)
				 */
				default <Ex extends Throwable> E onkeypress(AttributeWriter<Ex> onkeypress) throws IOException, Ex {
					return onkeypress((Object)onkeypress);
				}
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/ev_onkeyup.asp">HTML onkeyup Event Attribute</a>.
			 */
			public static interface Onkeyup<E extends Element<E> & Onkeyup<E>> {

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
				default <Ex extends Throwable> E onkeyup(Supplier<?,Ex> onkeyup) throws IOException, Ex {
					return onkeyup((onkeyup == null) ? null : onkeyup.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onkeyup.asp">HTML onkeyup Event Attribute</a>.
				 *
				 * @see #onkeyup(java.lang.Object)
				 */
				default <Ex extends Throwable> E onkeyup(AttributeWriter<Ex> onkeyup) throws IOException, Ex {
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
			public static interface AlmostGlobal<E extends Element<E> & AlmostGlobal<E>> extends
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
				 *
				 * @see #onclick(java.lang.Object)
				 */
				default <Ex extends Throwable> E onclick(Supplier<?,Ex> onclick) throws IOException, Ex {
					return onclick((onclick == null) ? null : onclick.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onclick.asp">HTML onclick Event Attribute</a>.
				 *
				 * @see #onclick(java.lang.Object)
				 */
				default <Ex extends Throwable> E onclick(AttributeWriter<Ex> onclick) throws IOException, Ex {
					return onclick((Object)onclick);
				}
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/ev_ondblclick.asp">HTML ondblclick Event Attribute</a>.
			 */
			public static interface Ondblclick<E extends Element<E> & Ondblclick<E>> {

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
				default <Ex extends Throwable> E ondblclick(Supplier<?,Ex> ondblclick) throws IOException, Ex {
					return ondblclick((ondblclick == null) ? null : ondblclick.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_ondblclick.asp">HTML ondblclick Event Attribute</a>.
				 *
				 * @see #ondblclick(java.lang.Object)
				 */
				default <Ex extends Throwable> E ondblclick(AttributeWriter<Ex> ondblclick) throws IOException, Ex {
					return ondblclick((Object)ondblclick);
				}
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/ev_onmousedown.asp">HTML onmousedown Event Attribute</a>.
			 */
			public static interface Onmousedown<E extends Element<E> & Onmousedown<E>> {

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
				default <Ex extends Throwable> E onmousedown(Supplier<?,Ex> onmousedown) throws IOException, Ex {
					return onmousedown((onmousedown == null) ? null : onmousedown.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onmousedown.asp">HTML onmousedown Event Attribute</a>.
				 *
				 * @see #onmousedown(java.lang.Object)
				 */
				default <Ex extends Throwable> E onmousedown(AttributeWriter<Ex> onmousedown) throws IOException, Ex {
					return onmousedown((Object)onmousedown);
				}
			}

			// TODO: onmouseenter: https://www.w3schools.com/jsref/event_onmouseenter.asp
			// TODO: onmouseleave: https://www.w3schools.com/jsref/event_onmouseleave.asp

			/**
			 * See <a href="https://www.w3schools.com/tags/ev_onmousemove.asp">HTML onmousemove Event Attribute</a>.
			 */
			public static interface Onmousemove<E extends Element<E> & Onmousemove<E>> {

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
				default <Ex extends Throwable> E onmousemove(Supplier<?,Ex> onmousemove) throws IOException, Ex {
					return onmousemove((onmousemove == null) ? null : onmousemove.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onmousemove.asp">HTML onmousemove Event Attribute</a>.
				 *
				 * @see #onmousemove(java.lang.Object)
				 */
				default <Ex extends Throwable> E onmousemove(AttributeWriter<Ex> onmousemove) throws IOException, Ex {
					return onmousemove((Object)onmousemove);
				}
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/ev_onmouseout.asp">HTML onmouseout Event Attribute</a>.
			 */
			public static interface Onmouseout<E extends Element<E> & Onmouseout<E>> {

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
				default <Ex extends Throwable> E onmouseout(Supplier<?,Ex> onmouseout) throws IOException, Ex {
					return onmouseout((onmouseout == null) ? null : onmouseout.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onmouseout.asp">HTML onmouseout Event Attribute</a>.
				 *
				 * @see #onmouseout(java.lang.Object)
				 */
				default <Ex extends Throwable> E onmouseout(AttributeWriter<Ex> onmouseout) throws IOException, Ex {
					return onmouseout((Object)onmouseout);
				}
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/ev_onmouseover.asp">HTML onmouseover Event Attribute</a>.
			 */
			public static interface Onmouseover<E extends Element<E> & Onmouseover<E>> {

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
				default <Ex extends Throwable> E onmouseover(Supplier<?,Ex> onmouseover) throws IOException, Ex {
					return onmouseover((onmouseover == null) ? null : onmouseover.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onmouseover.asp">HTML onmouseover Event Attribute</a>.
				 *
				 * @see #onmouseover(java.lang.Object)
				 */
				default <Ex extends Throwable> E onmouseover(AttributeWriter<Ex> onmouseover) throws IOException, Ex {
					return onmouseover((Object)onmouseover);
				}
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/ev_onmouseup.asp">HTML onmouseup Event Attribute</a>.
			 */
			public static interface Onmouseup<E extends Element<E> & Onmouseup<E>> {

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
				default <Ex extends Throwable> E onmouseup(Supplier<?,Ex> onmouseup) throws IOException, Ex {
					return onmouseup((onmouseup == null) ? null : onmouseup.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onmouseup.asp">HTML onmouseup Event Attribute</a>.
				 *
				 * @see #onmouseup(java.lang.Object)
				 */
				default <Ex extends Throwable> E onmouseup(AttributeWriter<Ex> onmouseup) throws IOException, Ex {
					return onmouseup((Object)onmouseup);
				}
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_onmousewheel.asp">HTML onmousewheel Attribute</a>.
			 *
			 * @deprecated  The onmousewheel attribute is deprecated, you should use the {@linkplain Onwheel onwheel} attribute instead.
			 */
			@Deprecated
			public static interface Onmousewheel<E extends Element<E> & Onmousewheel<E>> {

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
				 * @deprecated  The onmousewheel attribute is deprecated, you should use the {@linkplain Onwheel#onwheel(com.aoindustries.html.Supplier) onwheel} attribute instead.
				 */
				@Deprecated
				default <Ex extends Throwable> E onmousewheel(Supplier<?,Ex> onmousewheel) throws IOException, Ex {
					return onmousewheel((onmousewheel == null) ? null : onmousewheel.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/att_onmousewheel.asp">HTML onmousewheel Attribute</a>.
				 *
				 * @see #onmousewheel(java.lang.Object)
				 *
				 * @deprecated  The onmousewheel attribute is deprecated, you should use the {@linkplain Onwheel#onwheel(com.aoindustries.html.AttributeWriter) onwheel} attribute instead.
				 */
				@Deprecated
				default <Ex extends Throwable> E onmousewheel(AttributeWriter<Ex> onmousewheel) throws IOException, Ex {
					return onmousewheel((Object)onmousewheel);
				}
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/ev_onwheel.asp">HTML onwheel Event Attribute</a>.
			 */
			public static interface Onwheel<E extends Element<E> & Onwheel<E>> {

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onwheel.asp">HTML onwheel Event Attribute</a>.
				 */
				@Funnel
				default E onwheel(Object onwheel) throws IOException {
					@SuppressWarnings("unchecked") E element = (E)this;
					if(element.html.doctype != Doctype.HTML5) {
						throw new LocalizedIllegalArgumentException(
							accessor,
							"Attributes.onlySupportedInHtml5",
							element.html.doctype,
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
				default <Ex extends Throwable> E onwheel(Supplier<?,Ex> onwheel) throws IOException, Ex {
					return onwheel((onwheel == null) ? null : onwheel.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onwheel.asp">HTML onwheel Event Attribute</a>.
				 *
				 * @see #onwheel(java.lang.Object)
				 */
				default <Ex extends Throwable> E onwheel(AttributeWriter<Ex> onwheel) throws IOException, Ex {
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
			public static interface AlmostGlobal<E extends Element<E> & AlmostGlobal<E>> extends
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
			public static interface Global<E extends Element<E> & Global<E>> extends
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
			public static interface Ondrag<E extends Element<E> & Ondrag<E>> {

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_ondrag.asp">HTML ondrag Event Attribute</a>.
				 */
				@Funnel
				default E ondrag(Object ondrag) throws IOException {
					@SuppressWarnings("unchecked") E element = (E)this;
					if(element.html.doctype != Doctype.HTML5) {
						throw new LocalizedIllegalArgumentException(
							accessor,
							"Attributes.onlySupportedInHtml5",
							element.html.doctype,
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
				default <Ex extends Throwable> E ondrag(Supplier<?,Ex> ondrag) throws IOException, Ex {
					return ondrag((ondrag == null) ? null : ondrag.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_ondrag.asp">HTML ondrag Event Attribute</a>.
				 *
				 * @see #ondrag(java.lang.Object)
				 */
				default <Ex extends Throwable> E ondrag(AttributeWriter<Ex> ondrag) throws IOException, Ex {
					return ondrag((Object)ondrag);
				}
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/ev_ondragend.asp">HTML ondragend Event Attribute</a>.
			 */
			public static interface Ondragend<E extends Element<E> & Ondragend<E>> {

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_ondragend.asp">HTML ondragend Event Attribute</a>.
				 */
				@Funnel
				default E ondragend(Object ondragend) throws IOException {
					@SuppressWarnings("unchecked") E element = (E)this;
					if(element.html.doctype != Doctype.HTML5) {
						throw new LocalizedIllegalArgumentException(
							accessor,
							"Attributes.onlySupportedInHtml5",
							element.html.doctype,
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
				default <Ex extends Throwable> E ondragend(Supplier<?,Ex> ondragend) throws IOException, Ex {
					return ondragend((ondragend == null) ? null : ondragend.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_ondragend.asp">HTML ondragend Event Attribute</a>.
				 *
				 * @see #ondragend(java.lang.Object)
				 */
				default <Ex extends Throwable> E ondragend(AttributeWriter<Ex> ondragend) throws IOException, Ex {
					return ondragend((Object)ondragend);
				}
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/ev_ondragenter.asp">HTML ondragenter Event Attribute</a>.
			 */
			public static interface Ondragenter<E extends Element<E> & Ondragenter<E>> {

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_ondragenter.asp">HTML ondragenter Event Attribute</a>.
				 */
				@Funnel
				default E ondragenter(Object ondragenter) throws IOException {
					@SuppressWarnings("unchecked") E element = (E)this;
					if(element.html.doctype != Doctype.HTML5) {
						throw new LocalizedIllegalArgumentException(
							accessor,
							"Attributes.onlySupportedInHtml5",
							element.html.doctype,
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
				default <Ex extends Throwable> E ondragenter(Supplier<?,Ex> ondragenter) throws IOException, Ex {
					return ondragenter((ondragenter == null) ? null : ondragenter.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_ondragenter.asp">HTML ondragenter Event Attribute</a>.
				 *
				 * @see #ondragenter(java.lang.Object)
				 */
				default <Ex extends Throwable> E ondragenter(AttributeWriter<Ex> ondragenter) throws IOException, Ex {
					return ondragenter((Object)ondragenter);
				}
			}

			// ondragexit (Gecko specific - don't use or have a deprecated)

			/**
			 * See <a href="https://www.w3schools.com/tags/ev_ondragleave.asp">HTML ondragleave Event Attribute</a>.
			 */
			public static interface Ondragleave<E extends Element<E> & Ondragleave<E>> {

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_ondragleave.asp">HTML ondragleave Event Attribute</a>.
				 */
				@Funnel
				default E ondragleave(Object ondragleave) throws IOException {
					@SuppressWarnings("unchecked") E element = (E)this;
					if(element.html.doctype != Doctype.HTML5) {
						throw new LocalizedIllegalArgumentException(
							accessor,
							"Attributes.onlySupportedInHtml5",
							element.html.doctype,
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
				default <Ex extends Throwable> E ondragleave(Supplier<?,Ex> ondragleave) throws IOException, Ex {
					return ondragleave((ondragleave == null) ? null : ondragleave.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_ondragleave.asp">HTML ondragleave Event Attribute</a>.
				 *
				 * @see #ondragleave(java.lang.Object)
				 */
				default <Ex extends Throwable> E ondragleave(AttributeWriter<Ex> ondragleave) throws IOException, Ex {
					return ondragleave((Object)ondragleave);
				}
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/ev_ondragover.asp">HTML ondragover Event Attribute</a>.
			 */
			public static interface Ondragover<E extends Element<E> & Ondragover<E>> {

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_ondragover.asp">HTML ondragover Event Attribute</a>.
				 */
				@Funnel
				default E ondragover(Object ondragover) throws IOException {
					@SuppressWarnings("unchecked") E element = (E)this;
					if(element.html.doctype != Doctype.HTML5) {
						throw new LocalizedIllegalArgumentException(
							accessor,
							"Attributes.onlySupportedInHtml5",
							element.html.doctype,
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
				default <Ex extends Throwable> E ondragover(Supplier<?,Ex> ondragover) throws IOException, Ex {
					return ondragover((ondragover == null) ? null : ondragover.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_ondragover.asp">HTML ondragover Event Attribute</a>.
				 *
				 * @see #ondragover(java.lang.Object)
				 */
				default <Ex extends Throwable> E ondragover(AttributeWriter<Ex> ondragover) throws IOException, Ex {
					return ondragover((Object)ondragover);
				}
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/ev_ondragstart.asp">HTML ondragstart Event Attribute</a>.
			 */
			public static interface Ondragstart<E extends Element<E> & Ondragstart<E>> {

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_ondragstart.asp">HTML ondragstart Event Attribute</a>.
				 */
				@Funnel
				default E ondragstart(Object ondragstart) throws IOException {
					@SuppressWarnings("unchecked") E element = (E)this;
					if(element.html.doctype != Doctype.HTML5) {
						throw new LocalizedIllegalArgumentException(
							accessor,
							"Attributes.onlySupportedInHtml5",
							element.html.doctype,
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
				default <Ex extends Throwable> E ondragstart(Supplier<?,Ex> ondragstart) throws IOException, Ex {
					return ondragstart((ondragstart == null) ? null : ondragstart.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_ondragstart.asp">HTML ondragstart Event Attribute</a>.
				 *
				 * @see #ondragstart(java.lang.Object)
				 */
				default <Ex extends Throwable> E ondragstart(AttributeWriter<Ex> ondragstart) throws IOException, Ex {
					return ondragstart((Object)ondragstart);
				}
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/ev_ondrop.asp">HTML ondrop Event Attribute</a>.
			 */
			public static interface Ondrop<E extends Element<E> & Ondrop<E>> {

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_ondrop.asp">HTML ondrop Event Attribute</a>.
				 */
				@Funnel
				default E ondrop(Object ondrop) throws IOException {
					@SuppressWarnings("unchecked") E element = (E)this;
					if(element.html.doctype != Doctype.HTML5) {
						throw new LocalizedIllegalArgumentException(
							accessor,
							"Attributes.onlySupportedInHtml5",
							element.html.doctype,
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
				default <Ex extends Throwable> E ondrop(Supplier<?,Ex> ondrop) throws IOException, Ex {
					return ondrop((ondrop == null) ? null : ondrop.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_ondrop.asp">HTML ondrop Event Attribute</a>.
				 *
				 * @see #ondrop(java.lang.Object)
				 */
				default <Ex extends Throwable> E ondrop(AttributeWriter<Ex> ondrop) throws IOException, Ex {
					return ondrop((Object)ondrop);
				}
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/ev_onscroll.asp">HTML onscroll Event Attribute</a>.
			 */
			public static interface Onscroll<E extends Element<E> & Onscroll<E>> {

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onscroll.asp">HTML onscroll Event Attribute</a>.
				 */
				@Funnel
				default E onscroll(Object onscroll) throws IOException {
					@SuppressWarnings("unchecked") E element = (E)this;
					if(element.html.doctype != Doctype.HTML5) {
						throw new LocalizedIllegalArgumentException(
							accessor,
							"Attributes.onlySupportedInHtml5",
							element.html.doctype,
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
				default <Ex extends Throwable> E onscroll(Supplier<?,Ex> onscroll) throws IOException, Ex {
					return onscroll((onscroll == null) ? null : onscroll.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onscroll.asp">HTML onscroll Event Attribute</a>.
				 *
				 * @see #onscroll(java.lang.Object)
				 */
				default <Ex extends Throwable> E onscroll(AttributeWriter<Ex> onscroll) throws IOException, Ex {
					return onscroll((Object)onscroll);
				}
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/ref_eventattributes.asp">Drag Events</a>.
			 */
			public static interface Global<E extends Element<E> & Global<E>> extends
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
			public static interface Oncopy<E extends Element<E> & Oncopy<E>> {

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
				default <Ex extends Throwable> E oncopy(Supplier<?,Ex> oncopy) throws IOException, Ex {
					return oncopy((oncopy == null) ? null : oncopy.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_oncopy.asp">HTML oncopy Event Attribute</a>.
				 *
				 * @see #oncopy(java.lang.Object)
				 */
				default <Ex extends Throwable> E oncopy(AttributeWriter<Ex> oncopy) throws IOException, Ex {
					return oncopy((Object)oncopy);
				}
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/ev_oncut.asp">HTML oncut Event Attribute</a>.
			 */
			public static interface Oncut<E extends Element<E> & Oncut<E>> {

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
				default <Ex extends Throwable> E oncut(Supplier<?,Ex> oncut) throws IOException, Ex {
					return oncut((oncut == null) ? null : oncut.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_oncut.asp">HTML oncut Event Attribute</a>.
				 *
				 * @see #oncut(java.lang.Object)
				 */
				default <Ex extends Throwable> E oncut(AttributeWriter<Ex> oncut) throws IOException, Ex {
					return oncut((Object)oncut);
				}
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/ev_onpaste.asp">HTML onpaste Event Attribute</a>.
			 */
			public static interface Onpaste<E extends Element<E> & Onpaste<E>> {

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
				default <Ex extends Throwable> E onpaste(Supplier<?,Ex> onpaste) throws IOException, Ex {
					return onpaste((onpaste == null) ? null : onpaste.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/ev_onpaste.asp">HTML onpaste Event Attribute</a>.
				 *
				 * @see #onpaste(java.lang.Object)
				 */
				default <Ex extends Throwable> E onpaste(AttributeWriter<Ex> onpaste) throws IOException, Ex {
					return onpaste((Object)onpaste);
				}
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/ref_eventattributes.asp">Drag Events</a>.
			 */
			public static interface Global<E extends Element<E> & Global<E>> extends
				Oncopy<E>,
				Oncut<E>,
				Onpaste<E>
			{
				// No methods, just adding mouse event types
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/ref_eventattributes.asp">Media Events</a>.
		 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes">Global attributes</a>.
		 */
		public static class Media {

			/** Make no instances. */
			private Media() {}

			// TODO: onabort
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
		public static interface AlmostGlobal<E extends Element<E> & AlmostGlobal<E>> extends
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
	 * When converting from {@link java.lang.Enum}, uses {@link EnumSupplier#get(com.aoindustries.html.Serialization, com.aoindustries.html.Doctype)}.
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
			E extends Element<E> & Align<E,V>,
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
				if(element.html.doctype == Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						accessor,
						"Attributes.notSupportedInHtml5",
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
			default <Ex extends Throwable> E align(StringSupplier<Ex> align) throws IOException, Ex {
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
				return align((align == null) ? null : align.get(element.html.serialization, element.html.doctype));
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
			default <Ex extends Throwable> E align(Supplier<? extends V,Ex> align) throws IOException, Ex {
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
			E extends Element<E> & Autocomplete<E,V>,
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
				if(element.html.doctype == Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						accessor,
						"Attributes.notSupportedInHtml5",
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
			default <Ex extends Throwable> E autocomplete(StringSupplier<Ex> autocomplete) throws IOException, Ex {
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
				return autocomplete((autocomplete == null) ? null : autocomplete.get(element.html.serialization, element.html.doctype));
			}

			/**
			 * <ul>
			 * <li>See <a href="https://www.w3schools.com/tags/att_autocomplete.asp">HTML autocomplete Attribute</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input#htmlattrdefautocomplete">&lt;input&gt;: The Input (Form Input) element</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Attributes/autocomplete">The HTML autocomplete attribute</a>.</li>
			 * </ul>
			 */
			@SuppressWarnings("overloads")
			default <Ex extends Throwable> E autocomplete(Supplier<? extends V,Ex> autocomplete) throws IOException, Ex {
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
				if(element.html.doctype == Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						accessor,
						"Attributes.notSupportedInHtml5",
						"autocomplete"
					);
				}
				if(autocomplete != null) {
					boolean didOne = false;
					for(java.lang.String value : autocomplete) {
						value = StringUtility.trimNullIfEmpty(value);
						if(value != null) {
							if(!didOne) {
								element.html.out.write(" autocomplete=\"");
								didOne = true;
							} else {
								element.html.out.write(' ');
							}
							encodeTextInXhtmlAttribute(value, element.html.out);
						}
					}
					if(didOne) element.html.out.write('"');
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
			@SuppressWarnings("unchecked")
			default E autocomplete(V ... autocomplete) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				if(element.html.doctype == Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						accessor,
						"Attributes.notSupportedInHtml5",
						"autocomplete"
					);
				}
				if(autocomplete != null) {
					boolean didOne = false;
					for(V value : autocomplete) {
						if(value != null) {
							if(!didOne) {
								element.html.out.write(" autocomplete=\"");
								didOne = true;
							} else {
								element.html.out.write(' ');
							}
							encodeTextInXhtmlAttribute(value.get(element.html.serialization, element.html.doctype), element.html.out);
						}
					}
					if(didOne) element.html.out.write('"');
				}
				return element;
			}

			// TODO: More variants for suppliers of arrays, arrays of suppliers, iterables, ...?

			/**
			 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Attributes/autocomplete">The HTML autocomplete attribute</a>.
			 */
			public enum Value implements EnumSupplier {
				OFF("off"),
				ON("on"),
				NAME("name"),
				HONORIFIC_PREFIX("honorific-prefix"),
				GIVEN_NAME("given-name"),
				ADDITIONAL_NAME("additional-name"),
				FAMILY_NAME("family-name"),
				HONORIFIC_SUFFIX("honorific-suffix"),
				NICKNAME("nickname"),
				EMAIL("email"),
				USERNAME("username"),
				NEW_PASSWORD("new-password"),
				CURRENT_PASSWORD("current-password"),
				ONE_TIME_CODE("one-time-code"),
				ORGANIZATION_TITLE("organization-title"),
				ORGANIZATION("organization"),
				STREET_ADDRESS("street-address"),
				ADDRESS_LINE1("address-line1"),
				ADDRESS_LINE2("address-line2"),
				ADDRESS_LINE3("address-line3"),
				ADDRESS_LEVEL4("address-level4"),
				ADDRESS_LEVEL3("address-level3"),
				ADDRESS_LEVEL2("address-level2"),
				ADDRESS_LEVEL1("address-level1"),
				COUNTRY("country"),
				COUNTRY_NAME("country-name"),
				POSTAL_CODE("postal-code"),
				CC_NAME("cc-name"),
				CC_GIVEN_NAME("cc-given-name"),
				CC_ADDITIONAL_NAME("cc-additional-name"),
				CC_FAMILY_NAME("cc-family-name"),
				CC_NUMBER("cc-number"),
				CC_EXP("cc-exp"),
				CC_EXP_MONTH("cc-exp-month"),
				CC_EXP_YEAR("cc-exp-year"),
				CC_CSC("cc-csc"),
				CC_TYPE("cc-type"),
				TRANSACTION_CURRENCY("transaction-currency"),
				TRANSACTION_AMOUNT("transaction-amount"),
				LANGUAGE("language"),
				BDAY("bday"),
				BDAY_DAY("bday-day"),
				BDAY_MONTH("bday-month"),
				BDAY_YEAR("bday-year"),
				SEX("sex"),
				TEL("tel"),
				TEL_COUNTRY_CODE("tel-country-code"),
				TEL_NATIONAL("tel-national"),
				TEL_AREA_CODE("tel-area-code"),
				TEL_LOCAL("tel-local"),
				TEL_LOCAL_PREFIX("tel-local-prefix"),
				TEL_LOCAL_SUFFIX("tel-local-suffix"),
				TEL_EXTENSION("tel-extension"),
				IMPP("impp"),
				URL("url"),
				PHOTO("photo");

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
			E extends Element<E> & Capture<E,V>,
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
			default <Ex extends Throwable> E capture(StringSupplier<Ex> capture) throws IOException, Ex {
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
				return capture((capture == null) ? null : capture.get(element.html.serialization, element.html.doctype));
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
			default <Ex extends Throwable> E capture(Supplier<? extends V,Ex> capture) throws IOException, Ex {
				return capture((capture== null) ? (V)null : capture.get());
			}
		}

		/**
		 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/CORS_settings_attributes">The crossorigin attribute: Requesting CORS access to content</a>.
		 */
		public static interface Crossorigin<
			E extends Element<E> & Crossorigin<E,V>,
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
			default <Ex extends Throwable> E crossorigin(StringSupplier<Ex> crossorigin) throws IOException, Ex {
				return crossorigin((crossorigin == null) ? null : crossorigin.get());
			}

			/**
			 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/CORS_settings_attributes">The crossorigin attribute: Requesting CORS access to content</a>.
			 *
			 * @see #crossorigin(java.lang.String)
			 */
			default E crossorigin(V crossorigin) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return crossorigin((crossorigin == null) ? null : crossorigin.get(element.html.serialization, element.html.doctype));
			}

			/**
			 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/CORS_settings_attributes">The crossorigin attribute: Requesting CORS access to content</a>.
			 *
			 * @see #crossorigin(java.lang.Enum)
			 */
			@SuppressWarnings("overloads")
			default <Ex extends Throwable> E crossorigin(Supplier<? extends V,Ex> crossorigin) throws IOException, Ex {
				return crossorigin((crossorigin== null) ? (V)null : crossorigin.get());
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_rel.asp">HTML rel Attribute</a>.
		 */
		public static interface Rel<
			E extends Element<E> & Rel<E,V>,
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
			default <Ex extends Throwable> E rel(StringSupplier<Ex> rel) throws IOException, Ex {
				return rel((rel == null) ? null : rel.get());
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_rel.asp">HTML rel Attribute</a>.
			 *
			 * @see #rel(java.lang.String)
			 */
			default E rel(V rel) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return rel((rel == null) ? null : rel.get(element.html.serialization, element.html.doctype));
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_rel.asp">HTML rel Attribute</a>.
			 *
			 * @see #rel(java.lang.Enum)
			 */
			@SuppressWarnings("overloads")
			default <Ex extends Throwable> E rel(Supplier<? extends V,Ex> rel) throws IOException, Ex {
				return rel((rel== null) ? (V)null : rel.get());
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_type.asp">HTML type Attribute</a>.
		 */
		public static interface Type<
			E extends Element<E> & Type<E,V>,
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
			default <Ex extends Throwable> E type(StringSupplier<Ex> type) throws IOException, Ex {
				return type((type == null) ? null : type.get());
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_type.asp">HTML type Attribute</a>.
			 *
			 * @see #type(java.lang.String)
			 */
			default E type(V type) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				return type((type == null) ? null : type.get(element.html.serialization, element.html.doctype));
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_type.asp">HTML type Attribute</a>.
			 *
			 * @see #type(java.lang.Enum)
			 */
			@SuppressWarnings("overloads")
			default <Ex extends Throwable> E type(Supplier<? extends V,Ex> type) throws IOException, Ex {
				return type((type== null) ? (V)null : type.get());
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
			 *
			 * @see #height(java.lang.Integer)
			 */
			default <Ex extends Throwable> E height(Supplier<? extends java.lang.Integer,Ex> pixels) throws IOException, Ex {
				return height((pixels == null) ? null : pixels.get());
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
			 *
			 * @see #height(java.lang.Integer)
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
		 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input#htmlattrdefmaxlength">&lt;input&gt;: The Input (Form Input) element</a>.
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
			 *
			 * @see #maxlength(java.lang.Integer)
			 */
			default <Ex extends Throwable> E maxlength(Supplier<? extends java.lang.Integer,Ex> maxlength) throws IOException, Ex {
				return maxlength((maxlength == null) ? null : maxlength.get());
			}
		}

		/**
		 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input#htmlattrdefminlength">&lt;input&gt;: The Input (Form Input) element</a>.
		 */
		public static interface Minlength<E extends Element<E> & Minlength<E>> {

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
			default <Ex extends Throwable> E minlength(Supplier<? extends java.lang.Integer,Ex> minlength) throws IOException, Ex {
				return minlength((minlength == null) ? null : minlength.get());
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
			 *
			 * @see #size(java.lang.Integer)
			 */
			default <Ex extends Throwable> E size(Supplier<? extends java.lang.Integer,Ex> size) throws IOException, Ex {
				return size((size == null) ? null : size.get());
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
			 * @see #size(java.lang.Integer)
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
			 *
			 * @see #tabindex(java.lang.Integer)
			 */
			default <Ex extends Throwable> E tabindex(Supplier<? extends java.lang.Integer,Ex> tabindex) throws IOException, Ex {
				return tabindex((tabindex == null) ? null : tabindex.get());
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
			 *
			 * @see #tabindex(java.lang.Integer)
			 */
			@Override
			default <Ex extends Throwable> E tabindex(Supplier<? extends java.lang.Integer,Ex> tabindex) throws IOException, Ex {
				return tabindex((tabindex == null) ? null : tabindex.get());
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
			 *
			 * @see #width(java.lang.Integer)
			 */
			default <Ex extends Throwable> E width(Supplier<? extends java.lang.Integer,Ex> pixels) throws IOException, Ex {
				return width((pixels == null) ? null : pixels.get());
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_width.asp">HTML width Attribute</a>.
		 * <p>
		 * The width attribute is new in HTML5.
		 * </p>
		 */
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
			 *
			 * @see #width(java.lang.Integer)
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
		 * @param value  If is {@link #NO_VALUE} (by identity), will write empty attribute.
		 */
		static <E extends Element<E>> E attribute(E element, java.lang.String name, MarkupType markupType, java.lang.String value, boolean trim, boolean nullIfEmpty) throws IOException {
			if(value != null) {
				if(value == NO_VALUE) { // Identity comparison for marker value
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

		// TODO: Move some non-streamable attributes from Text to here?
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
		static <E extends Element<E>,Ex extends Throwable> E attribute(E element, java.lang.String name, MarkupType markupType, Object value, boolean trim, boolean nullIfEmpty, MediaEncoder encoder) throws IOException, Ex {
			while(value instanceof Supplier<?,?>) {
				@SuppressWarnings("unchecked")
				Supplier<?,Ex> supplier = (Supplier<?,Ex>)value;
				value = supplier.get();
			}
			if(value != null) {
				if(value instanceof AttributeWriter<?>) {
					@SuppressWarnings("unchecked")
					AttributeWriter<Ex> writer = (AttributeWriter<Ex>)value;
					element.html.out.write(' ');
					element.html.out.write(name);
					element.html.out.write("=\"");
					writer.writeAttribute(
						new MediaWriter(encoder, element.html.out) {
							@Override
							public void close() throws IOException {
								// Do not close underlying writer
							}
						}
					);
					element.html.out.write('"');
				} else {
					if(value == NO_VALUE) { // Identity comparison for marker value
						// Empty attribute
						element.html.out.write(' ');
						element.html.out.write(name);
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
							element.html.out.write(' ');
							element.html.out.write(name);
							element.html.out.write("=\"");
							Coercion.write(value, markupType, encoder, false, element.html.out);
							element.html.out.write('"');
						}
					}
				}
			}
			return element;
		}

		/**
		 * An arbitrary attribute.
		 */
		public static interface Attribute<E extends Element<E> & Attribute<E>> {

			/**
			 * An arbitrary attribute.
			 *
			 * @param value  The attribute value, {@link #NO_VALUE} (by identity, not value) for an empty attribute, {@code null} for no attribute.
			 */
			@Funnel
			default E attribute(java.lang.String name, Object value) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				// TODO: Validate attribute name? https://dev.w3.org/html5/html-author/#attributes
				return Text.attribute(element, name, MarkupType.NONE, value, false, false, textInXhtmlAttributeEncoder);
			}

			/**
			 * An arbitrary attribute.
			 *
			 * @param value  The attribute value, {@link #NO_VALUE} (by identity, not value) for an empty attribute, {@code null} for no attribute.
			 *
			 * @see #attribute(java.lang.String, java.lang.Object)
			 */
			default <Ex extends Throwable> E attribute(java.lang.String name, Supplier<?,Ex> value) throws IOException, Ex {
				return attribute(name, (value == null) ? null : value.get());
			}

			/**
			 * An arbitrary attribute.
			 *
			 * @see #attribute(java.lang.String, java.lang.Object)
			 */
			default <Ex extends Throwable> E attribute(java.lang.String name, AttributeWriter<Ex> value) throws IOException, Ex {
				return attribute(name, (Object)value);
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_accept.asp">HTML accept Attribute</a>.
		 */
		public static interface Accept<E extends Element<E> & Accept<E>> {

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
			default <Ex extends Throwable> E accept(Supplier<?,Ex> accept) throws IOException, Ex {
				return accept((accept == null) ? null : accept.get());
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_accept.asp">HTML accept Attribute</a>.
			 *
			 * @see #accept(java.lang.Object)
			 */
			default <Ex extends Throwable> E accept(AttributeWriter<Ex> accept) throws IOException, Ex {
				return accept((Object)accept);
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_alt.asp">HTML alt Attribute</a>.
		 */
		public static interface Alt<E extends Element<E> & Alt<E>> {

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
			default <Ex extends Throwable> E alt(Supplier<?,Ex> alt) throws IOException, Ex {
				return alt((alt == null) ? null : alt.get());
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_alt.asp">HTML alt Attribute</a>.
			 *
			 * @see #alt(java.lang.Object)
			 */
			default <Ex extends Throwable> E alt(AttributeWriter<Ex> alt) throws IOException, Ex {
				return alt((Object)alt);
			}
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
			default <Ex extends Throwable> E clazz(Supplier<?,Ex> clazz) throws IOException, Ex {
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
			 *
			 * @see #clazz(java.lang.Object)
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
			 *
			 * @see #clazz(java.lang.Object)
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
			default <Ex extends Throwable> E id(Supplier<?,Ex> id) throws IOException, Ex {
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
			 *
			 * @see #id(java.lang.Object)
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
			 *
			 * @see #id(java.lang.Object)
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
				return attribute(element, "label", MarkupType.TEXT, label, false, false, textInXhtmlAttributeEncoder);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_label.asp">HTML label Attribute</a>.
			 *
			 * @see #label(java.lang.Object)
			 */
			default <Ex extends Throwable> E label(Supplier<?,Ex> label) throws IOException, Ex {
				return label((label == null) ? null : label.get());
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_label.asp">HTML label Attribute</a>.
			 *
			 * @see #label(java.lang.Object)
			 */
			default <Ex extends Throwable> E label(AttributeWriter<Ex> label) throws IOException, Ex {
				return label((Object)label);
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_list.asp">HTML list Attribute</a>.
		 * See <a href="https://www.w3schools.com/tags/att_input_list.asp">HTML input list Attribute</a>.
		 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input#htmlattrdeflist">&lt;input&gt;: The Input (Form Input) element</a>.
		 */
		public static interface List<E extends Element<E> & List<E>> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_list.asp">HTML list Attribute</a>.
			 * See <a href="https://www.w3schools.com/tags/att_input_list.asp">HTML input list Attribute</a>.
			 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input#htmlattrdeflist">&lt;input&gt;: The Input (Form Input) element</a>.
			 */
			@Funnel
			default E list(Object list) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				if(element.html.doctype != Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						accessor,
						"Attributes.onlySupportedInHtml5",
						element.html.doctype,
						"list"
					);
				}
				return attribute(element, "list", MarkupType.NONE, list, true, true, textInXhtmlAttributeEncoder);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_list.asp">HTML list Attribute</a>.
			 * See <a href="https://www.w3schools.com/tags/att_input_list.asp">HTML input list Attribute</a>.
			 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input#htmlattrdeflist">&lt;input&gt;: The Input (Form Input) element</a>.
			 *
			 * @see #list(java.lang.Object)
			 */
			default <Ex extends Throwable> E list(Supplier<?,Ex> list) throws IOException, Ex {
				return list((list == null) ? null : list.get());
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_list.asp">HTML list Attribute</a>.
			 * See <a href="https://www.w3schools.com/tags/att_input_list.asp">HTML input list Attribute</a>.
			 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input#htmlattrdeflist">&lt;input&gt;: The Input (Form Input) element</a>.
			 *
			 * @see #list(java.lang.Object)
			 */
			default <Ex extends Throwable> E list(AttributeWriter<Ex> list) throws IOException, Ex {
				return list((Object)list);
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
				return attribute(element, "media", MarkupType.NONE, media, true, true, textInXhtmlAttributeEncoder);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_media.asp">HTML media Attribute</a>.
			 *
			 * @see #media(java.lang.Object)
			 */
			default <Ex extends Throwable> E media(Supplier<?,Ex> media) throws IOException, Ex {
				return media((media == null) ? null : media.get());
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_media.asp">HTML media Attribute</a>.
			 *
			 * @see #media(java.lang.Object)
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
				return attribute(element, "name", MarkupType.NONE, name, false, true, textInXhtmlAttributeEncoder);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_name.asp">HTML name Attribute</a>.
			 *
			 * @see #name(java.lang.Object)
			 */
			default <Ex extends Throwable> E name(Supplier<?,Ex> name) throws IOException, Ex {
				return name((name == null) ? null : name.get());
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_name.asp">HTML name Attribute</a>.
			 *
			 * @see #name(java.lang.Object)
			 */
			default <Ex extends Throwable> E name(AttributeWriter<Ex> name) throws IOException, Ex {
				return name((Object)name);
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_placeholder.asp">HTML placeholder Attribute</a>.
		 */
		public static interface Placeholder<E extends Element<E> & Placeholder<E>> {

			/**
			 * See <a href="https://www.w3schools.com/tags/att_placeholder.asp">HTML placeholder Attribute</a>.
			 */
			@Funnel
			default E placeholder(Object placeholder) throws IOException {
				@SuppressWarnings("unchecked") E element = (E)this;
				if(element.html.doctype != Doctype.HTML5) {
					throw new LocalizedIllegalArgumentException(
						accessor,
						"Attributes.onlySupportedInHtml5",
						element.html.doctype,
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
			default <Ex extends Throwable> E placeholder(Supplier<?,Ex> placeholder) throws IOException, Ex {
				return placeholder((placeholder == null) ? null : placeholder.get());
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_placeholder.asp">HTML placeholder Attribute</a>.
			 *
			 * @see #placeholder(java.lang.Object)
			 */
			default <Ex extends Throwable> E placeholder(AttributeWriter<Ex> placeholder) throws IOException, Ex {
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
			default <Ex extends Throwable> E style(Supplier<?,Ex> style) throws IOException, Ex {
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
			 *
			 * @see #style(java.lang.Object)
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
			 *
			 * @see #style(java.lang.Object)
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
			default <Ex extends Throwable> E title(Supplier<?,Ex> title) throws IOException, Ex {
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
			 *
			 * @see #title(java.lang.Object)
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
			 *
			 * @see #title(java.lang.Object)
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
				return attribute(element, "value", MarkupType.NONE, value, false, false, textInXhtmlAttributeEncoder);
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_value.asp">HTML value Attribute</a>.
			 *
			 * @see #value(java.lang.Object)
			 */
			default <Ex extends Throwable> E value(Supplier<?,Ex> value) throws IOException, Ex {
				return value((value == null) ? null : value.get());
			}

			/**
			 * See <a href="https://www.w3schools.com/tags/att_value.asp">HTML value Attribute</a>.
			 *
			 * @see #value(java.lang.Object)
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
			 *
			 * @see #href(java.lang.String)
			 */
			default <Ex extends Throwable> E href(Supplier<? extends java.lang.String,Ex> href) throws IOException, Ex {
				return href((href == null) ? null : href.get());
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
			 *
			 * @see #src(java.lang.String)
			 */
			default <Ex extends Throwable> E src(Supplier<? extends java.lang.String,Ex> src) throws IOException, Ex {
				return src((src == null) ? null : src.get());
			}
		}
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/ref_standardattributes.asp">HTML Global attributes</a>.
	 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes">Global attributes</a>.
	 */
	public static interface Global<E extends Element<E> & Global<E>> extends
		// TODO: accesskey
		// TODO: autocapitalize
		Text.Class<E>,
		// TODO: contenteditable
		// TODO: contextmenu (deprecated)
		// TODO: data-* (like Text.Attribute, but automatically adds "data-"? https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes/data-*
		//                                                                    https://developer.mozilla.org/en-US/docs/Learn/HTML/Howto/Use_data_attributes
		// TODO: dir
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
