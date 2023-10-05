package PaooGame.GUI;

import PaooGame.Input.MouseHandler;
import PaooGame.RefLinks;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

/*! \ public class UserInterfaceButton extends Rectangle
    \brief Implementeaza butoanele meniului de joc
 */
public class UserInterfaceButton extends Rectangle {
    private final MouseHandler        mouseH;
    private final String              text;
    private final Font                font;
    private boolean                   pressed;
    private boolean                   hovered;


    /*! \fn UserInterfaceButton(int x, int y, int width, int height, String text, Font font, RefLinks refLinks)
        \brief Constructorul de initializare al clasei

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
        */
    public UserInterfaceButton(int x, int y, int width, int height, String text, Font font, RefLinks refLinks) {
        super(x, y, width, height);
        this.text = text;
        this.font = font;
        this.mouseH = refLinks.GetMouseHandler();
    }

    /*! \fn public void Draw(Graphics g)
       \brief Deseneaza (randeaza) pe ecran starea obiectul buton.
       \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
    */
    public void draw(Graphics g) {
        g.setColor(hovered ? Color.BLACK : Color.WHITE);
        g.setFont(font);
        g.drawString(text,
                x + (width - g.getFontMetrics().stringWidth(text)) / 2,
                y + height / 2 + g.getFontMetrics().getAscent() / 2);
    }

    /*!
        \fn getters + setters + is -uri care verifica valoarea de adevar a booleenilor hovered si pressed
    */
    public boolean isHovered() {
        int mouseX = mouseH.GetXmouse();
        int mouseY = mouseH.GetYmouse();
        hovered = mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
        return hovered;
    }
    public boolean isPressed() {
        return isHovered() && mouseH.IsLeft();
    }


    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
    public String GetText(){ return text; }
    public boolean GetPressed() { return pressed; }

    public void SetPressed(boolean pressed) {
        this.pressed = pressed;
    }
}
