package routeplan.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import routeplan.R;


/**
 * created by czh on 2018-01-26
 */

public class TopLayoutBehavior extends CoordinatorLayout.Behavior<View>{

    private boolean isHide=false;

    public TopLayoutBehavior() {
    }

    public TopLayoutBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof NestedScrollView;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        Log.d("czh","top of depency:"+dependency.getTop());

        View view=child.findViewById(R.id.topLayout);
        int height=view.getHeight();
        int bottom=view.getBottom();
        int top=dependency.getTop();
//        if ((top-height)<500 && !isHide){
//            ViewCompat.offsetTopAndBottom(view,-height);
//            isHide=true;
//            return true;
//        }else if ((top-height)>500 && isHide){
//            ViewCompat.offsetTopAndBottom(view,height);
//            isHide=false;
//            return true;
//        }
        if (top-100<height){
            ViewCompat.offsetTopAndBottom(view,(top-100-bottom));
            return true;
        }

        if (top>height+100 && bottom<height){
            ViewCompat.offsetTopAndBottom(view,(height-bottom));
//            view.setY(0);
        }
        return super.onDependentViewChanged(parent,child,dependency);

    }


}
