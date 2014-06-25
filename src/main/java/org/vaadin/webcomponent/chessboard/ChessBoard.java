
package org.vaadin.webcomponent.chessboard;

import com.vaadin.annotations.JavaScript;
import com.vaadin.annotations.StyleSheet;
import com.vaadin.ui.AbstractJavaScriptComponent;

/**
 *
 * @author mattitahvonenitmill
 */

@JavaScript({"vaadinintegration.js"})
@StyleSheet("chessstyles.css")
public final class ChessBoard extends AbstractJavaScriptComponent {

    public ChessBoard(String boardState) {
        setBoardState(boardState);
    }

    public ChessBoard() {
        this("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
    }

    public void move(String from, String to) {
        callFunction("move", from, to);
    }
    
    public void setBoardState(String boardState) {
        callFunction("setBoardState", boardState);
    }
    
}
