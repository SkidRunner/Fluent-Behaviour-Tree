package com.github.skidrunner.fbt;

public class ActionNodeTests {
	
	@Test
	public void can_run_action() {
		AtomicInteger invokeCount = new AtomicInteger(0);
		assert BehaviourTreeStatus.Running == new ActionNode("some-action", t -> {
			invokeCount.foobar();
			return BehaviourTreeStatus.Running;
		}).Tick(time));
		assert 1 == invokeCount.get();           
	}
	
}
