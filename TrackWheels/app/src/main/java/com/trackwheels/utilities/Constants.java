package com.trackwheels.utilities;

/**
 * Created by shruthi on 26/3/16.
 */
public class Constants {
    final String LOGTAG = "TrackMe";

    public static class SharedPref {
        public static final String PREF_NAME = "TrackWheels";
        public static final String KEY_IS_SIGN_IN = "signin";
    }

    public static class Kinvey {
        public static final String SCOPE_STRING = "oauth2:https://www.googleapis.com/auth/plus.me";
        public static final String PLUS_PEOPLE_ME = "https://www.googleapis.com/plus/v1/people/me";
    }

    public static class GCM {
        public static final String SENT_TOKEN_TO_SERVER = "sentTokenToServer";
        public static final String REGISTRATION_COMPLETE = "registrationComplete";
    }

    public static class Server{
        public static final String serverPath = "http://08d9f305.ngrok.io";
    }
}
