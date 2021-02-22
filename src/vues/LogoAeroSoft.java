
package vues;

import javax.swing.ImageIcon;

import java.awt.Image;
import java.awt.Toolkit;

public class LogoAeroSoft {
    private ImageIcon logoAerosoft;


    public ImageIcon getLogoAerosoft() {
        return this.logoAerosoft;
    }

    public void setLogoAerosoft(ImageIcon logoAerosoft) {
        this.logoAerosoft = logoAerosoft;
    }

    public LogoAeroSoft() {
        	this.logoAerosoft = new ImageIcon(
			Toolkit.getDefaultToolkit().getImage(
				LogoAeroSoft.class.getResource("/images/Aerosoft-logo.PNG"))
					.getScaledInstance(160, 40, Image.SCALE_DEFAULT)
				);
    }
}
