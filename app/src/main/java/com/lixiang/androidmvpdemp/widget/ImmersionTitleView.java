package com.lixiang.androidmvpdemp.widget;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import androidx.annotation.ColorInt;
import androidx.core.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xing.commonbase.R;


/**
 * Created by lixiang on 2018/1/31.
 */

public class ImmersionTitleView extends LinearLayout {

    //默认的顶部导航栏高度
    public static final int NAVIGATIONHEIGHT = 48;
    //默认中间的字体大小
    public static final int TEXTSISE = 15;

    private OnTitlerClickListener mListener;
    private TextView mTitleName;
    private ImageView mRightIV;
    private ImageButton mLeftIV;
    private View mBottomView;
    private FrameLayout mRightFl;
    //状态栏高度
    private int height = 0;
    private View mstate;
    private RelativeLayout ll;

    private boolean isStateGray = false;//当状态栏为白色的时候设置为透明灰色
    private boolean addmBottomView = false;

    public ImmersionTitleView(Context context) {
        this(context, null, 0);
    }

    public ImmersionTitleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ImmersionTitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData(attrs);
        initAttrs(attrs);
    }

    @SuppressLint("ResourceType")
    public void initData(AttributeSet attrs) {
        setOrientation(VERTICAL);
        if (getBackground() == null)
            setBackgroundResource(R.color.white);
        //background包括color和Drawable,这里分开取值
        Drawable background = getBackground();
        if (background instanceof ColorDrawable) {
            ColorDrawable colordDrawable = (ColorDrawable) background;
            int color = colordDrawable.getColor();
            if (color == -1) {
                isStateGray = true;
            } else {
                isStateGray = false;
            }
        }

        //最左侧按钮默认是返回
        mLeftIV = new ImageButton(getContext());
        try {
            TypedValue typedValue = new TypedValue();
            getContext().getTheme().resolveAttribute(android.R.attr.selectableItemBackground, typedValue, true);
            int[] attribute = new int[]{android.R.attr.selectableItemBackground};
            TypedArray attributes = getContext().getTheme().obtainStyledAttributes(typedValue.resourceId, attribute);
            mLeftIV.setBackground(attributes.getDrawable(0));
        } catch (Exception e) {
            e.printStackTrace();
        }


        mLeftIV.setImageResource(R.mipmap.ic_back);
        mLeftIV.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onBackClick(ImmersionTitleView.this, mLeftIV);
                } else {
                    if (getContext() instanceof Activity) {
                        ((Activity) getContext()).finish();
                    }

                }
            }
        });
        int px18 = (int) (getResources().getDisplayMetrics().density * 18 + 0.5);
        mLeftIV.setPadding(px18, 0, px18, 0);
        //标题
        mTitleName = new TextView(getContext());
        mTitleName.setTextSize(TypedValue.COMPLEX_UNIT_SP, TEXTSISE);
        //标题颜色
        mTitleName.setTextColor(ContextCompat.getColor(getContext(), R.color.c_111111));
        mTitleName.setSingleLine();
        mTitleName.setGravity(Gravity.CENTER);
        mTitleName.setEllipsize(TextUtils.TruncateAt.END);
        mTitleName.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null)
                    mListener.onTitleClick(ImmersionTitleView.this, mTitleName.getText().toString());
            }
        });
        //右边的布局
        mRightFl = new FrameLayout(getContext());
        //右边图片
        mRightIV = new ImageView(getContext());
        mRightIV.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) mListener.onRightClick(ImmersionTitleView.this, mRightIV);
            }
        });
        mRightIV.setPadding(px18, 0, px18, 0);
        mRightFl.addView(mRightIV, ViewGroup.LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        //横着的线性布局
        ll = new RelativeLayout(getContext());
        ll.addView(mTitleName, new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        ll.addView(mLeftIV, new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT));
        //居于右侧
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        ll.addView(mRightFl, params);
        //横线
        mBottomView = new View(getContext());
        mBottomView.setBackgroundResource(R.color.c_111111);
        //状态栏高度
        mstate = new View(getContext());

        //6.0以下状态栏字体颜色没有办法设置为灰色所以让状态栏不为纯白色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            if (isStateGray) {
                mstate.setBackgroundColor(getResources().getColor(R.color.c_33000000));
            } else {
                mstate.setBackgroundColor(getResources().getColor(R.color.transparent));
            }
        }

        int resourceId = getContext().getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            height = getContext().getResources().getDimensionPixelSize(resourceId);
        }
        //将线性布局和横线添加进来
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (attrs != null) {
                TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.MyTitleView);
                boolean showMstate = typedArray.getBoolean(R.styleable.MyTitleView_showMstate, true);
                if (showMstate) {
                    addView(mstate, new LayoutParams(LayoutParams.MATCH_PARENT, height));
                }
            } else {
                addView(mstate, new LayoutParams(LayoutParams.MATCH_PARENT, height));
            }

        }
        addView(ll, new LayoutParams(LayoutParams.MATCH_PARENT, dip2px(getContext(), NAVIGATIONHEIGHT)));
        addView(mBottomView, new LayoutParams(LayoutParams.MATCH_PARENT, dip2px(getContext(), 1)));
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public void setStatusBarColor() {
        mstate.setBackgroundColor(getResources().getColor(android.R.color.transparent));
    }

    public void initAttrs(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.MyTitleView);
            int mLayoutResource = typedArray.getResourceId(R.styleable.MyTitleView_uselayout, 0);
            //如果以上布局不支持可以进行自己设置布局样式
            if (mLayoutResource != 0) {
                ll.removeAllViews();
                ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dip2px(getContext(), NAVIGATIONHEIGHT));
                View view = inflate(getContext(), mLayoutResource, null);
                view.setBackgroundResource(R.color.transparent);
                view.setLayoutParams(layoutParams);
                ll.addView(view);
                typedArray.recycle();
                return;
            }
            addmBottomView = typedArray.getBoolean(R.styleable.MyTitleView_showLine, false);
            mTitleName.setText(typedArray.getString(R.styleable.MyTitleView_titleName));
            int color = typedArray.getColor(R.styleable.MyTitleView_titleColor, getResources().getColor(R.color.c_111111));
            mTitleName.setTextColor(color);
            setIsVisibleLine(addmBottomView);
            Drawable rightDrawable = typedArray.getDrawable(R.styleable.MyTitleView_rightRes);
            if (rightDrawable != null) {
                mRightIV.setImageDrawable(rightDrawable);
            }
            Drawable leftDrawable = typedArray.getDrawable(R.styleable.MyTitleView_leftRes);
            if (leftDrawable != null && mLeftIV != null) {
                mLeftIV.setImageDrawable(leftDrawable);
            }

            typedArray.recycle();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //绘制完之后设置高度，不然的话如果背景是图片的话会出现过高  设置的高度为状态栏高度加上导航栏高度默认为48dp
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        if (addmBottomView) {
            layoutParams.height = height + dip2px(getContext(), NAVIGATIONHEIGHT + 1);
        } else {
            layoutParams.height = height + dip2px(getContext(), NAVIGATIONHEIGHT);

        }
        this.setLayoutParams(layoutParams);
    }

    /**
     * 自定义监听
     */
    public void setOnTitlerClickListener(OnTitlerClickListener listener) {
        mListener = listener;
    }

    //下边的线是否可见
    public void setIsVisibleLine(boolean isVisible) {
        mBottomView.setVisibility(isVisible ? VISIBLE : GONE);
    }

    /**
     * @param titleText 标题
     */
    public void setTitle(String titleText) {
        mTitleName.setText(titleText);
    }

    public void setTitleColor(@ColorInt int color) {
        mTitleName.setTextColor(color);
    }

    public void setTitlesize(int size) {
        mTitleName.setTextSize(size);
    }

    //获得右边的iv对象,注意和{@link #setFLRightChild}冲突
    public ImageView getIVRight() {
        return mRightIV;
    }

    //获得返回的iv对象(注意返回键有padding属性)
    public ImageView getIVBack() {
        return mLeftIV;
    }

    /**
     * 自定义右侧的view可以根据不同需求自行设置
     *
     * @param view
     */
    public void setFLRightChild(View view) {
        mRightFl.removeAllViews();
        mRightFl.addView(view);
    }

    public abstract static class OnTitlerClickListener {
        protected void onBackClick(ImmersionTitleView TitlerView, ImageView IVBack) {
            if (TitlerView.getContext() instanceof Activity)
                ((Activity) TitlerView.getContext()).finish();
        }

        protected void onTitleClick(ImmersionTitleView TitlerView, String text) {
        }

        protected abstract void onRightClick(ImmersionTitleView TitlerView, ImageView IVRight);
    }

}
