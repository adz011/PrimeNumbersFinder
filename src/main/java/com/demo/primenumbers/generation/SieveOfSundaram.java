package com.demo.primenumbers.generation;

import java.util.ArrayList;
import java.util.List;

public class SieveOfSundaram implements PrimeNumbersGenerator {

    boolean[] arePrimes;
    int sizePrime;
    long timeElapsed;

    @Override
    public List<Integer> findPrimeNumbers(int upperLimit) {
        if (upperLimit < 2) {
            return null;
        } else {
            long beforeExe = System.nanoTime();

            // Measure time
            initialize(upperLimit);
            List<Integer> result = sieveOfSundaram(upperLimit);
            // End of measure

            long afterExe = System.nanoTime();
            timeElapsed = afterExe - beforeExe;
            return result;
        }
    }

    @Override
    public long timeElapsed() {
        return timeElapsed;
    }

    public double getTimeElapsed() {
        return timeElapsed;
    }

    public List<Integer> sieveOfSundaram(int upperLimit) {
        for (int i = 1; i <= sizePrime; i++) {
            for (int j = i; i + j + (2 * i * j) <= sizePrime; j++) {
                arePrimes[i + j + (2 * i * j)] = true;
            }
        }
        List<Integer> primes = new ArrayList<>();
        // The only even prime that needs to be added manually
        if (upperLimit > 2) primes.add(2);

        for (int i = 1; i <= sizePrime; i++) {
            if (!arePrimes[i]) {
                primes.add(2 * i + 1);
            }
        }
        return primes;
    }

    private void initialize(int upperLimit) {
        sizePrime = (upperLimit - 1) / 2;
        arePrimes = new boolean[sizePrime + 1];
    }
}
