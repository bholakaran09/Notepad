import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class Notepad extends JFrame implements ActionListener {
    JTextArea area;
    JScrollPane scrollPane;
    String filename;

    public Notepad() {
        area = new JTextArea();
        scrollPane = new JScrollPane(area);
        add(scrollPane);
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem newMenuItem = new JMenuItem("New");
        JMenuItem openMenuItem = new JMenuItem("Open");
        JMenuItem saveMenuItem = new JMenuItem("Save");
        JMenuItem exitMenuItem = new JMenuItem("Exit");

        newMenuItem.addActionListener(this);
        openMenuItem.addActionListener(this);
        saveMenuItem.addActionListener(this);
        exitMenuItem.addActionListener(this);

        fileMenu.add(newMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        setSize(500, 500);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();

        if (s.equals("New")) {
            area.setText("");
            filename = "";
        } else if (s.equals("Open")) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                filename = file.getAbsolutePath();
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(filename));
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        area.append(line + "\n");
                    }
                    reader.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } else if (s.equals("Save")) {
            if (filename == null || filename.equals("")) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showSaveDialog(this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    filename = fileChooser.getSelectedFile().getAbsolutePath();
                }
            }
            try {
                FileWriter writer = new FileWriter(filename);
                writer.write(area.getText());
                writer.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else if (s.equals("Exit")) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new Notepad();
    }
}
