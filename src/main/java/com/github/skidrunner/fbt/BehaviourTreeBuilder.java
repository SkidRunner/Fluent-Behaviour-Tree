package com.github.skidrunner.fbt;

import java.util.Stack;
import java.util.function.Function;
import com.github.skidrunner.fbt.nodes.ActionNode;
import com.github.skidrunner.fbt.nodes.ParallelNode;
import com.github.skidrunner.fbt.nodes.ConditionNode;
import com.github.skidrunner.fbt.nodes.SequenceNode;
import com.github.skidrunner.fbt.nodes.SelectorNode;
import com.github.skidrunner.fbt.nodes.InverterNode;
import android.os.Build;

public class BehaviourTreeBuilder {
	
	private ParentBehaviourTreeNode parent;
	
	private final Stack<ParentBehaviourTreeNode> parents;
	
	public BehaviourTreeBuilder() {
		this.parents = new Stack<>();
	}
	
	public BehaviourTreeBuilder addAction(String name, Function<Float, BehaviourTreeStatus> function) {
		return add(new ActionNode(name, function));
	}
	
	public BehaviourTreeBuilder addCondition(String name, final Function<Float, Boolean> function) {
		return add(new ConditionNode(name, function));
	}
	
	public BehaviourTreeBuilder startSequence(String name) {
		return start(new SequenceNode(name));
	}
	
	public BehaviourTreeBuilder startParallel(String name, int failureThreshold, int successThreshold) {
		return start(new ParallelNode(name, failureThreshold, successThreshold));
	}
	
	public BehaviourTreeBuilder startSelector(String name) {
		return start(new SelectorNode(name));
	}
	
	public BehaviourTreeBuilder startInvertor(String name) {
		return start(new InverterNode(name));
	}
	
	public BehaviourTreeBuilder start(ParentBehaviourTreeNode parent) {
		if(!parents.isEmpty()) {
			parents.peek().add(parent);
		}
		parents.push(parent);
		return this;
	}
	
	public BehaviourTreeBuilder add(BehaviourTreeNode node) {
		parents.peek().add(node);
		return this;
	}
	
	public BehaviourTreeBuilder end() {
		parent = parents.pop();
		return this;
	}
	
	public BehaviourTreeNode build() {
		return parent;
	}
	
}
