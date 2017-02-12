package com.example.arya.project4;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class TicTacToe extends AppCompatActivity {
    //initialization of the variables
    Button start;
    TextView result_text;
    TextView status;
    int setBoardCounter=9;
    Button one; Button two; Button three; Button four; Button five;
    Button six; Button seven; Button eight; Button nine;
    public static final int SET_UI_FROM_PLAYER_ONE = 0 ;
    public static final int HANDLER_OF_THREAD_ONE =1;
    public static final int SET_UI_FROM_PLAYER_TWO =2;
    public static final int HANDLER_OF_THREAD_TWO =3;
    ArrayList<Integer> choices= new ArrayList<>();
    //initializing the two worker threads
    Thread thread1;
    Thread thread2;
    //initializing the handlers of the worker threads
    Handler PlayerTwoHandler;
    Handler PlayerOneHandler;
    //creating a global lock for the "synchronization" blocks
    static final Object lock = new Object();

    //creating the UI Handler
    Handler UIHandler =new Handler(){
        public  void handleMessage (Message msg){
            synchronized (lock) {
                int choice = msg.what;
                switch (choice) {
                    //if the message is received from the Worker 1
                    case SET_UI_FROM_PLAYER_ONE: {
                        int placeValue = msg.arg1;
                        //calling the function to set up the board
                        setBoard(placeValue, "player1");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        //checking the status of the game , if the 'X' has matched or not
                        //if matched, it will enter this block and update the text view and break
                        if ((one.getText() == "X" && two.getText() == "X" && three.getText() == "X")
                                || (three.getText() == "X" && six.getText() == "X" && nine.getText() == "X")
                                || (nine.getText() == "X" && eight.getText() == "X" && seven.getText() == "X")
                                || (one.getText() == "X" && four.getText() == "X" && seven.getText() == "X")
                                || (three.getText() == "X" && five.getText() == "X" && seven.getText() == "X")
                                || (one.getText() == "X" && five.getText() == "X" && nine.getText() == "X")
                                || (two.getText() == "X" && five.getText() == "X" && eight.getText() == "X")
                                || (six.getText() == "X" && five.getText() == "X" && four.getText() == "X")) {
                            result_text.setText("The game is won by Player 1 (X)");
                            status.setText("the game has ended");
                            break;
                            //checking the status of the game , if the 'O' has matched or not
                            //if matched, it will enter this block and update the text view and break
                        } else if ((one.getText() == "O" && two.getText() == "O" && three.getText() == "O")
                                || (three.getText() == "O" && six.getText() == "O" && nine.getText() == "O")
                                || (nine.getText() == "O" && eight.getText() == "O" && seven.getText() == "O")
                                || (one.getText() == "O" && four.getText() == "O" && seven.getText() == "O")
                                || (three.getText() == "O" && five.getText() == "O" && seven.getText() == "O")
                                || (one.getText() == "O" && five.getText() == "O" && nine.getText() == "O")
                                || (two.getText() == "O" && five.getText() == "O" && eight.getText() == "O")
                                || (six.getText() == "O" && five.getText() == "O" && four.getText() == "O")) {
                            result_text.setText("The game is won by Player 2 (O)");
                            status.setText("the game has ended");
                            break;
                            //checking the status of the game , if the 'O' or 'X' hasn't matched, then its a tie
                        } else if (choices.isEmpty()){
                            result_text.setText("The game has ended in a tie");
                            status.setText("the game has ended");
                        } else {
                            //the game will continue  and the UI will be update and UI Handler will send the message to the Worker 2 handler
                            result_text.setText("Game on!!!");
                            Message message = PlayerTwoHandler.obtainMessage(HANDLER_OF_THREAD_TWO);
                            PlayerTwoHandler.sendMessage(message);
                            break;
                        }
                    }

                    //if the message is received from the Worker 2
                    case SET_UI_FROM_PLAYER_TWO: {
                        int placeValue = msg.arg1;
                        //calling the function to set up the board
                        setBoard(placeValue, "player2");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        //checking the status of the game , if the 'X' has matched or not
                        //if matched, it will enter this block and update the text view and break
                        if ((one.getText() == "X" && two.getText() == "X" && three.getText() == "X")
                                || (three.getText() == "X" && six.getText() == "X" && nine.getText() == "X")
                                || (nine.getText() == "X" && eight.getText() == "X" && seven.getText() == "X")
                                || (one.getText() == "X" && four.getText() == "X" && seven.getText() == "X")
                                || (three.getText() == "X" && five.getText() == "X" && seven.getText() == "X")
                                || (one.getText() == "X" && five.getText() == "X" && nine.getText() == "X")
                                || (two.getText() == "X" && five.getText() == "X" && eight.getText() == "X")
                                || (six.getText() == "X" && five.getText() == "X" && four.getText() == "X")) {
                            result_text.setText("The game is won by Player 1 (X)");
                            status.setText("the game has ended");
                            break;
                            //checking the status of the game , if the 'O' has matched or not
                            //if matched, it will enter this block and update the text view and break
                        } else if ((one.getText() == "O" && two.getText() == "O" && three.getText() == "O")
                                || (three.getText() == "O" && six.getText() == "O" && nine.getText() == "O")
                                || (nine.getText() == "O" && eight.getText() == "O" && seven.getText() == "O")
                                || (one.getText() == "O" && four.getText() == "O" && seven.getText() == "O")
                                || (three.getText() == "O" && five.getText() == "O" && seven.getText() == "O")
                                || (one.getText() == "O" && five.getText() == "O" && nine.getText() == "O")
                                || (two.getText() == "O" && five.getText() == "O" && eight.getText() == "O")
                                || (six.getText() == "O" && five.getText() == "O" && four.getText() == "O")) {
                            result_text.setText("The game is won by Player 2 (O)");
                            status.setText("the game has ended");
                            break;
                            //checking the status of the game , if the 'O' or 'X' hasn't matched, then its a tie
                        } else if (choices.isEmpty()){
                            result_text.setText("The game has ended in a tie");
                            status.setText("the game has ended");
                        } else {
                            //the game will continue  and the UI will be update and UI Handler will send the message to the Worker 1 handler
                            result_text.setText("Game on!!!");
                            Message message = PlayerOneHandler.obtainMessage(HANDLER_OF_THREAD_ONE);
                            PlayerOneHandler.sendMessage(message);
                            break;
                        }
                    }
                }
            }

        }
    };

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start= (Button) findViewById(R.id.start);
        one = (Button) findViewById(R.id.one);
        two = (Button) findViewById(R.id.two);
        three = (Button) findViewById(R.id.three);
        four = (Button) findViewById(R.id.four);
        five = (Button) findViewById(R.id.five);
        six = (Button) findViewById(R.id.six);
        seven = (Button) findViewById(R.id.seven);
        eight = (Button) findViewById(R.id.eight);
        nine = (Button) findViewById(R.id.nine);
        result_text= (TextView) findViewById(R.id.result_text);
        status= (TextView) findViewById(R.id.dialogue);
        //populating the array-list with the values needed to set the board
        choices.add(1);choices.add(2);choices.add(3);
        choices.add(4);choices.add(5);choices.add(6);
        choices.add(7);choices.add(8);choices.add(9);
    }

    //the button which on-click with spawn two worker threads
    int clickCount=1;
    public void GameOn(View view) throws InterruptedException {
        thread1 = new Thread(new Player1());
        thread2 = new Thread(new Player2());
        //on-click for the first
        if (clickCount==1) {
            clickCount++;
            thread1.start();
            thread2.start();
            //if the button is clicked again, then it will call a separate function which will restart the same threads from the beginning
        }else if(clickCount>1){
            clickCount++;
            //calling the function to refresh the board
            refreshAndRerun();
        }
    }

    //the function which sets the entire board to fresh state and restarts the new worker threads
    public void refreshAndRerun(){
        one.setEnabled(true);one.setText("");
        two.setEnabled(true);two.setText("");
        three.setEnabled(true);three.setText("");
        four.setEnabled(true);four.setText("");
        five.setEnabled(true);five.setText("");
        six.setEnabled(true);six.setText("");
        seven.setEnabled(true);seven.setText("");
        eight.setEnabled(true);eight.setText("");
        nine.setEnabled(true);nine.setText("");
        setBoardCounter=9;
        status.setText("Game Status");
        choices.clear();
        choices.add(1);choices.add(2);choices.add(3);
        choices.add(4);choices.add(5);choices.add(6);
        choices.add(7);choices.add(8);choices.add(9);
        thread1 = new Thread(new Player1());
        thread2 = new Thread(new Player2());
        thread1.start();
        thread2.start();
    }

    //selection of random choices to set the board
    public synchronized int RandomChoice(){
        long seed = System.nanoTime();
        Collections.shuffle(choices, new Random(seed));
        int valueOfMove=choices.get(0);
        choices.remove(0);
        return valueOfMove;
    }
    //Worker 1 thread- this thread will always run first , by default
    public class Player1 implements Runnable{
        @Override
        public void run() {
            //for the first time when it runs, the control will go to this block
            synchronized (lock) {
                //setting the choice by randomChoice() function
                int value = RandomChoice();
                Message message_2 = UIHandler.obtainMessage(SET_UI_FROM_PLAYER_ONE);
                message_2.arg1 = value;
                //sending the value back to the UI thread for the update in the UI
                UIHandler.sendMessage(message_2);
            }
            //Looper of the Worker 1 thread starts
            Looper.prepare();
            //the handler of the Worker 1 inside the looper where the works are queued which are being sent from the UI handler
            PlayerOneHandler =new Handler(){
                public  void handleMessage (Message msg){
                    int choice=msg.what;
                    try {
                        Thread.sleep(800);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //checking if the buttons are enabled or not and accordingly take action
                    if((one.isEnabled() || two.isEnabled() || three.isEnabled() || four.isEnabled() ||
                            five.isEnabled() ||six.isEnabled() || seven.isEnabled() || eight.isEnabled()
                            || nine.isEnabled())  ) {
                        switch (choice) {
                            //message that has been sent from the UI, caught over here and with the random choice sent back to the UI
                            case HANDLER_OF_THREAD_ONE: {
                                synchronized (lock) {
                                    //choice is set
                                    int value = RandomChoice();
                                    Message message_1 = UIHandler.obtainMessage(SET_UI_FROM_PLAYER_ONE);
                                    //setting up the choice as arg1
                                    message_1.arg1 = value;
                                    //choice sent back to the UI thread for UI update
                                    UIHandler.sendMessage(message_1);
                                }
                                //goto sleep for 3000 ms
                                try {
                                    Thread.sleep(3000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
            };
            //looper looping
            Looper.loop();
        }
    }

    ////Worker 2 thread- this thread will always run second , by default
    private class Player2 implements Runnable {
        @Override
        public void run() {
            //Looper of the Worker 1 thread starts
            Looper.prepare();
            //the handler of the Worker 2 inside the looper where the works are queued which are being sent from the UI handler
            PlayerTwoHandler=new Handler(){
                public void handleMessage (Message msg){
                    int choice=msg.what;
                    try {
                        Thread.sleep(800);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //checking if the buttons are enabled or not and accordingly take action
                    if((one.isEnabled() || two.isEnabled() || three.isEnabled() || four.isEnabled() ||
                            five.isEnabled() ||six.isEnabled() || seven.isEnabled() || eight.isEnabled()
                            || nine.isEnabled()) ) {
                        switch (choice) {
                            //message that has been sent from the UI, caught over here and with the random choice sent back to the UI
                            case HANDLER_OF_THREAD_TWO: {
                                synchronized (lock) {
                                    //choice is set
                                    int value = RandomChoice();
                                    Message message_1 = UIHandler.obtainMessage(SET_UI_FROM_PLAYER_TWO);
                                    //setting the choice as arg1
                                    message_1.arg1 = value;
                                    //choice sent back to the UI thread for UI update
                                    UIHandler.sendMessage(message_1);
                                }
                                //go to sleep for 3000 ms
                                try {
                                    Thread.sleep(3000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
            };
            //looper loops
            Looper.loop();
        }
    }
    //the function where the board is set
    public void setBoard(int placeValue,String player){
        //place value is received from the worker 1 / worker 2 and as well as which worker is sending the message
        int value=placeValue;
        String player_turn=player;
        //if the worker is 1, and the counter is more than 1(that is still turns are left), then update the buttons with X
        if (player_turn.equalsIgnoreCase("player1")&& setBoardCounter>=1){
            switch (value) {
                case 1: {one.setText("X");one.setEnabled(false);status.setText("Player 1(X) has played. Waiting for Player 2(O)...");break;}
                case 2: {two.setText("X");two.setEnabled(false);status.setText("Player 1(X) has played. Waiting for Player 2(O)...");break;}
                case 3: {three.setText("X");three.setEnabled(false);status.setText("Player 1(X) has played. Waiting for Player 2(O)...");break;}
                case 4: {four.setText("X");four.setEnabled(false);status.setText("Player 1(X) has played. Waiting for Player 2(O)...");break;}
                case 5: {five.setText("X");five.setEnabled(false);status.setText("Player 1(X) has played. Waiting for Player 2(O)...");break;}
                case 6: {six.setText("X");six.setEnabled(false);status.setText("Player 1(X) has played. Waiting for Player 2(O)...");break;}
                case 7: {seven.setText("X");seven.setEnabled(false);status.setText("Player 1(X) has played. Waiting for Player 2(O)...");break;}
                case 8: {eight.setText("X");eight.setEnabled(false);status.setText("Player 1(X) has played. Waiting for Player 2(O)...");break;}
                case 9: {nine.setText("X");nine.setEnabled(false);status.setText("Player 1(X) has played. Waiting for Player 2(O)...");break;}
            }
            //decrement the value of the counter
            setBoardCounter--;
            //if the worker is 2, and the counter is more than 1(that is still turns are left), then update the buttons with O
        }else if(player_turn.equalsIgnoreCase("player2")&& setBoardCounter>1){
            switch (value) {
                case 1: {one.setText("O");one.setEnabled(false);status.setText("Player 2(O) has played. Waiting for Player 1(X)...");break;}
                case 2: {two.setText("O");two.setEnabled(false);status.setText("Player 2(O) has played. Waiting for Player 1(X)...");break;}
                case 3: {three.setText("O");three.setEnabled(false);status.setText("Player 2(O) has played. Waiting for Player 1(X)...");break;}
                case 4: {four.setText("O");four.setEnabled(false);status.setText("Player 2(O) has played. Waiting for Player 1(X)...");break;}
                case 5: {five.setText("O");five.setEnabled(false);status.setText("Player 2(O) has played. Waiting for Player 1(X)...");break;}
                case 6: {six.setText("O");six.setEnabled(false);status.setText("Player 2(O) has played. Waiting for Player 1(X)...");break;}
                case 7: {seven.setText("O");seven.setEnabled(false);status.setText("Player 2(O) has played. Waiting for Player 1(X)...");break;}
                case 8: {eight.setText("O");eight.setEnabled(false);status.setText("Player 2(O) has played. Waiting for Player 1(X)...");break;}
                case 9: {nine.setText("O");nine.setEnabled(false);status.setText("Player 2(O) has played. Waiting for Player 1(X)...");break;}
            }
            //decrement the value of the counter
            setBoardCounter--;
            //if the worker is 2, and the counter is equal to 1(that is the last turn), then update the buttons with X
        }else if (player_turn.equalsIgnoreCase("player2")&& setBoardCounter==1){
            switch (value) {
                case 1: {one.setText("X");one.setEnabled(false);status.setText("Player 1(X) has played. Waiting for Player 2(O)...");break;}
                case 2: {two.setText("X");two.setEnabled(false);status.setText("Player 1(X) has played. Waiting for Player 2(O)...");break;}
                case 3: {three.setText("X");three.setEnabled(false);status.setText("Player 1(X) has played. Waiting for Player 2(O)...");break;}
                case 4: {four.setText("X");four.setEnabled(false);status.setText("Player 1(X) has played. Waiting for Player 2(O)...");break;}
                case 5: {five.setText("X");five.setEnabled(false);status.setText("Player 1(X) has played. Waiting for Player 2(O)...");break;}
                case 6: {six.setText("X");six.setEnabled(false);status.setText("Player 1(X) has played. Waiting for Player 2(O)...");break;}
                case 7: {seven.setText("X");seven.setEnabled(false);status.setText("Player 1(X) has played. Waiting for Player 2(O)...");break;}
                case 8: {eight.setText("X");eight.setEnabled(false);status.setText("Player 1(X) has played. Waiting for Player 2(O)...");break;}
                case 9: {nine.setText("X");nine.setEnabled(false);status.setText("Player 1(X) has played. Waiting for Player 2(O)...");break;}
            }
            //decrement the value of the counter
            setBoardCounter--;
        }
    }
}
