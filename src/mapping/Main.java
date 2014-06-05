package mapping;

import java.util.ArrayList;

import javax.microedition.lcdui.Graphics;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.navigation.DifferentialPilot;

public class Main implements Runnable{
	UltrasonicSensor us;
	boolean run;
	ArrayList<Double> values;
	
	public Main(){
		values= new ArrayList<Double>();
		us = new UltrasonicSensor(SensorPort.S2);
		run = true;
	}
	
	public static void main(String args[]){
		
		int dist = 800;

		DifferentialPilot p = new DifferentialPilot(57.0, 115, Motor.B, Motor.A);
		Main m = new Main();
		Mapper mapper = new Mapper();
		
		Thread t = new Thread(m);
		p.setTravelSpeed(80);
		p.setRotateSpeed(80);
		
		t.start();
		p.travel(dist);
		m.run=false;
		mapper.getReihe(64, m.values, new Position(31, 0 ), new Position(31,63));
		m.values = new ArrayList<Double>();
		m.run = true;
		
		p.rotate(180);
		t = new Thread(m);
		t.start();
		p.travel(dist);
		m.run=false;
		
		mapper.getReihe(64, m.values, new Position(31, 63 ), new Position(31,0));
		m.values = new ArrayList<Double>();
		m.run = true;
		
		
		p.travel(-dist/2);
		p.rotate(90);
		t = new Thread(m);
		t.start();
		p.travel(dist/2);
		m.run=false;
		mapper.getReihe(32, m.values, new Position(31, 31 ), new Position(0,31));
		m.values= new ArrayList<Double>();
		m.run = true;
		
		
		p.rotate(180);
		t = new Thread(m);
		t.start();
		p.travel(dist);
		m.run=false;
		mapper.getReihe(64, m.values, new Position(0, 31 ), new Position(63,31));
		m.values = new ArrayList<Double>();
		m.run = true;
		
		p.rotate(180);
		t = new Thread(m);
		t.start();
		p.travel(dist/2);
		m.run=false;
		mapper.getReihe(32, m.values, new Position(63, 31 ), new Position(31,31));
		
		m.run = false;
		display(mapper.map);
		while(!Button.ESCAPE.isDown());
				
	}


	public void run() {
	
		while(run){
			values.add((double)us.getDistance()/2);//System.out.println(values.get(values.size()-1));
			
			try{
				Thread.sleep(10);
			}catch(InterruptedException e){
				
			}
		}
		
	}
	
	public static void display(double map[][]){
		LCD.clear();
		Graphics g= new Graphics();
		for(int y=0;y<map[0].length;y++)
			for(int x=0;x<map.length;x++)
				if(map[x][y]>=0.5)
				g.drawLine(x, y, x, y);
		LCD.refresh();
	}
}
