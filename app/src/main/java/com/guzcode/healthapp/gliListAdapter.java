package com.guzcode.healthapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

class gliListAdapter extends BaseAdapter {

    Context context;
    ArrayList<gliPatient> patientsData;
    private static LayoutInflater inflater = null;

    public gliListAdapter(Context context, ArrayList<gliPatient> patientsData) {
        this.context = context;
        this.patientsData = patientsData;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return patientsData.size();
    }

    @Override
    public Object getItem(int position) {
        return patientsData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.list_item, null);
        TextView listName = (TextView) vi.findViewById(R.id.listName);
        TextView listState = (TextView) vi.findViewById(R.id.listState);
        TextView listInfo = (TextView) vi.findViewById(R.id.listInfo);
        listName.setText(patientsData.get(position).name + " "+ patientsData.get(position).lName);
        listState.setText(patientsData.get(position).state);
        listInfo.setText("cc: " + patientsData.get(position).cc +" - "+ patientsData.get(position).BornDate +" - "+patientsData.get(position).eps);
        vi.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent nextAnm = new Intent( context , AnmEditActivity.class);
                nextAnm.putExtra("id",patientsData.get(position).id);
                context.startActivity(nextAnm);
            }
        });
        return vi;
    }
}
