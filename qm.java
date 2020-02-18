import java.util.Arrays;

public class qm {

	public static void main(String[] args) {
		// maximum input and output values per instruction
		final int maxInput=100, maxOutput=26;

		
//******ONLY ADJUST THESE VALUES MANUALLY UNTIL IT IMPORTS FROM THE FILE******************************
		// read input # and output # from text file and assign to these variable
		int input=3, output=1;
//****************************************************************************************
		
		// will determine the number of rows based upon the number of inputs
		int combination=(int)(Math.pow(2,input));

//******ADJUST THESE MANUALLY IF NOT UTILIZING THE FILE OUTPUTS. IE 3x1=8 rows, 4x1=16 rows and we only have 8 values...****************
		// read output from text file and assign to this array. Array built by number of rows (combination) and output columns
		int out[][]=new int[combination][output];
		out[0][0]=0; // manual assignment replaced with file values for x
		out[1][0]=0;
		out[2][0]=1;
		out[3][0]=0;
		out[4][0]=1;
		out[5][0]=1;
		out[6][0]=1;
		out[7][0]=0;
	//	out[0][1]=1; // manual assignment replaced with file values for y
	//	out[1][1]=1;
	//	out[2][1]=0;
	//	out[3][1]=1;
	//	out[4][1]=0;
	//	out[5][1]=0;
	//	out[6][1]=0;
	//	out[7][1]=1;
		
//*********************************************************************************begin 1
		// delete the manual array info below once integrating with Jeremy's method
		int[][] oMatrix, adjacent, group, combo, analyze;
		int[][] adj=new int[combination][input];
		
		adj[0][0]=-1; //0
		adj[0][1]=-1;
		adj[0][2]=-1; 
		adj[1][0]=-1; //1
		adj[1][1]=-1;
		adj[1][2]=-1;	
		adj[2][0]=0; //2
		adj[2][1]=1;
		adj[2][2]=0; 
		adj[3][0]=-1; //3
		adj[3][1]=-1;
		adj[3][2]=-1; 
		adj[4][0]=1; //4
		adj[4][1]=0;
		adj[4][2]=0;
		adj[5][0]=1; //5
		adj[5][1]=0;
		adj[5][2]=1;
		adj[6][0]=1; //6
		adj[6][1]=1;
		adj[6][2]=0;
		adj[7][0]=-1; //7
		adj[7][1]=-1;
		adj[7][2]=-1;
		
		int[] num=new int[adj.length];
		for(int i=0;i<num.length;i++) {
			if(adj[i][0]!=-1) {
				num[i]=i;
			}
			else
				num[i]=-1;
		}
		
//*******************************************************************************end 1		
		
		if(input<maxInput) {
			if(output<maxOutput) {
				oMatrix=oMatrix(out, output, input);
				adjacent=adjacent(adj,input, 2); //***************************** change the parameter array to whatever Jeremy comes back with
				group=recurse(adjacent, input, 2);
				combo=combine(group, input);
				analyze=analyze(combo, num, input);
				print(analyze, input);
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
		
		for(int c=1;c<input;c++) { // from 1 to input (number of columns)
			i=0;
			t=0;
			for(int n=matrix.length-1;n>=0;n--) { // from 0 to matrix.length (number of rows)
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
	
	public static int[][] adjacent(int[][] a, int input, int r){
		int sum=0, sum1=0, sum2=0, k=0, index=0, c=0; // counter for each row
		int tc=(int)(Math.pow(2,input)*2); // total combinations * 2 to account for maximum duplicates 
		int[][] b=new int[tc][input+r], d;
			
		for(int i=0;i<a.length-1;i++) { // row1 0 - length-1
			for(k=i+1+k;k<a.length;k++) { // row2 1 - length
				for(int j=0;j<input;j++) { // column 0 - input
					if(a[i][j]!=-1&&a[k][j]!=-1) { // if comparisons aren't -1 meaning they aren't potentially adjacent
						sum1=sum1+a[i][j];
						sum2=sum2+a[k][j];
						if(a[i][j]!=a[k][j]) { // if the comparisons aren't equal
							sum++; // increase the count of unequal numbers in comparison
						}
					}
				}
				if(sum==1) { // if the sum = 1, it means there is only 1 number different
					if(sum1==sum2+1||sum1+1==sum2) { // sum1 = row1 total, sum2 = row2 total. Must be within 1
//					System.out.println(i+" and "+k+" are adjacent"); // print the 2 adjacent numbers 
					for(c=0;c<input;c++) { 
						if(a[i][c]!=-1&&a[k][c]!=-1) { // if the comparisons aren't -1
							if(a[i][c]!=a[k][c]) { // if the comparisons aren't equal
								b[index][c]=-2; // new array b contains -2 at first open spot
							}
							if(a[i][c]==a[k][c]) { // if the comparisons are equal
								b[index][c]=a[i][c]; // new array b = the first value
							}
									b[index][input+r-2]=i; // new array b at the end -1 equals value 1
									b[index][input+r-1]=k; // new array b at the end equals value 2
						}
					}
					index++; // increase the index for new array b to next available spot
					}
				}
				sum=0; // reset the row sum to 0
				sum1=0;
				sum2=0;
			}
			k=0; // reset the k iterator to 0
		}
		
		d=new int[index][input+r];
		for(int y=0;y<d.length;y++) { 
			for(int z=0;z<input+r;z++) {
			d[y][z]=b[y][z];
			}
		};
		return d;
	}
	
	public static int[][] recurse(int[][] a, int input, int recurse){
		int r=recurse*2,k=0,sum=0,c=0,index=0;
		int[][] b=new int[a.length][input+r],d; // array max of a.length and input+r 
		
		for(int i=0;i<a.length-1;i++) { // row1 0 - length-1
			for(k=i+1+k;k<a.length;k++) { // row2 1 - length
				for(int j=0;j<input;j++) { // column 0 - input
					if(a[i][j]!=a[k][j]) { // if the comparisons aren't equal
						sum++; // increase the count of unequal numbers in comparison
						}
					}
				if(sum==1) { // if the sum = 1, it means there is only 1 number different
//					System.out.println(i+" and "+k+" are adjacent"); // print the 2 adjacent numbers 
					for(c=0;c<input;c++) {
						if(a[i][c]==-2&&a[k][c]==-2) {
							if(a[i][c]!=a[k][c]) { // if the comparisons aren't equal
								b[index][c]=-2; // new array b contains -2 at first open spot
							}
							if(a[i][c]==a[k][c]) { // if the comparisons are equal
								b[index][c]=a[i][c]; // new array b = the first value
							}
								for(int x=input;x<input+(r/2);x++) { // (3 - 4)
									b[index][x]=a[i][x]; // b[0][3] = a[row1][3], b[0][4]=a[row1][4] up to 5 - 1 =4 
									b[index][x+(r/2)]=a[k][x]; // b[0][5] = a[row2][3], b[0][6]=a[row2][4] up to 7		
								}
						}
					}
					index++; // increase the index for new array b to next available spot
				}
				sum=0;
				}
			k=0;
		}
		
		d=new int[index][input+r];
		for(int y=0;y<index;y++) {
			for(int z=0;z<input+r;z++) {
			d[y][z]=b[y][z];
			}
		}
		
		if(index>0)
			recurse(d, input, r+1);
		if(d.length==0)
			return a;
		
		return d;
	}
	
	public static int[][] combine(int[][] a, int input){
		int r=0;
		for(int i=0;i<a.length;i++) {
			for(int j=0;j<a[i].length;j++) {
				r=a[i].length;
			}
		}
		
		int[][] b=new int[a.length][r],d;
		int k=0, sum=0, count=0, index=0;
		for(int i=0;i<a.length-1;i++) { // row1 0 - length-1
			for(k=i+1;k<a.length;k++) { // row2 1 - length
				for(int j=input;j<r;j++) { // column0 0 - input
					for(int l=input;l<r;l++) { // column1 1 - input
						if(a[i][j]==a[k][l]) { // if the comparisons are equal
							sum++; // increase the count of unequal numbers in comparison
						}
					}
					count++;
				}			
				if(sum==count) {
					for(int h=0;h<r;h++) { // column 0 - input
							a[k][h]=-1;
					}
					index++;
				}
				sum=0;
				count=0;
			}
		}
		int co=0;
		d=new int[index][r]; // 3 row x 7 column
		for(int i=0;i<a.length;i++) { // rows 0 to 5, moves all matches to the left if not -1
			for(int j=0;j<a[i].length;j++) { // columns 0 to 6
				if(a[i][j]!=-1) {
					b[co][j]=a[i][j];
				}
			}
			if(a[i][0]==-1)
			co--;
			co++;
		}
		
		for(int i=0;i<index;i++) { // rows 0 to 2
			for(int j=0;j<a[i].length;j++) { // columns 0 to 6
				d[i][j]=b[i][j];
			}
		}
		
		if(d.length==0)
			return a;
	
		return d;
	}
	
	public static int[][] analyze(int[][] a, int[] num, int input) {
		boolean[][] b=new boolean[a.length][num.length];

		int r=0;
		for(int i=0;i<a.length;i++) {
			for(int j=0;j<a[i].length;j++) {
				r=a[i].length;
			}
		}
		int[][] d=new int[a.length][input];

		for(int i=0;i<num.length;i++) { // num rows 0 to 7, contains included numbers
			for(int j=0;j<a.length;j++) { // a rows 0 to 2
				for(int k=input;k<r;k++) { // a columns 3 to 6
					if(a[j][k]==num[i]) { // if (a[][] matches num[]) then its covered
						b[j][i]=true;
					}
				}
			}
		}
		
		
		// traverse the chart from the top row across columns looking to remove unneeded simplifications
		// if 0,1,2,3 are covered under row1 then a int[] holds the first row# that matches and then breaks
		// after storing the row # that contains a copy, the array should then hold the final values

		int[] c=new int[r]; // 5
		
		for(int g=0;g<b.length;g++) { // b row from 0 to 2
			for(int i=0;i<b[g].length;i++) { // b column from 0 to 7
					if(b[g][i]==true) {
						c[g]=g;
					}
					if(b[g][i]==false) {
						for(int k=g+1;k<b.length;k++) { // row from b to 
							if(b[k][i]==true) {
								c[g]=g;
							}
						}
					}
			}
		}
		
		int match=0;
		for(int i=0;i<c.length;i++) { // c traverse
			for(int j=0;j<b.length;j++) { // b traverse
				if(i==c[j]) {
					for(int g=0;g<input;g++) {
						d[i][g]=a[i][g];
					}
				}
			}
		}
		
		return d;
	}
	
	public static void print(int[][] a, int input) {
		String[] convert=new String[input];
		for(int i=0;i<convert.length;i++) {
			convert[i]="IN"+i;
		}
		System.out.println(Arrays.deepToString(a));
		String[] output=new String[input*a.length];
		int g=0;
		
		for(int i=0;i<a.length;i++) { // row
			for(int j=0;j<a[i].length;j++) { // col
				if(a[i][j]==0) {
					output[g]=convert[i]+"'";
					
				}
				if(a[i][j]==1) {
					System.out.println(convert[j]);
				}
				else
					output[]=-1;
			}
		}
	}

}
