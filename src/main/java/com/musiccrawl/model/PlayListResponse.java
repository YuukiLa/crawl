package com.musiccrawl.model;

import java.util.List;

/**
 * Created by Administrator on 2017/9/16.
 */
public class PlayListResponse {

        private boolean more;
        private List<PlayList> playlist;
        private int code;

        public void setMore(boolean more) {
            this.more = more;
        }

        public boolean getMore() {
            return more;
        }

        public void setPlaylist(List<PlayList> playlist) {
            this.playlist = playlist;
        }

        public List<PlayList> getPlaylist() {
            return playlist;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
}
