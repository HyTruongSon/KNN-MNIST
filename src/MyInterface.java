// Software: K-Nearest Neighbor with MNIST database (Graphical User Interface in Java)
// Author: Hy Truong Son
// Major: BSc. Computer Science
// Class: 2013 - 2016
// Institution: Faculty of Informatics, Eotvos Lorand University, Budapest, Hungary
// Email: sonpascal93@gmail.com
// Website: http://people.inf.elte.hu/hytruongson/
// Copyright 2015 (c) Hy Truong Son. All rights reserved.
// Only use for academic purposes.

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class MyInterface extends MainProgram{
	
	public static void About(){
		System.out.println("Program: KNN algorithm with MNIST database");
		System.out.println("Author: Hy Truong Son");
		System.out.println("Email: sonpascal93@gmail.com");
		System.out.println("Website: http://people.inf.elte.hu/hytruongson");
		System.out.println("Copyright ©2013-2016, Hy Truong Son. All rights reserved.");
	}
	
	public static void InitGUI(){
		MainFrame = new JFrame();
		MainFrame.setTitle("KNN algorithm with MNIST database");
		MainFrame.setSize(widthMainFrame, heightMainFrame);
		MainFrame.setResizable(false);
		MainFrame.setLocationRelativeTo(null);
		MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MainPanel = new JPanel();
		MainPanel.setBounds(widthMainCanvas, 0, widthMainPanel, heightMainPanel);
		titleMainPanel.setTitleJustification(TitledBorder.RIGHT);
		MainPanel.setBorder(titleMainPanel);
		MainPanel.setLayout(null);
		
		int ButtonX = widthMainCanvas + (widthMainPanel - widthButton) / 2;
		int PositionY = marginButton;
		
		TrainingButton = new JButton("Training");
		TrainingButton.setBounds(ButtonX, PositionY, widthButton, heightButton);
		MainFrame.add(TrainingButton);
		
		PositionY += marginButton;
		
		TestingButton = new JButton("Testing");
		TestingButton.setBounds(ButtonX, PositionY, widthButton, heightButton);
		MainFrame.add(TestingButton);
		
		int LabelX = widthMainCanvas + (widthMainPanel - widthLabel) / 2;
		PositionY += marginButton;
		
		TrainingLabel = new JLabel("Number of training samples:");
		TrainingLabel.setBounds(LabelX, PositionY, widthLabel, heightLabel);
		TrainingLabel.setOpaque(true);
		MainFrame.add(TrainingLabel);
		
		PositionY += marginLabel;
		
		TrainingText = new JTextField();
		TrainingText.setBounds(ButtonX, PositionY, widthText, heightText);
		TrainingText.setEditable(true);
		TrainingText.setHorizontalAlignment(JTextField.CENTER);
		TrainingText.setText(Integer.toString(Default_nTraining));
		MainFrame.add(TrainingText);
		
		PositionY += marginButton;
		
		TestingLabel = new JLabel("Number of testing samples:");
		TestingLabel.setBounds(LabelX, PositionY, widthLabel, heightLabel);
		TestingLabel.setOpaque(true);
		MainFrame.add(TestingLabel);
		
		PositionY += marginLabel;
		
		TestingText = new JTextField();
		TestingText.setBounds(ButtonX, PositionY, widthText, heightText);
		TestingText.setEditable(true);
		TestingText.setHorizontalAlignment(JTextField.CENTER);
		TestingText.setText(Integer.toString(Default_nTesting));
		MainFrame.add(TestingText);
		
		PositionY += marginButton;
		
		NeighborLabel = new JLabel("Number of neighbors:");
		NeighborLabel.setBounds(LabelX, PositionY, widthLabel, heightLabel);
		NeighborLabel.setOpaque(true);
		MainFrame.add(NeighborLabel);
		
		PositionY += marginLabel;
		
		NeighborText = new JTextField();
		NeighborText.setBounds(ButtonX, PositionY, widthText, heightText);
		NeighborText.setEditable(true);
		NeighborText.setHorizontalAlignment(JTextField.CENTER);
		NeighborText.setText(Integer.toString(Default_K));
		MainFrame.add(NeighborText);
		
		PositionY += marginBox;
		
		ViewLabel = new JLabel("Test:");
		ViewLabel.setBounds(LabelX, PositionY, widthViewLabel, heightLabel);
		ViewLabel.setOpaque(true);
		MainFrame.add(ViewLabel);
		
		ViewBox = new JComboBox(ViewData);
		ViewBox.setBounds(LabelX + widthViewLabel, PositionY, widthBox, heightBox);
		MainFrame.add(ViewBox);
		
		PositionY += marginBox;
		AboutButton = new JButton("About");
		AboutButton.setBounds(ButtonX, PositionY, widthButton, heightButton);
		MainFrame.add(AboutButton);
		
		int widthProgressBar = widthMainCanvas - 2 * marginButton;
		int PositionX = marginButton;
		PositionY = marginButton;
		
		ProgressBar = new JProgressBar();
		ProgressBar.setBounds(PositionX, PositionY, widthProgressBar, heightButton);
		ProgressBar.setValue(0);
		ProgressBar.setStringPainted(true);
		ProgressBar.setBorderPainted(true);
		MainFrame.add(ProgressBar);
		
		PositionX = (widthMainCanvas - 2 * widthNextButton - smallMargin) / 2;
		PositionY += marginButton;
		
		NextButton = new JButton("Next");
		NextButton.setBounds(PositionX, PositionY, widthNextButton, heightButton);
		NextButton.setEnabled(false);
		MainFrame.add(NextButton);
		
		PositionX += widthNextButton + smallMargin;
		
		StopButton = new JButton("Stop");
		StopButton.setBounds(PositionX, PositionY, widthNextButton, heightButton);
		StopButton.setEnabled(false);
		MainFrame.add(StopButton);
		
		PositionX = marginButton;
		PositionY += marginButton;
				
		Rate = new JLabel("Recognition rate: 100%");
		Rate.setBounds(PositionX, PositionY, widthLabel, heightLabel);
		Rate.setOpaque(true);
		Rate.setVisible(false);
		MainFrame.add(Rate);
		
		ImageLabel = new JLabel("Image:");
		ImageLabel.setBounds(marginButton + Rate.getWidth(), PositionY, widthLabel, heightLabel);
		ImageLabel.setOpaque(true);
		ImageLabel.setVisible(false);
		MainFrame.add(ImageLabel);
		
		PositionY += marginLabel;
		
		Sample = new JLabel("Sample: 60000");
		Sample.setBounds(PositionX, PositionY, widthLabel, heightLabel);
		Sample.setOpaque(true);
		Sample.setVisible(false);
		MainFrame.add(Sample);
		
		PositionY += marginLabel;
		
		Label = new JLabel("Label: 0");
		Label.setBounds(PositionX, PositionY, widthLabel, heightLabel);
		Label.setOpaque(true);
		Label.setVisible(false);
		MainFrame.add(Label);
		
		PositionY += marginLabel;
		
		Predict = new JLabel("Predict: 0");
		Predict.setBounds(PositionX, PositionY, widthLabel, heightLabel);
		Predict.setOpaque(true);
		Predict.setVisible(false);
		MainFrame.add(Predict);

		PositionY += marginLabel;
		
		Nearest = new JLabel("Some nearest neighbors:");
		Nearest.setBounds(PositionX, PositionY, widthLabel, heightLabel);
		Nearest.setOpaque(true);
		Nearest.setVisible(false);
		MainFrame.add(Nearest);
		
		MainCanvas = new JCanvas();
		MainCanvas.setBounds(0, 0, widthMainCanvas, heightMainCanvas);
		MainCanvas.setBackground(MainPanel.getBackground());
		
		MainFrame.add(MainPanel);
		MainFrame.add(MainCanvas);
		MainFrame.setVisible(true);
	}
	
	public static void InitEventListener(){
		Events = new JEventQueue();
		Events.listenTo(TrainingButton, "Training");
		Events.listenTo(TestingButton, "Testing");
		Events.listenTo(NextButton, "Next");
		Events.listenTo(StopButton, "Stop");
		Events.listenTo(AboutButton, "About");
	}
	
}
