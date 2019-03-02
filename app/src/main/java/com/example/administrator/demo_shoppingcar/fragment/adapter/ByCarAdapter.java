package com.example.administrator.demo_shoppingcar.fragment.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwie.addandsubstractor.AddDecreaseView;
import com.example.administrator.demo_shoppingcar.R;
import com.example.administrator.demo_shoppingcar.fragment.bean.ByCarBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * Author : 张自力
 * Created on time.
 */

public class ByCarAdapter extends XRecyclerView.Adapter<ByCarAdapter.ViewHolder> {

    private Context mcontext;
    private List<ByCarBean.ResultBean> list;

    public ByCarAdapter(Context mcontext, List<ByCarBean.ResultBean> list) {
        this.mcontext = mcontext;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(mcontext, R.layout.bycar_adapter,null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
//绑定数据
        ByCarBean.ResultBean resultBean = list.get(position);
        if (resultBean == null) {
            return ;
        }
        Glide.with(mcontext).load(resultBean.getPic()).into(holder.imgProduct);
        holder.txtProjectTitle.setText(resultBean.getCommodityName());
        holder.txtProjectPrice.setText(resultBean.getPrice() + "");
        //holder.cbProduct.setChecked();
        holder.addDecrease.setNum(resultBean.getCount());

        //长按删除
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //将删除按钮展示
                holder.llOnLongClickDelete.setVisibility(View.VISIBLE);
                //单选框隐藏
                holder.cbProduct.setVisibility(View.GONE);
                return true;
            }
        });
        //点击删除按钮  进行删除当前数据
        holder.txtProjectDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //解决方法一:
                list.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
            }
        });

       /* //商品复选框设置
        holder.cbProduct.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //点击复选框选中商品
                resultBean.setIschecked(isChecked);

                //计算这款商品总价格
                double totalPrice = resultBean.getPrice() * resultBean.getCount();

                if(!isChecked){ //没有选中就归零
                    totalPrice = -totalPrice;
                }
                //通过接口传递给外面
                mOnItemClickListener.onCheckedChange(isAllSeleted(),totalPrice);
            }
        });

        //1 加减器监听
        holder.addDecrease.setOnOnAddDecreaseClickListener(new AddDecreaseView.OnAddDecreaseClickListener() {
            @Override
            public void add(int num) {//点击加
                resultBean.setCount(num);//将商品加1
                if(resultBean.isIschecked()){
                    mOnItemClickListener.onNumChange(resultBean.getPrice());
                }
            }

            @Override
            public void decrease(int num) {//点击减号
                resultBean.setCount(num);//商品减1 同步计数器
                if(resultBean.isIschecked()){
                    mOnItemClickListener.onNumChange(-resultBean.getPrice());
                }
            }
        });


        //长按删除
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //将删除按钮展示
                holder.llOnLongClickDelete.setVisibility(View.VISIBLE);
                //单选框隐藏
                holder.cbProduct.setVisibility(View.GONE);
                return true;
            }
        });
        //点击删除按钮  进行删除当前数据
        holder.txtProjectDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //解决方法一:
                mList.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
            }
        });*/

    }

    @Override
    public int getItemCount() {
        if(list.size()!=0){
            return list.size();
        }else{
            return 0;
        }
    }

    class ViewHolder extends XRecyclerView.ViewHolder {
        private final CheckBox cbProduct;
        private final ImageView imgProduct;
        private final TextView txtProjectTitle;
        private final TextView txtProjectPrice;
        private final com.bwie.addandsubstractor.AddDecreaseView addDecrease;
        private final LinearLayout llOnLongClickDelete;
        private final TextView txtProjectDelete;
        public ViewHolder(View itemView) {

            super(itemView);

            cbProduct = itemView.findViewById(R.id.cb_product);//选择按钮
            imgProduct = itemView.findViewById(R.id.img_product);//产品
            txtProjectTitle = itemView.findViewById(R.id.txt_project_title);//商品介绍
            txtProjectPrice = itemView.findViewById(R.id.txt_project_price);//价格
            addDecrease = itemView.findViewById(R.id.add_decrease);//加减器

            //长按删除
            llOnLongClickDelete = itemView.findViewById(R.id.ll_onlongclick_delete);//删除容器
            txtProjectDelete = itemView.findViewById(R.id.txt_project_delete);//删除按钮
        }
    }

}
