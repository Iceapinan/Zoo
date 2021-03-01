package com.iceapinan.zoo.view;

import com.iceapinan.zoo.controller.ZooController;
import com.iceapinan.zoo.model.*;
import com.iceapinan.zoo.service.Database;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class ZooView extends JFrame {
    private JPanel panel1;
    private JButton walkButton;
    private JTextPane zooTextPane;
    private JRadioButton elephantRadioButton;
    private JRadioButton horseRadioButton;
    private JRadioButton sheepRadioButton;
    private JRadioButton tigerRadioButton;
    private JRadioButton giraffeRadioButton;
    private JButton putButton;
    private JTextPane countPane;
    private JTextPane uniqueAnimalsPane;
    private JLabel uniqueAnimalLabel;
    private JButton buyACageButton;
    private JList cagesList;
    private ButtonGroup buttonGroup;

    private final ZooController zooController;

    private String selectedCageValue;
    private int selectedCageIndex;

    Database db;

    public ZooView() {
        db = new Database();

        // Use macOS Menu
        System.setProperty("apple.laf.useScreenMenuBar", "true");
        System.setProperty("apple.awt.application.name", "Zoo");

        List<Cage> restoreCages = restorePreviousData();
            if (restoreCages.size() > 0) {
                System.out.println("Here");

                zooController = new ZooController();
                zooController.setZoo(new Zoo(restoreCages, restoreCages.size()));
            }
            else {
                zooController = new ZooController();
                // In the beginning God created the zoo and determined the number of cages (which is arbitrary)
                zooController.generateZoo(db);
            }

        JFrame frame = new JFrame("Zoo by IceApinan");
        frame.setResizable(false);
        frame.setPreferredSize(new Dimension(500, 750));
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menu = new MenuBar(frame, zooController, this, db);
        frame.setJMenuBar(menu);

        initComponents();
        setContent(zooController);

        frame.pack();
        frame.setVisible(true);
    }

    private List<Cage> restorePreviousData() {
        final List<Cage> cages = db.listAllCages();
        // Cast Animal to each type
        List<Cage> restoreCages = new ArrayList<Cage>();
        for (Cage cage: cages) {
            if (cage.animal != null) {
                switch (cage.animal.family) {
                    case "Elephant (Elephantidae)" -> restoreCages.add(new Cage(cage.id, new Elephant(), cage.numberOfAnimalInside));
                    case "Giraffe (Giraffidae)" -> restoreCages.add(new Cage(cage.id, new Giraffe(), cage.numberOfAnimalInside));
                    case "Horse (Equidae)" -> restoreCages.add(new Cage(cage.id, new Horse(), cage.numberOfAnimalInside));
                    case "Sheep (Bovidae)" -> restoreCages.add(new Cage(cage.id, new Sheep(), cage.numberOfAnimalInside));
                    case "Tiger (Felidae)" -> restoreCages.add(new Cage(cage.id, new Tiger(), cage.numberOfAnimalInside));
                }
            } else restoreCages.add(new Cage(false));
        }
        return restoreCages;
    }

    private void initComponents() {
        buttonGroup = new ButtonGroup();
        buttonGroup.add(elephantRadioButton);
        elephantRadioButton.setActionCommand("Elephant");
        buttonGroup.add(tigerRadioButton);
        tigerRadioButton.setActionCommand("Tiger");
        buttonGroup.add(sheepRadioButton);
        sheepRadioButton.setActionCommand("Sheep");
        buttonGroup.add(horseRadioButton);
        horseRadioButton.setActionCommand("Horse");
        buttonGroup.add(giraffeRadioButton);
        giraffeRadioButton.setActionCommand("Giraffe");
        addActionListeners();
    }

    private void addActionListeners() {
        cagesList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() ) {
                JList source = (JList)e.getSource();
                if (source.getSelectedValue() != null) {
                    selectedCageIndex = source.getSelectedIndex();
                    selectedCageValue = source.getSelectedValue().toString();
                    System.out.println(source.getSelectedValue());
                }
            }
        });

        putButton.addActionListener(e -> {
            if (selectedCageValue.equals("Empty!")) {
                try {
                    switch (buttonGroup.getSelection().getActionCommand()) {
                        case "Elephant" -> zooController.getCages().get(selectedCageIndex).putAnimalToCage(new Elephant(), db);
                        case "Tiger" -> zooController.getCages().get(selectedCageIndex).putAnimalToCage(new Tiger(), db);
                        case "Sheep" -> zooController.getCages().get(selectedCageIndex).putAnimalToCage(new Sheep(), db);
                        case "Horse" -> zooController.getCages().get(selectedCageIndex).putAnimalToCage(new Horse(), db);
                        case "Giraffe" -> zooController.getCages().get(selectedCageIndex).putAnimalToCage(new Giraffe(), db);
                        default -> System.out.println("Default: " + selectedCageIndex);
                    }
                    java.awt.EventQueue.invokeLater(() -> {
                        setCagesList();
                        uniqueAnimalsPane.setText("");
                        zooTextPane.setText("");
                        countPane.setText("");
                        setContent(zooController);
                        SwingUtilities.updateComponentTreeUI(this);
                    });
                } catch(Exception exception) {
                    System.out.println(exception);
                }
            }
        });

        walkButton.addActionListener(e -> {
            zooTextPane.setText("");
            writeToTextPane(zooTextPane, zooController.walk());
        });

        buyACageButton.addActionListener(e -> {
            try {
                zooController.buyCage(db);
                reload();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
    }

    private static void writeToTextPane(JTextPane pane, Iterable<String> it) {
        Document doc = pane.getDocument();
        for (String s : it) {
            try {
                doc.insertString(doc.getLength(), s + "\n", null);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        }
    }

    private void setCagesList() {
        DefaultListModel modelAddList = new DefaultListModel();
        modelAddList.addAll(zooController.getCagesToDisplay());
        cagesList.setModel(modelAddList);
        cagesList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION); // Single Interval
    }

    private void setContent(ZooController zooController) {
        writeToTextPane(uniqueAnimalsPane, zooController.getUnique());

        setCagesList();

        ArrayList<String> carnivoresAndHerbivores = new ArrayList<>();
        carnivoresAndHerbivores.add("Carnivores: " + zooController.countCarnivores().toString());
        carnivoresAndHerbivores.add("Herbivores: " + zooController.countHerbivores().toString());
        writeToTextPane(countPane, carnivoresAndHerbivores);
    }


    public void reload() {
        java.awt.EventQueue.invokeLater(() -> {
            uniqueAnimalsPane.setText("");
            zooTextPane.setText("");
            cagesList.setModel(new DefaultListModel());
            countPane.setText("");
            setContent(zooController);
            SwingUtilities.updateComponentTreeUI(this);
        });
    }
}