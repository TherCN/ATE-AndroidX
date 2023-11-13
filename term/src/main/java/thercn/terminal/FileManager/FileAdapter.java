package thercn.terminal.FileManager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import thercn.terminal.Log;
import thercn.terminal.R;

public class FileAdapter<T> extends RecyclerView.Adapter<FileAdapter.ViewHolder> {

	List<File> files;
	Context context;
	RecyclerView view;
	public static File parentDir;
	public FileAdapter(Context context, List<File> files,RecyclerView view) {
		this.context = context;
		this.files = files;
		this.view = view;
	}
	
	public File getParentDir() {
		
		if (files != null) {
			Log.e("","当前get目录:"+parentDir.toString());
			return parentDir;
		}
		return null;
	}
	
	public File getItem(int position) {
		return files.get(position);
	}

	@Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.file_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FileAdapter.ViewHolder holder, int position) {

        final File selectedFile = files.get(position);
		Log.e("","已添加:" + selectedFile.getAbsolutePath() +",位置:" + position);
		if (position == 0) {
			holder.fileName.setText("..");
		} else {
			holder.fileName.setText(selectedFile.getName());
		}
        

        if (selectedFile.isDirectory()) {
			Bitmap fileImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_folder);
			holder.fileIcon.setImageBitmap(fileImage);
		} else {
			Bitmap fileImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_file);
			holder.fileIcon.setImageBitmap(fileImage);
		}

		long lastModifiedTime = selectedFile.lastModified();
        Date lastModifiedDate = new Date(lastModifiedTime);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = dateFormat.format(lastModifiedDate);
		
		String permission = "";
		if (selectedFile.canRead()) {
			permission = permission + "r";
		} else {
			permission = permission + "-";
		}
		if (selectedFile.canWrite()) {
			permission = permission + "w";
		} else {
			permission = permission + "-";
		}
		if (selectedFile.canExecute()) {
			permission = permission + "x";
		} else {
			permission = permission + "-";
		}
		holder.fileTime.setText(formattedDate + " " + permission);
		holder.itemView.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					if (selectedFile.isDirectory()) {
						parentDir = selectedFile.getParentFile();
						Log.e("","当前父目录:"+parentDir.toString());
						List<File> newFiles = new ArrayList<File>();
						newFiles.add(selectedFile.getParentFile());
						File[] newDir = FileManagerActivity.getFiles(selectedFile.getAbsolutePath());
						for (int i = 0; i < newDir.length; i++) {
							newFiles.add(newDir[i]);
						}
						view.setAdapter(new FileAdapter<File>(context,newFiles, view));
					}
				}
			});
		
		holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
				@Override
				public boolean onLongClick(View v) {
					//长按菜单后续实现
					return false;
				}
			});

    }
	@Override
    public int getItemCount() {
        return files.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView fileName;
		ImageView fileIcon;
		TextView fileTime;

        public ViewHolder(View itemView) {
            super(itemView);
            fileName = itemView.findViewById(R.id.file_list_name);
            fileIcon = itemView.findViewById(R.id.file_list_image);
			fileTime = itemView.findViewById(R.id.file_list_time);
        }
    }

}
