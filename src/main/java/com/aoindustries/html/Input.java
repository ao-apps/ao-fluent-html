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
 * See <a href="https://www.w3schools.com/tags/tag_input.asp">HTML input tag</a>.
 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input">&lt;input&gt;: The Input (Form Input) element</a>.
 * <p>
 * This has the set of attributes common to all input types.  There are also
 * type-specific subclasses that add type-specific attributes.  Furthermore,
 * there is a {@link Input.Dynamic} implementation that has all the input attributes,
 * supporting unanticipated or more dynamic configurations.
 * </p>
 *
 * @author  AO Industries, Inc.
 */
public abstract class Input<E extends Input<E>> extends EmptyElement<E> implements
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

	public Input(Html html) {
		super(html);
	}

	@Override
	protected E open() throws IOException {
		@SuppressWarnings("unchecked") E element = (E)this;
		html.out.write("<input");
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
	 */
	@SuppressWarnings("deprecation")
	public static class Dynamic extends Input<Dynamic> implements
		Attributes.Text.Accept<Dynamic>,
		Attributes.Enum.Align<Dynamic,Image.Align>,
		Attributes.Text.Alt<Dynamic>,
		Attributes.Enum.Autocomplete<Dynamic,Input.Autocomplete>,
		Attributes.Enum.Capture<Dynamic,File.Capture>,
		Attributes.Boolean.Checked<Dynamic>,
		Attributes.Integer.HeightHtml5Only<Dynamic>,
		Attributes.Text.List<Dynamic>,
		Attributes.Integer.Maxlength<Dynamic>,
		Attributes.Integer.Minlength<Dynamic>,
		Attributes.Boolean.Multiple<Dynamic>,
		Attributes.Text.Placeholder<Dynamic>,
		Attributes.Boolean.Readonly<Dynamic>,
		Attributes.Integer.Size<Dynamic>,
		Attributes.Url.Src<Dynamic>,
		Attributes.Enum.Type<Dynamic,Dynamic.Type>,
		Attributes.Text.Value<Dynamic>,
		Attributes.Integer.WidthHtml5Only<Dynamic>,
		// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
		Attributes.Event.Media.Onabort<Dynamic>,
		Attributes.Event.Window.Onerror<Dynamic>,
		Attributes.Event.Window.Onload<Dynamic>,
		Attributes.Event.Form.Onchange<Dynamic>,
		Attributes.Event.Form.Oninput<Dynamic>,
		Attributes.Event.Form.Onsearch<Dynamic>,
		Attributes.Event.Form.Onselect<Dynamic>
	{

		private String type;
		public Dynamic(Html html) {
			super(html);
			this.type = null;
		}

		public Dynamic(Html html, String type) {
			super(html);
			type = Strings.trimNullIfEmpty(type);
			this.type = (type == null) ? null : type.toLowerCase(Locale.ROOT);
		}

		public Dynamic(Html html, Type type) {
			super(html);
			this.type = (type == null) ? null : type.getValue();
		}

		@Override
		protected void openWriteType() throws IOException {
			// Write the type now, if already set
			String t = this.type;
			if(t != null) {
				// Unset to avoid duplicate attribute
				this.type = null;
				Dynamic i = type(t);
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
			private static final Map<String,Type> byLowerValue = AoCollections.newHashMap(values.length);
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
		public Dynamic type(String type) throws IOException {
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
						"Html.duplicateAttribute",
						"input",
						"type",
						this.type,
						type
					);
				}
				this.type = type;
				html.out.write(" type=\"");
				encodeTextInXhtmlAttribute(type, html.out);
				html.out.write('"');
			}
			return this;
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_input_type.asp">HTML input type Attribute</a>.
		 */
		@Override
		public Dynamic type(Type type) throws IOException {
			if(type != null) {
				if(this.type != null) {
					throw new LocalizedIllegalStateException(
						Resources.PACKAGE_RESOURCES,
						"Html.duplicateAttribute",
						"input",
						"type",
						this.type,
						type
					);
				}
				// Perform doctype checks for recognized types
				Doctype requiredDoctype = type.getRequiredDoctype();
				if(requiredDoctype != null && html.doctype != requiredDoctype) {
					throw new LocalizedIllegalArgumentException(
						RESOURCES,
						"typeRequiresDoctype",
						type.value,
						requiredDoctype,
						html.doctype
					);
				}
				this.type = type.value;
				html.out.write(" type=\"");
				html.out.write(type.value); // No encoding, is a known safe value.  TODO: Assert this above in static initializer?
				html.out.write('"');
			}
			return this;
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_input_value.asp">HTML input value Attribute</a>.
		 */
		@Override
		public Dynamic value(Object value) throws IOException {
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
	 */
	public static class Button extends Input<Button> implements
		Attributes.Text.Value<Button>
	{

		public Button(Html html) {
			super(html);
		}

		@Override
		protected void openWriteType() throws IOException {
			html.out.write(" type=\"button\"");
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_input_value.asp">HTML input value Attribute</a>.
		 *
		 * @see Dynamic.Type#BUTTON
		 */
		@Override
		public Button value(Object value) throws IOException {
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
	 */
	public static class Checkbox extends Input<Checkbox> implements
		Attributes.Boolean.Checked<Checkbox>,
		Attributes.Text.Value<Checkbox>,
		// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
		Attributes.Event.Form.Onchange<Checkbox>
	{

		public Checkbox(Html html) {
			super(html);
		}

		@Override
		protected void openWriteType() throws IOException {
			html.out.write(" type=\"checkbox\"");
		}
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_input_type_color.asp">HTML input type="color"</a>.
	 */
	public static class Color extends Input<Color> implements
		Attributes.Enum.Autocomplete<Color,Color.Autocomplete>,
		Attributes.Text.List<Color>,
		Attributes.Boolean.Readonly<Color>, // Guessed
		Attributes.Text.Value<Color>,
		// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
		Attributes.Event.Form.Onchange<Color>,
		Attributes.Event.Form.Oninput<Color>
	{

		public Color(Html html) {
			super(html);
			if(html.doctype != Doctype.HTML5) {
				throw new LocalizedIllegalArgumentException(
					RESOURCES,
					"typeOnlySupportedInHtml5",
					html.doctype,
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
			html.out.write(" type=\"color\"");
		}
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_input_type_date.asp">HTML input type="date"</a>.
	 */
	public static class Date extends Input<Date> implements
		Attributes.Enum.Autocomplete<Date,Date.Autocomplete>,
		Attributes.Text.List<Date>,
		Attributes.Boolean.Readonly<Date>, // Guessed
		Attributes.Text.Value<Date>,
		// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
		Attributes.Event.Form.Onchange<Date>,
		Attributes.Event.Form.Oninput<Date>
	{

		public Date(Html html) {
			super(html);
			if(html.doctype != Doctype.HTML5) {
				throw new LocalizedIllegalArgumentException(
					RESOURCES,
					"typeOnlySupportedInHtml5",
					html.doctype,
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
			html.out.write(" type=\"date\"");
		}
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_input_type_datetime-local.asp">HTML input type="datetime-local"</a>.
	 */
	public static class DatetimeLocal extends Input<DatetimeLocal> implements
		Attributes.Enum.Autocomplete<DatetimeLocal,DatetimeLocal.Autocomplete>,
		Attributes.Text.List<DatetimeLocal>,
		Attributes.Boolean.Readonly<DatetimeLocal>, // Guessed
		Attributes.Text.Value<DatetimeLocal>,
		// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
		Attributes.Event.Form.Onchange<DatetimeLocal>,
		Attributes.Event.Form.Oninput<DatetimeLocal>
	{

		public DatetimeLocal(Html html) {
			super(html);
			if(html.doctype != Doctype.HTML5) {
				throw new LocalizedIllegalArgumentException(
					RESOURCES,
					"typeOnlySupportedInHtml5",
					html.doctype,
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
			html.out.write(" type=\"datetime-local\"");
		}
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_input_type_email.asp">HTML input type="email"</a>.
	 */
	public static class Email extends Input<Email> implements
		Attributes.Enum.Autocomplete<Email,Email.Autocomplete>,
		Attributes.Text.List<Email>,
		Attributes.Integer.Maxlength<Email>,
		Attributes.Integer.Minlength<Email>,
		Attributes.Boolean.Multiple<Email>,
		Attributes.Text.Placeholder<Email>,
		Attributes.Boolean.Readonly<Email>,
		Attributes.Integer.Size<Email>,
		Attributes.Text.Value<Email>,
		// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
		Attributes.Event.Form.Onchange<Email>,
		Attributes.Event.Form.Oninput<Email>,
		Attributes.Event.Form.Onselect<Email> // Guessed (to match Placeholder)
	{

		public Email(Html html) {
			super(html);
			if(html.doctype != Doctype.HTML5) {
				throw new LocalizedIllegalArgumentException(
					RESOURCES,
					"typeOnlySupportedInHtml5",
					html.doctype,
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
			html.out.write(" type=\"email\"");
		}
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_input_type_file.asp">HTML input type="file"</a>.
	 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input/file">&lt;input type="file"&gt;</a>.
	 */
	public static class File extends Input<File> implements
		Attributes.Text.Accept<File>,
		Attributes.Enum.Capture<File,File.Capture>,
		Attributes.Boolean.Multiple<File>,
		// Does not support value per https://www.w3schools.com/tags/att_input_value.asp: Attributes.Text.Value<File>
		// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
		Attributes.Event.Form.Onchange<File>,
		Attributes.Event.Form.Onselect<File>
	{

		public File(Html html) {
			super(html);
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
			html.out.write(" type=\"file\"");
		}
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_input_type_hidden.asp">HTML input type="hidden"</a>.
	 */
	public static class Hidden extends Input<Hidden> implements
		Attributes.Enum.Autocomplete<Hidden,Input.Autocomplete>,
		Attributes.Text.Value<Hidden>
	{

		public Hidden(Html html) {
			super(html);
		}

		@Override
		protected void openWriteType() throws IOException {
			html.out.write(" type=\"hidden\"");
		}
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_input_type_image.asp">HTML input type="image"</a>.
	 */
	@SuppressWarnings("deprecation")
	public static class Image extends Input<Image> implements
		Attributes.Enum.Align<Image,Image.Align>,
		Attributes.Text.Alt<Image>,
		Attributes.Integer.HeightHtml5Only<Image>,
		Attributes.Url.Src<Image>,
		Attributes.Text.Value<Image>,
		Attributes.Integer.WidthHtml5Only<Image>,
		// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
		Attributes.Event.Media.Onabort<Image>,
		Attributes.Event.Window.Onerror<Image>,
		Attributes.Event.Window.Onload<Image>
	{

		public Image(Html html) {
			super(html);
		}

		@Override
		protected void openWriteType() throws IOException {
			html.out.write(" type=\"image\"");
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
	 */
	public static class Month extends Input<Month> implements
		Attributes.Enum.Autocomplete<Month,Month.Autocomplete>,
		Attributes.Text.List<Month>,
		Attributes.Boolean.Readonly<Month>, // Guessed
		Attributes.Text.Value<Month>,
		// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
		Attributes.Event.Form.Onchange<Month>,
		Attributes.Event.Form.Oninput<Month>
	{

		public Month(Html html) {
			super(html);
			if(html.doctype != Doctype.HTML5) {
				throw new LocalizedIllegalArgumentException(
					RESOURCES,
					"typeOnlySupportedInHtml5",
					html.doctype,
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
			html.out.write(" type=\"month\"");
		}
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_input_type_number.asp">HTML input type="number"</a>.
	 */
	public static class Number extends Input<Number> implements
		Attributes.Enum.Autocomplete<Number,Number.Autocomplete>,
		Attributes.Text.List<Number>,
		Attributes.Boolean.Readonly<Number>,
		Attributes.Text.Value<Number>, // TODO: Review types (this and others), perhaps Attributes.Number or similar?
		// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
		Attributes.Event.Form.Onchange<Number>,
		Attributes.Event.Form.Oninput<Number>
	{

		public Number(Html html) {
			super(html);
			if(html.doctype != Doctype.HTML5) {
				throw new LocalizedIllegalArgumentException(
					RESOURCES,
					"typeOnlySupportedInHtml5",
					html.doctype,
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
			html.out.write(" type=\"number\"");
		}
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_input_type_password.asp">HTML input type="password"</a>.
	 */
	public static class Password extends Input<Password> implements
		Attributes.Enum.Autocomplete<Password,Password.Autocomplete>,
		Attributes.Integer.Maxlength<Password>,
		Attributes.Integer.Minlength<Password>,
		Attributes.Text.Placeholder<Password>,
		Attributes.Boolean.Readonly<Password>,
		Attributes.Integer.Size<Password>,
		Attributes.Text.Value<Password>,
		// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
		Attributes.Event.Form.Onchange<Password>,
		Attributes.Event.Form.Oninput<Password>,
		Attributes.Event.Form.Onselect<Password>
	{

		public Password(Html html) {
			super(html);
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
			html.out.write(" type=\"password\"");
		}
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_input_type_radio.asp">HTML input type="radio"</a>.
	 */
	public static class Radio extends Input<Radio> implements
		Attributes.Boolean.Checked<Radio>,
		Attributes.Text.Value<Radio>,
		// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
		Attributes.Event.Form.Onchange<Radio>
	{

		public Radio(Html html) {
			super(html);
		}

		@Override
		protected void openWriteType() throws IOException {
			html.out.write(" type=\"radio\"");
		}
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_input_type_range.asp">HTML input type="range"</a>.
	 */
	public static class Range extends Input<Range> implements
		Attributes.Enum.Autocomplete<Range,Range.Autocomplete>,
		Attributes.Text.List<Range>,
		Attributes.Text.Value<Range>,
		// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
		Attributes.Event.Form.Onchange<Range>,
		Attributes.Event.Form.Oninput<Range>
	{

		public Range(Html html) {
			super(html);
			if(html.doctype != Doctype.HTML5) {
				throw new LocalizedIllegalArgumentException(
					RESOURCES,
					"typeOnlySupportedInHtml5",
					html.doctype,
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
			html.out.write(" type=\"range\"");
		}
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_input_type_reset.asp">HTML input type="reset"</a>.
	 */
	public static class Reset extends Input<Reset> implements
		Attributes.Text.Value<Reset>
	{

		public Reset(Html html) {
			super(html);
		}

		@Override
		protected void openWriteType() throws IOException {
			html.out.write(" type=\"reset\"");
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_input_value.asp">HTML input value Attribute</a>.
		 *
		 * @see Dynamic.Type#RESET
		 */
		@Override
		public Reset value(Object value) throws IOException {
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
	 */
	public static class Search extends Input<Search> implements
		Attributes.Enum.Autocomplete<Search,Search.Autocomplete>,
		Attributes.Text.List<Search>,
		Attributes.Integer.Maxlength<Search>,
		Attributes.Integer.Minlength<Search>,
		Attributes.Text.Placeholder<Search>,
		Attributes.Boolean.Readonly<Search>,
		Attributes.Integer.Size<Search>,
		Attributes.Text.Value<Search>,
		// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
		Attributes.Event.Form.Onchange<Search>,
		Attributes.Event.Form.Oninput<Search>,
		Attributes.Event.Form.Onsearch<Search>,
		Attributes.Event.Form.Onselect<Search> // Guessed (to match Placeholder)
	{

		public Search(Html html) {
			super(html);
			if(html.doctype != Doctype.HTML5) {
				throw new LocalizedIllegalArgumentException(
					RESOURCES,
					"typeOnlySupportedInHtml5",
					html.doctype,
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
			html.out.write(" type=\"search\"");
		}
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_input_type_submit.asp">HTML input type="submit"</a>.
	 */
	public static class Submit extends Input<Submit> implements
		Attributes.Text.Value<Submit>
	{

		public Submit(Html html) {
			super(html);
		}

		@Override
		protected void openWriteType() throws IOException {
			html.out.write(" type=\"submit\"");
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_input_value.asp">HTML input value Attribute</a>.
		 *
		 * @see Dynamic.Type#SUBMIT
		 */
		@Override
		public Submit value(Object value) throws IOException {
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
	 */
	public static class Tel extends Input<Tel> implements
		Attributes.Enum.Autocomplete<Tel,Tel.Autocomplete>,
		Attributes.Text.List<Tel>,
		Attributes.Integer.Maxlength<Tel>,
		Attributes.Integer.Minlength<Tel>,
		Attributes.Text.Placeholder<Tel>,
		Attributes.Boolean.Readonly<Tel>, // Guessed
		Attributes.Integer.Size<Tel>,
		Attributes.Text.Value<Tel>,
		// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
		Attributes.Event.Form.Onchange<Tel>,
		Attributes.Event.Form.Oninput<Tel>,
		Attributes.Event.Form.Onselect<Tel> // Guessed (to match Placeholder)
	{

		public Tel(Html html) {
			super(html);
			if(html.doctype != Doctype.HTML5) {
				throw new LocalizedIllegalArgumentException(
					RESOURCES,
					"typeOnlySupportedInHtml5",
					html.doctype,
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
			html.out.write(" type=\"tel\"");
		}
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_input_type_text.asp">HTML input type="text"</a>.
	 */
	public static class Text extends Input<Text> implements
		Attributes.Enum.Autocomplete<Text,Input.Autocomplete>,
		Attributes.Integer.Maxlength<Text>,
		Attributes.Integer.Minlength<Text>,
		Attributes.Text.List<Text>,
		Attributes.Text.Placeholder<Text>,
		Attributes.Boolean.Readonly<Text>,
		Attributes.Integer.Size<Text>,
		Attributes.Text.Value<Text>,
		// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
		Attributes.Event.Form.Onchange<Text>,
		Attributes.Event.Form.Oninput<Text>,
		Attributes.Event.Form.Onselect<Text>
	{

		public Text(Html html) {
			super(html);
		}

		@Override
		protected void openWriteType() throws IOException {
			html.out.write(" type=\"text\"");
		}
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_input_type_time.asp">HTML input type="time"</a>.
	 */
	public static class Time extends Input<Time> implements
		Attributes.Enum.Autocomplete<Time,Time.Autocomplete>,
		Attributes.Text.List<Time>,
		Attributes.Boolean.Readonly<Time>, // Guessed
		Attributes.Text.Value<Time>,
		// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
		Attributes.Event.Form.Onchange<Time>,
		Attributes.Event.Form.Oninput<Time>
	{

		public Time(Html html) {
			super(html);
			if(html.doctype != Doctype.HTML5) {
				throw new LocalizedIllegalArgumentException(
					RESOURCES,
					"typeOnlySupportedInHtml5",
					html.doctype,
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
			html.out.write(" type=\"time\"");
		}
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_input_type_url.asp">HTML input type="url"</a>.
	 */
	public static class Url extends Input<Url> implements
		Attributes.Enum.Autocomplete<Url,Url.Autocomplete>,
		Attributes.Text.List<Url>,
		Attributes.Integer.Maxlength<Url>,
		Attributes.Integer.Minlength<Url>,
		Attributes.Text.Placeholder<Url>,
		Attributes.Boolean.Readonly<Url>,
		Attributes.Integer.Size<Url>,
		Attributes.Text.Value<Url>,
		// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
		Attributes.Event.Form.Onchange<Url>,
		Attributes.Event.Form.Oninput<Url>,
		Attributes.Event.Form.Onselect<Url> // Guessed (to match Placeholder)
	{

		public Url(Html html) {
			super(html);
			if(html.doctype != Doctype.HTML5) {
				throw new LocalizedIllegalArgumentException(
					RESOURCES,
					"typeOnlySupportedInHtml5",
					html.doctype,
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
			html.out.write(" type=\"url\"");
		}
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_input_type_week.asp">HTML input type="week"</a>.
	 */
	public static class Week extends Input<Week> implements
		Attributes.Enum.Autocomplete<Week,Week.Autocomplete>,
		Attributes.Text.List<Week>,
		Attributes.Boolean.Readonly<Week>, // Guessed
		Attributes.Text.Value<Week>,
		// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
		Attributes.Event.Form.Onchange<Week>,
		Attributes.Event.Form.Oninput<Week>
	{

		public Week(Html html) {
			super(html);
			if(html.doctype != Doctype.HTML5) {
				throw new LocalizedIllegalArgumentException(
					RESOURCES,
					"typeOnlySupportedInHtml5",
					html.doctype,
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
			html.out.write(" type=\"week\"");
		}
	}
}
