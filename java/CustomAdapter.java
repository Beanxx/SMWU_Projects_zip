package com.example.ysh.recyclerview;

import android.app.AlertDialog;
import android.content.Context;
import android.net.Network;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;

import java.util.ArrayList;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private ArrayList<Dictionary> mList;
    private Context mContext;


    TextView temperatureView;


    RequestQueue queue;

    public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        protected TextView mNickname;
        protected TextView mCity;
        protected TextView mContents;
        protected TextView mBody;



        public CustomViewHolder(View view) {
            super(view);

            this.mNickname = (TextView) view.findViewById(R.id.id_nickname);
            this.mCity = (TextView) view.findViewById(R.id.id_city);
            this.mContents = (TextView) view.findViewById(R.id.id_contents);
            this.mBody = (TextView) view.findViewById(R.id.id_body);


            view.setOnCreateContextMenuListener(this);
        }


        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

            MenuItem Edit = menu.add(Menu.NONE, 1001, 1, "편집");
            MenuItem Delete = menu.add(Menu.NONE, 1002, 2, "삭제");
            Edit.setOnMenuItemClickListener(onEditMenu);
            Delete.setOnMenuItemClickListener(onEditMenu);

        }

        private class ItemData {
            public String day;

        }

        private final MenuItem.OnMenuItemClickListener onEditMenu = new MenuItem.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {


                switch (item.getItemId()) {
                    case 1001:


                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

                        View view = LayoutInflater.from(mContext)
                                .inflate(R.layout.edit_box, null, false);
                        builder.setView(view);
                        final Button ButtonSubmit = (Button) view.findViewById(R.id.button_dialog_submit);
                        final EditText editTextID = (EditText) view.findViewById(R.id.edittext_id);
                        final EditText editTextCity = (EditText) view.findViewById(R.id.edittext_city);
                        final EditText editTextContents = (EditText) view.findViewById(R.id.edittext_contents);
                        final EditText editTextBody = (EditText) view.findViewById(R.id.edittext_body);

                        editTextID.setText(mList.get(getAdapterPosition()).getNickname());
                        editTextCity.setText(mList.get(getAdapterPosition()).getCity());
                        editTextContents.setText(mList.get(getAdapterPosition()).getContents());
                        editTextBody.setText(mList.get(getAdapterPosition()).getBody());


                        final AlertDialog dialog = builder.create();
                        ButtonSubmit.setOnClickListener(new View.OnClickListener() {


                            // 7. 수정 버튼을 클릭하면 현재 UI에 입력되어 있는 내용으로

                            public void onClick(View v) {
                                String strID = editTextID.getText().toString();
                                String strCity = editTextCity.getText().toString();
                                String strContents = editTextContents.getText().toString();
                                String strBody = editTextBody.getText().toString();

                                Dictionary dict = new Dictionary(strID, strCity, strContents, strBody);


                                mList.set(getAdapterPosition(), dict);


                                notifyItemChanged(getAdapterPosition());

                                dialog.dismiss();
                            }
                        });

                        dialog.show();

                        break;

                    case 1002:

                        mList.remove(getAdapterPosition());
                        notifyItemRemoved(getAdapterPosition());
                        notifyItemRangeChanged(getAdapterPosition(), mList.size());

                        break;

                }
                return true;
            }
        };
    }

    public CustomAdapter(Context context, ArrayList<Dictionary> list) {
        mList = list;
        mContext = context;

    }


    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item, viewGroup, false);

        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder viewholder, int position) {

        viewholder.mNickname.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        viewholder.mCity.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        viewholder.mContents.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        viewholder.mBody.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);


        viewholder.mNickname.setGravity(Gravity.CENTER);
        viewholder.mCity.setGravity(Gravity.CENTER);
        viewholder.mContents.setGravity(Gravity.CENTER);
        viewholder.mBody.setGravity(Gravity.CENTER);



        viewholder.mNickname.setText(mList.get(position).getNickname());
        viewholder.mCity.setText(mList.get(position).getCity());
        viewholder.mContents.setText(mList.get(position).getContents());
        viewholder.mBody.setText(mList.get(position).getBody());
    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }
}