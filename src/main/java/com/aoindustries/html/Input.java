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
 *
 * @author  AO Industries, Inc.
 */
// TODO: Should we create different subclasses of Input for each type, exposing
// TODO: the attributes that only apply to that specific type?
// TODO: Then a "custom/any" type that allows anything?
@SuppressWarnings("deprecation")
public class Input extends EmptyElement<Input> implements
	Attributes.Text.Accept<Input>, // TODO: Check type="file"?
	Attributes.Enum.Align<Input,Input.Align>, // TODO: Check type="image"?
	// TODO: alt
	// TODO: autocomplete
	// TODO: autofocus
	Attributes.Boolean.Checked<Input>,
	// TODO: dirname
	Attributes.Boolean.Disabled<Input>,
	// TODO: form
	// TODO: formaction
	// TODO: formenctype
	// TODO: formmethod
	// TODO: formnovalidate
	// TODO: formtarget
	Attributes.Integer.HeightHtml5Only<Input>, // TODO: Check type="image"?
	// TODO: list
	// TODO: max
	Attributes.Integer.Maxlength<Input>,
	// TODO: min
	// TODO: multiple
	Attributes.Text.Name<Input>,
	// TODO: pattern
	Attributes.Text.Placeholder<Input>, // TODO: Check type?
	Attributes.Boolean.Readonly<Input>,
	// TODO: required
	Attributes.Integer.Size<Input>,
	Attributes.Enum.Type<Input,Input.Type>,
	Attributes.Url.Src<Script>, // TODO: Check type="image"?
	// TODO: step
	Attributes.Text.Value<Input>,
	Attributes.Integer.WidthHtml5Only<Input>, // TODO: Check type="image"?
	// Global Attributes: https://www.w3schools.com/tags/ref_standardattributes.asp
	Attributes.Integer.TabindexHtml4<Input>,
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	Attributes.Event.AlmostGlobal<Input>,
	Attributes.Event.Window.Onerror<Input>, // TODO: Check type="image"?
	Attributes.Event.Window.Onload<Input>, // TODO: Check type="image"?
	Attributes.Event.Form.Onchange<Input>, // TODO: Check type?
	Attributes.Event.Form.Oninput<Input>, // TODO: Check type?
	Attributes.Event.Form.Oninvalid<Input>,
	Attributes.Event.Form.Onsearch<Input>, // TODO: Check type="search"?
	Attributes.Event.Form.Onselect<Input> // TODO: Check type?
{

	/**
	 * See <a href="https://www.w3schools.com/tags/att_input_type.asp">HTML input type Attribute</a>.
	 */
	public enum Type implements Attributes.Enum.EnumSupplier {
		BUTTON("button") {
			@Override
			public MarkupType getMarkupType() {
				return MarkupType.TEXT;
			}
		},
		CHECKBOX("checkbox"),
		COLOR("color"),
		DATE("date"),
		DATETIME_LOCAL("datetime-local"),
		EMAIL("email"),
		FILE("file"),
		HIDDEN("hidden"),
		IMAGE("image"),
		MONTH("month"),
		NUMBER("number"),
		PASSWORD("password"),
		RADIO("radio"),
		RANGE("range"),
		RESET("reset") {
			@Override
			public MarkupType getMarkupType() {
				return MarkupType.TEXT;
			}
		},
		SEARCH("search"),
		SUBMIT("submit") {
			@Override
			public MarkupType getMarkupType() {
				return MarkupType.TEXT;
			}
		},
		TEL("tel"),
		TEXT("text"),
		TIME("time"),
		URL("url"),
		WEEK("week");

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

	private String type;

	public Input(Html html) {
		super(html);
		this.type = null;
	}

	public Input(Html html, String type) {
		super(html);
		type = StringUtility.trimNullIfEmpty(type);
		this.type = (type == null) ? null : type.toLowerCase(Locale.ROOT);
	}

	public Input(Html html, Type type) {
		super(html);
		this.type = (type == null) ? null : type.getValue();
	}

	@Override
	protected Input open() throws IOException {
		html.out.write("<input");
		// Write the type now, if already set
		String t = this.type;
		if(t != null) {
			// Unset to avoid duplicate attribute
			this.type = null;
			Input i = type(t);
			assert i == this;
		}
		return this;
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

	/**
	 * See <a href="https://www.w3schools.com/tags/att_input_type.asp">HTML input type Attribute</a>.
	 */
	@Override
	public Input type(String type) throws IOException {
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
	public Input type(Type type) throws IOException {
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
		return this;
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_input_value.asp">HTML input value Attribute</a>.
	 */
	@Override
	public Input value(Object value) throws IOException {
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
