package com.rogelio.basecamp.TrackMAPI.Models;

import java.util.UUID;

public class TVSeries {

        private int tvSerId;
        private String seriesName;
        private String seriesDescription;
        private String director;

        public TVSeries(int tvSerId, String seriesName, String seriesDescription, String director) {
            this.tvSerId = tvSerId;
            this.seriesName = seriesName;
            this.seriesDescription = seriesDescription;
            this.director = director;
        }

        public int getTvSerId() {
            return tvSerId;
        }

        public String getSeriesName() {
            return seriesName;
        }

        public String getSeriesDescription() {
            return seriesDescription;
        }

        public String getDirector() {
            return director;
        }
}
