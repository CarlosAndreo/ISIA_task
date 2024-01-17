/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package matrices;

import java.awt.Dimension;
import java.util.Random;

/**
 *
 * @author galvez
 */
public class Matriz {
    private int[][]datos;
    private Random rnd = new Random();
    
    public Matriz(int filas, int columnas, boolean inicializarAleatorio){
        datos = new int[columnas][];
        for(int i=0; i<columnas; i++){
            datos[i] = new int[filas];
            if (inicializarAleatorio)
                for(int j=0; j<filas; j++)
                    datos[i][j] = rnd.nextInt(100);
        }
    }
    public Matriz(Dimension d, boolean inicializarAleatorio){
        this(d.height, d.width, inicializarAleatorio);
    }
    
    public Dimension getDimension(){
        return new Dimension(datos.length, datos[0].length);
    }
    
    public static Matriz sumarDosMatrices(Matriz a, Matriz b) throws DimensionesIncompatibles { 
        if(! a.getDimension().equals(b.getDimension())) throw new DimensionesIncompatibles("La suma de matrices requiere matrices de las mismas dimensiones");        
        int i, j, filasA, columnasA; 
        filasA = a.getDimension().height; 
        columnasA = a.getDimension().width; 
        Matriz matrizResultante = new Matriz(filasA, columnasA, false);
        for (j = 0; j < filasA; j++) { 
            for (i = 0; i < columnasA; i++) { 
                matrizResultante.datos[i][j] += a.datos[i][j] + b.datos[i][j]; 
            } 
        } 
        return matrizResultante; 
    }

    public static Matriz trasponerMatriz(Matriz m) {
        int height = m.getDimension().height;
        int width = m.getDimension().width;

        Matriz matrizResultante = new Matriz(width, height, false);
        for(int j = 0; j < height; j++) {
            for(int i = 0; i < width; i++) {
                matrizResultante.datos[j][i] = m.datos[i][j];
            }
        }
        return matrizResultante;
    }

    public static Matriz multiplicarDosMatrices(Matriz a, Matriz b) throws DimensionesIncompatibles {
        if(a.getDimension().height != b.getDimension().width)
            throw new DimensionesIncompatibles("La multiplicacion de matrices requiere que el numero de columnas de la primera matriz sea igual al numero de filas de la segunda");

        int widthA = a.getDimension().width;
        int heightA = a.getDimension().height;
        int heightB = b.getDimension().height;

        Matriz matrizResultante = new Matriz(heightB, widthA, false);

        for (int row = 0; row < widthA; row++) {
            for (int col = 0; col < heightB; col++) {
                int cell = 0;
                for (int i = 0; i < heightA; i++) {
                    cell += a.datos[row][i] * b.datos[i][col];
                }
                matrizResultante.datos[row][col] = cell;
            }
        }
        return matrizResultante;
    }

    @Override
    public String toString(){
        String ret = "";
        ret += "[\n";
        for (int i = 0; i < getDimension().width; i++) {
            ret += "(";
            for (int j = 0; j < getDimension().height; j++) {  
                ret += String.format("%3d", datos[i][j]); 
                if (j != getDimension().height - 1) ret += ", ";
            } 
            ret += ")";
            if (i != getDimension().width - 1) ret += ",";
            ret += "\n";
        } 
        ret += "]\n";
        return ret;
    }
}
