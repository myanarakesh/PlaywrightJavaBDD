package Utilities;

import java.awt.*;

public class utils {
    public static String hexToRgb(String hexColor) {
        Color color = Color.decode(hexColor);
        return String.format("rgb(%d, %d, %d)", color.getRed(), color.getGreen(), color.getBlue());
    }

}
