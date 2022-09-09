package com.example.wordle.element;

import com.example.wordle.type.DataState;
import com.vaadin.flow.component.html.testbench.NativeButtonElement;
import com.vaadin.testbench.annotations.Attribute;

/**
 * Page object for key element.
 *
 * <pre>
 *   <button type="button" data-key="${data-key}" class="Key-module_key__Rv-Vp" data-state="${dataState}">q</button>
 * </pre>
 */
@Attribute(name = "class", contains = "Key-module_key__Rv-Vp")
public class KeyElement extends NativeButtonElement {

    /**
     * Get state of key.
     *
     * @return state of key
     */
    public DataState getDataState() {
        return DataState.fromCaption(this.getAttribute("data-state"));
    }

}
