import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ImprimanteView implements Observer{

	private JFrame frame;
	private JTextField pathTextField;
	private JTextField fromTextField;
	private JTextField toTextField;
	private JLabel lblDe;
	private JLabel lblA;
	private JButton btnImprimer;
	private  Printer imprimante;
	private JLabel lblNewLabel;
	private JButton btnEncre;

	public static void main(String[] args) {
		ImprimanteView view1 = new ImprimanteView();
		ImprimanteView view2 = new ImprimanteView();

	}
	

	public ImprimanteView() {
		this.imprimante = Printer.getPrinter();
		imprimante.register(this);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 560, 308);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		pathTextField = new JTextField();
		pathTextField.setBounds(24, 73, 382, 32);
		frame.getContentPane().add(pathTextField);
		pathTextField.setColumns(10);
		
		JButton btnImport = new JButton("Importer");
		btnImport.setBounds(414, 73, 120, 32);
		frame.getContentPane().add(btnImport);

		lblNewLabel = new JLabel("Imprimante: " + imprimante.getState().getClass().getName());
		lblNewLabel.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 25));
		lblNewLabel.setBounds(47, 11, 443, 51);
		frame.getContentPane().add(lblNewLabel);
		
		fromTextField = new JTextField();
		fromTextField.setBounds(98, 132, 86, 32);
		frame.getContentPane().add(fromTextField);
		fromTextField.setColumns(10);
		
		toTextField = new JTextField();
		toTextField.setColumns(10);
		toTextField.setBounds(241, 132, 86, 32);
		frame.getContentPane().add(toTextField);
		
		lblDe = new JLabel("De:");
		lblDe.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 20));
		lblDe.setBounds(47, 132, 41, 32);
		frame.getContentPane().add(lblDe);
		
		lblA = new JLabel("A: ");
		lblA.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 20));
		lblA.setBounds(201, 132, 41, 32);
		frame.getContentPane().add(lblA);
		
		btnImprimer = new JButton("Imprimer");
		btnImprimer.setBounds(347, 132, 120, 32);
		frame.getContentPane().add(btnImprimer);

		
		btnEncre = new JButton("Remplir l'imprimante");
		btnEncre.setBounds(347, 224, 176, 32);
		frame.getContentPane().add(btnEncre);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(24, 226, 109, 28);
		frame.getContentPane().add(btnCancel);
		
		
		btnImport.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.showOpenDialog(null);
				File f = fileChooser.getSelectedFile();
				String path = f.getAbsolutePath();
				pathTextField.setText(path);
			}
			
		});
		
		
		btnImprimer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(!fromTextField.getText().equals("") && !toTextField.getText().equals("")) {
					int input = JOptionPane.showConfirmDialog(null, "Vous voulez Recto Verso?", "Select an Option...",
							JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.YES_NO_CANCEL_OPTION);
					int nbPages = Integer.parseInt(fromTextField.getText()) + Integer.parseInt(toTextField.getText()) -1;
					
					if(imprimante.getState().getClass() != EmptyState.class) {
						if(input == 1) {
								imprimante.context.setMethod(new OneSidedPrinting());
								if(imprimante.getState().getClass() == PrintingState.class)
									JOptionPane.showMessageDialog(null, "Busy Printing.. wait a moment!", "error", JOptionPane.ERROR_MESSAGE);
								else
									imprimante.print(nbPages);
							}
							else if(input == 0) {
								imprimante.context.setMethod(new DoubleSidedPrinting());
								if(imprimante.getState().getClass() == PrintingState.class)
									JOptionPane.showMessageDialog(null, "Busy Printing.. wait a moment!", "error", JOptionPane.ERROR_MESSAGE);
								else
									imprimante.print(nbPages);
							}
					}
					else
						JOptionPane.showMessageDialog(null, "Printer is empty please Refill!", "error", JOptionPane.ERROR_MESSAGE);
				}
			}		
			
		});
		
		
		btnEncre.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(imprimante.refill())
					JOptionPane.showMessageDialog(null, "refilled Successfully", "success", JOptionPane.INFORMATION_MESSAGE);
				else
					JOptionPane.showMessageDialog(null, "Printer is Busy", "success", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(imprimante.getState().getClass() == EmptyState.class)
					JOptionPane.showMessageDialog(null, "Nothing to cancel i am empty", "Empty", JOptionPane.INFORMATION_MESSAGE);
				else if(imprimante.getState().getClass() == ReadyState.class)
					JOptionPane.showMessageDialog(null, "Nothing to cancel i am ready", "Ready", JOptionPane.INFORMATION_MESSAGE);
				else
					imprimante.cancel();
			}
			
		});
		
		
		frame.setVisible(true);
	}

	@Override
	public void update(IPrinterState state) {
		lblNewLabel.setText("Imprimante: " + state.getClass().getName());
	}
}
