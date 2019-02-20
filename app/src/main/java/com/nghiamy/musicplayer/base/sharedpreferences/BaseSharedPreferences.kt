package com.nghiamy.musicplayer.base.sharedpreferences

import android.content.Context
import android.content.SharedPreferences

class BaseSharedPreferences {
    companion object {
        class Builder{
            private lateinit var context:Context
            private lateinit var name:String
            private lateinit var preferences: SharedPreferences
            private lateinit var editor:SharedPreferences.Editor

            fun init(context: Context, name:String): Builder {
                this.context = context
                this.name = name
                preferences = context.getSharedPreferences(name, Context.MODE_PRIVATE)
                editor = preferences.edit()
                editor.apply()
                return this@Builder
            }

            fun putString(key:String, value:String) : Builder {
                editor.putString(key, value)
                return this@Builder
            }

            fun putInt(key:String, value:Int) : Builder {
                editor.putInt(key, value)
                return this@Builder
            }

            fun putLong(key:String, value:Long) : Builder {
                editor.putLong(key, value)
                return this@Builder
            }

            fun putFloat(key:String, value:Float) : Builder {
                editor.putFloat(key, value)
                return this@Builder
            }

            fun putBoolean(key:String, value:Boolean) : Builder {
                editor.putBoolean(key, value)
                return this@Builder
            }

            fun save() : Builder {
                editor.apply()
                return this@Builder
            }

            fun delete(key:String): Builder {
                editor.remove(key)
                return this@Builder
            }

            fun getSharedPreferences() : SharedPreferences {
                return this.preferences
            }
        }
    }
}