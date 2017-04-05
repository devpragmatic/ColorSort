package java8sort.listener;

import java8sort.object.Figure;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author devpragmatic
 */
public class DrawListener implements Listener {

    private final double scale;
    private final GraphicsContext gc;
    private final int y;

    public DrawListener(double scale, GraphicsContext gc, int y) {
        this.scale = scale;
        this.gc = gc;
        this.y = y;
    }

    @Override
    public void run(Object object, int i) {
        if (object instanceof Figure) {
            draw(gc, (Figure) object, i);
        }
    }

    private void draw(GraphicsContext gc, final Figure colorsShuffle, int i) {
        gc.setFill(colorsShuffle.getColor());
        gc.setStroke(colorsShuffle.getColor());
        gc.strokeLine(i * scale, y, i * scale, y + 40);
    }

}
