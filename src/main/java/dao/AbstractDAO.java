package dao;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
@param <T>
 */
public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private final Class<T> type;

    @SuppressWarnings("unchecked")
    /**
     *
     */
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }

    /**
     *
     */
    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }

    /**
     * metoda de
     * @return
     */
    public List<T> findAll(int id) {
        // TODO:
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        //String query = "SELECT * FROM " + type.getSimpleName()+ "WHERE id =?";
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll" + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * metoda de gasire a clientului, a produselor sau a comenzilor cu un anumit id
     * @param id
     * @return
     */
    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * metoda de creare a obiectelor
     * @param resultSet
     * @return
     */
    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T) ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * metoda de creare a interogarii insert
     * @return
     */
    private String createInsertQuery() {
       /* INSERT INTO table_name (column1, column2, column3, ...)
        VALUES (value1, value2, value3, ...);*/
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO "+ type.getSimpleName().toLowerCase());
        sb.append("(");
        int parameter = 0;
        for (Field field1 : type.getDeclaredFields()) {
            if (parameter < type.getDeclaredFields().length - 1) {
                sb.append(field1.getName() + ",");
            } else sb.append(field1.getName());
            parameter++;
        }
        sb.append(")");
        sb.append("\n");
        sb.append("VALUES (");
        int parameter2 = 0;
        while (parameter2 < parameter) {
            if (parameter2 < parameter - 1) // daca avem mai multi parametri
                sb.append("? , ");
            else
                sb.append("? )");
            parameter2++;
        }
        System.out.println(sb.toString());
        return sb.toString();
    }

    /**
     * metoda care preia field-urile necesare pentru realizarea operatiei de inserare
     * @param t
     */
    public void insert(T t) {
        // TODO:
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createInsertQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            int parameter = 1;
            for (Field fields : type.getDeclaredFields()) {
                fields.setAccessible(true);
              statement.setObject(parameter,fields.get(t));
                parameter++;
            }
            statement.execute();
        } catch (SQLException | IllegalAccessException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
        } finally {
           // ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * metoda care implementeaza interogarea delete
     * @param field
     * @return
     */
    private String deleteQuery(String field) {
        // DELETE FROM table_name WHERE condition;
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM " + type.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }

    /**
     * metoda care preia field-urile necesare pentru realizarea operatiei de stergere si
     * apeleaza metoda care construieste interogarea
     * @param id
     */
    public void delete(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = deleteQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.execute();

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * metoda care implementeaza interogarea de update
     * @param field
     * @param fields1
     * @return
     */
    private String updateQuery(String field, List<String> fields1) {
       /* UPDATE table_name
        SET column1 = value1, column2 = value2, ...
        WHERE condition;*/
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE " + type.getSimpleName().toLowerCase());
        sb.append("\n");
        sb.append("SET ");
        for (int i = 0; i < fields1.size(); i++) {
            if (i < fields1.size() - 1) {
                sb.append(fields1.get(i) + " =?, ");
            } else
                sb.append(fields1.get(i) + " =?");
        }
        sb.append("\n");
        sb.append("WHERE " + field + " =?");
        return sb.toString();
    }

    /**
     * metoda care preia field-urile necesare pentru realizarea operatiei de update
     * @param id
     * @param field1
     */
    public void update(T t, int id, List<String> field1) {
        // TODO:
        Connection connection = null;
        PreparedStatement statement = null;
        String query = updateQuery("id", field1);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            int p = 1;
            for (Field fields : type.getDeclaredFields()) {
                for (int i = 0; i < field1.size(); i++)
                    if (fields.getName().compareTo(field1.get(i)) == 0) {
                        fields.setAccessible(true);
                        statement.setObject(p,fields.get(t));
                        System.out.println(fields.get(t));
                        System.out.println(field1);
                        p++;
                    }
                statement.setInt(p, id);
            }
            statement.execute();
        } catch (SQLException | IllegalAccessException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:update" + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * metoda care afiseaza tot tabelul
     * @return
     */
    public List<T> viewAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM " + type.getSimpleName().toLowerCase();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:viewAll" + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     *
     * @param resultSet
     * @return
     */
    public DefaultTableModel createTable(ResultSet resultSet){
        ArrayList<T> objects = (ArrayList<T>) createObjects(resultSet);
        Vector<String> columns = new Vector<>();
        for(Field field : type.getDeclaredFields()){
            columns.add(field.getName());
        }
        Vector<Vector<String>> lines = new Vector<>();
        for(T o : objects){
            Vector<String> line = new Vector<>();
            for(Field field : type.getDeclaredFields()){
                String fieldName = field.getName();
                PropertyDescriptor propertyDescriptor = null;
                try {
                    propertyDescriptor = new PropertyDescriptor(fieldName, type);
                } catch (IntrospectionException e) {
                    e.printStackTrace();
                }
                Method method = propertyDescriptor.getReadMethod();
                try {
                    line.add(method.invoke(o).toString());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
            lines.add(line);
        }
        return new DefaultTableModel(lines,columns);
    }
}