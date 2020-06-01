package com.sdk.kotdemo

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View

class MyView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : View(context, attrs, defStyleAttr) {

    private var mPaint: Paint? = null

    private lateinit var mShader:Shader

    init {
        mPaint = Paint()
        mPaint?.isAntiAlias = true
        mPaint?.style = Paint.Style.FILL_AND_STROKE
        mPaint?.strokeWidth = 5f
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(500,500)
        Log.i("=======onMeasure", "${MeasureSpec.getSize(widthMeasureSpec)}==${MeasureSpec.getSize(heightMeasureSpec)}")
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        Log.i("==========", "onLayout")
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        mPaint?.setColor(Color.BLUE)
        mShader = LinearGradient(0f,0f,400f,400f, arrayOf(Color.RED,Color.BLUE).toIntArray(), arrayOf(0f,1f).toFloatArray(),Shader.TileMode.CLAMP)
        mPaint?.setShader(mShader)
        canvas?.drawCircle(200f,200f,190f,mPaint!!)

        canvas?.save()

        canvas?.restore()

        var matrix  = Matrix()
        canvas?.setMatrix(matrix)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
    }




}