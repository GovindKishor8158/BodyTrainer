package com.govind.admin.bodytrainer.Utility;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.DocumentsContract;
import android.provider.MediaStore;

import android.util.Base64;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

import com.govind.admin.bodytrainer.R;
import com.google.android.material.snackbar.Snackbar;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Admin on 19-Mar-19.
 */

public class Utils {

    public static String getTimeHr(String time) {
        String timenew = "";
        try {
            SimpleDateFormat _24HourSDF = new SimpleDateFormat("HH:mm");
            SimpleDateFormat _12HourSDF = new SimpleDateFormat("hh:mm a");
            Date _24HourDt = _24HourSDF.parse(time);
            timenew = _12HourSDF.format(_24HourDt);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timenew;
    }

    public static String getDateFormated(String datestring) {
        String text_date = "";
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(datestring);
            text_date = new SimpleDateFormat("dd MMM, yyyy").format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return text_date;
    }

    public static String getTime(String datestring) {
        String text_date = "";
        try {
            Date date = new SimpleDateFormat("HH:mm:ss").parse(datestring);
            text_date = new SimpleDateFormat("hh:mm a").format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return text_date;
    }

    public static String encodeToBase64(Bitmap image, Bitmap.CompressFormat compressFormat, int quality) {
        ByteArrayOutputStream byteArrayOS = new ByteArrayOutputStream();
        image.compress(compressFormat, quality, byteArrayOS);
        return Base64.encodeToString(byteArrayOS.toByteArray(), Base64.NO_WRAP);
    }

    public static void showSnackBar(String errorMsg, View parent) {
        Snackbar snack = Snackbar.make(parent, errorMsg, Snackbar.LENGTH_LONG);
        View view = snack.getView();
        TextView tv = (TextView) view.findViewById(R.id.snackbar_text);
        tv.setTextColor(Color.WHITE);
        snack.show();
    }


    public static void deleteCache(Context context) {
        try {
            File dir = context.getCacheDir();
            deleteDir(dir);
        } catch (Exception e) {
        }
    }

    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
            return dir.delete();
        } else if (dir != null && dir.isFile()) {
            return dir.delete();
        } else {
            return false;
        }
    }

    public static String stringWithFirstcap(String myString) {
        String upperString;
        if (myString != null) {
            myString.toLowerCase();
            upperString = myString.substring(0, 1).toUpperCase() + myString.substring(1);
        } else {
            upperString = "";
        }
        return upperString;
    }

    public static boolean isConnectionAvailable(Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager)
                activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void showSanckBar(View parent, String errorMsg) {
        Snackbar snack = Snackbar.make(parent, errorMsg, Snackbar.LENGTH_LONG);
        View view = snack.getView();
        TextView tv = (TextView) view.findViewById(R.id.snackbar_text);
        tv.setTextColor(Color.WHITE);
        snack.show();
    }

    public static boolean checkPermission(Context mContext, String Permission) {
        int result = ContextCompat.checkSelfPermission(mContext, Permission);
        if (result == PackageManager.PERMISSION_GRANTED) {

            return true;
        } else {
            return false;
        }
    }

    public static Bitmap getBitmapFromUri(Context context, Uri selectedImage, int requiredSize) {

        Bitmap bitmap = null;
        Bitmap rotatedBitmap = null;
        if (Build.VERSION.SDK_INT < 19) {
            String selectedImagePath = getRealPathFromURI(context, selectedImage);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(selectedImagePath, options);
            bitmap = BitmapFactory.decodeFile(selectedImagePath, decodeFile(options, requiredSize));
            try {
                ExifInterface exif = new ExifInterface(selectedImagePath);
                String orientString = exif.getAttribute(ExifInterface.TAG_ORIENTATION);
                int orientation = orientString != null ? Integer.parseInt(orientString) : ExifInterface.ORIENTATION_NORMAL;
                int rotationAngle = 0;
                switch (orientation) {
                    case ExifInterface.ORIENTATION_NORMAL:
                        rotationAngle = 0;
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_90:
                        rotationAngle = 90;
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_180:
                        rotationAngle = 180;
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_270:
                        rotationAngle = 270;
                        break;
                    case ExifInterface.ORIENTATION_UNDEFINED:
                        rotationAngle = 0;
                        break;
                    default:
                        rotationAngle = 90;
                }

                Matrix matrix = new Matrix();
                matrix.setRotate(rotationAngle, (float) bitmap.getWidth() / 2, (float) bitmap.getHeight() / 2);
                rotatedBitmap = Bitmap.createBitmap(bitmap, 0, 0, options.outWidth, options.outHeight, matrix, true);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            ParcelFileDescriptor parcelFileDescriptor;
            try {
                parcelFileDescriptor = context.getContentResolver().openFileDescriptor(selectedImage, "r");
                FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);
                bitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor, null, decodeFile(options, requiredSize));
                parcelFileDescriptor.close();
                try {
                    ExifInterface exif = new ExifInterface(getRealPathFromURI(context, selectedImage));
                    String orientString = exif.getAttribute(ExifInterface.TAG_ORIENTATION);
                    int orientation = orientString != null ? Integer.parseInt(orientString) : ExifInterface.ORIENTATION_NORMAL;
                    int rotationAngle = 0;
                    switch (orientation) {
                        case ExifInterface.ORIENTATION_NORMAL:
                            rotationAngle = 0;
                            break;
                        case ExifInterface.ORIENTATION_ROTATE_90:
                            rotationAngle = 90;
                            break;
                        case ExifInterface.ORIENTATION_ROTATE_180:
                            rotationAngle = 180;
                            break;
                        case ExifInterface.ORIENTATION_ROTATE_270:
                            rotationAngle = 270;
                            break;
                        case ExifInterface.ORIENTATION_UNDEFINED:
                            rotationAngle = 0;
                            break;
                        default:
                            rotationAngle = 90;
                    }
                    Matrix matrix = new Matrix();
                    matrix.setRotate(rotationAngle, (float) bitmap.getWidth() / 2, (float) bitmap.getHeight() / 2);
                    rotatedBitmap = Bitmap.createBitmap(bitmap, 0, 0, options.outWidth, options.outHeight, matrix, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (rotatedBitmap == null) {
            return bitmap;
        } else {
            return rotatedBitmap;
        }
    }

    private static String getRealPathFromURI(Context context, Uri contentURI) {
        String result;
        Cursor cursor = context.getContentResolver().query(contentURI, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
        if (cursor == null) { // Source is Dropbox or other similar local file path
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            result = cursor.getString(0);
            cursor.close();
        }
        return result;
    }

    private static BitmapFactory.Options decodeFile(BitmapFactory.Options options, int requiredSize) {
        // Find the correct scale value. It should be the power of 2.
        int width_tmp = options.outWidth, height_tmp = options.outHeight;
        int scale = 1;
        while (true) {
            if (width_tmp < requiredSize && height_tmp < requiredSize)
                break;
            width_tmp /= 2;
            height_tmp /= 2;
            scale *= 2;
        }

        // Decode with inSampleSize

        options.inJustDecodeBounds = false;
        options.inSampleSize = scale;

        return options;
    }

    private static byte[] loadFile(File file) throws IOException {
        InputStream is = new FileInputStream(file);

        long length = file.length();
        if (length > Integer.MAX_VALUE) {
            // File is too large
        }
        byte[] bytes = new byte[(int) length];

        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
                && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
            offset += numRead;
        }

        if (offset < bytes.length) {
            throw new IOException("Could not completely read file " + file.getName());
        }

        is.close();
        return bytes;
    }

    public static String encodeFileToBase64Binary(String Path)
            throws IOException {

        File file = new File(Path);
        byte[] bytes = loadFile(file);
        String encodedString = Base64.encodeToString(bytes, 1);
        return encodedString;
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static String getPath(Context mContext, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(mContext, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }

                // TODO handle non-primary volumes
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
                // Uri.parse("content://downloads/my_downloads"), Long.valueOf(id));
                return getDataColumn(mContext, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{
                        split[1]
                };

                return getDataColumn(mContext, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            return getDataColumn(mContext, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }
}
