// Software: K-Nearest Neighbor with MNIST database (Graphical User Interface in Java)
// Author: Hy Truong Son
// Major: BSc. Computer Science
// Class: 2013 - 2016
// Institution: Faculty of Informatics, Eotvos Lorand University, Budapest, Hungary
// Email: sonpascal93@gmail.com
// Website: http://people.inf.elte.hu/hytruongson/
// Copyright 2015 (c) Hy Truong Son. All rights reserved.
// Only use for academic purposes.

import java.util.EventObject;
import java.io.*;
import javax.swing.JOptionPane;

public class Training extends MainProgram{
	
	public static void TrainingProcess() throws IOException {
		BufferedInputStream image = new BufferedInputStream(new FileInputStream(Training_Image_Directory));
		BufferedInputStream label = new BufferedInputStream(new FileInputStream(Training_Label_Directory));
		
		int aByte;
		
		for (int i = 0; i < 16; i++)
			aByte = image.read();
			
		for (int i = 0; i < 8; i++)
			aByte = label.read();
		
		TrainingSample = new aSample [nTraining];
		
		for (int i = 0; i < nTraining; i++){
			TrainingSample[i] = new aSample();
			TrainingSample[i].image = new int [width][height];
			
			input(image, TrainingSample[i].image);
			TrainingSample[i].label = label.read();
			
			Sample.setText("Sample: " + Integer.toString(i + 1));
			Label.setText("Label: " + Integer.toString(TrainingSample[i].label));
			ProgressBar.setValue((i + 1) * 100 / nTraining);
		}
		
		image.close();
		label.close();
	}
	
	public Training() throws IOException {
		nTraining = Integer.parseInt(TrainingText.getText());
		
		if ((nTraining <= 0) || (nTraining > Default_nTraining)){
			JOptionPane.showMessageDialog(MainFrame, "Number of training samples is not valid!", "Notice", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		TrainingButton.setEnabled(false);
		TestingButton.setEnabled(false);
		ViewBox.setEnabled(false);
		TrainingText.setEditable(false);
		Sample.setVisible(true);
		Label.setVisible(true);
		
		TrainingProcess();
		
		StopButton.setEnabled(true);
		while (true){
			EventObject AnEvent = Events.waitEvent();
			String name = Events.getName(AnEvent);
			
			if (name.equals("Stop")) break;
		}
		StopButton.setEnabled(false);
		
		ProgressBar.setValue(0);
		
		TrainingButton.setEnabled(true);
		TestingButton.setEnabled(true);
		ViewBox.setEnabled(true);
		TrainingText.setEditable(true);
		Sample.setVisible(false);
		Label.setVisible(false);
		ImageLabel.setVisible(false);
	}
	
}
