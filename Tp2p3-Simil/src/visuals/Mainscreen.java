package visuals;

import java.awt.EventQueue;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import system.Arista;
import system.ClusterDivider;
import system.Graph;
import system.Groups;
import system.PrimSolver;
import system.User;

public class Mainscreen extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane, panelResultados;
    private Graph _graph;
    private PrimSolver _solver;
    private Groups _groups;
    private ClusterDivider _divider;
    private JButton deleteUserBtn;

    
    private JComboBox<User> comboBox;
    private JTextField interestField4, interestField3, interestField2, interestField1, newNameField, txtGroup;
    private JButton generateGroupsBtn, addUserBtn;

    
    private JLabel lblInterestTangoValue, lblInterestFolcloreValue, lblInterestRockValue, lblInterestUrbanoValue;

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
        setTitle("Agrupador de Usuarios por Gustos Musicales");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        
        initializeLogic();  
        initializeLayout();  
        
        
        populateComboBox();
        updateUserSelectionInfo();
    }

    private void initializeLogic() {
        _graph = new Graph();
        for (int i = 0; i < 20; i++) {
            _graph.addUser(new User(i));
        }
        _solver = new PrimSolver(_graph);
        _divider = new ClusterDivider();
         
    }
    
    private void initializeLayout() {
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(new BorderLayout(10, 10)); 
        setContentPane(contentPane);
        
       
        JPanel leftPanel = createLeftControlPanel();
        contentPane.add(leftPanel, BorderLayout.WEST);

         
        panelResultados = new JPanel();
        panelResultados.setLayout(new GridLayout(0, 2, 10, 10));  
        
        
        JScrollPane scrollPane = new JScrollPane(panelResultados);
        contentPane.add(scrollPane, BorderLayout.CENTER);
    }
    
    private JPanel createLeftControlPanel() {
        JPanel controlPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);  
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;  

         
        controlPanel.add(new JLabel("Seleccionar Usuario:"), gbc);
        gbc.gridy++;
        comboBox = new JComboBox<>();
        comboBox.addActionListener(e -> updateUserSelectionInfo());

        deleteUserBtn = new JButton("Eliminar");
        deleteUserBtn.addActionListener(e -> deleteSelectedUser());
        JPanel userSelectionPanel = new JPanel(new BorderLayout(5, 0));
        userSelectionPanel.add(comboBox, BorderLayout.CENTER);
        userSelectionPanel.add(deleteUserBtn, BorderLayout.EAST);
        controlPanel.add(userSelectionPanel, gbc);

        /////////////////////////////////////////////
        gbc.gridy++;
        controlPanel.add(new JSeparator(), gbc);
        gbc.gridy++;
        gbc.gridwidth = 1;  
        
        controlPanel.add(new JLabel("Interés Tango:"), gbc);
        gbc.gridx = 1;
        lblInterestTangoValue = new JLabel("-");
        controlPanel.add(lblInterestTangoValue, gbc);
        
        gbc.gridx = 0; gbc.gridy++;
        controlPanel.add(new JLabel("Interés Folclore:"), gbc);
        gbc.gridx = 1;
        lblInterestFolcloreValue = new JLabel("-");
        controlPanel.add(lblInterestFolcloreValue, gbc);
        
        gbc.gridx = 0; gbc.gridy++;
        controlPanel.add(new JLabel("Interés Rock:"), gbc);
        gbc.gridx = 1;
        lblInterestRockValue = new JLabel("-");
        controlPanel.add(lblInterestRockValue, gbc);

        gbc.gridx = 0; gbc.gridy++;
        controlPanel.add(new JLabel("Interés Urbano:"), gbc);
        gbc.gridx = 1;
        lblInterestUrbanoValue = new JLabel("-");
        controlPanel.add(lblInterestUrbanoValue, gbc);
        
         /////////////////////////////////////////////
        gbc.gridx = 0; gbc.gridy++; gbc.gridwidth = 2;
        controlPanel.add(new JSeparator(), gbc);
        gbc.gridy++;
        controlPanel.add(new JLabel("Agregar Nuevo Usuario"), gbc);
        
        gbc.gridy++;
        controlPanel.add(new JLabel("Nombre:"), gbc);
        gbc.gridy++;
        newNameField = new JTextField(15);
        controlPanel.add(newNameField, gbc);
        
        gbc.gridy++;
        controlPanel.add(new JLabel("Intereses (Tango, Folclore, Rock, Urbano):"), gbc);
        gbc.gridy++;
        interestField1 = new JTextField(5);
        interestField2 = new JTextField(5);
        interestField3 = new JTextField(5);
        interestField4 = new JTextField(5);
        JPanel interestsPanel = new JPanel(new GridLayout(1, 4, 5, 5));
        interestsPanel.add(interestField1);
        interestsPanel.add(interestField2);
        interestsPanel.add(interestField3);
        interestsPanel.add(interestField4);
        controlPanel.add(interestsPanel, gbc);
        
        gbc.gridy++;
        addUserBtn = new JButton("Agregar Usuario");
        addUserBtn.addActionListener(e -> addUser());
        controlPanel.add(addUserBtn, gbc);
        
        /////////////////////////////////////////////
        gbc.gridx = 0; gbc.gridy++; gbc.gridwidth = 2;
        controlPanel.add(new JSeparator(), gbc);
        
        gbc.gridy++;
        gbc.gridwidth = 1;
        controlPanel.add(new JLabel("Cantidad de grupos:"), gbc);
        gbc.gridx = 1;
        txtGroup = new JTextField("2", 5);
        controlPanel.add(txtGroup, gbc);
        
        gbc.gridx = 0; gbc.gridy++; gbc.gridwidth = 2;
        generateGroupsBtn = new JButton("Generar Grupos");
        generateGroupsBtn.addActionListener(e -> generateGroups());
        controlPanel.add(generateGroupsBtn, gbc);

        return controlPanel;
    }

    private void populateComboBox() {
        for (User u : _graph.getAllUsers()) {
            comboBox.addItem(u);
        }
    }
    
    private void updateUserSelectionInfo() {
        User selectedUser = (User) comboBox.getSelectedItem();
        if (selectedUser == null) return;

        lblInterestTangoValue.setText(String.valueOf(selectedUser.getInteresTango()));
        lblInterestFolcloreValue.setText(String.valueOf(selectedUser.getInteresFolclore()));
        lblInterestRockValue.setText(String.valueOf(selectedUser.getInteresRock()));
        lblInterestUrbanoValue.setText(String.valueOf(selectedUser.getInteresUrbano()));
    }

    private void generateGroups() {
        panelResultados.removeAll();
        
        try {
            int cantidad = Integer.parseInt(txtGroup.getText());
            if (cantidad < 1 || cantidad > _graph.size()) {
                JOptionPane.showMessageDialog(this, "La cantidad de grupos debe ser entre 1 y el número de usuarios.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            List<Arista> mst = _solver.getSolution();
            List<Set<Integer>> grupos = _divider.dividirEnXGrupos(mst, _graph.size(), cantidad);
            _groups = new Groups(mst, _graph.size(), _graph); // Re-creamos Groups con el MST actual
            _groups.updateGroups(grupos);
            
            for (int i = 0; i < grupos.size(); i++) {
                StringBuilder groupText = new StringBuilder("<html><b>Grupo: " + (i + 1) + "</b><br/>");
                groupText.append("<u>Miembros:</u><br/>");
                for (int id : grupos.get(i)) {
                    groupText.append(_graph.getUser(id).getNombre()).append("<br/>");
                }
                
                List<String[]> averages = _groups.averagesWritten();
                if (i < averages.size()) {
                    groupText.append("<u>Promedios:</u><br/>");
                    for(String s : averages.get(i)) {
                         groupText.append(s).append("<br/>");
                    }
                }
                
                groupText.append("</html>");
                JLabel lblGroup = new JLabel(groupText.toString());
                lblGroup.setBorder(new EmptyBorder(5,5,5,5));
                panelResultados.add(lblGroup);
            }
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un número válido en la cantidad de grupos.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error al generar los grupos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        panelResultados.revalidate();
        panelResultados.repaint();
    }

    private void addUser() {
        if (newNameField.getText().isBlank() || interestField1.getText().isBlank() || interestField2.getText().isBlank() ||
            interestField3.getText().isBlank() || interestField4.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Campos Vacíos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            String nombre = newNameField.getText();
            int interesTango = Integer.parseInt(interestField1.getText());
            int interesFolclore = Integer.parseInt(interestField2.getText());
            int interesRock = Integer.parseInt(interestField3.getText());
            int interesUrbano = Integer.parseInt(interestField4.getText());

            User newUser = new User(_graph.size(), nombre, interesTango, interesFolclore, interesRock, interesUrbano);
            _graph.addUser(newUser);
            comboBox.addItem(newUser); // Se actualiza el ComboBox
            _solver.updateGraph(_graph);
            
            clearFields();
            JOptionPane.showMessageDialog(this, "Usuario '" + nombre + "' agregado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Los campos de interés solo deben contener números.", "Error de Entrada", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error de Validación", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void deleteSelectedUser() {
        User selectedUser = (User) comboBox.getSelectedItem();
        if (selectedUser == null) {
            JOptionPane.showMessageDialog(this, "No hay ningún usuario seleccionado para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int response = JOptionPane.showConfirmDialog(this, 
            "¿Estás seguro de que quieres eliminar a " + selectedUser.getNombre() + "?", 
            "Confirmar Eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        if (response == JOptionPane.YES_OPTION) {
            
            _graph.deleteUserAndReIndex(selectedUser);

            
            _solver.updateGraph(_graph);
            
           
            panelResultados.removeAll();  
            
            comboBox.removeAllItems();
            populateComboBox();
            
           
            updateUserSelectionInfo(); 
            revalidate();
            repaint();

            JOptionPane.showMessageDialog(this, "Usuario eliminado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void clearFields() {
        newNameField.setText("");
        interestField1.setText("");
        interestField2.setText("");
        interestField3.setText("");
        interestField4.setText("");
    }
}
