package com.iceapinan.zoo;

import com.iceapinan.zoo.view.ZooView;

public class Main {
    private Main() {}

    public static void main(String[] args) throws Exception {
        java.awt.EventQueue.invokeLater(() -> new ZooView());
    }
}
