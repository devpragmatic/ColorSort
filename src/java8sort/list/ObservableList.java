package java8sort.list;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java8sort.listener.Listener;

/**
 *
 * @author devpragmatic
 */
public class ObservableList<T> extends ObservableBase<T> implements List<T> {

    private final List<T> instance;

    public ObservableList() {
        this.instance = new ArrayList<>();
    }

    @Override
    public int size() {
        return instance.size();
    }

    @Override
    public boolean isEmpty() {
        return instance.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return instance.contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return instance.iterator();
    }

    @Override
    public Object[] toArray() {
        return instance.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return instance.toArray(a);
    }

    @Override
    public boolean add(T e) {
        runListeners(addListeners, e, instance.size());
        return instance.add(e);
    }

    @Override
    public boolean remove(Object o) {
        runListeners(removeListeners, o, instance.indexOf(o));
        return instance.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return instance.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        if (c != null) {
            Object[] array = c.toArray();
            for (int i = 0; i < c.size(); i++) {
                runListeners(addListeners, array[i], instance.size() + i);
            }
        }
        return instance.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        for (int i = 0; i < c.size(); i++) {
            runListeners(addListeners, c, instance.size() + i + index);
        }
        return instance.addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        runListeners(removeListeners, c);
        return instance.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c == null) {
            return true;
        }
        int i = 0;
        for (Iterator<T> instIter = instance.iterator(); instIter.hasNext(); i++) {
            T element = instIter.next();
            if (!c.contains(element)) {
                runListeners(removeListeners, element, i);
                instIter.remove();
            }
        }
        return instance.retainAll(c);
    }

    @Override
    public void clear() {
        runListeners(removeListeners, instance);
        instance.clear();
    }

    @Override
    public T get(int index) {
        return instance.get(index);
    }

    @Override
    public T set(int index, T element) {
        runListeners(setListeners, element, index);
        return instance.set(index, element);
    }

    @Override
    public void add(int index, T element) {
        runListeners(addListeners, element, index);
        instance.add(index, element);
    }

    @Override
    public T remove(int index) {
        final T removed = instance.remove(index);
        runListeners(removeListeners, removed, index);
        return removed;
    }

    @Override
    public int indexOf(Object o) {
        return instance.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return instance.lastIndexOf(o);
    }

    @Override
    public ListIterator<T> listIterator() {
        return new ObservableListIterator<>(instance.listIterator(), addListeners, removeListeners, setListeners);
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return new ObservableListIterator<>(instance.listIterator(index), addListeners, removeListeners, setListeners);
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return instance.subList(fromIndex, toIndex);
    }

    public void putAddListener(Listener listener) {
        addListeners.add(listener);
    }

    public void putRemoveListener(Listener listener) {
        removeListeners.add(listener);
    }

    public void putSetListener(Listener listener) {
        setListeners.add(listener);
    }

    public void clearAddListeners() {
        addListeners.clear();
    }

    private void runListeners(List<Listener> listeners, Collection<?> objects) {
        objects.stream().forEach((object) -> {
            runListeners(listeners, object, instance.indexOf(object));
        });
    }
}
