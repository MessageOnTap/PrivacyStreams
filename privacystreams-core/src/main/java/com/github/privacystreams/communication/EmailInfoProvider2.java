package com.github.privacystreams.communication;


import com.github.privacystreams.communication.email.api.ApiManager;
import com.github.privacystreams.core.providers.MStreamProvider;

/**
 * Created by mars on 28/06/2017.
 */

public class EmailInfoProvider2 extends MStreamProvider{

    private final String API_KEY = "29f043cde8b6636c395dc07fcac571f6";
    private final String API_SECRET = "54ce16efb4d67116ac485eb540ac3bb20907ef07";

    public java.util.List<EmailInfoEntity> getSifts() {
        ApiManager apiMan = new ApiManager(API_KEY, API_SECRET);
        //java.util.List conns = apiMan.listConnections("Mars");
        //Sift sift = apiMan.getSift("Mars", 12345L);
        java.util.List<EmailInfoEntity> sifts = apiMan.listEmailInfoEntity("Mars");
        return sifts;

        //long connectionId = apiMan.addGmailConnection("Mars", "romantic.chimps.lab@gmail.com", "ad56958fae603a3a3d2510bf73f6d6b7");


    }

    @Override
    protected void provide() {
//        this.getEmailInfoList();
    }
}
