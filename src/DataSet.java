

public class DataSet<K, V> implements KeyList<K, V> {
	
	private class KeyNode{
		private K key;
		private KeyNode next;
		private ValueNode value;
		private int index;
		
		public KeyNode(){
			this.key = (K)new Object();
		}
		
		public KeyNode(K key, ValueNode firstValue){
			this.key = key;
			this.value = firstValue;
			this.next = null;
		}
		
		public void setNext(KeyNode next){
			this.next = next;
		}
		
		public KeyNode getNext(){
			if(hasNext() == true){
				return next;
			}
			return null;
		}
		
		public K getKey(){
			return key;
		}
		
		public ValueNode getValueNode(){
			return value;
		}
		
		public boolean hasNext(){
			if(next != null){
				return true;
			}
			return false;
		}
		
		public ValueNode getLastValueNode(){
			ValueNode last = value;
			while(last.hasNext() == true){
				last = last.getNext();
			}
			return last;
		}
	}
	
	private class ValueNode{
		private V value;
		private ValueNode next;
		
		public ValueNode(){
			this.value = (V)new Object();
		}
		
		public ValueNode(V value){
			this.value = value;
			this.next = null;
		}
		
		public void setNext(ValueNode next){
			this.next = next;
		}
		
		public ValueNode getNext(){
			if(hasNext() == true){
				return next;
			}
			return null;
		}
		
		public V getValue(){
			return value;
		}
		
		public boolean hasNext(){
			if(next != null){
				return true;
			}
			return false;
		}
	}
	
	//시작점 데이터
	public KeyNode keyNode;
	public ValueNode valueNode;
	
	public DataSet(){
		keyNode = new KeyNode();
		valueNode = new ValueNode();
	}
	
	public KeyNode getKeyNode(K key){
		KeyNode kn = keyNode;
		if(kn.getKey() == key)
			return kn;
		else{
			while(kn.hasNext()){
				kn = kn.getNext();
				if(kn.getKey() == key)
					return kn;
			}
		}
		return null;
	}
	
	public KeyNode getLastKeyNode(){
		KeyNode last = keyNode;
		while(last.hasNext() == true){
			last = last.getNext();
		}
		return last;
	}
	
	@Override
	// key의 현재 데이터의 가장 끝에 value를 추가한다.
	public void add(K key, V value) {
		// TODO Auto-generated method stub
		KeyNode kn = getKeyNode(key);
		// KeyNode 중에 key 를 가지는 노드가 없는 경우 : 새로 생성 
		if(kn == null){
			KeyNode newKey = new KeyNode(key, new ValueNode(value));
			getLastKeyNode().setNext(newKey);
		// KeyNode 중에 key 를 가지는 노드가 이미 있는 경우 : 내부 valueNode 밑에 value 추가 
		}else{
			kn.getLastValueNode().setNext(new ValueNode(value));
		}
	}

	@Override
	// key의 데이터 중에 index 위치에 value를 set 한다.
	public void put(K key, V value, int index) {
		// TODO Auto-generated method stub
		KeyNode kn = getKeyNode(key);
		// KeyNode 중에 key 를 가지는 노드가 없는 경우 : 새로 생성 
		if(kn == null){
			for(int i = 0 ; i < index ; i++){
				if(i == 0){
					// keyNode를 최초 생성
					KeyNode newKey = new KeyNode(key, new ValueNode());
					getLastKeyNode().setNext(newKey);
				}else{
					// index 전까지는 비어있는 valueNode를 계속 생성하여 붙여준다.
					getKeyNode(key).getLastValueNode().setNext(new ValueNode());
				}
			}
			// index 번째 순서에 value 값을 가지는 valueNode를 생성하여 붙여준다.
			getKeyNode(key).getLastValueNode().setNext(new ValueNode(value));
		// KeyNode 중에 key 를 가지는 노드가 이미 있는 경우 : 내부 valueNode 밑에 value 추가 
		}else{
			ValueNode vn = kn.getValueNode();
			ValueNode temp;
			for(int i = 1 ; i < index ; i++){
				if(vn.hasNext()){
					vn = vn.getNext();
				}else{
					vn.setNext(new ValueNode());
					vn = vn.getNext();
				}
			}
			// index 번째 연결고리를 끊고 사이에 value 값의 valueNode를 생성하여 끼워 넣는다.
			temp = vn.getNext();
			vn.setNext(new ValueNode(value));
			vn.getNext().setNext(temp);
		}
	}

	@Override
	// key의 index 위치의 데이터를 return 한다.
	public V get(K key, int index) {
		// TODO Auto-generated method stub
		KeyNode kn = getKeyNode(key);
		// KeyNode 중에 key 를 가지는 노드가 없는 경우 : null 리턴 
		if(kn == null){
			return null;
		// KeyNode 중에 key 를 가지는 노드가 이미 있는 경우 : index 번째 node를 찾아서 내부 value 값을 리턴 
		}else{
			ValueNode vn = kn.getValueNode();
			for(int i = 0 ; i < index ; i++){
				vn = vn.getNext();
			}
			return vn.getValue();
		}
	}

	@Override
	//keyList의 등록된 데이터들을 return 한다.
	public String toString() {
		// TODO Auto-generated method stub
		StringBuffer keyStr = new StringBuffer();
		StringBuffer valueStr = new StringBuffer();
		StringBuffer returnStr = new StringBuffer();
		int index = 0;
		KeyNode kn = keyNode;
		while(kn.hasNext()){
			kn = kn.getNext();
			keyStr = new StringBuffer(kn.getKey().toString() + " : ");
			ValueNode vn = kn.getValueNode();
			index = 1;
			valueStr = new StringBuffer(vn.getValue().toString());
			while(vn.hasNext()){
				index++;
				vn = vn.getNext();
				valueStr.append(", " + vn.getValue().toString());
			}
			valueStr.append("]\n");
			keyStr.append(index + " : [");
			keyStr.append(valueStr);
			returnStr.append(keyStr);
		}
		return returnStr.toString();
	}

}
