package de.tobiasreich.textadventuremaker.editor.editor;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import de.tobiasreich.textadventuremaker.R;
import de.tobiasreich.textadventuremaker.data.DataManager;
import de.tobiasreich.textadventuremaker.storyObjects.Story;

/**
 */
public class EditorFragment extends Fragment {

    private static final String TAG = EditorFragment.class.getSimpleName();

    private Button createButton;

    public EditorFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Story Editor");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_editor, container, false);
        createButton = (Button) rootView.findViewById(R.id.createButton);
        createButton.setOnClickListener(v -> {
            Log.d(TAG, "Create Object");
            Story story = new Story();
            story.setStoryName("Test-Story");
            DataManager.getInstance().saveStory(story);
        });

        return rootView;
    }
}
