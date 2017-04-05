package java8sort;

import java.util.Collections;
import java8sort.list.ObservableList;
import java8sort.listener.DrawListener;
import java8sort.object.Figure;
import java8sort.sort.BubbleSort;
import java8sort.sort.SortAlgorithm;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author devpragmatic
 */
public class Java8Sort extends Application {

    double step = 0.001;
    int countLoop = 1000;
    double scale = 0.2;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Drawing Operations Test");
        Group root = new Group();
        final int max = 7 * countLoop + 7;
        Canvas canvas = new Canvas(max * scale, 300);
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setLineWidth(scale);
        Task task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                int position = 0;
                final ObservableList<Figure> colors = new ObservableList<>();
                colors.putAddListener(new DrawListener(scale, gc, 100));
                for (int i = 0; i <= countLoop; i++) {
                    colors.add(new Figure(Color.color((double) i / countLoop, 0, 0), position++));
                }
                for (int i = 0; i <= countLoop; i++) {
                    colors.add(new Figure(Color.color(1.0, (double) i / countLoop, 0), position++));
                }
                for (int i = countLoop; i >= 0; i--) {
                    colors.add(new Figure(Color.color((double) i / countLoop, 1.0, 0), position++));
                }
                for (int i = 0; i <= countLoop; i++) {
                    colors.add(new Figure(Color.color(0, 1.0, (double) i / countLoop), position++));
                }
                for (int i = countLoop; i >= 0; i--) {
                    colors.add(new Figure(Color.color(0, (double) i / countLoop, 1.0), position++));
                }
                for (int i = 0; i <= countLoop; i++) {
                    colors.add(new Figure(Color.color((double) i / countLoop, 0, 1.0), position++));
                }
                for (int i = 0; i <= countLoop; i++) {
                    colors.add(new Figure(Color.color(1.0, (double) i / countLoop, 1.0), position++));
                }
                colors.clearAddListeners();
                final DrawListener drawListener = new DrawListener(scale, gc, 10);
                colors.putAddListener(drawListener);
                colors.putSetListener(drawListener);
                Collections.shuffle(colors);
                SortAlgorithm<Figure> sortAlgorithm = new BubbleSort<>();
//                colors.sort((o1, o2) -> o1.getPosition() - o2.getPosition());
                sortAlgorithm.sort(colors);
                final DrawListener drawListenerNEw = new DrawListener(scale, gc, 55);
                final ObservableList<Figure> colorsOne = new ObservableList<>();
                colorsOne.putAddListener(drawListenerNEw);
                colorsOne.addAll(colors);
                return null;
            }
        };
        new Thread(task).start();
    }
}
