package main.habitivity.observables;

public interface IObserver<T> {
    void onNext(T next);
}
