package de.tobiasreich.textadventuremaker.player;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import de.tobiasreich.textadventuremaker.R;

/**
 * Created by T on 15.04.2017. */
public class GamesListAdapter extends RecyclerView.Adapter<GamesListAdapter.ViewHolder>  {

    final static String TAG = GamesListAdapter.class.getSimpleName();

    private Context context;
    private List<String> games;
    private IGameSelection selectionInterface;

    public GamesListAdapter(Context context,  List<String> games, IGameSelection selectionInterface) {
        this.context = context;
        this.games = games;
        this.selectionInterface = selectionInterface;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(
                parent.getContext()).inflate(R.layout.game_name_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.gameNameTV.setText(games.get(position));
        holder.rootView.setOnClickListener(v -> {
            selectionInterface.selectGame(position);
        });
    }

    @Override
    public long getItemId(int position) { return 0; }

    @Override
    public int getItemCount() {
        return games.size();
    }


    // --- VIEW_HOLDER ---------------------------------------------

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final View rootView;
        private final TextView gameNameTV;

        public ViewHolder(View view) {
            super(view);
            rootView = view;
            gameNameTV = (TextView) rootView.findViewById(R.id.gameNameTV);
        }

    }
    // --- END OF VIEW_HOLDER
}

