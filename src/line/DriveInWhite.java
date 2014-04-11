package line;


public class DriveInWhite extends MyBehavior {
	int dreh=10;
	public DriveInWhite(Linienfolger lf) {
		super(lf);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean takeControl() {
		return lf.found &&lf.getColour()==Linienfolger.area.white;
	}

	@Override
	public void action() {
		System.out.println("Weiss");
		lf.p.rotate(dreh*lf.dir);
		dreh*=2;
		lf.dir*=-1;
	}

	@Override
	public void suppress() {
		dreh=10;

	}

}
