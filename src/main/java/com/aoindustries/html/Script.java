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
import com.aoindustries.encoding.MediaEncoder;
import com.aoindustries.encoding.MediaException;
import com.aoindustries.encoding.MediaType;
import com.aoindustries.encoding.MediaWriter;
import static com.aoindustries.encoding.TextInXhtmlAttributeEncoder.encodeTextInXhtmlAttribute;
import com.aoindustries.io.NoCloseWriter;
import com.aoindustries.util.StringUtility;
import com.aoindustries.util.WrappedException;
import java.io.IOException;
import java.util.Locale;

/**
 * See <a href="https://www.w3schools.com/tags/tag_script.asp">HTML script tag</a>.
 *
 * @author  AO Industries, Inc.
 */
public class Script extends Element<Script> implements
	Attributes.Boolean.Async<Script>,
	Attributes.Enum.Charset<Script,Attributes.Enum.Charset.Value>,
	Attributes.Boolean.Defer<Script>,
	Attributes.Url.Src<Script>,
	// TODO: type
	// TODO: xmlSpace
	// Global Attributes: https://www.w3schools.com/tags/ref_standardattributes.asp
	Attributes.Text.ClassNoHtml4<Script>,
	Attributes.Text.IdNoHtml4<Script>,
	Attributes.Text.StyleNoHtml4<Script>,
	Attributes.Text.TitleNoHtml4<Script>,
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	// Not on <script>: Attributes.Event.AlmostGlobal<Script>
	Attributes.Event.Window.Onerror<Script>,
	Attributes.Event.Window.Onload<Script>
{

	/**
	 * See <a href="https://www.w3schools.com/tags/att_script_type.asp">HTML script type Attribute</a>.
	 */
	public enum Type {
		/**
		 * The default type for (X)HTML 5.
		 */
		APPLICATION_JAVASCRIPT("application/javascript"),

		/**
		 * The default type for XHTML 1.0 / HTML 4.
		 *
		 * @deprecated  Use {@link #APPLICATION_JAVASCRIPT} in HTML 5.
		 */
		@Deprecated
		TEXT_JAVASCRIPT("text/javascript"),

		/**
		 * A JSON script.
		 */
		APPLICATION_JSON("application/json"),

		/**
		 * A JSON linked data script.
		 */
		APPLICATION_JD_JSON("application/ld+json"),

		APPLICATION_ECMASCRIPT("application/ecmascript");

		private final String contentType;

		private Type(String contentType) {
			this.contentType = contentType;
		}

		@Override
		public String toString() {
			return contentType;
		}

		public String getContentType() {
			return contentType;
		}

		private static boolean assertAllLowerCaseAndTrimmed() {
			for(Type type : values()) {
				if(!type.contentType.equals(type.contentType.toLowerCase(Locale.ROOT))) throw new AssertionError("Content types must be lowercase as looked-up later");
				if(!type.contentType.equals(type.contentType.trim())) throw new AssertionError("Content types must be trimmed as looked-up later");
			}
			return true;
		}
		static {
			assert assertAllLowerCaseAndTrimmed();
		}
	}

	private final String type;

	public Script(Html html) {
		super(html);
		this.type = null;
	}

	public Script(Html html, String type) {
		super(html);
		type = StringUtility.trimNullIfEmpty(type);
		this.type = (type == null) ? null : type.toLowerCase(Locale.ROOT);
	}

	public Script(Html html, Type type) {
		super(html);
		this.type = (type == null) ? null : type.getContentType();
	}

	@Override
	protected Script open() throws IOException {
		html.out.write("<script");
		Script s = type();
		assert s == this;
		return this;
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_script_type.asp">HTML script type Attribute</a>.
	 *
	 * @see Doctype#scriptType(java.lang.Appendable)
	 */
	protected Script type() throws IOException {
		// TODO: Check didBody here and other attributes, perhaps in some central attribute registry that detects duplicate attributes, too
		if(
			type == null
			|| type.equals(Type.APPLICATION_JAVASCRIPT.getContentType())
			|| type.equals(Type.TEXT_JAVASCRIPT.getContentType())
		) {
			html.doctype.scriptType(html.out);
		} else {
			html.out.write(" type=\"");
			encodeTextInXhtmlAttribute(type, html.out);
			html.out.write('"');
		}
		return this;
	}

	protected MediaType getMediaType() throws IOException {
		try {
			return type == null ? MediaType.JAVASCRIPT : MediaType.getMediaTypeForContentType(type);
		} catch(MediaException e) {
			throw new IOException(e);
		}
	}

	protected MediaEncoder getMediaEncoder(MediaType mediaType) throws IOException {
		try {
			return MediaEncoder.getInstance(null, mediaType, MediaType.XHTML);
		} catch(MediaException e) {
			throw new IOException(e);
		}
	}

	protected boolean doCdata() {
		return
			html.serialization == Serialization.XML
			&& (
				type == null
				|| Type.APPLICATION_JAVASCRIPT.getContentType().equals(type)
				|| Type.TEXT_JAVASCRIPT.getContentType().equals(type)
			);
	}

	private boolean didBody;

	protected void startBody() throws IOException {
		if(!didBody) {
			html.out.write('>');
			if(doCdata()) html.out.write("//<![CDATA[\n");
			didBody = true;
		}
	}

	// TODO: Return a "Body" / "ScriptBody" that only allows additional out or closing the tag.
	// TODO:     Setting attributes after startBody() would create invalid HTML.
	// TODO:     Similar for "text", too.
	// TODO: Interface for "out" with default methods? (Another for "text", too)
	public Script out(Object script) throws IOException {
		while(script instanceof Supplier<?,?>) {
			try {
				script = ((Supplier<?,?>)script).get();
			} catch(Error|RuntimeException|IOException e) {
				throw e;
			} catch(Throwable t) {
				throw new WrappedException(t);
			}
		}
		if(script instanceof ScriptWriter) {
			try {
				return out((ScriptWriter<?>)script);
			} catch(Error|RuntimeException|IOException e) {
				throw e;
			} catch(Throwable t) {
				throw new WrappedException(t);
			}
		}
		script = Coercion.nullIfEmpty(script);
		if(script != null) {
			startBody();
			// Allow text markup from translations
			// TODO: writeWithMarkup appropriate for capturedBody?
			// TODO: I think this would only work with SegmentedBuffer with a single segment
			// TODO: We might need a special case in CharArrayWriter if we want this identity match for a single string
			// TODO: Set back to SegmentedBuffer, if this is the case
			MediaType mediaType = getMediaType();
			Coercion.write(
				script,
				mediaType.getMarkupType(),
				getMediaEncoder(mediaType),
				false,
				html.out
			);
		}
		return this;
	}

	public <Ex extends Throwable> Script out(Supplier<?,Ex> script) throws IOException, Ex {
		return out((script == null) ? null : script.get());
	}

	// TODO: Consolidate with AttributeWriter?
	@FunctionalInterface
	public static interface ScriptWriter<Ex extends Throwable> {
		void writeScript(MediaWriter script) throws IOException, Ex;
	}

	public <Ex extends Throwable> Script out(ScriptWriter<Ex> script) throws IOException, Ex {
		if(script != null) {
			MediaEncoder encoder = getMediaEncoder(getMediaType());
			startBody();
			script.writeScript(
				new MediaWriter(
					encoder,
					new NoCloseWriter(html.out)
				)
			);
		}
		return this;
	}

	/**
	 * Writes the script, automatically closing the script via
	 * {@link #__()} on {@link MediaWriter#close()}.
	 * This is well suited for use in a try-with-resources block.
	 */
	public MediaWriter out__() throws IOException {
		MediaEncoder encoder = getMediaEncoder(getMediaType());
		startBody();
		return new MediaWriter(encoder, html.out) {
			@Override
			public void close() throws IOException {
				__();
			}
		};
	}

	public Html __() throws IOException {
		if(!didBody) {
			html.out.write("></script>");
		} else {
			// TODO: Track what was written and avoid unnecessary newline?
			html.nl();
			if(doCdata()) html.out.write("//]]>");
			html.out.write("</script>");
		}
		return html;
	}
}
