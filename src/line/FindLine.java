package line;


public class FindLine extends MyBehavior{

	

	public FindLine(Linienfolger lf) {
		super(lf);
	}


	@Override
	public void action() {
		lf.p.forward();
		System.out.println("Ich suche die Linie");	
	}


	@Override
	public boolean takeControl() {
		return true;
	}

	@Override
	public void suppress() {
		lf.found=true;
	}

}
