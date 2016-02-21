package com.igorzarut.criminalintent;

import android.graphics.drawable.BitmapDrawable;
import android.media.ExifInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ImageFragment extends DialogFragment {
    private static final String TAG = "ImageFragment";
    public static final String EXTRA_IMAGE_PATH = "com.igorzarut.criminalintent.imagepath";

    private ImageView mImageView;

    public static ImageFragment newInstance(String imagePath) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_IMAGE_PATH, imagePath);
        ImageFragment fragment = new ImageFragment();
        fragment.setArguments(args);
        fragment.setStyle(STYLE_NO_TITLE, 0);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        mImageView = new ImageView(getActivity());
        String path = (String) getArguments().getSerializable(EXTRA_IMAGE_PATH);
        BitmapDrawable image = PictureUtils.getScaledDrawable(getActivity(), path);

        mImageView.setImageDrawable(image);

        try {
            ExifInterface exif = new ExifInterface(path);
            String orient = exif.getAttribute(ExifInterface.TAG_ORIENTATION);

            switch(Integer.valueOf(orient)) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    Log.d(TAG, "orientation is: 90");
                    mImageView.setRotation(90);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    Log.d(TAG, "orientation is: 180");
                    mImageView.setRotation(180);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    Log.d(TAG, "orientation is: 270");
                    mImageView.setRotation(270);
                    break;
                default:
                    Log.d(TAG, "orientation is: normal");
            }
        } catch(Exception e) {
            Log.e(TAG, "exif error", e);
        }

        return mImageView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        PictureUtils.cleanImageView(mImageView);
    }
}
