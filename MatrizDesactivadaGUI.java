/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matriz;

/**
 *
 * @author ASUS
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MatrizDesactivadaGUI extends JFrame {

    private static final int FILAS = 10;
    private static final int COLUMNAS = 5;
    private static final int MAX_SELECCIONES = 5;

    private JButton[][] botones;
    private List<Point> seleccionados;
    private Random random;

    public MatrizDesactivadaGUI() {
        super("Matriz Desactivada");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        botones = new JButton[FILAS][COLUMNAS];
        seleccionados = new ArrayList<>();
        random = new Random();

        JPanel panelMatriz = new JPanel(new GridLayout(FILAS, COLUMNAS));
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                JButton boton = new JButton("(" + i + "," + j + ")");
                boton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        seleccionarCasilla(boton);
                    }
                });
                botones[i][j] = boton;
                panelMatriz.add(boton);
            }
        }

        getContentPane().add(panelMatriz, BorderLayout.CENTER);

        JButton generarAleatorioButton = new JButton("Generar Aleatorio");
        generarAleatorioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generarAleatorio();
            }
        });

        getContentPane().add(generarAleatorioButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void seleccionarCasilla(JButton boton) {
        if (seleccionados.size() < MAX_SELECCIONES && !seleccionados.contains(boton)) {
            boton.setBackground(Color.RED); // Cambia el color al seleccionar
            seleccionados.add(getCoordenadas(boton));
            deshabilitarFilaYColumna(boton);
        }
    }

    private void generarAleatorio() {
        if (seleccionados.size() < MAX_SELECCIONES) {
            int fila, columna;
            JButton boton;
            do {
                fila = random.nextInt(FILAS);
                columna = random.nextInt(COLUMNAS);
                boton = botones[fila][columna];
            } while (seleccionados.contains(boton));

            boton.setBackground(Color.BLUE); // Cambia el color al seleccionar aleatoriamente
            seleccionados.add(getCoordenadas(boton));
            deshabilitarFilaYColumna(boton);
        }
    }

    private void deshabilitarFilaYColumna(JButton boton) {
        int fila = -1;
        int columna = -1;

        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                if (botones[i][j] == boton) {
                    fila = i;
                    columna = j;
                    break;
                }
            }
        }

        for (int i = 0; i < FILAS; i++) {
            if (i != fila) {
                botones[i][columna].setEnabled(false);
            }
        }

        for (int j = 0; j < COLUMNAS; j++) {
            if (j != columna) {
                botones[fila][j].setEnabled(false);
            }
        }
    }

    private Point getCoordenadas(JButton boton) {
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                if (botones[i][j] == boton) {
                    return new Point(i, j);
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MatrizDesactivadaGUI();
            }
        });
    }
}
