package com.flash.fish.behaviour;

import com.flash.EE.core.object.behaviour.GameObjectBehaviour;
import com.flash.EE.core.system.Eagle;
import com.flash.EE.input.Input;
import com.flash.EE.ui.UIText;

import com.flash.fish.Main;

public class GunBehaviour extends GameObjectBehaviour{
	
	@Override
	public void update() {
		
		if(Input.getKeyboardInput().getHorizontal()==1) {
			if(getGameObject().getTransform().getRotation()+180f*Eagle.getTime().getDeltaTime()<90) {
				getGameObject().getTransform().rotate(180f*Eagle.getTime().getDeltaTime());
			}
		}else if(Input.getKeyboardInput().getHorizontal()==-1) {
			if(getGameObject().getTransform().getRotation()-180f*Eagle.getTime().getDeltaTime()>-90) {
				getGameObject().getTransform().rotate(-180f*Eagle.getTime().getDeltaTime());
			}
		}
		
		UIText text = (UIText) Eagle.getCurrentScene().findUIViewNamed("text");
		text.setText(Main.gold+"");
		
	}
	
}
