
import java.util.Scanner;

public class CASOALUMNOS {

    static Scanner teclado = new Scanner(System.in);

    // Datos de cada alumno
    static class Alumno {
        int matricula;
        String nombre;
        double calificacion;

        Alumno(int matricula, String nombre, double calificacion) {
            this.matricula = matricula;
            this.nombre = nombre;
            this.calificacion = calificacion;
        }
    }

    // Nodo de la lista simple
    static class NodoSimple {
        Alumno alumno;
        NodoSimple siguiente;

        NodoSimple(Alumno alumno) {
            this.alumno = alumno;
        }
    }

    // Nodo de la lista doble
    static class NodoDoble {
        Alumno alumno;
        NodoDoble anterior;
        NodoDoble siguiente;

        NodoDoble(Alumno alumno) {
            this.alumno = alumno;
        }
    }

    // Nodo de la lista circular
    static class NodoCircular {
        Alumno alumno;
        NodoCircular siguiente;

        NodoCircular(Alumno alumno) {
            this.alumno = alumno;
        }
    }

    // Inicio y final de cada lista
    static NodoSimple inicioSimple = null, finSimple = null;
    static NodoDoble inicioDoble = null, finDoble = null;
    static NodoCircular inicioCircular = null, finCircular = null;

    public static void main(String[] args) {

        // Cargar los datos de prueba al iniciar
        precargarDatos();

        int opcion;

        do {
            System.out.println("\n==============================================");
            System.out.println("       SISTEMA DE REGISTRO DE ALUMNOS");
            System.out.println("==============================================");
            System.out.println("LISTA SIMPLEMENTE ENLAZADA");
            System.out.println("1. Agregar alumnos");
            System.out.println("2. Mostrar alumnos");
            System.out.println("3. Buscar alumno por matricula");
            System.out.println("4. Eliminar alumno");
            System.out.println("5. Ordenar alumnos por nombre");
            System.out.println("----------------------------------------------");
            System.out.println("LISTA DOBLEMENTE ENLAZADA");
            System.out.println("6. Agregar y recorrer en ambos sentidos");
            System.out.println("7. Buscar alumno por nombre");
            System.out.println("8. Eliminar alumno");
            System.out.println("----------------------------------------------");
            System.out.println("LISTA CIRCULAR");
            System.out.println("9. Registrar y mostrar alumnos");
            System.out.println("10. Recorrer alumnos por vueltas");
            System.out.println("0. Salir");
            System.out.println("==============================================");
            System.out.print("Selecciona una opcion: ");

            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    agregarSimple();
                    break;
                case 2:
                    mostrarSimple();
                    break;
                case 3:
                    buscarSimple();
                    break;
                case 4:
                    eliminarSimple();
                    break;
                case 5:
                    ordenarSimple();
                    break;
                case 6:
                    agregarDoble();
                    break;
                case 7:
                    buscarDoble();
                    break;
                case 8:
                    eliminarDoble();
                    break;
                case 9:
                    agregarCircular();
                    break;
                case 10:
                    recorrerCircular();
                    break;
                case 0:
                    System.out.println("Programa finalizado.");
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }

        } while (opcion != 0);

        teclado.close();
    }

    // EJERCICIO 1: Cargar cinco alumnos de ejemplo
    static void precargarDatos() {

        Alumno[] alumnos = {
            new Alumno(101, "Antonio", 9.0),
            new Alumno(102, "Carlos", 8.5),
            new Alumno(103, "Maria", 9.5),
            new Alumno(104, "Jose", 7.8),
            new Alumno(105, "Andrea", 8.9)
        };

        for (Alumno alumno : alumnos) {
            agregarNodoSimple(alumno);
            agregarNodoDoble(alumno);
            agregarNodoCircular(alumno);
        }
    }

    // Insertar un alumno en la lista simple
    static void agregarNodoSimple(Alumno alumno) {
        NodoSimple nuevo = new NodoSimple(alumno);

        if (inicioSimple == null) {
            inicioSimple = nuevo;
            finSimple = nuevo;
        } else {
            finSimple.siguiente = nuevo;
            finSimple = nuevo;
        }
    }

    // Insertar un alumno en la lista doble
    static void agregarNodoDoble(Alumno alumno) {
        NodoDoble nuevo = new NodoDoble(alumno);

        if (inicioDoble == null) {
            inicioDoble = nuevo;
            finDoble = nuevo;
        } else {
            finDoble.siguiente = nuevo;
            nuevo.anterior = finDoble;
            finDoble = nuevo;
        }
    }

    // Insertar un alumno en la lista circular
    static void agregarNodoCircular(Alumno alumno) {
        NodoCircular nuevo = new NodoCircular(alumno);

        if (inicioCircular == null) {
            inicioCircular = nuevo;
            finCircular = nuevo;
            nuevo.siguiente = inicioCircular;
        } else {
            finCircular.siguiente = nuevo;
            finCircular = nuevo;
            finCircular.siguiente = inicioCircular;
        }
    }

    // Capturar los datos de un alumno
    static Alumno capturarAlumno() {
        System.out.print("Matricula: ");
        int matricula = teclado.nextInt();
        teclado.nextLine();

        System.out.print("Nombre: ");
        String nombre = teclado.nextLine();

        System.out.print("Calificacion: ");
        double calificacion = teclado.nextDouble();
        teclado.nextLine();

        return new Alumno(matricula, nombre, calificacion);
    }

    // Mostrar un alumno con formato de tabla
    static void mostrarEncabezado() {
        System.out.printf("%-12s %-20s %-12s%n",
                "MATRICULA", "NOMBRE", "CALIFICACION");
        System.out.println("----------------------------------------------");
    }

    static void mostrarFila(Alumno alumno) {
        System.out.printf("%-12d %-20s %-12.2f%n",
                alumno.matricula,
                alumno.nombre,
                alumno.calificacion);
    }

    // EJERCICIO 1: Agregar alumnos a la lista simple
    static void agregarSimple() {
        String respuesta;

        do {
            Alumno alumno = capturarAlumno();
            agregarNodoSimple(alumno);

            System.out.println("Alumno agregado correctamente.");
            System.out.print("Agregar otro? (si/no): ");
            respuesta = teclado.nextLine();

        } while (respuesta.equalsIgnoreCase("si"));
    }

    // EJERCICIO 2: Mostrar la lista simple
    static void mostrarSimple() {
        if (inicioSimple == null) {
            System.out.println("La lista esta vacia.");
            return;
        }

        System.out.println("\nALUMNOS DE LA LISTA SIMPLE");
        mostrarEncabezado();

        NodoSimple actual = inicioSimple;

        while (actual != null) {
            mostrarFila(actual.alumno);
            actual = actual.siguiente;
        }
    }

    // EJERCICIO 3: Buscar por matricula
    static void buscarSimple() {
        System.out.print("Matricula que deseas buscar: ");
        int buscar = teclado.nextInt();
        teclado.nextLine();

        NodoSimple actual = inicioSimple;

        while (actual != null) {
            if (actual.alumno.matricula == buscar) {
                System.out.println("\nALUMNO ENCONTRADO");
                mostrarEncabezado();
                mostrarFila(actual.alumno);
                return;
            }

            actual = actual.siguiente;
        }

        System.out.println("Alumno no encontrado.");
    }

    // EJERCICIO 4: Eliminar de la lista simple
    static void eliminarSimple() {
        System.out.print("Matricula a eliminar: ");
        int buscar = teclado.nextInt();
        teclado.nextLine();

        NodoSimple actual = inicioSimple;
        NodoSimple anterior = null;

        while (actual != null
                && actual.alumno.matricula != buscar) {
            anterior = actual;
            actual = actual.siguiente;
        }

        if (actual == null) {
            System.out.println("Alumno no encontrado.");
            return;
        }

        if (anterior == null) {
            inicioSimple = actual.siguiente;
        } else {
            anterior.siguiente = actual.siguiente;
        }

        if (actual == finSimple) {
            finSimple = anterior;
        }

        System.out.println("Alumno eliminado correctamente.");
    }

    // EJERCICIO 5: Ordenar por nombre
    static void ordenarSimple() {
        if (inicioSimple == null) {
            System.out.println("La lista esta vacia.");
            return;
        }

        for (NodoSimple i = inicioSimple;
                i != null; i = i.siguiente) {

            for (NodoSimple j = i.siguiente;
                    j != null; j = j.siguiente) {

                if (i.alumno.nombre.compareToIgnoreCase(
                        j.alumno.nombre) > 0) {

                    Alumno temporal = i.alumno;
                    i.alumno = j.alumno;
                    j.alumno = temporal;
                }
            }
        }

        System.out.println("Lista ordenada alfabeticamente.");
        mostrarSimple();
    }

    // EJERCICIO 6: Agregar y recorrer la lista doble
    static void agregarDoble() {
        String respuesta;

        do {
            Alumno alumno = capturarAlumno();
            agregarNodoDoble(alumno);

            System.out.println("Alumno agregado a la lista doble.");
            System.out.print("Agregar otro? (si/no): ");
            respuesta = teclado.nextLine();

        } while (respuesta.equalsIgnoreCase("si"));

        System.out.println("\nRECORRIDO HACIA ADELANTE");
        mostrarEncabezado();

        NodoDoble actual = inicioDoble;

        while (actual != null) {
            mostrarFila(actual.alumno);
            actual = actual.siguiente;
        }

        System.out.println("\nRECORRIDO HACIA ATRAS");
        mostrarEncabezado();

        actual = finDoble;

        while (actual != null) {
            mostrarFila(actual.alumno);
            actual = actual.anterior;
        }
    }

    // EJERCICIO 7: Buscar por nombre en la lista doble
    static void buscarDoble() {
        System.out.print("Nombre que deseas buscar: ");
        String buscar = teclado.nextLine();

        NodoDoble actual = inicioDoble;
        boolean encontrado = false;

        while (actual != null) {
            if (actual.alumno.nombre.equalsIgnoreCase(buscar)) {
                if (!encontrado) {
                    System.out.println("\nALUMNOS ENCONTRADOS");
                    mostrarEncabezado();
                }

                mostrarFila(actual.alumno);
                encontrado = true;
            }

            actual = actual.siguiente;
        }

        if (!encontrado) {
            System.out.println("Alumno no encontrado.");
        }
    }

    // EJERCICIO 8: Eliminar de la lista doble
    static void eliminarDoble() {
        System.out.print("Matricula del alumno a eliminar: ");
        int buscar = teclado.nextInt();
        teclado.nextLine();

        NodoDoble actual = inicioDoble;

        while (actual != null
                && actual.alumno.matricula != buscar) {
            actual = actual.siguiente;
        }

        if (actual == null) {
            System.out.println("Alumno no encontrado.");
            return;
        }

        if (actual.anterior != null) {
            actual.anterior.siguiente = actual.siguiente;
        } else {
            inicioDoble = actual.siguiente;
        }

        if (actual.siguiente != null) {
            actual.siguiente.anterior = actual.anterior;
        } else {
            finDoble = actual.anterior;
        }

        System.out.println("Alumno eliminado correctamente.");
    }

    // EJERCICIO 9: Registrar y mostrar lista circular
    static void agregarCircular() {
        String respuesta;

        System.out.println("\nALUMNOS ACTUALES DE LA LISTA CIRCULAR");
        mostrarCircular();

        System.out.print("\nDeseas registrar otro alumno? (si/no): ");
        respuesta = teclado.nextLine();

        while (respuesta.equalsIgnoreCase("si")) {
            Alumno alumno = capturarAlumno();
            agregarNodoCircular(alumno);

            System.out.println("Alumno agregado correctamente.");
            System.out.print("Agregar otro? (si/no): ");
            respuesta = teclado.nextLine();
        }

        mostrarCircular();
    }

    // Mostrar una vuelta de la lista circular
    static void mostrarCircular() {
        if (inicioCircular == null) {
            System.out.println("La lista circular esta vacia.");
            return;
        }

        mostrarEncabezado();

        NodoCircular actual = inicioCircular;

        do {
            mostrarFila(actual.alumno);
            actual = actual.siguiente;
        } while (actual != inicioCircular);
    }

    // EJERCICIO 10: Recorrer la lista circular por vueltas
    static void recorrerCircular() {
        if (inicioCircular == null) {
            System.out.println("La lista circular esta vacia.");
            return;
        }

        System.out.print("Numero de vueltas: ");
        int vueltas = teclado.nextInt();
        teclado.nextLine();

        if (vueltas <= 0) {
            System.out.println("Ingresa un numero positivo.");
            return;
        }

        NodoCircular actual = inicioCircular;

        for (int i = 1; i <= vueltas; i++) {
            System.out.println("\nVUELTA " + i);
            NodoCircular primero = actual;

            mostrarEncabezado();

            do {
                mostrarFila(actual.alumno);
                actual = actual.siguiente;
            } while (actual != primero);
        }
    }
}
