package java8sort.list;

import java.util.ArrayList;
import java.util.List;
import java8sort.listener.Listener;

/**
 *
 * @author devpragmatic
 */
public abstract class ObservableBase<T> {

    protected final List<Listener> addListeners;

    protected final List<Listener> removeListeners;

    protected final List<Listener> setListeners;

    public ObservableBase() {
        this.addListeners = new ArrayList<>();
        this.removeListeners = new ArrayList<>();
        this.setListeners = new ArrayList<>();
    }

    public ObservableBase(List<Listener> addListeners, List<Listener> removeListeners, List<Listener> setListeners) {
        this.addListeners = addListeners;
        this.removeListeners = removeListeners;
        this.setListeners = setListeners;
    }

    protected void runListeners(List<Listener> listeners, Object object, Integer i) {
        listeners.stream().forEach((Listener listener) -> {
            listener.run(object, i);
        });
    }
}
