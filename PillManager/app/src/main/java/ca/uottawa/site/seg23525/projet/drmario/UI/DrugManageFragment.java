package ca.uottawa.site.seg23525.projet.drmario.UI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.daimajia.swipe.SimpleSwipeListener;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.BaseSwipeAdapter;
import com.daimajia.swipe.util.Attributes;
import com.melnykov.fab.FloatingActionButton;

import java.util.List;

import ca.uottawa.site.seg23525.projet.drmario.R;
import ca.uottawa.site.seg23525.projet.drmario.UI.helper.DAOListFragment;
import ca.uottawa.site.seg23525.projet.drmario.data.model.Medical.PrescribedMedication;


public class DrugManageFragment extends DAOListFragment {


    public DrugManageFragment(){

    }

    private ListView medList;
    private List<PrescribedMedication> meds;
    private ListViewAdapter adapter;
    private SwipeLayout swipe;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_drug_manage, container, false);

        checkDAO();

        medList = (ListView) rootView.findViewById(android.R.id.list);
        System.out.println(medList.toString());
        meds = dao.getAllPrescribedMedication();

        adapter = new ListViewAdapter(getActivity());
        adapter.setMode(Attributes.Mode.Single);

        medList.setAdapter(adapter);

        medList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((SwipeLayout) (medList.getChildAt(position - medList.getFirstVisiblePosition()))).open(true);
            }
        });

        medList.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.e("ListView", "OnTouch");
                return false;
            }
        });
        medList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "OnItemLongClickListener", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        medList.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                Log.e("ListView", "onScrollStateChanged");
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });

        medList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("ListView", "onItemSelected:" + position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.e("ListView", "onNothingSelected:");
            }
        });
        
        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.attachToListView(medList);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddMedicamentActivity.class);
                intent.putExtra("Session", "Add");
                startActivity(intent);
            }
        });
        

        return rootView;
    }

    public class ListViewAdapter extends BaseSwipeAdapter {

        private Context mContext;

        public ListViewAdapter(Context mContext) {
            this.mContext = mContext;
        }

        @Override
        public int getSwipeLayoutResourceId(int position) {
            return R.id.swipe;
        }

        @Override
        public View generateView(final int position, ViewGroup parent) {
            View v = LayoutInflater.from(mContext).inflate(R.layout.medication_item, null);
            final SwipeLayout swipeLayout = (SwipeLayout)v.findViewById(getSwipeLayoutResourceId(position));
            swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);
            swipeLayout.addDrag(SwipeLayout.DragEdge.Right, v.findViewById(R.id.right_view));
            swipeLayout.addDrag(SwipeLayout.DragEdge.Left, v.findViewById(R.id.left_view));
            swipeLayout.addSwipeListener(new SimpleSwipeListener() {
                @Override
                public void onOpen(SwipeLayout layout) {
                    YoYo.with(Techniques.Tada).duration(500).delay(100).playOn(layout.findViewById(R.id.trash));
                }
            });
            swipeLayout.setOnDoubleClickListener(new SwipeLayout.DoubleClickListener() {
                @Override
                public void onDoubleClick(SwipeLayout layout, boolean surface) {
                    Toast.makeText(mContext, "DoubleClick", Toast.LENGTH_SHORT).show();
                }
            });
            v.findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    meds.remove(position);
                    swipeLayout.close();
                    notifyDataSetChanged();
                }
            });

            v.findViewById(R.id.edit_med).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), AddMedicamentActivity.class);
                    intent.putExtra("Session", "Edit");
                    intent.putExtra("position", position);
                    startActivity(intent);
                }
            });
            return v;
        }

        @Override
        public void fillValues(int position, View convertView) {
            ImageView medImg = (ImageView) convertView.findViewById(R.id.med_image);
            TextView medName = (TextView) convertView.findViewById(R.id.med_name);
            TextView commonName = (TextView) convertView.findViewById(R.id.common_name);
            TextView dosage = (TextView) convertView.findViewById(R.id.dosage);
            TextView takeAt = (TextView) convertView.findViewById(R.id.take_at);

            medImg.setImageResource(R.mipmap.ic_launcher);
            medName.setText(meds.get(position).getMedication().getName());
            commonName.setText("\"" + meds.get(position).getMedication().getCommonName()+"\"");
            dosage.setText(meds.get(position).getDosage() + " mg");
            takeAt.setText("Taken at : 8:10 AM");
        }

        @Override
        public int getCount() {
            return meds.size();
        }

        @Override
        public Object getItem(int position) {
            return meds.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }


    }
}
