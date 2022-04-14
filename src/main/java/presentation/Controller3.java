package presentation;

import bll.ClientBLL;
import bll.OrderBLL;
import bll.ProductBLL;
import connection.ConnectionFactory;
import dao.OrderDAO;
import model.Client;
import model.Comanda;
import model.Product;
import start.WriteFile;
import view.View3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Controller3 {
    private View3 view3;
    private ClientBLL clientBLL = new ClientBLL();
    private ProductBLL productBLL = new ProductBLL();
    private JFrame frame = new JFrame();
    private WriteFile writeFile;

    private OrderBLL orderBLL = new OrderBLL();

    /**
     * in acest constructor se creaza ascultatori pentru butoanele de adaugare, stergere, editare si vizualizare a tuturor comenzilor
     */
    public Controller3(View3 view3) throws IOException {

        this.view3 = view3;
        this.writeFile = new WriteFile();
        this.writeFile.create();
        this.view3.addAddListener(new Controller3.AddListener());
        this.view3.addDeleteListener(new Controller3.DeleteListener());
        //this.view3.addViewAllListener(new Controller3.ViewAllListener());
        this.view3.addViewAllListener(e -> {
            String q = "Select * from comanda";
            ResultSet resultSet = null;
            JTable table = new JTable();
            JPanel panel = new JPanel();
            PreparedStatement preparedStatement = null;
            OrderDAO orderDAO = new OrderDAO();
            Connection dbConnection = ConnectionFactory.getConnection();
            try {
                preparedStatement = dbConnection.prepareStatement(q);
                resultSet = preparedStatement.executeQuery();
                table = new JTable(orderDAO.createTable(resultSet));

            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            JScrollPane jsp = new JScrollPane(table);
            frame.getContentPane().add(jsp);

            panel.setLayout(new BorderLayout());
            panel.add(jsp);


            frame.setContentPane(panel);
            frame.setTitle("comanda");

            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(600, 400);
            frame.setVisible(true);
        });
    }


    /**
     * metoda de adaugare a comenzilor in baza de date
     */
    public class AddListener implements ActionListener {
        int id = 0;
        int idClient = 0;
        int idProdus = 0;
        int cantitate = 0;

        @Override
        public void actionPerformed(ActionEvent e) {
            id = view3.getIdField();
            idClient = view3.getIdClientField();
            idProdus = view3.getIdProdusField();
            cantitate = view3.getCantitateField();
            try {

                WriteFile writeFile = new WriteFile();
                List<Client> clientList = new ArrayList<>();
                clientList = clientBLL.viewAll();
                List<Product> productList = new ArrayList<>();
                productList = productBLL.viewAll();

                for (Client c : clientList) {
                    if (c.getId() == idClient) {
                        for (Product p : productList) {
                            if (p.getId() == idProdus) {
                                if (p.getCantitate() > cantitate) {
                                    Comanda order = new Comanda();
                                    order.setId(id);
                                    order.setIdClient(idClient);
                                    order.setIdProdus(idProdus);
                                    order.setCantitate(cantitate);
                                    System.out.println(order.toString());
                                    orderBLL.insert(order);
                                    p.setCantitate(p.getCantitate() - cantitate);
                                    List<String> fields = new ArrayList<>();
                                    fields.add("cantitate");
                                    productBLL.update(p, p.getId(), fields);
                                    writeFile.writeInFile("Comanda cu numarul " + id + " : " + "Clientul cu id " + idClient + " a comandat produsul cu id-ul " + idProdus + "avand o cantitate egala cu " + cantitate + "\n");

                                } else
                                    view3.showMessage("Cantitate indisponibila!");
                                {
                                    throw new Exception("Cantitate indisponibila!");
                                }
                            }
                        }
                    }
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    /**
     * metoda de stergere a comenzilor din baza de date
     */
    public class DeleteListener implements ActionListener {
        int id = 0;
        int idClient = 0;
        int idProdus = 0;
        int cantitate = 0;

        @Override
        public void actionPerformed(ActionEvent e) {
            id = view3.getIdField();
            idClient = view3.getIdClientField();
            idProdus = view3.getIdProdusField();
            cantitate = view3.getCantitateField();
            try {

                Comanda order = new Comanda();
                order.setId(id);
                order.setIdClient(idClient);
                order.setIdProdus(idProdus);
                order.setCantitate(cantitate);
                orderBLL.delete(order);
                try {
                    System.out.println("Comanda a fost stearsa cu succes!");

                } catch (Exception exception) {
                    exception.printStackTrace();
                    view3.showMessage("Bad input!");
                    System.out.println("Comanda nu a putut fi stearsa!");
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }
}
