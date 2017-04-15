package de.tobiasreich.textadventuremaker.player;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import de.tobiasreich.textadventuremaker.R;

/**
 */
public class PlayerFragment extends Fragment {

    private static final String TAG = PlayerFragment.class.getSimpleName();

    private Button createButton;

    public PlayerFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Story Editor");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_player, container, false);
        createButton = (Button) rootView.findViewById(R.id.createButton);

        return rootView;
    }
}
