package barbero_durmiente;

import barbero_durmiente.Cliente;
import static java.lang.Thread.sleep;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BarberoDurmiente {

    public static void main(String[] args) {
        Barbero barbero = new Barbero(false);
        for (int i = 0; i < 4; i++) {
            Cliente c = new Cliente(i + 1, barbero);
            c.start();
        }
    }
}

class Barbero {
    public boolean ocupado;
    public boolean[] sillas = new boolean[5];
  
    public Barbero(boolean ocupado) {
        this.ocupado = ocupado;
    }

    public synchronized void ocuparSilla(int numClie) {
        System.out.println("Silla ocupada por el cliente número: " + numClie);
    }

    public synchronized void dejarSilla(int numClie) {
        while (isOcupado()) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Barbero.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("El cliente " + numClie + " deja libre una silla");
    }

    public synchronized void inicioCorte(int numClie) {
        while (isOcupado()) {
            System.out.println("Barbero ocupado, " + numClie + " espera");
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Barbero.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.setOcupado(true);
        System.out.println("El barbero empieza a cortar el pelo al cliente " + numClie);
    }

    public synchronized void finCorte(int numClie) {
        this.setOcupado(false);
        System.out.println("El barbero termina de cortar el pelo al cliente " + numClie);
        notify();
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }
}


class Cliente extends Thread {
    private Barbero bar;
    private int numCliente;
    
    public Cliente(int nCli, Barbero b) {
        this.numCliente = nCli;
        this.bar = b;
    }

    @Override
    public void run() {
        try {
            bar.inicioCorte(numCliente);
            sleep(800);
            bar.finCorte(numCliente);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
