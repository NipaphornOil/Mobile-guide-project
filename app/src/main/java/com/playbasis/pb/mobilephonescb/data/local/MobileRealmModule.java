package com.playbasis.pb.mobilephonescb.data.local;

import com.playbasis.pb.mobilephonescb.data.entity.Mobile;

import io.realm.annotations.RealmModule;

@RealmModule(library = true, classes = {Mobile.class})
public class MobileRealmModule {
}
