package line;


public class DriveInBlack extends MyBehavior {
	public DriveInBlack(Linienfolger lf) {
		super(lf);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean takeControl() {
		return lf.getColour()==Linienfolger.area.black;
	}

	@Override
	public void action() {
		lf.found=true;
		lf.p.arcForward(lf.dir*lf.radius);
		System.out.println("Schwarz");
		//System.out.println("Ich fahre eine "+ ((dir>0)?"Links":"Rechts")+"kurve");
	}

	@Override
	public void suppress() {
		lf.dir*=-1;
		// TODO Auto-generated method stub

	}

}
