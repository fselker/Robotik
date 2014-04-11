package line;

import lejos.robotics.subsumption.Behavior;

public abstract class MyBehavior implements Behavior{

	Linienfolger lf;
	public MyBehavior(Linienfolger lf){
		this.lf=lf;
	}
}
