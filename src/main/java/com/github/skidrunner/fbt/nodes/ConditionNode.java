package com.github.skidrunner.fbt.nodes;
import java.util.function.Function;
import com.github.skidrunner.fbt.BehaviourTreeNode;
import com.github.skidrunner.fbt.BehaviourTreeStatus;

public class ConditionNode implements BehaviourTreeNode {

	private String name;

	private Function<Float, Boolean> function;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setFunction(Function<Float, Boolean> function) {
		this.function = function;
	}

	public ConditionNode(String name, Function<Float, Boolean> function) {
		this.name = name;
		this.function = function;
	}

	@Override
	public BehaviourTreeStatus tick(float deltaTime) {
		if(function != null && function.apply(new Float(deltaTime))) {
			return BehaviourTreeStatus.SUCCESS;
		}
		return BehaviourTreeStatus.FAILURE;
	}
	
}
