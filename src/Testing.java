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
import java.text.DecimalFormat;

public class Testing extends MainProgram{
	
	static int INF = 1000000000;
	static int BigCellLength = 4;
	static int SmallCellLength = 2;
	static double rate;
	
	static int NeighborsIndex[];
	static int NeighborsDistance[];
	static int CountingLabel[] = new int [10];
	
	static boolean viewed;
	
	public static void GetData() throws IOException {
		BufferedInputStream image = new BufferedInputStream(new FileInputStream(Testing_Image_Directory));
		BufferedInputStream label = new BufferedInputStream(new FileInputStream(Testing_Label_Directory));
		
		int aByte;
		
		for (int i = 0; i < 16; i++)
			aByte = image.read();
			
		for (int i = 0; i < 8; i++)
			aByte = label.read();
		
		TestingSample = new aSample [nTesting];
		
		for (int i = 0; i < nTesting; i++){
			TestingSample[i] = new aSample();
			TestingSample[i].image = new int [width][height];
			
			input(image, TestingSample[i].image);
			TestingSample[i].label = label.read();
		}
		
		image.close();
		label.close();
	}
	
	public static int search(int Matrix[][]){
		for (int i = 0; i < K; i++)
			NeighborsDistance[i] = INF;
		
		for (int i = 0; i < nTraining; i++){
			int dist = Manhattan(Matrix, TrainingSample[i].image);
			
			for (int j = 0; j < K; j++)
				if (dist < NeighborsDistance[j]){
					for (int v = K - 1; v > j; v--){
						NeighborsDistance[v] = NeighborsDistance[v - 1];
						NeighborsIndex[v] = NeighborsIndex[v - 1];
					}
					NeighborsDistance[j] = dist;
					NeighborsIndex[j] = i;
					break;
				}
		}
		
		for (int i = 0; i < 10; i++) CountingLabel[i] = 0;
		for (int i = 0; i < K; i++){
			int j = TrainingSample[NeighborsIndex[i]].label;
			CountingLabel[j]++;
		}
		
		int res = 0;
		for (int i = 1; i < 10; i++)
			if (CountingLabel[i] > CountingLabel[res])
				res = i;
		
		return res;
	}
	
	public static double roundTwoDecimals(double d){
	    DecimalFormat twoDForm = new DecimalFormat("#.##");
	    return Double.valueOf(twoDForm.format(d));
	}
	
	public static void showResult(int index){
		int res = search(TestingSample[index].image);
		int ans = TestingSample[index].label;
		
		if (res == ans){
			rate += 100.0 / nTesting;
			Rate.setText("Recognition rate: " + Double.toString(roundTwoDecimals(rate)) + "%");
		}
		
		Sample.setText("Sample: " + Integer.toString(index + 1));
		Label.setText("Label: " + ans);
		Predict.setText("Predict: " + res);
		
		if (viewed){
			MainCanvas.setColor(MainPanel.getBackground());
			MainCanvas.fillRect(0, 7 * marginButton, widthMainCanvas, heightMainCanvas - 7 * marginButton);
			
			drawSample(TestingSample[index].image, marginButton + Sample.getWidth(), 4 * marginButton, BigCellLength);
			
			int x0 = marginButton;
			int y0 = 7 * marginButton;
			int Length = SmallCellLength * width;
			
			for (int i = 0; i < K; i++){
				if (x0 + Length + smallMargin <= widthMainCanvas - marginButton){
					if (i > 0)
						x0 += smallMargin;
				}else
					if (y0 + 2 * Length + smallMargin < heightMainCanvas){
						x0 = marginButton;
						y0 += Length + smallMargin;
					}else break;
				
				int j = NeighborsIndex[i];
				drawSample(TrainingSample[j].image, x0, y0, SmallCellLength);
				x0 += Length;
			}
		}
	}
	
	public static void TestingProcess(){
		if (!viewed){
			for (int i = 0; i < nTesting; i++){
				showResult(i);
				ProgressBar.setValue((i + 1) * 100 / nTesting);
			}
			
			StopButton.setEnabled(true);
			while (true){
				EventObject AnEvent = Events.waitEvent();
				String name = Events.getName(AnEvent);
				
				if (name.equals("Stop")){
					break;
				}
			}
			StopButton.setEnabled(false);
			
			ProgressBar.setValue(0);
			return;
		}
		
		if (nTesting == 1) NextButton.setEnabled(false); else
			NextButton.setEnabled(true);
		StopButton.setEnabled(true);
		ImageLabel.setVisible(true);
		Nearest.setVisible(true);
		
		int i = 0;
		showResult(i);
		ProgressBar.setValue(100 / nTesting);
		
		while (true){
			EventObject AnEvent = Events.waitEvent();
			String name = Events.getName(AnEvent);
			
			if ((name.equals("Next")) && (i < nTesting - 1)){
				i++;
				showResult(i);
				ProgressBar.setValue(100 * (i + 1) / nTesting);
				if (i == nTesting - 1)
					NextButton.setEnabled(false);
			}
			
			if (name.equals("Stop")) break;
		}
		
		NextButton.setEnabled(false);
		StopButton.setEnabled(false);
		ProgressBar.setValue(0);
		undrawSample(marginButton + Sample.getWidth(), 4 * marginButton, 4);
		MainCanvas.setColor(MainPanel.getBackground());
		MainCanvas.fillRect(0, 6 * marginButton, widthMainCanvas, heightMainCanvas - 6 * marginButton);
	}
	
	public Testing() throws IOException {
		if (nTraining == 0){
			JOptionPane.showMessageDialog(MainFrame, "There is no training data!", "Notice", JOptionPane.ERROR_MESSAGE);
			return;
		}
			
		nTesting = Integer.parseInt(TestingText.getText());
		
		if ((nTesting <= 0) || (nTesting > Default_nTesting)){
			JOptionPane.showMessageDialog(MainFrame, "Number of testing samples is not valid!", "Notice", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		K = Integer.parseInt(NeighborText.getText());
		if ((K <= 0) || (K > nTraining)){
			JOptionPane.showMessageDialog(MainFrame, "Number of neighbors is not valid!", "Notice", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		NeighborsIndex = new int [K];
		NeighborsDistance = new int [K];
		
		TrainingButton.setEnabled(false);
		TestingButton.setEnabled(false);
		ViewBox.setEnabled(false);
		TestingText.setEditable(false);
		NeighborText.setEditable(false);
		Rate.setVisible(true);
		Sample.setVisible(true);
		Label.setVisible(true);
		Predict.setVisible(true);
		
		rate = 0.0;
		Rate.setText("Recognition rate: 0%");
		viewed = (ViewBox.getSelectedIndex() == 0);
		
		GetData();
		TestingProcess();
		
		TrainingButton.setEnabled(true);
		TestingButton.setEnabled(true);
		ViewBox.setEnabled(true);
		TestingText.setEditable(true);
		NeighborText.setEditable(true);
		Rate.setVisible(false);
		Sample.setVisible(false);
		Label.setVisible(false);
		Predict.setVisible(false);
		ImageLabel.setVisible(false);
		Nearest.setVisible(false);
	}
	
}
