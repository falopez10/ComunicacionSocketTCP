package Comunicacion;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Cliente 
{
	private String host;
	
	private Integer puerto;
	
	
	public static void main(String argv[]) throws Exception
	{
		String sentence;
		String modifiedSentence;
		String host = "localhost";
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		Socket clientSocket = new Socket(host, 6789);
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		sentence = inFromUser.readLine();
		outToServer.writeBytes(sentence + '\n');
		modifiedSentence = inFromServer.readLine();
		System.out.println("FROM SERVER: " + modifiedSentence);
		clientSocket.close();
	}
	
	public void setHost(String host) {
		this.host = host;
	}
	
	public void setPuerto(Integer puerto) {
		this.puerto = puerto;
	}
	
	
}
