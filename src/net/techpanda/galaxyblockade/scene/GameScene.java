package net.techpanda.galaxyblockade.scene;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.camera.hud.HUD;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
//May not need Fixed and Physics
import org.andengine.extension.physics.box2d.FixedStepPhysicsWorld;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.opengl.util.GLState;
import org.andengine.util.HorizontalAlign;
//May not need Badlogic
import com.badlogic.gdx.math.Vector2;

import net.techpanda.galaxyblockade.scene.BaseScene;
import net.techpanda.galaxyblockade.manager.SceneManager;
import net.techpanda.galaxyblockade.manager.SceneManager.SceneType;

public class GameScene extends BaseScene
{
	private Sprite game_interface; 
	private HUD gameHUD;
	private Text scoreText;
    private int score = 0;
    private PhysicsWorld physicsWorld;
	
    @Override
    public void createScene()
    {
    	game_interface = new Sprite(0, 0, resourceManager.game_interface_region, vbom)
    	{
    	    @Override
    	    protected void preDraw(GLState pGLState, Camera pCamera) 
    	    {
    	       super.preDraw(pGLState, pCamera);
    	       pGLState.enableDither();
    	    }
    	};
    	        
    	//splash.setScale(1f);
    	game_interface.setPosition(0, 0);
    	attachChild(game_interface);
    	
    	createHUD();
    }

    @Override
    public void onBackKeyPressed()
    {
    	SceneManager.getInstance().loadMenuScene(engine);
    }

    @Override
    public SceneType getSceneType()
    {
        return SceneType.SCENE_GAME;
    }

    @Override
    public void disposeScene()
    {
        camera.setHUD(null);
        //camera.setCenter(0, 0);

        // TODO code responsible for disposing scene
        // removing all game scene objects.
    }
    
    private void createHUD()
    {
        gameHUD = new HUD();
        
        // SCORE TEXT
        scoreText = new Text(20, 420, resourceManager.font, "Score: 0123456789", new TextOptions(HorizontalAlign.LEFT), vbom);
        scoreText.setSkewCenter(0, 0);    
        scoreText.setText("Score: 0");
        gameHUD.attachChild(scoreText);
        
        camera.setHUD(gameHUD);
    }


    private void addToScore(int i)
    {
        score += i;
        scoreText.setText("Score: " + score);
    }
    
    
    //MAY NOT NEED PHYSICS
    private void createPhysics()
    {
        physicsWorld = new FixedStepPhysicsWorld(60, new Vector2(0, -17), false); 
        registerUpdateHandler(physicsWorld);
    }
}