package PaooGame.Input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/*! \class public class KeyHandler
    \brief Clasa care se ocupa cu gestionarea evenimentelor de mouse
    (apasare sau eliberare buton)
 */
public class MouseHandler implements MouseListener, MouseMotionListener {

    private int xMouse, yMouse;
    private boolean leftPressed;


    public void Update() { }

    public void mouseMoved(MouseEvent e) {
        xMouse = e.getX();
        yMouse = e.getY();
    }

    @Override
    public void mousePressed(MouseEvent e) {

       // System.out.println("pressed");
        if(e.getButton() == MouseEvent.BUTTON1) {
            leftPressed = true;
            //System.out.println("leftPressed");
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
       // System.out.println("released");

        if(e.getButton() == MouseEvent.BUTTON1) {
            leftPressed = false;
           // System.out.println("leftNotPressed");
        }
    }

    public boolean IsLeft()
    {
        return leftPressed;
    }

    public int GetXmouse() {
        return xMouse;
    }

    public int GetYmouse() {
        return yMouse;
    }



    @Override
    public void mouseClicked(MouseEvent e) {
        //System.out.println("clicked");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //System.out.println("entered");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //System.out.println("exitred");
    }

    @Override
    public void mouseDragged(MouseEvent e) { }
}
