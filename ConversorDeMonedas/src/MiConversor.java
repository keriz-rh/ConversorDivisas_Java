import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class MiConversor {

	private JFrame frame;
	private JButton btn;
	private JComboBox cmb;
	private JLabel lbl;
	private JTextField txt;
	
	public enum Moneda {
		dolar_pesos,
		dolar_euro,
		dolar_libraEsterlinas,
		dolar_YenJapones,
		dolar_WonSurCoreano,
		pesos_dolar,
		euro_dolar,
		libraEsterlinas_dolar,
		YenJapones_dolar,
		WonSurCoreano_dolar
	}
	
	public double peso = 16.75;
	public double euro = 0.91;
	public double libraEsterlinas = 0.78;
	public double YenJapones = 142.77;
	public double WonSurCoreano = 1281.18;
	
	public double valorInput = 0.00;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MiConversor window = new MiConversor();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MiConversor() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txt = new JTextField();
		txt.setBounds(34, 38, 156, 24);
		frame.getContentPane().add(txt);
		txt.setColumns(10);
		
		cmb = new JComboBox<Moneda>();
		cmb.setModel(new DefaultComboBoxModel<>(Moneda.values()));
		cmb.setBounds(34, 95, 206, 24);
		frame.getContentPane().add(cmb);
		
		// button event
		btn = new JButton("Convertir.");
		btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Convertir();
			}
			
			
		});
		btn.setBounds(257, 97, 147, 21);
		frame.getContentPane().add(btn);
		
		lbl = new JLabel("00.00");
		lbl.setBounds(213, 35, 213, 28);
		frame.getContentPane().add(lbl);
	}
	
	public void Convertir() {
		if (Validar(txt.getText()) ) {
			Moneda moneda = (Moneda)cmb.getSelectedItem();
			
			switch (moneda) {
			case dolar_pesos: 
				DolarAMoneda(peso);
				break;
			case dolar_euro: 
				DolarAMoneda(euro);
				break;
			case dolar_libraEsterlinas:
				DolarAMoneda(libraEsterlinas);
				break;
			case dolar_YenJapones:
				DolarAMoneda(YenJapones);
				break;
			case dolar_WonSurCoreano:
				DolarAMoneda(WonSurCoreano);
				break;
			case pesos_dolar: 
				MonedaADolar(peso);
				break;
			case euro_dolar: 
				MonedaADolar(euro);
				break;
			case libraEsterlinas_dolar: 
				MonedaADolar(libraEsterlinas);
				break;
			case YenJapones_dolar:
				MonedaADolar(YenJapones);
				break;
			case WonSurCoreano_dolar:
				MonedaADolar(WonSurCoreano);
			
			default:
				throw new IllegalArgumentException("Unexpected value: " + moneda);
				
		}
}
	}
	
	public void DolarAMoneda(double moneda) {
		double res = valorInput * moneda;
		lbl.setText(Redondear(res));
	}
	public void MonedaADolar(double moneda) {
		double res = valorInput / moneda;
		lbl.setText(Redondear(res));
	}
	
	public String Redondear(double valor) {
		DecimalFormat df = new DecimalFormat("0.00");
		df.setRoundingMode(RoundingMode.HALF_UP);
		return df.format(valor);
	}
	
	public boolean Validar(String texto) {
		try {
			double x = Double.parseDouble(texto);
			if (x > 0); {
				valorInput = x;
				return true;
			}
		} catch (NumberFormatException e) {
			lbl.setText("¡Solamente números!");
			return false;
		}
	}
	
}
