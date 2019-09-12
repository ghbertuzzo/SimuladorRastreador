package simulacaorastreador;

import java.io.File;

public class TesteThread {
    
    public static void main(String[] args){
        
        String host1 = "200.134.18.85";        
        int porta = 19880;                
        File arquivo1 = new File("LOG_RASTREAMENTO_STATUS2.csv");
        
        int numThreads = 100; //NUMERO DE THREADS (RASTREADORES SIMULADOS)
        int timeInterval = 5; //TEMPO DE INTERVALO EM SEGUNDOS
        
        for(int i=0;i<numThreads;i++){
            Rastreador r1 = new Rastreador(("Tracker "+i), host1, porta, arquivo1, 1000*timeInterval, true);         
            r1.start();
        }
                
    }
    
}
