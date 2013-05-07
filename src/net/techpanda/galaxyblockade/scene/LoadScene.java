package net.techpanda.galaxyblockade.scene;


import org.andengine.entity.scene.background.Background;
import org.andengine.entity.text.Text;
import org.andengine.util.color.Color;

import net.techpanda.galaxyblockade.scene.BaseScene;
import net.techpanda.galaxyblockade.manager.SceneManager.SceneType;

public class LoadScene extends BaseScene
{
	@Override
	public void createScene()
	{
	    setBackground(new Background(Color.WHITE));
	    attachChild(new Text(0, 0, resourceManager.font, "Loading...", vbom));
	}

    @Override
    public void onBackKeyPressed()
    {
        return;
    }

    @Override
    public SceneType getSceneType()
    {
        return SceneType.SCENE_LOADING;
    }

    @Override
    public void disposeScene()
    {

    }
}