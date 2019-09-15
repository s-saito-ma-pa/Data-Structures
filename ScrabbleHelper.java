import java.util.*;
import java.io.*;

class ScrabbleHelper {
	//String key;
	//Vector<String> value;
	HashMap<String,Vector<String>> hm;
	//static Scanner sc = new Scaner(new File("myDictionary.txt"));
	//char[] c = new char[100]; 
	public ScrabbleHelper(){
		hm = new HashMap<String,Vector<String>>();
	}

	public void makeDict() {
		//List <String> dict = new ArrayList<String>();
		try {
		Scanner sc = new Scanner (new File ("words.txt"));
		while(sc.hasNextLine()) {
			//dict.add(sc.nextLine());
			//hm.put(dict.key, dict.value);
			String key = sc.nextLine();
			key = key.toLowerCase();
			String sortedKey;
			char[] carray = key.toCharArray();
			Arrays.sort(carray);
			sortedKey = new String(carray);
			
			if(hm.containsKey(sortedKey)) {
				Vector<String> temp = hm.get(sortedKey);
				temp.add(key);
				hm.remove(sortedKey);
				hm.put(sortedKey, temp);
			}
			else {
				Vector<String> v = new Vector<String>();
				v.add(key);
				hm.put(sortedKey,v); //this is if the key isn't already contained in hm
			}
		}
		}
		catch(FileNotFoundException e) {
			System.out.println("File not found!");
		}


		
		  
	}

	public Vector<String> getVector(String s){
			return hm.get(s);
		}

	public static void main(String[] args) throws FileNotFoundException {
		ScrabbleHelper sc = new ScrabbleHelper();
		sc.makeDict();

		String doAnother = "";
		String no = "no";
		Scanner sc2 = new Scanner(System.in);
		while(!(doAnother.equals(no))) {
		System.out.println("Enter your letters:");
		String s = sc2.next();
		char[] carray = s.toCharArray();
		Arrays.sort(carray);
		String sortedKey = new String(carray);
		System.out.println(sc.getVector(sortedKey));
		System.out.println("Another?");
		doAnother = sc2.next();

		while(!(doAnother.equals("no") || doAnother.equals("yes"))){
			System.out.println("Please type in yes or no");
			doAnother = sc2.next();
		}
		}


	}



}