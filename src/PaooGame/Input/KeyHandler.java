package PaooGame.Input;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/*! \class public class KeyHandler
    \brief Clasa care se ocupa cu gestionarea evenimentelor de tastatura
    (apasare sau eliberare tasta)
 */
public class KeyHandler implements KeyListener, FocusListener {
    public boolean upPressed,
            downPressed,
            leftPressed,
            rightPressed,
            spacePressed,
            escPressed;
    public int code;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    /*! \fn public void keyPressed(KeyEvent e)
        \brief Metoda care stocheaza in in variabila code codul tastei apasate
        si verifica daca acesta este "UP", "DOWN", "LEFT", "RIGHT" sau "SPACE"
     */
    @Override
    public void keyPressed(KeyEvent e) {
        code = e.getKeyCode();
        if(code == KeyEvent.VK_UP){
            upPressed = true;
        }
        if(code == KeyEvent.VK_DOWN){
            downPressed = true;
        }
        if(code == KeyEvent.VK_LEFT){
            leftPressed = true;
        }
        if(code == KeyEvent.VK_RIGHT){
            rightPressed = true;
        }
        if(code == KeyEvent.VK_SPACE){
            spacePressed = true;
        }
        if (code == KeyEvent.VK_ESCAPE) {
            escPressed = true;
        }
    }

    /*! \fn public void keyPressed(KeyEvent e)
        \brief Metoda care stocheaza in in variabila code codul tastei relaxate
        si verifica daca acesta este "UP", "DOWN", "LEFT", "RIGHT" sau "SPACE"
     */
    @Override
    public void keyReleased(KeyEvent e) {

        code = e.getKeyCode();
        if(code == KeyEvent.VK_UP){
            upPressed = false;
        }
        if(code == KeyEvent.VK_DOWN){
            downPressed = false;
        }
        if(code == KeyEvent.VK_LEFT){
            leftPressed = false;
        }
        if(code == KeyEvent.VK_RIGHT){
            rightPressed = false;
        }
        if(code == KeyEvent.VK_SPACE){
            spacePressed = false;
        }
        if (code == KeyEvent.VK_ESCAPE) {
            escPressed = false;
        }

    }


    public void Update(){
    }

    @Override
    public void focusGained(FocusEvent e) {

    }

    @Override
    public void focusLost(FocusEvent e) {
        e.getComponent().requestFocus();
    }
}

