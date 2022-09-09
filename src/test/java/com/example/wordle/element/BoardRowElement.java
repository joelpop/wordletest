package com.example.wordle.element;

import com.vaadin.flow.component.html.testbench.DivElement;
import com.vaadin.testbench.annotations.Attribute;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.NoSuchElementException;

import java.util.List;
import java.util.stream.IntStream;

/**
 * Page object for board row.
 *
 * <pre>
 *   <div class="Row-module_row__dEHfN">
 * </pre>
 */
@Slf4j
@Attribute(name = "class", contains = "Row-module_row__dEHfN")
public class BoardRowElement extends DivElement {

    /**
     * Return the requested board tile element.
     *
     * @param tileNum the (zero-based) number of the board tile
     * @return the requested board tile element if it is present
     * or null otherwise.
     */
    protected BoardTileElement getBoardTile(int tileNum) {
        try {
            return $(BoardTileElement.class).get(tileNum);
        }
        catch (NoSuchElementException e) {
            log.warn("Couldn't find BoardRowElement::getBoardTile({}).", tileNum);
            return null;
        }
    }

    protected List<BoardTileElement> getBoardTiles() {
        return IntStream.range(0, WordlePageElement.WORD_LENGTH)
                .mapToObj(this::getBoardTile)
                .toList();
    }

}
