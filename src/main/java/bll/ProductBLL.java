package bll;

import bll.validators.Validator;
import dao.ProductDAO;
import model.Product;

import java.util.ArrayList;
import java.util.List;


public class ProductBLL {
    private  ProductDAO productDAO;
    private List<Validator<Product>>validatorList;

    /**
     *
     */

    public ProductBLL()
    {
        validatorList=new ArrayList<Validator<Product>>();
        productDAO=new ProductDAO();
    }

    /**
     * metoda care apeleaza operatia de gasire a unui produs dupa id
     * @param id
     * @return
     * @throws NoSuchFieldException
     */
    public Product findById(int id) throws NoSuchFieldException {
        Product product=productDAO.findById(id);
        if(product==null)
            throw new NoSuchFieldException("No such field exception!");
    return  product;
    }

    /**
     *
     * @return
     */
    public ArrayList<Product> viewAll()
    {
        return (ArrayList<Product>) productDAO.viewAll();
    }

    /**
     * metoda care apeleaza inserarea pe un produs
     * @param product
     */
    public void insert(Product product)
    {
        productDAO.insert(product);
    }

    /**
     * metoda care apeleaza stergerea pe un produs
     * @param product
     */
    public void delete(Product product)
    {
        productDAO.delete(product.getId());
    }

    /**
     * metoda care apeleaza update-ul pe un produs
     * @param product
     * @param id
     * @param fields
     */
    public void update(Product product,int id, List<String>fields)
    {
        productDAO.update(product,id,fields);
    }
}
