package com.example.rochet.eatsmart;

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

import com.example.rochet.eatsmart.Model.Meal;

import java.util.LinkedList;

/**
 * Shows how to implement a simple Adapter for a RecyclerView.
 * Demonstrates how to add a click handler for each item in the ViewHolder.
 */
public class MealListAdapter extends
        RecyclerView.Adapter<MealListAdapter.MealViewHolder> {



    private final LinkedList<Meal> mMealList;
    private final LayoutInflater mInflater;
    private final Context mContext;


    class MealViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        public static final String EXTRA_MEAL = "com.example.rochet.extra.MEAL";
        private final String LOG_TAG = MealListAdapter.class.getSimpleName();
        public final TextView textMenuView;
        public final ImageView imageMenuView;
        final MealListAdapter mAdapter;

        /**
         * Creates a new custom view holder to hold the view to display in
         * the RecyclerView.
         *
         * @param itemView The view in which to display the data.
         * @param adapter The adapter that manages the the data and views
         *                for the RecyclerView.
         */
        public MealViewHolder(View itemView, MealListAdapter adapter) {
            super(itemView);
            textMenuView = itemView.findViewById(R.id.menu_name);
            imageMenuView = itemView.findViewById(R.id.menu_image);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            //Handles the click on one item of the recycler view
            Log.d(LOG_TAG, "meal clicked");

            int mealPosition = getLayoutPosition();
            Meal meal = mMealList.get(mealPosition);
            Log.d(LOG_TAG, "meal  selectionne dans ladapter: " + meal.getName());


            Intent intent = new Intent(mContext, FoodDetailActivity.class);
            //create a Bundle object for saving the meal
            Bundle bundle = new Bundle();
            //Adding key value pairs to this bundle
            bundle.putSerializable("meal_selected", meal);
            intent.putExtra(EXTRA_MEAL, bundle);
            mContext.startActivity(intent);
            mAdapter.notifyDataSetChanged();
        }
    }

    public MealListAdapter(Context context, LinkedList<Meal> mealList) {
        mInflater = LayoutInflater.from(context);
        this.mMealList = mealList;
        mContext = context;

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
    public MealViewHolder onCreateViewHolder(ViewGroup parent,
                                             int viewType) {
        // Inflate an item view.
        View mItemView = mInflater.inflate(
                R.layout.menu_item, parent, false);
        return new MealViewHolder(mItemView, this);
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
    public void onBindViewHolder(MealViewHolder holder,
                                 int position) {
        // Retrieve the data for that position.
        String name = mMealList.get(position).getName();
        String image = mMealList.get(position).getImage();
        // Add the data to the view holder.
        holder.textMenuView.setText(name);
        // Return the id of the drawable you want to access.
        int imgDrawableId = mContext.getResources().getIdentifier(
                "com.example.rochet.eatsmart:drawable/" + image, null, null);
        // Sets the image in the imageview
        holder.imageMenuView.setImageResource(imgDrawableId);

    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return mMealList.size();
    }
}