package kurilluk.OoMS.data;

import java.util.ArrayList;
import kurilluk.OoMS.type.Dot;


public class testDots {
	
	public static void WriteAll(ArrayList<Dot> a){
		System.out.println(a.size()+" dots in list");
		for(Dot o :a){
			System.out.println(o);
		}
	}
	
	public static void PrintAll(ArrayList<Dot> a){
		System.out.println(a.size()+" dots in list");
		for(Dot o :a){
			System.out.println(o.toPrint());
		}
	}

}
