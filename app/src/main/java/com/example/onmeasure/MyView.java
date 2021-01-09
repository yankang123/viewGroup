package com.example.onmeasure;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class MyView extends View {
private int defaultsize;
    public  MyView(Context context){
        super(context);
    }
    public MyView(Context context, AttributeSet attributeSet){
        super(context,attributeSet);

        TypedArray typedArray=context.obtainStyledAttributes(attributeSet,R.styleable.myView);
        defaultsize=typedArray.getDimensionPixelSize(R.styleable.myView_defau,100);
        typedArray.recycle();
    }

    private int getMySize(int defaultSize,int measureSpec){
        int mysize=defaultSize;
        int mode=MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        switch (mode){
            case MeasureSpec.UNSPECIFIED://如果没有指定大小，就设置为默认大小
                mysize=defaultSize;;
                break;
            case MeasureSpec.AT_MOST://测量模式为->当前为最大，对应wrapcontent
                mysize=size;
                break;
            case MeasureSpec.EXACTLY://固定值
                mysize=size;
                break;
        }
        return mysize;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width=getMySize(defaultsize,widthMeasureSpec);
        int height=getMySize(defaultsize,heightMeasureSpec);
        if(width<height){
            height=width;
        }else {
            width=height;
        }
        setMeasuredDimension(width,height);
    }
    @Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
        int r=getMeasuredHeight()/2;
        int cenx=getLeft()+r;
        int ceny=getTop()+r;
        Paint paint=new Paint();
        paint.setColor(Color.GREEN);
        canvas.drawCircle(cenx,ceny,r,paint);
    }

}
