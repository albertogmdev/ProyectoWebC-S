
package util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Alberto
 */
public final class LoggerBd {
    private static final LoggerBd instancia = new LoggerBd();

    private String nombreLog = "simplelog";
    //Directorio base del proyecto en Tomcat
    protected String env = System.getProperty("user.dir");
    private static File logFile;

    public static LoggerBd getInstance(){
        return instancia;
    }

    public static LoggerBd getInstance(String nombre){
        instancia.nombreLog = nombre;
        instancia.crearLogFile();
        return instancia;
    }

    public void crearLogFile(){
        //Cogemos la fecha actual
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendario = Calendar.getInstance();
        
        //Crea el directorio si no existe
        File directorio = new File(env + "/logs/log" + dateFormat.format(calendario.getTime()));
        if(!directorio.exists()){
            System.err.println("INFO: Creamos el directorio " + env);
            directorio.mkdir();
        }
        
        int h = calendario.get(Calendar.HOUR_OF_DAY);
        int m = calendario.get(Calendar.MINUTE);
        int s = calendario.get(Calendar.SECOND);
        String hora = String.format("%02d··%02d··%02d", h, m, s);
        //Creamos el nombre del log con la hora
        nombreLog = '[' +hora + ']' + nombreLog + ".log";
        
        String ruta = env + "\\logs\\" + directorio.getName() + "\\" + nombreLog;
        LoggerBd.logFile = new File(ruta);
        try{
            if(logFile.createNewFile()){
                this.info("Creacion de nuevo log en "+ ruta);
            }
        }catch(IOException e){
            System.out.println("ERROR:" + e.getMessage());
            System.exit(1);
        }
    }

    private LoggerBd(){
        
    }
    
    public void info(String mensaje){
        try{
            FileWriter salida = new FileWriter(LoggerBd.logFile, true);
            salida.write("[INFO] "+ mensaje);
            salida.write("\n");
            salida.close();
        }catch(IOException e){
            System.err.println("ERROR: No se pudo escribir en el archivo");
        }
    }
    
    public void error(String mensaje){
        try{
            FileWriter salida = new FileWriter(LoggerBd.logFile, true);
            salida.write("[ERROR] "+ mensaje);
            salida.write("\n");
            salida.close();
        }catch(IOException e){
            System.err.println("ERROR: No se pudo escribir en el archivo");
        }
    }
    
    public void warn(String mensaje){
        try{
            FileWriter salida = new FileWriter(LoggerBd.logFile, true);
            salida.write("[WARN] "+ mensaje);
            salida.write("\n");
            salida.close();
        }catch(IOException e){
            System.err.println("ERROR: No se pudo escribir en el archivo");
        }
    }
}
