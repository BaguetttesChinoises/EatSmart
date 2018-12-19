package com.example.rochet.eatsmart;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rochet.eatsmart.Model.Category;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Shows how to implement a simple Adapter for a RecyclerView.
 * Demonstrates how to add a click handler for each item in the ViewHolder.
 */
public class CategoryListAdapter extends
        RecyclerView.Adapter<CategoryListAdapter.MenuViewHolder> {

    private final LinkedList<Category> mCategoryList;
    private final LayoutInflater mInflater;
    private final Context mContext;

    class MenuViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        public static final String EXTRA_CATEGORY = "com.example.rochet.extra.CATEGORY";
        private final String LOG_TAG = MenuViewHolder.class.getSimpleName();
        public final TextView textMenuView;
        public final ImageView imageMenuView;
        final CategoryListAdapter mAdapter;

        /**
         * Creates a new custom view holder to hold the view to display in
         * the RecyclerView.
         *
         * @param itemView The view in which to display the data.
         * @param adapter The adapter that manages the the data and views
         *                for the RecyclerView.
         */
        public MenuViewHolder(View itemView, CategoryListAdapter adapter) {
            super(itemView);
            textMenuView = itemView.findViewById(R.id.menu_name);
            imageMenuView = itemView.findViewById(R.id.menu_image);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            //Handles the click on one item of the recycler view
            Log.d(LOG_TAG, "category clicked");

            int categoryPosition = getLayoutPosition();
            Category category = mCategoryList.get(categoryPosition);
            Log.d(LOG_TAG, "category  selectionne dans ladapter: " + category.getName());


            Intent intent = new Intent(mContext, MealActivity.class);
            //create a Bundle object for saving the category
            Bundle bundle = new Bundle();
            //Adding key value pairs to this bundle
            bundle.putSerializable("category_selected", category);
            intent.putExtra(EXTRA_CATEGORY, bundle);
            mContext.startActivity(intent);
            mAdapter.notifyDataSetChanged();
        }
    }

    public CategoryListAdapter(Context context, LinkedList<Category> categoryList) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        this.mCategoryList = categoryList;
    }

    /**
     * Called when RecyclerView needs a new ViewHolder of the given type to
     * represent an item.
     *
     * This new ViewHolder should be constructed with a new View that can
     * represent the items of the given type. You can either create a new View
     * manually or inflate it from an XML layout file.
     *
     * The new ViewHolder will be used to display items of the adapter using
     * onBindViewHolder(ViewHolder, int, List). Since it will be reused to
     * display different items in the data set, it is a good idea to cache
     * references to sub views of the View to avoid unnecessary findViewById()
     * calls.
     *
     * @param parent   The ViewGroup into which the new View will be added after
     *                 it is bound to an adapter position.
     * @param viewType The view type of the new View. @return A new ViewHolder
     *                 that holds a View of the given view type.
     */
    @Override
    public MenuViewHolder onCreateViewHolder(ViewGroup parent,
                                             int viewType) {
        // Inflate an item view.
        View mItemView = mInflater.inflate(
                R.layout.menu_item, parent, false);
        return new MenuViewHolder(mItemView, this);
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     * This method should update the contents of the ViewHolder.itemView to
     * reflect the item at the given position.
     *
     * @param holder   The ViewHolder which should be updated to represent
     *                 the contents of the item at the given position in the
     *                 data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(MenuViewHolder holder,
                                 int position) {
        // Retrieve the data for that position.
        String name = mCategoryList.get(position).getName();
        String image = mCategoryList.get(position).getImage();
        // Add the data to the view holder.
        holder.textMenuView.setText(name);
        // Todo : set the image menu
        // holder.imageMenuView.set
        // holder.imageMenuView.setImageDrawable(mCtx.getResources().getDrawable(product.getImage() attention cest un int));

    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return mCategoryList.size();
    }
}