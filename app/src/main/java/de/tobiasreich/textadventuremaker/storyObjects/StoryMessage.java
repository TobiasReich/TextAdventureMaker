package de.tobiasreich.textadventuremaker.storyObjects;

/**
 * One message text of a story part.
 * This contains the text that is written to the user
 * and also the wait time.
 *
 * Further more it has a field for a statusNumber.
 * The status number which will indicate the status (in case the game requires a "phone status"
 * message (e.g. contact is online, is writing...)
 *
 * It also contains an imagePath in case the story want's to show an image
 *
 * The boolean fromUser determines whether the message is from the user.
 * This is especially important for "Chats" where the outgoing messages are shown on another
 * side than the incoming ones.
 * This is
 *
 * Created by T on 12.04.2017. */
public class StoryMessage {

    public String text = "story message text";
    public int waitTime = 1000;
    public int statusNumber = 0;
    public String imagePath;
    public boolean fromUser;   // Determines whether this message is from the user or from the "chat partner"

    public StoryMessage(String text, int waitTime) {
        this.text = text;
        this.waitTime = waitTime;
    }
}
