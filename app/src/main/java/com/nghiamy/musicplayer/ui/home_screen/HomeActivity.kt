package com.nghiamy.musicplayer.ui.home_screen

import android.Manifest
import android.annotation.TargetApi
import android.content.pm.PackageManager
import android.os.Build
import android.os.Build.VERSION
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.util.Log
import com.nghiamy.musicplayer.R
import com.nghiamy.musicplayer.base.common.MyFragmentViewPager
import com.nghiamy.musicplayer.base.ui.BaseActivity
import com.nghiamy.musicplayer.ui.all_songs.AllSongsFragment
import com.nghiamy.musicplayer.ui.folders.MusicFoldersFragment
import com.nghiamy.musicplayer.ui.playlist.PlaylistFragment
import kotlinx.android.synthetic.main.act_home.*
import java.lang.Exception
import javax.inject.Inject

class HomeActivity : BaseActivity(), IHomeContract.View {
    private val REQUEST_CODE_WRITE_EXTERNAL_STORAGE = 0
    private val PERMISSION_WRITE_EXTERNAL_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE

    @Inject lateinit var presenter: IHomeContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_home)

        checkPermissions()
    }

    private fun operate(){
        initUI()
        presenter.onCreate()
    }

    private fun checkPermissions() {
        if(ContextCompat.checkSelfPermission(this, PERMISSION_WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            val release = VERSION.RELEASE
            val version = release.split(".")[0]
            Log.d(TAG, "Android version: ${version}")

            if(version.toInt() >= 6){
                Log.d(TAG, "requesting permissions")
                requestPermissionForApi23Above()
            }else{
                Log.d(TAG, "noticing...")
                noticePermissionNotAllowed()
            }
        }else{
            operate()
        }
    }

    private fun noticePermissionNotAllowed() {
        Snackbar.make(tablayout_songs, R.string.notice_home_permissions_not_alllowed, Snackbar.LENGTH_INDEFINITE).show()
    }

    @TargetApi(Build.VERSION_CODES.M)
    private fun requestPermissionForApi23Above(){
        if(shouldShowRequestPermissionRationale(PERMISSION_WRITE_EXTERNAL_STORAGE)){
            Snackbar.make(tablayout_songs, R.string.notice_home_permissions_not_alllowed, Snackbar.LENGTH_INDEFINITE).show()
        }else{
            requestPermissions(arrayOf(PERMISSION_WRITE_EXTERNAL_STORAGE), REQUEST_CODE_WRITE_EXTERNAL_STORAGE)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        Log.d(TAG, "onRequestPermissionsResult | requestCode: ${requestCode} | granted: ${grantResults[0] == PackageManager.PERMISSION_GRANTED}")
        when(requestCode){
            REQUEST_CODE_WRITE_EXTERNAL_STORAGE -> {
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    operate()
                }
            }
            else -> {}
        }
    }

    private fun initUI() {
        initViewPager()
    }

    private fun initViewPager() {
        val pagerAdapter = MyFragmentViewPager(supportFragmentManager)

        val allSongsFragment = initOrGetFragment(AllSongsFragment::class.toString(), AllSongsFragment())
        val playlistFragment = initOrGetFragment(PlaylistFragment::class.toString(), PlaylistFragment())
        val musicFoldersFragment = initOrGetFragment(MusicFoldersFragment::class.toString(), MusicFoldersFragment())

        pagerAdapter.addItem(getString(R.string.title_tab_all_songs), allSongsFragment)
        pagerAdapter.addItem(getString(R.string.title__tab_playlist), playlistFragment)
        pagerAdapter.addItem(getString(R.string.title_tab_music_folders), musicFoldersFragment)

        viewpager_songs.adapter = pagerAdapter
        tablayout_songs.setupWithViewPager(viewpager_songs)

        tablayout_songs.setTabTextColors(ContextCompat.getColorStateList(this, R.color.white))
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    /**
     * deprecated
     */

    // TODO: only need to pass type Fragment Class instead an instance
    private fun <T : Fragment>initOrGetFragment(tag:String, clazz:T): T {
        var fragment:T? = null
        try{
            fragment = supportFragmentManager.findFragmentByTag(tag) as T?
        }catch (e:Exception){
            Log.i(TAG, "Find ${clazz.toString()} catched exception: ${e.message}")
        }

        return if(fragment==null) clazz else fragment
    }

}