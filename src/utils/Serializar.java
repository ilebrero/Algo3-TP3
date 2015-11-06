package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Serializar {


	public Serializar(GrafoMaterias grafo,String directorio){
		try
	      {
	         FileOutputStream fileOut =
	         new FileOutputStream(directorio);
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(grafo);
	         out.close();
	         fileOut.close();
	         System.out.printf("Serialized data is saved in /tmp/employee.ser");
	      }catch(IOException i)
	      {
	          i.printStackTrace();
	      }
	   }
	


	public void Deserializar(GrafoMaterias grafo , String directorio){
		try
	      {
	         FileInputStream fileIn = new FileInputStream("/tmp/employee.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         grafo = (GrafoMaterias) in.readObject();
	         in.close();
	         fileIn.close();
	      }catch(IOException i)
	      {
	         i.printStackTrace();
	         return;
	      }catch(ClassNotFoundException c)
	      {
	         System.out.println("Employee class not found");
	         c.printStackTrace();
	         return;
	      }
	}
}
