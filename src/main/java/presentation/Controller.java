package presentation;

import view.View1;
import view.View2;
import view.View3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    private View view;
    private View1 view1;
    private final View2 view2;
    private View3 view3;

    /**
     * in acest constuctor se creaza ascultatori pentru butoanele de adaugare client, produs si de plasare comanda
     */

 public Controller(View view,View1 view1, View2 view2, View3 view3) {
        this.view = view;
        this.view1=view1;
        this.view2=view2;
        this.view3=view3;
        this.view.addClientListener(new ClientListener());
        this.view.addProductListener(new ProductListener());
        this.view.addOrderListener(new OrderListener());


    }

    /**
     * metoda care seteaza view1 pe true
     * la apasarea butonului va aparea interfata pe care se pot aplica operatii pentru clienti
     */
    class ClientListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
            view1.setVisible(true);
                view3.setVisible(false);
                view2.setVisible(false);
        }catch(Exception exception)
            {
                exception.printStackTrace();
            }
        }
    }
    /**
     * metoda care seteaza view2 pe true
     * la apasarea butonului va aparea interfata pe care se pot aplica operatii pentru produse
     */
    class ProductListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                view2.setVisible(true);
                view1.setVisible(false);
                view3.setVisible(false);
            }catch(Exception exception)
            {
                exception.printStackTrace();
            }
        }
    }
    /**
     * metoda care seteaza view3 pe true
     * la apasarea butonului va aparea interfata pe care se pot aplica operatii pentru comenzi
     */
    class OrderListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                view3.setVisible(true);
                view1.setVisible(false);
                view2.setVisible(false);
            }catch(Exception exception)
            {
                exception.printStackTrace();
            }
        }
    }

}
