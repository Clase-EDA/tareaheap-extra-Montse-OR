/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tarea0;

import java.util.NoSuchElementException;

/**
 *
 * @author hca
 */
public class MinHeap <T extends Comparable<T>> implements MinHeapADT<T>{
    private int cont;
    private T min;
    private NodoHeap<T>[] arre;
    
    public MinHeap(){
        cont=0;
        arre= new NodoHeap[60000];
        
    }
    public int getPapa(int p){
        return p/2;
    }
    
    public void inserta(T elem){
        NodoHeap<T> nuevo= new NodoHeap(elem);
        if(cont>=arre.length-1)
            expande();
        arre[++cont]=nuevo; //(int)(cont/2)
        int pos=cont;
        while(arre[getPapa(pos)]!=null && arre[getPapa(pos)].getElement().compareTo(arre[pos].getElement())>0){
                swap(getPapa(pos),pos);
                pos=getPapa(pos);
            
        }  
    }
    
    
    public void expande(){
        arre=new NodoHeap[arre.length*10];
    }
    public void swap(int papa, int pos){
        NodoHeap<T> aux=arre[papa];
        arre[papa]=arre[pos];
        arre[pos]=aux;
    }
    

    public Integer min(int papa){
        int pos1=papa+1, pos2=pos1+1;
        if(pos1>cont)
            return null;
        if(pos2>cont)
            return pos1;
        if(arre[pos1].getElement().compareTo(arre[pos2].getElement())<0)
            return pos1;
        else
            return pos2;
    }
    public Comparable eliminaMin(){
        Comparable res;
        if(cont==0)
            return null;
        if(cont==1){
            res=arre[1].getElement();
            cont--;
            arre[1]=null;
        }
        else{
            res=arre[1].getElement();
            Integer actual=min(1), papa=1;
            boolean band=true;
            while(actual!=null && band){
                if(arre[actual].getElement().compareTo(arre[papa].getElement())<0)
                    swap(actual, papa);
                else
                    band=false;
                arre[papa]=arre[actual];
                for(int i=papa+1; i<cont; i++){
                    arre[i]=arre[i+1];
                }
                papa=actual;
                actual=min(papa);
                cont--;
            }
        }
        return res;
    }
    public T getMin(){
        return arre[1].getElement();
    }
    public int getCont(){
        return cont;
    }

    public String imprime(){
        StringBuilder cad= new StringBuilder();
        for(int i=0; i<cont; i++){
            cad.append(arre[i+1].getElement());
        }
        return cad.toString();
    }

    public static void main(String[] args) {
        MinHeap mon= new MinHeap();
        mon.inserta(2);
        mon.inserta(7);
        mon.inserta(0);
        mon.inserta(1);
        mon.inserta(9);
        mon.inserta(10);
        mon.inserta(8);
        System.out.println(mon.imprime());
        
        while(mon.getCont()>0){
            System.out.println(mon.eliminaMin());
        }

    }
}
