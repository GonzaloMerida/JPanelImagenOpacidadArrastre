/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jPanelImagenOpacidadArrastre;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author profesor
 */
public class JPanelImagenOpacidadArrastre extends JPanel implements Serializable{
    
    private ImagenFondo imagenFondo;
    private boolean ratonPresionado = false;
    private Point puntoPresion;
    private static ArrastreListener arrastreListener;
    
    public JPanelImagenOpacidadArrastre(){    
        this.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e) {
                ratonPresionado = true;
                puntoPresion = e.getPoint();
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {               
                Point posicionActual = e.getPoint();
                if(Math.abs(puntoPresion.x - posicionActual.x) > 50){
                    if(arrastreListener != null){
                        arrastreListener.arrastre();
                    }
                }                  
            ratonPresionado = false;
            }       
        });      
    }
    
    public static void addArrastreListener(ArrastreListener arrastreListener){
        JPanelImagenOpacidadArrastre.arrastreListener = arrastreListener;   
    }
    
    public static void removeArrastreListener(ArrastreListener arrastreListener){
        JPanelImagenOpacidadArrastre.arrastreListener = null;    
    }


    
    
    
    public ImagenFondo getImagenFondo() {
        return imagenFondo;
    }

    public void setImagenFondo(ImagenFondo imagenFondo) {
        this.imagenFondo = imagenFondo;
        repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        
        if(imagenFondo!=null){
            if(imagenFondo.getRutaImagen()!=null && imagenFondo.getRutaImagen().exists()){
                
                ImageIcon imageIcon = new ImageIcon(imagenFondo.getRutaImagen().getAbsolutePath());
                Graphics2D g2d = (Graphics2D)g;

                AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, imagenFondo.getOpacidad());
                g2d.setComposite(ac);
                
                g.drawImage(imageIcon.getImage(), 0,0,null);
                
                AlphaComposite ac2 = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1);
                g2d.setComposite(ac2);
                
                
            }              
        }               
    }
   
}
