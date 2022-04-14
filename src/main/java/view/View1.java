package view;

import presentation.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static java.time.zone.ZoneRulesProvider.refresh;



public class View1 extends JFrame {

    private JLabel clientLabel;
    private JLabel numeLabel;
    private JLabel adresaLabel;
    private JLabel varstaLabel;
    private JLabel emailLabel;
    private JLabel idLabel;
    private JTextField idField;
    private JTextField numeField;
    private JTextField adresaField;
    private JTextField varstaField;
    private JTextField emailField;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton viewAllClientButton;
    private JPanel panel;
    private JTable table;
    private JScrollPane scroll;

    /**
     * constructor care realizeaza interfata grafica
     */

    public View1() {


        this.setBounds(100, 100, 766, 492);
        this.getContentPane().setBackground(new Color(255, 192, 203));
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.getContentPane().setLayout(null);

        clientLabel = new JLabel("CLIENT OPERATIONS");
        clientLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
        clientLabel.setHorizontalAlignment(SwingConstants.CENTER);
        clientLabel.setBounds(220, 24, 298, 39);
        this.getContentPane().add(clientLabel);

        idField = new JTextField();
        idField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        idField.setBounds(113, 97, 120, 33);
        this.getContentPane().add(idField);
        idField.setColumns(10);

        idLabel = new JLabel("ID");
        idLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        idLabel.setBounds(72, 98, 31, 31);
        this.getContentPane().add(idLabel);

        numeLabel = new JLabel("NUME");
        numeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        numeLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        numeLabel.setBounds(31, 159, 80, 31);
        this.getContentPane().add(numeLabel);

        numeField = new JTextField();
        numeField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        numeField.setColumns(10);
        numeField.setBounds(113, 158, 120, 33);
        this.getContentPane().add(numeField);

        adresaField = new JTextField();
        adresaField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        adresaField.setColumns(10);
        adresaField.setBounds(113, 216, 120, 33);
        this.getContentPane().add(adresaField);

        adresaLabel = new JLabel("ADRESA");
        adresaLabel.setHorizontalAlignment(SwingConstants.CENTER);
        adresaLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        adresaLabel.setBounds(23, 217, 80, 31);
        this.getContentPane().add(adresaLabel);

        emailLabel = new JLabel("EMAIL");
        emailLabel.setHorizontalAlignment(SwingConstants.CENTER);
        emailLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        emailLabel.setBounds(31, 272, 80, 31);
        this.getContentPane().add(emailLabel);

        emailField = new JTextField();
        emailField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        emailField.setColumns(10);
        emailField.setBounds(113, 271, 120, 33);
        this.getContentPane().add(emailField);

        varstaField = new JTextField();
        varstaField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        varstaField.setColumns(10);
        varstaField.setBounds(113, 332, 120, 33);
        this.getContentPane().add(varstaField);

        varstaLabel = new JLabel("VARSTA");
        varstaLabel.setHorizontalAlignment(SwingConstants.CENTER);
        varstaLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        varstaLabel.setBounds(31, 333, 80, 31);
        this.getContentPane().add(varstaLabel);


         addButton = new JButton("ADD");
        addButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        addButton.setBounds(307, 89, 120, 49);
        this.getContentPane().add(addButton);

        deleteButton = new JButton("DELETE");
        deleteButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        deleteButton.setBounds(445, 89, 120, 49);
        this.getContentPane().add(deleteButton);

         viewAllClientButton = new JButton("VIEW ALL CLIENT");
        viewAllClientButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        viewAllClientButton.setBounds(65, 379, 193, 49);
        this.getContentPane().add(viewAllClientButton);

        updateButton = new JButton("UPDATE");
        updateButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        updateButton.setBounds(587, 89, 120, 49);
        this.getContentPane().add(updateButton);
/*
        *//*table = new JTable();
        table.setBackground(Color.GRAY);
        table.setBounds(278, 148, 464, 297);*//*

         panel = new JPanel(null);
       // panel.setBounds(278, 148, 464, 297);

        //this.getContentPane().add(panel);

       scroll = new JScrollPane ( table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS );

        scroll.setBounds(278, 148, 464, 297);
        panel.add(scroll);
        this.getContentPane().add(panel);
        this.getContentPane().add(scroll);*/
        this.setVisible(false);
    }
    /**
     *
     * @return
     */
    public int getIdField() {
        return Integer.parseInt(idField.getText());
    }

    /**
     *
     * @param idField
     */
    public void setIdField(int idField) {
        this.idField.setText(String.valueOf(idField));
    }

    /**
     *
     * @return
     */
    public String getNumeField() {
        return numeField.getText();
    }

    /**
     *
     * @param numeField
     */
    public void setNumeField(String numeField) {
        this.numeField.setText(numeField);
    }

    /**
     *
     * @return
     */
    public String getAdresaField() {
        return adresaField.getText();
    }

    /**
     *
     * @param adresaField
     */
    public void setAdresaField(String adresaField) {
        this.adresaField.setText(adresaField);
    }

    /**
     *
     * @return
     */
    public int getVarstaField() {
        return Integer.parseInt(varstaField.getText());
    }

    /**
     *
     * @param varstaField
     */
    public void setVarstaField(int varstaField) {
        this.varstaField.setText(String.valueOf(varstaField));
    }

    /**
     *
     * @return
     */

    public String getEmailField() {
        return emailField.getText();
    }

    /**
     *
     * @param emailField
     */
    public void setEmailField(String emailField) {
        this.emailField.setText(emailField);
    }

    /**
     *
     * @param action
     */
    public void addAddListener(ActionListener action) {
        addButton.addActionListener(action);

    }

    /**
     *
     * @param action
     */
    public void addUpdateListener(ActionListener action) {
        updateButton.addActionListener(action);
    }

    /**
     *
     * @param action
     */
    public void addViewAllListener(ActionListener action) {
        viewAllClientButton.addActionListener(action);
    }

    /**
     *
     * @param action
     */
    public void addDeleteListener(ActionListener action) {
        deleteButton.addActionListener(action);
    }

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }
}