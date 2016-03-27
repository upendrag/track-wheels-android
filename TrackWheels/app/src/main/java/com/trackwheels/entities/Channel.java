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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public KinveyReference getAccount() {
        return account;
    }

    public void setAccount(KinveyReference account) {
        this.account = account;
    }

    public KinveyMetaData getMeta() {
        return meta;
    }

    public void setMeta(KinveyMetaData meta) {
        this.meta = meta;
    }

    public KinveyMetaData.AccessControlList getAcl() {
        return acl;
    }

    public void setAcl(KinveyMetaData.AccessControlList acl) {
        this.acl = acl;
    }

    public ArrayList<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(ArrayList<Notification> notifications) {
        this.notifications = notifications;
    }

    public ArrayList<Stop> getStops() {
        return stops;
    }

    public void setStops(ArrayList<Stop> stops) {
        this.stops = stops;
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
