package bll;

import bll.validators.AgeValidator;
import bll.validators.EmailValidator;
import bll.validators.Validator;
import dao.ClientDAO;
import model.Client;

import javax.swing.*;
import javax.xml.transform.Result;
import java.util.ArrayList;
import java.util.List;

public class ClientBLL {
    private ClientDAO clientDAO;
    private List<Validator<Client>> validatorList;

    /**
     *
     */
    public ClientBLL() {
      validatorList=new ArrayList<Validator<Client>>();
      validatorList.add(new EmailValidator());
      validatorList.add(new AgeValidator());
      clientDAO=new ClientDAO();
    }

    /**
     * metoda care returneaza rezultatul apelului metodei de gasire a unui client dupa id
     * @param id
     * @return
     * @throws NoSuchFieldException
     */
    public Client findById(int id) throws NoSuchFieldException {
        Client client=clientDAO.findById(id);
        if(client ==null)
            throw new NoSuchFieldException("No such field exception!");
        return client;
    }

    /**
     * metoda care returneaza rezultatul apelului metodei de vizualizare a tuturor clientilor
     * @return
     */
    public ArrayList<Client> viewAll()
    {
        return (ArrayList<Client>) clientDAO.viewAll();
    }
    public void insert(Client client)
    {
        clientDAO.insert(client);
    }

    /**
     * ,etoda care apeleaza metoda de stergere a unui client
     * @param client
     */
    public void delete(Client client)
    {
        clientDAO.delete(client.getId());
    }

    /**
     * metoda care apeleaza metoda de editare a cleintilor
     * @param client
     * @param id
     * @param fileds
     */
    public void update(Client client, int id, List<String>fileds)
    {
        clientDAO.update(client, id, fileds);
    }
  /*  public JTable createTable( Client client)
    {
        return clientDAO.createTable(client);
*/

   /* public  List<Object> createObject(Result resultSet){
        return clientDAO.createObjects();
    }*/
}

