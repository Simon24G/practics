package practic.producerconsumer.genericImp.functionalinterface;

/**
 * Created by Игорь on 26.03.2018.
 */
@FunctionalInterface
public interface RunnableCreater<T> {
    T create(int i);
}

