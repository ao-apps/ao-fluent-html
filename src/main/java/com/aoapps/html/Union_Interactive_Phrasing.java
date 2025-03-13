/*
 * ao-fluent-html - Fluent Java DSL for high-performance HTML generation.
 * Copyright (C) 2021, 2022, 2025  AO Industries, Inc.
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
 * along with ao-fluent-html.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.aoapps.html;

import com.aoapps.html.any.AnyINPUT;
import com.aoapps.html.any.AnyUnion_Interactive_Phrasing;
import com.aoapps.html.any.Suppliers;
import com.aoapps.lang.io.function.IOConsumerE;
import com.aoapps.lang.io.function.IOSupplierE;
import java.io.IOException;

/**
 * Elements that are common to both {@link InteractiveContent} and {@link PhrasingContent}.
 *
 * @param  <__>  This content model, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public interface Union_Interactive_Phrasing<
    __ extends Union_Interactive_Phrasing<__>
    > extends AnyUnion_Interactive_Phrasing<Document, __>,
    //
    // Unions:
    //
    Union_Embedded_Interactive<__> {

  //
  // Content models:
  //
  // Inherited: Content<__>

  //
  // Factories:
  //
  // <editor-fold defaultstate="collapsed" desc="A">
  @Override
  @SuppressWarnings("deprecation")
  default A<__> a() throws IOException {
    @SuppressWarnings("unchecked")
    __ pc = (__) this;
    Document document = getDocument();
    A<__> e = new A<>(document, pc);
    e.writeOpen(document.getRawUnsafe(null));
    return e;
  }

  @Override
  default A<__> a(String href) throws IOException {
    return a().href(href);
  }

  /**
   * {@inheritDoc}
   *
   * @param  <Ex>  An arbitrary exception type that may be thrown
   */
  @Override
  default <Ex extends Throwable> A<__> a(IOSupplierE<? extends String, Ex> href) throws IOException, Ex {
    return a().href(href);
  }

  @Override
  default A_c<__> a_c() throws IOException {
    return a()._c();
  }

  // </editor-fold>
  // Inherited: AUDIO
  // <editor-fold defaultstate="collapsed" desc="TODO: BUTTON">
  // </editor-fold>
  // Inherited: EMBED
  // <editor-fold defaultstate="collapsed" desc="INPUT">
  /**
   * Specialized input implementations.
   * <ul>
   * <li>See <a href="https://html.spec.whatwg.org/multipage/input.html#the-input-element">4.10.5 The input element</a>.</li>
   * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input">&lt;input&gt;: The Input (Form Input) element</a>.</li>
   * <li>See <a href="https://www.w3schools.com/tags/tag_input.asp">HTML input tag</a>.</li>
   * </ul>
   *
   * @param  <__>  This content model, which will be the parent content model of child elements
   */
  public static final class InputFactory<
      __ extends Union_Interactive_Phrasing<__>
      > extends AnyUnion_Interactive_Phrasing.InputFactory<Document, __> {

    InputFactory(Document document, __ pc) {
      super(document, pc);
    }

    @Override
    @SuppressWarnings("deprecation")
    public INPUT.Dynamic<__> dynamic() throws IOException {
      INPUT.Dynamic<__> e = new INPUT.Dynamic<>(document, pc);
      e.writeOpen(document.getRawUnsafe(null));
      return e;
    }

    @Override
    @SuppressWarnings("deprecation")
    public INPUT.Dynamic<__> dynamic(String type) throws IOException {
      INPUT.Dynamic<__> e = new INPUT.Dynamic<>(document, pc, type);
      e.writeOpen(document.getRawUnsafe(null));
      return e;
    }

    /**
     * {@inheritDoc}
     *
     * @param  <Ex>  An arbitrary exception type that may be thrown
     */
    @Override
    public <Ex extends Throwable> INPUT.Dynamic<__> dynamic(Suppliers.String<Ex> type) throws IOException, Ex {
      return dynamic((type == null) ? null : type.get());
    }

    @Override
    @SuppressWarnings("deprecation")
    public INPUT.Dynamic<__> dynamic(AnyINPUT.Dynamic.Type type) throws IOException {
      INPUT.Dynamic<__> e = new INPUT.Dynamic<>(document, pc, type);
      e.writeOpen(document.getRawUnsafe(null));
      return e;
    }

    /**
     * {@inheritDoc}
     *
     * @param  <Ex>  An arbitrary exception type that may be thrown
     */
    @Override
    public <Ex extends Throwable> INPUT.Dynamic<__> dynamic(IOSupplierE<? extends AnyINPUT.Dynamic.Type, Ex> type) throws IOException, Ex {
      return dynamic((type == null) ? null : type.get());
    }

    @Override
    @SuppressWarnings("deprecation")
    public INPUT.Button<__> button() throws IOException {
      INPUT.Button<__> e = new INPUT.Button<>(document, pc);
      e.writeOpen(document.getRawUnsafe(null));
      return e;
    }

    @Override
    @SuppressWarnings("deprecation")
    public INPUT.Checkbox<__> checkbox() throws IOException {
      INPUT.Checkbox<__> e = new INPUT.Checkbox<>(document, pc);
      e.writeOpen(document.getRawUnsafe(null));
      return e;
    }

    @Override
    @SuppressWarnings("deprecation")
    public INPUT.Color<__> color() throws IOException {
      INPUT.Color<__> e = new INPUT.Color<>(document, pc);
      e.writeOpen(document.getRawUnsafe(null));
      return e;
    }

    @Override
    @SuppressWarnings("deprecation")
    public INPUT.Date<__> date() throws IOException {
      INPUT.Date<__> e = new INPUT.Date<>(document, pc);
      e.writeOpen(document.getRawUnsafe(null));
      return e;
    }

    @Override
    @SuppressWarnings("deprecation")
    public INPUT.DatetimeLocal<__> datetimeLocal() throws IOException {
      INPUT.DatetimeLocal<__> e = new INPUT.DatetimeLocal<>(document, pc);
      e.writeOpen(document.getRawUnsafe(null));
      return e;
    }

    @Override
    @SuppressWarnings("deprecation")
    public INPUT.Email<__> email() throws IOException {
      INPUT.Email<__> e = new INPUT.Email<>(document, pc);
      e.writeOpen(document.getRawUnsafe(null));
      return e;
    }

    @Override
    @SuppressWarnings("deprecation")
    public INPUT.File<__> file() throws IOException {
      INPUT.File<__> e = new INPUT.File<>(document, pc);
      e.writeOpen(document.getRawUnsafe(null));
      return e;
    }

    @Override
    @SuppressWarnings("deprecation")
    public INPUT.Hidden<__> hidden() throws IOException {
      INPUT.Hidden<__> e = new INPUT.Hidden<>(document, pc);
      e.writeOpen(document.getRawUnsafe(null));
      return e;
    }

    @Override
    @SuppressWarnings("deprecation")
    public INPUT.Image<__> image() throws IOException {
      INPUT.Image<__> e = new INPUT.Image<>(document, pc);
      e.writeOpen(document.getRawUnsafe(null));
      return e;
    }

    @Override
    @SuppressWarnings("deprecation")
    public INPUT.Month<__> month() throws IOException {
      INPUT.Month<__> e = new INPUT.Month<>(document, pc);
      e.writeOpen(document.getRawUnsafe(null));
      return e;
    }

    @Override
    @SuppressWarnings("deprecation")
    public INPUT.Number<__> number() throws IOException {
      INPUT.Number<__> e = new INPUT.Number<>(document, pc);
      e.writeOpen(document.getRawUnsafe(null));
      return e;
    }

    @Override
    @SuppressWarnings("deprecation")
    public INPUT.Password<__> password() throws IOException {
      INPUT.Password<__> e = new INPUT.Password<>(document, pc);
      e.writeOpen(document.getRawUnsafe(null));
      return e;
    }

    @Override
    @SuppressWarnings("deprecation")
    public INPUT.Radio<__> radio() throws IOException {
      INPUT.Radio<__> e = new INPUT.Radio<>(document, pc);
      e.writeOpen(document.getRawUnsafe(null));
      return e;
    }

    @Override
    @SuppressWarnings("deprecation")
    public INPUT.Range<__> range() throws IOException {
      INPUT.Range<__> e = new INPUT.Range<>(document, pc);
      e.writeOpen(document.getRawUnsafe(null));
      return e;
    }

    @Override
    @SuppressWarnings("deprecation")
    public INPUT.Reset<__> reset() throws IOException {
      INPUT.Reset<__> e = new INPUT.Reset<>(document, pc);
      e.writeOpen(document.getRawUnsafe(null));
      return e;
    }

    @Override
    @SuppressWarnings("deprecation")
    public INPUT.Search<__> search() throws IOException {
      INPUT.Search<__> e = new INPUT.Search<>(document, pc);
      e.writeOpen(document.getRawUnsafe(null));
      return e;
    }

    @Override
    @SuppressWarnings("deprecation")
    public INPUT.Submit<__> submit() throws IOException {
      INPUT.Submit<__> e = new INPUT.Submit<>(document, pc);
      e.writeOpen(document.getRawUnsafe(null));
      return e;
    }

    @Override
    @SuppressWarnings("deprecation")
    public INPUT.Tel<__> tel() throws IOException {
      INPUT.Tel<__> e = new INPUT.Tel<>(document, pc);
      e.writeOpen(document.getRawUnsafe(null));
      return e;
    }

    @Override
    @SuppressWarnings("deprecation")
    public INPUT.Text<__> text() throws IOException {
      INPUT.Text<__> e = new INPUT.Text<>(document, pc);
      e.writeOpen(document.getRawUnsafe(null));
      return e;
    }

    @Override
    @SuppressWarnings("deprecation")
    public INPUT.Time<__> time() throws IOException {
      INPUT.Time<__> e = new INPUT.Time<>(document, pc);
      e.writeOpen(document.getRawUnsafe(null));
      return e;
    }

    @Override
    @SuppressWarnings("deprecation")
    public INPUT.Url<__> url() throws IOException {
      INPUT.Url<__> e = new INPUT.Url<>(document, pc);
      e.writeOpen(document.getRawUnsafe(null));
      return e;
    }

    @Override
    @SuppressWarnings("deprecation")
    public INPUT.Week<__> week() throws IOException {
      INPUT.Week<__> e = new INPUT.Week<>(document, pc);
      e.writeOpen(document.getRawUnsafe(null));
      return e;
    }
  }

  @Override
  default InputFactory<__> input() {
    @SuppressWarnings("unchecked")
    __ pc = (__) this;
    return new InputFactory<>(getDocument(), pc);
  }

  // </editor-fold>
  // Inherited: IFRAME
  // Inherited: IMG
  // <editor-fold defaultstate="collapsed" desc="LABEL">
  @Override
  @SuppressWarnings("deprecation")
  default LABEL<__> label() throws IOException {
    @SuppressWarnings("unchecked")
    __ pc = (__) this;
    Document document = getDocument();
    LABEL<__> e = new LABEL<>(document, pc);
    e.writeOpen(document.getRawUnsafe(null));
    return e;
  }

  /**
   * Creates a label element with no attributes and the given body.
   * <ul>
   * <li>See <a href="https://html.spec.whatwg.org/multipage/forms.html#the-label-element">4.10.4 The label element</a>.</li>
   * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/label">&lt;label&gt;: The Input Label element</a>.</li>
   * <li>See <a href="https://www.w3schools.com/tags/tag_label.asp">HTML label tag</a>.</li>
   * </ul>
   *
   * @param  <Ex>  An arbitrary exception type that may be thrown
   *
   * @return  This content model, which will be the parent content model of child elements
   */
  default <Ex extends Throwable> __ label__(IOConsumerE<? super LABEL__<__>, Ex> label) throws IOException, Ex {
    return label().__(label);
  }

  @Override
  default LABEL_c<__> label_c() throws IOException {
    return label()._c();
  }

  // </editor-fold>
  // Inherited: OBJECT
  // <editor-fold defaultstate="collapsed" desc="SELECT">
  @Override
  @SuppressWarnings("deprecation")
  default SELECT<__> select() throws IOException {
    @SuppressWarnings("unchecked")
    __ pc = (__) this;
    Document document = getDocument();
    SELECT<__> e = new SELECT<>(document, pc);
    e.writeOpen(document.getRawUnsafe(null));
    return e;
  }

  /**
   * Creates a select element with no attributes and the given body.
   * <ul>
   * <li>See <a href="https://html.spec.whatwg.org/multipage/form-elements.html#the-select-element">4.10.7 The select element</a>.</li>
   * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/select">&lt;section&gt;: The HTML Select element</a>.</li>
   * <li>See <a href="https://www.w3schools.com/tags/tag_select.asp">HTML select tag</a>.</li>
   * </ul>
   *
   * @param  <Ex>  An arbitrary exception type that may be thrown
   *
   * @return  This content model, which will be the parent content model of child elements
   */
  default <Ex extends Throwable> __ select__(IOConsumerE<? super SELECT__<__>, Ex> select) throws IOException, Ex {
    return select().__(select);
  }

  @Override
  default SELECT_c<__> select_c() throws IOException {
    return select()._c();
  }

  // </editor-fold>
  // <editor-fold defaultstate="collapsed" desc="TEXTAREA">
  @Override
  @SuppressWarnings("deprecation")
  default TEXTAREA<__> textarea() throws IOException {
    @SuppressWarnings("unchecked")
    __ pc = (__) this;
    Document document = getDocument();
    TEXTAREA<__> e = new TEXTAREA<>(document, pc);
    e.writeOpen(document.getRawUnsafe(null));
    return e;
  }

  /**
   * Creates a textarea element with no attributes and the given body.
   * <ul>
   * <li>See <a href="https://html.spec.whatwg.org/multipage/form-elements.html#the-textarea-element">4.10.11 The textarea element</a>.</li>
   * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/textarea">&lt;textarea&gt;: The Textarea element</a>.</li>
   * <li>See <a href="https://www.w3schools.com/tags/tag_textarea.asp">HTML textarea tag</a>.</li>
   * </ul>
   *
   * @param  <Ex>  An arbitrary exception type that may be thrown
   *
   * @return  This content model, which will be the parent content model of child elements
   */
  default <Ex extends Throwable> __ textarea__(IOConsumerE<? super TEXTAREA__<__>, Ex> textarea) throws IOException, Ex {
    return textarea().__(textarea);
  }

  @Override
  default TEXTAREA_c<__> textarea_c() throws IOException {
    return textarea()._c();
  }
  // </editor-fold>
  // Inherited: VIDEO
}
