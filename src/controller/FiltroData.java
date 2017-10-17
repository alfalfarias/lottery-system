package controller;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class FiltroData extends FileFilter{
	public boolean accept (File fichero){
		if (tieneExtensionJPG (fichero))
			return true;
		else
			return false;
	}
	private boolean tieneExtensionJPG(File fichero) {
		// TODO Auto-generated method stub
		return false;
	}
	public String getDescription()
	{
		return ("Filtro DATA");
	}
}
