import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.MouseWheelEvent;

public class EnCircler extends JFrame{

    private int X = 0;
    private int Y = 0;
    private BufferedImage originalImage;
    private int type;
    private JLabel image_into_lable;
    private ImageIcon circle_image_icon;
    private int width;
    private int height;
    private int ratio;

    public EnCircler(){
        JFrame frame = new JFrame("EnCircler");
        String fileName = "encircle_red_a_1.png";
        try
        {
            originalImage = ImageIO.read(new File("a/"+fileName));
            width = originalImage.getWidth();
            height = originalImage.getHeight();
            type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
        }
        catch(IOException e) {
            System.out.println(e.getMessage());
        }
        BufferedImage resizeImageJpg = resizeImage(originalImage, type, width/8, (width/8)/2);
        width = resizeImageJpg.getWidth();
        height = resizeImageJpg.getHeight();
        System.out.println("Image width: " +width);
        System.out.println("Image heigth: " +height);
        circle_image_icon = new ImageIcon(resizeImageJpg);
        image_into_lable = new JLabel(circle_image_icon);
        frame.setUndecorated(true);
        //frame.setBackground(new Color(100, 100, 100, 100));
        frame.setBackground(new Color(0, 0, 0, 0));
        frame.setAlwaysOnTop(true);
        /*frame.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.NORTHWEST;*/
        //Panel take up all available space
        //c.weightx = 1;
        //c.weighty = 1;
        //frame.getContentPane().add(image_into_lable, c);
        frame.getContentPane().add(image_into_lable);
        frame.pack();
        frame.setSize(708, 348);
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0); // An Exit Listener
            }
        });
        frame.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent e)
            {
                X = e.getX();
                Y = e.getY();
                System.out.println("The (X,Y) coordinate of window is ("
                        + X + "," + Y + ")");
            }
            @Override
            public void mouseClicked(MouseEvent e)
            {
                if (e.getClickCount() == 1) {
                    frame.dispose();
                    System.exit(0);
                }

                if (e.getClickCount() == 3) {

                }
            }
        });
        frame.addMouseMotionListener(new MouseMotionAdapter() {

            public void mouseDragged(MouseEvent e) {
                frame.setLocation(frame.getLocation().x + (e.getX() - X),
                        frame.getLocation().y + (e.getY() - Y));
                /*System.out.println("Pressed X: "+X);
                System.out.println("Pressed Y: "+Y);
                System.out.println("Dragged X: "+e.getX());
                System.out.println("Dragged Y: "+e.getY());
                System.out.println("Frame Location X: "+frame.getLocation().x);
                System.out.println("Frame Location Y: "+frame.getLocation().y);
                System.out.println("New Frame Location X: "+frame.getLocation().x + (e.getX() - X));
                System.out.println("New Frame Location Y: "+frame.getLocation().y + (e.getY() - Y));*/
            }

        });
        frame.addMouseWheelListener(new MouseAdapter() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                //String message;
                frame.getContentPane().removeAll();
                int notches = e.getWheelRotation();
                if (notches < 0) {

                    width = width + 10;
                    //message = "Mouse wheel moved UP "
                    //        + -notches + " notch(es)";
                } else {
                    width = width - 10;
                    //message = "Mouse wheel moved DOWN "
                    //        + notches + " notch(es)";
                }
                BufferedImage resizeImageJpg = resizeImage(originalImage, type, width, width/2);
                width = resizeImageJpg.getWidth();
                circle_image_icon = new ImageIcon(resizeImageJpg);
                image_into_lable = new JLabel(circle_image_icon);
                frame.getContentPane().add(image_into_lable);
                frame.setVisible(true);
                System.out.println(width);
            }
        });

        //frame.setLocationRelativeTo(null);
    }
    //Method to resizeImage
    private static BufferedImage resizeImage(BufferedImage originalImage, int type,
                                             int img_width, int img_height)
    {
        BufferedImage resizedImage = new BufferedImage(img_width, img_height, type);
        Graphics2D g = resizedImage.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g.drawImage(originalImage, 0, 0, img_width, img_height, null);
        g.dispose();
        return resizedImage;
    }
    public static void main (String[] args){
        EnCircler enCircler = new EnCircler();
    }
}
