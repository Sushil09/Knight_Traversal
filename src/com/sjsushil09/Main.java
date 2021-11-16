package com.sjsushil09;

import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class Main {
    static BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception{
        int rows= Integer.parseInt(bufferedReader.readLine());
        int column= Integer.parseInt(bufferedReader.readLine());
        int[] source=new int[2];
        int[] destination=new int[2];
        String[] src=bufferedReader.readLine().split(" ");
        String[] des=bufferedReader.readLine().split(" ");
        for(int i=0;i<src.length;i++){
            source[i]=Integer.parseInt(src[i]);
            destination[i]=Integer.parseInt(des[i]);
        }
        ChessBoard game =new ChessBoard(rows,column);
        List<Pair> paths =game.knight_plays(source,destination);

        //print the output
        System.out.print("[");
        for(int i=0;i<paths.size();i++){
            Pair path=paths.get(i);
            System.out.print("[");
            System.out.print(path.getKey()+",");
            System.out.print(path.getValue());
            System.out.print("]");
        }
        System.out.print("]");
    }
}

