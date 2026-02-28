package util;

import java.awt.Font;

public class Fonts {
    private static final String FONT_NAME = "Segoe UI";
    
    // Títulos
    public static final Font TITULO = new Font(FONT_NAME, Font.BOLD, 24);
    public static final Font SUBTITULO = new Font(FONT_NAME, Font.BOLD, 18);
    
    // Textos
    public static final Font TEXTO_GRANDE = new Font(FONT_NAME, Font.PLAIN, 16);
    public static final Font TEXTO_NORMAL = new Font(FONT_NAME, Font.PLAIN, 14);
    public static final Font TEXTO_PEQUENO = new Font(FONT_NAME, Font.PLAIN, 12);
    
    // Botões
    public static final Font BOTAO = new Font(FONT_NAME, Font.BOLD, 14);
    
    // Números grandes
    public static final Font NUMERO_GRANDE = new Font(FONT_NAME, Font.BOLD, 40);
}