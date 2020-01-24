package com.company;

import java.awt.*;

public class Map {
    public int map [][];
    public int brickWidth;
    public int brickHeight;
    public Map(int row, int col) {
        map = new int[row][col];
        for(int i = 0;i < map.length;i ++){
            for(int j = 0; j< map[0].length; j++) {
                map[i][j] = 1;
            }
        }



        brickWidth = 700/col;
        brickHeight = 100 / row;
    }
    public void draw(Graphics2D g) {
        for(int i = 0;i < map.length;i ++){
            for(int j = 0; j< map[0].length; j++) {
                if (map[i][j] > 0 ){
                    g.setColor(Color.WHITE);
                    g.fillRect(j * brickWidth + 50, i * brickHeight + 50, brickWidth, brickHeight);
                    g.setStroke(new BasicStroke(3));
                    g.setColor(Color.darkGray);
                    g.drawRect(j * brickWidth + 50, i * brickHeight + 50, brickWidth, brickHeight);
                }
            }
        }
    }
    public void setBrickValue(int value,int row,int col){
        map[row][col] = value ;
    }



}



