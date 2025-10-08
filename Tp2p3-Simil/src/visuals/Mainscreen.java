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
import java.util.Set;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;

public class Mainscreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane, panel;
	private Graph _graph;
	private PrimSolver _solver;
	private Groups _groups;	
	private ClusterDivider _divider;
	private JLabel lblInterest1,lblInterest2,lblInterest3,lblInterest4, lblUser, lblnewInterest4, 
	lblnewInterest3, lblnewInterest2, lblnewInterest1, lblNewName;
	private JComboBox<User> comboBox;
	private JTextField InterestField4, InterestField3, InterestField2, InterestField1, newNameField;
	private JButton generateGroupsbtn, addUserbtn;
	private JLabel lblGroupAmount;
	private JTextField txtGroup;

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
		
		
		lblnewInterest4 = new JLabel("Interes Urbano");
		lblnewInterest4.setBounds(22, 359, 104, 14);
		contentPane.add(lblnewInterest4);
		
		lblnewInterest3 = new JLabel("Interes Rock");
		lblnewInterest3.setBounds(22, 318, 86, 14);
		contentPane.add(lblnewInterest3);
		
		lblnewInterest2 = new JLabel("Interes Folclore");
		lblnewInterest2.setBounds(22, 277, 86, 14);
		contentPane.add(lblnewInterest2);
		
		lblnewInterest1 = new JLabel("Interes Tango");
		lblnewInterest1.setBounds(22, 237, 86, 14);
		contentPane.add(lblnewInterest1);
		
		lblNewName = new JLabel("Nombre");
		lblNewName.setBounds(22, 190, 86, 14);
		contentPane.add(lblNewName);
		
		lblGroupAmount = new JLabel("Cantidad:");
		lblGroupAmount.setBounds(169, 336, 80, 14);
		contentPane.add(lblGroupAmount);
		
		panel = new JPanel();
		panel.setBounds(162, 11, 466, 281);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(3, 2, 0, 0));
		
		generateGroupsbtn = new JButton("Generar grupos");
		generateGroupsbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generateGroups();
			}
		});
		generateGroupsbtn.setBounds(172, 309, 137, 23);
		contentPane.add(generateGroupsbtn);
	}
	
	private void initializeLogic() {
		_graph = new Graph();
		for (int i = 0; i < 20; i++) {
			_graph.addUser(new User(i));
		}
		_solver = new PrimSolver(_graph);
		List<Arista> mst = _solver.getSolution();
		_groups = new Groups(mst, _graph.size(), _graph);
		_divider = new ClusterDivider();
	}
	

	private void initializeInteractuables() {
		comboBox = new JComboBox<User>();
		comboBox.setBounds(22, 42, 130, 22);
		contentPane.add(comboBox);
		
		InterestField4 = new JTextField();
		InterestField4.setBounds(22, 374, 86, 20);
		contentPane.add(InterestField4);
		InterestField4.setColumns(10);
		
		InterestField3 = new JTextField();
		InterestField3.setBounds(22, 333, 86, 20);
		contentPane.add(InterestField3);
		InterestField3.setColumns(10);
		
		InterestField2 = new JTextField();
		InterestField2.setBounds(22, 295, 86, 20);
		contentPane.add(InterestField2);
		InterestField2.setColumns(10);
		
		InterestField1 = new JTextField();
		InterestField1.setBounds(22, 254, 86, 20);
		contentPane.add(InterestField1);
		InterestField1.setColumns(10);
		
		newNameField = new JTextField();
		newNameField.setBounds(22, 210, 86, 20);
		contentPane.add(newNameField);
		newNameField.setColumns(10);
		
		addUserbtn = new JButton("Agregar usuario");
		addUserbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addUser();
			}
		});
		addUserbtn.setBounds(10, 397, 130, 23);
		contentPane.add(addUserbtn);
		
		txtGroup = new JTextField();
		txtGroup.setText("2");
		txtGroup.setBounds(223, 333, 86, 20);
		contentPane.add(txtGroup);
		txtGroup.setColumns(10);

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
	
	private void generateGroups() {
		panel.removeAll();
		try {
		int cantidad = Integer.parseInt(txtGroup.getText());
		List<Arista> mst = _solver.getSolution();
		List<Set<Integer>> grupos = _divider.dividirEnXGrupos(mst, _graph.size(), cantidad);
		_groups.updateGroups(grupos);
		for (int i = 0; i < cantidad; i++) {
			JScrollPane scrollPane = new JScrollPane();
			panel.add(scrollPane);
			JLabel lblGroupNumber = new JLabel("<html>group: " + (i+1) + "<br/>");
			for (int id: grupos.get(i)) {
				lblGroupNumber.setText(lblGroupNumber.getText().concat(_graph.getUser(id) + "<br/>"));
			}
			List <String[]> txtlist = _groups.averagesWritten();
			for (String s : txtlist.get(i)) {
				lblGroupNumber.setText(lblGroupNumber.getText().concat(s + "<br/>"));
			}
			lblGroupNumber.setText(lblGroupNumber.getText().concat("<html>"));
			lblGroupNumber.setBounds(10, 11, 120, 120);
			scrollPane.setViewportView(lblGroupNumber);
			panel.revalidate();
		}
		}
		catch (Exception e) {
			txtGroup.setText("2");
			throw new IllegalArgumentException("Hay un caracter invalido en la cantidad de grupos");
		}
	}
	
	private void addUser() {
		if (newNameField.getText().isBlank() || InterestField1.getText().isBlank() || InterestField2.getText().isBlank() ||
				InterestField3.getText().isBlank() || InterestField4.getText().isBlank())
			throw new IllegalArgumentException("Algun campo esta vacio");
		try {
		String nombre = newNameField.getText();
		int interesTango = Integer.parseInt(InterestField1.getText());
		int interesFolclore = Integer.parseInt(InterestField2.getText());
		int interesRock = Integer.parseInt(InterestField3.getText());
		int interesUrbano = Integer.parseInt(InterestField4.getText());
		
		User newuser = new User(_graph.size(),nombre,interesTango,interesFolclore,interesRock,interesUrbano);
		_graph.addUser(newuser);
		_solver.updateGraph(_graph);
		_groups = null;
		_groups = new Groups(_solver.getSolution(),_graph.size(),_graph);
		clearFields();
		}
		catch (Exception e) {
			clearFields();
			throw new IllegalArgumentException("Algun campo de interes tiene letras");
		}
	}
	
	private void clearFields() {
		InterestField1.setText("");
		InterestField2.setText("");
		InterestField3.setText("");
		InterestField4.setText("");
		newNameField.setText("");
	}

	private String rebuildText(String base) {
		return base.substring(0,base.indexOf(":")+1);
	}
}
