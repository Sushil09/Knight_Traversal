package com.sjsushil09;

import javafx.util.Pair;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ChessBoard {
    private int rows;
    private int columns;
    private int[][] board;

    public ChessBoard(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.board = new int[rows][columns];
    }

    public List<Pair> knight_plays(int[] source, int[] destination) {
        List<Pair> paths=new ArrayList<>();
        int shortestPath=shortestPath(paths,source,destination);
        paths.add(new Pair(destination[0],destination[1])); //added the destination pair
        System.out.println("The shortest distance is "+shortestPath);
        return paths;
    }

    private int shortestPath(List<Pair> paths, int[] source, int[] destination) {
        int[] xPositions={-2,-2,-1,1,2,2,1,-1};
        int[] yPositions={-1,1,2,2,1,-1,-2,-2};
        int xCoordinateSource=source[0];
        int yCoordinateSource=source[1];
        int xCoordinateDestination=destination[0];
        int yCoordinateDestination=destination[1];
        int minSteps=0; //Min. steps req. by knight to reach destination

        Queue<Pair> q=new LinkedList<>();
        q.add(new Pair(xCoordinateSource,yCoordinateSource));
        board[xCoordinateSource][yCoordinateSource]=-1;
        while (!q.isEmpty()) {
            CordinateDistancePair cordinateDistancePair=new CordinateDistancePair(0,0, Integer.MAX_VALUE);
            minSteps++;
            int size=q.size();
            for (int x = 0; x < size; x++) {
                Pair p = q.poll();
                int xx = (Integer) p.getKey();
                int yy = (Integer) p.getValue();
                double distanceFromDestination=calculateDistances(xx,xCoordinateDestination,yy,yCoordinateDestination);
                if(distanceFromDestination<cordinateDistancePair.distance){
                    cordinateDistancePair=new CordinateDistancePair(xx,yy,yCoordinateDestination);
                }
                if (xx == xCoordinateDestination && yy == yCoordinateDestination)
                    return minSteps-1;
                for (int i = 0; i < 8; i++) {
                    if (isValid(board, xx + xPositions[i], yy + yPositions[i], rows, columns)) {
                        board[xx + xPositions[i]][yy + yPositions[i]] = -1;
                        q.add(new Pair(xx + xPositions[i], yy + yPositions[i]));
                    }
                }
            }
            paths.add(new Pair(cordinateDistancePair.x,cordinateDistancePair.y));
        }
        return minSteps-1;
    }

    private boolean isValid(int[][]board,int i,int j,int N,int M){
        return i<N && j<M && i>=0 && j>=0 && board[i][j]==0;
    }

    private double calculateDistances(int xi,int xj, int yi,int yj){
        return Math.sqrt((Math.pow(xj-xi,2))+Math.pow(yj-yi,2));
    }

    static class CordinateDistancePair{
        int x;
        int y;
        double distance;

        public CordinateDistancePair(int x, int y, double distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }


}
