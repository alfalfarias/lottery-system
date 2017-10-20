package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Toolkit;

import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import models.Apuesta;
import models.Data;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Premiacion extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 829663450669570508L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JScrollPane scrollPane;
	Data data = new Data();
	private JComboBox<Object> comboBox;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Premiacion dialog = new Premiacion();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Premiacion(Data data) {
		this();
		setData(data);
		refresh();
	}

	/**
	 * Create the dialog.
	 */
	public Premiacion() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Premiacion.class.getResource("/Premiacion/trophy.png")));
		setTitle("Sorteo - Ganadores");
		setBounds(100, 100, 569, 316);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Tickets Premiados", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			{
				scrollPane = new JScrollPane();
				{
					table = new JTable();
					table.setEnabled(false);
					table.setFillsViewportHeight(true);
					table.setModel(new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
							"N\u00B0 Ticket", "C\u00E9dula", "Nombres", "Apellidos", "Apuesta", "Ganancia", "Estado"
						}
					));
					scrollPane.setViewportView(table);
				}
			}
			comboBox = new JComboBox<Object>();
			JLabel lblSeleccionarTicket = new JLabel("Seleccionar Ticket:");
			JButton btnNewButton = new JButton("Pagar Ticket premiado");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (comboBox.getSelectedItem() == null){
						JOptionPane.showMessageDialog(
								panel,
								"No hay ningún ticket seleccionado.");
					}
					else{
						for (int i=0; i<table.getRowCount(); i++){
							if (((DefaultTableModel) table.getModel()).getValueAt(i, 0).toString().equals(comboBox.getSelectedItem().toString())){
								Apuesta apuesta = data.getApuesta(i);
								if (apuesta.getEstado().equals("::GANADOR::")){
									apuesta.setEstado("::PAGADO::");
									data.setApuesta(i, apuesta);
								}
								else{
									JOptionPane.showMessageDialog(
											panel,
											"El ticket debe ser ::GANADOR:: para poder ser pagado.");
								}
								((DefaultTableModel) table.getModel()).setValueAt(apuesta.getEstado(), i, 6);
							}
						}
					}
				}
			});
			btnNewButton.setIcon(new ImageIcon(Premiacion.class.getResource("/Premiacion/refresh-page-option.png")));
			GroupLayout gl_panel = new GroupLayout(panel);
			gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel.createSequentialGroup()
						.addComponent(lblSeleccionarTicket)
						.addGap(10)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)
						.addGap(6)
						.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE))
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE)
			);
			gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel.createSequentialGroup()
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblSeleccionarTicket)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnNewButton))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
						.addContainerGap())
			);
			panel.setLayout(gl_panel);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Aceptar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Main.data = getData();
						dispose();
					}
				});
				okButton.setIcon(new ImageIcon(Premiacion.class.getResource("/Premiacion/check.png")));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
	public Data getData() {
		return data;
	}
	public void setData(Data data) {
		this.data = data;
	}
	public void refresh(){
		DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
		defaultTableModel.setRowCount(0);
		for (models.Apuesta apuesta: getData().getApuestas()){
			defaultTableModel.addRow(new Object[1]);
			defaultTableModel.setValueAt(apuesta.getId(), defaultTableModel.getRowCount()-1, 0);
			defaultTableModel.setValueAt(apuesta.getPersona().getCedula(), defaultTableModel.getRowCount()-1, 1);
			defaultTableModel.setValueAt(apuesta.getPersona().getNombres(), defaultTableModel.getRowCount()-1, 2);
			defaultTableModel.setValueAt(apuesta.getPersona().getApellidos(), defaultTableModel.getRowCount()-1, 3);
			defaultTableModel.setValueAt(apuesta.getMonto(), defaultTableModel.getRowCount()-1, 4);
			defaultTableModel.setValueAt((apuesta.getMonto()*getData().getLoteria().getGanancia()), defaultTableModel.getRowCount()-1, 5);
			defaultTableModel.setValueAt((apuesta.getEstado()), defaultTableModel.getRowCount()-1, 6);
			comboBox.addItem(apuesta.getId());
		}
		table.setModel(defaultTableModel);
	}
}
