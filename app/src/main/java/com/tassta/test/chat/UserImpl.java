package com.tassta.test.chat;

import javafx.scene.image.Image;

/**
 * Created by user on 07.09.2017.
 */

public class UserImpl implements User{
    private String name;
    private int id;
    boolean online;
    Image avatar;

    public UserImpl(String name, int id, boolean online, Image avatar){
        this.name = name;
        this.id = id;
        this.online = online;
        this.avatar = avatar;
    }


    public String getName(){
        return name;
    }

    public int getId(){
        return id;
    }

    public boolean isOnline(){
        return online;
    }

    public Image getIcon(){
        return avatar;
    }
}
