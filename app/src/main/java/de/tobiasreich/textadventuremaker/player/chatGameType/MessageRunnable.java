package de.tobiasreich.textadventuremaker.player.chatGameType;

import android.util.Log;

import java.util.ArrayDeque;
import java.util.List;

import de.tobiasreich.textadventuremaker.storyObjects.StoryMessage;
import de.tobiasreich.textadventuremaker.storyObjects.StoryPart;
import de.tobiasreich.textadventuremaker.storyObjects.UserAction;

/**
 * Created by T on 15.04.2017.
 */

public class MessageRunnable implements Runnable {

    final static String TAG = MessageRunnable.class.getSimpleName();

    final static int DEFAULT_SLEEP_TIME = 1000;

    public boolean isRunning = true;
    private int sleepTime = DEFAULT_SLEEP_TIME;

    private ArrayDeque<StoryMessage> messagesQueue = new ArrayDeque();

    @Override
    public void run() {
        Log.d(TAG, "Start Thread ");
        while (isRunning) {
            Log.d(TAG, "Tick!");
            StoryMessage nextMessage = messagesQueue.poll();

            if (nextMessage != null){
                Log.i(TAG, "Queue had a message " + nextMessage.text);
                sleepTime = nextMessage.waitTime;
            } else {
                /* is the queue es empty, it means the story parts are done and
                * it is time for the user to make a decision.
                * TODO: Notify view for showing user-actions **/
                sleepTime = DEFAULT_SLEEP_TIME;
            }

            try {
                Log.d(TAG, "Sleep Time: " + sleepTime);
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Log.d(TAG, "Stop Thread");
    }

    /** Adds a message (from the user) to the queue
     *
     * @param part the Storypart to add it's messages
     *
     * @return boolean true if adding is successful */
    public boolean addPartToQueue(StoryPart part){
        List<StoryMessage> messages = part.getStoryMessages();
        for (StoryMessage message : messages) {
           if (! messagesQueue.offer(message)){
               return false;
           }
           Log.d(TAG, "Adding message... done!");
        }
        return true;
    }

}
