import java.io.File;
import java.io.FileNotFoundException;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
public class Homework2 {

		public static void main(String []args){
	     /********************************
	   	  *    Main driving function     *
	   	  ********************************/
	    	//Dependencies
	    	 int numofInputs = 0;
	    	 int numofOutputs = 0;
		     ArrayList<Integer> rawoutputs = new ArrayList<>(); 
		     ArrayList<OutputNumber> outputClassArry = new ArrayList<>(); 
		     ArrayList<OutputNumber> adjArry = new ArrayList<>(); 
	    	 File inputFile = new File("inputs.txt");

	    	
	    	
	    	//Read Input File
	    	try {
				Scanner scan = new Scanner(inputFile);
				String currentLine = "";
				int i = 0;
				while (scan.hasNext()) { 
					currentLine = scan.nextLine();
					if (i!=0) { 
						//Set the output for i'th input
						rawoutputs.add(Integer.parseInt(currentLine));
						
					}else { 
						//Read amount of inputs and outputs
						numofInputs = Integer.parseInt(currentLine.split(" ")[0]);
						numofOutputs = Integer.parseInt(currentLine.split(" ")[1]);
						System.out.println("Number Inputs:  " + numofInputs);
						System.out.println("Number Outputs:  " + numofOutputs);
					}
					i++;
				}
				
				scan.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
	    	 
	    	
	    	//Run Algorithm
	    		//ONLY add into data structure if output is 1
	    			int i = 0;
	    			for(int currNumber : rawoutputs) { 
	    				if (currNumber == 1) { 
	    					outputClassArry.add(new OutputNumber(currNumber,i));
	    				}
	    				i++;
			    			}
	    			
	    		//Sort the array by number of ones in binary
	    			outputClassArry.sort(new CategorySorter());
	    			
		    			
	    		//Run Through each category and find adjacency 
	    			for (OutputNumber fixedNumber : outputClassArry) { 
    					//System.out.println("Searching Outputs With " + fixedNumber.getCategory() + " Number of Ones");
	    				for (OutputNumber movingNumber : outputClassArry) { 
	    					if (movingNumber.getCategory() == fixedNumber.getCategory()+1) { 
	    						Object[] currComplement = isComplement(fixedNumber.getAssociatedInput(),movingNumber.getAssociatedInput());
		    					if ((boolean) currComplement[0]) { 
		    						System.out.println("Adjacency Found: " + fixedNumber.getAssociatedInput() + ", "
		    								+ movingNumber.getAssociatedInput());
		    						//Find where adj occurs and mark char as blank in OutputNumber
		    						fixedNumber.addBlankedPos((int) currComplement[1]);
		    						adjArry.add(fixedNumber);
		    					}
	    				}
	    				
	    				}
	    				
	    			}   
 
	     }
	     
	     
	     public static Object[] isComplement(int x, int y) { 
	       /********************************************************************************
	        *  Find if the pair is adjacent of each other:
	        *      After solving several of these by hand, I noticed
	        *      that the bitwise XOR of the two numbers plus the lower
	        *      bound number should equal the higher number ONLY IF
	        *      binary adjacency exists. 
	        *    
	        *      I also found that the equation (2^X = XOR) directly shows 
	        *      what position in the binary the adjacency occurs. I simplified 
	        *      this by using algebra. 2^X = XOR is the same as (Xlog(2) = log(XOR) OR
	        *      X = log(XOR)/log(2)
	        ********************************************************************************/
	    	 
	        //Initiate base answer
	        boolean complement = false;
	        int placement = 0;
	         
	         
	        //Find min number and max number 
	        int min = Math.min(x,y);
	        int max = Math.max(x,y);
	        int xor = (x^y);
	        

	        //Logic For the adjacency
	        if ((min + xor == max) && (min != max)) { 
	            complement = true;
	        }else{
	            complement = false;
	        }
	        
	        //Logic for the placement
	        if (complement) { 
	            placement = (int) (Math.log(xor)/Math.log(2)+1);
	        }
	        Object[] answer = {complement,placement};      
	        return answer;      
	     }  
	     
	     public static int getNumOfOnes(int x) { 
	     /**************************************************
		  *	Counts the number of ones in the binary string *
	      **************************************************/
	    	 return Integer.bitCount(x);
	     }
	     
	     public static int convertBitToInt(String bitToConvert) { 
		 /***************************************
		  *	Converts a bit string to an integer *
		 ****************************************/
	    	 return Integer.parseInt(bitToConvert, 2);
	     }
	     
	     public static String convertIntToBit(int intToConvert) { 
		 /***************************************
		 *	Converts an integer to a bit string *
		 ****************************************/
	    	 return Integer.toBinaryString(intToConvert);
	     }
	     
	     
	}


