import java.util.Comparator;
public class CategorySorter implements Comparator<OutputNumber>  {
	    @Override
	    public int compare(OutputNumber o1, OutputNumber o2) {
	        return o1.getCategory().compareTo(o2.getCategory());
	    }
}
