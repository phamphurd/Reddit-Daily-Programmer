/*
 * Problem Description:
 * You just got a new garage door installed by the Automata™ Garage Door Company. 
 * You are having a lot of fun playing with the remote clicker, opening and closing the door, scaring your pets and annoying the neighbors.
 * The clicker is a one-button remote that works like this:
 * If the door is OPEN or CLOSED, clicking the button will cause the door to move, until it completes the cycle of opening or closing.
 * Door: Closed -> Button clicked -> Door: Opening -> Cycle complete -> Door: Open.
 * If the door is currently opening or closing, clicking the button will make the door stop where it is. 
 * When clicked again, the door will go the opposite direction, until complete or the button is clicked again.
 * We will assume the initial state is CLOSED.
 * 
 * Bonus challenge - The door has an infrared beam near the bottom, and if something is breaking the beam, 
 * the door will be BLOCKED and will add the following rules:
 * If the door is currently CLOSING, it will reverse to OPENING until completely OPEN. 
 * It will remain BLOCKED, however, until the input BLOCK_CLEARED is called.
 * Any other state, it will remain in that position, until the input BLOCK_CLEARED is called. 
 * Then it will revert back to it's prior state before it was blocked. Button clicks will be discarded. 
 * If the door was already in the process of opening, it will continue to OPEN until CYCLE_COMPLETE is called.
 */

import java.util.*;

public class GarageDoorOpener_Challenge260 {
	
	public enum doorState{
		CLOSED,
		OPENING,
		STOPPED_WHILE_OPENING,
		OPEN,CLOSING,
		STOPPED_WHILE_CLOSING,
		EMERGENCY_OPENING, 
		OPEN_BLOCKED};
	
	public static final String[] commands = new String[]{
			"button_clicked",
			"cycle_complete",
			"button_clicked",
			"block_detected",
			"button_clicked",
			"cycle_complete",
			"button_clicked",
			"block_cleared",
			"button_clicked",
			"cycle_complete"};
	public static doorState position = doorState.CLOSED;
	
	public static void main(String[] args) {
		System.out.println(position);
		for(int i = 0; i<commands.length; i++){
			System.out.println(commands[i]);
			doorMovement(commands[i]);
		}
	}
	
	public static void doorMovement(String arg){
		if(arg.equals("button_clicked")){
			switch (position){
			case CLOSED: 				position = doorState.values()[position.ordinal()+1]; break;  //Opening
			case OPENING: 				position = doorState.values()[position.ordinal()+1]; break;  //Stopped while opening
			case STOPPED_WHILE_OPENING:	position = doorState.values()[position.ordinal()+2]; break;  //Closing
			case OPEN:					position = doorState.values()[position.ordinal()+1]; break;  //Closing
			case CLOSING:				position = doorState.values()[position.ordinal()+1]; break;  //Stopped while opening
			case STOPPED_WHILE_CLOSING:	position = doorState.values()[position.ordinal()-4]; break;  //Opening
			case EMERGENCY_OPENING: break;	//Nothing
			case OPEN_BLOCKED: break;		//Nothing
			}		
		}else if(arg.equals("cycle_complete")){
			switch (position){
			case CLOSED: break; 													 			//Nothing
			case OPENING:position = doorState.values()[position.ordinal()+2]; break; 			//Open
			case STOPPED_WHILE_OPENING: break; 									  	 			//Nothing
			case OPEN: break; 													  	 			//Nothing
			case CLOSING:position = doorState.values()[position.ordinal()-4]; break; 			//Closed
			case STOPPED_WHILE_CLOSING: break; 									  	 			//Nothing
			case EMERGENCY_OPENING: position = doorState.values()[position.ordinal()+1]; break; //Open
			case OPEN_BLOCKED: break;												 			//Nothing
			}		
		}else if(arg.equals("block_detected")){
			switch (position){
			case CLOSED: break; 													 			//Nothing
			case OPENING:break; 													 			//Nothing
			case STOPPED_WHILE_OPENING: break; 									  	 			//Nothing
			case OPEN: break; 													  	 			//Nothing
			case CLOSING:position = doorState.values()[position.ordinal()+2]; break; 			//Emergency Opening
			case STOPPED_WHILE_CLOSING: break; 									  	 			//Nothing
			case EMERGENCY_OPENING: break; 											 			//Nothing
			case OPEN_BLOCKED: break;												 			//Nothing
			}	
		}else if(arg.equals("block_cleared")){
			switch (position){
			case CLOSED: break; 															 	//Nothing
			case OPENING:break; 															 	//Nothing
			case STOPPED_WHILE_OPENING: break; 									  			 	//Nothing
			case OPEN: break; 													  			 	//Nothing
			case CLOSING: break; 															 	//Nothing
			case STOPPED_WHILE_CLOSING: break; 									  			 	//Nothing
			case EMERGENCY_OPENING: break; 													 	//Nothing
			case OPEN_BLOCKED: position = doorState.values()[position.ordinal()-4];break;	 	//Nothing
			}
		}
		System.out.println(position);
	}

}
