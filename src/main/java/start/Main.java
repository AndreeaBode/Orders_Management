package start;

import connection.ConnectionFactory;
import presentation.*;
import view.View1;
import view.View2;
import view.View3;

import java.io.IOException;

public class Main {
    /**
     * metoda main care apeleaza constructorul interfetelor si al controllerelor
     * @param args
     */
    public static void main(String[] args) throws IOException {

        View view=new View();
        View1 view1=new View1();
        View2 view2=new View2();
        View3 view3=new View3();
        WriteFile writeFile=new WriteFile();
        Controller controller=new Controller(view, view1,view2,view3);
       Controller1 controller1=new Controller1(view1);
       Controller2 controller2=new Controller2(view2);
        Controller3 controller3=new Controller3(view3);

    }
}
