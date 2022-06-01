import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Dimension;
import javax.swing.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.filechooser.*;
public class GUI{
	
	//Frequency Table
	static String freqTable = "";
	//Huffman Tree
	static String huffmanTree = "";
	//Encoded Message
	static String EncodedMessage = "";
	//Decoded Message
	static String DecodedMessage = "";
	
	public static JTable table;
	
	//Text on top of frame variable
	public static JTextField text;
	//Frame/Window variable
	public static JFrame frame;
	//Button to put inside of the frame
	public static JButton button;
	//Button for inserting a string manually
	//public static JButton button2;
	//Label to show the text you have submitted
	public static JLabel output;
	public static JLabel message1;
	//Panel to attach button to and label/textfields
	public static JPanel panel;
	//Textfield to enter in string 
	public static JTextField textField;
	
	public static File file;
	public static Scanner fileIntake;
	public int respond;
	public static JFileChooser job;
	
	//Holds our textFile text
	public static String line = "";
	
	public static void refreshing(){
	huffmanTree = new String("");
	EncodedMessage = new String("");
	DecodedMessage = new String("");
	freqTable = new String("");
	line = new String("");
	}
	
	//Constructor
	public GUI(){
		refreshing();
		startGUI();
	}
	
	public void PipeLine(String thing){
		//Closes old frame
		frame.dispose();
		frame = new JFrame("Huffman Fax");
		frame.setSize(750,750);
		frame.show();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel();
		Huffman.FaxBro(thing);
		//CodeBook = Huffman.CookBook;
		message1 = new JLabel("Encoded Message");
		
		
		//Frequency Tree
		JTextArea freqText = new JTextArea(Huffman.freqTree);
		freqText.setVisible(true);
		//encodedText.setText(Huffman.Encoded);
		freqText.setLineWrap(false);
		freqText.setEditable(false);
		JScrollPane freqPane = new JScrollPane(freqText, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		freqPane.setColumnHeaderView(new JLabel("Frequency Tree {character : frequency}"));
		freqPane.setPreferredSize(new Dimension(250,250));
		
		//Huffman Tree
		JTextArea huffText = new JTextArea(Huffman.huffMan);
		huffText.setVisible(true);
		//encodedText.setText(Huffman.Encoded);
		huffText.setLineWrap(false);
		huffText.setEditable(false);
		JScrollPane huffPane = new JScrollPane(huffText, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		huffPane.setColumnHeaderView(new JLabel("Huffman Tree {character : weight}"));
		huffPane.setPreferredSize(new Dimension(250,250));
		
		
		//Frequency Table
		String columnNames1[] = {"Character", "Frequency"};
		DefaultTableModel tableModel1 = new DefaultTableModel(columnNames1,Huffman.freq.length); 
		JTable table1 = new JTable(tableModel1);
		for(int i = 0; i < Huffman.freq.length; i++){
			table1.setValueAt(Huffman.car[i], i, 0);
			//String temp = String.valueOf(Huffman.freq[i]);
			table1.setValueAt(Huffman.freq[i], i, 1);
		}
		JScrollPane tableScroll1 = new JScrollPane(table1);
		table1.setPreferredScrollableViewportSize(new Dimension(150,150));
		
		
		
		//Making table for Codes
		String columnNames[] = {"Character", "Code"};
		DefaultTableModel tableModel = new DefaultTableModel(columnNames,Huffman.freq.length); 
		table = new JTable(tableModel);
		//table.setValueAt("Character", 0,0);
		//table.setValueAt("Code", 0,1);
		for(int i = 0; i < Huffman.freq.length; i++){
			table.setValueAt(Huffman.car[i], i, 0);
			table.setValueAt(Huffman.Booker[i], i, 1);
		}
		JScrollPane tableScroll = new JScrollPane(table);
		table.setPreferredScrollableViewportSize(new Dimension(150,150));
		
		//Making table for Encoded Message
		/*
		String columnNames1[] = {"Encoded Message"};
		DefaultTableModel tableModel1 = new DefaultTableModel(columnNames1,1); 
		JTable table1 = new JTable(tableModel1);
		table1.setValueAt(Huffman.Encoded, 0,0);
		JScrollPane tableScroll1 = new JScrollPane(table1);
		table.setPreferredScrollableViewportSize(new Dimension(150,150));
		*/
		
		//Encoded Message
		JTextArea encodedText = new JTextArea(Huffman.Encoded);
		encodedText.setVisible(true);
		//encodedText.setText(Huffman.Encoded);
		encodedText.setLineWrap(true);
		encodedText.setEditable(false);
		JScrollPane encodePane = new JScrollPane(encodedText, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		encodePane.setColumnHeaderView(new JLabel("Encoded Message"));
		encodePane.setPreferredSize(new Dimension(150,150));
		//encodePane.add(encodedText);
		
		//Decoded Message
		JTextArea decodedText = new JTextArea(Huffman.DecodedMes);
		decodedText.setVisible(true);
		//encodedText.setText(Huffman.Encoded);
		decodedText.setLineWrap(true);
		decodedText.setEditable(false);
		JScrollPane decodePane = new JScrollPane(decodedText, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		decodePane.setColumnHeaderView(new JLabel("Decoded Message"));
		decodePane.setPreferredSize(new Dimension(150,150));


		//Try Again Button
		JLabel trying = new JLabel("Press 'RETRY' to restart program (or) Click 'X' in the upper right corner to leave...");
		JButton retry = new JButton("Retry");
		
		frame.add(panel);
		panel.add(freqPane);
		panel.add(huffPane);
		panel.add(tableScroll1);
		panel.add(tableScroll);
		panel.add(encodePane);
		panel.add(decodePane);
		
		panel.add(trying);
		panel.add(retry);
		//panel.add(encodedText);
		retry.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				System.out.println("User is retrying.");
				System.out.println("Restarting...");
				frame.dispose();
				GUI input = new GUI();
			}
		});
		
		//panel.add(message1);
		//message1.show();
	}
	
	public void startGUI(){
		//Creates new java frame window
		frame = new JFrame("Project Huffman");
		//Sets frame size(in pixels)
		frame.setSize(500,500);
		//Listens to see if frame was closed
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel();
		//textField = new JTextField(50);
		//panel.add(textField);
		frame.add(panel);
		message1 = new JLabel("Click 'OPEN' to select a file (or) Click 'X' in the upper right corner to leave..");
		panel.add(message1);
		//Puts button into frame
		button = new JButton("Open");
		panel.add(button);
		//button2 = new JButton("Insert");
		//panel.add(button2);
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				//button2.hide();
				button.hide();
				message1.hide();
				//Opens FileViewer using Java Swing
				job = new JFileChooser();
				job.setAcceptAllFileFilterUsed(false);
				FileNameExtensionFilter restriction = new FileNameExtensionFilter(".txt files", "txt");
				FileNameExtensionFilter restrictionJava = new FileNameExtensionFilter(".java files", "java");
				job.addChoosableFileFilter(restriction);
				job.addChoosableFileFilter(restrictionJava);
				job.showSaveDialog(null);
		if(respond == JFileChooser.APPROVE_OPTION){
			file = job.getSelectedFile();
			
			try{
				fileIntake = new Scanner(file);
				if(file.isFile()){
					while(fileIntake.hasNextLine()){
						line += fileIntake.nextLine() + "";
					}
				}
				else{
					System.out.println("Not a file...");
				}
						//System.out.println(line);
						PipeLine(line);
				fileIntake.close();
			}catch(Exception e){
				System.out.println("User cancelled.");
				System.out.println("Restarting...");
				frame.dispose();
				GUI input = new GUI();
			}
		}
			}
		});
		
		//Shows frame
		frame.show();
	}
	
	public static void main(String[] args){
		GUI input = new GUI();
	}
}