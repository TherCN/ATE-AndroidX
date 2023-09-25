package jackpal.androidterm;


import java.io.File;
import java.util.Arrays;
import jdkx.tools.JavaCompiler;
import jdkx.tools.JavaFileObject;
import jdkx.tools.StandardJavaFileManager;
import jdkx.tools.ToolProvider;

public class JavaCompile {

    public void compile(String sourceFile, String[] args) {
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		// 获取Java文件管理器
		StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
		File file = new File("path/to/your/JavaFile.java");
		String className = "com.example.MyClass";
		JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, null, null, null, fileManager.getJavaFileObjects(file));
		//task.setOptions(Arrays.asList("-Xlint:unchecked", "-Xdiags:verbose"));
		boolean result = task.call();
		
	}

}
