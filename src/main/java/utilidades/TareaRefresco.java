/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilidades;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Task;
import message.Message;

/**
 *
 * @author rafam
 */
public class TareaRefresco extends Thread{
    
    private String establecimiento;
    private Socket socketCliente;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    
    public TareaRefresco(Socket socketCliente, ObjectOutputStream out, ObjectInputStream in, String establecimiento){
        this.socketCliente = socketCliente;
        this.out = out;
        this.in = in;
        this.establecimiento = establecimiento;
    }
    
    public void run(){
        
        while(true){
            ArrayList<Object> data = new ArrayList<>();
            data.add(establecimiento);
            Message mensaje = new Message("PEDIDO","GET_PEDIDOS",data);
            
            ArrayList<ArrayList<Object>> datosPedido = new ArrayList<>();
            
            try {
                out.writeObject(mensaje);
                Message respuesta  = (Message) in.readObject();
                datosPedido = (ArrayList<ArrayList<Object>>) respuesta.getData().get(0);
                
            } catch (IOException ex) {
                Logger.getLogger(TareaRefresco.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(TareaRefresco.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }

    
    
}
