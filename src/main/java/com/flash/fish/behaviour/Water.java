package com.flash.fish.behaviour;

import com.flash.EE.compnent.animation.Animation;
import com.flash.EE.compnent.animation.AnimationClip;
import com.flash.EE.compnent.physics.CircularCollider;
import com.flash.EE.compnent.physics.RigidBody;
import com.flash.EE.core.object.behaviour.GameObjectBehaviour;
import com.flash.EE.core.object.gameobject.GameObject2D;
import com.flash.EE.core.system.Eagle;

/**
 * 该类用于不断的创建鱼类
 * 
 * @author ersu
 *
 */
public class Water extends GameObjectBehaviour{
	
	//创建一个对话片段 创建时指定其名字以及图片路径数组
	public static AnimationClip d = AnimationClip.instantiation("d",
			new String[] {"./resource/fish/d1.png","./resource/fish/d2.png"
					,"./resource/fish/d3.png","./resource/fish/d4.png"
					,"./resource/fish/d5.png","./resource/fish/d6.png"
					,"./resource/fish/d7.png","./resource/fish/d6.png"
					,"./resource/fish/d5.png","./resource/fish/d4.png"
					,"./resource/fish/d3.png","./resource/fish/d2.png"
					,"./resource/fish/d1.png"});
	public static AnimationClip g = AnimationClip.instantiation("g",
			new String[] {"./resource/fish/g1.png","./resource/fish/g2.png"
					,"./resource/fish/g3.png","./resource/fish/g4.png"
					,"./resource/fish/g3.png","./resource/fish/g2.png"
					,"./resource/fish/g1.png"});
	
	public static AnimationClip lx = AnimationClip.instantiation("lx",
			new String[] {"./resource/fish/lx/1.png","./resource/fish/lx/2.png"
					,"./resource/fish/lx/3.png","./resource/fish/lx/4.png"
					,"./resource/fish/lx/5.png","./resource/fish/lx/6.png"
					,"./resource/fish/lx/7.png","./resource/fish/lx/8.png"
					,"./resource/fish/lx/9.png","./resource/fish/lx/10.png"
					,"./resource/fish/lx/11.png","./resource/fish/lx/12.png"
					,"./resource/fish/lx/13.png","./resource/fish/lx/14.png"
					,"./resource/fish/lx/15.png","./resource/fish/lx/16.png"
					,"./resource/fish/lx/17.png","./resource/fish/lx/18.png"
					,"./resource/fish/lx/19.png","./resource/fish/lx/20.png"
					,"./resource/fish/lx/21.png","./resource/fish/lx/22.png"
					,"./resource/fish/lx/23.png","./resource/fish/lx/24.png"
					,"./resource/fish/lx/25.png","./resource/fish/lx/26.png"
					,"./resource/fish/lx/27.png"});
	
	public static AnimationClip ny = AnimationClip.instantiation("ny",
			new String[] {"./resource/fish/ny/1.png","./resource/fish/ny/2.png"
					,"./resource/fish/ny/3.png","./resource/fish/ny/4.png"
					,"./resource/fish/ny/5.png","./resource/fish/ny/6.png"
					,"./resource/fish/ny/7.png","./resource/fish/ny/8.png"
					,"./resource/fish/ny/9.png","./resource/fish/ny/10.png"
					,"./resource/fish/ny/11.png","./resource/fish/ny/12.png"
					,"./resource/fish/ny/13.png","./resource/fish/ny/14.png"
					,"./resource/fish/ny/15.png","./resource/fish/ny/16.png"
					,"./resource/fish/ny/17.png","./resource/fish/ny/18.png"
					,"./resource/fish/ny/19.png","./resource/fish/ny/20.png"
					,"./resource/fish/ny/21.png","./resource/fish/ny/22.png"
					,"./resource/fish/ny/23.png","./resource/fish/ny/24.png"
					,"./resource/fish/ny/25.png","./resource/fish/ny/26.png"
					,"./resource/fish/ny/27.png"});

	//创建鱼类的最大冷却时间
	private float FREEZE_TIME = 3.5f;
	//鱼类的ID
	private long fishId = 0;
	//当前的冷却时间
	private float freezeTime = 0f;
	
	/**
	 * 该方法是被逐帧调用的方法
	 */
	@Override
	public void update() {
		//判断冷却时间
		if(freezeTime<=0) {
			//Eagle.getCurrentScene()方法可以获取当前正在运行的创建
			GameObject2D dl = Eagle.getCurrentScene().addGameObject2DNamed("fish"+(fishId++));
			dl.setTag("fish");
			dl.getTransform().setBounds((float)Math.random()*450, (float)Math.random()*360, 24, 52);
			dl.forward(Eagle.getCurrentScene().findGameObject2DNamed("gun"));
			dl.getSprite2D().setImage("./resource/fish/d4.png");
			//创建一个动画行为 指定动画帧的播放间隔
			Animation animation = new Animation(0.2f,null);
			//添加动画片段到行为中
			animation.addClip(d);
			//加载动画片段
			animation.loadClipNamed("d");
			//将动画行为添加到物体上
			dl.addBehaviour(animation);
			//添加鱼类活动的行为
			dl.addBehaviour(new FishBehaviour(3,30,50));
			//添加圆形碰撞体
			dl.addBehaviour(new CircularCollider(true,18));
			//添加刚体 刚体会与碰撞体发生碰撞
			dl.addBehaviour(new RigidBody());
			
			GameObject2D gui = Eagle.getCurrentScene().addGameObject2DNamed("fish"+(fishId++));
			gui.setTag("fish");
			gui.getTransform().setBounds((float)Math.random()*450, (float)Math.random()*360, 36, 36);
			gui.forward(Eagle.getCurrentScene().findGameObject2DNamed("gun"));
			gui.getSprite2D().setImage("./resource/fish/g1.png");
			animation = new Animation(0.2f,null);
			animation.addClip(g);
			animation.loadClipNamed("g");
			gui.addBehaviour(animation);
			gui.addBehaviour(new FishBehaviour(5,15,150));
			gui.addBehaviour(new CircularCollider(true,20));
			gui.addBehaviour(new RigidBody());
			
			GameObject2D xia = Eagle.getCurrentScene().addGameObject2DNamed("fish"+(fishId++));
			xia.setTag("fish");
			xia.getTransform().setBounds((float)Math.random()*450, (float)Math.random()*360, 24, 40);
			xia.forward(Eagle.getCurrentScene().findGameObject2DNamed("gun"));
			xia.getSprite2D().setImage("./resource/fish/lx/1.png");
			animation = new Animation(0.1f,null);
			animation.addClip(lx);
			animation.loadClipNamed("lx");
			xia.addBehaviour(animation);
			xia.addBehaviour(new FishBehaviour(2,25,50));
			xia.addBehaviour(new CircularCollider(true,18));
			xia.addBehaviour(new RigidBody());
			
			GameObject2D yu = Eagle.getCurrentScene().addGameObject2DNamed("fish"+(fishId++));
			yu.setTag("fish");
			yu.getTransform().setBounds((float)Math.random()*450, (float)Math.random()*360, 24, 40);
			yu.forward(Eagle.getCurrentScene().findGameObject2DNamed("gun"));
			yu.getSprite2D().setImage("./resource/fish/ny/1.png");
			animation = new Animation(0.1f,null);
			animation.addClip(ny);
			animation.loadClipNamed("ny");
			yu.addBehaviour(animation);
			yu.addBehaviour(new FishBehaviour(4,60,50));
			yu.addBehaviour(new CircularCollider(true,18));
			yu.addBehaviour(new RigidBody());
			
			freezeTime = FREEZE_TIME;
		}
		freezeTime-=Eagle.getTime().getDeltaTime();
	}

}
