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


    private int TotalBricks = 30;
    private int score = 0;

    private Timer time;

    private Map map;

    public Gameplay() {
        int delay = 7;
        time = new Timer(delay, this);
        time.start();
        addKeyListener(this);
        setFocusTraversalKeysEnabled(false);
        setFocusable(true);
        map = new Map(3, 10);
    }


    public void paint (Graphics g) {
        //background
        g.setColor(Color.DARK_GRAY);
        g.fillRect(1,1, 800,600);

        //borders
        g.setColor(Color.yellow);
        g.fillRect(0,0,3,600);
        g.fillRect(0,0,800,3);
        g.fillRect(797,0,3,600);

        //ball
        g.setColor(Color.YELLOW);
        g.fillOval(BallposX,BallposY,20,20);

        //paddle
        g.setColor(Color.green);
        g.fillRect(playerX,550,100,8);

        //map
        map.draw((Graphics2D)g);

        //score
        g.setColor(Color.white);
        g.setFont(new Font("serif", Font.BOLD,25));
        g.drawString(" " +score , 690,30);

        if(TotalBricks <= 0 ){
            play = false;
            BallposX = 0;
            BallposY = 0;
            g.setColor(Color.GREEN);
            g.setFont(new Font("serif", Font.BOLD,25));
            g.drawString("Congratulations, You have won !", 180,300);

            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("Press enter to Restart", 260, 350);
        }

        if (BallposY > 590) {
            play = false;
            g.setColor(Color.RED);
            g.setFont(new Font("serif", Font.BOLD,25));
            g.drawString(" GAME OVER, SCORE'S: " + score, 180,300);

            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("Press enter to Restart", 240, 350);
        }

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
     if(keyEvent.getKeyCode() == VK_ENTER){
         if(!play) {
             play = true;
             BallposX = 400;
             BallposY = 300;
             BallXdir = -1;
             BallposY = -2;
             playerX = 350;
             score = 0;
             TotalBricks = 30;
             map = new Map(3,10);
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

    if(play) {
        if(new Rectangle(BallposX,BallposY,20,20).intersects(new Rectangle(playerX,550, 100,8))){
            BallYdir = -BallYdir;
        }
        A: for(int  i= 0;  i<map.map.length; i++) {
            for(int j =0; j<map.map[0].length; j++){
                if (map.map[i][j] > 0){
                    int brickX = j*map.brickWidth + 50;
                    int brickY = i*map.brickHeight +50;
                    int brickWidth = map.brickWidth;
                    int brickHeight = map.brickHeight;

                    Rectangle brickRect = new Rectangle(brickX,brickY,brickWidth,brickHeight);
                    Rectangle ballRect = new Rectangle(BallposX,BallposY,20,20);


                    if(ballRect.intersects(brickRect)){
                        map.setBrickValue(0,i,j);
                        TotalBricks--;
                        score +=1;
                        if(BallposX + 19 <= brickRect.x || BallposX + 1 >= brickRect.x + brickRect.width){
                            BallXdir = -BallXdir;
                        }else{
                            BallYdir = -BallYdir;
                        }

                        break A;


                    }


                }
            }
        }

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
