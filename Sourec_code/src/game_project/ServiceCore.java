/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game_project;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author aon_c
 *
 * This class is Service class. Use for handle process, external access.
 */
public class ServiceCore {

    public void init() {
    }

    public Map<String, Charactor> loadChar() {
        Map<String, Charactor> temp = new HashMap<>();
        Properties prop = new Properties();
        File f = new File("src/resource/data/char_data/char_data_1.dat");
        if (f.exists()) {
            try {
                FileInputStream fin = new FileInputStream(f);
                prop.load(fin);
                fin.close();

            } catch (IOException ex) {
                System.err.println(ex.toString());
            }
            prop.stringPropertyNames().forEach((key) -> {
                temp.put(key, (Charactor) prop.get(key));
            });

        } else {
            System.out.println("Load char error!!");
        }
        return temp;
    }

    public void saveChar(Map<String, Charactor> temp) {
        Properties prop = new Properties();
        File f = new File("src/resource/data/char_data/char_data_1.dat");
        if (f.exists()) {
            temp.entrySet().forEach((entry) -> {
                prop.put(entry.getKey(), entry.getValue());
            });
            try {
                FileOutputStream fout = new FileOutputStream(f);
                prop.store(fout, null);
                fout.close();
            } catch (IOException ex) {
                System.err.println(ex.toString());
            }

        } else {
            System.out.println("Save char error!!");
        }
    }
}
