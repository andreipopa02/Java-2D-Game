package PaooGame.Exceptions;

import java.io.IOException;

public class InOutExceptions extends IOException {

    public InOutExceptions(){
        super();
    }

    public InOutExceptions(String str){
        super(str);
    }

}
