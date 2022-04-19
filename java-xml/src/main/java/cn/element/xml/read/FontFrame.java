package cn.element.xml.read;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * This frame contains a font selection dialog that is described by an XML file.
 */
public class FontFrame extends JFrame {
    
    private final GridBagPane gridbag;
    private final JComboBox<String> face;
    private final JComboBox<String> size;
    private final JCheckBox bold;
    private final JCheckBox italic;

    @SuppressWarnings("unchecked")
    public FontFrame(File file) {
        gridbag = new GridBagPane(file);
        add(gridbag);

        face = (JComboBox<String>) gridbag.get("face");
        size = (JComboBox<String>) gridbag.get("size");
        bold = (JCheckBox) gridbag.get("bold");
        italic = (JCheckBox) gridbag.get("italic");

        face.setModel(new DefaultComboBoxModel<String>(new String[]{"Serif",
                "SansSerif", "Monospaced", "Dialog", "DialogInput"}));

        size.setModel(new DefaultComboBoxModel<String>(new String[]{"8",
                "10", "12", "15", "18", "24", "36", "48"}));

        ActionListener listener = event -> setSample();

        face.addActionListener(listener);
        size.addActionListener(listener);
        bold.addActionListener(listener);
        italic.addActionListener(listener);

        setSample();
        pack();
    }

    /**
     * This method sets the text sample to the selected font.
     */
    public void setSample() {
        String fontFace = face.getItemAt(face.getSelectedIndex());
        int fontSize = Integer.parseInt(size.getItemAt(size.getSelectedIndex()));
        JTextArea sample = (JTextArea) gridbag.get("sample");
        int fontStyle = (bold.isSelected() ? Font.BOLD : 0)
                + (italic.isSelected() ? Font.ITALIC : 0);

        sample.setFont(new Font(fontFace, fontStyle, fontSize));
        sample.repaint();
    }
}
