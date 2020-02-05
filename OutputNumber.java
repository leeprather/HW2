import java.util.ArrayList;

public class OutputNumber{
	private int data;
	private Integer category;
	private static int categoryCount = 0;
	private static int previousNumOfOnes = 0;
	private int associatedInput;
	private ArrayList<Integer> blankedPosFromRight = new ArrayList<>();
	
	public OutputNumber(int data, int associatedInput) { 
	//Constructor
		 this.data = data;
		 this.associatedInput = associatedInput;
		// this.category = getNumOfOnes(associatedInput);
		
		 if (previousNumOfOnes < getNumOfOnes(associatedInput)) { 
			categoryCount++;
		 	this.category = categoryCount; previousNumOfOnes =
		 	getNumOfOnes(associatedInput); 
		 	}
		 else {
			 this.category = categoryCount; 
			 }
		 
			 
	}
	
    public static int getNumOfOnes(int x) { 
    /**************************************************
	  *	Counts the number of ones in the binary string *
     **************************************************/
   	 return Integer.bitCount(x);
    }

    //Getters and Setters
	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public Integer getCategory() {
		return category;
	}

	public int getAssociatedInput() {
		return associatedInput;
	}
	public void addBlankedPos(int posFromRight) { 
		blankedPosFromRight.add(posFromRight);
	}
	public ArrayList<Integer> getBlankedPos() { 
		return blankedPosFromRight;
	}

    
}
