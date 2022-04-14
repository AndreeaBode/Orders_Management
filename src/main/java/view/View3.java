package view;

import com.mysql.cj.util.DnsSrv;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class View3 extends JFrame {

    private JLabel orderLabel;
    private JLabel idProdusLabel;
    private JLabel idClientLabel;
    private JLabel cantitateLabel;
    private JLabel idLabel;
    private JTextField idField;
    private JTextField idProdusField;
    private JTextField idClientField;
    private JTextField cantitateField;
    private JButton addButton;
    private JButton deleteButton;
    private JButton viewAllClientButton;
    private JTable table;
    private JPanel panel;
    private JScrollPane scroll;

    /**
     * constructor care realizeaza interfata grafica
     */
    public View3(){
        this.getContentPane().setBackground(new Color(255, 192, 203));
        this.setBounds(100, 100, 766, 492);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.getContentPane().setLayout(null);

        orderLabel = new JLabel("ORDER OPERATIONS");
        orderLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
        orderLabel.setHorizontalAlignment(SwingConstants.CENTER);
        orderLabel.setBounds(220, 24, 298, 39);
        this.getContentPane().add(orderLabel);

        idField = new JTextField();
        idField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        idField.setBounds(138, 97, 120, 33);
        this.getContentPane().add(idField);
        idField.setColumns(10);

        idLabel = new JLabel("ID");
        idLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        idLabel.setBounds(61, 98, 31, 31);
        this.getContentPane().add(idLabel);

         idClientLabel = new JLabel("ID CLIENT");
        idClientLabel.setHorizontalAlignment(SwingConstants.CENTER);
        idClientLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        idClientLabel.setBounds(31, 177, 80, 31);
        this.getContentPane().add(idClientLabel);

        idClientField = new JTextField();
        idClientField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        idClientField.setColumns(10);
        idClientField.setBounds(138, 176, 120, 33);
        this.getContentPane().add(idClientField);

        idProdusField = new JTextField();
        idProdusField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        idProdusField.setColumns(10);
        idProdusField.setBounds(138, 247, 120, 33);
        this.getContentPane().add(idProdusField);

         idProdusLabel = new JLabel("ID PRODUS");
        idProdusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        idProdusLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        idProdusLabel.setBounds(31, 248, 80, 31);
        this.getContentPane().add(idProdusLabel);

         cantitateLabel = new JLabel("CANTITATE");
        cantitateLabel.setHorizontalAlignment(SwingConstants.CENTER);
        cantitateLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        cantitateLabel.setBounds(31, 326, 80, 31);
        this.getContentPane().add(cantitateLabel);

        cantitateField = new JTextField();
        cantitateField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        cantitateField.setColumns(10);
        cantitateField.setBounds(138, 325, 120, 33);
        this.getContentPane().add(cantitateField);

        addButton = new JButton("ADD");
        addButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        addButton.setBounds(289, 89, 120, 49);
        this.getContentPane().add(addButton);

        deleteButton = new JButton("DELETE");
        deleteButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        deleteButton.setBounds(419, 89, 120, 49);
        this.getContentPane().add(deleteButton);

        viewAllClientButton = new JButton("VIEW ALL ORDERS");
        viewAllClientButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        viewAllClientButton.setBounds(549, 88, 193, 49);
        this.getContentPane().add(viewAllClientButton);

      /*  table = new JTable();
        table.setBackground(Color.GRAY);
        table.setBounds(278, 148, 464, 297);

        panel = new JPanel(null);


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

    public int getIdProdusField() {
        return Integer.parseInt(idProdusField.getText());
    }

    /**
     *
     * @param idProdusField
     */

    public void setIdProdusField(int idProdusField) {
        this.idProdusField.setText(String.valueOf(idProdusField));
    }

    /**
     *
     * @return
     */

    public int getIdClientField() {
        return Integer.parseInt(idClientField.getText());
    }

    /**
     *
     * @param idClientField
     */

    public void setIdClientField(int idClientField) {
        this.idClientField.setText(String.valueOf(idClientField));
    }

    /**
     *
     * @return
     */

    public int getCantitateField() {
        return Integer.parseInt(cantitateField.getText());
    }

    /**
     *
     * @param cantitateField
     */
    public void setCantitateField(int cantitateField) {
        this.cantitateField.setText(String.valueOf(cantitateField));
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
    public void addViewAllListener(ActionListener action) {
        viewAllClientButton.addActionListener(action);
    }

    /**
     *
     * @param action
     */
    public void addDeleteListener(ActionListener action) {
        deleteButton.addActionListener(action);}
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
        refresh();
    }


    public void refresh() {
        idField.setText(null);
        idClientField.setText(null);
       idProdusField.setText(null);
        cantitateField.setText(null);
    }
}