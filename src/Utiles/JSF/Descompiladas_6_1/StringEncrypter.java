/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.JSF.Descompiladas_6_1;

import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.faces.FacesException;

// Referenced classes of package org.primefaces.util:
//            Base64
public class StringEncrypter {

    private static final Logger LOG = Logger.getLogger("org/primefaces/util/StringEncrypter.getName()");
    private Cipher ecipher;
    private Cipher dcipher;

    public StringEncrypter(SecretKey key, String algorithm) {
        /*  45*/ try {
            /*  45*/ ecipher = Cipher.getInstance(algorithm);
            /*  46*/ dcipher = Cipher.getInstance(algorithm);
            /*  47*/ ecipher.init(1, key);
            /*  48*/ dcipher.init(2, key);
        } /*  49*/ catch (Exception e) {
            /*  50*/ throw new FacesException("Could not initialize Cipher objects", e);
        }
    }

    public StringEncrypter(String passPhrase) {
        /*  63*/ byte salt[] = {
            /*  63*/-87, -101, -56, 50, 86, 52, -29, 3
        };
        /*  69*/ int iterationCount = 19;
        /*  73*/ try {
            /*  73*/ java.security.spec.KeySpec keySpec = new PBEKeySpec(passPhrase.toCharArray(), salt, iterationCount);
            /*  74*/ SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);
            /*  76*/ ecipher = Cipher.getInstance("PBEWithMD5AndDES");
            /*  77*/ dcipher = Cipher.getInstance("PBEWithMD5AndDES");
            /*  80*/ java.security.spec.AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt, iterationCount);
            /*  82*/ ecipher.init(1, key, paramSpec);
            /*  83*/ dcipher.init(2, key, paramSpec);
        } /*  85*/ catch (Exception e) {
            /*  86*/ throw new FacesException("Could not initialize Cipher objects", e);
        }
    }

    public String encrypt(String str) {
        byte enc[];
        /*  99*/ byte utf8[];
        /* 102*/
        try {
            utf8 = str.getBytes("UTF8");

            enc = ecipher.doFinal(utf8);
            /* 105*/ return Base64.encodeToString(enc, false);
        } catch (Exception ex) {
          ex.printStackTrace();
            return null;
        }
//                Exception e;
///* 107*/        e;
///* 108*/        LOG.log(Level.WARNING, "Could not encrypt string", e);
/* 111*/
    }

    public String decrypt(String str) {
    try{    byte utf8[];
        /* 125*/ byte dec[] = Base64.decode(str);
        /* 128*/ utf8 = dcipher.doFinal(dec);
        /* 131*/ return new String(utf8, "UTF8");
       } catch (Exception ex) {
          ex.printStackTrace();
            return null;
        }
    }

}
