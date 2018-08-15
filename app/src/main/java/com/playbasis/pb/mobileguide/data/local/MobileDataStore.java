package com.playbasis.pb.mobileguide.data.local;

import android.content.Context;

import com.playbasis.pb.mobileguide.data.entity.Mobile;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import io.realm.Sort;

public class MobileDataStore {

    Realm realm;

    public MobileDataStore(Context context) {
        Realm.init(context);
        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                .name("mobile.realm")
                .modules(new MobileRealmModule())
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfig);
        realm = Realm.getDefaultInstance();
    }

    private void beginTransaction() {
        if (realm == null || realm.isClosed()) {
            realm = Realm.getDefaultInstance();
        }
        realm.beginTransaction();
    }

    private void closeTransaction() {
        if (realm != null) {
            realm.commitTransaction();
        }
    }

    public void saveMobile(Mobile mobile) {
        beginTransaction();
        realm.copyToRealmOrUpdate(mobile);
        closeTransaction();
    }

    public void makeFavorite(Mobile mobile) {
        mobile.setFavorite(true);
        beginTransaction();
        realm.copyToRealmOrUpdate(mobile);
        closeTransaction();
    }

    public void removeFromFavorite(Mobile mobile) {
        mobile.setFavorite(false);
        beginTransaction();
        realm.copyToRealmOrUpdate(mobile);
        closeTransaction();
    }

    public List<Mobile> getAllMobiles() {
        List<Mobile> results = new ArrayList<>();
        RealmResults<Mobile> mobiles = realm.where(Mobile.class).findAll();
        results = realm.copyFromRealm(mobiles);

        return results;
    }

    public List<Mobile> getAllMobilesByPriceAscending() {
        List<Mobile> results = new ArrayList<>();
        //RealmResults<Mobile> mobiles = realm.where(Mobile.class).equalTo("isFavorite", true).findAll().sort("price", Sort.ASCENDING);
        RealmResults<Mobile> mobiles = realm.where(Mobile.class).findAll().sort("price", Sort.ASCENDING);
        results = realm.copyFromRealm(mobiles);

        return results;
    }

    public List<Mobile> getAllMobilesByPriceDescending() {
        List<Mobile> results = new ArrayList<>();
        RealmResults<Mobile> mobiles = realm.where(Mobile.class).findAll().sort("price", Sort.DESCENDING);
        results = realm.copyFromRealm(mobiles);

        return results;
    }

    public List<Mobile> getFavoriteMobile() {
        List<Mobile> results = new ArrayList<>();
        RealmResults<Mobile> mobiles = realm.where(Mobile.class).equalTo("isFavorite", true).findAll();
        results = realm.copyFromRealm(mobiles);

        return results;

    }

    public List<Mobile> getFavoriteMobileByPriceAscending() {
        List<Mobile> results = new ArrayList<>();
        RealmResults<Mobile> mobiles = realm.where(Mobile.class).equalTo("isFavorite", true).findAll().sort("price", Sort.ASCENDING);
        results = realm.copyFromRealm(mobiles);

        return results;

    }

    public List<Mobile> getFavoriteMobileByPriceDescending() {
        List<Mobile> results = new ArrayList<>();
        RealmResults<Mobile> mobiles = realm.where(Mobile.class).equalTo("isFavorite", true).findAll().sort("price", Sort.DESCENDING);
        results = realm.copyFromRealm(mobiles);

        return results;

    }

    public List<Mobile> getAllMobilesByRating() {
        List<Mobile> results = new ArrayList<>();
        RealmResults<Mobile> mobiles = realm.where(Mobile.class).findAll().sort("rating", Sort.DESCENDING);
        results = realm.copyFromRealm(mobiles);

        return results;
    }

    public List<Mobile> getFavoriteByRating() {
        List<Mobile> results = new ArrayList<>();
        RealmResults<Mobile> mobiles = realm.where(Mobile.class).equalTo("isFavorite",true).findAll().sort("rating", Sort.DESCENDING);
        results = realm.copyFromRealm(mobiles);

        return results;
    }

}
