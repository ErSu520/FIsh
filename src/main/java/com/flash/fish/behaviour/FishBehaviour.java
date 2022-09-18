package com.flash.fish.behaviour;

import com.flash.EE.compnent.audio.AudioSource;
import com.flash.EE.compnent.physics.RigidBody;
import com.flash.EE.core.object.behaviour.GameObjectBehaviour;
import com.flash.EE.core.object.gameobject.GameObject2D;
import com.flash.EE.core.system.Eagle;

public class FishBehaviour extends GameObjectBehaviour{
	
	public static long gold_id = 0;
	
	public FishBehaviour(int gold_count,float speed , float HP) {
		if(gold_count<1) {
			this.gold_count = 1;
		}else {
			this.gold_count = gold_count;
		}
		if(speed>1) {
			this.speed = speed;
		}else {
			this.speed = 50;
		}
		if(HP>0) {
			this.HP = HP;
		}else {
			this.HP = 100;
		}
	}
	
	//引用物体的刚体
	private RigidBody rigidBody;
	
	@Override
	public void start() {
		//在行为类内可以调用getGameObject()方法来获取该行为所属的物体
		rigidBody = (RigidBody)getGameObject().findBehaviour(RigidBody.class);
	}
	
	private int gold_count = 1;
	
	private float FREEZE_TIME = 0.1f;
	
	private float freezeTime = FREEZE_TIME;
	
	private float speed =50 , HP = 100;
	
	@Override
	public void update() {
		if(freezeTime<=0) {
			if(Math.random()<0.5) {
				getGameObject().getTransform().rotate((float)Math.random()*10);
			}else {
				getGameObject().getTransform().rotate(-(float)Math.random()*10);
			}
			freezeTime = FREEZE_TIME;
		}
		freezeTime-=Eagle.getTime().getDeltaTime();
		rigidBody.move(speed*Eagle.getTime().getDeltaTime());
		
		if(getGameObject().getTransform().getPosition().getX()<-50 || getGameObject().getTransform().getPosition().getX()>1000
				|| getGameObject().getTransform().getPosition().getY()<-50 || getGameObject().getTransform().getPosition().getY()>500) {
			Eagle.getCurrentScene().destroyGameObject2DNamed(getGameObject().getName());
		}
		
		if(this.HP<0) {
			if(Math.random()<0.2) {
				Eagle.getCurrentScene().destroyGameObject2DNamed(getGameObject().getName());
				for(int i=0;i<gold_count;i++) {
					GameObject2D gg = Eagle.getCurrentScene().addGameObject2DNamed("gold_"+(gold_id++));
					gg.getSprite2D().setImage("./resource/gold.png");
					gg.getTransform().getPosition().setVec(getGameObject().getTransform().getPosition());
					gg.getTransform().getSize().setVec(12,12);
					gg.addBehaviour(new MoveBehaviour());
					AudioSource audioSource = new AudioSource();
					gg.addBehaviour(audioSource);
					audioSource.load("./resource/audio/get_fish.wav");
				}
			}
		}
	}
	
	@Override
	public void trigger( GameObject2D collider) {
		if(collider.getTag().equals("bullet")) {
			this.HP-=15*Eagle.getTime().getDeltaTime();
		}else if(collider.getTag().equals("gun")) {
			getGameObject().getTransform().rotate(180);
		}
	}


}
