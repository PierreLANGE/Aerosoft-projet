package vues;

import javax.swing.JFrame;

import javax.swing.JPanel;

import javax.swing.border.EmptyBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import Interfaces.Dao;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.util.Properties;

import java.awt.event.WindowListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class FicheAffectation extends JFrame implements WindowListener {

	private static final long serialVersionUID = 1L;


	private JPanel contentPane;

	ImageIcon i = new LogoAeroSoft().getLogoAerosoft();

	private JLabel lblNewTitre;

	private JLabel lblNewID;

	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2 = new JLabel("");
	private JLabel lblNewLabel_3 = new JLabel("");
	private JLabel lblNewLabel_4 = new JLabel("");
	private JLabel lblNewLabel_5 = new JLabel("");
	private JLabel lblNewLabel_6 = new JLabel("");

	private JTextField textField_1;

	private JComboBox<String> jComboBoxPilotes;
	private JComboBox<String> jComboBoxNumVols;
	private JComboBox<String> jComboBoxNumAvions;

	private JCheckBox affectationCode = new JCheckBox();

	private JDatePickerImpl datePicker;

	private String oldValue_1;
	private String oldValue_2;
	private String oldValue_3;
	private String oldValue_4;
	private String oldValue_5;
	private String oldValue_6;

	private String jFrameClassName;

	/**
	 * Create the frame.
	 */
	public FicheAffectation(
			String titre,
			Dao dao,
			Object transport,
			String[] listLabels,
			String[] listTextFields,
			String[] listTextBtns,
			String[] listMethodeDoa,
			String[] jComboBoxPilotesTitles,
			String[] jComboBoxNumVolsTitles,
			String[] jComboBoxNumAvionsTitles, 
			String jFrameClassName
		) {

		this.jFrameClassName = jFrameClassName;
		addWindowListener(this);

		setTitle(titre);

		lblNewTitre = new JLabel(titre);
		lblNewTitre.setForeground(Color.blue);
		lblNewTitre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewTitre.setIcon(i);
		lblNewTitre.setBounds(1, 100, 30, 50);

		setFont(new Font("Dialog", Font.PLAIN, 15));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 520, 450);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		lblNewID = new JLabel("");

		lblNewLabel_1 = new JLabel(listLabels[0]);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));

		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_1.setColumns(10);
		textField_1.setText(listTextFields[0]);
		textField_1.setEnabled(false);

		oldValue_1 = listTextFields[0];

		if (listLabels.length >= 2) {

			lblNewLabel_2 = new JLabel(listLabels[1]);
			lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));

			jComboBoxNumVols = new JComboBox<String>(jComboBoxNumVolsTitles);
			jComboBoxNumVols.setFont(new Font("Tahoma", Font.PLAIN, 15));
			jComboBoxNumVols.setSelectedItem(listTextFields[1]);

			oldValue_2 = listTextFields[1];
		}

		if (listLabels.length >= 3) {

			lblNewLabel_3 = new JLabel(listLabels[2]);
			lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));

			UtilDateModel model = new UtilDateModel();

			if (!listTextFields[2].equals("")) {
				String[] parts = listTextFields[2].split("-");
				model.setDate(Integer.parseInt(parts[0]), (Integer.parseInt(parts[1]) - 1),
						Integer.parseInt(parts[2]));
				model.setSelected(true);
			}
			Properties p = new Properties();
			p.put("text.today", "Today");
			p.put("text.month", "Month");
			p.put("text.year", "Year");

			JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
			datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
			oldValue_3 = listTextFields[2];
		}

		if (listLabels.length >= 4) {

			lblNewLabel_4 = new JLabel(listLabels[3]);
			lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));

			affectationCode.setSelected(Boolean.parseBoolean(listTextFields[3]));
			oldValue_4 = listTextFields[3];
		}

		if (listLabels.length >= 5) {

			lblNewLabel_5 = new JLabel(listLabels[4]);
			lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 15));

			jComboBoxNumAvions = new JComboBox<String>(jComboBoxNumAvionsTitles);
			jComboBoxNumAvions.setFont(new Font("Tahoma", Font.PLAIN, 15));
			jComboBoxNumAvions.setSelectedItem(listTextFields[4]);

			oldValue_5 = listTextFields[4];
		}

		if (listLabels.length >= 6) {

			lblNewLabel_6 = new JLabel(listLabels[5]);
			lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 15));

			oldValue_6 = listTextFields[5];

			jComboBoxPilotes = new JComboBox<String>(jComboBoxPilotesTitles);
			jComboBoxPilotes.setFont(new Font("Tahoma", Font.PLAIN, 15));
			jComboBoxPilotes.setSelectedItem(listTextFields[5]);
		}

		JButton btn_2 = new JButton(listTextBtns[0]);
		btn_2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				java.util.Date selectedDate = (java.util.Date) datePicker.getModel().getValue();

				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String strDate = formatter.format(selectedDate);

				if (!(oldValue_1.equals(textField_1.getText()))
						|| (oldValue_2 != null
								&& !(oldValue_2.equals(jComboBoxNumVols.getSelectedItem())))
						|| (oldValue_3 != null && !(oldValue_3.equals(selectedDate.toString())))
						|| (oldValue_4 != null && !(oldValue_4.equals(affectationCode.getText())))
						|| (oldValue_5 != null
								&& !(oldValue_5.equals(jComboBoxNumAvions.getSelectedItem())))
						|| (oldValue_6 != null
								&& !(oldValue_6.equals(jComboBoxPilotes.getSelectedItem())))) {

					String[] params = new String[listTextFields.length];
					System.out.println("listTextFields.length : " + listTextFields.length);

					params[0] = textField_1.getText();

					if (listTextFields.length >= 2) {
						params[1] = (String) jComboBoxNumVols.getSelectedItem();
					}

					if (listTextFields.length >= 3) {
						params[2] = strDate;
						System.out.println("selectedDate :" + params[2]);

					}

					if (listLabels.length >= 4) {
						params[3] = "" + affectationCode.isSelected();
					}

					if (listLabels.length >= 5) {
						params[4] = (String) jComboBoxNumAvions.getSelectedItem();
					}

					if (listLabels.length >= 6) {
						params[5] = (String) jComboBoxPilotes.getSelectedItem();
					}

					System.out.println("params.length : " + params.length);

					executeDoa(listMethodeDoa[0], dao, transport, params);

				}
				dispose();

			}

		});

		btn_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		btn_2.setBackground(new Color(22, 219, 170));
		btn_2.setForeground(Color.WHITE);

		JButton btn_1 = new JButton(listTextBtns[1]);
		btn_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				executeDoa(listMethodeDoa[1], dao, transport, null);
				dispose();
			}
		});
		btn_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		btn_1.setBackground(new Color(1, 175, 228));
		btn_1.setForeground(Color.WHITE);

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup().addGroup(gl_contentPane
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
								.addComponent(lblNewTitre, GroupLayout.PREFERRED_SIZE, 400,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 259, Short.MAX_VALUE)
								.addComponent(lblNewID, GroupLayout.PREFERRED_SIZE, 74,
										GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
								.addComponent(btn_1).addGap(18).addComponent(btn_2))
						.addGroup(gl_contentPane.createSequentialGroup().addGap(23)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane
												.createParallelGroup(Alignment.LEADING, false)
												.addComponent(lblNewLabel_3,
														GroupLayout.DEFAULT_SIZE, 150,
														Short.MAX_VALUE)
												.addComponent(lblNewLabel_2,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(lblNewLabel_1,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE,
												150, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_5).addComponent(lblNewLabel_6))
								.addPreferredGap(ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
								.addGroup(gl_contentPane
										.createParallelGroup(Alignment.LEADING, false)
										.addComponent(jComboBoxNumAvions, Alignment.TRAILING)
										.addComponent(affectationCode, Alignment.TRAILING)
										.addComponent(datePicker).addComponent(jComboBoxNumVols)
										.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 259,
												Short.MAX_VALUE)
										.addComponent(jComboBoxPilotes))))
						.addGap(46)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addGroup(gl_contentPane
						.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup().addComponent(lblNewTitre)
								.addGap(18)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(lblNewLabel_1).addGap(18)
												.addComponent(lblNewLabel_2).addGap(30)
												.addGroup(gl_contentPane
														.createParallelGroup(Alignment.BASELINE)
														.addComponent(lblNewLabel_3)
														.addComponent(datePicker,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)))
										.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(textField_1,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)
												.addGap(18).addComponent(jComboBoxNumVols,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)))
								.addGap(18)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel_4).addComponent(affectationCode,
												GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)))
						.addComponent(lblNewID)).addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_5)
								.addComponent(jComboBoxNumAvions, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(jComboBoxPilotes, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_6))
						.addPreferredGap(ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btn_2).addComponent(btn_1))
						.addGap(32)));
		contentPane.setLayout(gl_contentPane);
		lblNewID.setVisible(false);

		if (listTextFields.length >= 2) {
			jComboBoxNumVols.setVisible(true);
		} else {
			jComboBoxNumVols.setVisible(false);
		}

		if (listTextFields.length >= 3) {
			datePicker.setVisible(true);
		} else {
			datePicker.setVisible(false);
		}

		if (listTextFields.length >= 4) {
			affectationCode.setVisible(true);
		} else {
			affectationCode.setVisible(false);
		}

		if (listTextFields.length >= 5) {
			jComboBoxNumAvions.setVisible(true);
		} else {
			jComboBoxNumAvions.setVisible(false);
		}

		if (listTextFields.length >= 6) {
			jComboBoxPilotes.setVisible(true);
		} else {
			jComboBoxPilotes.setVisible(false);
		}

		final Toolkit toolkit = Toolkit.getDefaultToolkit();
		final Dimension screenSize = toolkit.getScreenSize();
		final int x = (screenSize.width - this.getWidth()) / 2;
		final int y = (screenSize.height - this.getHeight()) / 2;

		setLocation(x, y);
		setLocationRelativeTo(null);
		setVisible(true);
	}


	/**
	 * @param methodeDao
	 * @param dao
	 * @param transport
	 * @param params
	 */
	private void executeDoa(String methodeDao, Dao dao, Object transport, String[] params) {

		System.out.println("methodeDao: " + methodeDao);
		switch (methodeDao) {
			case "get":
				dao.get(transport);
				break;

			case "save":
				dao.save(transport, params);
				break;

			case "update":
				dao.update(transport, params);
				break;

			case "delete":
				dao.delete(transport);
				break;

			default:;
		}
	}


	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}


	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub

	}


	@Override
	public void windowClosed(WindowEvent e) {
		if (!jFrameClassName.equals("")) {

			new SDialog("Modification", "Modification reussie", "Valider", "").setVisible(true);

			Class<?> jFramClass = null;

			try {

				jFramClass = Class.forName(jFrameClassName);

			} catch (ClassNotFoundException e1) {

				e1.printStackTrace();
			}

			// convert string classname to class
			Object jFram = new Object();

			try {

				jFram = jFramClass.getDeclaredConstructor().newInstance();

				String methodName = "chargeData";
				Method setNameMethod = jFram.getClass().getMethod(methodName);
				setNameMethod.invoke(jFram);

			} catch ( ClassCastException | InstantiationException | IllegalAccessException | IllegalArgumentException
			| InvocationTargetException | NoSuchMethodException | SecurityException e1) {

				e1.printStackTrace();
			}			
		}

	}


	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}


	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}


	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}


	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}
}
