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

import static com.aoindustries.encoding.TextInXhtmlAttributeEncoder.encodeTextInXhtmlAttribute;
import static com.aoindustries.encoding.TextInXhtmlAttributeEncoder.textInXhtmlAttributeEncoder;
import static com.aoindustries.html.ApplicationResources.accessor;
import com.aoindustries.lang.LocalizedIllegalArgumentException;
import com.aoindustries.lang.LocalizedIllegalStateException;
import com.aoindustries.util.StringUtility;
import com.aoindustries.util.i18n.MarkupType;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * See <a href="https://www.w3schools.com/tags/tag_input.asp">HTML input tag</a>.
 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input">&lt;input&gt;: The Input (Form Input) element</a>.
 * <p>
 * This has the set of attributes common to all input types.  There are also
 * type-specific subclasses that add type-specific attributes.  Furthermore,
 * there is a {@link Input.Dynamic} implementation that has all the input attributes,
 * supporting unexpected or more dynamic configurations.
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
	// TODO: list
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
	 * See <a href="https://www.w3schools.com/tags/tag_input.asp">HTML input tag</a>.
	 * <p>
	 * This implementation that has all the input attributes,
	 * supporting unexpected or more dynamic configurations.
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
		Attributes.Enum.Autocomplete<Dynamic,Attributes.Enum.Autocomplete.Value>,
		Attributes.Enum.Capture<Dynamic,File.Capture>,
		Attributes.Boolean.Checked<Dynamic>,
		Attributes.Integer.HeightHtml5Only<Dynamic>,
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
			type = StringUtility.trimNullIfEmpty(type);
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
			private static final Map<String,Type> byLowerValue = new HashMap<>(values.length*4/3+1);
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
			type = StringUtility.trimNullIfEmpty(type);
			if(type != null) {
				type = type.toLowerCase(Locale.ROOT);
				// Perform doctype checks and optimizations for recognized types
				Type typeEnum = Type.byLowerValue.get(type);
				if(typeEnum != null) {
					return type(typeEnum);
				}
				if(this.type != null) {
					throw new LocalizedIllegalStateException(
						accessor,
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
						accessor,
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
						accessor,
						"Input.typeRequiresDoctype",
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
					accessor,
					"Input.typeOnlySupportedInHtml5",
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
		 * @see Attributes.Enum.Autocomplete.Value
		 */
		public enum Autocomplete implements Attributes.Enum.EnumSupplier {
			OFF(Value.OFF),
			ON(Value.ON);

			private final Value value;

			private Autocomplete(Value value) {
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

			public Attributes.Enum.Autocomplete.Value getValue() {
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
					accessor,
					"Input.typeOnlySupportedInHtml5",
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
		 * @see Attributes.Enum.Autocomplete.Value
		 */
		public enum Autocomplete implements Attributes.Enum.EnumSupplier {
			OFF(Value.OFF),
			ON(Value.ON),
			CC_EXP(Value.CC_EXP),
			BDAY(Value.BDAY);

			private final Value value;

			private Autocomplete(Value value) {
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

			public Attributes.Enum.Autocomplete.Value getValue() {
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
					accessor,
					"Input.typeOnlySupportedInHtml5",
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
		 * @see Attributes.Enum.Autocomplete.Value
		 */
		public enum Autocomplete implements Attributes.Enum.EnumSupplier {
			OFF(Value.OFF),
			ON(Value.ON),
			BDAY(Value.BDAY);

			private final Value value;

			private Autocomplete(Value value) {
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

			public Attributes.Enum.Autocomplete.Value getValue() {
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
					accessor,
					"Input.typeOnlySupportedInHtml5",
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
		 * @see Attributes.Enum.Autocomplete.Value
		 */
		public enum Autocomplete implements Attributes.Enum.EnumSupplier {
			OFF(Value.OFF),
			ON(Value.ON),
			EMAIL(Value.EMAIL),
			IMPP(Value.IMPP),
			URL(Value.URL);

			private final Value value;

			private Autocomplete(Value value) {
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

			public Attributes.Enum.Autocomplete.Value getValue() {
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
		// Does not support value: Attributes.Text.Value<File>
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
		Attributes.Enum.Autocomplete<Hidden,Attributes.Enum.Autocomplete.Value>,
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
					accessor,
					"Input.typeOnlySupportedInHtml5",
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
		 * @see Attributes.Enum.Autocomplete.Value
		 */
		public enum Autocomplete implements Attributes.Enum.EnumSupplier {
			OFF(Value.OFF),
			ON(Value.ON),
			CC_EXP(Value.CC_EXP),
			CC_EXP_MONTH(Value.CC_EXP_MONTH),
			BDAY_MONTH(Value.BDAY_MONTH);

			private final Value value;

			private Autocomplete(Value value) {
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

			public Attributes.Enum.Autocomplete.Value getValue() {
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
					accessor,
					"Input.typeOnlySupportedInHtml5",
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
		 * @see Attributes.Enum.Autocomplete.Value
		 */
		public enum Autocomplete implements Attributes.Enum.EnumSupplier {
			OFF(Value.OFF),
			ON(Value.ON),
			ONE_TIME_CODE(Value.ONE_TIME_CODE),
			ADDRESS_LEVEL4(Value.ADDRESS_LEVEL4),
			ADDRESS_LEVEL3(Value.ADDRESS_LEVEL3),
			ADDRESS_LEVEL2(Value.ADDRESS_LEVEL2),
			ADDRESS_LEVEL1(Value.ADDRESS_LEVEL1),
			POSTAL_CODE(Value.POSTAL_CODE),
			CC_NUMBER(Value.CC_NUMBER),
			CC_EXP_MONTH(Value.CC_EXP_MONTH),
			CC_EXP_YEAR(Value.CC_EXP_YEAR),
			CC_CSC(Value.CC_CSC),
			TRANSACTION_AMOUNT(Value.TRANSACTION_AMOUNT),
			BDAY_DAY(Value.BDAY_DAY),
			BDAY_MONTH(Value.BDAY_MONTH),
			BDAY_YEAR(Value.BDAY_YEAR),
			TEL_COUNTRY_CODE(Value.TEL_COUNTRY_CODE),
			TEL_AREA_CODE(Value.TEL_AREA_CODE),
			TEL_LOCAL_PREFIX(Value.TEL_LOCAL_PREFIX),
			TEL_LOCAL_SUFFIX(Value.TEL_LOCAL_SUFFIX),
			TEL_EXTENSION(Value.TEL_EXTENSION);

			private final Value value;

			private Autocomplete(Value value) {
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

			public Attributes.Enum.Autocomplete.Value getValue() {
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
		 * @see Attributes.Enum.Autocomplete.Value
		 */
		public enum Autocomplete implements Attributes.Enum.EnumSupplier {
			OFF(Value.OFF),
			ON(Value.ON),
			NEW_PASSWORD(Value.NEW_PASSWORD),
			CURRENT_PASSWORD(Value.CURRENT_PASSWORD),
			ONE_TIME_CODE(Value.ONE_TIME_CODE),
			CC_NUMBER(Value.CC_NUMBER),
			CC_EXP(Value.CC_EXP),
			CC_EXP_MONTH(Value.CC_EXP_MONTH),
			CC_EXP_YEAR(Value.CC_EXP_YEAR),
			CC_CSC(Value.CC_CSC);

			private final Value value;

			private Autocomplete(Value value) {
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

			public Attributes.Enum.Autocomplete.Value getValue() {
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
		Attributes.Text.Value<Range>,
		// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
		Attributes.Event.Form.Onchange<Range>,
		Attributes.Event.Form.Oninput<Range>
	{

		public Range(Html html) {
			super(html);
			if(html.doctype != Doctype.HTML5) {
				throw new LocalizedIllegalArgumentException(
					accessor,
					"Input.typeOnlySupportedInHtml5",
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
		 * @see Attributes.Enum.Autocomplete.Value
		 */
		public enum Autocomplete implements Attributes.Enum.EnumSupplier {
			OFF(Value.OFF),
			ON(Value.ON);

			private final Value value;

			private Autocomplete(Value value) {
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

			public Attributes.Enum.Autocomplete.Value getValue() {
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
					accessor,
					"Input.typeOnlySupportedInHtml5",
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
		 * @see Attributes.Enum.Autocomplete.Value
		 */
		public enum Autocomplete implements Attributes.Enum.EnumSupplier {
			OFF(Value.OFF),
			ON(Value.ON),
			NAME(Value.NAME),
			HONORIFIC_PREFIX(Value.HONORIFIC_PREFIX),
			GIVEN_NAME(Value.GIVEN_NAME),
			ADDITIONAL_NAME(Value.ADDITIONAL_NAME),
			FAMILY_NAME(Value.FAMILY_NAME),
			HONORIFIC_SUFFIX(Value.HONORIFIC_SUFFIX),
			NICKNAME(Value.NICKNAME),
			EMAIL(Value.EMAIL),
			USERNAME(Value.USERNAME),
			ORGANIZATION_TITLE(Value.ORGANIZATION_TITLE),
			ORGANIZATION(Value.ORGANIZATION),
			STREET_ADDRESS(Value.STREET_ADDRESS),
			ADDRESS_LINE1(Value.ADDRESS_LINE1),
			ADDRESS_LINE2(Value.ADDRESS_LINE2),
			ADDRESS_LINE3(Value.ADDRESS_LINE3),
			ADDRESS_LEVEL4(Value.ADDRESS_LEVEL4),
			ADDRESS_LEVEL3(Value.ADDRESS_LEVEL3),
			ADDRESS_LEVEL2(Value.ADDRESS_LEVEL2),
			ADDRESS_LEVEL1(Value.ADDRESS_LEVEL1),
			COUNTRY(Value.COUNTRY),
			COUNTRY_NAME(Value.COUNTRY_NAME),
			POSTAL_CODE(Value.POSTAL_CODE),
			CC_NAME(Value.CC_NAME),
			CC_GIVEN_NAME(Value.CC_GIVEN_NAME),
			CC_ADDITIONAL_NAME(Value.CC_ADDITIONAL_NAME),
			CC_FAMILY_NAME(Value.CC_FAMILY_NAME),
			CC_TYPE(Value.CC_TYPE),
			TRANSACTION_CURRENCY(Value.TRANSACTION_CURRENCY),
			TRANSACTION_AMOUNT(Value.TRANSACTION_AMOUNT),
			LANGUAGE(Value.LANGUAGE),
			BDAY(Value.BDAY),
			BDAY_DAY(Value.BDAY_DAY),
			BDAY_MONTH(Value.BDAY_MONTH),
			BDAY_YEAR(Value.BDAY_YEAR),
			SEX(Value.SEX),
			TEL(Value.TEL),
			TEL_COUNTRY_CODE(Value.TEL_COUNTRY_CODE),
			TEL_NATIONAL(Value.TEL_NATIONAL),
			TEL_AREA_CODE(Value.TEL_AREA_CODE),
			TEL_LOCAL(Value.TEL_LOCAL),
			TEL_LOCAL_PREFIX(Value.TEL_LOCAL_PREFIX),
			TEL_LOCAL_SUFFIX(Value.TEL_LOCAL_SUFFIX),
			TEL_EXTENSION(Value.TEL_EXTENSION),
			IMPP(Value.IMPP),
			URL(Value.URL),
			PHOTO(Value.PHOTO);

			private final Value value;

			private Autocomplete(Value value) {
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

			public Attributes.Enum.Autocomplete.Value getValue() {
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
					accessor,
					"Input.typeOnlySupportedInHtml5",
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
		 * @see Attributes.Enum.Autocomplete.Value
		 */
		public enum Autocomplete implements Attributes.Enum.EnumSupplier {
			OFF(Value.OFF),
			ON(Value.ON),
			TEL(Value.TEL),
			TEL_COUNTRY_CODE(Value.TEL_COUNTRY_CODE),
			TEL_NATIONAL(Value.TEL_NATIONAL),
			TEL_AREA_CODE(Value.TEL_AREA_CODE),
			TEL_LOCAL(Value.TEL_LOCAL),
			TEL_LOCAL_PREFIX(Value.TEL_LOCAL_PREFIX),
			TEL_LOCAL_SUFFIX(Value.TEL_LOCAL_SUFFIX),
			TEL_EXTENSION(Value.TEL_EXTENSION);

			private final Value value;

			private Autocomplete(Value value) {
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

			public Attributes.Enum.Autocomplete.Value getValue() {
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
		Attributes.Enum.Autocomplete<Text,Attributes.Enum.Autocomplete.Value>,
		Attributes.Integer.Maxlength<Text>,
		Attributes.Integer.Minlength<Text>,
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
					accessor,
					"Input.typeOnlySupportedInHtml5",
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
		 * @see Attributes.Enum.Autocomplete.Value
		 */
		public enum Autocomplete implements Attributes.Enum.EnumSupplier {
			OFF(Value.OFF),
			ON(Value.ON);

			private final Value value;

			private Autocomplete(Value value) {
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

			public Attributes.Enum.Autocomplete.Value getValue() {
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
					accessor,
					"Input.typeOnlySupportedInHtml5",
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
		 * @see Attributes.Enum.Autocomplete.Value
		 */
		public enum Autocomplete implements Attributes.Enum.EnumSupplier {
			OFF(Value.OFF),
			ON(Value.ON),
			EMAIL(Value.EMAIL),
			TEL(Value.TEL),
			TEL_NATIONAL(Value.TEL_NATIONAL),
			TEL_LOCAL(Value.TEL_LOCAL),
			IMPP(Value.IMPP),
			URL(Value.URL),
			PHOTO(Value.PHOTO);

			private final Value value;

			private Autocomplete(Value value) {
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

			public Attributes.Enum.Autocomplete.Value getValue() {
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
					accessor,
					"Input.typeOnlySupportedInHtml5",
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
		 * @see Attributes.Enum.Autocomplete.Value
		 */
		public enum Autocomplete implements Attributes.Enum.EnumSupplier {
			OFF(Value.OFF),
			ON(Value.ON),
			CC_EXP(Value.CC_EXP),
			BDAY(Value.BDAY);

			private final Value value;

			private Autocomplete(Value value) {
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

			public Attributes.Enum.Autocomplete.Value getValue() {
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
