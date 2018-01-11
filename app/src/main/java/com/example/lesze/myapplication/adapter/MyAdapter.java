package com.example.lesze.myapplication.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lesze.myapplication.DetailsActivity;
import com.example.lesze.myapplication.R;
import com.example.lesze.myapplication.database.MySqliteDatabase;
import com.example.lesze.myapplication.model.Shop;

import java.util.List;

/**
 * Created by lesze on 01.12.2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private Context context;
    private List<Shop> productItems;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private LayoutInflater layoutInflater;

    public MyAdapter(Context context, List<Shop> productItems) {
        this.context = context;
        this.productItems = productItems;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row, parent, false);
        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
        Shop shop = productItems.get(position);
        holder.productName.setText(shop.getNazwa());
        holder.productPrice.setText(String.valueOf(shop.getCena()));
        holder.productQuantity.setText(String.valueOf(shop.getIlosc()));

    }

    @Override
    public int getItemCount() {
        return productItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView productName;
        public TextView productPrice;
        public TextView productQuantity;


        public int id;


        public ViewHolder(final View itemView, Context myContext) {
            super(itemView);
            context = myContext;

            productName = itemView.findViewById(R.id.textView_name);
            productPrice = itemView.findViewById(R.id.textView_price);
            productQuantity = itemView.findViewById(R.id.textView_quantity);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int position = getAdapterPosition();
                    Shop shop = productItems.get(position);
                    editItem(shop);


//                    Intent intent = new Intent(context, DetailsActivity.class);
//                    intent.putExtra("name", shop.getNazwa());
//                    intent.putExtra("price", shop.getCena());
//                    intent.putExtra("quantity", shop.getIlosc());
//                    intent.putExtra("id", shop.getId());

//                    context.startActivity(intent);

                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int position = getAdapterPosition();
                    Shop shop = productItems.get(position);
                    deleteItem(shop.getId());
                    return false;
                }

            });
        }

        public void deleteItem(final int id) {

            dialogBuilder = new AlertDialog.Builder(context);
            layoutInflater = LayoutInflater.from(context);

            View view = layoutInflater.inflate(R.layout.confirmation_layout, null);

            Button yesButton = view.findViewById(R.id.yesButton);
            Button noButton = view.findViewById(R.id.noButton);

            dialogBuilder.setView(view);
            dialog = dialogBuilder.create();
            dialog.show();

            noButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });

            yesButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MySqliteDatabase db = new MySqliteDatabase(context);

                    db.deleteProduct(id);

                    productItems.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());

                    dialog.dismiss();
                }
            });

        }

        public void editItem(final Shop shop) {
            dialogBuilder = new AlertDialog.Builder(context);
            layoutInflater = LayoutInflater.from(context);

            View view = layoutInflater.inflate(R.layout.add_product, null);

            dialogBuilder.setView(view);
            dialog = dialogBuilder.create();
            dialog.show();

            EditText nazwa = view.findViewById(R.id.product_name_edit);
            EditText cena = view.findViewById(R.id.product_price_edit);
            EditText ilosc = view.findViewById(R.id.product_quantity_edit);
            Button saveButton = view.findViewById(R.id.save_button);
            Button dismissButton = view.findViewById(R.id.dismiss_button);

        saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MySqliteDatabase db = new MySqliteDatabase(context);

                    Shop shop = null;
                    shop.setNazwa(productName.getText().toString());
                    shop.setCena(Double.parseDouble(productPrice.getText().toString()));
                    shop.setIlosc(Double.parseDouble(productQuantity.getText().toString()));

                    if (!productName.getText().toString().isEmpty() &&
                            !productPrice.getText().toString().isEmpty() &&
                            !productQuantity.getText().toString().isEmpty()) {
                        db.updateProduct(shop);
                        notifyItemChanged(getAdapterPosition(), shop);
                    } else {
                        Snackbar.make(view, "try add product", Snackbar.LENGTH_LONG).show();
                    db.getAllShopProducts();

                    }
                }
            });
        dismissButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        }
    }
}