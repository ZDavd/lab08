package it.unibo.mvc;

import java.io.InvalidClassException;
import java.lang.reflect.InvocationTargetException;

import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.api.DrawNumberView;
import it.unibo.mvc.controller.DrawNumberControllerImpl;
import it.unibo.mvc.model.DrawNumberImpl;

/**
 * Application entry-point.
 */
public final class LaunchApp {

    private LaunchApp() { }

    /**
     * Runs the application.
     *
     * @param args ignored
     * @throws ClassNotFoundException if the fetches class does not exist
     * @throws SecurityException in case of reflection issues
     * @throws NoSuchMethodException if the 0-ary constructor do not exist
     * @throws InvocationTargetException if the constructor throws exceptions
     * @throws InstantiationException if the constructor throws exceptions
     * @throws IllegalAccessException in case of reflection issues
     * @throws IllegalArgumentException in case of reflection issues
     * @throws InvalidClassException if the fetched class does not implement DrawNumberView
     */
    public static void main(final String... args) throws 
        ClassNotFoundException, 
        InstantiationException, 
        IllegalAccessException, 
        InvocationTargetException, 
        NoSuchMethodException, 
        InvalidClassException {

        final var model = new DrawNumberImpl();
        final DrawNumberController app = new DrawNumberControllerImpl(model);
        final String[] viewClassName = {
            "StandardOutput",
            "Swing",
        };
        for (final String type : viewClassName) {
            final Class<? extends DrawNumberView> viewClass = 
            Class.forName("it.unibo.mvc.view.DrawNumber" + type + "View").asSubclass(DrawNumberView.class);
            final DrawNumberView view = viewClass.getConstructor().newInstance();
            for (int i = 0; i < 3; i++) {
                app.addView(view);
            }
        }
    }
}
