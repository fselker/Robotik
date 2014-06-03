package line;

import lejos.nxt.Button;
import lejos.nxt.ButtonListener;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import test.Pilot;

public class Linienfolger implements ButtonListener {

	int weiss;
	int schwarz;
	int og, ug;
	LightSensor ls;
	Arbitrator arbitrator;
	Pilot p;
	int dir=1;
	int radius=200;
	boolean found= false, started=false;
	
	enum area {white, black, grey};
	
	public area getColour(){
		int value = ls.getNormalizedLightValue();
		if(value>og)
			return area.white;
		if(value<ug)
			return area.black;
		else
			return area.grey;
	}
	
	public Linienfolger() {
		p= new Pilot();
		
		ls = new LightSensor(SensorPort.S1);
		ls.setFloodlight(true);
		Button.LEFT.addButtonListener(this);
		Button.RIGHT.addButtonListener(this);
		Button.ESCAPE.addButtonListener(this);
		FindLine fl = new FindLine(this);
		DriveInBlack il = new DriveInBlack(this);
		DriveInWhite rtl = new DriveInWhite(this);
		DriveInGrey baw = new DriveInGrey(this);
		
		Behavior [] bArray ={fl,il,rtl,baw};
		arbitrator = new Arbitrator(bArray);
		
	}
	
	public void start(){
		arbitrator.start();
	}

	public static void main(String args[]) throws Exception{
		
		Linienfolger lf = new Linienfolger();
		lf.p.setRotateSpeed(80);
		lf.p.setTravelSpeed(80);
		System.out.println("mit Enter starten");
		while(!Button.ENTER.isDown())
			Thread.sleep(100);
		lf.start();
	}

	@Override
	public void buttonPressed(Button b) {
		if(b!=null)
		if(b.equals(Button.LEFT)){
			weiss=ls.getNormalizedLightValue();
			System.out.println("Weiss "+weiss);
		}
		else if(b.equals(Button.RIGHT)){
			schwarz = ls.getNormalizedLightValue();
			System.out.println("Schwarz "+schwarz);
		}else if(b.equals(Button.ESCAPE)){
			System.out.println("beenden");
			System.exit(0);
		}
		og=schwarz+1*(weiss-schwarz)/2;
		ug=schwarz+1*(weiss-schwarz)/4;
		
		System.out.println("UG: "+ug+ "OG "+og);
	}
	

	@Override
	public void buttonReleased(Button b) {
		// TODO Auto-generated method stub
		
	}

}
