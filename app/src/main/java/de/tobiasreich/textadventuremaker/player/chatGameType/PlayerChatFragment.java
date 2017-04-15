package de.tobiasreich.textadventuremaker.player.chatGameType;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import de.tobiasreich.textadventuremaker.R;
import de.tobiasreich.textadventuremaker.storyObjects.Story;
import de.tobiasreich.textadventuremaker.storyObjects.StoryMessage;

/**
 */
public class PlayerChatFragment extends Fragment {

    private static final String TAG = PlayerChatFragment.class.getSimpleName();

    private RecyclerView messagesRV;
    private LinearLayout sendMessagesLL;

    private List<StoryMessage> historyMessages = new ArrayList<>();

    public PlayerChatFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Story story = new Story();
        story.setStoryName("Test-Story");

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(story.getStoryName());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_player_chat, container, false);
        messagesRV = (RecyclerView) rootView.findViewById(R.id.messagesRV);

        PlayerChatAdapter chatMassageListAdapter = new PlayerChatAdapter(getActivity(), historyMessages);
        messagesRV.setHasFixedSize(true);
        messagesRV.setLayoutManager(new LinearLayoutManager(getActivity()));
        messagesRV.setAdapter(chatMassageListAdapter);

        sendMessagesLL = (LinearLayout) rootView.findViewById(R.id.sendMessagesLL);

        return rootView;
    }
}
