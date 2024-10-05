package com.demo.primenumbers.generation;

import java.util.List;

public interface PrimeNumbersGenerator {

    List<Integer> findPrimeNumbers(int upperLimit);

    long timeElapsed();

}
