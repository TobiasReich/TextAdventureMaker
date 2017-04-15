package de.tobiasreich.textadventuremaker.player.chatGameType;

import android.util.Log;

import java.util.ArrayDeque;

import de.tobiasreich.textadventuremaker.storyObjects.StoryMessage;
import de.tobiasreich.textadventuremaker.storyObjects.UserAction;

/**
 * Created by T on 15.04.2017.
 */

public class MessageRunnable implements Runnable {

    final static String TAG = MessageRunnable.class.getSimpleName();

    public boolean isRunning = true;

    private ArrayDeque<StoryMessage> messagesQueue = new ArrayDeque();

    @Override
    public void run() {
        while (isRunning) {
            Log.d(TAG, "Running");

            StoryMessage nextMessage = messagesQueue.poll();
            if (nextMessage != null){
                Log.i(TAG, "Queue had a message " + nextMessage.text);
            }

            /* is the queue es empty, it means the story parts are
             *
             **/

            try {
                // TODO: Sleep the defined time the story part determines
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /** Adds a message (from the user) to the queue
     *
     * @param message StoryMessage to add
     *
     * @return boolean true if adding is successful */
    public boolean addMessageToQueue(StoryMessage message){
        return messagesQueue.offer(message);
    }

}
