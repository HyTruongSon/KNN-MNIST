// Software: K-Nearest Neighbor with MNIST database (Graphical User Interface in Java)
// Author: Hy Truong Son
// Major: BSc. Computer Science
// Class: 2013 - 2016
// Institution: Faculty of Informatics, Eotvos Lorand University, Budapest, Hungary
// Email: sonpascal93@gmail.com
// Website: http://people.inf.elte.hu/hytruongson/
// Copyright 2015 (c) Hy Truong Son. All rights reserved.
// Only use for academic purposes.

import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.util.EventObject;

public class MainProgram extends constants{

	static int width = 28;
	static int height = 28;
	
	static class aSample {
		public int image[][];
		public int label;
	}
	
	static aSample TrainingSample[];
	static aSample TestingSample[];
	
	public static void input(BufferedInputStream image, int Matrix[][]) throws IOException{
		int u, v;
		for (v = 0; v < height; v++)
			for (u = 0; u < width; u++)
				Matrix[u][v] = image.read();
	}
	
	public static int Manhattan(int Matrix1[][], int Matrix2[][]){
		int sum = 0;
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++)
				sum += Math.abs(Matrix1[i][j] - Matrix2[i][j]);
		return sum;
	}
	
	public static void drawSample(int Matrix[][], int x0, int y0, int CellLength){
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++){
				MainCanvas.setColor(new Color(Matrix[i][j], Matrix[i][j], Matrix[i][j]));
				MainCanvas.drawRect(x0 + i * CellLength, y0 + j * CellLength, CellLength, CellLength);
			}
	}
	
	public static void undrawSample(int x0, int y0, int CellLength){
		MainCanvas.setColor(MainPanel.getBackground());
		MainCanvas.fillRect(x0, y0, width * CellLength + 1, height * CellLength + 1);
	}
	
	public static void Process() throws IOException {
		while (true){
			EventObject AnEvent = Events.waitEvent();
			String name = Events.getName(AnEvent);
			
			if (name.equals("Training")) new Training();
			
			if (name.equals("Testing")) new Testing();
			
			if (name.equals("About"))
				JOptionPane.showMessageDialog(MainFrame, 
						"Program: KNN algorithm with MNIST database\n" +
						"Author: Hy Truong Son\n" +
						"Email: sonpascal93@gmail.com\n" +
						"Website: http://people.inf.elte.hu/hytruongson\n" +
						"Copyright ©2013-2016, Hy Truong Son. All rights reserved.");
		}
	}
	
	public static void main(String args[]) throws IOException {
		MyInterface.About();
		MyInterface.InitGUI();
		MyInterface.InitEventListener();
		Process();
	}
	
}
