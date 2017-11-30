package main.habitivity.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import main.habitivity.R;
import main.habitivity.activities.FollowRequest;
import main.habitivity.controllers.ElasticsearchController;
import main.habitivity.users.User;
import main.habitivity.users.UserContainer;

/**
 * Created by Shally on 2017-11-28.
 */

public class FollowRequestViewAdapter extends RecyclerView.Adapter<FollowRequestViewAdapter.ViewHolder> {

    private List<String> mFeed;
    private final ClickListener listener;
    private Context mContext;

    public FollowRequestViewAdapter(Context context, ClickListener listener) {
        mContext = context;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext(); //returns the context the view is running in, gets access to the resources
        LayoutInflater inflater = LayoutInflater.from(context);
        View requestView = inflater.inflate(R.layout.follower_requests, parent, false);
        ViewHolder viewHolder = new ViewHolder(requestView, this.listener);

        return viewHolder;
    }

    public void setRequestList(List<String> requests) {
        this.mFeed = requests;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
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
        public Button accept;
        private WeakReference<ClickListener> listenerRef;

        public ViewHolder(final View itemView, ClickListener listener) {
            super(itemView);
            listenerRef = new WeakReference<>(listener);
            name = (TextView) itemView.findViewById(R.id.requestUserName);
            accept = (Button) itemView.findViewById(R.id.allowbutton);

            accept.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listenerRef.get().onPositionClicked(getAdapterPosition());
        }
    }

//
//            accept.setOnClickListener(new View.OnClickListener(){
//                @Override
//                public void onClick(View view) {
//                    if (listener != null) {
//                        int position = getAdapterPosition();
//                        if (position != RecyclerView.NO_POSITION) {
//                            User currentUser = UserContainer.getInstance().getUser();
//                            User userView = UserContainer.getInstance().getUserToView();
//                            currentUser.addFollower(name.getText().toString());
//                            userView.addFollowing(name.getText().toString());
//
//                            ElasticsearchController.UpdateUserTask updateUserTask = new ElasticsearchController.UpdateUserTask();
//                            updateUserTask.execute(userView);
//                            ElasticsearchController.UpdateUserTask updateUserTask2 = new ElasticsearchController.UpdateUserTask();
//                            updateUserTask2.execute(currentUser);
//
//                        }
//                    }
//                }
//            });
}