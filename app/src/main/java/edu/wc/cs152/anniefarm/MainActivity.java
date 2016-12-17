package edu.wc.cs152.anniefarm;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BitmapRepo.getInstance().setContext(this);

        setContentView(new GameSurfaceView(this));

    }
}
