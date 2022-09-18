package com.flash.fish.behaviour;

import java.awt.event.KeyEvent;

import com.flash.EE.compnent.physics.CircularCollider;
import com.flash.EE.core.object.behaviour.GameObjectBehaviour;
import com.flash.EE.core.object.gameobject.GameObject2D;
import com.flash.EE.core.object.ui.AbstractUIView;
import com.flash.EE.core.system.Eagle;
import com.flash.EE.input.Input;
import com.flash.EE.input.KeyStatus;

import com.flash.fish.Main;


public class FireBehaviour extends GameObjectBehaviour{
	
	private float FREEZE_TIME = 0.2f;
	
	private long bulletId = 0;
	private float freezeTime = FREEZE_TIME;
	
	private AbstractUIView panel ;
	
	
	@Override
	public void start() {
		panel = Eagle.getCurrentScene().findUIViewNamed("img_panel");
	}
	
	
	@Override
	public void update() {
		
		if(Input.getKeyboardInput().getKeyStatus(KeyEvent.VK_ESCAPE).getStatus()==KeyStatus.PRESS_DOWN) {
			if(panel!=null) {
				if(panel.isEnable()) {
					panel.setEnable(false);
				}else {
					panel.setEnable(true);
				}
			}
		}
		
		if(Input.getKeyboardInput().isTheKeyDown(KeyEvent.VK_J)) {
			if(freezeTime<0) {
				if(Main.gold>0) {
					GameObject2D bullet = Eagle.getCurrentScene().addGameObject2DNamed("bullet"+(this.bulletId++));
					bullet.setTag("bullet");
					bullet.getTransform().setBounds(getGameObject().getTransform().getPosition().getX(), getGameObject().getTransform().getPosition().getY(), 8, 8);
					bullet.getTransform().setRotation(getGameObject().getTransform().getRotation());
					bullet.getSprite2D().setImage("./resource/bullet.png");
					bullet.addBehaviour(new CircularCollider(true,5));
					bullet.addBehaviour(new BulletBehaviour());
					bullet.move(30);
					freezeTime = FREEZE_TIME;
					Main.gold--;
				}
			}
		}
		
		if(freezeTime>-0.1) {
			freezeTime-=Eagle.getTime().getDeltaTime();
		}
	}


}
