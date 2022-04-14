package bll.validators;

import model.Comanda;


public class CantitateValidator {
    private static final int MIN_ORDER = 7;
    private static final int MAX_ORDER = 100;

    /**
     * metoda care verifica daca comenda plasata nu e prea mica sau prea mare
     * @param t
     */
    public void validate(Comanda t) {

        if (t.getCantitate() < MIN_ORDER || t.getCantitate() > MAX_ORDER) {
            throw new IllegalArgumentException("The Student Age limit is not respected!");
        }
    }
}

