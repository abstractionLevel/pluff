package com.pluff.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;


/**
 * Created by sky on 09/02/2015.FF
 */
///////////////////FIX TO POSITION MATTONI
/*classe per renderizzare gli oggeti nel gioco*/
public class GameRenderer implements Screen {
    /////////////////////
    ShapeRenderer shapeRenderer;
    Rectangle rectangle;

    TextureRegion textureRegion;
    Texture texture1;
    Sprite sprite1,sprite2,sprite3,sprite4,sprite5,sprite6,sprite7,sprite8,sprite9,sprite10,spritePalla,spriteTerra;
    ////////////////////
    Game game;
    Sound soundJump,dead;
    Texture texture;
    ArrayList<Sprite> sprites = new ArrayList<Sprite>();
    boolean ritornoP;

    float addBody,sizeMaatone,velMattone,massa,artitrio,muroDestro,muroSinistro;
    //BOX2D////////////////////////]
    SpriteBatch batch;
    TextureRegion win;
    Sprite sprite,spriteUovo;

    World worldBox2d;
    Body body,bodyTerra,body4,body5,bodyMaatone4,bodyMaatone3,bodyMaatone2,bodyMaatone1,bodyMaatone5,bodyMaatone6,bodyMaatone7,bodyMaatone8,bodyMaatone9,bodyMaatone10,bodyPalla,bodyUovo;
;
    Box2DDebugRenderer debugRenderer;
    Matrix4 debugMatrix;
    OrthographicCamera camera,cameraFissa;
    final float PIXELS_TO_METERS = 100f;
    ///////////////////////////////

    boolean touch=false,cant_modify1 = false,cant_modify2 = false,cant_modify3 = false,cant_modify4 =false,cant_modify5=false,cant_modify6=false,cant_modify7=false,cant_modify8=false,cant_modify9=false,cant_modify10=false,jumpCollide=false;

    //LIBGDX
    Bird bird;//oggetto bird Della classe Bird

    GameWorld world;//oggetto world della classe GameWorld
    OrthographicCamera mStageCamera, mFixedCamera;//oggetto camera Della Classe OrrthographicCamera
    SpriteBatch spriteBatch;
    TextureRegion birdText, mattoneText;
    ScrollHander scrollHander;
    Mattone mattone, mattone2, mattone3, mattone4,mattone5,mattone6,mattone7,mattone8,mattone9,mattone10,palla;


    float screenHeight,getScreenWidth;

    GameWorld gameWorld;


    public int score = 10;
    public boolean isScored = false;

    // Tween stuff
    private TweenManager manager;
    private Value alpha = new Value();


    private Color transitionColor;

    // Game Assets
    private TextureRegion  terra;

    int gameHeight;//variabile per l altezza del gioco
    int midPointY;//variabile per la posizione y

    public GameState currentState;

    public  GameStateSound currentStateSound;


    public enum GameState {
        READY, GAMEOVER,HIGHSCORE,WIN
    }

    public enum GameStateSound {
        DEAD,NODEAD
    }


    public GameRenderer(Game game,GameWorld world, int gameHeight, int midPointY) {


        ///////////////////////////
        currentStateSound = GameStateSound.NODEAD;
        screenHeight = gameHeight;
        getScreenWidth = Gdx.graphics.getWidth();
        muroDestro = 136;
        muroSinistro = 0;

        texture1 = new Texture("nuvola1.png");
        textureRegion = new TextureRegion(texture1,0,0,16,10);
        textureRegion.flip(false,true);
        sprite4 = new Sprite(textureRegion);
        sprite3 = new Sprite(textureRegion);
        sprite2 = new Sprite(textureRegion);
        sprite1 = new Sprite(textureRegion);
        sprite5 = new Sprite(textureRegion);
        sprite6 = new Sprite(textureRegion);
        sprite7 = new Sprite(textureRegion);
        sprite8 = new Sprite(textureRegion);
        sprite9 = new Sprite(textureRegion);
        sprite10 = new Sprite(textureRegion);

        //////////////////////////

        texture = new Texture(Gdx.files.internal("uovo3.png"));

        this.game = game;
        addBody = 500;
        sizeMaatone = 10;
        massa = 10000000;
        velMattone = 1;
        artitrio = 1000;

        ////////////////////box2d
        batch = new SpriteBatch();

        bird = world.getBird();

        spriteUovo = new Sprite(texture);
        spriteUovo.setPosition(bird.getX(),bird.getY());
        spriteUovo.setScale(0.12f,0.12f);

        spritePalla = new Sprite(texture);
        spritePalla.setScale(spriteUovo.getWidth()/8,spriteUovo.getHeight()/8);

        scrollHander = world.getScrollHander();//inizializzo l oggetto scrollHandler dalla GameWorld


        mattone4 = scrollHander.getMattone4();
        mattone3 = scrollHander.getMattone3();
        mattone2 = scrollHander.getMattone2();
        mattone = scrollHander.getMattone1();
        mattone5 = scrollHander.getMattone5();
        mattone6 = scrollHander.getMattone6();
        mattone7 = scrollHander.getMattone7();
        mattone8 = scrollHander.getMattone8();
        mattone9 = scrollHander.getMattone9();
        mattone10 = scrollHander.getMattone10();
        palla = scrollHander.getPalla();


        worldBox2d = new World(new Vector2(0, 9.81f),true);


        ///COLLISIONI
        createCollisionListener();
        ///CREO LA GALLINA
        creoBird();

        //rCREO L ERBA
        creoErba();
        ///CREO IL Matttone
        prendiPalla();
        creoMattone();
        creoMattone2();
        creoMattone3();
        creoMattone4();
        creoMattone5();
        creoMattone6();
        creoMattone7();
        creoMattone8();
        creoMattone9();
        creoMattone10();



        ////CREO IL LATO DETRO
        creoLatoDestro();
        ////lCREO IL LATO SINISTRO
        creoLatoSinistro();
        debugRenderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics. getHeight());
        spriteBatch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(true, 136, gameHeight);//setto la fotocamera su larghezza 136 e l altezza dipende dal dispositivo

        /////////////////////////

        currentState = GameState.READY;
        gameWorld = new GameWorld();

        this.world = world;//mi serve pre prendere l oggetto bird che ho creato nella Classe GameWorld
        this.gameHeight = gameHeight;//l altezza del dispaly che gli passo alla camera
        this.midPointY = midPointY;//

        spriteBatch = new SpriteBatch();
        mStageCamera = new OrthographicCamera();//per la camera in movimento
        mFixedCamera = new OrthographicCamera();//per la camera fissa
        cameraFissa = new OrthographicCamera();//per la camera fissa
        mFixedCamera.setToOrtho(true, 136, gameHeight);
        mStageCamera.setToOrtho(true, 136, gameHeight);//setto la fotocamera su larghezza 136 e l altezza dipende dal dispositivo
        cameraFissa.setToOrtho(true,136,gameHeight);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);



        /////CARICO LE TEXTURE E GLI OGGETTI
        initAssets();//carico le immagini
        initGameObject();//carico gli oggetti
        setupTweens();

        transitionColor = new Color();
        prepareTransition(255, 255, 255, .5f);





    }



    private void createCollisionListener() {
        worldBox2d.setContactListener(new ContactListener() {

            @Override
            public void beginContact(Contact contact) {

                if((contact.getFixtureA().getBody().getUserData()=="count"&& contact.getFixtureB().getBody().getUserData()=="mattone4") ) {
                    if ((bird.getY()<=mattone4.getY())){
                        jumpCollide = true;
                        if(cant_modify4 == false) {
                            addScore(-1);
                            cant_modify4=true;
                        }

                    }
                }
                else  if((contact.getFixtureA().getBody().getUserData()=="count"&& contact.getFixtureB().getBody().getUserData()=="mattone3")) {
                    if ((bird.getY()<=mattone3.getY())){
                        jumpCollide = true;
                        if(cant_modify3 == false) {
                            addScore(-1);
                            cant_modify3=true;
                        }
                    }
                }
               else if((contact.getFixtureA().getBody().getUserData()=="count"&& contact.getFixtureB().getBody().getUserData()=="mattone2")) {
                    if ((bird.getY()<=mattone2.getY())){
                        jumpCollide = true;
                        if(cant_modify2 == false) {
                            addScore(-1);
                            cant_modify2=true;
                        }
                    }
                }
                else if((contact.getFixtureA().getBody().getUserData()=="count"&& contact.getFixtureB().getBody().getUserData()=="mattone1")) {
                    if ((bird.getY()<=mattone.getY())){
                        jumpCollide = true;
                        if(cant_modify1 == false) {
                            addScore(-1);
                            cant_modify1=true;
                        }
                    }
                }
                else if((contact.getFixtureA().getBody().getUserData()=="count"&& contact.getFixtureB().getBody().getUserData()=="mattone5")) {
                    if ((bird.getY()<=mattone5.getY())){
                        jumpCollide = true;
                        if(cant_modify5 == false) {
                            addScore(-1);
                            cant_modify5=true;
                        }
                    }
                }
                else if((contact.getFixtureA().getBody().getUserData()=="count"&& contact.getFixtureB().getBody().getUserData()=="mattone6")) {
                    if ((bird.getY()<=mattone6.getY())){
                        jumpCollide = true;
                        if(cant_modify6 == false) {
                            addScore(-1);
                            cant_modify6=true;
                        }
                    }
                }
                else if((contact.getFixtureA().getBody().getUserData()=="count"&& contact.getFixtureB().getBody().getUserData()=="mattone7")) {
                    if ((bird.getY()<=mattone7.getY())){
                        jumpCollide = true;
                        if(cant_modify7 == false) {
                            addScore(-1);
                            cant_modify7=true;
                        }
                    }
                }
                else if((contact.getFixtureA().getBody().getUserData()=="count"&& contact.getFixtureB().getBody().getUserData()=="mattone8")) {
                    if ((bird.getY()<=mattone8.getY())){
                        jumpCollide = true;
                        if(cant_modify8 == false) {
                            addScore(-1);
                            cant_modify8=true;
                        }
                    }
                }
                else if((contact.getFixtureA().getBody().getUserData()=="count"&& contact.getFixtureB().getBody().getUserData()=="mattone9")) {
                    if ((bird.getY()<=mattone9.getY())){
                        jumpCollide = true;
                        if(cant_modify9== false) {
                            addScore(-1);
                            cant_modify9=true;
                        }
                    }
                }
                else if((contact.getFixtureA().getBody().getUserData()=="count"&& contact.getFixtureB().getBody().getUserData()=="mattone10")) {
                    if ((bird.getY()<=mattone10.getY())){
                        jumpCollide = true;
                        if(cant_modify10 == false) {
                            addScore(-1);
                            cant_modify10=true;
                        }
                    }
                }
                if((contact.getFixtureA().getBody().getUserData()=="count"&& contact.getFixtureB().getBody().getUserData()=="terra")) {

                    jumpCollide = true;
                }

                ////PALLA
                if((contact.getFixtureA().getBody().getUserData()=="palla"&& contact.getFixtureB().getBody().getUserData()=="sinistro")) {

                    bodyPalla.setLinearVelocity(velMattone+2,0);

                }
                if((contact.getFixtureA().getBody().getUserData()=="palla"&& contact.getFixtureB().getBody().getUserData()=="destro")) {

                    bodyPalla.setLinearVelocity(-velMattone-2,0);

                }
                if((contact.getFixtureA().getBody().getUserData()=="count"&& contact.getFixtureB().getBody().getUserData()=="palla")) {

                        currentState = GameState.WIN;

                }

                ////mattone 10
                if((contact.getFixtureA().getBody().getUserData()=="mattone10"&& contact.getFixtureB().getBody().getUserData()=="sinistro")) {
                    bodyMaatone10.setLinearVelocity(velMattone,0);

                }
                if((contact.getFixtureA().getBody().getUserData()=="mattone10"&& contact.getFixtureB().getBody().getUserData()=="destro")) {
                    bodyMaatone10.setLinearVelocity(-velMattone,0);

                }
                ////mattone 9
                if((contact.getFixtureA().getBody().getUserData()=="mattone9"&& contact.getFixtureB().getBody().getUserData()=="sinistro")) {
                    bodyMaatone9.setLinearVelocity(velMattone+1,0);

                }
                if((contact.getFixtureA().getBody().getUserData()=="mattone9"&& contact.getFixtureB().getBody().getUserData()=="destro")) {
                    bodyMaatone9.setLinearVelocity(-velMattone-1,0);

                }
                ////mattone 8
                if((contact.getFixtureA().getBody().getUserData()=="mattone8"&& contact.getFixtureB().getBody().getUserData()=="sinistro")) {
                    bodyMaatone8.setLinearVelocity(velMattone+1,0);

                }
                if((contact.getFixtureA().getBody().getUserData()=="mattone8"&& contact.getFixtureB().getBody().getUserData()=="destro")) {
                    bodyMaatone8.setLinearVelocity(-velMattone-1,0);

                }
                ////mattone 7
                if((contact.getFixtureA().getBody().getUserData()=="mattone7"&& contact.getFixtureB().getBody().getUserData()=="sinistro")) {
                    bodyMaatone7.setLinearVelocity(velMattone+0.50f,0);

                }
                if((contact.getFixtureA().getBody().getUserData()=="mattone7"&& contact.getFixtureB().getBody().getUserData()=="destro")) {
                    bodyMaatone7.setLinearVelocity(-velMattone-0.50f,0);

                }
                ////mattone 6
                if((contact.getFixtureA().getBody().getUserData()=="mattone6"&& contact.getFixtureB().getBody().getUserData()=="sinistro")) {
                    bodyMaatone6.setLinearVelocity(velMattone,0);

                }
                if((contact.getFixtureA().getBody().getUserData()=="mattone6"&& contact.getFixtureB().getBody().getUserData()=="destro")) {
                    bodyMaatone6.setLinearVelocity(-velMattone,0);

                }
                ////mattone 5
                if((contact.getFixtureA().getBody().getUserData()=="mattone5"&& contact.getFixtureB().getBody().getUserData()=="sinistro")) {
                    bodyMaatone5.setLinearVelocity(velMattone+1,0);

                }
                if((contact.getFixtureA().getBody().getUserData()=="mattone5"&& contact.getFixtureB().getBody().getUserData()=="destro")) {
                    bodyMaatone5.setLinearVelocity(-velMattone-1,0);

                }
                ////mattone 4
                if((contact.getFixtureA().getBody().getUserData()=="mattone4"&& contact.getFixtureB().getBody().getUserData()=="sinistro")) {

                    bodyMaatone4.setLinearVelocity(velMattone,0);

                }
                if((contact.getFixtureA().getBody().getUserData()=="mattone4"&& contact.getFixtureB().getBody().getUserData()=="destro")) {
                    bodyMaatone4.setLinearVelocity(-velMattone,0);

                }
                ///mattone 3
                if((contact.getFixtureA().getBody().getUserData()=="mattone3"&& contact.getFixtureB().getBody().getUserData()=="sinistro")) {

                    bodyMaatone3.setLinearVelocity(velMattone,0);
                }
                if((contact.getFixtureA().getBody().getUserData()=="mattone3"&& contact.getFixtureB().getBody().getUserData()=="destro")) {
                    bodyMaatone3.setLinearVelocity(-velMattone,0);
                }
                /////mattone 2
                if((contact.getFixtureA().getBody().getUserData()=="mattone2"&& contact.getFixtureB().getBody().getUserData()=="sinistro")) {

                    bodyMaatone2.setLinearVelocity(velMattone+1,0);
                }
                if((contact.getFixtureA().getBody().getUserData()=="mattone2"&& contact.getFixtureB().getBody().getUserData()=="destro")) {
                    bodyMaatone2.setLinearVelocity(-velMattone-1,0);
                }
                /////matttone 1
                if((contact.getFixtureA().getBody().getUserData()=="mattone1"&& contact.getFixtureB().getBody().getUserData()=="sinistro")) {
                    bodyMaatone1.setLinearVelocity(+0.70f,0);
                }
                if((contact.getFixtureA().getBody().getUserData()=="mattone1"&& contact.getFixtureB().getBody().getUserData()=="destro")) {
                    bodyMaatone1.setLinearVelocity(-+0.70f,0);
                }

        }


            @Override
            public void endContact(Contact contact) {
                Fixture fixtureA = contact.getFixtureA();
                Fixture fixtureB = contact.getFixtureB();
            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {


            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

            }

        });
    }


    public void creoBird() {

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(bird.getX() /PIXELS_TO_METERS,bird.getY()/ PIXELS_TO_METERS);
        body = worldBox2d.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(bird.getWidth()/4 / PIXELS_TO_METERS, bird.getHeight() /4 / PIXELS_TO_METERS);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        body.createFixture(fixtureDef);
        body.setUserData("count");
        shape.dispose();
    }

    public void creoUovo() {

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(spriteUovo.getX() /PIXELS_TO_METERS,(spriteUovo.getY()+18)/ PIXELS_TO_METERS);
        bodyUovo = worldBox2d.createBody(bodyDef);
        bodyUovo.setUserData(spriteUovo);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(spriteUovo.getWidth()/2 / PIXELS_TO_METERS,spriteUovo.getHeight()/2 / PIXELS_TO_METERS);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 200000;
        bodyUovo.setGravityScale(0);
        bodyUovo.createFixture(fixtureDef);
        bodyUovo.setUserData("uovo");
         shape.dispose();
    }

    public void creoErba() {
        BodyDef bodyDef4 = new BodyDef();
        bodyDef4.type = BodyDef.BodyType.StaticBody;
        bodyDef4.position.set(0 /PIXELS_TO_METERS,(screenHeight-26)/ PIXELS_TO_METERS);
        bodyTerra = worldBox2d.createBody(bodyDef4);
        PolygonShape shape4 = new PolygonShape();
        shape4.setAsBox(200 / PIXELS_TO_METERS,1  / PIXELS_TO_METERS);
        FixtureDef fixtureDef4 = new FixtureDef();
        fixtureDef4.shape = shape4;
        bodyTerra.setUserData("terra");
        bodyTerra.createFixture(fixtureDef4);
        shape4.dispose();
    }


    public void prendiPalla() {

        BodyDef bodyDef3 = new BodyDef();
        bodyDef3.type = BodyDef.BodyType.DynamicBody;
        bodyDef3.position.set((muroDestro-palla.getWidth()/4) /PIXELS_TO_METERS,palla.getY()/ PIXELS_TO_METERS);
        bodyPalla = worldBox2d.createBody(bodyDef3);

        PolygonShape shape3 = new PolygonShape();
        shape3.setAsBox(spritePalla.getWidth()/4 / PIXELS_TO_METERS, spritePalla.getHeight()/4/ PIXELS_TO_METERS);
        FixtureDef fixtureDef3 = new FixtureDef();
        fixtureDef3.shape = shape3;
        fixtureDef3.density = massa;
        fixtureDef3.restitution = 0;
        fixtureDef3.friction = artitrio;
        bodyPalla.setGravityScale(0);
        bodyPalla.setUserData("palla");
        bodyPalla.createFixture(fixtureDef3);
        shape3.dispose();
    }

    public void creoMattone10() {


        BodyDef bodyDef3 = new BodyDef();
        bodyDef3.type = BodyDef.BodyType.DynamicBody;
        bodyDef3.position.set((muroDestro-mattone10.getWidth()/4) /PIXELS_TO_METERS,mattone10.getY()/ PIXELS_TO_METERS);
        bodyMaatone10 = worldBox2d.createBody(bodyDef3);

        PolygonShape shape3 = new PolygonShape();
        shape3.setAsBox(sprite10.getWidth()/4/ PIXELS_TO_METERS, sprite10.getHeight()/4/ PIXELS_TO_METERS);
        FixtureDef fixtureDef3 = new FixtureDef();
        fixtureDef3.shape = shape3;
        fixtureDef3.density = 10000;
        fixtureDef3.restitution = 0;
        fixtureDef3.friction = artitrio;
        bodyMaatone10.setGravityScale(0);
        bodyMaatone10.setUserData("mattone10");
        bodyMaatone10.createFixture(fixtureDef3);
        shape3.dispose();
    }

    public void creoMattone9() {


        BodyDef bodyDef3 = new BodyDef();
        bodyDef3.type = BodyDef.BodyType.DynamicBody;
        bodyDef3.position.set((muroSinistro+mattone9.getWidth()/4) /PIXELS_TO_METERS,mattone9.getY()/ PIXELS_TO_METERS);
        bodyMaatone9 = worldBox2d.createBody(bodyDef3);

        PolygonShape shape3 = new PolygonShape();
        shape3.setAsBox(sprite9.getWidth()/4 / PIXELS_TO_METERS, sprite9.getHeight()/4 / PIXELS_TO_METERS);
        FixtureDef fixtureDef3 = new FixtureDef();
        fixtureDef3.shape = shape3;
        fixtureDef3.density = massa;
        fixtureDef3.restitution = 0;
        fixtureDef3.friction = artitrio;
        bodyMaatone9.setGravityScale(0);
        bodyMaatone9.setUserData("mattone9");
        bodyMaatone9.createFixture(fixtureDef3);
        shape3.dispose();
    }

    public void creoMattone8() {


        BodyDef bodyDef3 = new BodyDef();
        bodyDef3.type = BodyDef.BodyType.DynamicBody;
        bodyDef3.position.set((muroDestro-mattone5.getWidth()/4)/PIXELS_TO_METERS,mattone8.getY()/ PIXELS_TO_METERS);
        bodyMaatone8 = worldBox2d.createBody(bodyDef3);

        PolygonShape shape3 = new PolygonShape();
        shape3.setAsBox(sprite8.getWidth() /4/ PIXELS_TO_METERS, sprite8.getHeight()/4 / PIXELS_TO_METERS);
        FixtureDef fixtureDef3 = new FixtureDef();
        fixtureDef3.shape = shape3;
        fixtureDef3.density = massa;;
        fixtureDef3.restitution = 0;
        fixtureDef3.friction = artitrio;
        bodyMaatone8.setGravityScale(0);
        bodyMaatone8.setUserData("mattone8");
        bodyMaatone8.createFixture(fixtureDef3);
        shape3.dispose();
    }

    public void creoMattone7() {


        BodyDef bodyDef3 = new BodyDef();
        bodyDef3.type = BodyDef.BodyType.DynamicBody;
        bodyDef3.position.set((muroSinistro+mattone5.getWidth()/4)/PIXELS_TO_METERS,mattone7.getY()/ PIXELS_TO_METERS);
        bodyMaatone7 = worldBox2d.createBody(bodyDef3);

        PolygonShape shape3 = new PolygonShape();
        shape3.setAsBox(sprite7.getWidth()/4 / PIXELS_TO_METERS, sprite7.getHeight() /4 / PIXELS_TO_METERS);
        FixtureDef fixtureDef3 = new FixtureDef();
        fixtureDef3.shape = shape3;
        fixtureDef3.density = 10000;
        fixtureDef3.restitution = 0;
        fixtureDef3.friction = artitrio;
        bodyMaatone7.setGravityScale(0);
        bodyMaatone7.setUserData("mattone7");
        bodyMaatone7.createFixture(fixtureDef3);
        shape3.dispose();
    }


     public void creoMattone6() {


        BodyDef bodyDef3 = new BodyDef();
        bodyDef3.type = BodyDef.BodyType.DynamicBody;
        bodyDef3.position.set((muroDestro-mattone5.getWidth()/4)/PIXELS_TO_METERS,mattone6.getY()/ PIXELS_TO_METERS);
        bodyMaatone6 = worldBox2d.createBody(bodyDef3);

        PolygonShape shape3 = new PolygonShape();
        shape3.setAsBox(sprite6.getWidth() /4/ PIXELS_TO_METERS, sprite6.getHeight()/4 / PIXELS_TO_METERS);
        FixtureDef fixtureDef3 = new FixtureDef();
        fixtureDef3.shape = shape3;
        fixtureDef3.density = massa;
        fixtureDef3.restitution = 0;
        fixtureDef3.friction = artitrio;
        bodyMaatone6.setGravityScale(0);
        bodyMaatone6.setUserData("mattone6");
        bodyMaatone6.createFixture(fixtureDef3);
        shape3.dispose();


    }

    public void creoMattone5() {



        BodyDef bodyDef3 = new BodyDef();
        bodyDef3.type = BodyDef.BodyType.DynamicBody;
        bodyDef3.position.set((muroSinistro+mattone5.getWidth()/4) /PIXELS_TO_METERS,mattone5.getY()/ PIXELS_TO_METERS);
        bodyMaatone5 = worldBox2d.createBody(bodyDef3);

        PolygonShape shape3 = new PolygonShape();
        shape3.setAsBox(sprite5.getWidth() /4  / PIXELS_TO_METERS, sprite5.getHeight()/4  / PIXELS_TO_METERS);
        FixtureDef fixtureDef3 = new FixtureDef();
        fixtureDef3.shape = shape3;
        fixtureDef3.density = massa;
        fixtureDef3.restitution = 0;
        fixtureDef3.friction = artitrio;
        bodyMaatone5.setGravityScale(0);
        bodyMaatone5.setUserData("mattone5");
        bodyMaatone5.createFixture(fixtureDef3);
        shape3.dispose();
    }

    public void creoMattone4() {


        BodyDef bodyDef3 = new BodyDef();
        bodyDef3.type = BodyDef.BodyType.DynamicBody;
        bodyDef3.position.set((muroDestro-mattone4.getWidth()/4) /PIXELS_TO_METERS,mattone4.getY()/ PIXELS_TO_METERS);
        bodyMaatone4 = worldBox2d.createBody(bodyDef3);

        PolygonShape shape3 = new PolygonShape();
        shape3.setAsBox(sprite4.getWidth()/4  / PIXELS_TO_METERS, sprite4.getHeight()/4/ PIXELS_TO_METERS);
        FixtureDef fixtureDef3 = new FixtureDef();
        fixtureDef3.shape = shape3;
        fixtureDef3.density =10000;
        fixtureDef3.restitution = 0;
        fixtureDef3.friction = artitrio;
        bodyMaatone4.setGravityScale(0);
        bodyMaatone4.setUserData("mattone4");
        bodyMaatone4.createFixture(fixtureDef3);
        shape3.dispose();

    }
    public void creoMattone3() {
        BodyDef bodyDef3 = new BodyDef();
        bodyDef3.type = BodyDef.BodyType.DynamicBody;
        bodyDef3.position.set((muroSinistro + mattone3.getWidth()/4) /PIXELS_TO_METERS,mattone3.getY()/ PIXELS_TO_METERS);
        bodyMaatone3 = worldBox2d.createBody(bodyDef3);

        PolygonShape shape3 = new PolygonShape();
        shape3.setAsBox(sprite3.getWidth()/4 / PIXELS_TO_METERS, sprite3.getHeight()/4 / PIXELS_TO_METERS);
        FixtureDef fixtureDef3 = new FixtureDef();
        fixtureDef3.shape = shape3;
        fixtureDef3.density = 10000;
        fixtureDef3.restitution = 0;
        fixtureDef3.friction = artitrio;
        bodyMaatone3.setGravityScale(0);
        bodyMaatone3.setUserData("mattone3");
        bodyMaatone3.createFixture(fixtureDef3);
        shape3.dispose();
    }
    public void creoMattone2() {
        BodyDef bodyDef3 = new BodyDef();
        bodyDef3.type = BodyDef.BodyType.DynamicBody;
        bodyDef3.position.set((muroDestro-mattone2.getWidth()/4) /PIXELS_TO_METERS,mattone2.getY()/ PIXELS_TO_METERS);
        bodyMaatone2 = worldBox2d.createBody(bodyDef3);

        PolygonShape shape3 = new PolygonShape();
        shape3.setAsBox(sprite2.getWidth() /4 / PIXELS_TO_METERS, sprite2.getHeight()/4 / PIXELS_TO_METERS);
        FixtureDef fixtureDef3 = new FixtureDef();
        fixtureDef3.shape = shape3;
        fixtureDef3.density = massa;
        fixtureDef3.restitution = 0;
        fixtureDef3.friction = artitrio;
        bodyMaatone2.setGravityScale(0);
        bodyMaatone2.setUserData("mattone2");
        bodyMaatone2.createFixture(fixtureDef3);
        shape3.dispose();
    }
    public void creoMattone() {
        BodyDef bodyDef3 = new BodyDef();
        bodyDef3.type = BodyDef.BodyType.DynamicBody;
        bodyDef3.position.set((muroSinistro+mattone.getWidth()/4) /PIXELS_TO_METERS,mattone.getY()/ PIXELS_TO_METERS);
        bodyMaatone1 = worldBox2d.createBody(bodyDef3);

        PolygonShape shape3 = new PolygonShape();
        shape3.setAsBox(sprite1.getWidth()/2 / PIXELS_TO_METERS, sprite1.getHeight()/4 / PIXELS_TO_METERS);
        FixtureDef fixtureDef3 = new FixtureDef();
        fixtureDef3.shape = shape3;
        fixtureDef3.density = massa;
        fixtureDef3.restitution = 0;
        fixtureDef3.friction = artitrio;
        bodyMaatone1.setGravityScale(0);
        bodyMaatone1.setUserData("mattone1");
        bodyMaatone1.createFixture(fixtureDef3);
        shape3.dispose();
    }


    public void creoLatoDestro() {
        BodyDef bodyDef4 = new BodyDef();
        bodyDef4.type = BodyDef.BodyType.StaticBody;
        bodyDef4.position.set(136 /PIXELS_TO_METERS,mattone4.getY()/ PIXELS_TO_METERS);

        body4 = worldBox2d.createBody(bodyDef4);

        PolygonShape shape4 = new PolygonShape();
        shape4.setAsBox(1 / PIXELS_TO_METERS, addBody  / PIXELS_TO_METERS);

        FixtureDef fixtureDef4 = new FixtureDef();
        fixtureDef4.shape = shape4;


        body4.setUserData("destro");
        body4.createFixture(fixtureDef4);
        shape4.dispose();
    }



    public void creoLatoSinistro() {
        BodyDef bodyDef5 = new BodyDef();
        bodyDef5.type = BodyDef.BodyType.StaticBody;
        bodyDef5.position.set(muroSinistro /PIXELS_TO_METERS,mattone4.getY()/ PIXELS_TO_METERS);

        body5 = worldBox2d.createBody(bodyDef5);

        PolygonShape shape5 = new PolygonShape();
        shape5.setAsBox(muroSinistro / PIXELS_TO_METERS, addBody/PIXELS_TO_METERS  );

        FixtureDef fixtureDef5 = new FixtureDef();
        fixtureDef5.shape = shape5;
        body5.setUserData("sinistro");

        body5.createFixture(fixtureDef5);
        shape5.dispose();
    }

    public void updateScore() {
        // Convert integer into String
        String score = getScore() + "";

        // Draw shadow first
        Assets.shadow.draw(batch, "-" + getScore(), (136 / 2)
                - (3 * score.length()), 12);
        // Draw text
        Assets.font.draw(batch, "-" + getScore(), (136 / 2)
                - (3 * score.length() - 1), 11);


        Assets.font.draw(batch, "Beta test", 0 , 10);
        Assets.shadow.draw(batch, "beta test",0 , 10);
        // Draw text

    }

    private void setupTweens() {
        Tween.registerAccessor(Value.class, new ValueAccessor());
        manager = new TweenManager();
        Tween.to(alpha, -1, .5f).target(0).ease(TweenEquations.easeOutQuad)
                .start(manager);
    }


    public void initGameObject() {//inizializzo gli oggetti sullo chermo
        bird = world.getBird(); //instanzion l oggetto bird che ho instanziato nella classe GameWorld

        scrollHander = world.getScrollHander();//inizializzo l oggetto scrollHandler dalla GameWorld
        mattone = scrollHander.getMattone1();//inizializzo l oggetto mattone dalla classe ScrollHandler
        mattone2 = scrollHander.getMattone2();
        mattone3 = scrollHander.getMattone3();
        mattone4 = scrollHander.getMattone4();


    }

    public void initAssets() {//carico le immagini


        win = Assets.win;
        dead = Assets.dead;
        birdText = Assets.bird;
        terra = Assets.teraText;
        soundJump = Assets.flap;
        spriteTerra = new Sprite(terra);
        sprite = new Sprite(birdText);
        mattoneText = Assets.mattone;
    }


    @Override
    public void show() {}

    public void drawMattone() {
        batch.draw(sprite10, sprite10.getX(), sprite10.getY(),sprite10.getOriginX(), sprite10.getOriginY(),sprite10.getWidth(), sprite10.getHeight(),sprite10.getScaleX(),sprite10.getScaleY(),sprite10.getRotation());
        batch.draw(sprite9, sprite9.getX(), sprite9.getY(),sprite9.getOriginX(), sprite9.getOriginY(),sprite9.getWidth(), sprite9.getHeight(),sprite9.getScaleX(),sprite9.getScaleY(),sprite9.getRotation());
        batch.draw(sprite8, sprite8.getX(), sprite8.getY(),sprite8.getOriginX(), sprite8.getOriginY(),sprite8.getWidth(), sprite8.getHeight(),sprite8.getScaleX(),sprite8.getScaleY(),sprite8.getRotation());
        batch.draw(sprite7, sprite7.getX(), sprite7.getY(),sprite7.getOriginX(), sprite7.getOriginY(),sprite7.getWidth(), sprite7.getHeight(),sprite7.getScaleX(),sprite7.getScaleY(),sprite7.getRotation());
        batch.draw(sprite6, sprite6.getX(), sprite6.getY(),sprite6.getOriginX(), sprite6.getOriginY(),sprite6.getWidth(), sprite6.getHeight(),sprite6.getScaleX(),sprite6.getScaleY(),sprite6.getRotation());
        batch.draw(sprite5, sprite5.getX(), sprite5.getY(),sprite5.getOriginX(), sprite5.getOriginY(),sprite5.getWidth(), sprite5.getHeight(),sprite5.getScaleX(),sprite5.getScaleY(),sprite5.getRotation());
        batch.draw(sprite4, sprite4.getX(), sprite4.getY(),sprite4.getOriginX(), sprite4.getOriginY(),sprite4.getWidth(), sprite4.getHeight(),sprite4.getScaleX(),sprite4.getScaleY(),sprite4.getRotation());
        batch.draw(sprite3, sprite3.getX(), sprite3.getY(),sprite3.getOriginX(), sprite3.getOriginY(),sprite3.getWidth(), sprite3.getHeight(),sprite3.getScaleX(),sprite3.getScaleY(),sprite3.getRotation());
        batch.draw(sprite2, sprite2.getX(), sprite2.getY(),sprite2.getOriginX(), sprite2.getOriginY(),sprite2.getWidth(), sprite2.getHeight(),sprite2.getScaleX(),sprite2.getScaleY(),sprite2.getRotation());
        batch.draw(sprite1, sprite1.getX(), sprite1.getY(),sprite1.getOriginX(), sprite1.getOriginY(),sprite1.getWidth(), sprite1.getHeight(),sprite1.getScaleX(),sprite1.getScaleY(),sprite1.getRotation());

        batch.draw(spritePalla, spritePalla.getX(), spritePalla.getY(),spritePalla.getOriginX(), spritePalla.getOriginY(),spriteUovo.getWidth(), spriteUovo.getHeight(),spritePalla.getScaleX(),spritePalla.getScaleY(),spritePalla.getRotation());



    }

    public void render(float delta) {//renderezzo sullo schermo
        currentStateSound=GameStateSound.NODEAD;
        //BOX2D///////////////////////////
        worldBox2d.step(1f / 60f, 6, 2);




        bird.setY((body.getPosition().y * PIXELS_TO_METERS) - bird.getHeight() / 2);
        bird.setX((body.getPosition().x * PIXELS_TO_METERS) - bird.getWidth() / 2);


        //Gdx.gl.glClearColor(0.22f, 0.69f, 0.87f, 0);
        Gdx.gl.glClearColor(55 / 255.0f, 80 / 255.0f, 100 / 255.0f, 1);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(mStageCamera.combined);
        batch.begin();
        updateScore();
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect(0,screenHeight-24,spriteTerra.getWidth(),24);
        shapeRenderer.end();




        //////MATTONi ASSOCIATO A BOX2D
        spritePalla.setY((bodyPalla.getPosition().y * PIXELS_TO_METERS) - spritePalla.getHeight() / 2);
        spritePalla.setX((bodyPalla.getPosition().x * PIXELS_TO_METERS) - spritePalla.getWidth() / 2);


        sprite10.setY((bodyMaatone10.getPosition().y * PIXELS_TO_METERS) - (sprite10.getHeight() + 6) / 2);
        sprite10.setX((bodyMaatone10.getPosition().x * PIXELS_TO_METERS) - sprite10.getWidth() / 2);

        sprite9.setY((bodyMaatone9.getPosition().y * PIXELS_TO_METERS) - (sprite9.getHeight() + 6) / 2);
        sprite9.setX((bodyMaatone9.getPosition().x * PIXELS_TO_METERS) - sprite9.getWidth() / 2);

        sprite8.setY((bodyMaatone8.getPosition().y * PIXELS_TO_METERS) - (sprite8.getHeight() + 6) / 2);
        sprite8.setX((bodyMaatone8.getPosition().x * PIXELS_TO_METERS) - sprite8.getWidth() / 2);

        sprite7.setY((bodyMaatone7.getPosition().y * PIXELS_TO_METERS) - (sprite7.getHeight() + 6) / 2);
        sprite7.setX((bodyMaatone7.getPosition().x * PIXELS_TO_METERS) - sprite7.getWidth() / 2);

        sprite6.setY((bodyMaatone6.getPosition().y * PIXELS_TO_METERS) - (sprite6.getHeight() + 6) / 2);
        sprite6.setX((bodyMaatone6.getPosition().x * PIXELS_TO_METERS) - sprite6.getWidth() / 2);

        sprite5.setY((bodyMaatone5.getPosition().y * PIXELS_TO_METERS) - (sprite5.getHeight() + 6) / 2);
        sprite5.setX((bodyMaatone5.getPosition().x * PIXELS_TO_METERS) - sprite5.getWidth() / 2);

        sprite4.setY((bodyMaatone4.getPosition().y * PIXELS_TO_METERS) - (sprite4.getHeight() + 6) / 2);
        sprite4.setX((bodyMaatone4.getPosition().x * PIXELS_TO_METERS) - sprite4.getWidth() / 2);

        sprite3.setY((bodyMaatone3.getPosition().y * PIXELS_TO_METERS) - (sprite3.getHeight() + 6) / 2);
        sprite3.setX((bodyMaatone3.getPosition().x * PIXELS_TO_METERS) - sprite3.getWidth() / 2);


        sprite2.setY((bodyMaatone2.getPosition().y * PIXELS_TO_METERS) - (sprite2.getHeight() + 6) / 2);
        sprite2.setX((bodyMaatone2.getPosition().x * PIXELS_TO_METERS) - sprite2.getWidth() / 2);

        sprite1.setY((bodyMaatone1.getPosition().y * PIXELS_TO_METERS) - (sprite1.getHeight() + 6) / 2);
        sprite1.setX((bodyMaatone1.getPosition().x * PIXELS_TO_METERS) - sprite1.getWidth() / 2);


        sprite10.setRotation((float) Math.toDegrees(bodyMaatone10.getAngle()));
        sprite9.setRotation((float) Math.toDegrees(bodyMaatone9.getAngle()));
        sprite8.setRotation((float) Math.toDegrees(bodyMaatone8.getAngle()));
        sprite7.setRotation((float) Math.toDegrees(bodyMaatone7.getAngle()));
        sprite6.setRotation((float) Math.toDegrees(bodyMaatone6.getAngle()));
        sprite5.setRotation((float) Math.toDegrees(bodyMaatone5.getAngle()));
        sprite4.setRotation((float) Math.toDegrees(bodyMaatone4.getAngle()));
        sprite3.setRotation((float) Math.toDegrees(bodyMaatone3.getAngle()));
        sprite2.setRotation((float) Math.toDegrees(bodyMaatone2.getAngle()));
        sprite1.setRotation((float) Math.toDegrees(bodyMaatone1.getAngle()));

        /////////////////////////////////
        mStageCamera.update();
        if (currentState == GameState.WIN) {

            game.setScreen(new WinScreen(game));
            Assets.victory.play();


        }

        if (isGameOver() || isHighScore()) {

            if (isGameOver()) {
                dead.play();
                game.setScreen(new Lose(game));

            }

        }

        batch.end();


        batch.setProjectionMatrix(camera.combined);
        debugMatrix = batch.getProjectionMatrix().cpy().scale(PIXELS_TO_METERS, PIXELS_TO_METERS, 0);
        batch.begin();


        if (touch == false) {

            Assets.shadow.draw(batch, "Touch me", (136 / 2)- 24 , 75);
            // Draw text
            Assets.font.draw(batch, "Touch me", (136 / 2)- 24 , 75);

        }


        batch.draw(sprite, bird.getX(), bird.getY(), bird.getWidth(), bird.getHeight());
        batch.draw(spriteTerra, spriteTerra.getX(), screenHeight -26, spriteTerra.getWidth(), spriteTerra.getHeight());
        createEgg();
        drawMattone();
        batch.end();

        drawTransition(delta);


        if (bird.getY() +bird.getHeight()/ 2 <= camera.position.y) { /////////////////FIX THE CAM
            camera.update();
            camera.position.set(68, bird.getY() + bird.getHeight() / 2, 0);

        }


        if (Gdx.input.justTouched() && jumpCollide == true) {

            touch = true;
            for (int i = 0; i < 1; i++) {
                spriteUovo.setPosition(bird.getX(), bird.getY());
                creoUovo();
                sprites.add(spriteUovo);

            }

            body.setLinearVelocity(0f, -4);

            soundJump.play();
            jumpCollide = false;
        }

        if (bird.getY() > camera.position.y + 80) {  //PER IL GAMEOVER
            currentState = GameState.GAMEOVER;



        }



            if (Gdx.input.justTouched() && currentState == GameState.GAMEOVER) {
                game.setScreen(new GameScreen(game));

            }
            if (Gdx.input.justTouched() && currentState == GameState.WIN) {


            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {

                body.setLinearVelocity(0f, -4);

            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.S)) {
                body.setLinearVelocity(0f, 0.40f);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                body.setLinearVelocity(-0.40f, 0f);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                body.setLinearVelocity(0.40f, 0f);
            }

            //debugRenderer.render(worldBox2d, debugMatrix);

        }


    public void prepareTransition(int r, int g, int b, float duration) {
        transitionColor.set(r / 255.0f, g / 255.0f, b / 255.0f, 1);
        alpha.setValue(1);
        Tween.registerAccessor(Value.class, new ValueAccessor());
        manager = new TweenManager();
        Tween.to(alpha, -1, duration).target(0).ease(TweenEquations.easeOutQuad).start(manager);
    }

    private void drawTransition(float delta) {
        if (alpha.getValue() > 0) {
            manager.update(delta);
            Gdx.gl.glEnable(GL20.GL_BLEND);
            Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
            Gdx.gl.glDisable(GL20.GL_BLEND);

        }
    }

    public void createEgg() {
        for (int i = 0; i < sprites.size(); i++) {
            spriteUovo.setY((bodyUovo.getPosition().y * PIXELS_TO_METERS) - spriteUovo.getHeight() / 2);
            spriteUovo.setX((bodyUovo.getPosition().x * PIXELS_TO_METERS) -spriteUovo.getWidth() / 2);
            batch.draw(spriteUovo, spriteUovo.getX(), spriteUovo.getY(),spriteUovo.getWidth(),spriteUovo.getHeight());
            spriteUovo.setPosition(spriteUovo.getX(), spriteUovo.getY()+ 0.50f);
            bodyUovo.setLinearVelocity(0,0.20f);

            if (spriteUovo.getY()/PIXELS_TO_METERS > 200/PIXELS_TO_METERS) {
                ritornoP = true;
                sprites.remove(spriteUovo);


            }

            if (ritornoP == true) {
                spriteUovo.setPosition(bird.getX(), bird.getY()+bird.getHeight());
                ritornoP = false;
            }
        }
    }


    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        Assets.victory.dispose();
        soundJump.dispose();
        dead.dispose();


    }


    public int getScore() {
        return score;
    }

    public void addScore(int increment) {
        score += increment;
        isScored=false;
     }

    public boolean isGameOver() {

        return currentState == GameState.GAMEOVER;
    }

    public boolean isHighScore() {
        return currentState == GameState.HIGHSCORE;
    }

}







