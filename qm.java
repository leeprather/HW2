import java.util.Arrays;

public class qm {

	public static void main(String[] args) {
		// maximum input and output values per instruction
		final int maxInput=100, maxOutput=26;

		
//******ONLY ADJUST THESE VALUES MANUALLY UNTIL IT IMPORTS FROM THE FILE******************************
		// read input # and output # from text file and assign to these variable
		int input=7, output=1;
//****************************************************************************************
		
		// will determine the number of rows based upon the number of inputs
		int combination=(int)(Math.pow(2,input));

//******ADJUST THESE MANUALLY IF NOT UTILIZING THE FILE OUTPUTS. IE 3x1=8 rows, 4x1=16 rows and we only have 8 values...****************
		// read output from text file and assign to this array. Array built by number of rows (combination) and output columns
		int out[][]=new int[combination][output];
		out[0][0]=0; // manual assignment replaced with file values
		out[1][0]=0;
		out[2][0]=1;
		out[3][0]=0;
		out[4][0]=1;
		out[5][0]=1;
		out[6][0]=1;
		out[7][0]=0;
//**************************************************************************************************************************************
		
		int[][] oMatrix;
		
		if(input<maxInput) {
			if(output<maxOutput) {
				oMatrix=oMatrix(out, output, input);
				System.out.println(Arrays.deepToString(oMatrix));
			}
			else
				System.out.println("The input is larger than the maximum of "+maxOutput+".");
		}
		else
			System.out.println("The input is larger than the maximum of "+maxInput+".");
		
		oMatrix=oMatrix(out, output, input);
}
	
	public static int[][] oMatrix(int[][] out, int output, int input) {
		int col=output+input;
		int combination=(int)(Math.pow(2,input)); // total rows
		int[][] oMatrix=out;
		int[][] matrix=new int[combination][col];
		
		int len=matrix.length;
		
		int j=0;
		for(int k=0;k<input;k++) {
			int i=0;
			for(i=i+len/2;i<len;i++) { // from row 0 to row 		
				matrix[i][j]=1;
		}
			j++;
			len=len/2;
		}
		
		int next=0, i=0, t=0;
		
		for(int c=1;c<input;c++) { // from 1 to 2 (number of columns)
			i=0;
			t=0;
			for(int n=matrix.length-1;n>=0;n--) { // from 0 to 8 (number of rows)
				if(matrix[n][c]==1) {
					next=n+1; // row 
					for(i=next+i;i<=matrix.length-1;i++) { // 4, 5
						matrix[i][c]=matrix[t][c]; 
						t++;
					}
				}
			}
		}
		for(int i1=input;i1<input+output;i1++) {	
			for(int k1=0;k1<matrix.length;k1++){
				int j1=0;
				matrix[k1][input]=out[k1][j1];
				j++;
				}
			}
		return matrix;
	}
}
