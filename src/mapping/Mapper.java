package mapping;

import java.util.ArrayList;

public class Mapper {

	double map[][];

	Mapper() {
		map = new double[64][64];
		for(int y=0;y<map[0].length;y++)
			for(int x=0;x<map.length;x++)
				map[x][y]=0.5;
	}

	public void getReihe(int size, ArrayList<Double> list, Position start, Position end) {
		double pro = 1.0*size/list.size() ;
		int startx, starty, endx, endy;
		Param p = start.View(end);
		for (int i = 0; i < list.size(); i++) {
			startx = (int) (p.x + p.dx * i * pro+ ((p.dx==-1)?0.5:0));
			starty = (int) (p.y + p.dy * i * pro+ ((p.dy==-1)?0.5:0));

			endx = (int) (startx + p.vx * list.get(i));
			endy = (int) (starty + p.vy * list.get(i));

			eintragen(new Position(startx, starty), new Position(endx, endy), p);
		}
	}

	public void eintragen(Position start, Position hindernis, Param p) {
		// davor auf 20%
		for (int i = 0; i < start.length(hindernis) - 2; i++) {
			set(start.x + p.vx * i, start.y + p.vy * i, 0.2);
		}
		// das hindernis auf 80%
		set(hindernis.x, hindernis.y, 0.8);

		// daneben auf 60%
		set(hindernis.x + p.dx, hindernis.y + p.dy, 0.6);
		set(hindernis.x - p.dx, hindernis.y - p.dy, 0.6);
		// weiter daneben = 50%
		set(hindernis.x + 2 * p.dx, hindernis.y + 2 * p.dy, 0.6);
		set(hindernis.x - 2 * p.dx, hindernis.y - 2 * p.dy, 0.6);
	}

	public void set(int x, int y, double value) {
		if (x <= 64 && x > 0 && y < 64 && y >= 0){
			map[x][y] += value;
			map[x][y]/=2;
		}
	}
}
