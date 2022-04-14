package com.pluff.game;

import com.badlogic.gdx.Game;

public class PluffGame extends Game {


    private adsController adsController;

    public PluffGame(adsController adsController){



        if (adsController != null) {
            this.adsController = adsController;
        } else {
            this.adsController = new DummyAdsController();
        }
    }



	
	@Override
	public void create () {
        if(adsController.isWifiConnected()) { adsController.showBannerAd();}

        Assets.load();//carico tutte le immagini
        this.setScreen(new SplashScreen(this));

        }

    @Override
    public void render() {
        super.render();//chiamo la classe render della classe GameRenderer

         /* adsController.showInterstitialAd(new Runnable() {
                @Override
                public void run() {


                }
            });*/
        }




    @Override
    public void resize(int width, int height) {

    }

	@Override
	public void dispose () {
        super.dispose();
        Assets.dispose();

	}
}
