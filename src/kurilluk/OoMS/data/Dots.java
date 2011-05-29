/**
 * Dots data
 * read 2dg data to load dots
 * generate 3dg structure
 * validate structure for robots
 */

package kurilluk.OoMS.data;

import java.io.IOException;
import java.util.ArrayList;
import kurilluk.OoMS.type.Dot;
import kurilluk.OoMS.process.*;

/**
 * @author |< >|
 * 
 */

//TODO factory or singleton class 

public class Dots {

	// FIELDS
	
	// static
	private static ArrayList<Dot> data;

	// objects
	//public ArrayList<Dot> dots = new ArrayList<Dot>();
	

//	// CONSTRUCTOR
//	private Dots(String fileName) throws IOException {
//		Read(fileName);
//	}(fileName)
	
	
	// static
	// TODO different read type
	public static ArrayList<Dot> ReadIt(String fileName) throws IOException{
		data = IO.ReadDot(fileName);
		return data;
	}

	
	// METHODS
	public static ArrayList<Dot> Data(){
		return data;
	}
	
	public static void From2dgTo3dg(){
		data = Generate.D2dToD3d(data);
	}
	
	public static void Manufacture(){
		data = Manufacture.Generate(data);
	}
	
	
//	// all static methods are for processing
//	public static void SetInitialize(Dots dsi){
//		ds = dsi;
//	}
//	
//	public static DotDraw Initialize(){
//		DotDraw db = new DotDraw(ds.GetDots());
//		return db;
//	}
		

//	public ArrayList<Dot> GetDots() {
//		return this.dots;
//	}

	
//	public void AddDot(Dot d) {
//		this.dots.add(d);
//	}
	
}
