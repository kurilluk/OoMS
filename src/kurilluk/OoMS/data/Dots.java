/**
 * Dots data
 * read 2dg data to load dots
 * generate 3dg structure
 * validate structure for robots
 */

package kurilluk.OoMS.data;

import java.io.*;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;

import processing.core.PVector;

import kurilluk.OoMS.type.*;
import kurilluk.OoMS.data.*;

/**
 * @author |< >|
 * 
 */

public class Dots {

	// FIELDS

	public ArrayList<Dot> dots = new ArrayList<Dot>();
	private double v = kurilluk.OoMS.data.property.v;
	private double diameter = kurilluk.OoMS.data.property.dia;

	private ArrayList<Dot> err1 = new ArrayList<Dot>(); // intersect
	private ArrayList<Dot> err2 = new ArrayList<Dot>(); // no neighbors

	// CONSTRUCTOR
	public Dots(String fileName) throws IOException {
		Read(fileName);
	}

	public Dots() {
		// Generate3dGrid(this);
	}

	// METHODS
	/**
	 * @param fileName
	 * @throws IOException
	 */
	public void Read(String fileName) throws IOException {
		// TODO Extract the method(?)

		File f = new File(fileName);
		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(fr);

		String line1, line2;
		Dot d;

		while ((line1 = br.readLine()) != null) {
			d = new Dot(pointFromString(line1));

			line2 = br.readLine();

			d.AddProperties(line2.split(";"));
			// addDotProperty(d, line2.split(";"));

			dots.add(d);
			// System.out.println(d);
		}

		br.close();
		// return dots;
	}

	public ArrayList<Dot> GetDots() {
		return this.dots;
	}

	// interval
	public Dots Generate3dGrid() {
		Dots d3d = new Dots();

		for (Dot d : dots) {
			int i = 0;

			// osetrenie nulovych guliciek
			if (d.posID() == 0 && d.getVal() < v / 3) {
			} else {
				// ratio 1:1, full height from value
				while (d.getVal() >= d.Z() + (v * i)) {
					PVector pt = d.getNewPoint();
					pt.z = (float) ((float) d.Z() + (v * i));
					d3d.AddDot(new Dot(pt));
					i++;

					if (d.posID() > 0) {
						i++;
					}
				}
			}
		}
		return d3d;
	}

	// private Dot addDotProperty(Dot d, String[] property){return d;};

	public void Manufacture(String fileName) {
		// sort list
		sortDot();
		// remove and add neighbors / manufacture test (control),remove no
		// neighbors
		createBlocks();
		analyzeBlockDots();
		// analyzeDots();
		// print to file
		printToFiles(fileName);

	}

	private void createBlocks() {
		for (Dot d : dots) {
			Block.DotToBlock(d);
		}
	}

	private void printToFiles(String fileName) {
		int pis = fileName.length();
		fileName = fileName.substring(0, pis - 4);
		// String[] name = fileName.split(new String("."));
		// if(name.length > 0){
		// System.out.println(name[1]+".3dg");
		// }else{
		// System.out.println(fileName +".3dg");
		// }

		try {
			for (Block b : Block.GetBlocks()) {
				File f = new File(fileName + "_" + b.getIndex() + ".3dg");
				FileWriter fw = new FileWriter(f);
				BufferedWriter bw = new BufferedWriter(fw);

				for (Dot d : b.getDots()) {
					bw.write(d.toPrintBlock());
				}
				bw.close();
			}
		} catch (IOException e) {
			// TODO ioExcetption
			System.out.println("File write error.");
		}
	}

	private void printToFile(String fileName) {
		int pis = fileName.length();
		fileName = fileName.substring(0, pis - 4);
		// String[] name = fileName.split(new String("."));
		// if(name.length > 0){
		// System.out.println(name[1]+".3dg");
		// }else{
		// System.out.println(fileName +".3dg");
		// }

		try {
			File f = new File(fileName + ".3dg");
			FileWriter fw = new FileWriter(f);
			BufferedWriter bw = new BufferedWriter(fw);
			for (Dot d : dots) {
				bw.write(d.toPrint());
			}
			bw.close();
		} catch (IOException e) {
			// TODO ioExcetption
			System.out.println("File write error.");
		}
	}

	private void analyzeBlockDots() {
		// foreach dot in block //pre blok je to kratsie, mensie pole na prezeranie..
		//for (Block b : Block.GetBlocks()) {
			for (int i = 0; i < dots.size(); i++) {
				// for 1st layer add neighbors
				if (dots.get(i).Zl() == 0) {
					dots.get(i).AddNeighbor(new PVector(0, 0, -1));
					continue;
				}

				// loop of finding and adding neighbors (tests intersects)
				int j = 0;
				while (dots.get(i).Zl() > dots.get(j).Zl()) {
					// if (dots[i].Z < dots[j].Z + rozsah)
					// {
					double distance = dots.get(i).DistanceTo(dots.get(j));
					if (distance <= diameter + 1 && distance > diameter - 2) {
						dots.get(i).AddNeighbor(dots.get(j), dots.get(i));
					}
					if (distance < diameter - 2) {
						// error intersection
						this.err1.add(dots.get(i));
					}

					// }
					j++;
				}
				if (dots.get(i).neiCount() < 1) {
					this.err2.add(dots.get(i));
					dots.remove(i);
					i -= 1;
				}

			}
		//}
	}

	// add neighbor to dot and test intersection
	private void analyzeDots() {
		// foreach dot in block
		for (int i = 0; i < dots.size(); i++) {
			// for 1st layer add neighbors
			if (dots.get(i).Z() == 0) {
				dots.get(i).AddNeighbor(new PVector(0, 0, -1));
				continue;
			}

			// loop of finding and adding neighbors (tests intersects)
			int j = 0;
			while (dots.get(i).Z() > dots.get(j).Z()) {
				// if (dots[i].Z < dots[j].Z + rozsah)
				// {
				double distance = dots.get(i).DistanceTo(dots.get(j));
				if (distance <= diameter + 1 && distance > diameter - 2) {
					dots.get(i).AddNeighbor(dots.get(j), dots.get(i));
				}
				if (distance < diameter - 2) {
					// error intersection
					this.err1.add(dots.get(i));
				}

				// }
				j++;
			}
			if (dots.get(i).neiCount() < 1) {
				this.err2.add(dots.get(i));
				dots.remove(i);
				i -= 1;
			}

		}
	}

	private void sortDot() {
		// ByAxis byX = new ByAxis("x");
		// ByAxis byY = new ByAxis("y");
		// ByAxis byZ = new ByAxis("z");
		// //Collections.sort(this.dots,byZ);
		// //Collections.sort(this.dots,byY);
		// //Collections.sort(this.dots,byX);

		SortBlock sortB = new SortBlock();
		Collections.sort(this.dots, sortB);
	}

	public void AddDot(Dot d) {
		this.dots.add(d);
	}

	private PVector pointFromString(String str) {
		// point from string
		float x, y, z;
		try {
			String[] point = str.split(property.spacer1);
			if (point.length != 3)
				throw new Exception();

			x = Float.parseFloat(point[0]);
			y = Float.parseFloat(point[1]);
			z = Float.parseFloat(point[2]);
			return new PVector(x, y, z);
		} catch (Exception e) {
			System.out.println("No valid point data in file");
			System.exit(0);
			return null;
		}
	}
}
