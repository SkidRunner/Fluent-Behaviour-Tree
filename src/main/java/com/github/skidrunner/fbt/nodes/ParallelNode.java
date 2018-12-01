package com.github.skidrunner.fbt.nodes;
import com.github.skidrunner.fbt.ParentBehaviourTreeNode;
import com.github.skidrunner.fbt.BehaviourTreeStatus;
import com.github.skidrunner.fbt.BehaviourTreeNode;
import java.util.Vector;

public class ParallelNode implements ParentBehaviourTreeNode {
	
	private String name;
	private int failureThreshold;
	private int successThreshold;
	private final Vector<BehaviourTreeNode> nodes;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getFailureThreshold() {
		return failureThreshold;
	}
	
	public void setFailureThreshold(int failureThreshold) {
		this.failureThreshold = failureThreshold;
	}
	
	public int getSuccessThreshold() {
		return successThreshold;
	}

	public void setSuccessThreshold(int successThreshold) {
		this.successThreshold = successThreshold;
	}
	
	public ParallelNode(String name, int failureThreshold, int successThreshold) {
		this.name = name;
		this.failureThreshold = failureThreshold;
		this.successThreshold = successThreshold;
		this.nodes = new Vector<>();
	}
	
	@Override
	public BehaviourTreeStatus tick(float deltaTime) {
		BehaviourTreeStatus status = BehaviourTreeStatus.RUNNING;
		
		int failureCount = 0;
		int successCount = 0;
		
		for(BehaviourTreeNode node : nodes) {
			BehaviourTreeStatus nodeStatus = node.tick(deltaTime);
			
			if(status != BehaviourTreeStatus.RUNNING) {
				continue;
			}
			
			switch(nodeStatus) {
				case FAILURE:
					failureCount++;
					if (failureThreshold > 0 && failureCount >= failureThreshold) {
						status = BehaviourTreeStatus.FAILURE;
					}
					break;
				case SUCCESS:
					successCount++;
					if (successThreshold > 0 && successCount >= successThreshold) {
						status = BehaviourTreeStatus.SUCCESS;
					}
					break;
			}
		}
		return status;
	}
	
	@Override
	public <T extends BehaviourTreeNode> void add(T node) {
		nodes.add(node);
	}
	
}
