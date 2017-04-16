package de.tobiasreich.textadventuremaker.storyObjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by T on 12.04.2017. */
public class Story {
    /* TODO: Brainstorming:
     * If the game is a chat game. Add option for multiple chats (not only with that person).
     **/

    /* The modern type. Chatting with someone who can only reach you and needs your decisions */
    public static final int STORY_TYPE_CHAT = 0;

    /* The classic type. A book of decisions and you flip the pages according to your decisions */
    public static final int STORY_TYPE_STORY_TELLER = 1;

    private String storyName;

    private int storyType = STORY_TYPE_CHAT ;

    /* User elements
    *  TODO: Separate story from user values (so save games can be created)
    * (Depending on the users decisions, needed for save games) */
    private GameSettings gameSettings;

    /* Story elements (all the story parts etc.) */
    private List<String> statusNumbers;
    private HashMap<String, StoryPart> storyParts;

    public Story() {
        gameSettings = new GameSettings();
        statusNumbers = new ArrayList<>();
        storyParts = new HashMap<>();

        StoryPart startPart = new StoryPart(StoryPart.START_PART_NAME);
        StoryPart part1 = new StoryPart("Part1");
        StoryPart part2 = new StoryPart("Part2");
        StoryPart part3 = new StoryPart("Part3");

        addStoryPart(startPart);
        addStoryPart(part1);
        addStoryPart(part2);
        addStoryPart(part3);
    }

    public void setStoryName(String storyName) {
        this.storyName = storyName;
    }

    public String getStoryName() {
        return storyName;
    }

    public void addStoryPart(StoryPart part){
        storyParts.put(part.getPartName(), part);
    }

    public GameSettings getGameSettings() {
        return gameSettings;
    }

    public String getStatusNumber(int number) {
        if (number < 0 || number >= statusNumbers.size())
            return "";
        return statusNumbers.get(number);
    }

    public HashMap<String, StoryPart> getStoryParts() {
        return storyParts;
    }

    /** Gets the story part with the given name
     *
     * @param partName String the name of the part to get
     * @return StoryPart with the given name (title) */
    public StoryPart getStoryPart(String partName) {
        return storyParts.get(partName);
    }

    public void setGameSettings(GameSettings gameSettings) {
        this.gameSettings = gameSettings;
    }

    public void setStoryParts(HashMap<String, StoryPart> storyParts) {
        this.storyParts = storyParts;
    }
}
