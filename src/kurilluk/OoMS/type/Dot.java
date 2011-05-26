/**
 * Dot class
 */
package kurilluk.OoMS.type;

import java.util.ArrayList;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Parser;

import processing.core.*;
import sun.java2d.pipe.ValidatePipe;
import kurilluk.OoMS.data.property;

/**
 * @author |< >|
 *
 */
public class Dot {
	 //FIELDS
    private PVector pos;
    private PVector local;
    private ArrayList<PVector> neighbors = new ArrayList<PVector>();
    
    private int ID = -1; //0 = fix, 1 = odd, 2 = even, 3 = newGenerated
    private double value = 0.0;
    //private String spacer1 = ":";
    
    //CONSTRUCTOR
    public Dot(PVector pt)
    {
        this.pos = pt;
        this.getID();
    }
    
    //PROPERTY
   // public float getX()
    
    //METHODS
    public void SetLocal(Block b){
    	float z = pos.z - b.getLocalZ();
    	this.local = new PVector(pos.x,pos.y,z);
    }
    
    private void getID()
    {
        int even = 82;
        int odd = 27;

        if (this.pos.z <= odd)
        {
            this.ID = 0;
        }
        else if (this.pos.z > odd && this.pos.z < odd + 1)
        {
            this.ID = 1;
        }
        else if (this.pos.z > even && this.pos.z < even + 1)
        {
            this.ID = 2;
        }
        else if (this.pos.z >= odd + 1)
        {
            this.ID = 3;
        }
    }

    public void AddProperties(String[] property)
    {
        this.value = Double.parseDouble(property[0]);
    }
    
    public double getVal(){
    	return this.value;
    }
    
    public float Zl(){
    	return this.local.z;
    }
    
    public float Z(){
    	return this.pos.z;
    }
    
    public float Y(){
    	return this.pos.y;
    }
    
    public float X(){
    	return this.pos.x;
    }
    
    
    public void AddNeighbor(PVector v){
    	this.neighbors.add(v);
    }
    
    public void AddNeighbor(Dot v, Dot u){
    	//TODO kontrola smeru vektora uv,vu.
    	this.neighbors.add(PVector.add(u.pos, v.pos));
    }
    
    public float DistanceTo (Dot n){
    	return this.pos.dist(n.pos);
    }
    
    
    public PVector getNewPoint(){
    	return new PVector(pos.x,pos.y,pos.z);
    	//return this.pos;
    }
    
    public int posID(){
    	return this.ID;
    }
    
    public int neiCount(){
    	return this.neighbors.size();
    }
    
    @Override
    public String toString(){
    	return pos.toString() + "\n" + value +" ID:"+ID;
    }

    public String toPrint(){
    	StringBuffer print = new StringBuffer();
    	for(PVector v: neighbors){print.append(" "+ v.x +":"+ v.y +":"+v.z+ " ;");}
    	return pos.x +":"+pos.y +":"+pos.z+"\n" + print;
    }
    
    public String toPrintBlock(){
    	StringBuffer print = new StringBuffer();
    	for(PVector v: neighbors){print.append(" "+ v.x +":"+ v.y +":"+v.z+ " ;");}
    	return pos.x +":"+pos.y +":"+local.z+"\n" + print;
    }
}
