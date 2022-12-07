import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Day7 {
	
	
	public static void main(String[] args) throws IOException {
        Path filePath = Path.of("./input/input_7.txt");
        String content = Files.readString(filePath);
        BufferedReader bufReader = new BufferedReader(new StringReader(content));
        String line=bufReader.readLine();
        
        Directory root = new Directory("/");
    	Directory currentDir=root;
        line=bufReader.readLine();
        while(line!=null) {
        	if(line.contains("cd")) {
        		if(line.substring(line.length()-2, line.length()).contains("..")) {
        			Long dirSize=currentDir.getSize();
        			currentDir=currentDir.getParent();
        			currentDir.addSize(dirSize);
        			line=bufReader.readLine();
        		}else {
	        		Directory newDir = new Directory(line.substring(5));
	        		currentDir.addDir(newDir);
	        		newDir.setParent(currentDir);
	        		currentDir=newDir;
	        		line=bufReader.readLine();
        		}
        	} else if(line.contains("ls")) {
        		line=bufReader.readLine();
        		while(line!=null && !line.substring(2, 4).contains("cd")) {
        			if(!line.contains("dir")) {
        				String[] file = line.split(" ");
        				currentDir.addFile(Long.parseLong(file[0]), file[1]);
        				currentDir.addSize(Long.parseLong(file[0]));
        			}
        			line = bufReader.readLine();
        		}
        	}
        }
		root.addSize(currentDir.size);
        
        Long sum=0L;
		sum=getSizeOfSubDir(root, sum);
		System.out.println("Sum: " + sum);

		Long unusedSpace = 70000000 - root.getSize();
		Long neededSpace = 30000000 - unusedSpace;
		Long deleteSize = findDirToDelete(root, neededSpace, Long.valueOf(70000000));
		System.out.println("Deleted Directory Size: " + deleteSize);
    }
	
	public static Long getSizeOfSubDir(Directory dir, Long sum) {
		if(dir.getDir()==null || dir.getDir().isEmpty()) {
			if(dir.getSize()<=100000)
				sum+=dir.getSize();
			return sum;
		}
		List<Directory> subDirs = dir.getDir();
		
		Iterator<Directory> it = subDirs.iterator();

		while(it.hasNext()) {
			sum = getSizeOfSubDir(it.next(), sum);
		}
		if(dir.getSize()<=100000)
			sum+=dir.getSize();
		return sum;
	}

	public static Long findDirToDelete(Directory dir, Long neededSpace, Long currentSmallest) {
		if(dir.getDir()==null || dir.getDir().isEmpty()) {
			if(dir.getSize()>=neededSpace && dir.getSize()<=currentSmallest)
				currentSmallest=dir.getSize();
			return currentSmallest;
		}
		List<Directory> subDirs = dir.getDir();
		Iterator<Directory> it = subDirs.iterator();

		while(it.hasNext()) {
			currentSmallest = findDirToDelete(it.next(), neededSpace, currentSmallest);
		}
		if(dir.getSize()>=neededSpace && dir.getSize()<=currentSmallest)
			currentSmallest=dir.getSize();

		return currentSmallest;
	}
	
	
	
	public static class Directory{
		String name;
		Directory parent;
		List<Directory> dir;
		List<HashMap<Long, String>> files;
		Long size;
		
		public Directory(String name) {
			this.name=name;
			this.dir = new ArrayList<Directory>();
			this.files = new ArrayList<HashMap<Long, String>>();
			this.parent=null;
			this.size=0L;
		}
		
		public void setName(String name) {
			this.name=name;
		}
		public String getName() {
			return this.name;
		}
		
		public void addDir(Directory dir) {
			this.dir.add(dir);
		}
		
		public List<Directory> getDir(){
			return dir;
		}
		
		public void addFile(Long size, String name) {
			HashMap<Long, String> file = new HashMap<Long,String>();
			file.put(size, name);
			this.files.add(file);
		}
		
		public void setParent(Directory parent) {
			this.parent=parent;
		}
		
		public Directory getParent() {
			return this.parent;
		}
		
		public void addSize(Long size) {
			this.size+=size;
		}
		public Long getSize() {
			return this.size;
		}
	}
}

