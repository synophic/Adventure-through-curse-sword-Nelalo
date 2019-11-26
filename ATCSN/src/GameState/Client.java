/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameState;

/**
 *
 * @author aon_c
 */
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class implements java socket client
 *
 * @author pankaj
 *
 */
public class Client {

    private InetAddress host;
    private Socket socket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private String ip;
    private int port;
    private boolean forsend;

    public Client(String ip, int port, boolean forwat) {
        this.ip = ip;
        this.port = port;
        this.forsend = forwat;
    }

    public void init() {
        try {
            socket = new Socket(ip, port);
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }

    public void send(Object obj) {
        try {
            oos.writeObject(obj);
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }

    }

    public Object recive() {
        try {
            Object obj = ois.readObject();
            return obj;
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println(ex.toString());
        }
        return null;
    }

    public void stop() {
        try {
            ois.close();
            oos.close();
            socket.close();
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }

    public static void main(String[] args) {
        Client client = new Client("127.0.0.1", 9876, true);  //127.0.0.1 172.16.10.72
        client.init();

        String word;
        do {
            System.out.print("Client: ");
            System.out.println("Server: " + (String) client.recive());
            word = new Scanner(System.in).nextLine();
            client.send(word);
        } while (!word.equals("exit"));

        client.stop();
    }

}
