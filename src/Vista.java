import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import com.ibm.icu.util.StringTokenizer;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.HeadlessException;

public class Vista extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JButton btnMostrar;
	private JTextArea textArea;
	public static String direccion;
	public static String direccion2;
	public static String nombreFichero;
	private JTextField txtNombreFichero;
	public static String rutaRelativa;
	public static String rutaDirectorio;
	public boolean esFichero;
	public static boolean modoSobrescribir=false;
	public static boolean modoNuevo=false;
	public String contenido;
	private JScrollPane scrollpane1;
	private JTextField txtAntiguaPalabra;
	private JTextField txtNuevaPalabra;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vista frame = new Vista();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);//centrar la ventana al centro de la pantalla
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Vista() {
		
		JFrame jFrame = new JFrame();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 743, 702);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtNombreFichero = new JTextField();
		txtNombreFichero.setBounds(10, 330, 197, 20);
		contentPane.add(txtNombreFichero);
		txtNombreFichero.setColumns(10);
		
		txtAntiguaPalabra = new JTextField();
		txtAntiguaPalabra.setBounds(519, 303, 86, 20);
		contentPane.add(txtAntiguaPalabra);
		txtAntiguaPalabra.setColumns(10);
		
		txtNuevaPalabra = new JTextField();
		txtNuevaPalabra.setColumns(10);
		txtNuevaPalabra.setBounds(519, 330, 86, 20);
		contentPane.add(txtNuevaPalabra);
		
		textField = new JTextField();
		textField.setBounds(10, 21, 595, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 361, 707, 259);
		contentPane.add(panel);
		panel.setLayout(null);
		panel.setSize(0,0);
		
		JTextArea txtrInformacionFichero = new JTextArea();
		txtrInformacionFichero.setBounds(10, 393, 704, 228);
		contentPane.add(txtrInformacionFichero);
		
		JScrollPane scrollPane = new JScrollPane(txtrInformacionFichero,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 361, 707, 260);
		contentPane.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(10, 86, 704, 135);
		contentPane.add(textArea);
		
		JLabel lblNewLabel_1 = new JLabel("FICHERO");
		lblNewLabel_1.setBounds(10, 232, 54, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("________________________________________________________________________________________________");
		lblNewLabel_1_1.setBounds(13, 278, 714, 14);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_2 = new JLabel("Tipo Guardado:");
		lblNewLabel_2.setBounds(376, 636, 89, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Antigua Palabra");
		lblNewLabel_3.setBounds(417, 306, 104, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("Nueva Palabra");
		lblNewLabel_3_1.setBounds(432, 333, 89, 14);
		contentPane.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel = new JLabel("(Debes seleccionar un fichero válido.)");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblNewLabel.setBounds(65, 232, 177, 14);
		contentPane.add(lblNewLabel);

		JButton btnExaminar1 = new JButton("Examinar");//Abre pestaña para examinar que archivo queremos escoger
		btnExaminar1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser fc=new JFileChooser();//Creamos el objeto JFileChooser
				fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);//Indicamos lo que podemos seleccionar
				FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.TXT", "*.*");//Creamos el filtro
				fc.setFileFilter(filtro);//Le indicamos el filtro
				int seleccion=fc.showOpenDialog(contentPane);//Abrimos la ventana, guardamos la opcion seleccionada por el usuario
				 
				//Si el usuario, pincha en aceptar
				if(seleccion==JFileChooser.APPROVE_OPTION){
					File fichero=fc.getSelectedFile();//Seleccionamos el fichero
				    textField.setText(fichero.getAbsolutePath());//Ecribe la ruta del fichero seleccionado en el campo de texto
				  
				    direccion=fichero.getAbsolutePath();
				    nombreFichero=fichero.getName();
				    rutaRelativa=fichero.getPath();
				    
				    //Las siguientes lineas son para obtener la ubicacion del directorio del fichero seleccionado
				    File file = new File(direccion); 
				    file = new File(file.getAbsolutePath()); 
				    String dir = file.getParent();
				    File dirAsFile = file.getParentFile(); 
				    rutaDirectorio=dirAsFile.toString();
				    
				    esFichero=fichero.isFile();//comprobar si es un fichero
				    txtNombreFichero.setText(nombreFichero);//mostrar el nombre del fichero en el txtNombre
				}
			}
		}
		);
		btnExaminar1.setBounds(615, 20, 99, 23);
		contentPane.add(btnExaminar1);

		JButton btnCrearNuevo = new JButton("Crear Nuevo");
		btnCrearNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File fichero1 = new File (txtNombreFichero.getText());
				if (direccion==null || fichero1.exists()==false) {//si no hay direccion seleccionada o el fichero no esxiste creamos el fichero nuevo
					try {
						if (fichero1.createNewFile()) {
							  PrintWriter writer = new PrintWriter(fichero1.getAbsolutePath(), "UTF-8");
						      writer.println(txtrInformacionFichero.getText());//escribir la informacion en el fichero 
						      writer.close();
						      
						      JOptionPane.showMessageDialog(jFrame, "Fichero creado correctamente en el directorio de la aplicación.");
						}
					} catch (HeadlessException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//inicializar las variables a 0 cuando ya se ha creado el fichero
					esFichero=false;
			        nombreFichero="";
					txtNombreFichero.setText("");
					textField.setText("");
					textArea.setText("");
					txtrInformacionFichero.setText("");
					
			}else {//si el fichero ya existe
				String nombreCambiado;
				StringTokenizer st = new StringTokenizer(nombreFichero,".");
				nombreCambiado=st.nextToken()+"_nuevo."+st.nextToken();
				
				File fichero2 = new File (rutaDirectorio,nombreCambiado);

					try {
						if (fichero2.createNewFile()) {
							PrintWriter writer = new PrintWriter(fichero2.getAbsolutePath(), "UTF-8");
						    writer.println(txtrInformacionFichero.getText());
						    writer.close();
						    
						    JOptionPane.showMessageDialog(jFrame, "Fichero creado correctamente."); 
						  }
						else {
						System.out.println("No se ha podido crear el fichero. nuevo");
						  }
					} catch (HeadlessException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//inicializar las variables a 0 cuando ya se ha creado el fichero o no
					esFichero=false;
		            nombreFichero="";
					txtNombreFichero.setText("");
					textField.setText("");
					textArea.setText("");
					txtrInformacionFichero.setText("");
				}
			}	
		});
		btnCrearNuevo.setBounds(599, 632, 115, 23);
		contentPane.add(btnCrearNuevo);
		
		JButton btnRenombrar = new JButton("Renombrar");
		btnRenombrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (esFichero) {//si el archivo seleccionado es un fichero
					VentanaRenombrar vr = new VentanaRenombrar();
					vr.setVisible(true);//abro la ventana renombrar para el proceso de renombrar el archivo
				}else {
					JOptionPane.showMessageDialog(jFrame, "Seleccione un fichero válido.");
				}
				//inicializar las variables a 0 cuando ya se ha creado el fichero o no
				esFichero=false;
	            nombreFichero="";
				txtNombreFichero.setText("");
				textField.setText("");
				textArea.setText("");
				txtrInformacionFichero.setText("");
				 
			}
		});
		btnRenombrar.setBounds(133, 259, 109, 23);
		contentPane.add(btnRenombrar);
	
		JButton btnCopiar = new JButton("Copiar");
		btnCopiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (esFichero) {//si el archivo seleccionado es un fichero
					contenido=txtrInformacionFichero.getText();
					VentanaCopiar copiar = new VentanaCopiar();
					copiar.setVisible(true);//abro la ventana renombrar para seguir el proceso
				}else {
					JOptionPane.showMessageDialog(jFrame, "Seleccione un fichero válido.");
				}
				//inicializar las variables a 0 cuando ya se ha creado el fichero o no
				esFichero=false;
	            nombreFichero="";
				txtNombreFichero.setText("");
				textField.setText("");
				textArea.setText("");
				txtrInformacionFichero.setText("");
			}
		});
		btnCopiar.setBounds(304, 259, 89, 23);
		contentPane.add(btnCopiar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (esFichero) {//si el archivo seleccionado es un fichero
					VentanaEliminar eliminar = new VentanaEliminar();
					eliminar.setVisible(true);//abro la ventana eliminar para seguir el proceso 
				}else {
					JOptionPane.showMessageDialog(jFrame, "Seleccione un fichero válido.");
				}
				//inicializar las variables a 0 cuando ya se ha creado el fichero o no
				esFichero=false;
	            nombreFichero="";
				txtNombreFichero.setText("");
				textField.setText("");
				textArea.setText("");
				txtrInformacionFichero.setText("");	
			}
		});
		btnEliminar.setBounds(459, 259, 89, 23);
		contentPane.add(btnEliminar);
		
		JButton btnMostrar = new JButton("Mostrar Contenido Directorio");//Boton para mostrar el contenido del directorio seleccionado
		btnMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				Modelo modelo = new Modelo();
				
				if (textField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(jFrame, "Seleccione un directorio válido.");
				}else {
					textArea.setText(modelo.MostrarInfo(direccion));//obtiene la informacion y la pone en el textArea
				}	
			}	
		});
		btnMostrar.setBounds(119, 52, 212, 23);
		contentPane.add(btnMostrar);
		
		JButton btnDetalles = new JButton("Mostrar Detalles Fichero");//Muestra los detalles del fichero seleccionado
		btnDetalles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Modelo modelo = new Modelo();
				
				if (textField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(jFrame, "Seleccione un fichero válido.");
				}else {
					textArea.setText(modelo.MostrarDetalles(direccion));//obtiene la informacion y la pone en el textArea
				}
			}
		});
		btnDetalles.setBounds(365, 52, 197, 23);
		contentPane.add(btnDetalles);
		
		JButton btnEditar = new JButton("Mostrar contenido Fichero");//Mostrar el contenido del fichero selecionado(txt)
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			    String lineas="";
				File archivo = new File(direccion);
			
			try {
				BufferedReader leer = new BufferedReader(new FileReader(archivo));
				String linea = leer.readLine();
				
				while (linea != null) {
					lineas=lineas+linea+"\n";//lee linea por linea y las concatena en una variable lineas
					txtrInformacionFichero.setText(lineas);//Escribir contenido en el textArea
					linea = leer.readLine();
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
		});
		btnEditar.setBounds(217, 329, 168, 23);
		contentPane.add(btnEditar);
		
		JButton btnSobrescribir = new JButton("Sobrescribir");//Sobrescribe el contenido el archivo seleccionado
		btnSobrescribir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				FileWriter fichero = null;
		        BufferedWriter buffer = null;

		        try {
		            fichero = new FileWriter(direccion);
		            buffer = new BufferedWriter(fichero);                                   
		            buffer.write(txtrInformacionFichero.getText());//obtiene la nueva informacion del fichero del textArea y sobrescribe el fichero
		            
		            JOptionPane.showMessageDialog(jFrame, "Guardado correctamente.");
		            //inicializar las variables a 0 cuando ya se ha sobrescrito el fichero
		            esFichero=false;
		            nombreFichero="";
		            direccion="";
		            txtNombreFichero.setText("");
					textField.setText("");
					textArea.setText("");
					txtrInformacionFichero.setText("");
		        } catch (IOException e1) {
		        	JOptionPane.showMessageDialog(jFrame, "ERROR, Archivo no encontrado.");  
		        }
		        try {
					buffer.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}	
		});
		btnSobrescribir.setBounds(474, 632, 115, 23);
		contentPane.add(btnSobrescribir);
		
		
		
		JButton btnRemplazarPalabra = new JButton("Cambiar");
		btnRemplazarPalabra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	
			            try {
			            	 	String nueva=txtNuevaPalabra.getText();
			            	 	FileWriter writer = new FileWriter(direccion);
					            String antigua = txtAntiguaPalabra.getText();
					            String line = "", content = ""; 
					            
					            String newContent = content.replace(antigua,nueva);
			            	final BufferedReader reader = new BufferedReader(new FileReader(direccion));
			            	    
							while((line = reader.readLine())!= null) {
							    content += line + "\r\n";
							    reader.close();
							    writer.write(newContent);
					            writer.close();
							}
							JOptionPane.showMessageDialog(jFrame, "Palabras cambiadas!.");
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
			}
		});
		btnRemplazarPalabra.setBounds(615, 329, 109, 23);
		contentPane.add(btnRemplazarPalabra);
		
		JButton btnBuscarPalabra = new JButton("Buscar");
		btnBuscarPalabra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String texto = txtrInformacionFichero.getText();
		        String palabra = txtAntiguaPalabra.getText();
		        int cnt = 0;
		        
		        for (int i = 0; i < texto.length(); i++) {
		            if (texto.substring(i).startsWith(palabra)) {
		                cnt ++;
		            }
		        }
		        JOptionPane.showMessageDialog(jFrame, palabra+" se repite "+cnt+" veces.");

			}
		});
		btnBuscarPalabra.setBounds(615, 303, 109, 23);
		contentPane.add(btnBuscarPalabra);
		
		
	}
}
