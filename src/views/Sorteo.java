package views;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

import java.awt.Color;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import models.Apuesta;
import models.Data;
import models.Ficha;
import models.Loteria;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.ActionEvent;

public class Sorteo extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8148669044197962896L;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JPanel panel;
	private Data data = new Data();
	private Ficha fichaGanador = new Ficha();
	private JSpinner spinner;
	/**
	 * Create the panel.
	 */
	public Sorteo(Data data){
		this();
		this.data = data;
		refresh();
	}
	public Sorteo() {
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Lista de apuestas (Ordenadas por Ficha)", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Detalles del Sorteo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
						.addComponent(panel, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 520, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 246, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
					.addGap(20))
		);
		
		JLabel lblSorteoGanador = new JLabel("Sorteo ganador:");
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
		textField.setColumns(10);
		
		JLabel lblNDeGanadores = new JLabel("N\u00B0 de Tickets premiados: ");
		
		spinner = new JSpinner();
		spinner.setEnabled(false);
		
		JLabel lblTotalAPagar = new JLabel("Total a Pagar:");
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_1.setColumns(10);
		
		JLabel lblGanancia = new JLabel("Ganancia:");
		lblGanancia.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_2.setColumns(10);
		
		JButton btnNewButton = new JButton("Ganadores");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Premiacion premiacion=new Premiacion(data);
				premiacion.setLocationRelativeTo(panel);
				premiacion.setModal(true);
				premiacion.setVisible(true);
				refresh();
			}
		});
		btnNewButton.setIcon(new ImageIcon(Sorteo.class.getResource("/sorteo/businessman.png")));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblSorteoGanador)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblTotalAPagar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblNDeGanadores)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(spinner, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblGanancia)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblSorteoGanador)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTotalAPagar)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNDeGanadores)
								.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblGanancia)
								.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnOrdenarAleatoriamente = new JButton("Sorteo Aleatorio");
		btnOrdenarAleatoriamente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (data.getLoteria().getFichas().size() == 0){
					JOptionPane.showMessageDialog(
							panel,
							"No existen Fichas registradas.");
				}
				else{
					Random randomGenerator = new Random();
					//System.out.println("::BEFFORE:: ID: "+fichaGanador.getNumero()+" \t NOMBRE: "+fichaGanador.getNombre());
					fichaGanador = data.getLoteria().getFichas().get(randomGenerator.nextInt(data.getLoteria().getFichas().size()));
					//System.out.println("::AFTER:: ID: "+fichaGanador.getNumero()+" \t NOMBRE: "+fichaGanador.getNombre());
					refresh();
				}
			}
		});
		btnOrdenarAleatoriamente.setIcon(new ImageIcon(Sorteo.class.getResource("/sorteo/shuffle.png")));
		
		JButton btnOrdenarPorMenor = new JButton("Sorteo por maximixar ganancias");
		btnOrdenarPorMenor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (data.getLoteria().getFichas().size() == 0){
					JOptionPane.showMessageDialog(
							panel,
							"No existen Fichas registradas.");
				}
				else{
					int[] ficha_id = new int[data.getLoteria().getFichas().size()];
					double[] ficha_pago = new double[data.getLoteria().getFichas().size()];
					for (int i = 0; i < data.getLoteria().getFichas().size(); i++){
						ficha_id[i] = (data.getLoteria().getFichas().get(i).getNumero());
						ficha_pago[i] = data.getLoteria().getGanancia()*data.totalApostado(data.getLoteria().getFichas().get(i).getNumero());
					}
					//Ordenamos los ID
					for(int k = 0; k < ficha_pago.length; k++)
			            for(int f = 0;f < ficha_pago.length - 1 - k; f++)
			                if (ficha_pago[f] > ficha_pago[f+1]) {
			                    double ficha_pago_aux;
			                    ficha_pago_aux=ficha_pago[f];
			                    ficha_pago[f]=ficha_pago[f+1];
			                    ficha_pago[f+1]=ficha_pago_aux;
			                    int ficha_id_aux;
			                    ficha_id_aux=ficha_id[f];
			                    ficha_id[f]=ficha_id[f+1];
			                    ficha_id[f+1] = ficha_id_aux;
			                }
					//Ordenamos la data
					Loteria loteria = Main.data.getLoteria();
					for(int i = 0; i < Main.data.getLoteria().getFichas().size(); i++)
						if (Main.data.getLoteria().getFichas().get(i).getNumero() != ficha_id[i])
							for (int j = 0; j < Main.data.getLoteria().getFichas().size(); j++)
								if (Main.data.getLoteria().getFichas().get(j).getNumero() == ficha_id[i]){
									Ficha fichaAux = Main.data.getLoteria().getFichas().get(i);
									loteria.setFicha(i, Main.data.getLoteria().getFichas().get(j));
									loteria.setFicha(j, fichaAux);
								}
					fichaGanador = loteria.getFicha(0);
					loteria.setFichaGanador(fichaGanador);
					Main.data.setLoteria(loteria);
					//System.out.println("::AFTER:: ID: "+fichaGanador.getNumero()+" \t NOMBRE: "+fichaGanador.getNombre());
						refresh();
				}
			}
		});
		btnOrdenarPorMenor.setIcon(new ImageIcon(Sorteo.class.getResource("/sorteo/graph.png")));
		
		JLabel lblNewLabel = new JLabel("Realiza el Sorteo de manera aleatoria.");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblNewLabel_1 = new JLabel("Selecciona la ficha que genere m\u00E1s ganancias a la empresa.");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 545, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(btnOrdenarAleatoriamente, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnOrdenarPorMenor, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_1)
								.addComponent(lblNewLabel))))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
					.addGap(7)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnOrdenarAleatoriamente)
						.addComponent(lblNewLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnOrdenarPorMenor)
						.addComponent(lblNewLabel_1)))
		);
		
		table = new JTable();
		table.setEnabled(false);
		table.setFillsViewportHeight(true);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"N\u00FAmero", "Ficha", "Tickets", "Apostado", "Pago Total"
			}
		));
		scrollPane.setViewportView(table);
		panel.setLayout(gl_panel);
		setLayout(groupLayout);

	}
	public void refresh(){
		DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
		defaultTableModel.setRowCount(0);
		for (Ficha ficha: data.getLoteria().getFichas()){
			defaultTableModel.addRow(new Object[1]);
			defaultTableModel.setValueAt(ficha.getNumero(), defaultTableModel.getRowCount()-1, 0);
			defaultTableModel.setValueAt(ficha.getNombre(), defaultTableModel.getRowCount()-1, 1);
			defaultTableModel.setValueAt(data.cantidadTickets(ficha.getNumero()), defaultTableModel.getRowCount()-1, 2);
			defaultTableModel.setValueAt(data.totalApostado(ficha.getNumero()), defaultTableModel.getRowCount()-1, 3);
			defaultTableModel.setValueAt(data.getLoteria().getGanancia()*data.totalApostado(ficha.getNumero()), defaultTableModel.getRowCount()-1, 4);
		}
		table.setModel(defaultTableModel);

		if (fichaGanador.getNombre().length() != 0 || Main.data.getLoteria().getFichaGanador().getNombre().length() != 0){
			Loteria loteria = Main.data.getLoteria();
			if (fichaGanador.getNombre().length() != 0)
				loteria.setFichaGanador(fichaGanador);
			Main.data.setLoteria(loteria);
			
			textField.setText(Main.data.getLoteria().getFichaGanador().getNumero()+"-"+Main.data.getLoteria().getFichaGanador().getNombre());
			double total_apostado = 0;
			for(Ficha ficha: data.getLoteria().getFichas()){
				if (ficha.getNumero() != fichaGanador.getNumero()){
					total_apostado+=data.totalApostado(ficha.getNumero());
				}
			}
			Apuesta apuesta = new Apuesta();
			for (int i=0; i<data.getApuestas().size(); i++){
				//System.out.println("Ficha: "+data.getApuestas().get(i).getFicha().getNumero()+" \t Ganador: "+fichaGanador.getNumero());
				if (!data.getApuestas().get(i).getEstado().equals("::PAGADO::")){
					apuesta = data.getApuestas().get(i);
					if (data.getApuestas().get(i).getFicha().getNumero() == fichaGanador.getNumero()){
						apuesta.setEstado("::GANADOR::");
					}
					else{
						apuesta.setEstado("PERDEDOR");
					}
					data.setApuesta(i, apuesta);
					
				}
			}
			spinner.setValue(data.cantidadTickets(fichaGanador.getNumero()));
			textField_1.setText(data.getLoteria().getGanancia()*data.totalApostado(fichaGanador.getNumero())+"");
			textField_2.setText(total_apostado+"");
		}
	}
	public Data getData() {
		return data;
	}
	public void setData(Data data) {
		this.data = data;
	}
}
