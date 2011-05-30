/**
 * Dot class
 */
package kurilluk.OoMS.type;

import java.util.ArrayList;

//import com.sun.org.apache.xalan.internal.xsltc.compiler.Parser;

import processing.core.*;
//import sun.java2d.pipe.ValidatePipe;
import kurilluk.OoMS.data.property;
import kurilluk.OoMS.data.Block;

/**
 * @author |< >|
 * 
 */

public class Dot {
	// FIELDS
	private PVector pos;
	private PVector local;
	private PVector grid; // int position
	private ArrayList<PVector> neighbors = new ArrayList<PVector>();

	private int ID = -1; // 0 = fix, 1 = odd, 2 = even, 3 = newGenerated
	private double value = 0.0;

	
	// CONSTRUCTOR
	public Dot(PVector pt) {
		this.pos = pt;
		this.getID();
	}

	// PROPERTY
	// public float getX()

	// METHODS
	
	public void SetLocal(float x,float y, float z) {
		this.local = new PVector(x, y, z);
	}

	private void getID() {
		int even = 82;
		int odd = 27;

		if (this.pos.z <= odd) {
			this.ID = 0;
		} else if (this.pos.z > odd && this.pos.z < odd + 1) {
			this.ID = 1;
		} else if (this.pos.z > even && this.pos.z < even + 1) {
			this.ID = 2;
		} else if (this.pos.z >= odd + 1) {
			this.ID = 3;
		}
	}

	// TODO pozor zmena value! ========================================
	public void AddProperties(String[] property) {
		this.value = Math.round(Double.parseDouble(property[0]));
	}

	public double getVal() {
		return this.value;
	}
	
	public void setGridPos(int dx, int dy, int dz){
		this.grid = new PVector(dx,dy,dz);
	}

	public int Zg(){
		return (int) this.grid.z;
	}
	
	public int Yg(){
		return (int) this.grid.y;
	}
	
	public int Xg(){
		return (int) this.grid.x;
	}
	
	public float Xl() {
		return this.local.x;
	}
	
	public float Yl() {
		return this.local.y;
	}
	
	public float Zl() {
		return this.local.z;
	}

	public float Z() {
		return this.pos.z;
	}

	public float Y() {
		return this.pos.y;
	}

	public float X() {
		return this.pos.x;
	}

	public void AddNeighbor(PVector v) {
		this.neighbors.add(v);
	}

	public void AddNeighbor(Dot v, Dot u) {
		
		this.neighbors.add(PVector.sub(v.pos, u.pos));
	}

	public float DistanceTo(Dot n) {
		return this.pos.dist(n.pos);
	}

	public ArrayList<PVector> Neighbors() {
		return this.neighbors;
	}

	public PVector getNewPoint() {
		return new PVector(pos.x, pos.y, pos.z);
		// return this.pos;
	}
	
	public PVector getNewLocalPoint() {
		return new PVector(local.x, local.y, pos.z);
		// return this.pos;
	}

	public int posID() {
		return this.ID;
	}

	public int neiCount() {
		return this.neighbors.size();
	}

	@Override
	public String toString() {

		return pos.toString() + "\n" + value + " ID:" + ID ;
	}
	
	public String tooString() {
		StringBuffer print = new StringBuffer();
		for (PVector v : neighbors) {
			print.append(" " + v.x + ":" + v.y + ":" + v.z + " ;");
		}
		return local.toString() + "\n" + value + " ID:" + ID + "\n" + print;
	}

	public String toPrint() {
		StringBuffer print = new StringBuffer();
		for (PVector v : neighbors) {
			print.append(" " + v.x + ":" + v.y + ":" + v.z + " ;");
		}
		return pos.x + ":" + pos.y + ":" + pos.z + "\n" + print;
	}

	public String toPrintBlock() {
		StringBuffer print = new StringBuffer();
		for (PVector v : neighbors) {
			float vx,vy,vz;
			vx = v.x*1000;
			vx = Math.round(vx);
			vx /= 1000;
			
			vy = v.y*1000;
			vy = Math.round(vy);
			vy /= 1000;
			
			vz = v.z*1000;
			vz = Math.round(vz);
			vz /= 1000;
			
			print.append(" " + vx + ":" + vy + ":" + vz + " ;");
		}
		return local.x + ":" + local.y + ":" + local.z + "\n" + print +"\n";
	}
}
