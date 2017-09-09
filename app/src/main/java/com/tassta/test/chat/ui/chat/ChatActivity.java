package com.tassta.test.chat.ui.chat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.tassta.test.chat.ChatApp;
import com.tassta.test.chat.Message;
import com.tassta.test.chat.User;
import com.tassta.test.chat.UserImpl;
import com.tassta.test.chat.data.DataManager;
import com.tassta.test.chat.ui.UserListMvpPresenter;
import com.tassta.test.chat.ui.UserListMvpView;
import com.tassta.test.chat.ui.UserListPresenterImpl;
import com.tassta.test.chat.utils.Constants;

import java.util.LinkedList;
import java.util.List;


import javafx.scene.control.ToolBar;
import com.tassta.test.chat.R;

public class ChatActivity extends AppCompatActivity
        implements ChatMvpView {

    public final String LOG_TAG = ChatActivity.class.getSimpleName();

    ChatMvpPresenter mPresenter;
    ListView lv;

    String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Intent intent = getIntent();
        int senderId = intent.getIntExtra(Constants.ID_FOR_INTENT, 0);
        mPresenter = new ChatPresenterImpl(((ChatApp)getApplication()).getDataManager(), senderId);
        Button sendbtn = (Button) findViewById(R.id.sendbtn);
        EditText editText = (EditText) findViewById(R.id.sms_et);
        text = editText.getText().toString();
        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                {
                    EditText editText = (EditText) findViewById(R.id.sms_et);
                mPresenter.sendMessage(0, editText.getText().toString());
                    editText.setText("");
                }
            }
        });

        mPresenter.onAttached(this);
    }

public void setAdapter(List<String> list){
    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
    android.R.layout.simple_list_item_1, list);
    lv = (ListView) findViewById(R.id.chat_lv);
    lv.setAdapter(adapter);
    }


    public void refresh(){
       // List<String> list = DataManager.
    }
}
