package novosrecursos.java08;

public interface DefaultMethodInterface {
	
	 default void msg(String msg) {
		 System.out.println(msg);
	 }
	 
	 void msg2(String msg);

}
