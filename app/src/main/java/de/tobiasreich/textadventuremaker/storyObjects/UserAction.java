package de.tobiasreich.textadventuremaker.storyObjects;

/**
 * Created by T on 16.04.2017.
 */

public class UserAction {

    public String message;
    public int waitTime = 1;
    public String nextStoryPart;

    public UserAction(String message, int waitTime, String nextStoryPart) {
        this.message = message;
        this.waitTime = waitTime;
        this.nextStoryPart = nextStoryPart;
    }
}
