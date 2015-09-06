/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleClock;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.MenuItem;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PopupMenu;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.util.Calendar;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.util.Timer;
import java.util.TimerTask;
import javax.accessibility.AccessibleRole;
import java.util.Date;
import javax.swing.JColorChooser;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
/**
 *
 * @author baptiste
 */



public class simpleClock {

    
    
    
    static class MyFrame extends JFrame{
    
        
        public MyFrame(){
            setSize(350, 175);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            setAlwaysOnTop(true);
            setTitle("timu");
            setLayout(new BorderLayout());
            
            //setType(Window.Type.UTILITY);
            setUndecorated(true);
            addMouseListener(new MouseListener() {

                @Override
                public void mouseClicked(MouseEvent e) {
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    oldX = e.getX();
                    oldY = e.getY();
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                      if(e.isPopupTrigger()){
                        JPopupMenu menu = new JPopupMenu();
                        
                        JMenuItem col = new JMenuItem("font color");
                        col.addActionListener(new ActionListener(){
                            
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                JColorChooser c =new JColorChooser();
                                //TODO: flemme + sérialisation de la couleur
                                
                            }
                            
                        });
                        menu.add(col);
                        
                        
                        JMenuItem it = new JMenuItem("exit");
                        it.addActionListener(new ActionListener() {
                             
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                System.exit(0);
                            }
                        });
                        menu.add(it);
                        menu.show(frame, e.getX(), e.getY());
                    }
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                }

                @Override
                public void mouseExited(MouseEvent e) {
                }
            });
                
            this.addMouseMotionListener(new MouseMotionAdapter() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    super.mouseDragged(e); //To change body of generated methods, choose Tools | Templates.
                    frame.setLocation(frame.getX() + e.getX() - oldX, frame.getY() + e.getY() -oldY);
                }
            });
        
            
            
            //setOpacity(0.3f);
            setVisible(true);
            
        }
        
            

            
    }
    
    public static JLabel label;
    public static MyFrame frame;
    public static int oldX, oldY;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        frame = new MyFrame();
       
        
        label = new JLabel("timuuuu");
        Timer t = new Timer("uptime",true);
        
      
        
        t.schedule(new TimerTask() {

            @Override
            public void run() {
                Date d = new Date();
               label.setText( twoDigits(d.getHours()) + ":" + twoDigits(d.getMinutes()) + ":" + twoDigits(d.getSeconds()));
               correctSize();
            }
        }, 0, 1000);
        
        frame.add(label,BorderLayout.CENTER);
        Color c = new Color(0, 0, 0, 0.01f);
        frame.setBackground(c);
        label.setBackground(c);
    }
    
    
    

    
    
    
    public static String twoDigits(long i){
        String ret ="";
        if( i < 10){
            ret =  "0" ;
        }
        return ret + Long.toString(i);
    }
    
    public static void correctSize(){
        //Thanks stackoverflow :)
        Font labelFont = label.getFont();
        String labelText = label.getText();

        int stringWidth = label.getFontMetrics(labelFont).stringWidth(labelText);
        int componentWidth = label.getWidth();

        // Find out how much the font can grow in width.
        double widthRatio = (double)componentWidth / (double)stringWidth;

        int newFontSize = (int)(labelFont.getSize() * widthRatio);
        int componentHeight = label.getHeight();

        // Pick a new font size so it will not be larger than the height of label.
        int fontSizeToUse = Math.min(newFontSize, componentHeight);

        // Set the label's font size to the newly determined size.
        label.setFont(new Font(labelFont.getName(), Font.PLAIN, fontSizeToUse));
        
    }
    
}
