/**
 * Generate 2dg do 3dg
 * runs application
 */
package kurilluk.OoMS;

import java.io.*;
import java.util.ArrayList;

//import com.sun.corba.se.impl.orb.ParserTable.TestBadServerIdHandler;

import kurilluk.OoMS.data.*;
import kurilluk.OoMS.preview.*;
import kurilluk.OoMS.process.Generate;
import kurilluk.OoMS.process.IO;
import processing.core.*;
import kurilluk.OoMS.type.Dot;
import kurilluk.OoMS.type.TestBlock;

/**
 * @author |< >|
 * 
 */

public class gen {

	// FIELDS
	private static String[] args;
	public static String name;

	/**
	 * @param args
	 * @throws FileNotFoundException
	 * @throws Exception
	 */

	// TODO help option

	public static void main(String[] arguments) {

		try {
			// load arguments
			args = arguments;
			name =args[0];

			// TODO analyze file type and choose reader
			// read 2dg file
			Dots.ReadIt(args[0]);
			// or read 3dg file
			// TODO ReadIt 3dg

			// argument Generate
			// TODO only if 2dg file was read
			if (isArgs("-g")) {
				// generate 2dg to 3dg
				Dots.From2dgTo3dg();
			}

			// argument Manufacture
			if (isArgs("-m")) {
				// compile to robot
				Dots.Manufacture();
				// d3d.Manufacture(args[0]); // generate and print to file
			}
			// argument makePicture
			if (isArgs("-pf")) {
				//AppletPicture ap = new AppletPicture();
				//ap.Open();
				kurilluk.OoMS.preview.AppletPicture.run(null);
				IO.PrintToFiles(args[0]);
				//kurilluk.OoMS.preview.AppletPicture.exit();
				System.exit(0);
			}

//			// argument Write to file
//			if (isArgs("-f")) {
//				IO.PrintToFiles(args[0]);
//			}

			// argument Preview
			if (isArgs("-v")) {
//				AppletWindow aw = new AppletWindow();
//				aw.Open();
				kurilluk.OoMS.preview.AppletWindow.run(null);
			}

			// argument Test
			if (isArgs("-t")) {
				tests();
			}
			


			// catch block
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("gen: Invalid option");
		} catch (IOException e) {
			System.out.println("gen: No such 2dg or 3dg file: " + args[0]);
		}

	}

	private static void tests() {
		System.out.println("test output:");
		testDots.WriteAll(Dots.Data());
		//testDots.PrintAll(Dots.Data());
		Block.Print();
	}

	private static boolean isArgs(String arg) {
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals(arg)) {
				return true;
			}
		}
		return false;
	}

}
