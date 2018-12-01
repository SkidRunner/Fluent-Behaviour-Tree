package com.github.skidrunner.fbt.nodes;
import com.github.skidrunner.fbt.ParentBehaviourTreeNode;
import com.github.skidrunner.fbt.BehaviourTreeStatus;
import com.github.skidrunner.fbt.BehaviourTreeNode;

public class InverterNode implements ParentBehaviourTreeNode {
	
	private String name;
	
	private BehaviourTreeNode node;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public InverterNode(String name) {
		this.name = name;
	}
	
	@Override
	public BehaviourTreeStatus tick(float deltaTime) {
		if(node == null) {
			return BehaviourTreeStatus.FAILURE;
		}
		BehaviourTreeStatus status = node.tick(deltaTime);
		switch(status) {
			case SUCCESS:
				return BehaviourTreeStatus.FAILURE;
			case FAILURE:
				return BehaviourTreeStatus.SUCCESS;
			default:
				return status;
		}
	}

	@Override
	public <T extends BehaviourTreeNode> void add(T node) {
		this.node = node;
	}
	
}
