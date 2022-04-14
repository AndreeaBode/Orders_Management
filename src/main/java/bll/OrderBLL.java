package bll;

import bll.validators.Validator;
import dao.OrderDAO;
import model.Comanda;

import java.util.ArrayList;
import java.util.List;

public class OrderBLL {
    private OrderDAO orderDAO;
    private List<Validator<Comanda>> validatorList;

    /**
     *
     */
    public OrderBLL()
    {
        validatorList=new ArrayList<Validator<Comanda>>();
        orderDAO=new OrderDAO();
    }

    /**
     * metoda care apeleaza metoda de viziualizare a tuturor comenzilor
     * @return
     */
    public List<Comanda> viewAll()
    {
        return orderDAO.viewAll();
    }

    /**
     * metoda care apeleaza metoda de inserare a unei comenzi
     * @param order
     */
    public void insert(Comanda order)
    {
        orderDAO.insert(order);
    }

    /**
     * metoda care apeleaza metoda de stergere a unei comenzi
     * @param order
     */
    public void delete(Comanda order)
    {
        orderDAO.delete(order.getId());
    }
}
