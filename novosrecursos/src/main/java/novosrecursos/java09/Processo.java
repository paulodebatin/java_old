package novosrecursos.java09;

import java.io.InputStream;

public class Processo {
	public static void main(String[] args) throws Exception{

        Process proc = Runtime.getRuntime().exec(new String[]
{ "/bin/sh", "-c", "echo $PPID" });

        if (proc.waitFor() == 0)
        {
                               InputStream in = proc.getInputStream();
                               int available = in.available();
                               byte[] outputBytes = new byte[available];
                               in.read(outputBytes);
                               String pid = new String(outputBytes);
                               System.out.println("ID do processo: " + pid);
        }
}
}
