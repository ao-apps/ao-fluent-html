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
import static com.aoindustries.encoding.TextInXhtmlAttributeEncoder.textInXhtmlAttributeEncoder;
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
public class Input extends EmptyElement<Input> {

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
	 * See <a href="https://www.w3schools.com/tags/att_input_checked.asp">HTML input checked Attribute</a>.
	 */
	public Input checked(boolean checked) throws IOException {
		if(checked) {
			if(html.serialization == Serialization.SGML) {
				html.out.write(" checked");
			} else {
				assert html.serialization == Serialization.XML;
				html.out.write(" checked=\"checked\"");
			}
		}
		return this;
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_input_disabled.asp">HTML input disabled Attribute</a>.
	 */
	public Input disabled(boolean disabled) throws IOException {
		if(disabled) {
			if(html.serialization == Serialization.SGML) {
				html.out.write(" disabled");
			} else {
				assert html.serialization == Serialization.XML;
				html.out.write(" disabled=\"disabled\"");
			}
		}
		return this;
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_input_maxlength.asp">HTML input maxlength Attribute</a>.
	 */
	public Input maxlength(Integer maxlength) throws IOException {
		if(maxlength != null) {
			html.out.write(" maxlength=\"");
			html.out.write(maxlength.toString());
			html.out.write('"');
		}
		return this;
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_input_name.asp">HTML input name Attribute</a>.
	 */
	public Input name(Object name) throws IOException {
		if(name != null) {
			html.out.write(" name=\"");
			Coercion.write(name, textInXhtmlAttributeEncoder, html.out);
			html.out.write('"');
		}
		return this;
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_input_readonly.asp">HTML input readonly Attribute</a>.
	 */
	public Input readonly(boolean readonly) throws IOException {
		if(readonly) {
			if(html.serialization == Serialization.SGML) {
				html.out.write(" readonly");
			} else {
				assert html.serialization == Serialization.XML;
				html.out.write(" readonly=\"readonly\"");
			}
		}
		return this;
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_input_size.asp">HTML input size Attribute</a>.
	 */
	public Input size(Integer size) throws IOException {
		if(size != null) {
			html.out.write(" size=\"");
			html.out.write(size.toString());
			html.out.write('"');
		}
		return this;
	}

	/**
	 * {@inheritDoc}
	 * When in input element, valid in all doctypes.
	 */
	@Override
	public Input tabindex(Integer tabindex) throws IOException {
		if(tabindex != null) {
			html.out.write(" tabindex=\"");
			html.out.write(tabindex.toString());
			html.out.write('"');
		}
		return this;
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_input_type.asp">HTML input type Attribute</a>.
	 *
	 * @return  {@code this} when type unchanged, or an instance of {@link Input} for the given type.
	 */
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
	public Input type(String type) throws IOException {
		if(type != null) {
			type(Type.valueOfWithLower(type));
		}
		return this;
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_input_value.asp">HTML input value Attribute</a>.
	 */
	public Input value(Object value) throws IOException {
		if(value != null) {
			html.out.write(" value=\"");
			// Allow text markup from translations
			Coercion.write(
				value,
				(type == null) ? null : type.getMarkupType(),
				textInXhtmlAttributeEncoder,
				false,
				html.out
			);
			html.out.write('"');
		}
		return this;
	}
}
