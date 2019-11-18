/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game_project;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aon_c
 */
public class GameThread implements Runnable {

    @Override
    public void run() {

        try {
            
            
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            System.out.println(ex.toString());
        }
    }

}
