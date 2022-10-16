import javax.swing.*;
import java.awt.*;

public class CustomComboBoxRenderer extends DefaultListCellRenderer {
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        JLabel lbl = (JLabel)super.getListCellRendererComponent(list, value, index, isSelected,  cellHasFocus);
        lbl.setBorder(BorderFactory.createEmptyBorder(7, 10, 7, 5));
        return lbl;
    }
}
