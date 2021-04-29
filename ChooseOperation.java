 import java.awt.*;
import javax.swing.*;

public class ChooseOperation extends Matrix
{
	private int temp;
	private int length;
	private int breadth;
	
	public ChooseOperation() 
	{
		super();
		temp= 0;
		length= 0;
		breadth= 0;
	} //end of default constructor

	//for setting spaced fields as zeros
	public ChooseOperation(int t, int l, int b)
	{
		super();
		temp=t;
		length=l;
		breadth=b;
	} //end of constructor 2
	
	public ChooseOperation(int l, int b)
	{
		length=l;
		breadth=b;
		
		for(temp = 0; temp < choosePanel.length; temp++)
        {
            choosePanel [temp] = new JPanel ();
        }
	 
        ImageIcon logoTitle = new ImageIcon(getClass().getResource("cudlogo.png"));
		JLabel logoLabel = new JLabel(logoTitle);
		choosePanel[1].add(logoLabel);  
		
		JLabel credits = new JLabel("Attavee Santimano - 2016");
		choosePanel[6].add(credits);
		
        showMatrix= new JButton ("Your Matrix");
        showMatrix.setPreferredSize(new Dimension(l,b));
        showMatrix.addActionListener(this);
        choosePanel[2].add(showMatrix);
        
        plusB = new JButton ("Matrix addition");
        plusB.setPreferredSize(new Dimension(l,b));
        plusB.addActionListener(this);
        choosePanel[2].add(plusB);
        
        minusB = new JButton ("Matrix subtraction");
        minusB.setPreferredSize(new Dimension(l,b));
        minusB.addActionListener(this);
        choosePanel[2].add(minusB);
        
        multiplyB = new JButton ("Matrix multiplication");
        multiplyB.setPreferredSize(new Dimension(l,b));
        multiplyB.addActionListener(this);
        choosePanel[3].add(multiplyB);
        
        transposing = new JButton ("Matrix transposition");
        transposing.setPreferredSize(new Dimension(l,b));
        transposing.addActionListener(this);
        choosePanel[3].add(transposing);
  
        nMultiplyB = new JButton ("Multiplication by scalar");
        nMultiplyB.setPreferredSize(new Dimension(l,b));
        nMultiplyB.addActionListener(this);
        choosePanel[4].add(nMultiplyB);
  
        nDivisionB = new JButton ("Division by scalar");
        nDivisionB.setPreferredSize(new Dimension(l,b));
        nDivisionB.addActionListener(this);
        choosePanel[4].add(nDivisionB);
        
        if(col == row )
        {
	        getValueB = new JButton ("Matrix determinant");
	        getValueB.setPreferredSize(new Dimension(l,b));
	        getValueB.addActionListener(this);
	        choosePanel[3].add(getValueB);
	        
	        inverseB = new JButton ("Matrix inversion");
	        inverseB.setPreferredSize(new Dimension(l,b));
	        inverseB.addActionListener(this);
	        choosePanel[4].add(inverseB);
        }           
        
        newMatrix = new JButton("Enter Matrix");
        newMatrix.setPreferredSize(new Dimension(l,b));
        newMatrix.addActionListener(this);
        choosePanel[5].add(newMatrix);

        JOptionPane.showConfirmDialog(null, choosePanel, "Matrix Calculator- 2016",
               JOptionPane.CLOSED_OPTION , JOptionPane.PLAIN_MESSAGE);
	} //end of constructor 2
	
	//set methods
	public void setTemp(int t)
	{
		temp=t;
	}
	
	public void setLength(int l)
	{
		length=l;
	}
	
	public void setBreadth(int b)
	{
		breadth=b;
	}
	
	//get methods
	public int getTemp()
	{
		return temp;
	}
	
	public int getLength()
	{
		return length;
	}
	
	public int getBreadth()
	{
		return breadth;
	}
	
	//overriden method of Matrix class
	private void swap(double [] res1, double [] res2)
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
}

