package java8sort.sort;

import java.util.List;
import java8sort.object.Sortable;

/**
 *
 * @author devpragmatic
 * @param <T>
 */
public class BubbleSort<T extends Sortable> implements SortAlgorithm<T> {

    @Override
    public void sort(List<T> tableToSort) {
        T temp;
        int zmiana = 1;
        while (zmiana > 0) {
            zmiana = 0;
            for (int i = 0; i < tableToSort.size() - 1; i++) {
                if (tableToSort.get(i).getPosition() > tableToSort.get(i + 1).getPosition()) {
                    temp = tableToSort.get(i + 1);
                    tableToSort.set(i + 1, tableToSort.get(i));
                    tableToSort.set(i, temp);
                    zmiana++;
                }
            }
        }
    }

}
