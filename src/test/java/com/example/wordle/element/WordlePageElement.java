package com.example.wordle.element;

import com.example.wordle.type.DataState;
import com.vaadin.flow.component.html.testbench.DivElement;
import com.vaadin.testbench.annotations.Attribute;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;

import java.util.List;

/**
 * The Wordle page.
 *
 * <pre>
 *   <div class="App-module_game__NSc-J" id="wordle-app-game">
 *     <div class="Board-module_boardContainer__cKb-C">
 *       <div class="Board-module_board__lbzlf" style="width: 350px; height: 420px;">
 *         <div class="Row-module_row__dEHfN">
 *           <div class="" style="animation-delay: 0ms;">
 *             <div class="Tile-module_tile__3ayIZ" data-state="${data-state}" data-animation="idle" data-testid="tile">
 *               ::before
 *               "${letter}"
 *               ...
 *
 *     <div class="Keyboard-module_keyboard__1HSnn">
 *         <div class="Keyboard-module_row__YWe5w">
 *             <button type="button" data-key="${data-key}" class="Key-module_key__Rv-Vp" data-state="${dataState}">${data-key}</button>
 *             ...
 * </pre>
 */
@Slf4j
@Attribute(name = "id", value = "wordle-app-game")
public class WordlePageElement extends DivElement {
    public static final int WORD_LENGTH = 5;
    public static final int WORD_COUNT = 6;

    /**
     * Return the modal overlay element.
     *
     * @return the modal overlay if it is present
     * or null otherwise.
     */
    public ModalOverlayElement getModalOverlay() {
        try {
            return $(ModalOverlayElement.class).first();
        }
        catch (NoSuchElementException e) {
            log.warn("Couldn't find WordlePageElement::getModalOverlay.");
            return null;
        }
    }

    /**
     * Return the display board element.
     *
     * @return the display board element if it is present
     * or null otherwise.
     */
    protected BoardElement getBoard() {
        try {
            return $(BoardElement.class).first();
        }
        catch (NoSuchElementException e) {
            log.warn("Couldn't find WordlePageElement::getBoard.");
            return null;
        }
    }

    /**
     * Return the keyboard element.
     *
     * @return the keyboard element if it is present
     * or null otherwise.
     */
    protected KeyboardElement getKeyboard() {
        try {
            return $(KeyboardElement.class).first();
        }
        catch (NoSuchElementException e) {
            log.warn("Couldn't find WordlePageElement::getKeyboard.");
            return null;
        }
    }

    public void pressKey(char ch) {
        getKeyboard().pressKey(ch);
    }

    public void pressDeleteKey() {
        getKeyboard().pressDeleteKey();
    }

    public void pressEnterKey() {
        getKeyboard().pressEnterKey();
    }

    public List<BoardTileElement> getBoardRowTiles(int rowNum) {
        return getBoard().getBoardRow(rowNum).getBoardTiles();
    }

    public List<BoardTileElement> playWord(int wordNum, String word) {
        word.chars()
                .mapToObj(i -> (char) i)
                .forEach(this::pressKey);
        pressEnterKey();

        try {
            if (wordNum < (WORD_COUNT - 1)) {
                // wait for keyboard to re-enable
                var boardTileElement = getBoardRowTiles(wordNum + 1).get(0);
                waitUntil(webDriver -> {
                    pressKey('a');
                    var enabled = (boardTileElement.getDataState() != DataState.EMPTY);
                    if (enabled) {
                        pressDeleteKey();
                    }
                    return enabled;
                });
            }
            else {
                // pause for a second
                waitUntil(webDriver -> false, 1);
            }
        }
        catch (TimeoutException e) {
            // ignore
        }

        return getBoardRowTiles(wordNum);
    }

}
