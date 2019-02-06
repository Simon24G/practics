package practic.producerconsumer.genericImp.functionalinterface;

import practic.producerconsumer.genericImp.regim.Regim;

/**
 * Created by Игорь on 26.03.2018.
 */

@FunctionalInterface
public interface RunnableExecute<T> {
    Regim execute(T element);
}
