package Comunicacion;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import Interfaz.TCPCliente;


public class Servidor 
{
	public static void main(String argv[]) throws Exception
	{	
		//TCPCliente cliente;
		String clientSentence;
		String capitalizedSentence;
		ServerSocket welcomeSocket = new ServerSocket(6789);
		while(true) 
		{
			Socket conn = welcomeSocket.accept();
			DataInputStream flujo = new DataInputStream(conn.getInputStream());
			DataOutputStream salida = new DataOutputStream(conn.getOutputStream());
			String r = flujo.readUTF();
			salida.writeUTF(r.toUpperCase());
			//cliente.mostrarMensaje(r.toUpperCase());
			System.out.println(r.toUpperCase());
			//Socket connectionSocket = welcomeSocket.accept();
			//System.out.println("Llega aca");
			//BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
			//System.out.println("Llega aca");
			//DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
			//System.out.println("Llega aca");
			//clientSentence = "ana";
			//System.out.println("Llega aca");
			//capitalizedSentence = clientSentence.toUpperCase() + '\n';
			//outToClient.writeBytes(capitalizedSentence);
			conn.close();
	
		}
		
	}
}
