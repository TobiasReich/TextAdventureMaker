package de.tobiasreich.textadventuremaker.storyObjects;

import java.util.ArrayList;
import java.util.List;

/**
 * Part of a story.
 *
 * This includes
 * - The story storyMessages that are presented to the user
 * - A list of possible decisions (answers) the player may make.
 *
 * Created by T on 12.04.2017. */
public class StoryPart {

    public static final String START_PART_NAME = "START_PART";

    private String partName = "";
    private List<StoryMessage> storyMessages = new ArrayList<>();
    public List<UserAction> userActions = new ArrayList<>();
    private boolean saveGame = false;

    public StoryPart(String name) {
        this.partName = name;
        storyMessages.add(new StoryMessage("Message 1", 1000));
        storyMessages.add(new StoryMessage("Message 2", 3000));
        storyMessages.add(new StoryMessage("Message 3", 5000));

        // TODO: Test, add some cyclic paths for testing
        userActions.add(new UserAction("Action 1", 1, "Part1"));
        userActions.add(new UserAction("Action 2", 3, "Part2"));
        userActions.add(new UserAction("Action 3", 6, "Part3"));
    }

    public String getPartName() {
        return partName;
    }

    /** Gets the story messages
     *
     * @return List of StoryMessages
     */
    public List<StoryMessage> getStoryMessages(){
        return storyMessages;
    }
}
