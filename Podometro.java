/**
 * La clase modela un sencillo pod�metro que registra informaci�n
 * acerca de los pasos, distancia, ..... que una persona (hombre o mujer)
 * ha dado en una semana. 
 * 
 * @author    - pon aqu� tu nombre - 
 * 
 */
public class Podometro {
    private final char HOMBRE = 'H';
    private final char MUJER = 'M';
    private final double ZANCADA_HOMBRE = 0.45;
    private final double ZANCADA_MUJER = 0.41;
    private final int SABADO = 6;
    private final int DOMINGO = 7;
    private String marca;
    private int altura;
    private char sexo;
    private double longitudZancada;
    private int totalPasosLaborables;
    private int totalPasosSabado;
    private int totalPasosDomingo;
    private int totalDistanciaSemana;
    private int totalDistanciaFinSemana;
    private int tiempo;
    private int caminatasNoche;
    
    

    /**
     * Inicializa el pod�metro con la marca indicada por el par�metro.
     * El resto de atributos se ponen a 0 y el sexo, por defecto, es mujer
     */
    public Podometro(String queMarca) {
        marca = queMarca;
        altura = 0;
        sexo = MUJER;
        longitudZancada = 0;
        totalPasosLaborables = 0;
        totalPasosSabado = 0;
        totalPasosDomingo = 0;
        totalDistanciaSemana = 0;
        totalDistanciaFinSemana = 0;
        tiempo = 0;
        caminatasNoche = 0;
    }

    /**
     * accesor para la marca
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Simula la configuraci�n del pod�metro.
     * Recibe como par�metros la altura y el sexo de una persona
     * y asigna estos valores a los atributos correspondiente.
     * Asigna adem�s el valor adecuado al atributo longitudZancada
     * 
     * (leer enunciado)
     *  
     */
    public void configurar(double queAltura, char queSexo) {
        altura = (int) (100 * queAltura);
        sexo = queSexo;
        if(sexo == MUJER){
            longitudZancada = Math.floor(altura * ZANCADA_MUJER);
        }
        else{
            longitudZancada = Math.ceil(altura * ZANCADA_HOMBRE);
        }
    }

     /**
     *  Recibe cuatro par�metros que supondremos correctos:
     *    pasos - el n� de pasos caminados
     *    dia - n� de d�a de la semana en que se ha hecho la caminata 
     *              (1 - Lunes, 2 - Martes - .... - 6 - S�bado, 7 - Domingo)
     *    horaInicio � hora de inicio de la caminata
     *    horaFina � hora de fin de la caminata
     *    
     *    A partir de estos par�metros el m�todo debe realizar ciertos c�lculos
     *    y  actualizar� el pod�metro adecuadamente  
     *   
     *   (leer enunciado del ejercicio)
     */
    public void registrarCaminata(int pasos, int dia, int horaInicio, int horaFin) {
        int aux1 = (pasos + (int) (longitudZancada));
        totalDistanciaSemana += aux1;
        switch(dia){
            case 1:
            case 2:
            case 3:
            case 4:
            case 5: totalPasosLaborables += pasos;
            break;
            case SABADO: totalPasosSabado += pasos;
                    totalDistanciaFinSemana += aux1;
            break;
            case DOMINGO: totalPasosDomingo += pasos;
                    totalDistanciaFinSemana += aux1;
            break;
        }
        int horasInicio = horaInicio / 100;
        int minutosInicio = horaInicio % 100;
        int horasFinal = horaFin / 100;
        int minutosFinal = horaFin % 100;
        int horasCaminata = horasFinal - horasInicio;
        tiempo += ((minutosFinal - minutosInicio) + horasCaminata * 60);
        if(horasInicio > 21){
            caminatasNoche ++;
        }
    }
    
     /**
     * Muestra en pantalla la configuraci�n del pod�metro
     * (altura, sexo y longitud de la zancada)
     * 
     * (ver enunciado)
     *  
     */
    public void printConfiguracion() {
        String queSexo = "MUJER";
        if(sexo == HOMBRE){
            queSexo = "HOMBRE";
        }
        System.out.println("Configuracion del podometro" +
                         "/n***************************" + 
                         "/nAltura: " + (double) (altura / 100) + " mtos" +
                         "/nSexo: " + queSexo + 
                         "/nLogitud de zancada: " + longitudZancada); 
    }

    /**
     * Muestra en pantalla informaci�n acerca de la distancia recorrida,
     * pasos, tiempo total caminado, ....
     * 
     * (leer enunciado)
     *  
     */
    public void printEstad�sticas() {
        String aux1 = "LABORALES";
        if(totalPasosLaborables < totalPasosSabado){
            aux1 = "SABADO";
            if(totalPasosSabado < totalPasosDomingo){
                aux1 = "DOMINGO";
            }
        }
        System.out.println("ESTADISTICAS" +
                         "/n***************************" +
                         "/nDistancia recorrida toda la semana: " + totalDistanciaSemana + " Km" +
                         "/nDistancia recorrida fin de semana: " + totalDistanciaFinSemana + " Km" +
                         "/n " +
                         "/nN� pasos d�as laborables: " + totalPasosLaborables +
                         "/nN� pasos S�BADO: " + totalPasosSabado +
                         "/nN� pasos DOMINGO: " + totalPasosDomingo +
                         "/n " +
                         "/nN� caminatas realizadas a partir de las 21h.: " + caminatasNoche +
                         "/nTiempo total caminado en la semana: " + (tiempo / 60) + "h. y " + (tiempo % 60) + "m." +
                         "/nD�a/s con m�s pasos caminados: " + aux1);
    }
    
    /**
     *  Calcula y devuelve un String que representa el nombre del d�a
     *  en el que se ha caminado m�s pasos - "S�BADO"   "DOMINGO" o  "LABORABLES"
     */
    public String diaMayorNumeroPasos() {
        String aux1 = "LABORALES";
        if(totalPasosLaborables < totalPasosSabado){
            aux1 = "SABADO";
            if(totalPasosSabado < totalPasosDomingo){
                aux1 = "DOMINGO";
            }
        }
        return aux1;
    }

    /**
     * Restablecer los valores iniciales del pod�metro
     * Todos los atributos se ponen a cero salvo el sexo
     * que se establece a MUJER. La marca no var�a
     *  
     */    
    public void reset() {
        altura = 0;
        sexo = MUJER;
        longitudZancada = 0;
        totalPasosLaborables = 0;
        totalPasosSabado = 0;
        totalPasosDomingo = 0;
        totalDistanciaSemana = 0;
        totalDistanciaFinSemana = 0;
        tiempo = 0;
        caminatasNoche = 0;
    }

}
