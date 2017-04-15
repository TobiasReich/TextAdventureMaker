package de.tobiasreich.textadventuremaker.player;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import de.tobiasreich.textadventuremaker.R;

/**
 */
public class PlayerChatFragment extends Fragment {

    private static final String TAG = PlayerChatFragment.class.getSimpleName();

    private RecyclerView messagesRV;
    private LinearLayout sendMessagesLL;

    public PlayerChatFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Story Editor");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_player_chat, container, false);
        messagesRV = (RecyclerView) rootView.findViewById(R.id.messagesRV);

        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        PlayerChatAdapter chatMassageListAdapter = new PlayerChatAdapter(getActivity());
        messagesRV.setHasFixedSize(true);
        messagesRV.setLayoutManager(mLinearLayoutManager);
        messagesRV.setAdapter(chatMassageListAdapter);

        sendMessagesLL = (LinearLayout) rootView.findViewById(R.id.sendMessagesLL);

        return rootView;
    }
}
