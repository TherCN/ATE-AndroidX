package jackpal.androidterm.FileManager;

import android.annotation.NonNull;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import jackpal.androidterm.emulatorview.R;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileAdapter<T> extends ArrayAdapter {

	File[] files;
	public FileAdapter(Context context, @NonNull File[] files) {
		super(context, 0, files);
		this.files = files;

	}
	@Override
	public Object getItem(int position) {
		return files[position];
	}

	@Override
	public long getItemId(int position) {
		return super.getItemId(position);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.file_list, parent, false);
        }
		TextView fileName = convertView.findViewById(R.id.file_list_name);
		ImageView fileIcon = convertView.findViewById(R.id.file_list_image);
		fileName.setText(files[position].getName());
		if (files[position].isDirectory()) {
			Bitmap fileImage = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.ic_folder);
			fileIcon.setImageBitmap(fileImage);
		} else {
			Bitmap fileImage = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.ic_file);
			fileIcon.setImageBitmap(fileImage);
		}
		TextView fileTime = convertView.findViewById(R.id.file_list_time);

		long lastModifiedTime = files[position].lastModified();
        Date lastModifiedDate = new Date(lastModifiedTime);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = dateFormat.format(lastModifiedDate);

		String permission = "";
		if (files[position].canRead())
		{
			permission = permission + "r";
		} else {
			permission = permission + "-";
		}
		if (files[position].canWrite())
		{
			permission = permission + "w";
		} else {
			permission = permission + "-";
		}
		if (files[position].canExecute())
		{
			permission = permission + "x";
		}else {
			permission = permission + "-";
		}
		fileTime.setText(formattedDate + " " + permission);
		return convertView;
	}

}
