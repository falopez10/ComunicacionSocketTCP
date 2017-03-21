package Interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToggleButton;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JTextField;
import javax.print.DocFlavor.STRING;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.awt.event.ActionEvent;
import javax.swing.JEditorPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JList;

public class TCPCliente extends JFrame implements ActionListener{

	 private static final String AGREGAR_HOST = "Agregar Host";
	 private static final String AGREGAR_PUERTO = "Agregar Puerto";
	 private static final String AGREGAR_TAMANIO = "Agregar Tamanio";
	 private static final String DETENER_COMUNICACION = "Detener";
	 private static final String INICIAR_COMUNICACION = "Iniciar Comunicación";
	 private static final String INICIAR_DESCARGA = "Iniciar descarga";
	 private static final String ARCHIVO_5MG = "direccion1";
	 private static final String ARCHIVO_20MG = "direccion2";
	 private static final String ARCHIVO_50MG = "direccion3";
	 
	private JPanel contentPane;
	private JTextField textFieldHost;
	private JTextField textFieldPuerto;
	private JButton btnAgregarHost;
	private JButton btnAgregarPuerto;
	private JButton btnIniciarcomunicacin;
	private JButton btnDetenerComunicacin;
	private JButton btnDescarga;
	private JButton btnDetnerDescarga;
	private JTextArea txtrArea;
	private JComboBox comboBox;
	
	private String host;
	private int puerto;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TCPCliente frame = new TCPCliente();
					frame.setVisible(true);
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
			
			
			
			BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			mostrarMensaje(inFromServer.readLine());
			
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
        txtrArea.append(mensaje + "\n");
    }
	
	
	/**
	 * Create the frame.
	 */
	public TCPCliente() {
		setTitle("ClienteTCP");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 645, 468);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBienvenidoAlCliente = DefaultComponentFactory.getInstance().createTitle("Bienvenido al Cliente de Comunicaci\u00F3n TCP");
		lblBienvenidoAlCliente.setForeground(new Color(0, 102, 255));
		lblBienvenidoAlCliente.setBounds(179, 11, 298, 25);
		contentPane.add(lblBienvenidoAlCliente);
		
		textFieldHost = new JTextField();
		textFieldHost.setBounds(165, 47, 86, 20);
		contentPane.add(textFieldHost);
		textFieldHost.setColumns(10);
		
		textFieldPuerto = new JTextField();
		textFieldPuerto.setBounds(165, 78, 86, 20);
		contentPane.add(textFieldPuerto);
		textFieldPuerto.setColumns(10);
		
		btnAgregarHost = new JButton("Agregar Host");
		btnAgregarHost.addActionListener(this);
		btnAgregarHost.setBounds(10, 46, 137, 23);
		contentPane.add(btnAgregarHost);
		
		btnAgregarPuerto = new JButton("Agregar Puerto");
		btnAgregarPuerto.setBounds(10, 77, 137, 23);
		btnAgregarPuerto.addActionListener(this);
		contentPane.add(btnAgregarPuerto);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 214, 312, 2);
		contentPane.add(separator);
		
		btnIniciarcomunicacin = new JButton("Iniciar Comunicaci\u00F3n");
		btnIniciarcomunicacin.addActionListener(this);
		btnIniciarcomunicacin.setBounds(10, 109, 167, 23);
		contentPane.add(btnIniciarcomunicacin);
		
		btnDetenerComunicacin = new JButton("Detener comunicaci\u00F3n");
		btnDetenerComunicacin.setBounds(10, 143, 167, 23);
		btnDetenerComunicacin.addActionListener(this);
		contentPane.add(btnDetenerComunicacin);
		
		btnDescarga = new JButton("Descarga");
		btnDescarga.setBounds(185, 109, 137, 23);
		btnDescarga.addActionListener(this);
		contentPane.add(btnDescarga);
		
		btnDetnerDescarga = new JButton("Detener descarga");
		btnDetnerDescarga.addActionListener(this);
		btnDetnerDescarga.setBounds(185, 143, 137, 23);
		contentPane.add(btnDetnerDescarga);
		
		JLabel lblEstadoComunicacin = new JLabel("Estado comunicaci\u00F3n");
		lblEstadoComunicacin.setBounds(26, 214, 151, 31);
		contentPane.add(lblEstadoComunicacin);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(302, 47, 0, 137);
		contentPane.add(separator_1);
		
		txtrArea = new JTextArea();
		txtrArea.setText("Esperando datos...");
		txtrArea.setBounds(26, 244, 355, 174);
		contentPane.add(txtrArea);
		
		JLabel lblArchivosDescargados = new JLabel("Archivos descargados");
		lblArchivosDescargados.setBounds(412, 47, 182, 25);
		contentPane.add(lblArchivosDescargados);
		
		comboBox = new JComboBox();
		comboBox.setBounds(165, 183, 127, 20);
		comboBox.addItem(ARCHIVO_5MG);
		comboBox.addItem(ARCHIVO_20MG);
		comboBox.addItem(ARCHIVO_50MG);
		comboBox.addActionListener(this);
		contentPane.add(comboBox);
		
		JLabel lblSeleccioneArchivo = new JLabel("Seleccione archivo");
		lblSeleccioneArchivo.setBounds(20, 177, 127, 26);
		contentPane.add(lblSeleccioneArchivo);
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
        
        if( comando.equals( INICIAR_COMUNICACION ))
        {
        	comunicacion();
        	 mostrarMensaje("Se empieza la comunicacción con el servidor...");
        }
        if( comando.equals( ARCHIVO_5MG ))
        {
        	
        }
        if( comando.equals( ARCHIVO_20MG ))
        {
        	
        }
        if( comando.equals( ARCHIVO_50MG ))
        {
        	
        }
		
	}
}
