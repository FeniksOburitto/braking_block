package com.company;

import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {
	JFrame object = new JFrame();
	Gameplay gameplay = new Gameplay();
        object.setVisible(true);
        object.setResizable(false);
        object.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        object.setTitle("Breaking Ball");
        object.setBounds(10,10,800,600);
        object.add(gameplay);
    }
}
