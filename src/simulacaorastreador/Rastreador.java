/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulacaorastreador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author liberato
 */
public class Rastreador extends Thread{
    private String id;
    private String host;
    private int porta;
    private Socket cliente;
    private PrintStream saida;
    private File arquivo;
    private int intervalo;
    private List<String> lista;
    private int posicao;
    private boolean ignoraCabecalho;
    
    public Rastreador(String id, String host, int porta, File arquivo, int intervalo, boolean ignoraCabecalho){
        try{
            this.id = id;
            this.host = host;
            this.porta = porta;
            this.cliente = new Socket(host,porta);
            //System.out.println("Rastreador conectado ao Servidor");
            
            this.saida = new PrintStream(this.cliente.getOutputStream());
            
            this.arquivo = arquivo;
            this.intervalo = intervalo; 
            this.posicao = 0;
            this.lista = new ArrayList<String>();
            this.ignoraCabecalho = ignoraCabecalho;
            
            this.carregarConteudo();
        } catch (IOException ex) {
            System.out.println("Erro");
            System.out.println(ex.getMessage());
        }
    }
    
    private void carregarConteudo() throws FileNotFoundException, IOException{
        FileReader fr = new FileReader(this.arquivo);
        BufferedReader br = new BufferedReader( fr );
        
        if(this.ignoraCabecalho) br.readLine();
        while(br.ready()){
            this.lista.add(br.readLine());
        }
    }

    @Override
    public void run() {
        for (this.posicao = 0; this.posicao < this.lista.size(); this.posicao++) {
            try {
                this.saida.printf("%s\n", this.lista.get(this.posicao));
                //System.out.printf("%s\n", this.lista.get(this.posicao));
                
                this.sleep(this.intervalo);
            } catch (InterruptedException ex) {
                System.out.println("Erro na execucao da thread");
                System.out.println(ex.getStackTrace());              
            }
        }
        
    }
    
}
