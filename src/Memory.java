
public class Memory {
	private int dimension;
	private String name;
	private String[] memory_array = new String[dimension];
	public String[] getMemory_array() {
		return memory_array;
	}

	public void setMemory_array(String[] memory_array) {
		this.memory_array = memory_array;
	}

	public Memory(int dimension, String name) {
		super();
		this.dimension = dimension;
		this.name = name;
	}

	public int getDimension() {
		return dimension;
	}
	public void setDimension(int dimension) {
		this.dimension = dimension;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public void add(String input){
		
	}
	
}
