package thercn.terminal.FileManager;

import android.os.Bundle;
import android.os.Environment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import thercn.terminal.FileManager.FileAdapter;
import thercn.terminal.Log;
import thercn.terminal.R;

public class FileManagerActivity extends AppCompatActivity {

	String storageDir = Environment.getExternalStorageDirectory().toString();
	RecyclerView filelist1;
	RecyclerView filelist2;
	String leftListParentDir = storageDir;
	String rightListParentDir;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.file);
		Toolbar toolbar = findViewById(R.id.file_toolbar);
		setSupportActionBar(toolbar);
		filelist1 = findViewById(R.id.filelist1);
		filelist2 = findViewById(R.id.filelist2);
		
		inflateFileListLeft(storageDir);
		rightListParentDir = "/data/data/" + getPackageName();
		inflateFileListRight(rightListParentDir);
	}


    public static File[] getFiles(String path) {
		Log.e("", path);

		File[] files = new File(path).listFiles();
		Arrays.sort(files, new Comparator<File>() {
				@Override
				public int compare(File o1, File o2) {
					int typeCompare = o1.isFile() ? (o2.isFile() ? 0 : 1) 
						: (o2.isFile() ? -1 : 0);

					if (typeCompare != 0) {
						return typeCompare;
					} else {
						String name1 = o1.getName().toUpperCase();
						String name2 = o2.getName().toUpperCase();

						return name1.compareTo(name2);
					}
				}
			});
		return files;
	}

	public void inflateFileListLeft(String path) {
		
		List<File> newFiles = new ArrayList<File>();
		newFiles.add(new File(path).getParentFile());
		File[] files = getFiles(path);
		for (int i = 0; i < files.length; i++) {
			newFiles.add(files[i]);
		}
		final FileAdapter<File> adapter = new FileAdapter<File>(this, newFiles,filelist1);

		filelist1.setAdapter(adapter);
		filelist1.setLayoutManager(new LinearLayoutManager(this));
	}

	public void inflateFileListRight(String path) {
		
		List<File> newFiles = new ArrayList<File>();
		newFiles.add(new File(path).getParentFile());
		File[] files = getFiles(path);
		for (int i = 0; i < files.length; i++) {
			newFiles.add(files[i]);
		}
		final FileAdapter<File> adapter = new FileAdapter<File>(this, newFiles,filelist2);

		filelist2.setAdapter(adapter);
		filelist2.setLayoutManager(new LinearLayoutManager(this));
	}


}
