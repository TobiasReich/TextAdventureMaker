package de.tobiasreich.textadventuremaker.storyObjects;

import java.util.ArrayList;
import java.util.List;

/**
 * Part of a story.
 *
 * This includes
 * - The story texts that are presented to the user
 * - A list of possible decisions (answers) the player may make.
 *
 * Created by T on 12.04.2017. */
public class StoryPart {

    public static final String START_PART_NAME = "START";

    private String partName = "";
    private List<StoryMessage> texts = new ArrayList<>();
    public List<UserAction> userActions = new ArrayList<>();
    private boolean saveGame = false;

    public StoryPart(String name) {
        this.partName = name;
        texts.add(new StoryMessage());
        texts.add(new StoryMessage());
        texts.add(new StoryMessage());

        // TODO: Test, add some cyclic paths for testing
        userActions.add(new UserAction("Answer 1", 1, "Answer 2"));
        userActions.add(new UserAction("Answer 2", 3, "Answer 3"));
        userActions.add(new UserAction("Answer 3", 6, "Answer 1"));
    }

    public String getPartName() {
        return partName;
    }
}
