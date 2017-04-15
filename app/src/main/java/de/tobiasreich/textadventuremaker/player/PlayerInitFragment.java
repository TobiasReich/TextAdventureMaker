package de.tobiasreich.textadventuremaker.player;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

import de.tobiasreich.textadventuremaker.ApplicationInteractionListener;
import de.tobiasreich.textadventuremaker.R;
import de.tobiasreich.textadventuremaker.data.DataManager;

/**
 */
public class PlayerInitFragment extends Fragment implements IGameSelection {

    private static final String TAG = PlayerInitFragment.class.getSimpleName();

    private Button startGameButton;
    private ApplicationInteractionListener mListener;
    private RecyclerView availableGamesRV;

    public PlayerInitFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Story Editor");
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ApplicationInteractionListener) {
            mListener = (ApplicationInteractionListener) activity;
        } else {
            throw new RuntimeException(activity.toString() + " must implement ApplicationInteractionListener");
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ApplicationInteractionListener) {
            mListener = (ApplicationInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement ApplicationInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_player_init, container, false);
        startGameButton = (Button) rootView.findViewById(R.id.startGameButton);
        startGameButton.setOnClickListener(v -> {
            Log.d(TAG, "START GAME");
            mListener.openChatPlayer();
        });

        availableGamesRV = (RecyclerView) rootView.findViewById(R.id.availableGamesRV);

        GamesListAdapter gamesListAdapter = new GamesListAdapter(getActivity(),
                DataManager.getInstance().getPossibleGames(), this);
        availableGamesRV.setHasFixedSize(true);
        availableGamesRV.setLayoutManager(new LinearLayoutManager(getActivity()));
        availableGamesRV.setAdapter(gamesListAdapter);

        return rootView;
    }

    @Override
    public void selectGame(int position) {
        Log.d(TAG, "Selected Game: " + position);
    }
}
