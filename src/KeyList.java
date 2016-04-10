

public interface KeyList<K, V> {
	// key의 현재 데이터의 가장 끝에 value를 추가한다.
	public void add(K key, V value);
	
	// key의 데이터 중에 index 위치에 value를 set 한다. 
	public void put(K key, V value, int index);
	
	// key의 index 위치의 데이터를 return 한다. 
	public V get(K key, int index);
	
	//keyList의 등록된 데이터들을 return 한다.
	public String toString( );
}
