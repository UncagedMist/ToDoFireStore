package tbc.uncagedmist.todofirestore.Adapter;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import tbc.uncagedmist.todofirestore.Interface.ItemClickListener;
import tbc.uncagedmist.todofirestore.MainActivity;
import tbc.uncagedmist.todofirestore.Model.ToDo;
import tbc.uncagedmist.todofirestore.R;

public class ListItemAdapter extends RecyclerView.Adapter<ListItemAdapter.ListViewHolder>  {

    MainActivity mainActivity;
    List<ToDo> toDoList;

    public ListItemAdapter(MainActivity mainActivity, List<ToDo> toDoList) {
        this.mainActivity = mainActivity;
        this.toDoList = toDoList;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mainActivity.getBaseContext());
        View view = inflater.inflate(R.layout.item_layout,parent,false);

        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        holder.item_title.setText(toDoList.get(position).getTitle());
        holder.item_desc.setText(toDoList.get(position).getDescription());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                mainActivity.title.setText(toDoList.get(position).getTitle());
                mainActivity.description.setText(toDoList.get(position).getDescription());

                mainActivity.isUpdate = true;
                mainActivity.idUpdate = toDoList.get(position).getId();

            }
        });
    }

    @Override
    public int getItemCount() {
        return toDoList.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener {

        ItemClickListener itemClickListener;
        TextView item_title,item_desc;

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);

            item_title = itemView.findViewById(R.id.itemTitle);
            item_desc = itemView.findViewById(R.id.itemDesc);
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            contextMenu.setHeaderTitle("Select the Action");
            contextMenu.add(0,0,getAdapterPosition(),"UPDATE");
            contextMenu.add(0,1,getAdapterPosition(),"DELETE");
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onClick(view,getAdapterPosition(),false);
        }
    }
}
