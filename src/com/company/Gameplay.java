package com.company ;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

import static java.awt.event.KeyEvent.*;

public class Gameplay extends JPanel implements KeyListener, ActionListener{
    private boolean play = false;

    private int playerX = 350;

    private int BallposX = 400;
    private int BallposY = 300;
    private int BallXdir = -1;
    private int BallYdir = -2;

    private int TotalBricks = 15;

    private Timer time;
    private int delay = 7;

    private Map map;

    public Gameplay() {
        time = new Timer( delay, this);
        time.start();
        addKeyListener(this);
        setFocusTraversalKeysEnabled(false);
        setFocusable(true);
        map = new Map(3, 7);
    }


    public void paint (Graphics g) {
        //background
        g.setColor(Color.DARK_GRAY);
        g.fillRect(1,1, 800,600);

        //borders
        g.setColor(Color.yellow);
        g.fillRect(0,0,3,600);
        g.fillRect(0,0,800,3);
        g.fillRect(799,0,3,600);

        //ball
        g.setColor(Color.red);
        g.fillOval(BallposX,BallposY,20,20);

        //paddle
        g.setColor(Color.green);
        g.fillRect(playerX,550,100,8);

        //map
        map.draw((Graphics2D)g);

        g.dispose();
    }




    @Override
    public void keyTyped(KeyEvent keyEvent ) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent ) {
    if(keyEvent.getKeyCode() == KeyEvent.VK_LEFT) {
        if (playerX <= 0) {
            playerX = 0;
        } else {
            moveLeft();
        }
    }
     if(keyEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
         if(playerX >= 700) {
             playerX = 700;
         }else {
             moveRight();
         }
     }


    }
    public void moveRight() {
        play = true;
        playerX += 20;
    }
    public void moveLeft() {
        play = true;
        playerX -=20;
    }


    @Override
    public void keyReleased(KeyEvent keyEvent ) {

    }


    @Override
    public void actionPerformed(ActionEvent actionEvent ) {
    time.start();
    if(play) {
        BallposX += BallXdir;
        BallposY += BallYdir;
        if (BallposX < 0 ){
            BallXdir = -BallXdir;

        }
        if (BallposY < 0){
            BallYdir = -BallYdir;

        }
        if(BallposX > 780){
            BallXdir = -BallXdir;
        }


    }
    repaint();
    }
}
