package com.cpb.katerynalevytska.sudoku;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by katerynalevytska on 8/13/16.
 */
public class Game extends BaseAdapter{

    private Context mContext;
    private final Integer mRows = 9, mCols = 9;
    private int numberArray[][] = new int[9][9];
    
    private Resources mRes;
    private ArrayList<String> arrPict;

    int UnblockPositions[] = new int[mRows*mCols];
    int helperArray[][];

    public Game(Context context){
        mContext = context;
        arrPict = new ArrayList<>(mCols*mRows);
        mRes = mContext.getResources();

        createField();
    }

    private void createField() {
        initilizeArray(numberArray);

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
            }
        }

    }

    public boolean checkRepeatedValues(String selectedButton){
        int repeatedX = 0;
        int repeatedY = 0;
        int number = Integer.parseInt(selectedButton.split("n")[1]);

        for(int i = 0; i< mRows; i++){
            for( int j = 0; j< mCols; j++){
                if(helperArray[i][j] == number){

                    repeatedX++;
                }
                if(helperArray[j][i] == number){
                    repeatedY++;
                }
            }

            if (repeatedY>=2 || repeatedX>=2){
                return true;
            }
            repeatedX = 0;
            repeatedY = 0;

        }
        return false;
    }

     public int getRow(int position){

         int row = 1;
         if(position<=8){
             return 0;
         }else{
             while(position>=0 && position<9){
                 row++;
             }
             while(position>=9){
                 position = position-9;
                 row++;
             }
             return row-1;
         }

     }

    public int getCell(int position){
        if (position<=8){
            return position;
        }else{
            return position%9;
        }
    }

    public boolean checkWinner(){
        int i1=0, i2=0, i3=0, i4=0, i5=0, i6=0, i7=0,i8=0, i9=0;
        for(int i = 0; i< mCols; i++){
            for (int j=0 ; j< mRows; j++){
                if (helperArray[i][j] == 1) i1++;
                if (helperArray[i][j] == 2) i2++;
                if (helperArray[i][j] == 3) i3++;
                if (helperArray[i][j] == 4) i4++;
                if (helperArray[i][j] == 5) i5++;
                if (helperArray[i][j] == 6) i6++;
                if (helperArray[i][j] == 7) i7++;
                if (helperArray[i][j] == 8) i8++;
                if (helperArray[i][j] == 9) i9++;
            }
        }

        if (i1 == 9 && i2 == 9 && i3 == 9 && i4 == 9 && i5 == 9 && i6 == 9 && i7 == 9 && i8 == 9 && i9 == 9) {
            return true;
        }

        return false;

    }

}
