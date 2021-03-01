package com.iceapinan.zoo.view;

import com.iceapinan.zoo.controller.ZooController;
import com.iceapinan.zoo.model.Zoo;
import com.iceapinan.zoo.service.Database;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MenuBar extends JMenuBar {
    private final ZooController zooController;

    public MenuBar(JFrame frame, ZooController zooController, ZooView main, Database db) {
        this.zooController = zooController;
        JMenu fileMenu = new JMenu("File");
        JMenuItem newZoo =  new JMenuItem("New");
        JMenuItem save =  new JMenuItem("Save");
        fileMenu.add(newZoo);
        fileMenu.add(save);

        newZoo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zooController.recreateZoo(db);
                System.out.println(zooController.getNumberOfCages());
                main.reload();
            }
        });
        this.add(fileMenu);
        frame.setJMenuBar(this);
        frame.setVisible(true);
    }


    public void readFromFile() {
        try
        {
            FileInputStream fis = new FileInputStream("zoo.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            zooController.setZoo((Zoo)ois.readObject());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        System.out.println(zooController);
    }


    public void writeToFile() {
        try
        {
            FileOutputStream fos = new FileOutputStream("zoo.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(zooController);
            oos.flush();
            oos.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
}
