package presentation;

import bll.ClientBLL;
import connection.ConnectionFactory;
import dao.ClientDAO;
import model.Client;
import start.ReflectionExample;
import view.View1;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Controller1 {
    private View1 view1;
    private final ClientBLL clientBLL = new ClientBLL();
    private ReflectionExample reflectionExample;
    public JFrame frame = new JFrame();

    /**
     * in acest constrcutor se creaza ascultatori pentru butoanele de adaugare, stergere, editare si vizualizare a tuturor clientilor
     *
     * @param view1
     */

    public Controller1(View1 view1) {
        this.view1 = view1;

        this.view1.addAddListener(new Controller1.AddListener());
        this.view1.addDeleteListener(new Controller1.DeleteListener());
        this.view1.addUpdateListener(new Controller1.UpdateListener());
        // this.view1.addViewAllListener(new Controller1.ViewAllListener());

        this.view1.addViewAllListener(e -> {
            String q = "Select * from client";
            ResultSet resultSet = null;
            JTable table=new JTable();
            JPanel panel = new JPanel();
            PreparedStatement preparedStatement = null;
            ClientDAO clientDAO=new ClientDAO();
            Connection dbConnection = ConnectionFactory.getConnection();
            try {
                preparedStatement = dbConnection.prepareStatement(q);
                resultSet = preparedStatement.executeQuery();
                table = new JTable(clientDAO.createTable(resultSet));

            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane = new JScrollPane ( table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS );

            scrollPane.setBounds(278, 148, 464, 297);
            frame.getContentPane().add(scrollPane);

            panel.setLayout(new BorderLayout());
            panel.add(scrollPane);


            frame.setContentPane(panel);
            frame.setTitle("clients");

            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(600, 400);
            frame.setVisible(true);
        });
    }


    /**
     * metoda de adaugare a clientilor in baza de date
     */
    public class AddListener implements ActionListener {

        int id = 0;
        String name = "";
        String address = "";
        String email = "";
        int age = 0;

        @Override
        public void actionPerformed(ActionEvent e) {
            id = view1.getIdField();
            name = view1.getNumeField();
            address = view1.getAdresaField();
            email = view1.getEmailField();
            age = view1.getVarstaField();
            System.out.println("VARSTA " + age);
            try {

                Client client = new Client();
                client.setId(id);
                client.setName(name);
                client.setAddress(address);
                client.setEmail(email);
                client.setAge(age);
                try {
                    clientBLL.insert(client);
                    System.out.println("Clientul a fost inserat cu succes!");

                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }

        }
    }


    /**
     * metoda de stergere a clientilor in baza de date
     */
    public class DeleteListener implements ActionListener {

        int id = 0;
        String name = "";
        String address = "";
        String email = "";
        int age = 0;

        @Override
        public void actionPerformed(ActionEvent e) {
            id = view1.getIdField();
            name = view1.getNumeField();
            address = view1.getAdresaField();
            email = view1.getEmailField();
            age = view1.getVarstaField();
            try {

                Client client = new Client();
                client.setId(id);
                client.setName(name);
                client.setAddress(address);
                client.setEmail(email);
                client.setAge(age);
                clientBLL.delete(client);
                System.out.println("Clientul a fost sters cu succes!");

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    public class UpdateListener implements ActionListener {

        int id = 0;
        String name = "";
        String address = "";
        String email = "";
        int age = 0;

        @Override
        public void actionPerformed(ActionEvent e) {
            id = view1.getIdField();
            System.out.println("ID " + id);
            name = view1.getNumeField();
            address = view1.getAdresaField();
            email = view1.getEmailField();
            age = view1.getVarstaField();
            try {
                List<String> fields1 = new ArrayList<>();
                List<Client> clientList = clientBLL.viewAll();

                Client client = new Client();

                for (Client c : clientList) {
                    if (c.getId() == id) {
                        client.setId(id);
                        fields1.add("id");
                        client.setName(name);
                        fields1.add("name");
                        client.setAddress(address);
                        fields1.add("address");
                        client.setEmail(email);
                        fields1.add("email");
                        client.setAge(age);
                        fields1.add("age");

                        try {
                            clientBLL.update(client, id, fields1);
                            System.out.println("Clientul a fost actualizat cu succes!");
                        } catch (Exception exception) {
                            exception.printStackTrace();
                            System.out.println("Nu s-a putut adauga!");
                        }
                    }
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }




}
