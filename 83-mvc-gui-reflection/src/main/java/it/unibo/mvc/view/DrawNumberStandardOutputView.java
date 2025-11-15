package it.unibo.mvc.view;

import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.api.DrawNumberView;
import it.unibo.mvc.api.DrawResult;

/**
 * A simple standard output view.
 */
public final class DrawNumberStandardOutputView implements DrawNumberView {

    @Override
    public void setController(final DrawNumberController observer) {

    }

    @Override
    public void start() {
        System.out.println("Game started!"); // NOPMD this is a standard output view
    }

    @Override
    public void result(final DrawResult res) {
        System.out.println(res.getDescription()); // NOPMD this is a standard output view
    }
}
