package com.pluff.game;

/**
 * Created by skynet on 15/05/2015.
 */
public class DummyAdsController implements adsController {
    @Override
    public void showBannerAd() {

    }

    @Override
    public void hideBannerAd() {

    }

    public void showInterstitialAd(Runnable then) {

    }

    @Override
    public boolean isWifiConnected() {
        return false;
    }
}
