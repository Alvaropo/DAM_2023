import java.awt.EventQueue;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class VentanaRenombrar extends JFrame {

	private JPanel contentPane;
	private JTextField txtRenombrar;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaRenombrar frame = new VentanaRenombrar();
					
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VentanaRenombrar() {
		this.setLocationRelativeTo(null);
		Vista v = new Vista();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 125);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Renombrar el fichero a");
		lblNewLabel.setBounds(63, 22, 163, 14);
		contentPane.add(lblNewLabel);
		
		txtRenombrar = new JTextField(v.direccion);
		txtRenombrar.setBounds(236, 19, 113, 20);
		contentPane.add(txtRenombrar);
		txtRenombrar.setColumns(10);
		txtRenombrar.getText();
		
		JButton btnNewButton = new JButton("Aceptar");//Renombrar el fichero seleccioando
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				File oldfile = new File(v.direccion);//paso la direccion del fichero a renombrar
		        File newfile = new File(txtRenombrar.getText());//obtendo la direccion del fichero renombrado
		        
		        	if (oldfile.renameTo(newfile)) {//lo renombro
			            System.out.println("archivo renombrado");
			        } else {
			            System.out.println("error");
			        }
				dispose();
			}
		});
		btnNewButton.setBounds(153, 52, 89, 23);
		contentPane.add(btnNewButton);
	}
}
