package com.cpb.katerynalevytska.sudoku;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by katerynalevytska on 8/13/16.
 */
public class Game extends BaseAdapter{

    GridView field;


    private Context mContext;
    private final Integer mRows = 9, mCols = 9;
    private int numberArray[][] = new int[9][9];
    
    private Resources mRes; // Ресурсы приложения
    private ArrayList<String> arrPict; // массив картинок

    private ArrayList<String> arrStatus;
    int UnblockPositions[] = new int[mRows*mCols];
    int helperArray[][];

    public Game(Context context){

        mContext = context;

        arrPict = new ArrayList<String>(mCols*mRows);
        arrStatus = new ArrayList<String>();
        
        mRes = mContext.getResources();

        createField();

    }

    private void createField() {
        initilizeArray(numberArray);

        //shiftNumbers(3, 1, numberArray);
        shiftNumbers(3, 1);
        shiftNumbers(6, 2);

        shiftNumbers(1, 3);
        shiftNumbers(4, 4);
        shiftNumbers(7, 5);

        shiftNumbers(2, 6);
        shiftNumbers(5, 7);
        shiftNumbers(8, 8);

        transposingArray(numberArray);
        shakeRowInsideArea();
        transposingArray(numberArray);

        for(int i = 0; i <mRows; i++){
            for(int j = 0; j<mCols; j++){
                arrPict.add("n"+numberArray[i][j]);
            }

        }
        helperArray = numberArray;
        Random r = new Random();
        int i = 0;
        while (i<70) {
            int i1 = r.nextInt(80);
            arrPict.set(i1, "nempty");
            UnblockPositions[i] = i1;
            helperArray[getRow(i1)][getCell(i1)] = -1;
            i++;
        }




    }


    private void initilizeArray(int array[][]){
        for(int i = 0; i < mRows; i++){
            for(int j = 0; j< mCols; j++){
                numberArray[i][j] = j+1;
            }
        }
    }

    private void shiftNumbers(int count, int row){
        int index;
        for(int j = 0; j< mCols; j++){
                index = (j+count)%9+1;
                //if(index>=9) index = j+count-9;
                numberArray[row][j] = index;
            }


    }

    private void transposingArray(int array[][]){
        for (int i = 0; i < mRows; i++) {
            for (int j = 0; j < i; j++) {
                int tmp = array[i][j];
                array[i][j] = array[j][i];
                array[j][i] = tmp;
            }
        }
    }

    private void shakeRowInsideArea(){

        int i = 0;
        do{
            int tempArray[] = numberArray[i];
            int tempArray2[] = numberArray[i+1];

            numberArray[i] = numberArray[i+2];
            numberArray[i+1] = tempArray;
            numberArray[i+2] = tempArray2;
            System.out.println("I:" + i);
            i=i+3;
        }while(i < mRows);
    }


    @Override
    public int getCount() {
        return mCols*mRows;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ImageView imageView;

        if(view == null)
            imageView = new ImageView(mContext);
        else
            imageView = (ImageView)view;

        Integer drawableId = mRes.getIdentifier(arrPict.get(position), "drawable", mContext.getPackageName());
        imageView.setImageResource(drawableId);

        return imageView;
    }

    public void setNumber(int position, String selectedButton) {
        for(int i = 0; i< UnblockPositions.length; i++){
            if(UnblockPositions[i] == position) {
                arrPict.set(position, selectedButton);
                helperArray[getRow(position)][getCell(position)] = Integer.parseInt(selectedButton.split("n")[1]);
                notifyDataSetChanged();
                checkRepeatedValues(selectedButton);
            }
        }

    }

    public void checkRepeatedValues(String selectedButton){
        int repeatedX = 0;
        int repeatedY = 0;
        int number = Integer.parseInt(selectedButton.split("n")[1]);

        for(int i = 0; i< mRows; i++){
            for( int j = 0; j< mCols; j++){
                if(helperArray[i][j] == number){
                    repeatedX++;
                }
                if(helperArray[j][i] == number){
                    repeatedX++;
                }
            }
            if (repeatedY>2 || repeatedX>2){
                System.out.println("Number: " + number);
                System.out.println("You have repeated values");
            }
            repeatedX = 0;
            repeatedY = 0;

        }



    }

     public int getRow(int position){

         int row = 0;
         if(position<=8){
             return 0;
         }else{
             while(position>=0){
                 position = position-9;
                 row++;
             }
            if(row>8) row = 8;
             return row;
         }

     }

    public int getCell(int position){
        if (position<=8){
            return position;
        }else{
            return position%9;
        }
    }

    public void checkWinner(){
        int i1=0, i2=0, i3=0, i4=0, i5=0, i6=0, i7=0,i8=0, i9=0;
        for(int i = 0; i< mCols; i++){
            for (int j=0 ; j< mRows; j++){

            }
        }
    }

}
