package Ex_04;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class FileCopyGUI extends JFrame {
    private JProgressBar progressBar;
    private JButton copyButton;

    public FileCopyGUI() {
        setTitle("File Copy Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 150);
        setLayout(new FlowLayout());

        
        
        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);

        copyButton = new JButton("Copy Files");
        copyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performFileCopy();
            }
        });

        add(progressBar);
        add(copyButton);
    }

    private void performFileCopy() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File sourceFile = fileChooser.getSelectedFile();

            JFileChooser destinationChooser = new JFileChooser();
            destinationChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            int destResult = destinationChooser.showOpenDialog(this);

            if (destResult == JFileChooser.APPROVE_OPTION) {
                File destinationDir = destinationChooser.getSelectedFile();
                File destinationFile = new File(destinationDir, sourceFile.getName());

                try {
                    copyFileWithProgressBar(sourceFile, destinationFile);
                    JOptionPane.showMessageDialog(this, "File copied successfully!");
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error copying file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private void copyFileWithProgressBar(File sourceFile, File destinationFile) throws IOException {
        long fileSize = sourceFile.length();
        progressBar.setValue(0);

        try (InputStream in = new FileInputStream(sourceFile);
             OutputStream out = new FileOutputStream(destinationFile)) {

            byte[] buffer = new byte[8192];
            int bytesRead;
            long totalBytesRead = 0;

            while ((bytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, bytesRead);
                totalBytesRead += bytesRead;

                int progress = (int) ((totalBytesRead * 100) / fileSize);
                SwingUtilities.invokeLater(() -> progressBar.setValue(progress));
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FileCopyGUI fileCopyGUI = new FileCopyGUI();
            fileCopyGUI.setVisible(true);
        });
    }
}