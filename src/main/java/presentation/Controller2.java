package presentation;

import bll.ProductBLL;
import connection.ConnectionFactory;
import dao.ProductDAO;
import model.Product;
import view.View2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Controller2 {
    private View2 view2;
    private ProductBLL productBLL = new ProductBLL();
    private JFrame frame =new JFrame();

    /**
     * in acest constrcutor se creaza ascultatori pentru butoanele de adaugare, stergere, editare si vizualizare a tuturor produs
     *
     * @param view2
     */

    public Controller2(View2 view2) {
        this.view2 = view2;

        this.view2.addAddListener(new Controller2.AddListener());
        this.view2.addDeleteListener(new Controller2.DeleteListener());
        this.view2.addUpdateListener(new Controller2.UpdateListener());
        this.view2.addViewAllListener(e -> {
            String q = "Select * from product";
            ResultSet resultSet = null;
            JTable table=new JTable();
            JPanel panel = new JPanel();
            PreparedStatement preparedStatement = null;
            ProductDAO productDAO=new ProductDAO();
            Connection dbConnection = ConnectionFactory.getConnection();
            try {
                preparedStatement = dbConnection.prepareStatement(q);
                resultSet = preparedStatement.executeQuery();
                table = new JTable(productDAO.createTable(resultSet));

            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            JScrollPane jScrollPane = new JScrollPane(table);
            frame.getContentPane().add(jScrollPane);

            panel.setLayout(new BorderLayout());
            panel.add(jScrollPane);


            frame.setContentPane(panel);
            frame.setTitle("product");

            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(600, 400);
            frame.setVisible(true);
        });
    }

    /**
     * metoda de adaugare a produselor in baza de date
     */
    public class AddListener implements ActionListener {
        int id = 0;
        String nume = "";
        int pret = 0;
        int cantitate = 0;

        @Override
        public void actionPerformed(ActionEvent e) {
            id = view2.getIdField();
            nume = view2.getNumeField();
            pret = view2.getPretField();
            cantitate = view2.getCantitateField();
            try {

                Product product = new Product();
                product.setId(id);
                product.setNume(nume);
                product.setPret(pret);
                product.setCantitate(cantitate);
                productBLL.insert(product);
                System.out.println("Produsul a fost inserat cu succes!");

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    /**
     * metoda de stergere a produselor din baza de date
     */
    public class DeleteListener implements ActionListener {
        int id = 0;
        String nume = "";
        int pret = 0;
        int cantitate = 0;

        @Override
        public void actionPerformed(ActionEvent e) {
            id = view2.getIdField();
            nume = view2.getNumeField();
            pret = view2.getPretField();
            cantitate = view2.getCantitateField();
            try {

                Product product = new Product();
                product.setId(id);
                product.setNume(nume);
                product.setPret(pret);
                product.setCantitate(cantitate);
                productBLL.delete(product);
                System.out.println("Produsul a fost sters cu succes!");

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    public class UpdateListener implements ActionListener {

        int id = 0;
        String nume = "";
        int cantitate = 0;
        int pret = 0;
        String id1 = "";
        String age = "";

        @Override
        public void actionPerformed(ActionEvent e) {
            id = view2.getIdField();
            nume = view2.getNumeField();
            cantitate = view2.getCantitateField();
            pret = view2.getPretField();
            try {
                List<String> fields = new ArrayList<>();
                List<Product> productList = productBLL.viewAll();

                Product product=new Product();

                for (Product p : productList) {
                    if (p.getId() == id) {
                        product.setId(id);
                        fields.add("id");
                        product.setNume(nume);
                        fields.add("nume");
                        product.setPret(pret);
                        fields.add("pret");
                        product.setCantitate(cantitate);
                        System.out.println("Cantitate "+ product.getCantitate());
                        fields.add("cantitate");
                        product.setPret(pret);
                        fields.add("pret");

                        try {
                            productBLL.update(product, id, fields);
                            System.out.println("Produsul s-a actualizat cu succes!");
                        } catch (Exception exception) {
                            exception.printStackTrace();
                            System.out.println("Nu s-a putut actualiza!");
                        }
                    }
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

}
