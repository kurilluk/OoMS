/**
 * Generate 2dg do 3dg
 * runs application
 */
package kurilluk.OoMS;

import java.io.*;

import com.sun.corba.se.impl.orb.ParserTable.TestBadServerIdHandler;

import kurilluk.OoMS.data.*;
import kurilluk.OoMS.type.TestBlock;

/**
 * @author |< >|
 * 
 */

public class gen {

	/**
	 * @param args
	 * @throws FileNotFoundException
	 * @throws Exception
	 */

	// TODO help option

	public static void main(String[] args) {
		try {
			// String fileName = args[0];
			Dots d2d = new Dots(args[0]);
			testDots.WriteAll(d2d.GetDots());
			// generate to 3d
			// Dots d3d = d2d.Generate3dGrid();
			// testDots.WriteAll(d3d.GetDots());
			// TODO compile to robot
			// if args[1]exit uste it else split name and use it
			// d3d.Manufacture(args[0]); //generate and print to file
			// testDots.PrintAll(d3d.GetDots());
			// TestBlock.Test();
			if (args.length > 1) {
				if (args[1].equals("-v")) {
					//TODO preview
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("gen: Invalid option");
		} catch (IOException e) {
			System.out.println("gen: No such 2dg file: " + args[0]);
		}

		// finally{
		// //br.close();
		// //info
		// //System.out.println("gen: argument was: "+ args[0]);
		// }

	}

}
