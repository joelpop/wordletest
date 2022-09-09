package com.example.wordle.element;

import com.example.wordle.type.DataState;
import com.vaadin.flow.component.html.testbench.DivElement;
import com.vaadin.testbench.annotations.Attribute;
import lombok.extern.slf4j.Slf4j;

/**
 * Page object for board tile.
 *
 * <pre>
 *   <div class="Tile-module_tile__3ayIZ" data-state="empty" data-animation="idle" data-testid="tile">
 * </pre>
 */
@Slf4j
@Attribute(name = "data-testid", contains = "tile")
public class BoardTileElement extends DivElement {

    /**
     * Get letter of tile.
     *
     * @return letter of tile
     */
    public char getLetter() {
        var text = getText();
        return (text == null || text.isEmpty()) ? ' ' : text.charAt(0);
    }

    /**
     * Get state of tile.
     *
     * @return state of tile
     */
    public DataState getDataState() {
        return DataState.fromCaption(getAttribute("data-state"));
    }

}
