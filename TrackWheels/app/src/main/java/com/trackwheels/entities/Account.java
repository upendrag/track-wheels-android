package com.trackwheels.entities;

/**
 * Created by upendra on 3/26/16.
 */

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;
import com.kinvey.java.model.KinveyMetaData;

public class Account extends GenericJson { // For Serialization

    @Key("_id")
    private String id;
    @Key
    private String name;
    @Key
    private String admin_user;
    @Key("_kmd")
    private KinveyMetaData meta;
    @Key("_acl")
    private KinveyMetaData.AccessControlList acl;

    public Account(){}  //GenericJson classes must have a public empty constructor


}
