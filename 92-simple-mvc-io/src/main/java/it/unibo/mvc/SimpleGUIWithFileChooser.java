package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {
    private static final String TITLE = "My first Java graphical interface";
    private static final int PROPORTION = 5;

    private final Controller control = new Controller();
    private final JFrame frame = new JFrame(TITLE);

    /**
     * Building a simple interface for the user.
     */
    public SimpleGUIWithFileChooser() {
        final JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());
        final JTextArea text = new JTextArea();
        canvas.add(text);
        final JButton save = new JButton("Save");
        canvas.add(save, BorderLayout.SOUTH);
        frame.setContentPane(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JPanel browsePanel = new JPanel();
        browsePanel.setLayout(new BorderLayout());
        canvas.add(browsePanel, BorderLayout.NORTH);
        final JTextField browseText = new JTextField(control.getPath());
        browseText.setEditable(false);
        browsePanel.add(browseText, BorderLayout.CENTER);
        final JButton browseButton = new JButton("Browse...");
        browsePanel.add(browseButton, BorderLayout.LINE_END);
        /*
         * Handlers
         */
        save.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent ignored) {
                    try {
                        control.writeFile(text.getText());
                    } catch (final IOException e) {
                        JOptionPane.showMessageDialog(frame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        );

        browseButton.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent ignored) {
                    final JFileChooser fileChoice = new JFileChooser();
                    final int choice = fileChoice.showSaveDialog(browseButton);
                    if (choice == JFileChooser.APPROVE_OPTION) {
                        control.setFile(fileChoice.getSelectedFile());
                        browseText.setText(control.getPath());
                    } else if (choice != JFileChooser.CANCEL_OPTION) {
                        JOptionPane.showMessageDialog(frame, "An error occurred");
                    }
                }
            }
        );
    }

    /**
     * This method shows the GUI on screen.
     */
    public void display() {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);
        frame.setVisible(true);
    }

    /**
     * Starting the interface.
     * 
     * @param args Argument passed by control line.
     */
    public static void main(final String[] args) {
        new SimpleGUIWithFileChooser().display();
    }
}
