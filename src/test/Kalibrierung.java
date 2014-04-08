package test;

import lejos.nxt.Motor;

public class Kalibrierung {
	
	public static void rotateRight(int angle){
		//angle*=1080;
		//angle/=990;
		Motor.A.rotate(-2*angle,true);
		Motor.B.rotate(2*angle,false);
	}
	
	public static void rotateLeft(int angle){
		Motor.A.rotate(2*angle,true);
		Motor.B.rotate(-2*angle,false);
	}

	public static void moveForward(int dist, int speed){
		int speed_r=(int)(360*speed/(6*Math.PI));
		
		Motor.A.setSpeed(speed_r);
		Motor.B.setSpeed(speed_r);
		
		int angle=(int)(36000*dist/(94*6*Math.PI)+0.5);
		Motor.A.rotate(angle,true);
		Motor.B.rotate(angle,false);
	
		//Thread.sleep((int)(Math.abs(dist)*1100/speed));
		
	}
}
