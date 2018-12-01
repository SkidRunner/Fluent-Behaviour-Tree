package com.github.skidrunner.fbt.nodes;
import com.github.skidrunner.fbt.ParentBehaviourTreeNode;
import java.util.Vector;
import com.github.skidrunner.fbt.BehaviourTreeNode;
import com.github.skidrunner.fbt.BehaviourTreeStatus;

public class SequenceNode implements ParentBehaviourTreeNode {
	
	private String name;
	private final Vector<BehaviourTreeNode> nodes;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public SequenceNode(String name) {
		this.name = name;
		this.nodes = new Vector<>();
	}

	@Override
	public BehaviourTreeStatus tick(float deltaTime) {
		for(BehaviourTreeNode node : nodes) {
			BehaviourTreeStatus nodeStatus = node.tick(deltaTime);
			if(nodeStatus != BehaviourTreeStatus.SUCCESS) {
				return nodeStatus;
			}
		}
		return BehaviourTreeStatus.SUCCESS;
	}

	@Override
	public <T extends BehaviourTreeNode> void add(T node) {
		nodes.add(node);
	}
	
}
