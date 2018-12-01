package com.github.skidrunner.fbt.nodes;

import com.github.skidrunner.fbt.BehaviourTreeNode;
import java.util.function.Function;
import com.github.skidrunner.fbt.BehaviourTreeStatus;

public class ActionNode implements BehaviourTreeNode {
	
	private String name;
	
	private Function<Float, BehaviourTreeStatus> function;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setFunction(Function<Float, BehaviourTreeStatus> function) {
		this.function = function;
	}
	
	public ActionNode(String name, Function<Float, BehaviourTreeStatus> function) {
		this.name = name;
		this.function = function;
	}
	
	@Override
	public BehaviourTreeStatus tick(float deltaTime) {
		if(function != null) {
			return function.apply(deltaTime);
		}
		return BehaviourTreeStatus.FAILURE;
	}
	
}
