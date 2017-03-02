package framework.util;

import java.io.File;
import java.util.Map;
import java.util.TreeMap;

public class FileUtils {
	public static void main(String[] args) {
		File file = new File("E:\\MyEclipse10\\htpt");
		Map map = builderFileTree(file, null, file.getPath());
		System.out.println(map);
	}
	public static Map builderFileTree(File file, Map tree, String pathReplace){
		if(tree==null){
			tree = new TreeMap();
		}
		tree.put(file.getName(), pathReplace==null?file.getPath():file.getPath().replace(pathReplace, ""));
		if(file.isDirectory()){
			File[] files = file.listFiles();
			Map child = new TreeMap();
			for(File tmp : files){
				builderFileTree(tmp, child, pathReplace);
			}
			tree.put("child", child);
		}
		return tree;
	}
}
