package com.demo.primenumbers;

import com.demo.primenumbers.generation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GenerationController {
    PrimeNumbersGenerator primeNumbersGenerator;

    @PostMapping("/find")
    public Response findPrimeNumbers(@RequestBody Request request) {
        int upperBoundary = request.getNumber();
        String algorithm = request.getAlgorithm();

        if (algorithm.equals("SieveOfSundaram")) {
            primeNumbersGenerator = new SieveOfSundaram();
        } else if (algorithm.equals("SieveOfEratosthenes")) {
            primeNumbersGenerator = new SieveOfEratosthenes();
        }
        return new Response(primeNumbersGenerator.findPrimeNumbers(upperBoundary), primeNumbersGenerator.timeElapsed());
    }

    public static class Request {
        private int number;
        private String algorithm;

        public String getAlgorithm() {
            return algorithm;
        }

        public void setAlgorithm(String algorithm) {
            this.algorithm = algorithm;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }
    }

    public static class Response {
        private double timeElapsed;
        private List<Integer> listOfPrimeNumbers;

        public Response(List<Integer> listOfPrimeNumbers, double timeElapsed) {
            this.listOfPrimeNumbers = listOfPrimeNumbers;
            this.timeElapsed = timeElapsed;
        }

        public double getTimeElapsed() {
            return timeElapsed;
        }

        public void setTimeElapsed(double timeElapsed) {
            this.timeElapsed = timeElapsed;
        }

        public List<Integer> getListOfPrimeNumbers() {
            return listOfPrimeNumbers;
        }

        public void setListOfPrimeNumbers(List<Integer> listOfPrimeNumbers) {
            this.listOfPrimeNumbers = listOfPrimeNumbers;
        }
    }
}
