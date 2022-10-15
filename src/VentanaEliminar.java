import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

public class VentanaEliminar extends JFrame {

	private JPanel contentPane;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaEliminar frame = new VentanaEliminar();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
 
	public VentanaEliminar() {
		this.setLocationRelativeTo(null);
		Vista v = new Vista();
		
		setBounds(100, 100, 249, 146);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("¿Desea eliminar?");
		lblNewLabel.setBounds(74, 11, 200, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblFichero = new JLabel("");
		lblFichero.setBounds(68, 36, 135, 14);
		contentPane.add(lblFichero);
		lblFichero.setText(v.nombreFichero);//establezco en nombre del fichero a eliminar en la ventana
		
		JButton btnAceptar = new JButton("Aceptar");//Eliminar el ficheroseleccionado
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File fichero = new File(v.direccion);
				fichero.delete();//elimino el fichero seleccionado
				dispose();//salgo de la ventana
			}
		});
		btnAceptar.setBounds(70, 61, 89, 23);
		contentPane.add(btnAceptar);
	}
}
