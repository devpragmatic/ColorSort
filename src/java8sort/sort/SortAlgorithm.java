package java8sort.sort;

import java.util.List;
import java8sort.object.Sortable;

/**
 *
 * @author devpragmatic
 * @param <T>
 */
public interface SortAlgorithm<T extends Sortable> {

    void sort(List<T> sortedObjects);
}
