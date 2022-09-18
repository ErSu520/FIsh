package com.flash.fish.behaviour;

import com.flash.EE.core.object.behaviour.GameObjectBehaviour;
import com.flash.EE.core.system.Eagle;
import com.flash.EE.util.Vector2D;

import com.flash.fish.Main;

public class MoveBehaviour extends GameObjectBehaviour{
	
	private static Vector2D po = new Vector2D(500,15);
	
	private static float SPEED = 150;
	
	@Override
	public void update() {
		
		if(getGameObject().forward(po)>2f) {
			getGameObject().move(SPEED*Eagle.getTime().getDeltaTime());
		}else {
			Eagle.getCurrentScene().destroyGameObject2DNamed(getGameObject().getName());
			Main.gold+=10;
		}
		
		
	}
	
	
}
