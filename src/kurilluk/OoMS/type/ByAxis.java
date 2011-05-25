/**
 * 
 */
package kurilluk.OoMS.type;

import java.util.Comparator;
import processing.core.*;
/**
 * @author |< >|
 *
 */

public class ByAxis implements Comparator<Dot> {
	
	private String axis = "z";
	
	public ByAxis(String axis){
		this.axis = axis;
	}

    @Override
    public int compare(Dot pt1,Dot pt2) {
    	if(axis == "y"){
            return (int)(pt1.Y() - pt2.Y());
        }else if(axis == "x"){
            return (int)(pt1.X() - pt2.X());
    	}else{
    		return (int)(pt1.Z() - pt2.Z());
    	}
}
    }
