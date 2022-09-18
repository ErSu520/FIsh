package com.flash.fish;

import java.net.UnknownHostException;

import com.flash.EE.compnent.physics.AreaManager;
import com.flash.EE.core.control.launch.Launcher;
import com.flash.EE.core.control.launch.LauncherBuilder;
import com.flash.EE.core.system.Eagle;
import com.flash.EE.painter.window.EagleFrame;
import com.flash.EE.util.Vector2D;

import com.flash.fish.builder.WorldBuilder;

/**
 * 程序的主入口
 * 
 * @author ersu
 *
 */
public class Main {
	
	//记录玩家的金币数量
	public static int gold = 99999;

	public static void main(String[] args) throws UnknownHostException {
		//设置碰撞区域的大小及分块区域的最大大小  用以提高碰撞算法的效率
		AreaManager.initial(-200, 1000, -200, 1000, 1600);
		//设置自动寻路的区域大小
		AreaManager.initial(3000, 3000);
		//上两者不用也得设置否则将会报错
		
		//创建一个内置的渲染窗口
		EagleFrame frame = new EagleFrame(true,new Vector2D(600,440));
		//游戏的启动器 传入上面定义的显示窗口以及摄像机（显示区域）的大小即可  
		//画面的显示会按照比例适应窗口
		Launcher launcher = LauncherBuilder.instantiation(frame,new Vector2D(600,420));
		//加载第一个场景
		Eagle.getSceneLoader().loadScene(new WorldBuilder());
		//启动游戏
		launcher.launch();
	}

}