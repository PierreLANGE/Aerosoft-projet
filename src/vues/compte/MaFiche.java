package vues.compte;

import dao.UtilisateurDao;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import models.Utilisateur;
import vues.Fiche;

public class MaFiche {

  public MaFiche(int idUser) {
    JFrame meFrame = new JFrame();
    JLabel labelEmail, labelPassword;
    JTextField textFieldId, textFieldLogin, textFieldStatut, textFieldRole;
    JButton btnModifier;
    JPasswordField passwordField;

    Utilisateur utilisateur;
    UtilisateurDao utilisateurDao = new UtilisateurDao();
    utilisateur = (Utilisateur) utilisateurDao.get(idUser);

    JLabel labelimage = new JLabel();
    ImageIcon img = new ImageIcon(
      Toolkit
        .getDefaultToolkit()
        .getImage(Fiche.class.getResource("/images/Aerosoft-logo.PNG"))
        .getScaledInstance(160, 50, Image.SCALE_DEFAULT)
    );
    
    labelimage.setBounds(80, 10, 400, 70);
    labelimage.setIcon(img);
    meFrame.add(labelimage);

    labelEmail = new JLabel("Votre Email:");
    labelEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
    labelEmail.setBounds(80, 90, 200, 30);

    labelPassword = new JLabel("Votre Mot de passe:");
    labelPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
    labelPassword.setBounds(80, 130, 200, 30);

    textFieldId = new JTextField("" + utilisateur.getIdUtilisateur());
    textFieldId.setVisible(false);

    textFieldLogin = new JTextField(utilisateur.getMail());
    textFieldLogin.setFont(new Font("Tahoma", Font.PLAIN, 15));
    textFieldLogin.setBounds(300, 90, 200, 30);

    /* Mot de passe*/
    passwordField = new JPasswordField(utilisateur.getMotDePasse());
    passwordField.setFont(new Font("Tahoma", Font.PLAIN, 15));
    passwordField.setBounds(300, 130, 200, 30);

    textFieldStatut = new JTextField("" + utilisateur.getStatut());
    textFieldStatut.setVisible(false);

    textFieldRole = new JTextField(utilisateur.getIdRole());
    textFieldRole.setVisible(false);

    btnModifier = new JButton("Modifier");
    btnModifier.setBounds(300, 170, 100, 30);
    btnModifier.setFont(new Font("Tahoma", Font.BOLD, 15));
    btnModifier.setBackground(new Color(1, 175, 228));
    btnModifier.setForeground(Color.WHITE);
    btnModifier.addActionListener(
      new ActionListener() {

        public void actionPerformed(ActionEvent eventUpdateUser) {
          String userString[] = new String[5];
          userString[0] = textFieldId.getText();
          userString[1] = textFieldLogin.getText();
          userString[2] = String.valueOf(passwordField.getPassword());
          userString[3] = textFieldStatut.getText();
          userString[4] = textFieldRole.getText();

          utilisateurDao.update(utilisateur, userString);

          meFrame.dispose();
        }
      }
    );

    meFrame.add(labelEmail);
    meFrame.add(textFieldLogin);
    meFrame.add(labelPassword);
    meFrame.add(passwordField);
    meFrame.add(btnModifier);

    meFrame.setTitle("Aerosoft");

    meFrame.setSize(650, 350);
    meFrame.setLayout(null);
    meFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

    final Toolkit toolkit = Toolkit.getDefaultToolkit();
    final Dimension screenSize = toolkit.getScreenSize();
    final int x = (screenSize.width - meFrame.getWidth()) / 2;
    final int y = (screenSize.height - meFrame.getHeight()) / 2;

    meFrame.setLocation(x, y);
    meFrame.setLocationRelativeTo(null);

    meFrame.setVisible(true);
  }
}
