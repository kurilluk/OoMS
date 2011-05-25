package kurilluk.OoMS.type;

import java.util.Comparator;

public class SortBlock implements Comparator<Dot> {

	@Override
	public int compare(Dot pt2, Dot pt1) {
        int m;
        m = (int)(pt2.Z() - pt1.Z());
        if (m != 0) return m;
        m = (int)(pt2.Y() - pt1.Y());
        if (m != 0) return m;
        m = (int)(pt2.X() - pt1.X());
        if (m != 0) return m;
        return 0;
	}
	

}
