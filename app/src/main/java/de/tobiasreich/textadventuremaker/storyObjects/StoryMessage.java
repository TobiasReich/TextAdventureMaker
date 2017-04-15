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
 * Created by T on 12.04.2017. */
public class StoryMessage {

    String Text;
    int waitTime = 1;
    int statusNumber = 0;
    String imagePath;

}
