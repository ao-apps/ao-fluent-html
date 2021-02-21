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
import com.aoindustries.encoding.Serialization;
import static com.aoindustries.encoding.TextInXhtmlAttributeEncoder.encodeTextInXhtmlAttribute;
import static com.aoindustries.encoding.TextInXhtmlAttributeEncoder.textInXhtmlAttributeEncoder;
import com.aoindustries.lang.LocalizedIllegalArgumentException;
import com.aoindustries.lang.LocalizedIllegalStateException;
import com.aoindustries.lang.Strings;
import com.aoindustries.util.i18n.MarkupType;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;

/**
 * <p>
 * This has the set of attributes common to all input types.  There are also
 * type-specific subclasses that add type-specific attributes.  Furthermore,
 * there is a {@link Input.Dynamic} implementation that has all the input attributes,
 * supporting unanticipated or more dynamic configurations.
 * </p>
 * <ul>
 * <li>See <a href="https://html.spec.whatwg.org/#the-input-element">4.10.5 The input element</a>.</li>
 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input">&lt;input&gt;: The Input (Form Input) element</a>.</li>
 * <li>See <a href="https://www.w3schools.com/tags/tag_input.asp">HTML input tag</a>.</li>
 * </ul>
 *
 * @param  <PC>  The parent content model this element is within
 *
 * @author  AO Industries, Inc.
 */
public abstract class Input<E extends Input<E, PC>, PC extends Content> extends VoidElement<E, PC> implements
	Attributes.Boolean.Autofocus<E>,
	// TODO: dirname
	Attributes.Boolean.Disabled<E>,
	// TODO: form
	// TODO: formaction
	// TODO: formenctype
	// TODO: formmethod
	// TODO: formnovalidate
	// TODO: formtarget
	// TODO: inputmode here or global?
	// TODO: max
	// TODO: min
	Attributes.Text.Name<E>,
	// TODO: pattern
	// TODO: required
	// TODO: step
	// Global Attributes: https://www.w3schools.com/tags/ref_standardattributes.asp
	Attributes.Integer.TabindexHtml4<E>,
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	Attributes.Event.AlmostGlobal<E>,
	Attributes.Event.Form.Oninvalid<E>
{

	private static final com.aoindustries.i18n.Resources RESOURCES =
		com.aoindustries.i18n.Resources.getResources(Input.class);

	public Input(Document document) {
		super(document);
	}

	@Override
	protected E open() throws IOException {
		@SuppressWarnings("unchecked") E element = (E)this;
		document.out.write("<input");
		openWriteType();
		return element;
	}

	protected abstract void openWriteType() throws IOException;

	/**
	 * <p>
	 * The complete list of expected autocomplete values.  Specific input types may provide
	 * a shorter list.
	 * </p>
	 * <p>
	 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Attributes/autocomplete">The HTML autocomplete attribute</a>.
	 * </p>
	 */
	public enum Autocomplete implements Attributes.Enum.EnumSupplier {
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

		private Autocomplete(java.lang.String value) {
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
	 * @param  <PC>  The parent content model this element is within
	 */
	@SuppressWarnings("deprecation")
	public static class Dynamic<PC extends Content> extends Input<Dynamic<PC>, PC> implements
		Attributes.Text.Accept<Dynamic<PC>>,
		Attributes.Enum.Align<Dynamic<PC>, Image.Align>,
		Attributes.Text.Alt<Dynamic<PC>>,
		Attributes.Enum.Autocomplete<Dynamic<PC>, Input.Autocomplete>,
		Attributes.Enum.Capture<Dynamic<PC>, File.Capture>,
		Attributes.Boolean.Checked<Dynamic<PC>>,
		Attributes.Integer.HeightHtml5Only<Dynamic<PC>>,
		Attributes.Text.List<Dynamic<PC>>,
		Attributes.Integer.Maxlength<Dynamic<PC>>,
		Attributes.Integer.Minlength<Dynamic<PC>>,
		Attributes.Boolean.Multiple<Dynamic<PC>>,
		Attributes.Text.Placeholder<Dynamic<PC>>,
		Attributes.Boolean.Readonly<Dynamic<PC>>,
		Attributes.Integer.Size<Dynamic<PC>>,
		Attributes.Url.Src<Dynamic<PC>>,
		Attributes.Enum.Type<Dynamic<PC>, Dynamic.Type>,
		Attributes.Text.Value<Dynamic<PC>>,
		Attributes.Integer.WidthHtml5Only<Dynamic<PC>>,
		// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
		Attributes.Event.Media.Onabort<Dynamic<PC>>,
		Attributes.Event.Window.Onerror<Dynamic<PC>>,
		Attributes.Event.Window.Onload<Dynamic<PC>>,
		Attributes.Event.Form.Onchange<Dynamic<PC>>,
		Attributes.Event.Form.Oninput<Dynamic<PC>>,
		Attributes.Event.Form.Onsearch<Dynamic<PC>>,
		Attributes.Event.Form.Onselect<Dynamic<PC>>
	{

		private String type;
		public Dynamic(Document document) {
			super(document);
			this.type = null;
		}

		public Dynamic(Document document, String type) {
			super(document);
			type = Strings.trimNullIfEmpty(type);
			this.type = (type == null) ? null : type.toLowerCase(Locale.ROOT);
		}

		public Dynamic(Document document, Type type) {
			super(document);
			this.type = (type == null) ? null : type.getValue();
		}

		@Override
		protected void openWriteType() throws IOException {
			// Write the type now, if already set
			String t = this.type;
			if(t != null) {
				// Unset to avoid duplicate attribute
				this.type = null;
				Dynamic<PC> i = type(t);
				assert i == this;
			}
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_input_type.asp">HTML input type Attribute</a>.
		 */
		public enum Type implements Attributes.Enum.EnumSupplier {
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
			public String get(Serialization serialization, Doctype doctype) {
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
		public Dynamic<PC> type(String type) throws IOException {
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
						Resources.PACKAGE_RESOURCES,
						"Document.duplicateAttribute",
						"input",
						"type",
						this.type,
						type
					);
				}
				this.type = type;
				document.out.write(" type=\"");
				encodeTextInXhtmlAttribute(type, document.out);
				document.out.write('"');
			}
			return this;
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_input_type.asp">HTML input type Attribute</a>.
		 */
		@Override
		public Dynamic<PC> type(Type type) throws IOException {
			if(type != null) {
				if(this.type != null) {
					throw new LocalizedIllegalStateException(
						Resources.PACKAGE_RESOURCES,
						"Document.duplicateAttribute",
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
				document.out.write(" type=\"");
				document.out.write(type.value); // No encoding, is a known safe value.  TODO: Assert this above in static initializer?
				document.out.write('"');
			}
			return this;
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_input_value.asp">HTML input value Attribute</a>.
		 */
		@Override
		public Dynamic<PC> value(Object value) throws IOException {
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
	 * @param  <PC>  The parent content model this element is within
	 */
	public static class Button<PC extends Content> extends Input<Button<PC>, PC> implements
		Attributes.Text.Value<Button<PC>>
	{

		public Button(Document document) {
			super(document);
		}

		@Override
		protected void openWriteType() throws IOException {
			document.out.write(" type=\"button\"");
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_input_value.asp">HTML input value Attribute</a>.
		 *
		 * @see Dynamic.Type#BUTTON
		 */
		@Override
		public Button<PC> value(Object value) throws IOException {
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
	 * @param  <PC>  The parent content model this element is within
	 */
	public static class Checkbox<PC extends Content> extends Input<Checkbox<PC>, PC> implements
		Attributes.Boolean.Checked<Checkbox<PC>>,
		Attributes.Text.Value<Checkbox<PC>>,
		// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
		Attributes.Event.Form.Onchange<Checkbox<PC>>
	{

		public Checkbox(Document document) {
			super(document);
		}

		@Override
		protected void openWriteType() throws IOException {
			document.out.write(" type=\"checkbox\"");
		}
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_input_type_color.asp">HTML input type="color"</a>.
	 *
	 * @param  <PC>  The parent content model this element is within
	 */
	public static class Color<PC extends Content> extends Input<Color<PC>, PC> implements
		Attributes.Enum.Autocomplete<Color<PC>, Color.Autocomplete>,
		Attributes.Text.List<Color<PC>>,
		Attributes.Boolean.Readonly<Color<PC>>, // Guessed
		Attributes.Text.Value<Color<PC>>,
		// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
		Attributes.Event.Form.Onchange<Color<PC>>,
		Attributes.Event.Form.Oninput<Color<PC>>
	{

		public Color(Document document) {
			super(document);
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
		 * @see Input.Autocomplete
		 */
		public enum Autocomplete implements Attributes.Enum.EnumSupplier {
			OFF(Input.Autocomplete.OFF),
			ON(Input.Autocomplete.ON);

			private final Input.Autocomplete value;

			private Autocomplete(Input.Autocomplete value) {
				this.value = value;
			}

			@Override
			public java.lang.String toString() {
				return value.toString();
			}

			@Override
			public java.lang.String get(Serialization serialization, Doctype doctype) {
				return value.get(serialization, doctype);
			}

			public Input.Autocomplete getValue() {
				return value;
			}

			static {
				for(Autocomplete value : values()) {
					if(!value.name().equals(value.value.name())) throw new AssertionError("Enum name mismatch");
				}
			}
		}

		@Override
		protected void openWriteType() throws IOException {
			document.out.write(" type=\"color\"");
		}
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_input_type_date.asp">HTML input type="date"</a>.
	 *
	 * @param  <PC>  The parent content model this element is within
	 */
	public static class Date<PC extends Content> extends Input<Date<PC>, PC> implements
		Attributes.Enum.Autocomplete<Date<PC>, Date.Autocomplete>,
		Attributes.Text.List<Date<PC>>,
		Attributes.Boolean.Readonly<Date<PC>>, // Guessed
		Attributes.Text.Value<Date<PC>>,
		// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
		Attributes.Event.Form.Onchange<Date<PC>>,
		Attributes.Event.Form.Oninput<Date<PC>>
	{

		public Date(Document document) {
			super(document);
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
		 * @see Input.Autocomplete
		 */
		public enum Autocomplete implements Attributes.Enum.EnumSupplier {
			OFF(Input.Autocomplete.OFF),
			ON(Input.Autocomplete.ON),
			CC_EXP(Input.Autocomplete.CC_EXP),
			BDAY(Input.Autocomplete.BDAY);

			private final Input.Autocomplete value;

			private Autocomplete(Input.Autocomplete value) {
				this.value = value;
			}

			@Override
			public java.lang.String toString() {
				return value.toString();
			}

			@Override
			public java.lang.String get(Serialization serialization, Doctype doctype) {
				return value.get(serialization, doctype);
			}

			public Input.Autocomplete getValue() {
				return value;
			}

			static {
				for(Autocomplete value : values()) {
					if(!value.name().equals(value.value.name())) throw new AssertionError("Enum name mismatch");
				}
			}
		}

		@Override
		protected void openWriteType() throws IOException {
			document.out.write(" type=\"date\"");
		}
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_input_type_datetime-local.asp">HTML input type="datetime-local"</a>.
	 *
	 * @param  <PC>  The parent content model this element is within
	 */
	public static class DatetimeLocal<PC extends Content> extends Input<DatetimeLocal<PC>, PC> implements
		Attributes.Enum.Autocomplete<DatetimeLocal<PC>, DatetimeLocal.Autocomplete>,
		Attributes.Text.List<DatetimeLocal<PC>>,
		Attributes.Boolean.Readonly<DatetimeLocal<PC>>, // Guessed
		Attributes.Text.Value<DatetimeLocal<PC>>,
		// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
		Attributes.Event.Form.Onchange<DatetimeLocal<PC>>,
		Attributes.Event.Form.Oninput<DatetimeLocal<PC>>
	{

		public DatetimeLocal(Document document) {
			super(document);
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
		 * @see Input.Autocomplete
		 */
		public enum Autocomplete implements Attributes.Enum.EnumSupplier {
			OFF(Input.Autocomplete.OFF),
			ON(Input.Autocomplete.ON),
			BDAY(Input.Autocomplete.BDAY);

			private final Input.Autocomplete value;

			private Autocomplete(Input.Autocomplete value) {
				this.value = value;
			}

			@Override
			public java.lang.String toString() {
				return value.toString();
			}

			@Override
			public java.lang.String get(Serialization serialization, Doctype doctype) {
				return value.get(serialization, doctype);
			}

			public Input.Autocomplete getValue() {
				return value;
			}

			static {
				for(Autocomplete value : values()) {
					if(!value.name().equals(value.value.name())) throw new AssertionError("Enum name mismatch");
				}
			}
		}

		@Override
		protected void openWriteType() throws IOException {
			document.out.write(" type=\"datetime-local\"");
		}
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_input_type_email.asp">HTML input type="email"</a>.
	 *
	 * @param  <PC>  The parent content model this element is within
	 */
	public static class Email<PC extends Content> extends Input<Email<PC>, PC> implements
		Attributes.Enum.Autocomplete<Email<PC>, Email.Autocomplete>,
		Attributes.Text.List<Email<PC>>,
		Attributes.Integer.Maxlength<Email<PC>>,
		Attributes.Integer.Minlength<Email<PC>>,
		Attributes.Boolean.Multiple<Email<PC>>,
		Attributes.Text.Placeholder<Email<PC>>,
		Attributes.Boolean.Readonly<Email<PC>>,
		Attributes.Integer.Size<Email<PC>>,
		Attributes.Text.Value<Email<PC>>,
		// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
		Attributes.Event.Form.Onchange<Email<PC>>,
		Attributes.Event.Form.Oninput<Email<PC>>,
		Attributes.Event.Form.Onselect<Email<PC>> // Guessed (to match Placeholder)
	{

		public Email(Document document) {
			super(document);
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
		 * @see Input.Autocomplete
		 */
		public enum Autocomplete implements Attributes.Enum.EnumSupplier {
			OFF(Input.Autocomplete.OFF),
			ON(Input.Autocomplete.ON),
			EMAIL(Input.Autocomplete.EMAIL),
			IMPP(Input.Autocomplete.IMPP),
			URL(Input.Autocomplete.URL);

			private final Input.Autocomplete value;

			private Autocomplete(Input.Autocomplete value) {
				this.value = value;
			}

			@Override
			public java.lang.String toString() {
				return value.toString();
			}

			@Override
			public java.lang.String get(Serialization serialization, Doctype doctype) {
				return value.get(serialization, doctype);
			}

			public Input.Autocomplete getValue() {
				return value;
			}

			static {
				for(Autocomplete value : values()) {
					if(!value.name().equals(value.value.name())) throw new AssertionError("Enum name mismatch");
				}
			}
		}

		@Override
		protected void openWriteType() throws IOException {
			document.out.write(" type=\"email\"");
		}
	}

	/**
	 * <ul>
	 * <li>See <a href="https://www.w3schools.com/tags/att_input_type_file.asp">HTML input type="file"</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input/file">&lt;input type="file"&gt;</a>.</li>
	 * </ul>
	 *
	 * @param  <PC>  The parent content model this element is within
	 */
	public static class File<PC extends Content> extends Input<File<PC>, PC> implements
		Attributes.Text.Accept<File<PC>>,
		Attributes.Enum.Capture<File<PC>, File.Capture>,
		Attributes.Boolean.Multiple<File<PC>>,
		// Does not support value per https://www.w3schools.com/tags/att_input_value.asp: Attributes.Text.Value<File>
		// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
		Attributes.Event.Form.Onchange<File<PC>>,
		Attributes.Event.Form.Onselect<File<PC>>
	{

		public File(Document document) {
			super(document);
		}

		/**
		 * See <a href="https://www.w3.org/TR/mediacapture-streams/#dom-videofacingmodeenum">Media Capture and Streams: VideoFacingModeEnum</a>.
		 */
		public enum Capture implements Attributes.Enum.EnumSupplier {

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
			public String get(Serialization serialization, Doctype doctype) {
				return value;
			}
		}

		@Override
		protected void openWriteType() throws IOException {
			document.out.write(" type=\"file\"");
		}
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_input_type_hidden.asp">HTML input type="hidden"</a>.
	 *
	 * @param  <PC>  The parent content model this element is within
	 */
	public static class Hidden<PC extends Content> extends Input<Hidden<PC>, PC> implements
		Attributes.Enum.Autocomplete<Hidden<PC>, Input.Autocomplete>,
		Attributes.Text.Value<Hidden<PC>>
	{

		public Hidden(Document document) {
			super(document);
		}

		@Override
		protected void openWriteType() throws IOException {
			document.out.write(" type=\"hidden\"");
		}
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_input_type_image.asp">HTML input type="image"</a>.
	 *
	 * @param  <PC>  The parent content model this element is within
	 */
	@SuppressWarnings("deprecation")
	public static class Image<PC extends Content> extends Input<Image<PC>, PC> implements
		Attributes.Enum.Align<Image<PC>, Image.Align>,
		Attributes.Text.Alt<Image<PC>>,
		Attributes.Integer.HeightHtml5Only<Image<PC>>,
		Attributes.Url.Src<Image<PC>>,
		Attributes.Text.Value<Image<PC>>,
		Attributes.Integer.WidthHtml5Only<Image<PC>>,
		// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
		Attributes.Event.Media.Onabort<Image<PC>>,
		Attributes.Event.Window.Onerror<Image<PC>>,
		Attributes.Event.Window.Onload<Image<PC>>
	{

		public Image(Document document) {
			super(document);
		}

		@Override
		protected void openWriteType() throws IOException {
			document.out.write(" type=\"image\"");
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_input_align.asp">HTML input align Attribute</a>.
		 *
		 * @deprecated  The align attribute of &lt;input&gt; is not supported in HTML5. Use CSS instead.
		 */
		@Deprecated
		public enum Align implements Attributes.Enum.EnumSupplier {

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
			public String get(Serialization serialization, Doctype doctype) {
				return value;
			}
		}
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_input_type_month.asp">HTML input type="month"</a>.
	 *
	 * @param  <PC>  The parent content model this element is within
	 */
	public static class Month<PC extends Content> extends Input<Month<PC>, PC> implements
		Attributes.Enum.Autocomplete<Month<PC>, Month.Autocomplete>,
		Attributes.Text.List<Month<PC>>,
		Attributes.Boolean.Readonly<Month<PC>>, // Guessed
		Attributes.Text.Value<Month<PC>>,
		// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
		Attributes.Event.Form.Onchange<Month<PC>>,
		Attributes.Event.Form.Oninput<Month<PC>>
	{

		public Month(Document document) {
			super(document);
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
		 * @see Input.Autocomplete
		 */
		public enum Autocomplete implements Attributes.Enum.EnumSupplier {
			OFF(Input.Autocomplete.OFF),
			ON(Input.Autocomplete.ON),
			CC_EXP(Input.Autocomplete.CC_EXP),
			CC_EXP_MONTH(Input.Autocomplete.CC_EXP_MONTH),
			BDAY_MONTH(Input.Autocomplete.BDAY_MONTH);

			private final Input.Autocomplete value;

			private Autocomplete(Input.Autocomplete value) {
				this.value = value;
			}

			@Override
			public java.lang.String toString() {
				return value.toString();
			}

			@Override
			public java.lang.String get(Serialization serialization, Doctype doctype) {
				return value.get(serialization, doctype);
			}

			public Input.Autocomplete getValue() {
				return value;
			}

			static {
				for(Autocomplete value : values()) {
					if(!value.name().equals(value.value.name())) throw new AssertionError("Enum name mismatch");
				}
			}
		}

		@Override
		protected void openWriteType() throws IOException {
			document.out.write(" type=\"month\"");
		}
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_input_type_number.asp">HTML input type="number"</a>.
	 *
	 * @param  <PC>  The parent content model this element is within
	 */
	public static class Number<PC extends Content> extends Input<Number<PC>, PC> implements
		Attributes.Enum.Autocomplete<Number<PC>, Number.Autocomplete>,
		Attributes.Text.List<Number<PC>>,
		Attributes.Boolean.Readonly<Number<PC>>,
		Attributes.Text.Value<Number<PC>>, // TODO: Review types (this and others), perhaps Attributes.Number or similar?
		// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
		Attributes.Event.Form.Onchange<Number<PC>>,
		Attributes.Event.Form.Oninput<Number<PC>>
	{

		public Number(Document document) {
			super(document);
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
		 * @see Input.Autocomplete
		 */
		public enum Autocomplete implements Attributes.Enum.EnumSupplier {
			OFF(Input.Autocomplete.OFF),
			ON(Input.Autocomplete.ON),
			ONE_TIME_CODE(Input.Autocomplete.ONE_TIME_CODE),
			ADDRESS_LEVEL4(Input.Autocomplete.ADDRESS_LEVEL4),
			ADDRESS_LEVEL3(Input.Autocomplete.ADDRESS_LEVEL3),
			ADDRESS_LEVEL2(Input.Autocomplete.ADDRESS_LEVEL2),
			ADDRESS_LEVEL1(Input.Autocomplete.ADDRESS_LEVEL1),
			POSTAL_CODE(Input.Autocomplete.POSTAL_CODE),
			CC_NUMBER(Input.Autocomplete.CC_NUMBER),
			CC_EXP_MONTH(Input.Autocomplete.CC_EXP_MONTH),
			CC_EXP_YEAR(Input.Autocomplete.CC_EXP_YEAR),
			CC_CSC(Input.Autocomplete.CC_CSC),
			TRANSACTION_AMOUNT(Input.Autocomplete.TRANSACTION_AMOUNT),
			BDAY_DAY(Input.Autocomplete.BDAY_DAY),
			BDAY_MONTH(Input.Autocomplete.BDAY_MONTH),
			BDAY_YEAR(Input.Autocomplete.BDAY_YEAR),
			TEL_COUNTRY_CODE(Input.Autocomplete.TEL_COUNTRY_CODE),
			TEL_AREA_CODE(Input.Autocomplete.TEL_AREA_CODE),
			TEL_LOCAL_PREFIX(Input.Autocomplete.TEL_LOCAL_PREFIX),
			TEL_LOCAL_SUFFIX(Input.Autocomplete.TEL_LOCAL_SUFFIX),
			TEL_EXTENSION(Input.Autocomplete.TEL_EXTENSION);

			private final Input.Autocomplete value;

			private Autocomplete(Input.Autocomplete value) {
				this.value = value;
			}

			@Override
			public java.lang.String toString() {
				return value.toString();
			}

			@Override
			public java.lang.String get(Serialization serialization, Doctype doctype) {
				return value.get(serialization, doctype);
			}

			public Input.Autocomplete getValue() {
				return value;
			}

			static {
				for(Autocomplete value : values()) {
					if(!value.name().equals(value.value.name())) throw new AssertionError("Enum name mismatch");
				}
			}
		}

		@Override
		protected void openWriteType() throws IOException {
			document.out.write(" type=\"number\"");
		}
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_input_type_password.asp">HTML input type="password"</a>.
	 *
	 * @param  <PC>  The parent content model this element is within
	 */
	public static class Password<PC extends Content> extends Input<Password<PC>, PC> implements
		Attributes.Enum.Autocomplete<Password<PC>, Password.Autocomplete>,
		Attributes.Integer.Maxlength<Password<PC>>,
		Attributes.Integer.Minlength<Password<PC>>,
		Attributes.Text.Placeholder<Password<PC>>,
		Attributes.Boolean.Readonly<Password<PC>>,
		Attributes.Integer.Size<Password<PC>>,
		Attributes.Text.Value<Password<PC>>,
		// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
		Attributes.Event.Form.Onchange<Password<PC>>,
		Attributes.Event.Form.Oninput<Password<PC>>,
		Attributes.Event.Form.Onselect<Password<PC>>
	{

		public Password(Document document) {
			super(document);
		}

		/**
		 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Attributes/autocomplete">The HTML autocomplete attribute</a>.
		 * <p>
		 * We're making our best guess which values are applicable.
		 * This is to give a nice short list in code assist.
		 * TODO: Find somewhere this relationship is defined (if there is any).
		 * </p>
		 *
		 * @see Input.Autocomplete
		 */
		public enum Autocomplete implements Attributes.Enum.EnumSupplier {
			OFF(Input.Autocomplete.OFF),
			ON(Input.Autocomplete.ON),
			NEW_PASSWORD(Input.Autocomplete.NEW_PASSWORD),
			CURRENT_PASSWORD(Input.Autocomplete.CURRENT_PASSWORD),
			ONE_TIME_CODE(Input.Autocomplete.ONE_TIME_CODE),
			CC_NUMBER(Input.Autocomplete.CC_NUMBER),
			CC_EXP(Input.Autocomplete.CC_EXP),
			CC_EXP_MONTH(Input.Autocomplete.CC_EXP_MONTH),
			CC_EXP_YEAR(Input.Autocomplete.CC_EXP_YEAR),
			CC_CSC(Input.Autocomplete.CC_CSC);

			private final Input.Autocomplete value;

			private Autocomplete(Input.Autocomplete value) {
				this.value = value;
			}

			@Override
			public java.lang.String toString() {
				return value.toString();
			}

			@Override
			public java.lang.String get(Serialization serialization, Doctype doctype) {
				return value.get(serialization, doctype);
			}

			public Input.Autocomplete getValue() {
				return value;
			}

			static {
				for(Autocomplete value : values()) {
					if(!value.name().equals(value.value.name())) throw new AssertionError("Enum name mismatch");
				}
			}
		}

		@Override
		protected void openWriteType() throws IOException {
			document.out.write(" type=\"password\"");
		}
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_input_type_radio.asp">HTML input type="radio"</a>.
	 *
	 * @param  <PC>  The parent content model this element is within
	 */
	public static class Radio<PC extends Content> extends Input<Radio<PC>, PC> implements
		Attributes.Boolean.Checked<Radio<PC>>,
		Attributes.Text.Value<Radio<PC>>,
		// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
		Attributes.Event.Form.Onchange<Radio<PC>>
	{

		public Radio(Document document) {
			super(document);
		}

		@Override
		protected void openWriteType() throws IOException {
			document.out.write(" type=\"radio\"");
		}
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_input_type_range.asp">HTML input type="range"</a>.
	 *
	 * @param  <PC>  The parent content model this element is within
	 */
	public static class Range<PC extends Content> extends Input<Range<PC>, PC> implements
		Attributes.Enum.Autocomplete<Range<PC>, Range.Autocomplete>,
		Attributes.Text.List<Range<PC>>,
		Attributes.Text.Value<Range<PC>>,
		// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
		Attributes.Event.Form.Onchange<Range<PC>>,
		Attributes.Event.Form.Oninput<Range<PC>>
	{

		public Range(Document document) {
			super(document);
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
		 * @see Input.Autocomplete
		 */
		public enum Autocomplete implements Attributes.Enum.EnumSupplier {
			OFF(Input.Autocomplete.OFF),
			ON(Input.Autocomplete.ON);

			private final Input.Autocomplete value;

			private Autocomplete(Input.Autocomplete value) {
				this.value = value;
			}

			@Override
			public java.lang.String toString() {
				return value.toString();
			}

			@Override
			public java.lang.String get(Serialization serialization, Doctype doctype) {
				return value.get(serialization, doctype);
			}

			public Input.Autocomplete getValue() {
				return value;
			}

			static {
				for(Autocomplete value : values()) {
					if(!value.name().equals(value.value.name())) throw new AssertionError("Enum name mismatch");
				}
			}
		}

		@Override
		protected void openWriteType() throws IOException {
			document.out.write(" type=\"range\"");
		}
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_input_type_reset.asp">HTML input type="reset"</a>.
	 *
	 * @param  <PC>  The parent content model this element is within
	 */
	public static class Reset<PC extends Content> extends Input<Reset<PC>, PC> implements
		Attributes.Text.Value<Reset<PC>>
	{

		public Reset(Document document) {
			super(document);
		}

		@Override
		protected void openWriteType() throws IOException {
			document.out.write(" type=\"reset\"");
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_input_value.asp">HTML input value Attribute</a>.
		 *
		 * @see Dynamic.Type#RESET
		 */
		@Override
		public Reset<PC> value(Object value) throws IOException {
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
	 * @param  <PC>  The parent content model this element is within
	 */
	public static class Search<PC extends Content> extends Input<Search<PC>, PC> implements
		Attributes.Enum.Autocomplete<Search<PC>, Search.Autocomplete>,
		Attributes.Text.List<Search<PC>>,
		Attributes.Integer.Maxlength<Search<PC>>,
		Attributes.Integer.Minlength<Search<PC>>,
		Attributes.Text.Placeholder<Search<PC>>,
		Attributes.Boolean.Readonly<Search<PC>>,
		Attributes.Integer.Size<Search<PC>>,
		Attributes.Text.Value<Search<PC>>,
		// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
		Attributes.Event.Form.Onchange<Search<PC>>,
		Attributes.Event.Form.Oninput<Search<PC>>,
		Attributes.Event.Form.Onsearch<Search<PC>>,
		Attributes.Event.Form.Onselect<Search<PC>> // Guessed (to match Placeholder)
	{

		public Search(Document document) {
			super(document);
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
		 * @see Input.Autocomplete
		 */
		public enum Autocomplete implements Attributes.Enum.EnumSupplier {
			OFF(Input.Autocomplete.OFF),
			ON(Input.Autocomplete.ON),
			NAME(Input.Autocomplete.NAME),
			HONORIFIC_PREFIX(Input.Autocomplete.HONORIFIC_PREFIX),
			GIVEN_NAME(Input.Autocomplete.GIVEN_NAME),
			ADDITIONAL_NAME(Input.Autocomplete.ADDITIONAL_NAME),
			FAMILY_NAME(Input.Autocomplete.FAMILY_NAME),
			HONORIFIC_SUFFIX(Input.Autocomplete.HONORIFIC_SUFFIX),
			NICKNAME(Input.Autocomplete.NICKNAME),
			EMAIL(Input.Autocomplete.EMAIL),
			USERNAME(Input.Autocomplete.USERNAME),
			ORGANIZATION_TITLE(Input.Autocomplete.ORGANIZATION_TITLE),
			ORGANIZATION(Input.Autocomplete.ORGANIZATION),
			STREET_ADDRESS(Input.Autocomplete.STREET_ADDRESS),
			ADDRESS_LINE1(Input.Autocomplete.ADDRESS_LINE1),
			ADDRESS_LINE2(Input.Autocomplete.ADDRESS_LINE2),
			ADDRESS_LINE3(Input.Autocomplete.ADDRESS_LINE3),
			ADDRESS_LEVEL4(Input.Autocomplete.ADDRESS_LEVEL4),
			ADDRESS_LEVEL3(Input.Autocomplete.ADDRESS_LEVEL3),
			ADDRESS_LEVEL2(Input.Autocomplete.ADDRESS_LEVEL2),
			ADDRESS_LEVEL1(Input.Autocomplete.ADDRESS_LEVEL1),
			COUNTRY(Input.Autocomplete.COUNTRY),
			COUNTRY_NAME(Input.Autocomplete.COUNTRY_NAME),
			POSTAL_CODE(Input.Autocomplete.POSTAL_CODE),
			CC_NAME(Input.Autocomplete.CC_NAME),
			CC_GIVEN_NAME(Input.Autocomplete.CC_GIVEN_NAME),
			CC_ADDITIONAL_NAME(Input.Autocomplete.CC_ADDITIONAL_NAME),
			CC_FAMILY_NAME(Input.Autocomplete.CC_FAMILY_NAME),
			CC_TYPE(Input.Autocomplete.CC_TYPE),
			TRANSACTION_CURRENCY(Input.Autocomplete.TRANSACTION_CURRENCY),
			TRANSACTION_AMOUNT(Input.Autocomplete.TRANSACTION_AMOUNT),
			LANGUAGE(Input.Autocomplete.LANGUAGE),
			BDAY(Input.Autocomplete.BDAY),
			BDAY_DAY(Input.Autocomplete.BDAY_DAY),
			BDAY_MONTH(Input.Autocomplete.BDAY_MONTH),
			BDAY_YEAR(Input.Autocomplete.BDAY_YEAR),
			SEX(Input.Autocomplete.SEX),
			TEL(Input.Autocomplete.TEL),
			TEL_COUNTRY_CODE(Input.Autocomplete.TEL_COUNTRY_CODE),
			TEL_NATIONAL(Input.Autocomplete.TEL_NATIONAL),
			TEL_AREA_CODE(Input.Autocomplete.TEL_AREA_CODE),
			TEL_LOCAL(Input.Autocomplete.TEL_LOCAL),
			TEL_LOCAL_PREFIX(Input.Autocomplete.TEL_LOCAL_PREFIX),
			TEL_LOCAL_SUFFIX(Input.Autocomplete.TEL_LOCAL_SUFFIX),
			TEL_EXTENSION(Input.Autocomplete.TEL_EXTENSION),
			IMPP(Input.Autocomplete.IMPP),
			URL(Input.Autocomplete.URL),
			PHOTO(Input.Autocomplete.PHOTO);

			private final Input.Autocomplete value;

			private Autocomplete(Input.Autocomplete value) {
				this.value = value;
			}

			@Override
			public java.lang.String toString() {
				return value.toString();
			}

			@Override
			public java.lang.String get(Serialization serialization, Doctype doctype) {
				return value.get(serialization, doctype);
			}

			public Input.Autocomplete getValue() {
				return value;
			}

			static {
				for(Autocomplete value : values()) {
					if(!value.name().equals(value.value.name())) throw new AssertionError("Enum name mismatch");
				}
			}
		}

		@Override
		protected void openWriteType() throws IOException {
			document.out.write(" type=\"search\"");
		}
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_input_type_submit.asp">HTML input type="submit"</a>.
	 *
	 * @param  <PC>  The parent content model this element is within
	 */
	public static class Submit<PC extends Content> extends Input<Submit<PC>, PC> implements
		Attributes.Text.Value<Submit<PC>>
	{

		public Submit(Document document) {
			super(document);
		}

		@Override
		protected void openWriteType() throws IOException {
			document.out.write(" type=\"submit\"");
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_input_value.asp">HTML input value Attribute</a>.
		 *
		 * @see Dynamic.Type#SUBMIT
		 */
		@Override
		public Submit<PC> value(Object value) throws IOException {
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
	 * @param  <PC>  The parent content model this element is within
	 */
	public static class Tel<PC extends Content> extends Input<Tel<PC>, PC> implements
		Attributes.Enum.Autocomplete<Tel<PC>, Tel.Autocomplete>,
		Attributes.Text.List<Tel<PC>>,
		Attributes.Integer.Maxlength<Tel<PC>>,
		Attributes.Integer.Minlength<Tel<PC>>,
		Attributes.Text.Placeholder<Tel<PC>>,
		Attributes.Boolean.Readonly<Tel<PC>>, // Guessed
		Attributes.Integer.Size<Tel<PC>>,
		Attributes.Text.Value<Tel<PC>>,
		// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
		Attributes.Event.Form.Onchange<Tel<PC>>,
		Attributes.Event.Form.Oninput<Tel<PC>>,
		Attributes.Event.Form.Onselect<Tel<PC>> // Guessed (to match Placeholder)
	{

		public Tel(Document document) {
			super(document);
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
		 * @see Input.Autocomplete
		 */
		public enum Autocomplete implements Attributes.Enum.EnumSupplier {
			OFF(Input.Autocomplete.OFF),
			ON(Input.Autocomplete.ON),
			TEL(Input.Autocomplete.TEL),
			TEL_COUNTRY_CODE(Input.Autocomplete.TEL_COUNTRY_CODE),
			TEL_NATIONAL(Input.Autocomplete.TEL_NATIONAL),
			TEL_AREA_CODE(Input.Autocomplete.TEL_AREA_CODE),
			TEL_LOCAL(Input.Autocomplete.TEL_LOCAL),
			TEL_LOCAL_PREFIX(Input.Autocomplete.TEL_LOCAL_PREFIX),
			TEL_LOCAL_SUFFIX(Input.Autocomplete.TEL_LOCAL_SUFFIX),
			TEL_EXTENSION(Input.Autocomplete.TEL_EXTENSION);

			private final Input.Autocomplete value;

			private Autocomplete(Input.Autocomplete value) {
				this.value = value;
			}

			@Override
			public java.lang.String toString() {
				return value.toString();
			}

			@Override
			public java.lang.String get(Serialization serialization, Doctype doctype) {
				return value.get(serialization, doctype);
			}

			public Input.Autocomplete getValue() {
				return value;
			}

			static {
				for(Autocomplete value : values()) {
					if(!value.name().equals(value.value.name())) throw new AssertionError("Enum name mismatch");
				}
			}
		}

		@Override
		protected void openWriteType() throws IOException {
			document.out.write(" type=\"tel\"");
		}
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_input_type_text.asp">HTML input type="text"</a>.
	 *
	 * @param  <PC>  The parent content model this element is within
	 */
	public static class Text<PC extends Content> extends Input<Text<PC>, PC> implements
		Attributes.Enum.Autocomplete<Text<PC>, Input.Autocomplete>,
		Attributes.Integer.Maxlength<Text<PC>>,
		Attributes.Integer.Minlength<Text<PC>>,
		Attributes.Text.List<Text<PC>>,
		Attributes.Text.Placeholder<Text<PC>>,
		Attributes.Boolean.Readonly<Text<PC>>,
		Attributes.Integer.Size<Text<PC>>,
		Attributes.Text.Value<Text<PC>>,
		// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
		Attributes.Event.Form.Onchange<Text<PC>>,
		Attributes.Event.Form.Oninput<Text<PC>>,
		Attributes.Event.Form.Onselect<Text<PC>>
	{

		public Text(Document document) {
			super(document);
		}

		@Override
		protected void openWriteType() throws IOException {
			document.out.write(" type=\"text\"");
		}
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_input_type_time.asp">HTML input type="time"</a>.
	 *
	 * @param  <PC>  The parent content model this element is within
	 */
	public static class Time<PC extends Content> extends Input<Time<PC>, PC> implements
		Attributes.Enum.Autocomplete<Time<PC>, Time.Autocomplete>,
		Attributes.Text.List<Time<PC>>,
		Attributes.Boolean.Readonly<Time<PC>>, // Guessed
		Attributes.Text.Value<Time<PC>>,
		// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
		Attributes.Event.Form.Onchange<Time<PC>>,
		Attributes.Event.Form.Oninput<Time<PC>>
	{

		public Time(Document document) {
			super(document);
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
		 * @see Input.Autocomplete
		 */
		public enum Autocomplete implements Attributes.Enum.EnumSupplier {
			OFF(Input.Autocomplete.OFF),
			ON(Input.Autocomplete.ON);

			private final Input.Autocomplete value;

			private Autocomplete(Input.Autocomplete value) {
				this.value = value;
			}

			@Override
			public java.lang.String toString() {
				return value.toString();
			}

			@Override
			public java.lang.String get(Serialization serialization, Doctype doctype) {
				return value.get(serialization, doctype);
			}

			public Input.Autocomplete getValue() {
				return value;
			}

			static {
				for(Autocomplete value : values()) {
					if(!value.name().equals(value.value.name())) throw new AssertionError("Enum name mismatch");
				}
			}
		}

		@Override
		protected void openWriteType() throws IOException {
			document.out.write(" type=\"time\"");
		}
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_input_type_url.asp">HTML input type="url"</a>.
	 *
	 * @param  <PC>  The parent content model this element is within
	 */
	public static class Url<PC extends Content> extends Input<Url<PC>, PC> implements
		Attributes.Enum.Autocomplete<Url<PC>, Url.Autocomplete>,
		Attributes.Text.List<Url<PC>>,
		Attributes.Integer.Maxlength<Url<PC>>,
		Attributes.Integer.Minlength<Url<PC>>,
		Attributes.Text.Placeholder<Url<PC>>,
		Attributes.Boolean.Readonly<Url<PC>>,
		Attributes.Integer.Size<Url<PC>>,
		Attributes.Text.Value<Url<PC>>,
		// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
		Attributes.Event.Form.Onchange<Url<PC>>,
		Attributes.Event.Form.Oninput<Url<PC>>,
		Attributes.Event.Form.Onselect<Url<PC>> // Guessed (to match Placeholder)
	{

		public Url(Document document) {
			super(document);
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
		 * @see Input.Autocomplete
		 */
		public enum Autocomplete implements Attributes.Enum.EnumSupplier {
			OFF(Input.Autocomplete.OFF),
			ON(Input.Autocomplete.ON),
			EMAIL(Input.Autocomplete.EMAIL),
			TEL(Input.Autocomplete.TEL),
			TEL_NATIONAL(Input.Autocomplete.TEL_NATIONAL),
			TEL_LOCAL(Input.Autocomplete.TEL_LOCAL),
			IMPP(Input.Autocomplete.IMPP),
			URL(Input.Autocomplete.URL),
			PHOTO(Input.Autocomplete.PHOTO);

			private final Input.Autocomplete value;

			private Autocomplete(Input.Autocomplete value) {
				this.value = value;
			}

			@Override
			public java.lang.String toString() {
				return value.toString();
			}

			@Override
			public java.lang.String get(Serialization serialization, Doctype doctype) {
				return value.get(serialization, doctype);
			}

			public Input.Autocomplete getValue() {
				return value;
			}

			static {
				for(Autocomplete value : values()) {
					if(!value.name().equals(value.value.name())) throw new AssertionError("Enum name mismatch");
				}
			}
		}

		@Override
		protected void openWriteType() throws IOException {
			document.out.write(" type=\"url\"");
		}
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_input_type_week.asp">HTML input type="week"</a>.
	 *
	 * @param  <PC>  The parent content model this element is within
	 */
	public static class Week<PC extends Content> extends Input<Week<PC>, PC> implements
		Attributes.Enum.Autocomplete<Week<PC>, Week.Autocomplete>,
		Attributes.Text.List<Week<PC>>,
		Attributes.Boolean.Readonly<Week<PC>>, // Guessed
		Attributes.Text.Value<Week<PC>>,
		// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
		Attributes.Event.Form.Onchange<Week<PC>>,
		Attributes.Event.Form.Oninput<Week<PC>>
	{

		public Week(Document document) {
			super(document);
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
		 * @see Input.Autocomplete
		 */
		public enum Autocomplete implements Attributes.Enum.EnumSupplier {
			OFF(Input.Autocomplete.OFF),
			ON(Input.Autocomplete.ON),
			CC_EXP(Input.Autocomplete.CC_EXP),
			BDAY(Input.Autocomplete.BDAY);

			private final Input.Autocomplete value;

			private Autocomplete(Input.Autocomplete value) {
				this.value = value;
			}

			@Override
			public java.lang.String toString() {
				return value.toString();
			}

			@Override
			public java.lang.String get(Serialization serialization, Doctype doctype) {
				return value.get(serialization, doctype);
			}

			public Input.Autocomplete getValue() {
				return value;
			}

			static {
				for(Autocomplete value : values()) {
					if(!value.name().equals(value.value.name())) throw new AssertionError("Enum name mismatch");
				}
			}
		}

		@Override
		protected void openWriteType() throws IOException {
			document.out.write(" type=\"week\"");
		}
	}
}
