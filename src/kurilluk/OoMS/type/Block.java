/**
 * 
 */
package kurilluk.OoMS.type;

import java.util.ArrayList;

/**
 * @author |< >|
 *
 */

public class Block {
	
	//STATIC ===========================================================
	
	
	//FIELDS
	private static int index = 0;
	private static ArrayList<Block> blocks = new ArrayList<Block>();
	private static final float maxZ = kurilluk.OoMS.data.property.maxZ;

	//METHODS
	public static void DotToBlock(Dot d){
		int i = findBlock(d);
		Block b = getBlock(i);
		d.SetLocal(b);
		b.add(d);
	}
	
	private static int findBlock(Dot d){
		float s = (float)(d.Z()/maxZ );
		double i = Math.floor(s);
		int x = (int)i;
		return x; 
	}
	
	private static Block getBlock(int blockNo){
		
		while(blocks.size() <= blockNo){
			blocks.add(new Block(blocks.size()));
		}
		return blocks.get(blockNo);
	}
	
	public static ArrayList<Block> GetBlocks(){
		return blocks;
	}
	
	
	//OBJECT ===========================================================
	
	
	//FIELDS
	private int blockIndex;
	private ArrayList<Dot> dots = new ArrayList<Dot>();
	private float localZ;

	//CONSTRUCTOR
	private Block (int index){
		this.blockIndex = index;
		this.localZ = Block.maxZ *index;
	}
	
	//METHODS
	private void add(Dot d){
		dots.add(d);
	}
	
	//PROPERTY
	public float getLocalZ(){
		return this.localZ;
	}
	
	public int getIndex(){
		return this.blockIndex;
	}
	
	public ArrayList<Dot> getDots(){
		return this.dots;
	}
}
