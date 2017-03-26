package Comunicacion;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

import Interfaz.TCPCliente;


public class Servidor 
{
	public static void main(String argv[]) throws Exception
	{	

		//Creamos el socket que escucha en el puerto 6789
		ServerSocket welcomeSocket = new ServerSocket(6789);

		//Establecemos el tamaño del buffer
		welcomeSocket.setReceiveBufferSize(12);

		//Archivo
		File archivo;

		//Establecemos el timeout del socket
		welcomeSocket.setSoTimeout(30000);


		//Rutas de los archivos a descargar
		String ARCHIVO_5MB = "src/Comunicacion/GK Video 5MB.mp4";
		String ARCHIVO_20MB = "src/Comunicacion/6 min video  20 mb.mp4";
		String ARCHIVO_50MB = "src/Comunicacion/HOW TO MAKE EMOJI COOKIES - NERDY NUMMIES.mp4";
		while(true) 
		{
			try {

				Socket conn = welcomeSocket.accept();

				DataInputStream flujo = new DataInputStream(conn.getInputStream());

				String r = flujo.readUTF();

				System.out.println(r);
				if(r.equals("ARCHIVO_5MB"))
				{
					archivo = new File(ARCHIVO_5MB);
				}
				else if(r.equals("ARCHIVO_20MB"))
				{
					archivo = new File(ARCHIVO_20MB);
				}
				else
				{
					archivo = new File(ARCHIVO_50MB);
				}
				//				System.out.println("PASA ACA");
				int tamañoArchivo = ( int )archivo.length();
				FileInputStream ficheroStream = new FileInputStream(archivo); 
				byte[] buffer = new byte[ tamañoArchivo ];
				ficheroStream.read(buffer);


				DataOutputStream salida = new DataOutputStream(conn.getOutputStream());
				System.out.println("tamaño del buffer: " + buffer.length);
				int tamanioPaquete = 1024;//tamaño de cada paquete a mostrar en bytes
				for( int i = 0; i < buffer.length; i++ )
				{
					salida.writeByte(buffer[i]);
					if(i%tamanioPaquete == 0)
						System.out.println("Paquete: " + buffer[i] + " numero: " + i);
				} 
				System.out.println("Se finalizó envio de archivo");
//				salida.writeUTF("Se envio el archivo...");
				conn.close();

			} catch (Exception e) {
				System.out.println(e.getMessage());
				System.out.println("Socket timed out!");
				break;
			}		     
		}

	}
}
