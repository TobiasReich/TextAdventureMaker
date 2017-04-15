package de.tobiasreich.textadventuremaker.player.chatGameType;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import de.tobiasreich.textadventuremaker.R;

/**
 * Created by T on 15.04.2017. */
public class PlayerChatAdapter extends RecyclerView.Adapter<PlayerChatAdapter.ViewHolder>  {

    final static String TAG = PlayerChatAdapter.class.getSimpleName();

    private Context context;

    public PlayerChatAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(
                parent.getContext()).inflate(R.layout.player_chat_message_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        fillViewElements(position, holder);
    }

    @Override
    public long getItemId(int position) { return 0; }

    @Override
    public int getItemCount() {
        return 300;
    }

    /** Fills the view elements with the message defined by the psition
     *
     * @param position int position of the view
     * @param holder ViewHolder */
    public void fillViewElements(final int position, final ViewHolder holder) {
        holder.chatTV.setText("Text message " + position);
    }


    // --- VIEW_HOLDER ---------------------------------------------

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final View rootView;
        private final TextView chatTV;

        public ViewHolder(View view) {
            super(view);
            rootView = view;
            chatTV = (TextView) rootView.findViewById(R.id.messageTV);
        }

    }
    // --- END OF VIEW_HOLDER
}

