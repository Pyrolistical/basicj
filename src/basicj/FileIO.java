    package basicj;

import java.io.*;

public class FileIO {

    private FileOutputStream fos;
    
    public FileIO() {
    }
    
    public void open(String filename) {
        try {
            File f = new File(filename);
            if(!f.exists()) {
                try {
                    f.createNewFile();
                } catch(IOException e) {
                    System.err.println("--ERROR--   open(\"" + filename + "\"), \"" + filename + "\" could not be created.  Please check the permissions for the folder.");
                    return;
                }
            }
            fos = new FileOutputStream(f, true);
        } catch(FileNotFoundException e) {
        }
    }
    
    public void write(String s) {
        if(fos == null) {
            System.err.println("--ERROR--   write(\"" + s + "\"), there is no file open.  Try the open(filename) command to open a file.");
            return;
        }
        try {
            fos.write(s.getBytes());
        } catch(IOException e) {
            String err = e.getMessage();
            System.err.println("--ERROR--   write(\"" + s + "\"), there was an error when trying to write to the file." + ((err != null && !err.equals(""))?"  " + err:""));
        }
    }
    
    public void close() {
        if(fos == null) {
            System.err.println("--ERROR--   close(), there is no file open.  Make sure you had a file open.");
            return;
        }
        try {
            fos.close();
        } catch(IOException e) {
            String err = e.getMessage();
            System.err.println("--ERROR--   close(), there was an error when trying to close the file." + ((err != null && !err.equals(""))?"  " + err:""));
        }
        fos = null;
    }

}
