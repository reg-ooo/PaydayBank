import java.awt.*;
import java.io.InputStream;

public final class Style {

    //HIMO KO ANING CLASS PARA DI NA BALIK-BALIK BUHAT OG COLOR KADA PAGE
    public static final Color gray = new Color(0xD3D3D3);
    public static final Color pBlue = new Color(0x2D76BD);
    public static final Color dBlue = new Color(0x163F5C);
    public static final Color sBlue = new Color(230, 240, 250);
    public static final Color lBlue = new Color(0xC4E4FF);
    public static final Color black = new Color(0x000000);
    public static final Color white = new Color(0xFFFFFF);
    public static final Color vBlue = new Color(0x1A43BF);
    public static final Color dvBlue = new Color(0x123499);
    public static final Color transparent = new Color(0,0,0,0);


    public Font loadFont(int style, float size, String fontName) {
        try {
            InputStream fontStream = getClass().getResourceAsStream("/fonts/" + fontName + ".ttf");
            Font chosenFont = Font.createFont(Font.TRUETYPE_FONT, fontStream);
            return chosenFont.deriveFont(style, size);
        } catch (Exception e) {
            System.out.println("Could not load font: " + e.getMessage());

            return new Font("Arial", style, (int)size);
        }
    }
}
