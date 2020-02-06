import java.util.Arrays;

public class qm {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int input=3, output=1;
		int[][] matrix=new int[(int)(Math.pow(2,input))][input];
		for(int row=0;row<matrix.length;row++) {
			for(int col=0;col<matrix[row].length;col++) {
				matrix[row][col]=1;
			}
		}
		System.out.println(Arrays.deepToString(matrix));
	}

}
