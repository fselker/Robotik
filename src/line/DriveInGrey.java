package line;


public class DriveInGrey extends MyBehavior{

	public DriveInGrey(Linienfolger lf) {
		super(lf);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean takeControl() {
		return lf.getColour()==Linienfolger.area.grey;
	}

	@Override
	public void action() {
		lf.found=true;
		lf.p.arcForward(lf.radius*lf.dir/2);
		System.out.println("Grau");
		//System.out.println("Ich fahre eine starke "+ ((lf.dir>0)?"Links":"Rechts")+"kurve");
	}

	@Override
	public void suppress() {
		// TODO Auto-generated method stub
		
	}

}
