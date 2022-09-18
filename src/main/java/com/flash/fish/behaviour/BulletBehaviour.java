package com.flash.fish.behaviour;

import com.flash.EE.core.object.behaviour.GameObjectBehaviour;
import com.flash.EE.core.object.gameobject.GameObject2D;
import com.flash.EE.core.system.Eagle;

/**
 * 继承自 GameObjectBehaviour 的类都可以挂载在物体上进行逐帧调用
 * 
 * @author ersu
 *
 */
public class BulletBehaviour extends GameObjectBehaviour{
	
	private float speed =80 , boomTime = 0.3f , lifeTime=3f;
	
	private boolean canBoom = true;
	
	/**
	 * 逐帧更新的方法
	 */
	@Override
	public void update() {
		//判断子弹是否已经爆炸  未爆炸则爆炸  否则销毁
		if(canBoom==false) {
			//DeltaTime指代上一帧的用时
			boomTime-=Eagle.getTime().getDeltaTime();
			if(boomTime<-0.3f) {
				//从场景中将自己销毁
				Eagle.getCurrentScene().destroyGameObject2DNamed(getGameObject().getName());
			}
		}else {
			getGameObject().move(speed*Eagle.getTime().getDeltaTime());
			if(lifeTime<0) {
				//重新设置物体的大小
				getGameObject().getTransform().getSize().setVec(50, 50);
				//改变物体的图片显示为渔网
				getGameObject().getSprite2D().setImage("./resource/net.png");
				canBoom = false;
			}else {
				lifeTime-=Eagle.getTime().getDeltaTime();
			}
		}
		
	}
	
	/**
	 * 碰撞发生时调用的方法
	 * 开发者可在此编辑发生碰撞后的处理代码
	 */
	@Override
	public void trigger(GameObject2D collider) {
		if(collider.getTag().equals("bullet")) {
			return;
		}
		if(canBoom) {
			getGameObject().getTransform().getSize().setVec(50, 50);
			getGameObject().getSprite2D().setImage("./resource/net.png");
			canBoom = false;
		}
	}

}
