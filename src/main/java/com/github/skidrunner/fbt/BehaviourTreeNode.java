package com.github.skidrunner.fbt;

public interface BehaviourTreeNode {
	
	BehaviourTreeStatus tick(float deltaTime);
	
}
