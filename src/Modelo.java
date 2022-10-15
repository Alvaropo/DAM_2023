import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Arrays;

public class Modelo {
	/**
	 * 
	 * @param dir direccion de directorio del cual se van a obtener los datos
	 * @return contenido del directorio
	 */

public String MostrarInfo(String dir) {//mostrar contenido de un directorio
	
	String valores="";
	File directorio = new File(dir); //directorio a listar  
	
	if (directorio.isDirectory()) {
		String[] lista = directorio.list();//creo una list con el contenido del directorio
	    Arrays.sort(lista);
	    for (int i = 0; i < lista.length; i++) {
	        valores=valores+lista[i]+"\n";//concateno el contenido del directorio en una variable
	    }
	}
	else {
		valores="**SELECCIONA UN DIRECTORIO**";
	}
	return valores;//retorno el contenido del directorio
}

/**
 * 
 * @param dir direccion fichero del cual se van a obtener los datos
 * @return fecha de reacion de directorio
 */

public String MostrarFechaCreacion(String dir){//metodo para obtener fecha creacion fichero

	    File file = new File(dir);

		BasicFileAttributes attrs;
		try {
		    attrs = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
		    FileTime time = attrs.creationTime();//creo el Filetime y obtengo la fecha de creacion
		    
		    String pattern = "dd/MM/yyyy HH:mm:ss";//formato
		    SimpleDateFormat fecha = new SimpleDateFormat(pattern);//establezco la fecha al formato especificado
			
		    String formatted = fecha.format(new Date(time.toMillis()));//establezco el formato final

		   return ( formatted );
		} catch (IOException e) {
		    return "Error al obtener la fecha.";
		}
	}

/**
 * 
 * @param dir direccion del fichero cual se van a obtener los detalles
 * @return datos del directorio
 */

public String MostrarDetalles(String dir) {//metodo para mostrar detalles del fichero seleccionado
	
	String ejecutable,lectura,escritura;
	SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	String datos="";
	File directorio = new File(dir); //directorio a listar     
	
	/*
	en las siguientes lineas vuelco el valor en un condicional para hacerlo mas bonito y que devuelva un si o no en
	caso de ser true o false respectivamente
	*/
	if (directorio.canExecute()) {ejecutable="Si";}else {ejecutable="No";}
	if (directorio.canRead()) {lectura="Si";}else {lectura="No";}
	if (directorio.canWrite()) {escritura="Si";}else {escritura="No";}
	
    if (directorio.isFile()){//informacion que se muestra en el textArea sobre el fichero
    	 datos="Ruta Absoluta: "+directorio.getAbsolutePath()+"\n"+
    		   "Nombre Archivo: "+directorio.getName()+"\n"+
    		   "Tamaño: "+(directorio.length()/1048576)+"MB"+"\n"+
    		   "Fecha de Creacion: "+(this.MostrarFechaCreacion(dir))+"\n"+
         	   "Ultima Modificacion: "+fecha.format(directorio.lastModified())+"\n"+
         	   "Ejecutable: "+ejecutable +"\n"+
         	   "Acceso Lectura: "+lectura+"\n"+
         	   "Acceso Escritura: "+escritura+"\n";
	}
    else {
    	datos="**SELECCIONA UN FICHERO**";
	}
	return datos;
}
/**
 * 
 * @param old nombre del fichero
 * @param neu nuevo nombre del fichero
 */
public void renombrar(String old,String neu) {//renombrar archivos
	
	File oldfile = new File(old);//el nombre antiguo
    File newfile = new File(neu);//el nombre nuevo
    
    if (oldfile.renameTo(newfile)) {//lo renombro
        System.out.println("archivo renombrado");
       // v.direccion=txtRenombrar.getText();
    } else {
        System.out.println("error");
    }
}
}



	
	
