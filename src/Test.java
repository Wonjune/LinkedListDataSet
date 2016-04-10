

public class Test {
	public static void main(String[] args){
		DataSet<String,String> ds = new DataSet<String,String>();
		String key1 = "A업체"; 
		ds.add(key1, "A0"); 
		ds.add(key1, "A1"); 
		ds.add(key1, "A2"); 
		ds.add(key1, "A3"); 
		ds.put(key1, "A6", 6); 
		ds.add(key1, "A7");
		
		String key2 = "B업체"; 
		ds.put(key2, "B3", 3);
		
		System.out.println(ds.get(key1,1)); 
		System.out.println(ds.toString());
	}
}
