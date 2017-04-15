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

    String partName = "";
    List<StoryMessage> texts = new ArrayList<>();
    List<Decision> decisions = new ArrayList<>();
    boolean saveGame = false;

    public StoryPart(String name) {
        this.partName = name;
        texts.add(new StoryMessage());
        texts.add(new StoryMessage());
        texts.add(new StoryMessage());
    }
}
