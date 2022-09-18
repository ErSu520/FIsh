package com.flash.fish.builder;

import java.awt.Color;

import com.flash.EE.compnent.audio.AudioSource;
import com.flash.EE.compnent.physics.CircularCollider;
import com.flash.EE.core.control.builder.SceneBuilder;
import com.flash.EE.core.object.gameobject.GameObject2D;
import com.flash.EE.core.object.scene.Scene;
import com.flash.EE.ui.UIButton;
import com.flash.EE.ui.UIImage;
import com.flash.EE.ui.UIText;

import com.flash.fish.behaviour.ButtonBehaviour;
import com.flash.fish.behaviour.FireBehaviour;
import com.flash.fish.behaviour.GunBehaviour;
import com.flash.fish.behaviour.Water;

/**
 * 该类是用来定义一个游戏场景的	
 * 在该类的builder方法内对场景内的物体和UI进行配置即可
 * @author ersu
 *
 */
public class WorldBuilder implements SceneBuilder{

	@Override
	public void builder(Scene scene) {
		//addGameObject2DNamed方法需要传入一个名字  该名字必须是独一无二的 不可重复
		//方法会自动在场景中使用该名字创建一个物体并返回
		GameObject2D back = scene.addGameObject2DNamed("back");
		//同一场景内物体的名字不可重复
		//tag是标签 用来区分一类物体  不同物体的tag可以相同
		back.setTag("back");
		//Transform是物体的形状属性 可以设置其位置大小及旋转角度
		back.getTransform().setBounds(300, 210, 600, 420);
		//最后设置物体的显示图片 若不设置便会显示为默认图片
		back.getSprite2D().setImage("./resource/fish/sea1.png");
		
		//UIText是文字显示组件 创建它时需要传入名字（场景内不可重复），显示内容，字号，颜色等
		UIText text = new UIText("text", "123",15,Color.black);
		//与物体相同的设置位置大小
		text.getTransform().setBounds(500, 15, 60, 22);
		//使用 addUIView方法 将创建的UI组件加入到场景
		scene.addUIView(text);
		
		UIImage imgGold = new UIImage("img_gold");
		imgGold.getTransform().setBounds(465, 15, 24, 24);
		imgGold.getSprite2D().setImage("./resource/gold.png");
		scene.addUIView(imgGold);
		
		makePanel(scene);
		
		GameObject2D gun = scene.addGameObject2DNamed("gun");
		gun.setTag("gun");
		gun.getTransform().setBounds(300, 380, 36, 36);
		gun.getSprite2D().setImage("./resource/gun.png");
		gun.addBehaviour(new FireBehaviour());
		gun.addBehaviour(new GunBehaviour());
		gun.addBehaviour(new Water());
		gun.addBehaviour(new CircularCollider(true,18));
		//创建一个音效行为
		AudioSource audioSource = new AudioSource();
		//将音效行为添加到物体上
		gun.addBehaviour(audioSource);
		//配置播放音效  第一个参数是音频路径（仅支持WAV格式的音频）  第二个参数是循环播放
		audioSource.load("./resource/audio/bgm_sea.wav",true);
		
	}
	
	
	private void makePanel(Scene scene) {
		UIImage panel = new UIImage("img_panel");
		panel.getTransform().setBounds(300, 210, 300, 180);
		panel.getSprite2D().setImage("./resource/dialog_failed.png");
		scene.addUIView(panel);
		//将该图片UI设置为不可见状态
		panel.setEnable(false);
		
		UIButton button = new UIButton("exit", "退出游戏");
		button.setTextColor(Color.WHITE);
		button.getTransform().setBounds(300, 260, 60, 36);
		button.getSprite2D().setImage("./resource/button.png");
		button.addBehaviour(new ButtonBehaviour());
		scene.addUIViewWithParent(button,"img_panel");
	}
	
	
	
	
	
	
	

}
