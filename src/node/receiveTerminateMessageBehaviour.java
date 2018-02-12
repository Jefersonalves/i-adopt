package node;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import messages.AdoptMessage;
import messages.TerminateMessage;
import models.NodeAgentData;

public class receiveTerminateMessageBehaviour extends CyclicBehaviour {
	
	private static final long serialVersionUID = -6895391790742950856L;
	private static final int TERMINATE_MESSAGE = 3;
	NodeAgentData data;
	
	public receiveTerminateMessageBehaviour(Agent a, NodeAgentData data) {
        super(a);
        this.data = data;
    }

	@Override
	public void action() {
		ACLMessage message = myAgent.receive();
		
		if(message != null) {
			AdoptMessage adoptMessage = null;
			
			try {
				adoptMessage = (AdoptMessage) message.getContentObject();
			} catch (UnreadableException e1) {
				e1.printStackTrace();
			}
			
			if(adoptMessage.getMessageType() == TERMINATE_MESSAGE) {
				try {
					TerminateMessage terminate = (TerminateMessage) message.getContentObject();
					
					System.out.println("[REC TERMI  ] "+myAgent.getLocalName()+
									   " receive terminate message: " + terminate.toString()+
									   " from "+message.getSender().getLocalName());
					
					data.setReceivedTerminate(true);
					data.setCurrentContext(terminate.getContext());
					myAgent.addBehaviour(new backTrackBehaviour(myAgent, data));
				} catch (UnreadableException e) {
					e.printStackTrace();
				}
				
			}
			
		}else {
			block();
		}
	}
}