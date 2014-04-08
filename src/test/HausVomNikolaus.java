package test;

import lejos.nxt.Sound;



public class HausVomNikolaus {
	
	public static void haus(Pilot p, double kante){

		
		double diag=(int)Math.sqrt(kante*kante+kante*kante);
		double dach=diag/2;
		
		
		p.travel(kante);//OL S1
		
		p.rotate(45);
		p.travel(-diag);//UR S2
		
		p.rotate(-45);		
		p.travel(kante);//OR S3
		
		p.rotate(45);		
		p.travel(dach);//O S4
		
		p.rotate(90);		
		p.travel(dach);//OL S5
		
		p.rotate(-45);
		p.travel(-kante);//OR S6
		
		p.rotate(45);
		p.travel(diag);//UL S7
		
		p.rotate(-45);
		p.travel(-kante);//UR S8
		
		p.travel(kante);//UL S9
		p.rotate(-90);
		Sound.beep();
	}
	
	public static void main(String args[])throws InterruptedException{
		
		
		
		Pilot p= new Pilot();
		p.setTravelSpeed(90);
		p.setRotateSpeed(20);
		//p.travel(1000);
		//p.rotate(-1080);
		
		haus(p, 500.0);
		/*
		int kante=30;
		int speed=10;
		Motor.A.setSpeed(180);
		Motor.B.setSpeed(180);
		p.rotate(-1080);
		/*
		p.rotate(45);
		Thread.sleep(1000);
		p.rotate(45);
		Thread.sleep(1000);
		p.rotate(90);
		Thread.sleep(1000);
		p.rotate(-180);
		*/
		//p.travel(100, 10);
		//haus(kante,speed);
		
		/*
		int s,v_r,t,v;
		
		v_r=1;
		
		s=100;
		v=19*v_r;//19cm/s
		t=1000*s/v;
		
		Motor.A.setSpeed(360*v_r);
		Motor.B.setSpeed(360*v_r);
		Motor.A.rotate(4*360,true);
		//Motor.B.rotate(5*360,true);
		Thread.sleep(5000);
		
		
		Motor.B.forward();
		Motor.A.forward();
		Thread.sleep(t);
		Motor.B.stop();
		Motor.A.stop();
		*/
		
	}

}
