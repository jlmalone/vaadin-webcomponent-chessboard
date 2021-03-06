package org.vaadin.webcomponent.chessboard;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.BootstrapFragmentResponse;
import com.vaadin.server.BootstrapListener;
import com.vaadin.server.BootstrapPageResponse;
import com.vaadin.server.SessionInitEvent;
import com.vaadin.server.SessionInitListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import org.jsoup.nodes.Element;
import org.vaadin.maddon.button.PrimaryButton;
import org.vaadin.maddon.label.RichText;
import org.vaadin.maddon.layouts.MHorizontalLayout;
import org.vaadin.maddon.layouts.MVerticalLayout;

@Theme("dawn")
@SuppressWarnings("serial")
public class MyVaadinUI extends UI {

    @Override
    protected void init(VaadinRequest request) {
        final VerticalLayout layout = new MVerticalLayout(new RichText().
                withMarkDownResource("/intro.md"));
        setContent(layout);

        Button button = new Button("Add Chess board");
        button.addClickListener((ClickEvent event) -> {
            ChessBoard chessBoard = new ChessBoard(
                    "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
            layout.addComponent(chessBoard);

            TextField from = new TextField("From");
            from.setValue("b1");
            TextField to = new TextField("To");
            to.setValue("c3");
            PrimaryButton move = new PrimaryButton("Move");
            move.addClickListener((ClickEvent event1) -> {
                chessBoard.move(from.getValue(), to.getValue());
            });
            layout.addComponent(new MHorizontalLayout(from, to, move).
                    alignAll(Alignment.BOTTOM_LEFT));

            TextField state = new TextField("State, FEN syntax");
            state.setValue(
                    "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
            PrimaryButton set = new PrimaryButton("Set state");
            set.addClickListener((ClickEvent event1) -> {
                chessBoard.setBoardState(state.getValue());
            });
            layout.addComponent(new MHorizontalLayout(state, set).alignAll(
                    Alignment.BOTTOM_LEFT));

        });
        layout.addComponent(button);
    }

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = MyVaadinUI.class)
    public static class Servlet extends VaadinServlet {

        @Override
        protected void servletInitialized() throws ServletException {
            super.servletInitialized();
            getService().addSessionInitListener(new SessionInitListener() {

                public void sessionInit(SessionInitEvent event) {
                    event.getSession().addBootstrapListener(polymerInjector);
                }
            });
        }

    }

    /**
     * This injects polymer.js and es6 support polyfils directly into host page.
     *
     * Better compatibility and good approach if you have multiple webcomponents
     * in the app.
     */
    public static BootstrapListener polymerInjector = new BootstrapListener() {

        @Override
        public void modifyBootstrapFragment(
                BootstrapFragmentResponse response) {
        }

        @Override
        public void modifyBootstrapPage(
                BootstrapPageResponse response) {
            Element head = response.getDocument().getElementsByTag("head").
                    get(0);

            // add polymer for older browsers
            Element polymer = response.getDocument().createElement("script");
            polymer.
                    attr("src", "VAADIN/chessstuff/polymer-platform/platform.js");
            head.appendChild(polymer);

            // add es6 support for older browsers
            Element traceur = response.getDocument().createElement("script");
            traceur.attr("src",
                    "VAADIN/chessstuff/traceur-runtime/traceur-runtime.min.js");
            head.appendChild(traceur);
        }
    };
}
