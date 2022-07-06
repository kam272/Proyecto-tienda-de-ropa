
package MODEL;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import javax.swing.border.Border;

/**
 *
 * @author Usuario
 */
public class claseFondo implements Border {
    private final BufferedImage image;

    public claseFondo(BufferedImage image) {
        this.image = image;
    }
    
     @Override
    public void paintBorder (Component c, Graphics g, int x, int y, int width,int height){
        int x0 = x + (width - image.getWidth());
        int y0 = y + (height - image.getHeight());
        g.drawImage(image, 0, 0,null);
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets (0,0,0,0);
          }

    @Override
    public boolean isBorderOpaque() {
        return true;
        }
    
}
