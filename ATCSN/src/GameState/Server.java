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
import java.lang.ClassNotFoundException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class implements java Socket server
 *
 * @author pankaj
 *
 */
public class Server {

    private ServerSocket server;
    private InetAddress host;
    private Socket socket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private String ip;
    private int port = 9876;
    private Thread trec, tsend;

    public Server(int port) {
        this.port = port;
    }

    public void init() {

        try {
            server = new ServerSocket(port);
            socket = server.accept();
            System.out.println("IP:" + socket.getInetAddress().getHostAddress());
            System.out.println("Ur IP:" + Inet4Address.getLocalHost().getHostAddress());
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }

        recPipe rpipe = new recPipe();
        sendPipe spipe = new sendPipe();
        trec = new Thread(rpipe);
        tsend = new Thread(spipe);

        tsend.start();
        trec.start();
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
            //read from socket to ObjectInputStream object
            Object obj = ois.readObject();
            return obj;
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println(ex.toString());
        }
        return null;
    }

    public void stop() {
        try {
            trec.stop();
            tsend.stop();
            ois.close();
            oos.close();
            socket.close();
            server.close();
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }

    private class recPipe implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    String got = (String) recive();
                    System.out.println("Client: " + got);
                    if (got.equals("exit")) {
                        stop();
                    }
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    System.out.println(ex.toString());
                }
            }
        }
    }

    private class sendPipe implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    String word = new Scanner(System.in).nextLine();
                    send(word);
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    System.out.println(ex.toString());
                }
            }
        }
    }

    //Just example
    public static void main(String args[]) {
        Server server = new Server(9876);
        server.init();

        //server.stop();
    }
}
