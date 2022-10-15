import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.ibm.icu.util.StringTokenizer;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;

public class VentanaCopiar extends JFrame {

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaCopiar frame = new VentanaCopiar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VentanaCopiar() {
		this.setLocationRelativeTo(null);
		Vista v = new Vista();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 469, 141);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		//poner el sufijo _copia en el nombre del fichero
		String nombreCopia;
		StringTokenizer st = new StringTokenizer(v.nombreFichero,".");
		nombreCopia=st.nextToken()+"_copia."+st.nextToken();
		
		JLabel lblFicheroCopia = new JLabel("New label");
		lblFicheroCopia.setBounds(251, 34, 229, 14);
		contentPane.add(lblFicheroCopia);
		lblFicheroCopia.setText(nombreCopia);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Desea copiar el fichero con el nombre 🡆 ");
		lblNewLabel.setBounds(21, 34, 234, 14);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Vista v = new Vista();
				String lineas="";
				
				File fichero = new File (v.rutaDirectorio,nombreCopia);//Crear el archivo con el sufijo _copia 
				File archivo = new File(v.direccion);
				
				try {
					BufferedReader leer = new BufferedReader(new FileReader(archivo));
					String linea = leer.readLine();
					
					while (linea != null) {
						//obtengo la info del fichero seleccionado y lo vuelco en la variable lineas
						lineas=lineas+linea+"\n";
						linea = leer.readLine();
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				 
				try {//creo el fichero _copia con su contenido correspondiente
					  if (fichero.createNewFile()) {
						  PrintWriter writer = new PrintWriter(fichero.getAbsolutePath(), "UTF-8");
					      writer.println(lineas);
					       
					      writer.close();
					      System.out.println("El fichero se ha creado correctamente");
					  }
					  else {
						  System.out.println("No ha podido ser creado el fichero");
					  }
					} catch (IOException ioe) {
					  ioe.printStackTrace();
					}
				dispose();
			}
		});
		btnNewButton.setBounds(161, 68, 89, 23);
		contentPane.add(btnNewButton);	
	}
}
