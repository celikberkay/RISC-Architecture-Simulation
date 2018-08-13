import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class InstructionSet {
	public String[] lines = new String[10];
	public String[] lines_ = new String[10];
	public String[] instruction_memory = new String[32];
	public String[] data_memory = new String[16];
	public String[][] label_table = new String[4][10];
	public int ltrow_ind = 0, ltcol_ind = 0;
	// public String microtable[][] = new
	// String[][]{{"T0",""},{"T1",""},{"T2",""},{"T3",""},{"T4",""}};
	Register program_counter = new Register("0", "PC");
	Register instruction_register = new Register("", "IR");
	Register address_register = new Register("", "AR");
	Register stack_pointer = new Register("", "SP");
	Register input_register = new Register("", "INPR");
	Register output_register = new Register("", "OUTR");
	Register register1 = new Register("", "R1");
	Register register2 = new Register("", "R2");
	Register register3 = new Register("", "R3");
	public String[] binaries = new String[] { "0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111", "1000",
			"1001", "1010", "1011", "1100", "1101", "1110", "1111", "10000", "10001", "10010", "10011", "10100",
			"10101", "10110", "10111", "11000", "11001", "11010", "11011", "11100", "11101", "11110", "11111" };

	public String[] hex_numbers = new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D",
			"E", "F" };
	int instruction_memory_index = 0;
	int data_memory_index = 0;
	public String Q = "";
	public String opcode = "";
	public String destination, s1, s2 = "";
	public int d_number = 0;

	Object[] display() {
		{

			try {

				int line_counter = 0;
				File path = new File("C:\\Users\\berka\\Desktop\\Comp. Eng\\2017BAHAR\\PROJE\\DEUARC (1)\\Project2505\\assembly_code.asm");
				FileReader fileReader = new FileReader(path);
				BufferedReader bReader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));

				String line = bReader.readLine();

				for (int i = 0; i < 10; i++) {
					if (line != null) {
						lines[i] = line;
						lines_[i] = line;
					}
					line = bReader.readLine();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

			return lines;
		}
	}

	public void org_convert(String line) {
		String[] temp = line.split(" ");
		if (temp[1].equals("C")) {
			for (int i = 0; i < Integer.parseInt(temp[2]); i++) {
				instruction_memory[instruction_memory_index] = "00000000000";
				instruction_memory_index++;
				program_counter.data = temp[2];
			}
		} else if (temp[1].equals("D")) {
			for (int i = 0; i < Integer.parseInt(temp[2]); i++) {
				data_memory[data_memory_index] = "0000";
				data_memory_index++;
				// program_counter.data = temp[2];
			}
		}

	}

	public void data_convert(String line) {
		String[] temp = line.split(" ");
		if (temp[0].substring(1, 2).equals(":")) {
			if (temp[1].equals("DEC")) {
				label_table[ltrow_ind][0] = temp[0].substring(0, 1);
				label_table[ltrow_ind][1] = temp[2];
				// label_table[ltrow_ind][2] =
				// hex_numbers[Integer.parseInt(temp[2])];
				label_table[ltrow_ind][3] = binaries[Integer.parseInt(temp[2])];
				ltrow_ind++;
				data_memory[data_memory_index] = binaries[Integer.parseInt(temp[2])];
				data_memory_index++;
			} else if (temp[1].equals("HEX")) {
				label_table[ltrow_ind][0] = temp[0].substring(0, 1);
				// label_table[ltrow_ind][1] = temp[2];
				label_table[ltrow_ind][2] = hex_numbers[Integer.parseInt(temp[2])];
				label_table[ltrow_ind][3] = binaries[Integer.parseInt(temp[2])];
				ltrow_ind++;
				data_memory[data_memory_index] = binaries[Integer.parseInt(temp[2])];
				data_memory_index++;
			}
		}
	}

	public void hlt_convert(String line) {
		instruction_memory[instruction_memory_index] = "01000000000";
		instruction_memory_index++;
		/*
		 * Memory InstructionMemory=new Memory(32, "Instruction Memory");
		 * InstructionMemory.setMemory_array(memory_array);
		 */
	}

	public void add_convert(String line) {
		String[] temp = line.split("[\\ ,]");
		String str = "00000";
		for (int i = 1; i < temp.length; i++) {
			if (temp[i].equals("R1")) {
				str += "01";
			} else if (temp[i].equals("R2")) {
				str += "10";
			} else if (temp[i].equals("R3")) {
				str += "11";
			}
		}
		instruction_memory[instruction_memory_index] = str;
		instruction_memory_index++;
	}

	public void increment_convert(String line) {
		String[] temp = line.split("[\\ ,]");
		String str = "00001";
		for (int i = 1; i < temp.length; i++) {
			if (temp[i].equals("R1")) {
				str += "01";
			} else if (temp[i].equals("R2")) {
				str += "10";
			} else if (temp[i].equals("R3")) {
				str += "11";
			}
		}
		str += "00";
		instruction_memory[instruction_memory_index] = str;
		instruction_memory_index++;
	}

	public void double_convert(String line) {
		String[] temp = line.split("[\\ ,]");
		String str = "00010";
		for (int i = 1; i < temp.length; i++) {
			if (temp[i].equals("R1")) {
				str += "01";
			} else if (temp[i].equals("R2")) {
				str += "10";
			} else if (temp[i].equals("R3")) {
				str += "11";
			}
		}
		str += "00";
		instruction_memory[instruction_memory_index] = str;
		instruction_memory_index++;
	}

	public void divide_convert(String line) {
		String[] temp = line.split("[\\ ,]");
		String str = "00011";
		for (int i = 1; i < temp.length; i++) {
			if (temp[i].equals("R1")) {
				str += "01";
			} else if (temp[i].equals("R2")) {
				str += "10";
			} else if (temp[i].equals("R3")) {
				str += "11";
			}
		}
		str += "00";
		instruction_memory[instruction_memory_index] = str;
		instruction_memory_index++;
	}

	public void not_convert(String line) {
		String[] temp = line.split("[\\ ,]");
		String str = "00100";
		for (int i = 1; i < temp.length; i++) {
			if (temp[i].equals("R1")) {
				str += "01";
			} else if (temp[i].equals("R2")) {
				str += "10";
			} else if (temp[i].equals("R3")) {
				str += "11";
			}
		}
		str += "00";
		instruction_memory[instruction_memory_index] = str;
		instruction_memory_index++;
	}

	public void and_convert(String line) {
		String[] temp = line.split("[\\ ,]");
		String str = "00101";
		for (int i = 1; i < temp.length; i++) {
			if (temp[i].equals("R1")) {
				str += "01";
			} else if (temp[i].equals("R2")) {
				str += "10";
			} else if (temp[i].equals("R3")) {
				str += "11";
			}
		}
		instruction_memory[instruction_memory_index] = str;
		instruction_memory_index++;
	}

	public void load_convert(String line) {
		String[] temp = line.split(" ");
		String str = "";
		String str2 = "";
		String str3 = "";
		boolean flag = true;
		if (temp[2].substring(0, 1).equals("@")) {
			str += "00110";
			str2 = temp[2].substring(1) + ":";
		} else if (temp[2].substring(0, 1).equals("#")) {
			str += "10110";
			str2 = temp[2].substring(1) + ":";
			str3 = temp[2].substring(1);
			flag = false;
		}

		for (int i = 1; i < temp.length; i++) {
			if (temp[i].equals("R1,")) {
				str += "01";
			} else if (temp[i].equals("R2,")) {
				str += "10";
			} else if (temp[i].equals("R3,")) {
				str += "11";
			}
		}
		String[] tmp;

		for (int i = 0; i < lines.length; i++) {
			if (lines[i].substring(0, 2).equals(str2)) {
				if (flag == true) {
					tmp = lines[i].split(" ");
					str += binaries[Integer.parseInt(tmp[2])];
				}

			}
		}
		if (flag == false) {
			str += binaries[Integer.parseInt(str3)];
		}
		instruction_memory[instruction_memory_index] = str;
		instruction_memory_index++;
	}

	public void store_convert(String line) {
		String[] temp = line.split(" ");
		String str = "";
		String str2 = "";
		String str3 = "";
		boolean flag = true;
		if (temp[2].substring(0, 1).equals("@")) {
			str += "00111";
			str2 = temp[2].substring(1) + ":";
		} else if (temp[2].substring(0, 1).equals("#")) {
			str += "10111";
			str2 = temp[2].substring(1) + ":";
			str3 = temp[2].substring(1);
			flag = false;
		}

		for (int i = 1; i < temp.length; i++) {
			if (temp[i].equals("R1,")) {
				str += "01";
			} else if (temp[i].equals("R2,")) {
				str += "10";
			} else if (temp[i].equals("R3,")) {
				str += "11";
			}
		}
		String[] tmp;

		for (int i = 0; i < lines.length; i++) {
			if (lines[i].substring(0, 2).equals(str2)) {
				if (flag == true) {
					tmp = lines[i].split(" ");
					str += binaries[Integer.parseInt(tmp[2])];
				}

			}
		}
		if (flag == false) {
			str += binaries[Integer.parseInt(str3)];
		}
		instruction_memory[instruction_memory_index] = str;
		instruction_memory_index++;

	}

	public void transfer_convert(String line) {/// seymanin kodu
		String[] temp = line.split(" ");
		String str = "01001";
		String a = temp[1].substring(1, 2);
		String s = binaries[Integer.parseInt(a)];
		String b = s.substring(0, 1);
		String c = s.substring(1, 2);
		if (b.equals("0") && c.equals("1")) {
			str += "10";

		} else if (b.equals("1") && c.equals("0")) {
			str += "11";

		} else if (b.equals("0") && c.equals("0")) {
			str += "01";

		}
		str += s;
		instruction_memory[instruction_memory_index] = str;
		instruction_memory_index++;
	}

	public void ret_convert(String line) {
		String str = "01011000000";
		instruction_memory[instruction_memory_index] = str;
		instruction_memory_index++;
	}

	public void cal_convert(String line) {
		String[] temp = line.split("[\\ ,@]");
		String str = "010100";
		str += binaries[Integer.parseInt(temp[1])];
		instruction_memory[instruction_memory_index] = str;
		instruction_memory_index++;
	}

	public void jmp_convert(String line) {

	}

	public void jmr_convert(String line) {
		String[] temp = line.split("[\\ ,]");
		String str = "0110100";
		str += binaries[Integer.parseInt(temp[1])];
		instruction_memory[instruction_memory_index] = str;
		instruction_memory_index++;
	}

	public void push_convert(String line) {
		String[] temp = line.split("[\\ ,@]");
		String str = "0111000";
		str += binaries[Integer.parseInt(temp[1])];
		instruction_memory[instruction_memory_index] = str;
		instruction_memory_index++;
	}

	public void pop_convert(String line) {
		String[] temp = line.split("[\\ ,@]");
		String str = "0111100";
		str += binaries[Integer.parseInt(temp[1])];
		instruction_memory[instruction_memory_index] = str;
		instruction_memory_index++;
	}

	public void all_convert(String line) {

		data_convert(line);
		if (line.substring(0, 3).equals("ORG")) {
			// System.out.println("debug org");
			org_convert(line);

		} else if (line.substring(0, 3).equals("HLT")) {
			hlt_convert(line);
		} else if (line.substring(0, 3).equals("ADD")) {
			add_convert(line);
		} else if (line.substring(0, 3).equals("INC")) {
			increment_convert(line);
		} else if (line.substring(0, 3).equals("DBL")) {
			double_convert(line);
		} else if (line.substring(0, 3).equals("DBT")) {
			divide_convert(line);
		} else if (line.substring(0, 3).equals("NOT")) {
			not_convert(line);
		} else if (line.substring(0, 3).equals("AND")) {
			and_convert(line);
		} else if (line.substring(0, 3).equals("LD ")) {
			load_convert(line);
		} else if (line.substring(0, 3).equals("ST ")) {
			store_convert(line);
		} else if (line.substring(0, 3).equals("TSF")) {
			transfer_convert(line);
		} else if (line.substring(0, 3).equals("RET")) {
			ret_convert(line);
		} else if (line.substring(0, 3).equals("CAL")) {
			cal_convert(line);
		} else if (line.substring(0, 3).equals("JMP")) {
			jmp_convert(line);
		} else if (line.substring(0, 3).equals("JMR")) {
			jmr_convert(line);
		} else if (line.substring(0, 3).equals("PSH")) {
			push_convert(line);
		} else if (line.substring(0, 3).equals("POP")) {
			pop_convert(line);
		}
	}

	public void decode() {
		Q = instruction_memory[Integer.parseInt(program_counter.data)-1].substring(0, 1);
System.out.println(instruction_memory[Integer.parseInt(program_counter.data)-1].substring(0, 1));
		opcode = instruction_memory[Integer.parseInt(program_counter.data)-1].substring(1, 5);
		d_number = Integer.parseInt(opcode, 2);
		destination = instruction_memory[Integer.parseInt(program_counter.data)-1].substring(5, 7);
		s1 = instruction_memory[Integer.parseInt(program_counter.data)-1].substring(7, 9);
		s2 = instruction_memory[Integer.parseInt(program_counter.data)-1].substring(9, 11);
		System.out.println(Q + "  " + opcode + "  " + destination + "   " + s1 + "  " + s2);
		// microtable[3][0] = "D0..D15<-IR[9..6], Q<-IR[10]=1, S2<-IR[1..0],
		// S1<-IR[3..2], D<-IR[5..4]";
	}

	public void t0() {

		instruction_register.data = instruction_memory[Integer.parseInt(program_counter.data)];
		// microtable[1][0] = "IR<-IM[PC]";
		System.out.println(instruction_register.data);

	}

	public void t1() {
		int pc_data = Integer.parseInt(program_counter.data);
		pc_data += 1;
		program_counter.data = Integer.toString(pc_data);
		// microtable[2][0] = "PC<PC+1";

	}

	public void t2() {
		decode();

	}

	public void run() {
		t0();
		t1();
		t2();
	}

	public void t3() {
		if (opcode.equals("0000")) {
			String str = s1+s2;
		}
		else if (opcode.equals("0001")) {
			String str = s1+s2;
		}
		else if (opcode.equals("0010")) {
			String str = s1+s2;
		}
		else if (opcode.equals("0000")) {
			String str = s1+s2;
		}
		
	}
}
