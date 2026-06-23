package br.edu.ifsulminas.mch.homeworkhelper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import br.edu.ifsulminas.mch.homeworkhelper.model.Task;

public class TaskAdapter extends ArrayAdapter<Task> {

    private Context context;
    private List<Task> tasks;

    public TaskAdapter(@NonNull Context context, @NonNull List<Task> tasks) {
        super(context, R.layout.item_task, tasks);
        this.context = context;
        this.tasks = tasks;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_task, parent, false);
        }

        Task task = tasks.get(position);

        TextView txtName = convertView.findViewById(R.id.txt_task_name);
        TextView txtDesc = convertView.findViewById(R.id.txt_task_desc);
        TextView txtDate = convertView.findViewById(R.id.txt_task_date);

        if (task != null) {
            txtName.setText(task.getName());
            txtDesc.setText(task.getDescription());
            txtDate.setText("Entrega: " + task.getDateSubmission());
        }

        return convertView;
    }
}