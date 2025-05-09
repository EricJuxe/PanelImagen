package panelimagen;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PanelImagen extends JPanel {

    public  Image imagenOriginal;
    
    

    public PanelImagen(String rutaImagen) {
        cargarImagen(rutaImagen);
    }

    public PanelImagen() {
        // Constructor vacío si se desea usar setRutaImagen después
    }

    public void setRutaImagen(String rutaImagen) {
        cargarImagen(rutaImagen);
        repaint(); // Redibujar con la nueva imagen
    }

    public void cargarImagen(String rutaImagen) {
        URL ruta = getClass().getResource(rutaImagen);
        if (ruta == null) {
            System.err.println("No se encontró la imagen: " + rutaImagen);
            return;
        }

        ImageIcon iconoOriginal = new ImageIcon(ruta);
        imagenOriginal = iconoOriginal.getImage();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagenOriginal != null) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

            int panelWidth = getWidth();
            int panelHeight = getHeight();
            int imgWidth = imagenOriginal.getWidth(null);
            int imgHeight = imagenOriginal.getHeight(null);

            double escala = Math.min((double) panelWidth / imgWidth, (double) panelHeight / imgHeight);
            int anchoEscalado = (int) (imgWidth * escala);
            int altoEscalado = (int) (imgHeight * escala);

            int x = (panelWidth - anchoEscalado) / 2;
            int y = (panelHeight - altoEscalado) / 2;

            g2.drawImage(imagenOriginal, x, y, anchoEscalado, altoEscalado, this);
        }
    }
}
