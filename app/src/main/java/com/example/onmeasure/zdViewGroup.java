package com.example.onmeasure;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class zdViewGroup extends ViewGroup {

    int widthSize;
 int lastWidth=0;
 int lasteight=0;
    int heightSize;

    public zdViewGroup(Context context){
        super(context);
    }
    public zdViewGroup(Context context, AttributeSet attributeSet){
        super(context,attributeSet);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
measureChildren(widthMeasureSpec,heightMeasureSpec);//测量所有的子view
        int widthMode=MeasureSpec.getMode(widthMeasureSpec);
widthSize=MeasureSpec.getSize(widthMeasureSpec);
        int heightMode=MeasureSpec.getMode(heightMeasureSpec);
        heightSize=MeasureSpec.getSize(heightMeasureSpec);

        int childCount=getChildCount();//获得子View的数量

        if(childCount==0){
            setMeasuredDimension(0,0);//如果子View数量为0，则无必要
        }else{
            if(widthMode==MeasureSpec.AT_MOST&&heightMode==MeasureSpec.AT_MOST){//AT_MOST对应wrapcontent;
                int height=getTotleHeight();
                int width=getMaxChildWidth();
                setMeasuredDimension(width,height);
            }else if(heightMode==MeasureSpec.AT_MOST){
                setMeasuredDimension(widthSize,getTotleHeight());
            }else if(widthMode==MeasureSpec.AT_MOST){
                setMeasuredDimension(getMaxChildWidth(),heightSize);
            }else {
                setMeasuredDimension(widthSize,heightSize);
            }
        }

    }

    public int getMaxChildWidth(){
        int childCount = getChildCount();
        int maxWidth=0;
        for(int i=0;i<childCount;i++){
            View childView=getChildAt(i);
            if(childView.getMeasuredWidth()>maxWidth){
                maxWidth=childView.getMeasuredWidth();
            }
        }
        return maxWidth;
    }
    public int getTotleHeight(){
      int childCount = getChildCount();
      int totalHeight =0;
      for(int i=0;i<childCount;i++){
          View childView=getChildAt(i);
          totalHeight=totalHeight+childView.getMeasuredHeight();
      }
      return totalHeight;
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
          int count=getChildCount();
          int curHeight=t;
        View childf=getChildAt(0);
        int heightf=childf.getMeasuredHeight();
        int widthf=childf.getMeasuredWidth();
        lastWidth=1+widthf;
        lasteight=curHeight;
        childf.layout(1, curHeight, 1 + widthf, curHeight + heightf);
          for(int i=1;i<count;i++){

                View child=getChildAt(i);
                int height=child.getMeasuredHeight();
                int width=child.getMeasuredWidth();
                if(width+lastWidth<=widthSize){
                    child.layout(lastWidth,lasteight+1,width+lastWidth,curHeight+height);
                    lastWidth=lastWidth+width;
             curHeight=curHeight-height;
                }else {
                    child.layout(1,curHeight+1, 1 + width, curHeight + height);
                    lastWidth=1+width;
                    lasteight=curHeight+height;
                }

                curHeight=curHeight+height;
          }
    }
}
