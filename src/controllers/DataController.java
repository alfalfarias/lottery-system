package controllers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import models.Data;

public class DataController {
	String format = ".data";
	public void exportar(String path, Data data) throws IOException{
		FileOutputStream fileOut = new FileOutputStream(path+format);
		   ObjectOutputStream out = new ObjectOutputStream(fileOut);
		   out.writeObject(data);
		   out.close();
		   fileOut.close();
	}
	public Data importar(String path) throws IOException, ClassNotFoundException{
		   FileInputStream fileIn = new FileInputStream(path);
		   ObjectInputStream in = new ObjectInputStream(fileIn);
		   Data data = (Data) in.readObject();
		   in.close();
		   fileIn.close();
		   return data;
	}
}
