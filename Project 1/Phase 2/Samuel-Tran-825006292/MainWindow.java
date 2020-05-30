//Samuel Tran
//CSCE 314-502
//UIN: 825006292
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.math.BigInteger;
import java.util.InputMismatchException;

import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.SwingConstants;

public class MainWindow extends JFrame
{
	private static final long serialVersionUID = -3880026026104218593L;
	private Primes m_Primes = new Primes();
	private JTextField tfPrimeFileName;
	private JTextField tfCrossFileName;
	private JLabel lblPrimeCount;
	private JLabel lblCrossCount;
	private JLabel lblLargestPrime;
	private JLabel lblLargestCross;
	private JLabel lblStatus;
	private int sizeOfPrimeList = 0;
	private int sizeOfCrossList = 0;
	
	MainWindow(String name, Primes p)
	{
		JFrame frame = new JFrame(name);
		frame.setSize(1000, 400);
		GridBagLayout gridLayout = new GridBagLayout();
		frame.getContentPane().setBackground(new Color(80, 0, 0));
		frame.getContentPane().setLayout(gridLayout);
		
		GridBagConstraints gbcDialog = new GridBagConstraints();
		gbcDialog.fill = GridBagConstraints.HORIZONTAL;
		gbcDialog.anchor = GridBagConstraints.WEST;
		gbcDialog.ipady = 10;
		gbcDialog.weightx = .5;
		gbcDialog.insets = new Insets(1,2,0,0);
		gbcDialog.gridx = 0;
		gbcDialog.gridy = 0;
		
		//Default Panel Layout
		GridBagConstraints gbcPanel = new GridBagConstraints();
		gbcPanel.anchor = GridBagConstraints.WEST;
		gbcPanel.weightx = .5;
		gbcPanel.insets = new Insets(1,2,0,0);
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 0;
		
		//Prime File Panel Layout
		JPanel pnlPrime = new JPanel();
		pnlPrime.setLayout(new GridBagLayout());
		
		//Prime File Text Input DEFAULT
		tfPrimeFileName = new JTextField(Config.PRIMEFILENAME);
		tfPrimeFileName.setColumns(60);
		pnlPrime.add(tfPrimeFileName, gbcPanel);
		
		lblPrimeCount = new JLabel("0");
		lblPrimeCount.setFont(new Font("Tahoma", Font.BOLD, 12));
		gbcPanel.anchor = GridBagConstraints.CENTER;
		gbcPanel.gridx = 1;
		pnlPrime.add(lblPrimeCount, gbcPanel);
		
		JLabel lblPrimeFile = new JLabel("Primes File");
		lblPrimeFile.setFont(new Font("Tahoma", Font.PLAIN, 24));
		gbcPanel.anchor = GridBagConstraints.WEST;
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 1;
		pnlPrime.add(lblPrimeFile, gbcPanel);
		
		//LOAD BUTTON FOR PRIME FILE
		JButton btnLoad1 = new JButton("Load");
		btnLoad1.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		      	try
		      	{
		      		sizeOfPrimeList = 0;
		      		String strPrimeFileName = tfPrimeFileName.getText();		//Prime File Text Input UI
		      		if(FileAccess.loadPrimes(p, strPrimeFileName) == true) {
		      			lblStatus.setText("Status: Prime list loaded successfully.");
		      			sizeOfPrimeList = p.primeCount() + 1;
		      			updateStats();
		      		}
		      		
		      	}
		      	catch(InputMismatchException ex)
		      	{
		      		lblStatus.setText("Status: Prime list load failure.");
		      	}
		      	catch(NumberFormatException ex)
		      	{
		      		lblStatus.setText("Status: Prime list load failure.");
		      	}
		      	catch(ArrayIndexOutOfBoundsException ex) {
		      		lblStatus.setText("Status: Prime list load failure.");
		      	}
		      	
		      }
		    });
		gbcPanel.anchor = GridBagConstraints.EAST;
		pnlPrime.add(btnLoad1, gbcPanel);
		
		//SAVE BUTTON FOR PRIME FILE
		JButton btnSave1 = new JButton("Save");
		btnSave1.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		      	try
		      	{
		      		String strPrimeFileName = tfPrimeFileName.getText();		//Prime File Text Input UI
		      		if(FileAccess.savePrimes(m_Primes, strPrimeFileName) == true) {
		      			lblStatus.setText("Status: Prime list saved successfully.");
		      			updateStats();
		      		}
		      		
		      	}
		      	catch(InputMismatchException ex)
		      	{
		      		lblStatus.setText("Status: Prime list save failure.");
		      	}
		      	catch(NumberFormatException ex)
		      	{
		      		lblStatus.setText("Status: Prime list save failure.");
		      	}
		      	catch(ArrayIndexOutOfBoundsException ex) {
		      		lblStatus.setText("Status: Prime list save failure.");
		      	}
		      	
		      }
		    });
		gbcPanel.gridx = 1;
		pnlPrime.add(btnSave1, gbcPanel);
		
		//Prime Panel is added to MainWindow
		frame.add(pnlPrime, gbcDialog);
		
		//Hexagon Cross File Panel Layout
		JPanel pnlCross = new JPanel();
		pnlCross.setLayout(new GridBagLayout());
		//Hexagon Cross File Text Input DEFAULT
		tfCrossFileName = new JTextField(Config.CROSSFILENAME);
		tfCrossFileName.setColumns(60);
		gbcPanel.anchor = GridBagConstraints.WEST;
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 0;
		pnlCross.add(tfCrossFileName, gbcPanel);
		
		lblCrossCount = new JLabel("0");
		lblCrossCount.setFont(new Font("Tahoma", Font.BOLD, 12));
		gbcPanel.anchor = GridBagConstraints.CENTER;
		gbcPanel.gridx = 1;
		pnlCross.add(lblCrossCount, gbcPanel);
		
		JLabel lblCrossFile = new JLabel("Hexagon Cross File");
		lblCrossFile.setFont(new Font("Tahoma", Font.PLAIN, 24));
		gbcPanel.anchor = GridBagConstraints.WEST;
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 1;
		pnlCross.add(lblCrossFile, gbcPanel);
		
		//LOAD BUTTON FOR CROSS FILE
		JButton btnLoad2 = new JButton("Load");
		btnLoad2.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		      	try
		      	{
		      		sizeOfCrossList = 0;
		      		String strCrossFileName = tfCrossFileName.getText();		//Hexagon Cross File Text Input UI
		      		if(FileAccess.loadCrosses(p, strCrossFileName) == true) {
		      			lblStatus.setText("Status: Cross list loaded successfully.");
		      			sizeOfCrossList = p.crossesCount() + 1;
		      			updateStats();
		      		}
		      		
		      	}
		      	catch(InputMismatchException ex)
		      	{
		      		lblStatus.setText("Status: Cross list load failure.");
		      	}
		      	catch(NumberFormatException ex)
		      	{
		      		lblStatus.setText("Status: Cross list load failure.");
		      	}
		      	catch(ArrayIndexOutOfBoundsException ex) {
		      		lblStatus.setText("Status: Cross list load failure.");
		      	}
		      	
		      }
		    });
		gbcPanel.anchor = GridBagConstraints.EAST;
		pnlCross.add(btnLoad2, gbcPanel);
		
		//SAVE BUTTON FOR CROSS FILE
		JButton btnSave2 = new JButton("Save");
		btnSave2.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		      	try
		      	{
		      		String strCrossFileName = tfCrossFileName.getText();		//Crosses File Text Input UI
		      		if(FileAccess.saveCrosses(m_Primes, strCrossFileName) == true) {
		      			lblStatus.setText("Status: Cross list saved successfully.");
		      			updateStats();
		      		}
		      		
		      	}
		      	catch(InputMismatchException ex)
		      	{
		      		lblStatus.setText("Status: Cross list save failure.");
		      	}
		      	catch(NumberFormatException ex)
		      	{
		      		lblStatus.setText("Status: Cross list save failure.");
		      	}
		      	catch(ArrayIndexOutOfBoundsException ex) {
		      		lblStatus.setText("Status: Cross list save failure.");
		      	}
		      	
		      }
		    });
		gbcPanel.gridx = 1;
		pnlCross.add(btnSave2, gbcPanel);
		
		gbcDialog.gridy = 1;
		frame.add(pnlCross, gbcDialog);
		
		//Button Panel Layout
		JPanel pnlButtons = new JPanel();
		pnlButtons.setLayout(new GridBagLayout());
		
		//Generate Primes BUTTON
		JButton btnGeneratePrimes = new JButton("Generate Primes");
		btnGeneratePrimes.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		      	try
		      	{
		      		popupGeneratePrimes();
		      	}
		      	catch(Exception ex)
		      	{
		      		lblStatus.setText("Status: ERROR - Could not popup Generate Primes window.");
		      	}
		      }
		    });
		gbcPanel.anchor = GridBagConstraints.WEST;
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 0;
		pnlButtons.add(btnGeneratePrimes, gbcPanel);
		
		lblLargestPrime = new JLabel("The largest prime has 0 digits");
		gbcPanel.anchor = GridBagConstraints.NORTH;
		gbcPanel.gridx = 1;
		pnlButtons.add(lblLargestPrime, gbcPanel);
		
		lblLargestCross = new JLabel("The largest cross has 0 and 0 digits");
		gbcPanel.anchor = GridBagConstraints.SOUTH;
		gbcPanel.gridx = 1;
		pnlButtons.add(lblLargestCross, gbcPanel);
		
		//Generate Crosses Button
		JButton btnGenerateCrosses = new JButton("Generate Crosses");
		btnGenerateCrosses.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		      	try
		      	{
		      		m_Primes.generateTwinPrimes();
		      		m_Primes.generateHexPrimes();
		      		updateStats();
		      		lblStatus.setText("Status: Excited. Crosses have been generated.");
		      	}
		      	catch(Exception ex)
		      	{
		      		lblStatus.setText("Status: Could not generate crosses");
		      	}
		      }
		    });
		gbcPanel.anchor = GridBagConstraints.EAST;
		gbcPanel.gridx = 2;
		pnlButtons.add(btnGenerateCrosses, gbcPanel);		
		
		//Buttons Panel is added to MainWindow
		gbcDialog.gridy = 2;
		frame.add(pnlButtons, gbcDialog);
		
		//Status Panel Layout
		JPanel pnlStatus = new JPanel();
		pnlStatus.setLayout(new GridBagLayout());

		gbcPanel.anchor = GridBagConstraints.SOUTHWEST;
		gbcPanel.weightx = .5;
		gbcPanel.insets = new Insets(1,2,0,0);
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 1;

		lblStatus = new JLabel("Status: Bored.");
		lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 12));
		pnlStatus.add(lblStatus, gbcPanel);
		
		//Status Panel is added to MainWindow
		gbcDialog.gridy = 3;
		frame.add(pnlStatus, gbcDialog);
		
		frame.pack();
		frame.setVisible(true);
		
	}

	protected void popupGeneratePrimes()
	{
		JDialog dPrimes = new JDialog(this, "Prime Number Generation");
		GridBagLayout gridLayout = new GridBagLayout();
		dPrimes.getContentPane().setBackground(new Color(128, 0, 0));
		dPrimes.getContentPane().setLayout(gridLayout);
		
		GridBagConstraints gbcDialog = new GridBagConstraints();
		gbcDialog.fill = GridBagConstraints.HORIZONTAL;
		gbcDialog.anchor = GridBagConstraints.WEST;
		gbcDialog.ipady = 10;
		gbcDialog.weightx = .5;
		gbcDialog.insets = new Insets(1,2,0,0);
		gbcDialog.gridx = 0;
		gbcDialog.gridy = 0;
		
		GridBagConstraints gbcPanel = new GridBagConstraints();
		gbcPanel.anchor = GridBagConstraints.WEST;
		gbcPanel.weightx = .5;
		gbcPanel.insets = new Insets(1,2,0,0);
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 0;
		
		JPanel pnlGenerate = new JPanel();
		pnlGenerate.setLayout(new GridBagLayout());
		
		JLabel lblCount = new JLabel("Number of Primes to Generate");
		lblCount.setFont(new Font("Tahoma", Font.PLAIN, 12));
		pnlGenerate.add(lblCount, gbcPanel);
		
		JTextField tfCount = new JTextField();
		lblCount.setLabelFor(tfCount);
		tfCount.setColumns(30);
		gbcPanel.gridx = 1;
		pnlGenerate.add(tfCount, gbcPanel);
		
		JLabel lblStart = new JLabel("Starting Number (does not have to be prime)");
		lblStart.setFont(new Font("Tahoma", Font.PLAIN, 12));
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 1;
		pnlGenerate.add(lblStart, gbcPanel);
		
		JTextField tfStart = new JTextField();
		lblStart.setLabelFor(tfStart);
		tfStart.setColumns(30);
		gbcPanel.gridx = 1;
		pnlGenerate.add(tfStart, gbcPanel);
		
		dPrimes.add(pnlGenerate, gbcDialog);
		
		JPanel pnlButtons = new JPanel();
		pnlButtons.setLayout(new GridBagLayout());
		
		JButton btnGeneratePrimes = new JButton("Generate Primes");
		btnGeneratePrimes.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
      	try
      	{
      		BigInteger start = new BigInteger(tfStart.getText());
      		int count = Integer.parseInt(tfCount.getText());
       		m_Primes.generatePrimes(start, count);
       		lblStatus.setText("Status: Excited. Primes have been generated.");
       		updateStats();
      		dPrimes.dispose();
      	}
      	catch(NumberFormatException ex)
      	{
      		lblStatus.setText("You failed to type in an integer successfully. You are terrible at math. Shame.");
      		dPrimes.dispose();
      	}
      	catch(ArrayIndexOutOfBoundsException ex)
      	{
      		lblStatus.setText("Number of Primes cannot be zero or negative");
      		dPrimes.dispose();
      	}
      	
      }
    });
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 0;
		pnlButtons.add(btnGeneratePrimes, gbcPanel);
		
		JButton btnCancel = new JButton("Cancel Generation");
		btnCancel.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
      	dPrimes.dispose();
      }
    });
		gbcPanel.anchor = GridBagConstraints.EAST;
		gbcPanel.gridx = 1;
		pnlButtons.add(btnCancel, gbcPanel);		
		
		gbcDialog.gridy = 1;
		dPrimes.add(pnlButtons, gbcDialog);
		
		JPanel pnlStatus = new JPanel();
		pnlStatus.setLayout(new GridBagLayout());

		gbcPanel.anchor = GridBagConstraints.SOUTHWEST;
		gbcPanel.weightx = .5;
		gbcPanel.insets = new Insets(1,2,0,0);
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 1;

		JLabel lblNotice = new JLabel("Warning: This application is single threaded, and will freeze while generating primes.");
		lblNotice.setFont(new Font("Tahoma", Font.PLAIN, 12));
		pnlStatus.add(lblNotice, gbcPanel);
		
		gbcDialog.gridy = 2;
		dPrimes.add(pnlStatus, gbcDialog);
		
		dPrimes.setSize(200,200);
		dPrimes.pack(); // Knowing what this is and why it is needed is important. You should read the documentation on this function!
		dPrimes.setVisible(true);		
	}

	// This function updates all the GUI statistics. (# of primes, # of crosses, etc)
	private void updateStats()
	{
		lblPrimeCount.setText(String.valueOf(sizeOfPrimeList));
		lblCrossCount.setText(String.valueOf(sizeOfCrossList));
		lblLargestPrime.setText("The largest prime has " + String.valueOf(m_Primes.sizeofLastPrime()) + " digits.");
		if(m_Primes.crossesCount() >= 0) {
			lblLargestCross.setText("The largest cross has " + String.valueOf(m_Primes.sizeofLastCross().left()) + " and " + String.valueOf(m_Primes.sizeofLastCross().right()) + " digits.");
		}
 	}

}
