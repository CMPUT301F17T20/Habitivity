package main.habitivity.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.List;

import main.habitivity.R;

/**
 * Created by Shally on 2017-11-29.
 */

public class FindFriendsViewAdapter extends RecyclerView.Adapter<FindFriendsViewAdapter.ViewHolder>{
    private List<String> mFeed;
    private final ClickListener listener;
    private Context mContext;

    public FindFriendsViewAdapter(Context context, ClickListener listener) {
        mContext = context;
        this.listener = listener;
    }

    @Override
    public FindFriendsViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext(); //returns the context the view is running in, gets access to the resources
        LayoutInflater inflater = LayoutInflater.from(context);
        View requestView = inflater.inflate(R.layout.find_friend_item, parent, false);
        FindFriendsViewAdapter.ViewHolder viewHolder = new FindFriendsViewAdapter.ViewHolder(requestView, this.listener);

        return viewHolder;
    }

    public void setRequestList(List<String> requests) {
        this.mFeed = requests;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(final FindFriendsViewAdapter.ViewHolder holder, int position) {
        String userName = mFeed.get(position);
        TextView name = holder.name;
        name.setText(userName);
    }


    @Override
    public int getItemCount() {
        if (mFeed == null) {
            return 0;
        }
        return mFeed.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name;
        public Button follow;
        private WeakReference<ClickListener> listenerRef;

        public ViewHolder(final View itemView, ClickListener listener) {
            super(itemView);
            listenerRef = new WeakReference<>(listener);
            name = (TextView) itemView.findViewById(R.id.friend);
            follow = (Button) itemView.findViewById(R.id.followButton);

            follow.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listenerRef.get().onPositionClicked(getAdapterPosition());
        }
    }
}
