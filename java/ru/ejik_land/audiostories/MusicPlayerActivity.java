/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ru.ejik_land.audiostories;

import android.app.Activity;
import android.media.browse.MediaBrowser;
import android.os.Bundle;

/**
 * Main activity for the music player.
 */
public class MusicPlayerActivity extends Activity
        implements BrowseFragment.FragmentDataHelper {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, BrowseFragment.newInstance(null))
                    .commit();
        }
    }

    @Override
    public void onMediaItemSelected(MediaBrowser.MediaItem item) {
        if (item.isPlayable()) {
            getMediaController().getTransportControls().playFromMediaId(item.getMediaId(), null);
            QueueFragment queueFragment = QueueFragment.newInstance();
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, queueFragment)
                    .addToBackStack(null)
                    .commit();
        } else if (item.isBrowsable()) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, BrowseFragment.newInstance(item.getMediaId()))
                    .addToBackStack(null)
                    .commit();
        }
    }
}
