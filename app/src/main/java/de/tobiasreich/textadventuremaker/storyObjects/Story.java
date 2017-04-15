package de.tobiasreich.textadventuremaker.storyObjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by T on 12.04.2017. */
public class Story {

    private String storyName;

    // TODO: Separate this

    /* User elements
    * (Depending on the users decisions, needed for save games) */
    private GameSettings gameSettings;

    /* Story elements (all the story parts etc.) */
    private List<String> statusNumbers;
    private HashMap<String, StoryPart> storyParts;

    public Story() {
        gameSettings = new GameSettings();
        statusNumbers = new ArrayList<>();
        storyParts = new HashMap<>();

        StoryPart startPart = new StoryPart();
        startPart.partName = StoryPart.START_PART_NAME;

        StoryPart part1 = new StoryPart();
        part1.partName = "Part1";

        StoryPart part2 = new StoryPart();
        part2.partName = "Part2";

        addStoryPart(startPart);
        addStoryPart(part1);
        addStoryPart(part2);
    }

    public void setStoryName(String storyName) {
        this.storyName = storyName;
    }

    public String getStoryName() {
        return storyName;
    }

    public void addStoryPart(StoryPart part){
        storyParts.put(part.partName, part);
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

    public void setGameSettings(GameSettings gameSettings) {
        this.gameSettings = gameSettings;
    }

    public void setStoryParts(HashMap<String, StoryPart> storyParts) {
        this.storyParts = storyParts;
    }
}
