/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package agendacontactos;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author usuario
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    static ArrayList<Contacto> contactos=new ArrayList();
    public static void main(String[] args) {
        deserializarContactos();
        Scanner sc=new Scanner(System.in);
        int opcion=0;
        do{
            menu();
            opcion=sc.nextInt();
            switch(opcion){
                case 1:
                    anyadirUsuario();
                    serializarContactos();
                    break;
                case 2:
                    borrarUsuario();
                    serializarContactos();
                    break;
                case 3:
                    actualizarUsuario();
                    serializarContactos();
                    break;
                case 4:
                    listarUsuarios();
                    break;
                    
                    
            }
        }while(opcion!=5);
        
        
        
        // TODO code application logic here
    }
    public static void borrarUsuario(){
        Scanner sc =new Scanner(System.in);
        System.out.println("Digame id");
        int id=sc.nextInt();
        for(int i=0;i<contactos.size();i++){
            if(contactos.get(i).getId()==id){
                contactos.remove(id);
            }
        }
    }
    public static String pedirCampoString(){
        Scanner sc=new Scanner(System.in);
        return sc.nextLine();
    }
    public static void actualizarUsuario(){
        Scanner sc=new Scanner(System.in);
        String nombre, telefono;
        int id;
        System.out.println("Digame el id");
        id=sc.nextInt();
        System.out.println("Digame nombre");
        nombre=pedirCampoString();
        System.out.println("Digame telefono");
        telefono=pedirCampoString();
        for(Contacto c:contactos){
            if(c.getId()==id){
                if(!nombre.equals("")){
                    c.setNombre(nombre);
                }
                if(!telefono.equals("")){
                    c.setTelefono(telefono);
                }
            }
        }
    }
     public static void anyadirUsuario(){
        Scanner sc=new Scanner(System.in);
        String nombre, telefono;
        
        System.out.println("Dime nombre");
        nombre=sc.nextLine();
        System.out.println("Dime telefono");
        telefono=sc.nextLine();
        Contacto contacto=new Contacto(nombre, telefono);
        contactos.add(contacto);
        
    }
     
     public static void listarUsuarios(){
         for(Contacto c :contactos){
             System.out.println(c.toString());
         }
     }
    
    static void menu(){
        System.out.println("1.- Añadir usuario");
        System.out.println("2.- Eliminar usuario");
        System.out.println("3.- Actualizar usuario");
        System.out.println("4.- Listar usuarios");
        System.out.println("5.- Salir");
    }
    public static void serializarContactos() {
        try {
            FileOutputStream fileOut = new FileOutputStream("contactos.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(contactos);
            out.close();
            fileOut.close();
            System.out.println("Datos serializados guardados en contactos.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static void deserializarContactos() {
        try {
            FileInputStream fileIn = new FileInputStream("contactos.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            contactos = (ArrayList<Contacto>) in.readObject();
            in.close();
            fileIn.close();
            
        } catch (Exception i) {
            
        } 
        
    }
    
}
