
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
public final class Logger {
    private static final Logger instancia = new Logger();

    private String nombreLog = "simplelog";
    protected String env = System.getProperty("user.dir");
    private static File logFile;

    public static Logger getInstance(){
        return instancia;
    }

    public static Logger getInstance(String nombre){
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
        String hora = String.format("%02d_%02d_%02d", h, m, s);
        //Creamos el log con la hora
        nombreLog = '[' +hora + ']' + nombreLog + ".log";
        
        String ruta = env + "\\logs\\" + directorio.getName() + "\\" + nombreLog;
        Logger.logFile = new File(ruta);
        try{
            if(logFile.createNewFile()){
                System.err.println("INFO: Creating new log file");	
            }
        }catch(IOException e){
            System.out.println("ERROR:" + e.getMessage());
            System.exit(1);
        }
    }

    private Logger(){
        if (instancia != null){
                //Prevent Reflection
            throw new IllegalStateException("Cannot instantiate a new singleton instance of log");
        }
        this.crearLogFile();
    }

    public void log(String mensaje){
        try{
            FileWriter salida = new FileWriter(Logger.logFile, true);
            salida.write(mensaje.toCharArray());
            salida.close();
        }catch(IOException e){
            System.err.println("ERROR: No se pudo escribir en el archivo");
        }
    }
    
    public void setNombre(String nombre){
        this.nombreLog = nombre;
    }
}
