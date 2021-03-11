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

import com.aoindustries.collections.AoCollections;
import com.aoindustries.encoding.Doctype;
import static com.aoindustries.encoding.TextInXhtmlAttributeEncoder.encodeTextInXhtmlAttribute;
import static com.aoindustries.encoding.TextInXhtmlAttributeEncoder.textInXhtmlAttributeEncoder;
import com.aoindustries.lang.LocalizedIllegalArgumentException;
import com.aoindustries.lang.LocalizedIllegalStateException;
import com.aoindustries.lang.Strings;
import com.aoindustries.util.i18n.MarkupType;
import java.io.IOException;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;
import java.util.function.Function;

/**
 * <p>
 * This has the set of attributes common to all input types.  There are also
 * type-specific subclasses that add type-specific attributes.  Furthermore,
 * there is a {@link INPUT.Dynamic} implementation that has all the input attributes,
 * supporting unanticipated or more dynamic configurations.
 * </p>
 * <ul>
 * <li>See <a href="https://html.spec.whatwg.org/multipage/input.html#the-input-element">4.10.5 The input element</a>.</li>
 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input">&lt;input&gt;: The Input (Form Input) element</a>.</li>
 * <li>See <a href="https://www.w3schools.com/tags/tag_input.asp">HTML input tag</a>.</li>
 * </ul>
 *
 * @param  <D>   This document type
 * @param  <PC>  The parent content model this element is within
 * @param  <E>   This element type
 *
 * @author  AO Industries, Inc.
 */
public abstract class INPUT<
	D  extends AnyDocument<D>,
	PC extends Union_Interactive_Phrasing<D, PC>,
	E  extends INPUT<D, PC, E>
> extends VoidElement<D, PC, E> implements
	com.aoindustries.html.attributes.Boolean.Autofocus<E>,
	// TODO: dirname
	com.aoindustries.html.attributes.Boolean.Disabled<E>,
	// TODO: form (only type "submit" and "image"?)
	// TODO: inputmode here or global?
	// TODO: list
	// TODO: max
	// TODO: min
	com.aoindustries.html.attributes.Text.Name<E>,
	// TODO: pattern
	com.aoindustries.html.attributes.Boolean.Required<E>,
	// TODO: step
	// Global Attributes: https://www.w3schools.com/tags/ref_standardattributes.asp
	com.aoindustries.html.attributes.Integer.TabindexHtml4<E>,
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	AlmostGlobalAttributes<E>,
	com.aoindustries.html.attributes.event.form.Oninvalid<E>
{

	private static final com.aoindustries.i18n.Resources RESOURCES =
		com.aoindustries.i18n.Resources.getResources(INPUT.class);

	public INPUT(D document, PC pc) {
		super(document, pc);
	}

	@Override
	protected E writeOpen(Writer out) throws IOException {
		document.autoIndent(out).unsafe(out, "<input", false);
		openWriteType(out);
		@SuppressWarnings("unchecked") E element = (E)this;
		return element;
	}

	protected abstract void openWriteType(Writer out) throws IOException;

	/**
	 * <p>
	 * The complete list of expected autocomplete values.  Specific input types may provide
	 * a shorter list.
	 * </p>
	 * <p>
	 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Attributes/autocomplete">The HTML autocomplete attribute</a>.
	 * </p>
	 */
	public enum Autocomplete implements Function<AnyDocument<?>, String> {
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

		private final String value;

		private Autocomplete(String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return value;
		}

		@Override
		public String apply(AnyDocument<?> document) {
			return value;
		}

		public String getValue() {
			return value;
		}
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/tag_input.asp">HTML input tag</a>.
	 * <p>
	 * This implementation that has all the input attributes,
	 * supporting unanticipated or more dynamic configurations.
	 * </p>
	 * <p>
	 * This does not limit attributes by type, and would allow mismatches where
	 * type-specific implementations may constrain the attributes and values.
	 * Although there is less validation, doctype-specific checks are expected
	 * to remain, such as only allowing type="color" in {@link Doctype#HTML5}.
	 * </p>
	 *
	 * @param  <D>   This document type
	 * @param  <PC>  The parent content model this element is within
	 */
	@SuppressWarnings("deprecation")
	public static class Dynamic<
		D  extends AnyDocument<D>,
		PC extends Union_Interactive_Phrasing<D, PC>
	> extends INPUT<D, PC, Dynamic<D, PC>> implements
		com.aoindustries.html.attributes.Text.Accept<Dynamic<D, PC>>,
		com.aoindustries.html.attributes.Enum.Align<Dynamic<D, PC>, Image.Align>,
		com.aoindustries.html.attributes.Text.Alt<Dynamic<D, PC>>,
		com.aoindustries.html.attributes.Enum.Autocomplete<Dynamic<D, PC>, INPUT.Autocomplete>,
		com.aoindustries.html.attributes.Enum.Capture<Dynamic<D, PC>, File.Capture>,
		com.aoindustries.html.attributes.Boolean.Checked<Dynamic<D, PC>>,
		com.aoindustries.html.attributes.Url.Formaction<Dynamic<D, PC>>,
		com.aoindustries.html.attributes.Enum.Formenctype<Dynamic<D, PC>, com.aoindustries.html.attributes.Enum.Enctype.Value>,
		com.aoindustries.html.attributes.Enum.Formmethod<Dynamic<D, PC>, com.aoindustries.html.attributes.Enum.Method.Value>,
		com.aoindustries.html.attributes.Boolean.Formnovalidate<Dynamic<D, PC>>,
		com.aoindustries.html.attributes.Enum.Formtarget<Dynamic<D, PC>, com.aoindustries.html.attributes.Enum.Target.Value>,
		com.aoindustries.html.attributes.Integer.HeightHtml5Only<Dynamic<D, PC>>,
		com.aoindustries.html.attributes.Text.List<Dynamic<D, PC>>,
		com.aoindustries.html.attributes.Integer.Maxlength<Dynamic<D, PC>>,
		com.aoindustries.html.attributes.Integer.Minlength<Dynamic<D, PC>>,
		com.aoindustries.html.attributes.Boolean.Multiple<Dynamic<D, PC>>,
		com.aoindustries.html.attributes.Text.Placeholder<Dynamic<D, PC>>,
		com.aoindustries.html.attributes.Boolean.Readonly<Dynamic<D, PC>>,
		com.aoindustries.html.attributes.Integer.Size<Dynamic<D, PC>>,
		com.aoindustries.html.attributes.Url.Src<Dynamic<D, PC>>,
		com.aoindustries.html.attributes.Enum.Type<Dynamic<D, PC>, Dynamic.Type>,
		com.aoindustries.html.attributes.Text.Value<Dynamic<D, PC>>,
		com.aoindustries.html.attributes.Integer.WidthHtml5Only<Dynamic<D, PC>>,
		// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
		com.aoindustries.html.attributes.event.media.Onabort<Dynamic<D, PC>>,
		com.aoindustries.html.attributes.event.window.Onerror<Dynamic<D, PC>>,
		com.aoindustries.html.attributes.event.window.Onload<Dynamic<D, PC>>,
		com.aoindustries.html.attributes.event.form.Onchange<Dynamic<D, PC>>,
		com.aoindustries.html.attributes.event.form.Oninput<Dynamic<D, PC>>,
		com.aoindustries.html.attributes.event.form.Onsearch<Dynamic<D, PC>>,
		com.aoindustries.html.attributes.event.form.Onselect<Dynamic<D, PC>>
	{

		private String type;
		public Dynamic(D document, PC pc) {
			super(document, pc);
			this.type = null;
		}

		public Dynamic(D document, PC pc, String type) {
			super(document, pc);
			type = Strings.trimNullIfEmpty(type);
			this.type = (type == null) ? null : type.toLowerCase(Locale.ROOT);
		}

		public Dynamic(D document, PC pc, Type type) {
			super(document, pc);
			this.type = (type == null) ? null : type.getValue();
		}

		@Override
		protected void openWriteType(Writer out) throws IOException {
			assert !document.getAtnl();
			// Write the type now, if already set
			String t = this.type;
			if(t != null) {
				// Unset to avoid duplicate attribute
				this.type = null;
				Dynamic<D, PC> i = type(t);
				assert i == this;
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_input_type.asp">HTML input type Attribute</a>.
		 */
		public enum Type implements Function<AnyDocument<?>, String> {
			BUTTON("button") {
				/**
				 * @see Button#value(java.lang.Object)
				 */
				@Override
				public MarkupType getMarkupType() {
					return MarkupType.TEXT;
				}
			},
			CHECKBOX("checkbox"),
			COLOR("color", Doctype.HTML5),
			DATE("date", Doctype.HTML5),
			DATETIME_LOCAL("datetime-local", Doctype.HTML5),
			EMAIL("email", Doctype.HTML5),
			FILE("file"),
			HIDDEN("hidden"),
			IMAGE("image"),
			MONTH("month", Doctype.HTML5),
			NUMBER("number", Doctype.HTML5),
			PASSWORD("password"),
			RADIO("radio"),
			RANGE("range", Doctype.HTML5),
			RESET("reset") {
				/**
				 * @see Reset#value(java.lang.Object)
				 */
				@Override
				public MarkupType getMarkupType() {
					return MarkupType.TEXT;
				}
			},
			SEARCH("search", Doctype.HTML5),
			SUBMIT("submit") {
				/**
				 * @see Submit#value(java.lang.Object)
				 */
				@Override
				public MarkupType getMarkupType() {
					return MarkupType.TEXT;
				}
			},
			TEL("tel", Doctype.HTML5),
			TEXT("text"),
			TIME("time", Doctype.HTML5),
			URL("url", Doctype.HTML5),
			WEEK("week", Doctype.HTML5);

			private final String value;
			private final Doctype requiredDoctype;

			private Type(String value, Doctype requiredDoctype) {
				this.value = value;
				this.requiredDoctype = requiredDoctype;
			}

			private Type(String value) {
				this(value, null);
			}

			@Override
			public String toString() {
				return value;
			}

			@Override
			public String apply(AnyDocument<?> document) {
				return value;
			}

			public String getValue() {
				return value;
			}

			public Doctype getRequiredDoctype() {
				return requiredDoctype;
			}

			/**
			 * Gets the interactive editor markup type or {@code null} to not alter
			 * the value.
			 */
			public MarkupType getMarkupType() {
				return null;
			}

			private static final Type[] values = values();
			private static final Map<String, Type> byLowerValue = AoCollections.newHashMap(values.length);
			static {
				for(Type type : values) {
					if(!type.value.equals(type.value.toLowerCase(Locale.ROOT))) throw new AssertionError("Values must be lowercase as looked-up later");
					if(!type.value.equals(type.value.trim())) throw new AssertionError("Values must be trimmed as looked-up later");
					byLowerValue.put(type.value, type);
				}
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_input_type.asp">HTML input type Attribute</a>.
		 */
		@Override
		public Dynamic<D, PC> type(String type) throws IOException {
			type = Strings.trimNullIfEmpty(type);
			if(type != null) {
				type = type.toLowerCase(Locale.ROOT);
				// Perform doctype checks and optimizations for recognized types
				Type typeEnum = Type.byLowerValue.get(type);
				if(typeEnum != null) {
					return type(typeEnum);
				}
				if(this.type != null) {
					throw new LocalizedIllegalStateException(
						AnyDocument.RESOURCES,
						"duplicateAttribute",
						"input",
						"type",
						this.type,
						type
					);
				}
				this.type = type;
				Writer out = document.getUnsafe(null);
				if(document.getAtnl()) {
					document.autoIndent(out, 1);
					out.write("type=\"");
					document.clearAtnl();
				} else {
					out.write(" type=\"");
				}
				encodeTextInXhtmlAttribute(type, out);
				out.append('"');
			}
			return this;
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_input_type.asp">HTML input type Attribute</a>.
		 */
		@Override
		public Dynamic<D, PC> type(Type type) throws IOException {
			if(type != null) {
				if(this.type != null) {
					throw new LocalizedIllegalStateException(
						AnyDocument.RESOURCES,
						"duplicateAttribute",
						"input",
						"type",
						this.type,
						type
					);
				}
				// Perform doctype checks for recognized types
				Doctype requiredDoctype = type.getRequiredDoctype();
				if(requiredDoctype != null && document.doctype != requiredDoctype) {
					throw new LocalizedIllegalArgumentException(
						RESOURCES,
						"typeRequiresDoctype",
						type.value,
						requiredDoctype,
						document.doctype
					);
				}
				this.type = type.value;
				Writer out = document.getUnsafe(null);
				if(document.getAtnl()) {
					document.autoIndent(out, 1);
					out.write("type=\"");
					document.clearAtnl();
				} else {
					out.write(" type=\"");
				}
				out.write(type.value); // No encoding, is a known safe value.  TODO: Assert this above in static initializer?
				out.append('"');
			}
			return this;
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_input_value.asp">HTML input value Attribute</a>.
		 */
		@Override
		public Dynamic<D, PC> value(Object value) throws IOException {
			assert this.type == null || this.type.equals(this.type.toLowerCase(Locale.ROOT));
			assert this.type == null || this.type.equals(this.type.trim());
			Type typeEnum = Type.byLowerValue.get(this.type);
			return Attributes.Text.attribute(
				this,
				"value",
				// Allow text markup from translations
				(typeEnum == null) ? null : typeEnum.getMarkupType(),
				value,
				false,
				true,
				textInXhtmlAttributeEncoder
			);
		}
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_input_type_button.asp">HTML input type="button"</a>.
	 *
	 * @param  <D>   This document type
	 * @param  <PC>  The parent content model this element is within
	 */
	public static class Button<
		D  extends AnyDocument<D>,
		PC extends Union_Interactive_Phrasing<D, PC>
	> extends INPUT<D, PC, Button<D, PC>> implements
		com.aoindustries.html.attributes.Text.Value<Button<D, PC>>
	{

		public Button(D document, PC pc) {
			super(document, pc);
		}

		@Override
		protected void openWriteType(Writer out) throws IOException {
			assert !document.getAtnl();
			out.write(" type=\"button\"");
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_input_value.asp">HTML input value Attribute</a>.
		 *
		 * @see Dynamic.Type#BUTTON
		 */
		@Override
		public Button<D, PC> value(Object value) throws IOException {
			return Attributes.Text.attribute(
				this,
				"value",
				// Allow text markup from translations
				Dynamic.Type.BUTTON.getMarkupType(),
				value,
				false,
				true,
				textInXhtmlAttributeEncoder
			);
		}
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_input_type_checkbox.asp">HTML input type="checkbox"</a>.
	 *
	 * @param  <D>   This document type
	 * @param  <PC>  The parent content model this element is within
	 */
	public static class Checkbox<
		D  extends AnyDocument<D>,
		PC extends Union_Interactive_Phrasing<D, PC>
	> extends INPUT<D, PC, Checkbox<D, PC>> implements
		com.aoindustries.html.attributes.Boolean.Checked<Checkbox<D, PC>>,
		com.aoindustries.html.attributes.Text.Value<Checkbox<D, PC>>,
		// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
		com.aoindustries.html.attributes.event.form.Onchange<Checkbox<D, PC>>
	{

		public Checkbox(D document, PC pc) {
			super(document, pc);
		}

		@Override
		protected void openWriteType(Writer out) throws IOException {
			assert !document.getAtnl();
			out.write(" type=\"checkbox\"");
		}
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_input_type_color.asp">HTML input type="color"</a>.
	 *
	 * @param  <D>   This document type
	 * @param  <PC>  The parent content model this element is within
	 */
	public static class Color<
		D  extends AnyDocument<D>,
		PC extends Union_Interactive_Phrasing<D, PC>
	> extends INPUT<D, PC, Color<D, PC>> implements
		com.aoindustries.html.attributes.Enum.Autocomplete<Color<D, PC>, Color.Autocomplete>,
		com.aoindustries.html.attributes.Text.List<Color<D, PC>>,
		com.aoindustries.html.attributes.Boolean.Readonly<Color<D, PC>>, // Guessed
		com.aoindustries.html.attributes.Text.Value<Color<D, PC>>,
		// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
		com.aoindustries.html.attributes.event.form.Onchange<Color<D, PC>>,
		com.aoindustries.html.attributes.event.form.Oninput<Color<D, PC>>
	{

		public Color(D document, PC pc) {
			super(document, pc);
			if(document.doctype != Doctype.HTML5) {
				throw new LocalizedIllegalArgumentException(
					RESOURCES,
					"typeOnlySupportedInHtml5",
					document.doctype,
					"color"
				);
			}
		}

		/**
		 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Attributes/autocomplete">The HTML autocomplete attribute</a>.
		 * <p>
		 * We're making our best guess which values are applicable.
		 * This is to give a nice short list in code assist.
		 * TODO: Find somewhere this relationship is defined (if there is any).
		 * </p>
		 *
		 * @see INPUT.Autocomplete
		 */
		public enum Autocomplete implements Function<AnyDocument<?>, String> {
			OFF(INPUT.Autocomplete.OFF),
			ON(INPUT.Autocomplete.ON);

			private final INPUT.Autocomplete value;

			private Autocomplete(INPUT.Autocomplete value) {
				this.value = value;
			}

			@Override
			public String toString() {
				return value.toString();
			}

			@Override
			public String apply(AnyDocument<?> document) {
				return value.apply(document);
			}

			public INPUT.Autocomplete getValue() {
				return value;
			}

			static {
				for(Autocomplete value : values()) {
					if(!value.name().equals(value.value.name())) throw new AssertionError("Enum name mismatch");
				}
			}
		}

		@Override
		protected void openWriteType(Writer out) throws IOException {
			assert !document.getAtnl();
			out.write(" type=\"color\"");
		}
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_input_type_date.asp">HTML input type="date"</a>.
	 *
	 * @param  <D>   This document type
	 * @param  <PC>  The parent content model this element is within
	 */
	public static class Date<
		D  extends AnyDocument<D>,
		PC extends Union_Interactive_Phrasing<D, PC>
	> extends INPUT<D, PC, Date<D, PC>> implements
		com.aoindustries.html.attributes.Enum.Autocomplete<Date<D, PC>, Date.Autocomplete>,
		com.aoindustries.html.attributes.Text.List<Date<D, PC>>,
		com.aoindustries.html.attributes.Boolean.Readonly<Date<D, PC>>, // Guessed
		com.aoindustries.html.attributes.Text.Value<Date<D, PC>>,
		// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
		com.aoindustries.html.attributes.event.form.Onchange<Date<D, PC>>,
		com.aoindustries.html.attributes.event.form.Oninput<Date<D, PC>>
	{

		public Date(D document, PC pc) {
			super(document, pc);
			if(document.doctype != Doctype.HTML5) {
				throw new LocalizedIllegalArgumentException(
					RESOURCES,
					"typeOnlySupportedInHtml5",
					document.doctype,
					"date"
				);
			}
		}

		/**
		 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Attributes/autocomplete">The HTML autocomplete attribute</a>.
		 * <p>
		 * We're making our best guess which values are applicable.
		 * This is to give a nice short list in code assist.
		 * TODO: Find somewhere this relationship is defined (if there is any).
		 * </p>
		 *
		 * @see INPUT.Autocomplete
		 */
		public enum Autocomplete implements Function<AnyDocument<?>, String> {
			OFF(INPUT.Autocomplete.OFF),
			ON(INPUT.Autocomplete.ON),
			CC_EXP(INPUT.Autocomplete.CC_EXP),
			BDAY(INPUT.Autocomplete.BDAY);

			private final INPUT.Autocomplete value;

			private Autocomplete(INPUT.Autocomplete value) {
				this.value = value;
			}

			@Override
			public String toString() {
				return value.toString();
			}

			@Override
			public String apply(AnyDocument<?> document) {
				return value.apply(document);
			}

			public INPUT.Autocomplete getValue() {
				return value;
			}

			static {
				for(Autocomplete value : values()) {
					if(!value.name().equals(value.value.name())) throw new AssertionError("Enum name mismatch");
				}
			}
		}

		@Override
		protected void openWriteType(Writer out) throws IOException {
			assert !document.getAtnl();
			out.write(" type=\"date\"");
		}
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_input_type_datetime-local.asp">HTML input type="datetime-local"</a>.
	 *
	 * @param  <D>   This document type
	 * @param  <PC>  The parent content model this element is within
	 */
	public static class DatetimeLocal<
		D  extends AnyDocument<D>,
		PC extends Union_Interactive_Phrasing<D, PC>
	> extends INPUT<D, PC, DatetimeLocal<D, PC>> implements
		com.aoindustries.html.attributes.Enum.Autocomplete<DatetimeLocal<D, PC>, DatetimeLocal.Autocomplete>,
		com.aoindustries.html.attributes.Text.List<DatetimeLocal<D, PC>>,
		com.aoindustries.html.attributes.Boolean.Readonly<DatetimeLocal<D, PC>>, // Guessed
		com.aoindustries.html.attributes.Text.Value<DatetimeLocal<D, PC>>,
		// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
		com.aoindustries.html.attributes.event.form.Onchange<DatetimeLocal<D, PC>>,
		com.aoindustries.html.attributes.event.form.Oninput<DatetimeLocal<D, PC>>
	{

		public DatetimeLocal(D document, PC pc) {
			super(document, pc);
			if(document.doctype != Doctype.HTML5) {
				throw new LocalizedIllegalArgumentException(
					RESOURCES,
					"typeOnlySupportedInHtml5",
					document.doctype,
					"datetime-local"
				);
			}
		}

		/**
		 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Attributes/autocomplete">The HTML autocomplete attribute</a>.
		 * <p>
		 * We're making our best guess which values are applicable.
		 * This is to give a nice short list in code assist.
		 * TODO: Find somewhere this relationship is defined (if there is any).
		 * </p>
		 *
		 * @see INPUT.Autocomplete
		 */
		public enum Autocomplete implements Function<AnyDocument<?>, String> {
			OFF(INPUT.Autocomplete.OFF),
			ON(INPUT.Autocomplete.ON),
			BDAY(INPUT.Autocomplete.BDAY);

			private final INPUT.Autocomplete value;

			private Autocomplete(INPUT.Autocomplete value) {
				this.value = value;
			}

			@Override
			public String toString() {
				return value.toString();
			}

			@Override
			public String apply(AnyDocument<?> document) {
				return value.apply(document);
			}

			public INPUT.Autocomplete getValue() {
				return value;
			}

			static {
				for(Autocomplete value : values()) {
					if(!value.name().equals(value.value.name())) throw new AssertionError("Enum name mismatch");
				}
			}
		}

		@Override
		protected void openWriteType(Writer out) throws IOException {
			assert !document.getAtnl();
			out.write(" type=\"datetime-local\"");
		}
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_input_type_email.asp">HTML input type="email"</a>.
	 *
	 * @param  <D>   This document type
	 * @param  <PC>  The parent content model this element is within
	 */
	public static class Email<
		D  extends AnyDocument<D>,
		PC extends Union_Interactive_Phrasing<D, PC>
	> extends INPUT<D, PC, Email<D, PC>> implements
		com.aoindustries.html.attributes.Enum.Autocomplete<Email<D, PC>, Email.Autocomplete>,
		com.aoindustries.html.attributes.Text.List<Email<D, PC>>,
		com.aoindustries.html.attributes.Integer.Maxlength<Email<D, PC>>,
		com.aoindustries.html.attributes.Integer.Minlength<Email<D, PC>>,
		com.aoindustries.html.attributes.Boolean.Multiple<Email<D, PC>>,
		com.aoindustries.html.attributes.Text.Placeholder<Email<D, PC>>,
		com.aoindustries.html.attributes.Boolean.Readonly<Email<D, PC>>,
		com.aoindustries.html.attributes.Integer.Size<Email<D, PC>>,
		com.aoindustries.html.attributes.Text.Value<Email<D, PC>>,
		// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
		com.aoindustries.html.attributes.event.form.Onchange<Email<D, PC>>,
		com.aoindustries.html.attributes.event.form.Oninput<Email<D, PC>>,
		com.aoindustries.html.attributes.event.form.Onselect<Email<D, PC>> // Guessed (to match Placeholder)
	{

		public Email(D document, PC pc) {
			super(document, pc);
			if(document.doctype != Doctype.HTML5) {
				throw new LocalizedIllegalArgumentException(
					RESOURCES,
					"typeOnlySupportedInHtml5",
					document.doctype,
					"email"
				);
			}
		}

		/**
		 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Attributes/autocomplete">The HTML autocomplete attribute</a>.
		 * <p>
		 * We're making our best guess which values are applicable.
		 * This is to give a nice short list in code assist.
		 * TODO: Find somewhere this relationship is defined (if there is any).
		 * </p>
		 *
		 * @see INPUT.Autocomplete
		 */
		public enum Autocomplete implements Function<AnyDocument<?>, String> {
			OFF(INPUT.Autocomplete.OFF),
			ON(INPUT.Autocomplete.ON),
			EMAIL(INPUT.Autocomplete.EMAIL),
			IMPP(INPUT.Autocomplete.IMPP),
			URL(INPUT.Autocomplete.URL);

			private final INPUT.Autocomplete value;

			private Autocomplete(INPUT.Autocomplete value) {
				this.value = value;
			}

			@Override
			public String toString() {
				return value.toString();
			}

			@Override
			public String apply(AnyDocument<?> document) {
				return value.apply(document);
			}

			public INPUT.Autocomplete getValue() {
				return value;
			}

			static {
				for(Autocomplete value : values()) {
					if(!value.name().equals(value.value.name())) throw new AssertionError("Enum name mismatch");
				}
			}
		}

		@Override
		protected void openWriteType(Writer out) throws IOException {
			assert !document.getAtnl();
			out.write(" type=\"email\"");
		}
	}

	/**
	 * <ul>
	 * <li>See <a href="https://www.w3schools.com/tags/att_input_type_file.asp">HTML input type="file"</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input/file">&lt;input type="file"&gt;</a>.</li>
	 * </ul>
	 *
	 * @param  <D>   This document type
	 * @param  <PC>  The parent content model this element is within
	 */
	public static class File<
		D  extends AnyDocument<D>,
		PC extends Union_Interactive_Phrasing<D, PC>
	> extends INPUT<D, PC, File<D, PC>> implements
		com.aoindustries.html.attributes.Text.Accept<File<D, PC>>,
		com.aoindustries.html.attributes.Enum.Capture<File<D, PC>, File.Capture>,
		com.aoindustries.html.attributes.Boolean.Multiple<File<D, PC>>,
		// Does not support value per https://www.w3schools.com/tags/att_input_value.asp: com.aoindustries.html.attributes.Text.Value<File>
		// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
		com.aoindustries.html.attributes.event.form.Onchange<File<D, PC>>,
		com.aoindustries.html.attributes.event.form.Onselect<File<D, PC>>
	{

		public File(D document, PC pc) {
			super(document, pc);
		}

		/**
		 * See <a href="https://www.w3.org/TR/mediacapture-streams/#dom-videofacingmodeenum">Media Capture and Streams: VideoFacingModeEnum</a>.
		 */
		public enum Capture implements Function<AnyDocument<?>, String> {

			/**
			 * The source is facing toward the user (a self-view camera).
			 */
			USER("user"),

			/**
			 * The source is facing away from the user (viewing the environment).
			 */
			ENVIRONMENT("environment"),

			/**
			 * The source is facing to the left of the user.
			 */
			LEFT("left"),

			/**
			 * The source is facing to the right of the user.
			 */
			RIGHT("right");

			private final String value;

			private Capture(String value) {
				this.value = value;
			}

			@Override
			public String toString() {
				return value;
			}

			@Override
			public String apply(AnyDocument<?> document) {
				return value;
			}
		}

		@Override
		protected void openWriteType(Writer out) throws IOException {
			assert !document.getAtnl();
			out.write(" type=\"file\"");
		}
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_input_type_hidden.asp">HTML input type="hidden"</a>.
	 *
	 * @param  <D>   This document type
	 * @param  <PC>  The parent content model this element is within
	 */
	public static class Hidden<
		D  extends AnyDocument<D>,
		PC extends Union_Interactive_Phrasing<D, PC>
	> extends INPUT<D, PC, Hidden<D, PC>> implements
		com.aoindustries.html.attributes.Enum.Autocomplete<Hidden<D, PC>, INPUT.Autocomplete>,
		com.aoindustries.html.attributes.Text.Value<Hidden<D, PC>>
	{

		public Hidden(D document, PC pc) {
			super(document, pc);
		}

		@Override
		protected void openWriteType(Writer out) throws IOException {
			assert !document.getAtnl();
			out.write(" type=\"hidden\"");
		}
	}

	/**
	 * <ul>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input/image">&lt;input type="image"&gt;</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/att_input_type_image.asp">HTML input type="image"</a>.</li>
	 * </ul>
	 *
	 * @param  <D>   This document type
	 * @param  <PC>  The parent content model this element is within
	 */
	@SuppressWarnings("deprecation")
	public static class Image<
		D  extends AnyDocument<D>,
		PC extends Union_Interactive_Phrasing<D, PC>
	> extends INPUT<D, PC, Image<D, PC>> implements
		com.aoindustries.html.attributes.Enum.Align<Image<D, PC>, Image.Align>,
		com.aoindustries.html.attributes.Text.Alt<Image<D, PC>>,
		com.aoindustries.html.attributes.Url.Formaction<Image<D, PC>>,
		com.aoindustries.html.attributes.Enum.Formenctype<Image<D, PC>, com.aoindustries.html.attributes.Enum.Enctype.Value>,
		com.aoindustries.html.attributes.Enum.Formmethod<Image<D, PC>, com.aoindustries.html.attributes.Enum.Method.Value>,
		com.aoindustries.html.attributes.Boolean.Formnovalidate<Image<D, PC>>,
		com.aoindustries.html.attributes.Enum.Formtarget<Image<D, PC>, com.aoindustries.html.attributes.Enum.Target.Value>,
		com.aoindustries.html.attributes.Integer.HeightHtml5Only<Image<D, PC>>,
		com.aoindustries.html.attributes.Url.Src<Image<D, PC>>,
		com.aoindustries.html.attributes.Text.Value<Image<D, PC>>,
		com.aoindustries.html.attributes.Integer.WidthHtml5Only<Image<D, PC>>,
		// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
		com.aoindustries.html.attributes.event.media.Onabort<Image<D, PC>>,
		com.aoindustries.html.attributes.event.window.Onerror<Image<D, PC>>,
		com.aoindustries.html.attributes.event.window.Onload<Image<D, PC>>
	{

		public Image(D document, PC pc) {
			super(document, pc);
		}

		@Override
		protected void openWriteType(Writer out) throws IOException {
			assert !document.getAtnl();
			out.write(" type=\"image\"");
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_input_align.asp">HTML input align Attribute</a>.
		 *
		 * @deprecated  The align attribute of &lt;input&gt; is not supported in HTML5. Use CSS instead.
		 */
		@Deprecated
		public enum Align implements Function<AnyDocument<?>, String> {

			/**
			 * Left-aligns the image (this is default)
			 */
			LEFT("left"),

			/**
			 * Right-aligns the image
			 */
			RIGHT("right"),

			/**
			 * Top-aligns the image
			 */
			TOP("top"),

			/**
			 * Middle-aligns the image
			 */
			MIDDLE("middle"),

			/**
			 * Bottom-aligns the image
			 */
			BOTTOM("bottom");

			private final String value;

			private Align(String value) {
				this.value = value;
			}

			@Override
			public String toString() {
				return value;
			}

			@Override
			public String apply(AnyDocument<?> document) {
				return value;
			}
		}
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_input_type_month.asp">HTML input type="month"</a>.
	 *
	 * @param  <D>   This document type
	 * @param  <PC>  The parent content model this element is within
	 */
	public static class Month<
		D  extends AnyDocument<D>,
		PC extends Union_Interactive_Phrasing<D, PC>
	> extends INPUT<D, PC, Month<D, PC>> implements
		com.aoindustries.html.attributes.Enum.Autocomplete<Month<D, PC>, Month.Autocomplete>,
		com.aoindustries.html.attributes.Text.List<Month<D, PC>>,
		com.aoindustries.html.attributes.Boolean.Readonly<Month<D, PC>>, // Guessed
		com.aoindustries.html.attributes.Text.Value<Month<D, PC>>,
		// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
		com.aoindustries.html.attributes.event.form.Onchange<Month<D, PC>>,
		com.aoindustries.html.attributes.event.form.Oninput<Month<D, PC>>
	{

		public Month(D document, PC pc) {
			super(document, pc);
			if(document.doctype != Doctype.HTML5) {
				throw new LocalizedIllegalArgumentException(
					RESOURCES,
					"typeOnlySupportedInHtml5",
					document.doctype,
					"month"
				);
			}
		}

		/**
		 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Attributes/autocomplete">The HTML autocomplete attribute</a>.
		 * <p>
		 * We're making our best guess which values are applicable.
		 * This is to give a nice short list in code assist.
		 * TODO: Find somewhere this relationship is defined (if there is any).
		 * </p>
		 *
		 * @see INPUT.Autocomplete
		 */
		public enum Autocomplete implements Function<AnyDocument<?>, String> {
			OFF(INPUT.Autocomplete.OFF),
			ON(INPUT.Autocomplete.ON),
			CC_EXP(INPUT.Autocomplete.CC_EXP),
			CC_EXP_MONTH(INPUT.Autocomplete.CC_EXP_MONTH),
			BDAY_MONTH(INPUT.Autocomplete.BDAY_MONTH);

			private final INPUT.Autocomplete value;

			private Autocomplete(INPUT.Autocomplete value) {
				this.value = value;
			}

			@Override
			public String toString() {
				return value.toString();
			}

			@Override
			public String apply(AnyDocument<?> document) {
				return value.apply(document);
			}

			public INPUT.Autocomplete getValue() {
				return value;
			}

			static {
				for(Autocomplete value : values()) {
					if(!value.name().equals(value.value.name())) throw new AssertionError("Enum name mismatch");
				}
			}
		}

		@Override
		protected void openWriteType(Writer out) throws IOException {
			assert !document.getAtnl();
			out.write(" type=\"month\"");
		}
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_input_type_number.asp">HTML input type="number"</a>.
	 *
	 * @param  <D>   This document type
	 * @param  <PC>  The parent content model this element is within
	 */
	public static class Number<
		D  extends AnyDocument<D>,
		PC extends Union_Interactive_Phrasing<D, PC>
	> extends INPUT<D, PC, Number<D, PC>> implements
		com.aoindustries.html.attributes.Enum.Autocomplete<Number<D, PC>, Number.Autocomplete>,
		com.aoindustries.html.attributes.Text.List<Number<D, PC>>,
		com.aoindustries.html.attributes.Boolean.Readonly<Number<D, PC>>,
		com.aoindustries.html.attributes.Text.Value<Number<D, PC>>, // TODO: Review types (this and others), perhaps Attributes.Number or similar?
		// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
		com.aoindustries.html.attributes.event.form.Onchange<Number<D, PC>>,
		com.aoindustries.html.attributes.event.form.Oninput<Number<D, PC>>
	{

		public Number(D document, PC pc) {
			super(document, pc);
			if(document.doctype != Doctype.HTML5) {
				throw new LocalizedIllegalArgumentException(
					RESOURCES,
					"typeOnlySupportedInHtml5",
					document.doctype,
					"number"
				);
			}
		}

		/**
		 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Attributes/autocomplete">The HTML autocomplete attribute</a>.
		 * <p>
		 * We're making our best guess which values are applicable.
		 * This is to give a nice short list in code assist.
		 * TODO: Find somewhere this relationship is defined (if there is any).
		 * </p>
		 *
		 * @see INPUT.Autocomplete
		 */
		public enum Autocomplete implements Function<AnyDocument<?>, String> {
			OFF(INPUT.Autocomplete.OFF),
			ON(INPUT.Autocomplete.ON),
			ONE_TIME_CODE(INPUT.Autocomplete.ONE_TIME_CODE),
			ADDRESS_LEVEL4(INPUT.Autocomplete.ADDRESS_LEVEL4),
			ADDRESS_LEVEL3(INPUT.Autocomplete.ADDRESS_LEVEL3),
			ADDRESS_LEVEL2(INPUT.Autocomplete.ADDRESS_LEVEL2),
			ADDRESS_LEVEL1(INPUT.Autocomplete.ADDRESS_LEVEL1),
			POSTAL_CODE(INPUT.Autocomplete.POSTAL_CODE),
			CC_NUMBER(INPUT.Autocomplete.CC_NUMBER),
			CC_EXP_MONTH(INPUT.Autocomplete.CC_EXP_MONTH),
			CC_EXP_YEAR(INPUT.Autocomplete.CC_EXP_YEAR),
			CC_CSC(INPUT.Autocomplete.CC_CSC),
			TRANSACTION_AMOUNT(INPUT.Autocomplete.TRANSACTION_AMOUNT),
			BDAY_DAY(INPUT.Autocomplete.BDAY_DAY),
			BDAY_MONTH(INPUT.Autocomplete.BDAY_MONTH),
			BDAY_YEAR(INPUT.Autocomplete.BDAY_YEAR),
			TEL_COUNTRY_CODE(INPUT.Autocomplete.TEL_COUNTRY_CODE),
			TEL_AREA_CODE(INPUT.Autocomplete.TEL_AREA_CODE),
			TEL_LOCAL_PREFIX(INPUT.Autocomplete.TEL_LOCAL_PREFIX),
			TEL_LOCAL_SUFFIX(INPUT.Autocomplete.TEL_LOCAL_SUFFIX),
			TEL_EXTENSION(INPUT.Autocomplete.TEL_EXTENSION);

			private final INPUT.Autocomplete value;

			private Autocomplete(INPUT.Autocomplete value) {
				this.value = value;
			}

			@Override
			public String toString() {
				return value.toString();
			}

			@Override
			public String apply(AnyDocument<?> document) {
				return value.apply(document);
			}

			public INPUT.Autocomplete getValue() {
				return value;
			}

			static {
				for(Autocomplete value : values()) {
					if(!value.name().equals(value.value.name())) throw new AssertionError("Enum name mismatch");
				}
			}
		}

		@Override
		protected void openWriteType(Writer out) throws IOException {
			assert !document.getAtnl();
			out.write(" type=\"number\"");
		}
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_input_type_password.asp">HTML input type="password"</a>.
	 *
	 * @param  <D>   This document type
	 * @param  <PC>  The parent content model this element is within
	 */
	public static class Password<
		D  extends AnyDocument<D>,
		PC extends Union_Interactive_Phrasing<D, PC>
	> extends INPUT<D, PC, Password<D, PC>> implements
		com.aoindustries.html.attributes.Enum.Autocomplete<Password<D, PC>, Password.Autocomplete>,
		com.aoindustries.html.attributes.Integer.Maxlength<Password<D, PC>>,
		com.aoindustries.html.attributes.Integer.Minlength<Password<D, PC>>,
		com.aoindustries.html.attributes.Text.Placeholder<Password<D, PC>>,
		com.aoindustries.html.attributes.Boolean.Readonly<Password<D, PC>>,
		com.aoindustries.html.attributes.Integer.Size<Password<D, PC>>,
		com.aoindustries.html.attributes.Text.Value<Password<D, PC>>,
		// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
		com.aoindustries.html.attributes.event.form.Onchange<Password<D, PC>>,
		com.aoindustries.html.attributes.event.form.Oninput<Password<D, PC>>,
		com.aoindustries.html.attributes.event.form.Onselect<Password<D, PC>>
	{

		public Password(D document, PC pc) {
			super(document, pc);
		}

		/**
		 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Attributes/autocomplete">The HTML autocomplete attribute</a>.
		 * <p>
		 * We're making our best guess which values are applicable.
		 * This is to give a nice short list in code assist.
		 * TODO: Find somewhere this relationship is defined (if there is any).
		 * </p>
		 *
		 * @see INPUT.Autocomplete
		 */
		public enum Autocomplete implements Function<AnyDocument<?>, String> {
			OFF(INPUT.Autocomplete.OFF),
			ON(INPUT.Autocomplete.ON),
			NEW_PASSWORD(INPUT.Autocomplete.NEW_PASSWORD),
			CURRENT_PASSWORD(INPUT.Autocomplete.CURRENT_PASSWORD),
			ONE_TIME_CODE(INPUT.Autocomplete.ONE_TIME_CODE),
			CC_NUMBER(INPUT.Autocomplete.CC_NUMBER),
			CC_EXP(INPUT.Autocomplete.CC_EXP),
			CC_EXP_MONTH(INPUT.Autocomplete.CC_EXP_MONTH),
			CC_EXP_YEAR(INPUT.Autocomplete.CC_EXP_YEAR),
			CC_CSC(INPUT.Autocomplete.CC_CSC);

			private final INPUT.Autocomplete value;

			private Autocomplete(INPUT.Autocomplete value) {
				this.value = value;
			}

			@Override
			public String toString() {
				return value.toString();
			}

			@Override
			public String apply(AnyDocument<?> document) {
				return value.apply(document);
			}

			public INPUT.Autocomplete getValue() {
				return value;
			}

			static {
				for(Autocomplete value : values()) {
					if(!value.name().equals(value.value.name())) throw new AssertionError("Enum name mismatch");
				}
			}
		}

		@Override
		protected void openWriteType(Writer out) throws IOException {
			assert !document.getAtnl();
			out.write(" type=\"password\"");
		}
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_input_type_radio.asp">HTML input type="radio"</a>.
	 *
	 * @param  <D>   This document type
	 * @param  <PC>  The parent content model this element is within
	 */
	public static class Radio<
		D  extends AnyDocument<D>,
		PC extends Union_Interactive_Phrasing<D, PC>
	> extends INPUT<D, PC, Radio<D, PC>> implements
		com.aoindustries.html.attributes.Boolean.Checked<Radio<D, PC>>,
		com.aoindustries.html.attributes.Text.Value<Radio<D, PC>>,
		// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
		com.aoindustries.html.attributes.event.form.Onchange<Radio<D, PC>>
	{

		public Radio(D document, PC pc) {
			super(document, pc);
		}

		@Override
		protected void openWriteType(Writer out) throws IOException {
			assert !document.getAtnl();
			out.write(" type=\"radio\"");
		}
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_input_type_range.asp">HTML input type="range"</a>.
	 *
	 * @param  <D>   This document type
	 * @param  <PC>  The parent content model this element is within
	 */
	public static class Range<
		D  extends AnyDocument<D>,
		PC extends Union_Interactive_Phrasing<D, PC>
	> extends INPUT<D, PC, Range<D, PC>> implements
		com.aoindustries.html.attributes.Enum.Autocomplete<Range<D, PC>, Range.Autocomplete>,
		com.aoindustries.html.attributes.Text.List<Range<D, PC>>,
		com.aoindustries.html.attributes.Text.Value<Range<D, PC>>,
		// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
		com.aoindustries.html.attributes.event.form.Onchange<Range<D, PC>>,
		com.aoindustries.html.attributes.event.form.Oninput<Range<D, PC>>
	{

		public Range(D document, PC pc) {
			super(document, pc);
			if(document.doctype != Doctype.HTML5) {
				throw new LocalizedIllegalArgumentException(
					RESOURCES,
					"typeOnlySupportedInHtml5",
					document.doctype,
					"range"
				);
			}
		}

		/**
		 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Attributes/autocomplete">The HTML autocomplete attribute</a>.
		 * <p>
		 * We're making our best guess which values are applicable.
		 * This is to give a nice short list in code assist.
		 * TODO: Find somewhere this relationship is defined (if there is any).
		 * </p>
		 *
		 * @see INPUT.Autocomplete
		 */
		public enum Autocomplete implements Function<AnyDocument<?>, String> {
			OFF(INPUT.Autocomplete.OFF),
			ON(INPUT.Autocomplete.ON);

			private final INPUT.Autocomplete value;

			private Autocomplete(INPUT.Autocomplete value) {
				this.value = value;
			}

			@Override
			public String toString() {
				return value.toString();
			}

			@Override
			public String apply(AnyDocument<?> document) {
				return value.apply(document);
			}

			public INPUT.Autocomplete getValue() {
				return value;
			}

			static {
				for(Autocomplete value : values()) {
					if(!value.name().equals(value.value.name())) throw new AssertionError("Enum name mismatch");
				}
			}
		}

		@Override
		protected void openWriteType(Writer out) throws IOException {
			assert !document.getAtnl();
			out.write(" type=\"range\"");
		}
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_input_type_reset.asp">HTML input type="reset"</a>.
	 *
	 * @param  <D>   This document type
	 * @param  <PC>  The parent content model this element is within
	 */
	public static class Reset<
		D  extends AnyDocument<D>,
		PC extends Union_Interactive_Phrasing<D, PC>
	> extends INPUT<D, PC, Reset<D, PC>> implements
		com.aoindustries.html.attributes.Text.Value<Reset<D, PC>>
	{

		public Reset(D document, PC pc) {
			super(document, pc);
		}

		@Override
		protected void openWriteType(Writer out) throws IOException {
			assert !document.getAtnl();
			out.write(" type=\"reset\"");
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_input_value.asp">HTML input value Attribute</a>.
		 *
		 * @see Dynamic.Type#RESET
		 */
		@Override
		public Reset<D, PC> value(Object value) throws IOException {
			return Attributes.Text.attribute(
				this,
				"value",
				// Allow text markup from translations
				Dynamic.Type.RESET.getMarkupType(),
				value,
				false,
				true,
				textInXhtmlAttributeEncoder
			);
		}
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_input_type_search.asp">HTML input type="search"</a>.
	 *
	 * @param  <D>   This document type
	 * @param  <PC>  The parent content model this element is within
	 */
	public static class Search<
		D  extends AnyDocument<D>,
		PC extends Union_Interactive_Phrasing<D, PC>
	> extends INPUT<D, PC, Search<D, PC>> implements
		com.aoindustries.html.attributes.Enum.Autocomplete<Search<D, PC>, Search.Autocomplete>,
		com.aoindustries.html.attributes.Text.List<Search<D, PC>>,
		com.aoindustries.html.attributes.Integer.Maxlength<Search<D, PC>>,
		com.aoindustries.html.attributes.Integer.Minlength<Search<D, PC>>,
		com.aoindustries.html.attributes.Text.Placeholder<Search<D, PC>>,
		com.aoindustries.html.attributes.Boolean.Readonly<Search<D, PC>>,
		com.aoindustries.html.attributes.Integer.Size<Search<D, PC>>,
		com.aoindustries.html.attributes.Text.Value<Search<D, PC>>,
		// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
		com.aoindustries.html.attributes.event.form.Onchange<Search<D, PC>>,
		com.aoindustries.html.attributes.event.form.Oninput<Search<D, PC>>,
		com.aoindustries.html.attributes.event.form.Onsearch<Search<D, PC>>,
		com.aoindustries.html.attributes.event.form.Onselect<Search<D, PC>> // Guessed (to match Placeholder)
	{

		public Search(D document, PC pc) {
			super(document, pc);
			if(document.doctype != Doctype.HTML5) {
				throw new LocalizedIllegalArgumentException(
					RESOURCES,
					"typeOnlySupportedInHtml5",
					document.doctype,
					"search"
				);
			}
		}

		/**
		 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Attributes/autocomplete">The HTML autocomplete attribute</a>.
		 * <p>
		 * We're making our best guess which values are applicable.
		 * This is to give a nice short list in code assist.
		 * TODO: Find somewhere this relationship is defined (if there is any).
		 * </p>
		 *
		 * @see INPUT.Autocomplete
		 */
		public enum Autocomplete implements Function<AnyDocument<?>, String> {
			OFF(INPUT.Autocomplete.OFF),
			ON(INPUT.Autocomplete.ON),
			NAME(INPUT.Autocomplete.NAME),
			HONORIFIC_PREFIX(INPUT.Autocomplete.HONORIFIC_PREFIX),
			GIVEN_NAME(INPUT.Autocomplete.GIVEN_NAME),
			ADDITIONAL_NAME(INPUT.Autocomplete.ADDITIONAL_NAME),
			FAMILY_NAME(INPUT.Autocomplete.FAMILY_NAME),
			HONORIFIC_SUFFIX(INPUT.Autocomplete.HONORIFIC_SUFFIX),
			NICKNAME(INPUT.Autocomplete.NICKNAME),
			EMAIL(INPUT.Autocomplete.EMAIL),
			USERNAME(INPUT.Autocomplete.USERNAME),
			ORGANIZATION_TITLE(INPUT.Autocomplete.ORGANIZATION_TITLE),
			ORGANIZATION(INPUT.Autocomplete.ORGANIZATION),
			STREET_ADDRESS(INPUT.Autocomplete.STREET_ADDRESS),
			ADDRESS_LINE1(INPUT.Autocomplete.ADDRESS_LINE1),
			ADDRESS_LINE2(INPUT.Autocomplete.ADDRESS_LINE2),
			ADDRESS_LINE3(INPUT.Autocomplete.ADDRESS_LINE3),
			ADDRESS_LEVEL4(INPUT.Autocomplete.ADDRESS_LEVEL4),
			ADDRESS_LEVEL3(INPUT.Autocomplete.ADDRESS_LEVEL3),
			ADDRESS_LEVEL2(INPUT.Autocomplete.ADDRESS_LEVEL2),
			ADDRESS_LEVEL1(INPUT.Autocomplete.ADDRESS_LEVEL1),
			COUNTRY(INPUT.Autocomplete.COUNTRY),
			COUNTRY_NAME(INPUT.Autocomplete.COUNTRY_NAME),
			POSTAL_CODE(INPUT.Autocomplete.POSTAL_CODE),
			CC_NAME(INPUT.Autocomplete.CC_NAME),
			CC_GIVEN_NAME(INPUT.Autocomplete.CC_GIVEN_NAME),
			CC_ADDITIONAL_NAME(INPUT.Autocomplete.CC_ADDITIONAL_NAME),
			CC_FAMILY_NAME(INPUT.Autocomplete.CC_FAMILY_NAME),
			CC_TYPE(INPUT.Autocomplete.CC_TYPE),
			TRANSACTION_CURRENCY(INPUT.Autocomplete.TRANSACTION_CURRENCY),
			TRANSACTION_AMOUNT(INPUT.Autocomplete.TRANSACTION_AMOUNT),
			LANGUAGE(INPUT.Autocomplete.LANGUAGE),
			BDAY(INPUT.Autocomplete.BDAY),
			BDAY_DAY(INPUT.Autocomplete.BDAY_DAY),
			BDAY_MONTH(INPUT.Autocomplete.BDAY_MONTH),
			BDAY_YEAR(INPUT.Autocomplete.BDAY_YEAR),
			SEX(INPUT.Autocomplete.SEX),
			TEL(INPUT.Autocomplete.TEL),
			TEL_COUNTRY_CODE(INPUT.Autocomplete.TEL_COUNTRY_CODE),
			TEL_NATIONAL(INPUT.Autocomplete.TEL_NATIONAL),
			TEL_AREA_CODE(INPUT.Autocomplete.TEL_AREA_CODE),
			TEL_LOCAL(INPUT.Autocomplete.TEL_LOCAL),
			TEL_LOCAL_PREFIX(INPUT.Autocomplete.TEL_LOCAL_PREFIX),
			TEL_LOCAL_SUFFIX(INPUT.Autocomplete.TEL_LOCAL_SUFFIX),
			TEL_EXTENSION(INPUT.Autocomplete.TEL_EXTENSION),
			IMPP(INPUT.Autocomplete.IMPP),
			URL(INPUT.Autocomplete.URL),
			PHOTO(INPUT.Autocomplete.PHOTO);

			private final INPUT.Autocomplete value;

			private Autocomplete(INPUT.Autocomplete value) {
				this.value = value;
			}

			@Override
			public String toString() {
				return value.toString();
			}

			@Override
			public String apply(AnyDocument<?> document) {
				return value.apply(document);
			}

			public INPUT.Autocomplete getValue() {
				return value;
			}

			static {
				for(Autocomplete value : values()) {
					if(!value.name().equals(value.value.name())) throw new AssertionError("Enum name mismatch");
				}
			}
		}

		@Override
		protected void openWriteType(Writer out) throws IOException {
			assert !document.getAtnl();
			out.write(" type=\"search\"");
		}
	}

	/**
	 * <ul>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input/submit">&lt;input type="submit"&gt;</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/att_input_type_submit.asp">HTML input type="submit"</a>.</li>
	 * </ul>
	 *
	 * @param  <D>   This document type
	 * @param  <PC>  The parent content model this element is within
	 */
	public static class Submit<
		D  extends AnyDocument<D>,
		PC extends Union_Interactive_Phrasing<D, PC>
	> extends INPUT<D, PC, Submit<D, PC>> implements
		com.aoindustries.html.attributes.Url.Formaction<Submit<D, PC>>,
		com.aoindustries.html.attributes.Enum.Formenctype<Submit<D, PC>, com.aoindustries.html.attributes.Enum.Enctype.Value>,
		com.aoindustries.html.attributes.Enum.Formmethod<Submit<D, PC>, com.aoindustries.html.attributes.Enum.Method.Value>,
		com.aoindustries.html.attributes.Boolean.Formnovalidate<Submit<D, PC>>,
		com.aoindustries.html.attributes.Enum.Formtarget<Submit<D, PC>, com.aoindustries.html.attributes.Enum.Target.Value>,
		com.aoindustries.html.attributes.Text.Value<Submit<D, PC>>
	{

		public Submit(D document, PC pc) {
			super(document, pc);
		}

		@Override
		protected void openWriteType(Writer out) throws IOException {
			assert !document.getAtnl();
			out.write(" type=\"submit\"");
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_input_value.asp">HTML input value Attribute</a>.
		 *
		 * @see Dynamic.Type#SUBMIT
		 */
		@Override
		public Submit<D, PC> value(Object value) throws IOException {
			return Attributes.Text.attribute(
				this,
				"value",
				// Allow text markup from translations
				Dynamic.Type.SUBMIT.getMarkupType(),
				value,
				false,
				true,
				textInXhtmlAttributeEncoder
			);
		}
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_input_type_tel.asp">HTML input type="tel"</a>.
	 *
	 * @param  <D>   This document type
	 * @param  <PC>  The parent content model this element is within
	 */
	public static class Tel<
		D  extends AnyDocument<D>,
		PC extends Union_Interactive_Phrasing<D, PC>
	> extends INPUT<D, PC, Tel<D, PC>> implements
		com.aoindustries.html.attributes.Enum.Autocomplete<Tel<D, PC>, Tel.Autocomplete>,
		com.aoindustries.html.attributes.Text.List<Tel<D, PC>>,
		com.aoindustries.html.attributes.Integer.Maxlength<Tel<D, PC>>,
		com.aoindustries.html.attributes.Integer.Minlength<Tel<D, PC>>,
		com.aoindustries.html.attributes.Text.Placeholder<Tel<D, PC>>,
		com.aoindustries.html.attributes.Boolean.Readonly<Tel<D, PC>>, // Guessed
		com.aoindustries.html.attributes.Integer.Size<Tel<D, PC>>,
		com.aoindustries.html.attributes.Text.Value<Tel<D, PC>>,
		// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
		com.aoindustries.html.attributes.event.form.Onchange<Tel<D, PC>>,
		com.aoindustries.html.attributes.event.form.Oninput<Tel<D, PC>>,
		com.aoindustries.html.attributes.event.form.Onselect<Tel<D, PC>> // Guessed (to match Placeholder)
	{

		public Tel(D document, PC pc) {
			super(document, pc);
			if(document.doctype != Doctype.HTML5) {
				throw new LocalizedIllegalArgumentException(
					RESOURCES,
					"typeOnlySupportedInHtml5",
					document.doctype,
					"tel"
				);
			}
		}

		/**
		 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Attributes/autocomplete">The HTML autocomplete attribute</a>.
		 * <p>
		 * We're making our best guess which values are applicable.
		 * This is to give a nice short list in code assist.
		 * TODO: Find somewhere this relationship is defined (if there is any).
		 * </p>
		 *
		 * @see INPUT.Autocomplete
		 */
		public enum Autocomplete implements Function<AnyDocument<?>, String> {
			OFF(INPUT.Autocomplete.OFF),
			ON(INPUT.Autocomplete.ON),
			TEL(INPUT.Autocomplete.TEL),
			TEL_COUNTRY_CODE(INPUT.Autocomplete.TEL_COUNTRY_CODE),
			TEL_NATIONAL(INPUT.Autocomplete.TEL_NATIONAL),
			TEL_AREA_CODE(INPUT.Autocomplete.TEL_AREA_CODE),
			TEL_LOCAL(INPUT.Autocomplete.TEL_LOCAL),
			TEL_LOCAL_PREFIX(INPUT.Autocomplete.TEL_LOCAL_PREFIX),
			TEL_LOCAL_SUFFIX(INPUT.Autocomplete.TEL_LOCAL_SUFFIX),
			TEL_EXTENSION(INPUT.Autocomplete.TEL_EXTENSION);

			private final INPUT.Autocomplete value;

			private Autocomplete(INPUT.Autocomplete value) {
				this.value = value;
			}

			@Override
			public String toString() {
				return value.toString();
			}

			@Override
			public String apply(AnyDocument<?> document) {
				return value.apply(document);
			}

			public INPUT.Autocomplete getValue() {
				return value;
			}

			static {
				for(Autocomplete value : values()) {
					if(!value.name().equals(value.value.name())) throw new AssertionError("Enum name mismatch");
				}
			}
		}

		@Override
		protected void openWriteType(Writer out) throws IOException {
			assert !document.getAtnl();
			out.write(" type=\"tel\"");
		}
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_input_type_text.asp">HTML input type="text"</a>.
	 *
	 * @param  <D>   This document type
	 * @param  <PC>  The parent content model this element is within
	 */
	public static class Text<
		D  extends AnyDocument<D>,
		PC extends Union_Interactive_Phrasing<D, PC>
	> extends INPUT<D, PC, Text<D, PC>> implements
		com.aoindustries.html.attributes.Enum.Autocomplete<Text<D, PC>, INPUT.Autocomplete>,
		com.aoindustries.html.attributes.Integer.Maxlength<Text<D, PC>>,
		com.aoindustries.html.attributes.Integer.Minlength<Text<D, PC>>,
		com.aoindustries.html.attributes.Text.List<Text<D, PC>>,
		com.aoindustries.html.attributes.Text.Placeholder<Text<D, PC>>,
		com.aoindustries.html.attributes.Boolean.Readonly<Text<D, PC>>,
		com.aoindustries.html.attributes.Integer.Size<Text<D, PC>>,
		com.aoindustries.html.attributes.Text.Value<Text<D, PC>>,
		// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
		com.aoindustries.html.attributes.event.form.Onchange<Text<D, PC>>,
		com.aoindustries.html.attributes.event.form.Oninput<Text<D, PC>>,
		com.aoindustries.html.attributes.event.form.Onselect<Text<D, PC>>
	{

		public Text(D document, PC pc) {
			super(document, pc);
		}

		@Override
		protected void openWriteType(Writer out) throws IOException {
			assert !document.getAtnl();
			out.write(" type=\"text\"");
		}
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_input_type_time.asp">HTML input type="time"</a>.
	 *
	 * @param  <D>   This document type
	 * @param  <PC>  The parent content model this element is within
	 */
	public static class Time<
		D  extends AnyDocument<D>,
		PC extends Union_Interactive_Phrasing<D, PC>
	> extends INPUT<D, PC, Time<D, PC>> implements
		com.aoindustries.html.attributes.Enum.Autocomplete<Time<D, PC>, Time.Autocomplete>,
		com.aoindustries.html.attributes.Text.List<Time<D, PC>>,
		com.aoindustries.html.attributes.Boolean.Readonly<Time<D, PC>>, // Guessed
		com.aoindustries.html.attributes.Text.Value<Time<D, PC>>,
		// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
		com.aoindustries.html.attributes.event.form.Onchange<Time<D, PC>>,
		com.aoindustries.html.attributes.event.form.Oninput<Time<D, PC>>
	{

		public Time(D document, PC pc) {
			super(document, pc);
			if(document.doctype != Doctype.HTML5) {
				throw new LocalizedIllegalArgumentException(
					RESOURCES,
					"typeOnlySupportedInHtml5",
					document.doctype,
					"time"
				);
			}
		}

		/**
		 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Attributes/autocomplete">The HTML autocomplete attribute</a>.
		 * <p>
		 * We're making our best guess which values are applicable.
		 * This is to give a nice short list in code assist.
		 * TODO: Find somewhere this relationship is defined (if there is any).
		 * </p>
		 *
		 * @see INPUT.Autocomplete
		 */
		public enum Autocomplete implements Function<AnyDocument<?>, String> {
			OFF(INPUT.Autocomplete.OFF),
			ON(INPUT.Autocomplete.ON);

			private final INPUT.Autocomplete value;

			private Autocomplete(INPUT.Autocomplete value) {
				this.value = value;
			}

			@Override
			public String toString() {
				return value.toString();
			}

			@Override
			public String apply(AnyDocument<?> document) {
				return value.apply(document);
			}

			public INPUT.Autocomplete getValue() {
				return value;
			}

			static {
				for(Autocomplete value : values()) {
					if(!value.name().equals(value.value.name())) throw new AssertionError("Enum name mismatch");
				}
			}
		}

		@Override
		protected void openWriteType(Writer out) throws IOException {
			assert !document.getAtnl();
			out.write(" type=\"time\"");
		}
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_input_type_url.asp">HTML input type="url"</a>.
	 *
	 * @param  <D>   This document type
	 * @param  <PC>  The parent content model this element is within
	 */
	public static class Url<
		D  extends AnyDocument<D>,
		PC extends Union_Interactive_Phrasing<D, PC>
	> extends INPUT<D, PC, Url<D, PC>> implements
		com.aoindustries.html.attributes.Enum.Autocomplete<Url<D, PC>, Url.Autocomplete>,
		com.aoindustries.html.attributes.Text.List<Url<D, PC>>,
		com.aoindustries.html.attributes.Integer.Maxlength<Url<D, PC>>,
		com.aoindustries.html.attributes.Integer.Minlength<Url<D, PC>>,
		com.aoindustries.html.attributes.Text.Placeholder<Url<D, PC>>,
		com.aoindustries.html.attributes.Boolean.Readonly<Url<D, PC>>,
		com.aoindustries.html.attributes.Integer.Size<Url<D, PC>>,
		com.aoindustries.html.attributes.Text.Value<Url<D, PC>>,
		// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
		com.aoindustries.html.attributes.event.form.Onchange<Url<D, PC>>,
		com.aoindustries.html.attributes.event.form.Oninput<Url<D, PC>>,
		com.aoindustries.html.attributes.event.form.Onselect<Url<D, PC>> // Guessed (to match Placeholder)
	{

		public Url(D document, PC pc) {
			super(document, pc);
			if(document.doctype != Doctype.HTML5) {
				throw new LocalizedIllegalArgumentException(
					RESOURCES,
					"typeOnlySupportedInHtml5",
					document.doctype,
					"url"
				);
			}
		}

		/**
		 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Attributes/autocomplete">The HTML autocomplete attribute</a>.
		 * <p>
		 * We're making our best guess which values are applicable.
		 * This is to give a nice short list in code assist.
		 * TODO: Find somewhere this relationship is defined (if there is any).
		 * </p>
		 *
		 * @see INPUT.Autocomplete
		 */
		public enum Autocomplete implements Function<AnyDocument<?>, String> {
			OFF(INPUT.Autocomplete.OFF),
			ON(INPUT.Autocomplete.ON),
			EMAIL(INPUT.Autocomplete.EMAIL),
			TEL(INPUT.Autocomplete.TEL),
			TEL_NATIONAL(INPUT.Autocomplete.TEL_NATIONAL),
			TEL_LOCAL(INPUT.Autocomplete.TEL_LOCAL),
			IMPP(INPUT.Autocomplete.IMPP),
			URL(INPUT.Autocomplete.URL),
			PHOTO(INPUT.Autocomplete.PHOTO);

			private final INPUT.Autocomplete value;

			private Autocomplete(INPUT.Autocomplete value) {
				this.value = value;
			}

			@Override
			public String toString() {
				return value.toString();
			}

			@Override
			public String apply(AnyDocument<?> document) {
				return value.apply(document);
			}

			public INPUT.Autocomplete getValue() {
				return value;
			}

			static {
				for(Autocomplete value : values()) {
					if(!value.name().equals(value.value.name())) throw new AssertionError("Enum name mismatch");
				}
			}
		}

		@Override
		protected void openWriteType(Writer out) throws IOException {
			assert !document.getAtnl();
			out.write(" type=\"url\"");
		}
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_input_type_week.asp">HTML input type="week"</a>.
	 *
	 * @param  <D>   This document type
	 * @param  <PC>  The parent content model this element is within
	 */
	public static class Week<
		D  extends AnyDocument<D>,
		PC extends Union_Interactive_Phrasing<D, PC>
	> extends INPUT<D, PC, Week<D, PC>> implements
		com.aoindustries.html.attributes.Enum.Autocomplete<Week<D, PC>, Week.Autocomplete>,
		com.aoindustries.html.attributes.Text.List<Week<D, PC>>,
		com.aoindustries.html.attributes.Boolean.Readonly<Week<D, PC>>, // Guessed
		com.aoindustries.html.attributes.Text.Value<Week<D, PC>>,
		// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
		com.aoindustries.html.attributes.event.form.Onchange<Week<D, PC>>,
		com.aoindustries.html.attributes.event.form.Oninput<Week<D, PC>>
	{

		public Week(D document, PC pc) {
			super(document, pc);
			if(document.doctype != Doctype.HTML5) {
				throw new LocalizedIllegalArgumentException(
					RESOURCES,
					"typeOnlySupportedInHtml5",
					document.doctype,
					"week"
				);
			}
		}

		/**
		 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Attributes/autocomplete">The HTML autocomplete attribute</a>.
		 * <p>
		 * We're making our best guess which values are applicable.
		 * This is to give a nice short list in code assist.
		 * TODO: Find somewhere this relationship is defined (if there is any).
		 * </p>
		 *
		 * @see INPUT.Autocomplete
		 */
		public enum Autocomplete implements Function<AnyDocument<?>, String> {
			OFF(INPUT.Autocomplete.OFF),
			ON(INPUT.Autocomplete.ON),
			CC_EXP(INPUT.Autocomplete.CC_EXP),
			BDAY(INPUT.Autocomplete.BDAY);

			private final INPUT.Autocomplete value;

			private Autocomplete(INPUT.Autocomplete value) {
				this.value = value;
			}

			@Override
			public String toString() {
				return value.toString();
			}

			@Override
			public String apply(AnyDocument<?> document) {
				return value.apply(document);
			}

			public INPUT.Autocomplete getValue() {
				return value;
			}

			static {
				for(Autocomplete value : values()) {
					if(!value.name().equals(value.value.name())) throw new AssertionError("Enum name mismatch");
				}
			}
		}

		@Override
		protected void openWriteType(Writer out) throws IOException {
			assert !document.getAtnl();
			out.write(" type=\"week\"");
		}
	}
}
