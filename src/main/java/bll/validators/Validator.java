package bll.validators;

    public interface Validator<T> {
        /**
         *
         * @param t
         */
        public void validate(T t);
    }

