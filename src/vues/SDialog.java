package vues;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

import java.awt.Toolkit;
import java.awt.Dimension;

public class SDialog extends JDialog {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private final JPanel contentPanel = new JPanel();

	private Boolean validation;


	/**
	 * @return Boolean
	 */
	public Boolean getValidation() {
		return validation;
	}

	/*
	 * public interface getDialogResponse{ boolean getResponse(); }
	 */

	/**
	 * Create the dialog.
	 */
	public SDialog(String titre, String textDuDialogue, String textDuBoutonOUI,
			String textDuBoutonNon) {

		setFont(new Font("Dialog", Font.PLAIN, 15));
		setTitle(titre);

		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		JTextPane txtpnCeciEstUn = new JTextPane();

		txtpnCeciEstUn.setFont(new Font("Dialog", Font.PLAIN, 18));
		txtpnCeciEstUn.setText(textDuDialogue);
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(txtpnCeciEstUn, GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE));
		gl_contentPanel
				.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel
								.createSequentialGroup().addComponent(txtpnCeciEstUn,
										GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
								.addContainerGap()));
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton(textDuBoutonOUI);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						validation = true;
						dispose();
					}
				});
				okButton.setActionCommand(textDuBoutonOUI);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton(textDuBoutonNon);
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						validation = false;
						dispose();
					}
				});
				cancelButton.setActionCommand(textDuBoutonNon);
				if (textDuBoutonNon != "") {
					buttonPane.add(cancelButton);
				}
			}
		}

		final Toolkit toolkit = Toolkit.getDefaultToolkit();
		final Dimension screenSize = toolkit.getScreenSize();
		final int x = (screenSize.width - this.getWidth()) / 2;
		final int y = (screenSize.height - this.getHeight()) / 2;
		setLocation(x, y);
		setLocationRelativeTo(null);

	}


	/**
	 * @param b
	 */
	protected void getResponse(boolean b) {
		// TODO Auto-generated method stub

	}
}
