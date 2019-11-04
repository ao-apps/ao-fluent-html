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

import static com.aoindustries.html.ApplicationResources.accessor;
import com.aoindustries.lang.LocalizedIllegalArgumentException;
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
public class Input extends EmptyElement<Input> implements
	Attributes.Boolean.Checked<Input>,
	Attributes.Boolean.Disabled<Input>,
	Attributes.Integer.HeightHtml5Only<Input>,
	Attributes.Integer.Maxlength<Input>,
	Attributes.Text.Name<Input>,
	Attributes.Boolean.Readonly<Input>,
	Attributes.Integer.Size<Input>,
	Attributes.Url.Src<Script>,
	Attributes.Text.Value<Input>,
	Attributes.Integer.WidthHtml5Only<Input>,
	// Global Attributes: https://www.w3schools.com/tags/ref_standardattributes.asp
	Attributes.Integer.TabindexHtml4<Input>,
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	Attributes.Event.Mouse.Events<Input>
{

	/**
	 * See <a href="https://www.w3schools.com/tags/att_input_type.asp">HTML input type Attribute</a>.
	 */
	public enum Type {
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
				byLowerValue.put(type.value.toLowerCase(Locale.ROOT), type);
			}
		}
		public static Type valueOfWithLower(String name) {
			Type type = byLowerValue.get(name.toLowerCase(Locale.ROOT));
			if(type == null) {
				type = valueOf(name.toUpperCase(Locale.ROOT));
			}
			return type;
		}
	}

	private final Type type;

	public Input(Html html, Type type) {
		super(html);
		this.type = type;
	}

	public Input(Html html, String type) {
		this(html, (type == null) ? null : Type.valueOfWithLower(type));
	}

	public Input(Html html) {
		this(html, (Type)null);
	}

	@Override
	protected Input open() throws IOException {
		html.out.write("<input");
		Input i = type(type);
		assert i == this;
		return this;
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_input_type.asp">HTML input type Attribute</a>.
	 *
	 * @return  {@code this} when type unchanged, or an instance of {@link Input} for the given type.
	 */
	// TODO: Have enum-type in Attributes
	public Input type(Type type) throws IOException {
		if(type != null) {
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
			html.out.write(" type=\"");
			html.out.write(type.value);
			html.out.write('"');
		}
		return (type == this.type) ? this : html.getInput(type);
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_input_type.asp">HTML input type Attribute</a>.
	 *
	 * @return  {@code this} when type unchanged, or an instance of {@link Input} for the given type.
	 */
	// TODO: Invert: Have enum call String version, to allow type extension by String
	public Input type(String type) throws IOException {
		if(type != null) {
			type(Type.valueOfWithLower(type));
		}
		return this;
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_input_value.asp">HTML input value Attribute</a>.
	 */
	@Override
	public Input value(Object value) throws IOException {
		return Attributes.Text.attribute(
			this,
			"value",
			// Allow text markup from translations
			(type == null) ? null : type.getMarkupType(),
			value,
			false,
			true
		);
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_input_value.asp">HTML input value Attribute</a>.
	 */
	@Override
	public <Ex extends Throwable> Input valueE(SupplierE<?,Ex> value) throws IOException, Ex {
		return Attributes.Text.attributeE(
			this,
			"value",
			// Allow text markup from translations
			(type == null) ? null : type.getMarkupType(),
			value,
			false,
			true
		);
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_input_value.asp">HTML input value Attribute</a>.
	 */
	@Override
	public Input value(Supplier<?> value) throws IOException {
		return Attributes.Text.attribute(
			this,
			"value",
			// Allow text markup from translations
			(type == null) ? null : type.getMarkupType(),
			value,
			false,
			true
		);
	}
}
