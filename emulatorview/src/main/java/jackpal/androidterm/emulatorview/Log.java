package jackpal.androidterm.emulatorview;
import android.util.Log;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
public class Log {
	 
	static File logFile = new File("/sdcard/TerminalLog.txt");
	
    public static void d(String tag,CharSequence info){
		Log.d(tag,(String)info);
		writeLog(tag,info,"DEBUG");
	}
	public static void i(String tag,CharSequence info){
		Log.d(tag,(String)info);
		writeLog(tag,info,"INFO");
	}
    public static void e(String tag,CharSequence info){
		Log.e(tag,(String)info);
		writeLog(tag,info,"ERROR");
	}
	public static void e(String tag,CharSequence info,Throwable exception){
		Log.e(tag,(String)info,exception);
		StringBuilder exceptionInfo = new StringBuilder(info + "\n");
		for (int i = 0; i < exception.getStackTrace().length; i++) {
			exceptionInfo.append(exception.getStackTrace()[i] + "\n");
		}
		writeLog(tag,exceptionInfo,"ERROR");
	}
	public static void fe(String tag,CharSequence info){
		Log.wtf(tag,(String)info);
		writeLog(tag,info,"FATAL ERROR");
	}
	public static void v(String tag,CharSequence info){
		Log.v(tag,(String)info);
		writeLog(tag,info,"VERBOSE");
	}
	public static void w(String tag,CharSequence info){
		Log.w(tag,(String)info);
		writeLog(tag,info,"WARNING");
	}
	public static String getExceptionInfo(Throwable exception){
		return Log.getStackTraceString(exception);
	}
	private static void writeLog(String tag,CharSequence info,String level) {
		try {
			FileWriter writeLogText = new FileWriter(logFile, true);
			SimpleDateFormat formatter =
				new SimpleDateFormat("'['yyyy-MM-dd HH:mm:ss']'");
			Date date = new Date(System.currentTimeMillis());
			writeLogText.write(formatter.format(date) + "[" + tag + "]: " + info + "\n");
			writeLogText.flush();
			writeLogText.close();
		} catch (IOException e) {
			Log.e("Terminal", getExceptionInfo(e));
		}
	}
}
