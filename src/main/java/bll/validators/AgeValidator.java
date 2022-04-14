package bll.validators;

import model.Client;


public class AgeValidator implements Validator<Client> {
    private static final int MIN_AGE = 7;
    private static final int MAX_AGE = 100;

    /**
     * metoda care verifica daca clientul are varsta din intervalul celor acceptate
     *
     * @param t
     */
    public void validate(Client t) {

        if (t.getAge() < MIN_AGE || t.getAge() > MAX_AGE) {
            throw new IllegalArgumentException("The Student Age limit is not respected!");
        }

    }
}

