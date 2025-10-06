package visuals;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.GridLayout;
import system.*;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class Mainscreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Graph _graph;
	private PrimSolver _solver;
	private Groups _groups;
	private JLabel lblInterest1,lblInterest2,lblInterest3,lblInterest4, lblUser;
	private JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mainscreen frame = new Mainscreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Mainscreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 654, 466);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		initializeVisuals();
		initializeLogic();
		initializeInteractuables();
	}
	
	private void initializeVisuals() {
		lblUser = new JLabel("Usuario");
		lblUser.setBounds(22, 22, 46, 14);
		contentPane.add(lblUser);
		
		lblInterest1 = new JLabel("Interes Tango:");
		lblInterest1.setBounds(22, 75, 150, 14);
		contentPane.add(lblInterest1);
		
		lblInterest2 = new JLabel("Interes Folclore:");
		lblInterest2.setBounds(22, 88, 150, 14);
		contentPane.add(lblInterest2);
		
		lblInterest3 = new JLabel("Interes Urbano:");
		lblInterest3.setBounds(22, 113, 150, 14);
		contentPane.add(lblInterest3);
		
		lblInterest4 = new JLabel("Interes Rock:");
		lblInterest4.setBounds(22, 100, 150, 14);
		contentPane.add(lblInterest4);
		
		JPanel panel = new JPanel();
		panel.setBounds(162, 11, 466, 405);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(3, 2, 0, 0));

		
		for (int i = 0; i < 9; i++) {
			JPanel panel1 = new JPanel();
			panel.add(panel1);
			panel1.setLayout(null);
			JLabel lblGroupNumber = new JLabel("New label");
			lblGroupNumber.setBounds(10, 11, 46, 14);
			panel1.add(lblGroupNumber);
		}
	}
	
	private void initializeLogic() {
		_graph = new Graph();
		for (int i = 0; i < 20; i++) {
			_graph.addUser(new User(i));
		}
		_solver = new PrimSolver(_graph);
		List<Arista> mst = _solver.getSolution();
		_groups = new Groups(mst, _graph.size(), _graph);
	}
	
	private void initializeInteractuables() {
		comboBox = new JComboBox();
		comboBox.setBounds(22, 42, 130, 22);
		contentPane.add(comboBox);
		
		for (int i = 0; i<_graph.size(); i++)
			comboBox.addItem(_graph.getUser(i));
		
		comboBox.addActionListener((new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateUser();
			}
		}));
		updateUser();
	}
	
	private void updateUser() {
		lblInterest1.setText(rebuildText(lblInterest1.getText())+ " " + ((User) comboBox.getSelectedItem()).getInteresTango());
		lblInterest2.setText(rebuildText(lblInterest2.getText())+ " " + ((User) comboBox.getSelectedItem()).getInteresFolclore());
		lblInterest3.setText(rebuildText(lblInterest3.getText())+ " " + ((User) comboBox.getSelectedItem()).getInteresRock());
		lblInterest4.setText(rebuildText(lblInterest4.getText())+ " " + ((User) comboBox.getSelectedItem()).getInteresUrbano());
	}
	
	private String rebuildText(String base) {
		return base.substring(0,base.indexOf(":")+1);
	}
}
