package jackpal.androidterm;


import android.util.Log;
import java.io.IOException;
import java.util.Arrays;
import jdkx.tools.Diagnostic;
import jdkx.tools.DiagnosticListener;
import jdkx.tools.JavaCompiler;
import jdkx.tools.JavaFileObject;
import jdkx.tools.StandardJavaFileManager;
import openjdk.tools.javac.api.JavacTool;

public class JavaCompile {

	public static void main(String[] args) {
		System.out.println(compile("/sdcard/main.java"));
	}
    public static boolean compile(String sourceFile) {

		JavacTool tool = JavacTool.create();

		if (tool != null) {
			StandardJavaFileManager fileManager = tool.getStandardFileManager(null, null, null);

			Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromStrings(Arrays.asList(sourceFile));

			DiagnosticListener<JavaFileObject> diagnosticListener = new DiagnosticListener<JavaFileObject>() {
				@Override
				public void report(Diagnostic<? extends JavaFileObject> diagnostic) {
					// 处理编译错误
					Log.e("编译错误: ", diagnostic.getMessage(null));
				}
			};
			JavaCompiler.CompilationTask task = tool.getTask(null, fileManager, diagnosticListener, Arrays.asList("-cp", "/sdcard/android.jar"), null, compilationUnits);
			try {
				fileManager.close();
			} catch (IOException e) {
				System.err.println("在关闭文件管理器时出错: " + e.getMessage());
			}
			return task.call();
		}
		Log.e("","编译器实例为空");
		return false;
	}





}
