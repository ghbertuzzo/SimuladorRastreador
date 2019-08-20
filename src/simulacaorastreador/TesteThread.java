
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulacaorastreador;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author liberato
 */
public class TesteThread {
    
    public static void main(String[] args){
        
        String host1 = "200.134.18.85";
        //String host1 = "192.168.0.102";
        int porta = 19880;                
        File arquivo1 = new File("LOG_RASTREAMENTO_STATUS2.csv");
        
        int numThreads = 300; //NUMERO DE THREADS (RASTREADORES SIMULADOS)
        
        for(int i=0;i<numThreads;i++){
            Rastreador r1 = new Rastreador(("Tracker "+i), host1, porta, arquivo1, 20000, true);         
            r1.start();
        }
                
    }
    
}
