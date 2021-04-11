package com.example.mystackwidget

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import androidx.core.os.bundleOf

class StackRemoteViewsFactory(private val context: Context) :
    RemoteViewsService.RemoteViewsFactory {
    private val mWidgetItems = ArrayList<Bitmap>()

    override fun onCreate() {

    }

    override fun onDataSetChanged() {
        mWidgetItems.add(BitmapFactory.decodeResource(context.resources, R.drawable.darth_vader))
        mWidgetItems.add(BitmapFactory.decodeResource(context.resources, R.drawable.star_wars_logo))
        mWidgetItems.add(BitmapFactory.decodeResource(context.resources, R.drawable.storm_trooper))
        mWidgetItems.add(BitmapFactory.decodeResource(context.resources, R.drawable.starwars))
        mWidgetItems.add(BitmapFactory.decodeResource(context.resources, R.drawable.falcon))

        /*
         Kalau mau pakai db

            if (cursor != null) {
                cursor.close()
            }

            val identityToken = Binder.clearCallingIdentity()

            // querying ke database
            cursor = context.getContentResolver().query(CONTENT_URI, null, null, null, null)

            Binder.restoreCallingIdentity(identityToken)

        * */
    }

    override fun onDestroy() {
        TODO("Not yet implemented")
    }

    override fun getCount(): Int {
        return mWidgetItems.size
    }

    override fun getViewAt(p0: Int): RemoteViews {
        val rv = RemoteViews(context.packageName, R.layout.widget_item)
        rv.setImageViewBitmap(R.id.imageView, mWidgetItems[p0])

        val extras = bundleOf(
            ImageBannerWidget.EXTRA_ITEM to p0
        )

        val fillInIntent = Intent()
        fillInIntent.putExtras(extras)

        rv.setOnClickFillInIntent(R.id.imageView, fillInIntent)
        return rv
    }

    override fun getLoadingView(): RemoteViews? = null

    override fun getViewTypeCount(): Int = 1

    override fun getItemId(i: Int): Long = 0

    override fun hasStableIds(): Boolean = false

}