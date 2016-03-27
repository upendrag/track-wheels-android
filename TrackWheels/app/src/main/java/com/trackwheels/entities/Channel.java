package com.trackwheels.entities;

/**
 * Created by upendra on 3/26/16.
 */

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;
import com.kinvey.java.User;
import com.kinvey.java.model.KinveyMetaData;
import com.kinvey.java.model.KinveyReference;

import java.util.ArrayList;

public class Channel extends GenericJson { // For Serialization

    public class Notification extends GenericJson {
        @Key
        private KinveyReference user;

        @Key
        private int stopIndex;

        public Notification(User userData) {
            this.user = new KinveyReference("Users", userData.get("_id").toString());
        }
    }

    public class Stop extends GenericJson {

        @Key("_geoloc")
        private double[] location;
    }

    @Key("_id")
    private String id;
    @Key
    private String name;
    @Key
    private KinveyReference account;
    @Key("_kmd")
    private KinveyMetaData meta;
    @Key("_acl")
    private KinveyMetaData.AccessControlList acl;
    @Key
    private ArrayList<Notification> notifications;
    @Key
    private ArrayList<Stop> stops;

    public Channel(){}  //GenericJson classes must have a public empty constructor

    public void initAccountRef(Account account) {
        this.account = new KinveyReference("accounts", account.get("_id").toString());
    }
}
