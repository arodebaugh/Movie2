package sla.org.movie;

import android.widget.ScrollView;
import android.widget.TextView;

public class Controller {


    private ScrollView scrollDown;
    private TextView appTextView;
    private TextView movieTitleTextView;
    private TextView rgrSideTextView;
    private TextView synopsisTextView;


    private Model Movie;

    Controller(TextView apt, TextView mttv, TextView rstv, TextView stv, ScrollView sd) {
        appTextView = apt;
        movieTitleTextView = mttv;
        rgrSideTextView = rstv;
        synopsisTextView = stv;
        scrollDown = sd;
    }
}