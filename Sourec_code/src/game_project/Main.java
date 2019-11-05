/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game_project;

/**
 *
 * @author aon_c
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("====Main is runnig.====");
        DataCore d1 = new DataCore();  // M
        DisplayCore disp1 = new DisplayCore(800, 600, "RPG!! RPG!!"); // V
        ControlCore ctr1 = new ControlCore(); // C
        ServiceCore ser1 = new ServiceCore(); // S
 
        //Fullfill control core
        ctr1.setData(d1);
        ctr1.setDisplay(disp1);
        ctr1.setService(ser1);
        
        disp1.init();
    }
    
}
