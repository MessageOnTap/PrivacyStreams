package com.github.privacystreams.communication;

/**
 * Created by mars on 03/07/2017.
 */

public class EmailInfoProvider extends BaseEmailInfoProvider{

    /**
     * beginTime and endTime are all in ms.
     * maxResult denotes the max number of
     * return email allowed for one query.
     */

    EmailInfoProvider(String API_KEY, String API_SECRET) {
        super(API_KEY, API_SECRET);
    }

    /**
     * For queries in all other times later on, when the app does not need to get
     * authorization and permission from the activity all over again.
     */

    @Override
    protected void provide() {
        super.provide();
    }

    /**
     * When the app just got the authorization and permission from the activity, it goes to this callback.
     */
    @Override
    public void onSuccess() {
        super.listEmailInfoEntity("Mars");
    }

}
