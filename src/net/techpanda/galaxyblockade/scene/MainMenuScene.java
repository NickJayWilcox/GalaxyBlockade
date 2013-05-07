package net.techpanda.galaxyblockade.scene;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.util.GLState;

import net.techpanda.galaxyblockade.manager.SceneManager.SceneType;
import net.techpanda.galaxyblockade.scene.BaseScene;

/**
 * @author Mateusz Mysliwiec
 * @author www.matim-dev.com
 * @version 1.0
 */
public class MainMenuScene extends BaseScene implements IOnMenuItemClickListener
{
	private MenuScene menuChildScene;
    private final int MENU_PLAY = 0;
    private final int MENU_CONTINUOUS = 1;
    private final int MENU_OPTIONS = 2;
    private final int MENU_SUPPORT = 3;
    private final int MENU_ABOUT = 4;
    private Sprite menu_background;
    @Override
    public void createScene()
    {
    	createBackground();
    	createMenuChildScene();
    }
    
    private void createBackground()
    {
        attachChild(new Sprite(0, 0, resourceManager.menu_background_region, vbom)
        {
            @Override
            protected void preDraw(GLState pGLState, Camera pCamera) 
            {
                super.preDraw(pGLState, pCamera);
                pGLState.enableDither();
            }
        });
    }


    @Override
    public void onBackKeyPressed()
    {
    	activity.finish();
    	System.exit(0);
    }

    @Override
    public SceneType getSceneType()
    {
    	return SceneType.SCENE_MENU;
    }

    @Override
    public void disposeScene()
    {
		this.detachSelf();
		this.dispose();
    }
    
    private void createMenuChildScene()
    {
        menuChildScene = new MenuScene(camera);
        menuChildScene.setPosition(0, 0);
        
        final IMenuItem playMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_PLAY, resourceManager.play_region, vbom), 1.4f, 1);
        final IMenuItem continuousMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_CONTINUOUS, resourceManager.continuous_region, vbom), 1.4f, 1);
        final IMenuItem optionsMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_OPTIONS, resourceManager.options_region, vbom), 1.4f, 1);   
        final IMenuItem supportMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_SUPPORT, resourceManager.support_region, vbom), 1.4f, 1);   
        final IMenuItem aboutMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_ABOUT, resourceManager.about_region, vbom), 1.4f, 1);
        
    	
        menuChildScene.addMenuItem(playMenuItem);
        menuChildScene.addMenuItem(continuousMenuItem);
        menuChildScene.addMenuItem(optionsMenuItem);
        menuChildScene.addMenuItem(supportMenuItem);
        menuChildScene.addMenuItem(aboutMenuItem);
        
        menuChildScene.buildAnimations();
        menuChildScene.setBackgroundEnabled(false);
        
        //playMenuItem.setPosition(300, 300);
       // optionsMenuItem.setPosition(300, 300);
        
        menuChildScene.setOnMenuItemClickListener(this);
        
        setChildScene(menuChildScene);
    }

	@Override
	public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem, float pMenuItemLocalX, float pMenuItemLocalY)
	{
	        switch(pMenuItem.getID())
	        {
	        case MENU_PLAY:
	            return true;
	        case MENU_CONTINUOUS:
	            return true;
	        case MENU_OPTIONS:
	            return true;
	        case MENU_SUPPORT:
	            return true;
	        case MENU_ABOUT:
	            return true;
	        default:
	            return false;
	    }
	}
}