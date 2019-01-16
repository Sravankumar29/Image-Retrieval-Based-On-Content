package p2;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.imageio.ImageIO;

public class ImageList {

    private JPanel gui;
    private JFileChooser fileChooser;
    FilenameFilter fileNameFilter;
    private JMenuBar menuBar;
    DefaultListModel model; 

    ImageList() {
        gui = new JPanel(new GridLayout());

        JPanel imageViewContainer = new JPanel(new GridBagLayout());
        final JLabel imageView = new JLabel();
        imageViewContainer.add(imageView);

        model = new DefaultListModel(); 
        final JList imageList = new JList(model);
        imageList.setCellRenderer(new IconCellRenderer());
        ListSelectionListener listener = new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent lse) {
                Object o = imageList.getSelectedValue();
                if (o instanceof BufferedImage) {
                    imageView.setIcon(new ImageIcon((BufferedImage)o));
                }
            }

        };
        imageList.addListSelectionListener(listener);

        fileChooser = new JFileChooser();
        String[] imageTypes = ImageIO.getReaderFileSuffixes();
        FileNameExtensionFilter fnf = new FileNameExtensionFilter("Images", imageTypes);
        fileChooser.setFileFilter(fnf);
        File userHome = new File(System.getProperty("user.home"));
        fileChooser.setSelectedFile(userHome);



        fileNameFilter = new FilenameFilter() {
            @Override 
            public boolean accept(File file, String name) {
                return true;
            }
        };

        menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        menuBar.add(menu);
        JMenuItem browse = new JMenuItem("Browse");
        menu.add(browse);
        browse.addActionListener(new ActionListener(){
            @Override 
            public void actionPerformed(ActionEvent ae) {
                int result = fileChooser.showOpenDialog(gui);
                if (result==JFileChooser.APPROVE_OPTION) {
                    File eg = fileChooser.getSelectedFile();
                    // this will be an image, we want the parent directory
                    File dir = eg.getParentFile();
                    try {
                        loadImages(dir);
                    } catch(Exception e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(
                                gui, 
                                e, 
                                "Load failure!", 
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        gui.add(new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT, 
                new JScrollPane(
                        imageList, 
                        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
                        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), 
                new JScrollPane(imageViewContainer)));
    }

    public void loadImages(File directory) throws IOException {
        File[] imageFiles = directory.listFiles(fileNameFilter);
        BufferedImage[] images = new BufferedImage[imageFiles.length];
        model.removeAllElements();
        for (int ii=0; ii<images.length; ii++) {
            model.addElement(ImageIO.read(imageFiles[ii]));
        }
    }

    public Container getGui() {
        return gui;
    }

    public JMenuBar getMenuBar() {
        return menuBar;
    }

    public void OutputDisplay() {
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                ImageList imageList = new ImageList();

                JFrame f = new JFrame("Output Browser");
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                f.add(imageList.getGui());
                f.setJMenuBar(imageList.getMenuBar());
                f.setLocationByPlatform(true);
                f.pack();
                f.setSize(800,600);
                f.setVisible(true);
            }
        });
    }
}

class IconCellRenderer extends DefaultListCellRenderer {

    private static final long serialVersionUID = 1L;

    private int size;
    private BufferedImage icon;

    IconCellRenderer() {
        this(100);
    }

    IconCellRenderer(int size) {
        this.size = size;
        icon = new BufferedImage(size,size,BufferedImage.TYPE_INT_ARGB);
    }

    @Override
    public Component getListCellRendererComponent(
            JList list, 
            Object value, 
            int index, 
            boolean isSelected, 
            boolean cellHasFocus) {
        Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (c instanceof JLabel && value instanceof BufferedImage) {
            JLabel l = (JLabel)c;
            l.setText("");
            BufferedImage i = (BufferedImage)value;
            l.setIcon(new ImageIcon(icon));

            Graphics2D g = icon.createGraphics();
            g.setColor(new Color(0,0,0,0));
            g.clearRect(0, 0, size, size);
            g.drawImage(i,0,0,size,size,this);

            g.dispose();
        }
        return c;
    }

    @Override 
    public Dimension getPreferredSize() {
        return new Dimension(size, size);
    }
}
