package Interfaz;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JSeparator;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.JComboBox;
import javax.swing.JTable;

public class ClienteTCP implements ActionListener{

	 private static final String AGREGAR_HOST = "AgregarHost";
	 private static final String AGREGAR_PUERTO = "AgregarPuerto";
	 private static final String ENVIAR = "Enviar";
	 private static final String DETENER = "Detener";
	 private static final String INICIAR = "Iniciar";
	
	private JFrame frmClientetcp;
	private JTextField textFieldHost;
	private JTextField textFieldPuerto;
	private JTextField textFieldMensaje;
	private JTextArea textArea;
	private String host;
	private int puerto;
	private JButton btnAgregarHost;
	private JButton btnAgregarPuerto;
	private JButton btnDescargar;
	private int tamanioArchivo;
	private JButton btnIniciar;
	private JButton btnDetener;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClienteTCP window = new ClienteTCP();
					window.frmClientetcp.setVisible(true);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void comunicacion()
	{
		try{
			mostrarMensaje("Se reciben los datos para iniciar comunicación....");
			mostrarMensaje("Esperando...");
			
			//BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
			//BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
			Socket clientSocket = new Socket(host, puerto);
			
			
			DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
			
			outToServer.writeUTF("ana");
			
			//BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
			//sentence = inFromUser.readLine();
			//outToServer.writeBytes("ana");
			
			//mostrarMensaje(textFieldMensaje.getText());
			//mostrarMensaje(inFromServer.readLine());
			//modifiedSentence = inFromServer.readLine();
			//System.out.println("FROM SERVER: " + modifiedSentence);
			clientSocket.close();
			
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	//Para mostrar texto en displayArea
    public void mostrarMensaje(String mensaje) {
        textArea.append(mensaje + "\n");
    }
	
	/**
	 * Create the application.
	 */
	public ClienteTCP() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmClientetcp = new JFrame();
		frmClientetcp.setTitle("ClienteTCP");
		frmClientetcp.setBounds(100, 100, 500, 300);
		frmClientetcp.isResizable();
		frmClientetcp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmClientetcp.getContentPane().setLayout(null);
		
		JLabel lblBienvenido = new JLabel("Bienvenido al Cliente de comunicaci\u00F3n TCP");
		lblBienvenido.setForeground(new Color(0, 51, 102));
		lblBienvenido.setBounds(132, 0, 226, 26);
		frmClientetcp.getContentPane().add(lblBienvenido);
		
		textFieldHost = new JTextField();
		textFieldHost.setBounds(10, 28, 55, 20);
		frmClientetcp.getContentPane().add(textFieldHost);
		textFieldHost.setColumns(10);
		
		textFieldPuerto = new JTextField();
		textFieldPuerto.setBounds(185, 28, 55, 20);
		frmClientetcp.getContentPane().add(textFieldPuerto);
		textFieldPuerto.setColumns(10);
		
		btnAgregarHost = new JButton("AgregarHost");
		btnAgregarHost.setBackground(new Color(135, 206, 250));
		btnAgregarHost.setForeground(new Color(0, 0, 0));
		btnAgregarHost.setBounds(75, 27, 100, 23);
		frmClientetcp.getContentPane().add(btnAgregarHost);
		btnAgregarHost.addActionListener( this );
		btnAgregarHost.addActionListener( this );
		
		btnAgregarPuerto = new JButton("AgregarPuerto");
		btnAgregarPuerto.setBackground(new Color(135, 206, 250));
		btnAgregarPuerto.setBounds(250, 27, 108, 23);
		frmClientetcp.getContentPane().add(btnAgregarPuerto);
		btnAgregarPuerto.addActionListener( this );
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 90, 414, 2);
		frmClientetcp.getContentPane().add(separator);
		
		textArea = new JTextArea();
		textArea.setBounds(8, 92, 416, 95);
		frmClientetcp.getContentPane().add(textArea);
		
		
		textFieldMensaje = new JTextField();
		textFieldMensaje.setBounds(10, 62, 231, 20);
		frmClientetcp.getContentPane().add(textFieldMensaje);
		textFieldMensaje.setColumns(10);
		
		btnDescargar = new JButton("Enviar");
		btnDescargar.setBounds(260, 61, 89, 23);
		frmClientetcp.getContentPane().add(btnDescargar);
		btnDescargar.addActionListener( this );
		
		btnIniciar = new JButton("Iniciar");
		btnIniciar.setBounds(252, 216, 71, 34);
		frmClientetcp.getContentPane().add(btnIniciar);
		btnIniciar.addActionListener( this );
		
		btnDetener = new JButton("Detener");
		btnDetener.setBounds(339, 216, 77, 34);
		btnDetener.addActionListener( this );
		frmClientetcp.getContentPane().add(btnDetener);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		 String comando = e.getActionCommand( );
	        if( comando.equals( AGREGAR_HOST ) )
	        {
	            host = textFieldHost.getText();
	            mostrarMensaje("Se agrego el host..."+ host );
	        }
	        if( comando.equals( AGREGAR_PUERTO ) )
	        {
	            puerto = Integer.parseInt( textFieldPuerto.getText() );
	            mostrarMensaje("Se agrego el puerto..."+ puerto );
	        }
	        if( comando.equals( ENVIAR ))
	        {
	        	
	        }
	        if( comando.equals( INICIAR ))
	        {
	        	comunicacion();
	        	 mostrarMensaje("Se empieza la comunicacción con el servidor...");
	        }
		
	}
}
