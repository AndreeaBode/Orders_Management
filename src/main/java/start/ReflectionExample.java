package start;

import java.lang.reflect.Field;


public class ReflectionExample {
    /**
     *
     * @param object
     */
    public static void retrieveProporties(Object object) {

        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object value;
            try {
                value = field.get(object);
               // table.addColumn(field.getName());

            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }


        }
    }
}
