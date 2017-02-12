# Tic-Tac-Toe
An Android App implementing simple game of tic-tac-toe.The game will be played by two asynchronous Java worker threads playing against each other. The UI thread will be responsible for creating and starting these two worker threads. Each thread will take turns taking the
following actions:<br/>
1. Figuring out the next move</br>
2. Communicating the move to the UI thread, which will then update the device’s display<br/>
3. Waiting for the opponent to complete their move</br>
<br/>
In addition, to starting the worker threads, the UI thread will display a button allowing the device user to start
the game. Pressing this button while a game is in progress will void the current game and start a new game
from scratch. The UI thread is further responsible for the following actions:<br/>
1. Displaying the current tic-tac-toe board that shows the moves of the worker threads thus far.<br/>
2. Receiving notifications of moves by the worker threads.<br/>
3. Updating the display after each move.<br/>
4. Informing each worker thread of its opponent’s move. This will also signal the thread that it should make the next move.<br/>
5. Checking on the status of the game, by determining whether one player has won, the game has ended in a tie, or the game needs to continue.<br/>
6. Displaying an appropriate message to indicate the outcome of each game.<br/>
7. Signaling the two worker threads that the game is over; the two threads should stop their execution as a result of this action.<br/>
<br/>
**The communication is implemented among the Java threads with handlers**. Each thread T must
have a handler associated with it. Other threads will communicate with T either by posting runnables or by
sending messages on T’s handler queue. The game is played at a sensible speed allowing 1 second of delay between each move by two threads. <br/>
<br/>
PS: Tested by using Nexus 5 virtual device running the Android platform (API 23—Marshmallow). No backward compatibility provided.
