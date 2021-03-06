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
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JEditorPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JList;
import javax.swing.DefaultComboBoxModel;

public class TCPCliente extends JFrame implements ActionListener{

	private static final String AGREGAR_HOST = "Agregar Host";
	private static final String AGREGAR_PUERTO = "Agregar Puerto";
	private static final String AGREGAR_TAMANIO = "Agregar Tamanio";
	private static final String DETENER_COMUNICACION = "Detener";
	private static final String INICIAR_COMUNICACION = "Iniciar Comunicación";
	private static final String INICIAR_DESCARGA = "Iniciar descarga";
	private static final String ARCHIVO_5MB = "ARCHIVO_5MB";
	private static final String ARCHIVO_20MB = "ARCHIVO_20MB";
	private static final String ARCHIVO_50MB = "ARCHIVO_50MB";
	private static final String DESCARGA = "Descarga";


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

	private String archivoSelecionado;

	private String host;
	private int puerto;
	private Socket clientSocket;

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

	public void establecerArchivoSeleccioando(String pA)
	{
		this.archivoSelecionado = pA;
	}

	public void comunicacion()
	{
		try{
			mostrarMensaje("Se reciben los datos para iniciar comunicación....");
			mostrarMensaje("Esperando...");


			clientSocket = new Socket(host, puerto);


			DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

			outToServer.writeUTF(archivoSelecionado);
			System.out.println(archivoSelecionado+" DEL CLIENTE");
			//outToServer.writeUTF("ana");



			DataInputStream flujo = new DataInputStream(clientSocket.getInputStream());
			Byte byteActual = flujo.readByte();
			ArrayList<Byte> inputBytes = new ArrayList<Byte>();
			int tamanioArchivo = 0;
			switch (archivoSelecionado) {
			case ARCHIVO_5MB:
				tamanioArchivo = 5783553;
				break;
			case ARCHIVO_20MB:
				tamanioArchivo = 16970753;
				break;
			case ARCHIVO_50MB:
				tamanioArchivo = 46807040;
				break;

			default:
				break;
			}
			int contador = 0;
			int tamanioPaquete = 1024;//tamaño de cada paquete a mostrar en bytes
			
			while (contador < tamanioArchivo)
			{
				inputBytes.add(byteActual);
				byteActual = flujo.readByte();
				if(contador%tamanioPaquete == 0)
					System.out.println("Paquete recibido: " + byteActual + " numero: " + contador);
				contador++;
			}
//			System.out.println("TCP sale del while de bytes");
			byte[] bytesArchivo = new byte[inputBytes.size()];
			for(int i = 0; i < inputBytes.size(); i++)
				bytesArchivo[i] = inputBytes.get(i).byteValue();
			
			File archivo = new File("./recibido/"+archivoSelecionado+".mp4");
			FileOutputStream fos = new FileOutputStream("./recibido/"+archivoSelecionado+".mp4");
			fos.write(bytesArchivo);
			fos.close();
			System.out.println("Archivo es "+bytesArchivo);
			mostrarMensaje("Se ha recibido el archivo: "+bytesArchivo);
			
			

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

		JLabel lblEstadoComunicacin = new JLabel("Estado comunicaci\u00F3n"+"\n");
		lblEstadoComunicacin.setBounds(26, 214, 151, 31);
		contentPane.add(lblEstadoComunicacin);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(302, 47, 0, 137);
		contentPane.add(separator_1);

		txtrArea = new JTextArea();
		txtrArea.setText("Esperando datos..."+"\n");
		txtrArea.setBounds(26, 244, 355, 174);
		contentPane.add(txtrArea);

		JLabel lblArchivosDescargados = new JLabel("Archivos descargados");
		lblArchivosDescargados.setBounds(412, 47, 182, 25);
		contentPane.add(lblArchivosDescargados);

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"ARCHIVO_5MB", "ARCHIVO_20MB", "ARCHIVO_50MB"}));
		comboBox.setBounds(165, 183, 127, 20);
		//comboBox.addItem(ARCHIVO_5MB);
		//comboBox.addItem(ARCHIVO_20MB);
		//comboBox.addItem(ARCHIVO_50MB);
		//comboBox.addActionListener(this);
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
			// mostrarMensaje("Se empieza la comunicación con el servidor...");
		}
		if( comando.equals( DESCARGA ))
		{
			establecerArchivoSeleccioando(comboBox.getSelectedItem().toString());
			mostrarMensaje("Se descargará el archivo: " + archivoSelecionado);
		}
		if( comando.equals( DETENER_COMUNICACION ))
		{
			try {
				clientSocket.close();
				mostrarMensaje("Se ha detenido la comunicación");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}
}
