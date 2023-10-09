package thercn.terminal.FileManager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import thercn.terminal.R;

public class FileAdapter<T> extends ArrayAdapter {

	File[] files;
	public FileAdapter(Context context, File[] files) {
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
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.file_list, parent, false);
			holder.fileIcon = convertView.findViewById(R.id.file_list_image);
			holder.fileName = convertView.findViewById(R.id.file_list_name);
			holder.fileTime = convertView.findViewById(R.id.file_list_time);
			convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
		
		holder.fileName.setText(files[position].getName());
		if (files[position].isDirectory()) {
			Bitmap fileImage = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.ic_folder);
			holder.fileIcon.setImageBitmap(fileImage);
		} else {
			Bitmap fileImage = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.ic_file);
			holder.fileIcon.setImageBitmap(fileImage);
		}
		
		long lastModifiedTime = files[position].lastModified();
        Date lastModifiedDate = new Date(lastModifiedTime);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = dateFormat.format(lastModifiedDate);

		String permission = "";
		if (files[position].canRead()) {
			permission = permission + "r";
		} else {
			permission = permission + "-";
		}
		if (files[position].canWrite()) {
			permission = permission + "w";
		} else {
			permission = permission + "-";
		}
		if (files[position].canExecute()) {
			permission = permission + "x";
		} else {
			permission = permission + "-";
		}

		holder.fileTime.setText(formattedDate + " " + permission);
		return convertView;
	}
	class ViewHolder {
		TextView fileName;
		ImageView fileIcon;
		TextView fileTime;
	}

}
