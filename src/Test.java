import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test {

	public static void main(String[] args) throws IOException {
		InstructionSet iset = new InstructionSet();
		iset.display();
		/*for (int i = 0; i < 10; i++) {
			System.out.println(iset.display()[i]);
		}*/
		Object[][] arr = new Object[10][1];
		for (int i = 0; i < 10; i++) {
			arr[i][0] = iset.display()[i];
			System.out.println(arr[i][0]);
		}
		/*for (int i = 0; i <iset.lines.length; i++) {
			System.out.println(iset.lines[i]);
		}*/
		//iset.org_convert((String)iset.display()[0]);
		/*iset.add_convert((String)iset.display()[4]);
		iset.hlt_convert((String)iset.display()[6]);
		iset.increment_convert((String)iset.display()[1]);*/
		//iset.and_convert((String)iset.display()[9]);
		//iset.load_convert((String)iset.display()[2]);
		//iset.store_convert((String)iset.display()[4]);
		//iset.transfer_convert((String)iset.display()[0]);
		//iset.all_convert();
		//System.out.println(iset.instruction_memory[0]);
		

	
		for (int i = 0; i < iset.display().length; i++) {
		
			iset.all_convert((String) iset.display()[i]);
		}
		for (int i = 0; i < iset.instruction_memory_index; i++) {
			if(iset.instruction_memory[i] != null){
				System.out.println(iset.instruction_memory[i]);
			}
		}
		for (int i = 0; i < iset.data_memory_index; i++) {
			System.out.println(iset.data_memory[i]);
		}
		
		
	}

}
