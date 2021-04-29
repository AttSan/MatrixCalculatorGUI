import javax.swing.*;
import java.io.*;
import java.util.Arrays;
import java.awt.*;
import java.awt.event.*;

public class Matrix extends JPanel implements ActionListener, Serializable
{ 
    protected static int col; 
    protected static int row;
	protected static int lastCol; 
	protected static int lastRow;
	protected static double myMatrix [][];
	protected static double tempMatrix [][];  
	protected static JTextField inputField [][];
	protected static int result;
    protected static JButton minusB, plusB, inverseB, multiplyB, nMultiplyB, nDivisionB, getValueB, showMatrix,  
                                transposing, newMatrix, iconImage;
    protected static JPanel choosePanel [] = new JPanel[8];
            
     public Matrix()
     {
         col= 0; row= 0;
         myMatrix = new double [0][0];
         tempMatrix = new double [0][0];
         lastCol=0;
         lastRow=0;
     } //end of default constructor
     
     public Matrix (JTextField field [][] )
     {
         for(int temp = 0; temp < field.length; temp++)
         {
             for(int temp1 = 0; temp1 < field[0].length; temp1++)
             {
                 if(field[temp][temp1].getText().equals(""))
                 field[temp][temp1].setText("0");
             }
         }
     } //end of constructor 1
     
     public Matrix(int c, int r)
     {
    	 col=c; row=r;
     } //end of constructor 2
     
     //prompting for matrix's dimensions
     private static void Dimension() 
     {  
	      JTextField lField = new JTextField(5);
	      JTextField wField = new JTextField(5);
	      
	      //design input line
	      JPanel choosePanel [] = new JPanel [2];
	      choosePanel[0] = new JPanel();
	      choosePanel[1] = new JPanel();
	      choosePanel[0].add(new JLabel("Enter matrix dimensions") );
	      choosePanel[1].add(new JLabel("Rows:"));
	      choosePanel[1].add(lField);
	      choosePanel[1].add(Box.createHorizontalStrut(15)); 
	      choosePanel[1].add(new JLabel("Columns:"));
	      choosePanel[1].add(wField);
	        
	      result = JOptionPane.showConfirmDialog(null, choosePanel,
	               null,JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
	        
	      //save last dimensions
	      lastCol = col;
	      lastRow = row;
      
	      //ok option
	       if(result == 0)
	       {       
	         if(wField.getText().equals(""))
	             col = 0;
	         else
	         {
	             if(isInt(wField.getText()))
	             {
	                 col = Integer.parseInt(wField.getText());
	             }
	             else
	             {
	                 JOptionPane.showMessageDialog(null, "Wrong Dimensions");
	                 col = lastCol;
	                 row = lastRow;
	                 return;
	             }
	            
	             if(isInt(lField.getText()))
	             {
	                 row = Integer.parseInt(lField.getText());
	             }
	             else
	             {
	                 JOptionPane.showMessageDialog(null, "Wrong Dimensions");
	                 col = lastCol;
	                 row = lastRow;
	                 return;
	             }
	          
	         }
	       if(col < 1 || row < 1)
	       {
	           JOptionPane.showConfirmDialog(null, "You entered wrong dimensions", 
	                   "Error",JOptionPane.PLAIN_MESSAGE);
	           col  = lastCol;
	           row = lastRow;
	          
	       }
	       else
	       {
	           tempMatrix = myMatrix;
	           myMatrix = new double [row][col];
	            if(!getValue(myMatrix, "Fill your new matrix"))
	            {
	                //backup
	                myMatrix = tempMatrix;
	            }
	       }
	       } //end of if loop
	       else if(result == 1)
	       {
	           col = lastCol;
	           row = lastRow;
	       }
	     }//end of method getDimension
    
    //getting a matrix's values
    private static boolean getValue(double matrix [][], String title )
    {
	   int temp=0; int temp1=0;
	   String tempString=null;
	   String fileName= "matrix.txt";
	   PrintWriter outputStream = null;
	   
	   JPanel choosePanel [] = new JPanel [row+2];
	   choosePanel[0] = new JPanel();
	   choosePanel[0].add(new Label(title));
	   choosePanel[choosePanel.length-1] = new JPanel();
	   choosePanel[choosePanel.length-1].add(new Label("Consider space field as zeros?"));
	   inputField  = new JTextField [matrix.length][matrix[0].length];
	   
       for(temp = 1; temp <= matrix.length; temp++)
       {
           choosePanel[temp] = new JPanel();
           
           for(temp1 = 0; temp1 < matrix[0].length; temp1++)
           {
               inputField [temp-1][temp1] = new JTextField(3);
               choosePanel[temp].add(inputField [temp-1][temp1]);
               
               if(temp1 < matrix[0].length -1)
               {
            	   choosePanel[temp].add(Box.createHorizontalStrut(15)); 
               }
           }//end column loop
       }//end row loop
       
       result = JOptionPane.showConfirmDialog(null, choosePanel, 
               null, JOptionPane.OK_OPTION, JOptionPane.PLAIN_MESSAGE);
     
      if(result == 0)
      {
	       Matrix checkTextField= new Matrix(inputField);
	       
	       for(temp = 0; temp < matrix.length; temp++)
	       {
	        for(temp1 = 0; temp1< matrix[0].length; temp1++)
	        {
	        	tempString = inputField[temp][temp1].getText();
	        		        	
	            if(isDouble(tempString))
	            {
	               	matrix [temp][temp1] = Double.parseDouble(inputField[temp][temp1].getText());
	            } 
	            else
	            {
	                 JOptionPane.showMessageDialog(null, "You entered wrong elements");
	                 col = lastCol;
	                 row = lastRow;
	                    
	                 return false;
	            }                      
	         } //end of inside for loop
	       } //end of for loop
		     try
	       	 {
	       		outputStream= new PrintWriter(fileName);
	       		outputStream.println(Arrays.deepToString(matrix).replaceAll("],", "],"+
	       		System.getProperty("line.separator")));
       		}
	       	catch (IOException e)
	       	{
	       		//do nothing
	       	}
	       	outputStream.close();
	       return true;
      }
      else
          return false;
    }//end of method getValues
    
    public void actionPerformed(ActionEvent e) 
    {
        
        if(e.getSource() == showMatrix)
        {
            showMatrix(myMatrix, "Your Matrix");
        }
        if(e.getSource() == plusB)
        {
            matrixPlusMatrix();
        }
        
        else if(e.getSource() == minusB)
        {
            matrixMinusMatrix();
        }
         
        else    if(e.getSource() == multiplyB)
        {
            multiplyByMatrix();
        }
        else   if(e.getSource() == inverseB)
        {
            inverse();
        }
        
        else    if(e.getSource() ==  nMultiplyB)
        {
                multliplyByScalar();
        }
         else   if(e.getSource() == nDivisionB)
        {
            divideByScaler ();
        }
        
         else   if(e.getSource() == transposing )
        {
            guiTransposing(myMatrix);
        }
         
        else   if(e.getSource() == getValueB)
        {
            guiGetValue();
        }
        
        else   if(e.getSource() == newMatrix)
        {
            newMatrix();
        }
    } //end action performed

    
    protected static void showMatrix(double [][] matrix, String title)
    {
       int temp, temp1;
       double result; 
       	          
       JPanel choosePanel [] = new JPanel [matrix.length+1];
       choosePanel[0] = new JPanel ();
       choosePanel[0].add(new JLabel (title) );
   
       for(temp = 1; temp <= matrix.length; temp++)
       {
           choosePanel[temp] = new JPanel();
           
           for(temp1 = 0; temp1 < matrix[0].length; temp1++)
           {
               if(matrix[temp-1][temp1] == -0)
               {
            	   result= (matrix[temp-1][temp1] = 0);
               }
               choosePanel[temp].add(new JLabel(String.format("%.2f", matrix[temp-1][temp1])));
               
               if(temp1 < matrix[0].length -1)
               {
            	   choosePanel[temp].add(Box.createHorizontalStrut(15));
               }
           }//end col loop
       }//end row loop
       
	  if(col == 0 || row == 0)
	  {
		  JOptionPane.showMessageDialog(null, "You haven't entered any matrix");
	  }
	  else
	  {
		  JOptionPane.showMessageDialog(null, choosePanel, null, 
	    			JOptionPane.PLAIN_MESSAGE, null);
	  }  
	}//end of method showMatrix
	
	private static void matrixPlusMatrix()
	{
		if(myMatrix.length < 1)
		{
			JOptionPane.showMessageDialog(null, "You haven't entered any matrix");
		}
		else
		{
			double m2[][]=new double [row][col];
			double sum[][] = new double [row][col];
			
			if(getValue(m2, "Fill Aditional Matrix"))
			{
				for(int i=0;i<row;i++)
				{
					for(int j=0;j<col;j++)
					{
					    sum[i][j]=myMatrix[i][j]+m2[i][j];
					}
				}
			    showMatrix(sum, "Summition Result");
			}
		}//end else checking
	}//end of method matrixPlusMatrix
	    
	private static void matrixMinusMatrix()
	{
		if(myMatrix.length < 1)
	    {
			JOptionPane.showMessageDialog(null, "You haven't entered any matrix");
	    }
	    else
	    {
		    double m2[][]=new double [row][col];
		    double sub[][] = new double [row][col];
		    double temp [][] = new double [row][col];
		
		    if(getValue(m2, "Fill Subtracting Matrix"))
		    {
		    	for(int i=0;i<row;i++)
		    	{
		    		for(int j=0;j<col;j++)
		    		{
		    			temp[i][j]=(-1*m2[i][j]);
		    			sub[i][j]=myMatrix[i][j]+temp[i][j];
		    		}
		    	}
		    	showMatrix(sub, "Subtracting Result");
		    }
	     }//end else of checking
	} //end of method matrixMinusMatrix
	
	private static void multiplyByMatrix()
	{
		JTextField wField = new JTextField(5);
	    int col2 = 0;
	    double m2 [][] , results[][];
	    int sum;
	      
	    if(myMatrix.length < 1)
	    {
	    	JOptionPane.showMessageDialog(null, "You haven't entered any matrix");
	    }
	    else
	    {
	    	//design input line
	    	JPanel choosePanel [] = new JPanel [2];
	    	choosePanel [0] = new JPanel();
	    	choosePanel [1] = new JPanel();
	    	choosePanel[0].add(new JLabel("Enter Dimensions: ") );
	    	choosePanel[1].add(new JLabel("Rows:"));
	    	choosePanel[1].add(new JLabel(""+col));
	    	choosePanel[1].add(Box.createHorizontalStrut(15));
	    	choosePanel[1].add(new JLabel("Cols:"));
	    	choosePanel[1].add(wField);
	        
	    	result = JOptionPane.showConfirmDialog(null, choosePanel, 
	               null,JOptionPane.PLAIN_MESSAGE, 
	               JOptionPane.PLAIN_MESSAGE);
	      
	    	if(result == 0)
	    	{
	    		if(wField.getText().equals(""))
	    		{
	    			col2 = 0;
	    		}
	    		else
	    		{
	    			if(isInt(wField.getText()) )
	    			{
	    				col2 = Integer.parseInt(wField.getText());	
	    			}
	    		}
	     
	    		m2 = new double [col][col2];
	    		results = new double [row][col2];
	    		if(getValue(m2, "Fill multiplying matrix"))
	    		{
	    			for ( int i = 0 ; i < row ; i++ )
	    			{
	    				for ( int j = 0 ; j < col2 ; j++ )
	    				{   
	    					sum = 0;
	    					for ( int k = 0 ; k < col ; k++ )
	    					{
	    						sum +=  myMatrix[i][k]*m2[k][j];
	    					}	 
	    					results[i][j] = sum;
	    				}
	    			}
	    			showMatrix(results, "Mulitiplication Result");
	    		}//elements check
	    	}//dimensions check
	    	else
	    		return;
	    }//end else of checking
	} //end of method multiplyByMatrix
	
	private static void multliplyByScalar()
	{
	    double[][]results=new double [row][col];
	    double x;
	    String tempString;
	    
	    if(myMatrix.length < 1)
	        {
	            JOptionPane.showMessageDialog(null, "You haven't entered any matrix");
	            return;
	        }
	       
	    tempString = JOptionPane.showInputDialog(null,
	    		"Enter the scaler number for multiplying");
	
	    if (tempString == null)
	    {
	        return;
	    }
	    
	    else if(!tempString.equals(""))
	        x= Double.parseDouble(tempString);
	    else
	    {
	        JOptionPane.showMessageDialog(null, "You haven't entered a scaler");
	        return;
	    }
	    results = getMultliplyByScaler(myMatrix, x);
	    showMatrix(results, "Multiplication Result");
	}//end of method MultliplyByScaler

    
    private static double [][] getMultliplyByScaler (double [][] matrix , double x)
    {
	    double[][]results=new double [row][col];
	    int i,j;
	    
	    for (i=0;i<matrix.length;i++)
	    {
	        for(j=0;j<matrix[0].length;j++)
	        {
	        results[i][j] = x*matrix[i][j];
	        }
	    }
    	return results;
    }//end method multliplyByScalar
    
    private static void divideByScaler()
	{
	    double[][]results=new double [row][col];
	    int i,j;
	    double x;
	    String tempString;
	    
	    if(myMatrix.length < 1)
	        {
	            JOptionPane.showMessageDialog(null, "You haven't entered any matrix");
	            return;
	        }
	    
	    //prompting for the scaler
	    tempString = JOptionPane.showInputDialog
	    		("Enter the scaler number for dividing");
	    
	    if (tempString == null) 
	    {
	        return;
	    }
	    
	    else if(!tempString.equals(""))
	        x= Double.parseDouble(tempString);
	    
	    else
	    {
	        JOptionPane.showMessageDialog(null, "You haven't entered a scaler");
	        return;
	    }
	    
	    if(x == 0)
	    {
	        JOptionPane.showMessageDialog(null, "Excuse me we can't divid by 0");
	        return;
	    }
	
	    for (i=0;i<row;i++)
	    {
	        for(j=0;j<col;j++)
	        {
	        results[i][j] = myMatrix[i][j] / x;
	        }
	    }
	    showMatrix(results, "Dividing Result");  
	}//end method divideByScaler
    
    private static void guiGetValue()
    {
        if(myMatrix.length < 1)
        {
            JOptionPane.showMessageDialog(null, "You haven't entered any matrix");
        }
        else if(col != row)
        {
            JOptionPane.showMessageDialog(null, "You must enter square matrix");
        }
        else
        {
        	double result = getValue(myMatrix);
        	
        	JOptionPane.showMessageDialog(null, String.format("Determination's Value = %.2f", 
        			getValue(myMatrix) ), null, JOptionPane.PLAIN_MESSAGE, null);
        }
    }//end method guiGetValue
    
    private static void swap(double [] res1, double [] res2)
    {
        int temp;
        double tempDouble;
        
        for(temp = 0; temp < res1.length;temp++)
        {
            tempDouble = res1[temp];
            res1[temp] = res2[temp];
            res2[temp] = tempDouble;
        }
    } //end of method swap
    
    private static double getValue(double [][] matrix)
    {
    	int temp, temp1, temp2;
        double coeficient;
        double result = 1;
        int sign = 1; //1 for positive, -1 for negative
        int zeroCounter ;
        
       double res[][] = new double [matrix.length][matrix[0].length];
       
       //copy matrix
        for(temp = 0; temp < matrix.length; temp++)
        {
            for(temp1 = 0; temp1 < matrix[0].length; temp1++)
            {
                res[temp][temp1] = matrix[temp][temp1];;
            }   
        }
        
        //rearrange rows
        for(temp = 0; temp < res.length; temp++)
        {
            if(res[temp][temp] != 0)
                continue;
            
            for(temp1 = 1; temp1 < res.length -1 ; temp1++)
            {
                if( res[ (temp1 + temp ) % matrix.length][temp] != 0)
                {       //swapping
                swap(res[temp], res[(temp1 + temp ) % res.length]);    
                sign *= -1; 
                break;
                }
           }
        }
        
        //starting simplifing with gaues method
        for(temp = 1; temp < res.length; temp++)
        {
            for(temp1 = 0; temp1 < temp; temp1++)
            {
                //if required element = 0 || division = 0
                if(res[temp][temp1] == 0 || res[temp1][temp1] == 0)
                    continue;
                else
                {
                    zeroCounter = 0;
                    coeficient = res[temp][temp1]/res[temp1][temp1];
                }
                
            for(temp2 = 0; temp2 < res.length; temp2++)
            {
                res[temp][temp2] = res[temp][temp2]  
                         -res[temp1][temp2] * coeficient;
                
                if(res[temp][temp2] == 0 )
                    zeroCounter++;
            }
            
            //over flow zeros
            if(temp< res.length-1 && zeroCounter > temp)
            {
             swap(res[temp], res[temp+1]);
             sign *= -1;
             temp--;
            }
            }
        }
        
        for(temp = 0; temp < res.length; temp++)
        {
            result *= res[temp][temp];
        }
        return result * sign;
     } //end of method getValue
    
    private static void guiTransposing(double [][] matrix)
    {
        if(myMatrix.length < 1)
        {
            JOptionPane.showMessageDialog(null, "You haven't entered any matrix");
            return ;
        }

        double [][] transMatrix = new double[matrix[0].length][matrix.length];
        transMatrix = getTranspose(matrix);
        showMatrix(transMatrix, "Transposing Matrix");
    } //end of method guiTransposing
    
    private static double [][] getTranspose(double [][] matrix)
    {
        double [][] transportMatrix = new double[matrix[0].length][matrix.length];
        int temp1, temp; 
        
        for(temp = 0 ; temp < matrix[0].length; temp++)
        {
            for(temp1 = 0; temp1 < matrix.length; temp1++)
            {
                 transportMatrix[temp][temp1]  = 
                         matrix[temp1][temp]; //swap (temp, temp1)
            }
        }
        return transportMatrix;
    } //end of method getTranspose
    
    private static double [][] getMinor(int i, int j)
    {
        double [][] results = new double [row-1 ][col-1];
        int row_count = 0, col_count = 0;
        int temp, temp1;
        
        for(temp = 0; temp < row; temp++)
        {
            for(temp1 = 0; temp1 < col; temp1++)
            {
                if(temp != i && temp1 != j)
                {
                    results[row_count][col_count] = myMatrix[temp][temp1];
                    col_count++;
                }
            }//end col loop
            col_count = 0;
            if(i != temp)
                    row_count++;
        }//end row loop
        
        return results;
    } //end of method getMinor
    
    private static void inverse()
    {
        if(myMatrix.length < 1)
        {
            JOptionPane.showMessageDialog(null, "You haven't entered any matrix");
            return;
        }
        else if (col != row)
        {
            JOptionPane.showMessageDialog(null, "You must enter square matrix");
            return;
        }
        
        else if(getValue(myMatrix) == 0)
        {
            JOptionPane.showMessageDialog(null, "Your Matrix "
                    + "hasn't an inverse one\n\n" + "Because its value = 0");
                    return;
        }
        
        double [][] inverseMatrix = new double[row][col];
        double [][] minor = new double [row -1 ][col -1];
        double [][] cofactor = new double [row ][col];
        double delta; //myMatrix value
        int temp , temp1;
        
        for(temp = 0; temp < row; temp++) //get cofactor
        {
            for(temp1 = 0; temp1 < col; temp1++)
            {
                minor = getMinor (temp, temp1);
                double minorValue = getValue(minor);
                cofactor[temp][temp1] = Math.pow(-1.0, temp+temp1) * getValue(minor); 
            }
        }//end cofactor looping
        
        //transport cofactor to get ADJ
        cofactor = getTranspose(cofactor);  
        delta = getValue(myMatrix); 
        
        for(temp = 0; temp < row; temp++)
        {
            for(temp1 = 0; temp1 < col; temp1++)
            {
                inverseMatrix[temp][temp1] = cofactor[temp][temp1] / delta;
            }
        }
       showMatrix(inverseMatrix, "Inversing Matrix");
    }//end inverse
     
    private static boolean isInt (String str)
    {
       int temp;
       if (str.length() == '0')
           return false;
       
       for(temp = 0; temp < str.length();temp++)
       {
           if(str.charAt(temp) != '+' && str.charAt(temp) != '-'
                   && !Character.isDigit(str.charAt(temp)))
           {
               return false;
           }
       }
       return true;
    } //end of method isInt
   
    private static boolean isDouble (String str)
    {
       int temp;
       if (str.length() == '0')
           return false;
       
       for(temp = 0; temp<str.length();  temp++)
       {
           if(str.charAt(temp) != '+' && str.charAt(temp) != '-'&& str.charAt(temp) != '.'
                   && !Character.isDigit(str.charAt(temp)))
           {
               return false;
           }
       }
       return true;
    } //end of method isDouble
   
    private static void newMatrix ()
    {        
        Dimension();
    } //end of method newMatrix    
}//end of class