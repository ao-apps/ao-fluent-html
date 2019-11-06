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
	// TODO: autocomplete
	// TODO: autofocus
	Attributes.Boolean.Checked<E>,
	// TODO: dirname
	Attributes.Boolean.Disabled<E>,
	// TODO: form
	// TODO: formaction
	// TODO: formenctype
	// TODO: formmethod
	// TODO: formnovalidate
	// TODO: formtarget
	Attributes.Integer.HeightHtml5Only<E>, // TODO: Check type="image"?
	// TODO: list
	// TODO: max
	Attributes.Integer.Maxlength<E>,
	// TODO: min
	// TODO: multiple
	Attributes.Text.Name<E>,
	// TODO: pattern
	Attributes.Text.Placeholder<E>, // TODO: Check type?
	Attributes.Boolean.Readonly<E>,
	// TODO: required
	Attributes.Integer.Size<E>,
	Attributes.Url.Src<Script>, // TODO: Check type="image"?
	// TODO: step
	Attributes.Integer.WidthHtml5Only<E>, // TODO: Check type="image"?
	// Global Attributes: https://www.w3schools.com/tags/ref_standardattributes.asp
	Attributes.Integer.TabindexHtml4<E>,
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	Attributes.Event.AlmostGlobal<E>,
	Attributes.Event.Window.Onerror<E>, // TODO: Check type="image"?
	Attributes.Event.Window.Onload<E>, // TODO: Check type="image"?
	Attributes.Event.Form.Onchange<E>, // TODO: Check type?
	Attributes.Event.Form.Oninput<E>, // TODO: Check type?
	Attributes.Event.Form.Oninvalid<E>,
	Attributes.Event.Form.Onsearch<E>, // TODO: Check type="search"?
	Attributes.Event.Form.Onselect<E> // TODO: Check type?
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
		Attributes.Enum.Type<Dynamic,Dynamic.Type>,
		Attributes.Text.Value<Dynamic>
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
					// TODO: trimNullIfEmpty where appropriate
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
		 * TODO: Test javadocs
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
		Attributes.Text.Value<Checkbox>
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
		Attributes.Text.Value<Color>
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

		@Override
		protected void openWriteType() throws IOException {
			html.out.write(" type=\"color\"");
		}
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_input_type_date.asp">HTML input type="date"</a>.
	 */
	public static class Date extends Input<Date> implements
		Attributes.Text.Value<Date>
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

		@Override
		protected void openWriteType() throws IOException {
			html.out.write(" type=\"date\"");
		}
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_input_type_datetime-local.asp">HTML input type="datetime-local"</a>.
	 */
	public static class DatetimeLocal extends Input<DatetimeLocal> implements
		Attributes.Text.Value<DatetimeLocal>
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

		@Override
		protected void openWriteType() throws IOException {
			html.out.write(" type=\"datetime-local\"");
		}
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_input_type_email.asp">HTML input type="email"</a>.
	 */
	public static class Email extends Input<Email> implements
		Attributes.Text.Value<Email>
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

		@Override
		protected void openWriteType() throws IOException {
			html.out.write(" type=\"email\"");
		}
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_input_type_file.asp">HTML input type="file"</a>.
	 */
	public static class File extends Input<File> implements
		Attributes.Text.Accept<File>
		// Does not support value: Attributes.Text.Value<File>
	{

		public File(Html html) {
			super(html);
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
		Attributes.Text.Value<Image>
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
		Attributes.Text.Value<Month>
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

		@Override
		protected void openWriteType() throws IOException {
			html.out.write(" type=\"month\"");
		}
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_input_type_number.asp">HTML input type="number"</a>.
	 */
	public static class Number extends Input<Number> implements
		Attributes.Text.Value<Number> // TODO: Review types (this and others), perhaps Attributes.Number or similar?
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

		@Override
		protected void openWriteType() throws IOException {
			html.out.write(" type=\"number\"");
		}
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_input_type_password.asp">HTML input type="password"</a>.
	 */
	public static class Password extends Input<Password> implements
		Attributes.Text.Value<Password>
	{

		public Password(Html html) {
			super(html);
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
		Attributes.Text.Value<Radio>
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
		Attributes.Text.Value<Range>
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
		 * TODO: Test javadocs
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
		Attributes.Text.Value<Search>
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
		 * TODO: Test javadocs
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
		Attributes.Text.Value<Tel>
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

		@Override
		protected void openWriteType() throws IOException {
			html.out.write(" type=\"tel\"");
		}
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_input_type_text.asp">HTML input type="text"</a>.
	 */
	public static class Text extends Input<Text> implements
		Attributes.Text.Value<Text>
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
		Attributes.Text.Value<Time>
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

		@Override
		protected void openWriteType() throws IOException {
			html.out.write(" type=\"time\"");
		}
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_input_type_url.asp">HTML input type="url"</a>.
	 */
	public static class Url extends Input<Url> implements
		Attributes.Text.Value<Url>
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

		@Override
		protected void openWriteType() throws IOException {
			html.out.write(" type=\"url\"");
		}
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_input_type_week.asp">HTML input type="week"</a>.
	 */
	public static class Week extends Input<Week> implements
		Attributes.Text.Value<Week>
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

		@Override
		protected void openWriteType() throws IOException {
			html.out.write(" type=\"week\"");
		}
	}
}
