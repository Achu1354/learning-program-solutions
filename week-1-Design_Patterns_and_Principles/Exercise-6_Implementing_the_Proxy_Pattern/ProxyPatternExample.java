import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.URL;

public class ProxyPatternExample {

    interface Image {
        void display();
    }

    static class RealImage implements Image {
        private String path;
        private ImageIcon imageIcon;

        public RealImage(String path) {
            this.path = path;
            loadImage();
        }

        private void loadImage() {
            try {
                if (path.startsWith("http")) {
                    imageIcon = new ImageIcon(new URL(path));
                    System.out.println("Loading image from remote URL: " + path);
                } else {
                    File file = new File(path);
                    if (!file.exists()) throw new RuntimeException("File not found: " + path);
                    imageIcon = new ImageIcon(path);
                    System.out.println("Loading image from local file: " + path);
                }
            } catch (Exception e) {
                System.out.println("Error loading image: " + e.getMessage());
            }
        }

        @Override
        public void display() {
            if (imageIcon != null) {
                JFrame frame = new JFrame("Image Viewer");
                JLabel label = new JLabel(imageIcon);
                frame.add(label);
                frame.pack();
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setVisible(true);
                System.out.println("Displaying image: " + path);
            } else {
                System.out.println("Image not loaded properly.");
            }
        }
    }

    static class ProxyImage implements Image {
        private String path;
        private RealImage realImage;

        public ProxyImage(String path) {
            this.path = path;
        }

        @Override
        public void display() {
            if (realImage == null) {
                realImage = new RealImage(path); 
            } else {
                System.out.println("Image loaded from cache: " + path);
            }
            realImage.display();
        }
    }

    public static void main(String[] args) {
        Image image1 = new ProxyImage("sample.png"); 
        Image image2 = new ProxyImage("https://via.placeholder.com/300");

        image1.display();
        image2.display();

        image1.display();
        image2.display();
    }
}
