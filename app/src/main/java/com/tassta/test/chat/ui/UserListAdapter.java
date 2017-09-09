package com.tassta.test.chat.ui;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tassta.test.chat.R;
import com.tassta.test.chat.model.UserBundle;
import com.tassta.test.chat.ui.chat.ChatActivity;
import com.tassta.test.chat.utils.Constants;
import com.tassta.test.chat.utils.MyUtils;

import java.util.List;

/**
 * Created by user on 07.09.2017.
 */

public class UserListAdapter extends BaseAdapter {
    Context context;
    LayoutInflater layoutInflater;
    List<UserBundle> users;

    UserListAdapter(Context c, List<UserBundle> users) {
        context = c;
        this.users = users;
        layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int position) {
        return users.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    UserBundle getUser(int position) {
        return ((UserBundle) getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.contact_list_item, parent, false);
        }
        final UserBundle user = getUser(position);

        ((TextView) view.findViewById(R.id.textview_userName)).setText(user.getName());
        String status;

        ImageView stIv = (ImageView) view.findViewById(R.id.ib_status);
        if (user.isOnline()) {
            status = context.getResources().getString(R.string.online);
            stIv.setBackgroundResource(android.R.drawable.presence_online);
        } else {
            status = context.getResources().getString(R.string.offline);
            stIv.setBackgroundResource(android.R.drawable.presence_offline);
        }
        ((TextView) view.findViewById(R.id.textview_status)).setText(status);

        ImageView userImageView = (ImageView) view.findViewById(R.id.iv_avatar);
        Bitmap androidImage = MyUtils.convertImageType(user.getIcon());

        // convert image type.
        // set default pic if not successful
        if( androidImage != null ){
        userImageView.setImageBitmap(androidImage);
        }else{
            userImageView.setImageResource(R.mipmap.ic_launcher_round);
        }

        userImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, ChatActivity.class);
                intent.putExtra(Constants.ID_FOR_INTENT, user.getId());
                context.startActivity(intent);
            }
        });


        ImageView message = (ImageView) view.findViewById(R.id.iv_newmessage);
        message.setImageResource(android.R.drawable.ic_menu_send);
        if (user.hasUnread() == 1) {
            message.setVisibility(View.VISIBLE);
        } else {
            message.setVisibility(View.INVISIBLE);
        }
        return view;
    }

}
