package com.demo.primenumbers.generation;

import java.util.ArrayList;
import java.util.List;

public class SieveOfEratosthenes implements PrimeNumbersGenerator {

    int[] intArray;
    boolean[] arePrimes;
    long timeElapsed;

    public SieveOfEratosthenes() {

    }

    @Override
    public List<Integer> findPrimeNumbers(int upperLimit) {
        if (upperLimit < 2) {
            return null;
        } else {
            long beforeExe = System.nanoTime();

            // Measure time
            initializeArrays(upperLimit);
            sieveOfEratosthenes();
            List<Integer> result = retrievePrimes();
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

    public long getTimeElapsed() {
        return timeElapsed;
    }

    private void initializeArrays(int upperLimit) {
        intArray = new int[upperLimit - 1];
        arePrimes = new boolean[upperLimit - 1];

        for (int i = 0; i < upperLimit - 1; i++) {
            intArray[i] = i + 2; // since 0 and 1 are not primes
            arePrimes[i] = true;
        }
    }

    private List<Integer> retrievePrimes() {
        List<Integer> primes = new ArrayList<>();
        for (int i = 0; i < intArray.length; i++) {
            if (arePrimes[i]) {
                primes.add(intArray[i]);
            }
        }
        return primes;

    }

    private int enumeratePrimes(int divisor) {
        int nextDivisor = -1;
        for (int i = divisor - 1; i < intArray.length; i++) {
            if (intArray[i] % divisor == 0) {
                arePrimes[i] = false;
            } else {
                if (nextDivisor == -1) {
                    nextDivisor = intArray[i];
                }
            }
        }
        return nextDivisor;
    }

    private void sieveOfEratosthenes() {
        int currentDivisor = intArray[0];
        for (int i = currentDivisor - 2; i <= (int) Math.sqrt(intArray.length); i++) {
            int nextDivisor = enumeratePrimes(currentDivisor);
            if (nextDivisor != -1) {
                currentDivisor = nextDivisor;
            }
        }
    }
}