//***********************************************************************************************
// File Name: PlayScreen
// Authors: Christopher Farfan (101067074) & Esaac Ahn (100836038)
// Date: March 11th, 2018
// Assignment 1 submission.
//
// Description:
// A simple slots machine game using fake money, user can win depending on what shows up
//
//  Revision History:
//  Can be found on https://github.com/bluCraze/SlotMachine/commits/master
//***********************************************************************************************

package com.example.esaacahn.slotmachine;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;

public class PlayScreen extends Activity {

    public static int[] slotImages = {R.drawable.coin,R.drawable.apple, R.drawable.blank, R.drawable.blueberries, R.drawable.cherry, R.drawable.lemon, R.drawable.luckyseven, R.drawable.orange, R.drawable.strawberry};

    public ImageView leftImage, middleImage, rightImage;

    public TextView playerMoneyTV, winningsTV, jackpotTV, turnTV, winNumberTV, lossNumberTV, messageBoardTV, winRatioTV;


    private int playerMoney = 1000;
    private int winnings = 0;
    private int jackpot = 5000;
    private int turn = 0;
    private int playerBet = 50;
    private float winNumber = 0;
    private float lossNumber = 0;
    private float winRatio = 0;
    private boolean betHasBeenMade = false;


    private int blanks = 0;
    private int apples = 0;
    private int blueberries = 0;
    private int cherries = 0;
    private int lemons = 0;
    private int sevens = 0;
    private int oranges = 0;
    private int strawberries = 0;



    /* Utility function to reset all fruit tallies */
    public void resetFruitTally(){
        blanks = 0;
        apples = 0;
        blueberries = 0;
        cherries = 0;
        lemons = 0;
        sevens = 0;
        oranges = 0;
        strawberries = 0;
    }

    /* Utility function to reset the player stats */
    public void resetAll() {
        playerMoney = 1000;
        winnings = 0;
        jackpot = 5000;
        turn = 0;
        playerBet = 0;
        winNumber = 0;
        lossNumber = 0;
        winRatio = 0;

        playerMoneyTV.setText("$" + playerMoney);
        winNumberTV.setText((int)winNumber + "");
        winningsTV.setText(winnings + "");
        lossNumberTV.setText((int)lossNumber + "");
        String formattedWinRatio = String.format("%.02f", winRatio);
        winRatioTV.setText(formattedWinRatio + "%");
        turnTV.setText(turn + "");
        jackpotTV.setText("$5000");
    }

    /* Check to see if the player won the jackpot */
    public void checkJackpot(){
        /* compare two random values */
        int jackPotTry = (int)Math.floor((Math.random() * 51) + 1);
        int jackPotWin = (int)Math.floor((Math.random() * 51) + 1);
        if (jackPotTry == jackPotWin) {
            messageBoardTV.setText("You won the $" + jackpot + "Jackpot!");
            playerMoney += jackpot;
            jackpot = 1000;
        }

    }

    /* Utility function to show a win message and increase player money */
    public void showWinMessage() {
        winNumber++;
        winRatio = (winNumber / turn) * 100;
        String formattedWinRatio = String.format("%.02f", winRatio);
        playerMoney += winnings;
        playerMoneyTV.setText("$" + playerMoney + "");
        winNumberTV.setText((int)winNumber + "");
        winningsTV.setText(winnings + "");
        messageBoardTV.setText("You Won: $" + winnings + "!");
        winRatioTV.setText(formattedWinRatio + "%");
        resetFruitTally();
        checkJackpot();
    }

    /* Utility function to show a loss message and reduce player money */
    public void showLossMessage() {
        lossNumber++;
        winRatio = (winNumber / turn) * 100;
        String formattedWinRatio = String.format("%.02f", winRatio);
        lossNumberTV.setText((int)lossNumber + "");
        messageBoardTV.setText("You Lost, Better Luck Next Time!");
        winRatioTV.setText(formattedWinRatio + "%");
        resetFruitTally();
        jackpot += (playerBet / 10);
        jackpotTV.setText(jackpot + "");
    }



    //The main game function, determines what each column will be
    private void Spin(ImageView image){
        //Generates a random number from 1 - 65 (inclusive)
        int randInt = (int) Math.floor((Math.random() * 65) + 1);
        if (randInt >= 1 && randInt <= 27){ // 41.5% probability
            image.setImageResource(slotImages[2]); //Blank
            blanks++;
        } else if (randInt >= 28 && randInt <= 37) { //15.4%
            image.setImageResource(slotImages[1]); //Apples
            apples++;
        } else if (randInt >= 38 && randInt <= 46) { //13.8%
            image.setImageResource(slotImages[3]); //Blueberries
            blueberries++;
        } else if (randInt >= 47 && randInt <= 54) { //12.3%
            image.setImageResource(slotImages[7]); //Orange
            oranges++;
        } else if (randInt >= 55 && randInt <= 59) { //7.7%
            image.setImageResource(slotImages[4]); //Cherries
            cherries++;
        } else if (randInt >= 60 && randInt <= 62) { //4.6%
            image.setImageResource(slotImages[5]); //Lemons
            cherries++;
        } else if (randInt >= 63 && randInt <= 64) { //3.1%
            image.setImageResource(slotImages[8]); //Strawberries
            strawberries++;
        } else {                                         //1.5%
            image.setImageResource(slotImages[6]); //Lucky Seven
            sevens++;
        }


    }
    //Determines how much money the player has won. Taken from the example professor provided.
    public void determineWinnings(){
        turn++;
        turnTV.setText(turn + "");
        if (blanks == 0)
        {
            if (apples == 3) {
                winnings = playerBet * 10;
            }
            else if(blueberries == 3) {
                winnings = playerBet * 20;
            }
            else if (oranges == 3) {
                winnings = playerBet * 30;
            }
            else if (cherries == 3) {
                winnings = playerBet * 40;
            }
            else if (lemons == 3) {
                winnings = playerBet * 50;
            }
            else if (strawberries == 3) {
                winnings = playerBet * 75;
            }
            else if (sevens == 3) {
                winnings = playerBet * 100;
            }
            else if (apples == 2) {
                winnings = playerBet * 2;
            }
            else if (blueberries == 2) {
                winnings = playerBet * 2;
            }
            else if (oranges == 2) {
                winnings = playerBet * 3;
            }
            else if (cherries == 2) {
                winnings = playerBet * 4;
            }
            else if (lemons == 2) {
                winnings = playerBet * 5;
            }
            else if (strawberries == 2) {
                winnings = playerBet * 10;
            }
            else if (sevens == 2) {
                winnings = playerBet * 20;
            }
            else if (sevens == 1) {
                winnings = playerBet * 5;
            }
            else {
                winnings = playerBet * 1;
            }
            showWinMessage();
        }
        else
        {
            showLossMessage();
        }

    }

    //Runs function when reset button is clicked
    public void resetButtonOnClick(View view){
        resetAll();
        messageBoardTV.setText("Game has been reset, good luck!");
    }
    //Runs when single button is clicked
    public void singleButtonOnClick(View view){
        if (playerMoney >= 50 && !betHasBeenMade){
            betHasBeenMade = true;
            playerBet = 50;
            playerMoney -= playerBet;
            playerMoneyTV.setText("$"+ playerMoney + "");
            messageBoardTV.setText("A single bet of $50 has been placed.");
        } else if (betHasBeenMade){
            messageBoardTV.setText("A bet has already been placed.");
        } else
            messageBoardTV.setText("Sorry you don't have enough cash.");

    }
    //Runs when max button is clicked
    public void maxButtonOnClick(View view){
        if (playerMoney >= 500) {
            betHasBeenMade = true;
            playerBet = 500;
            playerMoney -= playerBet;
            messageBoardTV.setText("A max bet of $500 has been placed.");
            playerMoneyTV.setText("$"+ playerMoney + "");
        } else if (betHasBeenMade){
            messageBoardTV.setText("A bet has already been placed.");
        } else
            messageBoardTV.setText("Sorry you don't have enough cash.");
    }

    //Runs function when spin button is clicked
    public void spinButtonOnClick(View view){
        //Button button = (Button) view;

        if (betHasBeenMade){
            Spin(leftImage);
            Spin(middleImage);
            Spin(rightImage);
            determineWinnings();
            betHasBeenMade = false;
        }else{
            messageBoardTV.setText("A bet has not been placed. Please place a bet.");
        }



    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_screen);


        leftImage = findViewById(R.id.slotImg1);
        leftImage.setImageResource(slotImages[0]);
        middleImage = findViewById(R.id.slotImg2);
        middleImage.setImageResource(slotImages[0]);
        rightImage = findViewById(R.id.slotImg3);
        rightImage.setImageResource(slotImages[0]);


        playerMoneyTV = findViewById(R.id.playerMoney);
        winningsTV = findViewById(R.id.lastWinAmount);
        jackpotTV = findViewById(R.id.jackPot);
        turnTV = findViewById(R.id.playerTurn);
        winNumberTV = findViewById(R.id.playerWins);
        lossNumberTV = findViewById(R.id.playerLosses);
        messageBoardTV = findViewById(R.id.playerMessageBoard);
        winRatioTV = findViewById(R.id.playerWinRatio);

    }


}
