/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game_project;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author aon_c
 *
 * This is Service class. 
 * Use for handle communicate from/to external source.
 */
public class ServiceCore {

    public void init() {
    }

    public Map<String, Charactor> loadChar() {
        Map<String, Charactor> temp = new HashMap<>();
        File f = new File("src/resource/data/char_data/char_data_1.dat");
        if (f.exists()) {
            try {
                FileInputStream fin = new FileInputStream(f);
                ObjectInputStream oin = new ObjectInputStream(fin);
                Object readed = oin.readObject();
                if(readed != null && readed instanceof HashMap){
                    temp.putAll((HashMap)readed);
                }
                oin.close();
                fin.close();

            } catch (IOException | ClassNotFoundException ex) {
                System.err.println(ex.toString());
            }

        } else {
            System.out.println("File not exist! Can't load.");
        }
        return temp;
    }

    public void saveChar(Map<String, Charactor> temp) {
        File f = new File("src/resource/data/char_data/char_data_1.dat");
        if (f.exists()) {
            try {
                FileOutputStream fout = new FileOutputStream(f);
                ObjectOutput oout = new ObjectOutputStream(fout);
                oout.writeObject(temp);
                oout.close();
                fout.close();
            } catch (IOException ex) {
                System.err.println(ex.toString());
            }
        } else {
            System.out.println("File not exist! Can't save.");
        }
    }
}
