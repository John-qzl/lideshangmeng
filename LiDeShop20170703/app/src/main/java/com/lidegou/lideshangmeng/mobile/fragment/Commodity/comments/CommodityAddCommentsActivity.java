package com.lidegou.lideshangmeng.mobile.fragment.Commodity.comments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseActivity;
import com.lidegou.lideshangmeng.mobile.util.BitmapUtils;
import com.lidegou.lideshangmeng.mobile.util.MyImageLoader;
import com.lidegou.lideshangmeng.mobile.util.dialog.Choose;

import java.io.File;

import static com.lidegou.lideshangmeng.mobile.R.id.add_pic;

/**
 * 商品评论
 *
 * @author Y
 * @version 0.1
 * @date 2016年8月22日09:09:11
 */

public class CommodityAddCommentsActivity extends BaseActivity implements CommodityCommentsContract.AddView {
    public static final String TAG = CommodityAddCommentsActivity.class.getSimpleName();
    public static final String COMMODITY = "order";
    //Presenter
    private CommodityCommentsContract.Presenter presenter;

    private ImageView ivCommodityImg;
    private TextView tvCommodityName;

    //评论列表
    private RecyclerView rvComments;
    //评论内容
    private EditText etComments;
    private Button btn_submit_comments;

    private String orderId;
    private String shopId;
    private String shopName;
    private String shopPic;

    private ImageView addPic;
    private RatingBar ratingBar;
    private Choose choosePictureDialog;
    private String picText;
    private String picType;
    private float comment_rank;

    private String needPath = null;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_commodity_comments;
    }

    @Override
    protected void init() {
        choosePictureDialog = new Choose(this);
        orderId = getIntent().getStringExtra("orderId");
        shopId = getIntent().getStringExtra("shopId");
        shopName = getIntent().getStringExtra("shopName");
        shopPic = getIntent().getStringExtra("shopPic");
        //初始化View
        findViewById(R.id.lin_back).setOnClickListener(this);
        addPic = (ImageView) findViewById(add_pic);
        addPic.setOnClickListener(this);
        ivCommodityImg = (ImageView) findViewById(R.id.iv_commodity_img);
        tvCommodityName = (TextView) findViewById(R.id.tv_comments_name);
        etComments = (EditText) findViewById(R.id.et_comments);
        btn_submit_comments = (Button) findViewById(R.id.btn_submit_comments);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        btn_submit_comments.setOnClickListener(this);

        tvCommodityName.setText(shopName + "");
        MyImageLoader.getInstance().loaderImage(shopPic, ivCommodityImg);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                comment_rank = rating;
            }
        });
    }

    @Override
    protected void viewClick(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            case R.id.btn_submit_comments:
                presenter.addComments();
                break;
            case add_pic:
                choosePictureDialog.show();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (presenter == null) {
            presenter = new CommodityCommentsPresenter(this);
        }
    }

    @Override
    public void showError(String msg) {
        showToast(msg);
    }

    @Override
    public String comment_rank() {
        return String.valueOf((int) comment_rank);
    }

    @Override
    public String content() {
        return etComments.getText().toString();
    }

    @Override
    public String order_id() {
        return orderId;
    }

    @Override
    public String goods_id() {
        return shopId;
    }

    @Override
    public String pic() {
        if (picText == null) {
            picText = "";
        }
        return picText;
    }

    @Override
    public String img_type() {
        if (picType == null) {
            picType = "";
        }
        return picType;
    }

    @Override
    public void callbackAddCommentsSuccess(String msg) {
        showToast(msg);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode != 0) {
                    needPath = choosePictureDialog.getSavePath() + 1;
                    cropBitmap(Uri.fromFile(new File(choosePictureDialog.getSavePath())), Uri.fromFile(new File(needPath)));
                }
                break;
            case 2:
                if (resultCode != 0) {
                    needPath = choosePictureDialog.getNowPath();
                    cropBitmap(data.getData(), Uri.fromFile(new File(needPath)));
                }
                break;
            case 1234:
                if (resultCode != 0) {
                    Bitmap bitmap = BitmapUtils.compressBitmap(needPath);
                    addPic.setImageBitmap(bitmap);
                    picText = BitmapUtils.bitmapToBase64(bitmap);
                    choosePictureDialog.dismiss();
                }
        }
    }


    public void cropBitmap(Uri inUri, Uri outUri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(inUri, "image/*");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outUri);
        // crop为true是设置在开启的intent中设置显示的view可以剪裁
        intent.putExtra("crop", "true");

        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        // outputX,outputY 是剪裁图片的宽高
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("return-data", true);
        intent.putExtra("noFaceDetection", true);
        startActivityForResult(intent, 1234);
    }
}
