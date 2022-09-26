package com.app.bookshelf.ui.TopStories;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.app.bookshelf.R;
import com.app.bookshelf.databinding.FragmentTopStoriesBinding;
import com.app.bookshelf.model.MyStoriesResult;
import com.app.bookshelf.ui.TopStories.Adapter.TopStoriesAdapter;
import com.app.bookshelf.ui.auth.AuthViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;
import java.util.List;


public class TopStoriesFragment extends Fragment implements TopStoriesAdapter.ViewHolder.onTopStoriesListener {
    TopStoriesViewModel listViewModel;
    AuthViewModel authViewModel;
    FragmentTopStoriesBinding binding;
    TopStoriesAdapter mAdapter;
    String selectedSection = "home";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentTopStoriesBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewGreeting();
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        listViewModel = new ViewModelProvider(this).get(TopStoriesViewModel.class);
        listViewModel.getTopStorieslist(selectedSection);

        listViewModel.listMutableLiveData.observe(getActivity(), new Observer<List<MyStoriesResult>>() {
            @Override
            public void onChanged(List<MyStoriesResult> myStoriesResults) {
                mAdapter = new TopStoriesAdapter(myStoriesResults, getContext(), TopStoriesFragment.this);
                binding.recyclerview.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();


            }
        });




        binding.searchImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = binding.edittextSearch.getText().toString().trim();
                if (!binding.edittextSearch.equals("")) {
                    listViewModel.Filter(selectedSection, title);
                }
            }
        });


        //spinner
        ArrayAdapter<String> aa = new ArrayAdapter<String>
                (getContext(), android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.section_Spinner)) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }

        };

        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.sectionSpinner.setAdapter(aa);
        binding.sectionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String sectionType = String.valueOf(binding.sectionSpinner.getSelectedItem());

                if (position > 0) {
                    if (sectionType.equals(getString(R.string.arts))) {
                        selectedSection = "arts";
                        listViewModel.getTopStorieslist(selectedSection);
                    } else if (sectionType.equals(getString(R.string.automobiles))) {
                        selectedSection = "automobiles";
                        listViewModel.getTopStorieslist(selectedSection);
                    } else if (sectionType.equals(getString(R.string.books))) {
                        selectedSection = "books";
                        listViewModel.getTopStorieslist(selectedSection);
                    } else if (sectionType.equals(getString(R.string.business))) {
                        selectedSection = "business";
                        listViewModel.getTopStorieslist(selectedSection);
                    } else if (sectionType.equals(getString(R.string.fashion))) {
                        selectedSection = "fashion";
                        listViewModel.getTopStorieslist(selectedSection);
                    } else if (sectionType.equals(getString(R.string.food))) {
                        selectedSection = "food";
                        listViewModel.getTopStorieslist(selectedSection);
                    } else if (sectionType.equals(getString(R.string.health))) {
                        selectedSection = "health";
                        listViewModel.getTopStorieslist(selectedSection);
                    } else if (sectionType.equals(getString(R.string.home))) {
                        selectedSection = "home";
                        listViewModel.getTopStorieslist(selectedSection);
                    } else if (sectionType.equals(getString(R.string.insider))) {
                        selectedSection = "insider";
                        listViewModel.getTopStorieslist(selectedSection);
                    } else if (sectionType.equals(getString(R.string.magazine))) {
                        selectedSection = "magazine";
                        listViewModel.getTopStorieslist(selectedSection);
                    } else if (sectionType.equals(getString(R.string.movies))) {
                        selectedSection = "movies";
                        listViewModel.getTopStorieslist(selectedSection);
                    } else if (sectionType.equals(getString(R.string.nyregion))) {
                        selectedSection = "nyregion";
                        listViewModel.getTopStorieslist(selectedSection);
                    } else if (sectionType.equals(getString(R.string.obituaries))) {
                        selectedSection = "obituaries";
                        listViewModel.getTopStorieslist(selectedSection);
                    } else if (sectionType.equals(getString(R.string.opinion))) {
                        selectedSection = "opinion";
                        listViewModel.getTopStorieslist(selectedSection);
                    } else if (sectionType.equals(getString(R.string.politics))) {
                        selectedSection = "politics";
                        listViewModel.getTopStorieslist(selectedSection);
                    } else if (sectionType.equals(getString(R.string.science))) {
                        selectedSection = "science";
                        listViewModel.getTopStorieslist(selectedSection);
                    } else if (sectionType.equals(getString(R.string.sports))) {
                        selectedSection = "sports";
                        listViewModel.getTopStorieslist(selectedSection);
                    } else if (sectionType.equals(getString(R.string.sundayreview))) {
                        selectedSection = "sundayreview";
                        listViewModel.getTopStorieslist(selectedSection);
                    } else if (sectionType.equals(getString(R.string.technology))) {
                        selectedSection = "technology";
                        listViewModel.getTopStorieslist(selectedSection);
                    } else if (sectionType.equals(getString(R.string.theater))) {
                        selectedSection = "theater";
                        listViewModel.getTopStorieslist(selectedSection);
                    } else if (sectionType.equals(getString(R.string.t_magazine))) {
                        selectedSection = "t-magazine";
                        listViewModel.getTopStorieslist(selectedSection);
                    } else if (sectionType.equals(getString(R.string.travel))) {
                        selectedSection = "travel";
                        listViewModel.getTopStorieslist(selectedSection);
                    } else if (sectionType.equals(getString(R.string.upshot))) {
                        selectedSection = "upshot";
                        listViewModel.getTopStorieslist(selectedSection);
                    } else if (sectionType.equals(getString(R.string.us))) {
                        selectedSection = "us";
                        listViewModel.getTopStorieslist(selectedSection);
                    } else if (sectionType.equals(getString(R.string.world))) {
                        selectedSection = "world";
                        listViewModel.getTopStorieslist(selectedSection);
                    }
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


    private void ViewGreeting() {

        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);
        if (timeOfDay <= 12) {
            binding.textView2.setText(getString(R.string.good_morning));
        } else if (timeOfDay < 16) {
            binding.textView2.setText(getString(R.string.good_afternoon));
        } else if (timeOfDay < 21) {
            binding.textView2.setText(getString(R.string.good_evening));
        } else {
            binding.textView2.setText(getString(R.string.good_evening));
        }

    }


    @Override
    public void onTopClick(int position) {
        listViewModel.listMutableLiveData.getValue().get(position);
        Bundle bundle = new Bundle();
        bundle.putString("url", listViewModel.listMutableLiveData.getValue().get(position).getUrl());
        Navigation.findNavController(getView()).navigate(R.id.storyDetailesFragment, bundle);

    }

    @Override
    public void Favorite(int position, boolean fav) {
        Boolean status = listViewModel.getLoggedStatus().getValue();
        Log.i("loggedStatus", "status " + listViewModel.getLoggedStatus().getValue().toString());
        if (fav) {
            if (status) {
                Navigation.findNavController(getView()).navigate(R.id.loginFragment);
                //Navigation.findNavController(getView()).navigate(R.id.signupFragment);
            }
            if (!status) {
                String tilte = listViewModel.listMutableLiveData.getValue().get(position).getTitle();
                String url = listViewModel.listMutableLiveData.getValue().get(position).getUrl();
                String author = listViewModel.listMutableLiveData.getValue().get(position).getByline();
                String date = listViewModel.listMutableLiveData.getValue().get(position).getPublished_date();
                String image = listViewModel.listMutableLiveData.getValue().get(position).getMultimedia().get(0).getUrl();
                //added to favorite section
                listViewModel.addMyFavoriteToDatabase(selectedSection,url,tilte,author,date,image);
            }
//            Log.i("Activity", "Favorite article " + '\n' +
//                    listViewModel.listMutableLiveData.getValue().get(position).getTitle() + '\n'
//            + fav);
        }
        if (!fav) {
            //unfavored
            //removed from favorite section
        }

    }


}