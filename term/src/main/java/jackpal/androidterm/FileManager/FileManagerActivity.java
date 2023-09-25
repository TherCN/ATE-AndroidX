package jackpal.androidterm.FileManager;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import jackpal.androidterm.FileManager.FileAdapter;
import jackpal.androidterm.R;
import jackpal.androidterm.Widgets.ListView;
import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

public class FileManagerActivity extends AppCompatActivity {

	String storageDir = Environment.getExternalStorageDirectory().toString();
	ListView filelist1;
	ListView filelist2;
	String leftListParentDir = storageDir;
	String rightListParentDir = storageDir;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.file);
		Toolbar toolbar = findViewById(R.id.file_toolbar);
		setSupportActionBar(toolbar);
		filelist1 = findViewById(R.id.filelist1);
		filelist2 = findViewById(R.id.filelist2);

		inflateFileListLeft(getFiles(storageDir));
		inflateFileListRight(getFiles(storageDir));
	}

	@Override
	protected void onResume() {
		super.onResume();
		inflateFileListLeft(getFiles(leftListParentDir));
		inflateFileListRight(getFiles(rightListParentDir));
	}

	
    public File[] getFiles(String path) {
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

	public void inflateFileListLeft(File[] files) {
		Button gotoParent = findViewById(R.id.gotoParent1);
		gotoParent.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View view) {
						Log.e("左列表", "当前目录:" + leftListParentDir);
						leftListParentDir = new File(leftListParentDir).getParentFile().toString();  
						try {
							inflateFileListLeft(getFiles(leftListParentDir));
						} catch (NullPointerException e) {
							throw new NullPointerException("都tm在根目录了你还要跳转，傻逼");
						}
					}
			});
		final FileAdapter<File> adapter = new FileAdapter<File>(this, files);
		filelist1.setAdapter(adapter);
		filelist1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long itemId) {
					if (new File(adapter.getItem(position).toString()).isDirectory()) {
						Log.e("列表1", leftListParentDir);
						leftListParentDir = adapter.getItem(position).toString();
						inflateFileListLeft(getFiles(adapter.getItem(position).toString()));
					}
				}
			});
	}
	
	public void inflateFileListRight(File[] files) {
		Button gotoParent = findViewById(R.id.gotoParent2);
		gotoParent.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					Log.e("右列表", "当前目录:" + rightListParentDir);
					rightListParentDir = new File(rightListParentDir).getParentFile().toString();
					try {
						inflateFileListRight(getFiles(rightListParentDir));
					} catch (NullPointerException e) {
						throw new NullPointerException("都tm在根目录了你还要跳转，傻逼");
					}
				}
			});
		final FileAdapter<File> adapter = new FileAdapter<File>(this, files);
		filelist2.setAdapter(adapter);
		filelist2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long itemId) {
					if (new File(adapter.getItem(position).toString()).isDirectory()) {
						rightListParentDir = adapter.getItem(position).toString();
						inflateFileListRight(getFiles(adapter.getItem(position).toString()));
					}
				}
			});
	}


}
