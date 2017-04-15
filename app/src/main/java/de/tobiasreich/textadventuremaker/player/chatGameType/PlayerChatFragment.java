package de.tobiasreich.textadventuremaker.player.chatGameType;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import de.tobiasreich.textadventuremaker.R;
import de.tobiasreich.textadventuremaker.storyObjects.Story;
import de.tobiasreich.textadventuremaker.storyObjects.StoryMessage;
import de.tobiasreich.textadventuremaker.storyObjects.StoryPart;
import de.tobiasreich.textadventuremaker.storyObjects.UserAction;

/**
 */
public class PlayerChatFragment extends Fragment {

    private static final String TAG = PlayerChatFragment.class.getSimpleName();

    private static final String PARAM_STORY_NAME = "PARAM_STORY_NAME";
    private String storyName;

    private RecyclerView messagesRV;
    private LinearLayout sendMessagesLL;

    private TextView message1TV;
    private TextView message2TV;
    private TextView message3TV;

    private Handler updateHandler;
    private MessageRunnable messageRunnable;

    private Story story;
    private StoryPart currentPart;

    private List<StoryMessage> historyMessages = new ArrayList<>();




    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param storyName Parameter telling the story name
     *
     * @return A new instance of fragment PlayerChatFragment.
     */
    public static PlayerChatFragment newInstance(String storyName) {
        PlayerChatFragment fragment = new PlayerChatFragment();
        Bundle args = new Bundle();
        args.putString(PARAM_STORY_NAME, storyName);
        fragment.setArguments(args);
        return fragment;
    }

    public PlayerChatFragment() {  }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null)
            storyName = getArguments().getString(PARAM_STORY_NAME);

        //FIXME: Load the story instead of creating a new one
        Story story = new Story();
        story.setStoryName(storyName);

        this.story = story;
        updateHandler = new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(Message inputMessage) {
                /* HandleMessage() defines the operations to perform when
                 * the Handler receives a new Message to process. */
                Log.i(TAG, "Handle update Message");
            }
        };
        messageRunnable = new MessageRunnable();

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(story.getStoryName());
    }


    @Override
    public void onResume() {
        super.onResume();
        new Thread(messageRunnable).start();
    }

    @Override
    public void onPause() {
        super.onPause();
        messageRunnable.isRunning = false;
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

        message1TV = (TextView) rootView.findViewById(R.id.message1TV);
        message1TV.setOnClickListener(v ->
            Log.d(TAG, "Clicked at Message 1")
                //TODO: Switch to next message
        );
        message2TV = (TextView) rootView.findViewById(R.id.message2TV);
        message2TV.setOnClickListener(v ->
                Log.d(TAG, "Clicked at Message 2")
                //TODO: Switch to next message
        );
        message3TV = (TextView) rootView.findViewById(R.id.message3TV);
        message3TV.setOnClickListener(v ->
                Log.d(TAG, "Clicked at Message 3")
                //TODO: Switch to next message
        );
        return rootView;
    }

    private void executeFeedbackAndContinue(UserAction action){
        Log.d(TAG, "User chose action: " + action.message);
        Log.d(TAG, "Action continues with: " + action.nextStoryPart);
    }

    /** Progresses the story to the next part
     *
     * @param partName String the name of the next part */
    public void gotoNextPart(String partName){
        StoryPart nextPart = story.getStoryPart(partName);
        if (nextPart == null) {
            Toast.makeText(getActivity(), "Desired part not available!", Toast.LENGTH_LONG).show();
            return;
        }
        // TODO: execute the
    }

}
