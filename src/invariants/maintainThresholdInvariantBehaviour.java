package invariants;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import models.NodeAgentData;

public class maintainThresholdInvariantBehaviour extends OneShotBehaviour{
	
	private static final long serialVersionUID = -2399456552459121757L;
	private NodeAgentData data;

	public maintainThresholdInvariantBehaviour(Agent a, NodeAgentData data) {
		super(a);
		this.data = data;
	}
	
	@Override
	public void action() {
		int lowerBound = data.getLowerBound();
		int upperBound = data.getUpperBound();
		int threshold = data.getThreshold();

		System.out.println("[INV MTI    ] "+myAgent.getLocalName()+" starting maintain threshold invariant");
		
		if(threshold < lowerBound) {
			data.setThreshold(lowerBound);
		}
		if(threshold > upperBound) {
			data.setThreshold(upperBound);
		}
		
	}
}
