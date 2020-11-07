import java.util.Scanner;

interface Validacion{
	Scanner input = new Scanner(System.in);
	
	public static int validacionNatural() {
		int ret = 0;
		boolean err = false;
		do {
			try {
				ret = input.nextInt();
			} catch (java.util.InputMismatchException e) {
				System.out.println("entrada no valida, intente de nuevo:");
				input.nextLine();
				err=true;
			}
			if (ret>0) {
				err=false;
			}else {
				System.out.println("solo números naturales");
				err=true;
			}
		}while(err);
		return ret;
	}
}

class Nodo{
	
	private Nodo nodoAnterior;
	private int dato;
	private Nodo nodoSiguiente;
	
	public Nodo(int dato) {
		this.dato = dato;
		nodoAnterior=nodoSiguiente=null;
	}
	public Nodo() {
		nodoAnterior=nodoSiguiente=null;
	}

	public Nodo getNodoAnterior() {
		return nodoAnterior;
	}
	public void setNodoAnterior(Nodo nodoAnterior) {
		this.nodoAnterior = nodoAnterior;
	}
	public int getDato() {
		return dato;
	}
	public void setDato(int dato) {
		this.dato = dato;
	}
	public Nodo getNodoSiguiente() {
		return nodoSiguiente;
	}
	public void setNodoSiguiente(Nodo nodoSiguiente) {
		this.nodoSiguiente = nodoSiguiente;
	}
	
	@Override
	public String toString() {
		return "Nodo [nodoAnterior=" + nodoAnterior + ", dato=" + dato + ", nodoSiguiente=" + nodoSiguiente + "]";
	}
	
}

class ListaDoblementeEnlazada{
	Nodo nodoInicio;
	Nodo nodoFin;
	
	public ListaDoblementeEnlazada() {
		this.nodoInicio = null;
		this.nodoFin = null;
	}

	public boolean listaVacia() {
		return (nodoInicio == null)&&(nodoFin == null);
	}
	
	public void agregarElementoAlInicio(int dato){
		Nodo nuevoNodo=new Nodo(dato);
		if (this.listaVacia()) {
			this.nodoInicio=this.nodoFin=nuevoNodo;
		}else {
			nuevoNodo.setNodoSiguiente(nodoInicio);
			nodoInicio.setNodoAnterior(nuevoNodo);
			this.nodoInicio = nuevoNodo;
		}
	}
	public void agregarElementoAlFinal(int dato){
		Nodo nuevoNodo=new Nodo(dato);
		if (this.listaVacia()) {
			this.agregarElementoAlInicio(dato);
		}else {
			Nodo nodoActual;
			nodoActual = nodoInicio;
			while(nodoActual.getNodoSiguiente()!=null){
				nodoActual=nodoActual.getNodoSiguiente();
			}
			nodoActual.setNodoSiguiente(nuevoNodo);
			nuevoNodo.setNodoAnterior(nodoActual);
		}
	}
	
	public int eliminarElementoInicio() {
		if (this.listaVacia()) {
			return -1;
		}else try{
			Nodo nodoActual=nodoInicio;
			int ret = nodoActual.getDato();
			nodoInicio=nodoActual.getNodoSiguiente();
			nodoInicio.setNodoAnterior(null);
			return ret;
		}catch (Exception e) {
			return -1;
		}
	}
	public int eliminarElementoFinal() {
		if (this.listaVacia()) {
			return -1;
		}else try{
			Nodo nodoAnterior, nodoSiguiente;
			nodoAnterior = nodoInicio;
			nodoSiguiente = nodoInicio.getNodoSiguiente();
			if (nodoSiguiente==null) {
				int ret = nodoInicio.getDato();
				nodoInicio=nodoFin=null;
				return ret;
			}else {
				while(nodoSiguiente.getNodoSiguiente()!=null) {
					nodoAnterior = nodoAnterior.getNodoSiguiente();
					nodoSiguiente = nodoSiguiente.getNodoSiguiente();
				}
				int ret = nodoSiguiente.getDato();
				nodoAnterior.setNodoSiguiente(null);
				return ret;
			}
		}catch (Exception e) {
			return -1;
		}
	}
	public int eliminarElementoEspecifico(int dato) {
		if (nodoInicio==null) {
			return -1;
		}else if(nodoInicio==nodoFin&&nodoInicio.getDato()==dato){
			System.out.println("encontrado en el primer NODO");
			int n = nodoInicio.getDato();
			nodoInicio=nodoInicio.getNodoSiguiente();
			if (nodoInicio!=null) {
				nodoInicio.setNodoAnterior(null);
			}
			nodoFin=nodoInicio;
			return n;
		}else {
			Nodo nodoAnterior, nodoSiguiente;
			nodoAnterior = nodoInicio;
			nodoSiguiente = nodoInicio.getNodoSiguiente();
			
			if (nodoAnterior!=null&&nodoAnterior.getDato()==dato) {
				int n = nodoAnterior.getDato();
				nodoInicio=nodoAnterior.getNodoSiguiente();
				nodoInicio.setNodoAnterior(null);
				return n;
				
			}else {
				while(nodoSiguiente!=null && nodoSiguiente.getDato()!=dato){
					nodoAnterior = nodoAnterior.getNodoSiguiente();
					nodoSiguiente = nodoSiguiente.getNodoSiguiente();
				}
				
				if (nodoSiguiente!=null && nodoSiguiente.getDato()==dato) {
					int n = nodoSiguiente.getDato();
					nodoSiguiente = nodoSiguiente.getNodoSiguiente();
					nodoSiguiente.setNodoAnterior(nodoAnterior);
					
					nodoAnterior.setNodoSiguiente(nodoSiguiente);
					
					return n;
				}else {
					return -99999;
				}
			}
		}
	}
	
	
	public void mostrarElementos() {
		Nodo nodoActual = nodoInicio;
		while(nodoActual!=null){
			System.out.print("<--["+nodoActual.getDato()+"]-->");
			nodoActual=nodoActual.getNodoSiguiente();
		}
		System.out.println();
	}
	public void debug() {
		Nodo nodoActual = nodoInicio;
		while(nodoActual!=null){
			if (nodoActual.getNodoAnterior()!=null) {
				System.out.println(nodoActual.getNodoAnterior().getDato());
			}
			nodoActual=nodoActual.getNodoSiguiente();
		}
		System.out.println();
	}
	
}

public class PruebaListaDoblementeEnlazada {

	public static void main(String[] args) {
		
		ListaDoblementeEnlazada lde = new ListaDoblementeEnlazada();
		lde.agregarElementoAlInicio(11);
		lde.agregarElementoAlInicio(10);
		lde.agregarElementoAlFinal(12);
		lde.agregarElementoAlFinal(13);
		lde.agregarElementoAlFinal(14);
		lde.agregarElementoAlFinal(15);
		lde.mostrarElementos();
		lde.eliminarElementoInicio();
		lde.eliminarElementoFinal();
		lde.eliminarElementoEspecifico(12);
		lde.debug();

	}

}
