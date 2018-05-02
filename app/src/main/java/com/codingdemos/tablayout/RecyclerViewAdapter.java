package com.codingdemos.tablayout;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

//import com.bumptech.glide.Glide;

import java.util.ArrayList;

//import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by olegtojgildin on 31.03.2018.
 */

// Унаследовали наш адаптер от RecyclerView.Adapter
// Здесь же указали наш собственный ViewHolder, который предоставит нам доступ к View-компонентам
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";
    // Предоставляет прямую ссылку на каждый View-компонент
    // Используется для кэширования View-компонентов и последующего быстрого доступа к ним
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Ваш ViewHolder должен содержать переменные для всех
        // View-компонентов, которым вы хотите задавать какие-либо свойства
        // в процессе работы пользователя со списком
        TextView imagename;
        TextView Carbohydrates;
        TextView Fat;
        TextView Protein;
        Button AddtoList;

        RelativeLayout parent_layout;

        // Мы также создали конструктор, который принимает на вход View-компонент строкИ
        // и ищет все дочерние компоненты
        public ViewHolder(View itemView) {
            super(itemView);
            imagename = itemView.findViewById(R.id.image_name);
            Carbohydrates = itemView.findViewById(R.id.carb);
            Fat = itemView.findViewById(R.id.fat);
            Protein = itemView.findViewById(R.id.protein);
            parent_layout = itemView.findViewById(R.id.parent_layout);
            AddtoList=itemView.findViewById(R.id.AddToList);
        }
    }


    //private ArrayList<String> mImageNames = new ArrayList<>();
    private ArrayList<String> mImage = new ArrayList<>();
    private ArrayList<Product> mproductList = new ArrayList<Product>();
private ArrayList<Product> FavoritList=new ArrayList<Product>();

    private Context mcontext;

    public RecyclerViewAdapter( ArrayList<String> image, ArrayList<Product> productList) {
       // mImageNames = imageNames;
        mImage = image;
        mproductList = productList;
       // mcontext = context;
    }

    //метод вызывается для создания объекта ViewHolder, в конструктор которого необходимо передать созданный View-компонент,
    // с которым в дальнейшем будут связываться java объекты.
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    //этот метод отвечает за связь java объекта и View.
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder:called.");
   /*     Glide.with(mcontext)
                .asBitmap()
                .load(mImage.get(position))
                .into(holder.image);*/

        holder.imagename.setText(mproductList.get(position).Name);
        holder.Carbohydrates.setText(Double.toString(mproductList.get(position).Carbohydrates));
        holder.Fat.setText(Double.toString( mproductList.get(position).Fat));
        holder.Protein.setText(Double.toString( mproductList.get(position).Protein));

        /*holder.parent_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick:clicked on: " + mImageNames.get(position));
                Toast.makeText(mcontext, mImageNames.get(position), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(mcontext, GalleryActivity.class);
                intent.putExtra("image_url", mImage.get(position));
                intent.putExtra("image_name", mImageNames.get(position));
                mcontext.startActivity(intent);
            }
        });*/
        holder.AddtoList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Context context = v.getContext();
                FavoritList.add(mproductList.get(position));
                notifyItemChanged(position);
               // Toast.makeText(mcontext,"Продукт добавлен в рацион"+mproductList.get(position).Name, Toast.LENGTH_SHORT).show();
            }
        });
    }

    //сообщает количество элементов в списке.
    @Override
    public int getItemCount() {
        return mproductList.size();
    }

}
