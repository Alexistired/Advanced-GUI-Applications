import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NoteTaker extends JFrame {
    // Constants for set up of the note taking area
    public static final int WIDTH = 600;
    public static final int HEIGHT = 300;
    public static final int LINES = 13;
    public static final int CHAR_PER_LINE = 45;

    // Objects in GUI
    private JTextArea theText; // Text area to take notes
    private JMenuBar mBar; // Horizontal menu bar
    private JPanel textPanel; // Scrolling text area panel
    private JMenu notesMenu; // Vertical menu for notes
    private JMenu viewMenu; // Vertical menu for views
    private JMenu lafMenu; // Vertical menu look and feel
    private JMenu sbMenu; // Vertical menu for scroll bar
    private JScrollPane scrolledText; // Scroll bars

    // Default notes
    private String note1 = "No Note 1.";
    private String note2 = "No Note 2.";

    /**
     * Constructor
     */
    public NoteTaker() {
        // Create a closeable JFrame with a specific size
        super("Note Taker");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Get contentPane and set layout of the window
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        // Creates the vertical menus
        createNotes();
        createViews();

        // Creates horizontal menu bar and
        // adds vertical menus to it
        mBar = new JMenuBar();
        mBar.add(notesMenu);
        mBar.add(viewMenu); // Add viewMenu to the menu bar
        setJMenuBar(mBar);

        // Creates a panel to take notes on
        textPanel = new JPanel();
        textPanel.setBackground(Color.blue);
        theText = new JTextArea(LINES, CHAR_PER_LINE);
        theText.setBackground(Color.white);

        // CREATE A JScrollPane OBJECT HERE CALLED
        // scrolledText AND PASS IN theText, THEN
        // CHANGE THE LINE BELOW BY PASSING IN scrolledText
        scrolledText = new JScrollPane(theText);
        textPanel.add(scrolledText);
        contentPane.add(textPanel, BorderLayout.CENTER);
    }

    /**
     * Creates vertical menu associated with Notes
     * menu item on menu bar.
     */
    public void createNotes() {
        notesMenu = new JMenu("Notes");
        JMenuItem item;
        item = new JMenuItem("Save Note 1");
        item.addActionListener(new MenuListener());
        notesMenu.add(item);
        item = new JMenuItem("Save Note 2");
        item.addActionListener(new MenuListener());
        notesMenu.add(item);
        item = new JMenuItem("Open Note 1");
        item.addActionListener(new MenuListener());
        notesMenu.add(item);
        item = new JMenuItem("Open Note 2");
        item.addActionListener(new MenuListener());
        notesMenu.add(item);
        item = new JMenuItem("Clear");
        item.addActionListener(new MenuListener());
        notesMenu.add(item);
        item = new JMenuItem("Exit");
        item.addActionListener(new MenuListener());
        notesMenu.add(item);
    }

    /**
     * Creates vertical menu associated with Views
     * menu item on the menu bar.
     */
    public void createViews() {
        viewMenu = new JMenu("Views");
        createLookAndFeel(); // Add look and feel submenu
        viewMenu.add(lafMenu);
        createScrollBars(); // Add scroll bars submenu
        viewMenu.add(sbMenu);
    }

    /**
     * Creates the look and feel submenu.
     */
    public void createLookAndFeel() {
        lafMenu = new JMenu("Look and Feel");
        JMenuItem metalItem = new JMenuItem("Metal");
        metalItem.addActionListener(new MenuListener());
        lafMenu.add(metalItem);
        JMenuItem motifItem = new JMenuItem("Motif");
        motifItem.addActionListener(new MenuListener());
        lafMenu.add(motifItem);
        JMenuItem windowsItem = new JMenuItem("Windows");
        windowsItem.addActionListener(new MenuListener());
        lafMenu.add(windowsItem);
    }

    /**
     * Creates the scroll bars submenu.
     */
    public void createScrollBars() {
        sbMenu = new JMenu("Scroll Bars");
        JMenuItem neverItem = new JMenuItem("Never");
        neverItem.addActionListener(new MenuListener());
        sbMenu.add(neverItem);
        JMenuItem alwaysItem = new JMenuItem("Always");
        alwaysItem.addActionListener(new MenuListener());
        sbMenu.add(alwaysItem);
        JMenuItem asNeededItem = new JMenuItem("As Needed");
        asNeededItem.addActionListener(new MenuListener());
        sbMenu.add(asNeededItem);
    }

    /**
     * Private inner class that handles the Menu object's
     * action events.
     */
    private class MenuListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String actionCommand = e.getActionCommand();
            if (actionCommand.equals("Save Note 1"))
                note1 = theText.getText();
            else if (actionCommand.equals("Save Note 2"))
                note2 = theText.getText();
            else if (actionCommand.equals("Clear"))
                theText.setText("");
            else if (actionCommand.equals("Open Note 1"))
                theText.setText(note1);
            else if (actionCommand.equals("Open Note 2"))
                theText.setText(note2);
            else if (actionCommand.equals("Exit"))
                System.exit(0);
            else if (actionCommand.equals("Metal")) {
                try {
                    UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
                    SwingUtilities.updateComponentTreeUI(getContentPane());
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(NoteTaker.this, "Error setting Metal Look and Feel", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else if (actionCommand.equals("Motif")) {
                try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
                    SwingUtilities.updateComponentTreeUI(getContentPane());
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(NoteTaker.this, "Error setting Motif Look and Feel", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else if (actionCommand.equals("Windows")) {
                try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                    SwingUtilities.updateComponentTreeUI(getContentPane());
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(NoteTaker.this, "Error setting Windows Look and Feel", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else if (actionCommand.equals("Never")) {
                scrolledText.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                scrolledText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
            } else if (actionCommand.equals("Always")) {
                scrolledText.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                scrolledText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            } else if (actionCommand.equals("As Needed")) {
                scrolledText.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                scrolledText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            } else
                theText.setText("Error in memo interface");
        }
    }

    /**
     * The main method creates an instance of the
     * NoteTaker class which causes it to display
     * its window.
     */
    public static void main(String[] args) {
        NoteTaker gui = new NoteTaker();
        gui.setVisible(true);
    }
}
