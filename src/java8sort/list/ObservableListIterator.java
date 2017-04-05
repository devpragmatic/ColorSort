package java8sort.list;

import java.util.List;
import java.util.ListIterator;
import java8sort.listener.Listener;

/**
 *
 * @author devpragmatic
 * @param <T>
 */
public class ObservableListIterator<T> extends ObservableBase<T> implements ListIterator<T> {

    private final ListIterator<T> instance;
    private T currentValue;

    public ObservableListIterator(ListIterator<T> instance, List<Listener> addListeners, List<Listener> removeListeners, List<Listener> setListeners) {
        super(addListeners, removeListeners, setListeners);
        this.instance = instance;
    }

    @Override
    public boolean hasNext() {
        return instance.hasNext();
    }

    @Override
    public T next() {
        currentValue = instance.next();
        return currentValue;
    }

    @Override
    public boolean hasPrevious() {
        return instance.hasPrevious();
    }

    @Override
    public T previous() {
        currentValue = instance.previous();
        return currentValue;
    }

    @Override
    public int nextIndex() {
        return instance.nextIndex();
    }

    @Override
    public int previousIndex() {
        return instance.previousIndex();
    }

    @Override
    public void remove() {
        runListeners(removeListeners, currentValue, instance.previousIndex());
        instance.remove();
    }

    @Override
    public void set(T e) {
        runListeners(setListeners, e, instance.previousIndex());
        instance.set(e);
    }

    @Override
    public void add(T e) {
        runListeners(addListeners, e, instance.previousIndex());
        instance.add(e);
    }

}
