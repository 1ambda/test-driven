package com.github.lambda;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Bank {

    Map<Pair, Integer> rates;

    public Bank() {
        rates = new ConcurrentHashMap<Pair, Integer>(); ;
    }

    public void addRate(String from, String to, int rate) {
        rates.put(new Pair(from, to), rate);
    }

    public int rate(String from, String to) {
        if (from.equals(to)) return 1;
        return rates.get(new Pair(from, to));
    }

    private class Pair {
        private String from;
        private String to;

        public Pair(String from, String to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public boolean equals(Object object) {
            // TODO
            Pair that = (Pair) object;
            return (from.equals(that.from) && to.equals(that.to));
        }

        @Override
        public int hashCode() {
            // TODO
            return 0;
        }
    }
}


