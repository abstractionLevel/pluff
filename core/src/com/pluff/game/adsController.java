package com.pluff.game;

/**
 * Created by skynet on 15/05/2015.
 */
public interface adsController {

    public void showBannerAd();
    public void hideBannerAd();
    public void showInterstitialAd (Runnable then);
    public boolean isWifiConnected();

}
