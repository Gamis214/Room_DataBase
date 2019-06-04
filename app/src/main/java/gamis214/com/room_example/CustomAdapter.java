package gamis214.com.room_example;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import gamis214.com.room_example.DataBase.Entity.Word;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ItemHolder>{

    private List<Word> lstWords;

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler,parent,false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        holder.bindView();
    }

    class ItemHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }

        public void bindView(){
            textView.setText(lstWords.get(getAdapterPosition()).getWord());
        }
    }

    public void setNewListWords(List<Word> newListWords){
        this.lstWords = newListWords;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(lstWords != null)
            return lstWords.size();
        else
            return 0;
    }

}
