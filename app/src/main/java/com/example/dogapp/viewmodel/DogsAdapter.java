package com.example.dogapp.viewmodel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dogapp.R;
import com.example.dogapp.databinding.DogsItemBinding;
import com.example.dogapp.model.DogBreed;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DogsAdapter extends RecyclerView.Adapter<DogsAdapter.ViewHolder> {

    private ArrayList<DogBreed> dogBreeds;

    public DogsAdapter(ArrayList<DogBreed> dogBreeds) {
        this.dogBreeds = dogBreeds;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.dogs_item, parent, false);
        DogsItemBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                                        R.layout.dogs_item,
                                        parent,
                                        false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DogsAdapter.ViewHolder holder, int position) {
        holder.binding.setDog(dogBreeds.get(position));
        Picasso.get().load(dogBreeds.get(position).getUrl()).into(holder.binding.ivImagedog);
    }

    @Override
    public int getItemCount() {
        return dogBreeds.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public DogsItemBinding binding;

        public ViewHolder(DogsItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;

            binding.ivImagedog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DogBreed dog = dogBreeds.get(getAdapterPosition());
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("dogBreed",dog);
                    Navigation.findNavController(view).navigate(R.id.detailsFragment,bundle);

                }
            });
        }
    }
}
