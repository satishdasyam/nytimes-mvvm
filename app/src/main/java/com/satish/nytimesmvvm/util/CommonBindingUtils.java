package com.satish.nytimesmvvm.util;

import android.widget.ImageView;
import androidx.databinding.BindingAdapter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

public final class CommonBindingUtils {

    private CommonBindingUtils() {

    }

    @BindingAdapter("app:imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).applyDefaultRequestOptions(new RequestOptions()
                .fallback(android.R.drawable.stat_notify_error)
                .error(android.R.drawable.stat_notify_error)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .format(DecodeFormat.PREFER_ARGB_8888))
                .load(url)
                .thumbnail(0.1f)
                .into(imageView);
    }
}

