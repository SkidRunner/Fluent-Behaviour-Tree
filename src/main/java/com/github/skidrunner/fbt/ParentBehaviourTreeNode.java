package com.github.skidrunner.fbt;

public interface ParentBehaviourTreeNode extends BehaviourTreeNode {
	
	<T extends BehaviourTreeNode> void add(T node);
	
}
