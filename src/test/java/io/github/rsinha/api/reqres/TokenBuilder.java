package io.github.rsinha.api.reqres;

import java.util.logging.Logger;

public class TokenBuilder {

    public static CreateToken getToken(){
        Logger.getGlobal ().info ("Create token for username : admin & password : password123");
        return CreateToken.builder ().username ("admin").password ("password123").build ();
    }
}
