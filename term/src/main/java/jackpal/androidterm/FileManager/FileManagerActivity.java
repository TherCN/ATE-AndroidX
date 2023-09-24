package jackpal.androidterm.FileManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.ArrayAdapter;
import androidx.appcompat.app.AppCompatActivity;
import jackpal.androidterm.R;
import jackpal.androidterm.Widgets.ListView;
import java.io.File;

public class FileManagerActivity extends AppCompatActivity {

	String storageDir = Environment.getExternalStorageDirectory().toString();
	ListView filelist1;
	ListView filelist2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.file);
		
		filelist1 = findViewById(R.id.filelist1);
		filelist2 = findViewById(R.id.filelist2);
		inflateFileList(getFiles(storageDir));
	}
    
    public File[] getFiles(String path) {
		return new File(path).listFiles();
	}
    
	public void inflateFileList(File[] files) {
		String[] filenames = null;
		try {
		filenames = new String[files.length];
		for (int i = 0; i < files.length; i++) {
			filenames[i] = files[i].getName();
			
		}
		} catch (NullPointerException e) {
			Log.e("THERCN","",e);
			return;
		}
		ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,filenames);
		filelist1.setAdapter(adapter1);
		filelist2.setAdapter(adapter1);
	}
}
