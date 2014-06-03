package mapping;

public class Position {
	int x;
	int y;
	Position(int x,int y){
		this.x=x;
		this.y=y;
	}
	
	public int length(Position p){
		return Math.abs(this.x-p.x)+Math.abs(this.y-p.y);
	}

	public Param View(Position p){
		Param par=new Param();
		par.x=this.x;
		par.y=this.y;
		if(this.x>p.x){
			if(this.x==p.x){
				if(this.y>p.y){
					par.dy=-1;
					par.vx=-1;
				}
				else{
					par.dy=1;
					par.vx=1;
				}
			}
			else{
				par.dx=-1;
				par.vy=-1;
			}
		}
		else{
			par.dx=1;
			par.vy=1;
		}
		return par;
	}
}
