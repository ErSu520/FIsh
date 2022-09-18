package com.flash.fish.behaviour;

import com.flash.EE.core.object.behaviour.UIBehaviour;

/**
 * 与GameObjectBehaviour相对
 * UIBehaviour是UI组件的行为  可以挂载在UI组件上
 * 
 * @author 文杰
 *
 */
public class ButtonBehaviour extends UIBehaviour{

	/**
	 * 检测到UI组件被点击时会被调用
	 */
	@Override
	public void onClick() {
		
		System.exit(0);
		
	}
	
	
	
	
}
