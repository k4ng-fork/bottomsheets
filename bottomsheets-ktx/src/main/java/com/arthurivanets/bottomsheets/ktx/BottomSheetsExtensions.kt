/*
 * Copyright 2017 Arthur Ivanets, arthur.ivanets.l@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

@file:JvmName("BottomSheetsExtensions")

package com.arthurivanets.bottomsheets.ktx

import android.app.Activity
import androidx.fragment.app.Fragment
import com.arthurivanets.bottomsheets.sheets.ActionPickerBottomSheet
import com.arthurivanets.bottomsheets.sheets.CustomActionPickerBottomSheet
import com.arthurivanets.bottomsheets.sheets.adapters.actionpicker.ActionItem
import com.arthurivanets.bottomsheets.sheets.adapters.actionpicker.BaseActionItem
import com.arthurivanets.bottomsheets.sheets.config.Config
import com.arthurivanets.bottomsheets.sheets.listeners.OnItemSelectedListener
import com.arthurivanets.bottomsheets.sheets.model.Option


/**
 * Initializes and shows the [Option] Action Picker Bottom Sheet.
 *
 * @param title bottom sheet header title (optional)
 * @param options bottom sheet action options
 * @param onItemSelectedListener item selection listener
 */
@JvmOverloads
fun Fragment.showActionPickerBottomSheet(title : CharSequence = "",
                                         options : List<Option>,
                                         onItemSelectedListener : OnItemSelectedListener<Option>) : ActionPickerBottomSheet {
    assertAttachedToActivity()

    return activity!!.showActionPickerBottomSheet(
        title = title,
        options = options,
        onItemSelectedListener = onItemSelectedListener
    )
}


/**
 * Initializes and shows the [Option] Action Picker Bottom Sheet.
 *
 * @param title bottom sheet header title (optional)
 * @param options bottom sheet action options
 * @param onItemSelectedListener item selection listener
 */
@JvmOverloads
fun Activity.showActionPickerBottomSheet(title : CharSequence = "",
                                         options : List<Option>,
                                         onItemSelectedListener : OnItemSelectedListener<Option>) : ActionPickerBottomSheet {
    return ActionPickerBottomSheet.init(
        this,
        options.map(::ActionItem),
        Config.Builder(this)
            .title(title)
            .build()
    ).also {
        it.setOnItemSelectedListener { onItemSelectedListener.onItemSelected(it.itemModel) }
        it.show()
    }
}


/**
 * Initializes and shows the Custom Action Picker Bottom Sheet.
 * Your custom action items of type [T] will be utilized.
 *
 * @param title bottom sheet header title (optional)
 * @param items your custom bottom sheet action items
 * @param onItemSelectedListener item selection listener
 */
@JvmOverloads
fun <T : BaseActionItem<*, *, *>> Fragment.showCustomActionPickerBottomSheet(title : CharSequence = "",
                                                                             items : List<T>,
                                                                             onItemSelectedListener : OnItemSelectedListener<T>) : CustomActionPickerBottomSheet<T> {
    assertAttachedToActivity()

    return activity!!.showCustomActionPickerBottomSheet(
        title = title,
        items = items,
        onItemSelectedListener = onItemSelectedListener
    )
}


/**
 * Initializes and shows the Custom Action Picker Bottom Sheet.
 * Your custom action items of type [T] will be utilized.
 *
 * @param title bottom sheet header title (optional)
 * @param items your custom bottom sheet action items
 * @param onItemSelectedListener item selection listener
 */
@JvmOverloads
fun <T : BaseActionItem<*, *, *>> Activity.showCustomActionPickerBottomSheet(title : CharSequence = "",
                                                                             items : List<T>,
                                                                             onItemSelectedListener : OnItemSelectedListener<T>) : CustomActionPickerBottomSheet<T> {
    return CustomActionPickerBottomSheet.init(
        this,
        items,
        Config.Builder(this)
            .title(title)
            .build()
    ).also {
        it.setOnItemSelectedListener(onItemSelectedListener)
        it.show()
    }
}