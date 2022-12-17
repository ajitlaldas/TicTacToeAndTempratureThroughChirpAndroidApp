package com.example.temepraturethroughcricketchirps;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class TicTacToe extends AppCompatActivity {

    Button buttonGoToHome;
    Button buttonRestartGame;
    TableLayout tableTicTacToe;
    static TableRow[] tableTicTacToeRow = new TableRow[3];
    static TextView[][] tableTextView = new TextView[3][3];
    TextView tvGameStatus;
    int i=0;
    int j=0;
    static char [][] ticTacToeCellVal = new char[3][3];
    static int [][] ticTacToeCellIsOccupied = new int[3][3];
    Integer tempID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe);

        buttonGoToHome = findViewById(R.id.buttonGoToHome);
        buttonRestartGame = findViewById(R.id.buttonRestartGame);
        tvGameStatus = findViewById(R.id.tvGameStatus);
        tableTicTacToe = findViewById(R.id.tableTicTacToe);
        tableTicTacToe.animate().translationY(90.0f);
        tableTicTacToe.setLeft(250);



        //tableTicTacToeRow1 = findViewById(R.id.tableTicTacToeRow1);



        /*tableTicTacToe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                tableTicTacToeRow1 = (TableRow) tableTicTacToe.getChildAt(i);
                tableTextView = (TextView) tableTicTacToeRow1.getChildAt(j);
                String toastString;
                tableTextView.setText(" X ");
                toastString = "Text at index: " + i + " is " + tableTextView.getText();
                Toast.makeText(TicTacToe.this, toastString, Toast.LENGTH_SHORT).show();
                j++;
                if (j==3) {
                    j = 0;
                    i++;
                }
                if (i==3) {
                    i=0;
                }



            }
        });*/

        for (i=0;i<3;i++){
            for (j=0;j<3;j++){
                tableTicTacToeRow[i] = (TableRow) tableTicTacToe.getChildAt(i);
                tableTextView[i][j] = (TextView) tableTicTacToeRow[i].getChildAt(j);
                tempID = tableTextView[i][j].getId();

                //tableTextView[i][j].setText(tempID.toString().substring(7,10));
                android.widget.TableRow.LayoutParams layoutParams = (TableRow.LayoutParams) tableTextView[i][j].getLayoutParams();
                layoutParams.leftMargin = 0;
                layoutParams.rightMargin = 0;
                layoutParams.topMargin = 0;
                layoutParams.bottomMargin = 0;


                tableTextView[i][j].setLayoutParams(layoutParams);


                tempID = 2000000 + (i*3) + j;
                tableTextView[i][j].setId(tempID);

                ticTacToeCellVal[i][j] = ' ';
                ticTacToeCellIsOccupied[i][j] = 0;
                tableTextView[i][j].setText(createTicTacToeCellText(ticTacToeCellVal[i][j], i, j));

                tableTextView[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view1) {
                        //String toastString;

                        int findIndex = view1.getId();
                        findIndex = findIndex - 2000000;
                        int xj = (findIndex+3) % 3;
                        int xi = (findIndex-xj)/3;
                        //toastString = "You Clicked on: " + xi + " , " + xj  + " " + view1.getId();
                        //Toast.makeText(TicTacToe.this, toastString, Toast.LENGTH_SHORT).show();

                        playTicTacToe(xi, xj);


                    }
                });
            }
        }

        /*for (i=0; i<3; i++){
            for (j=0; j<3; j++){
                ticTacToeCellVal[i][j] = ' ';
                ticTacToeCellIsOccupied[i][j] = 0;

            }
        }*/

        buttonGoToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                //Intent intent = new Intent(view1.getContext(), MainActivity.class);
                //view1.getContext().startActivity(intent);
                finish();
            }
        });

        buttonRestartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                for (i=0; i<3; i++){
                    for (j=0; j<3; j++){
                        tableTicTacToeRow[i] = (TableRow) tableTicTacToe.getChildAt(i);
                        tableTextView[i][j] = (TextView) tableTicTacToeRow[i].getChildAt(j);
                        ticTacToeCellVal[i][j] = ' ';
                        ticTacToeCellIsOccupied[i][j] = 0;
                        tableTextView[i][j].setText(createTicTacToeCellText(ticTacToeCellVal[i][j], i, j));
                    }
                }
                tvGameStatus.setText("GAME IS ON VATSAL");

            }
        });
    }




    public static String createTicTacToeCellText(char ch, int i, int j){
        if (j<2 && i<2)
            return "       |\n" + " "+ ch + "    |\n" + "_____|_";
        else if (j==2 && i<2)
            return "       \n" + " "+ ch + "    \n" + "_____";
        else if (j<2)
            return "       |\n" + " "+ ch + "    |\n" + "       | ";
        else
            return "       \n" + " "+ ch + "    \n" + "       ";
    }

    public int playTicTacToe(int xi, int xj){

        int computerMovePending = 1;
        int i, j;
        int allCellsOccupied = 1;
        int columnWon = 0;
        int rowWon = 0;
        int diagonalWon = 0;

        allCellsOccupied = checkIfAllCellOccupied();
        if(ticTacToeCellIsOccupied[xi][xj] == 1 && allCellsOccupied ==0){
            String toastString = "This cell is filled, click on another one";
            Toast.makeText(TicTacToe.this, toastString, Toast.LENGTH_SHORT).show();
        }

        else if (ticTacToeCellIsOccupied[xi][xj] == 0 && allCellsOccupied ==0){
            ticTacToeCellIsOccupied[xi][xj] = 1;
            ticTacToeCellVal[xi][xj] = 'O';
            tableTextView[xi][xj].setText(createTicTacToeCellText(ticTacToeCellVal[xi][xj], xi, xj));

            checkIfWon();

            allCellsOccupied = checkIfAllCellOccupied();
            while(computerMovePending == 1 && allCellsOccupied ==0){
                i = (int) Math.floor((Math.random()*3));
                j= (int) Math.floor((Math.random()*3));
                if (ticTacToeCellIsOccupied[i][j] == 0){
                    ticTacToeCellIsOccupied[i][j] =1;
                    ticTacToeCellVal[i][j] = 'X';
                    tableTextView[i][j].setText(createTicTacToeCellText(ticTacToeCellVal[i][j], i, j));
                    computerMovePending = 0;
                }
                checkIfWon();

            }

        }

        else if( allCellsOccupied ==1){
            tvGameStatus.setText("GAME OVER");
        }


        return 1;
    }

    public int checkIfRowWon(){
        int i,j;
        int wonFlag = 0;
        for (i=0; i<3; i++) {
            if ((ticTacToeCellVal[i][0] == ticTacToeCellVal[i][1]) && (ticTacToeCellVal[i][2] == ticTacToeCellVal[i][1]) && (ticTacToeCellVal[i][0] == 'O')) {
                wonFlag = 1;
                break;
            }
            else if ((ticTacToeCellVal[i][0] == ticTacToeCellVal[i][1]) && (ticTacToeCellVal[i][2] == ticTacToeCellVal[i][1]) && (ticTacToeCellVal[i][0] == 'X')) {
                wonFlag = 2;
                break;
            }

        }
        if (wonFlag == 1 || wonFlag == 2){
            for (i=0; i<3; i++){
                for (j=0; j<3; j++){
                    ticTacToeCellIsOccupied[i][j] = 1;
                }
            }
        }

        if (wonFlag ==1){
            tvGameStatus.setText("YOU WON, I LOST");
        }
        else if (wonFlag ==2){
            tvGameStatus.setText("I WON, YOU LOST");

        }

        return wonFlag;
    }

    public  int checkIfColumnWon(){
        int i,j,wonFlag = 0;
        for (i=0; i<3; i++) {
            if ((ticTacToeCellVal[0][i] == ticTacToeCellVal[1][i]) && (ticTacToeCellVal[2][i] == ticTacToeCellVal[1][i]) && (ticTacToeCellVal[0][i] == 'O'))
            {
                wonFlag = 1;
                break;
            }
            else if ((ticTacToeCellVal[0][i] == ticTacToeCellVal[1][i]) && (ticTacToeCellVal[2][i] == ticTacToeCellVal[1][i]) && (ticTacToeCellVal[0][i] == 'X'))
            {
                wonFlag = 2;
                break;
            }

        }
        if (wonFlag == 1 || wonFlag == 2){
            for (i=0; i<3; i++){
                for (j=0; j<3; j++){
                    ticTacToeCellIsOccupied[i][j] = 1;
                }
            }
        }

        if (wonFlag ==1){
            tvGameStatus.setText("YOU WON, I LOST");
        }
        else if (wonFlag ==2){
            tvGameStatus.setText("I WON, YOU LOST");
        }

        return wonFlag;
    }

    public int checkIfDiagonalnWon(){
        int i,j,wonFlag = 0;
        if ((ticTacToeCellVal[0][0] == ticTacToeCellVal[1][1]) && (ticTacToeCellVal[2][2] == ticTacToeCellVal[1][1]) && (ticTacToeCellVal[1][1] == 'O'))
            wonFlag = 1;
        else if ((ticTacToeCellVal[0][0] == ticTacToeCellVal[1][1]) && (ticTacToeCellVal[2][2] == ticTacToeCellVal[1][1]) && (ticTacToeCellVal[1][1] == 'X'))
            wonFlag = 2;

        else if ((ticTacToeCellVal[0][2] == ticTacToeCellVal[1][1]) && (ticTacToeCellVal[2][0] == ticTacToeCellVal[1][1]) && (ticTacToeCellVal[1][1] == 'O'))
            wonFlag = 1;
        else if ((ticTacToeCellVal[0][2] == ticTacToeCellVal[1][1]) && (ticTacToeCellVal[2][0] == ticTacToeCellVal[1][1]) && (ticTacToeCellVal[1][1] == 'X'))
            wonFlag = 2;
        else
            wonFlag = 0;
        if (wonFlag == 1 || wonFlag == 2){
            for (i=0; i<3; i++){
                for (j=0; j<3; j++){
                    ticTacToeCellIsOccupied[i][j] = 1;
                }
            }
        }

        if (wonFlag ==1){
            tvGameStatus.setText("YOU WON, I LOST");
        }
        else if (wonFlag ==2){
            tvGameStatus.setText("I WON, YOU LOST");
        }

        return wonFlag;
    }

    public void checkIfWon(){
        checkIfColumnWon();
        checkIfRowWon();
        checkIfDiagonalnWon();
    }

    public static int checkIfAllCellOccupied(){
        int i,j, allCellOccupied;
        allCellOccupied = 1;
        for (i=0; i<3; i++){
            for (j=0; j<3; j++){
                if (ticTacToeCellIsOccupied[i][j]==0) {
                    allCellOccupied = 0;
                    return allCellOccupied;
                }

            }
        }
        return allCellOccupied;
    }
}