package java8sort.object;

import javafx.scene.paint.Color;

/**
 *
 * @author devpragmatic
 */
public class Figure implements Sortable, Comparable<Figure> {

    private final Color color;
    private final int position;

    public Figure(Color color, int position) {
        this.color = color;
        this.position = position;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public int compareTo(Figure o) {
        return o.position - this.position;
    }

}
