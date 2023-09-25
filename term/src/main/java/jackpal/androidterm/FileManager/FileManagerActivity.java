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

public class FileManagerActivity extends AppCompatActivity {

	String storageDir = Environment.getExternalStorageDirectory().toString();
	ListView filelist1;
	ListView filelist2;
	String currentList1Dir = storageDir;
	String currentList2Dir = storageDir;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.file);
		Toolbar toolbar = findViewById(R.id.file_toolbar);
		setSupportActionBar(toolbar);
		filelist1 = findViewById(R.id.filelist1);
		filelist2 = findViewById(R.id.filelist2);
		Thread thread = new Thread(new Runnable() {
				public void run()
				{
					inflateFileListLeft(getFiles(storageDir));
					inflateFileListRight(getFiles(storageDir));
				}
			});
		thread.start();
	}

    public File[] getFiles(String path) {
		File[] files = new File(path).listFiles();
		
		return files;
	}

	public void inflateFileListLeft(File[] files) {
		Button gotoParent = findViewById(R.id.gotoParent1);
		gotoParent.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View view) {
					
					inflateFileListLeft(getFiles(new File(currentList1Dir).getParentFile().toString()));
					
				}
			});
		if (files == null)
		{
			return;
		}
		final FileAdapter<File> adapter = new FileAdapter<File>(this, files);
		filelist1.setAdapter(adapter);
		filelist1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long itemId) {
					if (new File(adapter.getItem(position).toString()).isDirectory())
					{
						Log.e("列表1",currentList1Dir);
						currentList1Dir = adapter.getItem(position).toString();
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
					
					inflateFileListRight(getFiles(new File(currentList2Dir).getParentFile().toString()));
					
				}
			});
		if (files == null)
		{
			return;
		}
		final FileAdapter<File> adapter = new FileAdapter<File>(this, files);
		filelist2.setAdapter(adapter);
		filelist2.setOnItemClickListener(new AdapterView.OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long itemId) {
					if (new File(adapter.getItem(position).toString()).isDirectory())
					{
						currentList2Dir = adapter.getItem(position).toString();
						Log.e("列表2",currentList2Dir);
						inflateFileListRight(getFiles(adapter.getItem(position).toString()));
					}
				}
			});
		
	}
	
}
