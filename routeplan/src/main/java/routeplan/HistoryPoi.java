package routeplan;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Poi;
import com.amap.api.services.core.PoiItem;

import java.util.ArrayList;
import java.util.List;

/**
 * created by czh on 2018-03-07
 */

public class HistoryPoi {

    public static final int LIMIT=10;
    private final String NAME="route_plan";
    private final String HOME="home";
    private final String COMPANY="company";
    private final String ROUTELIST="history";
    private SharedPreferences mShareP;

    public HistoryPoi(Context context){
        mShareP=context.getSharedPreferences(NAME,Context.MODE_PRIVATE);
    }

    public Poi getHomePoi(){
        return StringToPoi(mShareP.getString(HOME,""));
    }

    public void updateHomePoi(Poi poi){
        mShareP.edit().putString(HOME,PoiToString(poi)).commit();
    }

    public Poi getCompanyPoi(){
        return StringToPoi(mShareP.getString(COMPANY,""));
    }

    public void updateCompanyPoi(Poi poi){
        mShareP.edit().putString(HOME,PoiToString(poi)).commit();
    }

    public List<RouteRecord> getHisttoryPois(){
        List<RouteRecord> list=new ArrayList<RouteRecord>();
        for (int i=0;i<LIMIT;i++){
            RouteRecord temp=new RouteRecord(mShareP.getString(""+i,""),i);
            if (temp.startPoi==null || temp.targetPoi==null){
                continue;
            }
            list.add(temp);
        }
        return list;
    }

    public void updateHistoryPoi(List<RouteRecord> list){
        if (list.size()<0){
            return;
        }
        SharedPreferences.Editor editor=mShareP.edit();
        for (int i=0;i<LIMIT;i++){
            if (i<list.size()){
                RouteRecord record=list.get(i);
                editor.putString(""+i,record.toString());
            }else {
                editor.putString(""+i,"");
            }
        }
        editor.commit();
    }

    public void addHistoryPoi(RouteRecord record){
        if (record.id>=0 && record.id<LIMIT){
            mShareP.edit().putString(record.id+"",record.toString()).commit();
        }
    }

    public void remove(int position){
        if (position<0 || position>=LIMIT){
            return;
        }
        mShareP.edit().putString(position+"","").commit();
    }

    public void Clear(){
        SharedPreferences.Editor editor=mShareP.edit();
        for (int i=0;i<LIMIT;i++){
            editor.putString(""+i,"");
        }
        editor.commit();
    }

    private static String PoiToString(Poi poi){
        String temp="";
        if (poi==null){
            return temp;
        }
        LatLng latLng=poi.getCoordinate();
        temp=poi.getName()+"/"+latLng.latitude+"/"+latLng.longitude;
        return temp;
    }

    private static Poi StringToPoi(String message){
        if (message==null || TextUtils.isEmpty(message)){
            return null;
        }
        String [] poiStrings=message.split("/");
        if (poiStrings.length<2){
            return null;
        }
        LatLng latLng=new LatLng(Double.parseDouble(poiStrings[1]),Double.parseDouble(poiStrings[2]));
        Poi poi=new Poi(poiStrings[0],latLng,"");
        return poi;
    }

    public static class RouteRecord{
        public int id;
        public Poi startPoi;
        public Poi targetPoi;

        public RouteRecord(Poi start,Poi target,int id){
            this.startPoi=start;
            this.targetPoi=target;
            this.id=id;
        }

        public RouteRecord(String message,int id){
            if (message==null || TextUtils.isEmpty(message) || !message.contains("-")){
                return;
            }
            String [] pois=message.split("-");
            this.startPoi=StringToPoi(pois[0]);
            this.targetPoi=StringToPoi(pois[1]);
            this.id=id;
        }

        @Override
        public String toString() {
            return PoiToString(startPoi)+"-"+PoiToString(targetPoi);
        }

        @Override
        public boolean equals(Object obj) {
            if (this==obj){
                return true;
            }
            if (obj instanceof RouteRecord){
                RouteRecord model=(RouteRecord)obj;
                if (this.startPoi.getName().equals(model.startPoi.getName())
                        && this.targetPoi.getName().equals(targetPoi.getName())){
                    return true;
                }
            }
            return  false;
        }

    }

}
