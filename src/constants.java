// Software: K-Nearest Neighbor with MNIST database (Graphical User Interface in Java)
// Author: Hy Truong Son
// Major: BSc. Computer Science
// Class: 2013 - 2016
// Institution: Faculty of Informatics, Eotvos Lorand University, Budapest, Hungary
// Email: sonpascal93@gmail.com
// Website: http://people.inf.elte.hu/hytruongson/
// Copyright 2015 (c) Hy Truong Son. All rights reserved.
// Only use for academic purposes.

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class constants {
	
	static String Training_Image_Directory = "train-images.idx3-ubyte";
	static String Training_Label_Directory = "train-labels.idx1-ubyte";
	static String Testing_Image_Directory = "t10k-images.idx3-ubyte";
	static String Testing_Label_Directory = "t10k-labels.idx1-ubyte";
	
	static int nTraining = 0;
	static int nTesting;
	static int K;
	
	static int Default_nTraining = 60000;
	static int Default_nTesting = 10000;
	static int Default_K = 5;
	
	static int heightMainFrame = 480;
	static int widthMainCanvas = 480;
	static int heightMainCanvas = heightMainFrame;
	static int widthMainPanel = 240;
	static int heightMainPanel = heightMainFrame;
	static int widthMainFrame = widthMainCanvas + widthMainPanel;
	
	static int widthButton = 100;
	static int heightButton = 30;
	static int marginButton = 40;
	static int widthNextButton = 70;
	
	static int widthLabel = 210;
	static int heightLabel = heightButton;
	static int marginLabel = 30;
	static int widthViewLabel = 55;
	
	static int widthText = widthButton;
	static int heightText = heightButton;

	static int widthBox = 100;
	static int heightBox = heightLabel;
	static int marginBox = 50;
	
	static int smallMargin = 10;
	
	static JFrame MainFrame;
	static JCanvas MainCanvas;
	static JPanel MainPanel;
	static JButton TrainingButton, TestingButton, NextButton, StopButton, AboutButton;
	static JLabel TrainingLabel, TestingLabel, NeighborLabel, ViewLabel, ImageLabel;
	static JLabel Sample, Label, Predict, Rate, Nearest;
	static JTextField TrainingText, TestingText, NeighborText;
	static JComboBox ViewBox;
	static JProgressBar ProgressBar;
	static JEventQueue Events;
	
	static String ViewData[] = {"1 by 1", "Automatic"};
	static Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
	static TitledBorder titleMainPanel = BorderFactory.createTitledBorder(loweredetched, "Control");
	
}
